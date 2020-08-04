package com.sayan.microservices.demospringbootmicroservices.dto;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class ExceptionDto extends RepresentationModel<ExceptionDto>{
	private Date timestamp;
	private String message;
	private String details;
	
	public ExceptionDto(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
}
