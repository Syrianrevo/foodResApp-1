package com.syrianrevo.foodApp.kafkaProducerAndConsumer;



import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;



import com.google.gson.Gson;

import com.google.gson.JsonElement;

import com.google.gson.JsonObject;

import com.google.gson.JsonParser;

import com.syrianrevo.foodApp.kafkaConfig.Constants;

import com.syrianrevo.foodApp.kafkaConfig.KafkaConsumerConfig;

import com.syrianrevo.foodApp.model.Entry;

import com.syrianrevo.foodApp.model.Menu;




@KafkaListener(topics = "entry")
public class KafkaConsumerFromTopic{

	//I am just trying to print each entry item to the console. 
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerFromTopic.class);

	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }

	  @KafkaHandler
	  public void receive(Entry entry) {
		  
	    LOGGER.info("received entry='{}'", entry.toString());
	    latch.countDown();
	  }
	  
	  

    

}