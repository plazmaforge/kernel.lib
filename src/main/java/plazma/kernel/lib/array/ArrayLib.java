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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import plazma.kernel.lib.ArithmeticContext;
import plazma.kernel.lib.ArithmeticEnvironment;
import plazma.kernel.lib.collection.CollectionLib;
import plazma.kernel.lib.num.NumLib;
import plazma.kernel.lib.obj.ObjLib;

public class ArrayLib {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    //
    // add/sub/union/intersection
    //
    // 1.1
    // - add({1, 3, 5}, {7, 9}): {1, 3, 5} + {7, 9} = {1, 3, 5, 7, 9}
    //
    // 1.2
    // - sub({1, 3, 5, 7, 9}, {3, 7}): {1, 3, 5, 7, 9} - {3, 7} = {1, 5, 9}
    //
    // 1.3
    // - union({1, 3, 5}, {7, 9, 3, 1}): {1, 3, 5} + {7, 9, 3, 1} = {1, 3, 5, 7, 9}
    //
    // 1.4
    // - intersection({1, 3, 5}, {7, 9, 3, 1}): {1, 3, 5} + {7, 9, 3, 1} = {1, 3}

    /////////////////////////////////////////////////////////////////////////////////
    //
    // copy/copyRange
    //
    // 2.1
    // - copy({1, 3, 5}): {1, 3, 5} = {1, 3, 5}
    // - copy({1, 3, 5}, 2): {1, 3, 5}, 2 = {1, 3}
    //
    // 2.2
    // - copyRange({1, 3, 5}, 0, 2): {1, 3, 5}, 0, 2 = {1, 3}

    /////////////////////////////////////////////////////////////////////////////////
    //
    // fill/replaceAll
    //
    // 3.1
    // - fill(array, "A"): {"A", "A", "A"}
    //
    // 3.2
    // - replaceAll(array, "C", "W"): {"B", "W"}
    //
    // 3.3
    // - replaceAll(array, new int{1, 2}, new int[] {10, 20}): 1 -> 10, 2 -> 20

    /////////////////////////////////////////////////////////////////////////////////
    //
    // isEmpty/equals/contains
    //
    // 4.1
    // - isEmpty(array)
    //
    // 4.2
    // - equals(array1, array2)
    //
    // 4.3
    // - contains(array, value)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // findFirst/findLast/findAll
    //
    // 5.1
    // - findFirst(array, filter)
    //
    // 5.2
    // - findLast(array, filter)
    //
    // 5.3
    // - findAll(array, filter)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // size/count/countNot/countZero/countNotZero/countNull/countNotNull
    //
    // 6.1
    // - size(array)
    //
    // 6.2
    // - count(array)
    //
    // 6.3
    // - count(array, "A")
    //
    // 6.4
    // - countNot(array, "B")
    //
    // 6.5
    // - countZero(array)
    //
    // 6.6
    // - countNotZero(array)
    //
    // 6.7
    // - countNull(array)
    //
    // 6.8
    // - countNotNull(array)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // min/max
    //
    // 7.1
    // - min(array)
    //
    // 7.2
    // - min(array, def)
    //
    // 7.3
    // - max(array)
    //
    // 7.4
    // - max(array, def)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // sumShift/sum/sumInt/sumDouble/sumBig
    //
    // 8.1
    // - double: sum(<type[]>)
    //
    // 8.2
    // - <type> sumShift(<type[]>) :byte, char, short, int, long: 'overflow' =>
    ///////////////////////////////////////////////////////////////////////////////// 'shift'
    //
    // 8.3
    // - <type>: sum<Type>(<type[]>)
    // - <type>: sum<Type>(<type[]>, context) :byte, char, short, int, long:
    ///////////////////////////////////////////////////////////////////////////////// 'overflow'
    ///////////////////////////////////////////////////////////////////////////////// =>
    ///////////////////////////////////////////////////////////////////////////////// 'shift/exception'
    ///////////////////////////////////////////////////////////////////////////////// depends
    ///////////////////////////////////////////////////////////////////////////////// context
    //
    // 8.4
    // - BigDecimal: sumBig(<type[]>)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 9.1
    // - avg(array)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 10.1
    // - sort(array)
    //
    // 10.2
    // - sort(array, fromIndex, toIndex)
    //

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 11.1
    //
    // - filter(array, filter)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 12.1
    // - distinct(array)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 13.1
    // - reverse(array)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 14.1
    // - trunc(array, newLength)
    //
    // 14.2
    // - resize(array, newLength)
    //
    // 14.3
    // - resize(array, newLength, element)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // toArray(array)/toArray<Type>(collection)/toArray(collection, type)
    //
    // 15.1
    // - toArray(array) :Primitive[] -> primitive[]
    //
    // 15.2
    // - toArray(array) :primitive[] -> Primitive[]
    //
    // 15.3
    // - to<Type>Array(collection) :Collection<Primitive> -> Primitive[]
    //
    // 15.4
    // - toPrimitive<Type>Array(collection) :Collection<Primitive> -> primitive[]
    //
    // 15.5
    // - toArray(collection, type)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // asArray
    //
    // 16.1
    // - asArray(e1, e2, ... en)
    // - asArray(type, e1, e2, ... en)
    // - asObjectArray(e1, e2, ... en)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // toSortArray/toFilterArray/toReverseArray
    //
    // 17.1
    // - toSortArray(array)
    // - toSortArray(array, comparator)
    //
    // 17.2
    // - toFilterArray(array, filter)
    //
    // 17.3
    // - toCriteriaArray(array, filter, sorter)
    //
    // 17.3
    // - toReverseArray(array) (? - primitive)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // toList
    //
    // 18.1
    // toList(array)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // random<Type>Array
    //
    // 19.1
    // - random<Type>Array(lenght)
    // - random<Type>Array(lenght, max)
    // - random<Type>Array(lenght, min, max)
    // - random<Type>Array(lenght, min, max, mapper)
    // - random<Type>Array(lenght, mapper)
    // - random<Type>Array(lenght, max, mapper)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // read/write array from/to String
    //
    // 20.1
    // - primitive[]: read<Primitive>Array(line) : String -> array
    //
    // 20.2
    // - String: write<Primitive>Array(primitive[]) : array -> String

    /////////////////////////////////////////////////////////////////////////////////
    //
    // toString
    //
    // 21.1
    // - toString(array)
    // - toString(array, hasQuote)
    // - toString(array, separator)
    // - toString(array, hasQuote, separator)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 22.1
    // - rangeDoubleArray(double min, double max)
    // - rangeDoubleArray(double min, double max, double step)
    //
    // 22.2
    // - pointDoubleArray(double min, double max, int count)
    //
    // 22.3
    // - evaluateDoubleArray(double[] x, Function<Double, Double> function)

    // https://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java
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

    private ArrayLib() {
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

    ////

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

    ////

//    public static <T> T[] newInstance(Class<? extends T[]> type, int length) {
//        @SuppressWarnings("unchecked")
//        T[] copy = ((Object) type == (Object) Object[].class)
//            ? (T[]) new Object[length]
//            : (T[]) Array.newInstance(type.getComponentType(), length);
//        return copy;
//    }

    // NonJS
    public static <T> T[] newEmptyArray(Class<T> componentType) {
        return ArrayHelper._newEmptyArray(componentType);
    }

    // NonJS
    public static <T> T[] newArray(Class<T> componentType, int length) {
        return ArrayHelper._newArray(componentType, length);
    }

    //// 1.1

    // x + y
    public static <T> T[] add(T[] x, T[] y) {
        return ArrayHelper._add(x, y);
    }

    public static <T> T[] add(T[] x, T[] y, ArithmeticContext context) {
        return ArrayHelper._add(x, y, context);
    }

    public static boolean[] add(boolean[] x, boolean[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static boolean[] add(boolean[] x, boolean[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    public static byte[] add(byte[] x, byte[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static byte[] add(byte[] x, byte[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    public static char[] add(char[] x, char[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static char[] add(char[] x, char[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    public static short[] add(short[] x, short[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static short[] add(short[] x, short[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    public static int[] add(int[] x, int[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static int[] add(int[] x, int[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    public static long[] add(long[] x, long[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static long[] add(long[] x, long[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    public static float[] add(float[] x, float[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static float[] add(float[] x, float[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    public static double[] add(double[] x, double[] y) {
        return ArrayPrimitiveHelper._add(x, y);
    }

    public static double[] add(double[] x, double[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._add(x, y, context);
    }

    //// 1.2

    // x - y
    public static <T> T[] sub(T[] x, T[] y) {
        return ArrayHelper._sub(x, y);
    }

    public static <T> T[] sub(T[] x, T[] y, ArithmeticContext context) {
        return ArrayHelper._sub(x, y, context);
    }

    public static boolean[] sub(boolean[] x, boolean[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static boolean[] sub(boolean[] x, boolean[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    public static byte[] sub(byte[] x, byte[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static byte[] sub(byte[] x, byte[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    public static char[] sub(char[] x, char[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static char[] sub(char[] x, char[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    public static short[] sub(short[] x, short[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static short[] sub(short[] x, short[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    public static int[] sub(int[] x, int[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static int[] sub(int[] x, int[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    public static long[] sub(long[] x, long[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static long[] sub(long[] x, long[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    public static float[] sub(float[] x, float[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static float[] sub(float[] x, float[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    public static double[] sub(double[] x, double[] y) {
        return ArrayPrimitiveHelper._sub(x, y);
    }

    public static double[] sub(double[] x, double[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._sub(x, y, context);
    }

    //// 1.3 union

    public static <T> T[] union(T[] x, T[] y) {
        return ArrayHelper._union(x, y, null);
    }

    public static <T> T[] union(T[] x, T[] y, ArithmeticContext context) {
        return ArrayHelper._union(x, y, context);
    }

    public static boolean[] union(boolean[] x, boolean[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static boolean[] union(boolean[] x, boolean[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    public static byte[] union(byte[] x, byte[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static byte[] union(byte[] x, byte[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    public static char[] union(char[] x, char[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static char[] union(char[] x, char[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    public static short[] union(short[] x, short[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static short[] union(short[] x, short[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    public static int[] union(int[] x, int[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static int[] union(int[] x, int[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    public static long[] union(long[] x, long[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static long[] union(long[] x, long[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    public static float[] union(float[] x, float[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static float[] union(float[] x, float[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    public static double[] union(double[] x, double[] y) {
        return ArrayPrimitiveHelper._union(x, y, null);
    }

    public static double[] union(double[] x, double[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._union(x, y, context);
    }

    //// 1.4 intersection

    public static <T> T[] intersection(T[] x, T[] y) {
        return ArrayHelper._intersection(x, y, null);
    }

    public static <T> T[] intersection(T[] x, T[] y, ArithmeticContext context) {
        return ArrayHelper._intersection(x, y, context);
    }

    public static boolean[] intersection(boolean[] x, boolean[] y) {
        return ArrayPrimitiveHelper._intersection(x, y, null);
    }

    public static boolean[] intersection(boolean[] x, boolean[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._intersection(x, y, context);
    }

    public static byte[] intersection(byte[] x, byte[] y) {
        return ArrayPrimitiveHelper._intersection(x, y, null);
    }

    public static byte[] intersection(byte[] x, byte[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._intersection(x, y, context);
    }

    public static char[] intersection(char[] x, char[] y) {
        return ArrayPrimitiveHelper._intersection(x, y, null);
    }

    public static char[] intersection(char[] x, char[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._intersection(x, y, context);
    }

    public static int[] intersection(int[] x, int[] y) {
        return ArrayPrimitiveHelper._intersection(x, y, null);
    }

    public static int[] intersection(int[] x, int[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._intersection(x, y, context);
    }

    public static long[] intersection(long[] x, long[] y) {
        return ArrayPrimitiveHelper._intersection(x, y, null);
    }

    public static long[] intersection(long[] x, long[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._intersection(x, y, context);
    }

    public static float[] intersection(float[] x, float[] y) {
        return ArrayPrimitiveHelper._intersection(x, y, null);
    }

    public static float[] intersection(float[] x, float[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._intersection(x, y, context);
    }

    public static double[] intersection(double[] x, double[] y) {
        return ArrayPrimitiveHelper._intersection(x, y, null);
    }

    public static double[] intersection(double[] x, double[] y, ArithmeticContext context) {
        return ArrayPrimitiveHelper._intersection(x, y, context);
    }

    //// 2.1

    public static <T> T[] copy(T[] original) {
        return ArrayHelper._copy(original);
    }

    public static <T> T[] copy(T[] original, int length) {
        return ArrayHelper._copy(original, length);
    }

    public static <T> T[] copy(T[] original, int fromIndex, int length) {
        return ArrayHelper._copy(original, fromIndex, length);
    }

    //// primitive: copy(array)

    public static boolean[] copy(boolean[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static boolean[] copy(boolean[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    public static byte[] copy(byte[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static byte[] copy(byte[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    public static char[] copy(char[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static char[] copy(char[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    public static short[] copy(short[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static short[] copy(short[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    public static int[] copy(int[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static int[] copy(int[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    public static long[] copy(long[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static long[] copy(long[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    public static float[] copy(float[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static float[] copy(float[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    public static double[] copy(double[] original) {
        return ArrayPrimitiveHelper._copy(original);
    }

    public static double[] copy(double[] original, int length) {
        return ArrayPrimitiveHelper._copy(original, length);
    }

    //// 2.2

    public static <T> T[] copyRange(T[] original, int fromIndex, int toIndex) {
        if (original == null) {
            return null;
        }

        // TODO: check IndexMode: checkIndex
        // fromIndex range
        if (checkIndexOut(fromIndex, original.length)) {
            return null;
        }

        // DISABLE: because we can expand array by 'null' values
        // toIndex range
        // if (checkIndexOut(toIndex, original.length) || toIndex < fromIndex) {
        // return null;
        // }

        // fromIndex, toIndex
        if (toIndex < fromIndex) {
            return null;
        }
        return Arrays.copyOfRange(original, fromIndex, toIndex);
    }

    public static boolean[] copyRange(boolean[] original, int fromIndex, int toIndex) {
        return ArrayPrimitiveHelper._copyRange(original, fromIndex, toIndex);
    }

    public static byte[] copyRange(byte[] original, int fromIndex, int toIndex) {
        return ArrayPrimitiveHelper._copyRange(original, fromIndex, toIndex);
    }

    public static char[] copyRange(char[] original, int fromIndex, int toIndex) {
        return ArrayPrimitiveHelper._copyRange(original, fromIndex, toIndex);
    }

    public static short[] copyRange(short[] original, int fromIndex, int toIndex) {
        return ArrayPrimitiveHelper._copyRange(original, fromIndex, toIndex);
    }

    public static int[] copyRange(int[] original, int fromIndex, int toIndex) {
        return ArrayPrimitiveHelper._copyRange(original, fromIndex, toIndex);
    }

    public static float[] copyRange(float[] original, int fromIndex, int toIndex) {
        return ArrayPrimitiveHelper._copyRange(original, fromIndex, toIndex);
    }

    public static double[] copyRange(double[] original, int fromIndex, int toIndex) {
        return ArrayPrimitiveHelper._copyRange(original, fromIndex, toIndex);
    }

    //// 3.1

    public static void fill(boolean[] array, boolean value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(byte[] array, byte value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(char[] array, char value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(short[] array, short value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(int[] array, int value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(long[] array, long value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(float[] array, float value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(double[] array, double value) {
        ArrayPrimitiveHelper._fill(array, value);
    }

    public static void fill(Object[] array, Object value) {
        if (array == null) {
            return;
        }
        Arrays.fill(array, value);
    }

    ////

    public static void fill(boolean[] array, boolean value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(byte[] array, byte value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(char[] array, char value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(short[] array, short value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(int[] array, int value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(long[] array, long value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(float[] array, float value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(double[] array, double value, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._fill(array, value, fromIndex, toIndex);
    }

    public static void fill(Object[] array, Object value, int fromIndex, int toIndex) {
        if (array == null) {
            return;
        }
        Arrays.fill(array, fromIndex, toIndex, value);
    }

    //// 3.2

    public static boolean replaceAll(boolean[] array, boolean oldValue, boolean newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(byte[] array, byte oldValue, byte newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(char[] array, char oldValue, char newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(short[] array, short oldValue, short newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(int[] array, int oldValue, int newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(long[] array, long oldValue, long newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(float[] array, float oldValue, float newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(double[] array, double oldValue, double newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean replaceAll(Object[] array, Object oldValue, Object newValue) {
        return ArrayHelper._replaceAll(array, oldValue, newValue);
    }

    //// 3.3

    public static boolean[] replaceAll(boolean[] array, boolean[] oldValue, boolean[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean[] replaceAll(byte[] array, byte[] oldValue, byte[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean[] replaceAll(char[] array, char[] oldValue, char[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean[] replaceAll(short[] array, short[] oldValue, short[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean[] replaceAll(int[] array, int[] oldValue, int[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean[] replaceAll(long[] array, long[] oldValue, long[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean[] replaceAll(float[] array, float[] oldValue, float[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static boolean[] replaceAll(double[] array, double[] oldValue, double[] newValue) {
        return ArrayPrimitiveHelper._replaceAll(array, oldValue, newValue);
    }

    public static <T> boolean[] replaceAll(Object[] array, T[] oldValues, T[] newValues) {
        return ArrayHelper._replaceAll(array, oldValues, newValues);
    }

    //// 4.1 isEmpty

    public static boolean isEmpty(boolean[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(byte[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(char[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(short[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(int[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(long[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(float[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(double[] array) {
        return ArrayPrimitiveHelper.isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return size(array) == 0;
    }

    //// 4.2 equals

    public static boolean equals(boolean[] array1, boolean[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(byte[] array1, byte[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(char[] array1, char[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(short[] array1, short[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(int[] array1, int[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(long[] array1, long[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(float[] array1, float[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(double[] array1, double[] array2) {
        return ArrayPrimitiveHelper.equals(array1, array2);
    }

    public static boolean equals(Object[] array1, Object[] array2) {
        return Arrays.equals(array1, array2);
    }

    //// 4.3 contains

    public static boolean contains(boolean[] array, boolean value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(byte[] array, byte value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(char[] array, char value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(short[] array, short value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(int[] array, int value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(long[] array, long value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(float[] array, float value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(double[] array, double value) {
        return ArrayPrimitiveHelper.contains(array, value);
    }

    public static boolean contains(Object[] array, Object value) {
        if (array == null) {
            return false;
        }
        int length = array.length;
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (ObjLib.equals(array[i], value)) {
                return true;
            }
        }
        return false;
    }

    //// findFirst
    // TODO: bynarySearch

    //// 5.1 findFirst

    public static boolean findFirst(boolean[] array, Predicate<Boolean> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static byte findFirst(byte[] array, Predicate<Byte> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static char findFirst(char[] array, Predicate<Character> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static short findFirst(short[] array, Predicate<Short> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static int findFirst(int[] array, Predicate<Integer> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static long findFirst(long[] array, Predicate<Long> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static float findFirst(float[] array, Predicate<Float> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static double findFirst(double[] array, Predicate<Double> filter) {
        return ArrayPrimitiveHelper._findFirst(array, filter);
    }

    public static <T> T findFirst(T[] array, Predicate<T> filter) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (length == 0) {
            return null;
        }
        if (filter == null) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            if (filter.test(array[i])) {
                return array[i];
            }
        }
        return null;
    }

    //// 5.2 findLast

    public static boolean findLast(boolean[] array, Predicate<Boolean> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static byte findLast(byte[] array, Predicate<Byte> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static char findLast(char[] array, Predicate<Character> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static short findLast(short[] array, Predicate<Short> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static int findLast(int[] array, Predicate<Integer> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static long findLast(long[] array, Predicate<Long> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static float findLast(float[] array, Predicate<Float> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static double findLast(double[] array, Predicate<Double> filter) {
        return ArrayPrimitiveHelper._findLast(array, filter);
    }

    public static <T> T findLast(T[] array, Predicate<T> filter) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        if (length == 0) {
            return null;
        }
        if (filter == null) {
            return null;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (filter.test(array[i])) {
                return array[i];
            }
        }
        return null;
    }

    //// 5.3 findAll

    public static boolean[] findAll(boolean[] array, Predicate<Boolean> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static byte[] findAll(byte[] array, Predicate<Byte> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static char[] findAll(char[] array, Predicate<Character> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static short[] findAll(short[] array, Predicate<Short> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static int[] findAll(int[] array, Predicate<Integer> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static long[] findAll(long[] array, Predicate<Long> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static float[] findAll(float[] array, Predicate<Float> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static double[] findAll(double[] array, Predicate<Double> filter) {
        return ArrayPrimitiveHelper._findAll(array, filter);
    }

    public static <T> T[] findAll(T[] array, Predicate<T> filter) {
        return filter(array, filter);
    }

    //// 6.1 size

    public static int size(boolean[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(byte[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(char[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(short[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(int[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(long[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(float[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(double[] array) {
        return ArrayPrimitiveHelper._size(array);
    }

    public static int size(Object[] array) {
        return array == null ? 0 : array.length;
    }

    //// 6.2 count

    public static int count(boolean[] array) {
        return size(array);
    }

    public static int count(byte[] array) {
        return size(array);
    }

    public static int count(char[] array) {
        return size(array);
    }

    public static int count(short[] array) {
        return size(array);
    }

    public static int count(int[] array) {
        return size(array);
    }

    public static int count(long[] array) {
        return size(array);
    }

    public static int count(float[] array) {
        return size(array);
    }

    public static int count(double[] array) {
        return size(array);
    }

    // alias: size()
    public static int count(Object[] array) {
        return size(array);
    }

    //// 6.3 count(array, value)

    public static int count(boolean[] array, boolean value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    public static int count(byte[] array, byte value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    public static int count(char[] array, char value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    public static int count(short[] array, short value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    public static int count(int[] array, int value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    public static int count(long[] array, long value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    public static int count(float[] array, float value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    public static int count(double[] array, double value) {
        return ArrayPrimitiveHelper._count(array, value, true);
    }

    //

    public static <T> int count(T[] array, T value) {
        return ArrayHelper._count(array, value, true);
    }

    //// 6.4 countNot(array, value)

    public static int countNot(boolean[] array, boolean value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    public static int countNot(byte[] array, byte value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    public static int countNot(char[] array, char value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    public static int countNot(short[] array, short value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    public static int countNot(int[] array, int value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    public static int countNot(long[] array, long value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    public static int countNot(float[] array, float value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    public static int countNot(double[] array, double value) {
        return ArrayPrimitiveHelper._count(array, value, false);
    }

    ////

    public static <T> int countNot(T[] array, T value) {
        return ArrayHelper._count(array, value, false); // Not
    }

    //// 6.5 countZero(array): for number only (non boolean)

    public static int countZero(byte[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_BYTE, true);
    }

    public static int countZero(char[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_CHAR, true);
    }

    public static int countZero(short[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_SHORT, true);
    }

    public static int countZero(int[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_INT, true);
    }

    public static int countZero(long[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_LONG, true);
    }

    public static int countZero(float[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_FLOAT, true);
    }

    public static int countZero(double[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_DOUBLE, true);
    }

    //// 6.6 countNotZero(array): for number only (non boolean)

    public static int countNotZero(byte[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_BYTE, false);
    }

    public static int countNotZero(char[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_CHAR, false);
    }

    public static int countNotZero(short[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_SHORT, false);
    }

    public static int countNotZero(int[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_INT, false);
    }

    public static int countNotZero(long[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_LONG, false);
    }

    public static int countNotZero(float[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_FLOAT, false);
    }

    public static int countNotZero(double[] array) {
        return ArrayPrimitiveHelper._count(array, DEFAULT_DOUBLE, false);
    }

    //// 6.7 countNull(array)

    public static <T> int countNull(T[] array) {
        return ArrayHelper._count(array, null, true);
    }

    //// 6.8 countNotNull(array)

    public static <T> int countNotNull(T[] array) {
        return ArrayHelper._count(array, null, false); // Not
    }

    //// 7.1 min(array)

    public static boolean min(boolean[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_BOOLEAN, false);
    }

    public static byte min(byte[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_BYTE, false);
    }

    public static char min(char[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_CHAR, false);
    }

    public static short min(short[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_SHORT, false);
    }

    public static int min(int[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_INT, false);
    }

    public static long min(long[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_LONG, false);
    }

    public static float min(float[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_FLOAT, false);
    }

    public static double min(double[] array) {
        return ArrayPrimitiveHelper._min(array, DEFAULT_DOUBLE, false);
    }

    //

    public static <T extends Comparable<T>> T min(T[] array) {
        return ArrayHelper._min(array, null, false);
    }

    //// 7.2 min(array, def)

    public static boolean min(boolean[] array, boolean def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    public static byte min(byte[] array, byte def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    public static char min(char[] array, char def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    public static short min(short[] array, short def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    public static int min(int[] array, int def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    public static long min(long[] array, long def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    public static float min(float[] array, float def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    public static double min(double[] array, double def) {
        return ArrayPrimitiveHelper._min(array, def, true);
    }

    //

    public static <T extends Comparable<T>> T min(T[] array, T def) {
        return ArrayHelper._min(array, def, true);
    }

    //// 7.3 max(array)

    public static boolean max(boolean[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_BOOLEAN, false);
    }

    public static byte max(byte[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_BYTE, false);
    }

    public static char max(char[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_CHAR, false);
    }

    public static short max(short[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_SHORT, false);
    }

    public static int max(int[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_INT, false);
    }

    public static long max(long[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_LONG, false);
    }

    public static float max(float[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_FLOAT, false);
    }

    public static double max(double[] array) {
        return ArrayPrimitiveHelper._max(array, DEFAULT_DOUBLE, false);
    }

    //

    public static <T extends Comparable<T>> T max(T[] array) {
        return ArrayHelper._max(array, null, false);
    }

    //// 7.4 max(array, def)

    public static boolean max(boolean[] array, boolean def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    public static byte max(byte[] array, byte def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    public static char max(char[] array, char def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    public static short max(short[] array, short def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    public static int max(int[] array, int def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    public static long max(long[] array, long def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    public static float max(float[] array, float def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    public static double max(double[] array, double def) {
        return ArrayPrimitiveHelper._max(array, def, true);
    }

    //

    public static <T extends Comparable<T>> T max(T[] array, T def) {
        return ArrayHelper._min(array, def, true);
    }

    //// 8.1 double: sum(<type[]>)

    public static double sum(byte[] array) {
        return ArrayHelper._sum(array);
    }

    public static double sum(char[] array) {
        return ArrayHelper._sum(array);
    }

    public static double sum(short[] array) {
        return ArrayHelper._sum(array);
    }

    public static double sum(int[] array) {
        return ArrayHelper._sum(array);
    }

    public static double sum(long[] array) {
        return ArrayHelper._sum(array);
    }

    public static double sum(float[] array) {
        return ArrayHelper._sum(array);
    }

    public static double sum(double[] array) {
        return ArrayHelper._sum(array);
    }

    //// 8.2 <type>: sumShift(<type[]>)

    public static byte sumShift(byte[] array) {
        return ArrayHelper._sumShift(array);
    }

    public static char sumShift(char[] array) {
        return ArrayHelper._sumShift(array);
    }

    public static short sumShift(short[] array) {
        return ArrayHelper._sumShift(array);
    }

    public static int sumShift(int[] array) {
        return ArrayHelper._sumShift(array);
    }

    public static long sumShift(long[] array) {
        return ArrayHelper._sumShift(array);
    }

    //// 8.3 <type>: sum<Type>(<type[]>)

    public static byte sumByte(byte[] array) {
        return ArrayHelper._sumByte(array, null);
    }

    public static int sumByte(byte[] array, ArithmeticContext context) {
        return ArrayHelper._sumByte(array, context);
    }

    public static short sumByte(short[] array) {
        return ArrayHelper._sumShort(array, null);
    }

    public static short sumByte(short[] array, ArithmeticContext context) {
        return ArrayHelper._sumShort(array, context);
    }

    public static int sumInt(int[] array) {
        return ArrayHelper._sumInt(array, null);
    }

    public static int sumInt(int[] array, ArithmeticContext context) {
        return ArrayHelper._sumInt(array, context);
    }

    //// 8.4 BigDecimal: sumBig(<type[]>)

    public static BigDecimal sumBig(long[] array) {
        return ArrayHelper._sumBig(array);
    }

    public static BigDecimal sumBig(float[] array) {
        return ArrayHelper._sumBig(array);
    }

    public static BigDecimal sumBig(double[] array) {
        return ArrayHelper._sumBig(array);
    }

    //// 9.1 avg(array)

    public static double avg(byte[] array) {
        return ArrayHelper._avg(array);
    }

    public static double avg(char[] array) {
        return ArrayHelper._avg(array);
    }

    public static double avg(short[] array) {
        return ArrayHelper._avg(array);
    }

    public static double avg(int[] array) {
        return ArrayHelper._avg(array);
    }

    public static double avg(float[] array) {
        return ArrayHelper._avg(array);
    }

    public static double avg(double[] array) {
        return ArrayHelper._avg(array);
    }

//    public static float avg(float[] array, ArithmeticContext context) {
//	return ArrayHelper._avg(array, context);
//    }

//    public static double avg(double[] array, ArithmeticContext context) {
//	return ArrayHelper._avg(array, context);
//    }

    //// 10.1

    public static void sort(boolean[] array) {
        ArrayStreamHelper._sort(array); // TODO
    }

    public static void sort(byte[] array) {
        ArrayPrimitiveHelper._sort(array);
    }

    public static void sort(char[] array) {
        ArrayPrimitiveHelper._sort(array);
    }

    public static void sort(short[] array) {
        ArrayPrimitiveHelper._sort(array);
    }

    public static void sort(int[] array) {
        ArrayPrimitiveHelper._sort(array);
    }

    public static void sort(long[] array) {
        ArrayPrimitiveHelper._sort(array);
    }

    public static void sort(float[] array) {
        ArrayPrimitiveHelper._sort(array);
    }

    public static void sort(double[] array) {
        ArrayPrimitiveHelper._sort(array);
    }

    //// 10.2

    public static void sort(boolean[] array, int fromIndex, int toIndex) {
        ArrayStreamHelper._sort(array, fromIndex, toIndex); // TODO
    }

    public static void sort(byte[] array, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._sort(array, fromIndex, toIndex);
    }

    public static void sort(char[] array, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._sort(array, fromIndex, toIndex);
    }

    public static void sort(short[] array, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._sort(array, fromIndex, toIndex);
    }

    public static void sort(int[] array, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._sort(array, fromIndex, toIndex);
    }

    public static void sort(long[] array, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._sort(array, fromIndex, toIndex);
    }

    public static void sort(float[] array, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._sort(array, fromIndex, toIndex);
    }

    public static void sort(double[] array, int fromIndex, int toIndex) {
        ArrayPrimitiveHelper._sort(array, fromIndex, toIndex);
    }

    ////

    public static void sort(Object[] array) {
        ArrayStreamHelper._sort(array);
    }

    public static void sort(Object[] array, int fromIndex, int toIndex) {
        ArrayStreamHelper._sort(array, fromIndex, toIndex);
    }

    public static <T> void sort(T[] array, int fromIndex, int toIndex, Comparator<? super T> comparator) {
        ArrayStreamHelper._sort(array, fromIndex, toIndex, comparator);
    }

    public static <T> void sort(T[] array, Comparator<? super T> comparator) {
        ArrayStreamHelper._sort(array, comparator);
    }

    //// 11.1

    public static boolean[] filter(boolean[] array, Predicate<Boolean> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    public static byte[] filter(byte[] array, Predicate<Byte> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    public static char[] filter(char[] array, Predicate<Character> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    public static short[] filter(short[] array, Predicate<Short> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    public static int[] filter(int[] array, Predicate<Integer> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    public static long[] filter(long[] array, Predicate<Long> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    public static float[] filter(float[] array, Predicate<Float> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    public static double[] filter(double[] array, Predicate<Double> filter) {
        return ArrayPrimitiveHelper._filter(array, filter);
    }

    //

    static <T> T[] filter(T[] array, Predicate<T> filter) {
        return ArrayStreamHelper._filter(array, filter);
    }

    //// 12.1

    public static boolean[] distinct(boolean[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    public static byte[] distinct(byte[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    public static char[] distinct(char[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    public static short[] distinct(short[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    public static int[] distinct(int[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    public static long[] distinct(long[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    public static float[] distinct(float[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    public static double[] distinct(double[] array) {
        return ArrayPrimitiveHelper._distinct(array);
    }

    // experimental

    public static int[] distinct2(int[] array) {
        return ArrayStreamHelper._distinct2(array);
    }

    //

    public static <T> T[] distinct(T[] array) {
        return ArrayStreamHelper._distinct(array);
    }

    //// 13.1 reverse

    public static void reverse(boolean[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static void reverse(byte[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static void reverse(char[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static void reverse(short[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static void reverse(int[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static void reverse(long[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static void reverse(float[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static void reverse(double[] array) {
        ArrayPrimitiveHelper._reverse(array);
    }

    public static <T> void reverse(T[] array) {
        ArrayHelper._reverse(array);
    }

    //// 14.1 truncate array: set newLength < oldLength

    public static boolean[] trunc(boolean[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static byte[] trunc(byte[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static char[] trunc(char[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static short[] trunc(short[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static int[] trunc(int[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static long[] trunc(long[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static float[] trunc(float[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static double[] trunc(double[] array, int newLength) {
        return ArrayPrimitiveHelper._trunc(array, newLength);
    }

    public static <T> T[] trunc(T[] array, int newLength) {
        return ArrayHelper._trunc(array, newLength);
    }

    // 14.2 resize array: set newLength <,> oldLength

    public static boolean[] resize(boolean[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static byte[] resize(byte[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static char[] resize(char[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static short[] resize(short[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static int[] resize(int[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static long[] resize(long[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static float[] resize(float[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static double[] resize(double[] array, int newLength) {
        return ArrayPrimitiveHelper._resize(array, newLength);
    }

    public static <T> T[] resize(T[] array, int newLength) {
        return ArrayHelper._resize(array, newLength);
    }

    // 14.3 resize array: set newLength <,> oldLength and fill expand space by
    // element

    public static boolean[] resize(boolean[] array, int newLength, boolean element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static byte[] resize(byte[] array, int newLength, byte element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static char[] resize(char[] array, int newLength, char element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static short[] resize(short[] array, int newLength, short element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static int[] resize(int[] array, int newLength, int element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static long[] resize(long[] array, int newLength, long element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static float[] resize(float[] array, int newLength, float element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static double[] resize(double[] array, int newLength, double element) {
        return ArrayPrimitiveHelper._resize(array, newLength, element);
    }

    public static <T> T[] resize(T[] array, int newLength, T element) {
        return ArrayHelper._resize(array, newLength, element);
    }

    //// 15.1 toArray: Primitive[] -> primitive[]

    public static boolean[] toArray(Boolean[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static byte[] toArray(Byte[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static char[] toArray(Character[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static short[] toArray(Short[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static int[] toArray(Integer[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static long[] toArray(Long[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static float[] toArray(Float[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static double[] toArray(Double[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    //// 15.2 toArray: primitive[] -> Primitive[]

    public static Boolean[] toArray(boolean[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static Byte[] toArray(byte[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static Character[] toArray(char[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static Short[] toArray(short[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static Integer[] toArray(int[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static Long[] toArray(long[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static Float[] toArray(float[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    public static Double[] toArray(double[] array) {
        return ArrayPrimitiveHelper._toArray(array);
    }

    //// 15.3 Collection<Primitive> -> Primitive[]

    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[0]);
    }

    public static Boolean[] toBooleanArray(Collection<Boolean> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Boolean[0]);
    }

    public static Byte[] toByteArray(Collection<Byte> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Byte[0]);
    }

    public static Character[] toCharacterArray(Collection<Character> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Character[0]);
    }

    public static Short[] toShortArray(Collection<Short> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Short[0]);
    }

    public static Integer[] toIntegerArray(Collection<Integer> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Integer[0]);
    }

    public static Long[] toLongArray(Collection<Long> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Long[0]);
    }

    public static Float[] toFloatArray(Collection<Float> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Float[0]);
    }

    public static Double[] toDoubleArray(Collection<Double> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new Double[0]);
    }

    //// 15.4 Collection<Primitive> -> primitive[]

    public static boolean[] toPrimitiveBooleanArray(Collection<Boolean> collection) {
        return ArrayPrimitiveHelper._toPrimitiveBooleanArray(collection);
    }

    public static byte[] toPrimitiveByteArray(Collection<Byte> collection) {
        return ArrayPrimitiveHelper._toPrimitiveByteArray(collection);
    }

    public static char[] toPrimitiveCharArray(Collection<Character> collection) {
        return ArrayPrimitiveHelper._toPrimitiveCharArray(collection);
    }

    public static short[] toPrimitiveShortArray(Collection<Short> collection) {
        return ArrayPrimitiveHelper._toPrimitiveShortArray(collection);
    }

    public static int[] toPrimitiveIntArray(Collection<Integer> collection) {
        return ArrayPrimitiveHelper._toPrimitiveIntArray(collection);
    }

    public static long[] toPrimitiveLongArray(Collection<Long> collection) {
        return ArrayPrimitiveHelper._toPrimitiveLongArray(collection);
    }

    public static float[] toPrimitiveFloatArray(Collection<Float> collection) {
        return ArrayPrimitiveHelper._toPrimitiveFloatArray(collection);
    }

    public static double[] toPrimitiveDoubleArray(Collection<Double> collection) {
        return ArrayPrimitiveHelper._toPrimitiveDoubleArray(collection);
    }

    //// 15.5

    // NonJS
    public static <T> T[] toArray(Collection<T> collection, Class<?> componentType) {
        if (collection == null) {
            return null;
        }
        return (T[]) collection.toArray((T[]) newEmptyArray(componentType));
    }

    //// 16.1 asArray

    public static boolean[] asArray(boolean... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    public static byte[] asArray(byte... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    public static char[] asArray(char... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    public static short[] asArray(short... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    public static int[] asArray(int... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    public static long[] asArray(long... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    public static float[] asArray(float... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    public static double[] asArray(double... array) {
        return ArrayPrimitiveHelper._asArray(array);
    }

    ////

    public static String[] asArray(String... array) {
        return ArrayHelper._asArray(array);
    }

    public static Object[] asObjectArray(Object... array) {
        return ArrayHelper._asObjectArray(array);
    }

    public static <T> T[] asArray(Class<T> componentType, T... array) {
        return ArrayHelper._asArray(componentType, array);
    }

    //// 17.1 toSortArray

    public static <T> T[] toSortArray(T[] array) {
        return toSortArray(array, null);
    }

    public static <T> T[] toSortArray(T[] array, Comparator<? super T> comparator) {
        if (array == null) {
            return null;
        }
        T[] result = copy(array);
        sort(result, comparator);
        return result;
    }

    //// 17.2 toFilterArray

    public static <T> T[] toFilterArray(T[] array, Predicate<T> filter) {
        if (array == null) {
            return null;
        }
        T[] result = filter(array, filter);
        return result;
    }

    //// 17.3 toCriteriaArray

    public static <T> T[] toCriteriaArray(T[] array, Predicate<T> filter,
            Comparator<? super T> sorter/* , boolean isDistinct */) {
        if (array == null) {
            return null;
        }
        T[] result = filter(array, filter);
        sort(result, sorter);
        return result;
    }

    //// 17.4 toReverseArray

    public static <T> T[] toReverseArray(T[] array) {
        if (array == null) {
            return null;
        }
        T[] result = copy(array);
        reverse(result);
        return result;
    }

    //// 18.1

    private static <T> List<T> toList(T[] array) {
        return CollectionLib.toList(array);
    }

    //// 19.1 random<Type>Array

    // int

    public static int[] randomIntArray(int lenght) {
        return ArrayRandomHelper._randomIntArray(lenght);
    }

    public static int[] randomIntArray(int lenght, int max) {
        return ArrayRandomHelper._randomIntArray(lenght, max);
    }

    public static int[] randomIntArray(int lenght, int min, int max) {
        return ArrayRandomHelper._randomIntArray(lenght, min, max);
    }

    public static int[] randomIntArray(int lenght, Function<Integer, Integer> function) {
        return ArrayRandomHelper._randomIntArray(lenght, function);
    }

    public static int[] randomFloatArray(int lenght, int max, Function<Integer, Integer> function) {
        return ArrayRandomHelper._randomIntArray(lenght, max, function);
    }

    public static int[] randomIntArray(int lenght, int min, int max, Function<Integer, Integer> function) {
        return ArrayRandomHelper._randomIntArray(lenght, min, max, function);
    }

    // float

    public static float[] randomFloatArray(int lenght) {
        return ArrayRandomHelper._randomFloatArray(lenght);
    }

    public static float[] randomFloatArray(int lenght, float max) {
        return ArrayRandomHelper._randomFloatArray(lenght, max);
    }

    public static float[] randomFloatArray(int lenght, float min, float max) {
        return ArrayRandomHelper._randomFloatArray(lenght, min, max);
    }

    public static float[] randomFloatArray(int lenght, Function<Float, Float> function) {
        return ArrayRandomHelper._randomFloatArray(lenght, function);
    }

    public static float[] randomFloatArray(int lenght, float max, Function<Float, Float> function) {
        return ArrayRandomHelper._randomFloatArray(lenght, max, function);
    }

    public static float[] randomFloatArray(int lenght, float min, float max, Function<Float, Float> function) {
        return ArrayRandomHelper._randomFloatArray(lenght, min, max, function);
    }

    // double

    public static double[] randomDoubleArray(int lenght) {
        return ArrayRandomHelper._randomDoubleArray(lenght);
    }

    public static double[] randomDoubleArray(int lenght, double max) {
        return ArrayRandomHelper._randomDoubleArray(lenght, max);
    }

    public static double[] randomDoubleArray(int lenght, double min, double max) {
        return ArrayRandomHelper._randomDoubleArray(lenght, min, max);
    }

    public static double[] randomDoubleArray(int lenght, Function<Double, Double> function) {
        return ArrayRandomHelper._randomDoubleArray(lenght, function);
    }

    public static double[] randomDoubleArray(int lenght, double max, Function<Double, Double> function) {
        return ArrayRandomHelper._randomDoubleArray(lenght, max, function);
    }

    public static double[] randomDoubleArray(int lenght, double min, double max, Function<Double, Double> function) {
        return ArrayRandomHelper._randomDoubleArray(lenght, min, max, function);
    }

    //// 20.1 read: String -> array

    public static byte[] readByteArrayFromString(String line) {
        return NumLib.readByteArrayFromString(line);
    }

    public static short[] readShortArrayFromString(String line) {
        return NumLib.readShortArrayFromString(line);
    }

    public static int[] readIntArrayFromString(String line) {
        return NumLib.readIntArrayFromString(line);
    }

    public static long[] readLongArrayFromString(String line) {
        return NumLib.readLongArrayFromString(line);
    }

    public static float[] readFloatArrayFromString(String line) {
        return NumLib.readFloatArrayFromString(line);
    }

    public static double[] readDoubleArrayFromString(String line) {
        return NumLib.readDoubleArrayFromString(line);
    }

    //// 20.2 write: array -> String

    public static String writeByteArrayToString(byte[] array) {
        return NumLib.writeByteArrayToString(array);
    }

    public static String writeShortArrayToString(short[] array) {
        return NumLib.writeShortArrayToString(array);
    }

    public static String writeIntArrayToString(int[] array) {
        return NumLib.writeIntArrayToString(array);
    }

    public static String writeLongArrayToString(long[] array) {
        return NumLib.writeLongArrayToString(array);
    }

    public static String writeFloatArrayToString(float[] array) {
        return NumLib.writeFloatArrayToString(array);
    }

    public static String writeDoubleArrayToString(double[] array) {
        return NumLib.writeDoubleArrayToString(array);
    }

    //// 21.1 toString

    public static String toString(byte[] array) {
        return ArrayStringHelper._toString(array);
    }

    public static String toString(byte[] array, boolean hasQuote) {
        return ArrayStringHelper._toString(array, hasQuote);
    }

    public static String toString(byte[] array, String separator) {
        return ArrayStringHelper._toString(array, separator);
    }

    public static String toString(byte[] array, boolean hasQuote, String separator) {
        return ArrayStringHelper._toString(array, hasQuote, separator);
    }

    //

    public static String toString(short[] array) {
        return ArrayStringHelper._toString(array);
    }

    public static String toString(short[] array, boolean hasQuote) {
        return ArrayStringHelper._toString(array, hasQuote);
    }

    public static String toString(short[] array, String separator) {
        return ArrayStringHelper._toString(array, separator);
    }

    public static String toString(short[] array, boolean hasQuote, String separator) {
        return ArrayStringHelper._toString(array, hasQuote, separator);
    }

    //

    public static String toString(int[] array) {
        return ArrayStringHelper._toString(array);
    }

    public static String toString(int[] array, boolean hasQuote) {
        return ArrayStringHelper._toString(array, hasQuote);
    }

    public static String toString(int[] array, String separator) {
        return ArrayStringHelper._toString(array, separator);
    }

    public static String toString(int[] array, boolean hasQuote, String separator) {
        return ArrayStringHelper._toString(array, hasQuote, separator);
    }

    //

    public static String toString(long[] array) {
        return ArrayStringHelper._toString(array);
    }

    public static String toString(long[] array, boolean hasQuote) {
        return ArrayStringHelper._toString(array, hasQuote);
    }

    public static String toString(long[] array, String separator) {
        return ArrayStringHelper._toString(array, separator);
    }

    public static String toString(long[] array, boolean hasQuote, String separator) {
        return ArrayStringHelper._toString(array, hasQuote, separator);
    }

    //

    public static String toString(float[] array) {
        return ArrayStringHelper._toString(array);
    }

    public static String toString(float[] array, boolean hasQuote) {
        return ArrayStringHelper._toString(array, hasQuote);
    }

    public static String toString(float[] array, String separator) {
        return ArrayStringHelper._toString(array, separator);
    }

    public static String toString(float[] array, boolean hasQuote, String separator) {
        return ArrayStringHelper._toString(array, hasQuote, separator);
    }

    //

    public static String toString(double[] array) {
        return ArrayStringHelper._toString(array);
    }

    public static String toString(double[] array, boolean hasQuote) {
        return ArrayStringHelper._toString(array, hasQuote);
    }

    public static String toString(double[] array, String separator) {
        return ArrayStringHelper._toString(array, separator);
    }

    public static String toString(double[] array, boolean hasQuote, String separator) {
        return ArrayStringHelper._toString(array, hasQuote, separator);
    }

    //// 22.1 rangeDoubleArray

    public static double[] rangeDoubleArray(double min, double max) {
        return rangeDoubleArray(min, max, 1.0d);
    }

    public static double[] rangeDoubleArray(double min, double max, double step) {
        if (min > max) {
            throw new IllegalArgumentException("min > max");
        }
        double range = max - min;
        if (step > range) {
            throw new IllegalArgumentException("step > (max - min)");
        }
        int count = (int) ((max - min) / step) + 1;
        double[] result = new double[count];

        double value = min;
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                value += step;
            }
            result[i] = value;
        }
        return result;
    }

    //// 22.2 pointDoubleArray

    public static double[] pointDoubleArray(double min, double max, int count) {
        if (min > max) {
            throw new IllegalArgumentException("min > max");
        }
        // double range = max - min;
        if (count < 2) {
            throw new IllegalArgumentException("count < 2");
        }
        double step = (max - min) / (count - 1);
        double[] result = new double[count];

        result[0] = min;
        result[count - 1] = max;

        double value = min;
        for (int i = 1; i < count - 1; i++) {
            value += step;
            result[i] = value;
        }
        return result;
    }

    //// 22.3 evaluateDoubleArray

    public static double[] evaluateDoubleArray(double[] x, Function<Double, Double> function) {
        if (x == null) {
            return null;
        }
        if (x.length == 0) {
            return new double[0];
        }
        if (function == null) {
            return copy(x);
        }

        double[] result = new double[x.length];
        Double value = null;
        for (int i = 0; i < x.length; i++) {
            value = function.apply(x[i]);
            if (value != null) {
                result[i] = value;
            }
        }
        return result;
    }

}
