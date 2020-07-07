package com.sayan.microservices.demospringbootmicroservices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String bookName;
	
	@ManyToOne
	private AuthorTable author;
	
	@Column
	private String description;
	
	@Column
	private Integer isbn;
}
