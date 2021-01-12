package flinkkafka;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.sql.Timestamp;

import java.util.Properties;

public class TrafficRate {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka:9092");

		/*
		props.put("security.protocol", "SSL");
		props.put("ssl.endpoint.identification.algorithm", "");
		props.put("ssl.truststore.location", "client.truststore.jks");
		props.put("ssl.truststore.password", "secret");
		props.put("ssl.keystore.type", "PKCS12");
		props.put("ssl.keystore.location", "client.keystore.p12");
		props.put("ssl.keystore.password", "secret");
		props.put("ssl.key.password", "secret");
		*/

		props.put("group.id", "test-flink-input-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

		//Consume data stream from Kafka topic
		FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>("metricsource-2", new SimpleStringSchema(), props);
		DataStream<String> stringInputStream = environment.addSource(consumer);

		DataStream<String> metric_values = stringInputStream.map(new MapFunction<String, String>(){
		    @Override
		    public String map(String value) throws Exception {
				try {
					System.out.println("Topic value: " + value);
					JSONObject jso = new JSONObject(value);
					//value = jso.get("value").toString();

					// IMPORTANTE MANTENER SI NO HAY TRANSFORMACIONES DE DATOS PREVIA!
					JSONObject data_value = new JSONObject(jso.get("value").toString());
					JSONObject data = new JSONObject(data_value.get("data").toString());
					JSONArray result = new JSONArray(data.get("result").toString()); JSONObject
					metric = result.getJSONObject(0); JSONArray metric_value = new
					JSONArray(metric.get("value").toString());

					// System.out.println("Metric value: " + metric_value.get(1));
					value = metric_value.get(1).toString();
					//JSONObject result = new JSONObject();
					//result.append("value", value);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return value;
		    }
		});

		//Produce data stream for Kafka topic
		FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<String>("metricsource-2-rate", new SimpleStringSchema(), props);

		//WindowedStream<String, String, TimeWindow> win_values = metric_values.keyBy(value -> "traffic_record").window(TumblingEventTimeWindows.of(Time.seconds(10)));
		metric_values.keyBy(value -> "traffic_records").countWindow(2,1)/*windowAll(TumblingEventTimeWindows.of(Time.seconds(10)))*/
		/*DataStreamSink<String> values = win_values*/.reduce(new ReduceFunction<String>() {
		    @Override
		    public String reduce(String value1, String value2)
		    throws Exception {
		    	System.out.println(value2 + "-" + value1);
		    	String traffic_rate = "reduce-" + Integer.toString(Integer.parseInt(value2) -  Integer.parseInt(value1));
		    	System.out.println(traffic_rate);
		        return traffic_rate;
		    }
		}).filter(value -> value.startsWith("reduce-")).map(new MapFunction<String, String>(){
		    @Override
		    public String map(String value) throws Exception {
				try {
					JSONObject result = new JSONObject();
					result.accumulate("value", value.split("-")[1]);
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					result.accumulate("timestamp", timestamp.toString());
					value = result.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return value;
		    }
		}).addSink(producer);

		environment.execute("TrafficRate");
	}
}