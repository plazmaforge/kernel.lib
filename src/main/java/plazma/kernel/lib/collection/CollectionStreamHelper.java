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
import java.util.Set;
import java.util.function.Predicate;

import plazma.kernel.lib.obj.ObjLib;

public class CollectionStreamHelper {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    // 1.1
    // - sort(collection) (+)

    // 1.2
    // - filter(collection) (+)
    // - filter(list) (+)
    // - filter(set) (???)

    private CollectionStreamHelper() {
    }

    ////

    private static <T> Comparator<T> safeComparator(Comparator<? super T> comparator) {
        return ObjLib.safeComparator(comparator);
    }

    ////

    static <T extends Comparable<? super T>> void _sort(List<T> list) {
        if (list == null) {
            return;
        }
        list.sort(safeComparator(new ObjLib.UComparator1<>()));
    }

    static <T> void _sort(List<T> list, Comparator<? super T> comparator) {
        if (list == null) {
            return;
        }
        list.sort(safeComparator(comparator == null ? new ObjLib.UComparator2<>() : comparator));
    }

    static <T> List<T> _filter(List<T> list, Predicate<T> filter) {
        if (list == null) {
            return null;
        }
        // no filter - return all
        if (filter == null) {
            return CollectionLib.copy(list);
        }

        // TODO: CollectionLib.FILL_THRESHOLD ?
        List<T> result = new ArrayList<T>(); // TODO: new instance by original type
        int size = list.size();
        T e = null;
        for (int i = 0; i < size; i++) {
            e = list.get(i);
            if (filter.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    static <T> Collection<T> _filter(Collection<T> collection, Predicate<T> filter) {
        if (collection == null) {
            return null;
        }
        // no filter - return all
        if (filter == null) {
            return CollectionLib.copy(collection);
        }

        // TODO: CollectionLib.FILL_THRESHOLD ?
        List<T> result = new ArrayList<T>(); // TODO: new instance by original type
        T e = null;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            e = iterator.next();
            if (filter.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    static <T> List<T> _filterList(Collection<T> collection, Predicate<T> filter) {
        if (collection == null) {
            return null;
        }
        if (collection instanceof List) {
            return _filter((List<T>) collection, filter);
        }

        // no filter - return all
        if (filter == null) {
            return CollectionLib.toList(collection);
        }

        // TODO: CollectionLib.FILL_THRESHOLD ?
        List<T> result = new ArrayList<T>(); // by default
        T e = null;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            e = iterator.next();
            if (filter.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    //

    static <T> List<T> _distinctList(Collection<T> collection) {
        if (collection == null) {
            return null;
        }
        Set<T> set = _toSet(collection);
        return CollectionLib.toList(set); // TODO: new instance by original type
    }

    //

    static <T> Set<T> _toSet(Collection<T> collection) {
        if (collection == null) {
            return null;
        }

        // TODO: CollectionLib.FILL_THRESHOLD ?
        Set<T> set = new LinkedHashSet<T>();
        T e = null;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            e = iterator.next();
            set.add(e);
        }
        return set;
    }

    //

    static <T> Set<T> _toSet(List<T> list) {
        if (list == null) {
            return null;
        }

        // TODO: CollectionLib.FILL_THRESHOLD ?
        Set<T> set = new LinkedHashSet<T>();
        T e = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            e = list.get(i);
            set.add(e);
        }
        return set;
    }

    //

    static <T> List<T> _distinct(List<T> list) {
        if (list == null) {
            return null;
        }
        Set<T> set = _toSet(list);
        return CollectionLib.toList(set); // TODO: new instance by original type
    }

    static <T> Collection<T> _distinct(Collection<T> collection) {
        if (collection == null) {
            return null;
        }

        // TODO: CollectionLib.FILL_THRESHOLD ?
        Set<T> set = new LinkedHashSet<T>();
        T e = null;
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            e = iterator.next();
            set.add(e);
        }
        return set; // TODO: new instance by original type
    }

}
