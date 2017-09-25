package com.gameshop.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gameshop.entity.Product;
import com.gameshop.model.CartItem;
import com.gameshop.model.ShoppingCart;
import com.gameshop.service.ProductService;
import com.gameshop.service.ShoppingCartService;

@Controller
@Scope("request")
public class ShoppingCartController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	private CartItem cartItem;

	@RequestMapping(value = "/addToCart", method=RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam("id")Long productId, ModelAndView model) {
		Product product = productService.getProductById(productId);
		cartItem = new CartItem(product);
		shoppingCartService.addToCart(cartItem);
		model.addObject("cart", shoppingCart);
		model.setViewName("homepage");
		return model;

	}
	
	@RequestMapping(value = "/updateCart", method=RequestMethod.POST)
	public ModelAndView updateCart(HttpServletRequest request, HttpSession session, ModelAndView model) {
		shoppingCartService.updateCartItem(request.getParameterValues("quantity"));
		model.setViewName("cart");
		return model;
	}

	@RequestMapping(value = "/cart", method=RequestMethod.GET)
	public ModelAndView showCart(ModelAndView model) {
		model.setViewName("cart");
		return model;
	}
	
	@RequestMapping(value = "/deleteItem", method=RequestMethod.POST)
	public ModelAndView deleteItem(@RequestParam("productName")String productName, ModelAndView model) {
		System.out.println(productName + " produckkkt");
		model.setViewName("cart");
		return model;
		
	}

	

}
