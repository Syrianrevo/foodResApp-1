package com.syrianrevo.foodApp.model;

import java.util.ArrayList;

import com.google.gson.JsonElement;

public class Menu {
	
	private ArrayList<String> items;
    
    
    public Menu() {
    	items = new ArrayList<>();
    }
    
    public void addItems(JsonElement menuItem) {
    	String in = menuItem.getAsJsonObject().get("itemName").toString();
		String id = menuItem.getAsJsonObject().get("itemDescription").toString();
		String ip = menuItem.getAsJsonObject().get("itemPrice").toString();
		String iq = menuItem.getAsJsonObject().get("itemQuantity").toString();
		String ic = menuItem.getAsJsonObject().get("itemCategory").toString();
		
		items.add(in + "," + id + ",");
    }
    
    @Override
	public String toString() {
		return items.toString();
	}

}
