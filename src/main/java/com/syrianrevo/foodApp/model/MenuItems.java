package com.syrianrevo.foodApp.model;



import java.lang.reflect.Type;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;



import com.google.gson.Gson;

import com.google.gson.JsonElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class MenuItems {



	private String itemName;

	private String itemDescription;

    private String itemPrice; 

    private String itemQuantity;

    private String itemCategory;
    

	//public static ArrayList<String> items;

    
    public MenuItems(JsonElement menuItems) {
    	
    	String in = menuItems.getAsJsonObject().get("itemName").toString();

		String id = menuItems.getAsJsonObject().get("itemDescription").toString();

		String ip = menuItems.getAsJsonObject().get("itemPrice").toString();

		String iq = menuItems.getAsJsonObject().get("itemQuantity").toString();

		String ic = menuItems.getAsJsonObject().get("itemCategory").toString();

		

		this.itemName = in.substring(1, in.length() - 1);
		this.itemDescription = id.substring(1, id.length() - 1);
		this.itemPrice = ip.substring(1, ip.length() - 1);
		this.itemQuantity = iq.substring(1, iq.length() - 1);
		this.itemCategory = ic.substring(1, ic.length() - 1);
    	
    }
    

    public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	
	public String toString() 
    { 
        return itemName + " " + itemDescription + " " + itemPrice + " " + itemQuantity + " " + itemCategory; 
    } 


	



}