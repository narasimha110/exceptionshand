package com.demo.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(EmployeeAlreadyExistsException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<ErrorResponse>handleEmployeeAlreadyExists(EmployeeAlreadyExistsException ex){
		ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(),HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
		
	}
	    @ExceptionHandler(EmployeeNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex) {
	        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	    }
	    @ExceptionHandler(InvalidEmployeeDataException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<ErrorResponse> handleInvalidEmployeeData(InvalidEmployeeDataException ex) {
	        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	    @ExceptionHandler(Exception.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex) {
	        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
