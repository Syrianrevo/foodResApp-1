package com.syrianrevo.foodApp.kafkaProducerAndConsumer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.syrianrevo.foodApp.kafkaConfig.Constants;
import com.syrianrevo.foodApp.kafkaConfig.KafkaConsumerConfig;
import com.syrianrevo.foodApp.model.Entry;
import com.syrianrevo.foodApp.model.Menu;


public class KafkaConsumerFromTopic{
	
	 // The Logger is used to show messages in the console.
    Logger logger = LoggerFactory.getLogger(KafkaConsumerFromTopic.class.getName());
    
    
     //Create Consumer
    
    //KafkaConsumer<String, Object> consumer = KafkaConsumerConfig.createConsumer(Constants.KAFKA_TOPIC);
    
    //Poll the data from the topic to the domain entry
    
    public static Menu menu = new Menu();
    
    @KafkaListener(topics= {"entry"})
    public static void recieve(Object obj) {
    	
    	//Entry entry = new Entry(getJson(obj)); 
    	menu.addItems(getJson(obj));
    	
    	//entry.toString();
    	
    	
    }
    
    public static JsonObject getJson(Object obj) {
    	
    	Gson gson = new Gson(); 
    	String json = gson.toJson(obj);
    	JsonElement jElement = new JsonParser().parse(json);
    	JsonObject jobject = jElement.getAsJsonObject();
    	return jobject.getAsJsonObject("value"); 
    }
    
}
	
