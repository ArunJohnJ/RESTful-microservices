package com.microservice.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ExceptionResponse {
	private LocalDateTime timeStamp;
	private String message;
	private String details;
}
