package com.syrianrevo.foodApp.repository;



import com.syrianrevo.foodApp.model.Entry;
import com.syrianrevo.foodApp.model.MenuItems;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


/**
 * Created by Ammar Mohrat.
 */
public interface EntryRepository extends CrudRepository<Entry, Long> {
   // List<MenuItems> findAll();
    List<Entry> findByItemName(String itemName);
    
    
    
    
    
}

