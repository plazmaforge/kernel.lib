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

import java.io.IOException;

import plazma.lib.data.DataWorker;
import plazma.lib.data.node.Node;

public class JsonWorker implements DataWorker {

    private JsonReader reader;

    // private JsonWriter writer;

    // tokenize

    public String[] tokenizeJsonFromText(String input) {
        return getReader().tokenizeJsonFromText(input);
    }

    public String[] tokenizeJsonFromArray(char[] input) {
        return getReader().tokenizeJsonFromArray(input);
    }

    // parse

    public Node parseJsonFromTokens(String[] tokens) {
        return getReader().parseJsonFromTokens(tokens);
    }
    
    // read

    public Node readJsonFromText(String input) {
        return getReader().readJsonFromText(input);
    }

    public Node readJsonFromFile(String fileName) throws IOException {
        return getReader().readJsonFromFile(fileName);
    }

    // write

    public String writeJsonToText(Node root) {
        return null; // TODO: getWriter().writeJsonToText(root);
    }

    // helper

    public JsonReader getReader() {
        if (reader == null) {
            reader = new JsonReader();
        }
        return reader;
    }

    /*
     * public JsonWriter getWriter() { if (writer == null) { writer = new
     * JsonWriter(); } return writer; }
     */

    ////

    @Override
    public String[] tokenizeFromText(String input) {
        return tokenizeJsonFromText(input);
    }

    @Override
    public String[] tokenizeFromArray(char[] input) {
        return tokenizeJsonFromArray(input);
    }

    @Override
    public Node parseFromTokens(String[] tokens) {
        return parseJsonFromTokens(tokens);
    }

    @Override
    public Node readFromText(String input) {
        return readJsonFromText(input);
    }

    @Override
    public Node readFromFile(String fileName) throws IOException {
        return readJsonFromFile(fileName);
    }

    @Override
    public String writeToText(Node root) {
        return writeJsonToText(root);
    }

}
