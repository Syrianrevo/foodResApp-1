package com.syrianrevo.foodApp.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.JsonElement;

/**
 * Created by Ammar Mohrat.
 */
@Entity
@Table(name = "entry")
public class Entry {

	@Id
	@Column(name = "entry_id")
	private Long id;
	@Column(name = "item_name")
	private String itemName;
	@Column(name = "item_description")
	private String itemDescription;
	@Column(name = "item_price")
	private String itemPrice;
	@Column(name = "item_quantity")
	private String itemQuantity;
	@Column(name = "item_category")
	private String itemCategory;

	
	

	

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String toString() {
		return id + " " + itemName + " " + itemDescription + " " + itemPrice + " " + itemQuantity + " " + itemCategory;
	}

}
