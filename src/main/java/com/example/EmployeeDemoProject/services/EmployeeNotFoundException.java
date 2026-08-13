package com.example.EmployeeDemoProject.services;

//package com.example.EmployeeDemoProject.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	    public EmployeeNotFoundException(String message) {
	        super(message);
	    }
}