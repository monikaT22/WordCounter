package com.example.wordcounter.service.impl;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.wordcounter.service.TranslatorService;
import com.example.wordcounter.service.WordCounterService;
import com.example.wordcounter.util.EnglishDictionary;


@Service
public class WordCounterServiceImpl implements WordCounterService{

    @Autowired
    private TranslatorService translatorService;

    public Map<String,AtomicInteger> wordMap = new ConcurrentHashMap<>();
    

    @Override
    public void addWords(List<String> words) { 
        
        for (String word : words) {
            //Check passed word in english dictonary else translate it into english
            word = checkEnglishDictionary(word).toLowerCase();

            //Put the word in map and increament count if already present   
            wordMap.computeIfAbsent(word, w -> new AtomicInteger(0)).incrementAndGet(); 

        }      
        
    }

    @Override
    public int getWordCount(String word) { 

        Optional<AtomicInteger> wordCount =  wordMap.entrySet().stream()
        .filter(entry -> entry.getKey().equals(checkEnglishDictionary(word)))
        .map(entry -> entry.getValue()).findFirst();

        return wordCount.isPresent() ? wordCount.get().get() : 0;   

    }    

    private String checkEnglishDictionary(String word){
        //Check passed word in english dictonary else translate it into english
        if (!EnglishDictionary.isPresent(word)) {
            word = translatorService.translateToEnglish(word);
            /*
            * Here either we will get exact translated word or similar word. Or else we will get message 
            * that service was unable to translate the word.
            * Need to handle accordingly then
            */               

        }

        return word;

        }
    
}
