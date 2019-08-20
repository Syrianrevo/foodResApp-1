package com.syrianrevo.foodApp.model;




import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.JsonElement;
import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaConsumerFromTopic;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by Ammar Mohrat.
 */
//@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	


   
    
 
    
    private ArrayList<MenuItems> itemMenu;

	public Menu() {
		itemMenu = new ArrayList<>();
		
		
		
	}

	public void addMenuItem(MenuItems item) {
		
		itemMenu.add(item);
	

	}
	
	public ArrayList<MenuItems> getMenuItems() {
        return itemMenu;
    }
	
	public void setItemMenu(ArrayList<MenuItems> itemMenu) {
        this.itemMenu = itemMenu;
    }

	public String toString()
    {
		String s = " "; 
		for(int i = 0; i < itemMenu.size(); i++) {
			 s = itemMenu.toString(); 
		}
		
        return s;
    }

	
    
	

}
