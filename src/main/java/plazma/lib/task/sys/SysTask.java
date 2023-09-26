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

package plazma.lib.task.sys;

import static plazma.lib.sys.SysLib.*;

import plazma.lib.fmt.FmtLib;
import plazma.lib.math.MathLib;
import plazma.lib.task.BaseTask;
import plazma.lib.task.TaskContext;

public class SysTask extends BaseTask {

    // tasks

    public static final String TASK_PRINT_SYSTEM_INFO = "print-system-info";
    public static final String TASK_PRINT_DATE = "print-date";
    public static final String TASK_PRINT_TIME = "print-time";
    public static final String TASK_PRINT_DATE_TIME = "print-date-time";

    public static final String TASK_PRINT_FACT = "print-fact";
    public static final String TASK_PRINT_FIB = "print-fib";


    // parameters

    private static final String PARAMETER_FORMAT = "format";
    private static final String PARAMETER_VALUE = "value";
    
    public SysTask() {
        super();
        setDysplayProcessing(false);
        setDysplayTiming(false);
    }

    public void execute(TaskContext ctx) throws Exception {

        String task = ctx.getTaskName();

        if (TASK_PRINT_SYSTEM_INFO.equals(task)) {
            executePrintSystemInfo(ctx);
        } else if (TASK_PRINT_DATE.equals(task)) {
            executePrintDate(ctx);
        } else if (TASK_PRINT_TIME.equals(task)) {
            executePrintTime(ctx);
        } else if (TASK_PRINT_DATE_TIME.equals(task)) {
            executePrintDateTime(ctx);

        } else if (TASK_PRINT_FACT.equals(task)) {
            executePrintFact(ctx);
        } else if (TASK_PRINT_FIB.equals(task)) {
            executePrintFib(ctx);            
        }

    }
    
    public void initParameters() {

        // print-date/time/date-time
        getParameters().addParameter(PARAMETER_FORMAT);

        // print-fact/fib
        getParameters().addParameter(PARAMETER_VALUE);
    }


    ////
    
    private String out(String str) {
       return str == null ? "" : str;        
    }

    protected void executePrintSystemInfo(TaskContext ctx) {
        println("System Info");

        println(" os.name        : " + out(getOsName()));
        println(" os.version     : " + out(getOsVersion()));
        println(" os.arch        : " + out(getOsArch()));
        println(" os.arch.data   : " + out(getOsArchData()));

        println(" user.name      : " + out(getUserName()));
        println(" user.home      : " + out(getUserHome()));
        println(" user.dir       : " + out(getUserDir()));        
        println(" tmp.dir        : " + out(getTmpDir()));

        println(" user.locale    : " + out(getUserLocale()));
        println(" user.country   : " + out(getUserCountry()));
        println(" user.language  : " + out(getUserLanguage()));
        println(" user.script    : " + out(getUserScript()));
        println(" user.variant   : " + out(getUserVariant()));
        
        println(" file.encoding  : " + out(getFileEncoding()));
        println(" file.separator : " + out(getFileSeparator()));        

    }

    protected void executePrintDate(TaskContext ctx) {
        println(formatCurrentDate(ctx));
    }

    protected void executePrintTime(TaskContext ctx) {
        println(formatCurrentTime(ctx));
    }

    protected void executePrintDateTime(TaskContext ctx) {
        println(formatCurrentDateTime(ctx));
    }

    //

    protected void executePrintFact(TaskContext ctx) {
        int n = ctx.getIntParameter(PARAMETER_VALUE);

        // Call factorial
        long result = MathLib.fact(n);
        println(result);
    }

    protected void executePrintFib(TaskContext ctx) {

        int n = ctx.getIntParameter(PARAMETER_VALUE);

        // Call fibonacci sequence (0 1 1 2 3 5 ...)
        long[] seq = MathLib.fibseq(n);

        int size = seq.length;
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                print(" ");
            }
            print(seq[i]);
        }
        println();
    }

    ////

    protected String getFormat(TaskContext ctx) {
        return ctx.getStringParameter(PARAMETER_FORMAT);
    }

    protected String getValue(TaskContext ctx) {
        return ctx.getStringParameter(PARAMETER_VALUE);
    }

    ////

    protected String formatCurrentDate(TaskContext ctx) {
        String format = getFormat(ctx);
        return FmtLib.formatDate(format);
    }

    protected String formatCurrentTime(TaskContext ctx) {
        String format = getFormat(ctx);
        return FmtLib.formatTime(format);
    }

    protected String formatCurrentDateTime(TaskContext ctx) {
        String format = getFormat(ctx);
        return FmtLib.formatDateTime(format);
    }

}
