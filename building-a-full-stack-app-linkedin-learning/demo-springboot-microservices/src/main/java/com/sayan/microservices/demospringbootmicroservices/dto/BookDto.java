package com.sayan.microservices.demospringbootmicroservices.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookDto extends RepresentationModel<BookDto>{
	
	private Long bookId;
	private String name;
	private Long isbn;
	private String description;
	
	//private List<GetAuthorWithBooksDto> authors= new ArrayList<>();

	public BookDto() {}

	/*public BookDto(Long bookId, String name, Long isbn) {
		this.bookId = bookId;
		this.name = name;
		this.isbn = isbn;
	}*/
	
	public BookDto(Long bookId, String name, Long isbn, String description) {
		this.bookId = bookId;
		this.name = name;
		this.isbn = isbn;
		this.description = description;
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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
