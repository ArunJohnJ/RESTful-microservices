package com.microservice.webservices.restfulwebservices.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.microservice.webservices.restfulwebservices.model.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	private int counter = 3;
	static {
		users.add(new User(1, "Arun", LocalDate.now()));
		users.add(new User(2, "Arun", LocalDate.now()));
		users.add(new User(3, "Arun", LocalDate.now()));
	}

	public List<User> getAll() {
		return users;
	}

	public User findOne(Integer id) {
		for (User user : users)
			if (user.getId() == id)
				return user;
		return null;
	}

	public User add(User user) {
		if (user.getId() == null) {
			user.setId(counter++);
		}
		users.add(user);
		return user;
	}
}
