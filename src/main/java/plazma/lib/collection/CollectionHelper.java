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

package plazma.lib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import plazma.lib.obj.ObjLib;

public class CollectionHelper {

    private CollectionHelper() {
    }

    // create default list
    private static <T> List<T> createList(Class<T> klass) {
        return new ArrayList<T>();
    }

    // create default list
    private static <T> List<T> createList(Collection<T> collection) {
        return new ArrayList<T>();
    }

    // create default list
    private static <T> List<T> createList(T[] array) {
        return new ArrayList<T>();
    }

    // create default list
    private static <T> Set<T> createSet(Class<T> klass) {
        return new LinkedHashSet<T>();
    }

    // create default list
    private static <T> Set<T> createSet(Collection<T> collection) {
        return new LinkedHashSet<T>();
    }

    // create default list
    private static <T> Set<T> createSet(T[] array) {
        return new LinkedHashSet<T>();
    }

    ////

    private static boolean equalsNull(Object o1, Object o2) {
        return ObjLib.equalsNull(o1, o2);
    }

    private static <T extends Comparable<T>> int compare(T o1, T o2) {
        return ObjLib.compare(o1, o2);
    }

    ////

    static <T> void _removeAll(Collection<T> collection, Collection<T> elements) {
        if (elements == null || elements.size() == 0) {
            return;
        }
        collection.removeAll(elements);
    }

    static <T> void _removeAll(Collection<T> collection, T[] elements) {
        if (elements == null || elements.length == 0) {
            return;
        }
        // TODO: Bad solution
        Iterator<T> itr = collection.iterator();
        while (itr.hasNext()) {
            T e1 = itr.next();
            for (int i = 0; i < elements.length; i++) {
                T e2 = elements[i];
                if (equalsNull(e1, e2)) {
                    itr.remove();
                    break;
                }
            }
        }
    }

    ////
    static <T> boolean _replaceAll(List<T> list, T oldValue, T newValue) {
        return Collections.replaceAll(list, oldValue, newValue);
    }

    static <T> boolean[] _replaceAll(List<T> list, T[] oldValues, T[] newValues) {
        if (oldValues == null) {
            // TODO: Exception
            return new boolean[0];

        }
        if (newValues == null) {
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
        ;
        for (int i = 0; i < size; i++) {
            oldValue = oldValues[i];
            newValue = newValues[i];
            // if (eqn(oldVal, newVal)) {
            // oldVal = newVal: why replace?
            // continue;
            // }
            if (_replaceAll(list, oldValue, newValue)) {
                result[i] = true;
            }
        }
        return result;
    }

    ////

    static <T> int _count(Collection<T> collection, T value, boolean eq) {
        if (collection == null) {
            return 0;
        }
        int size = collection.size();
        if (size == 0) {
            return 0;
        }
        int count = 0;
        Iterator<T> itr = collection.iterator();
        boolean compare = false;
        while (itr.hasNext()) {
            compare = equalsNull(itr.next(), value);
            if ((eq && compare) || (!eq && !compare)) {
                count++;
            }
        }
        return count;
    }

    ////

    static <T extends Comparable<T>> T _min(Collection<T> collection, T def, boolean useDef) {
        // public static <T extends Object & Comparable<? super T>> T min(Collection<?
        // extends T> collection) {
        if (collection == null) {
            return null;
        }
        Iterator<? extends T> itr = collection.iterator();
        T result = useDef ? def : itr.next();
        while (itr.hasNext()) {
            T next = itr.next();

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

    static <T extends Comparable<T>> T _max(Collection<T> collection, T def, boolean useDef) {
        if (collection == null) {
            return null;
        }
        Iterator<? extends T> itr = collection.iterator();
        T result = useDef ? def : itr.next();
        while (itr.hasNext()) {
            T next = itr.next();

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

    ////

    static <T> void _reverse(List<T> list) {
        if (list == null) {
            return;
        }
        int size = list.size();
        // no reverse for size = 0 or 1
        if (size < 2) {
            return;
        }
        int count = size / 2;
        T e = null;
        int j = 0;
        for (int i = 0; i < count; i++) {
            j = size - 1 - i;

            // swap: i <-> j
            e = list.get(i);
            list.set(i, list.get(j));
            list.set(j, e);
        }
    }

    ////

    static <T> void _next(ListIterator<? super T> itr, int size) {
        for (int i = 0; i < size; i++) {
            itr.next();
        }
    }

    static <T> void _fill(ListIterator<? super T> itr, T value, int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex; i++) {
            itr.next();
            itr.set(value);
        }
    }

    static <T> void _fill(List<? super T> list, T value, int fromIndex, int toIndex) {
        for (int i = fromIndex; i < toIndex; i++) {
            list.set(i, value);
        }
    }

    ////

    static <T> List<T> _toList(Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        List<T> list = createList(collection);
        list.addAll(collection);
        return list;
    }

    static <T> List<T> _toList(T[] array) {
        if (array == null) {
            return null;
        }
        List<T> list = createList(array);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;

        // TODO: For readonly
        // return Arrays.asList(array);
    }

    //// array[] -> List<Array>

    static List<Boolean> _toList(boolean[] array) {
        if (array == null) {
            return null;
        }
        List<Boolean> list = createList(Boolean.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Byte> _toList(byte[] array) {
        if (array == null) {
            return null;
        }
        List<Byte> list = createList(Byte.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Character> _toList(char[] array) {
        if (array == null) {
            return null;
        }
        List<Character> list = createList(Character.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Short> _toList(short[] array) {
        if (array == null) {
            return null;
        }
        List<Short> list = createList(Short.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Integer> _toList(int[] array) {
        if (array == null) {
            return null;
        }
        List<Integer> list = createList(Integer.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Long> _toList(long[] array) {
        if (array == null) {
            return null;
        }
        List<Long> list = createList(Long.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Float> _toList(float[] array) {
        if (array == null) {
            return null;
        }
        List<Float> list = createList(Float.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Double> _toList(double[] array) {
        if (array == null) {
            return null;
        }
        List<Double> list = createList(Double.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    //// Array[] -> List<Array>

    static List<Boolean> _toList(Boolean[] array) {
        if (array == null) {
            return null;
        }
        List<Boolean> list = createList(Boolean.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Byte> _toList(Byte[] array) {
        if (array == null) {
            return null;
        }
        List<Byte> list = createList(Byte.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Character> _toList(Character[] array) {
        if (array == null) {
            return null;
        }
        List<Character> list = createList(Character.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Short> _toList(Short[] array) {
        if (array == null) {
            return null;
        }
        List<Short> list = createList(Short.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Integer> _toList(Integer[] array) {
        if (array == null) {
            return null;
        }
        List<Integer> list = createList(Integer.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Long> _toList(Long[] array) {
        if (array == null) {
            return null;
        }
        List<Long> list = createList(Long.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Float> _toList(Float[] array) {
        if (array == null) {
            return null;
        }
        List<Float> list = createList(Float.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static List<Double> _toList(Double[] array) {
        if (array == null) {
            return null;
        }
        List<Double> list = createList(Double.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    //// toSet

    static <T> Set<T> _toSet(Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        Set<T> set = createSet(collection);
        set.addAll(collection);
        return set;
    }

    static <T> Set<T> _toSet(T[] array) {
        if (array == null) {
            return null;
        }
        Set<T> list = createSet(array);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    //// array[] -> Set<Array>

    static Set<Boolean> _toSet(boolean[] array) {
        if (array == null) {
            return null;
        }
        Set<Boolean> list = createSet(Boolean.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Byte> _toSet(byte[] array) {
        if (array == null) {
            return null;
        }
        Set<Byte> list = createSet(Byte.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Character> _toSet(char[] array) {
        if (array == null) {
            return null;
        }
        Set<Character> list = createSet(Character.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Short> _toSet(short[] array) {
        if (array == null) {
            return null;
        }
        Set<Short> list = createSet(Short.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Integer> _toSet(int[] array) {
        if (array == null) {
            return null;
        }
        Set<Integer> list = createSet(Integer.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Long> _toSet(long[] array) {
        if (array == null) {
            return null;
        }
        Set<Long> list = createSet(Long.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Float> _toSet(float[] array) {
        if (array == null) {
            return null;
        }
        Set<Float> list = createSet(Float.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Double> _toSet(double[] array) {
        if (array == null) {
            return null;
        }
        Set<Double> list = createSet(Double.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    ////

    //// Array[] -> Set<Array>

    static Set<Boolean> _toSet(Boolean[] array) {
        if (array == null) {
            return null;
        }
        Set<Boolean> list = createSet(Boolean.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Byte> _toSet(Byte[] array) {
        if (array == null) {
            return null;
        }
        Set<Byte> list = createSet(Byte.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Character> _toSet(Character[] array) {
        if (array == null) {
            return null;
        }
        Set<Character> list = createSet(Character.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Short> _toSet(Short[] array) {
        if (array == null) {
            return null;
        }
        Set<Short> list = createSet(Short.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Integer> _toSet(Integer[] array) {
        if (array == null) {
            return null;
        }
        Set<Integer> list = createSet(Integer.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Long> _toSet(Long[] array) {
        if (array == null) {
            return null;
        }
        Set<Long> list = createSet(Long.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Float> _toSet(Float[] array) {
        if (array == null) {
            return null;
        }
        Set<Float> list = createSet(Float.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    static Set<Double> _toSet(Double[] array) {
        if (array == null) {
            return null;
        }
        Set<Double> list = createSet(Double.class);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    ////

    static List<Boolean> _asList(boolean... array) {
        return _toList(array);
    }

    static List<Byte> _asList(byte... array) {
        return _toList(array);
    }

    static List<Character> _asList(char... array) {
        return _toList(array);
    }

    static List<Short> _asList(short... array) {
        return _toList(array);
    }

    static List<Integer> _asList(int... array) {
        return _toList(array);
    }

    static List<Long> _asList(long... array) {
        return _toList(array);
    }

    static List<Float> _asList(float... array) {
        return _toList(array);
    }

    static List<Double> _asList(double... array) {
        return _toList(array);
    }

    ////

    static <T> List<T> _asList(T... array) {
        if (array == null) {
            return null;
        }
        List<T> list = createList(array);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    ////

    static Set<Boolean> _asSet(boolean... array) {
        return _toSet(array);
    }

    static Set<Byte> _asSet(byte... array) {
        return _toSet(array);
    }

    static Set<Character> _asSet(char... array) {
        return _toSet(array);
    }

    static Set<Short> _asSet(short... array) {
        return _toSet(array);
    }

    static Set<Integer> _asSet(int... array) {
        return _toSet(array);
    }

    static Set<Long> _asSet(long... array) {
        return _toSet(array);
    }

    static Set<Float> _asSet(float... array) {
        return _toSet(array);
    }

    static Set<Double> _asSet(double... array) {
        return _toSet(array);
    }

    ////

    static <T> Set<T> _asSet(T... array) {
        if (array == null) {
            return null;
        }
        Set<T> list = createSet(array);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

}
