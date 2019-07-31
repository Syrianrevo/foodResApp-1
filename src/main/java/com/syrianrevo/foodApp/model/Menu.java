package com.syrianrevo.foodApp.model;




import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.JsonElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by Ammar Mohrat.
 */
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	


    private String itemName;

	private String itemDescription;

    private String itemPrice; 

    private String itemQuantity;

    private String itemCategory;
    
    
    
    
    public Menu(JsonElement menuItems) {
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
