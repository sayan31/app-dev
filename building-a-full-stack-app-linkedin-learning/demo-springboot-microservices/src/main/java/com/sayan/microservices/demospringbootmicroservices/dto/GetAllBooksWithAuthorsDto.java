package com.sayan.microservices.demospringbootmicroservices.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
	
	private Long bookId;
	private String name;
	private String description;
	private Long isbn;
	
	private List<AuthorDto> authors= new ArrayList<>();

	public BookDto() {}

	public BookDto(Long bookId, String name, Long isbn) {
		this.bookId = bookId;
		this.name = name;
		this.isbn = isbn;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String bookName) {
		this.name = bookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(AuthorDto author) {
		authors.add(author);
	}

	@Override
	public String toString() {
		return "BookDto{" + "id="+bookId + ", name=" + name + ", description=" + description +"isbn=" + isbn+'}';
	}
}
