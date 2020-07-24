package com.sayan.microservices.demospringbootmicroservices.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author S
 * The owned side of the Book-Author relationship.
 */
@Entity
@Table(uniqueConstraints= {@UniqueConstraint(columnNames={"authorLastName", "authorFirstName"})})
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

	@ManyToMany(mappedBy="authors",fetch=FetchType.LAZY)
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
