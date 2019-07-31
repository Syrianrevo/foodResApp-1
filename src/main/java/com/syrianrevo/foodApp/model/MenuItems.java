package com.syrianrevo.foodApp.model;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MenuItems {

	
public static ArrayList<String> items;
    
    public static Entry entry = new Entry();
    
    public MenuItems() {
    	items = new ArrayList<>();
    }
    
    public void addItems(JsonElement menuItem) {
    	
    	String in = menuItem.getAsJsonObject().get("itemName").toString();
		String id = menuItem.getAsJsonObject().get("itemDescription").toString();
		String ip = menuItem.getAsJsonObject().get("itemPrice").toString();
		String iq = menuItem.getAsJsonObject().get("itemQuantity").toString();
		String ic = menuItem.getAsJsonObject().get("itemCategory").toString();
		
		items.add(in + "," + id + "," + ip + "," + iq + "," + ic);
		
		
    }
    
    
    
    
    
    
    @Override
	public String toString() {
		return items.toString();
	}

	

}
