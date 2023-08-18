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

package plazma.kernel.lib.task;

import plazma.kernel.lib.sys.Parameter;
import plazma.kernel.lib.sys.Parameters;

public class TaskContext {
    
    private String taskName;
    
    private Parameters parameters;

    public TaskContext() {
        super();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Parameters getParameters() {
        if (parameters == null) {
            parameters = new Parameters();
        }
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    
    
    ////

    public Parameter getFoundParameter(String name) {

        ///////////////////////////////////////////
        Parameter parameter = getParameters().getParameter(name);
        if (parameter == null) {
            return null;
        }

        // We can have a parameter in the list but this parameter can be not found
        if (!parameter.isFound()) {
            return null;
        }

        return parameter;
        ////////////////////////////////////////////
    }

    ////

    public boolean hasParameter(String name) {

        ////////////////////////////////////////////
        Parameter parameter = getFoundParameter(name);
        return parameter != null;
        ////////////////////////////////////////////

        //return getParameters().hasParameter(name);
    }

    public String getStringParameter(String name) {
        return getParameters().getStringParameter(name);
    }

    public String getStringParameter(String name, String defValue) {
        return getParameters().getStringParameter(name, defValue);
    }

    public int getIntParameter(String name) {
        return getParameters().getIntParameter(name);
    }

    public int getIntParameter(String name, int defValue) {
        return getParameters().getIntParameter(name, defValue);
    }

    public float getFloatParameter(String name) {
        return getParameters().getFloatParameter(name);
    }

    public float getFloatParameter(String name, float defValue) {
        return getParameters().getFloatParameter(name, defValue);
    }

    public boolean getBooleanParameter(String name) {
        return getParameters().getBooleanParameter(name);
    }

    public boolean getBooleanParameter(String name, boolean defValue) {
        return getParameters().getBooleanParameter(name, defValue);
    }

    public boolean getFlagParameter(String name) {

        ////////////////////////////////////////////
        Parameter parameter = getFoundParameter(name);
        if (parameter == null) {
            return false;
        }

        if (!parameter.hasValue() || parameter.getValue().isEmpty()) {
            return true; // option - only name
        }
        ////////////////////////////////////////////

        return getParameters().getBooleanParameter(name);
    }

    public boolean getFlagParameter(String name, boolean defValue) {

        ////////////////////////////////////////////
        Parameter parameter = getFoundParameter(name);
        if (parameter == null) {
            return defValue;
        }

        if (!parameter.hasValue() || parameter.getValue().isEmpty()) {
            return true; // option - only name
        }
        ////////////////////////////////////////////

        return getParameters().getBooleanParameter(name, defValue);
    }    

}
