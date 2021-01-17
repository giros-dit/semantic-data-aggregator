package myapps;

import java.time.Duration;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Suppressed;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.kstream.WindowedSerdes;
import org.apache.kafka.streams.state.KeyValueStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CPULoad {

	// window size within which the filtering is applied
	private static final int WINDOW_SIZE = 60;

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-stream-sample");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);


		Collection<String> topics = new ArrayList<String>();
		topics.add("metricsource-3");
		topics.add("metricsource-4");

		final StreamsBuilder builder = new StreamsBuilder();
		final KStream<String, String> source = builder.stream(topics);

		//source.foreach((key, value) -> System.out.println("1" + key + " => " + value));

		final KStream<String, String> metric_source = source.flatMapValues(value -> {
			ArrayList<String> array = new ArrayList<String>();
			try {
				//System.out.println("Topic value: " + value);
				JSONObject jso = new JSONObject(value);
				//value = jso.get("value").toString();
				JSONArray metric_value = new JSONArray(jso.get("value").toString());
				value = metric_value.get(1).toString();

				// IMPORTANTE MANTENER SI NO HAY TRANSFORMACIONES DE DATOS PREVIA!
				/*JSONObject data_value = new JSONObject(jso.get("value").toString());
				JSONObject data = new JSONObject(data_value.get("data").toString());
				JSONArray result = new JSONArray(data.get("result").toString());
				JSONObject metric = result.getJSONObject(0);
				JSONArray metric_value = new JSONArray(metric.get("value").toString());*/
				//System.out.println("Metric value: " + metric_value.get(1));
				//value = metric_value.get(1).toString();

				array.add(value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return array;
		});

		//metric_source.foreach((key, value) -> System.out.println("2 " + key + " => " + value));

		final KTable<Windowed<String>, String> value_source = metric_source.groupBy((key, value) -> "cpu_loads")
				.windowedBy(TimeWindows.of(Duration.ofSeconds(WINDOW_SIZE)))
				.reduce((value1, value2) -> {
					System.out.println("value CPU Load node-1: "+value1+" && value CPU Load node-2: "+value2);
					//return "reduce-" + Float.toString((Float.parseFloat(value1) +  Float.parseFloat(value2))/2);
					if(value2!=null) {
						if(value1.startsWith("reduce-")){
						     value1 = value1.split("-")[1];
						}
						return "reduce-" + Float.toString((Float.parseFloat(value1) +  Float.parseFloat(value2))/2);
					} else {
						return null;
					}
				});

		value_source.toStream().foreach((key, value) -> {
			if(value.startsWith("reduce-")){
				System.out.println(key + " => " + value.split("-")[1]);
			}
		});

		final Serde<Windowed<String>> windowedSerde = WindowedSerdes.timeWindowedSerdeFrom(String.class);

		value_source.toStream().filter((key, value) -> value.startsWith("reduce-")).flatMapValues(value -> {
			ArrayList<String> array = new ArrayList<String>();
			try {
				value = value.split("-")[1];
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				JSONObject result = new JSONObject();
				result.accumulate("metric_name", "CPULoad");
				result.accumulate("value", value);
				result.accumulate("timestamp", timestamp);
				array.add(result.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return array;
		})
		.to("metricprocessor-2", Produced.with(windowedSerde, Serdes.String()));

		Topology topology = builder.build();
		System.out.println(topology.describe());
		final KafkaStreams streams = new KafkaStreams(topology, props);
		final CountDownLatch latch = new CountDownLatch(1);

		// attach shutdown handler to catch control-c
		Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
			@Override
			public void run() {
				streams.close();
				latch.countDown();
			}
		});

		try {
			streams.start();
			latch.await();
		} catch (final Throwable e) {
			System.exit(1);
		}
		System.exit(0);
	}

}

