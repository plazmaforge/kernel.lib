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

import java.io.IOException;

import plazma.lib.data.DataWorker;
import plazma.lib.data.node.Node;

public class CsvWorker implements DataWorker {

    private CsvReader reader;

    // private CsvWriter writer;

    // tokenize

    public String[] tokenizeCsvFromText(String input) {
        return getReader().tokenizeCsvFromText(input);
    }

    public String[] tokenizeCsvFromArray(char[] input) {
        return getReader().tokenizeCsvFromArray(input);
    }

    // parse

    public Node parseCsvFromTokens(String[] tokens) {
        return getReader().parseCsvFromTokens(tokens);
    }

    public Node readCsvFromText(String input) {
        return getReader().readCsvFromText(input);
    }
    
    // read  

    public Node readCsvFromFile(String fileName) throws IOException {
        return getReader().readCsvFromFile(fileName);
    }

    // write

    public String writeCsvToText(Node root) {
        return null; // TODO: getWriter().writeCsvToText(root);
    }

    // helper

    public CsvReader getReader() {
        if (reader == null) {
            reader = new CsvReader();
        }
        return reader;
    }

    /*
     * public CsvWriter getWriter() { if (writer == null) { writer = new
     * CsvWriter(); } return writer; }
     */

    ////

    @Override
    public String[] tokenizeFromText(String input) {
        return tokenizeCsvFromText(input);
    }

    @Override
    public String[] tokenizeFromArray(char[] input) {
        return tokenizeCsvFromArray(input);
    }

    @Override
    public Node parseFromTokens(String[] tokens) {
        return parseCsvFromTokens(tokens);
    }

    @Override
    public Node readFromText(String input) {
        return readCsvFromText(input);
    }

    @Override
    public Node readFromFile(String fileName) throws IOException {
        return readCsvFromFile(fileName);
    }

    @Override
    public String writeToText(Node root) {
        return writeCsvToText(root);
    }

}
