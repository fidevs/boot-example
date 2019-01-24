package com.example.exceptions;

public class BookIdMismatchException extends RuntimeException{
    public BookIdMismatchException() {
        super();
    }

    public BookIdMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
