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

import java.util.ArrayList;
import java.util.List;

import plazma.kernel.lib.num.NumLib;

public class Parameters {
    
    private List<Parameter> parameters;

    public Parameters() {
        super();
    }

    public void addParameter(Parameter parameter) {
        if (parameter == null) {
            return;
        }

        getParameters().add(parameter);

    }

    public void addParameter(String name) {
        addParameter(new Parameter(name));
    }

    public void addParameter(String name, String description) {
        addParameter(new Parameter(name, description));
    }

    public void addParameter(String name, String description, String datatType) {
        addParameter(new Parameter(name, description, datatType));
    }

    public void addParameter(String name, String description, String datatType, boolean required) {
        addParameter(new Parameter(name, description, datatType, required));
    }

    public void addParameter(String name, String description, boolean required) {
        addParameter(new Parameter(name, description, required));
    }

    public void addParameter(String name, boolean required) {
        addParameter(new Parameter(name, required));
    }

    ////

    public List<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList<Parameter>();
        }
        return parameters;
    }

    public boolean hasParameters() {
        return getParameterCount() > 0;
    }

    public int getParameterCount() {
        return parameters == null ? 0 : parameters.size();
    }

    // byIndex
    public Parameter getParameter(int index) {
        if (parameters == null) {
            return null;
        }
        if (index < 0 && index >= parameters.size()) {
            // Not found
            return null;
        }
        return parameters.get(index);
    }

    // byName
    public Parameter getParameter(String name) {
        if (parameters == null) {
            return null;
        }
        if (name == null || name.isEmpty()) {
            // Not found
            return null;
        }
        int count = getParameterCount();
        Parameter parameter = null;
        for (int i = 0; i < count; i++) {
            parameter = getParameter(i);
            if (equalsName(parameter, name)) {
                return parameter;
            }
        }
        // Not found
        return null;
    }

    public boolean hasParameter(String name) {
        Parameter parameter = getParameter(name);
        return parameter != null;
    }

    public boolean equalsName(Parameter parameter, String name) {
       if (parameter == null || name.isEmpty()) {
           return false;
        }
        return name == parameter.getName(); // TODO: Check 'longName'
    }    

    ////

    // string

    public String getStringParameter(String name) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return null;
        }
        return parameter.getValue();
    }

    public String getStringParameter(String name, String defValue) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return defValue;
        }
        return parameter.getValue();
    }

    // int

    public int getIntParameter(String name) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return 0;
        }
        return NumLib.toInt(parameter.getValue());
    }

    public int getIntParameter(String name, int defValue) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return defValue;
        }
        return NumLib.toInt(parameter.getValue(), defValue);
    }

    // float

    public float getFloatParameter(String name) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return 0.0f;
        }
        return NumLib.toFloat(parameter.getValue());
    }

    public float getFloatParameter(String name, float defValue) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return defValue;
        }
        return NumLib.toFloat(parameter.getValue(), defValue);
    }

    // boolean


    // [UTIL]
    public boolean toBoolean(String value, boolean defValue) {
        if (value == null || value.isEmpty()) {
            return defValue;
        }

        String str = value.toLowerCase();

        // true
        if (str == "true" || str == "yes" || str == "t" || str == "y") {
            return true;
        }

        // false
        if (str == "false" || str == "no" || str == "f" || str == "n") {
            return false;
        }

        // default
        return defValue;

    }

    public boolean toBoolean(String value) {
        return toBoolean(value, false);
    }

    public boolean getBooleanParameter(String name) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return false;
        }
        return toBoolean(parameter.getValue());
    }

    public boolean getBooleanParameter(String name, boolean defValue) {
        Parameter parameter = getParameter(name);
        if (parameter == null) {
            return defValue;
        }
        return toBoolean(parameter.getValue(), defValue);
    }

}
