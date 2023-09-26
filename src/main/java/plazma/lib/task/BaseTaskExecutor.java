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

import java.util.List;
import java.util.Properties;

import plazma.lib.fmt.FmtLib;
import plazma.lib.sys.Parameter;
import plazma.lib.sys.Parameters;

import static plazma.lib.sys.SysLib.*;
import static plazma.lib.task.TaskHelper.*;

public class BaseTaskExecutor implements TaskExecutor {

    public static final String MESSAGE_TASK_LIST = "Task List:";
    public static final String MESSAGE_NO_TASKS = "No tasks";
    
    public static final String ERROR_TASK_NAME_EMPTY = "Task name is empty";
    public static final String ERROR_TASK_NOT_FOUND = "Task not found: '%s'";
    public static final String ERROR_TASK_NOT_IMPLEMENTED = "Task not implemented: '%s'";    
    public static final String ERROR_TASK_PROVIDER_NOT_IMPLEMENTED = "Task provider not implemented";

        
    private TaskProvider provider;
    
    private boolean initFlag;
            
    public void init() {
        if (initFlag) {
            return;
        }
        provider = new BaseTaskProvider();
        initFlag = true;
    }
    
    /*
    @Override
    public void executeTask(Task task) throws TaskException {
        if (task == null) {
            throw new RuntimeException("Can't execute task. Task is null");
        }
        executeTask(task, null);
    }

    @Override
    public void executeTask(Task task, Properties parameters) throws TaskException {
        if (task == null) {
            throw new RuntimeException("Can't execute task. Task is null");
        }
        task.execute(parameters);
    }
    */

    @Override
    public void executeTask(String taskName, Properties parameters) throws TaskException {
        
        // CHECK EMPTY NAME
        if (taskName == null || taskName.isEmpty()) {
            error(ERROR_TASK_NAME_EMPTY);
            return;
        }
        
        // CHECK NAME       
        if (!hasTask(taskName)) {
            error(FmtLib.format(ERROR_TASK_NOT_FOUND, taskName));
            return;            
        }

        Task task = getTask(taskName);
        
        // CHECK TASK
        if (task == null) {
            error(FmtLib.format(ERROR_TASK_NOT_IMPLEMENTED, taskName));
            return;
        }
        
        // Init task (register parameters)
        task.init();

        // Prepare Context
        TaskContext ctx = createTaskContext(task, parameters);
        ctx.setTaskName(taskName);

        boolean dysplayProcessing = task.isDysplayProcessing();
        boolean dysplayTiming = dysplayProcessing && task.isDysplayTiming();
        

        // Processing
        if (dysplayProcessing) {
            printStartTask(taskName);
            printProcessing();
            println();
        }

        // Validate
        task.validate(ctx);

        // Dumping
        if (dysplayProcessing) {
            task.dump(ctx);
        }

        long time = startTime();

        /////////////////////////
        
        try {
            task.execute(ctx);
        } catch (Exception e) {
            e.printStackTrace();            
        }
        
        ////////////////////////
        
        // JMH        
        time = stopTime(time);

        // Timing
        if (dysplayTiming) {
            println();
            printTotalTime(time);
        }


    }
    
    ////

    protected TaskContext createTaskContext(Task task, Properties parameters) {

        TaskContext ctx = new TaskContext();

        if (parameters == null || parameters.isEmpty()) {
            return ctx;
        }

        Parameters taskParameters = task.getParameters();
        if (taskParameters == null || !taskParameters.hasParameters()) {
            return ctx;
        }

        int count = taskParameters.getParameterCount();
        Parameter parameter = null;
        String name = null;
        String value = null;

        for (int i = 0; i < count; i++) {
            parameter = taskParameters.getParameter(i);

            // Transfer Parameter
            parameter.reset(); // why?

            // Clone because we transfer parameters from Task to Context
            parameter = parameter.clone();

            ctx.getParameters().addParameter(parameter);

            name = parameter.getName();
            if (!hasParameter(parameters, name)) {
                // Not Found
                continue;
            }

            parameter.setFound(true); // Mark as found: IMPORTANT!
            value = getParameter(parameters, name);
            parameter.setValue(value);
        }
        
        return ctx;
    }

    
    protected boolean hasTask(String taskName) {
        return provider == null ? false : provider.hasTask(taskName);
    }

    protected Task getTask(String taskName) {
        return provider == null ? null : provider.getTask(taskName);
    }

    public void printTaskList() {
        if (provider == null) {
            error(ERROR_TASK_PROVIDER_NOT_IMPLEMENTED);
            return;            
        }
        
        // get base tasks, but we can use other task list
        List<String> taskNames = provider.getTaskNames();
        if (taskNames == null || taskNames.isEmpty()) {
            println(MESSAGE_NO_TASKS);
            return;            
        }
        
        println(MESSAGE_TASK_LIST);
        for (String taskName : taskNames) {
            println(" " + taskName);
        }
    }

}
