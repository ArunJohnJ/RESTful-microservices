package com.microservice.webservices.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microservice.webservices.restfulwebservices.model.LolBean;

@RestController
public class LolBeanController {

	@GetMapping("/static-filtering")
	public LolBean getLolBeans() {
		return new LolBean("name1", "name1", "name3");
	}

	@GetMapping("/static-filtering-list")
	public List<LolBean> getListLolBean() {
		return Arrays.asList(new LolBean("name1", "name2", "name3"), new LolBean("hiyo1", "hiyo2", "hiyo3"));
	}

	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue getLolBeansDynamicFilter() {
		LolBean lolBean = new LolBean("name1", "name1", "name3");

		MappingJacksonValue mapping = new MappingJacksonValue(lolBean);

		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("value3", "value1");
		FilterProvider filter = new SimpleFilterProvider().addFilter("FilterValues", propertyFilter);// 1st arg is the
																										// name of the
																										// filter to be
																										// present in
																										// the model
																										// class,

		mapping.setFilters(filter);

		return mapping;
	}

	@GetMapping("/dynamic-filtering-list")
	public MappingJacksonValue getListLolBeanDynamicFiltering() {

		List<LolBean> lolBean = Arrays.asList(new LolBean("name1", "name1", "name3"),new LolBean("hiyo1","hiyo2","hiyo3"));

		MappingJacksonValue mapping = new MappingJacksonValue(lolBean);

		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("value3");
		FilterProvider filter = new SimpleFilterProvider().addFilter("FilterValues", propertyFilter);

		mapping.setFilters(filter);

		return mapping;
	}
}
