
package com.epam.task4.entity;

public enum Delimiter {
    SPACE(" "),
    EMPTY(""),
    TAB("\t"),
    LF("\n"),
    CR("\r");
    
    private final String delimiter;
    
    Delimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
