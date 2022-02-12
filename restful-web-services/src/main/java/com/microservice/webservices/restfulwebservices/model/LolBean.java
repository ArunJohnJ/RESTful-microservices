package com.microservice.webservices.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"value3"})
@JsonFilter("FilterValues")
public class LolBean {
	private String value1;
//	@JsonIgnore
	private String value2;
	private String value3;
}
