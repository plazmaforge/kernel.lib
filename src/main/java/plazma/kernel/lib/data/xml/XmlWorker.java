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

import java.io.IOException;

import plazma.kernel.lib.data.DataWorker;
import plazma.kernel.lib.data.node.Node;

public class XmlWorker implements DataWorker {

    private XmlReader reader;

    private XmlWriter writer;

    // tokenize

    public String[] tokenizeXmlFromText(String input) {
        return getReader().tokenizeXmlFromText(input);
    }

    public String[] tokenizeXmlFromArray(char[] input) {
        return getReader().tokenizeXmlFromArray(input);
    }

    // parse

    public Node parseXmlFromTokens(String[] tokens) {
        return getReader().parseXmlFromTokens(tokens);
    }

    // read

    public Node readXmlFromText(String input) {
        return getReader().readXmlFromText(input);
    }
    
    public Node readXmlFromArray(char[] input) {
        return getReader().readXmlFromArray(input);
    }

    public Node readXmlFromFile(String fileName) throws IOException {
        return getReader().readXmlFromFile(fileName);
    }

    // write

    public String writeXmlToText(Node root) {
        return getWriter().writeXmlToText(root);
    }

    public String writeXmlToText(XmlWriterConfig config, Node root) {
        return getWriter().writeXmlToText(config, root);
    }
    
    public void writeXmlToFile(String fileName, Node root) throws IOException {
        getWriter().writeXmlToFile(fileName, root);
    }

    public void writeXmlToFile(String fileName, XmlWriterConfig config, Node root) throws IOException {
        getWriter().writeXmlToFile(fileName, config, root);
    }
    
    //

    public void writeXmlToConsole(Node root) throws IOException {
        getWriter().writeXmlToConsole(root);
    }

    public void writeXmlToConsole(XmlWriterConfig config, Node root) throws IOException {
        getWriter().writeXmlToConsole(config, root);
    }

    // helper

    protected XmlReader getReader() {
        if (reader == null) {
            reader = new XmlReader();
        }
        return reader;
    }

    protected XmlWriter getWriter() {
        if (writer == null) {
            writer = new XmlWriter();
        }
        return writer;
    }

    ////

    @Override
    public String[] tokenizeFromText(String input) {
        return tokenizeXmlFromText(input);
    }

    @Override
    public String[] tokenizeFromArray(char[] input) {
        return tokenizeXmlFromArray(input);
    }

    @Override
    public Node parseFromTokens(String[] tokens) {
        return parseXmlFromTokens(tokens);
    }

    @Override
    public Node readFromText(String input) {
        return readXmlFromText(input);
    }

    @Override
    public Node readFromFile(String fileName) throws IOException {
        return readXmlFromFile(fileName);
    }

    @Override
    public String writeToText(Node root) {
        return writeXmlToText(root);
    }

}
