package com.example.ecctrainingspring.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message){
        super(message);
    }

}
