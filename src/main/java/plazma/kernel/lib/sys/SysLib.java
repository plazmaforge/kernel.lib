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

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import plazma.kernel.lib.fmt.FmtLib;
import plazma.kernel.lib.num.NumLib;
import plazma.kernel.lib.str.StrLib;

public class SysLib {
    
    public static final int COLOR_BLACK      = 0;
    public static final int COLOR_DARK_BLUE  = 1;
    public static final int COLOR_DARK_GREEN = 2;
    public static final int COLOR_LIGHT_BLUE = 3;
    public static final int COLOR_DARK_RED   = 4;
    public static final int COLOR_MAGENTA    = 5;
    public static final int COLOR_ORANGE     = 6;
    public static final int COLOR_LIGHT_GRAY = 7;
    public static final int COLOR_GRAY       = 8;
    public static final int COLOR_BLUE       = 9;
    public static final int COLOR_GREEN      = 10;
    public static final int COLOR_CYAN       = 11;
    public static final int COLOR_RED        = 12;
    public static final int COLOR_PINK       = 13;
    public static final int COLOR_YELLOW     = 14;
    public static final int COLOR_WHITE      = 15;

    public static final int COLOR_INFO = COLOR_GREEN;
    public static final int COLOR_WARN = COLOR_YELLOW;
    public static final int COLOR_ERROR = COLOR_RED;


    public static final String MESSAGE_INFO = "[INFO] %s";
    public static final String MESSAGE_WARN = "[WARNING] %s";
    public static final String MESSAGE_ERROR = "[ERROR] %s";

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    //
    //
    // 1.1
    // - loadProperties(args)
    // - transferProperties(properties, map)
    //
    // 1.2
    // - toArgumentsMap(args)
    // - transferArguments(args, map)

    public static final String OS_NAME = System.getProperty("os.name");

    public static final String OS_VERSION = System.getProperty("os.version");

    public static final String OS_ARCH = System.getProperty("os.arch");

    public static final String OS_ARCH_DATA = System.getProperty("sun.arch.data.model");

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static final String FILE_ENCODING = System.getProperty("file.encoding");
    
    
    private static boolean colorizedConsole;

    private SysLib() {
    }

    //// 1.1

    /*
     * public static Properties loadProperties(String[] args) { Properties
     * properties = new Properties(); if (args == null || args.length == 0){ return
     * properties; } transferArguments(args, properties); return properties; }
     */

    public static void transferProperties(Properties properties, Map<String, String> map) {
        if (properties == null || map == null) {
            return;
        }
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet) {
            if (key == null) {
                continue;
            }
            map.put(key.toString(), properties.getProperty(key.toString()));
        }
    }

    //// 1.2

    /**
     * Converts command line arguments to Map<String, String>
     * 
     * @param args
     * @return
     */
    public static Map<String, String> toArgumentsMap(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        transferArguments(args, map);
        return map;
    }

    /**
     * Transfers command line arguments to Map
     * 
     * @param args
     * @param map
     */
    public static void transferArguments(String[] args, Map map) {
        if (args == null || args.length == 0) {
            return;
        }
        String p = null;
        String v = null;
        for (int i = 0; i < args.length; i++) {
            p = normalizeArg(args[i]);
            if (p == null) {
                continue;
            }
            if (p.startsWith("-") && p.length() > 1) {
                p = p.substring(1);
                if ((i + 1) < args.length) {
                    v = normalizeArg(args[i + 1]);
                    if (v != null && v.startsWith("-")) {
                        v = "true";
                    } else {
                        i++;
                    }
                } else {
                    v = "true";
                }
                if (v == null) {
                    continue;
                }
                map.put(p, v);

            }
        }
    }

    /**
     * Parse argument array and return parameter map
     * 
     * @param args
     * @return
     */
    public static Properties parseArguments2(String[] args) {
        Properties parameters = new Properties();
        if (args == null || args.length == 0) {
            return parameters;
        }

        String arg = "";
        String key = "";
        String value = "";
        int i = 0;
        int j = 0;
        boolean clearPrefix = true;

        // parse argument array
        // format: -parameter1 value1 -parameter2 value2 ... -parameter<N> value<N>
        // 'value' without 'parameter' will skip
        while (i < args.length) {
            arg = args[i];
            if (startsWith(arg, Options.DEFAULT_ARGUMENT_PREFIX) && arg.length() > 1) {
                key = arg;
                if (clearPrefix) {
                    key = key.substring(1);
                }
                value = "";
                j = i + 1;
                if (j < args.length && !startsWith(args[j], Options.DEFAULT_ARGUMENT_PREFIX)) {
                    value = args[j];
                    i = j;
                }

                // insert or update
                parameters.put(key, value);
            }
            i += 1;
        }
        return parameters;
    }

    public static Properties parseArguments(String[] args) {
        CommandLine cmd = parseCommandLine(args);
        return cmd.toParameters();
    }

    public static CommandLine parseCommandLine(String[] args) {
        return parseCommandLine(null, args);
    }

    public static CommandLine parseCommandLine(Options options, String[] args) {
        CommandLineParser parser = new CommandLineParser();
        CommandLine cmd = parser.parse(options, args);
        return cmd;
    }

    ////

    private static boolean startsWith(String str, String prefix) {
        return StrLib.startsWith(str, prefix);
    }

    private static String normalizeArg(String str) {
        return str; // ?
    }

    ////

    public static boolean hasParameter(Properties parameters, String name) {
        if (parameters == null || name == null) {
            return false;
        }
        return parameters.containsKey(name);
    }

    public static String getParameter(Properties parameters, String name) {
        if (parameters == null || name == null) {
            return null;
        }
        return parameters.getProperty(name);
    }

    // int

    public static int getIntParameter(Properties parameters, String name) {
        return NumLib.toInt(getParameter(parameters, name));
    }

    public static int getIntParameter(Properties parameters, String name, int def) {
        return NumLib.toInt(getParameter(parameters, name), def);
    }

    // long

    public static long getLongParameter(Properties parameters, String name) {
        return NumLib.toLong(getParameter(parameters, name));
    }

    public static long getLongParameter(Properties parameters, String name, long def) {
        return NumLib.toLong(getParameter(parameters, name), def);
    }

    // float

    public static float getFloatParameter(Properties parameters, String name) {
        return NumLib.toFloat(getParameter(parameters, name));
    }

    public static float getFloatParameter(Properties parameters, String name, float def) {
        return NumLib.toFloat(getParameter(parameters, name), def);
    }

    // double

    public static double getDoubleParameter(Properties parameters, String name) {
        return NumLib.toDouble(getParameter(parameters, name));
    }

    public static double getDoubleParameter(Properties parameters, String name, double def) {
        return NumLib.toDouble(getParameter(parameters, name), def);
    }

    ////

    // os.name
    public static String getOsName() {
        return OS_NAME;
    }

    // os.version
    public static String getOsVersion() {
        return OS_VERSION;
    }

    // os.arch
    public static String getOsArch() {
        return OS_ARCH;
    }

    // os.arch.data
    public static String getOsArchData() {
        return OS_ARCH_DATA;
    }

    // file.separator
    public static String getFileSeparator() {
        return FILE_SEPARATOR;
    }

    // file.encoding
    public static String getFileEncoding() {
        return FILE_ENCODING;
    }

    // user.name
    public static String getUserName() {
        return System.getProperty("user.name");
    }

    // user.home
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    // user.dir
    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String getTmpDir() {
        return System.getProperty("java.io.tmpdir");
    }

    // user.locale
    public static String getUserLocale() {
        Locale locale = Locale.getDefault();
        return locale.toString();
    }

    // user.country
    public static String getUserCountry() {
        return System.getProperty("user.country");
    }

    // user.language
    public static String getUserLanguage() {
        return System.getProperty("user.language");
    }

    public static String getUserScript() {
        Locale locale = Locale.getDefault();
        return locale.getScript();
    }

    public static String getUserVariant() {
        Locale locale = Locale.getDefault();
        return locale.getVariant();
    }

    // time

    // Unix time (1970-01-01 00:00:00) in milliseconds
    public static long getTimeInMilliseconds() {
        return System.currentTimeMillis();
    }

    // Unix time (1970-01-01 00:00:00) in seconds
    public static long getTimeInSeconds() {
        return getTimeInMilliseconds() / 1000;
    }

    public static long getTimeInNanoseconds() {
        return System.nanoTime();
    }

    //

    public static long startTime() {
        return getTimeInMilliseconds();
    }

    public static long stopTime(long time) {
        return getTimeInMilliseconds() - time;
    }

    /////////////////////////////////////////////////////////////////////

    private static final String format(String message, Object... args) {
        return FmtLib.format(message, args);
    }

    // log

    public static void info(String message) {
        println(format(MESSAGE_INFO, message));
    }

    public static void warn(String message) {
        println(format(MESSAGE_WARN, message));
    }

    public static void error(String message) {
        println(format(MESSAGE_ERROR, message));
    }

    // print

    public static void print(Object obj) {
        System.out.print(obj);
    }

    public static void println() {
        System.out.println();
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static void println(String... str) {
        if (str == null) {
            System.out.println((String) null);
            return;
        }
        int len = str.length;
        if (len == 0) {
            return;            
        }
        if (len == 1) {
            System.out.println(str[0]);
            return;            
        }
        if (len == 2) {
            System.out.println(str[0] + str[1]);
            return;            
        }

        if (len == 3) {
            System.out.println(str[0] + str[1] + str[2]);
            return;            
        }
        
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < len; i++) {
            buf.append(str[i]);            
        }

        System.out.println(buf.toString());
    }
    
    /////////////////////////////////////////////////////
    
    // console
    public static boolean isColorizedConsole() {
        return colorizedConsole;
    }

    public static void setColorizedConsole(boolean flag) {
        colorizedConsole = flag;
        
    }

    //

    public static boolean isColorizedStdOut() {
        return isColorizedConsole() && isStdOutEnabled();
        
    }

    public static boolean isColorizedStdErr() {
        return isColorizedConsole() && isStdErrEnabled();
        
    }

    //

    public static boolean isStdOutEnabled() {
        return isTerminal();        
    }

    public static boolean isStdErrEnabled() {
        return isTerminal();                
    }
    
    // java isatty
    // https://stackoverflow.com/questions/1403772/how-can-i-check-if-a-java-programs-input-output-streams-are-connected-to-a-term
    
    private static boolean isTerminal() {
        return System.console() != null;
    }

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    
    /////////////////////////////////////////////////////////////////////////////////////
    
    private static String getForegroundCode(int foreground) { // Linux only
        switch(foreground) {
        case  0: return "30"; // COLOR_BLACK      0
        case  1: return "34"; // COLOR_DARK_BLUE  1
        case  2: return "32"; // COLOR_DARK_GREEN 2
        case  3: return "36"; // COLOR_LIGHT_BLUE 3
        case  4: return "31"; // COLOR_DARK_RED   4
        case  5: return "35"; // COLOR_MAGENTA    5
        case  6: return "33"; // COLOR_ORANGE     6
        case  7: return "37"; // COLOR_LIGHT_GRAY 7
        case  8: return "90"; // COLOR_GRAY       8
        case  9: return "94"; // COLOR_BLUE       9
        case 10: return "92"; // COLOR_GREEN     10
        case 11: return "96"; // COLOR_CYAN      11
        case 12: return "91"; // COLOR_RED       12
        case 13: return "95"; // COLOR_PINK      13
        case 14: return "93"; // COLOR_YELLOW    14
        case 15: return "97"; // COLOR_WHITE     15
        default: return "37";
        }
    }

    private static String getBackgroundCode(int background) { // Linux only
        switch(background) {
        case  0: return  "40"; // COLOR_BLACK      0
        case  1: return  "44"; // COLOR_DARK_BLUE  1
        case  2: return  "42"; // COLOR_DARK_GREEN 2
        case  3: return  "46"; // COLOR_LIGHT_BLUE 3
        case  4: return  "41"; // COLOR_DARK_RED   4
        case  5: return  "45"; // COLOR_MAGENTA    5
        case  6: return  "43"; // COLOR_ORANGE     6
        case  7: return  "47"; // COLOR_LIGHT_GRAY 7
        case  8: return "100"; // COLOR_GRAY       8
        case  9: return "104"; // COLOR_BLUE       9
        case 10: return "102"; // COLOR_GREEN     10
        case 11: return "106"; // COLOR_CYAN      11
        case 12: return "101"; // COLOR_RED       12
        case 13: return "105"; // COLOR_PINK      13
        case 14: return "103"; // COLOR_YELLOW    14
        case 15: return "107"; // COLOR_WHITE     15
        default: return  "40";
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    

    public static String getPrintColor(int foreground) { // Linux only
        return "\033[" + getForegroundCode(foreground) + "m";        
    }

    public static String getPrintColor(int foreground, int background) { // Linux only
        return "\033[" + getForegroundCode(foreground) + ";" + getBackgroundCode(background) + "m";
    }

    public static String getPrintResetColor() { // Linux only
        return "\033[0m"; // reset color
    }

    ////
    
    public static boolean isSupportEscapeCode() {
        return true; // always true?
    }

    public static void setConsoleColor(int foreground) {
        setStdOutColor(foreground);        
    }

    public static void setConsoleColor(int foreground, int background) {
        setStdOutColor(foreground, background);        
    }

    public static void resetConsoleColor() {
        resetStdOutColor();
    }

    ////

    public static void setStdOutColor(int foreground) {
        setColor(System.out, foreground);        
    }

    public static void setStdOutColor(int foreground, int background) {
        setColor(System.out, foreground, background);        
    }

    public static void resetStdOutColor() {
        resetColor(System.out);
    }

    ////

    public static void setStdErrColor(int foreground) {
        setColor(System.err, foreground);        
    }

    public static void setStdErrColor(int foreground, int background) {
        setColor(System.err, foreground, background);        
    }

    public static void resetStdErrColor() {
        resetColor(System.err);
    }

    ////
    
    public static void setColor(PrintStream os, int foreground) {
        os.print(getPrintColor(foreground));
        
    }

    public static void setColor(PrintStream os, int foreground, int background) {
        os.print(getPrintColor(foreground, background));
        
    }

    public static void resetColor(PrintStream os) {
        os.print(getPrintResetColor());        
    }
    
    //////////////////////////////////////////////////////////////////////////////////////

    public static void print(String str, int foreground) {
        setConsoleColor(foreground);
        print(str);
        resetConsoleColor();
    }

}
