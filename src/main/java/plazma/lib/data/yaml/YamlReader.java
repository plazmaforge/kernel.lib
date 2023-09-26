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

package plazma.lib.data.yaml;

import java.io.File;
import java.io.IOException;

import plazma.lib.data.node.Node;
import plazma.lib.io.IOLib;

public class YamlReader {

    private YamlTokenizer tokenizer;

    private YamlParser parser;
     
    
    // tokenize

    public String[] tokenizeYamlFromText(String input) {
        return getTokenizer().tokenizeYamlFromText(input);
    }

    public String[] tokenizeYamlFromArray(char[] input) {
        return getTokenizer().tokenizeYamlFromArray(input);
    }

    // parse

    public Node parseYamlFromTokens(String[] tokens) {
        return getParser().parseYamlFromTokens(tokens);
    }

    public Node parseYamlFromTokens(YamlReaderConfig config, String[] tokens) {
        return getParser().parseYamlFromTokens(config, tokens);
    }
    
    // read

    public Node readYamlFromText(String input) {
        String[] tokens = tokenizeYamlFromText(input);
        Node root = parseYamlFromTokens(tokens);
        return root;
    }

    public Node readYamlFromText(YamlReaderConfig config, String input) {
        String[] tokens = tokenizeYamlFromText(input);
        Node root = parseYamlFromTokens(config, tokens);
        return root;
    }

    //

    public Node readYamlFromArray(char[] input) {
        String[] tokens = tokenizeYamlFromArray(input);
        Node root = parseYamlFromTokens(tokens);
        return root;
    }

    public Node readYamlFromArray(YamlReaderConfig config, char[] input) {
        String[] tokens = tokenizeYamlFromArray(input);
        Node root = parseYamlFromTokens(config, tokens);
        return root;
    }

    //
    
    public Node readYamlFromFile(String fileName) throws IOException {
        char[] chars = IOLib.readChars(fileName);
        Node root = readYamlFromArray(chars);
        return root;
    }

    public Node readYamlFromFile(YamlReaderConfig config, String fileName) throws IOException {
        char[] chars = IOLib.readChars(fileName);
        Node root = readYamlFromArray(config, chars);
        return root;
    }
    
    //

    public Node readYamlFromFile(File file) throws IOException {
        char[] chars = IOLib.readChars(file);
        Node root = readYamlFromArray(chars);
        return root;
    }

    public Node readYamlFromFile(YamlReaderConfig config, File file) throws IOException {
        char[] chars = IOLib.readChars(file);
        Node root = readYamlFromArray(config, chars);
        return root;
    }

    ////

    protected YamlTokenizer getTokenizer() {
        if (tokenizer == null) {
            tokenizer = new YamlTokenizer();
        }
        return tokenizer;
    }

    protected YamlParser getParser() {
        if (parser == null) {
            parser = new YamlParser();
        }
        return parser;
    }

}
