package com.gameshop.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@GetMapping("/allProducts/{page}")
	public String showAllProducts(Model model, Principal principal, @PathVariable(name = "page") Integer page) {
		List<Product> allProducts = productService.getLatestAvailableProducts();
		int prevPage = page - 1;
		int nextPage = page + 1;
		int numberOfPages = (allProducts.size() / 5);
		
		Pageable pageable = new PageRequest(page - 1, 5);
		List<Product> allProductsByPage = productService.getLatestAvailableProducts(pageable).getContent();
		Set<String> categories = productService.getProductCategories();
		productListLabel = "Browse ALL games in the shop!";
		
		model.addAttribute("pages", numberOfPages);
		model.addAttribute("products", allProductsByPage);
		model.addAttribute("categories", categories);
		model.addAttribute("productListLabel", productListLabel);
		if (page > 1) {
			model.addAttribute("prevPage", prevPage);
		} else {
			model.addAttribute("prevPage", 1);
		}
		
		if(nextPage < numberOfPages) {
		model.addAttribute("nextPage", nextPage);
		} else {
			model.addAttribute("nextPage", numberOfPages);
		}

		return "homepage";
	}

	@GetMapping("/{category}")
	public String showProductsByCategory(@PathVariable("category") String category, Model model, Principal principal,
			@RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
		Pageable pageable = new PageRequest(page, 5);
		List<Product> productsByCategory = productService.getProductsByCategory(category, pageable).getContent();
		int numberOfPages = productsByCategory.size() / 5;
		Set<String> categories = productService.getProductCategories();
		model.addAttribute("products", productsByCategory);
		model.addAttribute("categories", categories);
		model.addAttribute("pages", numberOfPages);

		return "homepage";
	}

	@GetMapping("/search")
	public String searchProductsByName(@RequestParam(value = "productName") String productName, Model model,
			Principal principal, @RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
		Pageable pageable = new PageRequest(page, 5);
		Set<String> categories = productService.getProductCategories();

		if (productName.trim().compareTo("") == 0) {
			model.addAttribute("categories", categories);

			return "homepage";
		}

		List<Product> productsByName = productService.searchProductsByName(productName, pageable).getContent();
		model.addAttribute("products", productsByName);
		model.addAttribute("categories", categories);

		return "homepage";
	}
}