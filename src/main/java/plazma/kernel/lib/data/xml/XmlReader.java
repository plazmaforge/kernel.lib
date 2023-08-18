
/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.kernel.lib.data.xml;

import java.io.File;
import java.io.IOException;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.io.IOLib;

public class XmlReader {

    private XmlTokenizer tokenizer;

    private XmlParser parser;
     
    
    // tokenize

    public String[] tokenizeXmlFromText(String input) {
        return getTokenizer().tokenizeXmlFromText(input);
    }

    public String[] tokenizeXmlFromArray(char[] input) {
        return getTokenizer().tokenizeXmlFromArray(input);
    }

    // parse

    public Node parseXmlFromTokens(String[] tokens) {
        return getParser().parseXmlFromTokens(tokens);
    }

    public Node parseXmlFromTokens(XmlReaderConfig config, String[] tokens) {
        return getParser().parseXmlFromTokens(config, tokens);
    }
    
    // read

    public Node readXmlFromText(String input) {
        String[] tokens = tokenizeXmlFromText(input);
        Node root = parseXmlFromTokens(tokens);
        return root;
    }

    public Node readXmlFromText(XmlReaderConfig config, String input) {
        String[] tokens = tokenizeXmlFromText(input);
        Node root = parseXmlFromTokens(config, tokens);
        return root;
    }

    //

    public Node readXmlFromArray(char[] input) {
        String[] tokens = tokenizeXmlFromArray(input);
        Node root = parseXmlFromTokens(tokens);
        return root;
    }

    public Node readXmlFromArray(XmlReaderConfig config, char[] input) {
        String[] tokens = tokenizeXmlFromArray(input);
        Node root = parseXmlFromTokens(config, tokens);
        return root;
    }

    //
    
    public Node readXmlFromFile(String fileName) throws IOException {
        char[] chars = IOLib.readChars(fileName);
        Node root = readXmlFromArray(chars);
        return root;
    }

    public Node readXmlFromFile(XmlReaderConfig config, String fileName) throws IOException {
        char[] chars = IOLib.readChars(fileName);
        Node root = readXmlFromArray(config, chars);
        return root;
    }
    
    //

    public Node readXmlFromFile(File file) throws IOException {
        char[] chars = IOLib.readChars(file);
        Node root = readXmlFromArray(chars);
        return root;
    }

    public Node readXmlFromFile(XmlReaderConfig config, File file) throws IOException {
        char[] chars = IOLib.readChars(file);
        Node root = readXmlFromArray(config, chars);
        return root;
    }

    ////

    protected XmlTokenizer getTokenizer() {
        if (tokenizer == null) {
            tokenizer = new XmlTokenizer();
        }
        return tokenizer;
    }

    protected XmlParser getParser() {
        if (parser == null) {
            parser = new XmlParser();
        }
        return parser;
    }

}
