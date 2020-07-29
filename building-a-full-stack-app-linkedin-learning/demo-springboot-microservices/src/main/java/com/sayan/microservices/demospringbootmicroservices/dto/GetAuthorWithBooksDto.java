package com.sayan.microservices.demospringbootmicroservices.dto;

import java.util.ArrayList;
import java.util.List;

public class GetAuthorWithBooksDto {
	private Long authorId;
	private String lastName;
	private String firstName;
	private List<GetAllBooksWithAuthorsDto> books = new ArrayList<>();
	
	public GetAuthorWithBooksDto() {}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public List<GetAllBooksWithAuthorsDto> getBooks() {
		return books;
	}

	public void setBooks(List<GetAllBooksWithAuthorsDto> books) {
		this.books = books;
	}
	
	public void addBook(GetAllBooksWithAuthorsDto book) {
		books.add(book);
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

	@Override
	public String toString() {
		return "GetAuthorWithBooksDto{" + "lastName="+lastName+"firstName"+firstName+'}';
	}
}