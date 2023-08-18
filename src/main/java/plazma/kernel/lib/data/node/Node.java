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

package plazma.kernel.lib.data.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Node implements Serializable {

    private static final long serialVersionUID = -6504811570231930505L;

    private String name;

    private String text;
    
    private int type;
    
    private int subType;

    private Node parent;

    private List<Node> children;

    private Map<String, String> attributes;
    
    private int indent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public boolean hasText() {
        return text != null;
    }

    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getParentType() {
        if (this.parent == null) {
            return 0;
        }
        return this.parent.type;
    }

    public int getParentSubType() {
        if (this.parent == null) {
            return 0;
        }
        return this.parent.subType;
    }

    //

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    //

    public void addChild(Node child) {
        if (child == null) {
            // TODO: error
            return;
        }
        child.setParent(this);
        getChildren().add(child);
    }

    public void removeChild(Node child) {
        if (child == null) {
            // TODO: error
            return;
        }
        if (children == null) {
            return;
        }
        child.setParent(null);
        getChildren().remove(child);
    }

    public Node getChild(int index) {
        if (index < 0 || index >= getChildCount()) {
            return null;
        }
        return children.get(index);
    }

    public Node firstChild() {
        if (!hasChildren()) {
            return null;
        }
        return children.get(0);
    }

    public Node lastChild() {
        if (!hasChildren()) {
            return null;
        }
        return children.get(getChildCount() - 1);
    }

    public List<Node> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public int getChildCount() {
        return children == null ? 0 : children.size();
    }

    //

    public void addAttribute(String name, String value) {
        getAttributes().put(name, value);
    }

    public void removeAttribute(String name) {
        if (attributes == null) {
            return;
        }
        attributes.remove(name);
    }

    public Map<String, String> getAttributes() {
        if (attributes == null) {
            attributes = new LinkedHashMap<>();
        }
        return attributes;
    }

    public boolean hasAttributes() {
        return attributes != null && !attributes.isEmpty();
    }

    public int getAttributeCount() {
        return attributes == null ? 0 : attributes.size();
    }

    ////
    
    public int getLevel()  { // 0 - getParent() == null
        if (parent == null) {
            return 0;
        }
        
        //Node* next = parent;
        //int level = 0;
        //while (next != null) {
        //    level++;
        //    next = next.getParent();
        //}

        Node next = this;
        int level = 0;
        while ( (next = next.getParent()) != null) {
            level++;
        }

        return level;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }
    
    public int getIndent() {
        return this.indent;
    }

    public boolean isType(int type, int subType) {
        return this.getType() == type && this.getSubType() == subType;
    }

    public boolean isType(int type) {
        return this.getType() == type;
    }

    public boolean isSubType(int subType) {
        return this.getSubType() == subType;
    }


    public boolean isParentType(int type) {
        if (this.parent == null) {
            return false;
        }
        return this.parent.getType() == type;
    }

    public boolean isParentType(int type, int subType) {
        if (this.parent == null) {
            return false;
        }
        return this.parent.getType() == type && this.parent.getSubType() == subType;
    }

    public boolean isParentSubType(int subType) {
        if (this.parent == null) {
            return false;
        }
        return this.parent.getSubType() == subType;
    }
    
}
