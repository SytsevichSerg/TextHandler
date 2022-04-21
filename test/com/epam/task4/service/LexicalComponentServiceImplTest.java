package com.epam.task4.service;

import com.epam.task4.entity.LexicalComponent;
import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.exception.LexicalCompositeException;
import com.epam.task4.parser.impl.SentenceParser;
import com.epam.task4.parser.impl.TextParser;
import com.epam.task4.service.impl.LexicalComponentServiceImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LexicalComponentServiceImplTest {
    
    private static final String FILE_PATH = "./test/com/epam/task4/src/text.txt";
    private static final String FILE_PATH_VOWEL ="./test/com/epam/task4/src/vowel_consonant.txt";
    
    @Test
    public void sortParagraphsTest() {
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        try{
            Assert.assertTrue(service.sortParagraphs(parseComponent(FILE_PATH)), "sortParagraphsTest");
        } catch (IOException e) {
            Assert.fail("sortParagraphsTest fails " + e);
        }
    }
    
    @Test
    public void findSentencesTest(){
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        try{
            LexicalComponent actual = service.findSentences(parseComponent(FILE_PATH));
            Assert.assertFalse(actual.getComponentList().toString().isEmpty(), "FindLongerstSentencesTest");
        }catch (IOException e) {
            Assert.fail("findSentencesTest falis " + e);
        }
    }
    
    @Test
    public void deleteSentencesTest(){
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        try{
            Assert.assertTrue(service.deleteSentences(parseComponent(FILE_PATH), 2), "deleteSentencesTest count less that 2");
        } catch (IOException e) {
            Assert.fail("deleteSentencesTest fails " + e);
        }
    }
    
    @Test
    public void findAndCountAlikeWordsTest(){
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        String expected = "{but=1, software=1, release=1, reader=2, when=2, lorem=2, that=3, only=1, english=1, passages=1, has=2, five=1, publishing=1, letters=1, readable=2, centuries=1, letraset=1, using=2, making=1, including=1, in=1, like=2, containing=1, its=2, is=3, it=6, survived=1, typesetting=1, less=1, look=1, remaining=1, as=1, at=2, versions=1, ipsum=3, looking=2, page=2, here=2, fact=2, be=2, distribution=1, long=1, content=3, point=1, into=1, not=1, sheets=1, desktop=1, and=1, of=6, by=1, electronic=1, leap=1, established=2, a=7, normal=1, opposed=1, or=1, will=2, more=2, recently=1, unchanged=1, was=1, also=1, distracted=1, bye=1, the=5, layout=2, with=2, essentially=1, to=1, popularised=1, aldus=1, pagemaker=1}";

        try{
            Map<String, Integer> wordsAlike = service.findAndCountAlikeWords(parseComponent(FILE_PATH));
            String actual = wordsAlike.toString();
            Assert.assertEquals(expected, actual);
        } catch (IOException e) {
            Assert.fail("findAndCountAlikeWordsTest fails " + e);
        }
    }
    
    @Test
    public void countVowelConsonantTest(){
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        String expected ="{Consonant=146, Vowel=94}";
        try{
            Map<String, Integer> vowelConsonant = service.countVowelConsonant(parseSentence(FILE_PATH_VOWEL));
            String actual = vowelConsonant.toString();
            Assert.assertEquals(expected, actual);
        } catch (LexicalCompositeException | IOException e) {
            Assert.fail("countVowelConsonantTest fails " + e);
        }
    }
    
    private LexicalComponent parseComponent(String path) throws IOException{
        StringBuilder text = new StringBuilder(); 
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine()).append("\n");
            }
        }
        LexicalComposite composite = new TextParser().parse(text.toString());
        return composite;
    }
    
    private LexicalComponent parseSentence(String path) throws IOException{
        StringBuilder text = new StringBuilder(); 
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
                text.append(bufferedReader.readLine()).append("\n");
        }
        LexicalComposite composite = new SentenceParser().parse(text.toString());
        return composite;
    }
}
