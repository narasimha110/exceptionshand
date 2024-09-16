package com.demo.practice.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException {
	
	  public EmployeeAlreadyExistsException(String message) {
	        super(message);
	    }

}
