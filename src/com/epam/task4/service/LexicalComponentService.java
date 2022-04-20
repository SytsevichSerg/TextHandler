
package com.epam.task4.service;

import com.epam.task4.entity.LexicalComponent;
import com.epam.task4.exception.LexicalCompositeException;
import java.util.Map;


public interface LexicalComponentService {
     boolean sortParagraphs(LexicalComponent component);
     LexicalComponent findSentences(LexicalComponent component);
     boolean deleteSentences(LexicalComponent component, int wordsCount);
     Map<String,Integer> findAndCountAlikeWords(LexicalComponent component);
     Map<String,Integer> countVowelConsonant(LexicalComponent component) throws LexicalCompositeException;
}
