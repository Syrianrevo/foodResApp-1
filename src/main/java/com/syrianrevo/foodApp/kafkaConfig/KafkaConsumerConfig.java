package com.syrianrevo.foodApp.kafkaConfig;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerConfig{

	public static KafkaConsumer<String, String> createConsumer(String topic){
		
		Properties properties = new Properties();
		
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); 
		
		//Create Consumer 
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		
		//Subscrive Consumer to the topic
		consumer.subscribe(Arrays.asList(topic)); 
		
		return consumer;
	}
}