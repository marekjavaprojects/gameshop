package com.gameshop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String showHomePageWithFourLatestProducts(HttpServletRequest request, Model model, @RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
		Pageable pageable = new PageRequest(page, 5);
		List<Product> latestProducts = productService.showHomePageProducts(pageable);
		Set<String> categories = productService.getProductCategories();
		productListLabel = "Latest Games!";
		HttpSession session = request.getSession();
		session.setAttribute("cart", shoppingCart);
		model.addAttribute("products", latestProducts);
		model.addAttribute("categories", categories);
		model.addAttribute("productListLabel", productListLabel);

		return "homepage";
	}

}
