package com.example.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.user.service.payload.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		String messageString = ex.getMessage();
		ApiResponse response = new ApiResponse(messageString,true, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
}