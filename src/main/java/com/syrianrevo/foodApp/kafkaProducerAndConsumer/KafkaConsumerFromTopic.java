package com.syrianrevo.foodApp.kafkaProducerAndConsumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.syrianrevo.foodApp.kafkaConfig.Constants;
import com.syrianrevo.foodApp.kafkaConfig.KafkaConsumerConfig;


public class KafkaConsumerFromTopic{
	
	 // The Logger is used to show messages in the console.
    Logger logger = LoggerFactory.getLogger(KafkaConsumerFromTopic.class.getName());
    
    
    //Create Consumer
    
    KafkaConsumer<String, String> consumer = KafkaConsumerConfig.createConsumer(Constants.KAFKA_TOPIC);
    
    //Poll the data from the topic to the domain entry
    
    
    
}
	
