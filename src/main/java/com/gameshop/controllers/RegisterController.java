package com.gameshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gameshop.entity.User;
import com.gameshop.service.UserService;
import com.gameshop.validator.UserValidator;



@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	UserService userService;
    @Autowired
	private UserValidator userValidator;
    
	@GetMapping("")
	public String showRegisterPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "register";
	}

	@PostMapping("/processForm")
	public String processRegisterForm(@ModelAttribute("user") User user, BindingResult result, Model model) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "register";
		}
		userService.createUser(user);
		
		return "redirect:/";
	}
}
