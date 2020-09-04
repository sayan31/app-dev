package com.sayan.microservices.demospringbootmicroservices.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookDto extends RepresentationModel<BookDto>{
	
	private Long bookId;
	private String bookName;
	private Long isbn;
	private String description;
	
	//private List<GetAuthorWithBooksDto> authors= new ArrayList<>();

	public BookDto() {}

	/*public BookDto(Long bookId, String bookName, Long isbn) {
		this.bookId = bookId;
		this.name = bookName;
		this.isbn = isbn;
	}*/
	
	public BookDto(Long bookId, String name, Long isbn, String description) {
		this.bookId = bookId;
		this.bookName = name;
		this.isbn = isbn;
		this.description = description;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}


	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
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
		return "GetAllBooksWithAuthorsDto{" + "id="+bookId + ", bookName=" + bookName + "isbn=" + isbn+'}';
	}
}
