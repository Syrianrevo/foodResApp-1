package com.syrianrevo.foodApp.kafkaProducerAndConsumer;



import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.syrianrevo.foodApp.kafkaConfig.Constants;
import com.syrianrevo.foodApp.kafkaConfig.KafkaProducerConfig;
import com.syrianrevo.foodApp.model.Entry;



public class KafkaProducer {
	
	private KafkaTemplate<String, Entry> kafkaTemplate = KafkaProducerConfig.kafkaTemplate();

	public void send(Entry pojo) throws InterruptedException, ExecutionException {
		Message<Entry> recordResponse = MessageBuilder.withPayload(pojo)
				.setHeader(KafkaHeaders.TOPIC, Constants.KAFKA_TOPIC).build();
		RecordMetadata metadata = kafkaTemplate.send(recordResponse).get().getRecordMetadata();
		System.out.println("Record sent to partition: " + metadata.partition() + ", Offset: " + metadata.offset() +
				", Timestamp: " + metadata.timestamp());
	}
	
	

}
