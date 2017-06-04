package com.allocadia.issues.issueClassifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Tokenizer {
    
    public static Set<String> tokenize(String text) {
        return Arrays.stream(text.split("[-:,./ ]"))
            .flatMap(s -> splitCamelCase(s).stream())
            .map(s -> s.toLowerCase())
            .collect(Collectors.toSet());
    }
    
    private static List<String> splitCamelCase(String camelCaseWord) {
        return Arrays.asList(camelCaseWord.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])"));
    }
    
}
