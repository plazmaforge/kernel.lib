package plazma.lib.ext;

public class PrimitiveArray {

    private boolean[] booleanArray;

    private byte[] byteArray;

    private char[] charArray;

    private short[] shortArray;

    private int[] intArray;

    private long[] longArray;

    private float[] floatArray;

    private double[] doubleArray;

    private Class<?> type;

    public PrimitiveArray(boolean[] array) {
        this.booleanArray = array;
        this.type = boolean.class;
    }

    public PrimitiveArray(byte[] array) {
        this.byteArray = array;
        this.type = byte.class;
    }

    public PrimitiveArray(char[] array) {
        this.charArray = array;
        this.type = char.class;
    }

    public PrimitiveArray(short[] array) {
        this.shortArray = array;
        this.type = short.class;
    }

    public PrimitiveArray(int[] array) {
        this.intArray = array;
        this.type = int.class;
    }

    public PrimitiveArray(long[] array) {
        this.longArray = array;
        this.type = long.class;
    }

    public PrimitiveArray(float[] array) {
        this.floatArray = array;
        this.type = float.class;
    }

    public PrimitiveArray(double[] array) {
        this.doubleArray = array;
        this.type = double.class;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean[] booleanArray() {
        return booleanArray;
    }

    public byte[] byteArray() {
        return byteArray;
    }

    public char[] charArray() {
        return charArray;
    }

    public short[] shortArray() {
        return shortArray;
    }

    public int[] intArray() {
        return intArray;
    }

    public long[] longArray() {
        return longArray;
    }

    public float[] floatArray() {
        return floatArray;
    }

    public double[] doubleArray() {
        return doubleArray;
    }

}
