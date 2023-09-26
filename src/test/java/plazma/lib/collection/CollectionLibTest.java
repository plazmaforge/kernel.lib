package plazma.lib.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import plazma.lib.AbstractTestCase;

public class CollectionLibTest extends AbstractTestCase {

    // +, -
    public void testOperators() {

        Collection<?> collection = null;

        // add
        collection = CollectionLib.add(null, null);
        printCollection(collection);

        // ["a", "b" , "c"] + ["d", "e"] = ["a", "b" , "c", "d", "e"]
        collection = CollectionLib.add(CollectionLib.asCollection("a", "b", "c"), CollectionLib.asCollection("d", "e"));
        printCollection(collection);

        // ["d", "e"] + ["a", "b" , "c"] = ["d", "e", "a", "b" , "c"]
        collection = CollectionLib.add(CollectionLib.asCollection("d", "e"), CollectionLib.asCollection("a", "b", "c"));
        printCollection(collection);

        collection = CollectionLib.add(CollectionLib.asIntegerCollection(1, 3, 5),
                CollectionLib.asIntegerCollection(9, 11)); // ambiguous
        printCollection(collection);

        collection = CollectionLib.add(CollectionLib.asIntegerCollection(9, 11),
                CollectionLib.asIntegerCollection(1, 3, 5)); // ambiguous
        printCollection(collection);

        // sub
        collection = CollectionLib.sub(null, null);
        printCollection(collection);

        collection = CollectionLib.sub(CollectionLib.asCollection("a", "b", "c"), CollectionLib.asCollection("b"));
        printCollection(collection);

        collection = CollectionLib.sub(CollectionLib.asIntegerCollection(1, 3, 5),
                CollectionLib.asIntegerCollection(9, 11, 1)); // ambiguous
        printCollection(collection);

    }

    // union
    public void testUnion() {

        printHeader("Test union()");
        Collection<?> collection = null;

        // add
        collection = CollectionLib.union(null, null);
        printCollection(collection);

        // ["a", "b" , "c"] U ["d", "e"] = ["a", "b", "c", "d", "e"]
        collection = CollectionLib.union(CollectionLib.asCollection("a", "b", "c"),
                CollectionLib.asCollection("d", "e"));
        printCollection(collection);

        // ["a", "b" , "c"] U ["a", "d", "e", "c"] = ["a", "b", "c", "d", "e"]
        collection = CollectionLib.union(CollectionLib.asCollection("a", "b", "c"),
                CollectionLib.asCollection("a", "d", "e", "c"));
        printCollection(collection);

        // ["d", "e"] U ["a", "b" , "c"] = ["d", "e", "a", "b" , "c"]
        collection = CollectionLib.union(CollectionLib.asCollection("d", "e"),
                CollectionLib.asCollection("a", "b", "c"));
        printCollection(collection);

        collection = CollectionLib.union(CollectionLib.asIntegerCollection(1, 3, 5),
                CollectionLib.asIntegerCollection(9, 11)); // ambiguous
        printCollection(collection);

        collection = CollectionLib.union(CollectionLib.asIntegerCollection(9, 11),
                CollectionLib.asIntegerCollection(1, 3, 5)); // ambiguous
        printCollection(collection);

    }

    // copy
    public void testCopy() {

        Collection<?> collection = null;

        collection = CollectionLib.copy(null);
        printCollection(collection);

        collection = CollectionLib.copy(CollectionLib.asIntegerCollection(1, 3, 5)); // ambiguous
        printCollection(collection);

        collection = CollectionLib.copy(CollectionLib.asIntegerCollection(1, 3, 5), 2); // ambiguous
        printCollection(collection);

        collection = CollectionLib.copy(CollectionLib.asIntegerCollection(1, 3, 5), 1, 2); // next 2
        printCollection(collection);

        collection = CollectionLib.copy(CollectionLib.asIntegerCollection(1, 3, 5), 1, 10); // next 10
        printCollection(collection);

        collection = CollectionLib.copyRange(CollectionLib.asIntegerList(1, 3, 5), 1, 2); // {1, 2]
        printCollection(collection);

        collection = CollectionLib.copyRange(CollectionLib.asIntegerList(1, 3, 5), 1, 10); // {1, 10]
        printCollection(collection);
    }

    // populate: fill, addAll, removeAll, replaceAll
    public void testPopulate() {

        printHeader("Test fill()");

        List<String> list = null;

        CollectionLib.fill(null, null);

        list = new ArrayList<String>();

        CollectionLib.fill(list, null);
        printCollection(list);

        CollectionLib.addAll(list, "A", 3);
        printCollection(list);

        CollectionLib.addAll(list, "B", 3);
        printCollection(list);

        CollectionLib.addAll(list, "C", 3);
        printCollection(list);

        CollectionLib.removeAll(list, new String[] { "B", "C" });
        printCollection(list);

        CollectionLib.fill(list, "0");
        printCollection(list);

        CollectionLib.replaceAll(list, "0", "1");
        printCollection(list);

        CollectionLib.addAll(list, "2", 3);
        CollectionLib.addAll(list, "3", 3);

        printCollection(list);

        CollectionLib.replaceAll(list, new String[] { "1", "3" }, new String[] { "X", "Y" });
        printCollection(list);
    }

    // min, max
    public void testMinMax() {
        printHeader("Test min()");

        Collection<Integer> collection = new ArrayList<Integer>();
        collection.add(null);
        collection.add(100);
        collection.add(null);
        collection.add(10);
        collection.add(-10);
        collection.add(0);

        println("min: " + CollectionLib.min(collection) + " of " + collection);
        println("max: " + CollectionLib.max(collection) + " of " + collection);

        collection = new ArrayList<Integer>();
        collection.add(10);
        collection.add(100);
        collection.add(1);
        println("min: " + CollectionLib.min(collection) + " of " + collection);
        println("max: " + CollectionLib.max(collection) + " of " + collection);
    }

    // size, count
    public void testCount() {
        printHeader("Test size()");

        println("size: " + CollectionLib.size(null) + " of null");
        println("count: " + CollectionLib.count(null) + " of null");

        Collection<Integer> collection = new ArrayList<>();

        println("size: " + CollectionLib.size(collection) + " of " + collection);
        println("count: " + CollectionLib.count(collection) + " of " + collection);

        collection.add(null);
        collection.add(100);
        collection.add(null);
        collection.add(5);
        collection.add(10);
        collection.add(-10);
        collection.add(0);
        collection.add(10);

        println("size: " + CollectionLib.size(collection) + " of " + collection);
        println("count: " + CollectionLib.count(collection) + " of " + collection);

        println("count(null): " + CollectionLib.count(collection, null) + " of " + collection);
        println("countNull(): " + CollectionLib.countNull(collection) + " of " + collection);
        println("countNotNull(): " + CollectionLib.countNotNull(collection) + " of " + collection);
        println("countNot(null): " + CollectionLib.countNot(collection, null) + " of " + collection);

        println("count(10): " + CollectionLib.count(collection, 10) + " of " + collection);

        println("countNot(10): " + CollectionLib.countNot(collection, 10) + " of " + collection);

        Collection<String> collection2 = new ArrayList<>();

        printHeader("Test count(): String");
        println("count: " + CollectionLib.count(collection2) + " of " + collection2);

        collection2.add(null);
        collection2.add("ABC");
        collection2.add(null);
        collection2.add("CDE");
        collection2.add("XY");
        collection2.add("QUERTY");
        collection2.add("");
        collection2.add("XY");

        println("count(null): " + CollectionLib.count(collection2, null) + " of " + collection2);
        println("countNull(): " + CollectionLib.countNull(collection2) + " of " + collection2);
        println("countNotNull(): " + CollectionLib.countNotNull(collection2) + " of " + collection2);
        println("countNot(null): " + CollectionLib.countNot(collection2, null) + " of " + collection2);

        println("count('XY'): " + CollectionLib.count(collection2, "XY") + " of " + collection2);

        println("countNot('XY'): " + CollectionLib.countNot(collection2, "XY") + " of " + collection2);
    }

    // sort
    public void testSort() {
        printHeader("Test sort()");

        List<Integer> collection = CollectionLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        CollectionLib.sort(collection, null);
        println("after: " + collection);
    }

    // toSortedList
    public void testToSortList() {
        printHeader("Test toSortedList()");

        List<Integer> collection = CollectionLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CollectionLib.toSortList(collection, null);
        println("after: " + collection);
    }

    // filter
    public void testFilter() {
        printHeader("Test filter()");

        List<Integer> collection = CollectionLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CollectionLib.filter(collection, (x) -> (x != null && x > 0));
        println("after: " + collection);
    }

    // toFilteredList
    public void testToFilterList() {
        printHeader("Test toFilteredList()");

        List<Integer> collection = CollectionLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CollectionLib.toFilterList(collection, (x) -> (x != null && x > 0));
        println("after: " + collection);
    }

    // distinct
    public void testDistinct() {
        printHeader("Test distinct()");

        List<Integer> collection = CollectionLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CollectionLib.distinct(collection);
        println("after: " + collection);
    }

    // toList
    public void testToList() {

        // integer
        List<Integer> integerList = CollectionLib.toList(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        printHeader("Integer: toList");
        println(integerList);

        // byte
        List<Byte> byteList = CollectionLib.toList(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5,
                (byte) 6, (byte) 7, (byte) 8, (byte) 9 });
        printHeader("Byte: toList");
        println(byteList);

        // String
        List<String> stringList = CollectionLib.toList(new String[] { "ABC", "XYZ" });
        printHeader("String: toList");
        println(stringList);

        // Date
        List<Date> dateList = CollectionLib.asList(new Date[] { new Date(), new Date() });
        printHeader("Date: toList");
        println(dateList);

    }

    // toSet
    public void testToSet() {

        // integer
        Set<Integer> integerList = CollectionLib.toSet(new int[] { 9, 1, 2, 3, 4, 2, 3, 5, 6, 8, 8, 7, 8, 9 });
        printHeader("Integer: toSet");
        println(integerList);

        // byte
        Set<Byte> byteList = CollectionLib.toSet(new byte[] { (byte) 9, (byte) 1, (byte) 2, (byte) 3, (byte) 4,
                (byte) 2, (byte) 3, (byte) 5, (byte) 6, (byte) 8, (byte) 8, (byte) 7, (byte) 8, (byte) 9 });
        printHeader("Byte: toSet");
        println(byteList);

        // String
        Set<String> stringList = CollectionLib.toSet(new String[] { "ABC", "XYZ" });
        printHeader("String: toSet");
        println(stringList);

        // Date
        Set<Date> dateList = CollectionLib.toSet(new Date[] { new Date(), new Date() });
        printHeader("Date: toSet");
        println(dateList);

    }

    // asSet
    public void testAsSet() {

        // integer
        Set<Integer> integerList = CollectionLib.asIntegerSet(9, 1, 2, 3, 4, 2, 3, 5, 6, 8, 8, 7, 8, 9); // ambiguous
        printHeader("Integer: asSet");
        println(integerList);

        // byte
        Set<Byte> byteList = CollectionLib.asByteSet((byte) 9, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 2,
                (byte) 3, (byte) 5, (byte) 6, (byte) 8, (byte) 8, (byte) 7, (byte) 8, (byte) 9); // ambiguous
        printHeader("Byte: asSet");
        println(byteList);

        // String
        Set<String> stringList = CollectionLib.asSet("ABC", "XYZ");
        printHeader("String: asSet");
        println(stringList);

        // Date
        Set<Date> dateList = CollectionLib.asSet(new Date(), new Date());
        printHeader("Date: asSet");
        println(dateList);

    }

    // asList
    public void testAs() {

        // integer
        List<Integer> integerList = CollectionLib.asIntegerList(1, 2, 3, 4, 5, 6, 7, 8, 9); // ambiguous
        printHeader("Integer: asList");
        println(integerList);

        // byte
        List<Byte> byteList = CollectionLib.asByteList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
                (byte) 7, (byte) 8, (byte) 9); // ambiguous
        printHeader("Byte: asList");
        println(byteList);

        // String
        List<String> stringList = CollectionLib.asList("ABC", "XYZ");
        printHeader("String: asList");
        println(stringList);

        // Date
        List<Date> dateList = CollectionLib.asList(new Date(), new Date());
        printHeader("Date: asList");
        println(dateList);
    }

    private <T> Collection<T> toCollection(T[] array) {
        return CollectionLib.toCollection(array);
    }

    private <T> List<T> toList(T[] array) {
        return CollectionLib.toList(array);
    }

}
