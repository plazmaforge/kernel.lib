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

package plazma.kernel.lib.array;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import plazma.kernel.lib.ArithmeticContext;
import plazma.kernel.lib.ArithmeticEnvironment;
import plazma.kernel.lib.collection.CollectionLib;
import plazma.kernel.lib.num.NumLib;
import plazma.kernel.lib.obj.ObjLib;

final class ArrayHelper {

    // https://www.techiedelight.com/convert-int-array-integer-array-java/
    // https://repl.it/languages
    // https://github.com/google/guava/tree/master/guava/src/com/google/common/math

    // https://habr.com/ru/post/352678/
    // https://habr.com/en/post/353416/
    // https://habr.com/en/post/413381/

    public static final boolean DEFAULT_BOOLEAN = ObjLib.DEFAULT_BOOLEAN;

    public static final byte DEFAULT_BYTE = ObjLib.DEFAULT_BYTE;

    public static final char DEFAULT_CHAR = ObjLib.DEFAULT_CHAR;

    public static final short DEFAULT_SHORT = ObjLib.DEFAULT_SHORT;

    public static final int DEFAULT_INT = ObjLib.DEFAULT_INT;

    public static final long DEFAULT_LONG = ObjLib.DEFAULT_LONG;

    public static final float DEFAULT_FLOAT = ObjLib.DEFAULT_FLOAT;

    public static final double DEFAULT_DOUBLE = ObjLib.DEFAULT_DOUBLE;

    //

    public static final boolean[] EMPTY_BOOLEAN_ARRAY = ObjLib.EMPTY_BOOLEAN_ARRAY;

    public static final byte[] EMPTY_BYTE_ARRAY = ObjLib.EMPTY_BYTE_ARRAY;

    public static final char[] EMPTY_CHAR_ARRAY = ObjLib.EMPTY_CHAR_ARRAY;

    public static final short[] EMPTY_SHORT_ARRAY = ObjLib.EMPTY_SHORT_ARRAY;

    public static final int[] EMPTY_INT_ARRAY = ObjLib.EMPTY_INT_ARRAY;

    public static final long[] EMPTY_LONG_ARRAY = ObjLib.EMPTY_LONG_ARRAY;

    public static final float[] EMPTY_FLOAT_ARRAY = ObjLib.EMPTY_FLOAT_ARRAY;

    public static final double[] EMPTY_DOUBLE_ARRAY = ObjLib.EMPTY_DOUBLE_ARRAY;

    // Byte.MAX_VALUE = 127
    // Integer.MAX_VALUE = 2 147 483 647
    // Long.MAX_VALUE = 9 223 372 036 854 775 808

    // 2147483647

    // max sum array:
    // =========================================================================================================
    // byte: 127 * 2 147 483 647 = 272 730 423 169
    // Integer.MAX_VALUE = 2 147 483 647
    // integer: 2 147 483 647 * 2 147 483 647 = 4 611 686 014 132 420 609
    // Long.MAX_VALUE = 9 223 372 036 854 775 808

    // long: 9 223 372 036 854 775 808 * 2 147 483 647 = 19 807 040 619 342 712 361
    // 531 211 776

    private ArrayHelper() {
        super();
    }

    public static ArithmeticContext getContext() {
        return ArithmeticEnvironment.getContext();
    }

    private static ArithmeticContext getContext(ArithmeticContext context) {
        return context == null ? getContext() : context;
    }

    private static boolean isNullException(ArithmeticContext context) {
        return ArithmeticEnvironment.isNullException(context);
    }

    private static boolean isOverflowException(ArithmeticContext context) {
        return ArithmeticEnvironment.isOverflowException(context);
    }

    //

    private static boolean checkNull(Object x, Object y, ArithmeticContext context, String message) {
        return ArithmeticEnvironment.checkNull(x, y, context, message);
    }

    private static <T> boolean checkNull(T[] x, T[] y, ArithmeticContext context) {
        return checkNull(x, y, context, "Array must be not null");
    }

    public static boolean checkIndexOut(int index, int fromIndex, int length) {
        return ArithmeticEnvironment.checkIndexOut(index, fromIndex, length);
    }

    public static boolean checkIndexOut(int index, int length) {
        return ArithmeticEnvironment.checkIndexOut(index, length);
    }

    // toIndex - exclusive!
    public static boolean checkIndexOutRange(int index, int fromIndex, int toIndex) {
        return ArithmeticEnvironment.checkIndexOutRange(index, fromIndex, toIndex);
    }

    //

    private static boolean equalsNull(Object o1, Object o2) {
        return ObjLib.equalsNull(o1, o2);
    }

    private static <T extends Comparable<T>> int compare(T o1, T o2) {
        return ObjLib.compare(o1, o2);
    }

    ////

    static <T> void _arraycopy(T[] from, T[] to, int fromPos, int toPos, int length) {
        System.arraycopy(from, fromPos, to, toPos, length);
    }

    static <T> void _arraycopy(T[] from, T[] to, int length) {
        _arraycopy(from, to, 0, 0, length);
    }

    static <T> void _arraycopy(T[] from, T[] to) {
        _arraycopy(from, to, 0, 0, Math.min(from.length, to.length));
    }

    //// arithmetic operators

    // x + y
    static <T> T[] _add(T[] x, T[] y) {
        return _add(x, y, null);
    }

    // x + y
    static <T> T[] _add(T[] x, T[] y, ArithmeticContext context) {
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }
            T[] r = (x == null) ? y : x; // add
            return _copy(r);
        }
        T[] r = _copy(x, x.length + y.length);
        ArrayHelper._arraycopy(y, r, 0, x.length, y.length);
        return r;
    }

    // x - y
    static <T> T[] _sub(T[] x, T[] y) {
        return _sub(x, y, null);
    }

    //// 1.2

    // x - y
    public static <T> T[] _sub(T[] x, T[] y, ArithmeticContext context) {
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }

            // null - null = null
            // null - {1, 4, 6} = null
            // {1, 4, 6} - null = {1, 4, 6}

            T[] r = (x == null) ? null : _copy(x); // sub
            return r;
        }
        List<T> r = _toList(x);
        List<T> yr = _toList(y);
        r.removeAll(yr);

        T[] empty = _copy(x, 0);
        return r.toArray(empty);
    }

    ////

    static <T> T[] _union(T[] x, T[] y) {
        return _union(x, y, null);
    }

    static <T> T[] _union(T[] x, T[] y, ArithmeticContext context) {
        // add
        T[] result = _add(x, y, context);
        if (result == null) {
            return null;
        }

        // distinct
        Set<T> set = CollectionLib.toSet(result);
        return set.toArray(_copy(result, 0));
    }

    //

    static <T> T[] _intersection(T[] x, T[] y) {
        return _intersection(x, y, null);
    }

    static <T> T[] _intersection(T[] x, T[] y, ArithmeticContext context) {

        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }
            T[] r = (x == null) ? y : x;
            return _copy(r);
        }

        Set<T> set = new LinkedHashSet<>();
        for (int i = 0; i < x.length; i++) {
            boolean found = false;
            for (int j = 0; j < y.length; j++) {
                if (x[i] == y[j]) {
                    found = true;
                    break;
                }
            }

            if (found) {
                set.add(x[i]);
            }
        }
        return set.toArray(_copy(x, 0));
    }

    ////

    public static <T> T[] _copy(T[] original) {
        if (original == null) {
            return null;
        }
        return _copy(original, original.length);
    }

    public static <T> T[] _copy(T[] original, int length) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, length);
    }

    public static <T> T[] _copy(T[] original, int fromIndex, int length) {
        if (original == null) {
            return null;
        }

        // fromIndex range
        if (checkIndexOut(fromIndex, original.length)) {
            return null;
        }
        return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    }

    // reverse(array)

    static <T> void _reverse(T[] array) {
        // no reverse for length = 0 or 1
        if (array == null || array.length < 2) {
            return;
        }
        int len = array.length;
        int count = len / 2;
        T e = null;
        int j = 0;
        for (int i = 0; i < count; i++) {
            j = len - 1 - i;

            // swap: i <-> j
            e = array[i];
            array[i] = array[j];
            array[j] = e;
        }
    }

    // trunc(array, newLength)

    static <T> T[] _trunc(T[] array, int newLength) {
        if (array == null) {
            return null;
        }
        checkTruncLength(array.length, newLength);
        return _resizeArray(array, newLength);
    }

    // resize(array, newLength)

    static <T> T[] _resize(T[] array, int newLength) {
        if (array == null) {
            return null;
        }
        checkResizeLength(array.length, newLength);
        return _resizeArray(array, newLength);
    }

    // resize(array, newLength, element)

    /**
     * Resize <code>array</code> and fill expand space by <code>element</code>
     * 
     * @param array
     * @param newLength
     * @param element
     * @return
     */
    static <T> T[] _resize(T[] array, int newLength, T element) {
        if (array == null) {
            return null;
        }
        T[] newArray = _resize(array, newLength);
        if (newLength > array.length) {
            for (int i = array.length; i < newLength; i++) {
                newArray[i] = element;
            }
        }
        return newArray;
    }

    //

    static <T> T[] _resizeArray(T[] array, int newLength) {
        T[] newArray = Arrays.copyOf(array, newLength);
        // _arraycopy(array, newArray, newLength);
        return newArray;
    }

    //

    static void checkTruncLength(int oldLength, int newLength) {
        if (newLength < 0) {
            throw new IllegalArgumentException("newLength < 0: " + newLength);
        }
        if (newLength > oldLength) {
            throw new IllegalArgumentException("newLength > oldLength: " + newLength + " > " + oldLength);
        }
    }

    static void checkResizeLength(int oldLength, int newLength) {
        if (newLength < 0) {
            throw new IllegalArgumentException("newLength < 0: " + newLength);
        }
    }

    // NonJS
    static <T> T[] _newEmptyArray(Class<T> componentType) {
        return _newArray(componentType, 0);
    }

    // NonJS
    static <T> T[] _newArray(Class<T> componentType, int length) {
        Object array = (componentType == Object.class) ? new Object[length] : Array.newInstance(componentType, length);
        return (T[]) array;
    }

    ////

    static <T> int _count(T[] array, T value, boolean eq) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        int count = 0;
        boolean compare = false;
        for (int i = 0; i < length; i++) {
            compare = equalsNull(array[i], value);
            if ((eq && compare) || (!eq && !compare)) {
                count++;
            }
        }
        return count;
    }

    //// min(array, def, useDef)

    static <T extends Comparable<T>> T _min(T[] array, T def, boolean useDef) {
        if (array == null) {
            return defValue(def, useDef);
        }
        int length = array.length;
        if (length == 0) {
            return defValue(def, useDef);
        }
        T result = defValue(def, useDef); // useDef ? def : null;
        for (int i = 0; i < length; i++) {
            T next = array[i];

            if (i == 0 && !useDef) {
                result = next;
                continue;
            }

            // TODO
            // if (next == null && isNullMode ) {
            // throw new NullPointerException();
            // }

            if (compare(next, result) < 0) {
                result = next;
            }
        }
        return result;
    }

    //// max(array, def, useDef)

    static <T extends Comparable<T>> T _max(T[] array, T def, boolean useDef) {
        if (array == null) {
            return defValue(def, useDef);
        }
        int length = array.length;
        if (length == 0) {
            return defValue(def, useDef);
        }
        T result = defValue(def, useDef); // useDef ? def : null;
        for (int i = 0; i < length; i++) {
            T next = array[i];

            if (i == 0 && !useDef) {
                result = next;
                continue;
            }

            // TODO
            // if (next == null && isNullMode ) {
            // throw new NullPointerException();
            // }

            if (compare(next, result) > 0) {
                result = next;
            }
        }
        return result;
    }

    //// double: sum(<type[]>)

    static double _sum(byte[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        byte value = 0;
        for (int i = 0; i < length; i++) {
            value = array[i];
            sum += value;
        }
        return sum;
    }

    static double _sum(char[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        char value = 0;
        for (int i = 0; i < length; i++) {
            value = array[i];
            sum += value;
        }
        return sum;
    }

    static double _sum(short[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        short value = 0;
        for (int i = 0; i < length; i++) {
            value = array[i];
            sum += value;
        }
        return sum;
    }

    static double _sum(int[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        int value = 0;
        for (int i = 0; i < length; i++) {
            value = array[i];
            sum += value;
        }
        return sum;
    }

    static double _sum(long[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        long value = 0;
        for (int i = 0; i < length; i++) {
            value = array[i];
            sum += value;
        }
        return sum;
    }

    static double _sum(float[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        float value = 0;
        for (int i = 0; i < length; i++) {
            value = array[i];
            sum += value;
        }
        return sum;
    }

    static double _sum(double[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        double value = 0;
        for (int i = 0; i < length; i++) {
            value = array[i];
            sum += value;
        }
        return sum;
    }

    //// <type>: sumShift(<type[]>)

    static byte _sumShift(byte[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        byte sum = 0;
        for (int i = 0; i < length; i++) {
            byte value = array[i];
            sum += value;
        }
        return sum;
    }

    static char _sumShift(char[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        char sum = 0;
        for (int i = 0; i < length; i++) {
            char value = array[i];
            sum += value;
        }
        return sum;
    }

    static short _sumShift(short[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        short sum = 0;
        for (int i = 0; i < length; i++) {
            short value = array[i];
            sum += value;
        }
        return sum;
    }

    static int _sumShift(int[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            int value = array[i];
            sum += value;
        }
        return sum;
    }

    static long _sumShift(long[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        long sum = 0;
        for (int i = 0; i < length; i++) {
            long value = array[i];
            sum += value;
        }
        return sum;
    }

    //// <type>: sum<Type>(<type[]>)

    static byte _sumByte(byte[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        byte sum = 0;
        for (int i = 0; i < length; i++) {
            byte value = array[i];
            sum = _sum(sum, value, context);
        }
        return sum;
    }

    static char _sumChar(char[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        char sum = 0;
        for (int i = 0; i < length; i++) {
            char value = array[i];
            sum = _sum(sum, value, context);
        }
        return sum;
    }

    static short _sumShort(short[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        short sum = 0;
        for (int i = 0; i < length; i++) {
            short value = array[i];
            sum = _sum(sum, value, context);
        }
        return sum;
    }

    static int _sumInt(int[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            int value = array[i];
            sum = _sum(sum, value, context);
        }
        return sum;
    }

    static long _sumLong(long[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        long sum = 0;
        for (int i = 0; i < length; i++) {
            long value = array[i];
            sum = _sum(sum, value, context);
        }
        return sum;
    }

    //// sumBig(array)

    static BigDecimal _sumBig(long[] array) {
        if (array == null) {
            return new BigDecimal(0);
        }
        int length = array.length;
        if (length == 0) {
            return new BigDecimal(0);
        }
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < length; i++) {
            float value = array[i];
            sum.add(toBigDecimal(value));
        }
        return sum;
    }

    static BigDecimal _sumBig(float[] array) {
        if (array == null) {
            return new BigDecimal(0);
        }
        int length = array.length;
        if (length == 0) {
            return new BigDecimal(0);
        }
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < length; i++) {
            float value = array[i];
            sum.add(toBigDecimal(value));
        }
        return sum;
    }

    static BigDecimal _sumBig(double[] array) {
        if (array == null) {
            return new BigDecimal(0);
        }
        int length = array.length;
        if (length == 0) {
            return new BigDecimal(0);
        }
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < length; i++) {
            double value = array[i];
            sum.add(toBigDecimal(value));
        }
        return sum;
    }

    private static BigDecimal toBigDecimal(double value) {
        return new BigDecimal(value);
    }

    //// avg(array)

    static double _avg(byte[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array);
        return sum / length;
    }

    static double _avg(char[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array);
        return sum / length;
    }

    static double _avg(short[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array);
        return sum / length;
    }

    static double _avg(int[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array);
        return sum / length;
    }

    static double _avg(long[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array);
        return sum / length;
    }

    static double _avg(float[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array);
        return sum / length;
    }

    static double _avg(double[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array);
        return sum / length;
    }

    ////

    // byte
    private static byte _sum(byte sum, byte value, ArithmeticContext context) {
        int result = sum + value;
        if (isOverflowByte(result) && isOverflowException(context)) {
            throw new ArithmeticException("Integer overflow");
        }
        return (byte) result;
    }

    // byte
    private static char _sum(char sum, char value, ArithmeticContext context) {
        int result = sum + value;
        if (isOverflowChar(result) && isOverflowException(context)) {
            throw new ArithmeticException("Integer overflow");
        }
        return (char) result;
    }

    // short
    private static short _sum(short sum, short value, ArithmeticContext context) {
        int result = sum + value;
        if (isOverflowShort(result) && isOverflowException(context)) {
            throw new ArithmeticException("Integer overflow");
        }
        return (short) result;
    }

    // int
    private static int _sum(int sum, int value, ArithmeticContext context) {
        int result = sum + value;
        if (isOverflowAdd(sum, value, result) && isOverflowException(context)) {
            throw new ArithmeticException("Integer overflow");
        }
        return result;
    }

    // long
    private static long _sum(long sum, long value, ArithmeticContext context) {
        long result = sum + value;
        if (isOverflowAdd(sum, value, result) && isOverflowException(context)) {
            throw new ArithmeticException("Long overflow");
        }
        return result;
    }

    private static boolean isOverflowByte(int x) {
        return x < Byte.MAX_VALUE || x > Byte.MAX_VALUE;
    }

    private static boolean isOverflowChar(int x) {
        return x < Character.MAX_VALUE || x > Character.MAX_VALUE;
    }

    private static boolean isOverflowShort(int x) {
        return x < Short.MAX_VALUE || x > Short.MAX_VALUE;
    }

    /*
     * // byte: x + y = r private static boolean isOverflowAdd(byte x, byte y, byte
     * r) { return (((x ^ r) & (y ^ r)) < 0 ); }
     * 
     * // char: x + y = r private static boolean isOverflowAdd(char x, char y, char
     * r) { return (((x ^ r) & (y ^ r)) < 0 ); }
     * 
     * // short: x + y = r private static boolean isOverflowAdd(short x, short y,
     * short r) { return (((x ^ r) & (y ^ r)) < 0 ); }
     */

    // int: x + y = r
    private static boolean isOverflowAdd(int x, int y, int r) {
        return (((x ^ r) & (y ^ r)) < 0);
    }

    // long: x + y = r
    private static boolean isOverflowAdd(long x, long y, long r) {
        return (((x ^ r) & (y ^ r)) < 0);
    }

    ////

    static boolean _replaceAll(Object[] array, Object oldValue, Object newValue) {
        if (array == null) {
            return false;
        }
        boolean result = false;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            Object e = array[i];
            if (equalsNull(e, oldValue)) {
                array[i] = newValue;
                result = true;
            }
        }
        return result;
    }

    static <T> boolean[] _replaceAll(Object[] array, T[] oldValues, T[] newValues) {
        if (array == null || oldValues == null || newValues == null) {
            // TODO: Exception
            return new boolean[0];
        }
        int size = Math.min(oldValues.length, newValues.length);
        if (size == 0) {
            return new boolean[0];
        }
        T oldValue = null;
        T newValue = null;
        boolean[] result = new boolean[size];
        for (int i = 0; i < size; i++) {
            oldValue = oldValues[i];
            newValue = newValues[i];
            // if (eqn(oldVal, newVal)) {
            // oldVal = newVal: why replace?
            // continue;
            // }
            if (_replaceAll(array, oldValue, newValue)) {
                result[i] = true;
            }
        }
        return result;
    }

    //// Converts a wrapper array to a primitive array
    // Converts Boolean[] -> boolean[]
    // Converts boolean[] -> Boolean[]

    static String[] _asArray(String... array) {
        if (array == null) {
            return null;
        }
        int size = array.length;
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    static Object[] _asObjectArray(Object... array) {
        if (array == null) {
            return null;
        }
        int size = array.length;
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    static <T> T[] _asArray(Class<T> componentType, T... array) {
        if (array == null) {
            return null;
        }
        int size = array.length;
        T[] result = _newArray(componentType, size);
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    ////

    private static boolean defValue(boolean def, boolean useDef) {
        return useDef ? def : false;
    }

    private static byte defValue(byte def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static char defValue(char def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static short defValue(short def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static int defValue(int def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static long defValue(long def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static float defValue(float def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static double defValue(double def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    //

    private static <T> T defValue(T def, boolean useDef) {
        return ObjLib.defValue(def, useDef);
    }

//    private static boolean isOverflowException(ArithmeticContext context) {
//	return context.isOverflowException();
//    } 

    private static <T> List<T> _toList(T[] array) {
        return CollectionLib.toList(array);
    }
}
