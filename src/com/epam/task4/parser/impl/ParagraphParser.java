package com.epam.task4.parser.impl;

import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.entity.LexicalLevel;
import com.epam.task4.parser.LexicalComponentParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements LexicalComponentParser{
    
    private static final String REGEX_SENTENCE = "\\p{Upper}.*?(?:[?!.])|(?:[.]{3})";

    @Override
    public LexicalComposite parse(String value) {
        LexicalComposite paragraph = new LexicalComposite();
        paragraph.setLevel(LexicalLevel.PARAGRAPH);
        Pattern pattern = Pattern.compile(REGEX_SENTENCE);
        Matcher matcher = pattern.matcher(value);
        SentenceParser sentenceParser = new SentenceParser();
        while (matcher.find()) {
            String group = matcher.group();
            LexicalComposite sentenceComposite = sentenceParser.parse(group);
            paragraph.add(sentenceComposite);
        }    
        return paragraph;
    }
    
}
