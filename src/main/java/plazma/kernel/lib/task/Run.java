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

import static plazma.kernel.lib.sys.SysLib.*;

import java.util.Properties;

public class Run {

    public static final String MESSAGE_TASK_NOT_SETTING = "-task is not setting";
    public static final String MESSAGE_TASK_EMPTY = "Task is empty";
    
    private TaskExecutor executor;

    private TaskExecutor getTaskExecutor() {
        if (executor == null) {
            executor = new BaseTaskExecutor();
            executor.init();
        }
        return executor;
    }

    ////

    private void printVersion() {
        println("Run [java] v1.0.8\n");
    }

    private void printUsage() {
        println("Usage: run [options]");
        println("Options:");

        println(" -version");
        println(" -help");
        println(" -task <name> [-<param> <value>]");
        println(" -task-list");
    }

    private void printTaskList() {
        getTaskExecutor().printTaskList();
    }
    
    ////

    private void executeTask(Properties parameters) throws TaskException {
        String task = getParameter(parameters, Task.PARAMETER_TASK);
        if (task == null) {
            println(MESSAGE_TASK_NOT_SETTING);
            printUsage();
            return;
        }

        if (task.isEmpty()) {
            error(MESSAGE_TASK_EMPTY);
            printUsage();
            return;
        }

        getTaskExecutor().executeTask(task, parameters);
    }

    private void execute(Properties parameters) {
        try {

            // VERSION
            if (hasParameter(parameters, Task.PARAMETER_VERSION)) {
                printVersion();
                return;
            }

            // HELP
            if (hasParameter(parameters, Task.PARAMETER_HELP)) {
                printUsage();
                return;
            }

            // TASK
            if (hasParameter(parameters, Task.PARAMETER_TASK)) {
                executeTask(parameters);
                return;
            }

            // TASK LIST
            if (hasParameter(parameters, Task.PARAMETER_TASK_LIST)) {
                printTaskList();
                return;
            }

            println(MESSAGE_TASK_NOT_SETTING);
            printUsage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Run run = new Run();

        // Parse command line arguments
        Properties parameters = parseArguments(args);

        // Execute task
        run.execute(parameters);
    }


}
