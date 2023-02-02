package com.example.wordcounter.model;

import java.util.List;

public class WordMatcherResult {
    private List<String> validWords;
    private List<String> invalidWords;
    
    public List<String> getValidWords() {
        return validWords;
    }
    public void setValidWords(List<String> validWords) {
        this.validWords = validWords;
    }
    public List<String> getInvalidWords() {
        return invalidWords;
    }
    public void setInvalidWords(List<String> invalidWords) {
        this.invalidWords = invalidWords;
    }
    public WordMatcherResult(List<String> validWords, List<String> invalidWords) {
        this.validWords = validWords;
        this.invalidWords = invalidWords;
    }

    
}
