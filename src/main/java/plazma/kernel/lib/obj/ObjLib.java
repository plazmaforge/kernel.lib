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

package plazma.kernel.lib.obj;

import java.util.Comparator;

public class ObjLib {

    // Functions
    
    /////////////////////////////////////////////////////////////////////////////////
    // 1.1 equals
    // - boolean equals(Object o1, Object o2)
    // - boolean equalsNull(Object o1, Object o2)			- Returns true if o1 and o2 are null
    //
    // 1.2 compare    
    // - <T extends Comparable<T>> int compare(T o1, T o2)
    // - <T> Comparator<T> safeComparator(Comparator<? super T> c)	- Nulls first comparator
    //
    // 1.3
    // - <T> T defValue(T def, boolean useDef)
    
    // TODO: min(), max()
    
    
    public static final int DEFAULT_RADIX = 10;

    //

    public static final boolean DEFAULT_BOOLEAN = false;

    public static final byte DEFAULT_BYTE = 0;

    public static final char DEFAULT_CHAR = 0;

    public static final short DEFAULT_SHORT = 0;

    public static final int DEFAULT_INT = 0;

    public static final long DEFAULT_LONG = 0L;

    public static final float DEFAULT_FLOAT = 0F;

    public static final double DEFAULT_DOUBLE = 0D;

    //

    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    public static final char[] EMPTY_CHAR_ARRAY = new char[0];

    public static final short[] EMPTY_SHORT_ARRAY = new short[0];

    public static final int[] EMPTY_INT_ARRAY = new int[0];

    public static final long[] EMPTY_LONG_ARRAY = new long[0];

    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];

    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

    private ObjLib() {
    }

    //// static classes

    public static class UComparator1<T extends Comparable<? super T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }

    }

    public static class UComparator2<T> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            if (o1 instanceof Comparable && o2 instanceof Comparable) {
                return ((Comparable) o1).compareTo((Comparable) o2);
            }
            return 0;
        }

    }

    //// 1.1 equals

    public static boolean equals(Object o1, Object o2) {
        // Objects.equals(a, b)
        // if (o1 == null && o2 == null) {
        // return true;
        // }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1 == o2) {
            return true;
        }
        return o1.equals(o2);
    }

    public static boolean equalsNull(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1 == o2) {
            return true;
        }
        return o1.equals(o2);
    }

    //// 1.2 compare

    public static <T extends Comparable<T>> int compare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    }

    public static <T> Comparator<T> safeComparator(Comparator<? super T> c) {
        return Comparator.nullsFirst(c);
    }

    //// 1.3

    public static <T> T defValue(T def, boolean useDef) {
        return useDef ? def : null;
    }
    
}
