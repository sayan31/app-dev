package com.sayan.microservices.demospringbootmicroservices.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author S
 * The owned side of the Book-Author relationship.
 */
@Entity
//@Table(uniqueConstraints= {@UniqueConstraint(columnNames={"authorLastName", "authorFirstName"})})
public class AuthorTable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String authorLastName;
	
	@Column
	private String authorFirstName;
	
	@Column
	private String authorAbout;
	

	/**
	 * It is more useful to use a {@link Set} to model bi-directional
	 * Many-to-many relations because it results in more efficient
	 * database interactions for many use-cases, for example, while
	 * removing entities.
	 * This results in cleaner and more efficient underlying SQL queries
	 */
	@ManyToMany(mappedBy="authors")
	private Set<BookTable> books = new HashSet<>();

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

	public Set<BookTable> getBooks() {
		return books;		
	}

	public void setBooks(Set<BookTable> books) {
		this.books=books;
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

        return id != null && id.equals(((AuthorTable) obj).id);
	}
	
	@Override
	public int hashCode() {
		return 2021;
	}
	
	@Override
	public String toString() {
		return "Author{" + "id=" + id + ", last name=" + authorLastName
                + ", first name=" + authorFirstName + ", about=" + authorAbout + '}';
	}	
	
}
