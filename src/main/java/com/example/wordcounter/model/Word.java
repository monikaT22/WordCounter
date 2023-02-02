package com.example.wordcounter.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



public class Word {

    
    @NotNull (message = "Word list is null")
    @NotEmpty (message = "Word list is empty")
    private List<String> words; 

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<String> getWords(){
        return words;
    }
    
    
}
