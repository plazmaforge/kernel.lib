package plazma.kernel.lib;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;
import plazma.kernel.lib.str.StrLib;

public abstract class AbstractTestCase extends TestCase {

    public static final String USER_DIR = System.getProperty("user.dir");

    public static String TEST_DIR;

    static {
        File userDir = new File(USER_DIR);
        TEST_DIR = userDir.getParent() + File.separator + "test.data";
        File testDir = new File(TEST_DIR);
        if (!testDir.exists()) {
            testDir.mkdir();
        }
    }

    //// resource

    protected URL getResource(String name) {
        return getClass().getResource(name);
    }

    protected InputStream getResourceAsStream(String name) {
        return getClass().getResourceAsStream(name);
    }
    
    
    //// TODO: getResourceFile()

    protected File getFile(String name) {
        URL url = getResource(name);
        if (url == null) {
            return null;
        }
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }
    
    //// TODO: getResourceFileName()

    protected String getFileName(String name) {
        File file = getFile(name);
        if (file == null) {
            return null;
        }
        return file.getAbsolutePath();
    }

    //// print

    protected void printHeader(String header) {
        println();
        if (header != null) {
            printLine();
            println(header);
        }
        printLine();
    }

    protected void printLine() {
        println("=============================================================================");
    }

    protected void println() {
        System.out.println();
    }

    protected void println(Object obj) {
        System.out.println(obj);
    }

    protected void printArray(boolean[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(byte[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(char[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(short[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(long[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(float[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(double[] array) {
        System.out.println(Arrays.toString(array));
    }

    protected void printArray(String message, double[] array) {
        System.out.println("" + message + Arrays.toString(array));
    }

    protected void printArray(Object[] array) {
        System.out.println(Arrays.toString(array));
    }

    //// toString

    protected String toString(int[] array) {
        return array == null ? null : Arrays.toString(array);
    }

    protected String toString(long[] array) {
        return array == null ? null : Arrays.toString(array);
    }

    protected String toString(float[] array) {
        return array == null ? null : Arrays.toString(array);
    }

    protected String toString(double[] array) {
        return array == null ? null : Arrays.toString(array);
    }

    protected String toString(Object[] array) {
        return array == null ? null : Arrays.toString(array);
    }

    protected void printCollection(Collection<?> collection) {
        printArray(collection == null ? null : collection.toArray());
    }

    protected String fill(Object obj, int len) {
        String str = obj == null ? "" : obj.toString();
        return StrLib.fill(str, len);
    }

}
