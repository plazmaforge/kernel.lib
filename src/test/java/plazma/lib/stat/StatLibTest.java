package plazma.lib.stat;

import java.math.BigDecimal;

import plazma.lib.AbstractTestCase;
import plazma.lib.array.ArrayLib;
import plazma.lib.stat.MeanType;
import plazma.lib.stat.StatLib;

public class StatLibTest extends AbstractTestCase {

    // https://github.com/python/cpython/blob/master/Lib/statistics.py

    public void testMean() {
        double[] array = new double[] { 20.78, 23.45, 33.65, 21.6, 17.97, 31.54 };

        double arithmeticMean = StatLib.arithmeticMean(array);
        double geometricMean = StatLib.geometricMean(array);
        double harmonicMean = StatLib.harmonicMean(array);
        double sqrtMean = StatLib.sqrtMean(array);
        double median = StatLib.median(array);
        double medianLow = StatLib.medianLow(array);
        double medianHigh = StatLib.medianHigh(array);
        double midrange = StatLib.midrange(array);

        double arithmeticMeanDelta = delta(array, arithmeticMean);
        double geometricMeanDelta = delta(array, geometricMean);
        double harmonicMeanDelta = delta(array, harmonicMean);
        double sqrtMeanDelta = delta(array, sqrtMean);
        double medianDelta = delta(array, median);
        double medianLowDelta = delta(array, medianLow);
        double medianHighDelta = delta(array, medianHigh);
        double midrangeDelta = delta(array, midrange);

        printHeader("Statistics");
        println(ArrayLib.toString(array));
        printLine();

        println("arithmeticMean = " + arithmeticMean + "\t delta = " + arithmeticMeanDelta);
        println("geometricMean  = " + geometricMean + "\t delta = " + geometricMeanDelta);
        println("harmonicMean   = " + harmonicMean + "\t delta = " + harmonicMeanDelta);
        println("sqrtMean       = " + sqrtMean + "\t delta = " + sqrtMeanDelta);
        println("median         = " + median + "\t\t\t delta = " + medianDelta);
        println("medianLow      = " + medianLow + "\t\t\t delta = " + medianLowDelta);
        println("medianHigh     = " + medianHigh + "\t\t\t delta = " + medianHighDelta);
        println("midrange       = " + midrange + "\t\t\t delta = " + midrangeDelta);

    }

    public void testMedian() {

        double[] array = null;
        double median = 0;
        double medianLow = 0;
        double medianHigh = 0;

        // median
        array = new double[] { 1, 3, 5 };
        median = StatLib.median(array);
        medianLow = StatLib.medianLow(array);
        medianHigh = StatLib.medianHigh(array);
        assertEquals(median, 3.0);
        assertEquals(medianLow, 3.0);
        assertEquals(medianHigh, 3.0);

        printHeader("Statistics: median");
        println(ArrayLib.toString(array));
        printLine();
        println("median         = " + median);
        println("medianLow      = " + medianLow);
        println("medianHigh     = " + medianHigh);

        // median
        array = new double[] { 1, 3, 5, 7 };
        median = StatLib.median(array);
        medianLow = StatLib.medianLow(array);
        medianHigh = StatLib.medianHigh(array);
        assertEquals(median, 4.0);
        assertEquals(medianLow, 3.0);
        assertEquals(medianHigh, 5.0);

        printHeader("Statistics: median");
        println(ArrayLib.toString(array));
        printLine();
        println("median         = " + median);
        println("medianLow      = " + medianLow);
        println("medianHigh     = " + medianHigh);
    }

    public void testVariance() {

        double[] array = null;
        double value = 0;

        // variance
        array = new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        value = StatLib.variance(array);
        assertEquals(value, 7.5);

        printHeader("Statistics: variance");
        println(ArrayLib.toString(array));
        printLine();
        println("variance         = " + value);

        // variance
        array = new double[] { 2.75, 1.75, 1.25, 0.25, 0.5, 1.25, 3.5 };
        value = StatLib.variance(array);
        assertEquals(value, 1.3720238095238095);

        printHeader("Statistics: variance");
        println(ArrayLib.toString(array));
        printLine();
        println("variance         = " + value);

        // variance
        array = new double[] { 27.5, 30.25, 30.25, 34.5, 41.75 };
        value = StatLib.variance(array);
        assertEquals(value, 31.01875);

        printHeader("Statistics: variance");
        println(ArrayLib.toString(array));
        printLine();
        println("variance         = " + value);

        // population variance
        array = new double[] { 0.0, 0.25, 0.25, 1.25, 1.5, 1.75, 2.75, 3.25 };
        value = StatLib.pvariance(array);
        assertEquals(value, 1.25);

        printHeader("Statistics: pvariance");
        println(ArrayLib.toString(array));
        printLine();
        println("pvariance         = " + value);

        // population variance
        array = new double[] { 27.5, 30.25, 30.25, 34.5, 41.75 };
        value = StatLib.pvariance(array);
        assertEquals(value, 24.815);

        printHeader("Statistics: pvariance");
        println(ArrayLib.toString(array));
        printLine();
        println("pvariance         = " + value);

    }

    public void testStdev() {

        double[] array = null;
        double value = 0;

        // stdev
        array = new double[] { 1.5, 2.5, 2.5, 2.75, 3.25, 4.75 };
        value = StatLib.stdev(array);
        assertEquals(value, 1.0810874155219827);

        printHeader("Statistics: stdev");
        println(ArrayLib.toString(array));
        printLine();
        println("stdev             = " + value);

        // pstdev
        array = new double[] { 1.5, 2.5, 2.5, 2.75, 3.25, 4.75 };
        value = StatLib.pstdev(array);
        assertEquals(value, 0.986893273527251);

        printHeader("Statistics: pstdev");
        println(ArrayLib.toString(array));
        printLine();
        println("pstdev            = " + value);

    }

    public void testMeanType() {
        double[] array = new double[] { 20.78, 23.45, 33.65, 21.6, 17.97, 31.54 };

        printHeader("Statistics: MeanTypes");
        println(ArrayLib.toString(array));
        printLine();

        MeanType[] meanTypes = MeanType.values();
        for (MeanType meanType : meanTypes) {
            println(fill(meanType, 20) + " = " + StatLib.mean(meanType, array));
        }
    }

    private double delta(double[] array, double x) {
        if (array == null || array.length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += Math.abs((x - array[i]));
        }
        return sum / array.length;
    }

    public void testS() {
        // byte[] array = new byte[Integer.MAX_VALUE];

        System.out.println();
        System.out.println("MAX FLOAT");
        System.out.println(Float.MAX_VALUE);

        System.out.println(Float.MAX_VALUE + Float.MAX_VALUE);

        int value = Integer.MAX_VALUE;
        int count = Integer.MAX_VALUE;

        float sum_f = 0;
        float avg_f = 0;
        for (int i = 0; i < count; i++) {
            sum_f += value;
        }
        avg_f = sum_f / count;
        System.out.println();
        System.out.println("SUM FLOAT");
        System.out.println(sum_f);
        System.out.println(avg_f);

        double sum_d = 0;
        double avg_d = 0;
        for (int i = 0; i < count; i++) {
            sum_d += value;
        }
        avg_d = sum_d / count;
        System.out.println();
        System.out.println("SUM DOUBLE");
        System.out.println(sum_d);
        System.out.println(avg_d);

        System.out.println();
        System.out.println("SUM DELTA");
        System.out.println(sum_d - sum_f);
        System.out.println(avg_d - avg_f);

        BigDecimal sum_b = new BigDecimal(0);
        // value = Byte.MAX_VALUE;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum_b = sum_b.add(new BigDecimal(value));
        }
        System.out.println();
        System.out.println("SUM BIG");
        System.out.println(sum_b);
        System.out.println(sum_b.doubleValue());

    }
}
