package plazma.lib.text;

import plazma.lib.sys.SysLib;

public class PrinterHelper {
    
    public static final String EVENT_PREFIX   = "=> ";
    public static final String EVENT_ITEM_PREFIX   = "   ";
    
    ////
    
    public static void println(String message) {
        SysLib.println(message);
    }
    
//    public static void println(String message1, String message2) {
//        println(message1 + message2);
//    }
//
//    public static void println(String message1, String message2, String message3) {
//        println(message1 + message2 + message3);
//    }
//
//    public static void println(String message1, String message2, String message3, String message4) {
//        println(message1 + message2 + message3 + message4);
//    }

    public static void println(String... message) {
        SysLib.println(message);
    }

    public static void println(boolean flag, String message1, String message2) {
        if (!flag) {
            return;
        }
        println(message1, message2);
    }

    public static void println(boolean flag, String message) {
        if (!flag) {
            return;
        }
        println(message);
    }
    
    ////

    public static void printEvent(boolean flag, String message) {
        if (!flag) {
            return;
        }
        SysLib.print(EVENT_PREFIX);
        SysLib.print(message);
        SysLib.println();
    }

    public static void printEvent(boolean flag, String message1, String message2) {
        if (!flag) {
            return;
        }
        SysLib.print(EVENT_PREFIX);
        SysLib.print(message1);
        SysLib.print(message2);
        SysLib.println();
    }

    public static void printEventItem(boolean flag, String message) {
        if (!flag) {
            return;
        }
        println(EVENT_ITEM_PREFIX, message);
    }

    public static void printEventItem(boolean flag, String message1, String message2) {
        if (!flag) {
            return;
        }
        println(EVENT_ITEM_PREFIX, message1, message2);
    }

    public static void printEventItem(boolean flag, String message1, String message2, String message3, String message4) {
        if (!flag) {
            return;
        }
        println(EVENT_ITEM_PREFIX, message1, message2, message3, message4);
    }
    
    ////

    public static void info(String message) {
        SysLib.info(message);
    }

    public static void warn(String message) {
        SysLib.warn(message);
    }

    public static void error(String message) {
        SysLib.error(message);
    }
    
    public static void error(String message1, String message2) {
        SysLib.error(message1 + message2);
    }

    public static void error(String message1, String message2, String message3) {
        SysLib.error(message1 + message2 + message3);
    }
    
    ////

    public static String toSafeString(String str) {
        return str == null ? "" : str;        
    }


}
