package plazma.lib.analysis;

public class LinearInterpolator extends AbstractInterpolator implements Interpolator {

    @Override
    public InterpolationFunction interpolate(double[] x, double[] y) {

        checkArrayLength(x, y);
        checkArrayLengthMin(x, 2);

        checkOrder(x);

        // Number of intervals. The number of data points is n + 1.
        int n = x.length - 1;

        // Slope of the lines between the datapoints.
        final double[] m = new double[n];
        for (int i = 0; i < n; i++) {
            m[i] = (y[i + 1] - y[i]) / (x[i + 1] - x[i]);
        }

        final PolynomialFunction polynomials[] = new PolynomialFunction[n];
        final double[] coefficients = new double[2];
        for (int i = 0; i < n; i++) {
            coefficients[0] = y[i];
            coefficients[1] = m[i];
            polynomials[i] = new PolynomialFunction(coefficients);
        }

        return new PolynomialSplineFunction(x, polynomials);
    }

}
