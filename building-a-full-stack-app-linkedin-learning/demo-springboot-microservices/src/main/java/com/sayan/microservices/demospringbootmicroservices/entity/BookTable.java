package com.sayan.microservices.demospringbootmicroservices.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class BookTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String bookName;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)	
	private List<AuthorTable> authors;
	
	@Column
	private String description;
	
	@Column
	private Long isbn;

	
	/**
	 * @param bookName
	 * @param author
	 * @param description
	 * @param isbn
	 * Constructor to generate an instance of a book
	 */

	public BookTable(String bookName, String description, Long isbn) {
		this.bookName = bookName;
		this.description = description;
		this.isbn = isbn;
	}
	
	protected BookTable() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public List<AuthorTable> getAuthor() {
		return authors;
	}


	public void setAuthor(AuthorTable author) {
		authors.add(author);
		author.getBook().add(this);
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
	
	
}
