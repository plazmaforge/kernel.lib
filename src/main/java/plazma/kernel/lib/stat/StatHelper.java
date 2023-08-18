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

package plazma.kernel.lib.stat;

import java.util.Arrays;

import plazma.kernel.lib.ArithmeticContext;
import plazma.kernel.lib.ArithmeticEnvironment;
import plazma.kernel.lib.math.MathLib;

public class StatHelper {

    // https://en.wikipedia.org/wiki/Arithmetic_mean
    // https://en.wikipedia.org/wiki/Geometric_mean
    // https://en.wikipedia.org/wiki/Harmonic_mean

    // http://mathworld.wolfram.com/Mean.html
    // http://mathworld.wolfram.com/ArithmeticMean.html
    // http://mathworld.wolfram.com/GeometricMean.html
    // http://mathworld.wolfram.com/HarmonicMean.html
    // http://mathworld.wolfram.com/Root-Mean-Square.html

    // https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance
    // http://mathworld.wolfram.com/SampleVariance.html
    // http://mathworld.wolfram.com/Variance.html

    // https://github.com/google/guava/blob/master/guava/src/com/google/common/math
    // JStats (?)

    // https://docs.python.org/3/library/statistics.html
    // https://github.com/python/cpython/blob/master/Lib/statistics.py

    private StatHelper() {
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

    // int: x + y = r
    private static boolean isOverflowAdd(int x, int y, int r) {
        return (((x ^ r) & (y ^ r)) < 0);
    }

    // long: x + y = r
    private static boolean isOverflowAdd(long x, long y, long r) {
        return (((x ^ r) & (y ^ r)) < 0);
    }

    ////

    /**
     * Returns mean by MeanType: ARITHMETIC, GEOMETRIC, HARMONIC...
     * 
     * @param meanType
     * @param array
     * @param context
     * @return
     */
    static double _mean(MeanType meanType, double[] array, ArithmeticContext context) {
        if (meanType == null) {
            return _mean(array, context);
        }

        if (meanType == MeanType.ARITHMETIC) {
            return _arithmeticMean(array, context);
        } else if (meanType == MeanType.GEOMETRIC) {
            return _geometricMean(array, context);
        } else if (meanType == MeanType.HARMONIC) {
            return _harmonicMean(array, context);
        } else if (meanType == MeanType.SQRT) {
            return _sqrtMean(array, context);
        } else if (meanType == MeanType.MEDIAN) {
            return _median(array/* , context */);
        } else if (meanType == MeanType.MEDIAN_LOW) {
            return _medianLow(array/* , context */);
        } else if (meanType == MeanType.MEDIAN_HIGH) {
            return _medianHigh(array/* , context */);
        } else if (meanType == MeanType.MIDRANGE) {
            return _midrange(array/* , context */);
        }

        return 0;
    }

    ////

    static double _mean(double[] array, ArithmeticContext context) {
        return _arithmeticMean(array, context);
    }

    // (x1 + x2 + ... + xn) / n
    // https://en.wikipedia.org/wiki/Arithmetic_mean
    // http://mathworld.wolfram.com/ArithmeticMean.html

    static double _arithmeticMean(double[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = _sum(array, context);
        return sum / length;
    }

    // (x1 * x2 * ... * xn) ^ (1/n)
    // https://en.wikipedia.org/wiki/Geometric_mean
    // http://mathworld.wolfram.com/GeometricMean.html

    static double _geometricMean(double[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double mul = _mul(array, context);
        return Math.pow(mul, (1.0 / length));
    }

    // n / (1/x1 + 1/x2 + ... 1/xn)
    // https://en.wikipedia.org/wiki/Harmonic_mean
    // http://mathworld.wolfram.com/HarmonicMean.html

    static double _harmonicMean(double[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double[] tmpArray = new double[length];
        for (int i = 0; i < length; i++) {
            tmpArray[i] = 1 / array[i]; // TODO: Devision by Zero ?
        }
        double sum = _sum(tmpArray, context);
        return length / sum;
    }

    // http://mathworld.wolfram.com/Root-Mean-Square.html
    // https://www.geeksforgeeks.org/program-to-calculate-root-mean-square/

    static double _sqrtMean(double[] array, ArithmeticContext context) {

        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }

        double sum = _sumSqr(array, context);
        double mean = sum / length;
        return Math.sqrt(mean);
    }

    /**
     * Return the median (middle value) of numeric data
     * 
     * @param an array of doubles
     * @return the median of the values array
     */
    static double _median(double[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return array[0];
        }

        int index = length / 2;

        // make a new copy of the array
        double[] newArray = _toSort(array);

        if ((length % 2) == 0) {
            // handle special case for even length arrays
            return (newArray[index - 1] + newArray[index]) / 2;
        } else {
            return newArray[index];
        }
    }

    /**
     * Return the low median of numeric data
     * 
     * @param array
     * @return
     */
    static double _medianLow(double[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return array[0];
        }

        int index = length / 2;

        // make a new copy of the array
        double[] newArray = _toSort(array);

        if ((length % 2) == 0) {
            // handle special case for even length arrays
            return newArray[index - 1];
        } else {
            return newArray[index];
        }
    }

    /**
     * Return the high median of data
     * 
     * @param array
     * @return
     */
    static double _medianHigh(double[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return array[0];
        }

        int index = length / 2;

        // make a new copy of the array
        double[] newArray = _toSort(array);

        return newArray[index];
    }

    /**
     * Return the midrange (the value exactly between the lowest and highest values)
     * from an array of doubles.
     * 
     * @param an array of doubles
     * @return the midrange of the values array
     */
    static double _midrange(double[] array) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double midrange = 0.0;
        if (length == 1) {
            return array[0];
        }

        double high = array[0];
        double low = array[0];
        for (int i = 1; i < length; i++) {
            if (array[i] > high) {
                high = array[i];
            }
            if (array[i] < low) {
                low = array[i];
            }
        }
        midrange = (high + low) / 2;
        return midrange;
    }

    //

    static double _sumSqr(double[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < length; i++) {
            double value = array[i];
            value = _sqr(value); // value ^ 2: // TODO: check Infinity as Overflow
            sum += value; // TODO: check Infinity as Overflow
            // sum = _sum(sum, value, context);
        }
        return sum;
    }

    static double _sumPow(double[] array, double n, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < length; i++) {
            double value = array[i];
            value = _pow(value, n); // value ^ n: // TODO: check Infinity as Overflow
            sum += value; // TODO: check Infinity as Overflow
        }
        return sum;
    }

    //

    static double _sumDelta(double[] array, double mean, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < length; i++) {
            double value = array[i];
            value = value - mean; // TODO: check Infinity as Overflow
            sum += value; // TODO: check Infinity as Overflow
            // sum = _sum(sum, value, context);
        }
        return sum;
    }

    static double _sumDeltaSqr(double[] array, double mean, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < length; i++) {
            double value = array[i];
            value = _sqr(value - mean); // value ^ 2: // TODO: check Infinity as Overflow
            sum += value; // TODO: check Infinity as Overflow
            // sum = _sum(sum, value, context);
        }
        return sum;
    }

    //

    // https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance
    // http://mathworld.wolfram.com/SampleVariance.html
    // http://mathworld.wolfram.com/Variance.html

    // special sum for variance
    static double _ss(double[] array, Double mean, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }

        // calculate mean
        if (mean == null) {
            mean = _mean(array, context);
        }
        double total = _sumDeltaSqr(array, mean, context); // sum( (x - delta) ^ 2)
        double total2 = _sumDelta(array, mean, context); // sum( (x - delta) )
        total -= _sqr(total2) / length;
        return total;
    }

    // variance

    /**
     * Return the sample variance of data
     * 
     * @param array
     * @param mean
     * @param context
     * @return
     */
    static double _variance(double[] array, Double mean, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;

        // TODO
        // if (length == 0) {
        // return 0;
        // }

        if (length < 2) {
            throw new IllegalArgumentException("Variance requires at least two data points");
        }

        double ss = _ss(array, mean, context);
        return ss / (length - 1);
    }

    // population variance

    /**
     * Return the population variance of data
     * 
     * @param array
     * @param mean
     * @param context
     * @return
     */
    static double _pvariance(double[] array, Double mean, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;

        // TODO
        // if (length == 0) {
        // return 0;
        // }

        if (length < 1) {
            throw new IllegalArgumentException("Population variance requires at least one data point");
        }

        double ss = _ss(array, mean, context);
        return ss / length;
    }

    /**
     * Return the square root of the sample variance
     * 
     * @param array
     * @param mean
     * @param context
     * @return
     */
    static double _stdev(double[] array, Double mean, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        double variance = _variance(array, mean, context);
        return _sqrt(variance);
    }

    /**
     * Return the square root of the population variance.
     * 
     * @param array
     * @param mean
     * @param context
     * @return
     */
    static double _pstdev(double[] array, Double mean, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        double variance = _pvariance(array, mean, context);
        return _sqrt(variance);
    }

    ////

    static double _sum(double[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < length; i++) {
            double value = array[i];
            sum += value; // TODO: check Infinity as Overflow
            // sum = _sum(sum, value, context);
        }
        return sum;
    }

    static double _mul(double[] array, ArithmeticContext context) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        double mul = array[0];
        for (int i = 1; i < length; i++) {
            double value = array[i];
            mul *= value; // TODO: check Infinity as Overflow
            // mul = _mul(sum, value, context);
        }
        return mul;
    }

    static double _product(double[] array, ArithmeticContext context) {
        return _mul(array, context);
    }

    // long
    private static long _sum(long sum, long value, ArithmeticContext context) {
        long result = sum + value;
        if (isOverflowAdd(sum, value, result) && isOverflowException(context)) {
            throw new ArithmeticException("Integer overflow");
        }
        return result;
    }

    private static long _mul(long mul, long value, ArithmeticContext context) {
        long result = mul * value;
        if (isOverflowAdd(mul, value, result) && isOverflowException(context)) {
            throw new ArithmeticException("Long overflow");
        }
        return result;
    }

    //

    private static double _pow(double x, double y) {
        return MathLib.pow(x, y);
    }

    private static double _sqr(double x) {
        return MathLib.sqr(x);
    }

    private static double _cbr(double x) {
        return MathLib.cbr(x);
    }

    private static double _sqrt(double x) {
        return MathLib.sqrt(x);
    }

    private static double _cbrt(double x) {
        return MathLib.cbrt(x);
    }

    //

    private static double[] _toSort(double[] array) {
        double[] newArray = new double[array.length];
        // make a new copy of the array
        System.arraycopy(array, 0, newArray, 0, array.length);
        Arrays.sort(newArray);
        return newArray;
    }

}
