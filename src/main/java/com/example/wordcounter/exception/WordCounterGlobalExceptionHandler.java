package com.example.wordcounter.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.wordcounter.constants.WordCounterConstants;
import com.example.wordcounter.model.WordResponse;

@ControllerAdvice
public class WordCounterGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler (value = {WordValidationException.class})
    public ResponseEntity<WordResponse> handleWordCounterException(WordValidationException wce){
        return new ResponseEntity<>(new WordResponse(wce.getMessage(), wce.getStatus()),HttpStatus.BAD_REQUEST); 
        

    }

    @ExceptionHandler (value = {Exception.class})
    public ResponseEntity<WordResponse> handleGlobalException(Exception e){
        return new ResponseEntity<>(new WordResponse(WordCounterConstants.UNKNOWN_EXCEPTION,HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }  

}

    

