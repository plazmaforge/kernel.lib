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

import static plazma.lib.sys.SysLib.*;

import plazma.lib.fmt.FmtLib;
import plazma.lib.fs.FSLib;
import plazma.lib.str.StrLib;
import plazma.lib.sys.Parameter;
import plazma.lib.sys.Parameters;

public class TaskHelper {

    public static final String MESSAGE_START_TASK = "Start task: '%s'";
    public static final String MESSAGE_PROCESSING = "Processing...";
    public static final String MESSAGE_TOTAL_TIME = "Total time: %s";

    public static final String MESSAGE_NOT_SETTING = "<not setting>";
    public static final String MESSAGE_BY_DEFAULT = "by default";
    
    
    public static boolean isEmpty(String str) {
        return StrLib.isEmpty(str);
    }

    public static boolean hasValue(String str) {
        return !isEmpty(str);
    }

    /**
     * Parse argument array and return parameter map
     * 
     * @param args
     * @return
     */

    ////
    
    public static void printParameter(Parameter parameter, int len) {
        if (parameter == null) {
            return;            
        }
        String name = parameter.getName();
        String value = parameter.getValue();
        String dataType = parameter.getDataType();
        boolean setting = parameter.hasValue();
        
        String displayName = formatName(name, len);
        String displayValue = formatValue(value, setting);
        
        //if ("Date".equalsIgnoreCase(dataType)) {
        //    displayValue = format
        //}        

        println(" " + displayName + ": " + displayValue);
    }

    
    // string
    public static void printParameter(String name, String value, int len, boolean setting) {
        println(" " + formatName(name, len) + ": " + formatValue(value, setting));
    }

    /*
    public static void printParameter(String name, String value) {
        printParameter(name, value, 0, true);
    }

    public static void printParameter(String name, String value, boolean setting) {
        printParameter(name, value, 0, setting);
    }

    // string
    public static void printParameter(String name, String value, int len) {
        printParameter(name, value, len, true);
    }

    // int
    public static void printParameter(String name, int value, int len, boolean setting) {
        printParameter(name, toString(value), len, setting);
    }

    // int
    public static void printParameter(String name, int value, int len) {
        printParameter(name, value, len, true);
    }

    // long
    public static void printParameter(String name, long value, int len, boolean setting) {
        printParameter(name, toString(value), len, setting);
    }

    // long
    public static void printParameter(String name, long value, int len) {
        printParameter(name, value, len, true);
    }

    // float
    public static void printParameter(String name, float value, int len, boolean setting) {
        printParameter(name, toString(value), len, setting);
    }

    // float
    public static void printParameter(String name, float value, int len) {
        printParameter(name, value, len, true);
    }

    // double
    public static void printParameter(String name, double value, int len, boolean setting) {
        printParameter(name, toString(value), len, setting);
    }

    // double
    public static void printParameter(String name, double value, int len) {
        printParameter(name, value, len, true);
    }

    // boolean
    public static void printParameter(String name, boolean value, int len, boolean setting) {
        printParameter(name, toString(value), len, setting);
    }

    // boolean
    public static void printParameter(String name, boolean value, int len) {
        printParameter(name, value, len, true);
    }
    */

    ////

    // int
    public static String toString(int value) {
        return "" + value;
    }

    // long
    public static String toString(long value) {
        return "" + value;
    }

    // float
    public static String toString(float value) {
        return "" + value;
    }

    // double
    public static String toString(double value) {
        return "" + value;
    }

    // boolean
    public static String toString(boolean value) {
        return (value ? "true" : "false");
    }

    ////

    public static void printStartTask(String task) {
        println(format(MESSAGE_START_TASK, task));
    }

    public static void printProcessing() {
        println(MESSAGE_PROCESSING);
    }

    public static void printTotalTime(long time) {
        println(format(MESSAGE_TOTAL_TIME, formatTime(time)));
    }

    // format

    public static final String format(String message, Object... args) {
        return FmtLib.format(message, args);
    }

    public static String formatName(String name, int len) {
        // -<name>......
        return "-" + (len < 1 ? name : StrLib.rpad(name, len, "."));
    }

    public static String formatValue(String value, boolean setting) {
        // <value>
        // not setting
        return (setting ? value : MESSAGE_NOT_SETTING);
    }

    // string
    public static String formatByDefault(String value, boolean def) {
        // <value>
        // <value> by default
        return "" + value + (def ? (" " + MESSAGE_BY_DEFAULT) : "");
    }

    // int
    public static String formatByDefault(int value, boolean def) {
        return formatByDefault(toString(value), def);
    }

    // long
    public static String formatByDefault(long value, boolean def) {
        return formatByDefault(toString(value), def);
    }

    // float
    public static String formatByDefault(float value, boolean def) {
        return formatByDefault(toString(value), def);
    }

    // double
    public static String formatByDefault(double value, boolean def) {
        return formatByDefault(toString(value), def);
    }

    // ms, s
    public static String formatTime(long time) {
        if (time > 1000) {
            return formatTime(time / 1000.0f, "s");
        }
        return formatTime(time, "ms");
    }

    public static String formatTime(long time, String unit) {
        return "" + time + " " + unit;
    }

    public static String formatTime(float time, String unit) {
        return "" + time + " " + unit;
    }

    ////

    public static int maxParameterLen(String[] array) {
        return StrLib.maxLen(array) + 2; // '<name>..'
    }
    
    public static int maxParameterLen(Parameters parameters) {

        if (parameters == null) {
            return 0;
        }

        int result = 0;
        int len = 0;
        int count = parameters.getParameterCount();

        Parameter parameter = null;
        String name;

        for (int i = 0; i < count; i++) {
            parameter = parameters.getParameter(i);
            name = parameter.getName();
            len = name.length();
            if (len > result) {
                result = len;
            }
        }

        return result + 2; // '<name>..'
    }
  

    ////

    public static String getOptionalPath(String dir, String filePrefix) {
        return FSLib.getOptionalPath(dir, filePrefix);
    }

    public static String generateFileName(String dir, String filePrefix, int number, String fileExt) {
        return FSLib.generateFileName(dir, filePrefix, number, fileExt);
    }

    public static String generateFileName(String filePrefix, int number, String fileExt) {
        return FSLib.generateFileName(filePrefix, number, fileExt);
    }
    
    //////////////////////////////////////////////////////////////////////////////////
    
    /*
    
    //// print

    protected void printHeader(String header) {
        println();
        if (header != null) {
            printLine();
            println(header);
        }
        printLine();
    }

    protected void printLine() {
        println("=============================================================================");
    }

    ////

    protected String format(Object obj, int len) {
        String str = obj == null ? "" : obj.toString();
        return StrLib.formatString(str, len);
    }
    */


}
