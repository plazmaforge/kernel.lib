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

package plazma.lib.trigonom;

import plazma.lib.AngleUnit;

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

public class TrigonomLib {
    
    public static final double PI_2 = Math.PI / 2;

    // Functions:
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1. RADIAN BY DEFAULT: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
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
    // 2. RADIAN: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
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
    // 3. DEGREE sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
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
    // 4. BY UNIT: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
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
    // 5. Util
    //
    // - toRadians(double angdeg)
    // - toDegrees(double angrad)
    
    
    // Inverse hyperbolic functions
    // https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions#atanh
    
    private TrigonomLib() {
    }

    //// 1. RADIAN BY DEFAULT

    // sin, cos, tan

    public static double sin(double a) {
        return sinRadian(a);
    }

    public static double cos(double a) {
        return cosRadian(a);
    }

    public static double tan(double a) {
        return tanRadian(a);
    }

    // csc, sec, cot

    public static double csc(double a) {
        return cscRadian(a);
    }

    public static double sec(double a) {
        return secRadian(a);
    }

    public static double cot(double a) {
        return cotRadian(a);
    }

    // asin, acos, atan

    public static double asin(double a) {
        return asinRadian(a);
    }

    public static double acos(double a) {
        return acosRadian(a);
    }

    public static double atan(double a) {
        return atanRadian(a);
    }

    // acsc, asec, acot

    public static double acsc(double a) {
        return acscRadian(a);
    }

    public static double asec(double a) {
        return asecRadian(a);
    }

    public static double acot(double a) {
        return acotRadian(a);
    }

    // hyperbolic: sinh, cosh, tanh

    public static double sinh(double a) {
        return sinhRadian(a);
    }

    public static double cosh(double a) {
        return coshRadian(a);
    }

    public static double tanh(double a) {
        return tanhRadian(a);
    }

    // hyperbolic: csch, sech, coth

    public static double csch(double a) {
        return cschRadian(a);
    }

    public static double sech(double a) {
        return sechRadian(a);
    }

    public static double coth(double a) {
        return cothRadian(a);
    }

    // hyperbolic: asinh, acosh, atanh

    public static double asinh(double a) {
        return asinhRadian(a);
    }

    public static double acosh(double a) {
        return acoshRadian(a);
    }

    public static double atanh(double a) {
        return atanhRadian(a);
    }

    // hyperbolic: acsch, asech, acoth

    public static double acsch(double a) {
        return acschRadian(a);
    }

    public static double asech(double a) {
        return asechRadian(a);
    }

    public static double acoth(double a) {
        return acothRadian(a);
    }

    //// 2. RADIAN: General formulas

    // radian: sin, cos, tan

    public static double sinRadian(double a) {
        return Math.sin(a);
    }

    public static double cosRadian(double a) {
        return Math.cos(a);
    }

    public static double tanRadian(double a) {
        return Math.tan(a);
    }

    // radian: csc, sec, cot
    // Java: Not implemented

    public static double cscRadian(double a) {
        return 1 / Math.sin(a);
    }

    public static double secRadian(double a) {
        return 1 / Math.cos(a);
    }

    public static double cotRadian(double a) {
        return 1 / Math.tan(a);
    }

    // radian: asin, acos, atan

    public static double asinRadian(double a) {
        return Math.asin(a);
    }

    public static double acosRadian(double a) {
        return Math.acos(a);
    }

    public static double atanRadian(double a) {
        return Math.atan(a);
    }

    // radian: acsc, asec, acot
    // Java: Not implemented

    public static double acscRadian(double a) {
        return PI_2 - Math.asin(a);
    }

    public static double asecRadian(double a) {
        return PI_2 - Math.acos(a);
    }

    public static double acotRadian(double a) {
        return PI_2 - Math.atan(a);
    }

    // hyperbolic/radian: sinh, cosh, tanh

    public static double sinhRadian(double a) {
        return Math.sinh(a);
    }

    public static double coshRadian(double a) {
        return Math.cosh(a);
    }

    public static double tanhRadian(double a) {
        return Math.tanh(a);
    }

    // hyperbolic/radian: csch, sech, coth
    // Java: Not implemented

    public static double cschRadian(double a) {
        return 1 / Math.sinh(a);
    }

    public static double sechRadian(double a) {
        return 1 / Math.cosh(a);
    }

    public static double cothRadian(double a) {
        return 1 / Math.tanh(a);
    }

    // hyperbolic/radian: asinh, acosh, atanh
    // Java: Not implemented

    // hyperbolic arc sine: ln(x + √(x^2 + 1))
    // range (-Infinity, +Infinity)
    public static double asinhRadian(double a) {

        // See C++: std:atanh(a)

        if (Double.isNaN(a) || Double.isInfinite(a)) {
            return a;
        }

        return Math.log(a + Math.sqrt(a * a + 1.0));
    }

    // hyperbolic arc cosine: ln(x + √(x^2 - 1))
    // range [1, +Infinity) : x >= 1
    //
    // x < 1 => NaN
    public static double acoshRadian(double a) {

        // See C++: std:acosh(a)

        if (Double.isNaN(a) || a == Double.NEGATIVE_INFINITY) {
            return Double.NaN;
        }

        if (a == Double.POSITIVE_INFINITY) {
            return a;
        }

        if (a < 1) {
            return Double.NaN;
        }
        return Math.log(a + Math.sqrt(a * a - 1.0));
    }

    // hyperbolic arc tangent: ln((1 + a) / (1 - a)) / 2
    // range [-1, 1]: x >= -1 and x <= 1
    //
    // x = -1 => -Infinity
    // x = 1 => Infinity
    // x < -1 or > x > 1 => NaN
    public static double atanhRadian(double a) {

        // See C++: std:atanh(a)

        if (Double.isNaN(a) || Double.isInfinite(a)) {
            return Double.NaN;
        }

        if (a == -1) {
            return Double.NEGATIVE_INFINITY;
        }

        if (a == 1) {
            return Double.POSITIVE_INFINITY;
        }

        if (a < -1 || a > 1) {
            return Double.NaN;
        }

        return Math.log((1 + a) / (1 - a)) / 2;
    }

    // hyperbolic arc cosecant: ln(1 / a + √(1 / a^2 + 1))
    public static double acschRadian(double a) {
        return Math.log(1 / a + Math.sqrt((1 / (a * a)) + 1));
    }

    // hyperbolic arc secant: ln(1 / a + √(1 / a^2 - 1))
    // range (0, 1]
    //
    // x = 1 => ??? Infinity
    // x <= 0 => NaN
    public static double asechRadian(double a) {
        if (a <= 0 || a > 1) {
            return Double.NaN;
        }
        return Math.log(1 / a + Math.sqrt((1 / (a * a)) - 1));
    }

    // hyperbolic arc cotangent: ln((a + 1) / (a - 1)) / 2
    // range (-Infinity, -1) and (1, +Infinity)
    //
    // x >= -1 and x <= 1 => NaN
    public static double acothRadian(double a) {
        if (a >= -1 && a <= 1) {
            return Double.NaN;
        }
        return Math.log((a + 1) / (a - 1)) / 2;
    }

    //// 3. DEGREE

    // degree: sin, cos, tan

    public static double sinDegree(double a) {
        return sinRadian(toRadians(a));
    }

    public static double cosDegree(double a) {
        return cosRadian(toRadians(a));
    }

    public static double tanDegree(double a) {
        return tanRadian(toRadians(a));
    }

    // degree: csc, sec, cot

    public static double cscDegree(double a) {
        return cscRadian(toRadians(a));
    }

    public static double secDegree(double a) {
        return secRadian(toRadians(a));
    }

    public static double cotDegree(double a) {
        return cotRadian(toRadians(a));
    }

    // degree: asin, acos, atan

    public static double asinDegree(double a) {
        return toDegrees(asinRadian(a));
    }

    public static double acosDegree(double a) {
        return toDegrees(acosRadian(a));
    }

    public static double atanDegree(double a) {
        return toDegrees(atanRadian(a));
    }

    // degree: acsc, asec, acot

    public static double acscDegree(double a) {
        return toDegrees(acscRadian(a));
    }

    public static double asecDegree(double a) {
        return toDegrees(asecRadian(a));
    }

    public static double acotDegree(double a) {
        return toDegrees(acotRadian(a));
    }

    // hyperbolic/degree: sinh, cosh, tanh

    public static double sinhDegree(double a) {
        return sinhRadian(toRadians(a));
    }

    public static double coshDegree(double a) {
        return coshRadian(toRadians(a));
    }

    public static double tanhDegree(double a) {
        return tanhRadian(toRadians(a));
    }

    // hyperbolic/degree: csch, sech, coth

    public static double cschDegree(double a) {
        return cschRadian(toRadians(a));
    }

    public static double sechDegree(double a) {
        return sechRadian(toRadians(a));
    }

    public static double cothDegree(double a) {
        return cothRadian(toRadians(a));
    }

    // hyperbolic/degree: asinh, acosh, atanh

    public static double asinhDegree(double a) {
        return toDegrees(asinhRadian(a));
    }

    public static double acoshDegree(double a) {
        return toDegrees(acoshRadian(a));
    }

    public static double atanhDegree(double a) {
        return toDegrees(atanhRadian(a));
    }

    // hyperbolic/degree: acsch, asech, acoth

    public static double acschDegree(double a) {
        return toDegrees(acschRadian(a));
    }

    public static double asechDegree(double a) {
        return toDegrees(asechRadian(a));
    }

    public static double acothDegree(double a) {
        return toDegrees(acothRadian(a));
    }

    //// 4. BY UNIT

    // by unit: sin, cos, tan

    public static double sin(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? sinDegree(a) : sinRadian(a);
    }

    public static double cos(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? cosDegree(a) : cosRadian(a);
    }

    public static double tan(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? tanDegree(a) : tanRadian(a);
    }

    // by unit: csc, sec, cot

    public static double csc(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? cscDegree(a) : cscRadian(a);
    }

    public static double sec(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? secDegree(a) : secRadian(a);
    }

    public static double cot(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? cotDegree(a) : cotRadian(a);
    }

    // by unit: asin, acos, atan

    public static double asin(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? asinDegree(a) : asinRadian(a);
    }

    public static double acos(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? acosDegree(a) : acosRadian(a);
    }

    public static double atan(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? atanDegree(a) : atanRadian(a);
    }

    // by unit: acsc, asec, acot

    public static double acsc(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? acscDegree(a) : acscRadian(a);
    }

    public static double asec(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? asecDegree(a) : asecRadian(a);
    }

    public static double acot(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? acotDegree(a) : acotRadian(a);
    }

    // hyperbolic/by unit: sinh, cosh, tanh

    public static double sinh(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? sinhDegree(a) : sinhRadian(a);
    }

    public static double cosh(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? coshDegree(a) : coshRadian(a);
    }

    public static double tanh(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? tanhDegree(a) : tanhRadian(a);
    }

    // hyperbolic/by unit: csch, sech, coth

    public static double csch(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? cschDegree(a) : cschRadian(a);
    }

    public static double sech(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? sechDegree(a) : sechRadian(a);
    }

    public static double coth(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? cothDegree(a) : cothRadian(a);
    }

    // hyperbolic/by unit: asinh, acosh, atanh

    public static double asinh(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? asinhDegree(a) : asinhRadian(a);
    }

    public static double acosh(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? acoshDegree(a) : acoshRadian(a);
    }

    public static double atanh(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? atanhDegree(a) : atanhRadian(a);
    }

    // hyperbolic/by unit: acsch, asech, acoth

    public static double acsch(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? acschDegree(a) : acschRadian(a);
    }

    public static double asech(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? asechDegree(a) : asechRadian(a);
    }

    public static double acoth(double a, AngleUnit unit) {
        return unit == AngleUnit.DEGREE ? acothDegree(a) : acothRadian(a);
    }

    //// 5. UTILS

    public static double toRadians(double angdeg) {
        return Math.toRadians(angdeg);
    }

    public static double toDegrees(double angrad) {
        return Math.toDegrees(angrad);
    }
    
}
