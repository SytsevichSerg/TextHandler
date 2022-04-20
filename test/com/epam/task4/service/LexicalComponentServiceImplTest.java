
package com.epam.task4.service;

import com.epam.task4.entity.LexicalComponent;
import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.exception.LexicalCompositeException;
import com.epam.task4.parser.impl.TextParser;
import com.epam.task4.service.impl.LexicalComponentServiceImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LexicalComponentServiceImplTest {
    
    private static final String FILE_PATH = "./test/com/epam/task4/src/text.txt";
    @Test
    public void sortParagraphsTest() throws LexicalCompositeException{
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        try{
            assertTrue(service.sortParagraphs(parseComponent()), "sortParagraphsTest");
        } catch (IOException e) {
            throw new LexicalCompositeException("sortParagraphsTest fails ", e);
        }
        
    }
    
    @Test
    public void findSentencesTest() throws LexicalCompositeException{
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        try{
            LexicalComponent actual = service.findSentences(parseComponent());
            assertFalse(actual.getComponentList().toString().isEmpty(), "FindLongerstSentencesTest");
        }catch (IOException e) {
            throw new LexicalCompositeException("findSentencesTest falis ", e);
        }
    }
    
    private LexicalComponent parseComponent() throws IOException{
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine()).append("\n");
            }
        }
        LexicalComposite composite = new TextParser().parse(text.toString());
        return composite;
    }
    
    
    /*
    @Test
    boolean deleteSentencesTest(LexicalComponent component, int wordsCount){
        return false;
    }
    
    @Test
    Map<String,Integer> findAndCountAlikeWordsTest(LexicalComponent component){
        return null;
    }
    
    @Test
    Map<String,Integer> countVowelConsonantTest(LexicalComponent component) throws LexicalCompositeException {
        return null;
    }
    */
}
