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

import plazma.lib.task.BaseTask;
import plazma.lib.task.TaskContext;
import plazma.lib.time.CalendarLib;
import plazma.lib.time.DateLib;

public class CalendarTask extends BaseTask {

    // tasks

    public static final String TASK_PRINT_CALENDAR = "print-calendar";
    public static final String TASK_PRINT_YEAR_CALENDAR = "print-year-calendar";
    public static final String TASK_PRINT_MONTH_CALENDAR = "print-month-calendar";

    // parameters

    private static final String PARAMETER_TYPE = "type";
    private static final String PARAMETER_YEAR = "year";
    private static final String PARAMETER_MONTH = "month";

    // Calendar:
    // https://rosettacode.org/wiki/Calendar#Java
    
    public CalendarTask() {
        super();
        setDysplayProcessing(false);
        setDysplayTiming(false);
    }
    
    public void execute(TaskContext ctx) throws Exception {

        String task = ctx.getTaskName();

        if (TASK_PRINT_CALENDAR.equals(task)) {
            executePrintCalendar(ctx);
        } else if (TASK_PRINT_YEAR_CALENDAR.equals(task)) {
            executePrintYearCalendar(ctx);
        } else if (TASK_PRINT_MONTH_CALENDAR.equals(task)) {
            executePrintMonthCalendar(ctx);
        }

    }
    
    public void initParameters() {
        getParameters().addParameter(PARAMETER_TYPE);
        getParameters().addParameter(PARAMETER_YEAR);
        getParameters().addParameter(PARAMETER_MONTH);
    }

    ////

    void executePrintCalendar(TaskContext ctx) {
        String type = "";
        if (ctx.hasParameter(PARAMETER_TYPE)) {
            type = ctx.getStringParameter(PARAMETER_TYPE);
        }

        if ("month".equals(type)) {
            executePrintMonthCalendar(ctx);
        } else {
            executePrintYearCalendar(ctx);
        }
    }

    void executePrintYearCalendar(TaskContext ctx) {
        int year = getYear(ctx);

        println("Year: " + year);
        CalendarLib.printYearCalendar(year, false, false);
    }

    void executePrintMonthCalendar(TaskContext ctx) {
        int year = getYear(ctx);
        int month = getMonth(ctx);

        println("Year: " + year + ", Month: " + month);
        CalendarLib.printMonthCalendar(year, month, false, false);
    }

    ////

    protected int getYear(TaskContext ctx) {
        int year = ctx.getIntParameter(PARAMETER_YEAR);
        if (year == 0) {
            // if 'year' is not setting then use current year
            year = DateLib.getCurrentYear();
        }
        return year;
    }

    protected int getMonth(TaskContext ctx) {
        int month = ctx.getIntParameter(PARAMETER_MONTH);
        if (month == 0) {
            // if 'month' is not setting then use current month
            month = DateLib.getCurrentMonth();
        }
        return month;
    }


}
