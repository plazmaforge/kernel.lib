package plazma.lib.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import plazma.lib.ArithmeticContext;
import plazma.lib.ArithmeticEnvironment;
import plazma.lib.clt.CltLib;
import plazma.lib.num.NumLib;
import plazma.lib.obj.ObjLib;

public class ArrayPrimitiveTemplate {

    public static final boolean DEFAULT_BOOLEAN = ObjLib.DEFAULT_BOOLEAN;

    public static final byte DEFAULT_BYTE = ObjLib.DEFAULT_BYTE;

    public static final char DEFAULT_CHAR = ObjLib.DEFAULT_CHAR;

    public static final short DEFAULT_SHORT = ObjLib.DEFAULT_SHORT;

    public static final int DEFAULT_INT = ObjLib.DEFAULT_INT;

    public static final long DEFAULT_LONG = ObjLib.DEFAULT_LONG;

    public static final float DEFAULT_FLOAT = ObjLib.DEFAULT_FLOAT;

    public static final double DEFAULT_DOUBLE = ObjLib.DEFAULT_DOUBLE;

    //

    public static final boolean[] EMPTY_BOOLEAN_ARRAY = ObjLib.EMPTY_BOOLEAN_ARRAY;

    public static final byte[] EMPTY_BYTE_ARRAY = ObjLib.EMPTY_BYTE_ARRAY;

    public static final char[] EMPTY_CHAR_ARRAY = ObjLib.EMPTY_CHAR_ARRAY;

    public static final short[] EMPTY_SHORT_ARRAY = ObjLib.EMPTY_SHORT_ARRAY;

    public static final int[] EMPTY_INT_ARRAY = ObjLib.EMPTY_INT_ARRAY;

    public static final long[] EMPTY_LONG_ARRAY = ObjLib.EMPTY_LONG_ARRAY;

    public static final float[] EMPTY_FLOAT_ARRAY = ObjLib.EMPTY_FLOAT_ARRAY;

    public static final double[] EMPTY_DOUBLE_ARRAY = ObjLib.EMPTY_DOUBLE_ARRAY;

    private ArrayPrimitiveTemplate() {
        super();
    }

    public static ArithmeticContext getContext() {
        return ArithmeticEnvironment.getContext();
    }

    private static ArithmeticContext getContext(ArithmeticContext context) {
        return context == null ? getContext() : context;
    }

    // BODY:START

    static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    static boolean equals(int[] array1, int[] array2) {
        return Arrays.equals(array1, array2);
    }

    static boolean contains(int[] array, int value) {
        if (array == null) {
            return false;
        }
        int length = array.length;
        if (length == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    static int _findFirst(int[] array, Predicate<Integer> filter) {
        if (array == null) {
            return DEFAULT_INT;
        }
        int length = array.length;
        if (length == 0) {
            return DEFAULT_INT;
        }
        if (filter == null) {
            return DEFAULT_INT;
        }
        for (int i = 0; i < length; i++) {
            if (filter.test(array[i])) {
                return array[i];
            }
        }
        return DEFAULT_INT;
    }

    static int _findLast(int[] array, Predicate<Integer> filter) {
        if (array == null) {
            return DEFAULT_INT;
        }
        int length = array.length;
        if (length == 0) {
            return DEFAULT_INT;
        }
        if (filter == null) {
            return DEFAULT_INT;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (filter.test(array[i])) {
                return array[i];
            }
        }
        return DEFAULT_INT;
    }

    static int[] _findAll(int[] array, Predicate<Integer> filter) {
        return _filter(array, filter);
    }

    static int _size(int[] array) {
        return array == null ? 0 : array.length;
    }

    static int _count(int[] array) {
        return _size(array);
    }

    static int _count(int[] array, int value, boolean eq) {
        if (array == null) {
            return 0;
        }
        int length = array.length;
        if (length == 0) {
            return 0;
        }
        int count = 0;
        boolean compare = false;
        for (int i = 0; i < length; i++) {
            compare = array[i] == value;
            if ((eq && compare) || (!eq && !compare)) {
                count++;
            }
        }
        return count;
    }

    static void _reverse(int[] array) {
        // no reverse for length = 0 or 1
        if (array == null || array.length < 2) {
            return;
        }
        int len = array.length;
        int count = len / 2;
        int e;
        int j = 0;
        for (int i = 0; i < count; i++) {
            j = len - 1 - i;

            // swap: i <-> j
            e = array[i];
            array[i] = array[j];
            array[j] = e;
        }
    }

    static int _min(int[] array, int def, boolean useDef) {
        if (array == null) {
            return defValue(def, useDef);
        }
        int length = array.length;
        if (length == 0) {
            return defValue(def, useDef);
        }
        int result = defValue(def, useDef);
        for (int i = 0; i < length; i++) {
            int next = array[i];

            if (i == 0 && !useDef) {
                result = next;
                continue;
            }

            if (next < result) {
                result = next;
            }
        }
        return result;
    }

    static int _max(int[] array, int def, boolean useDef) {
        if (array == null) {
            return defValue(def, useDef);
        }
        int length = array.length;
        if (length == 0) {
            return defValue(def, useDef);
        }
        int result = defValue(def, useDef);
        for (int i = 0; i < length; i++) {
            int next = array[i];

            if (i == 0 && !useDef) {
                result = next;
                continue;
            }

            if (next > result) {
                result = next;
            }
        }
        return result;
    }

    static int[] _copy(int[] original) {
        if (original == null) {
            return null;
        }
        return _copy(original, original.length);
    }

    static int[] _copy(int[] original, int length) {
        if (original == null) {
            return null;
        }
        return Arrays.copyOf(original, length);
    }

    static int[] _copy(int[] original, int fromIndex, int length) {
        if (original == null) {
            return null;
        }

        // fromIndex range
        if (checkIndexOut(fromIndex, original.length)) {
            return null;
        }
        return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    }

    static int[] _copyRange(int[] original, int fromIndex, int toIndex) {
        if (original == null) {
            return null;
        }

        // TODO: check IndexMode: checkIndex
        // fromIndex range
        if (checkIndexOut(fromIndex, original.length)) {
            return null;
        }

        // TODO: check IndexMode
        // fromIndex, toIndex
        if (toIndex < fromIndex) {
            return null;
        }
        return Arrays.copyOfRange(original, fromIndex, toIndex);
    }

    static int[] _add(int[] x, int[] y) {
        // x + y
        return _add(x, y, null);
    }

    static int[] _add(int[] x, int[] y, ArithmeticContext context) {
        // x + y
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNullArray(x, y, c)) {
                return null;
            }
            int[] r = (x == null) ? y : x; // add
            return _copy(r);
        }
        int[] r = _copy(x, x.length + y.length);
        _arraycopy(y, r, 0, x.length, y.length);
        return r;
    }

    static int[] _sub(int[] x, int[] y) {
        // x - y
        return _sub(x, y, null);
    }

    static int[] _sub(int[] x, int[] y, ArithmeticContext context) {
        // x - y
        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNullArray(x, y, c)) {
                return null;
            }

            // null - null = null
            // null - {1, 4, 6} = null
            // {1, 4, 6} - null = {1, 4, 6}

            int[] r = (x == null) ? null : _copy(x); // sub
            return r;
        }
        List<Integer> r = CltLib.toList(x); // TODO: maybe own _toList(array)
        List<Integer> yr = CltLib.toList(y);
        r.removeAll(yr);
        return _toPrimitiveIntArray(r);
    }

    static int[] _union(int[] x, int[] y) {
        return _union(x, y, null);
    }

    static int[] _union(int[] x, int[] y, ArithmeticContext context) {
        // add
        int[] result = _add(x, y, context);
        if (result == null) {
            return null;
        }

        // distinct
        Set<Integer> set = CltLib.toSet(result);
        return _toPrimitiveIntArray(set);
    }

    static int[] _intersection(int[] x, int[] y) {
        return _intersection(x, y, null);
    }

    static int[] _intersection(int[] x, int[] y, ArithmeticContext context) {

        if (x == null || y == null) {
            ArithmeticContext c = getContext(context);
            if (checkNullArray(x, y, c)) {
                return null;
            }
            int[] r = (x == null) ? y : x;
            return _copy(r);
        }

        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < x.length; i++) {
            boolean found = false;
            for (int j = 0; j < y.length; j++) {
                if (x[i] == y[j]) {
                    found = true;
                    break;
                }
            }

            if (found) {
                set.add(x[i]);
            }
        }
        return _toPrimitiveIntArray(set);
    }

    static void _sort(int[] array) {
        if (array == null) {
            return;
        }
        Arrays.sort(array);
    }

    static void _sort(int[] array, int fromIndex, int toIndex) {
        if (array == null) {
            return;
        }
        Arrays.sort(array, fromIndex, toIndex);
    }

    static int[] _filter(int[] array, Predicate<Integer> filter) {
        if (array == null) {
            return null;
        }
        // no filter - return all
        if (filter == null) {
            return Arrays.copyOf(array, array.length);
        }

        List<Integer> list = new ArrayList<Integer>();
        Integer e = null;
        for (int i = 0; i < array.length; i++) {
            e = array[i];
            if (filter.test(e)) {
                list.add(e);
            }
        }
        return _toPrimitiveIntArray(list);
    }

    static void _fill(int[] array, int value) {
        if (array == null) {
            return;
        }
        Arrays.fill(array, value);
    }

    static void _fill(int[] array, int value, int fromIndex, int toIndex) {
        if (array == null) {
            return;
        }
        Arrays.fill(array, fromIndex, toIndex, value);
    }

    static boolean _replaceAll(int[] array, int oldValue, int newValue) {
        if (array == null) {
            return false;
        }
        boolean result = false;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int e = array[i];
            if (e == oldValue) {
                array[i] = newValue;
                result = true;
            }
        }
        return result;
    }

    static boolean[] _replaceAll(int[] array, int[] oldValues, int[] newValues) {
        if (array == null || oldValues == null || newValues == null) {
            // TODO: Exception
            return new boolean[0];

        }
        int size = Math.min(oldValues.length, newValues.length);
        if (size == 0) {
            return new boolean[0];
        }
        int oldValue;
        int newValue;
        boolean[] result = new boolean[size];
        for (int i = 0; i < size; i++) {
            oldValue = oldValues[i];
            newValue = newValues[i];
            if (_replaceAll(array, oldValue, newValue)) {
                result[i] = true;
            }
        }
        return result;
    }

    static int[] _resize(int[] array, int newLength) {
        if (array == null) {
            return null;
        }
        checkResizeLength(array.length, newLength);
        return _resizeArray(array, newLength);
    }

    static int[] _resize(int[] array, int newLength, int element) {
        if (array == null) {
            return null;
        }
        int[] newArray = _resize(array, newLength);
        if (newLength > array.length) {
            for (int i = array.length; i < newLength; i++) {
                newArray[i] = element;
            }
        }
        return newArray;
    }

    static int[] _trunc(int[] array, int newLength) {
        if (array == null) {
            return null;
        }
        checkTruncLength(array.length, newLength);
        return _resizeArray(array, newLength);
    }

    static int[] _resizeArray(int[] array, int newLength) {
        return Arrays.copyOf(array, newLength);
    }

    static int[] _distinct(int[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        Set<Integer> set = CltLib.toSet(array);
        return _toPrimitiveIntArray(set);
    }

    static int[] _asArray(int... array) {
        if (array == null) {
            return null;
        }
        int size = array.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    static int[] _toArray(Integer[] array) {
        if (array == null) {
            return null;
        }
        int size = array.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            Integer e = array[i];
            result[i] = e == null ? DEFAULT_INT : e;
        }
        return result;
    }

    static Integer[] _toArray(int[] array) {
        if (array == null) {
            return null;
        }
        int size = array.length;
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[i];
        }
        return result;
    }

    static int[] _toPrimitiveIntArray(Collection<Integer> collection) {
        if (collection == null) {
            return null;
        }
        if (collection.isEmpty()) {
            return EMPTY_INT_ARRAY;
        }
        int size = collection.size();
        int[] array = new int[size];
        Iterator<Integer> itr = collection.iterator();
        for (int i = 0; i < size; i++) {
            array[i] = itr.next();
        }
        return array;
    }

    // BODY:END

    private static boolean defValue(boolean def, boolean useDef) {
        return useDef ? def : false;
    }

    private static byte defValue(byte def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static char defValue(char def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static short defValue(short def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static int defValue(int def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static long defValue(long def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static float defValue(float def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    private static double defValue(double def, boolean useDef) {
        return NumLib.defValue(def, useDef);
    }

    //

    private static void checkTruncLength(int oldLength, int newLength) {
        if (newLength < 0) {
            throw new IllegalArgumentException("newLength < 0: " + newLength);
        }
        if (newLength > oldLength) {
            throw new IllegalArgumentException("newLength > oldLength: " + newLength + " > " + oldLength);
        }
    }

    private static void checkResizeLength(int oldLength, int newLength) {
        if (newLength < 0) {
            throw new IllegalArgumentException("newLength < 0: " + newLength);
        }
    }

    //

    private static boolean checkNull(Object x, Object y, ArithmeticContext context, String message) {
        return ArithmeticEnvironment.checkNull(x, y, context, message);
    }

    // private static <T> boolean checkNull(T[] x, T[] y, ArithmeticContext context)
    // {
    // return checkNull(x, y, context, "Array must be not null");
    // }

    private static <T> boolean checkNullArray(Object x, Object y, ArithmeticContext context) {
        return checkNull(x, y, context, "Array must be not null");
    }

    public static boolean checkIndexOut(int index, int fromIndex, int length) {
        return ArithmeticEnvironment.checkIndexOut(index, fromIndex, length);
    }

    public static boolean checkIndexOut(int index, int length) {
        return ArithmeticEnvironment.checkIndexOut(index, length);
    }

    // toIndex - exclusive!
    public static boolean checkIndexOutRange(int index, int fromIndex, int toIndex) {
        return ArithmeticEnvironment.checkIndexOutRange(index, fromIndex, toIndex);
    }

    private static void _arraycopy(Object from, Object to, int fromPos, int toPos, int length) {
        System.arraycopy(from, fromPos, to, toPos, length);
    }

}
