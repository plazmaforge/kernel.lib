package plazma.kernel.lib.matrix;

import plazma.kernel.lib.AbstractTestCase;
import plazma.kernel.lib.array.ArrayLib;
import plazma.kernel.lib.math.MathLib;
import plazma.kernel.lib.matrix.MatrixLib;

public class MatrixLibTest extends AbstractTestCase {

    // write/read matrix: (COLS x ROWS)
    public void testPerformaceRandomMatrix() throws Exception {

        int FILE_COUNT = 100; // file count
        int COLS = 100; // 10, 100;
        int ROWS = 1000; // 100, 1000, 10000, 1000000;

        printHeader("Random float matrix");

        // write: avg = {cols = 100, rows 1000000} -> 2708 ms
        // write: avg = {cols = 100, rows 1000000} -> 16438 ms

        long time = 0;
        float timeTotal = 0f;
        float[][] floatMatrix = null;

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERATE AND WRIRE MATRIX (float)
        //////////////////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < FILE_COUNT; i++) {

            floatMatrix = MatrixLib.randomFloatMatrix(COLS, ROWS, -100f, 100f, (x) -> MathLib.round(x, 2));
            time = System.currentTimeMillis();
            MatrixLib.writeFloatMatrix(TEST_DIR + "/" + "test-float-matrix-" + (i + 1) + ".csv", floatMatrix);
            time = System.currentTimeMillis() - time;

            timeTotal += time;
            // println("write : time = " + time);

        }

        // JMH
        println("write: avg = " + (timeTotal / FILE_COUNT));

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // READ MATRIX (float)
        //////////////////////////////////////////////////////////////////////////////////////////////////
        timeTotal = 0f;
        for (int i = 0; i < FILE_COUNT; i++) {
            time = System.currentTimeMillis();
            floatMatrix = MatrixLib.readFloatMatrix(TEST_DIR + "/" + "test-float-matrix-" + (i + 1) + ".csv");
            time = System.currentTimeMillis() - time;
            timeTotal += time;
            // println("read : time = " + time);
        }

        // JMH
        println("read : avg = " + (timeTotal / FILE_COUNT));

    }

    public void testRandomDoubleMatrix() throws Exception {
        printHeader("Random double matrix");

        int COLS = 10;
        int ROWS = 100;
        double[][] doubleMatrix = MatrixLib.randomDoubleMatrix(COLS, ROWS, -100d, 100d, (x) -> MathLib.round(x, 2));
        // MatrixLib.printMatrix(doubleMatrix);
    }

    /*
     * public void testPerformance() throws Exception { long start =
     * System.currentTimeMillis();
     * 
     * float[][] matrix = null; int count = 100; for (int i = 1; i <= count; i++) {
     * matrix = MatrixLib.readFloatMatrix(TEST_DIR + "/" + "test-float-matrix-" + i
     * + ".csv"); }
     * 
     * //List<String> lines = IOLib._readLines(TEST_DIR + "/" +
     * "test-float-matrix.csv"); //for (String line: lines ) {
     * //System.out.print(line + "\n"); //}
     * 
     * long end = System.currentTimeMillis();
     * 
     * System.out.print("Elapsed time: " + ((end - start) / 1000.0) + "s"); }
     */

    public void testIntMatrix() throws Exception {
        int[][] intMatrix = new int[][] { { -1, 0, 10, 30, -23 }, { 23, 67, -23, 7, 89 }, { 6, 83, -2, 67, 9 } };

        String fileName = TEST_DIR + "/" + "test-int-matrix-1.csv";
        MatrixLib.writeIntMatrix(fileName, intMatrix);

        int[][] intMatrix2 = MatrixLib.readIntMatrix(fileName);

        MatrixLib.printMatrix(intMatrix2);

        byte[] byteArray = ArrayLib.readByteArrayFromString("0, 300, 2, 3, 4, 5, 6, 7, 8, 9");
        printHeader("Byte array");
        println(ArrayLib.toString(byteArray));

        int[] intArray = ArrayLib.readIntArrayFromString("0, 1, 2, 3, 4, 5, 6, 7, 8, 9");
        printHeader("Integer array");
        println(ArrayLib.toString(intArray));

        float[] floatArray = ArrayLib
                .readFloatArrayFromString("0, 1, 1.2, 2, 2.3, 3, 3.6, 4, 4.4, 5, 5.6, 6, 6.8, 7, 7.9, 8, 8.5, 9, 9.1");
        printHeader("Float array");
        println(ArrayLib.toString(floatArray));

        double[] doubleArray = ArrayLib
                .readDoubleArrayFromString("0, 1, 1.2, 2, 2.3, 3, 3.6, 4, 4.4, 5, 5.6, 6, 6.8, 7, 7.9, 8, 8.5, 9, 9.1");
        printHeader("Double array");
        println(ArrayLib.toString(doubleArray));

    }
}
