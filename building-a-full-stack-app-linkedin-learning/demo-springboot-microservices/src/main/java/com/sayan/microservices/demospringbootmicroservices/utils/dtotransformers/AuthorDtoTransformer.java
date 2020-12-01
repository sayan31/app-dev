package com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import com.sayan.microservices.demospringbootmicroservices.dto.AuthorDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.BookController;
import com.sayan.microservices.demospringbootmicroservices.entity.Author;

@Component
public class AuthorDtoTransformer {
	
	public List<AuthorDto> transform(Set<Author> result) {
		
		final Map<Long, AuthorDto> authorsDtoMap = new HashMap<>();
		for (Author author : result) {
			Long authorId = author.getId();
			
			AuthorDto authorDto = authorsDtoMap.get(authorId);
			if(null==authorDto) {
				authorDto = new AuthorDto();
				authorDto.setAuthorId(author.getId());
				authorDto.setLastName(author.getAuthorLastName());
				authorDto.setFirstName(author.getAuthorFirstName());
				authorDto.setAbout(author.getAuthorAbout());
			}
			
			authorDto.add(linkTo(methodOn(BookController.class).showBook(authorId)).withRel("books"));
			
			authorsDtoMap.putIfAbsent(authorDto.getAuthorId(), authorDto);
		}		
		return new ArrayList<>(authorsDtoMap.values());
	}
}
