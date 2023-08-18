package plazma.kernel.lib.analysis;

public class CheckLib {

    public static void checkNotNull(String name, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Value '" + name + "' must be not null");
        }
    }

    public static void checkArrayLength(double[] array1, double[] array2) {
        if (array1.length != array2.length) {
            throw new IllegalArgumentException(
                    "Dimension Mismatch: length1=" + array1.length + ", length2=" + array2.length);
        }
    }

    public static void checkArrayLength(int length1, int length2) {
        if (length1 != length2) {
            throw new IllegalArgumentException("Dimension Mismatch: length1=" + length1 + ", length2=" + length2);
        }
    }

    public static void checkArrayLengthMin(double[] array, int min) {
        checkArrayLengthMin(array.length, min);
    }

    public static void checkArrayLengthMin(int length, int min) {
        if (length < min) {
            throw new IllegalArgumentException("Dimension is small: length=" + length + ", min=" + min);
        }
    }

    public static void checkOrder(double[] array) {
        // TODO
    }

    public static void checkNoData(String name, double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Do Data in array '" + name + "'");
        }
    }
}
