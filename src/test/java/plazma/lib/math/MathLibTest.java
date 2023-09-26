package plazma.lib.math;

import java.math.BigDecimal;
import java.util.Arrays;

import plazma.lib.AbstractTestCase;
import plazma.lib.ArithmeticContext;
import plazma.lib.NullMode;
import plazma.lib.OverflowMode;
import plazma.lib.math.MathLib;

public class MathLibTest extends AbstractTestCase {

    private static double PI = MathLib.PI;

    private static double E = MathLib.E;

    public void test() {
        double pi1 = MathLib.PI;
        double pi2 = MathLib.M_PI;

        System.out.println("PI-1: " + pi1);
        System.out.println("PI-2: " + pi2);
        System.out.println("PI-D: " + (pi2 - pi1));
        System.out.println("PI >: " + (pi2 > pi1));
        System.out.println("PI =: " + (pi2 == pi1));

        float f1 = -0.0f;
        System.out.println(">0  :" + (f1 > 0));
        System.out.println("=0  :" + (f1 == 0));
        System.out.println("<0  :" + (f1 < 0));

        System.out.println("sign (-0.0):" + (Math.signum(-0.0f)));
        System.out.println("sign ( 0.0):" + (Math.signum(0.0f)));
        System.out.println("sign (+0.0):" + (Math.signum(+0.0f)));

        ArithmeticContext context = new ArithmeticContext(null, OverflowMode.EXCEPTION);

        byte a = 100;
        byte b = 27;

        byte r = MathLib.add(a, b, context);

        println(r);

        // println(div(10, 0, null));

        println(MathLib.add(BigDecimal.valueOf(100), null, new ArithmeticContext(NullMode.EMPTY, null)));
    }

    public void testPow() {

        double valuePow = 0;
        double valueFun = 0;
        double valueMul = 0;

        // sqr
        // 2 x 2
        valuePow = MathLib.pow(2.0, 2);
        valueFun = MathLib.sqr(2.0);
        valueMul = 2.0 * 2.0;

        assertEquals(valuePow, valueFun);
        assertEquals(valuePow, valueMul);

        printHeader("Math: pow, sqr");
        println("pow(2, 2)    = " + valuePow);
        println("sqr(2)       = " + valueFun);
        println("2 * 2        = " + valueMul);

        // PI x PI
        valuePow = MathLib.pow(PI, 2);
        valueFun = MathLib.sqr(PI);
        valueMul = PI * PI;

        assertEquals(valuePow, valueFun);
        assertEquals(valuePow, valueMul);

        printHeader("Math: pow, sqr");
        println("pow(PI, 2)   = " + valuePow);
        println("sqr(PI)      = " + valueFun);
        println("PI * PI      = " + valueMul);

        // cbr
        // 2 x 2 x 2
        valuePow = MathLib.pow(2.0, 3);
        valueFun = MathLib.cbr(2.0);
        valueMul = 2.0 * 2.0 * 2.0;

        assertEquals(valuePow, valueFun);
        assertEquals(valuePow, valueMul);

        printHeader("Math: pow, cbr");
        println("pow(2, 3)    = " + valuePow);
        println("cbr(2)       = " + valueFun);
        println("2 * 2 * 2    = " + valueMul);

        // PI x PI x PI
        valuePow = MathLib.pow(PI, 3);
        valueFun = MathLib.cbr(PI);
        valueMul = PI * PI * PI;

        assertEquals(valuePow, valueFun);
        assertEquals(valuePow, valueMul);

        printHeader("Math: pow, sqr");
        println("pow(PI, 3)   = " + valuePow);
        println("cbr(PI)      = " + valueFun);
        println("PI * PI * PI = " + valueMul);

        // exp
        // E x E
        valuePow = MathLib.pow(E, 2.0);
        valueFun = MathLib.exp(2.0);
        valueMul = E * E;

        assertEquals(valuePow, valueFun, 0.0000000000000100); // UPS: 7.3890560989306495 vs 7.38905609893065
        assertEquals(valuePow, valueMul);

        printHeader("Math: pow, exp");
        println("pow(E, 2)    = " + valuePow);
        println("exp(2)       = " + valueFun);
        println("E * E        = " + valueMul);

        printHeader("Math: pow2, pow3, pow10x");
        println("pow2(2)      = " + MathLib.pow2(2));
        println("pow2(3)      = " + MathLib.pow2(3));
        println("pow2(-3)     = " + MathLib.pow2(-3));
        println();
        println("pow3(2)      = " + MathLib.pow3(2));
        println("pow3(3)      = " + MathLib.pow3(3));
        println("pow3(-3)     = " + MathLib.pow3(-3));

        printHeader("Math: pow2x, pow10x, powEx");
        println("pow2x(2)     = " + MathLib.pow2x(2));
        println("pow2x(3)     = " + MathLib.pow2x(3));
        println("pow2x(-3)    = " + MathLib.pow2x(-3));
        println();
        println("pow10x(2)    = " + MathLib.pow10x(2));
        println("pow10x(3)    = " + MathLib.pow10x(3));
        println("pow10x(-3)   = " + MathLib.pow10x(-3));
        println();
        println("powEx(2)    = " + MathLib.powEx(2));
        println("powEx(3)    = " + MathLib.powEx(3));
        println("powEx(-3)   = " + MathLib.powEx(-3));

    }

    public void testSumnum() {

        long value = 0;

        value = MathLib.sumnum(0);
        assertEquals(value, 0);

        value = MathLib.sumnum(-10);
        assertEquals(value, 0);

        value = MathLib.sumnum(1);
        assertEquals(value, 1);

        value = MathLib.sumnum(2);
        assertEquals(value, 3);

        value = MathLib.sumnum(3);
        assertEquals(value, 6);

    }

    public void testRound() {
        // float x = MathLib.round(10.123f, 2);

        printHeader("Math: round");
        // println("pow(E, 2) = " + valuePow);

        // println("round(12345.12345f, -5) = " + MathLib.round(12345.12345d, -5));
        println("round(12345.12345f, -4) = " + MathLib.round(12345.12345d, -4));
        println("round(12345.12345f, -3) = " + MathLib.round(12345.12345d, -3));
        println("round(12345.12345f, -2) = " + MathLib.round(12345.12345d, -2));
        println("round(12345.12345f, -1) = " + MathLib.round(12345.12345d, -1));

        println("round(12345.12345f, 0)  = " + MathLib.round(12345.12345d, 0));

        println("round(12345.12345f, 1)  = " + MathLib.round(12345.12345d, 1));
        println("round(12345.12345f, 2)  = " + MathLib.round(12345.12345d, 2));
        println("round(12345.12345f, 3)  = " + MathLib.round(12345.12345d, 3)); // ??
        println("round(12345.12345f, 4)  = " + MathLib.round(12345.12345d, 4)); // ??
        // println("round(12345.12345f, 5) = " + MathLib.round(12345.12345d, 5));

        println();

        double x = 12345.12345;
        println("x = " + x);

        x = x * 10000;

        println("x * 10^4 = " + x);

        x = Math.round(x);

        println("round(x) = " + x);

        x = x / 10000;

        println("round(x) / 10^4 = " + x);

        println();

    }

    public void testAprog() {

        int a = 1;
        int n = 10;
        int d = 1;

        // aN = a0 + (n - 1) * d

        printHeader("Math: aprog");

        println("aprog(1, 10, 1)       = " + MathLib.aprog(a, n, d));
        println("aprog2(1, 10, 1)      = " + MathLib.aprog2(a, n, d));
        println("aprogseq(1, 10, 1)    = " + Arrays.toString(MathLib.aprogseq(a, n, d)));
        println("aprogsum(1, 10, 1)    = " + MathLib.aprogsum(a, n, d));
        println("aprogsum2(1, 10, 1)   = " + MathLib.aprogsum2(a, n, d));

        a = -10;

        println();
        println("aprog(-10, 10, 1)     = " + MathLib.aprog(a, n, d));
        println("aprog2(-10, 10, 1)    = " + MathLib.aprog2(a, n, d));
        println("aprogseq(-10, 10, 1)  = " + Arrays.toString(MathLib.aprogseq(a, n, d)));
        println("aprogsum(-10, 10, 1)  = " + MathLib.aprogsum(a, n, d));
        println("aprogsum2(-10, 10, 1) = " + MathLib.aprogsum2(a, n, d));

        a = 1;
        n = 100;
        d = 1;

        println();
        println("aprog(1, 100, 1)       = " + MathLib.aprog(a, n, d));
        println("aprog2(1, 100, 1)      = " + MathLib.aprog2(a, n, d));
        println("aprogseq(1, 100, 1)    = " + Arrays.toString(MathLib.aprogseq(a, n, d)));
        println("aprogsum(1, 100, 1)    = " + MathLib.aprogsum(a, n, d));
        println("aprogsum2(1, 100, 1)   = " + MathLib.aprogsum2(a, n, d));

        a = 1;
        n = 10;
        d = 2;

        println();
        println("aprog(1, 10, 2)       = " + MathLib.aprog(a, n, d));
        println("aprog2(1, 10, 2)      = " + MathLib.aprog2(a, n, d));
        println("aprogseq(1, 10, 2)    = " + Arrays.toString(MathLib.aprogseq(a, n, d)));
        println("aprogsum(1, 10, 2)    = " + MathLib.aprogsum(a, n, d));
        println("aprogsum2(1, 10, 2)   = " + MathLib.aprogsum2(a, n, d));

    }

    public void testGprog() {

        int a = 1;
        int n = 10;
        int d = 2;

        // aN = a0 * (r ^ n - 1)

        printHeader("Math: gprog");

        println("gprog(1, 10, 2)       = " + MathLib.gprog(a, n, d));
        println("gprog2(1, 10, 2)      = " + MathLib.gprog2(a, n, d));
        println("gprogseq(1, 10, 2)    = " + Arrays.toString(MathLib.gprogseq(a, n, d)));
        println("gprogsum(1, 10, 2)    = " + MathLib.gprogsum(a, n, d));
        println("gprogsum2(1, 10, 2)   = " + MathLib.gprogsum2(a, n, d));

        a = -10;

        println();
        println("gprog(-10, 10, 2)     = " + MathLib.gprog(a, n, d));
        println("gprog2(-10, 10, 2)    = " + MathLib.gprog2(a, n, d));
        println("gprogseq(-10, 10, 2)  = " + Arrays.toString(MathLib.gprogseq(a, n, d)));
        println("gprogsum(-10, 10, 2)  = " + MathLib.gprogsum(a, n, d));
        println("gprogsum2(-10, 10, 2) = " + MathLib.gprogsum2(a, n, d));

        a = 1;
        n = 100;
        d = 2;

        println();
        println("gprog(1, 100, 2)       = " + MathLib.gprog(a, n, d));
        println("gprog2(1, 100, 2)      = " + MathLib.gprog2(a, n, d));
        println("gprogseq(1, 100, 2)    = " + Arrays.toString(MathLib.gprogseq(a, n, d)));
        println("gprogsum(1, 100, 2)    = " + MathLib.gprogsum(a, n, d));
        println("gprogsum2(1, 100, 2)   = " + MathLib.gprogsum2(a, n, d));

        a = 1;
        n = 10;
        d = 2;

        println();
        println("gprog(1, 10, 2)       = " + MathLib.gprog(a, n, d));
        println("gprog2(1, 10, 2)      = " + MathLib.gprog2(a, n, d));
        println("gprogseq(1, 10, 2)    = " + Arrays.toString(MathLib.gprogseq(a, n, d)));
        println("gprogsum(1, 10, 2)    = " + MathLib.gprogsum(a, n, d));
        println("gprogsum2(1, 10, 2)   = " + MathLib.gprogsum2(a, n, d));

    }

    public void testLog() {

        printHeader("Math: log");

        println("log(3.5)            = " + MathLib.log(3.5)); // 1.252762968495368
        println("exp(log(2.4))       = " + MathLib.exp(MathLib.log(2.4))); // 2.4

        println("pow(10, 4)          = " + MathLib.pow(10, 4)); // 10000.0
        println("log(10000, 10)      = " + MathLib.log(10000, 10)); // 4.0
        println("log10(10000)        = " + MathLib.log10(10000)); // 4.0
        println("log(10000)/log(10)  = " + (MathLib.log(10000) / MathLib.log(10))); // 4.0

        println("log(1024, 2)        = " + MathLib.log(1024, 2)); // 10.0
        println("log2(1024)          = " + MathLib.log2(1024)); // 10.0
        println("pow(2, 10)          = " + MathLib.pow(2, 10)); // 1024.0

    }

    /*
     * public void testDot() { //int N = 1_000_000; int N = 100_000; int k = 0;
     * 
     * double[] x = new double[N]; double[] y = new double[N]; double[] z = new
     * double[N];
     * 
     * double[] f = new double[N];
     * 
     * for (int i = 0; i < N; i++) { k = i + 1; x[i] = k * 11; y[i] = k * 123; z[i]
     * = i * 1234; }
     * 
     * //int n = 1000; int n = 1; long time = 0; for (int i = 0; i < n;i++) { time
     * += testDot1(x, y, z, f); } println("Test  DOT-1: time=" + (time)); println();
     * 
     * time = 0; for (int i = 0; i < n;i++) { time += testDot2(x, y, z, f); }
     * println("Test  DOT-2: time=" + (time));
     * 
     * }
     * 
     * private long testDot1(double[] x, double[] y, double[] z, double[] f) { int N
     * = x.length; long time = System.currentTimeMillis();
     * 
     * for (int i = 0; i < N; i++) { f[i] = dot1(x[i], y[i], z[i]); }
     * 
     * time = System.currentTimeMillis() - time; //println("Test  DOT-1: time=" +
     * time); return time; }
     * 
     * private long testDot2(double[] x, double[] y, double[] z, double[] f) { int N
     * = x.length; long time = System.currentTimeMillis();
     * 
     * for (int i = 0; i < N; i++) { f[i] = dot2(x[i], y[i], z[i]); }
     * 
     * time = System.currentTimeMillis() - time; //println("Test  DOT-2: time=" +
     * time); return time; }
     * 
     * private static double dot1(double x, double y, double z) { // x * x + y * y +
     * z * z return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2); }
     * 
     * private static double dot2(double x, double y, double z) { // x * x + y * y +
     * z * z return x * x + y * y + z * z; }
     */

}
