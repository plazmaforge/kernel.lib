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

import java.math.BigDecimal;

import plazma.kernel.lib.ArithmeticContext;
import plazma.kernel.lib.array.ArrayLib;

public class StatLib {

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

    // Depends
    // - ArrayLib

    // Functions (count, min, max, sum, avg):

    /////////////////////////////////////////////////////////////////////////////////
    // 1.1
    // - count(array)
    // - count(array, value)
    // - countNot(array, value)
    // - countZero(array)
    // - countNotZero(array)
    // - countNull(array)
    // - countNotNull(array)

    /////////////////////////////////////////////////////////////////////////////////
    // 2.1
    // - min(array)
    // - min(array, def)

    // 2.2
    // - max(array)
    // - max(array, def)

    /////////////////////////////////////////////////////////////////////////////////
    // 3.1
    // - double: sum(<type[]>)

    // - sum(array, context)
    // - sumDouble(array)
    // - sumDouble(array, context)

    // - sumBig(array)

    /////////////////////////////////////////////////////////////////////////////////
    // 4.1
    // - avg(array)
    // - avg(array, context)
    // - avgBig(array) ???

    /////////////////////////////////////////////////////////////////////////////////
    // 5.1
    // - mean(array)
    // - mean(array, context)

    // 5.2
    // - arithmeticMean(array)
    // - arithmeticMean(array, context)

    // 5.3
    // - geometricMean(array)
    // - geometricMean(array, context)

    // 5.4
    // - harmonicMean(array)
    // - harmonicMean(array, context)

    // 5.5
    // - sqrtMean(array)
    // - sqrtMean(array, context)

    // 5.6
    // - median(array)
    // - medianLow(array)
    // - medianHigh(array)

    // 5.7
    // - variance(array)
    // - variance(array, mean)
    // - variance(array, context)
    // - variance(array, mean, context)

    // 5.8
    // - pvariance(array)
    // - pvariance(array, mean)
    // - pvariance(array, context)
    // - pvariance(array, mean, context)

    private StatLib() {
    }

    //// count

    public static int count(boolean[] array) {
        return ArrayLib.count(array);
    }

    public static int count(byte[] array) {
        return ArrayLib.count(array);
    }

    public static int count(char[] array) {
        return ArrayLib.count(array);
    }

    public static int count(short[] array) {
        return ArrayLib.count(array);
    }

    public static int count(int[] array) {
        return ArrayLib.count(array);
    }

    public static int count(long[] array) {
        return ArrayLib.count(array);
    }

    public static int count(float[] array) {
        return ArrayLib.count(array);
    }

    public static int count(double[] array) {
        return ArrayLib.count(array);
    }

    // alias: size()
    public static int count(Object[] array) {
        return ArrayLib.count(array);
    }

    //// count(array, value)

    public static int count(boolean[] array, boolean value) {
        return ArrayLib.count(array, value);
    }

    public static int count(byte[] array, byte value) {
        return ArrayLib.count(array, value);
    }

    public static int count(char[] array, char value) {
        return ArrayLib.count(array, value);
    }

    public static int count(short[] array, short value) {
        return ArrayLib.count(array, value);
    }

    public static int count(int[] array, int value) {
        return ArrayLib.count(array, value);
    }

    public static int count(long[] array, long value) {
        return ArrayLib.count(array, value);
    }

    public static int count(float[] array, float value) {
        return ArrayLib.count(array, value);
    }

    public static int count(double[] array, double value) {
        return ArrayLib.count(array, value);
    }

    //

    public static <T> int count(T[] array, T value) {
        return ArrayLib.count(array, value);
    }

    //// countZero(array): for number only (non boolean)

    public static int countZero(byte[] array) {
        return ArrayLib.countZero(array);
    }

    public static int countZero(char[] array) {
        return ArrayLib.countZero(array);
    }

    public static int countZero(short[] array) {
        return ArrayLib.countZero(array);
    }

    public static int countZero(int[] array) {
        return ArrayLib.countZero(array);
    }

    public static int countZero(long[] array) {
        return ArrayLib.countZero(array);
    }

    public static int countZero(float[] array) {
        return ArrayLib.countZero(array);
    }

    public static int countZero(double[] array) {
        return ArrayLib.countZero(array);
    }

    //// countNotZero(array): for number only (non boolean)

    public static int countNotZero(byte[] array) {
        return ArrayLib.countNotZero(array);
    }

    public static int countNotZero(char[] array) {
        return ArrayLib.countNotZero(array);
    }

    public static int countNotZero(short[] array) {
        return ArrayLib.countNotZero(array);
    }

    public static int countNotZero(int[] array) {
        return ArrayLib.countNotZero(array);
    }

    public static int countNotZero(long[] array) {
        return ArrayLib.countNotZero(array);
    }

    public static int countNotZero(float[] array) {
        return ArrayLib.countNotZero(array);
    }

    public static int countNotZero(double[] array) {
        return ArrayLib.countNotZero(array);
    }

    //// countNot(array, value)

    public static int countNot(boolean[] array, boolean value) {
        return ArrayLib.countNot(array, value);
    }

    public static int countNot(byte[] array, byte value) {
        return ArrayLib.countNot(array, value);
    }

    public static int countNot(char[] array, char value) {
        return ArrayLib.countNot(array, value);
    }

    public static int countNot(short[] array, short value) {
        return ArrayLib.countNot(array, value);
    }

    public static int countNot(int[] array, int value) {
        return ArrayLib.countNot(array, value);
    }

    public static int countNot(long[] array, long value) {
        return ArrayLib.countNot(array, value);
    }

    public static int countNot(float[] array, float value) {
        return ArrayLib.countNot(array, value);
    }

    public static int countNot(double[] array, double value) {
        return ArrayLib.countNot(array, value);
    }

    ////

    public static <T> int countNot(T[] array, T value) {
        return ArrayLib.countNot(array, value); // Not
    }

    public static <T> int countNull(T[] array) {
        return ArrayLib.countNull(array);
    }

    public static <T> int countNotNull(T[] array) {
        return ArrayLib.countNotNull(array); // Not
    }

    //// min(array)

    public static boolean min(boolean[] array) {
        return ArrayLib.min(array);
    }

    public static byte min(byte[] array) {
        return ArrayLib.min(array);
    }

    public static char min(char[] array) {
        return ArrayLib.min(array);
    }

    public static short min(short[] array) {
        return ArrayLib.min(array);
    }

    public static int min(int[] array) {
        return ArrayLib.min(array);
    }

    public static long min(long[] array) {
        return ArrayLib.min(array);
    }

    public static float min(float[] array) {
        return ArrayLib.min(array);
    }

    public static double min(double[] array) {
        return ArrayLib.min(array);
    }

    //

    public static <T extends Comparable<T>> T min(T[] array) {
        return ArrayLib.min(array);
    }

    //// min(array, def)

    public static boolean min(boolean[] array, boolean def) {
        return ArrayLib.min(array, def);
    }

    public static byte min(byte[] array, byte def) {
        return ArrayLib.min(array, def);
    }

    public static char min(char[] array, char def) {
        return ArrayLib.min(array, def);
    }

    public static short min(short[] array, short def) {
        return ArrayLib.min(array, def);
    }

    public static int min(int[] array, int def) {
        return ArrayLib.min(array, def);
    }

    public static long min(long[] array, long def) {
        return ArrayLib.min(array, def);
    }

    public static float min(float[] array, float def) {
        return ArrayLib.min(array, def);
    }

    public static double min(double[] array, double def) {
        return ArrayLib.min(array, def);
    }

    //

    public static <T extends Comparable<T>> T min(T[] array, T def) {
        return ArrayLib.min(array, def);
    }

    //// max(array)

    public static boolean max(boolean[] array) {
        return ArrayLib.max(array);
    }

    public static byte max(byte[] array) {
        return ArrayLib.max(array);
    }

    public static char max(char[] array) {
        return ArrayLib.max(array);
    }

    public static short max(short[] array) {
        return ArrayLib.max(array);
    }

    public static int max(int[] array) {
        return ArrayLib.max(array);
    }

    public static long max(long[] array) {
        return ArrayLib.max(array);
    }

    public static float max(float[] array) {
        return ArrayLib.max(array);
    }

    public static double max(double[] array) {
        return ArrayLib.max(array);
    }

    //

    public static <T extends Comparable<T>> T max(T[] array) {
        return ArrayLib.max(array);
    }

    //// max(array, def)

    public static boolean max(boolean[] array, boolean def) {
        return ArrayLib.max(array, def);
    }

    public static byte max(byte[] array, byte def) {
        return ArrayLib.max(array, def);
    }

    public static char max(char[] array, char def) {
        return ArrayLib.max(array, def);
    }

    public static short max(short[] array, short def) {
        return ArrayLib.max(array, def);
    }

    public static int max(int[] array, int def) {
        return ArrayLib.max(array, def);
    }

    public static long max(long[] array, long def) {
        return ArrayLib.max(array, def);
    }

    public static float max(float[] array, float def) {
        return ArrayLib.max(array, def);
    }

    public static double max(double[] array, double def) {
        return ArrayLib.max(array, def);
    }

    //

    public static <T extends Comparable<T>> T max(T[] array, T def) {
        return ArrayLib.min(array, def);
    }

    //// sum(array)

    public static double sum(byte[] array) {
        return ArrayLib.sum(array);
    }

    public static double sum(char[] array) {
        return ArrayLib.sum(array);
    }

    public static double sum(short[] array) {
        return ArrayLib.sum(array);
    }

    public static double sum(int[] array) {
        return ArrayLib.sum(array);
    }

    public static double sum(long[] array) {
        return ArrayLib.sum(array);
    }

    public static double sum(float[] array) {
        return ArrayLib.sum(array);
    }

    public static double sum(double[] array) {
        return ArrayLib.sum(array);
    }

    //// <type>: sumShift(<type[])

    public static byte sumShift(byte[] array) {
        return ArrayLib.sumShift(array);
    }

    public static char sumShift(char[] array) {
        return ArrayLib.sumShift(array);
    }

    public static short sumShift(short[] array) {
        return ArrayLib.sumShift(array);
    }

    public static int sumShift(int[] array) {
        return ArrayLib.sumShift(array);
    }

    public static long sumShift(long[] array) {
        return ArrayLib.sumShift(array);
    }

    ////

    public static int sumInt(int[] array) {
        return ArrayLib.sumInt(array);
    }

    public static int sumInt(int[] array, ArithmeticContext context) {
        return ArrayLib.sumInt(array, context);
    }

    //// BigDecimal: sumBig(<type[]>)

    public static BigDecimal sumBig(long[] array) {
        return ArrayLib.sumBig(array);
    }

    public static BigDecimal sumBig(float[] array) {
        return ArrayLib.sumBig(array);
    }

    public static BigDecimal sumBig(double[] array) {
        return ArrayLib.sumBig(array);
    }

    //// avg(array)

    public static double avg(byte[] array) {
        return ArrayLib.avg(array);
    }

    public static double avg(char[] array) {
        return ArrayLib.avg(array);
    }

    public static double avg(short[] array) {
        return ArrayLib.avg(array);
    }

    public static double avg(int[] array) {
        return ArrayLib.avg(array);
    }

    public static double avg(float[] array) {
        return ArrayLib.avg(array);
    }

    public static double avg(double[] array) {
        return ArrayLib.avg(array);
    }

    ////////////////////////////////////////////////////////////////////////////////
    //
    // STATISTICS
    //
    ////////////////////////////////////////////////////////////////////////////////

    // sumSqr

    public static double sumSqr(double[] array) {
        return StatHelper._sumSqr(array, null);
    }

    public static double sumSqr(double[] array, ArithmeticContext context) {
        return StatHelper._sumSqr(array, context);
    }

    // sumPow

    public static double sumPow(double[] array, double n) {
        return StatHelper._sumPow(array, n, null);
    }

    public static double sumPow(double[] array, double n, ArithmeticContext context) {
        return StatHelper._sumPow(array, n, context);
    }

    // mean(meanType, array)

    /**
     * Returns mean by MeanType: ARITHMETIC, GEOMETRIC, HARMONIC...
     * 
     * @param meanType
     * @param array
     * @return
     */
    public static double mean(MeanType meanType, double[] array) {
        return StatHelper._mean(meanType, array, null);
    }

    /**
     * Returns mean by MeanType: ARITHMETIC, GEOMETRIC, HARMONIC...
     * 
     * @param meanType
     * @param array
     * @param context
     * @return
     */
    public static double mean(MeanType meanType, double[] array, ArithmeticContext context) {
        return StatHelper._mean(meanType, array, context);
    }

    // mean(array)

    public static double mean(double[] array) {
        return arithmeticMean(array);
    }

    public static double mean(double[] array, ArithmeticContext context) {
        return arithmeticMean(array, context);
    }

    // arithmeticMean(array)
    // (x1 + x2 + ... + xn) / n

    public static double arithmeticMean(double[] array) {
        return StatHelper._arithmeticMean(array, null);
    }

    public static double arithmeticMean(double[] array, ArithmeticContext context) {
        return StatHelper._arithmeticMean(array, context);
    }

    // geometricMean(array)
    // (x1 * x2 * ... * xn) ^ (1/n)

    public static double geometricMean(double[] array) {
        return StatHelper._geometricMean(array, null);
    }

    public static double geometricMean(double[] array, ArithmeticContext context) {
        return StatHelper._geometricMean(array, context);
    }

    // harmonicMean(array)
    // n / (1/x1 + 1/x2 + ... 1/xn)

    public static double harmonicMean(double[] array) {
        return StatHelper._harmonicMean(array, null);
    }

    public static double harmonicMean(double[] array, ArithmeticContext context) {
        return StatHelper._harmonicMean(array, context);
    }

    // Root Mean Square
    public static double sqrtMean(double[] array) {
        return StatHelper._sqrtMean(array, null);
    }

    public static double sqrtMean(double[] array, ArithmeticContext context) {
        return StatHelper._sqrtMean(array, context);
    }

    // median

    public static double median(double[] array) {
        return StatHelper._median(array);
    }

    // medianLow

    public static double medianLow(double[] array) {
        return StatHelper._medianLow(array);
    }

    // medianHigh

    public static double medianHigh(double[] array) {
        return StatHelper._medianHigh(array);
    }

    public static double midrange(double[] array) {
        return StatHelper._midrange(array);
    }

    ////

    public static double variance(double[] array) {
        return StatHelper._variance(array, null, null);
    }

    public static double variance(double[] array, double mean) {
        return StatHelper._variance(array, mean, null);
    }

    public static double variance(double[] array, ArithmeticContext context) {
        return StatHelper._variance(array, null, context);
    }

    public static double variance(double[] array, double mean, ArithmeticContext context) {
        return StatHelper._variance(array, mean, context);
    }

    //

    public static double pvariance(double[] array) {
        return StatHelper._pvariance(array, null, null);
    }

    public static double pvariance(double[] array, double mean) {
        return StatHelper._pvariance(array, mean, null);
    }

    public static double pvariance(double[] array, ArithmeticContext context) {
        return StatHelper._pvariance(array, null, context);
    }

    public static double pvariance(double[] array, double mean, ArithmeticContext context) {
        return StatHelper._pvariance(array, mean, context);
    }

    //

    public static double stdev(double[] array) {
        return StatHelper._stdev(array, null, null);
    }

    public static double stdev(double[] array, double mean) {
        return StatHelper._stdev(array, mean, null);
    }

    public static double stdev(double[] array, ArithmeticContext context) {
        return StatHelper._stdev(array, null, context);
    }

    public static double stdev(double[] array, double mean, ArithmeticContext context) {
        return StatHelper._stdev(array, mean, context);
    }

    //

    public static double pstdev(double[] array) {
        return StatHelper._pstdev(array, null, null);
    }

    public static double pstdev(double[] array, double mean) {
        return StatHelper._pstdev(array, mean, null);
    }

    public static double pstdev(double[] array, ArithmeticContext context) {
        return StatHelper._pstdev(array, null, context);
    }

    public static double pstdev(double[] array, double mean, ArithmeticContext context) {
        return StatHelper._pstdev(array, mean, context);
    }

    //

    public static double product(double[] array, ArithmeticContext context) {
        return StatHelper._product(array, context);
    }

}
