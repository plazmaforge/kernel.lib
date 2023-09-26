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

package plazma.lib.math;

import java.math.BigDecimal;

import plazma.lib.AngleUnit;
import plazma.lib.ArithmeticContext;
import plazma.lib.ArithmeticEnvironment;
import plazma.lib.arithmetic.ArithmeticLib;
import plazma.lib.geom.GeomLib;
import plazma.lib.num.NumLib;
import plazma.lib.trigonom.TrigonomLib;

public class MathLib {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1.1 +, -, *, /
    //
    // - add(1, 2):      1 + 2 = 3
    // - sub(3, 2):      3 - 2 = 1
    // - mul(2, 3):      2 * 3 = 6
    // - div(7, 5):      7 / 5 = 1.4 (float division)
    // - divFloat(7, 5): 7 / 5 = 1.4 (float division)
    // - divInt(7, 5):   7 / 5 = 1   (integer division)
    //
    // 1.2 trunc, round, ceil, floor, rint, sign
    //
    // - trunc(float x)           : float    - 23.45 => 23.00
    // - trunc(double x)          : long
    //
    // - truncInt(float x)        : int      - 23.45 => 23
    // - truncInt(double x)       : long
    //
    // - truncDec(float x)        : float    - 23.45 => 0.45
    // - truncDec(double x)       : double
    //
    // - trunc(float x, int pos)  : float    - trunc(23.75, 1) = 23.70
    // - trunc(double x, int pos) : double
    //
    // - round(float x)           : float
    // - round(double x)          : double
    //
    // - roundInt(float x)        : int
    // - roundInt(double x)       : long
    //
    // - round(float x, int pos)  : float    - round(23.75, 1) = 23.80
    // - round(double x, int pos) : double
    //
    // - ceil(float x)
    // - ceil(double x)
    //
    // - floor(float x)
    // - floor(double x)
    //
    // - rint(float x)
    // - rint(double x)
    //
    // - sign(int x)                         - (-1, 0, 1)
    // - sign(long x)
    // - sign(float x)
    // - sign(double x)
    //
    // - abs(int x)
    // - abs(long x)
    // - abs(float x)
    // - abs(double x)
        
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 2.1 pow, sqr, cbr, sqrt, root, cbrt, exp
    //
    // - pow(double x, double y)              - x ^ y
    // - pow2(double x)                       - x ^ 2
    // - pow3(double x)                       - x ^ 3
    // 
    // - pow2x(double x)            : double  - 2 ^ x
    // - pow10x(double x)           : double  - 10 ^ x
    // - pow10x(int x)              : float   - 10 ^ x
    // - powEx(double x)            : double  - e ^ x
    //
    // - sqr(double x)                        - x ^ 2
    // - cbr(double x)                        - x ^ 3
    //
    // - root(double x, double n)             - x ^ (1/n)
    // - root2(double x)                      - x ^ (1/2)
    // - root3(double x)                      - x ^ (1/3)
    //
    // - sqrt(double x)                       - x ^ (1/2)
    // - cbrt(double x)                       - x ^ (1/3)
    //
    // - exp(double x)                        - e ^ x

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 2.2 arithmetic/geometric progression/sum/sequence, integer sequence
    //
    // - aprog(int a, int n, int d)          - arithmetic progression
    // - aprogsum(int a, int n, int d)       - arithmetic progression sum
    // - aprogseq(int a, int n, int d)       - arithmetic progression sequence
    //
    // - gprog(int a, int n, int r)          - geometric progression
    // - gprogsum(int a, int n, int r)       - geometric progression sum
    // - gprogseq(int a, int n, int r)       - geometric progression sequence
    //
    // - iseq(int n)                         - integer sequence: 0 1 2 3 4 5 ...
    // - isum(int n)                         - integer sequence sum: 0 + 1 + 2 + 3 + 4 + 5 ...
    //
    // - nseq(int n)                         - N integer sequence: 1 2 3 4 5 ...
    // - nsum(int n)                         - N integer sequence: 1 + 2 + 3 + 4 + 5 ...
    //
    // - sumnum
    //
    // 2.3 factorial, fibonaccy
    //
    // - fact(int n)                         - factorial
    // 
    // - fib(int n)                          - fibonaccy function
    // - fibseq(int n)                       - fibonaccy sequence (0 1 1 2 3 5 8 13 21 34 55 ...)
    // - fibseq2(int n)                      - simple fibonacci sequence (1 2 3 5 8 13 21 34 55 ...)
    // - fibseq(int n, bool simple)          - fibonaccy sequence with 'simple' flag 
    //
    // 2.4  mod, isEven, isOdd
    //
    // - mod(int x, int y)
    // - mod(long x, long y)
    //
    // - min(int x, int y): int              - minimum x, y 
    // - min(long x, long y): long 
    // - min(float x, float y): float 
    // - min(double x, double y): double 
    //
    // - max(int x, int y): int              - maximum x, y 
    // - max(long x, long y): long 
    // - max(float x, float y): float 
    // - max(double x, double y): double 
    //
    // - isMod(int x, int y)
    // - isMod(long x, long y)
    //
    // - isDiv(int x, int y)
    // - isDiv(long x, long y)
    //
    // - isEven(int x)
    // - isEven(long x)
    //
    // - isOdd(int x)
    // - isOdd(long x)
    //
    // - isNaN(float x)
    // - isNaN(double x)
    //
    // - isInfinite(float x)
    // - isInfinite(double x)
    // 
    // - isPositiveInfinite(float x)
    // - isPositiveInfinite(double x)
    // 
    // - isNegativeInfinite(float x)
    // - isNegativeInfinite(double x)
 
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 2.5
    //
    // - log(double x, double base)
    // - log(double x)               : log(x, e) - natural logarithm:
    // - log2(double x)              : log(x, 10)
    // - log10(double x)             : log(x, 10)
    // - ln(double x)                : [alias] log(x, e) - natural logarithm:
    // - lg(double x)                : [alias] log(x, 10)
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 2.6
    //
    // - hypot(int x, int y): int                               - 2D: sqrt(x^2 + y^2)
    // - hypot(float x, float y): float                         - 2D: sqrt(x^2 + y^2)
    // - hypot(double x, double y): double                      - 2D: sqrt(x^2 + y^2)
    //
    // - hypot(int x, int y, int z): int                        - 3D: sqrt(x^2 + y^2 + z^2)
    // - hypot(float x, float y, float z): float                - 3D: sqrt(x^2 + y^2 + z^2)
    // - hypot(double x, double y, double z): double            - 3D: sqrt(x^2 + y^2 + z^2)
    //
    // - dot(int x, int y): int                                 - 2D: x^2 + y^2
    // - dot(float x, float y): float                           - 2D: x^2 + y^2
    // - dot(double x, double y): double                        - 2D: x^2 + y^2
    //
    // - dot(int x, int y, int z): int                          - 3D: x^2 + y^2 + z^2
    // - dot(float x, float y, float z): float                  - 3D: x^2 + y^2 + z^2
    // - dot(double x, double y, double z): double              - 3D: x^2 + y^2 + z^2

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 3.1. RADIAN BY DEFAULT: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    //
    // sin, cos, tan
    //
    // - sin(double a)                - sine
    // - cos(double a)                - cosine
    // - tan(double a)                - tangent
    //
    // csc, sec, cot
    //
    // - csc(double a)                - cosecant  : 1/sin(x)
    // - sec(double a)                - secant    : 1/cos(x)
    // - cot(double a)                - cotangent : 1/tan(x)
    //
    // asin, acos, atan
    //
    // - asin(double a)               - arc sine
    // - acos(double a)               - arc cosine
    // - atan(double a)               - arc tangent
    //
    // acsc, asec, acot
    //
    // - acsc(double a)               - arc cosecant  : 1/sin(x)
    // - asec(double a)               - arc secant    : 1/cos(x)
    // - acot(double a)               - arc cotangent : 1/tan(x)
    //
    // hyperbolic: sinh, cosh, tanh
    //
    // - sinh(double a)               - hyperbolic sine
    // - cosh(double a)               - hyperbolic cosine
    // - tanh(double a)               - hyperbolic tangent
    //
    // hyperbolic: csch, sech, coth
    //
    // - csch(double a)               - hyperbolic cosecant
    // - sech(double a)               - hyperbolic secant
    // - coth(double a)               - hyperbolic cotangent
    //
    // hyperbolic: asinh, acosh, atanh
    //
    // - asinh(double a)              - hyperbolic arc sine
    // - acosh(double a)              - hyperbolic arc cosine
    // - atanh(double a)              - hyperbolic arc tangent
    //
    // hyperbolic: acsch, asech, acoth
    //
    // - acsch(double a)               - hyperbolic arc cosecant
    // - asech(double a)               - hyperbolic arc secant
    // - acoth(double a)               - hyperbolic arc cotangent
    //
    // 3.2. RADIAN: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    //
    // radian: sin, cos, tan
    //
    // - sinRadian(double a)
    // - cosRadian(double a)
    // - tanRadian(double a)
    //
    // radian: csc, sec, cot
    //
    // - cscRadian(double a)
    // - secRadian(double a)
    // - cotRadian(double a)
    //
    // radian: asin, acos, atan
    //
    // - asinRadian(double a)
    // - acosRadian(double a)
    // - atanRadian(double a)
    //
    // radian: acsc, asec, acot
    //
    // - acscRadian(double a)
    // - asecRadian(double a)
    // - acotRadian(double a)
    //
    // hyperbolic/radian: sinh, cosh, tanh
    //
    // - sinhRadian(double a)
    // - coshRadian(double a)
    // - tanhRadian(double a)
    //
    // hyperbolic/radian: csch, sech, coth
    //
    // - cschRadian(double a)
    // - sechRadian(double a)
    // - cothRadian(double a)
    //
    // hyperbolic/radian: asinh, acosh, atanh
    //
    // - asinhRadian(double a)
    // - acoshRadian(double a)
    // - atanhRadian(double a)
    //
    // hyperbolic/radian: acsch, asech, acoth
    //
    // - acschRadian(double a)
    // - asechRadian(double a)
    // - acothRadian(double a)
    //
    // 3.3. DEGREE sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    //
    // degree: sin, cos, tan
    //
    // - sinDegree(double a)
    // - cosDegree(double a)
    // - tanDegree(double a)
    //
    // degree: csc, sec, cot
    //
    // - cscDegree(double a)
    // - secDegree(double a)
    // - cotDegree(double a)
    //
    // degree: asin, acos, atan
    //
    // - asinDegree(double a)
    // - acosDegree(double a)
    // - atanDegree(double a)
    //
    // degree: acsc, asec, acot
    //
    // - acscDegree(double a)
    // - asecDegree(double a)
    // - acotDegree(double a)
    //
    // hyperbolic/degree: sinh, cosh, tanh
    //
    // - sinhDegree(double a)
    // - coshDegree(double a)
    // - tanhDegree(double a)
    //
    // hyperbolic/degree: csch, sech, coth
    //
    // - cschDegree(double a)
    // - sechDegree(double a)
    // - cothDegree(double a)
    //
    // hyperbolic/degree: asinh, acosh, atanh
    //
    // - asinhDegree(double a)
    // - acoshDegree(double a)
    // - atanhDegree(double a)
    //
    // hyperbolic/degree: acsch, asech, acoth
    //
    // - acschDegree(double a)
    // - asechDegree(double a)
    // - acothDegree(double a)
    //
    // 3.4. BY UNIT: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    //
    // by unit: sin, cos, tan
    //
    // - sin(double a, AngleUnit unit)
    // - cos(double a, AngleUnit unit)
    // - tan(double a, AngleUnit unit)
    //
    // by unit: csc, sec, cot
    //
    // - csc(double a, AngleUnit unit)
    // - sec(double a, AngleUnit unit)
    // - cot(double a, AngleUnit unit)
    //
    // by unit: asin, acos, atan
    //
    // - asin(double a, AngleUnit unit)
    // - acos(double a, AngleUnit unit)
    // - atan(double a, AngleUnit unit)
    //
    // by unit: acsc, asec, acot
    //
    // - acsc(double a, AngleUnit unit)
    // - asec(double a, AngleUnit unit)
    // - acot(double a, AngleUnit unit) 
    //
    // hyperbolic/by unit: sinh, cosh, tanh
    //
    // - sinh(double a, AngleUnit unit)
    // - cosh(double a, AngleUnit unit)
    // - tanh(double a, AngleUnit unit)
    //
    // hyperbolic/by unit: csch, sech, coth
    //
    // - csch(double a, AngleUnit unit)
    // - sech(double a, AngleUnit unit)
    // - coth(double a, AngleUnit unit)
    //
    // hyperbolic/by unit: asinh, acosh, atanh
    //
    // - asinh(double a, AngleUnit unit)
    // - acosh(double a, AngleUnit unit)
    // - atanh(double a, AngleUnit unit)
    //
    // hyperbolic/by unit: acsch, asech, acoth
    //
    // - acsch(double a, AngleUnit unit)
    // - asech(double a, AngleUnit unit)
    // - acoth(double a, AngleUnit unit)
    //
    // 3.5. Util
    //
    // - toRadians(double angdeg)
    // - toDegrees(double angrad)
    
    
    // java.lang.Math
    // https://github.com/google/guava/tree/master/guava/src/com/google/common/math    
    // https://rosettacode.org/wiki/Arithmetic/Integer
    // https://commons.apache.org/proper/commons-math
    
    public static final double PI = Math.PI;

    public static final double E = Math.E;

    public static final double M_PI = 3.14159265358979323846264338327950288d;
    // 3.14159265358979323846

    public static double LG_2 = Math.log10(2.0);

    public static double LN_2 = Math.log(2.0);

    private MathLib() {
    }

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

    private static boolean checkNull(Number x, Number y, ArithmeticContext context) {
        return ArithmeticEnvironment.checkNull(x, y, context, "Number must be not null");
    }

    private static BigDecimal toBigDecimal(Number x) {
        return NumLib.toBigDecimal(x);
    }

    //// ADD

    public static byte add(byte x, byte y) {
        return ArithmeticLib.add(x, y);
    }

    public static byte add(byte x, byte y, ArithmeticContext context) {
        return ArithmeticLib.add(x, y, context);
    }

    public static int add(int x, int y) {
        return ArithmeticLib.add(x, y);
    }

    public static int add(int x, int y, ArithmeticContext context) {
        return ArithmeticLib.add(x, y, context);
    }

    public static long add(long x, long y) {
        return ArithmeticLib.add(x, y);
    }

    public static long add(long x, long y, ArithmeticContext context) {
        return ArithmeticLib.add(x, y, context);
    }

    //// ADD OBJECT

    public static Number add(Number x, Number y) {
        return add(x, y, null);
    }

    public static Number add(Number x, Number y, ArithmeticContext context) {
        return ArithmeticLib.add(x, y, context);
    }

    //// SUB

    public static int sub(int x, int y) {
        return ArithmeticLib.sub(x, y);
    }

    public static int sub(int x, int y, ArithmeticContext context) {
        return ArithmeticLib.sub(x, y, context);
    }

    public static long sub(long x, long y) {
        return ArithmeticLib.sub(x, y);
    }

    public static long sub(long x, long y, ArithmeticContext context) {
        return ArithmeticLib.sub(x, y, context);
    }

    //// SUB OBJECT

    public static Number sub(Number x, Number y, ArithmeticContext context) {
        return ArithmeticLib.sub(x, y, context);
    }

    //// MUL

    public static int mul(int x, int y) {
        return ArithmeticLib.mul(x, y);
    }

    public static int mul(int x, int y, ArithmeticContext context) {
        return ArithmeticLib.mul(x, y, context);
    }

    public static long mul(long x, long y) {
        return ArithmeticLib.mul(x, y);
    }

    public static long mul(long x, long y, ArithmeticContext context) {
        return ArithmeticLib.mul(x, y, context);
    }

    //// DIV

    public static double div(int x, int y) {
        return ArithmeticLib.div(x, y);
    }

    public static double div(int x, int y, ArithmeticContext context) {
        return ArithmeticLib.div(x, y, context);
    }

    public static double div(long x, long y, ArithmeticContext context) {
        return ArithmeticLib.div(x, y, context);
    }

    //// DIV FLOAT

    public static double divFloat(int x, int y) {
        return ArithmeticLib.divFloat(x, y);
    }

    public static double divFloat(int x, int y, ArithmeticContext context) {
        return ArithmeticLib.divFloat(x, y, context);
    }

    public static double divFloat(long x, long y, ArithmeticContext context) {
        return ArithmeticLib.divFloat(x, y, context);
    }

    ////

    // https://rosettacode.org/wiki/Arithmetic/Integer

    public static int divInt(int x, int y) {
        return ArithmeticLib.divInt(x, y);
    }

    public static int divInt(int x, int y, ArithmeticContext context) {
        return ArithmeticLib.divInt(x, y, context);
    }

    public static long divInt(long x, long y) {
        return ArithmeticLib.divInt(x, y);
    }

    public static long divInt(long x, long y, ArithmeticContext context) {
        return ArithmeticLib.divInt(x, y, context);
    }

    //// trunc

    // 23.45 => 23.0
    // 23.50 => 23.0
    // 23.70 => 23.0
    public static float trunc(float value) {
        return NumLib.trunc(value);
    }

    // 23.45 => 23.0
    // 23.50 => 23.0
    // 23.70 => 23.0
    public static double trunc(double value) {
        return NumLib.trunc(value);
    }

    // 23.45 => 23
    // 23.50 => 23
    // 23.70 => 23
    public static int truncInt(float value) {
        return NumLib.truncInt(value);
    }

    // 23.45 => 23
    // 23.50 => 23
    // 23.70 => 23
    public static long truncInt(double value) {
        return NumLib.truncInt(value);
    }

    // 23.45 => 0.45
    // 23.50 => 0.50
    // 23.70 => 0.70
    public static float truncDec(float value) {
        return NumLib.truncDec(value);
    }

    // 23.45 => 0.45
    // 23.50 => 0.50
    // 23.70 => 0.70
    public static double truncDec(double value) {
        return NumLib.truncDec(value);
    }

    ////

    // trunc(23.45, 1) => 23.4
    // trunc(23.45, 2) => 23.45
    public static float trunc(float value, int pos) {
        if (pos == 0) {
            return trunc(value);
        }
        int n = pos < 0 ? (-1 * pos) : pos;
        float dec = getDec(n);
        if (pos < 0) {
            return trunc(value / dec) * dec;
        } else {
            return trunc(value * dec) / dec;
        }
    }

    // trunc(23.45, 1) => 23.4
    // trunc(23.45, 2) => 23.45
    public static double trunc(double value, int pos) {
        if (pos == 0) {
            return trunc(value);
        }
        int n = pos < 0 ? (-1 * pos) : pos;
        float dec = getDec(n);
        if (pos < 0) {
            return trunc(value / dec) * dec;
        } else {
            return trunc(value * dec) / dec;
        }
    }

    //// round

    // 23.45 => 23.0
    // 23.50 => 24.0
    // 23.70 => 24.0
    public static float round(float value) {
        return Math.round(value);
    }

    // 23.45 => 23.0
    // 23.50 => 24.0
    // 23.70 => 24.0
    public static double round(double value) {
        return Math.round(value);
    }

    //

    // 23.45 => 23
    // 23.50 => 24
    // 23.70 => 24
    public static int roundInt(float value) {
        return Math.round(value);
    }

    // 23.45 => 23
    // 23.50 => 24
    // 23.70 => 24
    public static long roundInt(double value) {
        return Math.round(value);
    }

    ////

    // round(23.45, 1) => 23.5
    // round(23.45, 2) => 23.45
    public static float round(float value, int pos) {
        if (pos == 0) {
            return round(value);
        }
        int n = pos < 0 ? (-1 * pos) : pos;
        float dec = getDec(n);
        if (pos < 0) {
            return round(value / dec) * dec;
        } else {
            return round(value * dec) / dec;
        }
    }

    // round(23.45, 1) => 23.5
    // round(23.45, 2) => 23.45
    public static double round(double value, int pos) {
        if (pos == 0) {
            return round(value);
        }
        int n = pos < 0 ? (-1 * pos) : pos;
        float dec = getDec(n);
        if (pos < 0) {
            return round(value / dec) * dec;
        } else {
            return round(value * dec) / dec;
        }
    }

    //// ceil

    public static float ceil(float x) {
        return (float) Math.ceil(x);
    }

    public static double ceil(double x) {
        return Math.ceil(x);
    }

    //// floor

    public static float floor(float x) {
        return (float) Math.floor(x);
    }

    public static double floor(double x) {
        return Math.floor(x);
    }

    //// rint

    public static float rint(float x) {
        return (float) Math.rint(x);
    }

    public static double rint(double x) {
        return Math.rint(x);
    }

    //// sign

    public static int sign(int x) {
        return x < 0 ? -1 : (x > 0 ? 1 : 0);
        // if (x == 0) {
        // return 0;
        // }
        // return (x < 0 ? -1 : 1);
    }

    public static long sign(long x) {
        return x < 0 ? -1 : (x > 0 ? 1 : 0);
        // if (x == 0) {
        // return 0;
        // }
        // return (x < 0 ? -1 : 1);
    }

    // [-1, 0, 1] only
    // return (Nan, -Infinity, +Infinity) is not correct

    public static float sign(float x) {
        return x < 0.0f ? -1f : (x > 0.0f ? 1.0f : 0.0f);
        // NaN -> 0, NaN?
        // +0.0 -> 0
        // -0.0 -> 0
        // +Infinity -> 1
        // -Infinity -> -1

        // return Math.signum(x);
    }

    // [-1, 0, 1] only
    // return (Nan, -Infinity, +Infinity) is not correct

    public static double sign(double x) {
        return x < 0.0d ? -1.0d : (x > 0.0d ? 1.0d : 0.0d);
        // NaN -> 0, NaN?
        // +0.0 -> 0
        // -0.0 -> 0
        // +Infinity -> 1
        // -Infinity -> -1

        // return Math.signum(x);
    }

    //// abs

    /*
     * public static byte abs(byte x) { //TODO: shift //return (byte) ((x < 0) ? -x
     * : x); return (byte) Math.abs(x); }
     * 
     * public static short abs(short x) { //TODO: shift return (short) Math.abs(x);
     * }
     */

    public static int abs(int x) {
        return Math.abs(x);
    }

    public static long abs(long x) {
        return Math.abs(x);
    }

    public static float abs(float x) {
        return Math.abs(x);
    }

    public static double abs(double x) {
        return Math.abs(x);
    }

    //// 2.1 pow, sqr, cbr, sqrt, root, cbrt, exp

    private static float getDec(int k) {
        return (k < 1) ? 1f : (float) Math.pow(10, k);
    }

    //// pow

    // x ^ y
    public static double pow(double x, double y) {
        return Math.pow(x, y);
    }

    // x ^ 2
    public static double pow2(double x) {
        // x * x
        return Math.pow(x, 2);
    }

    // x ^ 3
    public static double pow3(double x) {
        // x * x * x
        return Math.pow(x, 3);
    }

    // 2 ^ x
    public static double pow2x(double x) {
        return Math.pow(2, x);
    }

    // 10 ^ x
    public static double pow10x(double x) {
        return Math.pow(10, x);
    }

    // 10 ^ x
    public static float pow10x(int x) {
        return (float) Math.pow(10, x);
    }

    // e ^ x
    public static double powEx(double x) {
        // return MathHelper._powEx(x);
        // return exp(x);
        return Math.exp(x);
    }

    // x ^ 2
    public static double sqr(double x) {
        // return MathHelper._pow2(x);
        return pow2(x);
    }

    // x ^ 3
    public static double cbr(double x) {
        // return MathHelper._pow3(x);
        return pow3(x);
    }

    //// root

    // x ^ (1/n) = e ^ (ln(x)/n)
    // https://www.codeflow.site/ru/article/java-nth-root

    public static double root(double x, double n) {
        // return MathHelper._root(x, n);
        return Math.pow(Math.E, Math.log(x) / n);
    }

    // x ^ (1/2)
    public static double root2(double x) {
        // return MathHelper._root2(x);
        return Math.sqrt(x);
    }

    // x ^ (1/3)
    public static double root3(double x) {
        // return MathHelper._root3(x);
        return Math.cbrt(x);
    }

    // x ^ (1/2)
    public static double sqrt(double x) {
        // return MathHelper._root2(x);
        return Math.sqrt(x);
    }

    // x ^ (1/3)
    public static double cbrt(double x) {
        // return MathHelper._root3(x);
        return Math.cbrt(x);
    }

    // e ^ x
    public static double exp(double x) {
        // return MathHelper._exp(x);
        return Math.exp(x);
    }

    //// 2.2 arithmetic/geometric progression/sum/sequence, integer sequence

    /**
     * Arithmetic Progression (formula implementation)
     * 
     * @param a - start value
     * @param n - number of elements
     * @param d - delta
     * @return
     */
    public static long aprog(int a, int n, int d) {
        if (n <= 1) {
            return a;
        }
        return a + (n - 1) * d;
        /*
         * long v = a; for (int i = 1; i < n; i++) { v += d; } return v;
         */
    }

    /**
     * Arithmetic Progression (loop implementation)
     * 
     * @param a - start value
     * @param n - number of elements
     * @param d - delta
     * @return
     */
    public static long aprog2(int a, int n, int d) {
        if (n <= 1) {
            return a;
        }
        long v = a;
        for (int i = 1; i < n; i++) {
            v += d;
        }
        return v;
    }

    /**
     * Arithmetic Progression Sum (formula implementation)
     * 
     * @param a - start value
     * @param n - number of elements
     * @param d - delta
     * @return
     */
    public static long aprogsum(int a, int n, int d) {
        if (n <= 1) {
            return a;
        }
        return (2 * a + (n - 1) * d) * n / 2;
        /*
         * long v = a; long sum = a; for (int i = 1; i < n; i++) { v += d; sum += v; }
         * return sum;
         */
    }

    /**
     * Arithmetic Progression Sum (loop implementation)
     * 
     * @param a - start value
     * @param n - number of elements
     * @param d - delta
     * @return
     */
    public static long aprogsum2(int a, int n, int d) {
        if (n <= 1) {
            return a;
        }
        long v = a;
        long sum = a;
        for (int i = 1; i < n; i++) {
            v += d;
            sum += v;
        }
        return sum;
    }

    /**
     * Arithmetic Progression Sequence
     * 
     * @param a - start value
     * @param n - number of elements
     * @param d - delta
     * @return
     */
    public static long[] aprogseq(int a, int n, int d) {
        if (n < 1) {
            return new long[0];
        }
        long[] result = new long[n];
        long v = a;
        result[0] = v;
        for (int i = 1; i < n; i++) {
            v += d;
            result[i] = v;
        }
        return result;
    }

    //

    /**
     * Geometric Progression (formula implementation)
     * 
     * @param a - start value
     * @param n - number of element
     * @param r - ratio
     * @return
     */
    public static long gprog(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        if (r == 0) {
            return 0;
        }
        return (a * (int) (Math.pow(r, n - 1)));
        /*
         * long v = a; for (int i = 1; i < n; i++) { v *= r; } return v;
         */
    }

    /**
     * Geometric Progression (loop implementation)
     * 
     * @param a - start value
     * @param n - number of element
     * @param r - ratio
     * @return
     */
    public static long gprog2(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        if (r == 0) {
            return 0;
        }
        long v = a;
        for (int i = 1; i < n; i++) {
            v *= r;
        }
        return v;
    }

    /**
     * Geometric Progression Sum
     * 
     * @param a - start value
     * @param n - number of element
     * @param r - ratio
     * @return
     */
    public static long gprogsum(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        if (r == 0) {
            return a; // TODO
            // return 0;
        }

        if (r == 1) {
            // we can't use formula because 1 - r = 0: division by zero
            // loop implementation
            return gprogsum2(a, n, r);
        }

        return a * ((1 - (int) Math.pow(r, n)) / (1 - r));
        /*
         * long v = a; long sum = a; for (int i = 1; i < n; i++) { v *= r; sum += v; }
         * return sum;
         */
    }

    /**
     * Geometric Progression Sum
     * 
     * @param a - start value
     * @param n - number of element
     * @param r - ratio
     * @return
     */
    public static long gprogsum2(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        if (r == 0) {
            return a; // TODO
            // return 0;
        }
        long v = a;
        long sum = a;
        for (int i = 1; i < n; i++) {
            v *= r;
            sum += v;
        }
        return sum;
    }

    /**
     * Geometric Progression Sequence
     * 
     * @param a - start value
     * @param n - number of element
     * @param r - ratio
     * @return
     */
    public static long[] gprogseq(int a, int n, int r) {
        if (n < 1) {
            return new long[0];
        }
        long[] result = new long[n];
        long v = a;
        result[0] = v;
        for (int i = 1; i < n; i++) {
            v *= r;
            result[i] = v;
        }
        return result;
    }

    ////

    /**
     * Integer Sequence: 0 1 2 3 4 5 ...
     * 
     * @param n
     * @return
     */
    public static long[] iseq(int n) {
        return aprogseq(0, n, 1);
    }

    /**
     * Integer Sequence Sum: 0 + 1 + 2 + 3 + 4 + 5 ...
     * 
     * @param n
     * @return
     */
    public static long isum(int n) {
        if (n < 1) {
            return 0;
        }
        return aprogsum(0, n, 1);
    }

    /**
     * N Integer Sequence: 1 2 3 4 5 ...
     * 
     * @param n
     * @return
     */
    public static long[] nseq(int n) {
        return aprogseq(1, n, 1);
    }

    /**
     * N Integer Sum: 1 + 2 + 3 + 4 + 5 ...
     * 
     * @param n
     * @return
     */
    public static long nsum(int n) {
        if (n < 1) {
            return 0;
        }
        return aprogsum(1, n, 1);
    }

    //

    /**
     * N Sequence Sum: 1 + 2 + 3 + 4 + 5 ...
     * 
     * @param value
     * @return
     */
    public static long sumnum(int value) {
        return nsum(value);
    }

//    public static long sumnum(int value) {
//	return MathHelper._sumnum(value);
//    }

    //// 2.3 factorial, fibonaccy

    // https://guava.dev/releases/14.0/api/docs/src-html/com/google/common/math/DoubleMath.html

    public static long fact(int value) {
        if (value < 0) {
            return 0; // error
        }
        if (value == 0) {
            return 1;
        }
        long result = 1;
        for (int i = 1; i <= value; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Return fibonacci value ----------------------------------- n: 0 1 2 3 4 5 6 7
     * 8 9 10 ... f(n): 0 1 1 2 3 5 8 13 21 34 55 ...
     * -----------------------------------
     * 
     * @param n - number in sequence (start with '0')
     * @return
     */
    public static long fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long n1 = 0;
        long n2 = 1;
        long f = 0;

        // int k = 0;
        for (int i = 1; i <= n - 1; i++) {
            f = n1 + n2;

            // TODO
            /*
             * if (f < 0) {
             * 
             * // shift k = i; break; }
             */

            n1 = n2;
            n2 = f;
        }
        return f;
    }

    /**
     * Return fibonacci suquence ------------------------------------ n: 0 1 2 3 4 5
     * 6 7 8 9 10 ... f(n): 0 1 1 2 3 5 8 13 21 34 55 ... fs(n): 1 2 3 5 8 13 21 34
     * 55 ... ------------------------------------
     * 
     * @param n - number in sequence (start with '0')
     * @return
     */
    public static long[] fibseq(int n) {
        return fibseq(n, false);
    }

    /**
     * Return simple fibonacci suquence ------------------------------------ n: 0 1
     * 2 3 4 5 6 7 8 9 10 ... fs(n): 1 2 3 5 8 13 21 34 55 ...
     * ------------------------------------
     * 
     * @param n
     * @return
     */
    public static long[] fibseq2(int n) {
        return fibseq(n, true);
    }

    /**
     * Return fibonacci suquence ------------------------------------ n: 0 1 2 3 4 5
     * 6 7 8 9 10 ... f(n): 0 1 1 2 3 5 8 13 21 34 55 ... fs(n): 1 2 3 5 8 13 21 34
     * 55 ... ------------------------------------ simple sequence: 1 2 5 8 ...
     * (without first elements - 0 1)
     * 
     * @param n      - number in sequence (start with '0')
     * @param simple - flag for simple sequence
     * 
     * @return
     */
    public static long[] fibseq(int n, boolean simple) {
        long[] result;
        if (n <= 0) {
            if (!simple) {
                result = new long[1];
                result[0] = 0; // by default: why?
            } else {
                result = new long[0];
            }
            return result;
        }

        if (n == 1) {
            if (!simple) {
                result = new long[2];
                result[0] = 0; // by default: why?
                result[1] = 1;
            } else {
                result = new long[0];
            }
            return result;
        }

        long n1 = 0;
        long n2 = 1;
        long f = 0;

        result = new long[n];

        result[0] = 0; // by default: why?
        result[1] = 1;

        int k = 0;

        for (int i = 1; i <= n - 1; i++) {
            f = n1 + n2;

            if (f < 0) {

                // shift
                k = i;
                break;
            }

            n1 = n2;
            n2 = f;
            result[i] = f;
        }

        if (k > 0) {
            long[] result2 = new long[k];
            System.arraycopy(result, 0, result2, 0, k);
            return result2;
        }

        return result;
    }

    //// 2.4 mod, isEven, isOdd

    //// mod

    public static int mod(int x, int y) {
        return x % y;
    }

    public static long mod(long x, long y) {
        return x % y;
    }

//    public static float mod(float x, float y) {
//	return x % y;
//    }
//
//    public static double mod(double x, double y) {
//	return x % y;
//    }

    //// min/max

    // min

    public static int min(int x, int y) {
        return Math.min(x, y);
    }

    public static long min(long x, long y) {
        return Math.min(x, y);
    }

    public static float min(float x, float y) {
        return Math.min(x, y);
    }

    public static double min(double x, double y) {
        return Math.min(x, y);
    }

    // max

    public static int max(int x, int y) {
        return Math.max(x, y);
    }

    public static long max(long x, long y) {
        return Math.max(x, y);
    }

    public static float max(float x, float y) {
        return Math.max(x, y);
    }

    public static double max(double x, double y) {
        return Math.max(x, y);
    }

    //// isMod

    public static boolean isMod(int x, int y) {
        return mod(x, y) != 0;
    }

    public static boolean isMod(long x, long y) {
        return mod(x, y) != 0;
    }

    //// isDiv

    public static boolean isDiv(int x, int y) {
        return mod(x, y) == 0;
    }

    public static boolean isDiv(long x, long y) {
        return mod(x, y) == 0;
    }

    ////

    public static boolean isEven(int x) {
        return mod(x, 2) == 0;
    }

    public static boolean isEven(long x) {
        return mod(x, 2) == 0;
    }

    public static boolean isOdd(int x) {
        return !isEven(x);
    }

    public static boolean isOdd(long x) {
        return !isEven(x);
    }

    ////

    public static boolean isNaN(float x) {
        return Float.isNaN(x);
    }

    public static boolean isNaN(double x) {
        return Double.isNaN(x);
    }

    //

    public static boolean isInfinite(float x) {
        return Float.isInfinite(x);
    }

    public static boolean isInfinite(double x) {
        return Double.isInfinite(x);
    }

    //

    public static boolean isPositiveInfinite(float x) {
        return x == Float.POSITIVE_INFINITY;
    }

    public static boolean isPositiveInfinite(double x) {
        return x == Double.POSITIVE_INFINITY;
    }

    //

    public static boolean isNegativeInfinite(float x) {
        return x == Float.NEGATIVE_INFINITY;
    }

    public static boolean isNegativeInfinite(double x) {
        return x == Double.NEGATIVE_INFINITY;
    }

    //// 2.5 log, log2, log10

    // log: log(x, base)
    public static double log(double x, double base) {
        return Math.log(x) / Math.log(base);
    }

    // log: log(x, e) - natural logarithm

    public static double log(double x) {
        return Math.log(x);
    }

    // log2: log(x, 2)

    public static double log2(double x) {
        return Math.log(x) / LN_2;
    }

    // log10: log(x, 10)

    public static double log10(double x) {
        return Math.log10(x);
    }

    // ln: log(x, e) - natural logarithm

    public static double ln(double x) {
        return Math.log(x);
    }

    // lg: log(x, 10)

    public static double lg(double x) {
        return Math.log10(x);
    }

    //// 2.6 hypot, dot (2D, 3D)

    // hypot 2D

    public static int hypot(int x, int y) {
        return GeomLib.hypot(x, y);
    }

    public static float hypot(float x, float y) {
        return GeomLib.hypot(x, y);
    }

    public static double hypot(double x, double y) {
        return GeomLib.hypot(x, y);
    }

    // hypot 3D
    // Java: Not implemented

    public static int hypot(int x, int y, int z) {
        return GeomLib.hypot(x, y);
    }

    public static float hypot(float x, float y, float z) {
        return GeomLib.hypot(x, y);
    }

    public static double hypot(double x, double y, double z) {
        return GeomLib.hypot(x, y);
    }

    // dot 2D

    public static int dot(int x, int y) {
        // x * x + y * y
        return GeomLib.dot(x, y);
    }

    public static float dot(float x, float y) {
        // x * x + y * y
        return GeomLib.dot(x, y);
    }

    public static double dot(double x, double y) {
        // x * x + y * y
        return GeomLib.dot(x, y);
    }

    // dot 3D

    public static int dot(int x, int y, int z) {
        // x * x + y * y + z * z
        return GeomLib.dot(x, y, z);
    }

    public static float dot(float x, float y, float z) {
        // x * x + y * y + z * z
        return GeomLib.dot(x, y, z);
    }

    public static double dot(double x, double y, double z) {
        // x * x + y * y + z * z
        return GeomLib.dot(x, y, z);
    }

    //// 3.1 sin, cos, tan, asin, acos, atan, sinh, cosh, tanh

    //// RADIAN BY DEFAULT

    // sin, cos, tan

    public static double sin(double a) {
        return TrigonomLib.sin(a);
    }

    public static double cos(double a) {
        return TrigonomLib.cos(a);
    }

    public static double tan(double a) {
        return TrigonomLib.tan(a);
    }

    // csc, sec, cot

    public static double csc(double a) {
        return TrigonomLib.csc(a);
    }

    public static double sec(double a) {
        return TrigonomLib.sec(a);
    }

    public static double cot(double a) {
        return TrigonomLib.cot(a);
    }

    // asin, acos, atan

    public static double asin(double a) {
        return TrigonomLib.asin(a);
    }

    public static double acos(double a) {
        return TrigonomLib.acos(a);
    }

    public static double atan(double a) {
        return TrigonomLib.atan(a);
    }

    // acsc, asec, acot

    public static double acsc(double a) {
        return TrigonomLib.acsc(a);
    }

    public static double asec(double a) {
        return TrigonomLib.asec(a);
    }

    public static double acot(double a) {
        return TrigonomLib.acot(a);
    }

    // hyperbolic: sinh, cosh, tanh

    public static double sinh(double a) {
        return TrigonomLib.sinh(a);
    }

    public static double cosh(double a) {
        return TrigonomLib.cosh(a);
    }

    public static double tanh(double a) {
        return TrigonomLib.tanh(a);
    }

    // hyperbolic: csch, sech, coth

    public static double csch(double a) {
        return TrigonomLib.csch(a);
    }

    public static double sech(double a) {
        return TrigonomLib.sech(a);
    }

    public static double coth(double a) {
        return TrigonomLib.coth(a);
    }

    // hyperbolic: asinh, acosh, atanh

    public static double asinh(double a) {
        return TrigonomLib.asinh(a);
    }

    public static double acosh(double a) {
        return TrigonomLib.acosh(a);
    }

    public static double atanh(double a) {
        return TrigonomLib.atanh(a);
    }

    // hyperbolic: acsch, asech, acoth

    public static double acsch(double a) {
        return TrigonomLib.acsch(a);
    }

    public static double asech(double a) {
        return TrigonomLib.asech(a);
    }

    public static double acoth(double a) {
        return TrigonomLib.acoth(a);
    }

    //// 3.2 RADIAN: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh

    // radian: sin, cos, tan

    public static double sinRadian(double a) {
        return TrigonomLib.sinRadian(a);
    }

    public static double cosRadian(double a) {
        return TrigonomLib.cosRadian(a);
    }

    public static double tanRadian(double a) {
        return TrigonomLib.tanRadian(a);
    }

    // radian: csc, sec, cot

    public static double cscRadian(double a) {
        return TrigonomLib.cscRadian(a);
    }

    public static double secRadian(double a) {
        return TrigonomLib.secRadian(a);
    }

    public static double cotRadian(double a) {
        return TrigonomLib.cotRadian(a);
    }

    // radian: asin, acos, atan

    public static double asinRadian(double a) {
        return TrigonomLib.asinRadian(a);
    }

    public static double acosRadian(double a) {
        return TrigonomLib.acosRadian(a);
    }

    public static double atanRadian(double a) {
        return TrigonomLib.atanRadian(a);
    }

    // radian: acsc, asec, acot

    public static double acscRadian(double a) {
        return TrigonomLib.acscRadian(a);
    }

    public static double asecRadian(double a) {
        return TrigonomLib.asecRadian(a);
    }

    public static double acotRadian(double a) {
        return TrigonomLib.acotRadian(a);
    }

    // hyperbolic/radian: sinh, cosh, tanh

    public static double sinhRadian(double a) {
        return TrigonomLib.sinhRadian(a);
    }

    public static double coshRadian(double a) {
        return TrigonomLib.coshRadian(a);
    }

    public static double tanhRadian(double a) {
        return TrigonomLib.tanhRadian(a);
    }

    // hyperbolic/radian: csch, sech, coth

    public static double cschRadian(double a) {
        return TrigonomLib.cschRadian(a);
    }

    public static double sechRadian(double a) {
        return TrigonomLib.sechRadian(a);
    }

    public static double cothRadian(double a) {
        return TrigonomLib.cothRadian(a);
    }

    // hyperbolic/radian: asinh, acosh, atanh

    public static double asinhRadian(double a) {
        return TrigonomLib.asinhRadian(a);
    }

    public static double acoshRadian(double a) {
        return TrigonomLib.acoshRadian(a);
    }

    public static double atanhRadian(double a) {
        return TrigonomLib.atanhRadian(a);
    }

    // hyperbolic/radian: acsch, asech, acoth

    public static double acschRadian(double a) {
        return TrigonomLib.acschRadian(a);
    }

    public static double asechRadian(double a) {
        return TrigonomLib.asechRadian(a);
    }

    public static double acothRadian(double a) {
        return TrigonomLib.acothRadian(a);
    }

    //// 3.3 DEGREE: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh

    // degree: sin, cos, tan

    public static double sinDegree(double a) {
        return TrigonomLib.sinDegree(a);
    }

    public static double cosDegree(double a) {
        return TrigonomLib.cosDegree(a);
    }

    public static double tanDegree(double a) {
        return TrigonomLib.tanDegree(a);
    }

    // degree: csc, sec, cot

    public static double cscDegree(double a) {
        return TrigonomLib.cscDegree(a);
    }

    public static double secDegree(double a) {
        return TrigonomLib.secDegree(a);
    }

    public static double cotDegree(double a) {
        return TrigonomLib.cotDegree(a);
    }

    // degree: asin, acos, atan

    public static double asinDegree(double a) {
        return TrigonomLib.asinDegree(a);
    }

    public static double acosDegree(double a) {
        return TrigonomLib.acosDegree(a);
    }

    public static double atanDegree(double a) {
        return TrigonomLib.atanDegree(a);
    }

    // degree: acsc, asec, acot

    public static double acscDegree(double a) {
        return TrigonomLib.acscDegree(a);
    }

    public static double asecDegree(double a) {
        return TrigonomLib.asecDegree(a);
    }

    public static double acotDegree(double a) {
        return TrigonomLib.acotDegree(a);
    }

    // hyperbolic/degree: sinh, cosh, tanh

    public static double sinhDegree(double a) {
        return TrigonomLib.sinhDegree(a);
    }

    public static double coshDegree(double a) {
        return TrigonomLib.coshDegree(a);
    }

    public static double tanhDegree(double a) {
        return TrigonomLib.tanhDegree(a);
    }

    // hyperbolic/degree: csch, sech, coth

    public static double cschDegree(double a) {
        return TrigonomLib.cschDegree(a);
    }

    public static double sechDegree(double a) {
        return TrigonomLib.sechDegree(a);
    }

    public static double cothDegree(double a) {
        return TrigonomLib.cothDegree(a);
    }

    // hyperbolic/degree: asinh, acosh, atanh

    public static double asinhDegree(double a) {
        return TrigonomLib.asinhDegree(a);
    }

    public static double acoshDegree(double a) {
        return TrigonomLib.acoshDegree(a);
    }

    public static double atanhDegree(double a) {
        return TrigonomLib.atanhDegree(a);
    }

    // hyperbolic/degree: acsch, asech, acoth

    public static double acschDegree(double a) {
        return TrigonomLib.acschDegree(a);
    }

    public static double asechDegree(double a) {
        return TrigonomLib.asechDegree(a);
    }

    public static double acothDegree(double a) {
        return TrigonomLib.acothDegree(a);
    }

    //// BY UNIT

    // by unit: sin, cos, tan

    public static double sin(double a, AngleUnit unit) {
        return TrigonomLib.sin(a, unit);
    }

    public static double cos(double a, AngleUnit unit) {
        return TrigonomLib.cos(a, unit);
    }

    public static double tan(double a, AngleUnit unit) {
        return TrigonomLib.tan(a, unit);
    }

    // by unit: csc, sec, cot

    public static double csc(double a, AngleUnit unit) {
        return TrigonomLib.csc(a, unit);
    }

    public static double sec(double a, AngleUnit unit) {
        return TrigonomLib.sec(a, unit);
    }

    public static double cot(double a, AngleUnit unit) {
        return TrigonomLib.cot(a, unit);
    }

    // by unit: asin, acos, atan

    public static double asin(double a, AngleUnit unit) {
        return TrigonomLib.asin(a, unit);
    }

    public static double acos(double a, AngleUnit unit) {
        return TrigonomLib.acos(a, unit);
    }

    public static double atan(double a, AngleUnit unit) {
        return TrigonomLib.atan(a, unit);
    }

    // by unit: acsc, asec, acot

    public static double acsc(double a, AngleUnit unit) {
        return TrigonomLib.acsc(a, unit);
    }

    public static double asec(double a, AngleUnit unit) {
        return TrigonomLib.asec(a, unit);
    }

    public static double acot(double a, AngleUnit unit) {
        return TrigonomLib.acot(a, unit);
    }

    // hyperbolic/by unit: sinh, cosh, tanh

    public static double sinh(double a, AngleUnit unit) {
        return TrigonomLib.sinh(a, unit);
    }

    public static double cosh(double a, AngleUnit unit) {
        return TrigonomLib.cosh(a, unit);
    }

    public static double tanh(double a, AngleUnit unit) {
        return TrigonomLib.tanh(a, unit);
    }

    // hyperbolic/by unit: csch, sech, coth

    public static double csch(double a, AngleUnit unit) {
        return TrigonomLib.csch(a, unit);
    }

    public static double sech(double a, AngleUnit unit) {
        return TrigonomLib.sech(a, unit);
    }

    public static double coth(double a, AngleUnit unit) {
        return TrigonomLib.coth(a, unit);
    }

    // hyperbolic/by unit: asinh, acosh, atanh

    public static double asinh(double a, AngleUnit unit) {
        return TrigonomLib.asinh(a, unit);
    }

    public static double acosh(double a, AngleUnit unit) {
        return TrigonomLib.acosh(a, unit);
    }

    public static double atanh(double a, AngleUnit unit) {
        return TrigonomLib.atanh(a, unit);
    }

    // hyperbolic/by unit: acsch, asech, acoth

    public static double acsch(double a, AngleUnit unit) {
        return TrigonomLib.acsch(a, unit);
    }

    public static double asech(double a, AngleUnit unit) {
        return TrigonomLib.asech(a, unit);
    }

    public static double acoth(double a, AngleUnit unit) {
        return TrigonomLib.acoth(a, unit);
    }

    //// 3.4 Util

    public static double toRadians(double angdeg) {
        return TrigonomLib.toRadians(angdeg);
    }

    public static double toDegrees(double angrad) {
        return TrigonomLib.toDegrees(angrad);
    }
    
}
