package plazma.lib.analysis;

import plazma.lib.AbstractTestCase;
import plazma.lib.analysis.InterpolationFunction;
import plazma.lib.analysis.InterpolationLib;
import plazma.lib.array.ArrayLib;

public class InterpolationLibTest extends AbstractTestCase {

    public void testInterpolation() {

        // sqr function: x ^ 2
        double[] x = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10 };
        double[] y = new double[] { 1.0, 4.0, 9.0, 16.0, 25.0, 36.0, 49.0, 64.0, 81.0, 100 };

        printHeader("Spline Interpolator");

        int COUNT = 1000000;
        long time = 0;
        long totalTime = 0;
        InterpolationFunction func = null;

        /*
         * for (int i = 0; i < COUNT; i++) { time = System.currentTimeMillis(); func =
         * interpolate(x, y); time = System.currentTimeMillis() - time; totalTime +=
         * time;
         * 
         * } //System.out.println("Avg time: " + (totalTime/COUNT));
         * System.out.println("Avg time: " + (totalTime));
         */

        func = InterpolationLib.interpolateFunction(x, y);

        // InterpolationFunction func = interpolate(x, y);
        println("3^2 = " + func.evaluate(3.0));
        println("5^2 = " + func.evaluate(5.0));

        println("2.5^2 = " + func.evaluate(2.5));
        println("8.5^2 = " + func.evaluate(8.5));

        println("11^2 = " + func.evaluate(11.0));
        println("123.456^2 = " + func.evaluate(123.456));

        println();
        printHeader("Akima Interpolator");

        time = 0;
        totalTime = 0;
        InterpolationFunction func2 = null;

        /*
         * for (int i = 0; i < COUNT; i++) { time = System.currentTimeMillis(); func2 =
         * interpolate2(x, y); time = System.currentTimeMillis() - time; totalTime +=
         * time;
         * 
         * } System.out.println("Avg time: " + (totalTime));
         */

        func2 = InterpolationLib.interpolateFunction("akima", x, y);

        // InterpolationFunction func2 = interpolate2(x, y);
        println("3^2 = " + func2.evaluate(3.0));
        println("5^2 = " + func2.evaluate(5.0));

        println("2.5^2 = " + func2.evaluate(2.5));
        println("8.5^2 = " + func2.evaluate(8.5));

        println("11^2 = " + func2.evaluate(11.0));
        println("123.456^2 = " + func2.evaluate(123.456));

        // Calculate min/max values: range
        double min = ArrayLib.min(x);
        double max = ArrayLib.max(x);

        println();

        // Generate new x values: min, max range with 20 points
        double[] xNew = ArrayLib.pointDoubleArray(min, max, 20);
        printArray("xNew.............:", xNew);

        // Interpolate new x values: func
        double[] yNew1 = InterpolationLib.interpolate(func, xNew);
        printArray("yNew1   (linear).:", yNew1);

        // Interpolate new x values: func2
        double[] yNew2 = InterpolationLib.interpolate(func2, xNew);
        printArray("yNew2   (akima)..:", yNew2);

        double[] yNew2_1 = InterpolationLib.interpolate("akima", x, y, 20);
        printArray("yNew2_1 (akima)..:", yNew2_1);

        /// Evaluate real function: x ^ 2
        double[] fNew = ArrayLib.evaluateDoubleArray(xNew, (a) -> (a * a));
        printArray("fNew    (real)...:", fNew);

        ////

        // double[] yNew2_1 = InterpolationLib.interpolate("akima", x, y, 20);

        assertTrue(ArrayLib.equals(yNew2, yNew2_1));

    }
}
