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
import com.syrianrevo.foodApp.model.MenuItems;




@KafkaListener(topics = "menu")
public class KafkaConsumerFromTopic{
	public static MenuItems menuItems = new MenuItems();
	//I am just trying to print each entry item to the console. 
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerFromTopic.class);

	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }

	  @KafkaHandler
	  public void receive(Object obj) {
		  
	    //LOGGER.info("received menu='{}'", menu.toString());
	    
	    
	    
	    menuItems.addItems(getJson(obj));
	    System.out.println(menuItems);
	    latch.countDown();
	  }
	  
	  
	public static JsonObject getJson(Object obj) {

		Gson gson = new Gson();

		String json = gson.toJson(obj);

		JsonElement jElement = new JsonParser().parse(json);

		JsonObject jobject = jElement.getAsJsonObject();

		return jobject.getAsJsonObject("value");

	}
    

}