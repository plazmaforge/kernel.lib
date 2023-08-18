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

package plazma.kernel.lib.convert;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import plazma.kernel.lib.array.ArrayLib;
import plazma.kernel.lib.collection.CollectionLib;

public class ConvertLib {

    // Functions:
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1.1 toArray(...)
    //
    // - toArray(<Type>[] array			- Array[] -> array[]
    // - toArray(<primitive>[] array		- array[] -> Array[]
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 2.1 toList(...)
    //
    // - toList(Collection<T> collection)	- Collection<T> -> List<T>
    // - toList(T[] array)			- T[] -> List<T>
    //
    // 2.2 toSortList(...)
    //
    // - toSortList(Collection<T> collection)
    // - toSortList(Collection<T> collection, Comparator<? super T> comparator)
    //
    // 2.3 toFilterList(...)
    //
    // - toFilterList(Collection<T> collection, Predicate<T> filter)
    //
    // 2.4 toCriteriaList(...)
    //
    // - toCriteriaList(Collection<T> collection, Predicate<T> filter, Comparator<? super T> sorter, boolean isDistinct)
    //
    // 2.5
    // 
    // - toList(<primitive>[] array)		- array[] -> List<Array>
    // - toList(<Type>[] array)			- Array[] -> List<Array>

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 3.1 toSet(...)
    //
    // - toSet(Collection<T> collection)	- Collection<T> -> Set<T>
    // - toSet(T[] array)			- T[] -> Set<T>
    //
    // 3.2 toSet(...)
    //
    // - toSet(<primitive[] array)		- array[] -> Set<Array>
    // - toSet(<Type>[] array)			- Array[] -> Set<Array>
    //
   	
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 4.1 toArray(...)
    //
    // - toArray(Collection<T> collection, Class<?> componentType)
    //
    // 4.2
    // 
    // - toSortArray(T[] array)
    // - toSortArray(T[] array, Comparator<? super T> comparator)
    //
    // 4.3
    //
    // - toFilterArray(T[] array, Predicate<T> filter)
    //
    // 4.4
    //
    // - toCriteriaArray(T[] array, Predicate<T> filter, Comparator<? super T> sorter)
    //
    // 4.5
    //
    // - to<Type>Array(Collection<Type> collection)
    //
    // 4.
    //
    // - toPrimitive<Type>Array(Collection<Type> collection)
    //
    
    /////////////////////////////////////////////////////////////////////////////////
    //    
    // 5.1
    // 
    // - asArray(<type>... array)		- <type>... -> <type>[]

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 6.1 asArray(...), asObject(...)
    //
    // - asArray(String... array)
    // - asObjectArray(Object... array)
    // - asArray(Class<T> componentType, T... array)
    //
    // - asList(T... array)
    // - asSet(<type>... array)
    //
    // - asSet(T... array)
    
    ///
    
    private ConvertLib() {
    }

    //// 1.1
    // Array[] -> array[]

    public static boolean[] toArray(Boolean[] array) {
        return ArrayLib.toArray(array);
    }

    public static byte[] toArray(Byte[] array) {
        return ArrayLib.toArray(array);
    }

    public static char[] toArray(Character[] array) {
        return ArrayLib.toArray(array);
    }

    public static short[] toArray(Short[] array) {
        return ArrayLib.toArray(array);
    }

    public static int[] toArray(Integer[] array) {
        return ArrayLib.toArray(array);
    }

    public static float[] toArray(Float[] array) {
        return ArrayLib.toArray(array);
    }

    public static double[] toArray(Double[] array) {
        return ArrayLib.toArray(array);
    }

    //// array[] -> Array[]

    public static Boolean[] toArray(boolean[] array) {
        return ArrayLib.toArray(array);
    }

    public static Byte[] toArray(byte[] array) {
        return ArrayLib.toArray(array);
    }

    public static Character[] toArray(char[] array) {
        return ArrayLib.toArray(array);
    }

    public static Short[] toArray(short[] array) {
        return ArrayLib.toArray(array);
    }

    public static Integer[] toArray(int[] array) {
        return ArrayLib.toArray(array);
    }

    public static Float[] toArray(float[] array) {
        return ArrayLib.toArray(array);
    }

    public static Double[] toArray(double[] array) {
        return ArrayLib.toArray(array);
    }

    //// 2.1 toList

    public static <T> List<T> toList(Collection<T> collection) {
        return CollectionLib.toList(collection);
    }

    public static <T> List<T> toList(T[] array) {
        return CollectionLib.toList(array);
    }

    //// 2.2 toSortList

    public static <T> List<T> toSortList(Collection<T> collection) {
        return CollectionLib.toSortList(collection);
    }

    public static <T> List<T> toSortList(Collection<T> collection, Comparator<? super T> comparator) {
        return CollectionLib.toSortList(collection, comparator);
    }

    //// 2.3 toFilterList

    public static <T> List<T> toFilterList(Collection<T> collection, Predicate<T> filter) {
        return CollectionLib.toFilterList(collection, filter);
    }

    //// 2.4 toCriteriaList

    public static <T> List<T> toCriteriaList(Collection<T> collection, Predicate<T> filter,
            Comparator<? super T> sorter, boolean isDistinct) {
        return CollectionLib.toCriteriaList(collection, filter, sorter, isDistinct);
    }

    //// 2.5
    //// array[] -> List<Array>

    public static List<Boolean> toList(boolean[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Byte> toList(byte[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Character> toList(char[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Short> toList(short[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Integer> toList(int[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Long> toList(long[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Float> toList(float[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Double> toList(double[] array) {
        return CollectionLib.toList(array);
    }

    //// Array[] -> List<Array>

    public static List<Boolean> toList(Boolean[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Byte> toList(Byte[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Character> toList(Character[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Short> toList(Short[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Integer> toList(Integer[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Long> toList(Long[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Float> toList(Float[] array) {
        return CollectionLib.toList(array);
    }

    public static List<Double> toList(Double[] array) {
        return CollectionLib.toList(array);
    }

    //// 3.1 toSet

    public static <T> Set<T> toSet(Collection<T> collection) {
        return CollectionLib.toSet(collection);
    }

    public static <T> Set<T> toSet(T[] array) {
        return CollectionLib.toSet(array);
    }

    //// 3.2
    //// array[] -> Set<Array>

    public static Set<Boolean> toSet(boolean[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Byte> toSet(byte[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Character> toSet(char[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Short> toSet(short[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Integer> toSet(int[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Long> toSet(long[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Float> toSet(float[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Double> toSet(double[] array) {
        return CollectionLib.toSet(array);
    }

    //// Array[] -> Set<Array>

    public static Set<Boolean> toSet(Boolean[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Byte> toSet(Byte[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Character> toSet(Character[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Short> toSet(Short[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Integer> toSet(Integer[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Long> toSet(Long[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Float> toSet(Float[] array) {
        return CollectionLib.toSet(array);
    }

    public static Set<Double> toSet(Double[] array) {
        return CollectionLib.toSet(array);
    }

    //// 4.1

    // NonJS
    public static <T> T[] toArray(Collection<T> collection, Class<?> componentType) {
        return ArrayLib.toArray(collection, componentType);
    }

    //// 4.2 toSortArray

    public static <T> T[] toSortArray(T[] array) {
        return ArrayLib.toSortArray(array);
    }

    public static <T> T[] toSortArray(T[] array, Comparator<? super T> comparator) {
        return ArrayLib.toSortArray(array, comparator);
    }

    //// 4.3 toFilterArray

    public static <T> T[] toFilterArray(T[] array, Predicate<T> filter) {
        return ArrayLib.toFilterArray(array, filter);
    }

    //// 4.4 toCriteriaArray

    public static <T> T[] toCriteriaArray(T[] array, Predicate<T> filter, Comparator<? super T> sorter) {
        return ArrayLib.toCriteriaArray(array, filter, sorter);
    }

    //// 4.5 Collection<Array> -> Array[]

    public static String[] toStringArray(Collection<String> collection) {
        return ArrayLib.toStringArray(collection);
    }

    public static Boolean[] toBooleanArray(Collection<Boolean> collection) {
        return ArrayLib.toBooleanArray(collection);
    }

    public static Byte[] toByteArray(Collection<Byte> collection) {
        return ArrayLib.toByteArray(collection);
    }

    public static Character[] toCharacterArray(Collection<Character> collection) {
        return ArrayLib.toCharacterArray(collection);
    }

    public static Short[] toShortArray(Collection<Short> collection) {
        return ArrayLib.toShortArray(collection);
    }

    public static Integer[] toIntegerArray(Collection<Integer> collection) {
        return ArrayLib.toIntegerArray(collection);
    }

    public static Long[] toLongArray(Collection<Long> collection) {
        return ArrayLib.toLongArray(collection);
    }

    public static Float[] toFloatArray(Collection<Float> collection) {
        return ArrayLib.toFloatArray(collection);
    }

    public static Double[] toDoubleArray(Collection<Double> collection) {
        return ArrayLib.toDoubleArray(collection);
    }

    //// 4.6

    public static boolean[] toPrimitiveBooleanArray(Collection<Boolean> collection) {
        return ArrayLib.toPrimitiveBooleanArray(collection);
    }

    public static byte[] toPrimitiveByteArray(Collection<Byte> collection) {
        return ArrayLib.toPrimitiveByteArray(collection);
    }

    public static char[] toPrimitiveCharArray(Collection<Character> collection) {
        return ArrayLib.toPrimitiveCharArray(collection);
    }

    public static short[] toPrimitiveShortArray(Collection<Short> collection) {
        return ArrayLib.toPrimitiveShortArray(collection);
    }

    public static int[] toPrimitiveIntegerArray(Collection<Integer> collection) {
        return ArrayLib.toPrimitiveIntArray(collection);
    }

    public static long[] toPrimitiveLongArray(Collection<Long> collection) {
        return ArrayLib.toPrimitiveLongArray(collection);
    }

    public static float[] toPrimitiveFloatArray(Collection<Float> collection) {
        return ArrayLib.toPrimitiveFloatArray(collection);
    }

    public static double[] toPrimitiveDoubleArray(Collection<Double> collection) {
        return ArrayLib.toPrimitiveDoubleArray(collection);
    }

    //// 5.1

    public static boolean[] asArray(boolean... array) {
        return ArrayLib.asArray(array);
    }

    public static byte[] asArray(byte... array) {
        return ArrayLib.asArray(array);
    }

    public static char[] asArray(char... array) {
        return ArrayLib.asArray(array);
    }

    public static short[] asArray(short... array) {
        return ArrayLib.asArray(array);
    }

    public static int[] asArray(int... array) {
        return ArrayLib.asArray(array);
    }

    public static long[] asArray(long... array) {
        return ArrayLib.asArray(array);
    }

    public static float[] asArray(float... array) {
        return ArrayLib.asArray(array);
    }

    public static double[] asArray(double... array) {
        return ArrayLib.asArray(array);
    }

    //// 5.2

    public static String[] asArray(String... array) {
        return ArrayLib.asArray(array);
    }

    public static Object[] asObjectArray(Object... array) {
        return ArrayLib.asObjectArray(array);
    }

    public static <T> T[] asArray(Class<T> componentType, T... array) {
        return ArrayLib.asArray(componentType, array);
    }

    //// 6.1

    public static List<Boolean> asList(boolean... array) {
        return CollectionLib.asList(array);
    }

    public static List<Byte> asList(byte... array) {
        return CollectionLib.asList(array);
    }

    public static List<Character> asList(char... array) {
        return CollectionLib.asList(array);
    }

    public static List<Short> asList(short... array) {
        return CollectionLib.asList(array);
    }

    public static List<Integer> asList(int... array) {
        return CollectionLib.asList(array);
    }

    public static List<Long> asList(long... array) {
        return CollectionLib.asList(array);
    }

    public static List<Float> asList(float... array) {
        return CollectionLib.asList(array);
    }

    public static List<Double> asList(double... array) {
        return CollectionLib.asList(array);
    }

    ////

    public static <T> List<T> asList(T... array) {
        return CollectionLib.asList(array);
    }

    ////

    public static Set<Boolean> asSet(boolean... array) {
        return CollectionLib.asSet(array);
    }

    public static Set<Byte> asSet(byte... array) {
        return CollectionLib.asSet(array);
    }

    public static Set<Character> asSet(char... array) {
        return CollectionLib.asSet(array);
    }

    public static Set<Short> asSet(short... array) {
        return CollectionLib.asSet(array);
    }

    public static Set<Integer> asSet(int... array) {
        return CollectionLib.asSet(array);
    }

    public static Set<Long> asSet(long... array) {
        return CollectionLib.asSet(array);
    }

    public static Set<Float> asSet(float... array) {
        return CollectionLib.asSet(array);
    }

    public static Set<Double> asSet(double... array) {
        return CollectionLib.asSet(array);
    }

    ////

    public static <T> Set<T> asSet(T... array) {
        return CollectionLib.asSet(array);
    }
    
}
