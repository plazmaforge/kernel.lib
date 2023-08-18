package plazma.kernel.lib.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import plazma.kernel.lib.ArithmeticContext;
import plazma.kernel.lib.ArithmeticEnvironment;
import plazma.kernel.lib.collection.CollectionLib;
import plazma.kernel.lib.num.NumLib;
import plazma.kernel.lib.obj.ObjLib;

public class ArrayPrimitiveHelper {

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
    

    private ArrayPrimitiveHelper() {
	super();
    }

    public static ArithmeticContext getContext() {
	return ArithmeticEnvironment.getContext();
    }
    
    private static ArithmeticContext getContext(ArithmeticContext context) {
 	return context == null ? getContext() : context;
    }
    

    static boolean isEmpty(boolean[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean isEmpty(byte[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean isEmpty(char[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean isEmpty(short[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean isEmpty(int[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean isEmpty(long[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean isEmpty(float[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean isEmpty(double[] array) {
	return array == null || array.length == 0;
    }
    
    static boolean equals(boolean[] array1, boolean[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean equals(byte[] array1, byte[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean equals(char[] array1, char[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean equals(short[] array1, short[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean equals(int[] array1, int[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean equals(long[] array1, long[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean equals(float[] array1, float[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean equals(double[] array1, double[] array2) {
	return Arrays.equals(array1, array2);
    }
    
    static boolean contains(boolean[] array, boolean value) {
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
    
    static boolean contains(byte[] array, byte value) {
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
    
    static boolean contains(char[] array, char value) {
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
    
    static boolean contains(short[] array, short value) {
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
    
    static boolean contains(long[] array, long value) {
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
    
    static boolean contains(float[] array, float value) {
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
    
    static boolean contains(double[] array, double value) {
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
    
    static boolean _findFirst(boolean[] array, Predicate<Boolean> filter) {
	if (array == null) {
	    return DEFAULT_BOOLEAN;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_BOOLEAN;
	}
	if (filter == null) {
	    return DEFAULT_BOOLEAN;
	}
	for (int i = 0; i < length; i++) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_BOOLEAN;
    }
    
    static byte _findFirst(byte[] array, Predicate<Byte> filter) {
	if (array == null) {
	    return DEFAULT_BYTE;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_BYTE;
	}
	if (filter == null) {
	    return DEFAULT_BYTE;
	}
	for (int i = 0; i < length; i++) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_BYTE;
    }
    
    static char _findFirst(char[] array, Predicate<Character> filter) {
	if (array == null) {
	    return DEFAULT_CHAR;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_CHAR;
	}
	if (filter == null) {
	    return DEFAULT_CHAR;
	}
	for (int i = 0; i < length; i++) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_CHAR;
    }
    
    static short _findFirst(short[] array, Predicate<Short> filter) {
	if (array == null) {
	    return DEFAULT_SHORT;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_SHORT;
	}
	if (filter == null) {
	    return DEFAULT_SHORT;
	}
	for (int i = 0; i < length; i++) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_SHORT;
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
    
    static long _findFirst(long[] array, Predicate<Long> filter) {
	if (array == null) {
	    return DEFAULT_LONG;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_LONG;
	}
	if (filter == null) {
	    return DEFAULT_LONG;
	}
	for (int i = 0; i < length; i++) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_LONG;
    }
    
    static float _findFirst(float[] array, Predicate<Float> filter) {
	if (array == null) {
	    return DEFAULT_FLOAT;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_FLOAT;
	}
	if (filter == null) {
	    return DEFAULT_FLOAT;
	}
	for (int i = 0; i < length; i++) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_FLOAT;
    }
    
    static double _findFirst(double[] array, Predicate<Double> filter) {
	if (array == null) {
	    return DEFAULT_DOUBLE;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_DOUBLE;
	}
	if (filter == null) {
	    return DEFAULT_DOUBLE;
	}
	for (int i = 0; i < length; i++) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_DOUBLE;
    }
    
    static boolean _findLast(boolean[] array, Predicate<Boolean> filter) {
	if (array == null) {
	    return DEFAULT_BOOLEAN;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_BOOLEAN;
	}
	if (filter == null) {
	    return DEFAULT_BOOLEAN;
	}
	for (int i = length - 1; i >= 0; i--) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_BOOLEAN;
    }   
    
    static byte _findLast(byte[] array, Predicate<Byte> filter) {
	if (array == null) {
	    return DEFAULT_BYTE;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_BYTE;
	}
	if (filter == null) {
	    return DEFAULT_BYTE;
	}
	for (int i = length - 1; i >= 0; i--) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_BYTE;
    }   
    
    static char _findLast(char[] array, Predicate<Character> filter) {
	if (array == null) {
	    return DEFAULT_CHAR;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_CHAR;
	}
	if (filter == null) {
	    return DEFAULT_CHAR;
	}
	for (int i = length - 1; i >= 0; i--) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_CHAR;
    }   
    
    static short _findLast(short[] array, Predicate<Short> filter) {
	if (array == null) {
	    return DEFAULT_SHORT;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_SHORT;
	}
	if (filter == null) {
	    return DEFAULT_SHORT;
	}
	for (int i = length - 1; i >= 0; i--) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_SHORT;
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
    
    static long _findLast(long[] array, Predicate<Long> filter) {
	if (array == null) {
	    return DEFAULT_LONG;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_LONG;
	}
	if (filter == null) {
	    return DEFAULT_LONG;
	}
	for (int i = length - 1; i >= 0; i--) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_LONG;
    }   
    
    static float _findLast(float[] array, Predicate<Float> filter) {
	if (array == null) {
	    return DEFAULT_FLOAT;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_FLOAT;
	}
	if (filter == null) {
	    return DEFAULT_FLOAT;
	}
	for (int i = length - 1; i >= 0; i--) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_FLOAT;
    }   
    
    static double _findLast(double[] array, Predicate<Double> filter) {
	if (array == null) {
	    return DEFAULT_DOUBLE;
	}
	int length = array.length;
	if (length == 0) {
	    return DEFAULT_DOUBLE;
	}
	if (filter == null) {
	    return DEFAULT_DOUBLE;
	}
	for (int i = length - 1; i >= 0; i--) {
	    if (filter.test(array[i])) {
		return array[i];
	    }
	}
	return DEFAULT_DOUBLE;
    }   
    
    static boolean[] _findAll(boolean[] array, Predicate<Boolean> filter) {
	return _filter(array, filter);
    }
    
    static byte[] _findAll(byte[] array, Predicate<Byte> filter) {
	return _filter(array, filter);
    }
    
    static char[] _findAll(char[] array, Predicate<Character> filter) {
	return _filter(array, filter);
    }
    
    static short[] _findAll(short[] array, Predicate<Short> filter) {
	return _filter(array, filter);
    }
    
    static int[] _findAll(int[] array, Predicate<Integer> filter) {
	return _filter(array, filter);
    }
    
    static long[] _findAll(long[] array, Predicate<Long> filter) {
	return _filter(array, filter);
    }
    
    static float[] _findAll(float[] array, Predicate<Float> filter) {
	return _filter(array, filter);
    }
    
    static double[] _findAll(double[] array, Predicate<Double> filter) {
	return _filter(array, filter);
    }
    
    static int _size(boolean[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _size(byte[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _size(char[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _size(short[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _size(int[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _size(long[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _size(float[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _size(double[] array) {
	return array == null ? 0 : array.length;
    }
    
    static int _count(boolean[] array) {
   	return _size(array);
    }  
    
    static int _count(byte[] array) {
   	return _size(array);
    }  
    
    static int _count(char[] array) {
   	return _size(array);
    }  
    
    static int _count(short[] array) {
   	return _size(array);
    }  
    
    static int _count(int[] array) {
   	return _size(array);
    }  
    
    static int _count(long[] array) {
   	return _size(array);
    }  
    
    static int _count(float[] array) {
   	return _size(array);
    }  
    
    static int _count(double[] array) {
   	return _size(array);
    }  
    
    static int _count(boolean[] array, boolean value, boolean eq) {
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
    
    static int _count(byte[] array, byte value, boolean eq) {
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
    
    static int _count(char[] array, char value, boolean eq) {
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
    
    static int _count(short[] array, short value, boolean eq) {
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
    
    static int _count(long[] array, long value, boolean eq) {
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
    
    static int _count(float[] array, float value, boolean eq) {
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
    
    static int _count(double[] array, double value, boolean eq) {
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
    
    static void _reverse(boolean[] array) {
   	// no reverse for length = 0 or 1
   	if (array == null || array.length < 2) {
   	    return;
   	}
   	int len = array.length;
   	int count = len / 2;
   	boolean e;
   	int j = 0;
   	for (int i = 0; i < count; i++) {
   	    j = len - 1 - i;

   	    // swap: i <-> j
   	    e = array[i];
   	    array[i] = array[j];
   	    array[j] = e;
   	}
    }
    
    static void _reverse(byte[] array) {
   	// no reverse for length = 0 or 1
   	if (array == null || array.length < 2) {
   	    return;
   	}
   	int len = array.length;
   	int count = len / 2;
   	byte e;
   	int j = 0;
   	for (int i = 0; i < count; i++) {
   	    j = len - 1 - i;

   	    // swap: i <-> j
   	    e = array[i];
   	    array[i] = array[j];
   	    array[j] = e;
   	}
    }
    
    static void _reverse(char[] array) {
   	// no reverse for length = 0 or 1
   	if (array == null || array.length < 2) {
   	    return;
   	}
   	int len = array.length;
   	int count = len / 2;
   	char e;
   	int j = 0;
   	for (int i = 0; i < count; i++) {
   	    j = len - 1 - i;

   	    // swap: i <-> j
   	    e = array[i];
   	    array[i] = array[j];
   	    array[j] = e;
   	}
    }
    
    static void _reverse(short[] array) {
   	// no reverse for length = 0 or 1
   	if (array == null || array.length < 2) {
   	    return;
   	}
   	int len = array.length;
   	int count = len / 2;
   	short e;
   	int j = 0;
   	for (int i = 0; i < count; i++) {
   	    j = len - 1 - i;

   	    // swap: i <-> j
   	    e = array[i];
   	    array[i] = array[j];
   	    array[j] = e;
   	}
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
    
    static void _reverse(long[] array) {
   	// no reverse for length = 0 or 1
   	if (array == null || array.length < 2) {
   	    return;
   	}
   	int len = array.length;
   	int count = len / 2;
   	long e;
   	int j = 0;
   	for (int i = 0; i < count; i++) {
   	    j = len - 1 - i;

   	    // swap: i <-> j
   	    e = array[i];
   	    array[i] = array[j];
   	    array[j] = e;
   	}
    }
    
    static void _reverse(float[] array) {
   	// no reverse for length = 0 or 1
   	if (array == null || array.length < 2) {
   	    return;
   	}
   	int len = array.length;
   	int count = len / 2;
   	float e;
   	int j = 0;
   	for (int i = 0; i < count; i++) {
   	    j = len - 1 - i;

   	    // swap: i <-> j
   	    e = array[i];
   	    array[i] = array[j];
   	    array[j] = e;
   	}
    }
    
    static void _reverse(double[] array) {
   	// no reverse for length = 0 or 1
   	if (array == null || array.length < 2) {
   	    return;
   	}
   	int len = array.length;
   	int count = len / 2;
   	double e;
   	int j = 0;
   	for (int i = 0; i < count; i++) {
   	    j = len - 1 - i;

   	    // swap: i <-> j
   	    e = array[i];
   	    array[i] = array[j];
   	    array[j] = e;
   	}
    }
    
    static boolean _min(boolean[] array, boolean def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	boolean result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    boolean next = array[i];

	    if (i == 0 && !useDef) {
		result = next;
		continue;
	    }

	    if (!next) {
		result = next;
	    }
	}
	return result;
    }  
    
    static byte _min(byte[] array, byte def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	byte result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    byte next = array[i];

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
    
    static char _min(char[] array, char def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	char result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    char next = array[i];

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
    
    static short _min(short[] array, short def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	short result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    short next = array[i];

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
    
    static long _min(long[] array, long def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	long result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    long next = array[i];

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
    
    static float _min(float[] array, float def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	float result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    float next = array[i];

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
    
    static double _min(double[] array, double def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	double result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    double next = array[i];

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
    
    static boolean _max(boolean[] array, boolean def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	boolean result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    boolean next = array[i];

	    if (i == 0 && !useDef) {
		result = next;
		continue;
	    }

	    if (next) {
		result = next;
	    }
	}
	return result;
    }
    
    static byte _max(byte[] array, byte def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	byte result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    byte next = array[i];

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
    
    static char _max(char[] array, char def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	char result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    char next = array[i];

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
    
    static short _max(short[] array, short def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	short result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    short next = array[i];

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
    
    static long _max(long[] array, long def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	long result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    long next = array[i];

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
    
    static float _max(float[] array, float def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	float result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    float next = array[i];

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
    
    static double _max(double[] array, double def, boolean useDef) {
	if (array == null) {
	    return defValue(def, useDef);
	}
	int length = array.length;
	if (length == 0) {
	    return defValue(def, useDef);
	}
	double result = defValue(def, useDef);
	for (int i = 0; i < length; i++) {
	    double next = array[i];

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
    
    static boolean[] _copy(boolean[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static byte[] _copy(byte[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static char[] _copy(char[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static short[] _copy(short[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static int[] _copy(int[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static long[] _copy(long[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static float[] _copy(float[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static double[] _copy(double[] original) {
  	if (original == null) {
  	    return null;
  	}
  	return _copy(original, original.length);
    }
    
    static boolean[] _copy(boolean[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static byte[] _copy(byte[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static char[] _copy(char[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static short[] _copy(short[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static int[] _copy(int[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static long[] _copy(long[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static float[] _copy(float[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static double[] _copy(double[] original, int length) {
  	if (original == null) {
  	    return null;
  	}
  	return Arrays.copyOf(original, length);
    }

    static boolean[] _copy(boolean[] original, int fromIndex, int length) {
  	if (original == null) {
  	    return null;
  	}
  	
  	// fromIndex range
  	if (checkIndexOut(fromIndex, original.length)) {
  	    return null;
  	}
  	return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    } 
    
    static byte[] _copy(byte[] original, int fromIndex, int length) {
  	if (original == null) {
  	    return null;
  	}
  	
  	// fromIndex range
  	if (checkIndexOut(fromIndex, original.length)) {
  	    return null;
  	}
  	return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    } 
    
    static char[] _copy(char[] original, int fromIndex, int length) {
  	if (original == null) {
  	    return null;
  	}
  	
  	// fromIndex range
  	if (checkIndexOut(fromIndex, original.length)) {
  	    return null;
  	}
  	return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    } 
    
    static short[] _copy(short[] original, int fromIndex, int length) {
  	if (original == null) {
  	    return null;
  	}
  	
  	// fromIndex range
  	if (checkIndexOut(fromIndex, original.length)) {
  	    return null;
  	}
  	return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
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
    
    static long[] _copy(long[] original, int fromIndex, int length) {
  	if (original == null) {
  	    return null;
  	}
  	
  	// fromIndex range
  	if (checkIndexOut(fromIndex, original.length)) {
  	    return null;
  	}
  	return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    } 
    
    static float[] _copy(float[] original, int fromIndex, int length) {
  	if (original == null) {
  	    return null;
  	}
  	
  	// fromIndex range
  	if (checkIndexOut(fromIndex, original.length)) {
  	    return null;
  	}
  	return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    } 
    
    static double[] _copy(double[] original, int fromIndex, int length) {
  	if (original == null) {
  	    return null;
  	}
  	
  	// fromIndex range
  	if (checkIndexOut(fromIndex, original.length)) {
  	    return null;
  	}
  	return Arrays.copyOfRange(original, fromIndex, fromIndex + length);
    } 
    
    static boolean[] _copyRange(boolean[] original, int fromIndex, int toIndex) {
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
    
    static byte[] _copyRange(byte[] original, int fromIndex, int toIndex) {
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
    
    static char[] _copyRange(char[] original, int fromIndex, int toIndex) {
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
    
    static short[] _copyRange(short[] original, int fromIndex, int toIndex) {
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
    
    static long[] _copyRange(long[] original, int fromIndex, int toIndex) {
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
    
    static float[] _copyRange(float[] original, int fromIndex, int toIndex) {
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
    
    static double[] _copyRange(double[] original, int fromIndex, int toIndex) {
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
    
    static boolean[] _add(boolean[] x, boolean[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static byte[] _add(byte[] x, byte[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static char[] _add(char[] x, char[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static short[] _add(short[] x, short[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static int[] _add(int[] x, int[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static long[] _add(long[] x, long[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static float[] _add(float[] x, float[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static double[] _add(double[] x, double[] y) {
	// x + y
	return _add(x, y, null);
    }
    
    static boolean[] _add(boolean[] x, boolean[] y, ArithmeticContext context) {
	// x + y
	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    boolean[] r = (x == null) ? y : x; // add
	    return _copy(r);
	}
	boolean[] r = _copy(x, x.length + y.length);
	_arraycopy(y, r, 0, x.length, y.length);
	return r;
    }
    
    static byte[] _add(byte[] x, byte[] y, ArithmeticContext context) {
	// x + y
	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    byte[] r = (x == null) ? y : x; // add
	    return _copy(r);
	}
	byte[] r = _copy(x, x.length + y.length);
	_arraycopy(y, r, 0, x.length, y.length);
	return r;
    }
    
    static char[] _add(char[] x, char[] y, ArithmeticContext context) {
	// x + y
	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    char[] r = (x == null) ? y : x; // add
	    return _copy(r);
	}
	char[] r = _copy(x, x.length + y.length);
	_arraycopy(y, r, 0, x.length, y.length);
	return r;
    }
    
    static short[] _add(short[] x, short[] y, ArithmeticContext context) {
	// x + y
	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    short[] r = (x == null) ? y : x; // add
	    return _copy(r);
	}
	short[] r = _copy(x, x.length + y.length);
	_arraycopy(y, r, 0, x.length, y.length);
	return r;
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
    
    static long[] _add(long[] x, long[] y, ArithmeticContext context) {
	// x + y
	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    long[] r = (x == null) ? y : x; // add
	    return _copy(r);
	}
	long[] r = _copy(x, x.length + y.length);
	_arraycopy(y, r, 0, x.length, y.length);
	return r;
    }
    
    static float[] _add(float[] x, float[] y, ArithmeticContext context) {
	// x + y
	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    float[] r = (x == null) ? y : x; // add
	    return _copy(r);
	}
	float[] r = _copy(x, x.length + y.length);
	_arraycopy(y, r, 0, x.length, y.length);
	return r;
    }
    
    static double[] _add(double[] x, double[] y, ArithmeticContext context) {
	// x + y
	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    double[] r = (x == null) ? y : x; // add
	    return _copy(r);
	}
	double[] r = _copy(x, x.length + y.length);
	_arraycopy(y, r, 0, x.length, y.length);
	return r;
    }
    
    static boolean[] _sub(boolean[] x, boolean[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static byte[] _sub(byte[] x, byte[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static char[] _sub(char[] x, char[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static short[] _sub(short[] x, short[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static int[] _sub(int[] x, int[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static long[] _sub(long[] x, long[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static float[] _sub(float[] x, float[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static double[] _sub(double[] x, double[] y) {
	// x - y
	return _sub(x, y, null);
    }
    
    static boolean[] _sub(boolean[] x, boolean[] y, ArithmeticContext context) {
	// x - y
  	if (x == null || y == null) {
  	    ArithmeticContext c = getContext(context);
  	    if (checkNullArray(x, y, c)) {
  		return null;
  	    }
  	    
  	    // null - null = null
  	    // null - {1, 4, 6} = null
  	    // {1, 4, 6} - null = {1, 4, 6}
  	    
  	    boolean[] r = (x == null) ? null : _copy(x); // sub
  	    return r;
  	}
  	List<Boolean> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Boolean> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveBooleanArray(r);
    }    
    
    static byte[] _sub(byte[] x, byte[] y, ArithmeticContext context) {
	// x - y
  	if (x == null || y == null) {
  	    ArithmeticContext c = getContext(context);
  	    if (checkNullArray(x, y, c)) {
  		return null;
  	    }
  	    
  	    // null - null = null
  	    // null - {1, 4, 6} = null
  	    // {1, 4, 6} - null = {1, 4, 6}
  	    
  	    byte[] r = (x == null) ? null : _copy(x); // sub
  	    return r;
  	}
  	List<Byte> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Byte> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveByteArray(r);
    }    
    
    static char[] _sub(char[] x, char[] y, ArithmeticContext context) {
	// x - y
  	if (x == null || y == null) {
  	    ArithmeticContext c = getContext(context);
  	    if (checkNullArray(x, y, c)) {
  		return null;
  	    }
  	    
  	    // null - null = null
  	    // null - {1, 4, 6} = null
  	    // {1, 4, 6} - null = {1, 4, 6}
  	    
  	    char[] r = (x == null) ? null : _copy(x); // sub
  	    return r;
  	}
  	List<Character> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Character> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveCharArray(r);
    }    
    
    static short[] _sub(short[] x, short[] y, ArithmeticContext context) {
	// x - y
  	if (x == null || y == null) {
  	    ArithmeticContext c = getContext(context);
  	    if (checkNullArray(x, y, c)) {
  		return null;
  	    }
  	    
  	    // null - null = null
  	    // null - {1, 4, 6} = null
  	    // {1, 4, 6} - null = {1, 4, 6}
  	    
  	    short[] r = (x == null) ? null : _copy(x); // sub
  	    return r;
  	}
  	List<Short> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Short> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveShortArray(r);
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
  	List<Integer> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Integer> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveIntArray(r);
    }    
    
    static long[] _sub(long[] x, long[] y, ArithmeticContext context) {
	// x - y
  	if (x == null || y == null) {
  	    ArithmeticContext c = getContext(context);
  	    if (checkNullArray(x, y, c)) {
  		return null;
  	    }
  	    
  	    // null - null = null
  	    // null - {1, 4, 6} = null
  	    // {1, 4, 6} - null = {1, 4, 6}
  	    
  	    long[] r = (x == null) ? null : _copy(x); // sub
  	    return r;
  	}
  	List<Long> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Long> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveLongArray(r);
    }    
    
    static float[] _sub(float[] x, float[] y, ArithmeticContext context) {
	// x - y
  	if (x == null || y == null) {
  	    ArithmeticContext c = getContext(context);
  	    if (checkNullArray(x, y, c)) {
  		return null;
  	    }
  	    
  	    // null - null = null
  	    // null - {1, 4, 6} = null
  	    // {1, 4, 6} - null = {1, 4, 6}
  	    
  	    float[] r = (x == null) ? null : _copy(x); // sub
  	    return r;
  	}
  	List<Float> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Float> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveFloatArray(r);
    }    
    
    static double[] _sub(double[] x, double[] y, ArithmeticContext context) {
	// x - y
  	if (x == null || y == null) {
  	    ArithmeticContext c = getContext(context);
  	    if (checkNullArray(x, y, c)) {
  		return null;
  	    }
  	    
  	    // null - null = null
  	    // null - {1, 4, 6} = null
  	    // {1, 4, 6} - null = {1, 4, 6}
  	    
  	    double[] r = (x == null) ? null : _copy(x); // sub
  	    return r;
  	}
  	List<Double> r = CollectionLib.toList(x); // TODO: maybe own _toList(array)
  	List<Double> yr = CollectionLib.toList(y);
  	r.removeAll(yr);
  	return _toPrimitiveDoubleArray(r);
    }    
    
    static boolean[] _union(boolean[] x, boolean[] y) {
	return _union(x, y, null);
    }

    static byte[] _union(byte[] x, byte[] y) {
	return _union(x, y, null);
    }

    static char[] _union(char[] x, char[] y) {
	return _union(x, y, null);
    }

    static short[] _union(short[] x, short[] y) {
	return _union(x, y, null);
    }

    static int[] _union(int[] x, int[] y) {
	return _union(x, y, null);
    }

    static long[] _union(long[] x, long[] y) {
	return _union(x, y, null);
    }

    static float[] _union(float[] x, float[] y) {
	return _union(x, y, null);
    }

    static double[] _union(double[] x, double[] y) {
	return _union(x, y, null);
    }

    static boolean[] _union(boolean[] x, boolean[] y, ArithmeticContext context) {
	// add
	boolean[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Boolean> set = CollectionLib.toSet(result);
	return _toPrimitiveBooleanArray(set);
    }
      
    
    static byte[] _union(byte[] x, byte[] y, ArithmeticContext context) {
	// add
	byte[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Byte> set = CollectionLib.toSet(result);
	return _toPrimitiveByteArray(set);
    }
      
    
    static char[] _union(char[] x, char[] y, ArithmeticContext context) {
	// add
	char[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Character> set = CollectionLib.toSet(result);
	return _toPrimitiveCharArray(set);
    }
      
    
    static short[] _union(short[] x, short[] y, ArithmeticContext context) {
	// add
	short[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Short> set = CollectionLib.toSet(result);
	return _toPrimitiveShortArray(set);
    }
      
    
    static int[] _union(int[] x, int[] y, ArithmeticContext context) {
	// add
	int[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Integer> set = CollectionLib.toSet(result);
	return _toPrimitiveIntArray(set);
    }
      
    
    static long[] _union(long[] x, long[] y, ArithmeticContext context) {
	// add
	long[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Long> set = CollectionLib.toSet(result);
	return _toPrimitiveLongArray(set);
    }
      
    
    static float[] _union(float[] x, float[] y, ArithmeticContext context) {
	// add
	float[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Float> set = CollectionLib.toSet(result);
	return _toPrimitiveFloatArray(set);
    }
      
    
    static double[] _union(double[] x, double[] y, ArithmeticContext context) {
	// add
	double[] result = _add(x, y, context);
	if (result == null) {
	    return null;
	}

	// distinct
	Set<Double> set = CollectionLib.toSet(result);
	return _toPrimitiveDoubleArray(set);
    }
      
    
    static boolean[] _intersection(boolean[] x, boolean[] y) {
	return _intersection(x, y, null);
    }

    static byte[] _intersection(byte[] x, byte[] y) {
	return _intersection(x, y, null);
    }

    static char[] _intersection(char[] x, char[] y) {
	return _intersection(x, y, null);
    }

    static short[] _intersection(short[] x, short[] y) {
	return _intersection(x, y, null);
    }

    static int[] _intersection(int[] x, int[] y) {
	return _intersection(x, y, null);
    }

    static long[] _intersection(long[] x, long[] y) {
	return _intersection(x, y, null);
    }

    static float[] _intersection(float[] x, float[] y) {
	return _intersection(x, y, null);
    }

    static double[] _intersection(double[] x, double[] y) {
	return _intersection(x, y, null);
    }

    static boolean[] _intersection(boolean[] x, boolean[] y, ArithmeticContext context) {

	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    boolean[] r = (x == null) ? y : x;
	    return _copy(r);
	}

	Set<Boolean> set = new LinkedHashSet<>();
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
	return _toPrimitiveBooleanArray(set);
    }
       
    static byte[] _intersection(byte[] x, byte[] y, ArithmeticContext context) {

	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    byte[] r = (x == null) ? y : x;
	    return _copy(r);
	}

	Set<Byte> set = new LinkedHashSet<>();
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
	return _toPrimitiveByteArray(set);
    }
       
    static char[] _intersection(char[] x, char[] y, ArithmeticContext context) {

	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    char[] r = (x == null) ? y : x;
	    return _copy(r);
	}

	Set<Character> set = new LinkedHashSet<>();
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
	return _toPrimitiveCharArray(set);
    }
       
    static short[] _intersection(short[] x, short[] y, ArithmeticContext context) {

	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    short[] r = (x == null) ? y : x;
	    return _copy(r);
	}

	Set<Short> set = new LinkedHashSet<>();
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
	return _toPrimitiveShortArray(set);
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
       
    static long[] _intersection(long[] x, long[] y, ArithmeticContext context) {

	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    long[] r = (x == null) ? y : x;
	    return _copy(r);
	}

	Set<Long> set = new LinkedHashSet<>();
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
	return _toPrimitiveLongArray(set);
    }
       
    static float[] _intersection(float[] x, float[] y, ArithmeticContext context) {

	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    float[] r = (x == null) ? y : x;
	    return _copy(r);
	}

	Set<Float> set = new LinkedHashSet<>();
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
	return _toPrimitiveFloatArray(set);
    }
       
    static double[] _intersection(double[] x, double[] y, ArithmeticContext context) {

	if (x == null || y == null) {
	    ArithmeticContext c = getContext(context);
	    if (checkNullArray(x, y, c)) {
		return null;
	    }
	    double[] r = (x == null) ? y : x;
	    return _copy(r);
	}

	Set<Double> set = new LinkedHashSet<>();
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
	return _toPrimitiveDoubleArray(set);
    }
       
    // TODO: void _sort(boolean[] array)

    static void _sort(byte[] array) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array);
    }
    
    static void _sort(char[] array) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array);
    }
    
    static void _sort(short[] array) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array);
    }
    
    static void _sort(int[] array) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array);
    }
    
    static void _sort(long[] array) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array);
    }
    
    static void _sort(float[] array) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array);
    }
    
    static void _sort(double[] array) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array);
    }
    
    // TODO: void _sort(boolean[] array, int fromIndex, int toIndex)

    static void _sort(byte[] array, int fromIndex, int toIndex) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array, fromIndex, toIndex);
    }
    
    static void _sort(char[] array, int fromIndex, int toIndex) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array, fromIndex, toIndex);
    }
    
    static void _sort(short[] array, int fromIndex, int toIndex) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array, fromIndex, toIndex);
    }
    
    static void _sort(int[] array, int fromIndex, int toIndex) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array, fromIndex, toIndex);
    }
    
    static void _sort(long[] array, int fromIndex, int toIndex) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array, fromIndex, toIndex);
    }
    
    static void _sort(float[] array, int fromIndex, int toIndex) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array, fromIndex, toIndex);
    }
    
    static void _sort(double[] array, int fromIndex, int toIndex) {
	if (array == null) {
	    return;
	}
	Arrays.sort(array, fromIndex, toIndex);
    }
    
    static boolean[] _filter(boolean[] array, Predicate<Boolean> filter) {
	if (array == null) {
	    return null;
	}
	// no filter - return all
	if (filter == null) {
	    return Arrays.copyOf(array, array.length);
	}

	List<Boolean> list = new ArrayList<Boolean>();
	Boolean e = null;
	for (int i = 0; i < array.length; i++) {
	    e = array[i];
	    if (filter.test(e)) {
		list.add(e);
	    }
	}
	return _toPrimitiveBooleanArray(list);
    }    
    
    static byte[] _filter(byte[] array, Predicate<Byte> filter) {
	if (array == null) {
	    return null;
	}
	// no filter - return all
	if (filter == null) {
	    return Arrays.copyOf(array, array.length);
	}

	List<Byte> list = new ArrayList<Byte>();
	Byte e = null;
	for (int i = 0; i < array.length; i++) {
	    e = array[i];
	    if (filter.test(e)) {
		list.add(e);
	    }
	}
	return _toPrimitiveByteArray(list);
    }    
    
    static char[] _filter(char[] array, Predicate<Character> filter) {
	if (array == null) {
	    return null;
	}
	// no filter - return all
	if (filter == null) {
	    return Arrays.copyOf(array, array.length);
	}

	List<Character> list = new ArrayList<Character>();
	Character e = null;
	for (int i = 0; i < array.length; i++) {
	    e = array[i];
	    if (filter.test(e)) {
		list.add(e);
	    }
	}
	return _toPrimitiveCharArray(list);
    }    
    
    static short[] _filter(short[] array, Predicate<Short> filter) {
	if (array == null) {
	    return null;
	}
	// no filter - return all
	if (filter == null) {
	    return Arrays.copyOf(array, array.length);
	}

	List<Short> list = new ArrayList<Short>();
	Short e = null;
	for (int i = 0; i < array.length; i++) {
	    e = array[i];
	    if (filter.test(e)) {
		list.add(e);
	    }
	}
	return _toPrimitiveShortArray(list);
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
    
    static long[] _filter(long[] array, Predicate<Long> filter) {
	if (array == null) {
	    return null;
	}
	// no filter - return all
	if (filter == null) {
	    return Arrays.copyOf(array, array.length);
	}

	List<Long> list = new ArrayList<Long>();
	Long e = null;
	for (int i = 0; i < array.length; i++) {
	    e = array[i];
	    if (filter.test(e)) {
		list.add(e);
	    }
	}
	return _toPrimitiveLongArray(list);
    }    
    
    static float[] _filter(float[] array, Predicate<Float> filter) {
	if (array == null) {
	    return null;
	}
	// no filter - return all
	if (filter == null) {
	    return Arrays.copyOf(array, array.length);
	}

	List<Float> list = new ArrayList<Float>();
	Float e = null;
	for (int i = 0; i < array.length; i++) {
	    e = array[i];
	    if (filter.test(e)) {
		list.add(e);
	    }
	}
	return _toPrimitiveFloatArray(list);
    }    
    
    static double[] _filter(double[] array, Predicate<Double> filter) {
	if (array == null) {
	    return null;
	}
	// no filter - return all
	if (filter == null) {
	    return Arrays.copyOf(array, array.length);
	}

	List<Double> list = new ArrayList<Double>();
	Double e = null;
	for (int i = 0; i < array.length; i++) {
	    e = array[i];
	    if (filter.test(e)) {
		list.add(e);
	    }
	}
	return _toPrimitiveDoubleArray(list);
    }    
    
    static void _fill(boolean[] array, boolean value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(byte[] array, byte value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(char[] array, char value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(short[] array, short value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(int[] array, int value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(long[] array, long value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(float[] array, float value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(double[] array, double value) {
   	if (array == null) {
   	    return;
   	}
   	Arrays.fill(array, value);
    } 
    
    static void _fill(boolean[] array, boolean value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static void _fill(byte[] array, byte value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static void _fill(char[] array, char value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static void _fill(short[] array, short value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static void _fill(int[] array, int value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static void _fill(long[] array, long value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static void _fill(float[] array, float value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static void _fill(double[] array, double value, int fromIndex, int toIndex) {
  	if (array == null) {
  	    return;
  	}	
  	Arrays.fill(array, fromIndex, toIndex, value);
    } 
    
    static boolean _replaceAll(boolean[] array, boolean oldValue, boolean newValue) {
	if (array == null) {
	    return false;
	}
	boolean result = false;
	int length = array.length;
	for (int i = 0; i < length; i++) {
	    boolean e = array[i];
	    if (e == oldValue) {
		array[i] = newValue;
		result = true;
	    }
	}
	return result;
    } 
    
    static boolean _replaceAll(byte[] array, byte oldValue, byte newValue) {
	if (array == null) {
	    return false;
	}
	boolean result = false;
	int length = array.length;
	for (int i = 0; i < length; i++) {
	    byte e = array[i];
	    if (e == oldValue) {
		array[i] = newValue;
		result = true;
	    }
	}
	return result;
    } 
    
    static boolean _replaceAll(char[] array, char oldValue, char newValue) {
	if (array == null) {
	    return false;
	}
	boolean result = false;
	int length = array.length;
	for (int i = 0; i < length; i++) {
	    char e = array[i];
	    if (e == oldValue) {
		array[i] = newValue;
		result = true;
	    }
	}
	return result;
    } 
    
    static boolean _replaceAll(short[] array, short oldValue, short newValue) {
	if (array == null) {
	    return false;
	}
	boolean result = false;
	int length = array.length;
	for (int i = 0; i < length; i++) {
	    short e = array[i];
	    if (e == oldValue) {
		array[i] = newValue;
		result = true;
	    }
	}
	return result;
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
    
    static boolean _replaceAll(long[] array, long oldValue, long newValue) {
	if (array == null) {
	    return false;
	}
	boolean result = false;
	int length = array.length;
	for (int i = 0; i < length; i++) {
	    long e = array[i];
	    if (e == oldValue) {
		array[i] = newValue;
		result = true;
	    }
	}
	return result;
    } 
    
    static boolean _replaceAll(float[] array, float oldValue, float newValue) {
	if (array == null) {
	    return false;
	}
	boolean result = false;
	int length = array.length;
	for (int i = 0; i < length; i++) {
	    float e = array[i];
	    if (e == oldValue) {
		array[i] = newValue;
		result = true;
	    }
	}
	return result;
    } 
    
    static boolean _replaceAll(double[] array, double oldValue, double newValue) {
	if (array == null) {
	    return false;
	}
	boolean result = false;
	int length = array.length;
	for (int i = 0; i < length; i++) {
	    double e = array[i];
	    if (e == oldValue) {
		array[i] = newValue;
		result = true;
	    }
	}
	return result;
    } 
    
    static boolean[] _replaceAll(boolean[] array, boolean[] oldValues, boolean[] newValues) {
	if (array == null || oldValues == null || newValues == null) {
	    // TODO: Exception
	    return new boolean[0];

	}
	int size = Math.min(oldValues.length, newValues.length);
	if (size == 0) {
	    return new boolean[0];
	}
	boolean oldValue;
	boolean newValue;
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
    
    static boolean[] _replaceAll(byte[] array, byte[] oldValues, byte[] newValues) {
	if (array == null || oldValues == null || newValues == null) {
	    // TODO: Exception
	    return new boolean[0];

	}
	int size = Math.min(oldValues.length, newValues.length);
	if (size == 0) {
	    return new boolean[0];
	}
	byte oldValue;
	byte newValue;
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
    
    static boolean[] _replaceAll(char[] array, char[] oldValues, char[] newValues) {
	if (array == null || oldValues == null || newValues == null) {
	    // TODO: Exception
	    return new boolean[0];

	}
	int size = Math.min(oldValues.length, newValues.length);
	if (size == 0) {
	    return new boolean[0];
	}
	char oldValue;
	char newValue;
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
    
    static boolean[] _replaceAll(short[] array, short[] oldValues, short[] newValues) {
	if (array == null || oldValues == null || newValues == null) {
	    // TODO: Exception
	    return new boolean[0];

	}
	int size = Math.min(oldValues.length, newValues.length);
	if (size == 0) {
	    return new boolean[0];
	}
	short oldValue;
	short newValue;
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
    
    static boolean[] _replaceAll(long[] array, long[] oldValues, long[] newValues) {
	if (array == null || oldValues == null || newValues == null) {
	    // TODO: Exception
	    return new boolean[0];

	}
	int size = Math.min(oldValues.length, newValues.length);
	if (size == 0) {
	    return new boolean[0];
	}
	long oldValue;
	long newValue;
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
    
    static boolean[] _replaceAll(float[] array, float[] oldValues, float[] newValues) {
	if (array == null || oldValues == null || newValues == null) {
	    // TODO: Exception
	    return new boolean[0];

	}
	int size = Math.min(oldValues.length, newValues.length);
	if (size == 0) {
	    return new boolean[0];
	}
	float oldValue;
	float newValue;
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
    
    static boolean[] _replaceAll(double[] array, double[] oldValues, double[] newValues) {
	if (array == null || oldValues == null || newValues == null) {
	    // TODO: Exception
	    return new boolean[0];

	}
	int size = Math.min(oldValues.length, newValues.length);
	if (size == 0) {
	    return new boolean[0];
	}
	double oldValue;
	double newValue;
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
    
    static boolean[] _resize(boolean[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static byte[] _resize(byte[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static char[] _resize(char[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static short[] _resize(short[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static int[] _resize(int[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static long[] _resize(long[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static float[] _resize(float[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static double[] _resize(double[] array, int newLength) {
 	if (array == null) {
 	    return null;
 	}
 	checkResizeLength(array.length, newLength);
 	return _resizeArray(array, newLength);
    }
    
    static boolean[] _resize(boolean[] array, int newLength, boolean element) {
	if (array == null) {
	    return null;
	}
	boolean[] newArray = _resize(array, newLength);
	if (newLength > array.length) {
	    for (int i = array.length; i < newLength; i++) {
		newArray[i] = element;
	    }
	}
	return newArray;
    }
    
    static byte[] _resize(byte[] array, int newLength, byte element) {
	if (array == null) {
	    return null;
	}
	byte[] newArray = _resize(array, newLength);
	if (newLength > array.length) {
	    for (int i = array.length; i < newLength; i++) {
		newArray[i] = element;
	    }
	}
	return newArray;
    }
    
    static char[] _resize(char[] array, int newLength, char element) {
	if (array == null) {
	    return null;
	}
	char[] newArray = _resize(array, newLength);
	if (newLength > array.length) {
	    for (int i = array.length; i < newLength; i++) {
		newArray[i] = element;
	    }
	}
	return newArray;
    }
    
    static short[] _resize(short[] array, int newLength, short element) {
	if (array == null) {
	    return null;
	}
	short[] newArray = _resize(array, newLength);
	if (newLength > array.length) {
	    for (int i = array.length; i < newLength; i++) {
		newArray[i] = element;
	    }
	}
	return newArray;
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
    
    static long[] _resize(long[] array, int newLength, long element) {
	if (array == null) {
	    return null;
	}
	long[] newArray = _resize(array, newLength);
	if (newLength > array.length) {
	    for (int i = array.length; i < newLength; i++) {
		newArray[i] = element;
	    }
	}
	return newArray;
    }
    
    static float[] _resize(float[] array, int newLength, float element) {
	if (array == null) {
	    return null;
	}
	float[] newArray = _resize(array, newLength);
	if (newLength > array.length) {
	    for (int i = array.length; i < newLength; i++) {
		newArray[i] = element;
	    }
	}
	return newArray;
    }
    
    static double[] _resize(double[] array, int newLength, double element) {
	if (array == null) {
	    return null;
	}
	double[] newArray = _resize(array, newLength);
	if (newLength > array.length) {
	    for (int i = array.length; i < newLength; i++) {
		newArray[i] = element;
	    }
	}
	return newArray;
    }
    
    static boolean[] _trunc(boolean[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static byte[] _trunc(byte[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static char[] _trunc(char[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static short[] _trunc(short[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static int[] _trunc(int[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static long[] _trunc(long[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static float[] _trunc(float[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static double[] _trunc(double[] array, int newLength) {
	if (array == null) {
	    return null;
	}
	checkTruncLength(array.length, newLength);
	return _resizeArray(array, newLength);
    }    
    
    static boolean[] _resizeArray(boolean[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static byte[] _resizeArray(byte[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static char[] _resizeArray(char[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static short[] _resizeArray(short[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static int[] _resizeArray(int[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static long[] _resizeArray(long[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static float[] _resizeArray(float[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static double[] _resizeArray(double[] array, int newLength) {
 	return Arrays.copyOf(array, newLength);
    }
    
    static boolean[] _distinct(boolean[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_BOOLEAN_ARRAY;
 	}
   	Set<Boolean> set = CollectionLib.toSet(array);
   	return _toPrimitiveBooleanArray(set);
    }
    
    static byte[] _distinct(byte[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_BYTE_ARRAY;
 	}
   	Set<Byte> set = CollectionLib.toSet(array);
   	return _toPrimitiveByteArray(set);
    }
    
    static char[] _distinct(char[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_CHAR_ARRAY;
 	}
   	Set<Character> set = CollectionLib.toSet(array);
   	return _toPrimitiveCharArray(set);
    }
    
    static short[] _distinct(short[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_SHORT_ARRAY;
 	}
   	Set<Short> set = CollectionLib.toSet(array);
   	return _toPrimitiveShortArray(set);
    }
    
    static int[] _distinct(int[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_INT_ARRAY;
 	}
   	Set<Integer> set = CollectionLib.toSet(array);
   	return _toPrimitiveIntArray(set);
    }
    
    static long[] _distinct(long[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_LONG_ARRAY;
 	}
   	Set<Long> set = CollectionLib.toSet(array);
   	return _toPrimitiveLongArray(set);
    }
    
    static float[] _distinct(float[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_FLOAT_ARRAY;
 	}
   	Set<Float> set = CollectionLib.toSet(array);
   	return _toPrimitiveFloatArray(set);
    }
    
    static double[] _distinct(double[] array) {
   	if (array == null) {
   	    return null;
   	}
   	if (array.length == 0) {
 	    return EMPTY_DOUBLE_ARRAY;
 	}
   	Set<Double> set = CollectionLib.toSet(array);
   	return _toPrimitiveDoubleArray(set);
    }
    
    static boolean[] _asArray(boolean... array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	boolean[] result = new boolean[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }    
    
    static byte[] _asArray(byte... array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	byte[] result = new byte[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }    
    
    static char[] _asArray(char... array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	char[] result = new char[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }    
    
    static short[] _asArray(short... array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	short[] result = new short[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
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
    
    static long[] _asArray(long... array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	long[] result = new long[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }    
    
    static float[] _asArray(float... array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	float[] result = new float[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }    
    
    static double[] _asArray(double... array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	double[] result = new double[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }    
    
    static boolean[] _toArray(Boolean[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	boolean[] result = new boolean[size];
	for (int i = 0; i < size; i++) {
	    Boolean e = array[i];
	    result[i] = e == null ? DEFAULT_BOOLEAN : e;
	}
	return result;
    }
    
    static byte[] _toArray(Byte[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	byte[] result = new byte[size];
	for (int i = 0; i < size; i++) {
	    Byte e = array[i];
	    result[i] = e == null ? DEFAULT_BYTE : e;
	}
	return result;
    }
    
    static char[] _toArray(Character[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	char[] result = new char[size];
	for (int i = 0; i < size; i++) {
	    Character e = array[i];
	    result[i] = e == null ? DEFAULT_CHAR : e;
	}
	return result;
    }
    
    static short[] _toArray(Short[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	short[] result = new short[size];
	for (int i = 0; i < size; i++) {
	    Short e = array[i];
	    result[i] = e == null ? DEFAULT_SHORT : e;
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
    
    static long[] _toArray(Long[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	long[] result = new long[size];
	for (int i = 0; i < size; i++) {
	    Long e = array[i];
	    result[i] = e == null ? DEFAULT_LONG : e;
	}
	return result;
    }
    
    static float[] _toArray(Float[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	float[] result = new float[size];
	for (int i = 0; i < size; i++) {
	    Float e = array[i];
	    result[i] = e == null ? DEFAULT_FLOAT : e;
	}
	return result;
    }
    
    static double[] _toArray(Double[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	double[] result = new double[size];
	for (int i = 0; i < size; i++) {
	    Double e = array[i];
	    result[i] = e == null ? DEFAULT_DOUBLE : e;
	}
	return result;
    }
    
    static Boolean[] _toArray(boolean[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	Boolean[] result = new Boolean[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }
    
    static Byte[] _toArray(byte[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	Byte[] result = new Byte[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }
    
    static Character[] _toArray(char[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	Character[] result = new Character[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }
    
    static Short[] _toArray(short[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	Short[] result = new Short[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
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
    
    static Long[] _toArray(long[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	Long[] result = new Long[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }
    
    static Float[] _toArray(float[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	Float[] result = new Float[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }
    
    static Double[] _toArray(double[] array) {
	if (array == null) {
	    return null;
	}
	int size = array.length;
	Double[] result = new Double[size];
	for (int i = 0; i < size; i++) {
	    result[i] = array[i];
	}
	return result;
    }
    
    static boolean[] _toPrimitiveBooleanArray(Collection<Boolean> collection) {
	if (collection == null) {
	    return null;
	}
	if (collection.isEmpty()) {
	    return EMPTY_BOOLEAN_ARRAY;
	}
	int size = collection.size();
	boolean[] array = new boolean[size];
	Iterator<Boolean> itr = collection.iterator();
	for (int i = 0; i < size; i++) {
	    array[i] = itr.next();
	}
	return array;
    }
    
    static byte[] _toPrimitiveByteArray(Collection<Byte> collection) {
	if (collection == null) {
	    return null;
	}
	if (collection.isEmpty()) {
	    return EMPTY_BYTE_ARRAY;
	}
	int size = collection.size();
	byte[] array = new byte[size];
	Iterator<Byte> itr = collection.iterator();
	for (int i = 0; i < size; i++) {
	    array[i] = itr.next();
	}
	return array;
    }
    
    static char[] _toPrimitiveCharArray(Collection<Character> collection) {
	if (collection == null) {
	    return null;
	}
	if (collection.isEmpty()) {
	    return EMPTY_CHAR_ARRAY;
	}
	int size = collection.size();
	char[] array = new char[size];
	Iterator<Character> itr = collection.iterator();
	for (int i = 0; i < size; i++) {
	    array[i] = itr.next();
	}
	return array;
    }
    
    static short[] _toPrimitiveShortArray(Collection<Short> collection) {
	if (collection == null) {
	    return null;
	}
	if (collection.isEmpty()) {
	    return EMPTY_SHORT_ARRAY;
	}
	int size = collection.size();
	short[] array = new short[size];
	Iterator<Short> itr = collection.iterator();
	for (int i = 0; i < size; i++) {
	    array[i] = itr.next();
	}
	return array;
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
    
    static long[] _toPrimitiveLongArray(Collection<Long> collection) {
	if (collection == null) {
	    return null;
	}
	if (collection.isEmpty()) {
	    return EMPTY_LONG_ARRAY;
	}
	int size = collection.size();
	long[] array = new long[size];
	Iterator<Long> itr = collection.iterator();
	for (int i = 0; i < size; i++) {
	    array[i] = itr.next();
	}
	return array;
    }
    
    static float[] _toPrimitiveFloatArray(Collection<Float> collection) {
	if (collection == null) {
	    return null;
	}
	if (collection.isEmpty()) {
	    return EMPTY_FLOAT_ARRAY;
	}
	int size = collection.size();
	float[] array = new float[size];
	Iterator<Float> itr = collection.iterator();
	for (int i = 0; i < size; i++) {
	    array[i] = itr.next();
	}
	return array;
    }
    
    static double[] _toPrimitiveDoubleArray(Collection<Double> collection) {
	if (collection == null) {
	    return null;
	}
	if (collection.isEmpty()) {
	    return EMPTY_DOUBLE_ARRAY;
	}
	int size = collection.size();
	double[] array = new double[size];
	Iterator<Double> itr = collection.iterator();
	for (int i = 0; i < size; i++) {
	    array[i] = itr.next();
	}
	return array;
    }
    

    
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

    //private static <T> boolean checkNull(T[] x, T[] y, ArithmeticContext context) {
	//return checkNull(x, y, context, "Array must be not null");
    //}

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
