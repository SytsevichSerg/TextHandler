package com.epam.task4.parser.impl;

import com.epam.task4.entity.CharacterUnit;
import com.epam.task4.entity.LexicalComponent;
import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.entity.LexicalLeaf;
import com.epam.task4.entity.LexicalLevel;
import com.epam.task4.parser.ExpressionParser;
import com.epam.task4.parser.LexicalComponentParser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser implements LexicalComponentParser{
    private static final String REGEX_GROUP = "(-?[0-9(][0-9.+\\-*/()]+(?:[0-9)]))|(\\p{L}+)|(\\d+)|([,.\\-;!?()])";
    private static final String REGEX_SYMBOL = ".";
    private static final Logger LOG = LogManager.getLogger();
    
    @Override
    public LexicalComposite parse(String value) {
        LexicalComposite lexeme = new LexicalComposite();
        lexeme.setLevel(LexicalLevel.LEXEME);
        Pattern pattern = Pattern.compile(REGEX_GROUP);
        Matcher matcher = pattern.matcher(value);
        String subsequence;
        while (matcher.find()) {
            LexicalComposite composite = new LexicalComposite();
            if(matcher.group(1) != null) {
                subsequence = matcher.group(1);
                String result = ExpressionParser.parse(subsequence);
                if (result.equals(subsequence)) {
                    for (LexicalComponent component : parseSymbol(result).getComponentList() ) {
                        lexeme.add(component);
                    }
                    continue;
                } else {
                    composite = parseSymbol(result);
                    composite.setLevel(LexicalLevel.NUMERAL);
                }
            } else if(matcher.group(2) != null) {
                composite = parseSymbol(matcher.group(2));
                composite.setLevel(LexicalLevel.WORD);
            } else if(matcher.group(3) != null) {
                composite = parseSymbol(matcher.group(3));
                composite.setLevel(LexicalLevel.NUMERAL);
            } else if(matcher.group(4) != null) {
                composite = parseSymbol(matcher.group(4));
                composite.setLevel(LexicalLevel.PUNCTUATION);
            }
            LOG.info(composite.toString());
            lexeme.add(composite);
        }
        return lexeme;
    }   
    
    private LexicalComposite parseSymbol(String value) {
        LexicalComposite digit = new LexicalComposite();
        Pattern pattern = Pattern.compile(REGEX_SYMBOL);
        Matcher matcher = pattern.matcher(value);
        SymbolParser symbolParser = new SymbolParser();
        while (matcher.find()) {
            String group = matcher.group();
            LexicalLeaf leaf = symbolParser.parse(group);
            digit.add(leaf);
        }
        return digit;
    }
}
