package plazma.kernel.lib.analysis;

public class AkimaSplineInterpolator extends AbstractInterpolator implements Interpolator {

    private static final int MINIMUM_NUMBER_POINTS = 5;

    public InterpolationFunction interpolate(double[] xvals, double[] yvals) {

        chekNotNull("x", xvals);
        chekNotNull("y", yvals);

        checkArrayLength(xvals, yvals);
        checkArrayLengthMin(xvals.length, MINIMUM_NUMBER_POINTS);

        checkOrder(xvals);

        final int numberOfElements = xvals.length - 1;

        final double[] differences = new double[numberOfElements];
        final double[] weights = new double[numberOfElements];

        for (int i = 0; i < differences.length; i++) {
            differences[i] = (yvals[i + 1] - yvals[i]) / (xvals[i + 1] - xvals[i]);
        }

        for (int i = 1; i < weights.length; i++) {
            // weights[i] = FastMath.abs(differences[i] - differences[i - 1]); // oha
            weights[i] = Math.abs(differences[i] - differences[i - 1]);
        }

        // Prepare Hermite interpolation scheme.
        final double[] firstDerivatives = new double[xvals.length];

        for (int i = 2; i < firstDerivatives.length - 2; i++) {
            final double wP = weights[i + 1];
            final double wM = weights[i - 1];
            if (precisionEquals(wP, 0.0) && precisionEquals(wM, 0.0)) {
                final double xv = xvals[i];
                final double xvP = xvals[i + 1];
                final double xvM = xvals[i - 1];
                firstDerivatives[i] = (((xvP - xv) * differences[i - 1]) + ((xv - xvM) * differences[i])) / (xvP - xvM);
            } else {
                firstDerivatives[i] = ((wP * differences[i - 1]) + (wM * differences[i])) / (wP + wM);
            }
        }

        firstDerivatives[0] = differentiateThreePoint(xvals, yvals, 0, 0, 1, 2);
        firstDerivatives[1] = differentiateThreePoint(xvals, yvals, 1, 0, 1, 2);
        firstDerivatives[xvals.length - 2] = differentiateThreePoint(xvals, yvals, xvals.length - 2, xvals.length - 3,
                xvals.length - 2, xvals.length - 1);
        firstDerivatives[xvals.length - 1] = differentiateThreePoint(xvals, yvals, xvals.length - 1, xvals.length - 3,
                xvals.length - 2, xvals.length - 1);

        return interpolateHermiteSorted(xvals, yvals, firstDerivatives);
    }

    private double differentiateThreePoint(double[] xvals, double[] yvals, int indexOfDifferentiation, int indexP1,
            int indexP2, int indexP3) {
        final double x0 = yvals[indexP1];
        final double x1 = yvals[indexP2];
        final double x2 = yvals[indexP3];

        final double t = xvals[indexOfDifferentiation] - xvals[indexP1];
        final double t1 = xvals[indexP2] - xvals[indexP1];
        final double t2 = xvals[indexP3] - xvals[indexP1];

        final double a = (x2 - x0 - (t2 / t1 * (x1 - x0))) / (t2 * t2 - t1 * t2);
        final double b = (x1 - x0 - a * t1 * t1) / t1;

        return (2 * a * t) + b;
    }

    private PolynomialSplineFunction interpolateHermiteSorted(double[] xvals, double[] yvals,
            double[] firstDerivatives) {

        checkArrayLength(xvals, yvals);
        checkArrayLength(xvals, firstDerivatives);

        final int minimumLength = 2;
        checkArrayLengthMin(xvals, minimumLength);

        final int size = xvals.length - 1;
        final PolynomialFunction[] polynomials = new PolynomialFunction[size];
        final double[] coefficients = new double[4];

        for (int i = 0; i < polynomials.length; i++) {
            final double w = xvals[i + 1] - xvals[i];
            final double w2 = w * w;

            final double yv = yvals[i];
            final double yvP = yvals[i + 1];

            final double fd = firstDerivatives[i];
            final double fdP = firstDerivatives[i + 1];

            coefficients[0] = yv;
            coefficients[1] = firstDerivatives[i];
            coefficients[2] = (3 * (yvP - yv) / w - 2 * fd - fdP) / w;
            coefficients[3] = (2 * (yv - yvP) / w + fd + fdP) / w2;
            polynomials[i] = new PolynomialFunction(coefficients);
        }

        return new PolynomialSplineFunction(xvals, polynomials);
    }

    private static final long SGN_MASK = 0x8000000000000000L;

    public static boolean precisionEquals(double x, double y) {
        return precisionEquals(x, y, 1);
    }

    public static boolean precisionEquals(double x, double y, int maxUlps) {
        long xInt = Double.doubleToLongBits(x);
        long yInt = Double.doubleToLongBits(y);

        // Make lexicographically ordered as a two's-complement integer.
        if (xInt < 0) {
            xInt = SGN_MASK - xInt;
        }
        if (yInt < 0) {
            yInt = SGN_MASK - yInt;
        }

        // final boolean isEqual = FastMath.abs(xInt - yInt) <= maxUlps; // oha
        final boolean isEqual = Math.abs(xInt - yInt) <= maxUlps;

        return isEqual && !Double.isNaN(x) && !Double.isNaN(y);
    }
}
