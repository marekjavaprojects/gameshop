package com.gameshop.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gameshop.entity.Product;
import com.gameshop.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private String productListLabel;

	@Autowired
	ProductService productService;

	@GetMapping("/allProducts")
	public String showAllProducts(Model model) {

		
		List<Product> allProducts = productService.getProducts();
		Set<String> categories = productService.fetchCategoriesFromProducts(productService.getProducts());
		productListLabel = "Browse ALL games in the shop!";
		model.addAttribute("products", allProducts);
		model.addAttribute("categories", categories);
		model.addAttribute("productListLabel", productListLabel);
		
		return "shop-homepage";
	}

	@GetMapping("/{category}")
	public String showProductsByCategory(@PathVariable("category") String category, Model model) {

		List<Product> productsByCategory = productService.getProductsByCategory(category);		
		Set<String> categories = productService.fetchCategoriesFromProducts(productService.getProducts());

		model.addAttribute("products", productsByCategory);
		model.addAttribute("categories", categories);

		return "shop-homepage";
	}

	@GetMapping("/search")
	public String searchProductsByName(@RequestParam(value = "productName") String productName, Model model) {
		Set<String> categories = productService.fetchCategoriesFromProducts(productService.getProducts());

		if (productName.trim().compareTo("") == 0) {
			model.addAttribute("categories", categories);

			return "shop-homepage";

		}
		List<Product> productsByName = productService.searchProductsByName(productName);

		model.addAttribute("products", productsByName);
		model.addAttribute("categories", categories);

		return "shop-homepage";
	}

}
