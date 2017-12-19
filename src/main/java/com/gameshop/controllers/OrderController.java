package com.gameshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gameshop.entity.Product;
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

	@GetMapping("/processOrder")
	public ModelAndView processOrder(ModelAndView model) {
		List<Product> notAvailableProducts = orderService.checkIfProductsInCartAreAvailable(shoppingCart);
		orderService.processOrderIntoDatabase(shoppingCart,
				userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
		if (!notAvailableProducts.isEmpty()) {
			model.addObject("notAvailable", notAvailableProducts);
		}
		model.setViewName("cart");

		return model;
	}
}