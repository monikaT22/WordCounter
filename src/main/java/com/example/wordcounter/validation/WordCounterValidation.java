package com.example.wordcounter.validation;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.wordcounter.model.WordMatcherResult;


public class WordCounterValidation {       
    
    /**
     * This method will get list of strings in input and will segregate words by matching the pattern
     * @param words
     * @return wordMatcherResult
     */
    public static WordMatcherResult getValidAndInvalidWords(List<String> words) {
        
        List<String> validWords = words.stream().filter(str -> getRegexPattern().matcher(str).matches())
                                      .collect(Collectors.toList());
        List<String> invalidWords = words.stream().filter(str -> !getRegexPattern().matcher(str).matches())
                                        .collect(Collectors.toList());
        return new WordMatcherResult(validWords, invalidWords);
    }

    /**
     * This method will check individual word with regex pattern
     * @param word
     * @return
     */
    public static boolean isValidWord(String word){
        return getRegexPattern().matcher(word).matches();
    }


    /**
     * @return
     */
    private static Pattern getRegexPattern(){
        return Pattern.compile("^[a-zA-Z]*$");
    }
}
