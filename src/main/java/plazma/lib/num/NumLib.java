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

package plazma.lib.num;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

import plazma.lib.array.ArrayLib;
import plazma.lib.obj.ObjLib;

public class NumLib {

    // Functions:
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1.1
    //
    // - toHexString(value)
    // - toBinaryString(value)
    // 
    // 1.2
    // 
    // - formatHex(value)		- [ALIAS]
    // - formatBinary(value)		- [ALIAS]
    // 
    //
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 2.1 parse<Primitive>(str): NumberFormatException (!)
    //
    // - parseByte(str)
    // - parseByte(str, radix)
    //
    // - parseShort(str)
    // - parseShort(str, radix)
    //
    // - parseInt(str)
    // - parseInt(str, int radix)
    //
    // - parseLong(str)
    // - parseLong(str, int radix)
    //
    // - parseFloat(str)
    //
    // - parseDouble(str)
    //
    // - TODO: parseBigInteger(str) (?)
    // - TODO: parseBigDecimal(str) (?)
    // 
    // 2.2 to<Primitive>(str): No exception - use default value (!)
    // 
    // - toByte(str)
    // - toByte(byte def) {
    // - toByte(str, radix, def)
    //
    // - toShort(str)
    // - toShort(str, def)
    // - toShort(str, radix, def)
    //
    // - toInt(str)
    // - toInt(str, def)
    // - toInt(str, radix, def)
    //
    // - toLong(str)
    // - toLong(str, def)
    // - toLong(str, radix, def)
    //
    // - toFloat(str)
    // - toFloat(str, def)
    //
    // - toDouble(str)
    // - toDouble(str, def)   
    //
    // 2.3 toObject<Primitive>(str): No exception - use default value (!)
    // 
    // - toObjectByte(str)
    // - toObjectByte(byte def) {
    // - toObjectByte(str, radix, def)
    //
    // - toObjectShort(str)
    // - toObjectShort(str, def)
    // - toObjectShort(str, radix, def)
    //
    // - toObjectInt(str)
    // - toObjectInt(str, def)
    // - toObjectInt(str, radix, def)
    //
    // - toObjectLong(str)
    // - toObjectLong(str, def)
    // - toObjectLong(str, radix, def)
    //
    // - toObjectFloat(str)
    // - toObjectFloat(str, def)
    //
    // - toObjectDouble(str)
    // - toObjectDouble(str, def)      
    
    // - TODO: toBigInteger(str) (?)
    // - TODO: toBigDecimal(str) (?)
      
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 3.1 
    // 
    // - toBigInteger(number)
    // - toBigDecimal(number)
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 4.1 trunc, truncInt, truntDec
    // 
    // - intValue(float value)  : int
    // - longValue(double value): long
    //
    // - trunc(float value)    : float                  - 23.45 => 23
    // - trunc(double value)   : double 

    // - truncInt(float value) : int                    - 23.45 => 23
    // - truncInt(double value): long
    //
    // - truncDec(float value) : float                  - 23.45 => 0.45
    // - truncDec(double value): double

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 5.1 
    //
    // - defValue(primitive, useDef)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // 6.1 
    //
    // - randomByte()
    // - randomChar()
    // - randomShort()
    // 
    // - randomInt()
    // - randomInt(max)
    // - randomInt(min, max)
    // - randomInt(mapper)
    // - randomInt(max, mapper)
    //
    // - randomLong()
    // - randomLong(max)
    // - randomLong(min, max)
    // - randomLong(mapper)
    // - randomLong(max, mapper)
    //
    // - randomFloat()
    // - randomFloat(max)
    // - randomFloat(min, max)
    // - randomFloat(mapper)
    // - randomFloat(max, mapper)    
    //
    // - randomDouble()
    // - randomDouble(max)
    // - randomDouble(min, max)
    // - randomDouble(mapper)
    // - randomDouble(max, mapper)

    
    // TODO: min(), max()
    
    public static final int DEFAULT_RADIX = ObjLib.DEFAULT_RADIX;

    //

    public static final byte DEFAULT_BYTE = ObjLib.DEFAULT_BYTE;

    public static final char DEFAULT_CHAR = ObjLib.DEFAULT_CHAR;

    public static final short DEFAULT_SHORT = ObjLib.DEFAULT_SHORT;

    public static final int DEFAULT_INT = ObjLib.DEFAULT_INT;

    public static final long DEFAULT_LONG = ObjLib.DEFAULT_LONG;

    public static final float DEFAULT_FLOAT = ObjLib.DEFAULT_FLOAT;

    public static final double DEFAULT_DOUBLE = ObjLib.DEFAULT_DOUBLE;

    //

    public static final int[] ARAB = { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };

    public static final String[] ROMAN = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };

    //

    private static final String EMPTY_STRING = "";

    private NumLib() {
    }

    //// 1.1 toHex/Binary

    public static String toHexString(int value) {
        return Integer.toHexString(value);
    }

    public static String toBinaryString(int value) {
        return Integer.toBinaryString(value);
    }

    public static String toHexString(long value) {
        return Long.toHexString(value);
    }

    public static String toBinaryString(long value) {
        return Long.toBinaryString(value);
    }

    //// 1.2 FORMAT ALIASES

    public static String formatHex(int value) {
        return toHexString(value);
    }

    public static String formatBinary(int value) {
        return toBinaryString(value);
    }

    public static String formatHex(long value) {
        return toHexString(value);
    }

    public static String formatBinary(long value) {
        return toBinaryString(value);
    }

    //// 2.1 parse<Primitive>: PARSE NUMBER

    public static byte parseByte(String str) {
        return parseByte(str, DEFAULT_RADIX);
    }

    public static byte parseByte(String str, int radix) {
        return Byte.parseByte(str, radix);
    }

    public static short parseShort(String str) {
        return parseShort(str, DEFAULT_RADIX);
    }

    public static short parseShort(String str, int radix) {
        return Short.parseShort(str, radix);
    }

    public static int parseInt(String str) {
        return parseInt(str, DEFAULT_RADIX);
    }

    public static int parseInt(String str, int radix) {
        return Integer.parseInt(str, radix);
    }

    public static long parseLong(String str) {
        return parseLong(str, DEFAULT_RADIX);
    }

    public static long parseLong(String str, int radix) {
        return Long.parseLong(str, radix);
    }

    public static float parseFloat(String str) {
        return Float.parseFloat(str);
    }

    public static double parseDouble(String str) {
        return Double.parseDouble(str);
    }

    //// 2.2 to<Primitive>

    // byte

    public static byte toByte(String str) {
        return toByte(str, DEFAULT_RADIX, DEFAULT_BYTE);
    }

    public static byte toByte(String str, byte def) {
        return toByte(str, DEFAULT_RADIX, def);
    }

    public static byte toByte(String str, int radix, byte def) {
        if (str == null) {
            return def;
        }
        try {
            return parseByte(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // short

    public static short toShort(String str) {
        return toShort(str, DEFAULT_RADIX, DEFAULT_SHORT);
    }

    public static short toShort(String str, short def) {
        return toShort(str, DEFAULT_RADIX, def);
    }

    public static short toShort(String str, int radix, short def) {
        if (str == null) {
            return def;
        }
        try {
            return parseShort(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // int

    public static int toInt(String str) {
        return toInt(str, DEFAULT_RADIX, DEFAULT_INT);
    }

    public static int toInt(String str, int def) {
        return toInt(str, DEFAULT_RADIX, def);
    }

    public static int toInt(String str, int radix, int def) {
        if (str == null) {
            return def;
        }
        try {
            return parseInt(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // long

    public static long toLong(String str) {
        return toLong(str, DEFAULT_RADIX, DEFAULT_LONG);
    }

    public static long toLong(String str, long def) {
        return toLong(str, DEFAULT_RADIX, def);
    }

    public static long toLong(String str, int radix, long def) {
        if (str == null) {
            return def;
        }
        try {
            return parseLong(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // float

    public static float toFloat(String str) {
        return toFloat(str, DEFAULT_FLOAT);
    }

    public static float toFloat(String str, float def) {
        if (str == null) {
            return def;
        }
        try {
            return parseFloat(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // double

    public static double toDouble(String str) {
        return toDouble(str, DEFAULT_DOUBLE);
    }

    public static double toDouble(String str, double def) {
        if (str == null) {
            return def;
        }
        try {
            return parseDouble(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    //// 2.3 toObject<Type>

    // Byte

    public static Byte toObjectByte(String str) {
        return toObjectByte(str, DEFAULT_RADIX, null);
    }

    public static Byte toObjectByte(String str, Byte def) {
        return toObjectByte(str, DEFAULT_RADIX, def);
    }

    public static Byte toObjectByte(String str, int radix, Byte def) {
        if (str == null) {
            return def;
        }
        try {
            return parseByte(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // Short

    public static Short toObjectShort(String str) {
        return toObjectShort(str, DEFAULT_RADIX, null);
    }

    public static Short toObjectShort(String str, Short def) {
        return toObjectShort(str, DEFAULT_RADIX, def);
    }

    public static Short toObjectShort(String str, int radix, Short def) {
        if (str == null) {
            return def;
        }
        try {
            return parseShort(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // Integer

    public static Integer toObjectInteger(String str) {
        return toObjectInteger(str, DEFAULT_RADIX, null);
    }

    public static Integer toObjectInteger(String str, Integer def) {
        return toObjectInteger(str, DEFAULT_RADIX, def);
    }

    public static Integer toObjectInteger(String str, int radix, Integer def) {
        if (str == null) {
            return def;
        }
        try {
            return parseInt(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }

    }

    // Long

    public static Long toObjectLong(String str) {
        return toObjectLong(str, DEFAULT_RADIX, null);
    }

    public static Long toObjectLong(String str, Long def) {
        return toObjectLong(str, DEFAULT_RADIX, def);
    }

    public static Long toObjectLong(String str, int radix, Long def) {
        if (str == null) {
            return def;
        }
        try {
            return parseLong(str, radix);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // Float

    public static Float toObjectFloat(String str) {
        return toObjectFloat(str, null);
    }

    public static Float toObjectFloat(String str, Float def) {
        if (str == null) {
            return def;
        }
        try {
            return parseFloat(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    // Double

    public static Double toObjectDouble(String str) {
        return toObjectDouble(str, null);
    }

    public static Double toObjectDouble(String str, Double def) {
        if (str == null) {
            return def;
        }
        try {
            return parseDouble(str);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    //// 3.1

    public static BigInteger toBigInteger(Number x) {
        if (x == null) {
            return null;
        }
        if (x instanceof BigInteger) {
            return (BigInteger) x;
        }
        return BigInteger.valueOf(x.longValue());
    }

    public static BigDecimal toBigDecimal(Number x) {
        if (x == null) {
            return null;
        }
        if (x instanceof BigDecimal) {
            return (BigDecimal) x;
        }
        return BigDecimal.valueOf(x.doubleValue());
    }

    //// 4.1 trunc, truncInt, truntDec

    public static int intValue(float value) {
        return (int) value;
    }

    public static long longValue(double value) {
        return (long) value;
    }

    // 23.45 => 23.0
    public static float trunc(float value) {
        return truncInt(value);
    }

    // 23.45 => 23.0
    public static double trunc(double value) {
        return truncInt(value);
    }

    ////

    // 23.45 => 23
    public static int truncInt(float value) {
        return intValue(value);
    }

    // 23.45 => 23
    public static long truncInt(double value) {
        return longValue(value);
    }

    ////

    // 23.45 => 0.45
    public static float truncDec(float value) {
        return value - intValue(value);
    }

    // 23.45 => 0.45
    public static double truncDec(double value) {
        return value - longValue(value);
    }

    /*
     * public static double truncDecInt(double value) { return (long) (value -
     * longValue(value)); }
     */

    //// 5.1

    public static byte defValue(byte def, boolean useDef) {
        return useDef ? def : DEFAULT_BYTE;
    }

    public static char defValue(char def, boolean useDef) {
        return useDef ? def : DEFAULT_CHAR;
    }

    public static short defValue(short def, boolean useDef) {
        return useDef ? def : DEFAULT_SHORT;
    }

    public static int defValue(int def, boolean useDef) {
        return useDef ? def : DEFAULT_INT;
    }

    public static long defValue(long def, boolean useDef) {
        return useDef ? def : DEFAULT_LONG;
    }

    public static float defValue(float def, boolean useDef) {
        return useDef ? def : DEFAULT_FLOAT;
    }

    public static double defValue(double def, boolean useDef) {
        return useDef ? def : DEFAULT_DOUBLE;
    }

    ////

    public static byte randomByte() {
        return NumRandomHelper._randomByte();
    }

    public static char randomChar() {
        return NumRandomHelper._randomChar();
    }

    public static short randomShort() {
        return NumRandomHelper._randomShort();
    }

    // int

    public static int randomInt() {
        return NumRandomHelper._randomInt();
    }

    public static int randomInt(int max) {
        return NumRandomHelper._randomInt(max);
    }

    public static int randomInt(int min, int max) {
        return NumRandomHelper._randomInt(min, max);
    }

    public static int randomInt(Function<Integer, Integer> mapper) {
        return NumRandomHelper._randomInt(mapper);
    }

    public static int randomInt(int max, Function<Integer, Integer> mapper) {
        return NumRandomHelper._randomInt(max, mapper);
    }

    public static int randomInt(int min, int max, Function<Integer, Integer> mapper) {
        return NumRandomHelper._randomInt(min, max, mapper);
    }

    // long

    public static long randomLong() {
        return NumRandomHelper._randomLong();
    }

    public static long randomLong(long max) {
        return NumRandomHelper._randomLong(max);
    }

    public static long randomLong(long min, long max) {
        return NumRandomHelper._randomLong(min, max);
    }

    public static long randomLong(Function<Long, Long> mapper) {
        return NumRandomHelper._randomLong(mapper);
    }

    public static long randomLong(long max, Function<Long, Long> mapper) {
        return NumRandomHelper._randomLong(max, mapper);
    }

    public static long randomLong(long min, long max, Function<Long, Long> mapper) {
        return NumRandomHelper._randomLong(min, max, mapper);
    }

    // float

    public static float randomFloat() {
        return NumRandomHelper._randomFloat();
    }

    public static float randomFloat(float max) {
        return NumRandomHelper._randomFloat(max);
    }

    public static float randomFloat(float min, float max) {
        return NumRandomHelper._randomFloat(min, max);
    }

    public static float randomFloat(Function<Float, Float> mapper) {
        return NumRandomHelper._randomFloat(mapper);
    }

    public static float randomFloat(float max, Function<Float, Float> mapper) {
        return NumRandomHelper._randomFloat(max, mapper);
    }

    public static float randomFloat(float min, float max, Function<Float, Float> mapper) {
        return NumRandomHelper._randomFloat(min, max, mapper);
    }

    //

    public static double randomDouble() {
        return NumRandomHelper._randomDouble();
    }

    public static double randomDouble(double max) {
        return NumRandomHelper._randomDouble(max);
    }

    public static double randomDouble(double min, double max) {
        return NumRandomHelper._randomDouble(min, max);
    }

    public static double randomDouble(Function<Double, Double> mapper) {
        return NumRandomHelper._randomDouble(mapper);
    }

    public static double randomDouble(double max, Function<Double, Double> mapper) {
        return NumRandomHelper._randomDouble(max, mapper);
    }

    public static double randomDouble(double min, double max, Function<Double, Double> mapper) {
        return NumRandomHelper._randomDouble(min, max, mapper);
    }

    //// read: String -> array

    public static byte[] readByteArrayFromString(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new byte[0];
        }
        String[] values = _splitValues(line);
        byte[] result = new byte[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toByte(value);
        }
        return result;

    }

    public static short[] readShortArrayFromString(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new short[0];
        }
        String[] values = _splitValues(line);
        short[] result = new short[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toShort(value);
        }
        return result;
    }

    public static int[] readIntArrayFromString(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new int[0];
        }
        String[] values = _splitValues(line);
        int[] result = new int[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toInt(value);
        }
        return result;
    }

    public static long[] readLongArrayFromString(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new long[0];
        }
        String[] values = _splitValues(line);
        long[] result = new long[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toLong(value);
        }
        return result;
    }

    public static float[] readFloatArrayFromString(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new float[0];
        }
        String[] values = _splitValues(line);
        float[] result = new float[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toFloat(value);
        }
        return result;
    }

    public static double[] readDoubleArrayFromString(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new double[0];
        }
        String[] values = _splitValues(line);
        double[] result = new double[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toDouble(value);
        }
        return result;
    }

    //// write: String -> array -> String

    // byte
    public static String writeByteArrayToString(byte[] array) {
        return writeByteArrayToString(array, null);
    }

    public static String writeByteArrayToString(byte[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_STRING;
        }
        return ArrayLib.toString(array, false, separator);

    }

    // short
    public static String writeShortArrayToString(short[] array) {
        return writeShortArrayToString(array, null);
    }

    public static String writeShortArrayToString(short[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_STRING;
        }
        return ArrayLib.toString(array, false, separator);
    }

    // int
    public static String writeIntArrayToString(int[] array) {
        return writeIntArrayToString(array, null);
    }

    public static String writeIntArrayToString(int[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_STRING;
        }
        return ArrayLib.toString(array, false, separator);
    }

    // long
    public static String writeLongArrayToString(long[] array) {
        return writeLongArrayToString(array, null);
    }

    public static String writeLongArrayToString(long[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_STRING;
        }
        return ArrayLib.toString(array, false, separator);
    }

    // float
    public static String writeFloatArrayToString(float[] array) {
        return writeFloatArrayToString(array);
    }

    public static String writeFloatArrayToString(float[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_STRING;
        }
        return ArrayLib.toString(array, false, separator);
    }

    // double
    public static String writeDoubleArrayToString(double[] array) {
        return writeDoubleArrayToString(array, null);
    }

    public static String writeDoubleArrayToString(double[] array, String separator) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_STRING;
        }
        return ArrayLib.toString(array, false, separator);
    }

    //// read: Array -> String

    public static Byte[] readObjectByteArray(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new Byte[0];
        }
        String[] values = _splitValues(line);
        Byte[] result = new Byte[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toObjectByte(value);
        }
        return result;
    }

    public static Short[] readObjectShortArray(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new Short[0];
        }
        String[] values = _splitValues(line);
        Short[] result = new Short[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toObjectShort(value);
        }
        return result;
    }

    public static Integer[] readObjectIntegerArray(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new Integer[0];
        }
        String[] values = _splitValues(line);
        Integer[] result = new Integer[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toObjectInteger(value);
        }
        return result;
    }

    public static Long[] readObjectLongArray(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new Long[0];
        }
        String[] values = _splitValues(line);
        Long[] result = new Long[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toObjectLong(value);
        }
        return result;
    }

    public static Float[] readObjectFloatArray(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new Float[0];
        }
        String[] values = _splitValues(line);
        Float[] result = new Float[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toObjectFloat(value);
        }
        return result;
    }

    public static Double[] readObjectDoubleArray(String line) {
        if (line == null) {
            return null;
        }
        if (line.isEmpty()) {
            return new Double[0];
        }
        String[] values = _splitValues(line);
        Double[] result = new Double[values.length];
        String value = null;
        for (int i = 0; i < values.length; i++) {
            value = _trimValue(values[i]);
            result[i] = toObjectDouble(value);
        }
        return result;
    }

    //// arab/roman

    public static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            return ""; // RangeException
        }
        StringBuilder buf = new StringBuilder();
        int i = ARAB.length - 1;

        while (number > 0) {
            if (number >= ARAB[i]) {
                buf.append(ROMAN[i]);
                number -= ARAB[i];
            } else {
                i--;
            }
        }
        return buf.toString();
    }

    public static boolean isValidRomanFormat(String str) {
        int code = _validateRomanFormat(str);
        return code == 1;
    }

    public static String validateRomanFormat(String str) {
        int code = _validateRomanFormat(str);
        if (code == 1) {
            return "";
        }
        if (code == 0) {
            return "Empty roman number";
        }
        if (code == -1) {
            return "Invalid roman format: [I, V, X, L, C, D, M] only";
        }
        if (code == -2) {
            return "Invalid roman format: [V, L, D] count = 1";
        }
        if (code == -3) {
            return "Invalid roman format: [I, X, C, M] count = 1..3";
        }

        return "Invalid roman number";
    }

    public static boolean isValidRoman(String str) {
        if (!isValidRomanFormat(str)) {
            return false;
        }
        int number = _parseRoman(str);
        return number > 0;
    }

    public static String validateRoman(String str) {
        String error = validateRomanFormat(str);
        if (error != null && !error.isEmpty()) {
            return error;
        }
        int number = _parseRoman(str);
        if (number == 0) {
            return "Invalid roman number structure";
        }
        return "";
    }

    /**
     * 
     * @param str
     * @return validation result (code)
     */
    private static int _validateRomanFormat(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        char prev = 0;
        char ch = 0;
        char[] array = str.toCharArray();
        int count = 0;

        // boolean err1 = false;
        // boolean err2 = false;
        // boolean err3 = false;

        for (int i = 0; i < array.length; i++) {
            ch = array[i];
            if (ch != 'V' && ch != 'L' && ch != 'D' && ch != 'I' && ch != 'X' && ch != 'C' && ch != 'M') {
                // err1 = true;
                return -1;
            }
            if (ch == prev) {
                count++;

                if ((ch == 'V' || ch == 'L' || ch == 'D') && count > 1) {
                    // err2 = true;
                    return -2;
                }

                if ((ch == 'I' || ch == 'X' || ch == 'C' || ch == 'M') && count > 3) {
                    // err3 = true;
                    return -3;
                }

            } else {
                prev = ch;
                count = 1;
            }
        }

        return 1;
    }

    public static int parseRoman(String str) {
        if (str == null || str.isEmpty()) {
            // Empty Error
            throw new NumberFormatException("Empty roman number");
        }
        str = str.toUpperCase();

        // Validation format only:
        // =======================
        // V, L, D = 1
        // I, X, C, M = 3

        String error = validateRomanFormat(str);

        if (error != null && !error.isEmpty()) {
            // Format Error
            throw new NumberFormatException(error);
        }

        int number = _parseRoman(str);
        if (number == 0) {
            // Structure Error
            throw new NumberFormatException("Invalid roman number structure");
        }
        return number;
    }

    public static int toArab(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        str = str.toUpperCase();

        // Validation format only:
        // =======================
        // V, L, D = 1
        // I, X, C, M = 3

        if (_validateRomanFormat(str) != 1) {
            return 0;
        }

        int result = _parseRoman(str);
        return result;
    }

    private static int _parseRoman(String str) {

        int result = 0;
        int i = ARAB.length - 1;
        int pos = 0;
        int end = 0;

        while (i >= 0 && pos < str.length()) {
            end = pos + ROMAN[i].length();
            if (end <= str.length() && str.substring(pos, end).equals(ROMAN[i])) {
                result += ARAB[i];
                // pos += ROMAN[i].length();
                pos = end;
            } else {
                i--;
            }
        }

        if (end < str.length()) {
            return 0; // ParseException: Structure
        }

        return result;
    }

    ////

    private static String _trimValue(String value) {
        return value == null ? null : value.trim();
    }

    private static String[] _splitValues(String line) {
        if (line == null) {
            return null;
        }
        String[] values = line.split(",");
        return values;
    }
    
}
