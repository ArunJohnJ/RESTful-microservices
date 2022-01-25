package com.microservice.webservices.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class HelloMomBean {
	@Getter @Setter
	private String message;

	public HelloMomBean(String message) {
		this.message = message;
	}
}
