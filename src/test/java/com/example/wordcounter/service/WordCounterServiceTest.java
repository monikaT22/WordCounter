package com.example.wordcounter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import com.example.wordcounter.service.impl.WordCounterServiceImpl;


public class WordCounterServiceTest {

    @InjectMocks
    private WordCounterServiceImpl wordCounterService; 

    
    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    } 

    @Test
    public void test_addWords(){
        List<String> words = List.of("hello","world","hello");       
        wordCounterService.addWords(words);         
       
        assertEquals(2, wordCounterService.wordMap.get("hello").get());
        assertEquals(1, wordCounterService.wordMap.get("world").get());
       
    }

    @Test
    public void test_getWordCount(){

        wordCounterService.wordMap.put("hello", new AtomicInteger(5));
        int count = wordCounterService.getWordCount("hello");

        assertEquals(5, count);    
       
    }

    @Test
    public void test_zero_getWordCount(){        
        int count = wordCounterService.getWordCount("hello");

        assertEquals(0, count);    
       
    }

}

    

