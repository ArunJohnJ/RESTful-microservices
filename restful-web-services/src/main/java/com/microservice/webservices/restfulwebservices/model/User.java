package com.microservice.webservices.restfulwebservices.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
	private Integer id;
	@Size(min = 2, message = "name must be min 2 characters")
	private String name;
	private LocalDateTime birthDate;
}
