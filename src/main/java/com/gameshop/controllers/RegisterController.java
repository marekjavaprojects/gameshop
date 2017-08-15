package com.gameshop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gameshop.entity.User;
import com.gameshop.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public String showRegisterPage(@ModelAttribute("user") User user, Model model) {		
		return "register-form";
	}
	
	@PostMapping("/processForm")
	public String processRegisterForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {	
		if(result.hasErrors()) {
			return "register-form";
		}
		userService.createUser(user);
		
		return "redirect:/";
	}
}
