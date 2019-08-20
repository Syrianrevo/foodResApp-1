package com.syrianrevo.foodApp.model;

import java.util.ArrayList;
import java.util.List;

public class MenuContainer {
	
	private List<MenuItems> menuAList;
	

    public List<MenuItems> getMenuItems() {
        return menuAList;
    }

    public void setMenuItems(List<MenuItems> menuList) {
    	
    	
    	menuAList = menuList;
    }

    public String toString(){
        return menuAList.toString();  
     }
}
