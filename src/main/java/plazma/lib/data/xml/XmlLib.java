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

package plazma.lib.data.xml;

import java.io.IOException;

import plazma.lib.data.node.Node;

public class XmlLib {

    // tokenize

    public static String[] tokenizeXmlFromText(String input) {
        return new XmlTokenizer().tokenizeXmlFromText(input);
    }

    public static String[] tokenizeXmlFromArray(char[] input) {
        return new XmlTokenizer().tokenizeXmlFromArray(input);
    }

    // parse

    public static Node parseXmlFromTokens(String[] tokens) {
        return new XmlParser().parseXmlFromTokens(tokens);
    }

    public static Node parseXmlFromTokens(XmlReaderConfig config, String[] tokens) {
        return new XmlParser().parseXmlFromTokens(config, tokens);
    }

    // read

    public static Node readXmlFromText(String input) {
        return new XmlReader().readXmlFromText(input);
    }

    public static Node readXmlFromText(XmlReaderConfig config, String input) {
        return new XmlReader().readXmlFromText(config, input);
    }

    //

    public static Node readXmlFromArray(char[] input) {
        return new XmlReader().readXmlFromArray(input);
    }

    public static Node readXmlFromArray(XmlReaderConfig config, char[] input) {
        return new XmlReader().readXmlFromArray(config, input);
    }

    //

    public static Node readXmlFromFile(String fileName) throws IOException {
        return new XmlReader().readXmlFromFile(fileName);
    }

    public static Node readXmlFromFile(XmlReaderConfig config, String fileName) throws IOException {
        return new XmlReader().readXmlFromFile(config, fileName);
    }

    // write

    public static String writeXmlToText(Node root) {
        return new XmlWriter().writeXmlToText(root);
    }

    public static String writeXmlToText(XmlWriterConfig config, Node root) {
        return new XmlWriter().writeXmlToText(config, root);
    }
    
    //
    
    public static void writeXmlToFile(String fileName, Node root) throws IOException {
        new XmlWriter().writeXmlToFile(fileName, root);
    }

    public static  void writeXmlToFile(String fileName, XmlWriterConfig config, Node root) throws IOException {
        new XmlWriter().writeXmlToFile(fileName, config, root);
    }

    //

    public static void writeXmlToConsole(Node root) throws IOException {
        new XmlWriter().writeXmlToConsole(root);
    }

    public static void writeXmlToConsole(XmlWriterConfig config, Node root) throws IOException {
        new XmlWriter().writeXmlToConsole(config, root);
    }

    // convert

    public static Node convertXmlToJson(Node node) {
        Xml2JsonConverter converter = new Xml2JsonConverter();
        Node result = converter.convert(node);
        //if (converter != null) {
        //    converter.destroy();
        //}        
        return result;
    }

    public static Node convertXmlToYaml(Node node) {
        Xml2YamlConverter converter = new Xml2YamlConverter();
        Node result = converter.convert(node);
        //if (converter != null) {
        //    converter.destroy();
        //}        
        return result;
    }

}
