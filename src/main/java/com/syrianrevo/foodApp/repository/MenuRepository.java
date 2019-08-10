package com.syrianrevo.foodApp.repository;

import java.util.ArrayList;
import java.util.List;

import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaConsumerFromTopic;
import com.syrianrevo.foodApp.model.*; 



public interface MenuRepository {
	
	// ArrayList<> List = KafkaConsumerFromTopic.menu; 
	
	List<MenuItems> findAll();
    List<MenuItems> findByItemName(String itemName);

}
