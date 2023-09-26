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

package plazma.lib.task;

import static plazma.lib.task.TaskHelper.*;

import plazma.lib.sys.Parameter;
import plazma.lib.sys.Parameters;

public abstract class BaseTask implements Task {

    private boolean initFlag = false;

    private Parameters parameters;

    private boolean dysplayProcessing = true;
    
    private boolean dysplayTiming = true;

    
    public void init() {
        if (initFlag) {
            return;
        }
        initParameters();
        initFlag = true;
    }

    public void initParameters() {
        // by default
    }

    public void validate(TaskContext ctx) {
        if (ctx == null) {
            return;
        }
        validateParameters(ctx.getParameters());
    }

    public void validateParameters(Parameters parameters) {
        if (parameters == null) {
            return;
        }
        // by default
    }

    public void dump(TaskContext ctx) {
        if (ctx == null) {
            return;
        }
        dumpParameters(ctx.getParameters());
    }

    public void dumpParameters(Parameters parameters) {
        if (parameters == null) {
            return;
        }
                
        if (!parameters.hasParameters()) {
            return;
        }

        int parameterLen = maxParameterLen(parameters);
        int count = parameters.getParameterCount();
        Parameter parameter = null;

        for (int i = 0; i < count; i++) {
            parameter = parameters.getParameter(i);
            printParameter(parameter, parameterLen);
        }

   }

    public Parameters getParameters() {
        if (parameters == null) {
            parameters = new Parameters();
        }
        return parameters;
    }

    //
        
    public boolean isDysplayProcessing() {
        return dysplayProcessing;
    }

    public void setDysplayProcessing(boolean dysplayProcessing) {
        this.dysplayProcessing = dysplayProcessing;
    }

    public boolean isDysplayTiming() {
        return dysplayTiming;
    }

    public void setDysplayTiming(boolean dysplayTiming) {
        this.dysplayTiming = dysplayTiming;
    }
    
}
