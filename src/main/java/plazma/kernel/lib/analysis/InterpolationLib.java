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

package plazma.kernel.lib.analysis;

import plazma.kernel.lib.array.ArrayLib;

public class InterpolationLib {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    //
    //
    // 1.1
    // - interpolateFunction(double[] x, double[] y)
    // - interpolateFunction(String type, double[] x, double[] y)
    //
    // 1.2
    // - interpolate(double[] x, double[] y, int points)
    // - interpolate(String type, double[] x, double[] y, int points)
    // - interpolate(InterpolationFunction function, double[] x)

    private InterpolationLib() {
        super();
    }

    public static InterpolationFunction interpolateFunction(double[] x, double[] y) {
        return interpolateFunction(null, x, y);
    }

    public static InterpolationFunction interpolateFunction(String type, double[] x, double[] y) {

        chekNotNull("x", x);
        chekNotNull("y", y);

        checkArrayLength(x, y);

        Interpolator interpolator = getInterpolator(type);
        return interpolator.interpolate(x, y);
    }

    public static double[] interpolate(double[] x, double[] y, int points) {
        return interpolate(null, x, y, points);
    }

    public static double[] interpolate(String type, double[] x, double[] y, int points) {

        // Create interpolation function
        InterpolationFunction function = interpolateFunction(type, x, y);

        // Calculate min/max values: range
        double min = ArrayLib.min(x);
        double max = ArrayLib.max(x);

        // Generate new x values: min, max range with 20 points
        double[] xNew = ArrayLib.pointDoubleArray(min, max, 20);

        return interpolate(function, xNew);
    }

    public static double[] interpolate(InterpolationFunction function, double[] x) {
        if (function == null) {
            throw new IllegalArgumentException("Function is null");
        }

        if (x == null) {
            return null;
        }

        if (x.length == 0) {
            return new double[0];
        }

        // Evaluate y by function
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            y[i] = function.evaluate(x[i]);
        }

        return y;
    }

    ////

    protected static Interpolator getInterpolator(String type) {

        // default
        if (type == null) {
            return getDefaultInterpolator();
        }

        // switch
        if ("linear".equalsIgnoreCase(type)) {
            return new LinearInterpolator();
        } else if ("akima".equalsIgnoreCase(type)) {
            return new AkimaSplineInterpolator();
        }

        // TODO: Maybe throw exception

        // default
        return getDefaultInterpolator();
    }

    protected static Interpolator getDefaultInterpolator() {
        return new LinearInterpolator();
    }

    ////

    private static void checkArrayLength(double[] array1, double[] array2) {
        CheckLib.checkArrayLength(array1, array2);
    }

    private static void chekNotNull(String name, Object obj) {
        CheckLib.checkNotNull(name, obj);
    }
}
