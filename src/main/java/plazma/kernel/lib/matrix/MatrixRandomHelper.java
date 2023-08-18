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

package plazma.kernel.lib.matrix;

import java.util.function.Function;

import plazma.kernel.lib.num.NumLib;

public class MatrixRandomHelper {

    private MatrixRandomHelper() {
    }

    static void checkSize(int cols, int rows) {
        if (cols < 0) {
            throw new IllegalArgumentException("Columns must be > 0");
        }
        if (rows < 0) {
            throw new IllegalArgumentException("Rows must be > 0");
        }
    }

    ////

    // 100
    static float[][] _randomFloatMatrix(int cols, int rows) {
        return _randomFloatMatrix(100, cols, rows, 0f, 0f, null);
    }

    // 110
    static float[][] _randomFloatMatrix(int cols, int rows, float max) {
        return _randomFloatMatrix(110, cols, rows, 0f, max, null);
    }

    // 120
    static float[][] _randomFloatMatrix(int cols, int rows, float min, float max) {
        return _randomFloatMatrix(120, cols, rows, min, max, null);
    }

    // 101
    static float[][] _randomFloatMatrix(int cols, int rows, Function<Float, Float> mapper) {
        return _randomFloatMatrix(101, cols, rows, 0f, 0f, mapper);
    }

    // 111
    static float[][] _randomFloatMatrix(int cols, int rows, float max, Function<Float, Float> mapper) {
        return _randomFloatMatrix(111, cols, rows, 0f, max, mapper);
    }

    // 121
    static float[][] _randomFloatMatrix(int cols, int rows, float min, float max, Function<Float, Float> mapper) {
        return _randomFloatMatrix(121, cols, rows, min, max, mapper);
    }

    //

    static float[][] _randomFloatMatrix(int mode, int cols, int rows, float min, float max,
            Function<Float, Float> mapper) {
        checkSize(cols, rows);

        float[][] matrix = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            float[] rowArray = new float[cols];
            for (int j = 0; j < cols; j++) {
                rowArray[j] = _randomFloat(mode, min, max, mapper);
            }
            matrix[i] = rowArray;
        }
        return matrix;
    }

    ////

    // 100
    static double[][] _randomDoubleMatrix(int cols, int rows) {
        return _randomDoubleMatrix(100, cols, rows, 0f, 0f, null);
    }

    // 110
    static double[][] _randomDoubleMatrix(int cols, int rows, double max) {
        return _randomDoubleMatrix(110, cols, rows, 0f, max, null);
    }

    // 120
    static double[][] _randomDoubleMatrix(int cols, int rows, double min, double max) {
        return _randomDoubleMatrix(120, cols, rows, min, max, null);
    }

    // 101
    static double[][] _randomDoubleMatrix(int cols, int rows, Function<Double, Double> mapper) {
        return _randomDoubleMatrix(101, cols, rows, 0f, 0f, mapper);
    }

    // 111
    static double[][] _randomDoubleMatrix(int cols, int rows, double max, Function<Double, Double> mapper) {
        return _randomDoubleMatrix(111, cols, rows, 0f, max, mapper);
    }

    // 121
    static double[][] _randomDoubleMatrix(int cols, int rows, double min, double max, Function<Double, Double> mapper) {
        return _randomDoubleMatrix(121, cols, rows, min, max, mapper);
    }

    //

    static double[][] _randomDoubleMatrix(int mode, int cols, int rows, double min, double max,
            Function<Double, Double> mapper) {
        checkSize(cols, rows);

        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            double[] rowArray = new double[cols];
            for (int j = 0; j < cols; j++) {
                rowArray[j] = _randomDouble(mode, min, max, mapper);
            }
            matrix[i] = rowArray;
        }
        return matrix;
    }

    // 100 - ()
    // 110 - (max)
    // 120 - (min, max)
    //
    // 101 - (mapper)
    // 111 - (max, mapper)
    // 121 - (min, max, mapper)
    static float _randomFloat(int mode, float min, float max, Function<Float, Float> mapper) {
        if (mode == 100) {
            return NumLib.randomFloat();
        } else if (mode == 110) {
            return NumLib.randomFloat(max);
        } else if (mode == 120) {
            return NumLib.randomFloat(min, max);
        } else if (mode == 101) {
            return NumLib.randomFloat(mapper);
        } else if (mode == 111) {
            return NumLib.randomFloat(max, mapper);
        } else if (mode == 121) {
            return NumLib.randomFloat(min, max, mapper);
        }
        return 0f;
    }

    // 100 - ()
    // 110 - (max)
    // 120 - (min, max)
    //
    // 101 - (mapper)
    // 111 - (max, mapper)
    // 121 - (min, max, mapper)
    static double _randomDouble(int mode, double min, double max, Function<Double, Double> mapper) {
        if (mode == 100) {
            return NumLib.randomDouble();
        } else if (mode == 110) {
            return NumLib.randomDouble(max);
        } else if (mode == 120) {
            return NumLib.randomDouble(min, max);
        } else if (mode == 101) {
            return NumLib.randomDouble(mapper);
        } else if (mode == 111) {
            return NumLib.randomDouble(max, mapper);
        } else if (mode == 121) {
            return NumLib.randomDouble(min, max, mapper);
        }
        return 0d;
    }

}
