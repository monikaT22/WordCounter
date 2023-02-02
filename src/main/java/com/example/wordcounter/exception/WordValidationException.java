package com.example.wordcounter.exception;

import org.springframework.http.HttpStatus;

public class WordValidationException extends Exception{

    private String message;    
    private HttpStatus status;

    public WordValidationException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public WordValidationException(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }  
    
    
}
