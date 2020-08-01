package com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import com.sayan.microservices.demospringbootmicroservices.dto.GetAuthorWithBooksDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.BookController;
import com.sayan.microservices.demospringbootmicroservices.entity.AuthorTable;

@Component
public class AuthorDtoTransformer {
	
	public List<GetAuthorWithBooksDto> transform(Set<AuthorTable> result) {
		
		final Map<Long, GetAuthorWithBooksDto> authorsDtoMap = new HashMap<>();
		for (AuthorTable author : result) {
			Long authorId = author.getId();
			
			GetAuthorWithBooksDto authorDto = authorsDtoMap.get(authorId);
			if(null==authorDto) {
				authorDto = new GetAuthorWithBooksDto();
				authorDto.setAuthorId(author.getId());
				authorDto.setLastName(author.getAuthorLastName());
				authorDto.setFirstName(author.getAuthorFirstName());
			}
			
			authorDto.add(linkTo(methodOn(BookController.class).showBook(authorId)).withRel("books"));
			
			authorsDtoMap.putIfAbsent(authorDto.getAuthorId(), authorDto);
		}		
		return new ArrayList<>(authorsDtoMap.values());
	}
}
