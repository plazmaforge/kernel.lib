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

package plazma.kernel.lib.data.yaml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.text.ParserContext;

public class YamlParserContext extends ParserContext {
    
    public int currEvent;
    public int prevEvent;
    public int nodeEvent;

    public String currAttribute;

    public Node currNode;
    public Node lastNode; // YAML

    public Map<Integer, String> map;
    public boolean isRestored;
    
    public boolean isNewLine;
    public boolean isBlankLine;
    public boolean isNewBlankLine;
    public int spaceCount;
    public int indent;

    public List<String> lines;
    public boolean multiValue;
    
    public void init() {
        map = new LinkedHashMap<Integer, String>();
        lines = new ArrayList<>();
    }
    
    public void destroy() {
        currAttribute = null;
        currNode = null;
        lastNode = null;

        if (map != null) {
            map.clear();
            map = null;
        }
        if (lines != null) {
            lines.clear();
            lines = null;            
        }
    }

    public boolean levelDown(Node node, boolean sync) {
        if (node == null) {
            return false;
        }
        
        this.currNode = node;

        if (sync) {
            this.lastNode = node;
        }

        // Level DOWN
        this.level++;

        return true;
    }

    public boolean levelDown(Node node) {
        return levelDown(node, false);
    }

    public boolean levelUp(Node node) {

        this.currNode = node;

        // Level UP
        this.level--;

        return true;
    }

    public boolean levelUp(Node node, int count) {

        this.currNode = node;

        // Level UP
        for (int i = 0; i < count; i++) {
            this.level--;
        }
                
        return true;
    }

    public boolean levelUp() {
        if (this.currNode == null) {
            return false;
        }

        return levelUp(this.currNode.getParent());

        //this.currNode = this.currNode.getParent();
        // Level UP
        //this.level--;
        //return true;
    }


    public void resetBlankLineState() {
        spaceCount = 0;
        isBlankLine = false;
        isNewBlankLine = false;
    }

    ////

    public void addLine(String line) {
        this.lines.add(line);
    }

    public void clearLines() {
        this.lines.clear();
    }

    public String getLines() {
        if (this.lines.isEmpty()) {
            return "";
        }
        int size = this.lines.size();
        StringBuilder result = new StringBuilder();
        String line;
        for (int i = 0; i < size; i++) {
            line = this.lines.get(i);
            result.append(line);
        }
        return result.toString();
    }

    public String getFirstLines(String attribute) {
        if (this.lines.isEmpty()) {
            return "";
        }

        int size = this.lines.size();
        StringBuilder result = new StringBuilder();
        String line = null;
        String a = null;

        boolean check = attribute != null;
        if (check) {
            a = attribute;
        }
        List<String> lines = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            line = this.lines.get(i);
            if (check) {
                if (eq(line, a)) {
                    break;
                }
            }
            
            lines.add(line);
        }

        if (lines.isEmpty()) {
            return "";
        }

        int start = -1;
        int end = -1;

        // Check '\n' before
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            if (!eq(line, "\n")) {
                start = i;
                break;
            }            
        }

        if (start == -1) {
            return "";
        }

        // Check '\n' after        
        for (int i = lines.size() - 1; i >= 0; i--) {
            line = lines.get(i);
            if (!eq(line, "\n")) {
                end = i;
                break;
            }            
        }

        for (int i = start; i <= end; i++) {
            line = lines.get(i);
            result.append(line);
        }

        return result.toString();
    }

    public int getLineSize() {
        return this.lines.size();                
    }

    public int getRealSize() {
        if (this.lines.isEmpty()) {
            return 0;
        }
        int size = this.lines.size();
        int result = 0;
        String line;
        for (int i = 0; i < size; i++) {
            line = this.lines.get(i);
            if (!eq(line, "\n")) {
                result++;
            }
        }
        return result;
    }

    ////

    public int getIndent(String token) {
        if (token == null) {
            return 0;
        }
        String str = token;
        if (str.isEmpty()) {
            return 0;
        }
        int count = str.length();
        for (int i = 0; i < count; i++) {
            if (str.charAt(i) != ' ') {
                return i;
            }
        }
        return count;
    }

    public int getSpaceCount(String token) {
        if (token == null) {
            return 0;
        }
        String str = token;
        if (str.isEmpty()) {
            return 0;
        }
        int indent = getIndent(token);
        int count = str.length();
        return indent < count ? 0 : count;

        //for (int i = 0; i < count; i++) {
        //    if (str[i] != ' ') {
        //        return 0;
        //    }
        //}
        //return count;
    }

    public boolean checkBlankLine(String token) {
        int count = getSpaceCount(token);
        return count > 0;
    }

    public boolean checkNewLine(String token) {
        if (token == null) {
            return false;
        }
        return (eq(token, "\r") 
            || eq(token, "\n") 
            || eq(token, "\r\n"));
    }

    public boolean checkNextNewLine(String[] tokens, int tokenLen, int i) {
        if (tokens == null) {
            return false;
        }
        
        i++;
        // Has next token
        if (i >= tokenLen) {
            return false;
        }

        // Get next token
        String nextToken = tokens[i];
        if (nextToken == null) {
            return false;
        }

        // Next token is NewLine
        return checkNewLine(nextToken);
    }
    
    ////
    
    protected boolean eq(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;            
        }
        return str1.equals(str2);        
    }

    ////

    public String toString() {
        return "YamlParserContext[]";
    }


}
