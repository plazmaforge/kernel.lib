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

package plazma.lib.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import plazma.lib.data.csv.CsvWorker;
import plazma.lib.data.json.JsonWorker;
import plazma.lib.data.node.Node;
import plazma.lib.data.xml.XmlWorker;

public class DataLib {

    private static Map<String, DataWorker> workers = new HashMap<>();

    ////

    static {

        // Register standard workers
        workers.put("csv", new CsvWorker());
        workers.put("json", new JsonWorker());
        workers.put("xml", new XmlWorker());
    }

    private static DataWorker getWorker(String type) {
        if (type == null) {
            throw new RuntimeException("DataWorker not found: type is null");
        }
        DataWorker worker = workers.get(type.toLowerCase());
        if (worker == null) {
            throw new RuntimeException("DataWorker not found: type '" + type + "' is not supported");
        }
        return worker;
    }

    ////

    // tokenize

    public static String[] tokenizeFromText(String type, String input) {
        return getWorker(type).tokenizeFromText(input);
    }

    public static String[] tokenizeFromArray(String type, char[] input) {
        return getWorker(type).tokenizeFromArray(input);
    }

    // parse

    public static Node parseNodeFromTokens(String type, String[] tokens) {
        return getWorker(type).parseFromTokens(tokens);
    }

    // read

    public static Node readNodeFromText(String type, String input) {
        return getWorker(type).readFromText(input);
    }

    public static Node readNodeFromFile(String type, String fileName) throws IOException {
        return getWorker(type).readFromFile(fileName);
    }

    // write

    public static String writeNodeToText(String type, Node root) {
        return getWorker(type).writeToText(root);
    }

    public static void writeNodeToFile(String type, Node root, String fileName) throws IOException {
        // TODO
        // getWorker(type).writeNodeToFile(type, root, fileName);
    }

}
