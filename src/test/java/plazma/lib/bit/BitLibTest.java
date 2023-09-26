package plazma.lib.bit;

import plazma.lib.AbstractTestCase;
import plazma.lib.num.NumLib;

public class BitLibTest extends AbstractTestCase {

    public void testOperators() {

        // =============================
        // AND
        // =============================
        printLine();
        println(" AND ");
        printLine();

        // 0 & 0
        evalAnd(0, 0);

        // 10 & 0
        evalAnd(10, 0);

        // 0 & 10
        evalAnd(0, 10);

        // 10 & 10
        evalAnd(10, 10);

        // ==============================
        // OR
        // ==============================
        println("");
        printLine();
        println(" OR ");
        printLine();

        // 0 | 0
        evalOr(0, 0);

        // 10 & 0
        evalOr(10, 0);

        // 0 | 10
        evalOr(0, 10);

        // 10 | 10
        evalOr(10, 10);

        // ==============================
        // XOR
        // ==============================
        println("");
        printLine();
        println(" XOR ");
        printLine();

        // 0 ^ 0
        evalXor(0, 0);

        // 10 ^ 0
        evalXor(10, 0);

        // 0 ^ 10
        evalXor(0, 10);

        // 10 ^ 10
        evalXor(10, 10);

        // ==============================
        // LEFT SHIFT: <<
        // ==============================
        println("");
        printLine();
        println(" LEFT SHIFT: << ");
        printLine();

        // 0 & 0
        evalLeftShift(0, 1);

        // 10 & 0
        evalLeftShift(10, 11);

        // 0 & 10
        evalLeftShift(0, 10);

        // 10 & 10
        evalLeftShift(10, 10);

    }

    public void evalAnd(int x, int y) {
        printBit(x, y, BitLib.bitAnd(x, y), "&");
    }

    public void evalOr(int x, int y) {
        printBit(x, y, BitLib.bitOr(x, y), "|");
    }

    public void evalXor(int x, int y) {
        printBit(x, y, BitLib.bitXor(x, y), "^");
    }

    public void evalLeftShift(int x, int y) {
        printBit(x, y, BitLib.bitLeftShift(x, y), "<<");
    }

    public void printBit(int x, int y, int r, String op) {
        println(NumLib.toBinaryString(x) + "\t\t" + op + "\t\t" + NumLib.toBinaryString(y) + "\t\t=\t\t"
                + NumLib.toBinaryString(r));
    }

}
