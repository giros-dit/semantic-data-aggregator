import signal
from sqlite3 import Timestamp
import sys
import threading
import joblib
import argparse

#ADDED
from pyspark.sql import SparkSession
from pyspark.sql.functions import lit
import numpy as np
from datetime import datetime

# CONFIGURATION GLOBAL VARIABLES
KAFKA_BROKER = ""
KAFKA_TOPIC_CONSUME = ""
KAFKA_TOPIC_PRODUCE = ""
SPARK_MASTER =""
TIMESTAMP=datetime.now()

def crypto_detector():
    global TIMESTAMP
    print("Crypto Detection Engine started")
    # Spark Session Configuration
    scala_version = '2.12'
    spark_version = '3.3.0'
    # TODO: Ensure match above values match the correct versions
    #Packages to support data streaming
    packages = [f'org.apache.spark:spark-sql-kafka-0-10_{scala_version}:{spark_version}',
        'org.apache.kafka:kafka-clients:3.2.1']
    spark = SparkSession.builder\
        .appName("crypto-detector")\
        .master(SPARK_MASTER) \
        .config("spark.ui.showConsoleProgress", "false") \
        .config("spark.jars.packages", ",".join(packages))\
        .getOrCreate()

    sc = spark.sparkContext
    sc.setLogLevel('ERROR')

    print("Built Spark session")
    # Kafka Source Topic
    df = spark.read \
        .format("kafka") \
        .option("kafka.bootstrap.servers", KAFKA_BROKER) \
        .option("subscribe", KAFKA_TOPIC_CONSUME) \
        .option("auto.offset.reset", "latest") \
        .option("group_id", "event-gen") \
        .load()
    #Get value of timestamp to check if new data arrived
    df = df.where(df.timestamp > TIMESTAMP) if not(df.isEmpty()) else None
    timestamp = df.select("timestamp").collect()[-1][0] if not(df.isEmpty()) else None
    print(timestamp)

    if(timestamp is not None and timestamp > TIMESTAMP):
        # Get only value column from the dataframe
        df = df.selectExpr("CAST(value AS STRING)")

        # Load classifier and broadcast to executors.
        clf = sc.broadcast(joblib.load("RandomForestTrained.joblib"))
        print("ML module loaded")

        print("Initializing detector")
        def make_predictions(df, i):
            # PROCESS MESSAGE to obtain necessary values (transform into numpy array)
            featuresl = df.select("value").collect()
            featuresl = featuresl[i][0].split(",")
            # This will change if index of Anonymized & Preprocessed Netflow Data schema changes
            features_a = featuresl[62:]
            features_a = np.array(features_a).reshape(1,-1)
            # MAKE PREDICTION
            cryptoproba = clf.value.predict_proba(features_a)
            # Add prediction to output
            output = featuresl + ["Crypto", "Malware", str(cryptoproba[0][1])]
            output = ",".join(output)
            df = df.limit(1).withColumn("value", lit(output))
            return df
        
        for i in range(len(df.collect())):
            df_out = make_predictions(df,i)
            # Kafka Sink Topic
            df_out.write \
                .format("kafka") \
                .option("kafka.bootstrap.servers", KAFKA_BROKER) \
                .option("topic", KAFKA_TOPIC_PRODUCE) \
                .save()
            print("Detector Ready")
        #Update timestamp value
        TIMESTAMP = timestamp
            
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
    global SPARK_MASTER

    KAFKA_BROKER = args["broker"]
    KAFKA_TOPIC_CONSUME = args["consume"]
    KAFKA_TOPIC_PRODUCE = args["produce"]
    SPARK_MASTER = args["master"]

    print("Passed: -b " + KAFKA_BROKER + " -c " + KAFKA_TOPIC_CONSUME + " -p " + KAFKA_TOPIC_PRODUCE + " -m " + SPARK_MASTER)
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
    parser.add_argument('-m', '--master', metavar='path', required=False, default="local[*]",
                        help='Spark master URL to connect to standalone cluster')
    args = parser.parse_args()
    main(vars(args))
