package com.example.wordcounter.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wordcounter.constants.WordCounterConstants;
import com.example.wordcounter.exception.WordValidationException;
import com.example.wordcounter.model.Word;
import com.example.wordcounter.model.WordCountResponse;
import com.example.wordcounter.model.WordResponse;
import com.example.wordcounter.model.WordMatcherResult;
import com.example.wordcounter.service.WordCounterService;
import com.example.wordcounter.validation.WordCounterValidation;

@RestController
@RequestMapping (WordCounterConstants.WORD_API)
public class WordCounterController {

    @Autowired
    private WordCounterService wordCounterService;
    
    /**
     * Method to add words. It will accept list of words 
     * @param words
     * @param result
     * @return
     * @throws WordValidationException
     */
    @PostMapping (WordCounterConstants.ADD_WORDS) 
    public ResponseEntity<WordResponse> addWords(@Valid @RequestBody Word words,BindingResult result) throws WordValidationException{ 
        if (result.hasErrors()) {
            throw new WordValidationException(WordCounterConstants.WORD_LIST_EMPTY_OR_NULL,HttpStatus.BAD_REQUEST); 
        }        
       
        //Segregate valid/invalid words. Words with special characters and numbers are not allowed 
        WordMatcherResult wordMatcherResult = WordCounterValidation.getValidAndInvalidWords(words.getWords());
      
        if(!wordMatcherResult.getValidWords().isEmpty()){
            //Callling service to add words 
            wordCounterService.addWords(wordMatcherResult.getValidWords()); 
                return new ResponseEntity<>(new WordResponse(WordCounterConstants.WORDS_ADDED_SUCCESSFULLY,wordMatcherResult.getInvalidWords(),wordMatcherResult.getValidWords(),HttpStatus.OK), HttpStatus.OK);
        }else{
            //No valid words to add so returning response with invalid
            return new ResponseEntity<>(new WordResponse(WordCounterConstants.INVALID_WORDS,wordMatcherResult.getInvalidWords(),wordMatcherResult.getValidWords(),HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);

        }           
    } 

    
    /**
     * Method to get the count of passed word. It will provide count of only one word
     * @param word
     * @return
     */
    @GetMapping (WordCounterConstants.COUNT_WORD)
    public ResponseEntity<WordCountResponse> countWord(@PathVariable String word){
        return new ResponseEntity<>(new WordCountResponse(word,wordCounterService.getWordCount(word.trim().toLowerCase())),HttpStatus.OK);
        
    }
    
}
