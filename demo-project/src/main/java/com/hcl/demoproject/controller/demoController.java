package com.hcl.demoproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hcl.demoproject.Entity.User;
import com.hcl.demoproject.Entity.UserRepo;
import com.hcl.demoproject.Entity.Validations;

@Controller
@SessionAttributes("username")
public class demoController{

	List<String> errors = new ArrayList<>();
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping("/welcome")
	public String welcome(ModelMap map) {
		return "welcome.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(ModelMap map) {
		map.addAttribute("success", "<span style=\"color:royalblue;\">Logout Successful</span>");
		return "logout.jsp";
	}
	
	@GetMapping("/create")
	public String create() {
		return "create.jsp";
	}
	
	@PostMapping("/create")
	public String createAccount(@RequestParam String username, @RequestParam String password, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam(required = false) String gender,  ModelMap map) {
		
		errors.clear();
		map.addAttribute("username",username);
		map.addAttribute("firstName",firstName);
		map.addAttribute("lastName",lastName);
		map.addAttribute("gender",gender);
		
		String[] fields = {username,password,firstName,lastName,gender,"User Name","Password","First Name","Last Name","Gender"};
		
		errors = Validations.isValid(fields);
		
		// check database to see if name is taken after validations
		if(userRepo.existsById(fields[0])) {
			errors.add("<span style=\"color:red\">User Name ("+fields[0]+") Unavailable</span>");
		}

		
		if(errors.isEmpty()) {
			// check database to see if name is taken after validations
			if(userRepo.existsById(fields[0])) {
				errors.add("<span style=\"color:red\">User Name ("+fields[0]+") Unavailable</span>");
				map.addAttribute("errors", errors);
				return "create.jsp";
			}
			userRepo.save(new User(username,password,firstName,lastName,gender));
			map.addAttribute("success", "<span style=\"color:royalblue;\">New profile created for "+firstName+"</span>");
			return "login.jsp";			
		}else {
			map.addAttribute("errors", errors);
			return "create.jsp";
		}

	}

	@GetMapping("/login")
	public String login(ModelMap map) {
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String register(@RequestParam String username, @RequestParam String password, ModelMap map) {
		
		map.addAttribute("username", username);
		// check for username in database
		if(userRepo.existsById(username)) {
			// if username found check password
			User user = userRepo.findById(username).get();
			if(user.getPassword().equals(password)) {
				map.addAttribute("user", user);
				// if username and password match create session and login
				return "welcome.jsp";
			}
			map.addAttribute("errors","<span style=\"color:red\">Invalid username</span>");	
			return "login.jsp";
		}else {
			//if username not found return error message
			map.addAttribute("errors","<span style=\"color:red\">Invalid username</span>");			
			return "login.jsp";
		}
		
	}
}