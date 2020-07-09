package com.sayan.microservices.demospringbootmicroservices.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class AuthorTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String authorLastName;
	
	@Column
	private String authorFirstName;
	
	@Column
	private String authorAbout;
	
	@ManyToMany
	@JoinTable(name="BookTable_AuthorTable",joinColumns={@JoinColumn(name="author_id")},inverseJoinColumns = {@JoinColumn(name = "book_id")})
	private List<BookTable> books;

	public AuthorTable(String authorLastName, String authorFirstName, String authorAbout) {
		this.authorLastName = authorLastName;
		this.authorFirstName = authorFirstName;
		this.authorAbout = authorAbout;
	}

	protected AuthorTable() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorAbout() {
		return authorAbout;
	}

	public void setAuthorAbout(String authorAbout) {
		this.authorAbout = authorAbout;
	}

	public List<BookTable> getBook() {
		return books;
	}

	public void setBook(BookTable book) {
		books.add(book);
		book.getAuthor().add(this);
	}
	
	
}
