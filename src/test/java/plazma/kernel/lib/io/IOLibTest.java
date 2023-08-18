package plazma.kernel.lib.io;

import java.util.List;

import plazma.kernel.lib.AbstractTestCase;
import plazma.kernel.lib.collection.CollectionLib;
import static plazma.kernel.lib.fs.FSLib.*;
import plazma.kernel.lib.io.IOLib;

public class IOLibTest extends AbstractTestCase {

    public void testIOLines() throws Exception {

        String fileName = TEST_DIR + "/" + "tmp_lines.dat";

        List<String> lines = CollectionLib.asList("Line 1", "Line 2", "Line 3");

        IOLib.writeLines(fileName, lines);

        List<String> lines2 = IOLib.readLines(fileName);
        rm(fileName);

        assertTrue(CollectionLib.equals(lines, lines2));
    }

    public void testIOText() throws Exception {

        String fileName = TEST_DIR + "/" + "tmp_text.dat";

        String text = "Hello World!";

        IOLib.writeText(fileName, text);

        String text2 = IOLib.readText(fileName);
        rm(fileName);

        assertEquals(text, text2);
        
        
        
        //String text32 = IOLib.readText("test_utf32.txt");
        //String text32 = IOLib.readText("./src/test/resources/test_utf32.txt");
        //String text32 = IOLib.readText(USER_DIR + "/src/test/resources/test_utf32.txt");
        
        String text32 = IOLib.readText(getFileName("/test_utf32.txt"));
        
        text32 = text32 + "\uFFBA";
        
        String text32p = text32 + "Abcdef";
        String text16 = "Hello world! Привет мир! _";
        
        char[] char32 = text32.toCharArray();
        
        //text32.codePointCount(0, text32.length());
        int start = text32p.lastIndexOf("!") + 1; // 24
        int pos = text32p.offsetByCodePoints(start, 2);
        
        System.out.println(text32);
        
        System.out.println();        
        System.out.println("Text32.len=" + text32.length());
        System.out.println("Char32.len=" + char32.length);
        System.out.println("Text32.cpo=" + text32.codePointCount(0, text32.length()));
        
        
        System.out.println();        
        System.out.println("Text16.len=" + text16.length());
        
        System.out.print("[");        
        for (int i = 0; i < char32.length - 1; i++ ) {
            System.out.print(char32[i]);            
        }        
        System.out.print("]");
        
        System.out.println();
        System.out.println(start);
        System.out.println(pos);
        System.out.println(text32p.substring(pos));
    }

    public void testCopyTextFile() throws Exception {

        String fileName = TEST_DIR + "/" + "tmp_text.txt";
        String fileNameUTF8 = TEST_DIR + "/" + "tmp_text_utf8.txt";
        String fileNameUTF16 = TEST_DIR + "/" + "tmp_text_utf16.txt";
        String fileNameUTF32 = TEST_DIR + "/" + "tmp_text_utf32.txt";

        String text = "Hello World!  Привет!";

        IOLib.writeText(fileName, text);
        IOLib.writeText(fileNameUTF8, text, "UTF-8");
        IOLib.writeText(fileNameUTF16, text, "UTF-16");
        IOLib.writeText(fileNameUTF32, text, "UTF-32");

        // String text2 = IOLib._readText(fileName);
        // rm(fileName);

        // assertEquals(text, text2);
    }

    /*
     * private void readTextFile(String fileName) throws Exception { //long start =
     * System.currentTimeMillis(); List<String> lines = IOLib._readLines(fileName);
     * //for (String line: lines ) { //System.out.print(line + "\n"); //} //long end
     * = System.currentTimeMillis();
     * 
     * //System.out.print("elapsed time: " + (end - start)); }
     */

}
