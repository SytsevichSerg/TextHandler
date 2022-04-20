
package com.epam.task4.entity;

public enum LexicalLevel {
    TEXT,
    PARAGRAPH, // if given number of spaces || Tab =>  this is a Paragraph
    SENTENCE, // if " or Capital  . till «.», «?», «!» or «…»
    LEXEME, // space XXX space, 
    WORD,
    PUNCTUATION,
    NUMERAL,
    SYMBOL
}
