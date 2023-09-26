package plazma.lib.analysis;

import java.util.Arrays;

public class PolynomialSplineFunction extends AbstractFunction implements InterpolationFunction {

    private final double[] knots;

    private final PolynomialFunction[] polynomials;

    private final int n;

    public PolynomialSplineFunction(double[] knots, PolynomialFunction[] polynomials) {
        checkNotNull("knots", knots);
        checkNotNull("polynomials", polynomials);

        checkArrayLengthMin(knots, 2);
        checkArrayLength(knots.length - 1, polynomials.length);

        checkOrder(knots);

        this.n = knots.length - 1;
        this.knots = new double[n + 1];
        System.arraycopy(knots, 0, this.knots, 0, n + 1);
        this.polynomials = new PolynomialFunction[n];
        System.arraycopy(polynomials, 0, this.polynomials, 0, n);
    }

    public double evaluate(double v) {

        // oha-STUB
        /*
         * if (v < knots[0] || v > knots[n]) { throw new
         * RuntimeException("NumberIsTooSmallException"); //throw new
         * OutOfRangeException(v, knots[0], knots[n]); }
         */

        int i = Arrays.binarySearch(knots, v);
        if (i < 0) {
            i = -i - 2;
        }
        // This will handle the case where v is the last knot value
        // There are only n-1 polynomials, so if v is the last knot
        // then we will use the last polynomial to calculate the value.
        if (i >= polynomials.length) {
            i--;
        }
        return polynomials[i].evaluate(v - knots[i]);
    }
}
