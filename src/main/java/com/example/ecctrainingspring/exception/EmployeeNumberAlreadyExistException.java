package com.example.ecctrainingspring.exception;

public class EmployeeNumberAlreadyExistException extends RuntimeException {
    public EmployeeNumberAlreadyExistException(String message) {
        super(message);
    }
}
