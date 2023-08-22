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

package plazma.kernel.lib.data.json;

import java.io.IOException;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.data.xml.Xml2JsonConverter;
import plazma.kernel.lib.data.xml.Xml2YamlConverter;

public class JsonLib {
    
    // tokenize

    public static String[] tokenizeJsonFromText(String input) {
        return new JsonTokenizer().tokenizeJsonFromText(input);
    }

    public static String[] tokenizeJsonFromArray(char[] input) {
        return new JsonTokenizer().tokenizeJsonFromArray(input);
    }

    // parse

    public static Node parseJsonFromTokens(String[] tokens) {
        return new JsonParser().parseJsonFromTokens(tokens);
    }

    public static Node parseJsonFromTokens(JsonReaderConfig config, String[] tokens) {
        return new JsonParser().parseJsonFromTokens(config, tokens);
    }

    // read

    public static Node readJsonFromText(String input) {
        return new JsonReader().readJsonFromText(input);
    }

    public static Node readJsonFromText(JsonReaderConfig config, String input) {
        return new JsonReader().readJsonFromText(config, input);
    }
    
    //

    public static Node readJsonFromFile(String fileName) throws IOException {
        return new JsonReader().readJsonFromFile(fileName);
    }

    public static Node readJsonFromFile(JsonReaderConfig config, String fileName) throws IOException {
        return new JsonReader().readJsonFromFile(config, fileName);
    }

    // write

    public static String writeJsonToText(Node root) {
        return new JsonWriter().writeJsonToText(root);
    }

    public static String writeJsonToText(JsonWriterConfig config, Node root) {
        return new JsonWriter().writeJsonToText(config, root);
    }
    
    //
    
    public static void writeJsonToFile(String fileName, Node root) throws IOException {
        new JsonWriter().writeJsonToFile(fileName, root);
    }

    public static  void writeJsonToFile(String fileName, JsonWriterConfig config, Node root) throws IOException {
        new JsonWriter().writeJsonToFile(fileName, config, root);
    }
    
    //

    public static void writeJsonToConsole(Node root) {
        new JsonWriter().writeJsonToConsole(root);
    }

    public static void writeJsonToConsole(JsonWriterConfig config, Node root) {
        new JsonWriter().writeJsonToConsole(config, root);        
    }
    
    // convert

    public static Node convertJsonToXml(Node node) {
        Json2XmlConverter converter = new Json2XmlConverter();
        Node result = converter.convert(node);
        //if (converter != null) {
        //    converter.destroy();
        //}        
        return result;
    }

    public static Node convertJsonToYaml(Node node) {
        Json2YamlConverter converter = new Json2YamlConverter();
        Node result = converter.convert(node);
        //if (converter != null) {
        //    converter.destroy();
        //}        
        return result;
    }


}
