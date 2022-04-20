
package com.epam.task4.parser.impl;

import com.epam.task4.entity.CharacterUnit;
import com.epam.task4.entity.LexicalComponent;
import com.epam.task4.entity.LexicalLeaf;
import com.epam.task4.parser.LexicalComponentParser;

public class SymbolParser implements LexicalComponentParser{

    @Override
    public LexicalLeaf parse(String value) {
        LexicalLeaf leaf = new LexicalLeaf(value.charAt(0));
        if (Character.isLetter(value.charAt(0))) {
            leaf.setUnit(CharacterUnit.LETTER);
        } else if (Character.isDigit(value.charAt(0))) {
            leaf.setUnit(CharacterUnit.DIGIT);
        } else {
            leaf.setUnit(CharacterUnit.PUNCTUATION_MARK);
        }
        return leaf;
    }  
    
}
