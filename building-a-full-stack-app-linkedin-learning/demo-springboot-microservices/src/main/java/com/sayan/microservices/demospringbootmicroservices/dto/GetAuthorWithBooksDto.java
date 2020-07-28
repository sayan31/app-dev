package com.sayan.microservices.demospringbootmicroservices.dto;

import java.util.ArrayList;
import java.util.List;

public class GetAuthorWithBooksDto {
	private Long authorId;	
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
	
	@Override
	public String toString() {
		return "GetAuthorWithBooksDto{" + "id="+authorId +'}';
	}
}
