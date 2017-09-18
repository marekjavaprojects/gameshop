package com.gameshop.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gameshop.entity.Product;
import com.gameshop.model.ShoppingCart;
import com.gameshop.service.ProductService;

@Controller
public class HomePageController {
	
	private String productListLabel;

	@Autowired
	ProductService productService;
	@Autowired
	private ShoppingCart shoppingCart;
	
	@GetMapping("/")
	public String showHomePageWithFourLatestProducts(HttpServletRequest request, Model model) {
		
		List<Product> latestFourProducts = productService.getFourLatestProducts();
		Set<String> categories = productService.fetchCategoriesFromProducts(productService.getProducts());
		productListLabel = "Latest Games!";
		HttpSession session = request.getSession();		
		session.setAttribute("cart", shoppingCart);
		model.addAttribute("products", latestFourProducts);
		model.addAttribute("categories", categories);
		model.addAttribute("productListLabel", productListLabel);
		return "homepage";
	}
	
}
