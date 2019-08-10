package com.syrianrevo.foodApp.kafkaProducerAndConsumer;

import java.lang.reflect.Type;

import java.util.ArrayList;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.syrianrevo.foodApp.repository.EntryRepository;



@KafkaListener(topics = "menu")

public class KafkaConsumerFromTopic{
		
	  @Autowired
	  private EntryRepository entryRepository; 
	  
	  private long x = 0; 

	  //public static MenuItems menuItems = new MenuItems();
	  public static Menu menu = new Menu(); 
	  public static Entry entry = new Entry(); 
	  

	  @KafkaHandler

	  public void receive(Object obj) {
		  
		 MenuItems menuItems = new MenuItems(getJson(obj));
		
		 entry.setId(x);
		 entry.setItemName(menuItems.getItemName());
		 entry.setItemDescription(menuItems.getItemDescription());
		 entry.setItemPrice(menuItems.getItemPrice());
		 entry.setItemQuantity(menuItems.getItemQuantity());
		 entry.setItemCategory(menuItems.getItemCategory());
		 entryRepository.save(entry);
		 x++; 
		 menu.addMenuItem(menuItems);
		 
		
	  }

	  
	 
	public static JsonObject getJson(Object obj) {

		Gson gson = new Gson();

		String json = gson.toJson(obj);

		JsonElement jElement = new JsonParser().parse(json);

		JsonObject jobject = jElement.getAsJsonObject();

		return jobject.getAsJsonObject("value");

	}
}