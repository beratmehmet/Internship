package org.example.exception;

public class UserAlreadyExistException extends RuntimeException{
    String message;

    public UserAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
