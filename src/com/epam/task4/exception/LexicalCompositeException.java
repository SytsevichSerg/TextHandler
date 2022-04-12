
package com.epam.task4.exception;


public class LexicalCompositeException extends Exception {
    public LexicalCompositeException() {
        super();
    }
    
    public LexicalCompositeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LexicalCompositeException(String message) {
        super(message);
    }
    
    public LexicalCompositeException(Throwable cause) {
        super(cause);
    }
}
