package com.example.wordcounter.validation;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.wordcounter.model.WordMatcherResult;


public class WordCounterValidation {   


    public static WordMatcherResult getValidAndInvalidWords(List<String> words) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]*$");
        List<String> validWords = words.stream().filter(str -> pattern.matcher(str).matches())
                                      .collect(Collectors.toList());
        List<String> invalidWords = words.stream().filter(str -> !pattern.matcher(str).matches())
                                        .collect(Collectors.toList());
        return new WordMatcherResult(validWords, invalidWords);
    }
}
