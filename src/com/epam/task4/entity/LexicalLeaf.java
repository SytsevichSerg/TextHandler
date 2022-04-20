
package com.epam.task4.entity;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LexicalLeaf implements LexicalComponent{
    private static final Logger LOG = LogManager.getLogger();
    private static final LexicalLevel level = LexicalLevel.SYMBOL;
    private CharacterUnit unit;
    private char leaf;
    
    private final List<LexicalComponent> leafs = new ArrayList<>();
    
    public LexicalLeaf(char leaf) {
        this.leaf = leaf;
        leafs.add(this);
    }

    @Override
    public boolean add(LexicalComponent component) {
        LOG.error("leaf add: Unsupported operation exception");
        throw new UnsupportedOperationException();     
    }

    @Override
    public boolean remove(LexicalComponent component) {
        LOG.error("leaf remove: Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    @Override
    public LexicalLevel getLexicalLevel() {
       return level;
    }

    @Override
    public List<LexicalComponent> getComponentList() {
        return leafs;
    }
    
    @Override
    public String toString() {
        return String.valueOf(leaf);
    }

    public CharacterUnit getUnit() {
        return unit;
    }
    
    public void setUnit(CharacterUnit unit) {
        this.unit = unit;
    }
}
