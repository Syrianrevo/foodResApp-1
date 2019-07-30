package com.syrianrevo.foodApp.kafkaConfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


import com.syrianrevo.foodApp.model.Menu;

public class KafkaProducerConfig {

	public static Map<String, Object> producerConfig() {
		Map<String, Object> props = new HashMap<>();

		if (Constants.DEV) {
			props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER_LIST);
			
			

		} else {
			props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER);
		}

		props.put(ProducerConfig.CLIENT_ID_CONFIG, Constants.CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

		return props;
	}

	public static ProducerFactory<String, Menu> producerFactory() {
		JsonSerializer<Menu> jsonSerializer = new JsonSerializer<>();
		jsonSerializer.setAddTypeInfo(false);
		return new DefaultKafkaProducerFactory<String, Menu>(producerConfig(), Serdes.String().serializer(),
				jsonSerializer);
	}

	public static KafkaTemplate<String, Menu> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
