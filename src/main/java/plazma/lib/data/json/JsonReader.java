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

package plazma.lib.data.json;

import java.io.File;
import java.io.IOException;

import plazma.lib.data.node.Node;
import plazma.lib.io.IOLib;

public class JsonReader {

    private JsonTokenizer tokenizer;

    private JsonParser parser;

    // tokenize

    public String[] tokenizeJsonFromText(String input) {
        return getTokenizer().tokenizeJsonFromText(input);
    }

    public String[] tokenizeJsonFromArray(char[] input) {
        return getTokenizer().tokenizeJsonFromArray(input);
    }
    
    // parse

    public Node parseJsonFromTokens(String[] tokens) {
        return getParser().parseJsonFromTokens(tokens);
    }

    public Node parseJsonFromTokens(JsonReaderConfig config, String[] tokens) {
        return getParser().parseJsonFromTokens(config, tokens);
    }

    // read
    
    public Node readJsonFromText(String input) {
        String[] tokens = tokenizeJsonFromText(input);
        Node root = parseJsonFromTokens(tokens);
        return root;
    }

    public Node readJsonFromText(JsonReaderConfig config, String input) {
        String[] tokens = tokenizeJsonFromText(input);
        Node root = parseJsonFromTokens(config, tokens);
        return root;
    }

    //

    public Node readJsonFromFile(String fileName) throws IOException {
        String text = IOLib.readText(fileName);
        Node root = readJsonFromText(text);
        return root;
    }

    public Node readJsonFromFile(JsonReaderConfig config, String fileName) throws IOException {
        String text = IOLib.readText(fileName);
        Node root = readJsonFromText(config, text);
        return root;
    }

    //

    public Node readJsonFromFile(File file) throws IOException {
        String text = IOLib.readText(file);
        Node root = readJsonFromText(text);
        return root;
    }

    public Node readJsonFromFile(JsonReaderConfig config, File file) throws IOException {
        String text = IOLib.readText(file);
        Node root = readJsonFromText(config, text);
        return root;
    }

    ////

    protected JsonTokenizer getTokenizer() {
        if (tokenizer == null) {
            tokenizer = new JsonTokenizer();
        }
        return tokenizer;
    }

    protected JsonParser getParser() {
        if (parser == null) {
            parser = new JsonParser();
        }
        return parser;
    }

}
