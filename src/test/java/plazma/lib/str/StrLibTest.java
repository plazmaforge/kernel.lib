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
        assertTrue(StrLib.equals(null, null));

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

    public void testEqualsContent() {

        // equals(null, null)
        assertTrue(StrLib.equalsContent(null, (CharSequence) null));
        assertTrue(StrLib.equalsContent(null, (char[]) null));
                
        assertTrue(StrLib.equalsContent("", ""));
        assertTrue(StrLib.equalsContent(" ", " "));
        assertTrue(StrLib.equalsContent("  ", "  "));
        
        assertTrue(StrLib.equalsContent("a", "a"));
        assertTrue(StrLib.equalsContent("ab", "ab"));
        assertTrue(StrLib.equalsContent("abc", "abc"));
        
        assertTrue(StrLib.equalsContent("", new char[] {}));
        assertTrue(StrLib.equalsContent(" ", new char[] {' '}));
        assertTrue(StrLib.equalsContent("  ", new char[] {' ', ' '}));

        assertTrue(StrLib.equalsContent("a", new char[] {'a'}));
        assertTrue(StrLib.equalsContent("ab", new char[] {'a', 'b'}));
        assertTrue(StrLib.equalsContent("abc", new char[] {'a', 'b', 'c'}));

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
        
        // chars //////////////////////////////////////////////

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

    public void testTrimSpace() {

        // trimSpace(null), trimSpace(empty)
        assertNull(StrLib.trimSpace(null));
        assertEquals("", StrLib.trimSpace(""));

        // trimSpace(blank)
        assertEquals("", StrLib.trimSpace(" "));
        assertEquals("", StrLib.trimSpace("  "));

        // trimSpace(value)
        assertEquals("abc", StrLib.trimSpace("abc"));
        assertEquals("abc", StrLib.trimSpace(" abc"));
        assertEquals("abc", StrLib.trimSpace("abc "));
        assertEquals("abc", StrLib.trimSpace(" abc "));
        
        // trimSpace(" \t\n\r\f\v")
        assertEquals("\t\n\r\f\u000B", StrLib.trimSpace(" \t\n\r\f\u000B"));                                      // \v -> \u000B
        assertEquals("\t\n\r\f\u000Babc", StrLib.trimSpace(" \t\n\r\f\u000Babc"));                                // \v -> \u000B
        assertEquals("abc \t\n\r\f\u000B", StrLib.trimSpace("abc \t\n\r\f\u000B"));                               // \v -> \u000B
        assertEquals("\t\n\r\f\u000Babc \t\n\r\f\u000B", StrLib.trimSpace(" \t\n\r\f\u000Babc \t\n\r\f\u000B"));  // \v -> \u000B

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
        
        // ltrim(" \t\n\r\f\v")
        assertEquals("", StrLib.ltrim(" \t\n\r\f\u000B"));                                      // \v -> \u000B
        assertEquals("abc", StrLib.ltrim(" \t\n\r\f\u000Babc"));                                // \v -> \u000B
        assertEquals("abc \t\n\r\f\u000B", StrLib.ltrim("abc \t\n\r\f\u000B"));                 // \v -> \u000B
        assertEquals("abc \t\n\r\f\u000B", StrLib.ltrim(" \t\n\r\f\u000Babc \t\n\r\f\u000B"));  // \v -> \u000B

        // chars //////////////////////////////////////////////

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
                
        // rtrim(" \t\n\r\f\v")
        assertEquals("", StrLib.rtrim(" \t\n\r\f\u000B"));                                      // \v -> \u000B
        assertEquals(" \t\n\r\f\u000Babc", StrLib.rtrim(" \t\n\r\f\u000Babc"));                 // \v -> \u000B
        assertEquals("abc", StrLib.rtrim("abc \t\n\r\f\u000B"));                                // \v -> \u000B
        assertEquals(" \t\n\r\f\u000Babc", StrLib.rtrim(" \t\n\r\f\u000Babc \t\n\r\f\u000B"));  // \v -> \u000B
                
        // chars //////////////////////////////////////////////

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

    // 2.1
    
    public void testReplicate() {
        
        // replicate(null, -n), replicate(null, 0), replicate(null, n)
        assertNull(StrLib.replicate(null, -2));
        assertNull(StrLib.replicate(null, -1));
        assertNull(StrLib.replicate(null, 0));
        assertNull(StrLib.replicate(null, 1));
        assertNull(StrLib.replicate(null, 2));

        // replicate(empty, -n), replicate(empty, 0), replicate(empty, n)
        assertEquals("", StrLib.replicate("", -2));
        assertEquals("", StrLib.replicate("", -1));
        assertEquals("", StrLib.replicate("", 0));
        assertEquals("", StrLib.replicate("", 1));
        assertEquals("", StrLib.replicate("", 2));

        // char

        // replicate(char, -n), replicate(char, 0), replicate(char, n)
        assertEquals("", StrLib.replicate('.', -2));
        assertEquals("", StrLib.replicate('.', -1));
        assertEquals("", StrLib.replicate('.', 0));
        assertEquals(".", StrLib.replicate('.', 1));
        assertEquals("..", StrLib.replicate('.', 2));

        // replicate(char, n)
        assertEquals("*", StrLib.replicate('*', 1));
        assertEquals("**", StrLib.replicate('*', 2));
        assertEquals("***", StrLib.replicate('*', 3));

        assertEquals("a", StrLib.replicate('a', 1));
        assertEquals("aa", StrLib.replicate('a', 2));
        assertEquals("aaa", StrLib.replicate('a', 3));

        // string

        // replicate(str, -n), replicate(str, 0), replicate(str, n)
        assertEquals("", StrLib.replicate("abc", -2));
        assertEquals("", StrLib.replicate("abc", -1));
        assertEquals("", StrLib.replicate("abc", 0));
        assertEquals("abc", StrLib.replicate("abc", 1));
        assertEquals("abcabc", StrLib.replicate("abc", 2));
        assertEquals("abcabcabc", StrLib.replicate("abc", 3));        
    
    }

    // 2.2

    public void testLpad() {
        
        // null

        // lpad(null, -n), lpad(null, 0), lpad(null, n)
        assertNull(StrLib.lpad(null, -2));
        assertNull(StrLib.lpad(null, -1));
        assertNull(StrLib.lpad(null, 0));
        assertNull(StrLib.lpad(null, 1));
        assertNull(StrLib.lpad(null, 2));

        // lpad(null, -n, ''), lpad(null, 0, ''), lpad(null, n, '')
        assertNull(StrLib.lpad(null, -2, '\0'));
        assertNull(StrLib.lpad(null, -1, '\0'));
        assertNull(StrLib.lpad(null, 0, '\0'));
        assertNull(StrLib.lpad(null, 1, '\0'));
        assertNull(StrLib.lpad(null, 2, '\0'));

        // lpad(null, -n, ' '), lpad(null, 0, ' '), lpad(null, n, ' ')
        assertNull(StrLib.lpad(null, -2, ' '));
        assertNull(StrLib.lpad(null, -1, ' '));
        assertNull(StrLib.lpad(null, 0, ' '));
        assertNull(StrLib.lpad(null, 1, ' '));
        assertNull(StrLib.lpad(null, 2, ' '));

        // lpad(null, -n, '*'), lpad(null, 0, '*'), lpad(null, n, '*')
        assertNull(StrLib.lpad(null, -2, '*'));
        assertNull(StrLib.lpad(null, -1, '*'));
        assertNull(StrLib.lpad(null, 0, '*'));
        assertNull(StrLib.lpad(null, 1, '*'));
        assertNull(StrLib.lpad(null, 2, '*'));

        // empty

        // lpad(empty, -n), lpad(empty, 0), lpad(empty, n)
        assertEquals("", StrLib.lpad("", -2));
        assertEquals("", StrLib.lpad("", -1));
        assertEquals("", StrLib.lpad("", 0));
        assertEquals(" ", StrLib.lpad("", 1));
        assertEquals("  ", StrLib.lpad("", 2));
        assertEquals("   ", StrLib.lpad("", 3));

        assertEquals("", StrLib.lpad("", -2, '*'));
        assertEquals("", StrLib.lpad("", -1, '*'));
        assertEquals("", StrLib.lpad("", 0, '*'));
        assertEquals("*", StrLib.lpad("", 1, '*'));
        assertEquals("**", StrLib.lpad("", 2, '*'));
        assertEquals("***", StrLib.lpad("", 3, '*'));

        // char

        // lpad(char, -n), lpad(char, 0), lpad(char, n)
        assertEquals("a", StrLib.lpad("a", -2));
        assertEquals("a", StrLib.lpad("a", -1));
        assertEquals("a", StrLib.lpad("a", 0));
        assertEquals("a", StrLib.lpad("a", 1));
        assertEquals(" a", StrLib.lpad("a", 2));
        assertEquals("  a", StrLib.lpad("a", 3));

        assertEquals("a", StrLib.lpad("a", -2, '*'));
        assertEquals("a", StrLib.lpad("a", -1, '*'));
        assertEquals("a", StrLib.lpad("a", 0, '*'));
        assertEquals("a", StrLib.lpad("a", 1, '*'));
        assertEquals("*a", StrLib.lpad("a", 2, '*'));
        assertEquals("**a", StrLib.lpad("a", 3, '*'));

        // string

        // lpad(str, -n), lpad(str, 0), lpad(str, n)
        assertEquals("abc", StrLib.lpad("abc", -2));
        assertEquals("abc", StrLib.lpad("abc", -1));
        assertEquals("abc", StrLib.lpad("abc", 0));
        assertEquals("abc", StrLib.lpad("abc", 1));
        assertEquals("abc", StrLib.lpad("abc", 2));
        assertEquals("abc", StrLib.lpad("abc", 3));
        assertEquals(" abc", StrLib.lpad("abc", 4));
        assertEquals("  abc", StrLib.lpad("abc", 5));

        assertEquals("abc", StrLib.lpad("abc", -2, '*'));
        assertEquals("abc", StrLib.lpad("abc", -1, '*'));
        assertEquals("abc", StrLib.lpad("abc", 0, '*'));
        assertEquals("abc", StrLib.lpad("abc", 1, '*'));
        assertEquals("abc", StrLib.lpad("abc", 2, '*'));
        assertEquals("abc", StrLib.lpad("abc", 3, '*'));
        assertEquals("*abc", StrLib.lpad("abc", 4, '*'));
        assertEquals("**abc", StrLib.lpad("abc", 5, '*'));

        assertEquals("abc", StrLib.lpad("abc", -2, "yz"));
        assertEquals("abc", StrLib.lpad("abc", -1, "yz"));
        assertEquals("abc", StrLib.lpad("abc", 0, "yz"));
        assertEquals("abc", StrLib.lpad("abc", 1, "yz"));
        assertEquals("abc", StrLib.lpad("abc", 2, "yz"));
        assertEquals("abc", StrLib.lpad("abc", 3, "yz"));
        assertEquals("yabc", StrLib.lpad("abc", 4, "yz"));
        assertEquals("yzabc", StrLib.lpad("abc", 5, "yz"));
        assertEquals("yzyabc", StrLib.lpad("abc", 6, "yz"));
        assertEquals("yzyzabc", StrLib.lpad("abc", 7, "yz"));
        assertEquals("yzyzyabc", StrLib.lpad("abc", 8, "yz"));
        
        // 12345678
        // yzyzyz**
        // *****abc
        //---------
        // yzyzyabc
                
    }

    public void testRpad() {
        
        // null

        // rpad(null, -n), rpad(null, 0), rpad(null, n)
        assertNull(StrLib.rpad(null, -2));
        assertNull(StrLib.rpad(null, -1));
        assertNull(StrLib.rpad(null, 0));
        assertNull(StrLib.rpad(null, 1));
        assertNull(StrLib.rpad(null, 2));

        // rpad(null, -n, ''), rpad(null, 0, ''), rpad(null, n, '')
        assertNull(StrLib.rpad(null, -2, '\0'));
        assertNull(StrLib.rpad(null, -1, '\0'));
        assertNull(StrLib.rpad(null, 0, '\0'));
        assertNull(StrLib.rpad(null, 1, '\0'));
        assertNull(StrLib.rpad(null, 2, '\0'));

        // rpad(null, -n, ' '), rpad(null, 0, ' '), rpad(null, n, ' ')
        assertNull(StrLib.rpad(null, -2, ' '));
        assertNull(StrLib.rpad(null, -1, ' '));
        assertNull(StrLib.rpad(null, 0, ' '));
        assertNull(StrLib.rpad(null, 1, ' '));
        assertNull(StrLib.rpad(null, 2, ' '));

        // rpad(null, -n, '*'), rpad(null, 0, '*'), rpad(null, n, '*')
        assertNull(StrLib.rpad(null, -2, '*'));
        assertNull(StrLib.rpad(null, -1, '*'));
        assertNull(StrLib.rpad(null, 0, '*'));
        assertNull(StrLib.rpad(null, 1, '*'));
        assertNull(StrLib.rpad(null, 2, '*'));

        // empty

        // rpad(empty, -n), rpad(empty, 0), rpad(empty, n)
        assertEquals("", StrLib.rpad("", -2));
        assertEquals("", StrLib.rpad("", -1));
        assertEquals("", StrLib.rpad("", 0));
        assertEquals(" ", StrLib.rpad("", 1));
        assertEquals("  ", StrLib.rpad("", 2));
        assertEquals("   ", StrLib.rpad("", 3));

        assertEquals("", StrLib.rpad("", -2, '*'));
        assertEquals("", StrLib.rpad("", -1, '*'));
        assertEquals("", StrLib.rpad("", 0, '*'));
        assertEquals("*", StrLib.rpad("", 1, '*'));
        assertEquals("**", StrLib.rpad("", 2, '*'));
        assertEquals("***", StrLib.rpad("", 3, '*'));

        // char

        // rpad(char, -n), rpad(char, 0), rpad(char, n)
        assertEquals("a", StrLib.rpad("a", -2));
        assertEquals("a", StrLib.rpad("a", -1));
        assertEquals("a", StrLib.rpad("a", 0));
        assertEquals("a", StrLib.rpad("a", 1));
        assertEquals("a ", StrLib.rpad("a", 2));
        assertEquals("a  ", StrLib.rpad("a", 3));

        assertEquals("a", StrLib.rpad("a", -2, '*'));
        assertEquals("a", StrLib.rpad("a", -1, '*'));
        assertEquals("a", StrLib.rpad("a", 0, '*'));
        assertEquals("a", StrLib.rpad("a", 1, '*'));
        assertEquals("a*", StrLib.rpad("a", 2, '*'));
        assertEquals("a**", StrLib.rpad("a", 3, '*'));

        // string

        // rpad(str, -n), rpad(str, 0), rpad(str, n)
        assertEquals("abc", StrLib.rpad("abc", -2));
        assertEquals("abc", StrLib.rpad("abc", -1));
        assertEquals("abc", StrLib.rpad("abc", 0));
        assertEquals("abc", StrLib.rpad("abc", 1));
        assertEquals("abc", StrLib.rpad("abc", 2));
        assertEquals("abc", StrLib.rpad("abc", 3));
        assertEquals("abc ", StrLib.rpad("abc", 4));
        assertEquals("abc  ", StrLib.rpad("abc", 5));

        assertEquals("abc", StrLib.rpad("abc", -2, '*'));
        assertEquals("abc", StrLib.rpad("abc", -1, '*'));
        assertEquals("abc", StrLib.rpad("abc", 0, '*'));
        assertEquals("abc", StrLib.rpad("abc", 1, '*'));
        assertEquals("abc", StrLib.rpad("abc", 2, '*'));
        assertEquals("abc", StrLib.rpad("abc", 3, '*'));
        assertEquals("abc*", StrLib.rpad("abc", 4, '*'));
        assertEquals("abc**", StrLib.rpad("abc", 5, '*'));

        assertEquals("abc", StrLib.rpad("abc", -2, "yz"));
        assertEquals("abc", StrLib.rpad("abc", -1, "yz"));
        assertEquals("abc", StrLib.rpad("abc", 0, "yz"));
        assertEquals("abc", StrLib.rpad("abc", 1, "yz"));
        assertEquals("abc", StrLib.rpad("abc", 2, "yz"));
        assertEquals("abc", StrLib.rpad("abc", 3, "yz"));
        assertEquals("abcy", StrLib.rpad("abc", 4, "yz"));
        assertEquals("abcyz", StrLib.rpad("abc", 5, "yz"));
        assertEquals("abcyzy", StrLib.rpad("abc", 6, "yz"));
        assertEquals("abcyzyz", StrLib.rpad("abc", 7, "yz"));
        assertEquals("abcyzyzy", StrLib.rpad("abc", 8, "yz"));
        
        // 12345678
        // abc*****
        // ***yzyzyz
        // --------
        // abcyzyzy
        
    }
    
    
    // 2.3
        
    public void testFill() {
        
        // fill(null, -n), fill(null, 0), fill(null, n)
        assertNull(StrLib.fill(null, -2));
        assertNull(StrLib.fill(null, -1));
        assertNull(StrLib.fill(null, 0));
        assertNull(StrLib.fill(null, 1));
        assertNull(StrLib.fill(null, 2));

        assertNull(StrLib.fill(null, -2, '\0'));
        assertNull(StrLib.fill(null, -1, '\0'));
        assertNull(StrLib.fill(null, 0, '\0'));
        assertNull(StrLib.fill(null, 1, '\0'));
        assertNull(StrLib.fill(null, 2, '\0'));

        assertNull(StrLib.fill(null, -2, ' '));
        assertNull(StrLib.fill(null, -1, ' '));
        assertNull(StrLib.fill(null, 0, ' '));
        assertNull(StrLib.fill(null, 1, ' '));
        assertNull(StrLib.fill(null, 2, ' '));

        assertNull(StrLib.fill(null, -2, '*'));
        assertNull(StrLib.fill(null, -1, '*'));
        assertNull(StrLib.fill(null, 0, '*'));
        assertNull(StrLib.fill(null, 1, '*'));
        assertNull(StrLib.fill(null, 2, '*'));

        // fill(empty, -n), fill(empty, 0), fill(empty, n)
        assertEquals("", StrLib.fill("", -2));
        assertEquals("", StrLib.fill("", -1));
        assertEquals("", StrLib.fill("", 0));
        assertEquals(" ", StrLib.fill("", 1));
        assertEquals("  ", StrLib.fill("", 2));

        // fill(empty, -n, ''), fill(empty, 0, ''), fill(empty, n, '')
        assertEquals("", StrLib.fill("", -2, ""));
        assertEquals("", StrLib.fill("", -1, ""));
        assertEquals("", StrLib.fill("", 0, ""));
        assertEquals("", StrLib.fill("", 1, ""));
        assertEquals("", StrLib.fill("", 2, ""));

        // fill(empty, -n, ' '), fill(empty, 0, ' '), fill(empty, n, ' ')
        assertEquals("", StrLib.fill("", -2, ' '));
        assertEquals("", StrLib.fill("", -1, ' '));
        assertEquals("", StrLib.fill("", 0, ' '));
        assertEquals(" ", StrLib.fill("", 1, ' '));
        assertEquals("  ", StrLib.fill("", 2, ' '));

        // fill(empty, -n, '*'), fill(empty, 0, '*'), fill(empty, n, '*')
        assertEquals("", StrLib.fill("", -2, '*'));
        assertEquals("", StrLib.fill("", -1, '*'));
        assertEquals("", StrLib.fill("", 0, '*'));
        assertEquals("*", StrLib.fill("", 1, '*'));
        assertEquals("**", StrLib.fill("", 2, '*'));

        // char

        // fill(char, -n), fill(char, 0), fill(char, n)
        assertEquals("", StrLib.fill("a", -2));
        assertEquals("", StrLib.fill("a", -1));
        assertEquals("", StrLib.fill("a", 0));
        assertEquals("a", StrLib.fill("a", 1));
        assertEquals("a ", StrLib.fill("a", 2));
        assertEquals("a  ", StrLib.fill("a", 3));
        assertEquals("a   ", StrLib.fill("a", 4));
        assertEquals("a    ", StrLib.fill("a", 5));

        assertEquals("", StrLib.fill("a", -2, '*'));
        assertEquals("", StrLib.fill("a", -1, '*'));
        assertEquals("", StrLib.fill("a", 0, '*'));
        assertEquals("a", StrLib.fill("a", 1, '*'));
        assertEquals("a*", StrLib.fill("a", 2, '*'));
        assertEquals("a**", StrLib.fill("a", 3, '*'));
        assertEquals("a***", StrLib.fill("a", 4, '*'));
        assertEquals("a****", StrLib.fill("a", 5, '*'));

        // string

        // fill(str, -n), fill(str, 0), fill(str, n)
        assertEquals("", StrLib.fill("abcxyz", -2));
        assertEquals("", StrLib.fill("abcxyz", -1));
        assertEquals("", StrLib.fill("abcxyz", 0));
        assertEquals("a", StrLib.fill("abcxyz", 1));
        assertEquals("ab", StrLib.fill("abcxyz", 2));
        assertEquals("abc", StrLib.fill("abcxyz", 3));
        assertEquals("a...", StrLib.fill("abcxyz", 4));   // ellipsis
        assertEquals("ab...", StrLib.fill("abcxyz", 5));  // ellipsis
        assertEquals("abcxyz", StrLib.fill("abcxyz", 6));
        assertEquals("abcxyz ", StrLib.fill("abcxyz", 7));
        assertEquals("abcxyz  ", StrLib.fill("abcxyz", 8));

        // fill(str, -n, '*'), fill(str, 0, '*'), fill(str, n, '*')
        assertEquals("", StrLib.fill("abcxyz", -2, '*'));
        assertEquals("", StrLib.fill("abcxyz", -1, '*'));
        assertEquals("", StrLib.fill("abcxyz", 0, '*'));
        assertEquals("a", StrLib.fill("abcxyz", 1, '*'));
        assertEquals("ab", StrLib.fill("abcxyz", 2, '*'));
        assertEquals("abc", StrLib.fill("abcxyz", 3, '*'));
        assertEquals("a...", StrLib.fill("abcxyz", 4, '*'));   // ellipsis
        assertEquals("ab...", StrLib.fill("abcxyz", 5, '*'));  // ellipsis
        assertEquals("abcxyz", StrLib.fill("abcxyz", 6, '*'));
        assertEquals("abcxyz*", StrLib.fill("abcxyz", 7, '*'));
        assertEquals("abcxyz**", StrLib.fill("abcxyz", 8, '*'));
        
    }
    
    public void testEllipsis() {
        
        // ellipsis(null)
        assertNull(StrLib.ellipsis(null, -2));
        assertNull(StrLib.ellipsis(null, -1));
        assertNull(StrLib.ellipsis(null, 0));
        assertNull(StrLib.ellipsis(null, 1));
        assertNull(StrLib.ellipsis(null, 2));

        // ellipsis(empty, -n), ellipsis(empty, 0), ellipsis(empty, n)
        assertEquals("", StrLib.ellipsis("", -2));
        assertEquals("", StrLib.ellipsis("", -1));
        assertEquals("", StrLib.ellipsis("", 0));
        assertEquals("", StrLib.ellipsis("", 1));
        assertEquals("", StrLib.ellipsis("", 2));

        // char

        // ellipsis(char, -n), ellipsis(char, 0), ellipsis(char, n)
        assertEquals("a", StrLib.ellipsis("a", -2));
        assertEquals("a", StrLib.ellipsis("a", -1));
        assertEquals("a", StrLib.ellipsis("a", 0));
        assertEquals("a", StrLib.ellipsis("a", 1));
        assertEquals("a", StrLib.ellipsis("a", 2));

        // string

        // ellipsis(str, -n), ellipsis(str, 0), ellipsis(str, n)
        assertEquals("abcxyz", StrLib.ellipsis("abcxyz", -2));
        assertEquals("abcxyz", StrLib.ellipsis("abcxyz", -1));
        assertEquals("abcxyz", StrLib.ellipsis("abcxyz", 0));
        assertEquals("a", StrLib.ellipsis("abcxyz", 1));
        assertEquals("ab", StrLib.ellipsis("abcxyz", 2));
        assertEquals("abc", StrLib.ellipsis("abcxyz", 3));
        assertEquals("a...", StrLib.ellipsis("abcxyz", 4));   // ellipsis
        assertEquals("ab...", StrLib.ellipsis("abcxyz", 5));  // ellipsis
        assertEquals("abcxyz", StrLib.ellipsis("abcxyz", 6));
        assertEquals("abcxyz", StrLib.ellipsis("abcxyz", 7));
        assertEquals("abcxyz", StrLib.ellipsis("abcxyz", 8));
        
    }
    
    public void testTrunc() {
        
        // trunc(null)
        assertNull(StrLib.trunc(null, -2));
        assertNull(StrLib.trunc(null, -1));
        assertNull(StrLib.trunc(null, 0));
        assertNull(StrLib.trunc(null, 1));
        assertNull(StrLib.trunc(null, 2));

        // ellipsis=false
        assertNull(StrLib.trunc(null, -2, false));
        assertNull(StrLib.trunc(null, -1, false));
        assertNull(StrLib.trunc(null, 0, false));
        assertNull(StrLib.trunc(null, 1, false));
        assertNull(StrLib.trunc(null, 2, false));

        // ellipsis=true
        assertNull(StrLib.trunc(null, -2, true));
        assertNull(StrLib.trunc(null, -1, true));
        assertNull(StrLib.trunc(null, 0, true));
        assertNull(StrLib.trunc(null, 1, true));
        assertNull(StrLib.trunc(null, 2, true));

        // trunc(empty, -n), trunc(empty, 0), trunc(empty, n)
        assertEquals("", StrLib.trunc("", -2));
        assertEquals("", StrLib.trunc("", -1));
        assertEquals("", StrLib.trunc("", 0));
        assertEquals("", StrLib.trunc("", 1));
        assertEquals("", StrLib.trunc("", 2));

        // ellipsis=false
        assertEquals("", StrLib.trunc("", -2, false));
        assertEquals("", StrLib.trunc("", -1, false));
        assertEquals("", StrLib.trunc("", 0, false));
        assertEquals("", StrLib.trunc("", 1, false));
        assertEquals("", StrLib.trunc("", 2, false));

        // ellipsis=true
        assertEquals("", StrLib.trunc("", -2, true));
        assertEquals("", StrLib.trunc("", -1, true));
        assertEquals("", StrLib.trunc("", 0, true));
        assertEquals("", StrLib.trunc("", 1, true));
        assertEquals("", StrLib.trunc("", 2, true));

        // char

        // trunc(char, -n), trunc(char, 0), trunc(char, n)
        assertEquals("a", StrLib.trunc("a", -2));
        assertEquals("a", StrLib.trunc("a", -1));
        assertEquals("a", StrLib.trunc("a", 0));
        assertEquals("a", StrLib.trunc("a", 1));
        assertEquals("a", StrLib.trunc("a", 2));

        // ellipsis=false
        assertEquals("a", StrLib.trunc("a", -2, false));
        assertEquals("a", StrLib.trunc("a", -1, false));
        assertEquals("a", StrLib.trunc("a", 0, false));
        assertEquals("a", StrLib.trunc("a", 1, false));
        assertEquals("a", StrLib.trunc("a", 2, false));

        // ellipsis=true
        assertEquals("a", StrLib.trunc("a", -2, true));
        assertEquals("a", StrLib.trunc("a", -1, true));
        assertEquals("a", StrLib.trunc("a", 0, true));
        assertEquals("a", StrLib.trunc("a", 1, true));
        assertEquals("a", StrLib.trunc("a", 2, true));

        // string

        // trunc(str, -n), trunc(str, 0), trunc(str, n)
        assertEquals("abcxyz", StrLib.trunc("abcxyz", -2));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", -1));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 0));
        assertEquals("a", StrLib.trunc("abcxyz", 1));
        assertEquals("ab", StrLib.trunc("abcxyz", 2));
        assertEquals("abc", StrLib.trunc("abcxyz", 3));        
        assertEquals("abcx", StrLib.trunc("abcxyz", 4));   // non ellipsis
        assertEquals("abcxy", StrLib.trunc("abcxyz", 5));  // non ellipsis
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 6));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 7));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 8));

        // ellipsis=false
        assertEquals("abcxyz", StrLib.trunc("abcxyz", -2, false));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", -1, false));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 0, false));
        assertEquals("a", StrLib.trunc("abcxyz", 1, false));
        assertEquals("ab", StrLib.trunc("abcxyz", 2, false));
        assertEquals("abc", StrLib.trunc("abcxyz", 3, false));
        assertEquals("abcx", StrLib.trunc("abcxyz", 4, false));   // non ellipsis
        assertEquals("abcxy", StrLib.trunc("abcxyz", 5, false));  // non ellipsis
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 6, false));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 7, false));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 8, false));

        // ellipsis=true
        assertEquals("abcxyz", StrLib.trunc("abcxyz", -2, true));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", -1, true));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 0, true));
        assertEquals("a", StrLib.trunc("abcxyz", 1, true));
        assertEquals("ab", StrLib.trunc("abcxyz", 2, true));
        assertEquals("abc", StrLib.trunc("abcxyz", 3, true));        
        assertEquals("a...", StrLib.trunc("abcxyz", 4, true));   // ellipsis
        assertEquals("ab...", StrLib.trunc("abcxyz", 5, true));  // ellipsis
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 6, true));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 7, true));
        assertEquals("abcxyz", StrLib.trunc("abcxyz", 8, true));
        
    }

    public void testLeft() {

        // left(null)
        assertNull(StrLib.left(null, -2));
        assertNull(StrLib.left(null, -1));
        assertNull(StrLib.left(null, 0));
        assertNull(StrLib.left(null, 1));
        assertNull(StrLib.left(null, 2));

        // left(empty, -n), left(empty, 0), left(empty, n)
        assertEquals("", StrLib.left("", -2));
        assertEquals("", StrLib.left("", -1));
        assertEquals("", StrLib.left("", 0));
        assertEquals("", StrLib.left("", 1));
        assertEquals("", StrLib.left("", 2));

        // char

        // left(char, -n), left(char, 0), left(char, n)
        assertEquals("", StrLib.left("a", -2));
        assertEquals("", StrLib.left("a", -1));
        assertEquals("", StrLib.left("a", 0));
        assertEquals("a", StrLib.left("a", 1));
        assertEquals("a", StrLib.left("a", 2));

        // string

        // left(str, -n), left(str, 0), left(str, n)
        assertEquals("", StrLib.left("abcxyz", -2));
        assertEquals("", StrLib.left("abcxyz", -1));
        assertEquals("", StrLib.left("abcxyz", 0));
        assertEquals("a", StrLib.left("abcxyz", 1));
        assertEquals("ab", StrLib.left("abcxyz", 2));
        assertEquals("abc", StrLib.left("abcxyz", 3));        
        assertEquals("abcx", StrLib.left("abcxyz", 4));
        assertEquals("abcxy", StrLib.left("abcxyz", 5));
        assertEquals("abcxyz", StrLib.left("abcxyz", 6));
        assertEquals("abcxyz", StrLib.left("abcxyz", 7));
        assertEquals("abcxyz", StrLib.left("abcxyz", 8));

    }

    public void testRight() {
        
        // right(null)
        assertNull(StrLib.right(null, -2));
        assertNull(StrLib.right(null, -1));
        assertNull(StrLib.right(null, 0));
        assertNull(StrLib.right(null, 1));
        assertNull(StrLib.right(null, 2));

        // right(empty, -n), right(empty, 0), right(empty, n)
        assertEquals("", StrLib.right("", -2));
        assertEquals("", StrLib.right("", -1));
        assertEquals("", StrLib.right("", 0));
        assertEquals("", StrLib.right("", 1));
        assertEquals("", StrLib.right("", 2));

        // char

        // right(char, -n), right(char, 0), right(char, n)
        assertEquals("", StrLib.right("a", -2));
        assertEquals("", StrLib.right("a", -1));
        assertEquals("", StrLib.right("a", 0));
        assertEquals("a", StrLib.right("a", 1));
        assertEquals("a", StrLib.right("a", 2));

        // string

        // right(str, -n), right(str, 0), right(str, n)
        assertEquals("", StrLib.right("abcxyz", -2));
        assertEquals("", StrLib.right("abcxyz", -1));
        assertEquals("", StrLib.right("abcxyz", 0));
        assertEquals("z", StrLib.right("abcxyz", 1));
        assertEquals("yz", StrLib.right("abcxyz", 2));
        assertEquals("xyz", StrLib.right("abcxyz", 3));        
        assertEquals("cxyz", StrLib.right("abcxyz", 4));
        assertEquals("bcxyz", StrLib.right("abcxyz", 5));
        assertEquals("abcxyz", StrLib.right("abcxyz", 6));
        assertEquals("abcxyz", StrLib.right("abcxyz", 7));
        assertEquals("abcxyz", StrLib.right("abcxyz", 8));

    }
    
    // 3.1

    public void testCapitalize() {
        
        // capitalize(null), capitalize(empty)
        assertNull(StrLib.capitalize(null));
        assertEquals("", StrLib.capitalize(""));

        assertEquals(" ", StrLib.capitalize(" "));
        assertEquals("  ", StrLib.capitalize("  "));

        assertEquals("A", StrLib.capitalize("a"));
        assertEquals("Ab", StrLib.capitalize("ab"));
        assertEquals("Abc", StrLib.capitalize("abc"));
        assertEquals("Abcd", StrLib.capitalize("abcd"));
        
        // ForceRest=default
        assertEquals("AB", StrLib.capitalize("aB"));
        assertEquals("ABc", StrLib.capitalize("aBc"));
        assertEquals("ABcd", StrLib.capitalize("aBcd"));

        assertEquals("Hello world!", StrLib.capitalize("Hello world!"));
        assertEquals("Hello world!", StrLib.capitalize("hello world!"));
                
        // ForceRest=false        
        assertEquals("AB", StrLib.capitalize("aB", false));
        assertEquals("ABc", StrLib.capitalize("aBc", false));
        assertEquals("ABcd", StrLib.capitalize("aBcd", false));

        assertEquals("Hello world!", StrLib.capitalize("Hello world!", false));
        assertEquals("Hello world!", StrLib.capitalize("hello world!", false));
        
        // ForceRest=true
        assertEquals("Ab", StrLib.capitalize("aB", true));
        assertEquals("Abc", StrLib.capitalize("aBc", true));
        assertEquals("Abcd", StrLib.capitalize("aBcd", true));

        assertEquals("Hello world!", StrLib.capitalize("Hello world!", true));
        assertEquals("Hello world!", StrLib.capitalize("hello world!", true));
        
        //
        assertEquals("Hello world!", StrLib.capitalize("Hello World!", true));
        assertEquals("Hello world!", StrLib.capitalize("hello World!", true));
        
    }

    public void testDecapitalize() {

        // decapitalize(null), decapitalize(empty)
        assertNull(StrLib.decapitalize(null));
        assertEquals("", StrLib.decapitalize(""));

        assertEquals(" ", StrLib.decapitalize(" "));
        assertEquals("  ", StrLib.decapitalize("  "));

        assertEquals("a", StrLib.decapitalize("A"));
        assertEquals("ab", StrLib.decapitalize("Ab"));
        assertEquals("abc", StrLib.decapitalize("Abc"));
        assertEquals("abcd", StrLib.decapitalize("Abcd"));

        // ForceRest=default
        assertEquals("ab", StrLib.decapitalize("Ab"));
        assertEquals("abC", StrLib.decapitalize("AbC"));
        assertEquals("abCd", StrLib.decapitalize("AbCd"));

        assertEquals("hello world!", StrLib.decapitalize("hello world!"));
        assertEquals("hello world!", StrLib.decapitalize("Hello world!"));

        // ForceRest=false
        assertEquals("ab", StrLib.decapitalize("Ab", false));
        assertEquals("abC", StrLib.decapitalize("AbC", false));
        assertEquals("abCd", StrLib.decapitalize("AbCd", false));

        assertEquals("hello world!", StrLib.decapitalize("hello world!", false));
        assertEquals("hello world!", StrLib.decapitalize("Hello world!", false));

        // ForceRest=true
        assertEquals("aB", StrLib.decapitalize("Ab", true));
        assertEquals("aBC", StrLib.decapitalize("AbC", true));
        assertEquals("aBCD", StrLib.decapitalize("AbCd", true));

        //
        assertEquals("hELLO WORLD!", StrLib.decapitalize("hello world!", true));
        assertEquals("hELLO WORLD!", StrLib.decapitalize("Hello world!", true));        

    }

    public void testUpper() {

        // upper(null), upper(empty)
        assertNull(StrLib.upper(null));
        assertEquals("", StrLib.upper(""));

        // upper(blank)
        assertEquals(" ", StrLib.upper(" "));
        assertEquals("  ", StrLib.upper("  "));

        assertEquals("0123456789.,:!?", StrLib.upper("0123456789.,:!?"));

        // upper(value)
        assertEquals("A", StrLib.upper("A"));
        assertEquals("AB", StrLib.upper("AB"));
        assertEquals("ABC", StrLib.upper("ABC"));
        assertEquals("ABCD", StrLib.upper("ABCD"));

        assertEquals("A", StrLib.upper("a"));
        assertEquals("AB", StrLib.upper("ab"));
        assertEquals("ABC", StrLib.upper("abc"));
        assertEquals("ABCD", StrLib.upper("abcd"));

        assertEquals("AB", StrLib.upper("aB"));
        assertEquals("ABC", StrLib.upper("aBc"));
        assertEquals("ABCD", StrLib.upper("aBcD"));

    }

    public void testLower() {

        // lower(null), lower(empty)
        assertNull(StrLib.lower(null));
        assertEquals("", StrLib.lower(""));

        // lower(blank)
        assertEquals(" ", StrLib.lower(" "));
        assertEquals("  ", StrLib.lower("  "));

        assertEquals("0123456789.,:!?", StrLib.lower("0123456789.,:!?"));

        // lower(value)
        assertEquals("a", StrLib.lower("a"));
        assertEquals("ab", StrLib.lower("ab"));
        assertEquals("abc", StrLib.lower("abc"));
        assertEquals("abcd", StrLib.lower("abcd"));

        assertEquals("a", StrLib.lower("A"));
        assertEquals("ab", StrLib.lower("AB"));
        assertEquals("abc", StrLib.lower("ABC"));
        assertEquals("abcd", StrLib.lower("ABCD"));

        assertEquals("ab", StrLib.lower("Ab"));
        assertEquals("abc", StrLib.lower("AbC"));
        assertEquals("abcd", StrLib.lower("AbCd"));

    }

    public void testToUpperCase() {

        // toUpperCase(null), toUpperCase(empty)
        assertNull(StrLib.toUpperCase(null));
        assertEquals("", StrLib.toUpperCase(""));

        // toUpperCase(blank)
        assertEquals(" ", StrLib.toUpperCase(" "));
        assertEquals("  ", StrLib.toUpperCase("  "));

        assertEquals("0123456789.,:!?", StrLib.toUpperCase("0123456789.,:!?"));

        // toUpperCase(value)
        assertEquals("A", StrLib.toUpperCase("A"));
        assertEquals("AB", StrLib.toUpperCase("AB"));
        assertEquals("ABC", StrLib.toUpperCase("ABC"));
        assertEquals("ABCD", StrLib.toUpperCase("ABCD"));

        assertEquals("A", StrLib.toUpperCase("a"));
        assertEquals("AB", StrLib.toUpperCase("ab"));
        assertEquals("ABC", StrLib.toUpperCase("abc"));
        assertEquals("ABCD", StrLib.toUpperCase("abcd"));

        assertEquals("AB", StrLib.toUpperCase("aB"));
        assertEquals("ABC", StrLib.toUpperCase("aBc"));
        assertEquals("ABCD", StrLib.toUpperCase("aBcD"));

    }

    public void testToLowerCase() {

        // toLowerCase(null), toLowerCase(empty)
        assertNull(StrLib.toLowerCase(null));
        assertEquals("", StrLib.toLowerCase(""));

        // toLowerCase(blank)
        assertEquals(" ", StrLib.toLowerCase(" "));
        assertEquals("  ", StrLib.toLowerCase("  "));

        assertEquals("0123456789.,:!?", StrLib.toLowerCase("0123456789.,:!?"));

        // toLowerCase(value)
        assertEquals("a", StrLib.toLowerCase("a"));
        assertEquals("ab", StrLib.toLowerCase("ab"));
        assertEquals("abc", StrLib.toLowerCase("abc"));
        assertEquals("abcd", StrLib.toLowerCase("abcd"));

        assertEquals("a", StrLib.toLowerCase("A"));
        assertEquals("ab", StrLib.toLowerCase("AB"));
        assertEquals("abc", StrLib.toLowerCase("ABC"));
        assertEquals("abcd", StrLib.toLowerCase("ABCD"));

        assertEquals("ab", StrLib.toLowerCase("Ab"));
        assertEquals("abc", StrLib.toLowerCase("AbC"));
        assertEquals("abcd", StrLib.toLowerCase("AbCd"));

    }

    public void testToCase() {

        //// Upper ////

        // toCase(null), toCase(empty, true)
        assertNull(StrLib.toCase(null, true));
        assertEquals("", StrLib.toCase("", true));

        // toCase(blank, true)
        assertEquals(" ", StrLib.toCase(" ", true));
        assertEquals("  ", StrLib.toCase("  ", true));

        assertEquals("0123456789.,:!?", StrLib.toCase("0123456789.,:!?", true));

        // toCase(value, true)
        assertEquals("A", StrLib.toCase("A", true));
        assertEquals("AB", StrLib.toCase("AB", true));
        assertEquals("ABC", StrLib.toCase("ABC", true));
        assertEquals("ABCD", StrLib.toCase("ABCD", true));

        assertEquals("A", StrLib.toCase("a", true));
        assertEquals("AB", StrLib.toCase("ab", true));
        assertEquals("ABC", StrLib.toCase("abc", true));
        assertEquals("ABCD", StrLib.toCase("abcd", true));

        assertEquals("AB", StrLib.toCase("aB", true));
        assertEquals("ABC", StrLib.toCase("aBc", true));
        assertEquals("ABCD", StrLib.toCase("aBcD", true));

        //// Lower ////

        // toCase(null, false), toCase(empty, false)
        assertNull(StrLib.toCase(null, false));
        assertEquals("", StrLib.toCase("", false));

        // toCase(blank, false)
        assertEquals(" ", StrLib.toCase(" ", false));
        assertEquals("  ", StrLib.toCase("  ", false));

        assertEquals("0123456789.,:!?", StrLib.toCase("0123456789.,:!?", false));

        // toCase(value, false)
        assertEquals("a", StrLib.toCase("a", false));
        assertEquals("ab", StrLib.toCase("ab", false));
        assertEquals("abc", StrLib.toCase("abc", false));
        assertEquals("abcd", StrLib.toCase("abcd", false));

        assertEquals("a", StrLib.toCase("A", false));
        assertEquals("ab", StrLib.toCase("AB", false));
        assertEquals("abc", StrLib.toCase("ABC", false));
        assertEquals("abcd", StrLib.toCase("ABCD", false));

        assertEquals("ab", StrLib.toCase("Ab", false));
        assertEquals("abc", StrLib.toCase("AbC", false));
        assertEquals("abcd", StrLib.toCase("AbCd", false));


    }
    
    ////
    
    public void testGetCaseCode() {
        
        // getCaseCode(null), getCaseCode(empty)
        assertEquals(StrLib.CT_NONE, StrLib.getCaseCode(null));
        assertEquals(StrLib.CT_NONE, StrLib.getCaseCode(""));
        assertEquals(StrLib.CT_NONE, StrLib.getCaseCode(" "));
        assertEquals(StrLib.CT_NONE, StrLib.getCaseCode(" "));

        // getCaseCode(unknown)
        assertEquals(StrLib.CT_NONE, StrLib.getCaseCode("blahblahblah"));
        assertEquals(StrLib.CT_NONE, StrLib.getCaseCode("0123456789"));

        // lowercase
        assertEquals(StrLib.CT_lowercase, StrLib.getCaseCode("lower"));

        // UPPERCASE
        assertEquals(StrLib.CT_UPPERCASE, StrLib.getCaseCode("upper"));

        // camelCase
        assertEquals(StrLib.CT_camelCase, StrLib.getCaseCode("camel"));

        // PascalCase
        assertEquals(StrLib.CT_PascalCase, StrLib.getCaseCode("Camel"));
        assertEquals(StrLib.CT_PascalCase, StrLib.getCaseCode("Pascal"));
        assertEquals(StrLib.CT_PascalCase, StrLib.getCaseCode("pascal"));

        // snake_case
        assertEquals(StrLib.CT_snake_case, StrLib.getCaseCode("snake"));

        // SNAKE_CASE
        assertEquals(StrLib.CT_SNAKE_CASE, StrLib.getCaseCode("SNAKE"));
        assertEquals(StrLib.CT_SNAKE_CASE, StrLib.getCaseCode("MACRO"));
        assertEquals(StrLib.CT_SNAKE_CASE, StrLib.getCaseCode("macro"));

        // kebab-case
        assertEquals(StrLib.CT_kebab_case, StrLib.getCaseCode("kebab"));
        assertEquals(StrLib.CT_kebab_case, StrLib.getCaseCode("dash"));
        assertEquals(StrLib.CT_kebab_case, StrLib.getCaseCode("train"));
        assertEquals(StrLib.CT_kebab_case, StrLib.getCaseCode("lisp"));

        // KEBAB-CASE
        assertEquals(StrLib.CT_KEBAB_CASE, StrLib.getCaseCode("KEBAB"));
        assertEquals(StrLib.CT_KEBAB_CASE, StrLib.getCaseCode("DASH"));
        assertEquals(StrLib.CT_KEBAB_CASE, StrLib.getCaseCode("TRAIN"));
        assertEquals(StrLib.CT_KEBAB_CASE, StrLib.getCaseCode("COBOL"));
        assertEquals(StrLib.CT_KEBAB_CASE, StrLib.getCaseCode("cobol"));

        // CT_Kebab_Case
        assertEquals(StrLib.CT_Kebab_Case, StrLib.getCaseCode("Kebab"));
        assertEquals(StrLib.CT_Kebab_Case, StrLib.getCaseCode("Dash"));
        assertEquals(StrLib.CT_Kebab_Case, StrLib.getCaseCode("Train"));
        
    }
    
    ////

    public void testToCamelCase() {
        
        // toCamelCase(null), toCamelCase(empty)
        assertNull(StrLib.toCamelCase(null));
        assertEquals("", StrLib.toCamelCase(""));
        assertEquals(" ", StrLib.toCamelCase(" "));
        assertEquals("  ", StrLib.toCamelCase("  "));

        // capitalize = default
        assertEquals("ProductName", StrLib.toCamelCase("product name"));
        assertEquals("ProductName", StrLib.toCamelCase("product-name"));
        assertEquals("ProductName", StrLib.toCamelCase("product_name"));
        assertEquals("ProductName", StrLib.toCamelCase("productName"));
        
        assertEquals("ProductName", StrLib.toCamelCase("Product Name"));
        assertEquals("ProductName", StrLib.toCamelCase("Product-Name"));
        assertEquals("ProductName", StrLib.toCamelCase("Product_Name"));
        assertEquals("ProductName", StrLib.toCamelCase("ProductName"));
        
        assertEquals("ProductFullName", StrLib.toCamelCase("product full name"));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-full-name"));
        assertEquals("ProductFullName", StrLib.toCamelCase("product_full_name"));
        
        assertEquals("ProductFullName", StrLib.toCamelCase("product Full name"));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-Full-name"));
        assertEquals("ProductFullName", StrLib.toCamelCase("product_Full_name"));
        
        assertEquals("ProductFullName", StrLib.toCamelCase("product-_ full -_name"));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-_ Full -_name"));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-_ FullName"));

        // capitalize = true
        assertEquals("ProductName", StrLib.toCamelCase("product name", true));
        assertEquals("ProductName", StrLib.toCamelCase("product-name", true));
        assertEquals("ProductName", StrLib.toCamelCase("product_name", true));
        assertEquals("ProductName", StrLib.toCamelCase("productName", true));
        
        assertEquals("ProductName", StrLib.toCamelCase("Product Name", true));
        assertEquals("ProductName", StrLib.toCamelCase("Product-Name", true));
        assertEquals("ProductName", StrLib.toCamelCase("Product_Name", true));
        assertEquals("ProductName", StrLib.toCamelCase("ProductName", true));
        
        assertEquals("ProductFullName", StrLib.toCamelCase("product full name", true));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-full-name", true));
        assertEquals("ProductFullName", StrLib.toCamelCase("product_full_name", true));
        
        assertEquals("ProductFullName", StrLib.toCamelCase("product Full name", true));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-Full-name", true));
        assertEquals("ProductFullName", StrLib.toCamelCase("product_Full_name", true));
        
        assertEquals("ProductFullName", StrLib.toCamelCase("product-_ full -_name", true));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-_ Full -_name", true));
        assertEquals("ProductFullName", StrLib.toCamelCase("product-_ FullName", true));

        // capitalize = false
        assertEquals("productName", StrLib.toCamelCase("product name", false));
        assertEquals("productName", StrLib.toCamelCase("product-name", false));
        assertEquals("productName", StrLib.toCamelCase("product_name", false));
        assertEquals("productName", StrLib.toCamelCase("productName", false));
        
        assertEquals("productName", StrLib.toCamelCase("Product Name", false));
        assertEquals("productName", StrLib.toCamelCase("Product-Name", false));
        assertEquals("productName", StrLib.toCamelCase("Product_Name", false));
        assertEquals("productName", StrLib.toCamelCase("ProductName", false));
        
        assertEquals("productFullName", StrLib.toCamelCase("product full name", false));
        assertEquals("productFullName", StrLib.toCamelCase("product-full-name", false));
        assertEquals("productFullName", StrLib.toCamelCase("product_full_name", false));
        
        assertEquals("productFullName", StrLib.toCamelCase("product Full name", false));
        assertEquals("productFullName", StrLib.toCamelCase("product-Full-name", false));
        assertEquals("productFullName", StrLib.toCamelCase("product_Full_name", false));
        
        assertEquals("productFullName", StrLib.toCamelCase("product-_ full -_name", false));
        assertEquals("productFullName", StrLib.toCamelCase("product-_ Full -_name", false));
        assertEquals("productFullName", StrLib.toCamelCase("product-_ FullName", false));
        
    }

    public void testToSnakeCase() {
        
        // toSnakeCase(None), toSnakeCase(empty)
        assertNull(StrLib.toSnakeCase(null));
        assertEquals("", StrLib.toSnakeCase(""));
        assertEquals(" ", StrLib.toSnakeCase(" "));
        assertEquals("  ", StrLib.toSnakeCase("  "));

        // toSnakeCase(value)        
        assertEquals("product_name", StrLib.toSnakeCase("product name"));
        assertEquals("product_name", StrLib.toSnakeCase("product-name"));
        assertEquals("product_name", StrLib.toSnakeCase("product_name"));        
        assertEquals("product_name", StrLib.toSnakeCase("ProductName"));

        // upper = false
        assertEquals("product_name", StrLib.toSnakeCase("product name", false));
        assertEquals("product_name", StrLib.toSnakeCase("product-name", false));
        assertEquals("product_name", StrLib.toSnakeCase("product_name", false));        
        assertEquals("product_name", StrLib.toSnakeCase("ProductName", false));

        // upper = true
        assertEquals("PRODUCT_NAME", StrLib.toSnakeCase("product name", true));
        assertEquals("PRODUCT_NAME", StrLib.toSnakeCase("product-name", true));
        assertEquals("PRODUCT_NAME", StrLib.toSnakeCase("product_name", true));        
        assertEquals("PRODUCT_NAME", StrLib.toSnakeCase("ProductName", true));

    }

    public void testToKebabCase() {
        
        // toKebabCase(None), toKebabCase(empty)
        assertNull(StrLib.toKebabCase(null));
        assertEquals("", StrLib.toKebabCase(""));
        assertEquals(" ", StrLib.toKebabCase(" "));
        assertEquals("  ", StrLib.toKebabCase("  "));

        // toKebabCase(value)        
        assertEquals("product-name", StrLib.toKebabCase("product name"));
        assertEquals("product-name", StrLib.toKebabCase("product-name"));
        assertEquals("product-name", StrLib.toKebabCase("product_name"));        
        assertEquals("product-name", StrLib.toKebabCase("ProductName"));
        
        // upper = false
        assertEquals("product-name", StrLib.toKebabCase("product name", false));
        assertEquals("product-name", StrLib.toKebabCase("product-name", false));
        assertEquals("product-name", StrLib.toKebabCase("product_name", false));        
        assertEquals("product-name", StrLib.toKebabCase("ProductName", false));

        // upper = true
        assertEquals("PRODUCT-NAME", StrLib.toKebabCase("product name", true));
        assertEquals("PRODUCT-NAME", StrLib.toKebabCase("product-name", true));
        assertEquals("PRODUCT-NAME", StrLib.toKebabCase("product_name", true));        
        assertEquals("PRODUCT-NAME", StrLib.toKebabCase("ProductName", true));
        
    }
    
    public void testReverse() {
        
        // reverse(null), reverse(empty)
        assertNull(StrLib.reverse(null));
        assertEquals("", StrLib.reverse(""));

        // reverse(blank)
        assertEquals(" ", StrLib.reverse(" "));
        assertEquals("  ", StrLib.reverse("  "));

        // reverse(value)
        assertEquals("*", StrLib.reverse("*"));
        assertEquals("**", StrLib.reverse("**"));
        assertEquals("***", StrLib.reverse("***"));
        assertEquals("****", StrLib.reverse("****"));
        assertEquals("*****", StrLib.reverse("*****"));
        assertEquals("******", StrLib.reverse("******"));

        assertEquals("a", StrLib.reverse("a"));
        assertEquals("ba", StrLib.reverse("ab"));
        assertEquals("cba", StrLib.reverse("abc"));
        assertEquals("dcba", StrLib.reverse("abcd"));
        assertEquals("edcba", StrLib.reverse("abcde"));
        assertEquals("fedcba", StrLib.reverse("abcdef"));
                
    }
    
    // 4.1
    
    public void testStartsWith() {
        
        // startsWith(null, value)
        assertFalse(StrLib.startsWith(null, null));
        assertFalse(StrLib.startsWith(null, ""));
        assertFalse(StrLib.startsWith(null, " "));
        assertFalse(StrLib.startsWith(null, "abc"));
        
        // startsWith(empty, value)        
        assertFalse(StrLib.startsWith("", null));
        assertFalse(StrLib.startsWith("", ""));     // important
        assertFalse(StrLib.startsWith("", " "));
        assertFalse(StrLib.startsWith("", "abc"));

        // startsWith(blank, value)        
        assertFalse(StrLib.startsWith(" ", null));
        assertFalse(StrLib.startsWith(" ", ""));   // important
        assertTrue(StrLib.startsWith(" ", " "));   // True
        assertFalse(StrLib.startsWith(" ", "abc"));

        // startsWith(value, value)
        
        // False
        assertFalse(StrLib.startsWith("abc", null));
        assertFalse(StrLib.startsWith("abc", "")); // important
        assertFalse(StrLib.startsWith("abc", " "));
        assertFalse(StrLib.startsWith("abc", "xyz"));
        
        // True
        assertTrue(StrLib.startsWith("abc", "a"));
        assertTrue(StrLib.startsWith("abc", "ab"));
        assertTrue(StrLib.startsWith("abc", "abc"));

        ////
        assertTrue(StrLib.startsWith("myfile.txt", "my"));
        assertTrue(StrLib.startsWith("myfile.txt", "myfile"));
        assertTrue(StrLib.startsWith("myfile.txt", "myfile.txt"));
        
    }

    public void testStartsWithIgnoreCase() {
        
        // startsWithIgnoreCase(null, value)
        assertFalse(StrLib.startsWithIgnoreCase(null, null));
        assertFalse(StrLib.startsWithIgnoreCase(null, ""));
        assertFalse(StrLib.startsWithIgnoreCase(null, " "));
        assertFalse(StrLib.startsWithIgnoreCase(null, "abc"));
        
        // startsWithIgnoreCase(empty, value)        
        assertFalse(StrLib.startsWithIgnoreCase("", null));
        assertFalse(StrLib.startsWithIgnoreCase("", ""));     // important
        assertFalse(StrLib.startsWithIgnoreCase("", " "));
        assertFalse(StrLib.startsWithIgnoreCase("", "abc"));

        // startsWithIgnoreCase(blank, value)        
        assertFalse(StrLib.startsWithIgnoreCase(" ", null));
        assertFalse(StrLib.startsWithIgnoreCase(" ", ""));   // important
        assertTrue(StrLib.startsWithIgnoreCase(" ", " "));   // True
        assertFalse(StrLib.startsWithIgnoreCase(" ", "abc"));

        // startsWithIgnoreCase(value, value)
        
        // False
        assertFalse(StrLib.startsWithIgnoreCase("abc", null));
        assertFalse(StrLib.startsWithIgnoreCase("abc", "")); // important
        assertFalse(StrLib.startsWithIgnoreCase("abc", " "));
        assertFalse(StrLib.startsWithIgnoreCase("abc", "xyz"));
        
        // True
        assertTrue(StrLib.startsWithIgnoreCase("abc", "a"));
        assertTrue(StrLib.startsWithIgnoreCase("abc", "ab"));
        assertTrue(StrLib.startsWithIgnoreCase("abc", "abc"));

        // True - IgnoreCase
        assertTrue(StrLib.startsWithIgnoreCase("abc", "A"));
        assertTrue(StrLib.startsWithIgnoreCase("abc", "Ab"));
        assertTrue(StrLib.startsWithIgnoreCase("abc", "AbC"));

        ////
        assertTrue(StrLib.startsWithIgnoreCase("myfile.txt", "my"));
        assertTrue(StrLib.startsWithIgnoreCase("myfile.txt", "myfile"));
        assertTrue(StrLib.startsWithIgnoreCase("myfile.txt", "myfile.txt"));

        assertTrue(StrLib.startsWithIgnoreCase("myfile.txt", "My"));
        assertTrue(StrLib.startsWithIgnoreCase("myfile.txt", "Myfile"));
        assertTrue(StrLib.startsWithIgnoreCase("myfile.txt", "Myfile.TxT"));

    }

    public void testEndsWith() {

        // endsWith(null, value)
        assertFalse(StrLib.endsWith(null, null));
        assertFalse(StrLib.endsWith(null, ""));
        assertFalse(StrLib.endsWith(null, " "));
        assertFalse(StrLib.endsWith(null, "abc"));
        
        // endsWith(empty, value)        
        assertFalse(StrLib.endsWith("", null));
        assertFalse(StrLib.endsWith("", ""));     // important
        assertFalse(StrLib.endsWith("", " "));
        assertFalse(StrLib.endsWith("", "abc"));

        // endsWith(blank, value)        
        assertFalse(StrLib.endsWith(" ", null));
        assertFalse(StrLib.endsWith(" ", ""));   // important
        assertTrue(StrLib.endsWith(" ", " "));   // True
        assertFalse(StrLib.endsWith(" ", "abc"));
                
        // endsWith(value, value)

        // False
        assertFalse(StrLib.endsWith("abc", null));
        assertFalse(StrLib.endsWith("abc", "")); // important
        assertFalse(StrLib.endsWith("abc", " "));
        assertFalse(StrLib.endsWith("abc", "xyz"));

        // True
        assertTrue(StrLib.endsWith("abc", "c"));
        assertTrue(StrLib.endsWith("abc", "bc"));
        assertTrue(StrLib.endsWith("abc", "abc"));

        ////
        assertTrue(StrLib.endsWith("myfile.txt", "txt"));
        assertTrue(StrLib.endsWith("myfile.txt", ".txt"));
        assertTrue(StrLib.endsWith("myfile.txt", "myfile.txt"));
        
    }

    public void testEndsWithIgnoreCase() {

        // endsWithIgnoreCase(null, value)
        assertFalse(StrLib.endsWithIgnoreCase(null, null));
        assertFalse(StrLib.endsWithIgnoreCase(null, ""));
        assertFalse(StrLib.endsWithIgnoreCase(null, " "));
        assertFalse(StrLib.endsWithIgnoreCase(null, "abc"));
        
        // endsWithIgnoreCase(empty, value)        
        assertFalse(StrLib.endsWithIgnoreCase("", null));
        assertFalse(StrLib.endsWithIgnoreCase("", ""));     // important
        assertFalse(StrLib.endsWithIgnoreCase("", " "));
        assertFalse(StrLib.endsWithIgnoreCase("", "abc"));

        // endsWithIgnoreCase(blank, value)        
        assertFalse(StrLib.endsWithIgnoreCase(" ", null));
        assertFalse(StrLib.endsWithIgnoreCase(" ", ""));   // important
        assertTrue(StrLib.endsWithIgnoreCase(" ", " "));   // True
        assertFalse(StrLib.endsWithIgnoreCase(" ", "abc"));
                
        // endsWithIgnoreCase(value, value)

        // False
        assertFalse(StrLib.endsWithIgnoreCase("abc", null));
        assertFalse(StrLib.endsWithIgnoreCase("abc", "")); // important
        assertFalse(StrLib.endsWithIgnoreCase("abc", " "));
        assertFalse(StrLib.endsWithIgnoreCase("abc", "xyz"));

        // True
        assertTrue(StrLib.endsWithIgnoreCase("abc", "c"));
        assertTrue(StrLib.endsWithIgnoreCase("abc", "bc"));
        assertTrue(StrLib.endsWithIgnoreCase("abc", "abc"));

        // True - IgnoreCase
        assertTrue(StrLib.endsWithIgnoreCase("abc", "C"));
        assertTrue(StrLib.endsWithIgnoreCase("abc", "Bc"));
        assertTrue(StrLib.endsWithIgnoreCase("abc", "aBc"));

        ////
        assertTrue(StrLib.endsWithIgnoreCase("myfile.txt", "txt"));
        assertTrue(StrLib.endsWithIgnoreCase("myfile.txt", ".txt"));
        assertTrue(StrLib.endsWithIgnoreCase("myfile.txt", "myfile.txt"));

        assertTrue(StrLib.endsWithIgnoreCase("myfile.txt", "TxT"));
        assertTrue(StrLib.endsWithIgnoreCase("myfile.txt", ".TxT"));
        assertTrue(StrLib.endsWithIgnoreCase("myfile.txt", "MyFile.TxT"));

    }

    public void testHasPrefix() {
        
        // hasPrefix(null, value)
        assertFalse(StrLib.hasPrefix(null, null));
        assertFalse(StrLib.hasPrefix(null, ""));
        assertFalse(StrLib.hasPrefix(null, " "));
        assertFalse(StrLib.hasPrefix(null, "abc"));
        
        // hasPrefix(empty, value)        
        assertFalse(StrLib.hasPrefix("", null));
        assertFalse(StrLib.hasPrefix("", ""));     // important
        assertFalse(StrLib.hasPrefix("", " "));
        assertFalse(StrLib.hasPrefix("", "abc"));

        // hasPrefix(blank, value)        
        assertFalse(StrLib.hasPrefix(" ", null));
        assertFalse(StrLib.hasPrefix(" ", ""));   // important
        assertTrue(StrLib.hasPrefix(" ", " "));   // True
        assertFalse(StrLib.hasPrefix(" ", "abc"));

        // hasPrefix(value, value)
        
        // False
        assertFalse(StrLib.hasPrefix("abc", null));
        assertFalse(StrLib.hasPrefix("abc", "")); // important
        assertFalse(StrLib.hasPrefix("abc", " "));
        assertFalse(StrLib.hasPrefix("abc", "xyz"));
        
        // True
        assertTrue(StrLib.hasPrefix("abc", "a"));
        assertTrue(StrLib.hasPrefix("abc", "ab"));
        assertTrue(StrLib.hasPrefix("abc", "abc"));

        ////
        assertTrue(StrLib.hasPrefix("myfile.txt", "my"));
        assertTrue(StrLib.hasPrefix("myfile.txt", "myfile"));
        assertTrue(StrLib.hasPrefix("myfile.txt", "myfile.txt"));
        
    }
    
    public void testHasSuffix() {

        // hasSuffix(null, value)
        assertFalse(StrLib.hasSuffix(null, null));
        assertFalse(StrLib.hasSuffix(null, ""));
        assertFalse(StrLib.hasSuffix(null, " "));
        assertFalse(StrLib.hasSuffix(null, "abc"));
        
        // hasSuffix(empty, value)        
        assertFalse(StrLib.hasSuffix("", null));
        assertFalse(StrLib.hasSuffix("", ""));     // important
        assertFalse(StrLib.hasSuffix("", " "));
        assertFalse(StrLib.hasSuffix("", "abc"));

        // hasSuffix(blank, value)        
        assertFalse(StrLib.hasSuffix(" ", null));
        assertFalse(StrLib.hasSuffix(" ", ""));   // important
        assertTrue(StrLib.hasSuffix(" ", " "));   // True
        assertFalse(StrLib.hasSuffix(" ", "abc"));
                
        // hasSuffix(value, value)

        // False
        assertFalse(StrLib.hasSuffix("abc", null));
        assertFalse(StrLib.hasSuffix("abc", "")); // important
        assertFalse(StrLib.hasSuffix("abc", " "));
        assertFalse(StrLib.hasSuffix("abc", "xyz"));

        // True
        assertTrue(StrLib.hasSuffix("abc", "c"));
        assertTrue(StrLib.hasSuffix("abc", "bc"));
        assertTrue(StrLib.hasSuffix("abc", "abc"));

        ////
        assertTrue(StrLib.hasSuffix("myfile.txt", "txt"));
        assertTrue(StrLib.hasSuffix("myfile.txt", ".txt"));
        assertTrue(StrLib.hasSuffix("myfile.txt", "myfile.txt"));
        
    }
        
    // 4.2

    public void testRemovePrefix() {

        // removePrefix(null, value)
        assertNull(StrLib.removePrefix(null, null));
        assertNull(StrLib.removePrefix(null, ""));
        assertNull(StrLib.removePrefix(null, " "));
        assertNull(StrLib.removePrefix(null, "abc"));

        // removePrefix(empty, value)
        assertEquals("", StrLib.removePrefix("", null));
        assertEquals("", StrLib.removePrefix("", ""));
        assertEquals("", StrLib.removePrefix("", " "));
        assertEquals("", StrLib.removePrefix("", "abc"));

        // removePrefix(blank, value)
        assertEquals(" ", StrLib.removePrefix(" ", null));
        assertEquals(" ", StrLib.removePrefix(" ", ""));
        assertEquals("", StrLib.removePrefix(" ", " "));     // True
        assertEquals(" ", StrLib.removePrefix(" ", "abc"));

        // removePrefix(value, value)
        
        // False
        assertEquals("abc", StrLib.removePrefix("abc", null));
        assertEquals("abc", StrLib.removePrefix("abc", ""));
        assertEquals("abc", StrLib.removePrefix("abc", " "));
        assertEquals("abc", StrLib.removePrefix("abc", "xyz"));

        // True
        assertEquals("bc", StrLib.removePrefix("abc", "a"));
        assertEquals("c", StrLib.removePrefix("abc", "ab"));
        assertEquals("", StrLib.removePrefix("abc", "abc"));

        ////
        assertEquals("file.txt", StrLib.removePrefix("myfile.txt", "my"));
        assertEquals(".txt", StrLib.removePrefix("myfile.txt", "myfile"));
        assertEquals("", StrLib.removePrefix("myfile.txt", "myfile.txt"));
        
    }

    public void testRemovePrefixes() {

        // removePrefixes(null, value)
        assertNull(StrLib.removePrefixes(null, null));
        assertNull(StrLib.removePrefixes(null, new String[] {}));
        assertNull(StrLib.removePrefixes(null, new String[] {""}));
        assertNull(StrLib.removePrefixes(null, new String[] {" "}));
        assertNull(StrLib.removePrefixes(null, new String[] {"abc"}));

        // removePrefixes(empty, value)
        assertEquals("", StrLib.removePrefixes("", null));
        assertEquals("", StrLib.removePrefixes("", new String[] {}));
        assertEquals("", StrLib.removePrefixes("", new String[] {""}));
        assertEquals("", StrLib.removePrefixes("", new String[] {" "}));
        assertEquals("", StrLib.removePrefixes("", new String[] {"abc"}));

        // removePrefixes(blank, value)
        assertEquals(" ", StrLib.removePrefixes(" ", null));
        assertEquals(" ", StrLib.removePrefixes(" ", new String[] {}));
        assertEquals(" ", StrLib.removePrefixes(" ", new String[] {""}));
        assertEquals("", StrLib.removePrefixes(" ", new String[] {" "}));     // True
        assertEquals(" ", StrLib.removePrefixes(" ", new String[] {"abc"}));

        // removePrefixes(value, value)
        
        // False
        assertEquals("abc", StrLib.removePrefixes("abc", null));
        assertEquals("abc", StrLib.removePrefixes("abc", new String[] {}));
        assertEquals("abc", StrLib.removePrefixes("abc", new String[] {""}));
        assertEquals("abc", StrLib.removePrefixes("abc", new String[] {" "}));
        assertEquals("abc", StrLib.removePrefixes("abc", new String[] {"xyz"}));
        assertEquals("abc", StrLib.removePrefixes("abc", new String[] {"xyz", "def"}));
        assertEquals("abc", StrLib.removePrefixes("abc", new String[] {"def", "xyz"}));

        // True
        assertEquals("bc", StrLib.removePrefixes("abc", new String[] {"a"}));
        assertEquals("c", StrLib.removePrefixes("abc", new String[] {"ab"}));
        assertEquals("", StrLib.removePrefixes("abc", new String[] {"abc"}));
        assertEquals("", StrLib.removePrefixes("abc", new String[] {"abc", "xyz"}));
        assertEquals("", StrLib.removePrefixes("abc", new String[] {"xyz", "abc"}));
        
        assertEquals("c", StrLib.removePrefixes("abc", new String[] {"ab", "abc"})); // first 'ab'

        ////
        assertEquals("file.txt", StrLib.removePrefixes("myfile.txt", new String[] {"my"}));
        assertEquals(".txt", StrLib.removePrefixes("myfile.txt", new String[] {"myfile"}));
        assertEquals("", StrLib.removePrefixes("myfile.txt", new String[] {"myfile.txt"}));
        
        assertEquals(".txt", StrLib.removePrefixes("myfile.txt", new String[] {"myfile", "yourfile"}));
        
    }

    public void testRemoveSuffix() {

        // removeSuffix(null, value)
        assertNull(StrLib.removeSuffix(null, null));
        assertNull(StrLib.removeSuffix(null, ""));
        assertNull(StrLib.removeSuffix(null, " "));
        assertNull(StrLib.removeSuffix(null, "abc"));

        // removeSuffix(empty, value)
        assertEquals("", StrLib.removeSuffix("", null));
        assertEquals("", StrLib.removeSuffix("", ""));
        assertEquals("", StrLib.removeSuffix("", " "));
        assertEquals("", StrLib.removeSuffix("", "abc"));

        // removeSuffix(blank, value)
        assertEquals(" ", StrLib.removeSuffix(" ", null));
        assertEquals(" ", StrLib.removeSuffix(" ", ""));
        assertEquals("", StrLib.removeSuffix(" ", " "));    // True
        assertEquals(" ", StrLib.removeSuffix(" ", "abc"));

        // removeSuffix(value, value)
        
        // False
        assertEquals("abc", StrLib.removeSuffix("abc", null));
        assertEquals("abc", StrLib.removeSuffix("abc", ""));
        assertEquals("abc", StrLib.removeSuffix("abc", " "));
        assertEquals("abc", StrLib.removeSuffix("abc", "xyz"));
        
        // True
        assertEquals("ab", StrLib.removeSuffix("abc", "c"));
        assertEquals("a", StrLib.removeSuffix("abc", "bc"));
        assertEquals("", StrLib.removeSuffix("abc", "abc"));
        
        ////
        assertEquals("myfile.", StrLib.removeSuffix("myfile.txt", "txt"));
        assertEquals("myfile", StrLib.removeSuffix("myfile.txt", ".txt"));
        assertEquals("", StrLib.removeSuffix("myfile.txt", "myfile.txt"));
                
    }

    public void testRemoveSuffixes() {

        // removeSuffixes(null, value)
        assertNull(StrLib.removeSuffixes(null, null));
        assertNull(StrLib.removeSuffixes(null, new String[] {}));
        assertNull(StrLib.removeSuffixes(null, new String[] {""}));
        assertNull(StrLib.removeSuffixes(null, new String[] {" "}));
        assertNull(StrLib.removeSuffixes(null, new String[] {"abc"}));

        // removeSuffixes(empty, value)
        assertEquals("", StrLib.removeSuffixes("", null));
        assertEquals("", StrLib.removeSuffixes("", new String[] {}));
        assertEquals("", StrLib.removeSuffixes("", new String[] {""}));
        assertEquals("", StrLib.removeSuffixes("", new String[] {" "}));
        assertEquals("", StrLib.removeSuffixes("", new String[] {"abc"}));

        // removeSuffixes(blank, value)
        assertEquals(" ", StrLib.removeSuffixes(" ", null));
        assertEquals(" ", StrLib.removeSuffixes(" ", new String[] {}));
        assertEquals(" ", StrLib.removeSuffixes(" ", new String[] {""}));
        assertEquals("", StrLib.removeSuffixes(" ", new String[] {" "}));    // True
        assertEquals(" ", StrLib.removeSuffixes(" ", new String[] {"abc"}));

        // removeSuffixes(value, value)
        
        // False
        assertEquals("abc", StrLib.removeSuffixes("abc", null));
        assertEquals("abc", StrLib.removeSuffixes("abc", new String[] {}));
        assertEquals("abc", StrLib.removeSuffixes("abc", new String[] {""}));
        assertEquals("abc", StrLib.removeSuffixes("abc", new String[] {" "}));
        assertEquals("abc", StrLib.removeSuffixes("abc", new String[] {"xyz"}));
        assertEquals("abc", StrLib.removeSuffixes("abc", new String[] {"xyz", "def"}));
        assertEquals("abc", StrLib.removeSuffixes("abc", new String[] {"def", "xyz"}));
        
        // True
        assertEquals("ab", StrLib.removeSuffixes("abc", new String[] {"c"}));
        assertEquals("a", StrLib.removeSuffixes("abc", new String[] {"bc"}));
        assertEquals("", StrLib.removeSuffixes("abc", new String[] {"abc"}));
        assertEquals("", StrLib.removeSuffixes("abc", new String[] {"abc", "xyz"}));
        assertEquals("", StrLib.removeSuffixes("abc", new String[] {"xyz", "abc"}));
        
        assertEquals("a", StrLib.removeSuffixes("abc", new String[] {"bc", "abc"})); // first 'bc'
                
        ////
        assertEquals("myfile.", StrLib.removeSuffixes("myfile.txt", new String[] {"txt"}));
        assertEquals("myfile", StrLib.removeSuffixes("myfile.txt", new String[] {".txt"}));
        assertEquals("", StrLib.removeSuffixes("myfile.txt", new String[] {"myfile.txt"}));
        
        assertEquals("myfile", StrLib.removeSuffixes("myfile.txt", new String[] {".txt", ".csv"}));
                
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
