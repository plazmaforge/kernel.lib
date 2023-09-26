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

import java.io.IOException;
import java.io.PrintStream;
import java.util.function.Function;

public class MatrixLib {

    // Functions
    
    /////////////////////////////////////////////////////////////////////////////////
    // 1.1 randomFloatMatrix
    //
    // - float[][] randomFloatMatrix(int cols, int rows)
    // - float[][] randomFloatMatrix(int cols, int rows, float max)
    // - float[][] randomFloatMatrix(int cols, int rows, float min, float max)
    // - float[][] randomFloatMatrix(int cols, int rows, Function<Float, Float> mapper) {
    // - float[][] randomFloatMatrix(int cols, int rows, float max, Function<Float, Float> mapper)
    // - float[][] randomFloatMatrix(int cols, int rows, float min, float max, Function<Float, Float> mapper)
    //
    // 1.2 randomDoubleMatrix
    //
    // - double[][] randomDoubleMatrix(int cols, int rows)
    // - double[][] randomDoubleMatrix(int cols, int rows, double max)
    // - double[][] randomDoubleMatrix(int cols, int rows, double min, double max)
    // - double[][] randomDoubleMatrix(int cols, int rows, Function<Double, Double> mapper)
    // - double[][] randomDoubleMatrix(int cols, int rows, double max, Function<Double, Double> mapper)
    // - double[][] randomDoubleMatrix(int cols, int rows, double min, double max, Function<Double, Double> mapper)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 2.1 printMatrix: byte
    //
    // - void printMatrix(byte[][] matrix)
    // - void printMatrix(byte[][] matrix, PrintStream stream)
    //
    // 2.2 printMatrix: short
    //
    // - void printMatrix(short[][] matrix)
    // - void printMatrix(short[][] matrix, PrintStream stream)
    //
    // 2.3 printMatrix: int
    //
    // - void printMatrix(int[][] matrix)
    // - void printMatrix(int[][] matrix, PrintStream stream)
    //
    // 2.4 printMatrix: long
    //
    // - void printMatrix(long[][] matrix)
    // - void printMatrix(long[][] matrix, PrintStream stream)
    //
    // 2.5 printMatrix: float
    //
    // void printMatrix(float[][] matrix)
    // void printMatrix(float[][] matrix, PrintStream stream)
    //
    // 2.6 printMatrix: double
    //
    // - void printMatrix(double[][] matrix)
    // - void printMatrix(double[][] matrix, PrintStream stream)
   
    /////////////////////////////////////////////////////////////////////////////////
    // 3.1 read<Type>Matrix
    //
    // - byte[][] readByteMatrix(String fileName)
    // - short[][] readShortMatrix(String fileName)
    // - int[][] readIntMatrix(String fileName)
    // - long[][] readLongMatrix(String fileName)
    // - float[][] readFloatMatrix(String fileName)
    // - double[][] readDoubleMatrix(String fileName)

    /////////////////////////////////////////////////////////////////////////////////
    // 4.1 write<Type>Matrix
    //
    // - void writeByteMatrix(String fileName, byte[][] matrix)
    // - void writeShortMatrix(String fileName, short[][] matrix)
    // - void writeIntMatrix(String fileName, int[][] matrix)
    // - void writeLongMatrix(String fileName, long[][] matrix)
    // - void writeFloatMatrix(String fileName, float[][] matrix)
    // - void writeDoubleMatrix(String fileName, double[][] matrix)
    
    
    private MatrixLib() {
    }

    //// 1.1 randomFloatMatrix

    public static float[][] randomFloatMatrix(int cols, int rows) {
        return MatrixRandomHelper._randomFloatMatrix(cols, rows);
    }

    public static float[][] randomFloatMatrix(int cols, int rows, float max) {
        return MatrixRandomHelper._randomFloatMatrix(cols, rows, max);
    }

    public static float[][] randomFloatMatrix(int cols, int rows, float min, float max) {
        return MatrixRandomHelper._randomFloatMatrix(cols, rows, min, max);
    }

    public static float[][] randomFloatMatrix(int cols, int rows, Function<Float, Float> mapper) {
        return MatrixRandomHelper._randomFloatMatrix(cols, rows, mapper);
    }

    public static float[][] randomFloatMatrix(int cols, int rows, float max, Function<Float, Float> mapper) {
        return MatrixRandomHelper._randomFloatMatrix(cols, rows, max, mapper);
    }

    public static float[][] randomFloatMatrix(int cols, int rows, float min, float max, Function<Float, Float> mapper) {
        return MatrixRandomHelper._randomFloatMatrix(cols, rows, min, max, mapper);
    }

    //// 1.2 randomDoubleMatrix

    public static double[][] randomDoubleMatrix(int cols, int rows) {
        return MatrixRandomHelper._randomDoubleMatrix(cols, rows);
    }

    public static double[][] randomDoubleMatrix(int cols, int rows, double max) {
        return MatrixRandomHelper._randomDoubleMatrix(cols, rows, max);
    }

    public static double[][] randomDoubleMatrix(int cols, int rows, double min, double max) {
        return MatrixRandomHelper._randomDoubleMatrix(cols, rows, min, max);
    }

    public static double[][] randomDoubleMatrix(int cols, int rows, Function<Double, Double> mapper) {
        return MatrixRandomHelper._randomDoubleMatrix(cols, rows, mapper);
    }

    public static double[][] randomDoubleMatrix(int cols, int rows, double max, Function<Double, Double> mapper) {
        return MatrixRandomHelper._randomDoubleMatrix(cols, rows, max, mapper);
    }

    public static double[][] randomDoubleMatrix(int cols, int rows, double min, double max,
            Function<Double, Double> mapper) {
        return MatrixRandomHelper._randomDoubleMatrix(cols, rows, min, max, mapper);
    }

    //// 2.1 printMatrix: byte

    public static void printMatrix(byte[][] matrix) {
        MatrixPrintHelper._printMatrix(matrix);
    }

    public static void printMatrix(byte[][] matrix, PrintStream stream) {
        MatrixPrintHelper._printMatrix(matrix, stream);
    }

    //// 2.2 printMatrix: short

    public static void printMatrix(short[][] matrix) {
        MatrixPrintHelper._printMatrix(matrix);
    }

    public static void printMatrix(short[][] matrix, PrintStream stream) {
        MatrixPrintHelper._printMatrix(matrix, stream);
    }

    //// 2.3 printMatrix: int

    public static void printMatrix(int[][] matrix) {
        MatrixPrintHelper._printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix, PrintStream stream) {
        MatrixPrintHelper._printMatrix(matrix, stream);
    }

    //// 2.4 printMatrix: long

    public static void printMatrix(long[][] matrix) {
        MatrixPrintHelper._printMatrix(matrix);
    }

    public static void printMatrix(long[][] matrix, PrintStream stream) {
        MatrixPrintHelper._printMatrix(matrix, stream);
    }

    //// 2.5 printMatrix: float

    public static void printMatrix(float[][] matrix) {
        MatrixPrintHelper._printMatrix(matrix);
    }

    public static void printMatrix(float[][] matrix, PrintStream stream) {
        MatrixPrintHelper._printMatrix(matrix, stream);
    }

    //// 2.6 printMatrix: double

    public static void printMatrix(double[][] matrix) {
        MatrixPrintHelper._printMatrix(matrix);
    }

    public static void printMatrix(double[][] matrix, PrintStream stream) {
        MatrixPrintHelper._printMatrix(matrix, stream);
    }

    //// 3.1 read<Type>Matrix

    public static byte[][] readByteMatrix(String fileName) throws IOException {
        return MatrixHelper._readByteMatrix(fileName);
    }

    public static short[][] readShortMatrix(String fileName) throws IOException {
        return MatrixHelper._readShortMatrix(fileName);
    }

    public static int[][] readIntMatrix(String fileName) throws IOException {
        return MatrixHelper._readIntMatrix(fileName);
    }

    public static long[][] readLongMatrix(String fileName) throws IOException {
        return MatrixHelper._readLongMatrix(fileName);
    }

    public static float[][] readFloatMatrix(String fileName) throws IOException {
        return MatrixHelper._readFloatMatrix(fileName);
    }

    public static double[][] readDoubleMatrix(String fileName) throws IOException {
        return MatrixHelper._readDoubleMatrix(fileName);
    }

    //// 4.1 write<Type>Matrix

    // byte
    public static void writeByteMatrix(String fileName, byte[][] matrix) throws IOException {
        MatrixHelper._writeByteMatrix(fileName, matrix);
    }

    public static void writeByteMatrix(String fileName, byte[][] matrix, String separator) throws IOException {
        MatrixHelper._writeByteMatrix(fileName, matrix, separator);
    }

    // short
    public static void writeShortMatrix(String fileName, short[][] matrix) throws IOException {
        MatrixHelper._writeShortMatrix(fileName, matrix);
    }

    public static void writeShortMatrix(String fileName, short[][] matrix, String separator) throws IOException {
        MatrixHelper._writeShortMatrix(fileName, matrix, separator);
    }

    // int
    public static void writeIntMatrix(String fileName, int[][] matrix) throws IOException {
        MatrixHelper._writeIntMatrix(fileName, matrix);
    }

    public static void writeIntMatrix(String fileName, int[][] matrix, String separator) throws IOException {
        MatrixHelper._writeIntMatrix(fileName, matrix, separator);
    }

    // long
    public static void writeLongMatrix(String fileName, long[][] matrix) throws IOException {
        MatrixHelper._writeLongMatrix(fileName, matrix);
    }

    public static void writeLongMatrix(String fileName, long[][] matrix, String separator) throws IOException {
        MatrixHelper._writeLongMatrix(fileName, matrix, separator);
    }

    // float
    public static void writeFloatMatrix(String fileName, float[][] matrix) throws IOException {
        MatrixHelper._writeFloatMatrix(fileName, matrix);
    }

    public static void writeFloatMatrix(String fileName, float[][] matrix, String separator) throws IOException {
        MatrixHelper._writeFloatMatrix(fileName, matrix, separator);
    }

    // double
    public static void writeDoubleMatrix(String fileName, double[][] matrix) throws IOException {
        MatrixHelper._writeDoubleMatrix(fileName, matrix);
    }

    public static void writeDoubleMatrix(String fileName, double[][] matrix, String separator) throws IOException {
        MatrixHelper._writeDoubleMatrix(fileName, matrix, separator);
    }

}
