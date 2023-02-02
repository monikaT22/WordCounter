package com.example.wordcounter.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import com.example.wordcounter.constants.WordCounterConstants;
import com.example.wordcounter.exception.WordValidationException;
import com.example.wordcounter.model.Word;
import com.example.wordcounter.model.WordCountResponse;
import com.example.wordcounter.model.WordResponse;
import com.example.wordcounter.service.WordCounterService;


@ExtendWith(MockitoExtension.class)
public class WordCounterControllerTest {

    @InjectMocks
    private WordCounterController wordCountController;

    @Mock
    private WordCounterService wordCounterService;         

    BindingResult bindingResult = mock(BindingResult.class);
    Word word = new Word(); 

    @Test
    public void test_validation_exception_addWords() {
              
        word.setWords(List.of());      
        when(bindingResult.hasErrors()).thenReturn(true);     

        WordValidationException wordValidationException = assertThrows(WordValidationException.class, ()->{
        wordCountController.addWords(word, bindingResult);
        });

        assertEquals(HttpStatus.BAD_REQUEST, wordValidationException.getStatus());
        assertEquals(WordCounterConstants.WORD_LIST_EMPTY_OR_NULL, wordValidationException.getMessage());

    } 


    @Test
    public void test_add_only_valid_Words() throws WordValidationException {
          
        word.setWords(List.of("test","Fruit"));        
        when(bindingResult.hasErrors()).thenReturn(false);          

        ResponseEntity<WordResponse> wordResponse = wordCountController.addWords(word, bindingResult);
       
        assertEquals(HttpStatus.OK,  wordResponse.getBody().getStatus());
        assertEquals(WordCounterConstants.WORDS_ADDED_SUCCESSFULLY, wordResponse.getBody().getMessage());

    }

    @Test
    public void test_add_only_invalid_Words() throws WordValidationException {
          
        word.setWords(List.of("test@","Fruit#"));        
        when(bindingResult.hasErrors()).thenReturn(false);          

        ResponseEntity<WordResponse> wordResponse = wordCountController.addWords(word, bindingResult);
       
        assertEquals(HttpStatus.BAD_REQUEST,  wordResponse.getBody().getStatus());
        assertEquals(WordCounterConstants.INVALID_WORDS, wordResponse.getBody().getMessage());

    }

    @Test
    public void test_add_both_valid_invalid_Words() throws WordValidationException {
          
        word.setWords(List.of("Test","Fruit#","123Mango"));        
        when(bindingResult.hasErrors()).thenReturn(false);          

        ResponseEntity<WordResponse> wordResponse = wordCountController.addWords(word, bindingResult);
       
        assertEquals(HttpStatus.OK,  wordResponse.getBody().getStatus());
        assertEquals(WordCounterConstants.WORDS_ADDED_SUCCESSFULLY, wordResponse.getBody().getMessage());

    }

    @Test
    public void test_getcount_word(){

        String word = "test";
        int expectedWordCount = 1;
        when(wordCounterService.getWordCount((word.trim().toLowerCase()))).thenReturn(expectedWordCount);

        ResponseEntity<WordCountResponse> wordCountResponse = wordCountController.countWord(word);
        assertEquals(word,  wordCountResponse.getBody().getWord());
        assertEquals(expectedWordCount, wordCountResponse.getBody().getWordCount());
    }
    
    @Test
    public void test_getcount_zero_if_word_not_present(){

        String word = "test";
        int expectedWordCount = 0;
        when(wordCounterService.getWordCount((word.trim().toLowerCase()))).thenReturn(expectedWordCount);

        ResponseEntity<WordCountResponse> wordCountResponse = wordCountController.countWord(word);
        assertEquals(word,  wordCountResponse.getBody().getWord());
        assertEquals(expectedWordCount, wordCountResponse.getBody().getWordCount());
    }
}
