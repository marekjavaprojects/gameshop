package com.gameshop.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gameshop.entity.Product;
import com.gameshop.model.ShoppingCart;

@Controller
@Scope("request")
public class ShoppingCartController {

	@Autowired
	private ShoppingCart shoppingCart;

	private List<String> productsList;

	@GetMapping(value = "/addToCart/{productId}")
	public ModelAndView addToCart(@PathVariable("productId")int productId, ModelAndView model) {
		//sproductsList = Arrays.asList("testowy produkt");
		productsList = new ArrayList<>();
		productsList.add(String.valueOf(productId));
		shoppingCart.setProducts(productsList);
		model.addObject("cart", shoppingCart);
		return model;

	}

}
