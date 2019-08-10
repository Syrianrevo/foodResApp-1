package com.syrianrevo.foodApp.kafkaProducerAndConsumer;



import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.syrianrevo.foodApp.kafkaConfig.Constants;
import com.syrianrevo.foodApp.kafkaConfig.KafkaProducerConfig;

import com.syrianrevo.foodApp.model.Menu;
import com.syrianrevo.foodApp.model.MenuItems;



public class KafkaProducer {
	
	private KafkaTemplate<String, Menu> kafkaTemplate = KafkaProducerConfig.kafkaTemplate();

	public void send(MenuItems menuItems) throws InterruptedException, ExecutionException {
		Message<MenuItems> recordResponse = MessageBuilder.withPayload(menuItems)
				.setHeader(KafkaHeaders.TOPIC, Constants.KAFKA_TOPIC).build();
		RecordMetadata metadata = kafkaTemplate.send(recordResponse).get().getRecordMetadata();
		System.out.println("Record sent to partition: " + metadata.partition() + ", Offset: " + metadata.offset() +
				", Timestamp: " + metadata.timestamp());
	}
	
	

}
