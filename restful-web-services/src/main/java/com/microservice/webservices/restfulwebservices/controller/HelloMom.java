package com.microservice.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.webservices.restfulwebservices.model.HelloMomBean;

@RestController
public class HelloMom {

	@GetMapping("/hello-mom")
	public String helloMom() {
		return "Hello Mom";
	}
	
	@GetMapping("hello-mom-bean")
	public HelloMomBean helloMomBean() {
		return new HelloMomBean("Hello Mom");
	}
	
	@GetMapping(path = "hello-mom/path-variable/{name}")
	public HelloMomBean helloMomBeanWithPathVariable(@PathVariable String name) {
		return new HelloMomBean(String.format("Hello Mom - from %s", name));
	}
}
