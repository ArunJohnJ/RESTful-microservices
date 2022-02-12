package com.microservice.webservices.restfulwebservices.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore//if we don't have this, then we get recursive loop kind of thing, when we try to fetch the posts, it will try to fetch the user which in turn will try to fetch the posts and it will continue so on.
	private UserEntity user;
}
