import signal
import sys
import threading
import joblib
import argparse

#ADDED
import pandas as pd
from pyspark.sql import SparkSession
from pyspark.sql import functions as F
from pyspark.sql.types import DoubleType
from pyspark.sql.functions import lit,concat_ws, split
import warnings


# CONFIGURATION GLOBAL VARIABLES
KAFKA_BROKER = ""
KAFKA_TOPIC_CONSUME = ""
KAFKA_TOPIC_PRODUCE = ""
headers = 'ts,te,td,sa,da,sp,dp,pr,flg,fwd,stos,ipkt,ibyt,opkt,obyt,in,out,sas,das,smk,dmk,dtos,dir,nh,nhb,svln,dvln,ismc,odmc,idmc,osmc,mpls1,mpls2,mpls3,mpls4,mpls5,mpls6,mpls7,mpls8,mpls9,mpls10,cl,sl,al,ra,eng,exid,tr,tpkt,tbyt,cp,prtcp,prudp,pricmp,prigmp,prother,flga,flgs,flgf,flgr,flgp,flgu'
feature_cols = ['inbound_packets_per_second','outbound_packets_per_second','inbound_unique_bytes_per_second','outbound_unique_bytes_per_second','inbound_bytes_per_packet','outbound_bytes_per_packet','ratio_bytes_inbound/bytes_outbound','ratio_packets_inbound/packets_outbound']

def crypto_detector():
    print("Crypto Detection Engine started")
    scala_version = '2.12'
    spark_version = '3.3.0'
    # TODO: Ensure match above values match the correct versions
    packages = [f'org.apache.spark:spark-sql-kafka-0-10_{scala_version}:{spark_version}',
        'org.apache.kafka:kafka-clients:3.2.1']
    spark = SparkSession.builder\
        .master("local")\
        .appName("crypto-detector")\
        .config("spark.jars.packages", ",".join(packages))\
        .config("spark.ui.showConsoleProgress", "false") \
        .getOrCreate()

    sc = spark.sparkContext
    sc.setLogLevel('ERROR')

    print("Built Spark session")
    # Kafka Source Topic
    df = spark \
        .readStream \
        .format("kafka") \
        .option("kafka.bootstrap.servers", KAFKA_BROKER) \
        .option("subscribe", KAFKA_TOPIC_CONSUME) \
        .option("auto.offset.reset", "latest") \
        .option("group_id", "event-gen") \
        .load()
    df = df.selectExpr("CAST(value AS STRING)")

    # Load classifier and broadcast to executors.
    clf = sc.broadcast(joblib.load("RandomForestTrained.joblib"))
    print("ML module loaded")

    # Function to make predictions given an Spark context, dataframe and model
    def make_predictions(df, feature_cols):
        # Define Pandas UDF
        @F.pandas_udf(returnType=DoubleType())
        def predict(*cols):
            # Columns are passed as a tuple of Pandas Series'.
            # Combine into a Pandas DataFrame
            X = pd.concat(cols, axis=1)
            # Make prediction.
            with warnings.catch_warnings(): #To remove "X has feature names, but RandomForestClassifier was fitted without feature names" warning
                warnings.simplefilter("ignore")
                predictions = clf.value.predict_proba(X)[0][1]
            # Return Pandas Series of predictions.
            return pd.Series(predictions)

        # Add columns for correct format
        df = df.withColumn("headers", lit(headers))
        df = df.withColumn("CryptoMalware", lit("Crypto,Malware"))
        # Make predictions on Spark DataFrame.
        df = df.withColumn("predictions", predict(*feature_cols))
        df = df.select(concat_ws(',',df.headers, *feature_cols, df.CryptoMalware, df.predictions).alias("value"))
        return df

    print("Initializing detector")

    # MAKE PREDICTION
    # Split value into different columns
    split_col = split(df['value'], ',')
    #Add these columns into new dataframe
    for i in range(62,70):
        df2 = df.withColumn(feature_cols[i-62],split_col.getItem(i))
        df = df2
    # Make prediction
    df_out = make_predictions(df2, feature_cols)
    # Function to output stream to multiple sinks
    def foreach_batch_function(df, epoch_id):
    #persist dataframe in case you are reusing it multiple times
        df.persist()
        # Write to multiple sinks
        print("WRITING TO SINK")
        df_out \
            .writeStream \
            .trigger(processingTime='5 seconds') \
            .outputMode("update") \
            .format("kafka") \
            .option("kafka.bootstrap.servers", KAFKA_BROKER) \
            .option("topic", KAFKA_TOPIC_PRODUCE) \
            .option("checkpointLocation", "./checkpoint") \
            .option("failOnDataLoss", "false") \
            .start()
        df_out \
            .writeStream \
            .trigger(processingTime='5 seconds') \
            .outputMode("append") \
            .format("console") \
            .option("truncate","false") \
            .option("failOnDataLoss", "false") \
            .start()
        print("DONE WRITING")
        #free memory
        df.unpersist()
        pass
    output = df_out.writeStream.foreachBatch(foreach_batch_function).start()
    print("Detector Ready")
    output.awaitTermination()

                
def handler(number, frame):
    sys.exit(0)


def safe_loop(fn):
    while True:
        try:
            fn()
        except SystemExit:
            print("Good bye!")
            return
        except Exception as e:
            print(e)
            return


def main(args):

    global KAFKA_BROKER
    global KAFKA_TOPIC_CONSUME
    global KAFKA_TOPIC_PRODUCE

    KAFKA_BROKER = args["broker"]
    KAFKA_TOPIC_CONSUME = args["consume"]
    KAFKA_TOPIC_PRODUCE = args["produce"]

    print("Passed: -b " + KAFKA_BROKER + " -c " + KAFKA_TOPIC_CONSUME + " -p " + KAFKA_TOPIC_PRODUCE)
    print("Launching detector")

    signal.signal(signal.SIGTERM, handler)
    detection = threading.Thread(target=safe_loop, args=[crypto_detector])
    detection.start()
    detection.join()
    


if __name__ == "__main__":

    parser = argparse.ArgumentParser(description='Create a Crypto Detection System connected with Kafka')
    parser.add_argument('-b', '--broker', metavar='path', required=True,
                        help='Address of the Kafka Broker')
    parser.add_argument('-c', '--consume', metavar='path', required=True,
                        help='Kafka Topic to consume from')
    parser.add_argument('-p', '--produce', metavar='path', required=True,
                        help='Kafka Topic to write the result to')
    args = parser.parse_args()
    main(vars(args))
