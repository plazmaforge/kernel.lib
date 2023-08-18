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

package plazma.kernel.lib.sys;

public class Parameter implements Cloneable {
    
    private String name;

    private String description;

    private String dataType;

    private boolean required;

    private String value;

    private String defaultValue;
    
    //
    
    private boolean hasFlag; // has value

    private boolean found;   // found in parameter list
    
    //
    
    public Parameter() {
        super();
    }
    
    public Parameter(String name) {
        this.name = name;
    }

    public Parameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Parameter(String name, String description, String dataType) {
        this.name = name;
        this.description = description;
        this.dataType = dataType;
    }

    public Parameter(String name, String description, String dataType, boolean required) {
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.required = required;
    }

    //

    public Parameter(String name, String description, boolean required) {
        this.name = name;
        this.description = description;
        this.required = required;
    }

    public Parameter(String name, boolean required) {
        this.name = name;
        this.required = required;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.hasFlag = true;
    }
    
    public boolean hasValue() {
        return this.hasFlag;        
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void resetValue() {
        this.value = null;
        this.hasFlag = false;
    }

    public void reset() {
        resetValue();
        this.found = false;
    }


    public boolean isFound() {
        return found;
    }


    public void setFound(boolean found) {
        this.found = found;
    }

    public Parameter clone() {
        
        Parameter result  = new Parameter();

        result.name = this.name;
        result.description = this.description;
        result.dataType = this.dataType;
        result.required = this.required;
        result.value = this.value;
        result.defaultValue = this.defaultValue;

        result.hasFlag = this.hasFlag;
        result.found = this.found;

        return result;
    }

    

}
