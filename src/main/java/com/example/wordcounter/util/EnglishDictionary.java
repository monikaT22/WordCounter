package com.example.wordcounter.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class EnglishDictionary {
    /* 
    private static final HashSet<String> englishWords = new HashSet<>();

    static {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
                String word;
                while((word = reader.readLine()) != null){
                    englishWords.add(word.trim().toLowerCase());
                }
            

        }catch(Exception e){
            throw new RuntimeException();
        }

    } */

    /*
     * Method to check if given word is present in english dictionary
     */
    public static boolean isPresent (String word){
        //For now return as true 

        return true;

        /*
         * To check word is english or not we need to add all english words on one txt file and need to check hat file against each word
         */
        //return englishWords.contains(word.toLowerCase());
        
        
    }
    
}
