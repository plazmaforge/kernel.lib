package plazma.kernel.lib.analysis;

public class PolynomialFunction extends AbstractFunction {

    private final double[] coefficients;

    public PolynomialFunction(double[] c) {

        checkNotNull("coefficients", c);
        checkNoData("coefficients", c);

        int n = c.length;

        while ((n > 1) && (c[n - 1] == 0)) {
            --n;
        }
        this.coefficients = new double[n];
        System.arraycopy(c, 0, this.coefficients, 0, n);
    }

    public double evaluate(double x) {
        return evaluate(coefficients, x);
    }

    protected static double evaluate(double[] coefficients, double argument) {
        CheckLib.checkNotNull("coefficients", coefficients);
        CheckLib.checkNoData("coefficients", coefficients);

        int n = coefficients.length;

        double result = coefficients[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            result = argument * result + coefficients[j];
        }
        return result;
    }

    protected static double[] differentiate(double[] coefficients) {

        CheckLib.checkNotNull("coefficients", coefficients);
        CheckLib.checkNoData("coefficients", coefficients);

        int n = coefficients.length;

        if (n == 1) {
            return new double[] { 0 };
        }
        double[] result = new double[n - 1];
        for (int i = n - 1; i > 0; i--) {
            result[i - 1] = i * coefficients[i];
        }
        return result;
    }
}
