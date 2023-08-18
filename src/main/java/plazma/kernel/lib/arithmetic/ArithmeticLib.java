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

package plazma.kernel.lib.arithmetic;

import java.math.BigDecimal;

import plazma.kernel.lib.ArithmeticContext;
import plazma.kernel.lib.ArithmeticEnvironment;
import plazma.kernel.lib.num.NumLib;

public class ArithmeticLib {

    // Functions:
    //
    // - add(1, 2): 1 + 2 = 3
    // - sub(3, 2): 3 - 2 = 1
    // - mul(2, 3): 2 * 3 = 6
    // - div(7, 5): 7 / 5 = 1.4 (float division)
    // - divFloat(7, 5): 7 / 5 = 1.4 (float division)
    // - divInt(7, 5): 7 / 5 = 1 (integer division)

    // java.lang.Math
    // https://github.com/google/guava/tree/master/guava/src/com/google/common/math
    // https://rosettacode.org/wiki/Arithmetic/Integer

    public static ArithmeticContext getContext() {
        return ArithmeticEnvironment.getContext();
    }

    private static ArithmeticContext getContext(ArithmeticContext context) {
        return ArithmeticEnvironment.getContext(context);
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

    private static boolean checkNull(Number x, Number y, ArithmeticContext context) {
        return checkNull(x, y, context, "Number must be not null");
    }

    private static BigDecimal toBigDecimal(Number x) {
        return NumLib.toBigDecimal(x);
    }

    private static void throwOverflowException(String operator, String type) {
        throw new ArithmeticException("Operator '" + operator + "': " + type + " overflow");
    }

    //// Overflow: Operator: '+': ((x ^ r) & (y ^ r)) < 0

    // int

    private static boolean isOverflowAdd(int x, int y, int r) {
        return ((x ^ r) & (y ^ r)) < 0;
    }

    private static boolean isOverflowAdd(int x, int y, int r, ArithmeticContext context) {
        return isOverflowAdd(x, y, r) && isOverflowException(context);
    }

    // long

    private static boolean isOverflowAdd(long x, long y, long r) {
        return ((x ^ r) & (y ^ r)) < 0;
    }

    private static boolean isOverflowAdd(long x, long y, long r, ArithmeticContext context) {
        return isOverflowAdd(x, y, r) && isOverflowException(context);
    }

    //// Overflow: Operator: '-': ((x ^ y) & (x ^ r)) < 0

    // int

    private static boolean isOverflowSub(int x, int y, int r) {
        return ((x ^ y) & (x ^ r)) < 0;
    }

    private static boolean isOverflowSub(int x, int y, int r, ArithmeticContext context) {
        return isOverflowSub(x, y, r) && isOverflowException(context);
    }

    // long

    private static boolean isOverflowSub(long x, long y, long r) {
        return ((x ^ y) & (x ^ r)) < 0;
    }

    private static boolean isOverflowSub(long x, long y, long r, ArithmeticContext context) {
        return isOverflowSub(x, y, r) && isOverflowException(context);
    }

    //// Operator: '+'

    public static byte add(byte x, byte y) {
        return add(x, y, null);
    }

    ////

    public static byte add(byte x, byte y, ArithmeticContext context) {
        byte r = (byte) (x + y);
        if (((x ^ r) & (y ^ r)) < 0 && isOverflowException(context)) {
            throwOverflowException("+", "Byte");
        }
        return r;
    }

    public static int add(int x, int y) {
        return add(x, y, null);
    }

    public static int add(int x, int y, ArithmeticContext context) {
        int r = x + y;
        if (((x ^ r) & (y ^ r)) < 0 && isOverflowException(context)) {
            throwOverflowException("+", "Integer");
        }
        return r;
    }

    public static long add(long x, long y) {
        return add(x, y, null);
    }

    public static long add(long x, long y, ArithmeticContext context) {
        long r = x + y;
        if (((x ^ r) & (y ^ r)) < 0 && isOverflowException(context)) {
            throwOverflowException("+", "Long");
        }
        return r;
    }

    ////

    public static Number add(Number x, Number y) {
        return add(x, y, null);
    }

    public static Number add(Number x, Number y, ArithmeticContext context) {
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }
            return x == null ? y : x; // add
        }
        BigDecimal xr = toBigDecimal(x);
        BigDecimal yr = toBigDecimal(y);
        return xr.add(yr);
    }

    //// Operator: '-'

    public static int sub(int x, int y) {
        return sub(x, y, null);
    }

    public static int sub(int x, int y, ArithmeticContext context) {
        int r = x - y;
        if (((x ^ y) & (x ^ r)) < 0 && isOverflowException(context)) {
            throwOverflowException("-", "Integer");
        }
        return r;
    }

    public static long sub(long x, long y) {
        return sub(x, y, null);
    }

    public static long sub(long x, long y, ArithmeticContext context) {
        long r = x - y;
        if (((x ^ y) & (x ^ r)) < 0 && isOverflowException(context)) {
            throwOverflowException("-", "Long");
        }
        return r;
    }

    ////

    public static Number sub(Number x, Number y, ArithmeticContext context) {
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNull(x, y, c)) {
                return null;
            }
            if (y == null) {
                return x;
            }
            BigDecimal yr = toBigDecimal(y);
            return yr.multiply(toBigDecimal(-1));
        }
        BigDecimal xr = toBigDecimal(x);
        BigDecimal yr = toBigDecimal(y);
        return xr.subtract(yr);
    }

    //// Operator: '*'

    public static int mul(int x, int y) {
        return mul(x, y, null);
    }

    public static int mul(int x, int y, ArithmeticContext context) {
        long r = (long) x * (long) y;
        if ((int) r != r && isOverflowException(context)) {
            throwOverflowException("*", "Integer");
        }
        return (int) r;
    }

    public static long mul(long x, long y) {
        return mul(x, y, null);
    }

    public static long mul(long x, long y, ArithmeticContext context) {
        long r = x * y;
        long ax = Math.abs(x);
        long ay = Math.abs(y);
        if (((ax | ay) >>> 31 != 0)) {
            // Some bits greater than 2^31 that might cause overflow
            // Check the result using the divide operator
            // and check for the special case of Long.MIN_VALUE * -1
            if (((y != 0) && (r / y != x)) || (x == Long.MIN_VALUE && y == -1) && isOverflowException(context)) {
                throwOverflowException("*", "Long");
            }
        }
        return r;
    }

    //// Operator: '/': by defautl result of '/' is float-point

    public static float div(int x, int y) {
        return divFloat(x, y);
    }

    public static float div(int x, int y, ArithmeticContext context) {
        return divFloat(x, y, context);
    }

    public static double div(long x, long y, ArithmeticContext context) {
        return divFloat(x, y, context);
    }

    ////

    public static float divFloat(int x, int y) {
        return div(x, y, null);
    }

    public static float divFloat(int x, int y, ArithmeticContext context) {
        if (y == 0F) {
            throw new ArithmeticException("/ by zero");
        }
        float r = (float) x / (float) y;
        return r;
    }

    public static double divFloat(long x, long y, ArithmeticContext context) {
        if (y == 0L) {
            throw new ArithmeticException("/ by zero");
        }
        double r = (double) x / (double) y;
        return r;
    }

    ////

    // https://rosettacode.org/wiki/Arithmetic/Integer

    public static int divInt(int x, int y) {
        return divInt(x, y, null);
    }

    public static int divInt(int x, int y, ArithmeticContext context) {
        int r = x / y;
        return r;
    }

    public static long divInt(long x, long y) {
        return divInt(x, y, null);
    }

    public static long divInt(long x, long y, ArithmeticContext context) {
        long r = x / y;
        return r;
    }

    ////

}
