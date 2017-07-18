package com.gameshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gameshop.entity.User;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@GetMapping("")
	public String showRegisterPage(@ModelAttribute("user") User user, Model model) {	
		
		return "register-form";
	}
	
	@PostMapping("/processForm")
	public String processRegisterForm(@ModelAttribute("user") User user, Model model) {	
		System.out.println("process");
		return "shop-homepage";
	}

}
