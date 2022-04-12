
package com.epam.task4.entity;

import java.util.ArrayList;
import java.util.List;

public class LexicalComposite implements LexicalComponent{
    
    private final LexicalUnit unit;
    private List<LexicalComponent> componentList;
    
    public LexicalComposite(LexicalUnit unit){
        this.unit = unit;
        componentList = new ArrayList<>();
    }
    
    @Override
    public boolean add(LexicalComponent component) {
        return componentList.add(component);
    }

    @Override
    public boolean remove(LexicalComponent component) {
        return componentList.remove(component);
    }

    @Override
    public LexicalUnit geLexicalUnit() {
        return unit;
    }

    @Override
    public List<LexicalComponent> getComponentList() {
        return componentList;
    }
    
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = Delimiter.EMPTY.getDelimiter();
        switch (unit) {
            case TEXT:
                delimiter = Delimiter.LF.getDelimiter();
                break;
            case PARAGRAPH:
                stringBuilder.append(Delimiter.TAB.getDelimiter());
            case SENTENCE:
                delimiter = Delimiter.SPACE.getDelimiter();
                break;
        }
        for (LexicalComponent component : componentList) {
            stringBuilder.append(component.toString());
            stringBuilder.append(delimiter);
        }
        return stringBuilder.toString();
    }
    
}
