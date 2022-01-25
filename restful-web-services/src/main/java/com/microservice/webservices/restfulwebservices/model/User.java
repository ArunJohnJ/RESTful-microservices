package com.microservice.webservices.restfulwebservices.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
@AllArgsConstructor
public class User {
	private Integer id;
	private String name;
	private LocalDate birthDate;
}
