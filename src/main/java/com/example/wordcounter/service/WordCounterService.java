package com.example.wordcounter.service;

import java.util.List;

public interface WordCounterService {

    /*
     * Method to add given word in hashmap
     */
    public void addWords(List<String> words);

    /*
     * Method to get count of given word
     */
    public int getWordCount(String word);    
    
}
