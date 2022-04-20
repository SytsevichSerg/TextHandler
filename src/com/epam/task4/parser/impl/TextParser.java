
package com.epam.task4.parser.impl;


import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.entity.LexicalLevel;
import com.epam.task4.parser.LexicalComponentParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements LexicalComponentParser{
    private static final String REGEX_PARAGRAPH = "(\\s{4}.+)|(\\t.+)";
    
    @Override
    public LexicalComposite parse(String value) {
        LexicalComposite text = new LexicalComposite();
        text.setLevel(LexicalLevel.TEXT);
        Pattern pattern = Pattern.compile(REGEX_PARAGRAPH);
        Matcher matcher = pattern.matcher(value);
        ParagraphParser paragraphParser = new ParagraphParser();
        while (matcher.find()) {
            String group = matcher.group();
            LexicalComposite paragraphComposite = paragraphParser.parse(group);
            text.add(paragraphComposite);
        }
        return text;
    }
    
}
