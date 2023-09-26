package plazma.lib.clt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import plazma.lib.AbstractTestCase;
import plazma.lib.clt.CltLib;

public class CltLibTest extends AbstractTestCase {

    // +, -
    public void testOperators() {

        Collection<?> collection = null;

        // add
        collection = CltLib.add(null, null);
        printCollection(collection);

        // ["a", "b" , "c"] + ["d", "e"] = ["a", "b" , "c", "d", "e"]
        collection = CltLib.add(CltLib.asCollection("a", "b", "c"), CltLib.asCollection("d", "e"));
        printCollection(collection);

        // ["d", "e"] + ["a", "b" , "c"] = ["d", "e", "a", "b" , "c"]
        collection = CltLib.add(CltLib.asCollection("d", "e"), CltLib.asCollection("a", "b", "c"));
        printCollection(collection);

        collection = CltLib.add(CltLib.asIntegerCollection(1, 3, 5),
                CltLib.asIntegerCollection(9, 11)); // ambiguous
        printCollection(collection);

        collection = CltLib.add(CltLib.asIntegerCollection(9, 11),
                CltLib.asIntegerCollection(1, 3, 5)); // ambiguous
        printCollection(collection);

        // sub
        collection = CltLib.sub(null, null);
        printCollection(collection);

        collection = CltLib.sub(CltLib.asCollection("a", "b", "c"), CltLib.asCollection("b"));
        printCollection(collection);

        collection = CltLib.sub(CltLib.asIntegerCollection(1, 3, 5),
                CltLib.asIntegerCollection(9, 11, 1)); // ambiguous
        printCollection(collection);

    }

    // union
    public void testUnion() {

        printHeader("Test union()");
        Collection<?> collection = null;

        // add
        collection = CltLib.union(null, null);
        printCollection(collection);

        // ["a", "b" , "c"] U ["d", "e"] = ["a", "b", "c", "d", "e"]
        collection = CltLib.union(CltLib.asCollection("a", "b", "c"),
                CltLib.asCollection("d", "e"));
        printCollection(collection);

        // ["a", "b" , "c"] U ["a", "d", "e", "c"] = ["a", "b", "c", "d", "e"]
        collection = CltLib.union(CltLib.asCollection("a", "b", "c"),
                CltLib.asCollection("a", "d", "e", "c"));
        printCollection(collection);

        // ["d", "e"] U ["a", "b" , "c"] = ["d", "e", "a", "b" , "c"]
        collection = CltLib.union(CltLib.asCollection("d", "e"),
                CltLib.asCollection("a", "b", "c"));
        printCollection(collection);

        collection = CltLib.union(CltLib.asIntegerCollection(1, 3, 5),
                CltLib.asIntegerCollection(9, 11)); // ambiguous
        printCollection(collection);

        collection = CltLib.union(CltLib.asIntegerCollection(9, 11),
                CltLib.asIntegerCollection(1, 3, 5)); // ambiguous
        printCollection(collection);

    }

    // copy
    public void testCopy() {

        Collection<?> collection = null;

        collection = CltLib.copy(null);
        printCollection(collection);

        collection = CltLib.copy(CltLib.asIntegerCollection(1, 3, 5)); // ambiguous
        printCollection(collection);

        collection = CltLib.copy(CltLib.asIntegerCollection(1, 3, 5), 2); // ambiguous
        printCollection(collection);

        collection = CltLib.copy(CltLib.asIntegerCollection(1, 3, 5), 1, 2); // next 2
        printCollection(collection);

        collection = CltLib.copy(CltLib.asIntegerCollection(1, 3, 5), 1, 10); // next 10
        printCollection(collection);

        collection = CltLib.copyRange(CltLib.asIntegerList(1, 3, 5), 1, 2); // {1, 2]
        printCollection(collection);

        collection = CltLib.copyRange(CltLib.asIntegerList(1, 3, 5), 1, 10); // {1, 10]
        printCollection(collection);
    }

    // populate: fill, addAll, removeAll, replaceAll
    public void testPopulate() {

        printHeader("Test fill()");

        List<String> list = null;

        CltLib.fill(null, null);

        list = new ArrayList<String>();

        CltLib.fill(list, null);
        printCollection(list);

        CltLib.addAll(list, "A", 3);
        printCollection(list);

        CltLib.addAll(list, "B", 3);
        printCollection(list);

        CltLib.addAll(list, "C", 3);
        printCollection(list);

        CltLib.removeAll(list, new String[] { "B", "C" });
        printCollection(list);

        CltLib.fill(list, "0");
        printCollection(list);

        CltLib.replaceAll(list, "0", "1");
        printCollection(list);

        CltLib.addAll(list, "2", 3);
        CltLib.addAll(list, "3", 3);

        printCollection(list);

        CltLib.replaceAll(list, new String[] { "1", "3" }, new String[] { "X", "Y" });
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

        println("min: " + CltLib.min(collection) + " of " + collection);
        println("max: " + CltLib.max(collection) + " of " + collection);

        collection = new ArrayList<Integer>();
        collection.add(10);
        collection.add(100);
        collection.add(1);
        println("min: " + CltLib.min(collection) + " of " + collection);
        println("max: " + CltLib.max(collection) + " of " + collection);
    }

    // size, count
    public void testCount() {
        printHeader("Test size()");

        println("size: " + CltLib.size(null) + " of null");
        println("count: " + CltLib.count(null) + " of null");

        Collection<Integer> collection = new ArrayList<>();

        println("size: " + CltLib.size(collection) + " of " + collection);
        println("count: " + CltLib.count(collection) + " of " + collection);

        collection.add(null);
        collection.add(100);
        collection.add(null);
        collection.add(5);
        collection.add(10);
        collection.add(-10);
        collection.add(0);
        collection.add(10);

        println("size: " + CltLib.size(collection) + " of " + collection);
        println("count: " + CltLib.count(collection) + " of " + collection);

        println("count(null): " + CltLib.count(collection, null) + " of " + collection);
        println("countNull(): " + CltLib.countNull(collection) + " of " + collection);
        println("countNotNull(): " + CltLib.countNotNull(collection) + " of " + collection);
        println("countNot(null): " + CltLib.countNot(collection, null) + " of " + collection);

        println("count(10): " + CltLib.count(collection, 10) + " of " + collection);

        println("countNot(10): " + CltLib.countNot(collection, 10) + " of " + collection);

        Collection<String> collection2 = new ArrayList<>();

        printHeader("Test count(): String");
        println("count: " + CltLib.count(collection2) + " of " + collection2);

        collection2.add(null);
        collection2.add("ABC");
        collection2.add(null);
        collection2.add("CDE");
        collection2.add("XY");
        collection2.add("QUERTY");
        collection2.add("");
        collection2.add("XY");

        println("count(null): " + CltLib.count(collection2, null) + " of " + collection2);
        println("countNull(): " + CltLib.countNull(collection2) + " of " + collection2);
        println("countNotNull(): " + CltLib.countNotNull(collection2) + " of " + collection2);
        println("countNot(null): " + CltLib.countNot(collection2, null) + " of " + collection2);

        println("count('XY'): " + CltLib.count(collection2, "XY") + " of " + collection2);

        println("countNot('XY'): " + CltLib.countNot(collection2, "XY") + " of " + collection2);
    }

    // sort
    public void testSort() {
        printHeader("Test sort()");

        List<Integer> collection = CltLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        CltLib.sort(collection, null);
        println("after: " + collection);
    }

    // toSortedList
    public void testToSortList() {
        printHeader("Test toSortedList()");

        List<Integer> collection = CltLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CltLib.toSortList(collection, null);
        println("after: " + collection);
    }

    // filter
    public void testFilter() {
        printHeader("Test filter()");

        List<Integer> collection = CltLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CltLib.filter(collection, (x) -> (x != null && x > 0));
        println("after: " + collection);
    }

    // toFilteredList
    public void testToFilterList() {
        printHeader("Test toFilteredList()");

        List<Integer> collection = CltLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CltLib.toFilterList(collection, (x) -> (x != null && x > 0));
        println("after: " + collection);
    }

    // distinct
    public void testDistinct() {
        printHeader("Test distinct()");

        List<Integer> collection = CltLib.asList(null, 100, null, 10, -10, 0);

        println("before: " + collection);
        collection = CltLib.distinct(collection);
        println("after: " + collection);
    }

    // toList
    public void testToList() {

        // integer
        List<Integer> integerList = CltLib.toList(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        printHeader("Integer: toList");
        println(integerList);

        // byte
        List<Byte> byteList = CltLib.toList(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5,
                (byte) 6, (byte) 7, (byte) 8, (byte) 9 });
        printHeader("Byte: toList");
        println(byteList);

        // String
        List<String> stringList = CltLib.toList(new String[] { "ABC", "XYZ" });
        printHeader("String: toList");
        println(stringList);

        // Date
        List<Date> dateList = CltLib.asList(new Date[] { new Date(), new Date() });
        printHeader("Date: toList");
        println(dateList);

    }

    // toSet
    public void testToSet() {

        // integer
        Set<Integer> integerList = CltLib.toSet(new int[] { 9, 1, 2, 3, 4, 2, 3, 5, 6, 8, 8, 7, 8, 9 });
        printHeader("Integer: toSet");
        println(integerList);

        // byte
        Set<Byte> byteList = CltLib.toSet(new byte[] { (byte) 9, (byte) 1, (byte) 2, (byte) 3, (byte) 4,
                (byte) 2, (byte) 3, (byte) 5, (byte) 6, (byte) 8, (byte) 8, (byte) 7, (byte) 8, (byte) 9 });
        printHeader("Byte: toSet");
        println(byteList);

        // String
        Set<String> stringList = CltLib.toSet(new String[] { "ABC", "XYZ" });
        printHeader("String: toSet");
        println(stringList);

        // Date
        Set<Date> dateList = CltLib.toSet(new Date[] { new Date(), new Date() });
        printHeader("Date: toSet");
        println(dateList);

    }

    // asSet
    public void testAsSet() {

        // integer
        Set<Integer> integerList = CltLib.asIntegerSet(9, 1, 2, 3, 4, 2, 3, 5, 6, 8, 8, 7, 8, 9); // ambiguous
        printHeader("Integer: asSet");
        println(integerList);

        // byte
        Set<Byte> byteList = CltLib.asByteSet((byte) 9, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 2,
                (byte) 3, (byte) 5, (byte) 6, (byte) 8, (byte) 8, (byte) 7, (byte) 8, (byte) 9); // ambiguous
        printHeader("Byte: asSet");
        println(byteList);

        // String
        Set<String> stringList = CltLib.asSet("ABC", "XYZ");
        printHeader("String: asSet");
        println(stringList);

        // Date
        Set<Date> dateList = CltLib.asSet(new Date(), new Date());
        printHeader("Date: asSet");
        println(dateList);

    }

    // asList
    public void testAs() {

        // integer
        List<Integer> integerList = CltLib.asIntegerList(1, 2, 3, 4, 5, 6, 7, 8, 9); // ambiguous
        printHeader("Integer: asList");
        println(integerList);

        // byte
        List<Byte> byteList = CltLib.asByteList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6,
                (byte) 7, (byte) 8, (byte) 9); // ambiguous
        printHeader("Byte: asList");
        println(byteList);

        // String
        List<String> stringList = CltLib.asList("ABC", "XYZ");
        printHeader("String: asList");
        println(stringList);

        // Date
        List<Date> dateList = CltLib.asList(new Date(), new Date());
        printHeader("Date: asList");
        println(dateList);
    }

    private <T> Collection<T> toCollection(T[] array) {
        return CltLib.toCollection(array);
    }

    private <T> List<T> toList(T[] array) {
        return CltLib.toList(array);
    }

}
