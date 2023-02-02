package com.example.wordcounter.service.impl;

import org.springframework.stereotype.Service;

import com.example.wordcounter.service.TranslatorService;

@Service
public class TranslatorServiceImpl implements TranslatorService{

    @Override
    public String translateToEnglish(String word) {
        //Call third party api to translate word in english
        return "English";
    }
    
}
