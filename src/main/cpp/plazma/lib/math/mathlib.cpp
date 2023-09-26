
#include <cmath>
#include <vector>
#include "mathlib.h"

// Functions:

/////////////////////////////////////////////////////////////////////////////////
//
// 1.1 +, -, *, /
//
// -? add(1, 2):      1 + 2 = 3
// -? sub(3, 2):      3 - 2 = 1
// -? mul(2, 3):      2 * 3 = 6
// -? div(7, 5):      7 / 5 = 1.4 (float division)
// -? divFloat(7, 5): 7 / 5 = 1.4 (float division)
// -? divInt(7, 5):   7 / 5 = 1   (integer division)
//
// 1.2 trunc, round, ceil, floor, rint, sign, abs
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
// - round(float x)
// - round(double x)
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
// 2.1 pow, sqr, cbr, root, sqrt, cbrt, exp
//
// - pow(double x, double y)             - x ^ y
// - pow2(double x)                      - x ^ 2
// - pow3(double x)                      - x ^ 3
//
// - pow2x(double x)          : double   - 2 ^ x
// - pow10x(double x)         : double   - 10 ^ x
// - pow10x(int x)            : float    - 10 ^ x
// - powEx(double x)          : double   - e ^ x
//
// - sqr(double x)                       - x ^ 2
// - cbr(double x)                       - x ^ 3
//
// - root(double x, double n)            - x ^ (1/n)
// - root2(double x)                     - x ^ (1/2)
// - root3(double x)                     - x ^ (1/3)
//
// - sqrt(double x)                      - x ^ (1/2)
// - cbrt(double x)                      - x ^ (1/3)
//
// - exp(double x)                       - e ^ x

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
// - factorial(int n)                    - factorial
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
//
// 2.5 log, log2, log10
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
// 3.4 Util
//
// - toRadians(double angdeg)
// - toDegrees(double angrad)





// #define M_E         2.71828182845904523536028747135266250   /* e              */
// #define M_LOG2E     1.44269504088896340735992468100189214   /* log2(e)        */
// #define M_LOG10E    0.434294481903251827651128918916605082  /* log10(e)       */
// #define M_LN2       0.693147180559945309417232121458176568  /* loge(2)        */
// #define M_LN10      2.30258509299404568401799145468436421   /* loge(10)       */
// #define M_PI        3.14159265358979323846264338327950288   /* pi             */
// #define M_PI_2      1.57079632679489661923132169163975144   /* pi/2           */
// #define M_PI_4      0.785398163397448309615660845819875721  /* pi/4           */
// #define M_1_PI      0.318309886183790671537767526745028724  /* 1/pi           */
// #define M_2_PI      0.636619772367581343075535053490057448  /* 2/pi           */
// #define M_2_SQRTPI  1.12837916709551257389615890312154517   /* 2/sqrt(pi)     */
// #define M_SQRT2     1.41421356237309504880168872420969808   /* sqrt(2)        */
// #define M_SQRT1_2   0.707106781186547524400844362104849039  /* 1/sqrt(2)      */

// #define M_E		   2.7182818284590452354
// #define M_LOG2E	   1.4426950408889634074
// #define M_LOG10E	   0.43429448190325182765
// #define M_LN2	   0.69314718055994530942
// #define M_LN10	   2.30258509299404568402
// #define M_PI		   3.14159265358979323846
// #define M_PI_2	   1.57079632679489661923
// #define M_PI_4	   0.78539816339744830962
// #define M_1_PI	   0.31830988618379067154
// #define M_2_PI	   0.63661977236758134308
// #define M_2_SQRTPI  1.12837916709551257390
// #define M_SQRT2	   1.41421356237309504880
// #define M_SQRT1_2   0.70710678118654752440

const double E         = 2.7182818284590452354;
const double LOG2E	   = 1.4426950408889634074;
const double LOG10E	   = 0.43429448190325182765;
const double LN2	   = 0.69314718055994530942;
const double LN10	   = 2.30258509299404568402;
const double PI        = 3.14159265358979323846;
const double PI_2      = 1.57079632679489661923;
const double PI_4	   = 0.78539816339744830962;
const double _1_PI	   = 0.31830988618379067154;
const double _2_PI	   = 0.63661977236758134308;
const double _2_SQRTPI = 1.12837916709551257390;
const double SQRT2	   = 1.41421356237309504880;
const double SQRT1_2   = 0.70710678118654752440;

// https://mathjs.org/docs/reference/functions.html

namespace mathlib {

    //// 1.1

    
    //// Overflow: Operator: '+': ((x ^ r) & (y ^ r)) < 0

    // int

    bool isOverflowAdd(int x, int y, int r) {
        return ((x ^ r) & (y ^ r)) < 0;
    }

    //bool isOverflowAdd(int x, int y, int r, ArithmeticContext context) {
    //    return isOverflowAdd(x, y, r) && isOverflowException(context);
    //}

    // long

    bool isOverflowAdd(long x, long y, long r) {
        return ((x ^ r) & (y ^ r)) < 0;
    }

    //bool isOverflowAdd(long x, long y, long r, ArithmeticContext context) {
    //    return isOverflowAdd(x, y, r) && isOverflowException(context);
    //}


    //// Overflow: Operator: '-': ((x ^ y) & (x ^ r)) < 0

    // int

    bool isOverflowSub(int x, int y, int r) {
        return ((x ^ y) & (x ^ r)) < 0;
    }

    //bool isOverflowSub(int x, int y, int r, ArithmeticContext context) {
    //    return isOverflowSub(x, y, r) && isOverflowException(context);
    //}

    // long

    bool isOverflowSub(long x, long y, long r) {
        return ((x ^ y) & (x ^ r)) < 0;
    }

    //bool isOverflowSub(long x, long y, long r, ArithmeticContext context) {
    //    return isOverflowSub(x, y, r) && isOverflowException(context);
    //}

    //// add: '+'

    int add(int x, int y /*, ArithmeticContext context*/) {
        int r = x + y;
        //if (isOverflowAdd(x, y, r) && isOverflowException(context)) {
        //    throwOverflowException("+", "Integer");
        //}
        return r;
    }

    int add(long x, long y /*, ArithmeticContext context*/) {
        long r = x + y;
        //if (isOverflowAdd(x, y, r) && isOverflowException(context)) {
        //    throwOverflowException("+", "Integer");
        //}
        return r;
    }

    //// sub: '-'

    int sub(int x, int y /*, ArithmeticContext context*/) {
        int r = x - y;
        //if (isOverflowSub(x, y, r) && isOverflowException(context)) {
        //    throwOverflowException("-", "Integer");
        //}
        return r;
    }

    int sub(long x, long y /*, ArithmeticContext context*/) {
        long r = x - y;
        //if (isOverflowSub(x, y, r) && isOverflowException(context)) {
        //    throwOverflowException("-", "Integer");
        //}
        return r;
    }

    //// mul: '*'

    int mul(int x, int y /*, ArithmeticContext context*/) {
        int r = x * y;
        return r;
        //long r = (long) x * (long) y;
        //if ((int) r != r && isOverflowException(context)) {
        //    throwOverflowException("*", "Integer");
        //}
        //return (int) r;
    }

    long mul(long x, long y /*, ArithmeticContext context*/) {
        long r = x * y;
        return r;
        //long long r = (long long) x * (long long ) y;
        //if ((long) r != r && isOverflowException(context)) {
        //    throwOverflowException("*", "Integer");
        //}
        //return (long) r;
    }

    //// divInt: '/'

    int divInt(int x, int y) {
        int r = x / y;
        return r;
    }

    long divInt(long x, long y) {
        long r = x / y;
        return r;
    }

    //// divFloat: '/'

    float divFloat(int x, int y) {
        float r = (float) x / (float) y;
        return r;
    }

    double divFloat(long x, long y) {
        double r = (double) x / (double) y;
        return r;
    }

    //// div: '/' - divInt by default

    int div(int x, int y) {
        return divInt(x, y);
    }

    long div(long x, long y) {
        return divInt(x, y);
    }

    //// 1.2 trunc, round, ceil, floor, rint, sign, abs

    // trunc, truncInt, truntDec

    // cast: lost data
    int intValue(float value) {
        return (int) value;
    }

    // cast: lost data
    long longValue(double value) {
        return (long) value;
    }

    ////

    // 23.45 => 23.0
    float trunc(float x) {
        // cast to int
        return intValue(x);
    }

    // 23.45 => 23.0
    double trunc(double x) {
        // cast to long
        return longValue(x);
    }

    ////

    // 23.45 => 23
    int truncInt(float x) {
        return intValue(x);
    }

    // 23.45 => 23
    long truncInt(double x) {
        return longValue(x);
    }

    ////

    // 23.45 => 0.45
    float truncDec(float x) {
        return x - intValue(x);
    }

    // 23.45 => 0.45
    double truncDec(double x) {
        return x - longValue(x);
    }

    //
    
    // trunc(23.45, 1) => 23.4
    // trunc(23.45, 2) => 23.45
    float trunc(float value, int pos) {
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
    double trunc(double value, int pos) {
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

    // round

    float round(float x) {
        return std::round(x);
    }

    double round(double x) {
        return std::round(x);
    }

    // 

    int roundInt(float x) {
        return std::round(x);
    }

    long roundInt(double x) {
        return std::round(x);
    }

    // round(23.45, 1) => 23.5
    // round(23.45, 2) => 23.45
    float round(float x, int pos) {
        if (pos == 0) {
            return round(x);
        }
        int n = pos < 0 ? (-1 * pos) : pos;
        float dec = getDec(n);
        if (pos < 0) {
            return round(x / dec) * dec;
        } else {
            return round(x * dec) / dec;
        }
    }
    
    // round(23.45, 1) => 23.5
    // round(23.45, 2) => 23.45
    double round(double x, int pos) {
        if (pos == 0) {
            return round(x);
        }
        int n = pos < 0 ? (-1 * pos) : pos;
        float dec = getDec(n);
        if (pos < 0) {
            return round(x / dec) * dec;
        } else {
            return round(x * dec) / dec;
        }
    }

    // ceil

    float ceil(float x) {
        return std::ceil(x);
    }

    double ceil(double x) {
        return std::ceil(x);
    }

    // floor

    float floor(float x) {
        return std::floor(x);
    }

    double floor(double x) {
        return std::floor(x);
    }

    // rint

    float rint(float x) {
        return std::rint(x);
    }

    double rint(double x) {
        return std::rint(x);
    }

    // sign

    int sign(int x) {
        return x < 0 ? -1 : (x > 0 ? 1 : 0);
    }

    long sign(long x) {
        return x < 0 ? -1 : (x > 0 ? 1 : 0);
    }

    float sign(float x) {
        // NAN (?)
        return x < 0.0f ? -1.0f : (x > 0.0f ? 1.0f : 0.0f);
    }

    double sign(double x) {
        // NAN (?)
        return x < 0.0f ? -1.0f : (x > 0.0f ? 1.0f : 0.0f);
    }

    // abs

    int abs(int x) {
        return std::abs(x);
    }

    long abs(long x) {
        return std::abs(x);
    }

    float abs(float x) {
        return std::abs(x);
    }

    double abs(double x) {
        return std::abs(x);
    }

    //// 2.1 pow, sqr, cbr, root, sqrt, cbrt, exp

    float getDec(int k) {
        return pow10x(k);
    }    

    //// pow

    // x ^ y
    double pow(double x, double y) {
        return std::pow(x, y);
    }

    // x ^ 2
    double pow2(double x) {
        return std::pow(x, 2);
    }

    // x ^ 3
    double pow3(double x) {
        return std::pow(x, 3);
    }

    // 2 ^ x
    double pow2x(double x) {
        return std::pow(2, x);
    }

    // 10 ^ x
    double pow10x(double x) {
        return std::pow(10, x);
    }

    // 10 ^ x
    float pow10x(int x) {
        if (x == 1) {
           return 10; 
        } else if (x == 2) {
           return 100; 
        } else if (x == 3) {
           return 1000; 
        } else if (x == 4) {
           return 10000; 
        }
        return pow(10, x); 
    }

    // e ^ x
    double powEx(double x) {
        return std::exp(x);
    }

    // x ^ 2
    double sqr(double x) {
        return std::pow(x, 2);
    }

    // x ^ 3
    double cbr(double x) {
        return std::pow(x, 3);
    }

    //// root
    
    // x ^ (1/n) = e ^ (ln(x)/n)
    // https://www.codeflow.site/ru/article/java-nth-root
    double _root(double x, double n) {
        return std::pow(E, std::log(x) / n);
    }

    // x ^ (1/2)
    double root2(double x) {
        return std::sqrt(x);
    }
    
    // x ^ (1/3)
    double root3(double x) {
        return std::cbrt(x);
    }
     
    // x ^ (1/2)
    double sqrt(double x) {
        return std::sqrt(x);
    }

    // x ^ (1/3)
    double cbrt(double x) {
        return std::cbrt(x);
    }

    // e ^ x
    double exp(double x) {
        return std::exp(x);
    }
    
    //// 2.2 arithmetic/geometric progression/sum/sequence, integer sequence

    /*
     Arithmetic Progression (formula implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprog(int a, int n, int d) {
        if (n <= 1) {
            return a;
        }
        return a + (n - 1) * d;
        /*
        long v = a;
        for (int i = 1; i < n; i++) {
            v += d;
        }
        return v;
        */
  	}

    /*
     Arithmetic Progression (loop implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprog2(int a, int n, int d) {
        if (n <= 1) {
            return a;
        }
        long v = a;
        for (int i = 1; i < n; i++) {
            v += d;
        }
        return v;
  	}

    /*
     Arithmetic Progression Sum (formula implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprogsum(int a, int n, int d) {
        if (n <= 1) {
            return a;
        }
        return (2 * a + (n - 1) * d) * n / 2;
        /*
        long v = a;
        long sum = a;
        for (int i = 1; i < n; i++) {
            v += d;
            sum += v;
        }
        return sum;
        */
  	}

    /*
     Arithmetic Progression Sum (loop implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprogsum2(int a, int n, int d) {
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

    /*
     Arithmetic Progression Sequence
     a - start value
     n - number of elements
     d - delta
    */
    std::vector<long> aprogseq(int a, int n, int d) {
        std::vector<long> result;
        if (n < 1) {
            return result;
        }
        long v = a;
        result.push_back(v);
        for (int i = 1; i < n; i++) {
            v += d;
            result.push_back(v);
        }
        return result;
  	}

    //

    /*
     Geometric Progression (formula implementation)
     a - start value
     n - number of element
     r - ratio
    */
    long gprog(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        if (r == 0) {
            return 0;
        }
        return (a * (int) (std::pow(r, n - 1)));
        /*
        long v = a;
        for (int i = 1; i < n; i++) {
            v *= r;
        }
        return v;
        */
  	}

    /*
     Geometric Progression (loop implementation)
     a - start value
     n - number of element
     r - ratio
    */
    long gprog2(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        long v = a;
        for (int i = 1; i < n; i++) {
            v *= r;
        }
        return v;
  	}

    /*
     Geometric Progression Sum (formulas implementation)
     a - start value
     n - number of elements
     r - ratio
    */
    long gprogsum(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        if (r == 0) {
            return a; // TODO
            //return 0;
        }

        if (r == 1) {
            // we can't use formula because 1 - r = 0: division by zero
            // loop implementation
            return gprogsum2(a, n, r);
        }
        return a * (  (1 - (int) std::pow(r, n)) / (1 - r)  );
        /*
        long v = a;
        long sum = a;
        for (int i = 1; i < n; i++) {
            v *= r;
            sum += v;
        }
        return sum;
        */
  	}

    /*
     Geometric Progression Sum (loop implementation)
     a - start value
     n - number of elements
     r - ratio
    */
    long gprogsum2(int a, int n, int r) {
        if (n <= 1) {
            return a;
        }
        long v = a;
        long sum = a;
        for (int i = 1; i < n; i++) {
            v *= r;
            sum += v;
        }
        return sum;
  	}

    /*
     Geometric Progression Sequence
     a - start value
     n - number of elements
     r - ratio
    */
    std::vector<long> gprogseq(int a, int n, int r) {
        std::vector<long> result;
        if (n < 1) {
            return result;
        }
        long v = a;
        result.push_back(v);
        for (int i = 1; i < n; i++) {
            v *= r;
            result.push_back(v);
        }
        return result;
  	}

    ////

    /*
     Integer Sequence: 0 1 2 3 4 5 ...
    */
    std::vector<long> iseq(int n) {
        return aprogseq(0, n, 1);
  	}

    /*
     Integer Sequence Sum: 0 + 1 + 2 + 3 + 4 + 5 ...
    */
    long isum(int n) {
        if (n < 1) {
            return 0;
        }
        return aprogsum(0, n, 1);
  	}

    /*
     N Integer Sequence: 1 2 3 4 5 ...
    */
    std::vector<long> nseq(int n) {
        return aprogseq(1, n, 1);
  	}

    /*
     N Integer Sequence: 1 + 2 + 3 + 4 + 5 ...
    */
    long nsum(int n) {
        if (n < 1) {
            return 0;
        }
        return aprogsum(1, n, 1);
  	}

    //

    long sumnum(int value) {
        return nsum(value);
    }

    //// 2.3 factorial, fibonaccy

    // factorial
    
    // https://guava.dev/releases/14.0/api/docs/src-html/com/google/common/math/DoubleMath.html

    long fact(int value) {
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

    /*
     Return fibonacci value
     -----------------------------------
        n: 0 1 2 3 4 5 6  7  8  9 10 ...
     f(n): 0 1 1 2 3 5 8 13 21 34 55 ...
     -----------------------------------
     n - number in sequence (start with '0')
    */
    long fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long n1 = 0;
        long n2 = 1;
        long f = 0;

        //int k = 0;
        for (int i = 1; i <= n - 1; i++) {
            f = n1 + n2;

            // TODO
            /*
            if (f < 0) {

                // shift
                k = i;
                break;
            }
            */

            n1 = n2;
            n2 = f;
        }
        return f;
    }

    /*
     Return fibonacci suquence
     ------------------------------------
         n: 0 1 2 3 4 5 6  7  8  9 10 ...
      f(n): 0 1 1 2 3 5 8 13 21 34 55 ...
     fs(n):     1 2 3 5 8 13 21 34 55 ...
     ------------------------------------
     n - number in sequence (start with '0')
    */ 
    std::vector<long> fibseq(int n) {
        return fibseq(n, false);
    }

    /*
     Return simple fibonacci suquence
     ------------------------------------
         n: 0 1 2 3 4 5 6  7  8  9 10 ...
     fs(n):     1 2 3 5 8 13 21 34 55 ...
     ------------------------------------
     n - number in sequence (start with '0')
    */ 
    std::vector<long> fibseq2(int n) {
        return fibseq(n, true);
    }

    /*
     Return fibonacci suquence
     ------------------------------------
         n: 0 1 2 3 4 5 6  7  8  9 10 ...
      f(n): 0 1 1 2 3 5 8 13 21 34 55 ...
     fs(n):     1 2 3 5 8 13 21 34 55 ...
     ------------------------------------
     n - number in sequence (start with '0')
     simple - flag for simple sequence
     simple sequence: 1 2 5 8 ... (without first elements - 0 1)
    */ 
    std::vector<long> fibseq(int n, bool simple) {
        std::vector<long> result;
        
        if (n <= 0) {
            if (!simple) {
                result.push_back(0);
            }
            return result;
        }

        if (n == 1) {
            if (!simple) {
                result.push_back(0);
                result.push_back(1);
            }
            return result;
        }

        long n1 = 0;
        long n2 = 1;
        long f = 0;

        result.push_back(0);
        result.push_back(1);

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
            result.push_back(f);
        }
        return result;
    }

    //// 2.4 mod, isEven, isOdd

    int mod(int x, int y) {
        return x % y;
    }

    long mod(long x, long y) {
        return x % y;
    }
    
    //float mod(float x, float y) {
    //    return x % y;
    //}

    //double mod(double x, double y) {
    //    return x % y;
    //}    

    //// min/max
    
    // min

    int min(int x, int y) {
        return std::min(x, y);
    }

    long min(long x, long y) {
        return std::min(x, y);
    }

    float min(float x, float y) {
        return std::min(x, y);
    }

    double min(double x, double y) {
        return std::min(x, y);
    }

    // max

    int max(int x, int y) {
        return std::max(x, y);
    }

    long max(long x, long y) {
        return std::max(x, y);
    }

    float max(float x, float y) {
        return std::max(x, y);
    }

    double max(double x, double y) {
        return std::max(x, y);
    }

    //// isMod
    
    bool isMod(int x, int y) {
        return mod(x, y) != 0;
    }

    bool isMod(long x, long y) {
        return mod(x, y) != 0;
    }

    //// isDiv
    
    bool isDiv(int x, int y) {
        return mod(x, y) == 0;
    }

    bool isDiv(long x, long y) {
        return mod(x, y) == 0;
    }

    ////
    
    bool isEven(int x) {
        return (x % 2) == 0;
    }
    
    bool isEven(long x) {
        return (x % 2) == 0;
    }    
    
    bool isOdd(int x) {
        return !isEven(x);
    }

    bool isOdd(long x) {
        return !isEven(x);
    }

    ////

    bool isNaN(float x) {
        return std::isnan(x);
    }

    bool isNaN(double x) {
        return std::isnan(x);
    }

    //

    bool isInfinite(float x) {
        return std::isinf(x);
    }

    bool isInfinite(double x) {
        return std::isinf(x);
    }

    // 

    bool isPositiveInfinite(float x) {
        return x == INFINITY;
    }

    bool isPositiveInfinite(double x) {
        return x == INFINITY;
    }

    // 

    bool isNegativeInfinite(float x) {
        return x == -INFINITY;
    }

    bool isNegativeInfinite(double x) {
        return x == -INFINITY;
    }

    //// 2.5 log, log2, log10

    // log: log(x, base)
    // C++: Not implemented
    
    double log(double x, double base) {
        return std::log(x) / std::log(base);
    }

    // log: log(x, e) - natural logarith
    
    double log(double x) {
        return std::log(x);
    }
    
    // log2: log(x, 2)
        
    double log2(double x) {
        return std::log2(x);
    }

    // log10: log(x, 10)
        
    double log10(double x) {
        return std::log10(x);
    }

    // ln: log(x, e)

    double ln(double x) {
        return std::log(x);
    }

    // lg: log(x, 10)

    double lg(double x) {
        return std::log10(x);
    }

    //// 2.6 hypot, dot (2D, 3D)

    // hypot 2D

    double _hypot(double x, double y) {
        return std::hypot(x, y);
    }

    //

    int hypot(int x, int y) {
        return (int) _hypot(x, y);
    }    
    
    float hypot(float x, float y) {
        return (float) _hypot(x, y);
    }
    
    double hypot(double x, double y) {
        return _hypot(x, y);
    }
    
    // hypot 3D
    // C++: Not implemented

    double _hypot(double x, double y, double z) {
        return std::sqrt(dot(x, y, z));
    }

    //

    int hypot(int x, int y, int z) {
        return _hypot(x, y, z);
    }
    
    float hypot(float x, float y, float z) {
        return _hypot(x, y, z);
    }
    
    double hypot(double x, double y, double z) {
        return _hypot(x, y, z);
    }
    
    // dot 2D

    double _dot(double x, double y) {
        // x * x + y * y
        return std::pow(x, 2) + std::pow(y, 2);
    }

    int dot(int x, int y) {
        // x * x + y * y
        return (int) _dot(x, y);
    }

    float dot(float x, float y) {
        // x * x + y * y
        return (float) _dot(x, y);
    }

    double dot(double x, double y) {
        // x * x + y * y
        return _dot(x, y);
    }
    
    // dot 3D

    double _dot(double x, double y, double z) {
        // x * x + y * y + z * z
        return std::pow(x, 2) + std::pow(y, 2) + std::pow(z, 2);
    }

    int dot(int x, int y, int z) {
        // x * x + y * y + z * z
        return (int) _dot(x, y, z);
    }

    float dot(float x, float y, float z) {
        // x * x + y * y + z * z
        return (float) _dot(x, y, z);
    }

    double dot(double x, double y, double z) {
        // x * x + y * y + z * z
        return _dot(x, y, z);
    }

    //// 3.1 sin, cos, tan, asin, acos, atan, sinh, cosh, tanh

    //// RADIAN BY DEFAULT
    
    // sin, cos, tan
    
    double sin(double a) {
        return std::sin(a);
    }

    double cos(double a) {
        return std::cos(a);
    }

    double tan(double a) {
        return std::tan(a);
    }

    // csc, sec, cot
    // C++: Not implemented

    double csc(double a) {
        return cscRadian(a);
    }

    double sec(double a) {
        return secRadian(a);
    }

    double cot(double a) {
        return cotRadian(a);
    }

    // asin, acos, atan

    double asin(double a) {
        return std::asin(a);
    }

    double acos(double a) {
        return std::acos(a);
    }

    double atan(double a) {
        return std::atan(a);
    }

    // acsc, asec, acot
    // C++: Not implemented
    
    double acsc(double a) {
        return acscRadian(a);
    }

    double asec(double a) {
        return asecRadian(a);
    }

    double acot(double a) {
        return acotRadian(a);
    }

    // hyperbolic: sinh, cosh, tanh

    double sinh(double a) {
        return std::sinh(a);
    }

    double cosh(double a) {
        return std::cosh(a);
    }

    double tanh(double a) {
        return std::tanh(a);
    }

    // hyperbolic: csch, sech, coth
    // C++: Not implemented
    
    double csch(double a) {
        return cschRadian(a);
    }

    double sech(double a) {
        return sechRadian(a);
    }

    double coth(double a) {
        return cothRadian(a);
    }

    // hyperbolic: asinh, acosh, atanh
    
    double asinh(double a) {
        return std::asinh(a);
    }

    double acosh(double a) {
        return std::acosh(a);
    }

    double atanh(double a) {
        return std::atanh(a);
    }

    // hyperbolic: acsch, asech, acoth
    // C++: Not implemented

    // hyperbolic arc cosecant: ln(1 / a + √(1 / a^2 + 1))
    double acsch(double a) {
        return acschRadian(a);
    }

    // hyperbolic arc secant: ln(1 / a + √(1 / a^2 - 1))
    // range (0, 1]
    //
    // x = 1              => ??? Infinity
    // x <= 0             => NaN
    double asech(double a) {
        return asechRadian(a);
    }

    // hyperbolic arc cotangent: ln((a + 1) / (a - 1)) / 2
    // range (-Infinity, -1) and (1, +Infinity)
    //
    // x >= -1 and x <= 1 => NaN
    double acoth(double a) {
        return acothRadian(a);
    }

    //// 3.2 RADIAN: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    
    // radian: sin, cos, tan
    
    double sinRadian(double a) {
        return std::sin(a);
    }

    double cosRadian(double a) {
        return std::cos(a);
    }

    double tanRadian(double a) {
        return std::tan(a);
    }

    // radian: csc, sec, cot
    
    double cscRadian(double a) {
        return 1 / std::sin(a);
    }

    double secRadian(double a) {
        return 1 / std::cos(a);
    }

    double cotRadian(double a) {
        return 1 / std::tan(a);
    }

    // radian: asin, acos, atan
    
    double asinRadian(double a) {
        return std::asin(a);
    }

    double acosRadian(double a) {
        return std::acos(a);
    }

    double atanRadian(double a) {
        return std::atan(a);
    }

    // radian: acsc, asec, acot
    
    double acscRadian(double a) {
        return PI_2 - std::asin(a);
    }

    double asecRadian(double a) {
        return PI_2 - std::acos(a);
    }

    double acotRadian(double a) {
        return PI_2 - std::atan(a);
    }
    
    // hyperbolic/radian: sinh, cosh, tanh
    
    double sinhRadian(double a) {
        return std::sinh(a);
    }

    double coshRadian(double a) {
        return std::cosh(a);
    }

    double tanhRadian(double a) {
        return std::tanh(a);
    }

    // hyperbolic/radian: csch, sech, coth
    
    double cschRadian(double a) {
        return 1 / std::sinh(a);
    }

    double sechRadian(double a) {
        return 1 / std::cosh(a);
    }

    double cothRadian(double a) {
        return 1 / std::tanh(a);
    }

    // hyperbolic/radian: asinh, acosh, atanh
    
    double asinhRadian(double a) {
        return std::asinh(a);
    }

    double acoshRadian(double a) {
        return std::acosh(a);
    }

    double atanhRadian(double a) {
        return std::atanh(a);
    }

    // hyperbolic/radian: acsch, asech, acoth

    // hyperbolic arc cosecant: ln(1 / a + √(1 / a^2 + 1))
    double acschRadian(double a) {
        return std::log(1 / a +  std::sqrt((1 / (a * a)) + 1));
    }

    // hyperbolic arc secant: ln(1 / a + √(1 / a^2 - 1))
    // range (0, 1]
    //
    // x = 1              => ??? Infinity
    // x <= 0             => NaN
    double asechRadian(double a) {
        if (a <= 0 || a > 1) {
            return NAN;
        }
        return std::log(1 / a + std::sqrt((1 / (a * a)) - 1));
    }

    // hyperbolic arc cotangent: ln((a + 1) / (a - 1)) / 2
    // range (-Infinity, -1) and (1, +Infinity)
    //
    // x >= -1 and x <= 1 => NaN
    double acothRadian(double a) {
        if (a >= -1 && a <= 1) {
            return NAN;
        }
        return std::log((a + 1) / (a - 1)) / 2;
    }


    //// 3.3 DEGREE: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    
    // degree: sin, cos, tan
    
    double sinDegree(double a) {
        return std::sin(toRadians(a));
    }

    double cosDegree(double a) {
        return std::cos(toRadians(a));
    }

    double tanDegree(double a) {
        return std::tan(toRadians(a));
    }

    // degree: csc, sec, cot
    
    double cscDegree(double a) {
        return cscRadian(toRadians(a));
    }

    double secDegree(double a) {
        return secRadian(toRadians(a));
    }

    double cotDegree(double a) {
        return cotRadian(toRadians(a));
    }

    // degree: asin, acos, atan
    
    double asinDegree(double a) {
        return toDegrees(std::asin(a));
    }

    double acosDegree(double a) {
        return toDegrees(std::acos(a));
    }

    double atanDegree(double a) {
        return toDegrees(std::atan(a));
    }

    // degree: acsc, asec, acot
    
    double acscDegree(double a) {
        return toDegrees(acscRadian(a));
    }

    double asecDegree(double a) {
        return toDegrees(asecRadian(a));
    }

    double acotDegree(double a) {
        return toDegrees(acotRadian(a));
    }

    // hyperbolic/degree: sinh, cosh, tanh
    
    double sinhDegree(double a) {
        return std::sinh(toRadians(a));
    }

    double coshDegree(double a) {
        return std::cosh(toRadians(a));
    }

    double tanhDegree(double a) {
        return std::tanh(toRadians(a));
    }

    // hyperbolic/degree: csch, sech, coth
    
    double cschDegree(double a) {
        return cschRadian(toRadians(a));
    }

    double sechDegree(double a) {
        return sechRadian(toRadians(a));
    }

    double cothDegree(double a) {
        return cothRadian(toRadians(a));
    }

    // hyperbolic/degree: asinh, acosh, atanh
    
    double asinhDegree(double a) {
        return std::asinh(toRadians(a));
    }

    double acoshDegree(double a) {
        return std::acosh(toRadians(a));
    }

    double atanhDegree(double a) {
        return std::atanh(toRadians(a));
    }

    // hyperbolic/degree: acsch, asech, acoth

    // hyperbolic arc cosecant: ln(1 / a + √(1 / a^2 + 1))
    double acschDegree(double a) {
        return acschRadian(toRadians(a));
    }

    // hyperbolic arc secant: ln(1 / a + √(1 / a^2 - 1))
    // range (0, 1]
    //
    // x = 1              => ??? Infinity
    // x <= 0             => NaN
    double asechDegree(double a) {
        return asechRadian(toRadians(a));
    }

    // hyperbolic arc cotangent: ln((a + 1) / (a - 1)) / 2
    // range (-Infinity, -1) and (1, +Infinity)
    //
    // x >= -1 and x <= 1 => NaN
    double acothDegree(double a) {
        return acotRadian(toRadians(a));
    }

    //// 3.4 Util

    double toRadians(double angdeg) {
        return angdeg / 180.0 * PI;
    }

    double toDegrees(double angrad) {
        return angrad * 180.0 / PI;
    }


}



