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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import plazma.kernel.lib.ext.PrimitiveArray;
import plazma.kernel.lib.io.IOLib;
import plazma.kernel.lib.num.NumLib;

public class MatrixHelper {

    public static final String DEFAULT_SEPARATOR = ",";

    private MatrixHelper() {
    }

    //// read<Type>Matrix

    static byte[][] _readByteMatrix(String fileName) throws IOException {
        Class<?> type = byte.class;
        BufferedReader reader = null;
        try {
            reader = _createBufferedReader(fileName);
            List<PrimitiveArray> list = _readPrimitiveMatrix(reader, type);
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return new byte[0][0];
            }

            int size = list.size();
            byte[][] result = new byte[size][];
            for (int i = 0; i < size; i++) {
                result[i] = list.get(i).byteArray();
            }
            return result;
        } finally {
            _close(reader);
        }
    }

    static short[][] _readShortMatrix(String fileName) throws IOException {
        Class<?> type = short.class;
        BufferedReader reader = null;
        try {
            reader = _createBufferedReader(fileName);
            List<PrimitiveArray> list = _readPrimitiveMatrix(reader, type);
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return new short[0][0];
            }

            int size = list.size();
            short[][] result = new short[size][];
            for (int i = 0; i < size; i++) {
                result[i] = list.get(i).shortArray();
            }
            return result;
        } finally {
            _close(reader);
        }
    }

    static int[][] _readIntMatrix(String fileName) throws IOException {
        Class<?> type = int.class;
        BufferedReader reader = null;
        try {
            reader = _createBufferedReader(fileName);
            List<PrimitiveArray> list = _readPrimitiveMatrix(reader, type);
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return new int[0][0];
            }

            int size = list.size();
            int[][] result = new int[size][];
            for (int i = 0; i < size; i++) {
                result[i] = list.get(i).intArray();
            }
            return result;
        } finally {
            _close(reader);
        }
    }

    static long[][] _readLongMatrix(String fileName) throws IOException {
        Class<?> type = long.class;
        BufferedReader reader = null;
        try {
            reader = _createBufferedReader(fileName);
            List<PrimitiveArray> list = _readPrimitiveMatrix(reader, type);
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return new long[0][0];
            }

            int size = list.size();
            long[][] result = new long[size][];
            for (int i = 0; i < size; i++) {
                result[i] = list.get(i).longArray();
            }
            return result;
        } finally {
            _close(reader);
        }
    }

    static float[][] _readFloatMatrix(String fileName) throws IOException {
        Class<?> type = float.class;
        BufferedReader reader = null;
        try {
            reader = _createBufferedReader(fileName);
            List<PrimitiveArray> list = _readPrimitiveMatrix(reader, type);
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return new float[0][0];
            }

            int size = list.size();
            float[][] result = new float[size][];
            for (int i = 0; i < size; i++) {
                result[i] = list.get(i).floatArray();
            }
            return result;
        } finally {
            _close(reader);
        }
    }

    static double[][] _readDoubleMatrix(String fileName) throws IOException {
        Class<?> type = double.class;
        BufferedReader reader = null;
        try {
            reader = _createBufferedReader(fileName);
            List<PrimitiveArray> list = _readPrimitiveMatrix(reader, type);
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return new double[0][0];
            }

            int size = list.size();
            double[][] result = new double[size][];
            for (int i = 0; i < size; i++) {
                result[i] = list.get(i).doubleArray();
            }
            return result;
        } finally {
            _close(reader);
        }
    }

    //// write<Type>Matrix

    // byte
    static void _writeByteMatrix(String fileName, byte[][] matrix) throws IOException {
        _writeByteMatrix(fileName, matrix, null);
    }

    static void _writeByteMatrix(String fileName, byte[][] matrix, String separator) throws IOException {
        int length = matrix.length;
        Writer writer = null;
        try {
            writer = _createBufferedWriter(fileName);
            for (int i = 0; i < length; i++) {
                byte[] row = matrix[i];
                String rowStr = NumLib.writeByteArrayToString(row, separator);
                writer.write(rowStr + "\n");
            }
        } finally {
            _close(writer);
        }
    }

    // short
    static void _writeShortMatrix(String fileName, short[][] matrix) throws IOException {
        _writeShortMatrix(fileName, matrix, null);
    }

    static void _writeShortMatrix(String fileName, short[][] matrix, String separator) throws IOException {
        int length = matrix.length;
        Writer writer = null;
        try {
            writer = _createBufferedWriter(fileName);
            for (int i = 0; i < length; i++) {
                short[] row = matrix[i];
                String rowStr = NumLib.writeShortArrayToString(row, separator);
                writer.write(rowStr + "\n");
            }
        } finally {
            _close(writer);
        }
    }

    // int
    static void _writeIntMatrix(String fileName, int[][] matrix) throws IOException {
        _writeIntMatrix(fileName, matrix, null);
    }

    static void _writeIntMatrix(String fileName, int[][] matrix, String separator) throws IOException {
        int length = matrix.length;
        Writer writer = null;
        try {
            writer = _createBufferedWriter(fileName);
            for (int i = 0; i < length; i++) {
                int[] row = matrix[i];
                String rowStr = NumLib.writeIntArrayToString(row, separator);
                writer.write(rowStr + "\n");
            }
        } finally {
            _close(writer);
        }
    }

    // long
    static void _writeLongMatrix(String fileName, long[][] matrix) throws IOException {
        _writeLongMatrix(fileName, matrix, null);
    }

    static void _writeLongMatrix(String fileName, long[][] matrix, String separator) throws IOException {
        int length = matrix.length;
        Writer writer = null;
        try {
            writer = _createBufferedWriter(fileName);
            for (int i = 0; i < length; i++) {
                long[] row = matrix[i];
                String rowStr = NumLib.writeLongArrayToString(row, separator);
                writer.write(rowStr + "\n");
            }
        } finally {
            _close(writer);
        }
    }

    // float
    static void _writeFloatMatrix(String fileName, float[][] matrix) throws IOException {
        _writeFloatMatrix(fileName, matrix, null);
    }

    static void _writeFloatMatrix(String fileName, float[][] matrix, String separator) throws IOException {
        int length = matrix.length;
        Writer writer = null;
        try {
            writer = _createBufferedWriter(fileName);
            for (int i = 0; i < length; i++) {
                float[] row = matrix[i];
                String rowStr = NumLib.writeFloatArrayToString(row, separator);
                writer.write(rowStr + "\n");
            }
        } finally {
            _close(writer);
        }
    }

    // double
    static void _writeDoubleMatrix(String fileName, double[][] matrix) throws IOException {
        _writeDoubleMatrix(fileName, matrix, null);
    }

    static void _writeDoubleMatrix(String fileName, double[][] matrix, String separator) throws IOException {
        int length = matrix.length;
        Writer writer = null;
        try {
            writer = _createBufferedWriter(fileName);
            for (int i = 0; i < length; i++) {
                double[] row = matrix[i];
                String rowStr = NumLib.writeDoubleArrayToString(row, separator);
                writer.write(rowStr + "\n");
            }
        } finally {
            _close(writer);
        }
    }

    ////

    static List<PrimitiveArray> _readPrimitiveMatrix(BufferedReader reader, Class<?> type) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return null;
        }
        List<PrimitiveArray> list = new ArrayList<PrimitiveArray>();
        while (line != null) {

            PrimitiveArray values = _createPrimitiveArray(type, line);
            list.add(values);

            // read next line
            line = reader.readLine();
        }
        return list;
    }

    static PrimitiveArray _createPrimitiveArray(Class<?> type, String line) {
        if (line == null) {
            return null;
        }
        if (type == byte.class) {
            return new PrimitiveArray(NumLib.readByteArrayFromString(line));
        } else if (type == short.class) {
            return new PrimitiveArray(NumLib.readShortArrayFromString(line));
        } else if (type == int.class) {
            return new PrimitiveArray(NumLib.readIntArrayFromString(line));
        } else if (type == long.class) {
            return new PrimitiveArray(NumLib.readLongArrayFromString(line));
        } else if (type == float.class) {
            return new PrimitiveArray(NumLib.readFloatArrayFromString(line));
        } else if (type == double.class) {
            return new PrimitiveArray(NumLib.readDoubleArrayFromString(line));
        }
        throw new IllegalArgumentException("Unsupported type '" + type + "'");
    }

    ////

    static Reader _createReader(String fileName) throws IOException {
        return IOLib.createReader(fileName);
    }

    static BufferedReader _createBufferedReader(String fileName) throws IOException {
        return IOLib.createBufferedReader(fileName);
    }

    static Writer _createWriter(String fileName) throws IOException {
        return IOLib.createWriter(fileName);
    }

    static BufferedWriter _createBufferedWriter(String fileName) throws IOException {
        return IOLib.createBufferedWriter(fileName);
    }

    //

    static void _close(Reader reader) {
        IOLib.close(reader);
    }

    static void _close(Writer writer) {
        IOLib.close(writer);
    }

}
