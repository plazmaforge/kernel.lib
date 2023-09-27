package plazma.lib.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import plazma.lib.io.IOLib;
import plazma.lib.str.StrLib;

public class ArrayPrimitiveGenerator {

    private static String USER_DIR = System.getProperty("user.dir");
    private static String SRC_DIR = USER_DIR + "/" + "src/main/java";
    private static String GEN_DIR = USER_DIR + "/" + "gen/main/java";
    private static String PACKAGE_DIR = "plazma/lib/array";

    public static void main(String[] args) {
        process();
    }

    private static void process() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {

            String template = "ArrayPrimitiveTemplate.java";
            String templateFile = SRC_DIR + "/" + PACKAGE_DIR + "/" + template;

            System.out.println("USER_DIR=" + USER_DIR);
            System.out.println("SRC_DIR=" + SRC_DIR);
            System.out.println("templateFile=" + templateFile);

            reader = IOLib.createBufferedReader(templateFile);

            String line = reader.readLine();
            if (line == null) {
                return;
            }

            StringBuilder header = new StringBuilder();
            StringBuilder footer = new StringBuilder();

            List<Method> methods = new ArrayList<>();
            Method method = null;

            int mode = 0;
            String ts = null;
            boolean skipLine = false;
            while (line != null) {

                ts = line.trim();
                skipLine = false;

                if (isMarker(ts, "// BODY:START")) {
                    if (mode != 0) {
                        // error
                    }
                    mode = 1;
                    skipLine = true;
                }

                if (isMarker(ts, "// BODY:END")) {
                    if (mode != 1) {
                        // error
                    }
                    mode = 2;
                    skipLine = true;
                }

                if (!skipLine) {

                    // header
                    if (mode == 0) {
                        header.append(line + "\n");
                    }

                    // body
                    if (mode == 1) {

                        if (isMarker(ts, "static ")) {
                            method = new Method();
                            method.signature = StrLib.removePrefix(ts, "static ").trim();
                            method.signature = StrLib.removeSuffix(method.signature, "{").trim();

                            methods.add(method);
                        }

                        if (method != null) {
                            method.lines.add(line);
                        }

                        // body.append(line + "\n");
                    }

                    // footer
                    if (mode == 2) {
                        footer.append(line + "\n");
                    }

                }

                // read next line
                line = reader.readLine();
            }

            System.out.println("HEADER");
            System.out.println("=========================================");
            System.out.println(header.toString());

            System.out.println();
            System.out.println("BODY");
            System.out.println("=========================================");
            // System.out.println(body.toString());

            // StringBuilder buf = new StringBuilder();
            for (Method m : methods) {

                List<String> ls = m.lines;
                for (String l : ls) {
                    System.out.println(l);
                }
            }

            System.out.println();
            System.out.println("FOOTER");
            System.out.println("=========================================");
            System.out.println(footer.toString());

            String outputFile = GEN_DIR + "/" + PACKAGE_DIR + "/" + "ArrayPrimitiveHelper.java";
            writer = IOLib.createBufferedWriter(outputFile);

            String source = generate(header, footer, methods);
            writer.write(source);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOLib.close(reader);
            IOLib.close(writer);
        }
    }

    private static boolean isMarker(String str, String marker) {
        if (str == null || marker == null) {
            return false;
        }
        return str.startsWith(marker);
    }

    private static String generate(StringBuilder header, StringBuilder footer, List<Method> methods) {

        String headerStr = header.toString();
        headerStr = headerStr.replaceAll("ArrayPrimitiveTemplate", "ArrayPrimitiveHelper");

        StringBuilder buf = new StringBuilder();
        buf.append(headerStr);
        buf.append("\n");

        for (Method m : methods) {
            generateMethod(buf, m);
        }

        buf.append("\n");
        buf.append(footer);

        return buf.toString();
    }

    private static String[] PRIMITIVE_TYPES = new String[] { "boolean", "byte", "char", "short", "int", "long", "float",
            "double" };
    private static String[] OBJECT_TYPES = new String[] { "Boolean", "Byte", "Character", "Short", "Integer", "Long",
            "Float", "Double" };
    private static String[] OBJECT_TYPES2 = new String[] { "Boolean", "Byte", "Char", "Short", "Int", "Long", "Float",
            "Double" };

    private static String TEMPLATE_TYPE = "int";

    private static void generateMethod(StringBuilder buf, Method m) {

        List<String> ls = m.lines;
        if (ls == null || ls.isEmpty()) {
            // no source
            return;
        }
        String[] types = PRIMITIVE_TYPES; // new String[] {"int"};
        for (int i = 0; i < types.length; i++) {
            generateMethod(buf, m, ls, types[i], i);
        }
    }

    private static void generateMethod(StringBuilder buf, Method m, List<String> ls, String type, int typeIndex) {

        if ("boolean".equals(type)) {

            if ("void _sort(int[] array)".equals(m.signature)) {
                buf.append("    // TODO: void _sort(boolean[] array)\n\n");
                return;
            }

            if ("void _sort(int[] array, int fromIndex, int toIndex)".equals(m.signature)) {
                buf.append("    // TODO: void _sort(boolean[] array, int fromIndex, int toIndex)\n\n");
                return;
            }

        }
        String r = null;
        for (String l : ls) {

            r = l;

            if (!TEMPLATE_TYPE.equals(type)) {
                // replace all

                // int[] array, int value => <type>[] array, <type> value
                // int[] array, int def => <type>[] array, <type> def
                // int[] array => type[] array
                // int e = => type e =
                // int result =
                // int next =
                // <Integer>
                // Integer e =
                // .toPrimitiveIntArray(
                // int[] newArray =
                // static int[] _
                // int[] result = new int[size];
                // int...
                // EMPTY_INT_ARRAY

                String Type = OBJECT_TYPES[typeIndex];
                String Type2 = OBJECT_TYPES2[typeIndex];
                String TYPE2 = Type2.toUpperCase();

                r = replaceAll(r, "static int[] _toPrimitiveIntArray(",
                        "static " + type + "[] _toPrimitive" + OBJECT_TYPES2[typeIndex] + "Array(");

                r = replaceAll(r, "static int _find", "static " + type + " _find"); // *** NEW

                r = replaceAll(r, "int[] array, int oldValue, int newValue",
                        type + "[] array, " + type + " oldValue, " + type + " newValue");
                r = replaceAll(r, "int[] array, int[] oldValues, int[] newValues",
                        type + "[] array, " + type + "[] oldValues, " + type + "[] newValues");

                r = replaceAll(r, "int oldValue;", type + " oldValue;");
                r = replaceAll(r, "int newValue;", type + " newValue;");

                r = replaceAll(r, "int[] array, int value", type + "[] array, " + type + " value");
                r = replaceAll(r, "int[] array, int def", type + "[] array, " + type + " def");
                r = replaceAll(r, "int[] array, int newLength, int element",
                        type + "[] array, int newLength, " + type + " element");

                r = replaceAll(r, "int[] array = new int[size];", type + "[] array = new " + type + "[size];");
                r = replaceAll(r, "int[] array", type + "[] array");

                r = replaceAll(r, "Integer[] array", Type + "[] array");

                r = replaceAll(r, "int[] original", type + "[] original");
                r = replaceAll(r, "int[] x, int[] y", type + "[] x, " + type + "[] y");

                r = replaceAll(r, "int[] r = ", type + "[] r = ");

                r = replaceAll(r, "int e = ", type + " e = ");
                r = replaceAll(r, "int e;", type + " e;");

                r = replaceAll(r, "int result = ", type + " result = ");
                r = replaceAll(r, "int next = ", type + " next = ");
                r = replaceAll(r, "<Integer>", "<" + OBJECT_TYPES[typeIndex] + ">");
                r = replaceAll(r, "Integer e = ", OBJECT_TYPES[typeIndex] + " e = ");

                r = replaceAll(r, ".toPrimitiveIntArray(", ".toPrimitive" + OBJECT_TYPES2[typeIndex] + "Array(");
                r = replaceAll(r, "_toPrimitiveIntArray(", "_toPrimitive" + OBJECT_TYPES2[typeIndex] + "Array(");

                r = replaceAll(r, "int[] newArray =", type + "[] newArray =");

                r = replaceAll(r, "static int[] _", "static " + type + "[] _");
                r = replaceAll(r, "static Integer[] _", "static " + Type + "[] _"); // ***

                r = replaceAll(r, "static int _min(", "static " + type + " _min(");
                r = replaceAll(r, "static int _max(", "static " + type + " _max(");

                r = replaceAll(r, "int[] result = new int[size];", type + "[] result = new " + type + "[size];");
                r = replaceAll(r, "Integer[] result = new Integer[size];",
                        Type + "[] result = new " + Type + "[size];"); // ***
                r = replaceAll(r, "int[] result = ", type + "[] result = "); // !!!

                r = replaceAll(r, "int...", type + "...");
                r = replaceAll(r, "EMPTY_INT_ARRAY", "EMPTY_" + OBJECT_TYPES2[typeIndex].toUpperCase() + "_ARRAY");
                r = replaceAll(r, "DEFAULT_INT", "DEFAULT_" + TYPE2);

                if ("boolean".equals(type)) {

                    if ("int _min(int[] array, int def, boolean useDef)".equals(m.signature)) {

                        // (next < result) => (!next)
                        r = replaceAll(r, "(next < result)", "(!next)");
                    }

                    if ("int _max(int[] array, int def, boolean useDef)".equals(m.signature)) {

                        // (next > result) => (next)
                        r = replaceAll(r, "(next > result)", "(next)");
                    }

                }
            }

            buf.append(r + "\n");
            // System.out.println(l);
        }
    }

    static class Method {
        String name;
        String signature;
        List<String> lines = new ArrayList<>();
    }

    private static String replaceAll(String str, String s1, String s2) {
        if (str == null) {
            return null;
        }
        if (s1 == null || s2 == null) {
            return str;
        }
        StringBuffer sbuf = new StringBuffer();
        int pos = 0;
        int index = str.indexOf(s1);
        int patLen = s1.length();
        while (index >= 0) {
            sbuf.append(str.substring(pos, index));
            sbuf.append(s2);
            pos = index + patLen;
            index = str.indexOf(s1, pos);
        }
        sbuf.append(str.substring(pos));
        return sbuf.toString();
    }

}
