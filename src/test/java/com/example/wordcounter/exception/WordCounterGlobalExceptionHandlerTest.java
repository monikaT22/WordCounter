package com.example.wordcounter.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.wordcounter.constants.WordCounterConstants;
import com.example.wordcounter.model.WordResponse;

@ExtendWith(MockitoExtension.class)
public class WordCounterGlobalExceptionHandlerTest {

    @InjectMocks
    private WordCounterGlobalExceptionHandler wordCounterGlobalExceptionHandler;

    @Test
    public void test_handleWordCounterException() {
        WordValidationException wce = new WordValidationException(WordCounterConstants.WORD_LIST_EMPTY_OR_NULL, HttpStatus.BAD_REQUEST);
       
        ResponseEntity<WordResponse> wordResponse = wordCounterGlobalExceptionHandler.handleWordCounterException(wce);

        
        assertEquals(WordCounterConstants.WORD_LIST_EMPTY_OR_NULL, wordResponse.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, wordResponse.getBody().getStatus());
    }

    @Test
    public void test_handleGlobalException() {
        WordValidationException wce = new WordValidationException(WordCounterConstants.UNKNOWN_EXCEPTION,HttpStatus.INTERNAL_SERVER_ERROR);
       
        ResponseEntity<WordResponse> wordResponse = wordCounterGlobalExceptionHandler.handleWordCounterException(wce);

        
        assertEquals(WordCounterConstants.UNKNOWN_EXCEPTION, wordResponse.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, wordResponse.getBody().getStatus());
    }
    
}
