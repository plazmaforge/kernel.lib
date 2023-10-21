package plazma.lib.str;

import plazma.lib.AbstractTestCase;
import plazma.lib.str.StrLib;

public class StrLibTest extends AbstractTestCase {

    // 1.1

    public void testIsEmpty() {

        // isEmpty(null), isEmpty(empty)
        assertTrue(StrLib.isEmpty(null));
        assertTrue(StrLib.isEmpty(""));

        // isEmpty(blank)
        assertFalse(StrLib.isEmpty(" "));
        assertFalse(StrLib.isEmpty("  "));

        // isEmpty(value)
        assertFalse(StrLib.isEmpty("abc"));
        assertFalse(StrLib.isEmpty(" abc"));
        assertFalse(StrLib.isEmpty("abc "));
        assertFalse(StrLib.isEmpty(" abc "));
    }

    public void testIsBlank() {

        // isBlank(null), isBlank(empty)
        assertTrue(StrLib.isBlank(null));
        assertTrue(StrLib.isBlank(""));

        // isBlank(blank)
        assertTrue(StrLib.isBlank(" "));
        assertTrue(StrLib.isBlank("  "));

        // isBlank(value)
        assertFalse(StrLib.isBlank("abc"));
        assertFalse(StrLib.isBlank(" abc"));
        assertFalse(StrLib.isBlank("abc "));
        assertFalse(StrLib.isBlank(" abc "));        
    }

    public void testSize() {

        // size(null), size(empty)
        assertEquals(0, StrLib.size(null));
        assertEquals(0, StrLib.size(""));

        // size(blank)
        assertEquals(1, StrLib.size(" "));
        assertEquals(2, StrLib.size("  "));

        // size(value)
        assertEquals(3, StrLib.size("abc"));
        assertEquals(4, StrLib.size(" abc"));
        assertEquals(4, StrLib.size("abc "));
        assertEquals(5, StrLib.size(" abc "));

    }

    public void testEquals() {

        // equals(null, null)
        assertFalse(StrLib.equals(null, null));

        // equals(null, empty), equals(null, value)
        assertFalse(StrLib.equals(null, ""));
        assertFalse(StrLib.equals(null, " "));
        assertFalse(StrLib.equals(null, "abc"));

        // equals(empty, null), equals(value, null)
        assertFalse(StrLib.equals("", null));
        assertFalse(StrLib.equals(" ", null));
        assertFalse(StrLib.equals("abc", null));

        // equals(empty, value), equals(value, empty)
        assertFalse(StrLib.equals("", "abc"));
        assertFalse(StrLib.equals("abc", ""));

        // equals(empty, empty), equals(blank, blank)
        assertTrue(StrLib.equals("", ""));
        assertTrue(StrLib.equals(" ", " "));
        assertTrue(StrLib.equals("  ", "  "));

        // equals(empty, blank), equals(blank, empty)
        assertFalse(StrLib.equals("", " "));
        assertFalse(StrLib.equals("", "  "));
        assertFalse(StrLib.equals(" ", ""));
        assertFalse(StrLib.equals("  ", ""));

        // equals(value, value)
        assertFalse(StrLib.equals(" abc", "abc"));
        assertFalse(StrLib.equals("abc ", "abc"));
        assertFalse(StrLib.equals(" abc ", "abc"));

        assertTrue(StrLib.equals("abc", "abc"));
        assertTrue(StrLib.equals(" abc", " abc"));
        assertTrue(StrLib.equals("abc ", "abc "));
        assertTrue(StrLib.equals(" abc ", " abc "));        

    }

    // 1.2
    
    public void testNormalize() {

        // normalize(null), normalize(empty)
        assertNull(StrLib.normalize(null));
        assertNull(StrLib.normalize(""));

        // normalize(blank)
        assertNull(StrLib.normalize(" "));
        assertNull(StrLib.normalize("  "));

        // normalize(value)
        assertEquals("abc", StrLib.normalize("abc"));
        assertEquals("abc", StrLib.normalize(" abc"));
        assertEquals("abc", StrLib.normalize("abc "));
        assertEquals("abc", StrLib.normalize(" abc "));

        // normalize(" \t\n\r\f\v")
        assertNull(StrLib.normalize(" \t\n\r\f\u000B"));                             // \v -> \u000B
        assertEquals("abc", StrLib.normalize(" \t\n\r\f\u000Babc"));                 // \v -> \u000B
        assertEquals("abc", StrLib.normalize("abc\t\n\r\f\u000B "));                 // \v -> \u000B
        assertEquals("abc", StrLib.normalize(" \t\n\r\f\u000Babc\t\n\r\f\u000B "));  // \v -> \u000B

    }

    public void testNormalizeSafe() {

        // normalizeSafe(null), normalizeSafe(empty)
        assertEquals("", StrLib.normalizeSafe(null));
        assertEquals("", StrLib.normalizeSafe(""));

        // normalizeSafe(blank)
        assertEquals("", StrLib.normalizeSafe(" "));
        assertEquals("", StrLib.normalizeSafe("  "));

        // normalizeSafe(value)
        assertEquals("abc", StrLib.normalizeSafe("abc"));
        assertEquals("abc", StrLib.normalizeSafe(" abc"));
        assertEquals("abc", StrLib.normalizeSafe("abc "));
        assertEquals("abc", StrLib.normalizeSafe(" abc "));

        // normalizeSafe(" \t\n\r\f\v")
        assertEquals("", StrLib.normalizeSafe(" \t\n\r\f\u000B"));                      // \v -> \u000B
        assertEquals("abc", StrLib.normalizeSafe(" \t\n\r\f\u000Babc"));                 // \v -> \u000B
        assertEquals("abc", StrLib.normalizeSafe("abc\t\n\r\f\u000B "));                 // \v -> \u000B
        assertEquals("abc", StrLib.normalizeSafe(" \t\n\r\f\u000Babc\t\n\r\f\u000B "));  // \v -> \u000B

    }

    public void testNormalizeBlank() {

        // false, false
        // normalizeBlank(null, false, false), normalizeBlank(empty, false, false)
        assertNull(StrLib.normalizeBlank(null, false, false));
        assertEquals("", StrLib.normalizeBlank("", false, false));

        // normalizeBlank(blank, false, false)
        assertEquals(" ", StrLib.normalizeBlank(" ", false, false));
        assertEquals("  ", StrLib.normalizeBlank("  ", false, false));

        // normalizeBlank(value, false, false)
        assertEquals("abc", StrLib.normalizeBlank("abc", false, false));
        assertEquals(" abc", StrLib.normalizeBlank(" abc", false, false));
        assertEquals("abc ", StrLib.normalizeBlank("abc ", false, false));
        assertEquals(" abc ", StrLib.normalizeBlank(" abc ", false, false));

        // false, true
        // normalizeBlank(null, false, true), normalizeBlank(empty, false, true)
        assertNull(StrLib.normalizeBlank(null, false, true));
        assertNull(StrLib.normalizeBlank("", false, true));

        // normalizeBlank(blank, false, true)
        assertNull(StrLib.normalizeBlank(" ", false, true));
        assertNull(StrLib.normalizeBlank("  ", false, true));

        // normalizeBlank(value, false, true)
        assertEquals("abc", StrLib.normalizeBlank("abc", false, true));
        assertEquals(" abc", StrLib.normalizeBlank(" abc", false, true));
        assertEquals("abc ", StrLib.normalizeBlank("abc ", false, true));
        assertEquals(" abc ", StrLib.normalizeBlank(" abc ", false, true));

        // true, true
        // normalizeBlank(null, true, true), normalizeBlank(empty, true, true)
        assertNull(StrLib.normalizeBlank(null, true, true));
        assertNull(StrLib.normalizeBlank("", true, true));

        // normalizeBlank(blank, true, true)
        assertNull(StrLib.normalizeBlank(" ", true, true));
        assertNull(StrLib.normalizeBlank("  ", true, true));

        // normalizeBlank(value, true, true)
        assertEquals("abc", StrLib.normalizeBlank("abc", true, true));
        assertEquals("abc", StrLib.normalizeBlank(" abc", true, true));
        assertEquals("abc", StrLib.normalizeBlank("abc ", true, true));
        assertEquals("abc", StrLib.normalizeBlank(" abc ", true, true));

        // true, false
        // normalizeBlank(null, true, false), normalizeBlank(empty, true, false)
        assertNull(StrLib.normalizeBlank(null, true, false));
        assertNull(StrLib.normalizeBlank("", true, false));

        // normalizeBlank(blank, true, false)
        assertNull(StrLib.normalizeBlank(" ", true, false));
        assertNull(StrLib.normalizeBlank("  ", true, false));

        // normalizeBlank(value, true, false)
        assertEquals("abc", StrLib.normalizeBlank("abc", true, false));
        assertEquals("abc", StrLib.normalizeBlank(" abc", true, false));
        assertEquals("abc", StrLib.normalizeBlank("abc ", true, false));
        assertEquals("abc", StrLib.normalizeBlank(" abc ", true, false));

    }
    
    // emptyIfNull(String str)
    // nullIfEmpty(String str)
    // nullIfEmpty(String str, boolean trim)

    public void testDefaultIfNull() {
        
        // defaultIffNull(null/empty, null/empty)
        assertNull(StrLib.defaultIfNull(null, null));
        assertEquals("", StrLib.defaultIfNull("", null));        
        assertEquals("", StrLib.defaultIfNull(null, ""));        
        assertEquals("", StrLib.defaultIfNull("", ""));
        
        assertEquals(" ", StrLib.defaultIfNull(" ", null));        
        assertEquals(" ", StrLib.defaultIfNull(null, " "));        
        assertEquals("", StrLib.defaultIfNull("", " "));     // 1
        assertEquals(" ", StrLib.defaultIfNull(" ", " "));

        assertEquals(" ", StrLib.defaultIfNull(" ", ""));        
        assertEquals("", StrLib.defaultIfNull("", " "));     // 2        
        assertEquals("", StrLib.defaultIfNull("", " "));     // 3

        assertEquals(".", StrLib.defaultIfNull(".", null));        
        assertEquals(".", StrLib.defaultIfNull(null, "."));        
        assertEquals("", StrLib.defaultIfNull("", "."));     // 4
        assertEquals(".", StrLib.defaultIfNull(".", "."));

        assertEquals(".", StrLib.defaultIfNull(".", ""));        
        assertEquals("", StrLib.defaultIfNull("", "."));     // 5        
        assertEquals("", StrLib.defaultIfNull("", "."));     // 6

    }

    public void testDefaultIfEmpty() {
        
        // defaultIfEmpty(null/empty, null/empty)
        assertNull(StrLib.defaultIfEmpty(null, null));
        assertNull(StrLib.defaultIfEmpty("", null));        
        assertEquals("", StrLib.defaultIfEmpty(null, ""));        
        assertEquals("", StrLib.defaultIfEmpty("", ""));
        
        assertEquals(" ", StrLib.defaultIfEmpty(" ", null));        
        assertEquals(" ", StrLib.defaultIfEmpty(null, " "));        
        assertEquals(" ", StrLib.defaultIfEmpty("", " "));
        assertEquals(" ", StrLib.defaultIfEmpty(" ", " "));

        assertEquals(" ", StrLib.defaultIfEmpty(" ", ""));        
        assertEquals(" ", StrLib.defaultIfEmpty("", " "));        
        assertEquals(" ", StrLib.defaultIfEmpty("", " "));

        assertEquals(".", StrLib.defaultIfEmpty(".", null));        
        assertEquals(".", StrLib.defaultIfEmpty(null, "."));        
        assertEquals(".", StrLib.defaultIfEmpty("", "."));
        assertEquals(".", StrLib.defaultIfEmpty(".", "."));

        assertEquals(".", StrLib.defaultIfEmpty(".", ""));        
        assertEquals(".", StrLib.defaultIfEmpty("", "."));        
        assertEquals(".", StrLib.defaultIfEmpty("", "."));

    }
    
    // 1.3

    public void testTrim() {

        // trim(null), trim(empty)
        assertNull(StrLib.trim(null));
        assertEquals("", StrLib.trim(""));

        // trim(blank)
        assertEquals("", StrLib.trim(" "));
        assertEquals("", StrLib.trim("  "));

        // trim(value)
        assertEquals("abc", StrLib.trim("abc"));
        assertEquals("abc", StrLib.trim(" abc"));
        assertEquals("abc", StrLib.trim("abc "));
        assertEquals("abc", StrLib.trim(" abc "));
        //assertEquals("a b c", StrLib.trim(" a b c "));

        // trim(" \t\n\r\f\v")
        assertEquals("", StrLib.trim(" \t\n\r\f\u000B"));                       // \v -> \u000B
        assertEquals("abc", StrLib.trim(" \t\n\r\f\u000Babc"));                 // \v -> \u000B
        assertEquals("abc", StrLib.trim("abc \t\n\r\f\u000B"));                 // \v -> \u000B
        assertEquals("abc", StrLib.trim(" \t\n\r\f\u000Babc \t\n\r\f\u000B"));  // \v -> \u000B

        // trim(value, char)
        assertEquals("abc", StrLib.trim("**abc***", '*'));
        assertEquals("abc", StrLib.trim("++abc+++", '+'));

        assertEquals("abc", StrLib.trim("abc***", '*'));
        assertEquals("abc", StrLib.trim("***abc", '*'));

        // trim(value, chars)
        assertEquals("abc", StrLib.trim("**abc**", "*+-"));
        assertEquals("abc", StrLib.trim("**abc**", "*+-"));
        assertEquals("abc", StrLib.trim("*+*abc**-", "*+-"));
        
        assertEquals("abc", StrLib.trim("abc\r\n", "\r\n"));
        assertEquals(" abc", StrLib.trim(" abc\r\n", "\r\n"));
        
    }

    public void testTrimAll() {

        // trimAll(null), trimAll(empty)
        assertNull(StrLib.trimAll(null));
        assertEquals("", StrLib.trimAll(""));

        // trimAll(blank)
        assertEquals("", StrLib.trimAll(" "));
        assertEquals("", StrLib.trimAll("  "));

        // trimAll(value)
        assertEquals("abc", StrLib.trimAll("abc"));
        assertEquals("abc", StrLib.trimAll(" abc"));
        assertEquals("abc", StrLib.trimAll("abc "));
        assertEquals("abc", StrLib.trimAll(" abc "));
        
        // trimAll(" \t\n\r\f\v")
        assertEquals("", StrLib.trimAll(" \t\n\r\f\u000B"));                       // \v -> \u000B
        assertEquals("abc", StrLib.trimAll(" \t\n\r\f\u000Babc"));                 // \v -> \u000B
        assertEquals("abc", StrLib.trimAll("abc \t\n\r\f\u000B"));                 // \v -> \u000B
        assertEquals("abc", StrLib.trimAll(" \t\n\r\f\u000Babc \t\n\r\f\u000B"));  // \v -> \u000B

    }

    public void testLtrim() {

        // ltrim(null), ltrim(empty)
        assertNull(StrLib.ltrim(null));
        assertEquals("", StrLib.ltrim(""));

        // ltrim(blank)
        assertEquals("", StrLib.ltrim(" "));
        assertEquals("", StrLib.ltrim("  "));

        // ltrim(value)
        assertEquals("abc", StrLib.ltrim("abc"));
        assertEquals("abc", StrLib.ltrim(" abc"));
        assertEquals("abc ", StrLib.ltrim("abc "));
        assertEquals("abc ", StrLib.ltrim(" abc "));                        
        //assertEquals("a b c ", StrLib.ltrim(" a b c "));

        // ltrim(value, char)
        assertEquals("abc", StrLib.ltrim("...abc", '.'));
        assertEquals("abc..", StrLib.ltrim("...abc..", '.'));
        assertEquals("abc", StrLib.ltrim("\rabc", '\r'));
        assertEquals(" abc", StrLib.ltrim("\r abc", '\r'));
        assertEquals("abc\r", StrLib.ltrim("\rabc\r", '\r'));
        assertEquals("abc\r", StrLib.ltrim("\rabc\r", '\r'));

        // ltrim(value, chars)
        assertEquals("abc", StrLib.ltrim("...abc", "."));
        assertEquals("abc..", StrLib.ltrim("...abc..", "."));
        assertEquals("abc", StrLib.ltrim("\r\nabc", "\r\n"));
        assertEquals(" abc", StrLib.ltrim("\r\n abc", "\r\n"));
        assertEquals("abc\r\n", StrLib.ltrim("\r\nabc\r\n", "\r\n"));
        assertEquals("abc\r\n", StrLib.ltrim("\r\nabc\r\n", "\r\n"));

    }
    
    public void testRtrim() {

        // rtrim(null), rtrim(empty)
        assertNull(StrLib.rtrim(null));
        assertEquals("", StrLib.rtrim(""));

        // rtrim(blank)        
        assertEquals("", StrLib.rtrim(" "));
        assertEquals("", StrLib.rtrim("  "));
        
        // rtrim(value)
        assertEquals("abc", StrLib.rtrim("abc"));
        assertEquals(" abc", StrLib.rtrim(" abc"));
        assertEquals("abc", StrLib.rtrim("abc "));
        assertEquals(" abc", StrLib.rtrim(" abc "));
        //assertEquals(" a b c", StrLib.rtrim(" a b c "));

        // rtrim(value, char)
        assertEquals("abc", StrLib.rtrim("abc...", '.'));
        assertEquals("..abc", StrLib.rtrim("..abc...", '.'));
        assertEquals("abc", StrLib.rtrim("abc\r", '\r'));
        assertEquals(" abc", StrLib.rtrim(" abc\r", '\r'));
        assertEquals("\rabc", StrLib.rtrim("\rabc\r", '\r'));
        assertEquals("\rabc", StrLib.rtrim("\rabc\r", '\r'));

        // rtrim(value, chars)
        assertEquals("abc", StrLib.rtrim("abc...", "."));
        assertEquals("..abc", StrLib.rtrim("..abc...", "."));
        assertEquals("abc", StrLib.rtrim("abc\r\n", "\r\n"));
        assertEquals(" abc", StrLib.rtrim(" abc\r\n", "\r\n"));
        assertEquals("\r\nabc", StrLib.rtrim("\r\nabc\r\n", "\r\n"));
        assertEquals("\r\nabc", StrLib.rtrim("\r\nabc\r\n", "\r\n"));

    }
    
    public void testContains() {
        
        // char
        
        // False: contains(null/empty, null/empty)
        assertFalse(StrLib.contains(null, null));
        assertFalse(StrLib.contains("", null));
        assertFalse(StrLib.contains(null, '\0'));
        assertFalse(StrLib.contains("", '\0'));

        assertFalse(StrLib.contains(" ", null));
        assertFalse(StrLib.contains(null, ' '));

        // False: contains(blank, blank)
        assertTrue(StrLib.contains(" ", ' '));
        assertTrue(StrLib.contains("  ", ' '));
        assertTrue(StrLib.contains(".", '.'));

        // True: contains(value, value)
        assertTrue(StrLib.contains("a", 'a'));
        assertTrue(StrLib.contains("abc", 'a'));
        assertTrue(StrLib.contains("abc", 'b'));
        assertTrue(StrLib.contains("abc", 'c'));

        // False: contains(value, value)
        assertFalse(StrLib.contains("abc", '.'));
        assertFalse(StrLib.contains("abc", 'x'));
        assertFalse(StrLib.contains("abc", 'y'));
        assertFalse(StrLib.contains("abc", 'z'));
        
        
        // string
        
        // False: contains(null/empty, null/empty)
        assertFalse(StrLib.contains(null, null));
        assertFalse(StrLib.contains("", null));
        assertFalse(StrLib.contains(null, ""));
        assertFalse(StrLib.contains("", ""));

        assertFalse(StrLib.contains(" ", null));
        assertFalse(StrLib.contains(null, " "));

        // False: contains(blank, blank)
        assertTrue(StrLib.contains(" ", " "));
        assertTrue(StrLib.contains("  ", " "));
        assertTrue(StrLib.contains(".", "."));

        // True: contains(value, value)
        assertTrue(StrLib.contains("a", "a"));
        assertTrue(StrLib.contains("abc", "a"));
        assertTrue(StrLib.contains("abc", "b"));
        assertTrue(StrLib.contains("abc", "c"));

        assertTrue(StrLib.contains("abc", "ab"));
        assertTrue(StrLib.contains("abc", "bc"));
        assertTrue(StrLib.contains("abc", "abc"));

        // False: contains(value, value)
        assertFalse(StrLib.contains("abc", "ac"));

        assertFalse(StrLib.contains("abc", "."));
        assertFalse(StrLib.contains("abc", "x"));
        assertFalse(StrLib.contains("abc", "y"));
        assertFalse(StrLib.contains("abc", "z"));
        
        assertFalse(StrLib.contains("abc", "def"));
        assertFalse(StrLib.contains("abc", "xyz"));
        
    }
    
    public void testFindFirst() {

        // char
        
        // NotFound: null/empty
        assertEquals(-1, StrLib.findFirst(null, '\0'));
        assertEquals(-1, StrLib.findFirst("", '\0'));
        assertEquals(-1, StrLib.findFirst(null, ' '));
        assertEquals(-1, StrLib.findFirst("", ' '));
        assertEquals(-1, StrLib.findFirst(null, '.'));
        assertEquals(-1, StrLib.findFirst("", '.'));
        
        // NotFound: null/empty, pos
        assertEquals(-1, StrLib.findFirst(null, '\0', 0));
        assertEquals(-1, StrLib.findFirst("", '\0', 0));
        assertEquals(-1, StrLib.findFirst(null, ' ', 0));
        assertEquals(-1, StrLib.findFirst("", ' ', 0));
        assertEquals(-1, StrLib.findFirst(null, '.', 0));
        assertEquals(-1, StrLib.findFirst("", '.', 0));

        assertEquals(-1, StrLib.findFirst(null, '\0', -1));
        assertEquals(-1, StrLib.findFirst("", '\0', -1));
        assertEquals(-1, StrLib.findFirst(null, ' ', -1));
        assertEquals(-1, StrLib.findFirst("", ' ', -1));
        assertEquals(-1, StrLib.findFirst(null, '.', -1));
        assertEquals(-1, StrLib.findFirst("", '.', -1));

        assertEquals(-1, StrLib.findFirst(null, '\0', 1));
        assertEquals(-1, StrLib.findFirst("", '\0', 1));
        assertEquals(-1, StrLib.findFirst(null, ' ', 1));
        assertEquals(-1, StrLib.findFirst("", ' ', 1));
        assertEquals(-1, StrLib.findFirst(null, '.', 1));
        assertEquals(-1, StrLib.findFirst("", '.', 1));
        
        // NotFound: blank/value
        assertEquals(0, StrLib.findFirst(" ", ' '));
        assertEquals(0, StrLib.findFirst("  ", ' '));
        
        // Found: blank/value
        assertEquals(0, StrLib.findFirst(" .", ' '));
        assertEquals(0, StrLib.findFirst("  .", ' '));

        assertEquals(1, StrLib.findFirst(". ", ' '));
        assertEquals(1, StrLib.findFirst(".  ", ' '));

        // Found: value
        assertEquals(0, StrLib.findFirst("*", '*'));

        assertEquals(1, StrLib.findFirst(".*", '*'));
        assertEquals(2, StrLib.findFirst("..*", '*'));
        assertEquals(3, StrLib.findFirst("...*", '*'));

        assertEquals(0, StrLib.findFirst("*.", '*'));
        assertEquals(0, StrLib.findFirst("*..", '*'));
        assertEquals(0, StrLib.findFirst("*...", '*'));

        // NotFound: value, pos, min range
        assertEquals(-1, StrLib.findFirst("**..**..", '*', -1));
        assertEquals(-1, StrLib.findFirst("**..**..", '*', -2));

        // Found: value, pos
        assertEquals(0, StrLib.findFirst("**..**..", '*', 0));
        assertEquals(1, StrLib.findFirst("**..**..", '*', 1));
        assertEquals(4, StrLib.findFirst("**..**..", '*', 2));
        assertEquals(4, StrLib.findFirst("**..**..", '*', 3));
        assertEquals(4, StrLib.findFirst("**..**..", '*', 4));
        assertEquals(5, StrLib.findFirst("**..**..", '*', 5));

        // NotFound: value, pos
        assertEquals(-1, StrLib.findFirst("**..**..", '*', 6));
        assertEquals(-1, StrLib.findFirst("**..**..", '*', 7));

        // NotFound: value, pos, max range
        assertEquals(-1, StrLib.findFirst("**..**..", '*', 8));
        assertEquals(-1, StrLib.findFirst("**..**..", '*', 9));

    }
    
    public void testFindLast() {

        // char
        
        // NotFound: null/empty
        assertEquals(-1, StrLib.findLast(null, '\0'));
        assertEquals(-1, StrLib.findLast("", '\0'));
        assertEquals(-1, StrLib.findLast(null, ' '));
        assertEquals(-1, StrLib.findLast("", ' '));
        assertEquals(-1, StrLib.findLast(null, '.'));
        assertEquals(-1, StrLib.findLast("", '.'));
        
        // NotFound: null/empty, pos
        assertEquals(-1, StrLib.findLast(null, '\0', 0));
        assertEquals(-1, StrLib.findLast("", '\0', 0));
        assertEquals(-1, StrLib.findLast(null, ' ', 0));
        assertEquals(-1, StrLib.findLast("", ' ', 0));
        assertEquals(-1, StrLib.findLast(null, '.', 0));
        assertEquals(-1, StrLib.findLast("", '.', 0));

        assertEquals(-1, StrLib.findLast(null, '\0', -1));
        assertEquals(-1, StrLib.findLast("", '\0', -1));
        assertEquals(-1, StrLib.findLast(null, ' ', -1));
        assertEquals(-1, StrLib.findLast("", ' ', -1));
        assertEquals(-1, StrLib.findLast(null, '.', -1));
        assertEquals(-1, StrLib.findLast("", '.', -1));

        assertEquals(-1, StrLib.findLast(null, '\0', 1));
        assertEquals(-1, StrLib.findLast("", '\0', 1));
        assertEquals(-1, StrLib.findLast(null, ' ', 1));
        assertEquals(-1, StrLib.findLast("", ' ', 1));
        assertEquals(-1, StrLib.findLast(null, '.', 1));
        assertEquals(-1, StrLib.findLast("", '.', 1));
        
        // Found: blank
        assertEquals(0, StrLib.findLast(" ", ' '));
        assertEquals(1, StrLib.findLast("  ", ' '));

        // Found: blank/value
        assertEquals(0, StrLib.findLast(" .", ' '));
        assertEquals(1, StrLib.findLast("  .", ' '));

        assertEquals(1, StrLib.findLast(". ", ' '));
        assertEquals(2, StrLib.findLast(".  ", ' '));

        // Found: value
        assertEquals(0, StrLib.findLast("*", '*'));

        assertEquals(0, StrLib.findLast("*.", '*'));
        assertEquals(0, StrLib.findLast("*..", '*'));
        assertEquals(0, StrLib.findLast("*...", '*'));

        assertEquals(1, StrLib.findLast(".*", '*'));
        assertEquals(2, StrLib.findLast("..*", '*'));
        assertEquals(3, StrLib.findLast("...*", '*'));

        // NotFound: value, pos, min range
        assertEquals(-1, StrLib.findLast("..**..**", '*', -1));
        assertEquals(-1, StrLib.findLast("..**..**", '*', -2));

        // NotFound: value, pos
        assertEquals(-1, StrLib.findLast("..**..**", '*', 0));
        assertEquals(-1, StrLib.findLast("..**..**", '*', 1));

        // Found: value, pos
        assertEquals(2, StrLib.findLast("..**..**", '*', 2));
        assertEquals(3, StrLib.findLast("..**..**", '*', 3));
        assertEquals(3, StrLib.findLast("..**..**", '*', 4));
        assertEquals(3, StrLib.findLast("..**..**", '*', 5));
        assertEquals(6, StrLib.findLast("..**..**", '*', 6));
        assertEquals(7, StrLib.findLast("..**..**", '*', 7));

        // NotFound: value, pos, max range
        assertEquals(-1, StrLib.findLast("..**..**", '*', 8));
        assertEquals(-1, StrLib.findLast("..**..**", '*', 9));
                
    }


    public void testFindFirstOf() {

        // char
        
        // NotFound: null/empty
        assertEquals(-1, StrLib.findFirstOf(null, '\0'));
        assertEquals(-1, StrLib.findFirstOf("", '\0'));
        assertEquals(-1, StrLib.findFirstOf(null, ' '));
        assertEquals(-1, StrLib.findFirstOf("", ' '));
        assertEquals(-1, StrLib.findFirstOf(null, '.'));
        assertEquals(-1, StrLib.findFirstOf("", '.'));
        
        // NotFound: null/empty, pos
        assertEquals(-1, StrLib.findFirstOf(null, '\0', 0));
        assertEquals(-1, StrLib.findFirstOf("", '\0', 0));
        assertEquals(-1, StrLib.findFirstOf(null, ' ', 0));
        assertEquals(-1, StrLib.findFirstOf("", ' ', 0));
        assertEquals(-1, StrLib.findFirstOf(null, '.', 0));
        assertEquals(-1, StrLib.findFirstOf("", '.', 0));

        assertEquals(-1, StrLib.findFirstOf(null, '\0', -1));
        assertEquals(-1, StrLib.findFirstOf("", '\0', -1));
        assertEquals(-1, StrLib.findFirstOf(null, ' ', -1));
        assertEquals(-1, StrLib.findFirstOf("", ' ', -1));
        assertEquals(-1, StrLib.findFirstOf(null, '.', -1));
        assertEquals(-1, StrLib.findFirstOf("", '.', -1));

        assertEquals(-1, StrLib.findFirstOf(null, '\0', 1));
        assertEquals(-1, StrLib.findFirstOf("", '\0', 1));
        assertEquals(-1, StrLib.findFirstOf(null, ' ', 1));
        assertEquals(-1, StrLib.findFirstOf("", ' ', 1));
        assertEquals(-1, StrLib.findFirstOf(null, '.', 1));
        assertEquals(-1, StrLib.findFirstOf("", '.', 1));
        
        // NotFound: blank/value
        assertEquals(0, StrLib.findFirstOf(" ", ' '));
        assertEquals(0, StrLib.findFirstOf("  ", ' '));
        
        // Found: blank/value
        assertEquals(0, StrLib.findFirstOf(" .", ' '));
        assertEquals(0, StrLib.findFirstOf("  .", ' '));

        assertEquals(1, StrLib.findFirstOf(". ", ' '));
        assertEquals(1, StrLib.findFirstOf(".  ", ' '));

        // Found: value
        assertEquals(0, StrLib.findFirstOf("*", '*'));

        assertEquals(1, StrLib.findFirstOf(".*", '*'));
        assertEquals(2, StrLib.findFirstOf("..*", '*'));
        assertEquals(3, StrLib.findFirstOf("...*", '*'));

        assertEquals(0, StrLib.findFirstOf("*.", '*'));
        assertEquals(0, StrLib.findFirstOf("*..", '*'));
        assertEquals(0, StrLib.findFirstOf("*...", '*'));

        // NotFound: value, pos, min range
        assertEquals(-1, StrLib.findFirstOf("**..**..", '*', -1));
        assertEquals(-1, StrLib.findFirstOf("**..**..", '*', -2));

        // Found: value, pos
        assertEquals(0, StrLib.findFirstOf("**..**..", '*', 0));
        assertEquals(1, StrLib.findFirstOf("**..**..", '*', 1));
        assertEquals(4, StrLib.findFirstOf("**..**..", '*', 2));
        assertEquals(4, StrLib.findFirstOf("**..**..", '*', 3));
        assertEquals(4, StrLib.findFirstOf("**..**..", '*', 4));
        assertEquals(5, StrLib.findFirstOf("**..**..", '*', 5));

        // NotFound: value, pos
        assertEquals(-1, StrLib.findFirstOf("**..**..", '*', 6));
        assertEquals(-1, StrLib.findFirstOf("**..**..", '*', 7));

        // NotFound: value, pos, max range
        assertEquals(-1, StrLib.findFirstOf("**..**..", '*', 8));
        assertEquals(-1, StrLib.findFirstOf("**..**..", '*', 9));

    }

    public void testFindLastOf() {

        // char
        
        // NotFound: null/empty
        assertEquals(-1, StrLib.findLastOf(null, '\0'));
        assertEquals(-1, StrLib.findLastOf("", '\0'));
        assertEquals(-1, StrLib.findLastOf(null, ' '));
        assertEquals(-1, StrLib.findLastOf("", ' '));
        assertEquals(-1, StrLib.findLastOf(null, '.'));
        assertEquals(-1, StrLib.findLastOf("", '.'));
        
        // NotFound: null/empty, pos
        assertEquals(-1, StrLib.findLastOf(null, '\0', 0));
        assertEquals(-1, StrLib.findLastOf("", '\0', 0));
        assertEquals(-1, StrLib.findLastOf(null, ' ', 0));
        assertEquals(-1, StrLib.findLastOf("", ' ', 0));
        assertEquals(-1, StrLib.findLastOf(null, '.', 0));
        assertEquals(-1, StrLib.findLastOf("", '.', 0));

        assertEquals(-1, StrLib.findLastOf(null, '\0', -1));
        assertEquals(-1, StrLib.findLastOf("", '\0', -1));
        assertEquals(-1, StrLib.findLastOf(null, ' ', -1));
        assertEquals(-1, StrLib.findLastOf("", ' ', -1));
        assertEquals(-1, StrLib.findLastOf(null, '.', -1));
        assertEquals(-1, StrLib.findLastOf("", '.', -1));

        assertEquals(-1, StrLib.findLastOf(null, '\0', 1));
        assertEquals(-1, StrLib.findLastOf("", '\0', 1));
        assertEquals(-1, StrLib.findLastOf(null, ' ', 1));
        assertEquals(-1, StrLib.findLastOf("", ' ', 1));
        assertEquals(-1, StrLib.findLastOf(null, '.', 1));
        assertEquals(-1, StrLib.findLastOf("", '.', 1));
        
        // Found: blank
        assertEquals(0, StrLib.findLastOf(" ", ' '));
        assertEquals(1, StrLib.findLastOf("  ", ' '));

        // Found: blank/value
        assertEquals(0, StrLib.findLastOf(" .", ' '));
        assertEquals(1, StrLib.findLastOf("  .", ' '));

        assertEquals(1, StrLib.findLastOf(". ", ' '));
        assertEquals(2, StrLib.findLastOf(".  ", ' '));

        // Found: value
        assertEquals(0, StrLib.findLastOf("*", '*'));

        assertEquals(0, StrLib.findLastOf("*.", '*'));
        assertEquals(0, StrLib.findLastOf("*..", '*'));
        assertEquals(0, StrLib.findLastOf("*...", '*'));

        assertEquals(1, StrLib.findLastOf(".*", '*'));
        assertEquals(2, StrLib.findLastOf("..*", '*'));
        assertEquals(3, StrLib.findLastOf("...*", '*'));

        // NotFound: value, pos, min range
        assertEquals(-1, StrLib.findLastOf("..**..**", '*', -1));
        assertEquals(-1, StrLib.findLastOf("..**..**", '*', -2));

        // NotFound: value, pos
        assertEquals(-1, StrLib.findLastOf("..**..**", '*', 0));
        assertEquals(-1, StrLib.findLastOf("..**..**", '*', 1));

        // Found: value, pos
        assertEquals(2, StrLib.findLastOf("..**..**", '*', 2));
        assertEquals(3, StrLib.findLastOf("..**..**", '*', 3));
        assertEquals(3, StrLib.findLastOf("..**..**", '*', 4));
        assertEquals(3, StrLib.findLastOf("..**..**", '*', 5));
        assertEquals(6, StrLib.findLastOf("..**..**", '*', 6));
        assertEquals(7, StrLib.findLastOf("..**..**", '*', 7));

        // NotFound: value, pos, max range
        assertEquals(-1, StrLib.findLastOf("..**..**", '*', 8));
        assertEquals(-1, StrLib.findLastOf("..**..**", '*', 9));
                
    }
    
    public void testFindFirstNotOf() {
                
        // char
        
        // NotFound: null/empty
        assertEquals(-1, StrLib.findFirstNotOf(null, '\0'));
        assertEquals(-1, StrLib.findFirstNotOf("", '\0'));
        assertEquals(-1, StrLib.findFirstNotOf(null, ' '));
        assertEquals(-1, StrLib.findFirstNotOf("", ' '));
        assertEquals(-1, StrLib.findFirstNotOf(null, '.'));
        assertEquals(-1, StrLib.findFirstNotOf("", '.'));
        
        // NotFound: null/empty, pos
        assertEquals(-1, StrLib.findFirstNotOf(null, '\0', 0));
        assertEquals(-1, StrLib.findFirstNotOf("", '\0', 0));
        assertEquals(-1, StrLib.findFirstNotOf(null, ' ', 0));
        assertEquals(-1, StrLib.findFirstNotOf("", ' ', 0));
        assertEquals(-1, StrLib.findFirstNotOf(null, '.', 0));
        assertEquals(-1, StrLib.findFirstNotOf("", '.', 0));

        assertEquals(-1, StrLib.findFirstNotOf(null, '\0', -1));
        assertEquals(-1, StrLib.findFirstNotOf("", '\0', -1));
        assertEquals(-1, StrLib.findFirstNotOf(null, ' ', -1));
        assertEquals(-1, StrLib.findFirstNotOf("", ' ', -1));
        assertEquals(-1, StrLib.findFirstNotOf(null, '.', -1));
        assertEquals(-1, StrLib.findFirstNotOf("", '.', -1));

        assertEquals(-1, StrLib.findFirstNotOf(null, '\0', 1));
        assertEquals(-1, StrLib.findFirstNotOf("", '\0', 1));
        assertEquals(-1, StrLib.findFirstNotOf(null, ' ', 1));
        assertEquals(-1, StrLib.findFirstNotOf("", ' ', 1));
        assertEquals(-1, StrLib.findFirstNotOf(null, '.', 1));
        assertEquals(-1, StrLib.findFirstNotOf("", '.', 1));
        
        // NotFound: blank/value
        assertEquals(-1, StrLib.findFirstNotOf(" ", ' '));
        assertEquals(-1, StrLib.findFirstNotOf("  ", ' '));
        
        // Found: value
        assertEquals(0, StrLib.findFirstNotOf(".", ' '));
        assertEquals(1, StrLib.findFirstNotOf(" .", ' '));
        assertEquals(2, StrLib.findFirstNotOf("  .", ' '));

        assertEquals(0, StrLib.findFirstNotOf(".", '*'));
        assertEquals(1, StrLib.findFirstNotOf("*.", '*'));
        assertEquals(2, StrLib.findFirstNotOf("**.", '*'));
        
        // Found: value, pos
        assertEquals(2, StrLib.findFirstNotOf("**..**..", '*', 0));
        assertEquals(2, StrLib.findFirstNotOf("**..**..", '*', 1));
        assertEquals(2, StrLib.findFirstNotOf("**..**..", '*', 2));
        assertEquals(3, StrLib.findFirstNotOf("**..**..", '*', 3));        
        assertEquals(6, StrLib.findFirstNotOf("**..**..", '*', 4));
        assertEquals(6, StrLib.findFirstNotOf("**..**..", '*', 5));
        assertEquals(6, StrLib.findFirstNotOf("**..**..", '*', 6));
        assertEquals(7, StrLib.findFirstNotOf("**..**..", '*', 7));
        
        // NotFound: value, pos
        assertEquals(-1, StrLib.findFirstNotOf("**..**..", '*', 8));

    }

    public void testFindLastNotOf() {
        
        // char
        
        // NotFound: null/empty
        assertEquals(-1, StrLib.findLastNotOf(null, '\0'));
        assertEquals(-1, StrLib.findLastNotOf("", '\0'));
        assertEquals(-1, StrLib.findLastNotOf(null, ' '));
        assertEquals(-1, StrLib.findLastNotOf("", ' '));
        assertEquals(-1, StrLib.findLastNotOf(null, '.'));
        assertEquals(-1, StrLib.findLastNotOf("", '.'));
        
        // NotFound: null/empty, pos
        assertEquals(-1, StrLib.findLastNotOf(null, '\0', 0));
        assertEquals(-1, StrLib.findLastNotOf("", '\0', 0));
        assertEquals(-1, StrLib.findLastNotOf(null, ' ', 0));
        assertEquals(-1, StrLib.findLastNotOf("", ' ', 0));
        assertEquals(-1, StrLib.findLastNotOf(null, '.', 0));
        assertEquals(-1, StrLib.findLastNotOf("", '.', 0));

        assertEquals(-1, StrLib.findLastNotOf(null, '\0', -1));
        assertEquals(-1, StrLib.findLastNotOf("", '\0', -1));
        assertEquals(-1, StrLib.findLastNotOf(null, ' ', -1));
        assertEquals(-1, StrLib.findLastNotOf("", ' ', -1));
        assertEquals(-1, StrLib.findLastNotOf(null, '.', -1));
        assertEquals(-1, StrLib.findLastNotOf("", '.', -1));

        assertEquals(-1, StrLib.findLastNotOf(null, '\0', 1));
        assertEquals(-1, StrLib.findLastNotOf("", '\0', 1));
        assertEquals(-1, StrLib.findLastNotOf(null, ' ', 1));
        assertEquals(-1, StrLib.findLastNotOf("", ' ', 1));
        assertEquals(-1, StrLib.findLastNotOf(null, '.', 1));
        assertEquals(-1, StrLib.findLastNotOf("", '.', 1));
        
        // NotFound: blank/value
        assertEquals(-1, StrLib.findLastNotOf(" ", ' '));
        assertEquals(-1, StrLib.findLastNotOf("  ", ' '));
        
        // Found: value
        assertEquals(0, StrLib.findLastNotOf(".", ' '));
        assertEquals(1, StrLib.findLastNotOf(".. ", ' '));
        assertEquals(1, StrLib.findLastNotOf("..  ", ' '));

        assertEquals(0, StrLib.findLastNotOf(".", '*'));
        assertEquals(1, StrLib.findLastNotOf("..*", '*'));
        assertEquals(1, StrLib.findLastNotOf("..**", '*'));
        
        // NotFound: value, pos, min range
        assertEquals(-1, StrLib.findLastNotOf("..**..**", '*', -1));
        assertEquals(-1, StrLib.findLastNotOf("..**..**", '*', -2));
        
        // Found: value, pos
        assertEquals(0, StrLib.findLastNotOf("..**..**", '*', 0));
        assertEquals(1, StrLib.findLastNotOf("..**..**", '*', 1));
        assertEquals(1, StrLib.findLastNotOf("..**..**", '*', 2));
        assertEquals(1, StrLib.findLastNotOf("..**..**", '*', 3));
        assertEquals(4, StrLib.findLastNotOf("..**..**", '*', 4));
        assertEquals(5, StrLib.findLastNotOf("..**..**", '*', 5));
        assertEquals(5, StrLib.findLastNotOf("..**..**", '*', 6));
        assertEquals(5, StrLib.findLastNotOf("..**..**", '*', 7));
        
        // NotFound: value, pos, max range
        assertEquals(-1, StrLib.findLastNotOf("..**..**", '*', 8));
        assertEquals(-1, StrLib.findLastNotOf("..**..**", '*', 9));

    }

    // 2.2

    public void testLpad() {

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
        
    }

    public void testRpad() {
        
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
    
    
    // 2.3

    public void testLeft() {

        // left: null
        assertNull(StrLib.left(null, 3));

        // left: empty
        assertEquals("", StrLib.left("", 3));

        // left: value
        assertEquals("a", StrLib.left("a", 3));
        assertEquals("ab", StrLib.left("ab", 3));
        assertEquals("abc", StrLib.left("abc", 3));
        assertEquals("abc", StrLib.left("abcd", 3));

    }

    public void testRight() {

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
    
    // 3.1

    public void testCapitalize() {

        // capitalize: null
        assertNull(StrLib.capitalize(null));

        // capitalize: empty
        assertEquals("", StrLib.capitalize(""));
        assertEquals(" ", StrLib.capitalize(" "));

        // capitalize: value
        assertEquals("ABcdE", StrLib.capitalize("aBcdE"));
        assertEquals("Abcde", StrLib.capitalize("aBcdE", true));

    }

    public void testDecapitalize() {

        // decapitalize: null
        assertNull(StrLib.decapitalize(null));

        // decapitalize: empty
        assertEquals("", StrLib.decapitalize(""));
        assertEquals(" ", StrLib.decapitalize(" "));

        // decapitalize: value
        assertEquals("aBcdE", StrLib.decapitalize("ABcdE"));
        assertEquals("abcde", StrLib.decapitalize("ABcdE", true));

    }

    public void testUpper() {

        // upper: null
        assertNull(StrLib.upper(null));

        // upper: empty
        assertEquals("", StrLib.upper(""));
        assertEquals(" ", StrLib.upper(" "));

        // upper: value
        assertEquals("ABCDE", StrLib.upper("aBcdE"));

    }

    public void testLower() {

        // lower: null
        assertNull(StrLib.lower(null));

        // lower: empty
        assertEquals("", StrLib.lower(""));
        assertEquals(" ", StrLib.lower(" "));

        // lower: value
        assertEquals("abcde", StrLib.lower("aBcdE"));

    }

    public void testToUpperCase() {

        // toUpperCase: null
        assertNull(StrLib.toUpperCase(null));

        // toUpperCase: empty
        assertEquals("", StrLib.toUpperCase(""));
        assertEquals(" ", StrLib.toUpperCase(" "));

        // toUpperCase: value
        assertEquals("ABCDE", StrLib.toUpperCase("aBcdE"));

    }

    public void testToLowerCase() {

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
    
    // 4.1
    
    public void testHasPrefix() {
        
        assertFalse(StrLib.hasPrefix("", "abc"));  
        assertFalse(StrLib.hasPrefix("myfile.txt", "abc"));

        assertTrue(StrLib.hasPrefix("", ""));
        assertTrue(StrLib.hasPrefix("myfile.txt", ""));
        assertTrue(StrLib.hasPrefix("myfile.txt", "my"));
        assertTrue(StrLib.hasPrefix("myfile.txt", "myfile"));
        assertTrue(StrLib.hasPrefix("myfile.txt", "myfile.txt"));
        
    }
    
    // 4.2

    public void testRemovePrefix() {

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

    }

    public void testRemoveSuffix() {

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
    
    
    // 5.1

    public void testCountChars() {

        assertEquals(0, StrLib.countChars("abcde", 'z'));
        assertEquals(1, StrLib.countChars("abcde", 'a'));
        assertEquals(2, StrLib.countChars("abcdeabcx", 'a'));
        
        assertEquals(4, StrLib.countChars("Hello world, my world is very nice world.", 'o'));

    }

    public void testCountStrings() {

        assertEquals(0, StrLib.countStrings("abcde", "xyz"));
        assertEquals(1, StrLib.countStrings("abcde", "abc"));
        assertEquals(2, StrLib.countStrings("abcdeabcx", "abc"));

        assertEquals(2, StrLib.countStrings("abcdeabc", "abc"));
        assertEquals(2, StrLib.countStrings("abc de abc", "abc"));
        assertEquals(2, StrLib.countStrings("abc de abc x", "abc"));
        
        assertEquals(3, StrLib.countStrings("Hello world, my world is very nice world.", "world"));

    }
    
    public void testCountWords() {

        assertEquals(13, StrLib.countWords("Hello world, my world is very nice world. But we have other worlds."));

    }    
    
    // 6.1
    
    // 7.1
        
    public void testSplit() {
        
        String[] res = null;
        
        res = StrLib.split(null, null);        
        assertNotNull(res);
        assertEquals(0,  res.length);
        
        res = StrLib.split(null, '\0');        
        assertNotNull(res);
        assertEquals(0,  res.length);
        
        res = StrLib.split(null, "");        
        assertNotNull(res);
        assertEquals(0,  res.length);
        
        ////
        
        res = StrLib.split("", null);        
        assertNotNull(res);
        assertEquals(0,  res.length);
        
        res = StrLib.split("", '\0');        
        assertNotNull(res);
        assertEquals(0,  res.length);
        
        res = StrLib.split("", "");        
        assertNotNull(res);
        assertEquals(0,  res.length);
        
        ////

        res = StrLib.split(" ", null);        
        assertNotNull(res);
        assertEquals(1,  res.length);
        assertEquals(" ",  res[0]);
        
        res = StrLib.split(" ", '\0');        
        assertNotNull(res);
        assertEquals(1,  res.length);
        assertEquals(" ",  res[0]);
        
        res = StrLib.split(" ", "");        
        assertNotNull(res);
        assertEquals(1,  res.length);
        assertEquals(" ",  res[0]);
        
        ////

        // ValueSpace=0, SeparatorSpace=0
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1,200,500,-12", ','));        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1|200|500|-12", '|'));
        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1,200,500,-12", ","));        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1|200|500|-12", "|"));
        

        // ValueSpace=1, SeparatorSpace=0
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1, 200, 500, -12", ','));
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1| 200| 500| -12", '|'));
        
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1, 200, 500, -12", ","));
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1| 200| 500| -12", "|"));
        
        
        // ValueSpace=1, SeparatorSpace=1
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1, 200, 500, -12", ", "));
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1| 200| 500| -12", "| "));
        
        // ValueSpace=1-2, SeparatorSpace=1
        assertEquals(new String[] {"1", "200", "500", " -12"}, StrLib.split("1, 200, 500,  -12", ", "));        
        assertEquals(new String[] {"1", "200", "500", " -12"}, StrLib.split("1| 200| 500|  -12", "| "));
        
    }
    
    public void testSplitBySeparator() {
                
        // ValueSpace=0, SeparatorSpace=0
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1,200,500,-12", ','));        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1|200|500|-12", '|'));
        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1,200,500,-12", ","));        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1|200|500|-12", "|"));

        // ValueSpace=1, SeparatorSpace=0
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1, 200, 500, -12", ','));
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1| 200| 500| -12", '|'));
        
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1, 200, 500, -12", ","));
        assertEquals(new String[] {"1", " 200", " 500", " -12"}, StrLib.split("1| 200| 500| -12", "|"));
        
        // ValueSpace=1, SeparatorSpace=1
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1, 200, 500, -12", ", "));
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.split("1| 200| 500| -12", "| "));
        
        // ValueSpace=1-2, SeparatorSpace=1
        assertEquals(new String[] {"1", "200", "500", " -12"}, StrLib.split("1, 200, 500,  -12", ", "));        
        assertEquals(new String[] {"1", "200", "500", " -12"}, StrLib.split("1| 200| 500|  -12", "| "));
                
    }
    
    
    public void testSplitTrim() {
                
        // ValueSpace=0, SeparatorSpace=0
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1,200,500,-12", ","));        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1|200|500|-12", "|"));

        // ValueSpace=1, SeparatorSpace=0
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1, 200, 500, -12", ","));
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1| 200| 500| -12", "|"));
        
        // ValueSpace=1, SeparatorSpace=1
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1, 200, 500, -12", ", "));
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1| 200| 500| -12", "| "));
        
        // ValueSpace=1-2, SeparatorSpace=1
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1, 200, 500,  -12", ", "));        
        assertEquals(new String[] {"1", "200", "500", "-12"}, StrLib.splitTrim("1| 200| 500|  -12", "| "));
                
    }
    

    // isDigit, isISODigit
    public void testIsDigit() {

        //println("isDigit: " + Character.isDigit('\u0660'));
        //println("isLetter: " + Character.isLetter('\u0660'));
        //println("isAlpha: " + Character.isAlphabetic('\u0660'));

        //println();
        //println("isDigit: " + Character.isDigit('\u00B5'));
        //println("isLetter: " + Character.isLetter('\u00B5'));
        //println("isAlpha: " + Character.isAlphabetic('\u00B5'));

        //println();
        //println("isDigit: " + Character.isDigit('\u00B6'));
        //println("isLetter: " + Character.isLetter('\u00B6'));
        //println("isAlpha: " + Character.isAlphabetic('\u00B6'));

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
        //char x = ' ';
        //println("" + (int) x + ": '" + x + "'");

        assertTrue(StrLib.isISOWhitespace(' '));
        assertTrue(StrLib.isISOUnderline('_'));
        assertTrue(StrLib.isISODot('.'));

        //x = '\u00B7';
        //println("" + (int) x + ": '" + x + "'");

        assertFalse(StrLib.isISOWhitespace('#'));
        assertFalse(StrLib.isISOUnderline('-')); // minus is not underline
        assertFalse(StrLib.isISODot('\u00B7')); // it is displayed like dot but it is not dot :)
    }

    public void testIsISOIdentifier() {

        char x = 'x';
        //println("" + (int) x + ": '" + x + "'");

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
