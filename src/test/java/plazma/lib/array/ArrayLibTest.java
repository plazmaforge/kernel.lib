package plazma.lib.array;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import plazma.lib.AbstractTestCase;
import plazma.lib.ArithmeticContext;
import plazma.lib.OverflowMode;

public class ArrayLibTest extends AbstractTestCase {

    public void testBad() {
        printHeader("TEST MAX");
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        double z = x * y;

        BigDecimal bx = new BigDecimal(x);
        BigDecimal by = new BigDecimal(y);

        BigDecimal bz = bx.multiply(by);

        println(bz);

        println(x);
        println(y);
        println(x * y);

        // println(z);

        // println(Integer.MAX_VALUE * Integer.MAX_VALUE);
        println("start");
        println(2147483647 * 2147483646);
        println("end");

        println(Arrays.stream(new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE }).sum());

        println("pow");
        double c = Math.pow(2147483647, 2);

        println(c);

        double x2 = Integer.MAX_VALUE;
        double y2 = Integer.MAX_VALUE;

        println(x2 * y2);

        println("FLOAT!");
        double zzz = Float.MAX_VALUE * Integer.MAX_VALUE;
        println(zzz);
        println(Float.MAX_VALUE * Integer.MAX_VALUE);

        double m = meanOf(12L, 13L, 14L);

        println("mean-1: " + meanOf(12L, 13L, 14L));
        println("mean-2: " + meanOf_2(12L, 13L, 14L));
        println("mean-g: " + geometricMean(new double[] { 12L, 13L, 14L }));
    }

    public static double meanOf(long... values) {
        // checkArgument(values.length > 0);
        double mean = values[0];
        for (int index = 1; index < values.length; index++) {
            double value = values[index];
            mean += (value - mean) / (index + 1);

            // if (isFinite(value) && isFinite(mean)) {
            // // Art of Computer Programming vol. 2, Knuth, 4.2.2, (15)
            // mean += (value - mean) / (index + 1);
            // } else {
            // mean = calculateNewMeanNonFinite(mean, value);
            // }

        }
        return mean;
    }

    public static double geometricMean(double[] values) {
        int i;
        double product = 0.0;

        // if(values.length == 0)
        // throw new StatisticException("array is empty");

        for (i = 0; i < values.length; i++)
            product *= values[i];

        return Math.pow(product, (1 / values.length));
    }

    public static double meanOf_2(long... values) {
        double mean = values[0];
        for (int index = 1; index < values.length; index++) {
            double value = values[index];
            mean += value;

            // if (isFinite(value) && isFinite(mean)) {
            // // Art of Computer Programming vol. 2, Knuth, 4.2.2, (15)
            // mean += (value - mean) / (index + 1);
            // } else {
            // mean = calculateNewMeanNonFinite(mean, value);
            // }

        }
        return mean / values.length;
    }

    // +, -
    public void testOperators() {

        printHeader("Test Operators");
        Object[] array = null;

        // add
        array = ArrayLib.add((Object[]) null, (Object[]) null);
        printArray(array);

        // ["a", "b" , "c"] + ["d", "e"] = ["a", "b", "c", "d", "e"]
        array = ArrayLib.add(new String[] { "a", "b", "c" }, new String[] { "d", "e" });
        printArray(array);

        // ["d", "e"] + ["a", "b" , "c"] = ["d", "e", "a", "b" , "c"]
        array = ArrayLib.add(new String[] { "d", "e" }, new String[] { "a", "b", "c" });
        printArray(array);

        array = ArrayLib.add(new Integer[] { 1, 3, 5 }, new Integer[] { 9, 11 });
        printArray(array);

        array = ArrayLib.add(new Integer[] { 9, 11 }, new Integer[] { 1, 3, 5 });
        printArray(array);

        // sub
        array = ArrayLib.sub((Object[]) null, (Object[]) null);
        printArray(array);

        array = ArrayLib.sub(new String[] { "a", "b", "c" }, new String[] { "b" });
        printArray(array);

        array = ArrayLib.sub(new Integer[] { 1, 3, 5 }, new Integer[] { 9, 11, 1 });
        printArray(array);

    }

    // union
    public void testUnion() {

        printHeader("Test union()");
        Object[] array = null;

        // add
        array = ArrayLib.union((Object[]) null, (Object[]) null);
        printArray(array);

        // ["a", "b" , "c"] U ["d", "e"] = ["a", "b", "c", "d", "e"]
        array = ArrayLib.union(new String[] { "a", "b", "c" }, new String[] { "d", "e" });
        printArray(array);

        // ["a", "b" , "c"] U ["a", "d", "e", "c"] = ["a", "b", "c", "d", "e"]
        array = ArrayLib.union(new String[] { "a", "b", "c" }, new String[] { "a", "d", "e", "c" });
        printArray(array);

        // ["d", "e"] U ["a", "b" , "c"] = ["d", "e", "a", "b" , "c"]
        array = ArrayLib.union(new String[] { "d", "e" }, new String[] { "a", "b", "c" });
        printArray(array);

        array = ArrayLib.union(new Integer[] { 1, 3, 5 }, new Integer[] { 9, 11 });
        printArray(array);

        array = ArrayLib.union(new Integer[] { 9, 11 }, new Integer[] { 1, 3, 5 });
        printArray(array);

    }

    // intersection
    public void testIntersection() {

        printHeader("Test intersection()");
        Object[] array = null;

        // add
        array = ArrayLib.intersection((Object[]) null, (Object[]) null);
        printArray(array);

        // ["a", "b" , "c"] U ["d", "e"] = ["a", "b", "c", "d", "e"]
        array = ArrayLib.intersection(new String[] { "a", "b", "c" }, new String[] { "d", "e" });
        printArray(array);

        // ["a", "b" , "c"] U ["a", "d", "e", "c"] = ["a", "b", "c", "d", "e"]
        array = ArrayLib.intersection(new String[] { "a", "b", "c" }, new String[] { "a", "d", "e", "c" });
        printArray(array);

        // ["d", "e"] U ["a", "b" , "c"] = ["d", "e", "a", "b" , "c"]
        array = ArrayLib.intersection(new String[] { "d", "e" }, new String[] { "a", "b", "c" });
        printArray(array);

        array = ArrayLib.intersection(new Integer[] { 1, 3, 5 }, new Integer[] { 9, 11, 3, 1 });
        printArray(array);

        array = ArrayLib.intersection(new Integer[] { 9, 11 }, new Integer[] { 1, 3, 5, 9, 11 });
        printArray(array);

    }

    // copy
    public void testCopy() {

        printHeader("Test copy");
        Object[] array = null;

        array = ArrayLib.copy((Object[]) null);
        printArray(array);

        array = ArrayLib.copy(new Integer[] { 1, 3, 5 });
        printArray(array);

        array = ArrayLib.copy(new Integer[] { 1, 3, 5 }, 2);
        printArray(array);

        array = ArrayLib.copy(new Integer[] { 1, 3, 5 }, 1, 2); // next 2
        printArray(array);

        array = ArrayLib.copy(new Integer[] { 1, 3, 5 }, 1, 10); // next 10
        printArray(array);

        array = ArrayLib.copyRange(new Integer[] { 1, 3, 5 }, 1, 2); // {1, 2]
        printArray(array);

        array = ArrayLib.copyRange(new Integer[] { 1, 3, 5 }, 1, 10); // {1, 10]
        printArray(array);
    }

    // populate: fill, addAll, removeAll, replaceAll
    public void testPopulate() {

        printHeader("Test populate");

        String[] array = null;

        ArrayLib.fill(null, null);

        array = new String[10];

        ArrayLib.fill(array, null);
        printArray(array);

        ArrayLib.fill(array, "A");
        printArray(array);

        ArrayLib.fill(array, "B", 1, 3);
        printArray(array);

        ArrayLib.replaceAll(array, new String[] { "B", "3" }, new String[] { "X", "Y" });
        printArray(array);
    }

    public void testPopulate2() {

        printHeader("Test populate2");

        Number[] array = null;

        ArrayLib.fill(null, null);

        array = new Number[10];

        ArrayLib.fill(array, null);
        printArray(array);

        ArrayLib.fill(array, 100);
        printArray(array);

        ArrayLib.fill(array, -17, 1, 3);
        printArray(array);

        ArrayLib.replaceAll(array, new String[] { "1", "3" }, new String[] { "X", "Y" });
        printArray(array);
    }

    // min, max
    public void testMinMax() {
        printHeader("Test min()");

        Integer[] array = new Integer[] { null, 100, null, 10, -10, 0 };

        println("min: " + ArrayLib.min(array) + " of " + toString(array));
        println("max: " + ArrayLib.max(array) + " of " + toString(array));

        array = new Integer[] { 10, 100, 1 };

        println("min: " + ArrayLib.min(array) + " of " + toString(array));
        println("max: " + ArrayLib.max(array) + " of " + toString(array));
    }

    // size, count
    public void testCount() {
        printHeader("Test size()");

        println("size: " + ArrayLib.size((Object[]) null) + " of null");
        println("count: " + ArrayLib.count((Object[]) null) + " of null");

        Integer[] array = new Integer[] {};

        println("size: " + ArrayLib.size(array) + " of " + toString(array));
        println("count: " + ArrayLib.count(array) + " of " + toString(array));

        array = new Integer[] { null, 100, null, 5, 10, -10, 0, 10 };

        println("size: " + ArrayLib.size(array) + " of " + toString(array));
        println("count: " + ArrayLib.count(array) + " of " + toString(array));

        println("count(null): " + ArrayLib.count(array, null) + " of " + toString(array));
        println("countNull(): " + ArrayLib.countNull(array) + " of " + toString(array));
        println("countNotNull(): " + ArrayLib.countNotNull(array) + " of " + toString(array));
        println("countNot(null): " + ArrayLib.countNot(array, null) + " of " + toString(array));

        println("count(10): " + ArrayLib.count(array, 10) + " of " + toString(array));

        println("countNot(10): " + ArrayLib.countNot(array, 10) + " of " + toString(array));

        String[] array2 = new String[] {};

        printHeader("Test count(): String[]");
        println("count: " + ArrayLib.count(array2) + " of " + toString(array2));

        array2 = new String[] { null, "ABC", null, "CDE", "XY", "QUERTY", "", "XY" };

        println("count(null): " + ArrayLib.count(array2, null) + " of " + toString(array2));
        println("countNull(): " + ArrayLib.countNull(array2) + " of " + toString(array2));
        println("countNotNull(): " + ArrayLib.countNotNull(array2) + " of " + toString(array2));
        println("countNot(null): " + ArrayLib.countNot(array2, null) + " of " + toString(array2));

        println("count('XY'): " + ArrayLib.count(array2, "XY") + " of " + toString(array2));

        println("countNot('XY'): " + ArrayLib.countNot(array2, "XY") + " of " + toString(array2));

        int[] intArray = new int[] {};

        printHeader("Test count(): int[]");

        println("count: " + ArrayLib.count(intArray) + " of " + toString(intArray));
    }

    // sum
    public void testSum() {
        printHeader("Test sum()");

        int[] array = new int[] { 10, 10, 10, 10, 10, 10 };
        println("int: sum    : " + ArrayLib.sumInt(array) + " of " + toString(array));

        array = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                Integer.MAX_VALUE };
        println("int: sum    : " + ArrayLib.sumInt(array) + " of " + toString(array));
        println("double: sum : " + ArrayLib.sum(array) + " of " + toString(array));

        ArithmeticContext context = new ArithmeticContext(OverflowMode.EXCEPTION);
        try {
            ArrayLib.sumInt(array, context);
            assertTrue("Incorrect ArrayLib.sum with OverflowMode.EXCEPTION", true);
        } catch (ArithmeticException e) {
        }

        // array = new int[Integer.MAX_VALUE - 1000000000];
        long[] long_array = new long[] { Long.MAX_VALUE, Long.MAX_VALUE };
        // println("long: sum : " + ArrayLib.sumLong(long_array) + " of " +
        // toString(long_array));

        println(Double.MAX_VALUE);

        float[] float_array = new float[] { Float.MAX_VALUE, Float.MAX_VALUE };
        println("double: sum  : " + ArrayLib.sum(float_array) + " of " + toString(float_array));

    }

    // sort
    public void testSort() {
        printHeader("Test sort()");

        Integer[] array = new Integer[] { null, 100, null, 10, -10, 0 };

        println("before: " + toString(array));
        ArrayLib.sort(array, null);
        println("after: " + toString(array));

    }

    // filter
    public void testFilter() {
        printHeader("Test filter()");

        Integer[] array = new Integer[] { null, 100, null, 10, -10, 0 };

        println("before: " + toString(array));
        array = ArrayLib.filter(array, (x) -> (x != null && x > 0));
        println("after: " + toString(array));

    }

    // distinct
    public void testDistinct() {
        printHeader("Test distinct()");

        byte[] byteArray = new byte[] { 100, 1, 2, 120, 2, 1, 3, 3, 1 };
        assertTrue(ArrayLib.equals(new byte[] { 100, 1, 2, 120, 3 }, ArrayLib.distinct(byteArray)));

        char[] charArray = new char[] { 100, 1, 2, 120, 2, 1, 3, 3, 1 };
        assertTrue(ArrayLib.equals(new char[] { 100, 1, 2, 120, 3 }, ArrayLib.distinct(charArray)));

        short[] shortArray = new short[] { 100, 1, 2, 120, 2, 1, 3, 3, 1 };
        assertTrue(ArrayLib.equals(new short[] { 100, 1, 2, 120, 3 }, ArrayLib.distinct(shortArray)));

        int[] intArray = new int[] { 100, 1, 2, 120, 2, 1, 3, 3, 1 };
        assertTrue(ArrayLib.equals(new int[] { 100, 1, 2, 120, 3 }, ArrayLib.distinct(intArray)));

        long[] longArray = new long[] { 100, 1, 2, 120, 2, 1, 3, 3, 1 };
        assertTrue(ArrayLib.equals(new long[] { 100, 1, 2, 120, 3 }, ArrayLib.distinct(longArray)));

        float[] floatArray = new float[] { 100.0f, 1.1f, 2.0f, 120.0f, 2.0f, 1.1f, 3.0f, 3.0f, 1.1f };
        assertTrue(ArrayLib.equals(new float[] { 100.0f, 1.1f, 2.0f, 120.0f, 3.0f }, ArrayLib.distinct(floatArray)));

        double[] doubleArray = new double[] { 100.0d, 1.1d, 2.0d, 120.0d, 2.0d, 1.1d, 3.0d, 3.0d, 1.1d };
        assertTrue(ArrayLib.equals(new double[] { 100.0d, 1.1d, 2.0d, 120.0d, 3.0d }, ArrayLib.distinct(doubleArray)));

        Integer[] array = new Integer[] { null, 100, null, 10, -10, 0 };

        println("before: " + toString(array));
        array = ArrayLib.distinct(array);
        println("after: " + toString(array));

        printHeader("Test int[]: distinct()");
        intArray = new int[] { 0, 100, 0, 10, -10, 0 };

        println("before: " + toString(intArray));
        intArray = ArrayLib.distinct(intArray);
        println("after: " + toString(intArray));

        int[] randomArray = ArrayLib.randomIntArray(1000000);

        long time = 0;
        long time1 = 0;
        long time2 = 0;
        long time3 = 0;

        for (int i = 0; i < 10; i++) {

            time = System.currentTimeMillis();
            ArrayLib.distinct(randomArray);
            time = System.currentTimeMillis() - time;
            time1 += time;

            time = System.currentTimeMillis();
            ArrayLib.distinct2(randomArray);
            time = System.currentTimeMillis() - time;
            time2 += time;

        }

        System.out.println("time1=" + time1);
        System.out.println("time2=" + time2);

    }

    // asList
    public void testAs() {

        // integer
        int[] integerArray = ArrayLib.asArray(1, 2, 3, 4, 5, 6, 7, 8, 9);
        printHeader("Integer: asArray");
        printArray(integerArray);

        // byte
        byte[] byteArray = ArrayLib.asArray((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7,
                (byte) 8, (byte) 9);
        printHeader("Byte: asArray");
        printArray(byteArray);

        // String
        String[] stringArray = ArrayLib.asArray("ABC", "XYZ");
        printHeader("String: asArray");
        printArray(stringArray);

        // Date
        Date[] dateArray = ArrayLib.asArray(Date.class, new Date(), new Date());
        printHeader("Date: asArray");
        printArray(dateArray);

        Date d1 = new Date();
        Date d2 = new Date();

        long l1 = d1.getTime();
        long l2 = d2.getTime();

        printHeader("DATA");
        println(l1);
        println(l2);
    }

    // reverse
    public void testReverse() {

        Integer[] array = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        ArrayLib.reverse(array);

        printHeader("Reverse array");
        printArray(array);
    }

    // trunc
    public void testResize() {

        // trunc: 3
        Integer[] inputIntegerArray = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Integer[] outputIntegerArray = ArrayLib.trunc(inputIntegerArray, 3);
        assertNotNull(outputIntegerArray);
        assertEquals(outputIntegerArray.length, 3);

        printHeader("Resize: trunc(array, 3)");
        printArray(outputIntegerArray);

        // trunc: 0
        outputIntegerArray = ArrayLib.trunc(inputIntegerArray, 0);
        assertNotNull(outputIntegerArray);
        assertEquals(outputIntegerArray.length, 0);

        // trunc: -3
        try {
            outputIntegerArray = ArrayLib.trunc(inputIntegerArray, -3);
            assertTrue("Incorrect trunc(array, -3)", false);
        } catch (RuntimeException e) {
            println("Correct trunc(array, -3)");
        }

        // trunc: 30
        try {
            outputIntegerArray = ArrayLib.trunc(inputIntegerArray, 30);
            assertTrue("Incorrect trunc(array, 30)", false);
        } catch (RuntimeException e) {
            println("Correct trunc(array, 30)");
        }

        // trunc: null
        inputIntegerArray = null;
        outputIntegerArray = ArrayLib.trunc(inputIntegerArray, 3);
        assertNull(outputIntegerArray);

        // resize
        inputIntegerArray = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        outputIntegerArray = ArrayLib.resize(inputIntegerArray, 30);
        assertNotNull(outputIntegerArray);
        assertEquals(outputIntegerArray.length, 30);

        // resize and fill expand space
        inputIntegerArray = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        outputIntegerArray = ArrayLib.resize(inputIntegerArray, 30, 777);
        assertNotNull(outputIntegerArray);
        assertEquals(outputIntegerArray.length, 30);

        for (int i = 9; i < 30; i++) {
            assertEquals(outputIntegerArray[i], new Integer(777));
        }

        int[] inputIntArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] outputIntArray = ArrayLib.trunc(inputIntArray, 3);
        assertNotNull(outputIntArray);
        assertEquals(outputIntArray.length, 3);
    }

    public void testRangeDoubleArray() {

        double[] array = ArrayLib.rangeDoubleArray(-10, 10, 2);
        printHeader("Range double array: step=2");
        printArray(array);

        array = ArrayLib.rangeDoubleArray(-10, 10);
        printHeader("Range double array: step=1, by default");
        printArray(array);

        array = ArrayLib.pointDoubleArray(0, 10, 5);
        printHeader("Point double array: count=5");
        printArray(array);

        array = ArrayLib.evaluateDoubleArray(array, (x) -> (x * x));
        printHeader("Evaluate double array: count=5");
        printArray(array);
    }
}
