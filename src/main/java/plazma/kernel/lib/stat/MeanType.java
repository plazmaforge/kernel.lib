package plazma.kernel.lib.stat;

public enum MeanType {

    // (x1 + x2 + ... + xn) / n
    // https://en.wikipedia.org/wiki/Arithmetic_mean
    // http://mathworld.wolfram.com/ArithmeticMean.html
    ARITHMETIC,

    // (x1 * x2 * ... * xn) ^ (1/n)
    // https://en.wikipedia.org/wiki/Geometric_mean
    // http://mathworld.wolfram.com/GeometricMean.html
    GEOMETRIC,

    // n / (1/x1 + 1/x2 + ... 1/xn)
    // https://en.wikipedia.org/wiki/Harmonic_mean
    // http://mathworld.wolfram.com/HarmonicMean.html
    HARMONIC,

    // http://mathworld.wolfram.com/Root-Mean-Square.html
    // https://www.geeksforgeeks.org/program-to-calculate-root-mean-square/
    SQRT,

    MEDIAN,

    MEDIAN_LOW,

    MEDIAN_HIGH,

    MIDRANGE;

}
