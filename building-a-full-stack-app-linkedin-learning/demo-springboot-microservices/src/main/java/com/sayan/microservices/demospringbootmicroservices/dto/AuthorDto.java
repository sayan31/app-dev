package com.sayan.microservices.demospringbootmicroservices.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorDto {
	private Long authorId;
	private String lastName;
	private String firstName;
	private String about;
	
	private List<BookDto> books= new ArrayList<>();

	public AuthorDto() {}

	public AuthorDto(Long authorId, String lastName, String firstName) {
		this.authorId = authorId;
		this.lastName = lastName;
		this.firstName = firstName;
	}

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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}
	
	public void addBook(BookDto book) {
		books.add(book);
	}

	@Override
	public String toString() {
		return "AuthorDto{" + "id="+authorId + ", last name=" + lastName + ", first name=" + firstName +"about=" + about+'}';
	}
}
