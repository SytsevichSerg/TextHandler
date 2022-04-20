
package com.epam.task4.main;

import com.epam.task4.entity.LexicalComponent;
import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.exception.LexicalCompositeException;
import com.epam.task4.parser.ExpressionParser;
import com.epam.task4.parser.impl.TextParser;
import com.epam.task4.service.impl.LexicalComponentServiceImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Main {
     public static void main(String[] args) throws IOException {
        //String path = ClassLoader.getSystemResource("text.txt").getPath();
        
        final String sourceFile = "./src/com/epam/task4/src/text.txt";
        //final String sourceFile = "./src/com/epam/task4/src/arithmetic.txt";
        //final String sourceFile = "./src/com/epam/task4/src/light.txt";
        //final String sourceFile = "./src/com/epam/task4/src/bit.txt";
        
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine()).append("\n");
            }
        }
        
        LexicalComposite composite = new TextParser().parse(text.toString());
        LexicalComponentServiceImpl service = new LexicalComponentServiceImpl();
        service.sortParagraphs(composite);
        Map<String, Integer> wordsAlike = service.findAndCountAlikeWords(composite);
        try{
            for (LexicalComponent paragraph : composite.getComponentList())
                for ( LexicalComponent sentence : paragraph.getComponentList())
                    wordsAlike = service.countVowelConsonant(sentence);
        } catch (LexicalCompositeException e){
                        
        }
        
        
    }
}
