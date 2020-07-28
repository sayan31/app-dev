package com.sayan.microservices.demospringbootmicroservices.dto;

public class GetAuthorWithLastNameAndFirstNameDto {
	private Long authorId;
	private String lastName;
	private String firstName;
	
	//private List<GetAllBooksWithAuthorsDto> books= new ArrayList<>();

	public GetAuthorWithLastNameAndFirstNameDto() {}

	public GetAuthorWithLastNameAndFirstNameDto(Long authorId, String lastName, String firstName) {
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

	/*
	 * public List<GetAllBooksWithAuthorsDto> getBooks() { return books; }
	 * 
	 * public void setBooks(List<GetAllBooksWithAuthorsDto> books) { this.books =
	 * books; }
	 * 
	 * public void addBook(GetAllBooksWithAuthorsDto book) { books.add(book); }
	 */

	@Override
	public String toString() {
		return "AuthorWithLastNameAndFirstNameDto{" + ", last name=" + lastName + ", first name=" + '}';
	}
}
