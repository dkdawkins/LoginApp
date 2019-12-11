package com.webencyclop.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webencyclop.demo.model.User;
import com.webencyclop.demo.model.UserByEmailAndSecurity;
import com.webencyclop.demo.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}
	
	//Added to original code
	//////////////////////////////////////////////////////////////////////////
	@RequestMapping(value =  "/recover" , method = RequestMethod.GET)
	public ModelAndView recover() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("recover"); // resources/template/recover.html
		return modelAndView;
	}
	
	//WARNING: this request is not functional under the current implementation
	@RequestMapping(value =  "/recover" , method = RequestMethod.POST)
	public ModelAndView recoverUser(@Valid UserByEmailAndSecurity user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		
		//Check for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form.");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		
		//Replace user in database using given email and security with new password; returns false if unsuccessful
		else if (!userService.replaceUser(user)){
			modelAndView.addObject("successMessage", "User with given email and security question/answer does not exist.");
		}
		
		else {
			modelAndView.addObject("successMessage", "Successfully replaced old password.");
		}
		
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("recover"); // resources/template/recover.html
		return modelAndView;
	}
	//////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user); 
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		//Check for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Please correct the errors in form.");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if (userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "User already exists.");
		}
		//Save user if no binding errors
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User successfully registered!");
		}
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("register");
		return modelAndView;
	}
}
