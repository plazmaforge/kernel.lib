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

import plazma.lib.sys.Parameters;

public interface Task {

    String PARAMETER_TASK = "task";
    String PARAMETER_TASK_LIST = "task-list";
    String PARAMETER_VERSION = "version";
    String PARAMETER_HELP = "help";
    String PARAMETER_COLOR = "color";          // Color flag
    String PARAMETER_STDERR = "stderr";        // StdErr mode
    
    void execute(TaskContext ctx) throws Exception;
    
    public void init();
    
    public void initParameters();

    public void validate(TaskContext ctx);

    public void validateParameters(Parameters parameters);
    
    public void dump(TaskContext ctx);
    
    public void dumpParameters(Parameters parameters);
    
    public Parameters getParameters();

    boolean isDysplayProcessing();

    void setDysplayProcessing(boolean dysplayProcessing);

    boolean isDysplayTiming();

    void setDysplayTiming(boolean dysplayTiming);    

}
