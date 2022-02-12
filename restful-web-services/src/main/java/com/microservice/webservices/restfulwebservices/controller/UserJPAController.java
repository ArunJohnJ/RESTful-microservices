package com.microservice.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.microservice.webservices.restfulwebservices.model.Post;
import com.microservice.webservices.restfulwebservices.model.UserEntity;
import com.microservice.webservices.restfulwebservices.repository.PostRepository;
import com.microservice.webservices.restfulwebservices.repository.UserRepository;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userService;
	
//	@Autowired
//	private PostRepository postService;

	@GetMapping("/jpa/users")
	public List<UserEntity> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<UserEntity> getUser(@PathVariable int id) {
		Optional<UserEntity> user = userService.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id- " + id);
		}
		EntityModel<UserEntity> model = EntityModel.of(user.get());
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		model.add(link.withRel("all-users"));
		return model;
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getPosts(@PathVariable int id) {
		Optional<UserEntity> optionalUser = userService.findById(id);
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("user not found for id- "+id);
		return optionalUser.get().getPosts();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<UserEntity> insertUser(@Valid @RequestBody UserEntity user) {
		UserEntity newSavedUser = userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newSavedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> insertPost(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<UserEntity> user = userService.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("user not found for id -"+ id);
		post.setUser(user.get());	
//		postService.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteById(id);
	}
}
