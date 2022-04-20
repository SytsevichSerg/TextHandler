
package com.epam.task4.parser;
import com.epam.task4.entity.LexicalComposite;
import com.epam.task4.exception.LexicalCompositeException;
import com.epam.task4.parser.impl.TextParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TextParserTest {
    private static final String FILE_PATH = "./test/com/epam/task4/src/arithmetic_short.txt";

    @Test
    public void testParse() throws IOException{
        StringBuilder text = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine()).append("\n");
            }
        }
        
        LexicalComposite composite = new TextParser().parse(text.toString());
        String expected ="\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining -8.0 essentially 7.714285714285714 unchanged.  It was popularised in the -873.3293064876957 with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.  \n";
        String actual = composite.toString();
        assertEquals(actual, expected);
    }
    
}
