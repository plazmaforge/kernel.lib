package plazma.kernel.lib.analysis;

public class AbstractFunction {

    protected void checkNotNull(String name, Object obj) {
        CheckLib.checkNotNull(name, obj);
    }

    protected void checkArrayLength(double[] array1, double[] array2) {
        CheckLib.checkArrayLength(array1, array2);
    }

    protected void checkArrayLength(int length1, int length2) {
        CheckLib.checkArrayLength(length1, length2);
    }

    protected void checkArrayLengthMin(double[] array, int min) {
        CheckLib.checkArrayLengthMin(array, min);
    }

    protected void checkArrayLengthMin(int length, int min) {
        CheckLib.checkArrayLengthMin(length, min);
    }

    protected void checkOrder(double[] array) {
        CheckLib.checkOrder(array);
    }

    protected void checkNoData(String name, double[] array) {
        CheckLib.checkNotNull(name, array);
    }
}
