package plazma.lib.text;

import java.io.IOException;

import plazma.lib.AbstractTestCase;
import plazma.lib.data.node.Node;
import plazma.lib.data.xml.XmlWorker;
import plazma.lib.io.IOLib;
import plazma.lib.sys.SysLib;
import plazma.lib.text.TokenizerContext;
import plazma.lib.text.TextLib;

public class TextLibTest extends AbstractTestCase {

    public void testTokenizer() throws IOException {

        String text = "Hello, World: 'Hello, World'";

        char[] separators = new char[] { ' ', ':', ',' };
        char[] excludeSeparators = new char[] { ' ' };
        char[] quotes = new char[] { '\'', '"' };

        String[] tokens = null;

        println();
        println("TEST-1");
        tokens = TextLib.tokenize(text, separators, excludeSeparators, quotes, null);
        printTokens(tokens);

        excludeSeparators = new char[] { ' ', ':', ',' };
        println();
        println("TEST-2");
        tokens = TextLib.tokenize(text, separators, excludeSeparators, quotes, null);
        printTokens(tokens);

        text = "<table    spacing='0>0'>   <tr><td>1</td><td>2</td></tr></table>";
        separators = new char[] { '<', '>', ' ', '=', '?' };
        excludeSeparators = new char[] { ' ' };
        println();
        println("TEST-3");
        tokens = TextLib.tokenize(text, separators, excludeSeparators, quotes, null);
        printTokens(tokens);

        println("TEST-3-STRING");
        text = "<table    spacing='0>0'>   ABC <!-- COMMENTS-1 --> XYZ <!-- COMMENTS-2 -->  <tr><td>1</td><td>2</td><td><![CDATA[ <start> < end> ]]></td></tr><!-- COMMENTS-1 --> XYZ <!-- COMMENTS-2 --></table>";
        XmlWorker worker = new XmlWorker();
        Node root = worker.readXmlFromText(text);

        String xmlText = worker.writeXmlToText(root);
        println(xmlText);

        println();
        println("TEST-3-STRING");

        worker = new XmlWorker();

        println("TEST-START-!!!");
        println();

        String fileName = getFileName("/FirstJasper.jrxml"); // absolute path

        /*
         * try {
         * 
         * URL url = getResource("/FirstJasper.jrxml"); URI uri = url.toURI();
         * 
         * System.out.println("URL-1=" + url); System.out.println("URI-1=" + uri);
         * 
         * File file = new File(uri);
         * 
         * System.out.println("FLE-1=" + file.getAbsolutePath());
         * System.out.println("FLE-1=" + file.getCanonicalPath()); System.out.println();
         * 
         * url = getResource("/java/lang/Object.class"); uri = url.toURI();
         * 
         * 
         * System.out.println("URL-2=" + url); System.out.println("URI-2=" + uri);
         * System.out.println("EXT-2=" + url.toExternalForm());
         * 
         * file = new File(uri);
         * 
         * System.out.println("FLE-1=" + file.getAbsolutePath());
         * System.out.println("FLE-1=" + file.getCanonicalPath());
         * 
         * 
         * } catch (Throwable e) { e.printStackTrace(); }
         */

        text = IOLib.readText(getResourceAsStream("/FirstJasper.jrxml"));

        long time = SysLib.getTimeInMilliseconds();
        char[] chars = text.toCharArray();
        System.out.println("TIME-READ-CHARS: " + (SysLib.getTimeInMilliseconds() - time));

        time = SysLib.getTimeInMilliseconds();
        long count = 0;

        char[] a1 = { '\'', '"', '<', '>', '?', '=', ' ' };
        boolean found = false;
        int i = 0;

        /*
         * while (i < chars.length) { char ch = chars[i]; if (chars[i] > 0) { count++; }
         * 
         * found = Arrays.binarySearch(a1, ch) > -1; found = Arrays.binarySearch(a1, ch)
         * > -1; found = Arrays.binarySearch(a1, ch) > -1; found =
         * Arrays.binarySearch(a1, ch) > -1;
         * 
         * 
         * for (int j = 0; j < a1.length; j++) { if (ch == a1[j]) { found = true; } }
         * 
         * for (int j = 0; j < a1.length; j++) { if (ch == a1[j]) { found = true; } }
         * 
         * for (int j = 0; j < a1.length; j++) { if (ch == a1[j]) { found = true; } }
         * 
         * for (int j = 0; j < a1.length; j++) { if (ch == a1[j]) { found = true; } }
         * 
         * 
         * 
         * i++; } System.out.println("TIME-PROC-CHARS: " +
         * (SystemLib.getTimeInMilliseconds() - time) + " " + found);
         */

        time = SysLib.getTimeInMilliseconds();
        root = worker.readXmlFromFile(fileName);
        System.out.println("TIME-READ-DOCUMENT-SINGLE-1: " + (SysLib.getTimeInMilliseconds() - time));

        int K = 100; // 100000;
        time = SysLib.getTimeInMilliseconds();
        for (int k = 0; k < 10; k++) {
            // time = SystemLib.getTimeInMilliseconds();
            root = worker.readXmlFromFile(fileName);
            // System.out.println("TIME-READ-DOCUMENT-" + (k + 1) + ": " +
            // (SystemLib.getTimeInMilliseconds() - time));
        }

        time = SysLib.getTimeInMilliseconds() - time;
        System.out.println("[TOTAL] Count: " + K + ", Time: " + time);
        System.out.println("TIME-READ-DOCUMENT: " + (time * 1.0 / K));

        try {
            Thread.currentThread().sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        time = SysLib.getTimeInMilliseconds();
        root = worker.readXmlFromFile(fileName);
        System.out.println("TIME-READ-DOCUMENT-SINGLE-2: " + (SysLib.getTimeInMilliseconds() - time));

        println();
        println("TEST-END-!!!");

        println("TEST-3-NODE");

        xmlText = worker.writeXmlToText(root);

        println(xmlText);

        text = "{'id': 1234, 'name': 'Alex', 'contacts': [1, 2, 3]}";
        separators = new char[] { '{', '}', '[', ']', ',', ':' };
        // excludeSeparators = new char[] {' ', ':', ','};
        quotes = new char[] { '\'', '"' };

        println();
        println("TEST-4");
        tokens = TextLib.tokenize(text, separators, excludeSeparators, quotes, null);
        // printTokens(tokens);

    }

    public void testJsonTokenizer() throws IOException {
        String text = "{'id': 1234, 'name': 'Alex', 'contacts': [1, 2, 3]}";
        String[] separators = new String[] { "{", "}", "[", "]", ",", ":" };
        String[] excludeSeparators = null; // new char[] {' ', ':', ','};
        String[] quotes = new String[] { "'", "\"" };

        println();
        println("TEST-4-2");

        TokenizerContext context = new TokenizerContext();
        context.separators = separators;
        context.excludeSeparators = excludeSeparators;
        context.startQuotes = quotes;

        String[] tokens = TextLib.tokenize(context, text);
        printTokens(tokens);

    }

    public void testCsvTokenizer() throws IOException {
        String text = "Hello, ;World, 'Test ,; ' ";
        String[] separators = new String[] { ",", " ", ";" };
        String[] excludeSeparators = new String[] { ",", " ", ";" };
        String[] quotes = new String[] { "'", "\"" };

        println();
        println("TEST-4-3");

        TokenizerContext context = new TokenizerContext();
        context.separators = separators;
        context.excludeSeparators = excludeSeparators;
        context.startQuotes = quotes;

        String[] tokens = TextLib.tokenize(context, text);
        printTokens(tokens);

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
