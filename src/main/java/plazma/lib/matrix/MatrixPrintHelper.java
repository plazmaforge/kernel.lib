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

package plazma.lib.matrix;

import java.io.PrintStream;
import java.util.Arrays;

public class MatrixPrintHelper {

    private MatrixPrintHelper() {
    }

    ////

    static PrintStream _out() {
        return System.out;
    }

    // byte

    static void _printMatrix(byte[][] matrix) {
        _printMatrix(matrix, _out());
    }

    static void _printMatrix(byte[][] matrix, PrintStream stream) {
        if (matrix == null) {
            stream.println("" + null);
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            byte[] row = matrix[i];
            stream.println(Arrays.toString(row));
        }
    }

    // short

    static void _printMatrix(short[][] matrix) {
        _printMatrix(matrix, _out());
    }

    static void _printMatrix(short[][] matrix, PrintStream stream) {
        if (matrix == null) {
            stream.println("" + null);
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            short[] row = matrix[i];
            stream.println(Arrays.toString(row));
        }
    }

    // int

    static void _printMatrix(int[][] matrix) {
        _printMatrix(matrix, _out());
    }

    static void _printMatrix(int[][] matrix, PrintStream stream) {
        if (matrix == null) {
            stream.println("" + null);
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            int[] row = matrix[i];
            stream.println(Arrays.toString(row));
        }
    }

    // long

    static void _printMatrix(long[][] matrix) {
        _printMatrix(matrix, _out());
    }

    static void _printMatrix(long[][] matrix, PrintStream stream) {
        if (matrix == null) {
            stream.println("" + null);
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            long[] row = matrix[i];
            stream.println(Arrays.toString(row));
        }
    }

    // float

    static void _printMatrix(float[][] matrix) {
        _printMatrix(matrix, _out());
    }

    static void _printMatrix(float[][] matrix, PrintStream stream) {
        if (matrix == null) {
            stream.println("" + null);
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            float[] row = matrix[i];
            stream.println(Arrays.toString(row));
        }
    }

    // double

    static void _printMatrix(double[][] matrix) {
        _printMatrix(matrix, _out());
    }

    static void _printMatrix(double[][] matrix, PrintStream stream) {
        if (matrix == null) {
            stream.println("" + null);
        }
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            double[] row = matrix[i];
            stream.println(Arrays.toString(row));
        }
    }
    
}
