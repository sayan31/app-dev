package com.sayan.microservices.demospringbootmicroservices.dto;

import org.springframework.hateoas.RepresentationModel;

public class UpdateBookDescriptionDto extends RepresentationModel<UpdateBookDescriptionDto>{
	private String description;
	
	public UpdateBookDescriptionDto() {}
	
	public UpdateBookDescriptionDto(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
}
