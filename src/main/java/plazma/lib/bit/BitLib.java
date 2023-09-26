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

package plazma.lib.bit;

public class BitLib {

    // Functions

    /////////////////////////////////////////////////////////////////////////////////
    // 1.1
    //
    // - bitAnd(x, y) - Bitwise AND
    // - bitOr(x, y) - Bitwise OR
    // - bitXor(x, y) - Bitwise XOR
    // - bitNot(x) - Bitwise unary NOT

    // - bitLeftShift(x, y) - Bitwise left shift
    // - bitRightShift(x, y) - Bitwise right shift
    // - bitURightShift(x, y) - Bitwise right shift zero fill

    private BitLib() {
    }

    public static int bitAnd(int x, int y) {
        return x & y;
    }

    public static int bitOr(int x, int y) {
        return x | y;
    }

    public static int bitXor(int x, int y) {
        return x ^ y;
    }

    // bitNe
    public static int bitNot(int x) {
        return ~x;
    }

    public static int bitLeftShift(int x, int y) {
        return x << y;
    }

    public static int bitRightShift(int x, int y) {
        return x >> y;
    }

    public static int bitURightShift(int x, int y) {
        return x >>> y;
    }

}
