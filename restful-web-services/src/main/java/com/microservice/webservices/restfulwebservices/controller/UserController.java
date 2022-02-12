package com.microservice.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservice.webservices.restfulwebservices.exception.UserNotFoundException;
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

	@GetMapping("/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id- " + id);
		}
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		model.add(link.withRel("all-users"));
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<User> insertUser(@Valid @RequestBody User user) {
		User newSavedUser = userService.add(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newSavedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteUser(id);
		if (user == null)
			throw new UserNotFoundException("id- " + id);
	}
}
