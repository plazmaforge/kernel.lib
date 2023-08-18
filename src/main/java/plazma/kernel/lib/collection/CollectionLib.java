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

package plazma.kernel.lib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.function.Predicate;

import plazma.kernel.lib.ArithmeticContext;
import plazma.kernel.lib.ArithmeticEnvironment;
import plazma.kernel.lib.array.ArrayLib;
import plazma.kernel.lib.obj.ObjLib;

public class CollectionLib {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    // 1.1
    // - add({1, 3, 5}, {7, 9}): {1, 3, 5} + {7, 9} = {1, 3, 5, 7, 9}

    // 1.2
    // - sub({1, 3, 5, 7, 9}, {3, 7}): {1, 3, 5, 7, 9} - {3, 7} = {1, 5, 9}

    /////////////////////////////////////////////////////////////////////////////////
    // 2.1
    // - copy({1, 3, 5}): {1, 3, 5} = {1, 3, 5}
    // - copy({1, 3, 5}, 2): {1, 3, 5}, 2 = {1, 3}

    // 2.2
    // - copyRange({1, 3, 5}, 0, 2): {1, 3, 5}, 0, 2 = {1, 3}

    /////////////////////////////////////////////////////////////////////////////////
    // 3.1
    // - fill(list, "A"): {"A", "A", "A"}

    // 3.2
    // - addAll(list, {"B", "C"}): {"A", "A", "A", "B", "C"}

    // 3.3
    // - removeAll(list, {"A"}): {"B", "C"}

    // 3.4
    // - replaceAll(list, "C" , "W"): {"B", "W"}

    // - addElement(collection, element)
    // - removeElement(collection, element)

    // - getElement(List<T> list, int index)
    // - getElement(Map<K, T> map, K key)

    // - getValue(Map<K, V> map, K key)

    // - addElement(Map<K, T> map, K key, T element)
    // - addValue(Map<K, V> map, K key, V value)

    // - removeKey(Map<K, V> map, K key)

    /////////////////////////////////////////////////////////////////////////////////
    // 4.1
    // - isEmpty(collection)
    // - equals(collection1, collection2)
    // - contains(collection, value)

    // 4.2
    // - size(collection)

    // 4.3
    // - count(collection, "A")
    // - countNull(collection)
    // - countNotNull(collection)
    // - countNot(collection, "B")

    /////////////////////////////////////////////////////////////////////////////////
    // 5.1
    // - min(collection)
    // - min(collection, def)

    // 5.2
    // - max(collection)
    // - max(collection, def)

    /////////////////////////////////////////////////////////////////////////////////
    // 6.1
    // - sort(list)
    // - filter(collection, filter)
    // - filter(list, filter)
    // - distinct(collection)
    // - distinct(list)
    // - reverse(list)

    /////////////////////////////////////////////////////////////////////////////////
    // 7.1
    // - toCollection(array)

    // 7.2
    // - toList(collection)
    // - toList(array)

    // 7.3
    // - toSet(collection)
    // - toSet(array)

    // 7.4
    // - asList(e1, e2, ... en)
    // - asSet(e1, e2, ... en)
    // - asCollection(e1, e2, ... en)

    // - as<Type>List(e1, e2, ... en)
    // - as<Type>Set(e1, e2, ... en)
    // - as<Type>Collection(e1, e2, ... en)

    // 7.4
    // - toSortList(collection)
    // - toSortList(collection, comparator)

    // - toSortList(array)
    // - toSortList(array, comparator)

    // - toFilterList(collection, filter)
    // - toFilterList(array, filter)

    // - toCriteriaList(collection, filter, sorter, isDistinct)
    // - toCriteriaList(array, filter, sorter, isDistinct)

    // - toReverseList(collection)
    // - toReverseList(array)

    // - toIterator(collection)
    // - toIterator(array)

    // https://github.com/google/guava/tree/master/guava/src/com/google/common/math
    // https://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java

    private static final int FILL_THRESHOLD = 25;

    private CollectionLib() {
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

    private static boolean checkNull(Object x, ArithmeticContext context, String message) {
        return ArithmeticEnvironment.checkNull(x, context, message);
    }

    private static <T> boolean checkNull(Collection<T> x, Collection<T> y, ArithmeticContext context) {
        return checkNull(x, y, context, "Collection must be not null");
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

    //// 1.1

    // x + y
    public static <T> Collection<T> add(Collection<T> x, Collection<T> y) {
        return add(x, y, null);
    }

    // x + y
    public static <T> Collection<T> add(Collection<T> x, Collection<T> y, ArithmeticContext context) {
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }
            Collection<T> r = (x == null) ? y : x; // add
            return copy(r);
        }
        Collection<T> r = copy(x);
        r.addAll(y);

        return r;
    }

    // x + y: maybe addOf()
    public static <T> void addTo(Collection<T> x, Collection<T> y) {
        addTo(x, y, null);
    }

    // x + y: maybe addOf()
    public static <T> void addTo(Collection<T> x, Collection<T> y, ArithmeticContext context) {
        if (x == null) {
            throw new NullPointerException("First collection must be not null");
        }
        if (y == null) {
            ArithmeticContext c = getContext(context);
            checkNull(y, c, "Second collection must be not null");
            return;
        }

        x.addAll(y);
    }

    //// 1.2

    // x - y
    public static <T> Collection<T> sub(Collection<T> x, Collection<T> y) {
        return sub(x, y, null);
    }

    // x - y
    public static <T> Collection<T> sub(Collection<T> x, Collection<T> y, ArithmeticContext context) {
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }

            // null - null = null
            // null - {1, 4, 6} = null
            // {1, 4, 6} - null = {1, 4, 6}

            Collection<T> r = (x == null) ? null : copy(x); // sub
            return r;
        }

        Collection<T> r = copy(x);
        r.removeAll(y);

        return r;
    }

    // x - y: maybe subOf()
    public static <T> void subTo(Collection<T> x, Collection<T> y) {
        subTo(x, y, null);
    }

    // x - y: maybe subOf()
    public static <T> void subTo(Collection<T> x, Collection<T> y, ArithmeticContext context) {
        if (x == null) {
            throw new NullPointerException("First collection must be not null");
        }
        if (y == null) {
            ArithmeticContext c = getContext(context);
            checkNull(y, c, "Second collection must be not null");
            return;
        }
        x.removeAll(y);
    }

    // union
    public static <T> Collection<T> union(Collection<T> x, Collection<T> y) {
        return union(x, y, null);
    }

    public static <T> Collection<T> union(Collection<T> x, Collection<T> y, ArithmeticContext context) {
        // add
        Collection<T> result = add(x, y, context);
        if (result == null) {
            return null;
        }

        // distinct
        Set<T> set = CollectionLib.toSet(result);
        return set;
    }

    public static <T> Collection<T> intersection(Collection<T> x, Collection<T> y) {
        return intersection(x, y, null);
    }

    public static <T> Collection<T> intersection(Collection<T> x, Collection<T> y, ArithmeticContext context) {
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }
            Collection<T> r = (x == null) ? y : x;
            return copy(r);
        }

        Set<T> set = new LinkedHashSet<>();
        Iterator<T> iterator = x.iterator();
        while (iterator.hasNext()) {
            T e = iterator.next();
            if (y.contains(e)) {
                set.add(e);
            }
        }
        return set;
    }

    //// 2.1

    // copy(list)
    public static <T> List<T> copy(List<T> original) {
        if (original == null) {
            return null;
        }
        return copy(original, original.size());
    }

    public static <T> List<T> copy(List<T> original, int size) {
        if (original == null) {
            return null;
        }
        ArrayList<T> result = new ArrayList<>(); // TODO: new instance by original type

        // TODO: Need refactoring (code duplication). See copy(Collection<T> original,
        // int size)
        int originalSize = original.size();
        if (originalSize <= size) {
            result.addAll(original);
            if (originalSize < size) {
                // add rest elements (size - originalSize): fill 'null'
                addAll(result, null, size - originalSize);
            }
        } else {

            // copy elements from 'original' to 'result'
            Iterator<T> iterator = original.iterator();
            for (int i = 0; i < size; i++) {
                result.add(iterator.next());
            }
        }
        return result;
    }

    // copy(collection)

    public static <T> Collection<T> copy(Collection<T> original) {
        if (original == null) {
            return null;
        }
        return copy(original, original.size());
    }

    public static <T> Collection<T> copy(Collection<T> original, int size) {
        if (original == null) {
            return null;
        }
        ArrayList<T> result = new ArrayList<>(); // TODO: new instance by original type

        // TODO: Need refactoring (code duplication). See copy(List<T> original, int
        // size)
        int originalSize = original.size();
        if (originalSize <= size) {
            result.addAll(original);
            if (originalSize < size) {
                // add rest elements (size - originalSize): fill 'null'
                addAll(result, null, size - originalSize);
            }
        } else {

            // copy elements from 'original' to 'result'
            Iterator<T> iterator = original.iterator();
            for (int i = 0; i < size; i++) {
                result.add(iterator.next());
            }
        }
        return result;
    }

    // TODO: Need refactoring. See copyRange(List<T> original, int fromIndex, int
    // toIndex)
    //
    // Incorrect arguments:
    // For Collection : int fromIndex, int size
    // For List : int fromIndex, int toIndex
    public static <T> Collection<T> copy(Collection<T> original, int fromIndex, int size) {
        if (original == null) {
            return null;
        }

        int originalSize = original.size();

        // fromIndex range
        if (checkIndexOut(fromIndex, originalSize)) {
            return null;
        }

        ArrayList<T> result = new ArrayList<>(); // TODO: new instance by original type

        // int originalSize = original.size();
        if (originalSize <= size) {
            result.addAll(original);
            if (originalSize < size) {
                // add rest elements (length - l): fill 'null'
                addAll(result, null, size - originalSize);
            }
        } else {

            // copy elements from 'original' to 'result'
            Iterator<T> iterator = original.iterator();
            for (int i = 0; i < size; i++) {
                result.add(iterator.next());
            }
        }
        return result;
    }

    //// 2.2

    // We can copy List only
    // toIndex - exclusive!
    public static <T> List<T> copyRange(List<T> original, int fromIndex, int toIndex) {
        if (original == null) {
            return null;
        }

        int originalSize = original.size();

        // fromIndex range
        if (checkIndexOut(fromIndex, originalSize)) {
            return null;
        }

        // fromIndex, toIndex
        if (toIndex < fromIndex) {
            return null;
        }

        int toIndex2 = Math.min(toIndex, originalSize);

        ArrayList<T> result = new ArrayList<>(); // TODO: new instance by original type
        for (int i = fromIndex; i < toIndex2; i++) {
            result.add(original.get(i));
        }

        if (toIndex > toIndex2) {
            // add rest elements (toIndex - toIndex2): fill 'null'
            addAll(result, null, toIndex - toIndex2);
        }
        return result;
    }

    //// 3.1

    public static <T> void fill(List<? super T> list, T value) {
        if (list == null) {
            return;
        }
        int size = list.size();

        if (size < FILL_THRESHOLD || list instanceof RandomAccess) {
            CollectionHelper._fill(list, value, 0, size);
        } else {
            ListIterator<? super T> itr = list.listIterator();
            CollectionHelper._fill(itr, value, 0, size);
        }
    }

    public static <T> void fill(List<? super T> list, T value, int fromIndex, int toIndex) {
        if (list == null) {
            return;
        }
        int size = list.size();

        // fromIndex range
        if (checkIndexOut(fromIndex, size)) {
            return;
        }
        // toIndex range
        if (checkIndexOut(toIndex - 1, size)) {
            return;
        }

        // fromIndex, toIndex
        if (toIndex < fromIndex) {
            return;
        }

        if (size < FILL_THRESHOLD || list instanceof RandomAccess) {
            CollectionHelper._fill(list, value, fromIndex, toIndex);
        } else {
            ListIterator<? super T> itr = list.listIterator();
            if (fromIndex > 0) {
                CollectionHelper._next(itr, fromIndex);
            }
            CollectionHelper._fill(itr, value, fromIndex, toIndex);
        }
    }

    //// 3.2

    // fill with expanding
    // maybe rename to 'append()'
    public static <T> void addAll(Collection<T> collection, T element, int size) {
        if (size < 1) {
            // TODO: exception
            return;
        }
        for (int i = 0; i < size; i++) {
            collection.add(element);
        }
    }

    public static <T> void addAll(Collection<T> collection, Collection<T> elements) {
        if (elements == null || elements.size() == 0) {
            return;
        }
        collection.addAll(elements);
    }

    public static <T> void addAll(Collection<T> collection, T[] elements) {
        if (elements == null || elements.length == 0) {
            // TODO: exception
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            collection.add(elements[i]);
        }
    }

    //// 3.3

    public static <T> void removeAll(Collection<T> collection, Collection<T> elements) {
        CollectionHelper._removeAll(collection, elements);
    }

    public static <T> void removeAll(Collection<T> collection, T[] elements) {
        CollectionHelper._removeAll(collection, elements);
    }

    //// 3.4

    public static <T> boolean replaceAll(List<T> list, T oldValue, T newValue) {
        return CollectionHelper._replaceAll(list, oldValue, newValue);
    }

    public static <T> boolean[] replaceAll(List<T> list, T[] oldValues, T[] newValues) {
        return CollectionHelper._replaceAll(list, oldValues, newValues);
    }

    ///

    public static <T> void addElement(Collection<T> collection, T element) {
        collection.add(element);
    }

    public static <T> void removeElement(Collection<T> collection, T element) {
        collection.remove(element);
    }

    // List: getElement

    public static <T> T getElement(List<T> list, int index) {
        return list.get(index);
    }

    // Map: getElement

    public static <K, T> T getElement(Map<K, T> map, K key) {
        return map.get(key);
    }

    // Map: getValue (alias)

    public static <K, V> V getValue(Map<K, V> map, K key) {
        return map.get(key);
    }

    // Map: addElement

    public static <K, T> void addElement(Map<K, T> map, K key, T element) {
        map.put(key, element);
    }

    // Map: addValue (alias)

    public static <K, V> void addValue(Map<K, V> map, K key, V value) {
        map.put(key, value);
    }

    // Map: removeKey

    public static <K, V> void removeKey(Map<K, V> map, K key) {
        map.remove(key);
    }

    //// isEmpty

    public static boolean isEmpty(Collection<?> collection) {
        return size(collection) == 0;
    }

    //// equals

    public static <T> boolean equals(Collection<T> collection1, Collection<T> collection2) {
        return _equals(collection1, collection2, true); // TODO: collection1.getClass() != collection2.getClass() ???
    }

    static <T> boolean _equals(Collection<T> collection1, Collection<T> collection2, boolean checkClass) {
        if (collection1 == null || collection2 == null) {
            return false;
        }
        if (collection1 == collection2) {
            return true;
        }
        if (checkClass && collection1.getClass() != collection2.getClass()) {
            return false;
        }
        int size1 = collection1.size();
        int size2 = collection2.size();

        if (size1 != size2) {
            return false;
        }

        // empty collections are equal
        if (size1 == 0) {
            return true;
        }
        Iterator<T> iterator1 = collection1.iterator();
        Iterator<T> iterator2 = collection2.iterator();

        while (iterator1.hasNext()) {

            if (!iterator2.hasNext()) {
                // critical case: size1 == size2
                // but collection1 has less elements than collection1
                return false;
            }

            T e1 = iterator1.next();
            T e2 = iterator2.next();
            if (!ObjLib.equals(e1, e2)) {
                return false;
            }
        }
        return true;
    }

    //// contains

    public static <T> boolean contains(Collection<T> collection, T value) {
        if (collection == null) {
            return false;
        }
        return collection.contains(value);
    }

    ////

    public static int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    ////

    // alias: size()
    public static int count(Collection<?> collection) {
        return size(collection);
    }

    public static <T> int count(Collection<T> collection, T value) {
        return CollectionHelper._count(collection, value, true);
    }

    public static <T> int countNot(Collection<T> collection, T value) {
        return CollectionHelper._count(collection, value, false); // Not
    }

    public static <T> int countNull(Collection<T> collection) {
        return CollectionHelper._count(collection, null, true);
    }

    public static <T> int countNotNull(Collection<T> collection) {
        return CollectionHelper._count(collection, null, false); // Not
    }

    ////

    public static <T extends Comparable<T>> T min(Collection<T> collection) {
        return CollectionHelper._min(collection, null, false);
    }

    public static <T extends Comparable<T>> T min(Collection<T> collection, T def) {
        return CollectionHelper._min(collection, def, true);
    }

    public static <T extends Comparable<T>> T max(Collection<T> collection) {
        return CollectionHelper._max(collection, null, false);
    }

    public static <T extends Comparable<T>> T max(Collection<T> collection, T def) {
        return CollectionHelper._max(collection, def, true);
    }

    ////

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        CollectionStreamHelper._sort(list);
    }

    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        CollectionStreamHelper._sort(list, comparator);
    }

    //// filter

    public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> filter) {
        return CollectionStreamHelper._filter(collection, filter);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        return CollectionStreamHelper._filter(list, filter);
    }

    //// distinct

    public static <T> List<T> distinct(List<T> list) {
        return CollectionStreamHelper._distinct(list);
    }

    public static <T> Collection<T> distinct(Collection<T> collection) {
        return CollectionStreamHelper._distinct(collection);
    }

    //// reverse

    public static <T> void reverse(List<T> list) {
        CollectionHelper._reverse(list);
    }

    ////

    public static <T> Collection<T> toCollection(T[] array) {
        return toList(array);
    }

    //// toList: Collection

    public static <T> List<T> toList(Collection<T> collection) {
        return CollectionHelper._toList(collection);
    }

    //// toList: Array

    public static <T> List<T> toList(T[] array) {
        return CollectionHelper._toList(array);
    }

    //// toSortList: Collection

    public static <T> List<T> toSortList(Collection<T> collection) {
        return toSortList(collection, null);
    }

    public static <T> List<T> toSortList(Collection<T> collection, Comparator<? super T> comparator) {
        if (collection == null) {
            return null;
        }
        List<T> list = CollectionHelper._toList(collection);
        sort(list, comparator);
        return list;
    }

    //// toSortList: Array

    public static <T> List<T> toSortList(T[] array) {
        return toSortList(array, null);
    }

    public static <T> List<T> toSortList(T[] array, Comparator<? super T> comparator) {
        return toSortList(toList(array), comparator);
    }

    //// toFilterList: Collection

    public static <T> List<T> toFilterList(Collection<T> collection, Predicate<T> filter) {
        return CollectionStreamHelper._filterList(collection, filter);
    }

    //// toFilterList: Array

    public static <T> List<T> toFilterList(T[] array, Predicate<T> filter) {
        return toFilterList(toList(array), filter);
    }

    //// toDistinctList: Collection

    public static <T> List<T> toDistinctList(Collection<T> collection) {
        return CollectionStreamHelper._distinctList(collection);
    }

    //// toDistinctList: Array

    public static <T> List<T> toDistinctList(T[] array) {
        return toDistinctList(toList(array));
    }

    //// toCriteriaList: Collection: filter, sorter, distinct

    public static <T> List<T> toCriteriaList(Collection<T> collection, Predicate<T> filter,
            Comparator<? super T> sorter, boolean isDistinct) {

        if (collection == null) {
            return null;
        }
        List<T> result = null;

        // no filter, no sort
        if (filter == null && sorter == null) {
            result = isDistinct ? toDistinctList(collection) : toList(collection);
            return result;
        }

        // filter
        if (filter == null) {
            // no filter
            result = toList(collection);
        } else {
            // do filter
            result = toFilterList(collection, filter);
        }

        // sorter
        if (sorter != null) {
            sort(result, sorter);
        }

        // distinct
        if (isDistinct) {
            result = toDistinctList(result);
        }

        return result;
    }

    //// toCriteriaList: Array: filter, sorter, distinct

    public static <T> List<T> toCriteriaList(T[] array, Predicate<T> filter, Comparator<? super T> sorter,
            boolean isDistinct) {
        return toCriteriaList(toList(array), filter, sorter, isDistinct);
    }

    //// toReverseList: Collection

    public static <T> List<T> toReverseList(Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        List<T> list = toList(collection);
        reverse(list);
        return list;
    }

    //// toReverseList: Array

    public static <T> List<T> toReverseList(T[] array) {
        return toReverseList(toList(array));
    }

    //// toIterator: Collection

    public static <T> Iterator<T> toIterator(Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        return collection.iterator();
    }

    //// toIterator: Array

    public static <T> Iterator<T> toIterator(T[] array) {
        return toIterator(toList(array));
    }

    //// array[] -> List<Array>

    public static List<Boolean> toList(boolean[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Byte> toList(byte[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Character> toList(char[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Short> toList(short[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Integer> toList(int[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Long> toList(long[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Float> toList(float[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Double> toList(double[] array) {
        return CollectionHelper._toList(array);
    }

    //// Array[] -> List<Array>

    public static List<Boolean> toList(Boolean[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Byte> toList(Byte[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Character> toList(Character[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Short> toList(Short[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Integer> toList(Integer[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Long> toList(Long[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Float> toList(Float[] array) {
        return CollectionHelper._toList(array);
    }

    public static List<Double> toList(Double[] array) {
        return CollectionHelper._toList(array);
    }

    //// toSet

    public static <T> Set<T> toSet(Collection<T> collection) {
        return CollectionHelper._toSet(collection);
    }

    public static <T> Set<T> toSet(T[] array) {
        return CollectionHelper._toSet(array);
    }

    //// array[] -> Set<Array>

    public static Set<Boolean> toSet(boolean[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Byte> toSet(byte[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Character> toSet(char[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Short> toSet(short[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Integer> toSet(int[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Long> toSet(long[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Float> toSet(float[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Double> toSet(double[] array) {
        return CollectionHelper._toSet(array);
    }

    // NonJS
    public static <T> T[] toArray(Collection<T> collection, Class<?> componentType) {
        return ArrayLib.toArray(collection, componentType);
    }

    ////

    //// Array[] -> Set<Array>

    public static Set<Boolean> toSet(Boolean[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Byte> toSet(Byte[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Character> toSet(Character[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Short> toSet(Short[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Integer> toSet(Integer[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Long> toSet(Long[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Float> toSet(Float[] array) {
        return CollectionHelper._toSet(array);
    }

    public static Set<Double> toSet(Double[] array) {
        return CollectionHelper._toSet(array);
    }

    //// asList
    //// Usage: For Eclipse Java Compiler (ecj) only.

    public static List<Boolean> asList(boolean... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Byte> asList(byte... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Character> asList(char... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Short> asList(short... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Integer> asList(int... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Long> asList(long... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Float> asList(float... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Double> asList(double... array) {
        return CollectionHelper._asList(array);
    }

    public static <T> List<T> asList(T... array) {
        return CollectionHelper._asList(array);
    }

    //// as<Type>List
    //// For compatibility only: reference to asCollection is ambiguous both method
    //// asCollection(...)
    //// For Eclipse Java Compiler (ecj) it's OK.

    public static List<Boolean> asBooleanList(boolean... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Byte> asByteList(byte... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Character> asCharacterList(char... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Short> asShortList(short... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Integer> asIntegerList(int... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Long> asLongList(long... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Float> asFloatist(float... array) {
        return CollectionHelper._asList(array);
    }

    public static List<Double> asDoubleList(double... array) {
        return CollectionHelper._asList(array);
    }

    //// asSet
    //// Usage: For Eclipse Java Compiler (ecj) only.

    public static Set<Boolean> asSet(boolean... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Byte> asSet(byte... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Character> asSet(char... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Short> asSet(short... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Integer> asSet(int... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Long> asSet(long... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Float> asSet(float... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Double> asSet(double... array) {
        return CollectionHelper._asSet(array);
    }

    public static <T> Set<T> asSet(T... array) {
        return CollectionHelper._asSet(array);
    }

    //// as<Type>Set
    //// For compatibility only: reference to asCollection is ambiguous both method
    //// asCollection(...)
    //// For Eclipse Java Compiler (ecj) it's OK.

    public static Set<Boolean> asBooleanSet(boolean... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Byte> asByteSet(byte... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Character> asCharacterSet(char... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Short> asShortSet(short... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Integer> asIntegerSet(int... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Long> asLongSet(long... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Float> asFloatSet(float... array) {
        return CollectionHelper._asSet(array);
    }

    public static Set<Double> asDoubleSet(double... array) {
        return CollectionHelper._asSet(array);
    }

    //// asCollection
    //// Usage: For Eclipse Java Compiler (ecj) only.

    public static Collection<Boolean> asCollection(boolean... array) {
        return asList(array);
    }

    public static Collection<Byte> asCollection(byte... array) {
        return asList(array);
    }

    public static Collection<Character> asCollection(char... array) {
        return asList(array);
    }

    public static Collection<Short> asCollection(short... array) {
        return asList(array);
    }

    public static Collection<Integer> asCollection(int... array) {
        return asList(array);
    }

    public static Collection<Long> asCollection(long... array) {
        return asList(array);
    }

    public static Collection<Float> asCollection(float... array) {
        return asList(array);
    }

    public static Collection<Double> asCollection(double... array) {
        return asList(array);
    }

    public static <T> Collection<T> asCollection(T... array) {
        return asList(array);
    }

    //// as<Type>Collection
    //// For compatibility only: reference to asCollection is ambiguous both method
    //// asCollection(...)
    //// For Eclipse Java Compiler (ecj) it's OK.

    public static Collection<Boolean> asBooleanCollection(boolean... array) {
        return asList(array);
    }

    public static Collection<Byte> asByteCollection(byte... array) {
        return asList(array);
    }

    public static Collection<Character> asCharacterCollection(char... array) {
        return asList(array);
    }

    public static Collection<Short> asShortCollection(short... array) {
        return asList(array);
    }

    public static Collection<Integer> asIntegerCollection(int... array) {
        return asList(array);
    }

    public static Collection<Long> asLongCollection(long... array) {
        return asList(array);
    }

    public static Collection<Float> asFloatCollection(float... array) {
        return asList(array);
    }

    public static Collection<Double> asDoubleCollection(double... array) {
        return asList(array);
    }

}
