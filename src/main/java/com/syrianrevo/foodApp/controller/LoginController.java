package com.syrianrevo.foodApp.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.syrianrevo.foodApp.kafkaProducerAndConsumer.KafkaConsumerFromTopic;
import com.syrianrevo.foodApp.model.Entry;
import com.syrianrevo.foodApp.model.Menu;
import com.syrianrevo.foodApp.model.MenuContainer;
import com.syrianrevo.foodApp.model.MenuItems;
import com.syrianrevo.foodApp.model.User;
import com.syrianrevo.foodApp.repository.EntryRepository;
import com.syrianrevo.foodApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private EntryRepository entryRepositry; 
    
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
    	
    	User user1 = new User();
    	user1.setName("ammar");
    	user1.setLastName("mohrat");
    	user1.setEmail("a@a.com");
    	user1.setPassword("$2a$10$j3oUdFIf/hoaKx0UqbLlL.ebi5HbivHIBG6pkJd75awvq2/HZ8qDi");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

	/*
	 * @RequestMapping(value="/admin/home", method = RequestMethod.GET) public
	 * ModelAndView home(){ ModelAndView modelAndView = new ModelAndView();
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * User user = userService.findUserByEmail(auth.getName()); List<Entry>
	 * allEntries = entryRepositry.findAll(); modelAndView.addObject("entries",
	 * allEntries);
	 * 
	 * 
	 * modelAndView.setViewName("admin/home");
	 * 
	 * return modelAndView; }
	 */
    
    
    
    
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String home(Model model) throws Exception{
       List<MenuItems> menuItems = KafkaConsumerFromTopic.menuArrayL;
    	 
       
        model.addAttribute("menuItems", menuItems);
        System.out.println(menuItems.toString());
        return "/admin/home";
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(Model model) throws Exception{
       List<MenuItems> menuItems = KafkaConsumerFromTopic.menuArrayL;
    	 
       
        model.addAttribute("menuItems", menuItems);
       
        return "/order";
    }
   


}
