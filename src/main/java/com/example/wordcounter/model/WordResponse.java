package com.example.wordcounter.model;

import java.util.List;

import org.springframework.http.HttpStatus;

public class WordResponse {

    private String message;
    private List<String> invalidWords;
    private List<String> validWords;
    private HttpStatus status;
    
    public WordResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public WordResponse(String message, List<String> invalidWords, List<String> validWords,HttpStatus status) {
        this.message = message;
        this.invalidWords = invalidWords;
        this.validWords = validWords;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }   

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getInvalidWords() {
        return invalidWords;
    }

    public void setInvalidWords(List<String> invalidWords) {
        this.invalidWords = invalidWords;
    }

    public List<String> getValidWords() {
        return validWords;
    }

    public void setValidWords(List<String> validWords) {
        this.validWords = validWords;
    }

}
