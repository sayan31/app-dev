package com.sayan.microservices.demospringbootmicroservices.endpoints;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sayan.microservices.demospringbootmicroservices.dto.ExceptionDto;
import com.sayan.microservices.demospringbootmicroservices.exceptions.BooksNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandlerController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BooksNotFoundException.class)
	public final ResponseEntity<Object> handleBooksNotFoundException(BooksNotFoundException ex, WebRequest webRequest){
		ExceptionDto exception = new ExceptionDto(new Date(),ex.getMessage(),webRequest.getDescription(false));
		return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
	}
}
