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

package plazma.lib.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import plazma.lib.obj.ObjLib;

public class ArrayStreamHelper {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    // 1.1
    // - sort(array) (+)
    // - filter(array) (+)
    // - distinct(array[, number]) (+)
    // - limit(number) (?)
    // - map(array, function)
    // - reduce(array, function(x, y))

    // 1.2
    // - union(array)
    // - intersection
    // - cartesian()

    //

    public static final boolean[] EMPTY_BOOLEAN_ARRAY = ObjLib.EMPTY_BOOLEAN_ARRAY;

    public static final byte[] EMPTY_BYTE_ARRAY = ObjLib.EMPTY_BYTE_ARRAY;

    public static final char[] EMPTY_CHAR_ARRAY = ObjLib.EMPTY_CHAR_ARRAY;

    public static final short[] EMPTY_SHORT_ARRAY = ObjLib.EMPTY_SHORT_ARRAY;

    public static final int[] EMPTY_INT_ARRAY = ObjLib.EMPTY_INT_ARRAY;

    public static final long[] EMPTY_LONG_ARRAY = ObjLib.EMPTY_LONG_ARRAY;

    public static final float[] EMPTY_FLOAT_ARRAY = ObjLib.EMPTY_FLOAT_ARRAY;

    public static final double[] EMPTY_DOUBLE_ARRAY = ObjLib.EMPTY_DOUBLE_ARRAY;

    private ArrayStreamHelper() {
    }

    ////

    private static <T> Comparator<T> safeComparator2(Comparator<? super T> comparator) {
        return safeComparator(comparator == null ? new ObjLib.UComparator2<>() : comparator);
    }

    private static <T> Comparator<T> safeComparator(Comparator<? super T> comparator) {
        return ObjLib.safeComparator(comparator);
    }

    ////

    static void _sort(boolean[] array) {
        if (array == null) {
            return;
        }
        // TODO: Bad solution?
        int count = 0; // count 'false'
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) {
                count++;
            }
        }
        if (count == array.length) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = !(i < count);
        }
    }

    /*
     * static void _sort(byte[] array) { if (array == null) { return; }
     * Arrays.sort(array); }
     * 
     * static void _sort(char[] array) { if (array == null) { return; }
     * Arrays.sort(array); }
     * 
     * static void _sort(short[] array) { if (array == null) { return; }
     * Arrays.sort(array); }
     * 
     * static void _sort(int[] array) { if (array == null) { return; }
     * Arrays.sort(array); }
     * 
     * static void _sort(long[] array) { if (array == null) { return; }
     * Arrays.sort(array); }
     * 
     * static void _sort(float[] array) { if (array == null) { return; }
     * Arrays.sort(array); }
     * 
     * static void _sort(double[] array) { if (array == null) { return; }
     * Arrays.sort(array); }
     */

    ////

    static void _sort(boolean[] array, int fromIndex, int toIndex) {
        if (array == null) {
            return;
        }

        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > array.length) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }

        // TODO: Bad solution?
        int count = 0; // count 'false'
        for (int i = fromIndex; i < toIndex; i++) {
            if (!array[i]) {
                count++;
            }
        }
        if (count == toIndex - fromIndex) {
            return;
        }
        for (int i = fromIndex; i < toIndex; i++) {
            array[i] = !(i - fromIndex < count);
        }
    }

    /*
     * static void _sort(byte[] array, int fromIndex, int toIndex) { if (array ==
     * null) { return; } Arrays.sort(array, fromIndex, toIndex); }
     * 
     * static void _sort(char[] array, int fromIndex, int toIndex) { if (array ==
     * null) { return; } Arrays.sort(array, fromIndex, toIndex); }
     * 
     * static void _sort(short[] array, int fromIndex, int toIndex) { if (array ==
     * null) { return; } Arrays.sort(array, fromIndex, toIndex); }
     * 
     * static void _sort(int[] array, int fromIndex, int toIndex) { if (array ==
     * null) { return; } Arrays.sort(array, fromIndex, toIndex); }
     * 
     * static void _sort(long[] array, int fromIndex, int toIndex) { if (array ==
     * null) { return; } Arrays.sort(array, fromIndex, toIndex); }
     * 
     * static void _sort(float[] array, int fromIndex, int toIndex) { if (array ==
     * null) { return; } Arrays.sort(array, fromIndex, toIndex); }
     * 
     * static void _sort(double[] array, int fromIndex, int toIndex) { if (array ==
     * null) { return; } Arrays.sort(array, fromIndex, toIndex); }
     */

    ////

    static void _sort(Object[] array) {
        if (array == null) {
            return;
        }
        Arrays.sort(array, safeComparator2(null));
    }

    static void _sort(Object[] array, int fromIndex, int toIndex) {
        if (array == null) {
            return;
        }
        _sort(array, fromIndex, toIndex, null);
    }

    static <T> void _sort(T[] array, int fromIndex, int toIndex, Comparator<? super T> comparator) {
        if (array == null) {
            return;
        }
        Arrays.sort(array, fromIndex, toIndex, safeComparator2(comparator));
    }

    static <T> void _sort(T[] array, Comparator<? super T> comparator) {
        if (array == null) {
            return;
        }
        Arrays.sort(array, safeComparator2(comparator));
    }

    //// filter

    static <T> T[] _filter(T[] array, Predicate<T> filter) {
        if (array == null) {
            return null;
        }
        // no filter - return all
        if (filter == null) {
            return Arrays.copyOf(array, array.length);
        }

        List<T> list = new ArrayList<T>();
        T e = null;
        for (int i = 0; i < array.length; i++) {
            e = array[i];
            if (filter.test(e)) {
                list.add(e);
            }
        }
        return (T[]) list.toArray((T[]) Arrays.copyOf(array, 0));
    }

    //// distinct

    // experimental
    static int[] _distinct2(int[] array) {
        if (array == null) {
            return null;
        }
        return IntStream.of(array).distinct().toArray();
    }

    //

    static <T> T[] _distinct(T[] array) {
        if (array == null) {
            return null;
        }
        Set<T> set = new LinkedHashSet<T>();
        T e = null;
        for (int i = 0; i < array.length; i++) {
            e = array[i];
            set.add(e);
        }
        return (T[]) set.toArray((T[]) Arrays.copyOf(array, 0));
    }

}
