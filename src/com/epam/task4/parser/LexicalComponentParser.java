package com.epam.task4.parser;

import com.epam.task4.entity.LexicalComponent;

public interface LexicalComponentParser {
    LexicalComponent parse(String value);
}
