package com.gameshop.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@Scope("session")
@RequestMapping(value = "/cart")
public class ShoppingCartController {

	@Autowired
	private ProductService productService;	
	@Autowired
	private ShoppingCartService shoppingCartService;	
	@Autowired
	private ShoppingCart shoppingCart;	
	private CartItem cartItem;

	@RequestMapping(value = "/addToCart", method=RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam("productId")Long productId, ModelAndView model) {
		Product product = productService.getProductById(productId);
		cartItem = new CartItem(product);
		shoppingCartService.addToCart(cartItem);
		model.addObject("cart", shoppingCart);
		model.setViewName("redirect:/");
		
		return model;
	}
	
	@RequestMapping(value = "/updateCart", method=RequestMethod.POST)
	public ModelAndView updateCart(HttpServletRequest request, HttpSession session, ModelAndView model) {
		shoppingCartService.updateCartItem(request.getParameterValues("quantity"));
		model.setViewName("redirect:/cart/showCart");
		
		return model;
	}

	@RequestMapping(value = "/showCart", method=RequestMethod.GET)
	public ModelAndView showCart(ModelAndView model) {	
		for(CartItem item : shoppingCart.getCartItems()) {
			System.out.println(item.getProductName());
		}
		model.setViewName("cart");
		
		return model;
	}
	
	@RequestMapping(value = "/deleteItem/{id}", method=RequestMethod.GET)
	public ModelAndView deleteItem(@PathVariable("id")Long productId, ModelAndView model) {
		System.out.println(productId + " produckkkt");
		CartItem itemToDelete = shoppingCartService.findCartItemById(productId);
		shoppingCartService.deleteItemFromCart(itemToDelete);
		model.setViewName("cart");
		
		return model;		
	}	
}
