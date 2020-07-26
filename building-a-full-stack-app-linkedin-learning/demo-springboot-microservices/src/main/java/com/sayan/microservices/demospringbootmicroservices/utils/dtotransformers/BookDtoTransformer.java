package com.sayan.microservices.demospringbootmicroservices.utils.dtotransformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sayan.microservices.demospringbootmicroservices.dto.AuthorDto;
import com.sayan.microservices.demospringbootmicroservices.dto.BookDto;

@Component
public class BookDtoTransformer {
	public List<BookDto> transform(List<Object[]> resultSet){
		final Map<Long, BookDto> booksDtoMap = new HashMap<>();
		
		for (Object[] o : resultSet) {
			Long bookId = ((Number)o[0]).longValue();
			
			BookDto bookDto = booksDtoMap.get(bookId);
			if(null==bookDto) {
				bookDto = new BookDto();
				bookDto.setBookId(((Number)o[0]).longValue());
				bookDto.setName((String)o[1]);
				bookDto.setIsbn(((Number)o[2]).longValue());
			}
			
			AuthorDto authorDto =  new AuthorDto();
			authorDto.setAuthorId(((Number)o[3]).longValue());
			authorDto.setLastName((String)o[4]);
			authorDto.setFirstName((String)o[5]);
			
			bookDto.addAuthor(authorDto);
			booksDtoMap.putIfAbsent(bookDto.getBookId(), bookDto);
		} 		
		return new ArrayList<>(booksDtoMap.values());
	}
}
