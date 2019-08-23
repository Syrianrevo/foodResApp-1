package com.syrianrevo.foodApp.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.syrianrevo.foodApp.kafkaConfig.Constants;
import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaConsumerFromTopic;
import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaProducer;
import com.syrianrevo.foodApp.model.Entry;
import com.syrianrevo.foodApp.model.JsonProducer;
import com.syrianrevo.foodApp.model.Menu;
import com.syrianrevo.foodApp.model.MenuItems;


import com.syrianrevo.foodApp.repository.MenuRepository;
import com.syrianrevo.foodApp.service.MenuService;

/**
 * Created by Ammar Mohrat.
 * 
 */
@Controller
public class HomeController {
	public static KafkaProducer shoppingCartProducer = new KafkaProducer();
	
	
	
	  // ADD
	  
	  @RequestMapping(value = "/entry", method = RequestMethod.GET) public String
	  newMenuItems(Model model) {
	  model.addAttribute("pageTitle", "New Item");
	  model.addAttribute("givenAction", "/entry");
	  model.addAttribute("givenItemName", "");
	  model.addAttribute("givenItemDescription", "");
	  model.addAttribute("givenItemPrice", "");
	  model.addAttribute("givenItemQuantity", "");
	  model.addAttribute("givenItemCategory", "");
	  
	  return "entry"; }
	  
	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	public String addItem(@RequestParam String itemName, @RequestParam String itemDescription,

			@RequestParam String itemPrice, @RequestParam String itemQuantity, @RequestParam String itemCategory)
	{
		MenuItems menuItem = new MenuItems();
		menuItem.setItemName(itemName);
		menuItem.setItemDescription(itemDescription);
		menuItem.setItemPrice(itemPrice);
		menuItem.setItemQuantity(itemQuantity);
		menuItem.setItemCategory(itemCategory);

		try {
			JsonProducer.producer.send(menuItem);
		} catch (InterruptedException | ExecutionException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin/home";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(Model model) throws Exception{
       List<MenuItems> menuItems = KafkaConsumerFromTopic.menuArrayL;
    	 
       
        model.addAttribute("menuItems", menuItems);
       
        return "/order";
    }
	
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String reviewOrder(@RequestParam String itemName, @RequestParam String itemQuantity)	{
		System.out.println(itemName + itemQuantity); 
		
		return "/reviewOrder";
	}
	 
	// EDIT
	
	
	@RequestMapping(value = "/admin/updateItem/{itemName}", method = RequestMethod.GET)
	public String updateItem(@PathVariable(value = "itemName") String itemName, Model model) {
		model.addAttribute("givenItemName",itemName);
		List<MenuItems> menuItemsList = KafkaConsumerFromTopic.menuArrayL;
		for (MenuItems menuItems : menuItemsList) {
	        if (menuItems.getItemName().equals(itemName)) {
	        	model.addAttribute("givenItemDescription", menuItems.getItemDescription());
	    		model.addAttribute("givenItemPrice", menuItems.getItemPrice());
	    		model.addAttribute("givenItemQuantity", menuItems.getItemQuantity());
	    	    model.addAttribute("givenItemCategory", menuItems.getItemCategory());
	        }
	    }
		//MenuItems menuItems = MenuItemsRepository.findByItemName(itemName);
		//model.addAttribute("givenItemDescription", itemDescription);
		//model.addAttribute("givenItemPrice", itemPrice);
		//model.addAttribute("givenItemQuantity", itemQuantity);
	   // model.addAttribute("givenItemCategory", itemCategory);
	    
	    return "/admin/updateItem";

	}
	 
	
	@RequestMapping(value = "/reviewOrder/{itemName}", method = RequestMethod.GET)
	public String reviewOrder(@PathVariable(value = "itemName") String itemName, Model model) {
		List<MenuItems> menuItemsList = KafkaConsumerFromTopic.menuArrayL;
		for (MenuItems menuItems : menuItemsList) {
	        if (menuItems.getItemName().equals(itemName)) {
	        	
	        	MenuItems menuItem = new MenuItems();
	    		menuItem.setItemName(itemName);
	    		menuItem.setItemDescription(menuItems.getItemDescription());
	    		menuItem.setItemPrice(menuItems.getItemPrice());
	    		menuItem.setItemQuantity(menuItems.getItemQuantity());
	    		menuItem.setItemCategory(menuItems.getItemCategory());
	        
	    		 try {
	 				shoppingCartProducer.sendToShoppingCart(menuItem);
	 			} catch (InterruptedException | ExecutionException e) { // TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	        }
	        
	       
	    }
		return "/reviewOrder";
		
	}
	
	@RequestMapping(value = "/reviewOrder", method = RequestMethod.GET)
    public String reviewOrderPage(Model model) throws Exception{
       List<MenuItems> menuItems = KafkaConsumerFromTopic.shoppingList;
    	 
       
        model.addAttribute("menuItems", menuItems);
        System.out.println(menuItems.toString());
        
        return "/reviewOrder";
    }
	
	
	
	
	/*
	 * @RequestMapping(value = "/admin/updateItem", method = RequestMethod.POST)
	 * public String updateItem(@RequestParam String itemName, @RequestParam String
	 * itemDescription,
	 * 
	 * @RequestParam String itemPrice, @RequestParam String
	 * itemQuantity, @RequestParam String itemCategory) { MenuItems menuItem = new
	 * MenuItems(); menuItem.setItemName(itemName);
	 * menuItem.setItemDescription(itemDescription);
	 * menuItem.setItemPrice(itemPrice); menuItem.setItemQuantity(itemQuantity);
	 * menuItem.setItemCategory(itemCategory);
	 * 
	 * try { JsonProducer.producer.send(menuItem); } catch (InterruptedException |
	 * ExecutionException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return "redirect:/admin/home"; }
	 */
	

	// DELETE

	/*
	 * @RequestMapping(value = "/entry/delete/{id}", method = RequestMethod.GET)
	 * public String deleteEntry(@PathVariable(value = "id") Long entryId) {
	 * entryRepository.deleteById(entryId); return "redirect:/"; }
	 */

}
