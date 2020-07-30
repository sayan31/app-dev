package com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.sayan.microservices.demospringbootmicroservices.dto.GetAllBooksWithAuthorsDto;
import com.sayan.microservices.demospringbootmicroservices.endpoints.AuthorController;

@Component
public class BookDtoTransformer {
	public List<GetAllBooksWithAuthorsDto> transform(List<Object[]> resultSet){
		final Map<Long, GetAllBooksWithAuthorsDto> booksDtoMap = new HashMap<>();
		
		for (Object[] o : resultSet) {
			Long bookId = ((Number)o[0]).longValue();
			
			GetAllBooksWithAuthorsDto bookDto = booksDtoMap.get(bookId);
			if(null==bookDto) {
				bookDto = new GetAllBooksWithAuthorsDto();
				bookDto.setBookId(((Number)o[0]).longValue());
				bookDto.setName((String)o[1]);
				bookDto.setIsbn(((Number)o[2]).longValue());
			}
			
			/*GetAuthorWithBooksDto authorDto =  new GetAuthorWithBooksDto();
			authorDto.setAuthorId(((Number)o[3]).longValue());
			authorDto.setLastName((String)o[4]);
			authorDto.setFirstName((String)o[5]);
			
			bookDto.addAuthor(authorDto);*/
			
			bookDto.add(linkTo(methodOn(AuthorController.class).fetchAuthorWithBooks(bookId)).withRel("authors"));
			
			booksDtoMap.putIfAbsent(bookDto.getBookId(), bookDto);
		} 		
		return new ArrayList<>(booksDtoMap.values());
	}
}
