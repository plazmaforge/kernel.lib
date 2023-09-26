package plazma.lib.num;

import plazma.lib.AbstractTestCase;
import plazma.lib.array.ArrayLib;
import plazma.lib.math.MathLib;

public class NumLibTest extends AbstractTestCase {

    public void test() {
        int x = 0;
        int y = 0;
        String s = null;

        x = 10; // 10 <=> 1010
        s = NumLib.formatBinary(x);
        assertEquals("1010", s);

        y = NumLib.parseInt(s, 2);
        assertEquals(x, y);

        printInt(x, 10);
        printInt(s, 2);
        printInt(y, 10);

        x = 0b1010; // 1010 <=> 1010
        s = NumLib.formatBinary(x);
        assertEquals("1010", s);

        y = NumLib.parseInt(s, 2);
        assertEquals(x, y);

        println();
        printInt(x, 10);
        printInt(s, 2);
        printInt(y, 10);

    }

    public void testRandom() {
        int randomInt = NumLib.randomInt();
        float randomFloat = NumLib.randomFloat();
        double randomDouble = NumLib.randomDouble();

        println("randomInt: " + randomInt);
        println("randomFloat: " + randomFloat);
        println("randomDouble: " + randomDouble);

        println();
        printLine();
        println("Random integers");
        printLine();
        for (int i = 0; i < 100; i++) {
            randomInt = NumLib.randomInt();
            println("" + (i + 1) + ": " + randomInt);
        }

        println();
        printLine();
        println("Random floats");
        printLine();

        float[] randomFloatArray = ArrayLib.randomFloatArray(100, -100f, 100f, (x) -> MathLib.round(x, 2));

        for (int i = 0; i < 100; i++) {
            // randomFloat = MathLib.round(NumberLib.randomFloat(-100f, 100f), 2);

            randomFloat = randomFloatArray[i];
            println("" + (i + 1) + ": " + randomFloat);
        }

        /*
         * for (int i = 0; i < 100; i++) { //randomFloat =
         * MathLib.round(NumberLib.randomFloat(-100f, 100f), 2);
         * 
         * randomFloat = NumberLib.randomFloat(-100f, 100f, (x) -> MathLib.round(x, 2));
         * println("" + (i + 1) + ": " + randomFloat); }
         */

    }

    public void testRoman() {

        println();
        printLine();
        println("Roman Numbers");
        printLine();

        String roman = null;
        int arab = 0;
        for (int i = 1; i <= 3999; i++) {
            roman = NumLib.toRoman(i);
            arab = NumLib.toArab(roman);

            assertEquals(i, arab);

            println("" + i + ": " + roman + ": " + arab);
        }

        println("-----");
        println(NumLib.toArab("MMMMXXVII"));
        println("-----");
        println(NumLib.toArab("IMMMMXXVI"));
        println("-----");
        println(NumLib.toArab("-----"));

        roman = "MMMMXXVII";
        println(roman + "\t-> " + NumLib.validateRoman(roman));
        roman = "AMMMMXXVII";
        println(roman + "\t-> " + NumLib.validateRoman(roman));
        roman = "MIMMXXVII";
        println(roman + "\t-> " + NumLib.validateRoman(roman));
    }

    public void printInt(Object value, int radix) {
        // System.out.println("" + value + "\t\t\t\t(10)");
        System.out.println("" + value + " (" + radix + ")");
    }

}
