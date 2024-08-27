package com.smart.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.smart.entities.User;
import com.smart.helper.AppConstants;
import com.smart.helper.Message;
import com.smart.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home-Contact Manager");
		 return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title","About-ContatManager");
		 return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title","Register-ContatManager");
		model.addAttribute("user",new User());
		 return "signup";
	}
	   @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	
	
	@PostMapping("/do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user,BindingResult result1 ,@RequestParam(value= "agreement",defaultValue= "false") boolean agreement,Model model ,HttpSession session) {
		
		try { 
			if(!agreement) {
				System.out.println("plzz check the terms and conditions");
				throw new Exception("plz check the terms and conditions");
				
			}
			
			if(result1.hasErrors()) {
				
				System.out.println("Error"+ result1.toString());		
				model.addAttribute("user", user);	
				return "signup";
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoleList(List.of(AppConstants.ROLE_USER));
			
//		    user.setPassword(user.getPassword());
			System.out.println("agreement"+agreement);
			System.out.println("User"+ user);
			/*User result =*/userRepository.save(user);
			model.addAttribute("User", new User());
			session.setAttribute("message", new Message("Registration Succesfull","alert-success"));
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("User", user);
			session.setAttribute("message",new Message("Something went worng !!"+e.getMessage(),"alert-danger") );
			return "signup";
		}
		
		
		 
	}
	

}
