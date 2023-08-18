package plazma.kernel.lib.str;

import java.util.LinkedHashSet;
import java.util.Set;

import plazma.kernel.lib.AbstractTestCase;
import plazma.kernel.lib.str.StrLib;

public class StrLibSymbolTest extends AbstractTestCase {

    public void testSymbol() {

        println(StrLib.isUpperCase('1'));
        println(StrLib.isLowerCase('1'));

        // for (char ch = 0; ch < 255; ch++) {
        // println("" 
        // + ch 
        // + ", " 
        // + StrLib.isAlphabetic(ch) 
        // + ", " 
        // + StrLib.isLetter(ch));
        // }
        
        char c = 'A';
        int i = c;
        
        System.out.println();        
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));
        
        
        System.out.println(Integer.toBinaryString(65));
        System.out.println(Integer.toBinaryString(-65));               
        
        c = 'Ї';
        i = c;
        String s = "Ї";
        
        System.out.println();
        System.out.println(i);
        
        byte[] bytes = s.getBytes();
        
        System.out.println(s);
        
        System.out.println(bytes.length);
        
        //char[] array = new char[1];
        
        long time = System.currentTimeMillis();
        
        //if (true) {
        //    return;            
        //}
        
        char max = 2048; // Character.MAX_VALUE; // 256
        
        int count = 0;
        
        System.out.println("============");        
        System.out.println("\u041f");
        System.out.println("\u0420");
        System.out.println("\uD09f");
        System.out.println("u+D09f");
        System.out.println("\u2D63");
        System.out.println("\u2D53");
        System.out.println("============");
                
        for (char ch = 0; ch < max; ch++) {
            
            int intChar = ch;
            String hexChar = Integer.toHexString(intChar);
            
            bytes = getBytes(ch);
            String bytesChar = toHexString(bytes);
            
            // 1055: 41f: П: 2 {ffffffd0, ffffff9f}
            
            int len = bytes.length;            
            boolean alarm = false;
            
            
            
            //if (len > 1) {
                
            //}
            
            //for (int z = 0; z < len; z++) {
            //    if (bytes[z] == 60) {
            //        alarm = true;
            //    }
            //}
            
            if (len >= 3) {
                alarm = true;                
            }
            
            boolean print = false; //alarm;
            if (print) {
                count++;
                        
            System.out.println("" + intChar + ": " + hexChar + ": " + ch + ": " + bytes.length + " " + bytesChar);
            
            }
            
            //if (count > 1000)  {
            //    return;
            //    
            //}
            
            //System.out.println("" + i + ": " + ch + ": " + bytes.length + "[1]: " + bytes[0]);
            //System.out.println("" + i + ": " + ch + ": " + bytes.length + " " + buf.toString());
        }
                
        System.out.println("OK");
        
        time = System.currentTimeMillis() - time;
        
        System.out.println(time);

        
        
    }
    
    
    ////
    
    private static final char[] CHARS = new char[1];
        
    private static byte[] getBytes(char ch) {
        CHARS[0] = ch;
        return  new String(CHARS).getBytes();        
    }
    
    private static String toDecString(byte[] bytes) {
        return toString(bytes, 0);
    }
    
    private static String toBinString(byte[] bytes) {
        return toString(bytes, 1);
    }

    private static String toOctString(byte[] bytes) {
        return toString(bytes, 3);
    }

    private static String toHexString(byte[] bytes) {
        return toString(bytes, 4);
    }

    private static String toString(byte[] bytes) {
        return toString(bytes, 0);
    }
    
    private static String toString(byte[] bytes, int shift) {
        if (bytes == null) {
            return null;            
        }
        int len = bytes.length;
        StringBuilder buf = new StringBuilder("{");
        for (int z = 0; z < len; z++) {
            if (z > 0) {
                buf.append(", ");                    
            }
            byte value = bytes[z]; 
            
            if (shift == 1) {
                buf.append(Integer.toBinaryString(value));
            } else if (shift == 3) {
                buf.append(Integer.toOctalString(value));
            } else if (shift == 4) {
                buf.append(Integer.toHexString(value));
            } else {
                buf.append(value);
            }
        }
        buf.append("}");
        return buf.toString();
    }

}
