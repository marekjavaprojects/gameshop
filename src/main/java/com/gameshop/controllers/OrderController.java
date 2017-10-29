package com.gameshop.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;
import com.gameshop.service.OrderService;
import com.gameshop.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	ShoppingCart shoppingCart;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("/processOrder")
	public ModelAndView processOrder(ModelAndView model) {
		
		orderService.processOrderIntoDatabase(shoppingCart, userService.findByUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName()));
		model.setViewName("cart");
		return model;
		
	}

}
