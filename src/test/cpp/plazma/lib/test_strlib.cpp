//#ifndef PLAZMA_LIB_TEST_STRLIB_H
//#define PLAZMA_LIB_TEST_STRLIB_H

#include <string>
#include <iostream>

#include "plazma/lib/str/strlib.h" 
#include "test.h"

// 1.1

TEST(isEmpty) {

  // isEmpty(empty)
  ASSERT_TRUE(strlib::isEmpty(""));

  // isEmpty(blank)
  ASSERT_FALSE(strlib::isEmpty(" "));
  ASSERT_FALSE(strlib::isEmpty("  "));

  // isEmpty(value)
  ASSERT_FALSE(strlib::isEmpty("abc"));
  ASSERT_FALSE(strlib::isEmpty(" abc"));
  ASSERT_FALSE(strlib::isEmpty("abc "));
  ASSERT_FALSE(strlib::isEmpty(" abc "));

}

TEST(isBlank) {

  // isBlank(empty)
  ASSERT_TRUE(strlib::isBlank(""));

  // isBlank(blank)
  ASSERT_TRUE(strlib::isBlank(" "));
  ASSERT_TRUE(strlib::isBlank("  "));

  // isBlank(value)
  ASSERT_FALSE(strlib::isBlank("abc"));
  ASSERT_FALSE(strlib::isBlank(" abc"));
  ASSERT_FALSE(strlib::isBlank("abc "));
  ASSERT_FALSE(strlib::isBlank(" abc "));

}

TEST(size) {

  // size(empty)
  ASSERT_EQ(0, strlib::size(""));

  // size(blank)
  ASSERT_EQ(1, strlib::size(" "));
  ASSERT_EQ(2, strlib::size("  "));

  // size(value)
  ASSERT_EQ(3, strlib::size("abc"));
  ASSERT_EQ(4, strlib::size(" abc"));
  ASSERT_EQ(4, strlib::size("abc "));
  ASSERT_EQ(5, strlib::size(" abc "));

}

TEST(equals) {

  // char*

  // equals(null, null)
  ASSERT_TRUE(strlib::equals(nullptr, nullptr));

  // False
  ASSERT_FALSE(strlib::equals(nullptr, ""));
  ASSERT_FALSE(strlib::equals("", nullptr));
  
  // True
  const char* str1 = "";
  const char* str2 = "";
  ASSERT_TRUE(strlib::equals(str1, str2));

  str1 = " ";
  str2 = " ";
  ASSERT_TRUE(strlib::equals(str1, str2));

  str1 = "  ";
  str2 = "  ";
  ASSERT_TRUE(strlib::equals(str1, str2));

  str1 = "a";
  str2 = "a";
  ASSERT_TRUE(strlib::equals(str1, str2));

  str1 = "ab";
  str2 = "ab";
  ASSERT_TRUE(strlib::equals(str1, str2));

  str1 = "abc";
  str2 = "abc";
  ASSERT_TRUE(strlib::equals(str1, str2));

  // False
  str1 = "a";
  str2 = "b";
  ASSERT_FALSE(strlib::equals(str1, str2));

  str1 = "abc";
  str2 = "xyz";
  ASSERT_FALSE(strlib::equals(str1, str2));

  // string

  // equals(empty, value), equals(value, empty)
  ASSERT_FALSE(strlib::equals("", "abc"));
  ASSERT_FALSE(strlib::equals("abc", ""));

  // equals(empty, empty), equals(blank, blank)
  ASSERT_TRUE(strlib::equals("", ""));
  ASSERT_TRUE(strlib::equals(" ", " "));
  ASSERT_TRUE(strlib::equals("  ", "  "));

  // equals(empty, blank), equals(blank, empty)
  ASSERT_FALSE(strlib::equals("", " "));
  ASSERT_FALSE(strlib::equals("", "  "));
  ASSERT_FALSE(strlib::equals(" ", ""));
  ASSERT_FALSE(strlib::equals("  ", ""));

  // equals(value, value)
  ASSERT_FALSE(strlib::equals(" abc", "abc"));
  ASSERT_FALSE(strlib::equals("abc ", "abc"));
  ASSERT_FALSE(strlib::equals(" abc ", "abc"));

  ASSERT_TRUE(strlib::equals("abc", "abc"));
  ASSERT_TRUE(strlib::equals(" abc", " abc"));
  ASSERT_TRUE(strlib::equals("abc ", "abc "));
  ASSERT_TRUE(strlib::equals(" abc ", " abc "));
  
}

TEST(equalsContent) {

  // equalsContent(empty, null)
  ASSERT_TRUE(strlib::equalsContent("", nullptr));

  // False
  ASSERT_FALSE(strlib::equalsContent(" ", nullptr));
  ASSERT_FALSE(strlib::equalsContent("  ", nullptr));
  ASSERT_FALSE(strlib::equalsContent("  ", nullptr));

  ASSERT_FALSE(strlib::equalsContent("a", nullptr));
  ASSERT_FALSE(strlib::equalsContent("ab", nullptr));
  ASSERT_FALSE(strlib::equalsContent("abc", nullptr));

  // True
  ASSERT_TRUE(strlib::equalsContent("", ""));
  ASSERT_TRUE(strlib::equalsContent(" ", " "));
  ASSERT_TRUE(strlib::equalsContent("  ", "  "));

  ASSERT_TRUE(strlib::equalsContent("a", "a"));
  ASSERT_TRUE(strlib::equalsContent("ab", "ab"));
  ASSERT_TRUE(strlib::equalsContent("abc", "abc"));

  // False
  ASSERT_FALSE(strlib::equalsContent("ab", "a"));
  ASSERT_FALSE(strlib::equalsContent("abc", "a"));
  ASSERT_FALSE(strlib::equalsContent("abc", "xyz"));

}

// 1.2

TEST(normalize) {

  // normalize(empty)
  ASSERT_EQ("", strlib::normalize(""));

  // normalize(blank)
  ASSERT_EQ("", strlib::normalize(" "));
  ASSERT_EQ("", strlib::normalize("  "));

  // normalize(value)
  ASSERT_EQ("abc", strlib::normalize("abc"));
  ASSERT_EQ("abc", strlib::normalize(" abc"));
  ASSERT_EQ("abc", strlib::normalize("abc "));
  ASSERT_EQ("abc", strlib::normalize(" abc "));

  // normalize(" \t\n\r\f\v")
  ASSERT_EQ("", strlib::normalize(" \t\n\r\f\v"));
  ASSERT_EQ("abc", strlib::normalize(" \t\n\r\f\vabc"));
  ASSERT_EQ("abc", strlib::normalize("abc\t\n\r\f\v "));
  ASSERT_EQ("abc", strlib::normalize(" \t\n\r\f\vabc\t\n\r\f\v "));

}

TEST(normalizeBlank) {

  // false, false
  // normalizeBlank(empty, false, false)
  ASSERT_EQ("", strlib::normalizeBlank("", false, false));

  // normalizeBlank(blank, false, false)
  ASSERT_EQ(" ", strlib::normalizeBlank(" ", false, false));
  ASSERT_EQ("  ", strlib::normalizeBlank("  ", false, false));

  // normalizeBlank(value, false, false)
  ASSERT_EQ("abc", strlib::normalizeBlank("abc", false, false));
  ASSERT_EQ(" abc", strlib::normalizeBlank(" abc", false, false));
  ASSERT_EQ("abc ", strlib::normalizeBlank("abc ", false, false));
  ASSERT_EQ(" abc ", strlib::normalizeBlank(" abc ", false, false));

  // false, true
  // normalizeBlank(empty, false, true)
  ASSERT_EQ("", strlib::normalizeBlank("", false, true));

  // normalizeBlank(blank, false, true)
  ASSERT_EQ("", strlib::normalizeBlank(" ", false, true));
  ASSERT_EQ("", strlib::normalizeBlank("  ", false, true));

  // normalizeBlank(value, false, true)
  ASSERT_EQ("abc", strlib::normalizeBlank("abc", false, true));
  ASSERT_EQ(" abc", strlib::normalizeBlank(" abc", false, true));
  ASSERT_EQ("abc ", strlib::normalizeBlank("abc ", false, true));
  ASSERT_EQ(" abc ", strlib::normalizeBlank(" abc ", false, true));

  // true, true
  // normalizeBlank(empty, true, true)
  ASSERT_EQ("", strlib::normalizeBlank("", true, true));

  // normalizeBlank(blank, true, true)
  ASSERT_EQ("", strlib::normalizeBlank(" ", true, true));
  ASSERT_EQ("", strlib::normalizeBlank("  ", true, true));

  // normalizeBlank(value, true, true)
  ASSERT_EQ("abc", strlib::normalizeBlank("abc", true, true));
  ASSERT_EQ("abc", strlib::normalizeBlank(" abc", true, true));
  ASSERT_EQ("abc", strlib::normalizeBlank("abc ", true, true));
  ASSERT_EQ("abc", strlib::normalizeBlank(" abc ", true, true));

  // true, false
  // normalizeBlank(empty, true, false)
  ASSERT_EQ("", strlib::normalizeBlank("", true, false));

  // normalizeBlank(blank, true, false)
  ASSERT_EQ("", strlib::normalizeBlank(" ", true, false));
  ASSERT_EQ("", strlib::normalizeBlank("  ", true, false));

  // normalizeBlank(value, true, false)
  ASSERT_EQ("abc", strlib::normalizeBlank("abc", true, false));
  ASSERT_EQ("abc", strlib::normalizeBlank(" abc", true, false));
  ASSERT_EQ("abc", strlib::normalizeBlank("abc ", true, false));
  ASSERT_EQ("abc", strlib::normalizeBlank(" abc ", true, false));

}

TEST(defautIfEmpty) {

  // defaultIfEmpty(empty, empty)
  ASSERT_EQ("", strlib::defaultIfEmpty("", ""));
        
  ASSERT_EQ(" ", strlib::defaultIfEmpty(" ", ""));        
  ASSERT_EQ(" ", strlib::defaultIfEmpty("", " "));        
  ASSERT_EQ(" ", strlib::defaultIfEmpty("", " "));
  ASSERT_EQ(" ", strlib::defaultIfEmpty(" ", " "));

  ASSERT_EQ(".", strlib::defaultIfEmpty(".", ""));        
  ASSERT_EQ(".", strlib::defaultIfEmpty("", "."));        
  ASSERT_EQ(".", strlib::defaultIfEmpty("", "."));
  ASSERT_EQ(".", strlib::defaultIfEmpty(".", "."));

}

// 1.3

TEST(trim) {

  // trim(empty)
  ASSERT_EQ("", strlib::trim(""));

  // trim(blank)
  ASSERT_EQ("", strlib::trim(" "));
  ASSERT_EQ("", strlib::trim("  "));

  // trim(value)
  ASSERT_EQ("abc", strlib::trim("abc"));
  ASSERT_EQ("abc", strlib::trim(" abc"));
  ASSERT_EQ("abc", strlib::trim("abc "));
  ASSERT_EQ("abc", strlib::trim(" abc "));

  // trim(" \t\n\r\f\v")
  ASSERT_EQ("", strlib::trim(" \t\n\r\f\v"));
  ASSERT_EQ("abc", strlib::trim(" \t\n\r\f\vabc"));
  ASSERT_EQ("abc", strlib::trim("abc \t\n\r\f\v"))
  ASSERT_EQ("abc", strlib::trim(" \t\n\r\f\vabc \t\n\r\f\v"));

}

TEST(trimAll) { // alias: trim(str)

  // trimAll(empty)
  ASSERT_EQ("", strlib::trimAll(""));

  // trimAll(blank)
  ASSERT_EQ("", strlib::trimAll(" "));
  ASSERT_EQ("", strlib::trimAll("  "));

  // trimAll(value)
  ASSERT_EQ("abc", strlib::trimAll("abc"));
  ASSERT_EQ("abc", strlib::trimAll(" abc"));
  ASSERT_EQ("abc", strlib::trimAll("abc "));
  ASSERT_EQ("abc", strlib::trimAll(" abc "));

  // trimAll(" \t\n\r\f\v")
  ASSERT_EQ("", strlib::trimAll(" \t\n\r\f\v"));
  ASSERT_EQ("abc", strlib::trimAll(" \t\n\r\f\vabc"));
  ASSERT_EQ("abc", strlib::trimAll("abc \t\n\r\f\v"))
  ASSERT_EQ("abc", strlib::trimAll(" \t\n\r\f\vabc \t\n\r\f\v"));

}

TEST(trimSpace) {

  // trimSpace(empty)
  ASSERT_EQ("", strlib::trimSpace(""));

  // trimSpace(blank)
  ASSERT_EQ("", strlib::trimSpace(" "));
  ASSERT_EQ("", strlib::trimSpace("  "));

  // trimSpace(value)
  ASSERT_EQ("abc", strlib::trimSpace("abc"));
  ASSERT_EQ("abc", strlib::trimSpace(" abc"));
  ASSERT_EQ("abc", strlib::trimSpace("abc "));
  ASSERT_EQ("abc", strlib::trimSpace(" abc "));

  // trimSpace(" \t\n\r\f\v")
  ASSERT_EQ("\t\n\r\f\v", strlib::trimSpace(" \t\n\r\f\v"));
  ASSERT_EQ("\t\n\r\f\vabc", strlib::trimSpace(" \t\n\r\f\vabc"));
  ASSERT_EQ("abc \t\n\r\f\v", strlib::trimSpace("abc \t\n\r\f\v"))
  ASSERT_EQ("\t\n\r\f\vabc \t\n\r\f\v", strlib::trimSpace(" \t\n\r\f\vabc \t\n\r\f\v"));

}

TEST(ltrim) {

  // ltrim(empty)
  ASSERT_EQ("", strlib::ltrim(""));

  // ltrim(blank)
  ASSERT_EQ("", strlib::ltrim(" "));
  ASSERT_EQ("", strlib::ltrim("  "));

  // ltrim(value)
  ASSERT_EQ("abc", strlib::ltrim("abc"));
  ASSERT_EQ("abc", strlib::ltrim(" abc"));
  ASSERT_EQ("abc ", strlib::ltrim("abc "));
  ASSERT_EQ("abc ", strlib::ltrim(" abc "));

  // ltrim(" \t\n\r\f\v")
  ASSERT_EQ("", strlib::ltrim(" \t\n\r\f\v"));
  ASSERT_EQ("abc", strlib::ltrim(" \t\n\r\f\vabc"));
  ASSERT_EQ("abc \t\n\r\f\v", strlib::ltrim("abc \t\n\r\f\v"))
  ASSERT_EQ("abc \t\n\r\f\v", strlib::ltrim(" \t\n\r\f\vabc \t\n\r\f\v"));
  
}

TEST(rtrim) {

  // rtrim(empty)
  ASSERT_EQ("", strlib::rtrim(""));

  // rtrim(blank)
  ASSERT_EQ("", strlib::rtrim(" "));
  ASSERT_EQ("", strlib::rtrim("  "));

  // rtrim(value)
  ASSERT_EQ("abc", strlib::rtrim("abc"));
  ASSERT_EQ(" abc", strlib::rtrim(" abc"));
  ASSERT_EQ("abc", strlib::rtrim("abc "));
  ASSERT_EQ(" abc", strlib::rtrim(" abc "));

  // rtrim(" \t\n\r\f\v")
  ASSERT_EQ("", strlib::rtrim(" \t\n\r\f\v"));
  ASSERT_EQ(" \t\n\r\f\vabc", strlib::rtrim(" \t\n\r\f\vabc"));
  ASSERT_EQ("abc", strlib::rtrim("abc \t\n\r\f\v"))
  ASSERT_EQ(" \t\n\r\f\vabc", strlib::rtrim(" \t\n\r\f\vabc \t\n\r\f\v"));
  
}

// 1.4

TEST(contains) {

  // char

  // False: contains(empty, empty)
  ASSERT_FALSE(strlib::contains("", '\0'));

  // False: contains(blank, blank)
  ASSERT_TRUE(strlib::contains(" ", ' '));
  ASSERT_TRUE(strlib::contains("  ", ' '));
  ASSERT_TRUE(strlib::contains(".", '.'));

  // True: contains(value, value)
  ASSERT_TRUE(strlib::contains("a", 'a'));
  ASSERT_TRUE(strlib::contains("abc", 'a'));
  ASSERT_TRUE(strlib::contains("abc", 'b'));
  ASSERT_TRUE(strlib::contains("abc", 'c'));

  // False: contains(value, value)
  ASSERT_FALSE(strlib::contains("abc", '.'));
  ASSERT_FALSE(strlib::contains("abc", 'x'));
  ASSERT_FALSE(strlib::contains("abc", 'y'));
  ASSERT_FALSE(strlib::contains("abc", 'z'));

  // string

  // False: contains(empty, empty)
  ASSERT_FALSE(strlib::contains("", ""));

  // False: contains(blank, blank)
  ASSERT_TRUE(strlib::contains(" ", " "));
  ASSERT_TRUE(strlib::contains("  ", " "));
  ASSERT_TRUE(strlib::contains(".", "."));

  // True: contains(value, value)
  ASSERT_TRUE(strlib::contains("a", "a"));
  ASSERT_TRUE(strlib::contains("abc", "a"));
  ASSERT_TRUE(strlib::contains("abc", "b"));
  ASSERT_TRUE(strlib::contains("abc", "c"));

  ASSERT_TRUE(strlib::contains("abc", "ab"));
  ASSERT_TRUE(strlib::contains("abc", "bc"));
  ASSERT_TRUE(strlib::contains("abc", "abc"));

  // False: contains(value, value)
  ASSERT_FALSE(strlib::contains("abc", "."));
  ASSERT_FALSE(strlib::contains("abc", "x"));
  ASSERT_FALSE(strlib::contains("abc", "y"));
  ASSERT_FALSE(strlib::contains("abc", "z"));

  ASSERT_FALSE(strlib::contains("abc", "def"));
  ASSERT_FALSE(strlib::contains("abc", "xyz"));

}

TEST(findFirstOf) {

  // NotFound: empty
  ASSERT_EQ(-1, strlib::findFirstOf("", '\0'));
  ASSERT_EQ(-1, strlib::findFirstOf("", ' '));
  ASSERT_EQ(-1, strlib::findFirstOf("", '.'));

  // NotFound: empty, pos
  ASSERT_EQ(-1, strlib::findFirstOf("", '\0', 0));
  ASSERT_EQ(-1, strlib::findFirstOf("", ' ', 0));
  ASSERT_EQ(-1, strlib::findFirstOf("", '.', 0));

  ASSERT_EQ(-1, strlib::findFirstOf("", '\0', -1));
  ASSERT_EQ(-1, strlib::findFirstOf("", ' ', -1));
  ASSERT_EQ(-1, strlib::findFirstOf("", '.', -1));

  ASSERT_EQ(-1, strlib::findFirstOf("", '\0', 1));
  ASSERT_EQ(-1, strlib::findFirstOf("", ' ', 1));
  ASSERT_EQ(-1, strlib::findFirstOf("", '.', 1));

  // Found: blank
  ASSERT_EQ(0, strlib::findFirstOf(" ", ' '));
  ASSERT_EQ(0, strlib::findFirstOf("  ", ' '));

  // Found: blank/value
  ASSERT_EQ(0, strlib::findFirstOf(" .", ' '));
  ASSERT_EQ(0, strlib::findFirstOf("  .", ' '));

  ASSERT_EQ(1, strlib::findFirstOf(". ", ' '));
  ASSERT_EQ(1, strlib::findFirstOf(".  ", ' '));

  // Found: value
  ASSERT_EQ(0, strlib::findFirstOf("*", '*'));

  ASSERT_EQ(1, strlib::findFirstOf(".*", '*'));
  ASSERT_EQ(2, strlib::findFirstOf("..*", '*'));
  ASSERT_EQ(3, strlib::findFirstOf("...*", '*'));

  ASSERT_EQ(0, strlib::findFirstOf("*.", '*'));
  ASSERT_EQ(0, strlib::findFirstOf("*..", '*'));
  ASSERT_EQ(0, strlib::findFirstOf("*...", '*'));

  // NotFound: value, pos, min range
  ASSERT_EQ(-1, strlib::findFirstOf("**..**..", '*', -1));
  ASSERT_EQ(-1, strlib::findFirstOf("**..**..", '*', -2));

  // Found: value, pos
  ASSERT_EQ(0, strlib::findFirstOf("**..**..", '*', 0));
  ASSERT_EQ(1, strlib::findFirstOf("**..**..", '*', 1));
  ASSERT_EQ(4, strlib::findFirstOf("**..**..", '*', 2));
  ASSERT_EQ(4, strlib::findFirstOf("**..**..", '*', 3));
  ASSERT_EQ(4, strlib::findFirstOf("**..**..", '*', 4));
  ASSERT_EQ(5, strlib::findFirstOf("**..**..", '*', 5));

  // NotFound: value, pos
  ASSERT_EQ(-1, strlib::findFirstOf("**..**..", '*', 6));
  ASSERT_EQ(-1, strlib::findFirstOf("**..**..", '*', 7));

  // NotFound: value, pos, max range
  ASSERT_EQ(-1, strlib::findFirstOf("**..**..", '*', 8));
  ASSERT_EQ(-1, strlib::findFirstOf("**..**..", '*', 9));

}

TEST(findLastOf) {

  // NotFound: empty
  ASSERT_EQ(-1, strlib::findLastOf("", '\0'));
  ASSERT_EQ(-1, strlib::findLastOf("", ' '));
  ASSERT_EQ(-1, strlib::findLastOf("", '.'));

  // NotFound: empty, pos
  ASSERT_EQ(-1, strlib::findLastOf("", '\0', 0));
  ASSERT_EQ(-1, strlib::findLastOf("", ' ', 0));
  ASSERT_EQ(-1, strlib::findLastOf("", '.', 0));

  ASSERT_EQ(-1, strlib::findLastOf("", '\0', -1));
  ASSERT_EQ(-1, strlib::findLastOf("", ' ', -1));
  ASSERT_EQ(-1, strlib::findLastOf("", '.', -1));

  ASSERT_EQ(-1, strlib::findLastOf("", '\0', 1));
  ASSERT_EQ(-1, strlib::findLastOf("", ' ', 1));
  ASSERT_EQ(-1, strlib::findLastOf("", '.', 1));

  // Found: blank
  ASSERT_EQ(0, strlib::findLastOf(" ", ' '));
  ASSERT_EQ(1, strlib::findLastOf("  ", ' '));

  // Found: blank/value
  ASSERT_EQ(0, strlib::findLastOf(" .", ' '));
  ASSERT_EQ(1, strlib::findLastOf("  .", ' '));

  ASSERT_EQ(1, strlib::findLastOf(". ", ' '));
  ASSERT_EQ(2, strlib::findLastOf(".  ", ' '));

  // Found: value
  ASSERT_EQ(0, strlib::findLastOf("*", '*'));

  ASSERT_EQ(0, strlib::findLastOf("*.", '*'));
  ASSERT_EQ(0, strlib::findLastOf("*..", '*'));
  ASSERT_EQ(0, strlib::findLastOf("*...", '*'));

  ASSERT_EQ(1, strlib::findLastOf(".*", '*'));
  ASSERT_EQ(2, strlib::findLastOf("..*", '*'));
  ASSERT_EQ(3, strlib::findLastOf("...*", '*'));

  // NotFound: value, pos, min range
  ASSERT_EQ(-1, strlib::findLastOf("..**..**", '*', -1));
  ASSERT_EQ(-1, strlib::findLastOf("..**..**", '*', -2));

  // NotFound: value, pos
  ASSERT_EQ(-1, strlib::findLastOf("..**..**", '*', 0));
  ASSERT_EQ(-1, strlib::findLastOf("..**..**", '*', 1));

  // Found: value, pos
  ASSERT_EQ(2, strlib::findLastOf("..**..**", '*', 2));
  ASSERT_EQ(3, strlib::findLastOf("..**..**", '*', 3));
  ASSERT_EQ(3, strlib::findLastOf("..**..**", '*', 4));
  ASSERT_EQ(3, strlib::findLastOf("..**..**", '*', 5));
  ASSERT_EQ(6, strlib::findLastOf("..**..**", '*', 6));
  ASSERT_EQ(7, strlib::findLastOf("..**..**", '*', 7));

  // NotFound: value, pos, max range
  ASSERT_EQ(-1, strlib::findLastOf("..**..**", '*', 8));
  ASSERT_EQ(-1, strlib::findLastOf("..**..**", '*', 9));

}

TEST(findFirstNotOf) {

  // NotFound: empty
  ASSERT_EQ(-1, strlib::findFirstNotOf("", '\0'));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", ' '));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", '.'));

  // NotFound: empty, pos
  ASSERT_EQ(-1, strlib::findFirstNotOf("", '\0', 0));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", ' ', 0));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", '.', 0));

  ASSERT_EQ(-1, strlib::findFirstNotOf("", '\0', -1));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", ' ', -1));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", '.', -1));

  ASSERT_EQ(-1, strlib::findFirstNotOf("", '\0', 1));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", ' ', 1));
  ASSERT_EQ(-1, strlib::findFirstNotOf("", '.', 1));

  // NotFound: blank/value
  ASSERT_EQ(-1, strlib::findFirstNotOf(" ", ' '));
  ASSERT_EQ(-1, strlib::findFirstNotOf("  ", ' '));
        
  // Found: value
  ASSERT_EQ(0, strlib::findFirstNotOf(".", ' '));
  ASSERT_EQ(1, strlib::findFirstNotOf(" .", ' '));
  ASSERT_EQ(2, strlib::findFirstNotOf("  .", ' '));

  ASSERT_EQ(0, strlib::findFirstNotOf(".", '*'));
  ASSERT_EQ(1, strlib::findFirstNotOf("*.", '*'));
  ASSERT_EQ(2, strlib::findFirstNotOf("**.", '*'));
        
  // Found: value, pos
  ASSERT_EQ(2, strlib::findFirstNotOf("**..**..", '*', 0));
  ASSERT_EQ(2, strlib::findFirstNotOf("**..**..", '*', 1));
  ASSERT_EQ(2, strlib::findFirstNotOf("**..**..", '*', 2));
  ASSERT_EQ(3, strlib::findFirstNotOf("**..**..", '*', 3));
  ASSERT_EQ(6, strlib::findFirstNotOf("**..**..", '*', 4));
  ASSERT_EQ(6, strlib::findFirstNotOf("**..**..", '*', 5));
  ASSERT_EQ(6, strlib::findFirstNotOf("**..**..", '*', 6));
  ASSERT_EQ(7, strlib::findFirstNotOf("**..**..", '*', 7));

  // NotFound: value, pos
  ASSERT_EQ(-1, strlib::findFirstNotOf("**..**..", '*', 8));

}

TEST(findLastNotOf) {

  // NotFound: empty
  ASSERT_EQ(-1, strlib::findLastNotOf("", '\0'));
  ASSERT_EQ(-1, strlib::findLastNotOf("", ' '));
  ASSERT_EQ(-1, strlib::findLastNotOf("", '.'));

  // NotFound: empty, pos
  ASSERT_EQ(-1, strlib::findLastNotOf("", '\0', 0));
  ASSERT_EQ(-1, strlib::findLastNotOf("", ' ', 0));
  ASSERT_EQ(-1, strlib::findLastNotOf("", '.', 0));

  ASSERT_EQ(-1, strlib::findLastNotOf("", '\0', -1));
  ASSERT_EQ(-1, strlib::findLastNotOf("", ' ', -1));
  ASSERT_EQ(-1, strlib::findLastNotOf("", '.', -1));

  ASSERT_EQ(-1, strlib::findLastNotOf("", '\0', 1));
  ASSERT_EQ(-1, strlib::findLastNotOf("", ' ', 1));
  ASSERT_EQ(-1, strlib::findLastNotOf("", '.', 1));

  // NotFound: blank/value
  ASSERT_EQ(-1, strlib::findLastNotOf(" ", ' '));
  ASSERT_EQ(-1, strlib::findLastNotOf("  ", ' '));

  // Found: value
  ASSERT_EQ(0, strlib::findLastNotOf(".", ' '));
  ASSERT_EQ(1, strlib::findLastNotOf(".. ", ' '));
  ASSERT_EQ(1, strlib::findLastNotOf("..  ", ' '));

  ASSERT_EQ(0, strlib::findLastNotOf(".", '*'));
  ASSERT_EQ(1, strlib::findLastNotOf("..*", '*'));
  ASSERT_EQ(1, strlib::findLastNotOf("..**", '*'));

  // NotFound: value, pos, min range
  ASSERT_EQ(-1, strlib::findLastNotOf("..**..**", '*', -1));
  ASSERT_EQ(-1, strlib::findLastNotOf("..**..**", '*', -2));

  // Found: value, pos  
  ASSERT_EQ(0, strlib::findLastNotOf("..**..**", '*', 0));
  ASSERT_EQ(1, strlib::findLastNotOf("..**..**", '*', 1));
  ASSERT_EQ(1, strlib::findLastNotOf("..**..**", '*', 2));
  ASSERT_EQ(1, strlib::findLastNotOf("..**..**", '*', 3));
  ASSERT_EQ(4, strlib::findLastNotOf("..**..**", '*', 4));
  ASSERT_EQ(5, strlib::findLastNotOf("..**..**", '*', 5));
  ASSERT_EQ(5, strlib::findLastNotOf("..**..**", '*', 6));
  ASSERT_EQ(5, strlib::findLastNotOf("..**..**", '*', 7));

  // NotFound: value, pos, max range
  ASSERT_EQ(-1, strlib::findLastNotOf("..**..**", '*', 8));
  ASSERT_EQ(-1, strlib::findLastNotOf("..**..**", '*', 9));

}

// 2.1

TEST(replicate) {

  // replicate(empty, -n), replicate(empty, 0), replicate(empty, n)
  ASSERT_EQ("", strlib::replicate("", -2));
  ASSERT_EQ("", strlib::replicate("", -1));
  ASSERT_EQ("", strlib::replicate("", 0));
  ASSERT_EQ("", strlib::replicate("", 1));
  ASSERT_EQ("", strlib::replicate("", 2));

  // char

  // replicate(char, -n), replicate(char, 0), replicate(char, n)
  ASSERT_EQ("", strlib::replicate('.', -2));
  ASSERT_EQ("", strlib::replicate('.', -1));
  ASSERT_EQ("", strlib::replicate('.', 0));
  ASSERT_EQ(".", strlib::replicate('.', 1));
  ASSERT_EQ("..", strlib::replicate('.', 2));

  // replicate(char, n)
  ASSERT_EQ("*", strlib::replicate('*', 1));
  ASSERT_EQ("**", strlib::replicate('*', 2));
  ASSERT_EQ("***", strlib::replicate('*', 3));

  ASSERT_EQ("a", strlib::replicate('a', 1));
  ASSERT_EQ("aa", strlib::replicate('a', 2));
  ASSERT_EQ("aaa", strlib::replicate('a', 3));

  // string

  // replicate(str, -n), replicate(str, 0), replicate(str, n)
  ASSERT_EQ("", strlib::replicate("abc", -2));
  ASSERT_EQ("", strlib::replicate("abc", -1));
  ASSERT_EQ("", strlib::replicate("abc", 0));
  ASSERT_EQ("abc", strlib::replicate("abc", 1));
  ASSERT_EQ("abcabc", strlib::replicate("abc", 2));
  ASSERT_EQ("abcabcabc", strlib::replicate("abc", 3));

}

// 2.2

TEST(lpad) {

  // empty

  // lpad(empty, -n), lpad(empty, 0), lpad(empty, n)
  ASSERT_EQ("", strlib::lpad("", -2));
  ASSERT_EQ("", strlib::lpad("", -1));
  ASSERT_EQ("", strlib::lpad("", 0));
  ASSERT_EQ(" ", strlib::lpad("", 1));
  ASSERT_EQ("  ", strlib::lpad("", 2));
  ASSERT_EQ("   ", strlib::lpad("", 3));

  ASSERT_EQ("", strlib::lpad("", -2, '*'));
  ASSERT_EQ("", strlib::lpad("", -1, '*'));
  ASSERT_EQ("", strlib::lpad("", 0, '*'));
  ASSERT_EQ("*", strlib::lpad("", 1, '*'));
  ASSERT_EQ("**", strlib::lpad("", 2, '*'));
  ASSERT_EQ("***", strlib::lpad("", 3, '*'));

  // char

  // lpad(char, -n), lpad(char, 0), lpad(char, n)
  ASSERT_EQ("a", strlib::lpad("a", -2));
  ASSERT_EQ("a", strlib::lpad("a", -1));
  ASSERT_EQ("a", strlib::lpad("a", 0));
  ASSERT_EQ("a", strlib::lpad("a", 1));
  ASSERT_EQ(" a", strlib::lpad("a", 2));
  ASSERT_EQ("  a", strlib::lpad("a", 3));

  ASSERT_EQ("a", strlib::lpad("a", -2, '*'));
  ASSERT_EQ("a", strlib::lpad("a", -1, '*'));
  ASSERT_EQ("a", strlib::lpad("a", 0, '*'));
  ASSERT_EQ("a", strlib::lpad("a", 1, '*'));
  ASSERT_EQ("*a", strlib::lpad("a", 2, '*'));
  ASSERT_EQ("**a", strlib::lpad("a", 3, '*'));

  // string

  // lpad(str, -n), lpad(str, 0), lpad(str, n)
  ASSERT_EQ("abc", strlib::lpad("abc", -2));
  ASSERT_EQ("abc", strlib::lpad("abc", -1));
  ASSERT_EQ("abc", strlib::lpad("abc", 0));
  ASSERT_EQ("abc", strlib::lpad("abc", 1));
  ASSERT_EQ("abc", strlib::lpad("abc", 2));
  ASSERT_EQ("abc", strlib::lpad("abc", 3));
  ASSERT_EQ(" abc", strlib::lpad("abc", 4));
  ASSERT_EQ("  abc", strlib::lpad("abc", 5));

  ASSERT_EQ("abc", strlib::lpad("abc", -2, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", -1, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 0, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 1, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 2, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 3, '*'));
  ASSERT_EQ("*abc", strlib::lpad("abc", 4, '*'));
  ASSERT_EQ("**abc", strlib::lpad("abc", 5, '*'));

  ASSERT_EQ("abc", strlib::lpad("abc", -2, "yz"));
  ASSERT_EQ("abc", strlib::lpad("abc", -1, "yz"));
  ASSERT_EQ("abc", strlib::lpad("abc", 0, "yz"));
  ASSERT_EQ("abc", strlib::lpad("abc", 1, "yz"));
  ASSERT_EQ("abc", strlib::lpad("abc", 2, "yz"));
  ASSERT_EQ("abc", strlib::lpad("abc", 3, "yz"));
  ASSERT_EQ("yabc", strlib::lpad("abc", 4, "yz"));
  ASSERT_EQ("yzabc", strlib::lpad("abc", 5, "yz"));
  ASSERT_EQ("yzyabc", strlib::lpad("abc", 6, "yz"));
  ASSERT_EQ("yzyzabc", strlib::lpad("abc", 7, "yz"));
  ASSERT_EQ("yzyzyabc", strlib::lpad("abc", 8, "yz"));
        
  // 12345678
  // yzyzyz**
  // *****abc
  //---------
  // yzyzyabc

}

TEST(rpad) {

  // empty

  // rpad(empty, -n), rpad(empty, 0), rpad(empty, n)
  ASSERT_EQ("", strlib::rpad("", -2));
  ASSERT_EQ("", strlib::rpad("", -1));
  ASSERT_EQ("", strlib::rpad("", 0));
  ASSERT_EQ(" ", strlib::rpad("", 1));
  ASSERT_EQ("  ", strlib::rpad("", 2));
  ASSERT_EQ("   ", strlib::rpad("", 3));

  ASSERT_EQ("", strlib::rpad("", -2, '*'));
  ASSERT_EQ("", strlib::rpad("", -1, '*'));
  ASSERT_EQ("", strlib::rpad("", 0, '*'));
  ASSERT_EQ("*", strlib::rpad("", 1, '*'));
  ASSERT_EQ("**", strlib::rpad("", 2, '*'));
  ASSERT_EQ("***", strlib::rpad("", 3, '*'));

  // char

  // rpad(char, -n), rpad(char, 0), rpad(char, n)
  ASSERT_EQ("a", strlib::rpad("a", -2));
  ASSERT_EQ("a", strlib::rpad("a", -1));
  ASSERT_EQ("a", strlib::rpad("a", 0));
  ASSERT_EQ("a", strlib::rpad("a", 1));
  ASSERT_EQ("a ", strlib::rpad("a", 2));
  ASSERT_EQ("a  ", strlib::rpad("a", 3));

  ASSERT_EQ("a", strlib::rpad("a", -2, '*'));
  ASSERT_EQ("a", strlib::rpad("a", -1, '*'));
  ASSERT_EQ("a", strlib::rpad("a", 0, '*'));
  ASSERT_EQ("a", strlib::rpad("a", 1, '*'));
  ASSERT_EQ("a*", strlib::rpad("a", 2, '*'));
  ASSERT_EQ("a**", strlib::rpad("a", 3, '*'));

  // string

  // rpad(str, -n), rpad(str, 0), rpad(str, n)
  ASSERT_EQ("abc", strlib::rpad("abc", -2));
  ASSERT_EQ("abc", strlib::rpad("abc", -1));
  ASSERT_EQ("abc", strlib::rpad("abc", 0));
  ASSERT_EQ("abc", strlib::rpad("abc", 1));
  ASSERT_EQ("abc", strlib::rpad("abc", 2));
  ASSERT_EQ("abc", strlib::rpad("abc", 3));
  ASSERT_EQ("abc ", strlib::rpad("abc", 4));
  ASSERT_EQ("abc  ", strlib::rpad("abc", 5));

  ASSERT_EQ("abc", strlib::rpad("abc", -2, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", -1, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 0, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 1, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 2, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 3, '*'));
  ASSERT_EQ("abc*", strlib::rpad("abc", 4, '*'));
  ASSERT_EQ("abc**", strlib::rpad("abc", 5, '*'));

  ASSERT_EQ("abc", strlib::rpad("abc", -2, "yz"));
  ASSERT_EQ("abc", strlib::rpad("abc", -1, "yz"));
  ASSERT_EQ("abc", strlib::rpad("abc", 0, "yz"));
  ASSERT_EQ("abc", strlib::rpad("abc", 1, "yz"));
  ASSERT_EQ("abc", strlib::rpad("abc", 2, "yz"));
  ASSERT_EQ("abc", strlib::rpad("abc", 3, "yz"));
  ASSERT_EQ("abcy", strlib::rpad("abc", 4, "yz"));
  ASSERT_EQ("abcyz", strlib::rpad("abc", 5, "yz"));
  ASSERT_EQ("abcyzy", strlib::rpad("abc", 6, "yz"));
  ASSERT_EQ("abcyzyz", strlib::rpad("abc", 7, "yz"));
  ASSERT_EQ("abcyzyzy", strlib::rpad("abc", 8, "yz"));
        
  // 12345678
  // abc*****
  // ***yzyzyz
  // --------
  // abcyzyzy

}

// 2.3

TEST(fill) {

  // fill(empty, -n), fill(empty, 0), fill(empty, n)
  ASSERT_EQ("", strlib::fill("", -2));
  ASSERT_EQ("", strlib::fill("", -1));
  ASSERT_EQ("", strlib::fill("", 0));
  ASSERT_EQ(" ", strlib::fill("", 1));
  ASSERT_EQ("  ", strlib::fill("", 2));

  // fill(empty, -n, ''), fill(empty, 0, ''), fill(empty, n, '')
  ASSERT_EQ("", strlib::fill("", -2, ""));
  ASSERT_EQ("", strlib::fill("", -1, ""));
  ASSERT_EQ("", strlib::fill("", 0, ""));
  ASSERT_EQ("", strlib::fill("", 1, ""));
  ASSERT_EQ("", strlib::fill("", 2, ""));

  // fill(empty, -n, ' '), fill(empty, 0, ' '), fill(empty, n, ' ')
  ASSERT_EQ("", strlib::fill("", -2, ' '));
  ASSERT_EQ("", strlib::fill("", -1, ' '));
  ASSERT_EQ("", strlib::fill("", 0, ' '));
  ASSERT_EQ(" ", strlib::fill("", 1, ' '));
  ASSERT_EQ("  ", strlib::fill("", 2, ' '));

  // fill(empty, -n, '*'), fill(empty, 0, '*'), fill(empty, n, '*')
  ASSERT_EQ("", strlib::fill("", -2, '*'));
  ASSERT_EQ("", strlib::fill("", -1, '*'));
  ASSERT_EQ("", strlib::fill("", 0, '*'));
  ASSERT_EQ("*", strlib::fill("", 1, '*'));
  ASSERT_EQ("**", strlib::fill("", 2, '*'));

  // char

  // fill(char, -n), fill(char, 0), fill(char, n)
  ASSERT_EQ("", strlib::fill("a", -2));
  ASSERT_EQ("", strlib::fill("a", -1));
  ASSERT_EQ("", strlib::fill("a", 0));
  ASSERT_EQ("a", strlib::fill("a", 1));
  ASSERT_EQ("a ", strlib::fill("a", 2));
  ASSERT_EQ("a  ", strlib::fill("a", 3));
  ASSERT_EQ("a   ", strlib::fill("a", 4));
  ASSERT_EQ("a    ", strlib::fill("a", 5));

  ASSERT_EQ("", strlib::fill("a", -2, '*'));
  ASSERT_EQ("", strlib::fill("a", -1, '*'));
  ASSERT_EQ("", strlib::fill("a", 0, '*'));
  ASSERT_EQ("a", strlib::fill("a", 1, '*'));
  ASSERT_EQ("a*", strlib::fill("a", 2, '*'));
  ASSERT_EQ("a**", strlib::fill("a", 3, '*'));
  ASSERT_EQ("a***", strlib::fill("a", 4, '*'));
  ASSERT_EQ("a****", strlib::fill("a", 5, '*'));

  // string

  // fill(str, -n), fill(str, 0), fill(str, n)
  ASSERT_EQ("", strlib::fill("abcxyz", -2));
  ASSERT_EQ("", strlib::fill("abcxyz", -1));
  ASSERT_EQ("", strlib::fill("abcxyz", 0));
  ASSERT_EQ("a", strlib::fill("abcxyz", 1));
  ASSERT_EQ("ab", strlib::fill("abcxyz", 2));
  ASSERT_EQ("abc", strlib::fill("abcxyz", 3));
  ASSERT_EQ("a...", strlib::fill("abcxyz", 4));   // ellipsis
  ASSERT_EQ("ab...", strlib::fill("abcxyz", 5));  // ellipsis
  ASSERT_EQ("abcxyz", strlib::fill("abcxyz", 6));
  ASSERT_EQ("abcxyz ", strlib::fill("abcxyz", 7));
  ASSERT_EQ("abcxyz  ", strlib::fill("abcxyz", 8));

  // fill(str, -n, '*'), fill(str, 0, '*'), fill(str, n, '*')
  ASSERT_EQ("", strlib::fill("abcxyz", -2, '*'));
  ASSERT_EQ("", strlib::fill("abcxyz", -1, '*'));
  ASSERT_EQ("", strlib::fill("abcxyz", 0, '*'));
  ASSERT_EQ("a", strlib::fill("abcxyz", 1, '*'));
  ASSERT_EQ("ab", strlib::fill("abcxyz", 2, '*'));
  ASSERT_EQ("abc", strlib::fill("abcxyz", 3, '*'));
  ASSERT_EQ("a...", strlib::fill("abcxyz", 4, '*'));   // ellipsis
  ASSERT_EQ("ab...", strlib::fill("abcxyz", 5, '*'));  // ellipsis
  ASSERT_EQ("abcxyz", strlib::fill("abcxyz", 6, '*'));
  ASSERT_EQ("abcxyz*", strlib::fill("abcxyz", 7, '*'));
  ASSERT_EQ("abcxyz**", strlib::fill("abcxyz", 8, '*'));

}

TEST(ellipsis) {

  // ellipsis(empty, -n), ellipsis(empty, 0), ellipsis(empty, n)
  ASSERT_EQ("", strlib::ellipsis("", -2));
  ASSERT_EQ("", strlib::ellipsis("", -1));
  ASSERT_EQ("", strlib::ellipsis("", 0));
  ASSERT_EQ("", strlib::ellipsis("", 1));
  ASSERT_EQ("", strlib::ellipsis("", 2));

  // char

  // ellipsis(char, -n), ellipsis(char, 0), ellipsis(char, n)
  ASSERT_EQ("a", strlib::ellipsis("a", -2));
  ASSERT_EQ("a", strlib::ellipsis("a", -1));
  ASSERT_EQ("a", strlib::ellipsis("a", 0));
  ASSERT_EQ("a", strlib::ellipsis("a", 1));
  ASSERT_EQ("a", strlib::ellipsis("a", 2));

  // string

  // ellipsis(str, -n), ellipsis(str, 0), ellipsis(str, n)
  ASSERT_EQ("abcxyz", strlib::ellipsis("abcxyz", -2));
  ASSERT_EQ("abcxyz", strlib::ellipsis("abcxyz", -1));
  ASSERT_EQ("abcxyz", strlib::ellipsis("abcxyz", 0));
  ASSERT_EQ("a", strlib::ellipsis("abcxyz", 1));
  ASSERT_EQ("ab", strlib::ellipsis("abcxyz", 2));
  ASSERT_EQ("abc", strlib::ellipsis("abcxyz", 3));
  ASSERT_EQ("a...", strlib::ellipsis("abcxyz", 4));   // ellipsis
  ASSERT_EQ("ab...", strlib::ellipsis("abcxyz", 5));  // ellipsis
  ASSERT_EQ("abcxyz", strlib::ellipsis("abcxyz", 6));
  ASSERT_EQ("abcxyz", strlib::ellipsis("abcxyz", 7));
  ASSERT_EQ("abcxyz", strlib::ellipsis("abcxyz", 8));

}

TEST(trunc) {

  // trunc(empty, -n), trunc(empty, 0), trunc(empty, n)
  ASSERT_EQ("", strlib::trunc("", -2));
  ASSERT_EQ("", strlib::trunc("", -1));
  ASSERT_EQ("", strlib::trunc("", 0));
  ASSERT_EQ("", strlib::trunc("", 1));
  ASSERT_EQ("", strlib::trunc("", 2));

  // ellipsis=false
  ASSERT_EQ("", strlib::trunc("", -2, false));
  ASSERT_EQ("", strlib::trunc("", -1, false));
  ASSERT_EQ("", strlib::trunc("", 0, false));
  ASSERT_EQ("", strlib::trunc("", 1, false));
  ASSERT_EQ("", strlib::trunc("", 2, false));

  // ellipsis=true
  ASSERT_EQ("", strlib::trunc("", -2, true));
  ASSERT_EQ("", strlib::trunc("", -1, true));
  ASSERT_EQ("", strlib::trunc("", 0, true));
  ASSERT_EQ("", strlib::trunc("", 1, true));
  ASSERT_EQ("", strlib::trunc("", 2, true));

  // char

  // trunc(char, -n), trunc(char, 0), trunc(char, n)
  ASSERT_EQ("a", strlib::trunc("a", -2));
  ASSERT_EQ("a", strlib::trunc("a", -1));
  ASSERT_EQ("a", strlib::trunc("a", 0));
  ASSERT_EQ("a", strlib::trunc("a", 1));
  ASSERT_EQ("a", strlib::trunc("a", 2));

  // ellipsis=false
  ASSERT_EQ("a", strlib::trunc("a", -2, false));
  ASSERT_EQ("a", strlib::trunc("a", -1, false));
  ASSERT_EQ("a", strlib::trunc("a", 0, false));
  ASSERT_EQ("a", strlib::trunc("a", 1, false));
  ASSERT_EQ("a", strlib::trunc("a", 2, false));

  // ellipsis=true
  ASSERT_EQ("a", strlib::trunc("a", -2, true));
  ASSERT_EQ("a", strlib::trunc("a", -1, true));
  ASSERT_EQ("a", strlib::trunc("a", 0, true));
  ASSERT_EQ("a", strlib::trunc("a", 1, true));
  ASSERT_EQ("a", strlib::trunc("a", 2, true));

  // string

  // trunc(str, -n), trunc(str, 0), trunc(str, n)
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", -2));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", -1));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 0));
  ASSERT_EQ("a", strlib::trunc("abcxyz", 1));
  ASSERT_EQ("ab", strlib::trunc("abcxyz", 2));
  ASSERT_EQ("abc", strlib::trunc("abcxyz", 3));  
  ASSERT_EQ("abcx", strlib::trunc("abcxyz", 4));   // non ellipsis
  ASSERT_EQ("abcxy", strlib::trunc("abcxyz", 5));  // non ellipsis
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 6));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 7));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 8));

  // ellipsis=false
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", -2, false));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", -1, false));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 0, false));
  ASSERT_EQ("a", strlib::trunc("abcxyz", 1, false));
  ASSERT_EQ("ab", strlib::trunc("abcxyz", 2, false));
  ASSERT_EQ("abc", strlib::trunc("abcxyz", 3, false));
  ASSERT_EQ("abcx", strlib::trunc("abcxyz", 4, false));   // non ellipsis
  ASSERT_EQ("abcxy", strlib::trunc("abcxyz", 5, false));  // non ellipsis
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 6, false));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 7, false));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 8, false));

  // ellipsis=true
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", -2, true));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", -1, true));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 0, true));
  ASSERT_EQ("a", strlib::trunc("abcxyz", 1, true));
  ASSERT_EQ("ab", strlib::trunc("abcxyz", 2, true));
  ASSERT_EQ("abc", strlib::trunc("abcxyz", 3, true));  
  ASSERT_EQ("a...", strlib::trunc("abcxyz", 4, true));   // ellipsis
  ASSERT_EQ("ab...", strlib::trunc("abcxyz", 5, true));  // ellipsis
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 6, true));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 7, true));
  ASSERT_EQ("abcxyz", strlib::trunc("abcxyz", 8, true));

}

TEST(left) {

  // left(empty, -n), left(empty, 0), left(empty, n)
  ASSERT_EQ("", strlib::left("", -2));
  ASSERT_EQ("", strlib::left("", -1));
  ASSERT_EQ("", strlib::left("", 0));
  ASSERT_EQ("", strlib::left("", 1));
  ASSERT_EQ("", strlib::left("", 2));

  // char

  // left(char, -n), left(char, 0), left(char, n)
  ASSERT_EQ("", strlib::left("a", -2));
  ASSERT_EQ("", strlib::left("a", -1));
  ASSERT_EQ("", strlib::left("a", 0));
  ASSERT_EQ("a", strlib::left("a", 1));
  ASSERT_EQ("a", strlib::left("a", 2));

  // string

  // left(str, -n), left(str, 0), left(str, n)
  ASSERT_EQ("", strlib::left("abcxyz", -2));
  ASSERT_EQ("", strlib::left("abcxyz", -1));
  ASSERT_EQ("", strlib::left("abcxyz", 0));
  ASSERT_EQ("a", strlib::left("abcxyz", 1));
  ASSERT_EQ("ab", strlib::left("abcxyz", 2));
  ASSERT_EQ("abc", strlib::left("abcxyz", 3));  
  ASSERT_EQ("abcx", strlib::left("abcxyz", 4));
  ASSERT_EQ("abcxy", strlib::left("abcxyz", 5));
  ASSERT_EQ("abcxyz", strlib::left("abcxyz", 6));
  ASSERT_EQ("abcxyz", strlib::left("abcxyz", 7));
  ASSERT_EQ("abcxyz", strlib::left("abcxyz", 8));

}

TEST(right) {

  // right(empty, -n), right(empty, 0), right(empty, n)
  ASSERT_EQ("", strlib::right("", -2));
  ASSERT_EQ("", strlib::right("", -1));
  ASSERT_EQ("", strlib::right("", 0));
  ASSERT_EQ("", strlib::right("", 1));
  ASSERT_EQ("", strlib::right("", 2));

  // char

  // right(char, -n), right(char, 0), right(char, n)
  ASSERT_EQ("", strlib::right("a", -2));
  ASSERT_EQ("", strlib::right("a", -1));
  ASSERT_EQ("", strlib::right("a", 0));
  ASSERT_EQ("a", strlib::right("a", 1));
  ASSERT_EQ("a", strlib::right("a", 2));

  // string

  // right(str, -n), right(str, 0), right(str, n)
  ASSERT_EQ("", strlib::right("abcxyz", -2));
  ASSERT_EQ("", strlib::right("abcxyz", -1));
  ASSERT_EQ("", strlib::right("abcxyz", 0));
  ASSERT_EQ("z", strlib::right("abcxyz", 1));
  ASSERT_EQ("yz", strlib::right("abcxyz", 2));
  ASSERT_EQ("xyz", strlib::right("abcxyz", 3));  
  ASSERT_EQ("cxyz", strlib::right("abcxyz", 4));
  ASSERT_EQ("bcxyz", strlib::right("abcxyz", 5));
  ASSERT_EQ("abcxyz", strlib::right("abcxyz", 6));
  ASSERT_EQ("abcxyz", strlib::right("abcxyz", 7));
  ASSERT_EQ("abcxyz", strlib::right("abcxyz", 8));

}

// 3.1

TEST(capitalize) {

  ASSERT_EQ("", strlib::capitalize(""));
  ASSERT_EQ(" ", strlib::capitalize(" "));
  ASSERT_EQ("Abcd", strlib::capitalize("abcd"));
  ASSERT_EQ("AbCd", strlib::capitalize("abCd"));
  ASSERT_EQ("Abcd", strlib::capitalize("abCd", true));

}

TEST(decapitalize) {

  ASSERT_EQ("", strlib::decapitalize(""));
  ASSERT_EQ(" ", strlib::decapitalize(" "));
  ASSERT_EQ("abcd", strlib::decapitalize("Abcd"));
  ASSERT_EQ("abCd", strlib::decapitalize("AbCd"));
  ASSERT_EQ("aBCD", strlib::decapitalize("AbCd", true));

}

// upper
// lower

TEST(toUpperCase) {

  ASSERT_EQ("", strlib::toUpperCase(""));
  ASSERT_EQ(" ", strlib::toUpperCase(" "));
  ASSERT_EQ("ABCD", strlib::toUpperCase("abcd"));
  ASSERT_EQ("ABCD", strlib::toUpperCase("AbCd"));

}

TEST(toLowerCase) {

  ASSERT_EQ("", strlib::toLowerCase(""));
  ASSERT_EQ(" ", strlib::toLowerCase(" "));
  ASSERT_EQ("abcd", strlib::toLowerCase("ABCD"));
  ASSERT_EQ("abcd", strlib::toLowerCase("AbCd"));

}

TEST(toCase) {

  ASSERT_EQ("", strlib::toCase("", true));
  ASSERT_EQ(" ", strlib::toCase(" ", true));
  ASSERT_EQ("ABCD", strlib::toCase("abcd", true));
  ASSERT_EQ("ABCD", strlib::toCase("AbCd", true));

  ASSERT_EQ("", strlib::toCase("", false));
  ASSERT_EQ(" ", strlib::toCase(" ", false));
  ASSERT_EQ("abcd", strlib::toCase("ABCD", false));
  ASSERT_EQ("abcd", strlib::toCase("AbCd", false));
  
}

TEST(toCamelCase) {

  ASSERT_EQ("", strlib::toCamelCase(""));
  ASSERT_EQ(" ", strlib::toCamelCase(" "));
  ASSERT_EQ("FirstName", strlib::toCamelCase("first_name"));

}

TEST(toSnakeCase) {

  ASSERT_EQ("", strlib::toSnakeCase(""));
  ASSERT_EQ(" ", strlib::toSnakeCase(" "));
  ASSERT_EQ("first_name", strlib::toSnakeCase("FirstName"));

}

TEST(toKebabCase) {

  ASSERT_EQ("", strlib::toKebabCase(""));
  ASSERT_EQ(" ", strlib::toKebabCase(" "));
  ASSERT_EQ("first-name", strlib::toKebabCase("FirstName"));

}

TEST(reverse) {

  ASSERT_EQ("", strlib::reverse(""));
  ASSERT_EQ(" ", strlib::reverse(" "));
  ASSERT_EQ("*", strlib::reverse("*"));
  ASSERT_EQ("**", strlib::reverse("**"));
  ASSERT_EQ("***", strlib::reverse("***"));
  ASSERT_EQ("****", strlib::reverse("****"));
  ASSERT_EQ("fedcba", strlib::reverse("abcdef"));

}

// 4.1

// startsWith
// endsWith

TEST(hasPrefix) {

  ASSERT_FALSE(strlib::hasPrefix("", "abc"));  
  ASSERT_FALSE(strlib::hasPrefix("myfile.txt", "abc"));

  ASSERT_TRUE(strlib::hasPrefix("", ""));
  ASSERT_TRUE(strlib::hasPrefix("myfile.txt", ""));
  ASSERT_TRUE(strlib::hasPrefix("myfile.txt", "my"));
  ASSERT_TRUE(strlib::hasPrefix("myfile.txt", "myfile"));
  ASSERT_TRUE(strlib::hasPrefix("myfile.txt", "myfile.txt"));

}

TEST(hasSuffix) {

  ASSERT_FALSE(strlib::hasSuffix("", "abc"));  
  ASSERT_FALSE(strlib::hasSuffix("myfile.txt", "abc"));

  ASSERT_TRUE(strlib::hasSuffix("", ""));
  ASSERT_TRUE(strlib::hasSuffix("myfile.txt", ""));
  ASSERT_TRUE(strlib::hasSuffix("myfile.txt", "txt"));
  ASSERT_TRUE(strlib::hasSuffix("myfile.txt", ".txt"));
  ASSERT_TRUE(strlib::hasSuffix("myfile.txt", "myfile.txt"));

}

// 4.2

TEST(removePrefix) {

  ASSERT_EQ("", strlib::removePrefix("", ""));
  ASSERT_EQ("", strlib::removePrefix("", "my"));
  ASSERT_EQ("myfile.txt", strlib::removePrefix("myfile.txt", ""));
  ASSERT_EQ("myfile.txt", strlib::removePrefix("myfile.txt", "abc"));

  ASSERT_EQ("file.txt", strlib::removePrefix("myfile.txt", "my"));
  ASSERT_EQ(".txt", strlib::removePrefix("myfile.txt", "myfile"));
  ASSERT_EQ("txt", strlib::removePrefix("myfile.txt", "myfile."));
  ASSERT_EQ("", strlib::removePrefix("myfile.txt", "myfile.txt"));

  ASSERT_EQ("myfile.txt", strlib::removePrefix("myfile.txt", "abcmyfile.txt"));

}

TEST(removeSuffix) {

  ASSERT_EQ("", strlib::removeSuffix("", ""));
  ASSERT_EQ("", strlib::removeSuffix("", "my"));
  ASSERT_EQ("myfile.txt", strlib::removeSuffix("myfile.txt", ""));
  ASSERT_EQ("myfile.txt", strlib::removeSuffix("myfile.txt", "abc"));

  ASSERT_EQ("myfile.", strlib::removeSuffix("myfile.txt", "txt"));
  ASSERT_EQ("myfile", strlib::removeSuffix("myfile.txt", ".txt"));
  ASSERT_EQ("", strlib::removeSuffix("myfile.txt", "myfile.txt"));

  ASSERT_EQ("myfile.txt", strlib::removeSuffix("myfile.txt", "abcmyfile.txt"));

}

// 4.3
// 4.4
// 5.1

TEST(countChars) {

  ASSERT_EQ(4, strlib::countChars("Hello world, my world is very nice world", 'o'));

}

TEST(countStrings) {

  ASSERT_EQ(3, strlib::countStrings("Hello world, my world is very nice world", "world"));

}

TEST(countWords) {

  ASSERT_EQ(13, strlib::countWords("Hello world, my world is very nice world. But we have other worlds."));

}

// 6.1

TEST(replaceAll) {

  ASSERT_EQ("Abcdef12345Abcdef", strlib::replaceAll("abcdef12345abcdef", "a", "A"));

  std::vector<std::string> from = {"1", "2", "3", "4", "5", "6"};
  std::vector<std::string> to = {"A", "B", "C", "D", "E", "F"};
  ASSERT_EQ("ABCDEF", strlib::replaceAll("123456", from, to));

}

// 7.1

TEST(split) {

  // splitBySeparator = split

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1,200,500,-12", ',')));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1|200|500|-12", '|')));

  ASSERT_TRUE(((std::vector<std::string>){"1", " 200", " 500", " -12"} == strlib::split("1, 200, 500, -12", ',')));
  ASSERT_TRUE(((std::vector<std::string>){"1", " 200", " 500", " -12"} == strlib::split("1| 200| 500| -12", '|')));

  //printVector(strlib::split("1, 200, 500, -12", " ,"));

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1, 200, 500, -12", ", ")));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1| 200| 500| -12", "| ")));

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", " -12"} == strlib::split("1, 200, 500,  -12", ", ")));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", " -12"} == strlib::split("1| 200| 500|  -12", "| ")));

  //ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "", "-12"} == strlib::split("1, 200| 500|| -12", ",|", true))); // trim

}

TEST(splitBySeparator) {

  // splitBySeparator

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1,200,500,-12", ',')));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1|200|500|-12", '|')));

  ASSERT_TRUE(((std::vector<std::string>){"1", " 200", " 500", " -12"} == strlib::split("1, 200, 500, -12", ',')));
  ASSERT_TRUE(((std::vector<std::string>){"1", " 200", " 500", " -12"} == strlib::split("1| 200| 500| -12", '|')));

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1, 200, 500, -12", ", ")));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1| 200| 500| -12", "| ")));

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", " -12"} == strlib::split("1, 200, 500,  -12", ", ")));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", " -12"} == strlib::split("1| 200| 500|  -12", "| ")));

}

TEST(splitBySeparators) {

  // splitBySeparators

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::splitBySeparators("1, 200| 500|| -12", " ,|", false)));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::splitBySeparators("1, 200| 500||    -12", " ,|", false)));

}

TEST(splitWords) {

  std::vector<std::string> words = {"Hello", "world", "my", "world", "is", "very", "nice", "world", "But", "we", "have", "other", "worlds"};
  ASSERT_TRUE((words == strlib::splitWords("Hello world, my world is very nice world. But we have other worlds.")));

}

// 8.1

TEST(toString) {
 
  char a[] = {'H', 'e', 'l', 'l', 'o', '\0'};
  char b[] = "Hello";

  ASSERT_EQ("Hello", strlib::toString(a));
  ASSERT_EQ("Hello", strlib::toString(b));
   
}

// 8.2
// 9.1

TEST(isIdentifier) {

  ASSERT_FALSE(strlib::isIdentifier(""));
  ASSERT_FALSE(strlib::isIdentifier(" "));
  
  ASSERT_FALSE(strlib::isIdentifier("`"));
  ASSERT_FALSE(strlib::isIdentifier("~"));
  ASSERT_FALSE(strlib::isIdentifier("!"));
  ASSERT_FALSE(strlib::isIdentifier("@"));
  ASSERT_FALSE(strlib::isIdentifier("#"));
  ASSERT_FALSE(strlib::isIdentifier("$"));
  ASSERT_FALSE(strlib::isIdentifier("%"));
  ASSERT_FALSE(strlib::isIdentifier("^"));
  ASSERT_FALSE(strlib::isIdentifier("&"));
  ASSERT_FALSE(strlib::isIdentifier("*"));
  ASSERT_FALSE(strlib::isIdentifier("("));
  ASSERT_FALSE(strlib::isIdentifier(")"));
  ASSERT_FALSE(strlib::isIdentifier("{"));
  ASSERT_FALSE(strlib::isIdentifier("}"));
  ASSERT_FALSE(strlib::isIdentifier("["));
  ASSERT_FALSE(strlib::isIdentifier("]"));

  ASSERT_FALSE(strlib::isIdentifier("-"));
  ASSERT_FALSE(strlib::isIdentifier("+"));
  ASSERT_FALSE(strlib::isIdentifier("_"));
  ASSERT_FALSE(strlib::isIdentifier("="));
  ASSERT_FALSE(strlib::isIdentifier("?"));

  ASSERT_FALSE(strlib::isIdentifier("."));
  ASSERT_FALSE(strlib::isIdentifier(","));
  ASSERT_FALSE(strlib::isIdentifier(";"));
  ASSERT_FALSE(strlib::isIdentifier("\'"));
  ASSERT_FALSE(strlib::isIdentifier("\""));
  ASSERT_FALSE(strlib::isIdentifier("/"));
  ASSERT_FALSE(strlib::isIdentifier("\\"));
  
  ASSERT_FALSE(strlib::isIdentifier("12345"));
  ASSERT_FALSE(strlib::isIdentifier("1abcd"));
  ASSERT_FALSE(strlib::isIdentifier("____"));

  ASSERT_TRUE(strlib::isIdentifier("_abcd123"));
  ASSERT_TRUE(strlib::isIdentifier("abcd123"));

}

INIT(strlib) {

  SET_TEST(isEmpty);
  SET_TEST(isBlank);
  SET_TEST(size);
  SET_TEST(equals);
  SET_TEST(equalsContent);
  SET_TEST(normalize);
  SET_TEST(normalizeBlank)
  SET_TEST(defautIfEmpty)
  SET_TEST(trim)
  SET_TEST(trimAll)
  SET_TEST(trimSpace)
  SET_TEST(ltrim)
  SET_TEST(rtrim)

  SET_TEST(contains)
  SET_TEST(findFirstOf);
  SET_TEST(findLastOf);
  SET_TEST(findFirstNotOf);
  SET_TEST(findLastNotOf);
  SET_TEST(replicate);
  SET_TEST(lpad);
  SET_TEST(rpad);
  SET_TEST(fill);
  SET_TEST(ellipsis);
  SET_TEST(trunc);  
  SET_TEST(left)
  SET_TEST(right)

  SET_TEST(capitalize);
  SET_TEST(decapitalize);  
  SET_TEST(toUpperCase);
  SET_TEST(toLowerCase);
  SET_TEST(toCase);
  SET_TEST(toCamelCase);
  SET_TEST(toSnakeCase);
  SET_TEST(toKebabCase);
  SET_TEST(reverse);
  SET_TEST(hasPrefix);
  SET_TEST(hasSuffix);
  SET_TEST(removePrefix);
  SET_TEST(removeSuffix);
  SET_TEST(countChars);
  SET_TEST(countStrings);
  SET_TEST(countWords);
  SET_TEST(replaceAll);
  SET_TEST(split);
  SET_TEST(splitBySeparator);
  SET_TEST(splitBySeparators);
  SET_TEST(splitWords);
  SET_TEST(toString);
  SET_TEST(isIdentifier);

}

/*
TEST_ALL(strlib) { 

  RUN(isEmpty);
  RUN(size);
  RUN(normalize);
  RUN(lpad);
  RUN(rpad);
  RUN(fill);
  RUN(ellipsis);
  RUN(trunc);
  RUN(toCase);
  RUN(reverse);
  RUN(hasPrefix);
  RUN(hasSuffix);
  RUN(removePrefix);
  RUN(removeSuffix);
  RUN(count);
  RUN(split);
  RUN(splitWords);
  RUN(replaceAll);
  RUN(isIdentifier);
  RUN(toString);
    
 }
 */

//#endif // PLAZMA_LIB_TEST_STRLIB_H