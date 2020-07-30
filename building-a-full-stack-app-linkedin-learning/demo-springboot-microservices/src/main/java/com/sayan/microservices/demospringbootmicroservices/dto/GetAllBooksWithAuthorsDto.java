package com.sayan.microservices.demospringbootmicroservices.dto;

import org.springframework.hateoas.RepresentationModel;

public class GetAllBooksWithAuthorsDto extends RepresentationModel<GetAllBooksWithAuthorsDto>{
	
	private Long bookId;
	private String name;
	private Long isbn;
	
	//private List<GetAuthorWithBooksDto> authors= new ArrayList<>();

	public GetAllBooksWithAuthorsDto() {}

	public GetAllBooksWithAuthorsDto(Long bookId, String name, Long isbn) {
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

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	/*public List<GetAuthorWithBooksDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<GetAuthorWithBooksDto> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(GetAuthorWithBooksDto author) {
		authors.add(author);
	}*/

	@Override
	public String toString() {
		return "GetAllBooksWithAuthorsDto{" + "id="+bookId + ", name=" + name + "isbn=" + isbn+'}';
	}
}
