package com.microservice.webservices.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.webservices.restfulwebservices.model.User;
import com.microservice.webservices.restfulwebservices.service.UserDaoService;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAll();
	}

}
