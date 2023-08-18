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

package plazma.kernel.lib.array;

public class ArrayStringHelper {

    private ArrayStringHelper() {
    }

    ////

    static String _toString(byte[] array) {
        return _toString(array, true, null);
    }

    static String _toString(byte[] array, boolean hasQuote) {
        return _toString(array, hasQuote, null);
    }

    static String _toString(byte[] array, String separator) {
        return _toString(array, false, null);
    }

    static String _toString(byte[] array, boolean hasQuote, String separator) {
        String emptyString = _toEmptyString(array == null, array != null && array.length == 0, hasQuote);
        if (emptyString != null) {
            return emptyString;
        }
        StringBuilder buf = new StringBuilder();
        _addStartQuote(buf, hasQuote);
        String separatorStr = _normalizeSeparator(separator);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separatorStr);
            }
            buf.append(array[i]);
        }
        _addEndQuote(buf, hasQuote);
        return buf.toString();
    }

    //

    static String _toString(short[] array) {
        return _toString(array, true, null);
    }

    static String _toString(short[] array, boolean hasQuote) {
        return _toString(array, hasQuote, null);
    }

    static String _toString(short[] array, String separator) {
        return _toString(array, false, null);
    }

    static String _toString(short[] array, boolean hasQuote, String separator) {
        String emptyString = _toEmptyString(array == null, array != null && array.length == 0, hasQuote);
        if (emptyString != null) {
            return emptyString;
        }
        StringBuilder buf = new StringBuilder();
        _addStartQuote(buf, hasQuote);
        String separatorStr = _normalizeSeparator(separator);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separatorStr);
            }
            buf.append(array[i]);
        }
        _addEndQuote(buf, hasQuote);
        return buf.toString();
    }

    //

    static String _toString(int[] array) {
        return _toString(array, true, null);
    }

    static String _toString(int[] array, boolean hasQuote) {
        return _toString(array, hasQuote, null);
    }

    static String _toString(int[] array, String separator) {
        return _toString(array, false, null);
    }

    static String _toString(int[] array, boolean hasQuote, String separator) {
        String emptyString = _toEmptyString(array == null, array != null && array.length == 0, hasQuote);
        if (emptyString != null) {
            return emptyString;
        }
        StringBuilder buf = new StringBuilder();
        _addStartQuote(buf, hasQuote);
        String separatorStr = _normalizeSeparator(separator);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separatorStr);
            }
            buf.append(array[i]);
        }
        _addEndQuote(buf, hasQuote);
        return buf.toString();
    }

    //

    static String _toString(long[] array) {
        return _toString(array, true, null);
    }

    static String _toString(long[] array, boolean hasQuote) {
        return _toString(array, hasQuote, null);
    }

    static String _toString(long[] array, String separator) {
        return _toString(array, false, null);
    }

    static String _toString(long[] array, boolean hasQuote, String separator) {
        String emptyString = _toEmptyString(array == null, array != null && array.length == 0, hasQuote);
        if (emptyString != null) {
            return emptyString;
        }
        StringBuilder buf = new StringBuilder();
        _addStartQuote(buf, hasQuote);
        String separatorStr = _normalizeSeparator(separator);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separatorStr);
            }
            buf.append(array[i]);
        }
        _addEndQuote(buf, hasQuote);
        return buf.toString();
    }

    //

    static String _toString(float[] array) {
        return _toString(array, true, null);
    }

    static String _toString(float[] array, boolean hasQuote) {
        return _toString(array, hasQuote, null);
    }

    static String _toString(float[] array, String separator) {
        return _toString(array, false, null);
    }

    static String _toString(float[] array, boolean hasQuote, String separator) {
        String emptyString = _toEmptyString(array == null, array != null && array.length == 0, hasQuote);
        if (emptyString != null) {
            return emptyString;
        }
        StringBuilder buf = new StringBuilder();
        _addStartQuote(buf, hasQuote);
        String separatorStr = _normalizeSeparator(separator);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separatorStr);
            }
            buf.append(array[i]);
        }
        _addEndQuote(buf, hasQuote);
        return buf.toString();
    }

    //

    static String _toString(double[] array) {
        return _toString(array, true, null);
    }

    static String _toString(double[] array, boolean hasQuote) {
        return _toString(array, hasQuote, null);
    }

    static String _toString(double[] array, String separator) {
        return _toString(array, false, null);
    }

    static String _toString(double[] array, boolean hasQuote, String separator) {
        String emptyString = _toEmptyString(array == null, array != null && array.length == 0, hasQuote);
        if (emptyString != null) {
            return emptyString;
        }
        StringBuilder buf = new StringBuilder();
        _addStartQuote(buf, hasQuote);
        String separatorStr = _normalizeSeparator(separator);
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                buf.append(separatorStr);
            }
            buf.append(array[i]);
        }
        _addEndQuote(buf, hasQuote);
        return buf.toString();
    }

    ////

    private static String _toEmptyString(boolean isNull, boolean isEmpty, boolean hasQuote) {
        if (isNull) {
            return "null";
        }
        if (isEmpty) {
            return hasQuote ? "[]" : "";
        }
        return null;
    }

    private static void _addStartQuote(StringBuilder buf, boolean hasQuote) {
        _addQuote(buf, hasQuote ? "[" : null);
    }

    private static void _addEndQuote(StringBuilder buf, boolean hasQuote) {
        _addQuote(buf, hasQuote ? "]" : null);
    }

    private static void _addQuote(StringBuilder buf, String quote) {
        if (quote == null) {
            return;
        }
        buf.append(quote);
    }

    private static String _normalizeSeparator(String separator) {
        return separator == null ? ", " : separator;
    }

}
