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

import java.io.IOException;

import plazma.lib.data.node.Node;

public class YamlLib {
    
    // tokenize

    public static String[] tokenizeYamlFromText(String input) {
        return new YamlTokenizer().tokenizeYamlFromText(input);
    }

    public static String[] tokenizeYamlFromArray(char[] input) {
        return new YamlTokenizer().tokenizeYamlFromArray(input);
    }

    // parse

    public static Node parseYamlFromTokens(String[] tokens) {
        return new YamlParser().parseYamlFromTokens(tokens);
    }

    public static Node parseYamlFromTokens(YamlReaderConfig config, String[] tokens) {
        return new YamlParser().parseYamlFromTokens(config, tokens);
    }

    // read

    public static Node readYamlFromText(String input) {
        return new YamlReader().readYamlFromText(input);
    }

    public static Node readYamlFromText(YamlReaderConfig config, String input) {
        return new YamlReader().readYamlFromText(config, input);
    }

    //

    public static Node readYamlFromArray(char[] input) {
        return new YamlReader().readYamlFromArray(input);
    }

    public static Node readYamlFromArray(YamlReaderConfig config, char[] input) {
        return new YamlReader().readYamlFromArray(config, input);
    }

    //

    public static Node readYamlFromFile(String fileName) throws IOException {
        return new YamlReader().readYamlFromFile(fileName);
    }

    public static Node readYamlFromFile(YamlReaderConfig config, String fileName) throws IOException {
        return new YamlReader().readYamlFromFile(config, fileName);
    }

    // write

    public static String writeYamlToText(Node root) {
        return new YamlWriter().writeYamlToText(root);
    }

    public static String writeYamlToText(YamlWriterConfig config, Node root) {
        return new YamlWriter().writeYamlToText(config, root);
    }
    
    //
    
    public static void writeYamlToFile(String fileName, Node root) throws IOException {
        new YamlWriter().writeYamlToFile(fileName, root);
    }

    public static  void writeYamlToFile(String fileName, YamlWriterConfig config, Node root) throws IOException {
        new YamlWriter().writeYamlToFile(fileName, config, root);
    }

    //

    public static void writeYamlToConsole(Node root) throws IOException {
        new YamlWriter().writeYamlToConsole(root);
    }

    public static void writeYamlToConsole(YamlWriterConfig config, Node root) throws IOException {
        new YamlWriter().writeYamlToConsole(config, root);
    }

    // convert

    public static Node convertYamlToJson(Node node) {
        Yaml2JsonConverter converter = new Yaml2JsonConverter();
        Node result = converter.convert(node);
        //if (converter != null) {
        //    converter.destroy();
        //}        
        return result;
    }

    public static Node convertYamlToXml(Node node) {
        Yaml2XmlConverter converter = new Yaml2XmlConverter();
        Node result = converter.convert(node);
        //if (converter != null) {
        //    converter.destroy();
        //}        
        return result;
    }

}
