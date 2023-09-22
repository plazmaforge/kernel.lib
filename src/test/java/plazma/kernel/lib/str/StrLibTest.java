package plazma.kernel.lib.str;

import plazma.kernel.lib.AbstractTestCase;
import plazma.kernel.lib.str.StrLib;

public class StrLibTest extends AbstractTestCase {

    public void testTrim() {

        // trim: null
        assertNull(StrLib.trim(null));

        // trim: empty
        assertEquals("", StrLib.trim(""));
        assertEquals("", StrLib.trim(" "));
        assertEquals("", StrLib.trim("  "));
        assertEquals("", StrLib.trim("   "));

        // trim: value
        assertEquals("abc", StrLib.trim(" abc "));
        assertEquals("abc", StrLib.trim(" abc"));
        assertEquals("abc", StrLib.trim("abc "));
        assertEquals("abc", StrLib.trim("abc"));
        assertEquals("a b c", StrLib.trim(" a b c "));

        assertEquals("abc", StrLib.trim("**abc***", '*'));
        assertEquals("abc", StrLib.trim("++abc+++", '+'));

        assertEquals("abc", StrLib.trim("abc***", '*'));
        assertEquals("abc", StrLib.trim("***abc", '*'));

        assertEquals("abc", StrLib.trim("**abc**", "*+-"));
        assertEquals("abc", StrLib.trim("**abc**", "*+-"));
        assertEquals("abc", StrLib.trim("*+*abc**-", "*+-"));
        
        assertEquals("abc", StrLib.trim("abc\r\n", "\r\n"));
        assertEquals(" abc", StrLib.trim(" abc\r\n", "\r\n"));
        
        // left trim: null
        assertNull(StrLib.ltrim(null));

        // left trim: empty
        assertEquals("", StrLib.ltrim(""));
        assertEquals("", StrLib.ltrim(" "));
        assertEquals("", StrLib.ltrim("  "));
        assertEquals("", StrLib.ltrim("   "));

        // left trim: value
        assertEquals("abc ", StrLib.ltrim(" abc "));
        assertEquals("abc", StrLib.ltrim(" abc"));
        assertEquals("abc ", StrLib.ltrim("abc "));
        assertEquals("abc", StrLib.ltrim("abc"));
        assertEquals("a b c ", StrLib.ltrim(" a b c "));

        assertEquals("abc", StrLib.ltrim("...abc", '.'));
        assertEquals("abc..", StrLib.ltrim("...abc..", '.'));
        assertEquals("abc", StrLib.ltrim("\rabc", '\r'));
        assertEquals(" abc", StrLib.ltrim("\r abc", '\r'));
        assertEquals("abc\r", StrLib.ltrim("\rabc\r", '\r'));
        assertEquals("abc\r", StrLib.ltrim("\rabc\r", '\r'));

        assertEquals("abc", StrLib.ltrim("...abc", "."));
        assertEquals("abc..", StrLib.ltrim("...abc..", "."));
        assertEquals("abc", StrLib.ltrim("\r\nabc", "\r\n"));
        assertEquals(" abc", StrLib.ltrim("\r\n abc", "\r\n"));
        assertEquals("abc\r\n", StrLib.ltrim("\r\nabc\r\n", "\r\n"));
        assertEquals("abc\r\n", StrLib.ltrim("\r\nabc\r\n", "\r\n"));

        // right trim: null
        assertNull(StrLib.rtrim(null));

        // right trim: empty
        assertEquals("", StrLib.rtrim(""));
        assertEquals("", StrLib.rtrim(" "));
        assertEquals("", StrLib.rtrim("  "));
        assertEquals("", StrLib.rtrim("   "));

        // right trim: value
        assertEquals(" abc", StrLib.rtrim(" abc "));
        assertEquals(" abc", StrLib.rtrim(" abc"));
        assertEquals("abc", StrLib.rtrim("abc "));
        assertEquals("abc", StrLib.rtrim("abc"));
        assertEquals(" a b c", StrLib.rtrim(" a b c "));

        assertEquals("abc", StrLib.rtrim("abc...", '.'));
        assertEquals("..abc", StrLib.rtrim("..abc...", '.'));
        assertEquals("abc", StrLib.rtrim("abc\r", '\r'));
        assertEquals(" abc", StrLib.rtrim(" abc\r", '\r'));
        assertEquals("\rabc", StrLib.rtrim("\rabc\r", '\r'));
        assertEquals("\rabc", StrLib.rtrim("\rabc\r", '\r'));

        assertEquals("abc", StrLib.rtrim("abc...", "."));
        assertEquals("..abc", StrLib.rtrim("..abc...", "."));
        assertEquals("abc", StrLib.rtrim("abc\r\n", "\r\n"));
        assertEquals(" abc", StrLib.rtrim(" abc\r\n", "\r\n"));
        assertEquals("\r\nabc", StrLib.rtrim("\r\nabc\r\n", "\r\n"));
        assertEquals("\r\nabc", StrLib.rtrim("\r\nabc\r\n", "\r\n"));

        // trim all: null
        assertNull(StrLib.trimAll(null));

        // trim all: empty
        assertEquals("", StrLib.trimAll(""));
        assertEquals("", StrLib.trimAll(" "));
        assertEquals("", StrLib.trimAll("  "));
        assertEquals("", StrLib.trimAll("   "));

        // trim all: value
        assertEquals("tab cn", StrLib.trimAll(" tab cn "));
        assertEquals("abc", StrLib.trimAll(" abc \t"));
        assertEquals("abc", StrLib.trimAll("\n  \t \r \nabc \t \f"));
        assertEquals("abc", StrLib.trimAll("\n\t\r\nabc\t\f"));

    }

    public void testPad() {

        // lpad: null
        assertNull(StrLib.lpad(null, 3));

        // lpad: empty
        assertEquals("   ", StrLib.lpad("", 3));

        // lpad: value
        assertEquals("  a", StrLib.lpad("a", 3));
        assertEquals(" a ", StrLib.lpad(" a ", 3));
        assertEquals(" abcd ", StrLib.lpad(" abcd ", 3));
        
        assertEquals("abc", StrLib.lpad("abc", -1, '*'));
        assertEquals("abc", StrLib.lpad("abc", 0, '*'));
        assertEquals("abc", StrLib.lpad("abc", 1, '*'));
        assertEquals("abc", StrLib.lpad("abc", 3, '*'));
        assertEquals("*abc", StrLib.lpad("abc", 4, '*'));
        
        assertEquals("yzyzyabc", StrLib.lpad("abc", 8, "yz"));
        // 12345678
        // yzyzyz**
        // *****abc
        //---------
        // yzyzyabc
        
        // rpad: null
        assertNull(StrLib.rpad(null, 3));

        // rpad: empty
        assertEquals("   ", StrLib.rpad("", 3));

        // lpad: value
        assertEquals("a  ", StrLib.rpad("a", 3));
        assertEquals(" a ", StrLib.rpad(" a ", 3));
        assertEquals(" abcd ", StrLib.rpad(" abcd ", 3));

        assertEquals("abc", StrLib.rpad("abc", -1, '*'));
        assertEquals("abc", StrLib.rpad("abc", 0, '*'));
        assertEquals("abc", StrLib.rpad("abc", 1, '*'));
        assertEquals("abc", StrLib.rpad("abc", 3, '*'));
        assertEquals("abc*", StrLib.rpad("abc", 4, '*'));

        assertEquals("abcyzyzy", StrLib.rpad("abc", 8, "yz"));
        // 12345678
        // abc*****
        // ***yzyzyz
        // --------
        // abcyzyzy
        
    }

    public void testLeftRight() {

        // left: null
        assertNull(StrLib.left(null, 3));

        // left: empty
        assertEquals("", StrLib.left("", 3));

        // left: value
        assertEquals("a", StrLib.left("a", 3));
        assertEquals("ab", StrLib.left("ab", 3));
        assertEquals("abc", StrLib.left("abc", 3));
        assertEquals("abc", StrLib.left("abcd", 3));

        // right: null
        assertNull(StrLib.right(null, 3));

        // right: empty
        assertEquals("", StrLib.right("", 3));

        // right: value
        assertEquals("a", StrLib.right("a", 3));
        assertEquals("ab", StrLib.right("ab", 3));
        assertEquals("abc", StrLib.right("abc", 3));
        assertEquals("bcd", StrLib.right("abcd", 3));

    }

    public void testCapitalizeDecapitalize() {

        // capitalize: null
        assertNull(StrLib.capitalize(null));

        // capitalize: empty
        assertEquals("", StrLib.capitalize(""));
        assertEquals(" ", StrLib.capitalize(" "));

        // capitalize: value
        assertEquals("ABcdE", StrLib.capitalize("aBcdE"));
        assertEquals("Abcde", StrLib.capitalize("aBcdE", true));

        // decapitalize: null
        assertNull(StrLib.decapitalize(null));

        // decapitalize: empty
        assertEquals("", StrLib.decapitalize(""));
        assertEquals(" ", StrLib.decapitalize(" "));

        // decapitalize: value
        assertEquals("aBcdE", StrLib.decapitalize("ABcdE"));
        assertEquals("abcde", StrLib.decapitalize("ABcdE", true));

    }

    public void testUpperLower() {

        // upper: null
        assertNull(StrLib.upper(null));

        // upper: empty
        assertEquals("", StrLib.upper(""));
        assertEquals(" ", StrLib.upper(" "));

        // upper: value
        assertEquals("ABCDE", StrLib.upper("aBcdE"));

        // lower: null
        assertNull(StrLib.lower(null));

        // lower: empty
        assertEquals("", StrLib.lower(""));
        assertEquals(" ", StrLib.lower(" "));

        // lower: value
        assertEquals("abcde", StrLib.lower("aBcdE"));

    }

    public void testToUpperLowerCase() {

        // toUpperCase: null
        assertNull(StrLib.toUpperCase(null));

        // toUpperCase: empty
        assertEquals("", StrLib.toUpperCase(""));
        assertEquals(" ", StrLib.toUpperCase(" "));

        // toUpperCase: value
        assertEquals("ABCDE", StrLib.toUpperCase("aBcdE"));

        // toLowerCase: null
        assertNull(StrLib.toLowerCase(null));

        // toLowerCase: empty
        assertEquals("", StrLib.toLowerCase(""));
        assertEquals(" ", StrLib.toLowerCase(" "));

        // toLowerCase: value
        assertEquals("abcde", StrLib.toLowerCase("aBcdE"));

    }

    public void testToCase() {

        // toCase: null
        assertNull(StrLib.toCase(null, false));
        assertNull(StrLib.toCase(null, true));

        // toCase: empty
        assertEquals("", StrLib.toCase("", true));
        assertEquals(" ", StrLib.toCase(" ", true));

        // toCase: value
        assertEquals("ABCDE", StrLib.toCase("aBcdE", true));

        // toCase: null
        assertNull(StrLib.toCase(null, false));

        // toCase: empty
        assertEquals("", StrLib.toCase("", false));
        assertEquals(" ", StrLib.toCase(" ", false));

        // toCase: value
        assertEquals("abcde", StrLib.toCase("aBcdE", false));

    }

    public void testToCamelCase() {

        // toCamelCase: null
        assertNull(StrLib.toCamelCase(null));

        // toCamelCase: empty
        assertEquals("", StrLib.toCamelCase(""));
        assertEquals(" ", StrLib.toCamelCase(" "));

        // toCamelCase: value
        assertEquals("FirstName", StrLib.toCamelCase("first_name"));
        assertEquals("FirstName", StrLib.toCamelCase("first_name", true));
        assertEquals("firstName", StrLib.toCamelCase("first_name", false));

    }

    public void testToSnakeCase() {

        // toSnakeCase: null
        assertNull(StrLib.toSnakeCase(null));

        // toSnakeCase: empty
        assertEquals("", StrLib.toSnakeCase(""));
        assertEquals(" ", StrLib.toSnakeCase(" "));

        // toSnakeCase: value
        assertEquals("first_name", StrLib.toSnakeCase("FirstName"));
        assertEquals("first_name", StrLib.toSnakeCase("FirstName", false));
        assertEquals("FIRST_NAME", StrLib.toSnakeCase("FirstName", true));

    }

    public void testToKebabCase() {

        // toKebabCase: null
        assertNull(StrLib.toKebabCase(null));

        // toKebabCase: empty
        assertEquals("", StrLib.toKebabCase(""));
        assertEquals(" ", StrLib.toKebabCase(" "));

        // toKebabCase: value
        assertEquals("first-name", StrLib.toKebabCase("FirstName"));
        assertEquals("first-name", StrLib.toKebabCase("FirstName", false));
        assertEquals("FIRST-NAME", StrLib.toKebabCase("FirstName", true));

    }

    public void testPrefixSuffix() {

        // removePrefix: null
        assertNull(StrLib.removePrefix(null, null));
        assertNull(StrLib.removePrefix(null, ""));
        assertNull(StrLib.removePrefix(null, " "));
        assertNull(StrLib.removePrefix(null, "prefix"));

        // removePrefix: empty
        assertEquals("", StrLib.removePrefix("", null));
        assertEquals("", StrLib.removePrefix("", ""));
        assertEquals("", StrLib.removePrefix("", "prefix"));

        // removePrefix: value
        assertEquals("abcde", StrLib.removePrefix("abcde", null));
        assertEquals("abcde", StrLib.removePrefix("abcde", ""));
        assertEquals("abcde", StrLib.removePrefix("abcde", " "));
        assertEquals("abcde", StrLib.removePrefix("abcde", "xyz"));

        assertEquals("", StrLib.removePrefix("ab", "ab"));
        assertEquals("cde", StrLib.removePrefix("abcde", "ab"));

        // removeSuffix: null
        assertNull(StrLib.removeSuffix(null, null));
        assertNull(StrLib.removeSuffix(null, ""));
        assertNull(StrLib.removeSuffix(null, " "));
        assertNull(StrLib.removeSuffix(null, "suffix"));

        // removeSuffix: empty
        assertEquals("", StrLib.removeSuffix("", null));
        assertEquals("", StrLib.removeSuffix("", ""));
        assertEquals("", StrLib.removeSuffix("", "suffix"));

        // removeSuffix: value
        assertEquals("abcde", StrLib.removeSuffix("abcde", null));
        assertEquals("abcde", StrLib.removeSuffix("abcde", ""));
        assertEquals("abcde", StrLib.removeSuffix("abcde", " "));
        assertEquals("abcde", StrLib.removeSuffix("abcde", "xyz"));

        assertEquals("", StrLib.removeSuffix("ab", "ab"));
        assertEquals("abc", StrLib.removeSuffix("abcde", "de"));
    }

    public void testCount() {

        assertEquals(0, StrLib.countString("abcde", "xyz"));
        assertEquals(1, StrLib.countString("abcde", "abc"));
        assertEquals(2, StrLib.countString("abcdeabcx", "abc"));

        assertEquals(2, StrLib.countString("abcdeabc", "abc"));
        assertEquals(2, StrLib.countString("abc de abc", "abc"));
        assertEquals(2, StrLib.countString("abc de abc x", "abc"));

        assertEquals(0, StrLib.countChar("abcde", 'z'));
        assertEquals(1, StrLib.countChar("abcde", 'a'));
        assertEquals(2, StrLib.countChar("abcdeabcx", 'a'));

    }

    // isDigit, isISODigit
    public void testIsDigit() {

        println("isDigit: " + Character.isDigit('\u0660'));
        println("isLetter: " + Character.isLetter('\u0660'));
        println("isAlpha: " + Character.isAlphabetic('\u0660'));

        println();
        println("isDigit: " + Character.isDigit('\u00B5'));
        println("isLetter: " + Character.isLetter('\u00B5'));
        println("isAlpha: " + Character.isAlphabetic('\u00B5'));

        println();
        println("isDigit: " + Character.isDigit('\u00B6'));
        println("isLetter: " + Character.isLetter('\u00B6'));
        println("isAlpha: " + Character.isAlphabetic('\u00B6'));

        ///////////////////////////////////////
        //
        // isDigit
        //
        ///////////////////////////////////////

        // ISO-LATIN-1 digits ('0' through '9')
        assertTrue(StrLib.isDigit('0'));
        assertTrue(StrLib.isDigit('1'));
        assertTrue(StrLib.isDigit('2'));
        assertTrue(StrLib.isDigit('3'));
        assertTrue(StrLib.isDigit('4'));
        assertTrue(StrLib.isDigit('5'));
        assertTrue(StrLib.isDigit('6'));
        assertTrue(StrLib.isDigit('7'));
        assertTrue(StrLib.isDigit('8'));
        assertTrue(StrLib.isDigit('9'));

        // Arabic-Indic digits
        assertTrue(StrLib.isDigit('\u0660'));
        assertTrue(StrLib.isDigit('\u0661'));
        assertTrue(StrLib.isDigit('\u0662'));
        assertTrue(StrLib.isDigit('\u0663'));
        assertTrue(StrLib.isDigit('\u0664'));
        assertTrue(StrLib.isDigit('\u0665'));
        assertTrue(StrLib.isDigit('\u0666'));
        assertTrue(StrLib.isDigit('\u0667'));
        assertTrue(StrLib.isDigit('\u0668'));
        assertTrue(StrLib.isDigit('\u0669'));

        // Extended Arabic-Indic digits
        assertTrue(StrLib.isDigit('\u06F0'));
        assertTrue(StrLib.isDigit('\u06F1'));
        assertTrue(StrLib.isDigit('\u06F2'));
        assertTrue(StrLib.isDigit('\u06F3'));
        assertTrue(StrLib.isDigit('\u06F4'));
        assertTrue(StrLib.isDigit('\u06F5'));
        assertTrue(StrLib.isDigit('\u06F6'));
        assertTrue(StrLib.isDigit('\u06F7'));
        assertTrue(StrLib.isDigit('\u06F8'));
        assertTrue(StrLib.isDigit('\u06F9'));

        ///////////////////////////////////////
        //
        // isISODigit
        //
        ///////////////////////////////////////

        assertTrue(StrLib.isISODigit('0'));
        assertTrue(StrLib.isISODigit('1'));
        assertTrue(StrLib.isISODigit('2'));
        assertTrue(StrLib.isISODigit('3'));
        assertTrue(StrLib.isISODigit('4'));
        assertTrue(StrLib.isISODigit('5'));
        assertTrue(StrLib.isISODigit('6'));
        assertTrue(StrLib.isISODigit('7'));
        assertTrue(StrLib.isISODigit('8'));
        assertTrue(StrLib.isISODigit('9'));

        // false (all), because it is not ISO digit
        assertFalse(StrLib.isISODigit('\u0660'));
        assertFalse(StrLib.isISODigit('\u0661'));
        assertFalse(StrLib.isISODigit('\u0662'));
        assertFalse(StrLib.isISODigit('\u0663'));
        assertFalse(StrLib.isISODigit('\u0664'));
        assertFalse(StrLib.isISODigit('\u0665'));
        assertFalse(StrLib.isISODigit('\u0666'));
        assertFalse(StrLib.isISODigit('\u0667'));
        assertFalse(StrLib.isISODigit('\u0668'));
        assertFalse(StrLib.isISODigit('\u0669'));

        // Extended Arabic-Indic digits
        assertFalse(StrLib.isISODigit('\u06F0'));
        assertFalse(StrLib.isISODigit('\u06F1'));
        assertFalse(StrLib.isISODigit('\u06F2'));
        assertFalse(StrLib.isISODigit('\u06F3'));
        assertFalse(StrLib.isISODigit('\u06F4'));
        assertFalse(StrLib.isISODigit('\u06F5'));
        assertFalse(StrLib.isISODigit('\u06F6'));
        assertFalse(StrLib.isISODigit('\u06F7'));
        assertFalse(StrLib.isISODigit('\u06F8'));
        assertFalse(StrLib.isISODigit('\u06F9'));

    }

    public void testIsISO() {
        char x = ' ';
        println("" + (int) x + ": '" + x + "'");

        assertTrue(StrLib.isISOWhitespace(' '));
        assertTrue(StrLib.isISOUnderline('_'));
        assertTrue(StrLib.isISODot('.'));

        x = '\u00B7';
        println("" + (int) x + ": '" + x + "'");

        assertFalse(StrLib.isISOWhitespace('#'));
        assertFalse(StrLib.isISOUnderline('-')); // minus is not underline
        assertFalse(StrLib.isISODot('\u00B7')); // it is displayed like dot but it is not dot :)
    }

    public void testIsISOIdentifier() {

        char x = 'x';
        println("" + (int) x + ": '" + x + "'");

        assertTrue(StrLib.isISOLowerLetter(x));
        assertTrue(StrLib.isISOLetter(x));

        assertTrue(StrLib.isISOIdentifier("abc"));
        assertTrue(StrLib.isISOIdentifier("abc123"));
        assertTrue(StrLib.isISOIdentifier("abc123xyz"));

        assertTrue(StrLib.isISOIdentifier("_abc"));
        assertTrue(StrLib.isISOIdentifier("_abc123"));
        assertTrue(StrLib.isISOIdentifier("_abc123xyz"));

        assertTrue(StrLib.isISOIdentifier("_abc_"));
        assertTrue(StrLib.isISOIdentifier("_abc123_"));
        assertTrue(StrLib.isISOIdentifier("_abc123xyz_"));

        assertTrue(StrLib.isISOIdentifier("_ab_c"));
        assertTrue(StrLib.isISOIdentifier("_abc_123"));
        assertTrue(StrLib.isISOIdentifier("_abc_123_xyz"));

        assertFalse(StrLib.isISOIdentifier("__"));
        assertFalse(StrLib.isISOIdentifier("_2_"));
        assertTrue(StrLib.isISOIdentifier("_a_"));
        // int z = _2;
    }
}
