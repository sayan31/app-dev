package com.sayan.microservices.demospringbootmicroservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public class AuthorDto extends RepresentationModel<AuthorDto> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long authorId;
	@JsonProperty("authorLastName")
	private String lastName;
	@JsonProperty("authorFirstName")
	private String firstName;
	@JsonProperty("authorAbout")
	private String about;
	//private List<GetAllBooksWithAuthorsDto> books = new ArrayList<>();
	
	public AuthorDto() {}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAbout() { return about; }

	public void setAbout(String about) { this.about = about; }

	@Override
	public String toString() {
		return "AuthorDto{" + "lastName="+lastName+"firstName"+firstName+'}';
	}
}
