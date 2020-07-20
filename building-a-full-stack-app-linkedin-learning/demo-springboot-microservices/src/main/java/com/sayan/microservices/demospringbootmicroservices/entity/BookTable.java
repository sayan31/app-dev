package com.sayan.microservices.demospringbootmicroservices.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @author S
 * This is the representation of one BookTable record in the database.
 * There is a many-to-many association with the AuthorTable table.
 * This is the entity that is the owner of the Book-Author relationship.
 * Changes are propagated to the database from this side alone. 
 * A new AuthorTable instance can only be added through a BookTable instance.
 */
@Entity
public class BookTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String bookName;	
	
	@Column(length=2000)
	private String description;
	
	@Column
	private Long isbn;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="BookTable_AuthorTable",joinColumns={@JoinColumn(name="book_id",referencedColumnName="id")},inverseJoinColumns = {@JoinColumn(name = "author_id",referencedColumnName ="id")})
	private Set<AuthorTable> authors = new HashSet<>();
	
	/**
	 * @param author
	 * Helper to add an author to set of authors for this book
	 */
	public void addAuthor(AuthorTable author) {
		this.authors.add(author);
		author.getBooks().add(this);
	}
	
	/**
	 * @param author
	 * Helper to remove an author from the set of authors for this book
	 */
	public void removeAuthor(AuthorTable author) {
		this.authors.remove(author);
		author.getBooks().remove(this);
	}
	
	/**
	 * Removes all authors for this particular book 
	 */
	public void removeAuthors() {
        Iterator<AuthorTable> iterator = this.authors.iterator();
        while (iterator.hasNext()) {
        	AuthorTable author = iterator.next();
        	author.getBooks().remove(this);
            iterator.remove();
        }
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


	public Set<AuthorTable> getAuthor() {
		return authors;
	}


	public void setAuthor(Set<AuthorTable> authors) {
		this.authors=authors;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        return id != null && id.equals(((BookTable) obj).id);
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", name=" + bookName
                + ", description=" + description + ", isbn=" + isbn + '}';
	}	
	
	
}
