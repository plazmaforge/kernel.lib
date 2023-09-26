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

package plazma.lib.data.csv;

import java.io.File;
import java.io.IOException;

import plazma.lib.data.node.Node;
import plazma.lib.io.IOLib;

public class CsvReader {

    private CsvTokenizer tokenizer;

    // private CsvParser parser;

    public Node readCsvFromText(String input) {
        String[] tokens = tokenizeCsvFromText(input);
        Node root = parseCsvFromTokens(tokens);
        return root;
    }

    public Node readCsvFromFile(String fileName) throws IOException {
        String text = IOLib.readText(fileName);
        Node root = readCsvFromText(text);
        return root;
    }

    public Node readCsvFromFile(File file) throws IOException {
        String text = IOLib.readText(file);
        Node root = readCsvFromText(text);
        return root;
    }

    //// tokenize

    public String[] tokenizeCsvFromText(String input) {
        return getTokenizer().tokenizeCsvFromText(input);
    }

    public String[] tokenizeCsvFromArray(char[] input) {
        return getTokenizer().tokenizeCsvFromArray(input);
    }
    
    //// parse

    public Node parseCsvFromTokens(String[] tokens) {
        return null; // TODO getParser().parseCsvFromTokens(tokens);
    }

    ////

    protected CsvTokenizer getTokenizer() {
        if (tokenizer == null) {
            tokenizer = new CsvTokenizer();
        }
        return tokenizer;
    }


}
