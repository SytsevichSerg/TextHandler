package com.epam.task4.parser.impl;

import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.entity.LexicalLevel;
import com.epam.task4.parser.LexicalComponentParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements LexicalComponentParser{
    private static final String REGEX_LEXEME = "[^\\s]+";
    
    @Override
    public LexicalComposite parse(String value) {
        LexicalComposite sentence = new LexicalComposite();
        sentence.setLevel(LexicalLevel.SENTENCE);
        Pattern pattern = Pattern.compile(REGEX_LEXEME);
        Matcher matcher = pattern.matcher(value);
        LexemeParser lexemeParser = new LexemeParser();
        while (matcher.find()) {
            String group = matcher.group();
            LexicalComposite lexemeComposite = lexemeParser.parse(group);
            sentence.add(lexemeComposite);
        }
        return sentence;
    }
    
}
