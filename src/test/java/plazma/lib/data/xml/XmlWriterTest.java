package plazma.lib.data.xml;

import java.io.IOException;

import org.junit.Test;

import plazma.lib.AbstractTestCase;
import plazma.lib.data.node.Node;
import plazma.lib.data.xml.XmlTokenizer;
import plazma.lib.data.xml.XmlReader;
import plazma.lib.data.xml.XmlWriter;

public class XmlWriterTest extends AbstractTestCase {

    @Test
    public void testXmlWriter() throws IOException {
        String text = "<table    spacing='0>0'>   <tr><td>1</td><td>2</td></tr></table>";

        XmlTokenizer tokenizer = new XmlTokenizer();

        String[] tokens = tokenizer.tokenizeXmlFromText(text);
        printTokens(tokens);

        // read
        XmlReader reader = new XmlReader();
        Node root = reader.readXmlFromText(text);

        // write
        XmlWriter writer = new XmlWriter();
        String result = writer.writeXmlToText(root);

        System.out.println(result);

        String fileName = getFileName("/FirstJasper.jrxml");
        root = reader.readXmlFromFile(fileName);
        result = writer.writeXmlToText(root);

        System.out.println(result);

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
