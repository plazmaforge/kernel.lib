package plazma.lib.ext;

public class NumberPart {

    public static final int NULL_VAL = 1;
    public static final int EMPTY_VAL = 2;
    public static final int BLANK_VAL = 3;
    public static final int NAN_VAL = 4;

    private int errorCode;

    private long value;
    private long decimal;
    private long exp;

    private double value2;

    public String toErrorCode() {
        if (errorCode == NULL_VAL) {
            return "NULL_VAL";
        } else if (errorCode == EMPTY_VAL) {
            return "EMPTY_VAL";
        } else if (errorCode == BLANK_VAL) {
            return "BLANK_VAL";
        } else if (errorCode == NAN_VAL) {
            return "NAN_VAL";
        }
        return "";
    }

    public String toString() {
        return "errorCode=" + toErrorCode() + ", value=" + value + " , value2=" + value2;
    }

    public static NumberPart parseNumberPart(String str) {
        NumberPart result = new NumberPart();
        if (str == null) {
            result.errorCode = NULL_VAL;
            return result;
        }
        if (str.isEmpty()) {
            result.errorCode = EMPTY_VAL;
            return result;
        }

        char[] array = str.toCharArray();
        int size = array.length;

        /*
         * 
         * // size: 0 if (size == 0) { result.errorCode = EMPTY_VAL; return result; }
         * 
         * // size: 1 if (size == 1) { char first = array[0]; if (first == ' ') {
         * result.errorCode = EMPTY_VAL; return result; } if (!isDigit(first)) {
         * result.errorCode = NAN_VAL; return result; } result.value = toNumber(first);
         * return result; }
         * 
         * // size: 3 if (size == 2) {
         * 
         * // Na, NA, nA if (isNA(array)) { result.errorCode = NAN_VAL; // or NULL_VALUE
         * return result; }
         * 
         * }
         * 
         * // size: 3 if (size == 3) {
         * 
         * // NaN, NAN, nan... if (isNaN(array)) { result.errorCode = NAN_VAL; return
         * result; }
         * 
         * // N/A, n/a, N/a, n/A if (isN_A(array)) { result.errorCode = NAN_VAL; // or
         * NULL_VAL return result; } }
         * 
         * // size: 4, null, NULL, Null... if (size == 4) { if (isNull(array)) {
         * result.errorCode = NULL_VAL; // or NAN_VAL return result; } }
         */

        if (parseInternal(result, array, 0, size)) {
            return result;
        }

        int startIndex = 0;
        int endIndex = size - 1; // include
        boolean trim = false;
        for (int index = 0; index < size; index++) {
            char ch = array[index];
            if (ch == ' ') {
                startIndex = index;
                trim = true;
            } else {
                break;
            }
        }

        if (startIndex == size - 1) {
            result.errorCode = BLANK_VAL; // or EMPTY_VAL
            return result;
        }

        for (int index = size - 1; index >= 0; index--) {
            char ch = array[index];
            if (ch == ' ') {
                endIndex = index;
                trim = true;
            } else {
                break;
            }
        }

        if (trim) {
            int realSize = endIndex - startIndex + 1;
            if (parseInternal(result, array, startIndex, realSize)) {
                return result;
            }
        }

        boolean plus = false;
        boolean minus = false;
        boolean dot = false;

        boolean plusExp = false;
        boolean minusExp = false;

        int count1 = 0;
        int count2 = 0;

        double k = 0.1; // Coefficient for decimal

        long v1 = 0;
        double v2 = 0;
        long vv = 0;

        for (int index = startIndex; index <= endIndex; index++) {
            char ch = array[index];

            if (ch == '+') {
                // +
                if (plus) {
                    result.errorCode = NAN_VAL;
                    return result;
                } else {
                    plus = true;
                }
            } else if (ch == '-') {
                // -
                if (minus) {
                    result.errorCode = NAN_VAL;
                    return result;
                } else {
                    minus = true;
                }
            } else if (ch == '.') {
                // .
                if (dot) {
                    result.errorCode = NAN_VAL;
                    return result;
                } else {
                    dot = true;
                }
            } else if (isDigit(ch)) {
                if (dot) {
                    if (count2 > 0) {
                        k *= 0.1;
                    }
                    vv = toNumber(ch);
                    v2 += (vv * k);
                    count2++;

                } else {
                    vv = toNumber(ch);
                    if (count1 > 0) {
                        v1 *= 10;
                    }
                    v1 += vv;
                    count1++;

                }
            } else {
                result.errorCode = NAN_VAL;
                return result;
            }

            result.value = v1;
            result.value2 = v1 + v2;
        }

        // TODO
        return result;
    }

    private static boolean parseInternal(NumberPart result, char[] array, int startIndex, int size) {
        // int size = endIndex - startIndex + 1;

        // size: 0
        if (size == 0) {
            result.errorCode = EMPTY_VAL;
            return true;
        }

        // size: 1
        if (size == 1) {
            char first = array[startIndex];
            if (first == ' ') {
                result.errorCode = BLANK_VAL; // or BLANK_VAL
                return true;
            }
            if (!isDigit(first)) {
                result.errorCode = NAN_VAL;
                return true;
            }
            result.value = toNumber(first);
            return true;
        }

        // size: 3
        if (size == 2) {

            // Na, NA, nA
            if (isNA(array, startIndex)) {
                result.errorCode = NAN_VAL; // or NULL_VALUE
                return true;
            }

        }

        // size: 3
        if (size == 3) {

            // NaN, NAN, nan...
            if (isNaN(array, startIndex)) {
                result.errorCode = NAN_VAL;
                return true;
            }

            // N/A, n/a, N/a, n/A
            if (isN_A(array, startIndex)) {
                result.errorCode = NAN_VAL; // or NULL_VAL
                return true;
            }
        }

        // size: 4, null, NULL, Null...
        if (size == 4) {
            if (isNull(array, startIndex)) {
                result.errorCode = NULL_VAL; // or NAN_VAL
                return true;
            }
        }

        return false;
    }

    ////

    private static boolean isNaN(char[] array) {
        return ((array[0] == 'n' || array[0] == 'N') && (array[1] == 'a' || array[1] == 'A')
                && (array[2] == 'n' || array[2] == 'N'));
    }

    private static boolean isNull(char[] array) {
        return ((array[0] == 'n' || array[0] == 'N') && (array[1] == 'u' || array[1] == 'U')
                && (array[2] == 'l' || array[2] == 'L') && (array[3] == 'l' || array[3] == 'L'));
    }

    private static boolean isNA(char[] array) {
        return ((array[0] == 'n' || array[0] == 'N') && (array[1] == 'a' || array[1] == 'A'));
    }

    private static boolean isN_A(char[] array) {
        return ((array[0] == 'n' || array[0] == 'N') && (array[1] == '/') && (array[2] == 'a' || array[2] == 'A'));
    }

    ////

    private static boolean isNaN(char[] array, int offset) {
        return ((array[offset] == 'n' || array[offset] == 'N') && (array[offset + 1] == 'a' || array[offset + 1] == 'A')
                && (array[offset + 2] == 'n' || array[offset + 2] == 'N'));
    }

    private static boolean isNull(char[] array, int offset) {
        return ((array[offset] == 'n' || array[offset] == 'N') && (array[offset + 1] == 'u' || array[offset + 1] == 'U')
                && (array[offset + 2] == 'l' || array[offset + 2] == 'L')
                && (array[offset + 3] == 'l' || array[offset + 3] == 'L'));
    }

    private static boolean isNA(char[] array, int offset) {
        return ((array[offset] == 'n' || array[offset] == 'N')
                && (array[offset + 1] == 'a' || array[offset + 1] == 'A'));
    }

    private static boolean isN_A(char[] array, int offset) {
        return ((array[offset] == 'n' || array[offset] == 'N') && (array[offset + 1] == '/')
                && (array[offset + 2] == 'a' || array[offset + 2] == 'A'));
    }

    private static boolean isDigit(char ch) {
        return ch >= 48 && ch <= 57;
    }

    private static int toNumber(char ch) {
        switch (ch) {
        case '0':
            return 0;
        case '1':
            return 1;
        case '2':
            return 2;
        case '3':
            return 3;
        case '4':
            return 4;
        case '5':
            return 5;
        case '6':
            return 6;
        case '7':
            return 7;
        case '8':
            return 8;
        case '9':
            return 9;
        }
        return -1;
    }

    public static void main(String[] args) {
        NumberPart numberPart = null;
        String input = null;

        numberPart = NumberPart.parseNumberPart(input);
        System.out.println(numberPart);

        input = "";
        numberPart = NumberPart.parseNumberPart(input);
        System.out.println(numberPart);

        input = " ";
        numberPart = NumberPart.parseNumberPart(input);
        System.out.println(numberPart);

        input = "1";
        numberPart = NumberPart.parseNumberPart(input);
        System.out.println(numberPart);

        input = "12";
        numberPart = NumberPart.parseNumberPart(input);
        System.out.println(numberPart);

        input = "14.78";
        numberPart = NumberPart.parseNumberPart(input);
        System.out.println(numberPart);

    }
    
}
