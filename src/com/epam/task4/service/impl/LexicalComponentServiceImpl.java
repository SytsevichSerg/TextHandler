
package com.epam.task4.service.impl;

import com.epam.task4.entity.CharacterUnit;
import com.epam.task4.entity.LexicalComponent;
import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.entity.LexicalLeaf;
import com.epam.task4.entity.LexicalLevel;
import com.epam.task4.exception.LexicalCompositeException;
import com.epam.task4.service.LexicalComponentService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexicalComponentServiceImpl implements LexicalComponentService{
    
    private static final Logger LOG = LogManager.getLogger();
    private static final String REGEX_VOWEL = "[aeiouyаяоёуюэеиы]";
    private final LexicalComponent sentenceList = new LexicalComposite();
    private final List<LexicalComponent> sentencesToRemove = new ArrayList<>();
    private final Map<String, Integer> wordsMap = new HashMap<>();
    private int countLetter = 0;
    private int vowel = 0;
    private int consonant = 0;
    
    @Override
    public boolean sortParagraphs(LexicalComponent component) {
        //LOG.info("sortParagraphs: ПОСМОТРЕТЬ ИНТЕРПРЕТОР!!!");
        boolean ret = true; 
        if(component.getLexicalLevel() == LexicalLevel.TEXT) {
            component.getComponentList().sort(Comparator.comparingInt(c -> c.getComponentList().size()));
        } else {
            ret = false;
        }
        LOG.info(component.toString() + "sortParagraphs: ПОСМОТРЕТЬ ИНТЕРПРЕТОР!!!");
        return ret;
    }

    @Override
    public LexicalComponent findSentences(LexicalComponent component) {
        switch (component.getLexicalLevel()) {
            case TEXT: 
                for (LexicalComponent paragraph : component.getComponentList()){
                    for (LexicalComponent sentence : paragraph.getComponentList()){
                        iterateSentences(sentence);
                    }
                }break;
               
            case PARAGRAPH: 
               for (LexicalComponent sentence : component.getComponentList()){
                   iterateSentences(sentence);
               }break;
        }
        return sentenceList;
    }

    @Override
    public boolean deleteSentences(LexicalComponent component, int wordsCount) {
        boolean ret = false;
        switch (component.getLexicalLevel()) {
            case TEXT: {
                for (LexicalComponent paragraph : component.getComponentList()){
                    deleteSentence(paragraph, component, wordsCount);
                }
            }break;
            
            case PARAGRAPH: {
                ret = deleteSentence(component, component, wordsCount);
            }break;
        }
        return ret;
    }

    @Override
    public Map<String, Integer> findAndCountAlikeWords(LexicalComponent component) {
        Map<String, Integer> wordsAlike = new HashMap<>();
        switch (component.getLexicalLevel()) {
            case TEXT: {
                for (LexicalComponent paragraph : component.getComponentList()){
                    for (LexicalComponent sentence : paragraph.getComponentList()){
                        findWords(sentence);
                    }
                }
            }
        }
        LOG.info(wordsMap.toString() + "findAndCountAlikeWords: ПОСМОТРЕТЬ ИНТЕРПРЕТОР!!!");
        return wordsAlike;
    }

    @Override
    public Map<String, Integer> countVowelConsonant(LexicalComponent sentence) throws LexicalCompositeException{
        Map<String, Integer> lettersMap = new HashMap<>();
        if (sentence.getLexicalLevel() == LexicalLevel.SENTENCE) {
            for (LexicalComponent lexeme : sentence.getComponentList()){
                for (LexicalComponent symbol : lexeme.getComponentList()){
                    if (symbol.getLexicalLevel() == LexicalLevel.SYMBOL) {
                        countingLetter(symbol);
                    } else {
                        for (LexicalComponent letter: symbol.getComponentList()) {
                            countingLetter(letter);
                        }
                    }
                }
            }
        } else {
            LOG.error("Given # LexicalComponent # is not a sentence", sentence);
            throw new LexicalCompositeException();
        }
        
        lettersMap.put("Vowel", vowel);
        lettersMap.put("Consonant", consonant);
        LOG.info(lettersMap.toString() + "countVowelConsonant: ПОСМОТРЕТЬ ИНТЕРПРЕТОР!!!");
        return lettersMap;
    }
    
    private boolean iterateSentences(LexicalComponent component) {
        boolean resize = false;
        for (LexicalComponent lexeme : component.getComponentList()) {
            
            for (LexicalComponent word : lexeme.getComponentList()) {
               
                if (word.getLexicalLevel() == LexicalLevel.WORD) {
                    
                    if (word.getComponentList().size() == countLetter) {
                        sentenceList.add(component);
                    } else if (word.getComponentList().size() > countLetter) {
                        
                        countLetter = word.getComponentList().size();
                        sentenceList.getComponentList().clear();
                        sentenceList.add(component);
                        resize = true;
                    }  
                }
            }
        }
        return resize;
    }
    
    private boolean deleteSentence(LexicalComponent paragraph, LexicalComponent component, int count) {
        
        boolean ret = false;
        for (LexicalComponent sentence : paragraph.getComponentList()) {
            if(sentence.getComponentList().size() < count) {
                sentencesToRemove.add(sentence);
            }
        }
        for (LexicalComponent sentence : sentencesToRemove){
           ret = component.remove(sentence);
        }
        return ret;
    }
    
    private void findWords(LexicalComponent sentence) {
        for (LexicalComponent lexeme : sentence.getComponentList()) {
            for (LexicalComponent word : lexeme.getComponentList()) {
                if (word.getLexicalLevel()== LexicalLevel.WORD) {
                    String key = word.toString().toLowerCase(Locale.ROOT);
                    if (wordsMap.containsKey(key)) {
                        wordsMap.put(key, wordsMap.get(key) + 1);
                    } else {
                        wordsMap.put(key, 1);
                    }
                }
            }
        }
    }
    
    private void countingLetter(LexicalComponent leaf) {
        if (((LexicalLeaf)leaf).getUnit() == CharacterUnit.LETTER) {
            if (leaf.toString().matches(REGEX_VOWEL)) {
                vowel++;
            } else {
                consonant++;
            }
        }
    }
}
