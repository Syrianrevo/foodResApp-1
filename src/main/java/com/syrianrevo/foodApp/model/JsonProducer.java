package com.syrianrevo.foodApp.model;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaProducer;


public class JsonProducer {
	
	public static KafkaProducer producer = new KafkaProducer();

	//long x = 1; 
	
	public JsonProducer(String file) throws FileNotFoundException, IOException {
		JsonParser jsonParser = new JsonParser();
		
		try(FileReader reader = new FileReader(file)){
			Object obj = jsonParser.parse(reader);
			JsonArray infoList = (JsonArray) obj;
			
			infoList.forEach(info -> {
				
				
				
				MenuItems menuItems = new MenuItems(info);
				
				try {
					producer.send(menuItems);
				} catch(InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
