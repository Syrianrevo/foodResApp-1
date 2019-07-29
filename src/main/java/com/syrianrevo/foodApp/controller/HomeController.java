package com.syrianrevo.foodApp.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.syrianrevo.foodApp.model.Entry;
import com.syrianrevo.foodApp.model.JsonProducer;
import com.syrianrevo.foodApp.model.Menu;
import com.syrianrevo.foodApp.repository.EntryRepository;




/**
 * Created by Ammar Mohrat.
 * 
 */
@Controller
public class HomeController {
    //@Autowired
   // EntryRepository entryRepository;
    
    
    @GetMapping("/home")
    public ModelAndView home() throws FileNotFoundException, IOException {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        
      //  System.out.println(KafkaConsumerFromTopic.menu.toString());
        return modelAndView;
    }
   

	/*
	 * @RequestMapping("/mainmenu") public String home(Model model){ List<Entry>
	 * allEntries = entryRepository.findAll(); model.addAttribute("entries",
	 * allEntries); return "mainmenu"; }
	 */
    
	/*
	 * @RequestMapping("/order") public String order(Model model){ List<Entry>
	 * allEntries = entryRepository.findAll(); model.addAttribute("entries",
	 * allEntries);
	 * 
	 * return "order"; }
	 */
    
	/*
	 * // ADD
	 * 
	 * @RequestMapping(value = "/entry", method = RequestMethod.GET) public String
	 * newEntry(Model model) { model.addAttribute("pageTitle", "New Entry");
	 * model.addAttribute("givenAction", "/entry");
	 * model.addAttribute("givenItemName", "");
	 * model.addAttribute("givenItemDescription", "");
	 * model.addAttribute("givenItemPrice", "");
	 * model.addAttribute("givenItemQuantity", "");
	 * model.addAttribute("givenItemCategory", ""); return "entry"; }
	 */

	/*
	 * @RequestMapping(value = "/entry", method = RequestMethod.POST) public String
	 * addEntry(@RequestParam String itemName, @RequestParam String
	 * itemDescription, @RequestParam String itemPrice, @RequestParam String
	 * itemQuantity, @RequestParam String itemCategory) { Entry newEntry = new
	 * Entry(itemName, itemDescription, itemPrice, itemQuantity, itemCategory);
	 * entryRepository.save(newEntry); return "redirect:/entry"; }
	 */
    
    // EDIT 
	/*
	 * @RequestMapping(value = "/entry/{id}", method = RequestMethod.GET) public
	 * String editEntry(@PathVariable(value = "id") Long entryId, Model model) {
	 * Entry entry = entryRepository.findOne(entryId);
	 * model.addAttribute("pageTitle", "Edit Entry");
	 * model.addAttribute("givenAction", "/entry/" + entryId);
	 * model.addAttribute("givenItemName", entry.getItemName());
	 * model.addAttribute("givenItemDescription", entry.getItemDescription());
	 * model.addAttribute("givenItemPrice", entry.getItemPrice());
	 * model.addAttribute("givenItemQuantity", entry.getItemQuantity());
	 * model.addAttribute("givenItemCategory", entry.getItemCategory()); return
	 * "entry"; }
	 */

	/*
	 * @RequestMapping(value = "/entry/{id}", method = RequestMethod.POST) public
	 * String updateEntry(@PathVariable(value = "id") Long entryId,
	 * 
	 * @RequestParam String itemName,
	 * 
	 * @RequestParam String itemDescription,
	 * 
	 * @RequestParam String itemPrice,
	 * 
	 * @RequestParam String itemQuantity,
	 * 
	 * @RequestParam String itemCategory) { Entry entry =
	 * entryRepository.findOne(entryId); entry.setItemName(itemName);
	 * entry.setItemDescription(itemDescription); entry.setItemPrice(itemPrice);
	 * entry.setItemQuantity(itemQuantity); entry.setItemCategory(itemCategory);
	 * entryRepository.save(entry); return "redirect:/"; }
	 */
    // DELETE
	/*
	 * @RequestMapping(value = "/entry/delete/{id}", method = RequestMethod.GET)
	 * public String deleteEntry(@PathVariable(value = "id") Long entryId) {
	 * entryRepository.deleteById(entryId); return "redirect:/"; }
	 */
	
}

