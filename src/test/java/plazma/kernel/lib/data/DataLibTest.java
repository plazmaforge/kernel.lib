package plazma.kernel.lib.data;

import plazma.kernel.lib.AbstractTestCase;
import plazma.kernel.lib.data.DataLib;

public class DataLibTest extends AbstractTestCase {

    public void testCsvTokenizer() {

        String text = "1, 'Name', 123.45";
        String[] tokens = DataLib.tokenizeFromText("csv", text);

        printHeader("Test-CSV-Tokenizer: tokens");
        printTokens(tokens);

        // Check size
        int len = 3;
        assertEquals(len, tokens.length);

        // Check null
        for (int i = 0; i < len; i++) {
            assertNotNull(tokens[i]);
        }

        // Check value
        assertEquals("1", tokens[0].trim());
        assertEquals("'Name'", tokens[1].trim());
        assertEquals("123.45", tokens[2].trim());
    }

    public void testJsonTokenizer() {

        // String text = "1, 'Name', 123.45";
        String text = "{'id': 1234, 'name': 'Alex', 'contacts': [1, 2, 3]}";
        String[] tokens = DataLib.tokenizeFromText("json", text);

        printHeader("Test-Json-Tokenizer: tokens");
        printTokens(tokens);

        // Check size
        int len = 19;
        assertEquals(len, tokens.length);

        // Check null
        for (int i = 0; i < len; i++) {
            assertNotNull(tokens[i]);
        }

        // Check value
        assertEquals("{", tokens[0]);
        assertEquals("'id'", tokens[1]);
        assertEquals(":", tokens[2]);
        assertEquals("1234", tokens[3]);
        assertEquals(",", tokens[4]);
        assertEquals("'name'", tokens[5]);
        assertEquals(":", tokens[6]);
        assertEquals("'Alex'", tokens[7]);
        assertEquals(",", tokens[8]);
        assertEquals("'contacts'", tokens[9]);
        assertEquals(":", tokens[10]);
        assertEquals("[", tokens[11]);
        assertEquals("1", tokens[12]);
        assertEquals(",", tokens[13]);
        assertEquals("2", tokens[14]);
        assertEquals(",", tokens[15]);
        assertEquals("3", tokens[16]);
        assertEquals("]", tokens[17]);
        assertEquals("}", tokens[18]);

    }

    protected void printTokens(String[] tokens) {
        if (tokens == null) {
            System.out.println("null");
            return;
        }
        if (tokens.length == 0) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i < tokens.length; i++) {
            System.out.println("" + (i + 1) + ": '" + tokens[i] + "'");
        }
    }

}
