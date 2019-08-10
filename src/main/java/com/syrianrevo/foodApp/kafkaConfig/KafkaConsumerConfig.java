package com.syrianrevo.foodApp.kafkaConfig;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;
//import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.config.AbstractKafkaListenerContainerFactory;
import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaConsumerFromTopic;
import com.syrianrevo.foodApp.model.Entry;
import com.syrianrevo.foodApp.model.MenuItems;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {


	public Map<String, Object> consumerConfig() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GROUP_ID);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}

	@Bean
	public ConsumerFactory<String, MenuItems> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
				new JsonDeserializer<>(MenuItems.class));
	}

	

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MenuItems> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MenuItems> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());	

		return factory;
	}

	@Bean
	public KafkaConsumerFromTopic receiver() {
		
		return new KafkaConsumerFromTopic();
	}
	
	

}