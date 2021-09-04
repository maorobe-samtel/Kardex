package com.todo1.kardex.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo1.kardex.model.UserCredentialDto;
import com.todo1.kardex.model.UserDto;
import com.todo1.kardex.service.UserService;

@Controller
@RequestMapping
public class LoginController {
	public static final String LOGIN = "login";
	public static final String SIGN_UP = "signup";
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usercredentialdto", new UserCredentialDto());
		return LOGIN;
	}
	
	@GetMapping("/signupform")
	public String signUpForm(Model model) {
		model.addAttribute("userdto", new UserDto());
		return SIGN_UP;
	}

	@PostMapping("/logincheck")
	public String loginCheck(@Valid @ModelAttribute(name = "usercredentialdto") UserCredentialDto userCredentialDto,
			BindingResult bindingResult,Model model) {
			
		if (bindingResult.hasErrors() || !userService.findFirstByUserAndPassword( userCredentialDto.getUser(),userCredentialDto.getPassword())) {
//		
		model.addAttribute("usercredentialdto", new UserCredentialDto());
		model.addAttribute("error","The field cannot go empty");
		return LOGIN;
	}
			

		return "redirect:/productcontroller/showproducts";
	}

	@GetMapping("/")
	public String redirectProducts() {
		return "redirect:/login";
	}
	
	@PostMapping("/addUser")
	public String addUser(@Valid @ModelAttribute(name="userdto") UserDto userDto, BindingResult bindingResult,Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("userdto", new UserDto());
			model.addAttribute("error", "error");
			return SIGN_UP; 
		}
		
		userService.addUser(userDto);
		model.addAttribute("usercredentialdto", new UserCredentialDto());
		model.addAttribute("add", "add");
		
		
		return LOGIN;
	}

}
