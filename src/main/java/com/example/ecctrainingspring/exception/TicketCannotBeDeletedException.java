package com.example.ecctrainingspring.exception;

public class TicketCannotBeDeletedException extends RuntimeException{
    public TicketCannotBeDeletedException(String message) {
        super(message);
    }
}
