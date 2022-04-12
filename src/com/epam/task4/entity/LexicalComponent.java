
package com.epam.task4.entity;

import java.util.List;

public interface LexicalComponent {
    
    boolean  add(LexicalComponent component);
    boolean  remove(LexicalComponent component);
    public LexicalUnit geLexicalUnit();
    String toString();
    List<LexicalComponent> getComponentList(); 
}
