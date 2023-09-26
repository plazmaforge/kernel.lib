#ifndef PLAZMA_KERNEL_LIB_MATH_MATHLIB_H
#define PLAZMA_KERNEL_LIB_MATH_MATHLIB_H

#include <vector>

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

namespace mathlib {

    //// 1.1

    //// Overflow: Operator: '+': ((x ^ r) & (y ^ r)) < 0

    // int

    bool isOverflowAdd(int x, int y, int r);

    // long

    bool isOverflowAdd(long x, long y, long r);

    //// Overflow: Operator: '-': ((x ^ y) & (x ^ r)) < 0

    // int

    bool isOverflowSub(int x, int y, int r);

    // long

    bool isOverflowSub(long x, long y, long r);

    //// add: '+'

    int add(int x, int y /*, ArithmeticContext context*/);

    int add(long x, long y /*, ArithmeticContext context*/);

    //// sub: '-'

    int sub(int x, int y /*, ArithmeticContext context*/);

    int sub(long x, long y /*, ArithmeticContext context*/);

    //// mul: '*'

    int mul(int x, int y /*, ArithmeticContext context*/);

    long mul(long x, long y /*, ArithmeticContext context*/);

    //// divInt: '/'

    int divInt(int x, int y);

    long divInt(long x, long y);

    //// divFloat: '/'

    float divFloat(int x, int y);

    double divFloat(long x, long y);

    //// div: '/' - divInt by default

    int div(int x, int y);

    long div(long x, long y);


    //// 1.2 trunc, round, ceil, floor, rint, sign, abs

    // trunc, truncInt, truntDec

    // cast: lost data
    int intValue(float value);

    // cast: lost data
    long longValue(double value);

    ////

    // 23.45 => 23.00
    float trunc(float x);

    // 23.45 => 23.00
    double trunc(double x);

    ////

    // 23.45 => 23
    int truncInt(float x);

    // 23.45 => 23
    long truncInt(double x);

    ////

    // 23.45 => 0.45
    float truncDec(float x);

    // 23.45 => 0.45
    double truncDec(double x);

    //
    
    // trunc(23.45, 1) => 23.4
    // trunc(23.45, 2) => 23.45
    float trunc(float value, int pos);

    // trunc(23.45, 1) => 23.4
    // trunc(23.45, 2) => 23.45
    double trunc(double value, int pos);

    // round

    float round(float x);

    double round(double x);

    // 

    int roundInt(float x);

    long roundInt(double x);

    // round(23.45, 1) => 23.50
    // round(23.45, 2) => 23.45
    float round(float x, int pos);
    
    // round(23.45, 1) => 23.50
    // round(23.45, 2) => 23.45
    double round(double x, int pos);

    // ceil

    float ceil(float x);

    double ceil(double x);

    // floor

    float floor(float x);

    double floor(double x);

    // rint

    float rint(float x);

    double rint(double x);

    // sign

    int sign(int x);

    long sign(long x);

    float sign(float x);

    double sign(double x);

    // abs

    int abs(int x);

    long abs(long x);

    float abs(float x);

    double abs(double x);


    //// 2.1 pow, sqr, cbr, root, sqrt, cbrt, exp

    float getDec(int k);

    //// pow

    // x ^ y
    double pow(double x, double y);

    // x ^ 2
    double pow2(double x);

    // x ^ 3
    double pow3(double x);

    // 2 ^ x
    double pow2x(double x);

    // 10 ^ x
    double pow10x(double x);

    // 10 ^ x
    float pow10x(int x);

    // e ^ x
    double powEx(double x);

    // x ^ 2
    double sqr(double x);

    // x ^ 3
    double cbr(double x);

    //// root
    
    // x ^ (1/n) = e ^ (ln(x)/n)
    // https://www.codeflow.site/ru/article/java-nth-root
    double root(double x, double n);

    // x ^ (1/2)
    double root2(double x);
    
    // x ^ (1/3)
    double root3(double x);
         
    // x ^ (1/2)
    double sqrt(double x);

    // x ^ (1/3)
    double cbrt(double x);

    // e ^ x
    double exp(double x);

    //// 2.2 arithmetic/geometric progression/sum/sequence, integer sequence

    /*
     Arithmetic Progression (formula implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprog(int a, int n, int d);

    /*
     Arithmetic Progression (loop implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprog2(int a, int n, int d);

    /*
     Arithmetic Progression Sum (formula implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprogsum(int a, int n, int d);

    /*
     Arithmetic Progression Sum (loop implementation)
     a - start value
     n - number of elements
     d - delta
    */
    long aprogsum2(int a, int n, int d);

    /*
     Arithmetic Progression Sequence
     a - start value
     n - number of elements
     d - delta
    */
    std::vector<long> aprogseq(int a, int n, int d);

    //

    /*
     Geometric Progression (formula implementation)
     a - start value
     n - number of element
     r - ratio
    */
    long gprog(int a, int n, int r);

    /*
     Geometric Progression (loop implementation)
     a - start value
     n - number of element
     r - ratio
    */
    long gprog2(int a, int n, int r);

    /*
     Geometric Progression Sum (formula implementation)
     a - start value
     n - number of elements
     r - ratio
    */
    long gprogsum(int a, int n, int r);

    /*
     Geometric Progression Sum (loop implementation)
     a - start value
     n - number of elements
     r - ratio
    */
    long gprogsum2(int a, int n, int r);

    /*
     Geometric Progression Sequence
     a - start value
     n - number of elements
     r - ratio
    */
    std::vector<long> gprogseq(int a, int n, int r);

    ////

    /*
     Integer Sequence: 0 1 2 3 4 5 ...
    */
    std::vector<long> iseq(int n);

    /*
     Integer Sequence Sum: 0 + 1 + 2 + 3 + 4 + 5 ...
    */
    long isum(int n);

    /*
     N Integer Sequence: 1 2 3 4 5 ...
    */
    std::vector<long> nseq(int n);

    /*
     N Integer Sequence: 1 + 2 + 3 + 4 + 5 ...
    */
    long nsum(int n);

    //

    long sumnum(int value);


    //// 2.3 factorial, fibonaccy

    // fact (?)

    long fact(int value);

    /*
     Return fibonacci suquence
     ------------------------------------
         n: 0 1 2 3 4 5 6  7  8  9 10 ...
      f(n): 0 1 1 2 3 5 8 13 21 34 55 ...
     fs(n):     1 2 3 5 8 13 21 34 55 ...
     ------------------------------------
     n - number in sequence (start with '0')
    */ 
    std::vector<long> fibseq(int n);

    /*
     Return simple fibonacci suquence
     ------------------------------------
         n: 0 1 2 3 4 5 6  7  8  9 10 ...
     fs(n):     1 2 3 5 8 13 21 34 55 ...
     ------------------------------------
     n - number in sequence (start with '0')
    */ 
    std::vector<long> fibseq2(int n);

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
    std::vector<long> fibseq(int n, bool simple);


    //// 2.4 mod, isEven, isOdd

    int mod(int x, int y);

    long mod(long x, long y);

    //// min/max
    
    // min

    int min(int x, int y);

    long min(long x, long y);

    float min(float x, float y);

    double min(double x, double y);

    // max

    int max(int x, int y);

    long max(long x, long y);

    float max(float x, float y);

    double max(double x, double y);

    //// isMod
    
    bool isMod(int x, int y);

    bool isMod(long x, long y);

    //// isDiv
    
    bool isDiv(int x, int y);

    bool isDiv(long x, long y);

    ////
    
    bool isEven(int x);
    
    bool isEven(long x);
    
    bool isOdd(int x);

    bool isOdd(long x);

    ////

    bool isNaN(float x);

    bool isNaN(double x);

    //

    bool isInfinite(float x);

    bool isInfinite(double x);

    // 

    bool isPositiveInfinite(float x);

    bool isPositiveInfinite(double x);

    // 

    bool isNegativeInfinite(float x);

    bool isNegativeInfinite(double x);

    //// 2.5 log, log2, log10

    // log: log(x, base)

    double log(double x, double base);
    
    // log: log(x, e)
    
    double log(double x);

    // log2: log(x, 2)
        
    double log2(double x);

    // log10: log(x, 10)
        
    double log10(double x);

    // ln: log(x, e)

    double ln(double x);

    // lg: log(x, 10)

    double lg(double x);

    //// 2.6 hypot, dot (2D, 3D)

    // hypot 2D
    
    int hypot(int x, int y);
    
    float hypot(float x, float y);
    
    double hypot(double x, double y);
    
    // hypot 3D
    // C++: Not implemented

    int hypot(int x, int y, int z);
    
    float hypot(float x, float y, float z);
    
    double hypot(double x, double y, double z);
    
    // dot 2D

    int dot(int x, int y);

    float dot(float x, float y);

    double dot(double x, double y);
    
    // dot 3D

    int dot(int x, int y, int z);

    float dot(float x, float y, float z);

    double dot(double x, double y, double z);

    //// 3.1 sin, cos, tan, asin, acos, atan, sinh, cosh, tanh

    //// RADIAN BY DEFAULT
    
    // sin, cos, tan
    
    double sin(double a);

    double cos(double a);

    double tan(double a);

    // csc, sec, cot
    
    double csc(double a);

    double sec(double a);
    
    double cot(double a);

    // asin, acos, atan

    double asin(double a);

    double acos(double a);

    double atan(double a);

    // acsc, asec, acot
    
    double acsc(double a);

    double asec(double a);

    double acot(double a);

    // hyperbolic: sinh, cosh, tanh

    double sinh(double a);

    double cosh(double a);

    double tanh(double a);

    // hyperbolic: csch, sech, coth
    
    double csch(double a);

    double sech(double a);

    double coth(double a);

    // hyperbolic: asinh, acosh, atanh
    
    double asinh(double a);

    double acosh(double a);

    double atanh(double a);

    // hyperbolic: acsch, asech, acoth

    // hyperbolic arc cosecant: ln(1 / a + √(1 / a^2 + 1))
    double acsch(double a);

    // hyperbolic arc secant: ln(1 / a + √(1 / a^2 - 1))
    // range (0, 1]
    //
    // x = 1              => ??? Infinity
    // x <= 0             => NaN
    double asech(double a);

    // hyperbolic arc cotangent: ln((a + 1) / (a - 1)) / 2
    // range (-Infinity, -1) and (1, +Infinity)
    //
    // x >= -1 and x <= 1 => NaN
    double acoth(double a);

    //// 3.2 RADIAN: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    
    // radian: sin, cos, tan
    
    double sinRadian(double a);

    double cosRadian(double a);

    double tanRadian(double a);

    // radian: csc, sec, cot
    
    double cscRadian(double a);

    double secRadian(double a);

    double cotRadian(double a);

    // radian: asin, acos, atan
    
    double asinRadian(double a);

    double acosRadian(double a);

    double atanRadian(double a);

    // radian: acsc, asec, acot
    
    double acscRadian(double a);

    double asecRadian(double a);

    double acotRadian(double a);

    // hyperbolic/radian: sinh, cosh, tanh
    
    double sinhRadian(double a);

    double coshRadian(double a);

    double tanhRadian(double a);

    // hyperbolic/radian: csch, sech, coth
    
    double cschRadian(double a);

    double sechRadian(double a);

    double cothRadian(double a);

    // hyperbolic/radian: asinh, acosh, atanh
    
    double asinhRadian(double a);

    double acoshRadian(double a);

    double atanhRadian(double a);

    // hyperbolic/radian: acsch, asech, acoth

    // hyperbolic arc cosecant: ln(1 / a + √(1 / a^2 + 1))
    double acschRadian(double a);

    // hyperbolic arc secant: ln(1 / a + √(1 / a^2 - 1))
    // range (0, 1]
    //
    // x = 1              => ??? Infinity
    // x <= 0             => NaN
    double asechRadian(double a);

    // hyperbolic arc cotangent: ln((a + 1) / (a - 1)) / 2
    // range (-Infinity, -1) and (1, +Infinity)
    //
    // x >= -1 and x <= 1 => NaN
    double acothRadian(double a);

    //// 3.3 DEGREE: sin, cos, tan, asin, acos, atan, sinh, cosh, tanh
    
    // degree: sin, cos, tan
    
    double sinDegree(double a) ;

    double cosDegree(double a);

    double tanDegree(double a);

    // degree: csc, sec, cot
    
    double cscDegree(double a);

    double secDegree(double a);

    double cotDegree(double a);

    // degree: asin, acos, atan
    
    double asinDegree(double a);

    double acosDegree(double a);

    double atanDegree(double a);

    // degree: acsc, asec, acot
    
    double acscDegree(double a);

    double asecDegree(double a);

    double acotDegree(double a);

    // hyperbolic/degree: sinh, cosh, tanh
    
    double sinhDegree(double a);

    double coshDegree(double a);

    double tanhDegree(double a);

    // hyperbolic/degree: csch, sech, coth
    
    double cschDegree(double a);

    double sechDegree(double a);

    double cothDegree(double a);

    // hyperbolic/degree: asinh, acosh, atanh
    
    double asinhDegree(double a);

    double acoshDegree(double a);

    double atanhDegree(double a);

    // hyperbolic/degree: acsch, asech, acoth

    // hyperbolic arc cosecant: ln(1 / a + √(1 / a^2 + 1))
    double acschDegree(double a);

    // hyperbolic arc secant: ln(1 / a + √(1 / a^2 - 1))
    // range (0, 1]
    //
    // x = 1              => ??? Infinity
    // x <= 0             => NaN
    double asechDegree(double a);

    // hyperbolic arc cotangent: ln((a + 1) / (a - 1)) / 2
    // range (-Infinity, -1) and (1, +Infinity)
    //
    // x >= -1 and x <= 1 => NaN
    double acothDegree(double a);

    //// 3.4 Util

    double toRadians(double angdeg);

    double toDegrees(double angrad);

}

#endif // PLAZMA_KERNEL_LIB_MATH_MATHLIB_H