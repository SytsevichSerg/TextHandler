
package com.epam.task4.entity;

import java.util.ArrayList;
import java.util.List;

public class LexicalComposite implements LexicalComponent{
    
    private LexicalLevel level;
    private List<LexicalComponent> componentList;
    
    public LexicalComposite(){
        componentList = new ArrayList<>();
    }
    
    public LexicalComposite(LexicalLevel level){
        this.level = level;
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
    public LexicalLevel getLexicalLevel() {
        return level;
    }

    @Override
    public List<LexicalComponent> getComponentList() {
        return componentList;
    }
    
    public void setLevel(LexicalLevel level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = Delimiter.EMPTY.getDelimiter();
        switch (level) {
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
