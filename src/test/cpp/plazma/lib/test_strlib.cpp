
#include <string>
#include <iostream>

#include "plazma/lib/str/strlib.h" 
#include "test.h"

typedef std::vector<std::string> VS;

// 1.1

TEST(isEmpty_char) {

  // isEmpty(null)
  ASSERT_TRUE(strlib::isEmpty((char*) nullptr));

  // isEmpty(empty)
  ASSERT_TRUE(strlib::isEmpty((char*) ""));

  // isEmpty(blank)
  ASSERT_FALSE(strlib::isEmpty((char*) " "));
  ASSERT_FALSE(strlib::isEmpty((char*) "  "));

  // isEmpty(value)
  ASSERT_FALSE(strlib::isEmpty((char*) "abc"));
  ASSERT_FALSE(strlib::isEmpty((char*) " abc"));
  ASSERT_FALSE(strlib::isEmpty((char*) "abc "));
  ASSERT_FALSE(strlib::isEmpty((char*) " abc "));

}

TEST(isEmpty_string) {

  // isEmpty(empty)
  ASSERT_TRUE(strlib::isEmpty((std::string) ""));

  // isEmpty(blank)
  ASSERT_FALSE(strlib::isEmpty((std::string) " "));
  ASSERT_FALSE(strlib::isEmpty((std::string) "  "));

  // isEmpty(value)
  ASSERT_FALSE(strlib::isEmpty((std::string) "abc"));
  ASSERT_FALSE(strlib::isEmpty((std::string) " abc"));
  ASSERT_FALSE(strlib::isEmpty((std::string) "abc "));
  ASSERT_FALSE(strlib::isEmpty((std::string) " abc "));

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

TEST(size_char) {

  // size(null)
  ASSERT_EQ(0, strlib::size((char*) nullptr));

  // size(empty)
  ASSERT_EQ(0, strlib::size((char*) ""));

  // size(blank)
  ASSERT_EQ(1, strlib::size((char*) " "));
  ASSERT_EQ(2, strlib::size((char*) "  "));

  // size(value)
  ASSERT_EQ(3, strlib::size((char*) "abc"));
  ASSERT_EQ(4, strlib::size((char*) " abc"));
  ASSERT_EQ(4, strlib::size((char*) "abc "));
  ASSERT_EQ(5, strlib::size((char*) " abc "));

}

TEST(size_string) {

  // size(empty)
  ASSERT_EQ(0, strlib::size((std::string) ""));

  // size(blank)
  ASSERT_EQ(1, strlib::size((std::string) " "));
  ASSERT_EQ(2, strlib::size((std::string) "  "));

  // size(value)
  ASSERT_EQ(3, strlib::size((std::string) "abc"));
  ASSERT_EQ(4, strlib::size((std::string) " abc"));
  ASSERT_EQ(4, strlib::size((std::string) "abc "));
  ASSERT_EQ(5, strlib::size((std::string) " abc "));

}

TEST(equals_char) {

  // char*

  // equals(null, null)
  ASSERT_TRUE(strlib::equals((char*) nullptr, (char*) nullptr));

  // equals(null, empty), equals(null, value)
  ASSERT_FALSE(strlib::equals(nullptr, (char*) ""));
  ASSERT_FALSE(strlib::equals(nullptr, (char*) " "));
  ASSERT_FALSE(strlib::equals(nullptr, (char*) "abc"));

  // equals(empty, null), equals(value, null)
  ASSERT_FALSE(strlib::equals((char*) "", nullptr));
  ASSERT_FALSE(strlib::equals((char*) " ", nullptr));
  ASSERT_FALSE(strlib::equals((char*) "abc", nullptr));

  // equals(empty, value), equals(value, empty)
  ASSERT_FALSE(strlib::equals((char*) "", (char*) "abc"));
  ASSERT_FALSE(strlib::equals((char*) "abc", (char*) ""));

  // equals(empty, empty), equals(blank, blank)
  ASSERT_TRUE(strlib::equals((char*) "", (char*) ""));
  ASSERT_TRUE(strlib::equals((char*) " ", (char*) " "));
  ASSERT_TRUE(strlib::equals((char*) "  ", (char*) "  "));

  // equals(empty, blank), equals(blank, empty)
  ASSERT_FALSE(strlib::equals((char*) "", (char*) " "));
  ASSERT_FALSE(strlib::equals((char*) "", (char*) "  "));
  ASSERT_FALSE(strlib::equals((char*) " ", (char*) ""));
  ASSERT_FALSE(strlib::equals((char*) "  ", (char*) ""));

  // equals(value, value)
  ASSERT_FALSE(strlib::equals((char*) " abc", (char*) "abc"));
  ASSERT_FALSE(strlib::equals((char*) "abc ", (char*) "abc"));
  ASSERT_FALSE(strlib::equals((char*) " abc ", (char*) "abc"));
  ASSERT_FALSE(strlib::equals((char*) "abc", (char*) "xyz"));

  ASSERT_TRUE(strlib::equals((char*) "abc", (char*) "abc"));
  ASSERT_TRUE(strlib::equals((char*) " abc", (char*) " abc"));
  ASSERT_TRUE(strlib::equals((char*) "abc ", (char*) "abc "));
  ASSERT_TRUE(strlib::equals((char*) " abc ", (char*) " abc "));
  
}

TEST(equals_string) {

  // string

  // equals(empty, value), equals(value, empty)
  ASSERT_FALSE(strlib::equals((std::string) "", (std::string) "abc"));
  ASSERT_FALSE(strlib::equals((std::string) "abc", (std::string) ""));

  // equals(empty, empty), equals(blank, blank)
  ASSERT_TRUE(strlib::equals((std::string) "", (std::string) ""));
  ASSERT_TRUE(strlib::equals((std::string) " ", (std::string) " "));
  ASSERT_TRUE(strlib::equals((std::string) "  ", (std::string) "  "));

  // equals(empty, blank), equals(blank, empty)
  ASSERT_FALSE(strlib::equals((std::string) "", (std::string) " "));
  ASSERT_FALSE(strlib::equals((std::string) "", (std::string) "  "));
  ASSERT_FALSE(strlib::equals((std::string) " ", (std::string) ""));
  ASSERT_FALSE(strlib::equals((std::string) "  ", (std::string) ""));

  // equals(value, value)
  ASSERT_FALSE(strlib::equals((std::string) " abc", (std::string) "abc"));
  ASSERT_FALSE(strlib::equals((std::string) "abc ", (std::string) "abc"));
  ASSERT_FALSE(strlib::equals((std::string) " abc ", (std::string) "abc"));
  ASSERT_FALSE(strlib::equals((std::string) "abc", (std::string) "xyz"));

  ASSERT_TRUE(strlib::equals((std::string) "abc", (std::string) "abc"));
  ASSERT_TRUE(strlib::equals((std::string) " abc", (std::string) " abc"));
  ASSERT_TRUE(strlib::equals((std::string) "abc ", (std::string) "abc "));
  ASSERT_TRUE(strlib::equals((std::string) " abc ", (std::string) " abc "));
  
}

TEST(equalsContent_char) {

  // equalsContent(empty, null)

  // False
  ASSERT_FALSE(strlib::equalsContent("", nullptr));  // "" != null
  ASSERT_FALSE(strlib::equalsContent(" ", nullptr));
  ASSERT_FALSE(strlib::equalsContent("  ", nullptr));

  ASSERT_FALSE(strlib::equalsContent("a", nullptr));
  ASSERT_FALSE(strlib::equalsContent("ab", nullptr));
  ASSERT_FALSE(strlib::equalsContent("abc", nullptr));

  // True
  ASSERT_TRUE(strlib::equalsContent("", (char*) ""));
  ASSERT_TRUE(strlib::equalsContent(" ", (char*) " "));
  ASSERT_TRUE(strlib::equalsContent("  ", (char*) "  "));

  ASSERT_TRUE(strlib::equalsContent("a", (char*) "a"));
  ASSERT_TRUE(strlib::equalsContent("ab", (char*) "ab"));
  ASSERT_TRUE(strlib::equalsContent("abc", (char*) "abc"));

  // False
  ASSERT_FALSE(strlib::equalsContent("ab", (char*) "a"));
  ASSERT_FALSE(strlib::equalsContent("abc", (char*) "a"));
  ASSERT_FALSE(strlib::equalsContent("abc", (char*) "xyz"));

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

TEST(contains_char) {

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

}

TEST(contains_string) {

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

TEST(replicate_char) {

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

}

TEST(replicate_char_as_string) {

  // char as string

  // replicate(char, -n), replicate(char, 0), replicate(char, n)
  ASSERT_EQ("", strlib::replicate(".", -2));
  ASSERT_EQ("", strlib::replicate(".", -1));
  ASSERT_EQ("", strlib::replicate(".", 0));
  ASSERT_EQ(".", strlib::replicate(".", 1));
  ASSERT_EQ("..", strlib::replicate(".", 2));

  // replicate(char, n)
  ASSERT_EQ("*", strlib::replicate("*", 1));
  ASSERT_EQ("**", strlib::replicate("*", 2));
  ASSERT_EQ("***", strlib::replicate("*", 3));

  ASSERT_EQ("a", strlib::replicate("a", 1));
  ASSERT_EQ("aa", strlib::replicate("a", 2));
  ASSERT_EQ("aaa", strlib::replicate("a", 3));

}

TEST(replicate_string) {

  // string

  // replicate(empty, -n), replicate(empty, 0), replicate(empty, n)
  ASSERT_EQ("", strlib::replicate("", -2));
  ASSERT_EQ("", strlib::replicate("", -1));
  ASSERT_EQ("", strlib::replicate("", 0));
  ASSERT_EQ("", strlib::replicate("", 1));
  ASSERT_EQ("", strlib::replicate("", 2));

  // replicate(str, -n), replicate(str, 0), replicate(str, n)
  ASSERT_EQ("", strlib::replicate("abc", -2));
  ASSERT_EQ("", strlib::replicate("abc", -1));
  ASSERT_EQ("", strlib::replicate("abc", 0));
  ASSERT_EQ("abc", strlib::replicate("abc", 1));
  ASSERT_EQ("abcabc", strlib::replicate("abc", 2));
  ASSERT_EQ("abcabcabc", strlib::replicate("abc", 3));

}

// 2.2

TEST(lpad_char) {

  // empty

  // lpad(empty, -n, '\0'), lpad(empty, 0, '\0'), lpad(empty, n, '\0')
  ASSERT_EQ("", strlib::lpad("", -2, '\0'));
  ASSERT_EQ("", strlib::lpad("", -1, '\0'));
  ASSERT_EQ("", strlib::lpad("", 0, '\0'));
  //ASSERT_EQ("\0", strlib::lpad("", 1, '\0'));     // NUL SPEC
  //ASSERT_EQ("\0\0", strlib::lpad("", 2, '\0'));   // NUL SPEC
  //ASSERT_EQ("\0\0\0", strlib::lpad("", 3, '\0')); // NUL SPEC

  // lpad(empty, -n, ' '), lpad(empty, 0, ' '), lpad(empty, n, ' ')
  ASSERT_EQ("", strlib::lpad("", -2, ' '));
  ASSERT_EQ("", strlib::lpad("", -1, ' '));
  ASSERT_EQ("", strlib::lpad("", 0, ' '));
  ASSERT_EQ(" ", strlib::lpad("", 1, ' '));
  ASSERT_EQ("  ", strlib::lpad("", 2, ' '));
  ASSERT_EQ("   ", strlib::lpad("", 3, ' '));

  // lpad(empty, -n, '*'), lpad(empty, 0, '*'), lpad(empty, n, '*')
  ASSERT_EQ("", strlib::lpad("", -2, '*'));
  ASSERT_EQ("", strlib::lpad("", -1, '*'));
  ASSERT_EQ("", strlib::lpad("", 0, '*'));
  ASSERT_EQ("*", strlib::lpad("", 1, '*'));
  ASSERT_EQ("**", strlib::lpad("", 2, '*'));
  ASSERT_EQ("***", strlib::lpad("", 3, '*'));

  // char

  // lpad(char, -n, '*'), lpad(char, 0, '*'), lpad(char, n, '*')
  ASSERT_EQ("a", strlib::lpad("a", -2, '*'));
  ASSERT_EQ("a", strlib::lpad("a", -1, '*'));
  ASSERT_EQ("a", strlib::lpad("a", 0, '*'));
  ASSERT_EQ("a", strlib::lpad("a", 1, '*'));
  ASSERT_EQ("*a", strlib::lpad("a", 2, '*'));
  ASSERT_EQ("**a", strlib::lpad("a", 3, '*'));

  // string

  // lpad(str, -n, '*'), lpad(str, 0, '*'), lpad(str, n, '*')
  ASSERT_EQ("abc", strlib::lpad("abc", -2, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", -1, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 0, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 1, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 2, '*'));
  ASSERT_EQ("abc", strlib::lpad("abc", 3, '*'));
  ASSERT_EQ("*abc", strlib::lpad("abc", 4, '*'));
  ASSERT_EQ("**abc", strlib::lpad("abc", 5, '*'));

}

TEST(lpad_char_as_string) {

  // empty

  // lpad(empty, -n, ''), lpad(empty, 0, ''), lpad(empty, n, '')
  ASSERT_EQ("", strlib::lpad("", -2, ""));
  ASSERT_EQ("", strlib::lpad("", -1, ""));
  ASSERT_EQ("", strlib::lpad("", 0, ""));
  ASSERT_EQ("", strlib::lpad("", 1, ""));
  ASSERT_EQ("", strlib::lpad("", 2, ""));
  ASSERT_EQ("", strlib::lpad("", 3, ""));

  // lpad(empty, -n, '\0'), lpad(empty, 0, '\0'), lpad(empty, n, '\0')
  ASSERT_EQ("", strlib::lpad("", -2, "\0"));
  ASSERT_EQ("", strlib::lpad("", -1, "\0"));
  ASSERT_EQ("", strlib::lpad("", 0, "\0"));
  //ASSERT_EQ("\0", strlib::lpad("", 1, "\0"));     // NUL SPEC
  //ASSERT_EQ("\0\0", strlib::lpad("", 2, "\0"));   // NUL SPEC
  //ASSERT_EQ("\0\0\0", strlib::lpad("", 3, "\0")); // NUL SPEC

  // lpad(empty, -n, ' '), lpad(empty, 0, ' '), lpad(empty, n, ' ')
  ASSERT_EQ("", strlib::lpad("", -2, " "));
  ASSERT_EQ("", strlib::lpad("", -1, " "));
  ASSERT_EQ("", strlib::lpad("", 0, " "));
  ASSERT_EQ(" ", strlib::lpad("", 1, " "));
  ASSERT_EQ("  ", strlib::lpad("", 2, " "));
  ASSERT_EQ("   ", strlib::lpad("", 3, " "));

  // lpad(empty, -n, '*'), lpad(empty, 0, '*'), lpad(empty, n, '*')
  ASSERT_EQ("", strlib::lpad("", -2, "*"));
  ASSERT_EQ("", strlib::lpad("", -1, "*"));
  ASSERT_EQ("", strlib::lpad("", 0, "*"));
  ASSERT_EQ("*", strlib::lpad("", 1, "*"));
  ASSERT_EQ("**", strlib::lpad("", 2, "*"));
  ASSERT_EQ("***", strlib::lpad("", 3, "*"));

  // char

  // lpad(char, -n, '*'), lpad(char, 0, '*'), lpad(char, n, '*')
  ASSERT_EQ("a", strlib::lpad("a", -2, "*"));
  ASSERT_EQ("a", strlib::lpad("a", -1, "*"));
  ASSERT_EQ("a", strlib::lpad("a", 0, "*"));
  ASSERT_EQ("a", strlib::lpad("a", 1, "*"));
  ASSERT_EQ("*a", strlib::lpad("a", 2, "*"));
  ASSERT_EQ("**a", strlib::lpad("a", 3, "*"));

  // string

  // lpad(str, -n, '*'), lpad(str, 0, '*'), lpad(str, n, '*')
  ASSERT_EQ("abc", strlib::lpad("abc", -2, "*"));
  ASSERT_EQ("abc", strlib::lpad("abc", -1, "*"));
  ASSERT_EQ("abc", strlib::lpad("abc", 0, "*"));
  ASSERT_EQ("abc", strlib::lpad("abc", 1, "*"));
  ASSERT_EQ("abc", strlib::lpad("abc", 2, "*"));
  ASSERT_EQ("abc", strlib::lpad("abc", 3, "*"));
  ASSERT_EQ("*abc", strlib::lpad("abc", 4, "*"));
  ASSERT_EQ("**abc", strlib::lpad("abc", 5, "*"));

}

TEST(lpad_string) {

  // empty

  // lpad(empty, -n), lpad(empty, 0), lpad(empty, n)
  ASSERT_EQ("", strlib::lpad("", -2));
  ASSERT_EQ("", strlib::lpad("", -1));
  ASSERT_EQ("", strlib::lpad("", 0));
  ASSERT_EQ(" ", strlib::lpad("", 1));
  ASSERT_EQ("  ", strlib::lpad("", 2));
  ASSERT_EQ("   ", strlib::lpad("", 3));

  // char

  // lpad(char, -n), lpad(char, 0), lpad(char, n)
  ASSERT_EQ("a", strlib::lpad("a", -2));
  ASSERT_EQ("a", strlib::lpad("a", -1));
  ASSERT_EQ("a", strlib::lpad("a", 0));
  ASSERT_EQ("a", strlib::lpad("a", 1));
  ASSERT_EQ(" a", strlib::lpad("a", 2));
  ASSERT_EQ("  a", strlib::lpad("a", 3));

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

TEST(rpad_char) {

  // empty

  // rpad(empty, -n, '\0'), rpad(empty, 0, '\0'), rpad(empty, n, '\0')
  ASSERT_EQ("", strlib::rpad("", -2, '\0'));
  ASSERT_EQ("", strlib::rpad("", -1, '\0'));
  ASSERT_EQ("", strlib::rpad("", 0, '\0'));
  //ASSERT_EQ("\0", strlib::rpad("", 1, '\0'));     // NUL SPEC
  //ASSERT_EQ("\0\0", strlib::rpad("", 2, '\0'));   // NUL SPEC
  //ASSERT_EQ("\0\0\0", strlib::rpad("", 3, '\0')); // NUL SPEC

  // rpad(empty, -n, ' '), rpad(empty, 0, ' '), rpad(empty, n, ' ')
  ASSERT_EQ("", strlib::rpad("", -2, ' '));
  ASSERT_EQ("", strlib::rpad("", -1, ' '));
  ASSERT_EQ("", strlib::rpad("", 0, ' '));
  ASSERT_EQ(" ", strlib::rpad("", 1, ' '));
  ASSERT_EQ("  ", strlib::rpad("", 2, ' '));
  ASSERT_EQ("   ", strlib::rpad("", 3, ' '));

  // rpad(empty, -n, '*'), rpad(empty, 0, '*'), rpad(empty, n, '*')
  ASSERT_EQ("", strlib::rpad("", -2, '*'));
  ASSERT_EQ("", strlib::rpad("", -1, '*'));
  ASSERT_EQ("", strlib::rpad("", 0, '*'));
  ASSERT_EQ("*", strlib::rpad("", 1, '*'));
  ASSERT_EQ("**", strlib::rpad("", 2, '*'));
  ASSERT_EQ("***", strlib::rpad("", 3, '*'));

  // char

  // rpad(char, -n, '*'), rpad(char, 0, '*'), rpad(char, n, '*')
  ASSERT_EQ("a", strlib::rpad("a", -2, '*'));
  ASSERT_EQ("a", strlib::rpad("a", -1, '*'));
  ASSERT_EQ("a", strlib::rpad("a", 0, '*'));
  ASSERT_EQ("a", strlib::rpad("a", 1, '*'));
  ASSERT_EQ("a*", strlib::rpad("a", 2, '*'));
  ASSERT_EQ("a**", strlib::rpad("a", 3, '*'));

  // string

  ASSERT_EQ("abc", strlib::rpad("abc", -2, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", -1, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 0, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 1, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 2, '*'));
  ASSERT_EQ("abc", strlib::rpad("abc", 3, '*'));
  ASSERT_EQ("abc*", strlib::rpad("abc", 4, '*'));
  ASSERT_EQ("abc**", strlib::rpad("abc", 5, '*'));

}

TEST(rpad_char_as_string) {

  // empty

  // rpad(empty, -n, ''), rpad(empty, 0, ''), rpad(empty, n, '')
  ASSERT_EQ("", strlib::rpad("", -2, ""));
  ASSERT_EQ("", strlib::rpad("", -1, ""));
  ASSERT_EQ("", strlib::rpad("", 0, ""));
  ASSERT_EQ("", strlib::rpad("", 1, ""));
  ASSERT_EQ("", strlib::rpad("", 2, ""));
  ASSERT_EQ("", strlib::rpad("", 3, ""));

  // rpad(empty, -n, '\0'), rpad(empty, 0, '\0'), rpad(empty, n, '\0')
  ASSERT_EQ("", strlib::rpad("", -2, "\0"));
  ASSERT_EQ("", strlib::rpad("", -1, "\0"));
  ASSERT_EQ("", strlib::rpad("", 0, "\0"));
  //ASSERT_EQ("\0", strlib::rpad("", 1, "\0"));     // NUL SPEC
  //ASSERT_EQ("\0\0", strlib::rpad("", 2, "\0"));   // NUL SPEC
  //ASSERT_EQ("\0\0\0", strlib::rpad("", 3, "\0")); // NUL SPEC

  // rpad(empty, -n, ' '), rpad(empty, 0, ' '), rpad(empty, n, ' ')
  ASSERT_EQ("", strlib::rpad("", -2, " "));
  ASSERT_EQ("", strlib::rpad("", -1, " "));
  ASSERT_EQ("", strlib::rpad("", 0, " "));
  ASSERT_EQ(" ", strlib::rpad("", 1, " "));
  ASSERT_EQ("  ", strlib::rpad("", 2, " "));
  ASSERT_EQ("   ", strlib::rpad("", 3, " "));

  // rpad(empty, -n, '*'), rpad(empty, 0, '*'), rpad(empty, n, '*')
  ASSERT_EQ("", strlib::rpad("", -2, "*"));
  ASSERT_EQ("", strlib::rpad("", -1, "*"));
  ASSERT_EQ("", strlib::rpad("", 0, "*"));
  ASSERT_EQ("*", strlib::rpad("", 1, "*"));
  ASSERT_EQ("**", strlib::rpad("", 2, "*"));
  ASSERT_EQ("***", strlib::rpad("", 3, "*"));

  // char

  // rpad(char, -n, '*'), rpad(char, 0, '*'), rpad(char, n, '*')
  ASSERT_EQ("a", strlib::rpad("a", -2, "*"));
  ASSERT_EQ("a", strlib::rpad("a", -1, "*"));
  ASSERT_EQ("a", strlib::rpad("a", 0, "*"));
  ASSERT_EQ("a", strlib::rpad("a", 1, "*"));
  ASSERT_EQ("a*", strlib::rpad("a", 2, "*"));
  ASSERT_EQ("a**", strlib::rpad("a", 3, "*"));

  // string

  ASSERT_EQ("abc", strlib::rpad("abc", -2, "*"));
  ASSERT_EQ("abc", strlib::rpad("abc", -1, "*"));
  ASSERT_EQ("abc", strlib::rpad("abc", 0, "*"));
  ASSERT_EQ("abc", strlib::rpad("abc", 1, "*"));
  ASSERT_EQ("abc", strlib::rpad("abc", 2, "*"));
  ASSERT_EQ("abc", strlib::rpad("abc", 3, "*"));
  ASSERT_EQ("abc*", strlib::rpad("abc", 4, "*"));
  ASSERT_EQ("abc**", strlib::rpad("abc", 5, "*"));

}

TEST(rpad_string) {

  // empty

  // rpad(empty, -n), rpad(empty, 0), rpad(empty, n)
  ASSERT_EQ("", strlib::rpad("", -2));
  ASSERT_EQ("", strlib::rpad("", -1));
  ASSERT_EQ("", strlib::rpad("", 0));
  ASSERT_EQ(" ", strlib::rpad("", 1));
  ASSERT_EQ("  ", strlib::rpad("", 2));
  ASSERT_EQ("   ", strlib::rpad("", 3));

  // char

  // rpad(char, -n), rpad(char, 0), rpad(char, n)
  ASSERT_EQ("a", strlib::rpad("a", -2));
  ASSERT_EQ("a", strlib::rpad("a", -1));
  ASSERT_EQ("a", strlib::rpad("a", 0));
  ASSERT_EQ("a", strlib::rpad("a", 1));
  ASSERT_EQ("a ", strlib::rpad("a", 2));
  ASSERT_EQ("a  ", strlib::rpad("a", 3));

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

TEST(fill_char) {

  // empty

  // fill(empty, -n, '\0'), fill(empty, 0, '\0'), fill(empty, n, '\0')
  ASSERT_EQ("", strlib::fill("", -2, '\0'));
  ASSERT_EQ("", strlib::fill("", -1, '\0'));
  ASSERT_EQ("", strlib::fill("", 0, '\0'));
  //ASSERT_EQ("\0", strlib::fill("", 1, '\0'));      // NUL SPEC
  //ASSERT_EQ("\0\0", strlib::fill("", 2, '\0'));    // NUL SPEC
  //ASSERT_EQ("\0\0\0", strlib::fill("", 3, '\0'));  // NUL SPEC

  //std::string ve = "\0\0";
  //std::string ve(2, '\0');
  //std::string va = strlib::fill("", 2, '\0\0');

  //printf("EXPECTED=%s\n", ve.c_str());
  //printf("EXPECTED=%d\n", ve.length());

  //printf("ACTUAL  =%s\n", va.c_str());
  //printf("ACTUAL  =%d\n", va.length());  

  // fill(empty, -n, ' '), fill(empty, 0, ' '), fill(empty, n, ' ')
  ASSERT_EQ("", strlib::fill("", -2, ' '));
  ASSERT_EQ("", strlib::fill("", -1, ' '));
  ASSERT_EQ("", strlib::fill("", 0, ' '));
  ASSERT_EQ(" ", strlib::fill("", 1, ' '));
  ASSERT_EQ("  ", strlib::fill("", 2, ' '));
  ASSERT_EQ("   ", strlib::fill("", 3, ' '));

  // fill(empty, -n, '*'), fill(empty, 0, '*'), fill(empty, n, '*')
  ASSERT_EQ("", strlib::fill("", -2, '*'));
  ASSERT_EQ("", strlib::fill("", -1, '*'));
  ASSERT_EQ("", strlib::fill("", 0, '*'));
  ASSERT_EQ("*", strlib::fill("", 1, '*'));
  ASSERT_EQ("**", strlib::fill("", 2, '*'));
  ASSERT_EQ("***", strlib::fill("", 3, '*'));

  // char

  // fill(char, -n, '*'), fill(char, 0, '*'), fill(char, n, '*')
  ASSERT_EQ("", strlib::fill("a", -2, '*'));
  ASSERT_EQ("", strlib::fill("a", -1, '*'));
  ASSERT_EQ("", strlib::fill("a", 0, '*'));
  ASSERT_EQ("a", strlib::fill("a", 1, '*'));
  ASSERT_EQ("a*", strlib::fill("a", 2, '*'));
  ASSERT_EQ("a**", strlib::fill("a", 3, '*'));
  ASSERT_EQ("a***", strlib::fill("a", 4, '*'));
  ASSERT_EQ("a****", strlib::fill("a", 5, '*'));

  // string

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

TEST(fill_char_as_string) {

  // empty

  // fill(empty, -n, ''), fill(empty, 0, ''), fill(empty, n, '')
  ASSERT_EQ("", strlib::fill("", -2, ""));
  ASSERT_EQ("", strlib::fill("", -1, ""));
  ASSERT_EQ("", strlib::fill("", 0, ""));
  ASSERT_EQ("", strlib::fill("", 1, ""));
  ASSERT_EQ("", strlib::fill("", 2, ""));
  ASSERT_EQ("", strlib::fill("", 3, ""));

  // fill(empty, -n, '\0'), fill(empty, 0, '\0'), fill(empty, n, '\0')
  ASSERT_EQ("", strlib::fill("", -2, "\0"));
  ASSERT_EQ("", strlib::fill("", -1, "\0"));
  ASSERT_EQ("", strlib::fill("", 0, "\0"));
  //ASSERT_EQ("\0", strlib::fill("", 1, "\0"));   // NUL SPEC
  //ASSERT_EQ("\0\0", strlib::fill("", 2, "\0")); // NUL SPEC
  //ASSERT_EQ("\0\0\0", strlib::fill("", 3, "\0")); // NUL SPEC

  // fill(empty, -n, ' '), fill(empty, 0, ' '), fill(empty, n, ' ')
  ASSERT_EQ("", strlib::fill("", -2, " "));
  ASSERT_EQ("", strlib::fill("", -1, " "));
  ASSERT_EQ("", strlib::fill("", 0, " "));
  ASSERT_EQ(" ", strlib::fill("", 1, " "));
  ASSERT_EQ("  ", strlib::fill("", 2, " "));
  ASSERT_EQ("   ", strlib::fill("", 3, " "));

  // fill(empty, -n, '*'), fill(empty, 0, '*'), fill(empty, n, '*')
  ASSERT_EQ("", strlib::fill("", -2, "*"));
  ASSERT_EQ("", strlib::fill("", -1, "*"));
  ASSERT_EQ("", strlib::fill("", 0, "*"));
  ASSERT_EQ("*", strlib::fill("", 1, "*"));
  ASSERT_EQ("**", strlib::fill("", 2, "*"));
  ASSERT_EQ("***", strlib::fill("", 3, "*"));

  // char

  // fill(char, -n, '*'), fill(char, 0, '*'), fill(char, n, '*')
  ASSERT_EQ("", strlib::fill("a", -2, "*"));
  ASSERT_EQ("", strlib::fill("a", -1, "*"));
  ASSERT_EQ("", strlib::fill("a", 0, "*"));
  ASSERT_EQ("a", strlib::fill("a", 1, "*"));
  ASSERT_EQ("a*", strlib::fill("a", 2, "*"));
  ASSERT_EQ("a**", strlib::fill("a", 3, "*"));
  ASSERT_EQ("a***", strlib::fill("a", 4, "*"));
  ASSERT_EQ("a****", strlib::fill("a", 5, "*"));

  // string

  // fill(str, -n, '*'), fill(str, 0, '*'), fill(str, n, '*')
  ASSERT_EQ("", strlib::fill("abcxyz", -2, "*"));
  ASSERT_EQ("", strlib::fill("abcxyz", -1, "*"));
  ASSERT_EQ("", strlib::fill("abcxyz", 0, "*"));
  ASSERT_EQ("a", strlib::fill("abcxyz", 1, "*"));
  ASSERT_EQ("ab", strlib::fill("abcxyz", 2, "*"));
  ASSERT_EQ("abc", strlib::fill("abcxyz", 3, "*"));
  ASSERT_EQ("a...", strlib::fill("abcxyz", 4, "*"));   // ellipsis
  ASSERT_EQ("ab...", strlib::fill("abcxyz", 5, "*"));  // ellipsis
  ASSERT_EQ("abcxyz", strlib::fill("abcxyz", 6, "*"));
  ASSERT_EQ("abcxyz*", strlib::fill("abcxyz", 7, "*"));
  ASSERT_EQ("abcxyz**", strlib::fill("abcxyz", 8, "*"));

}

TEST(fill_string) {

  // fill(empty, -n), fill(empty, 0), fill(empty, n)
  ASSERT_EQ("", strlib::fill("", -2));
  ASSERT_EQ("", strlib::fill("", -1));
  ASSERT_EQ("", strlib::fill("", 0));
  ASSERT_EQ(" ", strlib::fill("", 1));
  ASSERT_EQ("  ", strlib::fill("", 2));

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
  ASSERT_EQ("", strlib::fill("abcxyz", -2, "*"));
  ASSERT_EQ("", strlib::fill("abcxyz", -1, "*"));
  ASSERT_EQ("", strlib::fill("abcxyz", 0, "*"));
  ASSERT_EQ("a", strlib::fill("abcxyz", 1, "*"));
  ASSERT_EQ("ab", strlib::fill("abcxyz", 2, "*"));
  ASSERT_EQ("abc", strlib::fill("abcxyz", 3, "*"));
  ASSERT_EQ("a...", strlib::fill("abcxyz", 4, "*"));   // ellipsis
  ASSERT_EQ("ab...", strlib::fill("abcxyz", 5, "*"));  // ellipsis
  ASSERT_EQ("abcxyz", strlib::fill("abcxyz", 6, "*"));
  ASSERT_EQ("abcxyz*", strlib::fill("abcxyz", 7, "*"));
  ASSERT_EQ("abcxyz**", strlib::fill("abcxyz", 8, "*"));
  ASSERT_EQ("abcxyz***", strlib::fill("abcxyz", 9, "*"));

  // fill(str, -n, '+-'), fill(str, 0, '+-'), fill(str, n, '+-')
  ASSERT_EQ("", strlib::fill("abcxyz", -2, "+-"));
  ASSERT_EQ("", strlib::fill("abcxyz", -1, "+-"));
  ASSERT_EQ("", strlib::fill("abcxyz", 0, "+-"));
  ASSERT_EQ("a", strlib::fill("abcxyz", 1, "+-"));
  ASSERT_EQ("ab", strlib::fill("abcxyz", 2, "+-"));
  ASSERT_EQ("abc", strlib::fill("abcxyz", 3, "+-"));
  ASSERT_EQ("a...", strlib::fill("abcxyz", 4, "+-"));   // ellipsis
  ASSERT_EQ("ab...", strlib::fill("abcxyz", 5, "+-"));  // ellipsis
  ASSERT_EQ("abcxyz", strlib::fill("abcxyz", 6, "+-"));
  ASSERT_EQ("abcxyz+", strlib::fill("abcxyz", 7, "+-"));
  ASSERT_EQ("abcxyz+-", strlib::fill("abcxyz", 8, "+-"));
  ASSERT_EQ("abcxyz+-+", strlib::fill("abcxyz", 9, "+-"));

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

  // capitalize(empty)
  ASSERT_EQ("", strlib::capitalize(""));

  ASSERT_EQ(" ", strlib::capitalize(" "));
  ASSERT_EQ("  ", strlib::capitalize("  "));

  ASSERT_EQ("A", strlib::capitalize("a"));
  ASSERT_EQ("Ab", strlib::capitalize("ab"));
  ASSERT_EQ("Abc", strlib::capitalize("abc"));
  ASSERT_EQ("Abcd", strlib::capitalize("abcd"));
  
  // ForceRest=default
  ASSERT_EQ("AB", strlib::capitalize("aB"));
  ASSERT_EQ("ABc", strlib::capitalize("aBc"));
  ASSERT_EQ("ABcd", strlib::capitalize("aBcd"));

  ASSERT_EQ("Hello world!", strlib::capitalize("Hello world!"));
  ASSERT_EQ("Hello world!", strlib::capitalize("hello world!"));
    
  // ForceRest=false  
  ASSERT_EQ("AB", strlib::capitalize("aB", false));
  ASSERT_EQ("ABc", strlib::capitalize("aBc", false));
  ASSERT_EQ("ABcd", strlib::capitalize("aBcd", false));

  ASSERT_EQ("Hello world!", strlib::capitalize("Hello world!", false));
  ASSERT_EQ("Hello world!", strlib::capitalize("hello world!", false));
  
  // ForceRest=true
  ASSERT_EQ("Ab", strlib::capitalize("aB", true));
  ASSERT_EQ("Abc", strlib::capitalize("aBc", true));
  ASSERT_EQ("Abcd", strlib::capitalize("aBcd", true));

  ASSERT_EQ("Hello world!", strlib::capitalize("Hello world!", true));
  ASSERT_EQ("Hello world!", strlib::capitalize("hello world!", true));
  
  //
  ASSERT_EQ("Hello world!", strlib::capitalize("Hello World!", true));
  ASSERT_EQ("Hello world!", strlib::capitalize("hello World!", true));
  
}

TEST(decapitalize) {

  // decapitalize(empty)
  ASSERT_EQ("", strlib::decapitalize(""));

  ASSERT_EQ(" ", strlib::decapitalize(" "));
  ASSERT_EQ("  ", strlib::decapitalize("  "));

  ASSERT_EQ("a", strlib::decapitalize("A"));
  ASSERT_EQ("ab", strlib::decapitalize("Ab"));
  ASSERT_EQ("abc", strlib::decapitalize("Abc"));
  ASSERT_EQ("abcd", strlib::decapitalize("Abcd"));

  // ForceRest=default
  ASSERT_EQ("ab", strlib::decapitalize("Ab"));
  ASSERT_EQ("abC", strlib::decapitalize("AbC"));
  ASSERT_EQ("abCd", strlib::decapitalize("AbCd"));

  ASSERT_EQ("hello world!", strlib::decapitalize("hello world!"));
  ASSERT_EQ("hello world!", strlib::decapitalize("Hello world!"));

  // ForceRest=false
  ASSERT_EQ("ab", strlib::decapitalize("Ab", false));
  ASSERT_EQ("abC", strlib::decapitalize("AbC", false));
  ASSERT_EQ("abCd", strlib::decapitalize("AbCd", false));

  ASSERT_EQ("hello world!", strlib::decapitalize("hello world!", false));
  ASSERT_EQ("hello world!", strlib::decapitalize("Hello world!", false));

  // ForceRest=true
  ASSERT_EQ("aB", strlib::decapitalize("Ab", true));
  ASSERT_EQ("aBC", strlib::decapitalize("AbC", true));
  ASSERT_EQ("aBCD", strlib::decapitalize("AbCd", true));

  //
  ASSERT_EQ("hELLO WORLD!", strlib::decapitalize("hello world!", true));
  ASSERT_EQ("hELLO WORLD!", strlib::decapitalize("Hello world!", true));  

}

TEST(upper) {
  // upper(empty)
  ASSERT_EQ("", strlib::upper(""));

  // upper(blank)
  ASSERT_EQ(" ", strlib::upper(" "));
  ASSERT_EQ("  ", strlib::upper("  "));

  ASSERT_EQ("0123456789.,:!?", strlib::upper("0123456789.,:!?"));

  // upper(value)
  ASSERT_EQ("A", strlib::upper("A"));
  ASSERT_EQ("AB", strlib::upper("AB"));
  ASSERT_EQ("ABC", strlib::upper("ABC"));
  ASSERT_EQ("ABCD", strlib::upper("ABCD"));

  ASSERT_EQ("A", strlib::upper("a"));
  ASSERT_EQ("AB", strlib::upper("ab"));
  ASSERT_EQ("ABC", strlib::upper("abc"));
  ASSERT_EQ("ABCD", strlib::upper("abcd"));

  ASSERT_EQ("AB", strlib::upper("aB"));
  ASSERT_EQ("ABC", strlib::upper("aBc"));
  ASSERT_EQ("ABCD", strlib::upper("aBcD"));

}

TEST(lower) {

  // lower(empty)
  ASSERT_EQ("", strlib::lower(""));

  // lower(blank)
  ASSERT_EQ(" ", strlib::lower(" "));
  ASSERT_EQ("  ", strlib::lower("  "));

  ASSERT_EQ("0123456789.,:!?", strlib::lower("0123456789.,:!?"));

  // lower(value)
  ASSERT_EQ("a", strlib::lower("a"));
  ASSERT_EQ("ab", strlib::lower("ab"));
  ASSERT_EQ("abc", strlib::lower("abc"));
  ASSERT_EQ("abcd", strlib::lower("abcd"));

  ASSERT_EQ("a", strlib::lower("A"));
  ASSERT_EQ("ab", strlib::lower("AB"));
  ASSERT_EQ("abc", strlib::lower("ABC"));
  ASSERT_EQ("abcd", strlib::lower("ABCD"));

  ASSERT_EQ("ab", strlib::lower("Ab"));
  ASSERT_EQ("abc", strlib::lower("AbC"));
  ASSERT_EQ("abcd", strlib::lower("AbCd"));

}

TEST(toUpperCase) {

  // toUpperCase(empty)
  ASSERT_EQ("", strlib::toUpperCase(""));

  // toUpperCase(blank)
  ASSERT_EQ(" ", strlib::toUpperCase(" "));
  ASSERT_EQ("  ", strlib::toUpperCase("  "));

  ASSERT_EQ("0123456789.,:!?", strlib::toUpperCase("0123456789.,:!?"));

  // toUpperCase(value)
  ASSERT_EQ("A", strlib::toUpperCase("A"));
  ASSERT_EQ("AB", strlib::toUpperCase("AB"));
  ASSERT_EQ("ABC", strlib::toUpperCase("ABC"));
  ASSERT_EQ("ABCD", strlib::toUpperCase("ABCD"));

  ASSERT_EQ("A", strlib::toUpperCase("a"));
  ASSERT_EQ("AB", strlib::toUpperCase("ab"));
  ASSERT_EQ("ABC", strlib::toUpperCase("abc"));
  ASSERT_EQ("ABCD", strlib::toUpperCase("abcd"));

  ASSERT_EQ("AB", strlib::toUpperCase("aB"));
  ASSERT_EQ("ABC", strlib::toUpperCase("aBc"));
  ASSERT_EQ("ABCD", strlib::toUpperCase("aBcD"));

}

TEST(toLowerCase) {

  // toLowerCase(empty)
  ASSERT_EQ("", strlib::toLowerCase(""));

  // toLowerCase(blank)
  ASSERT_EQ(" ", strlib::toLowerCase(" "));
  ASSERT_EQ("  ", strlib::toLowerCase("  "));

  ASSERT_EQ("0123456789.,:!?", strlib::toLowerCase("0123456789.,:!?"));

  // toLowerCase(value)
  ASSERT_EQ("a", strlib::toLowerCase("a"));
  ASSERT_EQ("ab", strlib::toLowerCase("ab"));
  ASSERT_EQ("abc", strlib::toLowerCase("abc"));
  ASSERT_EQ("abcd", strlib::toLowerCase("abcd"));

  ASSERT_EQ("a", strlib::toLowerCase("A"));
  ASSERT_EQ("ab", strlib::toLowerCase("AB"));
  ASSERT_EQ("abc", strlib::toLowerCase("ABC"));
  ASSERT_EQ("abcd", strlib::toLowerCase("ABCD"));

  ASSERT_EQ("ab", strlib::toLowerCase("Ab"));
  ASSERT_EQ("abc", strlib::toLowerCase("AbC"));
  ASSERT_EQ("abcd", strlib::toLowerCase("AbCd"));

}

TEST(toCase) {
  
  //// Upper ////

  // toCase(empty, true)
  ASSERT_EQ("", strlib::toCase("", true));

  // toCase(blank, true)
  ASSERT_EQ(" ", strlib::toCase(" ", true));
  ASSERT_EQ("  ", strlib::toCase("  ", true));

  ASSERT_EQ("0123456789.,:!?", strlib::toCase("0123456789.,:!?", true));

  // toCase(value, true)
  ASSERT_EQ("A", strlib::toCase("A", true));
  ASSERT_EQ("AB", strlib::toCase("AB", true));
  ASSERT_EQ("ABC", strlib::toCase("ABC", true));
  ASSERT_EQ("ABCD", strlib::toCase("ABCD", true));

  ASSERT_EQ("A", strlib::toCase("a", true));
  ASSERT_EQ("AB", strlib::toCase("ab", true));
  ASSERT_EQ("ABC", strlib::toCase("abc", true));
  ASSERT_EQ("ABCD", strlib::toCase("abcd", true));

  ASSERT_EQ("AB", strlib::toCase("aB", true));
  ASSERT_EQ("ABC", strlib::toCase("aBc", true));
  ASSERT_EQ("ABCD", strlib::toCase("aBcD", true));

  //// Lower ////

  // toCase(empty, false)
  ASSERT_EQ("", strlib::toCase("", false));

  // toCase(blank, false)
  ASSERT_EQ(" ", strlib::toCase(" ", false));
  ASSERT_EQ("  ", strlib::toCase("  ", false));

  ASSERT_EQ("0123456789.,:!?", strlib::toCase("0123456789.,:!?", false));

  // toCase(value, false)
  ASSERT_EQ("a", strlib::toCase("a", false));
  ASSERT_EQ("ab", strlib::toCase("ab", false));
  ASSERT_EQ("abc", strlib::toCase("abc", false));
  ASSERT_EQ("abcd", strlib::toCase("abcd", false));

  ASSERT_EQ("a", strlib::toCase("A", false));
  ASSERT_EQ("ab", strlib::toCase("AB", false));
  ASSERT_EQ("abc", strlib::toCase("ABC", false));
  ASSERT_EQ("abcd", strlib::toCase("ABCD", false));

  ASSERT_EQ("ab", strlib::toCase("Ab", false));
  ASSERT_EQ("abc", strlib::toCase("AbC", false));
  ASSERT_EQ("abcd", strlib::toCase("AbCd", false));

}

////

TEST(getCaseCode) {

  // getCaseCode(empty)
  ASSERT_EQ(strlib::CT_NONE, strlib::getCaseCode(""));
  ASSERT_EQ(strlib::CT_NONE, strlib::getCaseCode(" "));
  ASSERT_EQ(strlib::CT_NONE, strlib::getCaseCode(" "));

  // getCaseCode(unknown)
  ASSERT_EQ(strlib::CT_NONE, strlib::getCaseCode("blahblahblah"));
  ASSERT_EQ(strlib::CT_NONE, strlib::getCaseCode("0123456789"));

  // lowercase
  ASSERT_EQ(strlib::CT_lowercase, strlib::getCaseCode("lower"));

  // UPPERCASE
  ASSERT_EQ(strlib::CT_UPPERCASE, strlib::getCaseCode("upper"));

  // camelCase
  ASSERT_EQ(strlib::CT_camelCase, strlib::getCaseCode("camel"));

  // PascalCase
  ASSERT_EQ(strlib::CT_PascalCase, strlib::getCaseCode("Camel"));
  ASSERT_EQ(strlib::CT_PascalCase, strlib::getCaseCode("Pascal"));
  ASSERT_EQ(strlib::CT_PascalCase, strlib::getCaseCode("pascal"));

  // snake_case
  ASSERT_EQ(strlib::CT_snake_case, strlib::getCaseCode("snake"));

  // SNAKE_CASE
  ASSERT_EQ(strlib::CT_SNAKE_CASE, strlib::getCaseCode("SNAKE"));
  ASSERT_EQ(strlib::CT_SNAKE_CASE, strlib::getCaseCode("MACRO"));
  ASSERT_EQ(strlib::CT_SNAKE_CASE, strlib::getCaseCode("macro"));

  // kebab-case
  ASSERT_EQ(strlib::CT_kebab_case, strlib::getCaseCode("kebab"));
  ASSERT_EQ(strlib::CT_kebab_case, strlib::getCaseCode("dash"));
  ASSERT_EQ(strlib::CT_kebab_case, strlib::getCaseCode("train"));
  ASSERT_EQ(strlib::CT_kebab_case, strlib::getCaseCode("lisp"));

  // KEBAB-CASE
  ASSERT_EQ(strlib::CT_KEBAB_CASE, strlib::getCaseCode("KEBAB"));
  ASSERT_EQ(strlib::CT_KEBAB_CASE, strlib::getCaseCode("DASH"));
  ASSERT_EQ(strlib::CT_KEBAB_CASE, strlib::getCaseCode("TRAIN"));
  ASSERT_EQ(strlib::CT_KEBAB_CASE, strlib::getCaseCode("COBOL"));
  ASSERT_EQ(strlib::CT_KEBAB_CASE, strlib::getCaseCode("cobol"));

  // CT_Kebab_Case
  ASSERT_EQ(strlib::CT_Kebab_Case, strlib::getCaseCode("Kebab"));
  ASSERT_EQ(strlib::CT_Kebab_Case, strlib::getCaseCode("Dash"));
  ASSERT_EQ(strlib::CT_Kebab_Case, strlib::getCaseCode("Train"));

}

////

TEST(toCamelCase) {

  // toCamelCase(empty)
  ASSERT_EQ("", strlib::toCamelCase(""));
  ASSERT_EQ(" ", strlib::toCamelCase(" "));
  ASSERT_EQ("  ", strlib::toCamelCase("  "));

  // capitalize = default
  ASSERT_EQ("ProductName", strlib::toCamelCase("product name"));
  ASSERT_EQ("ProductName", strlib::toCamelCase("product-name"));
  ASSERT_EQ("ProductName", strlib::toCamelCase("product_name"));
  ASSERT_EQ("ProductName", strlib::toCamelCase("productName"));
  
  ASSERT_EQ("ProductName", strlib::toCamelCase("Product Name"));
  ASSERT_EQ("ProductName", strlib::toCamelCase("Product-Name"));
  ASSERT_EQ("ProductName", strlib::toCamelCase("Product_Name"));
  ASSERT_EQ("ProductName", strlib::toCamelCase("ProductName"));
  
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product full name"));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-full-name"));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product_full_name"));
  
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product Full name"));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-Full-name"));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product_Full_name"));
  
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-_ full -_name"));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-_ Full -_name"));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-_ FullName"));

  // capitalize = true
  ASSERT_EQ("ProductName", strlib::toCamelCase("product name", true));
  ASSERT_EQ("ProductName", strlib::toCamelCase("product-name", true));
  ASSERT_EQ("ProductName", strlib::toCamelCase("product_name", true));
  ASSERT_EQ("ProductName", strlib::toCamelCase("productName", true));
  
  ASSERT_EQ("ProductName", strlib::toCamelCase("Product Name", true));
  ASSERT_EQ("ProductName", strlib::toCamelCase("Product-Name", true));
  ASSERT_EQ("ProductName", strlib::toCamelCase("Product_Name", true));
  ASSERT_EQ("ProductName", strlib::toCamelCase("ProductName", true));
  
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product full name", true));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-full-name", true));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product_full_name", true));
  
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product Full name", true));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-Full-name", true));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product_Full_name", true));
  
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-_ full -_name", true));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-_ Full -_name", true));
  ASSERT_EQ("ProductFullName", strlib::toCamelCase("product-_ FullName", true));

  // capitalize = false
  ASSERT_EQ("productName", strlib::toCamelCase("product name", false));
  ASSERT_EQ("productName", strlib::toCamelCase("product-name", false));
  ASSERT_EQ("productName", strlib::toCamelCase("product_name", false));
  ASSERT_EQ("productName", strlib::toCamelCase("productName", false));
  
  ASSERT_EQ("productName", strlib::toCamelCase("Product Name", false));
  ASSERT_EQ("productName", strlib::toCamelCase("Product-Name", false));
  ASSERT_EQ("productName", strlib::toCamelCase("Product_Name", false));
  ASSERT_EQ("productName", strlib::toCamelCase("ProductName", false));
  
  ASSERT_EQ("productFullName", strlib::toCamelCase("product full name", false));
  ASSERT_EQ("productFullName", strlib::toCamelCase("product-full-name", false));
  ASSERT_EQ("productFullName", strlib::toCamelCase("product_full_name", false));
  
  ASSERT_EQ("productFullName", strlib::toCamelCase("product Full name", false));
  ASSERT_EQ("productFullName", strlib::toCamelCase("product-Full-name", false));
  ASSERT_EQ("productFullName", strlib::toCamelCase("product_Full_name", false));
  
  ASSERT_EQ("productFullName", strlib::toCamelCase("product-_ full -_name", false));
  ASSERT_EQ("productFullName", strlib::toCamelCase("product-_ Full -_name", false));
  ASSERT_EQ("productFullName", strlib::toCamelCase("product-_ FullName", false));

}

TEST(toSnakeCase) {

  // toSnakeCase(empty)
  ASSERT_EQ("", strlib::toSnakeCase(""));
  ASSERT_EQ(" ", strlib::toSnakeCase(" "));
  ASSERT_EQ("  ", strlib::toSnakeCase("  "));

  // toSnakeCase(value)  
  ASSERT_EQ("product_name", strlib::toSnakeCase("product name"));
  ASSERT_EQ("product_name", strlib::toSnakeCase("product-name"));
  ASSERT_EQ("product_name", strlib::toSnakeCase("product_name"));  
  ASSERT_EQ("product_name", strlib::toSnakeCase("ProductName"));

  // upper = false
  ASSERT_EQ("product_name", strlib::toSnakeCase("product name", false));
  ASSERT_EQ("product_name", strlib::toSnakeCase("product-name", false));
  ASSERT_EQ("product_name", strlib::toSnakeCase("product_name", false));  
  ASSERT_EQ("product_name", strlib::toSnakeCase("ProductName", false));

  // upper = true
  ASSERT_EQ("PRODUCT_NAME", strlib::toSnakeCase("product name", true));
  ASSERT_EQ("PRODUCT_NAME", strlib::toSnakeCase("product-name", true));
  ASSERT_EQ("PRODUCT_NAME", strlib::toSnakeCase("product_name", true));  
  ASSERT_EQ("PRODUCT_NAME", strlib::toSnakeCase("ProductName", true));

}

TEST(toKebabCase) {

  // toKebabCase(empty)
  ASSERT_EQ("", strlib::toKebabCase(""));
  ASSERT_EQ(" ", strlib::toKebabCase(" "));
  ASSERT_EQ("  ", strlib::toKebabCase("  "));

  // toKebabCase(value)  
  ASSERT_EQ("product-name", strlib::toKebabCase("product name"));
  ASSERT_EQ("product-name", strlib::toKebabCase("product-name"));
  ASSERT_EQ("product-name", strlib::toKebabCase("product_name"));  
  ASSERT_EQ("product-name", strlib::toKebabCase("ProductName"));
  
  // upper = false
  ASSERT_EQ("product-name", strlib::toKebabCase("product name", false));
  ASSERT_EQ("product-name", strlib::toKebabCase("product-name", false));
  ASSERT_EQ("product-name", strlib::toKebabCase("product_name", false));  
  ASSERT_EQ("product-name", strlib::toKebabCase("ProductName", false));

  // upper = true
  ASSERT_EQ("PRODUCT-NAME", strlib::toKebabCase("product name", true));
  ASSERT_EQ("PRODUCT-NAME", strlib::toKebabCase("product-name", true));
  ASSERT_EQ("PRODUCT-NAME", strlib::toKebabCase("product_name", true));  
  ASSERT_EQ("PRODUCT-NAME", strlib::toKebabCase("ProductName", true));

}

TEST(reverse) {

  // reverse(empty)
  ASSERT_EQ("", strlib::reverse(""));

  // reverse(blank)
  ASSERT_EQ(" ", strlib::reverse(" "));
  ASSERT_EQ("  ", strlib::reverse("  "));

  // reverse(value)
  ASSERT_EQ("*", strlib::reverse("*"));
  ASSERT_EQ("**", strlib::reverse("**"));
  ASSERT_EQ("***", strlib::reverse("***"));
  ASSERT_EQ("****", strlib::reverse("****"));
  ASSERT_EQ("*****", strlib::reverse("*****"));
  ASSERT_EQ("******", strlib::reverse("******"));

  ASSERT_EQ("a", strlib::reverse("a"));
  ASSERT_EQ("ba", strlib::reverse("ab"));
  ASSERT_EQ("cba", strlib::reverse("abc"));
  ASSERT_EQ("dcba", strlib::reverse("abcd"));
  ASSERT_EQ("edcba", strlib::reverse("abcde"));
  ASSERT_EQ("fedcba", strlib::reverse("abcdef"));

}

// 4.1

TEST (startsWith) {

  // startsWith(empty, value)
  ASSERT_FALSE(strlib::startsWith("", ""));      // important
  ASSERT_FALSE(strlib::startsWith("", " "));
  ASSERT_FALSE(strlib::startsWith("", "abc"));

  // startsWith(blank, value)
  ASSERT_FALSE(strlib::startsWith(" ", ""));     // important
  ASSERT_TRUE(strlib::startsWith(" ", " "));     // True
  ASSERT_FALSE(strlib::startsWith(" ", "abc"));

  // startsWith(value, value)

  // False
  ASSERT_FALSE(strlib::startsWith("abc", ""));   // important
  ASSERT_FALSE(strlib::startsWith("abc", " "));
  ASSERT_FALSE(strlib::startsWith("abc", "xyz"));

  // True
  ASSERT_TRUE(strlib::startsWith("abc", "a"));
  ASSERT_TRUE(strlib::startsWith("abc", "ab"));
  ASSERT_TRUE(strlib::startsWith("abc", "abc"));

  ////
  ASSERT_TRUE(strlib::startsWith("myfile.txt", "my"));
  ASSERT_TRUE(strlib::startsWith("myfile.txt", "myfile"));
  ASSERT_TRUE(strlib::startsWith("myfile.txt", "myfile.txt"));
  
}

TEST (startsWithIgnoreCase) {

  // startsWithIgnoreCase(empty, value)
  ASSERT_FALSE(strlib::startsWithIgnoreCase("", ""));      // important
  ASSERT_FALSE(strlib::startsWithIgnoreCase("", " "));
  ASSERT_FALSE(strlib::startsWithIgnoreCase("", "abc"));

  // startsWithIgnoreCase(blank, value)
  ASSERT_FALSE(strlib::startsWithIgnoreCase(" ", ""));     // important
  ASSERT_TRUE(strlib::startsWithIgnoreCase(" ", " "));     // True
  ASSERT_FALSE(strlib::startsWithIgnoreCase(" ", "abc"));

  // startsWithIgnoreCase(value, value)

  // False
  ASSERT_FALSE(strlib::startsWithIgnoreCase("abc", ""));   // important
  ASSERT_FALSE(strlib::startsWithIgnoreCase("abc", " "));
  ASSERT_FALSE(strlib::startsWithIgnoreCase("abc", "xyz"));

  // True
  ASSERT_TRUE(strlib::startsWithIgnoreCase("abc", "a"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("abc", "ab"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("abc", "abc"));

  // True - IgnoreCase
  ASSERT_TRUE(strlib::startsWithIgnoreCase("abc", "A"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("abc", "Ab"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("abc", "AbC"));

  ////
  ASSERT_TRUE(strlib::startsWithIgnoreCase("myfile.txt", "my"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("myfile.txt", "myfile"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("myfile.txt", "myfile.txt"));

  ASSERT_TRUE(strlib::startsWithIgnoreCase("myfile.txt", "My"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("myfile.txt", "MyFile"));
  ASSERT_TRUE(strlib::startsWithIgnoreCase("myfile.txt", "MyFile.TxT"));

}

TEST (endsWith) {

  // endsWith(empty, value)
  ASSERT_FALSE(strlib::endsWith("", ""));       // important
  ASSERT_FALSE(strlib::endsWith("", " "));
  ASSERT_FALSE(strlib::endsWith("", "abc"));

  // endsWith(blank, value)
  ASSERT_FALSE(strlib::endsWith(" ", ""));      // important
  ASSERT_TRUE(strlib::endsWith(" ", " "));      // True
  ASSERT_FALSE(strlib::endsWith(" ", "abc"));

  // endsWith(value, value)

  // False
  ASSERT_FALSE(strlib::endsWith("abc", ""));    // important
  ASSERT_FALSE(strlib::endsWith("abc", " "));
  ASSERT_FALSE(strlib::endsWith("abc", "xyz"));

  // True
  ASSERT_TRUE(strlib::endsWith("abc", "c"));
  ASSERT_TRUE(strlib::endsWith("abc", "bc"));
  ASSERT_TRUE(strlib::endsWith("abc", "abc"));

  ////
  ASSERT_TRUE(strlib::endsWith("myfile.txt", "txt"));
  ASSERT_TRUE(strlib::endsWith("myfile.txt", ".txt"));
  ASSERT_TRUE(strlib::endsWith("myfile.txt", "myfile.txt"));

}

TEST (endsWithIgnoreCase) {

  // endsWithIgnoreCase(empty, value)
  ASSERT_FALSE(strlib::endsWithIgnoreCase("", ""));       // important
  ASSERT_FALSE(strlib::endsWithIgnoreCase("", " "));
  ASSERT_FALSE(strlib::endsWithIgnoreCase("", "abc"));

  // endsWithIgnoreCase(blank, value)
  ASSERT_FALSE(strlib::endsWithIgnoreCase(" ", ""));      // important
  ASSERT_TRUE(strlib::endsWithIgnoreCase(" ", " "));      // True
  ASSERT_FALSE(strlib::endsWithIgnoreCase(" ", "abc"));

  // endsWithIgnoreCase(value, value)

  // False
  ASSERT_FALSE(strlib::endsWithIgnoreCase("abc", ""));    // important
  ASSERT_FALSE(strlib::endsWithIgnoreCase("abc", " "));
  ASSERT_FALSE(strlib::endsWithIgnoreCase("abc", "xyz"));

  // True
  ASSERT_TRUE(strlib::endsWithIgnoreCase("abc", "c"));
  ASSERT_TRUE(strlib::endsWithIgnoreCase("abc", "bc"));
  ASSERT_TRUE(strlib::endsWithIgnoreCase("abc", "abc"));

  // True - IgnoreCase
  ASSERT_TRUE(strlib::endsWithIgnoreCase("abc", "C"));
  ASSERT_TRUE(strlib::endsWithIgnoreCase("abc", "Bc"));
  ASSERT_TRUE(strlib::endsWithIgnoreCase("abc", "aBc"));

  ////
  ASSERT_TRUE(strlib::endsWithIgnoreCase("myfile.txt", "TxT"));
  ASSERT_TRUE(strlib::endsWithIgnoreCase("myfile.txt", ".TxT"));
  ASSERT_TRUE(strlib::endsWithIgnoreCase("myfile.txt", "MyFile.TxT"));

}

TEST(hasPrefix) {

  // hasPrefix(empty, value)
  ASSERT_FALSE(strlib::hasPrefix("", ""));      // important
  ASSERT_FALSE(strlib::hasPrefix("", " "));
  ASSERT_FALSE(strlib::hasPrefix("", "abc"));

  // hasPrefix(blank, value)
  ASSERT_FALSE(strlib::hasPrefix(" ", ""));     // important
  ASSERT_TRUE(strlib::hasPrefix(" ", " "));     // True
  ASSERT_FALSE(strlib::hasPrefix(" ", "abc"));

  // hasPrefix(value, value)

  // False
  ASSERT_FALSE(strlib::hasPrefix("abc", ""));   // important
  ASSERT_FALSE(strlib::hasPrefix("abc", " "));
  ASSERT_FALSE(strlib::hasPrefix("abc", "xyz"));

  // True
  ASSERT_TRUE(strlib::hasPrefix("abc", "a"));
  ASSERT_TRUE(strlib::hasPrefix("abc", "ab"));
  ASSERT_TRUE(strlib::hasPrefix("abc", "abc"));

  ////
  ASSERT_TRUE(strlib::hasPrefix("myfile.txt", "my"));
  ASSERT_TRUE(strlib::hasPrefix("myfile.txt", "myfile"));
  ASSERT_TRUE(strlib::hasPrefix("myfile.txt", "myfile.txt"));
  
}

TEST(hasSuffix) {

  // endsWith(empty, value)
  ASSERT_FALSE(strlib::hasSuffix("", ""));       // important
  ASSERT_FALSE(strlib::hasSuffix("", " "));
  ASSERT_FALSE(strlib::hasSuffix("", "abc"));

  // endsWith(blank, value)
  ASSERT_FALSE(strlib::hasSuffix(" ", ""));      // important
  ASSERT_TRUE(strlib::hasSuffix(" ", " "));      // True
  ASSERT_FALSE(strlib::hasSuffix(" ", "abc"));

  // endsWith(value, value)

  // False
  ASSERT_FALSE(strlib::hasSuffix("abc", ""));    // important
  ASSERT_FALSE(strlib::hasSuffix("abc", " "));
  ASSERT_FALSE(strlib::hasSuffix("abc", "xyz"));

  // True
  ASSERT_TRUE(strlib::hasSuffix("abc", "c"));
  ASSERT_TRUE(strlib::hasSuffix("abc", "bc"));
  ASSERT_TRUE(strlib::hasSuffix("abc", "abc"));

  ////
  ASSERT_TRUE(strlib::hasSuffix("myfile.txt", "txt"));
  ASSERT_TRUE(strlib::hasSuffix("myfile.txt", ".txt"));
  ASSERT_TRUE(strlib::hasSuffix("myfile.txt", "myfile.txt"));

}

// 4.2

TEST(removePrefix) {

  // removePrefix(empty, value)
  ASSERT_EQ("", strlib::removePrefix("", ""));
  ASSERT_EQ("", strlib::removePrefix("", " "));
  ASSERT_EQ("", strlib::removePrefix("", "abc"));

  // removePrefix(blank, value)
  ASSERT_EQ(" ", strlib::removePrefix(" ", ""));
  ASSERT_EQ("", strlib::removePrefix(" ", " "));   // true
  ASSERT_EQ(" ", strlib::removePrefix(" ", "abc"));

  // removePrefix(value, value)

  // False
  ASSERT_EQ("abc", strlib::removePrefix("abc", ""));
  ASSERT_EQ("abc", strlib::removePrefix("abc", " "));
  ASSERT_EQ("abc", strlib::removePrefix("abc", "xyz"));

  // True
  ASSERT_EQ("bc", strlib::removePrefix("abc", "a"));
  ASSERT_EQ("c", strlib::removePrefix("abc", "ab"));
  ASSERT_EQ("", strlib::removePrefix("abc", "abc"));

  ////
  ASSERT_EQ("file.txt", strlib::removePrefix("myfile.txt", "my"));
  ASSERT_EQ(".txt", strlib::removePrefix("myfile.txt", "myfile"));
  //ASSERT_EQ("txt", strlib::removePrefix("myfile.txt", "myfile."));
  ASSERT_EQ("", strlib::removePrefix("myfile.txt", "myfile.txt"));

}

TEST(removePrefixes) {

  // removePrefixes(empty, value)
  ASSERT_EQ("", strlib::removePrefixes("", (std::vector<std::string>){}));
  ASSERT_EQ("", strlib::removePrefixes("", (std::vector<std::string>){""}));
  ASSERT_EQ("", strlib::removePrefixes("", (std::vector<std::string>){" "}));
  ASSERT_EQ("", strlib::removePrefixes("", (std::vector<std::string>){"abc"}));

  // removePrefixes(blank, value)
  ASSERT_EQ(" ", strlib::removePrefixes(" ", (std::vector<std::string>){}));
  ASSERT_EQ(" ", strlib::removePrefixes(" ", (std::vector<std::string>){""}));
  ASSERT_EQ("", strlib::removePrefixes(" ", (std::vector<std::string>){" "}));   // true
  ASSERT_EQ(" ", strlib::removePrefixes(" ", (std::vector<std::string>){"abc"}));

  // removePrefixes(value, value)

  // False
  ASSERT_EQ("abc", strlib::removePrefixes("abc", (std::vector<std::string>){}));
  ASSERT_EQ("abc", strlib::removePrefixes("abc", (std::vector<std::string>){""}));
  ASSERT_EQ("abc", strlib::removePrefixes("abc", (std::vector<std::string>){" "}));
  ASSERT_EQ("abc", strlib::removePrefixes("abc", (std::vector<std::string>){"xyz"}));
  ASSERT_EQ("abc", strlib::removePrefixes("abc", (std::vector<std::string>){"xyz", "def"}));
  ASSERT_EQ("abc", strlib::removePrefixes("abc", (std::vector<std::string>){"def", "xyz"}));

  // True
  ASSERT_EQ("bc", strlib::removePrefixes("abc", (std::vector<std::string>){"a"}));
  ASSERT_EQ("c", strlib::removePrefixes("abc", (std::vector<std::string>){"ab"}));
  ASSERT_EQ("", strlib::removePrefixes("abc", (std::vector<std::string>){"abc"}));
  ASSERT_EQ("", strlib::removePrefixes("abc", (std::vector<std::string>){"abc", "xyz"}));
  ASSERT_EQ("", strlib::removePrefixes("abc", (std::vector<std::string>){"xyz", "abc"}));

  ASSERT_EQ("c", strlib::removePrefixes("abc", (std::vector<std::string>){"ab", "abc"})); // first 'ab'

  ////
  ASSERT_EQ("file.txt", strlib::removePrefixes("myfile.txt", (std::vector<std::string>){"my"}));
  ASSERT_EQ(".txt", strlib::removePrefixes("myfile.txt", (std::vector<std::string>){"myfile"}));
  ASSERT_EQ("", strlib::removePrefixes("myfile.txt", (std::vector<std::string>){"myfile.txt"}));

  ASSERT_EQ(".txt", strlib::removePrefixes("myfile.txt", (std::vector<std::string>){"myfile", "yourfile"}));

}

TEST(removeSuffix) {

  // removeSuffix((empty, value)
  ASSERT_EQ("", strlib::removeSuffix("", ""));
  ASSERT_EQ("", strlib::removeSuffix("", " "));
  ASSERT_EQ("", strlib::removeSuffix("", "abc"));

  // removeSuffix((blank, value)
  ASSERT_EQ(" ", strlib::removeSuffix(" ", ""));
  ASSERT_EQ("", strlib::removeSuffix(" ", " ")); // True
  ASSERT_EQ(" ", strlib::removeSuffix(" ", "abc"));

  // removeSuffix((value, value)

  // False
  ASSERT_EQ("abc", strlib::removeSuffix("abc", ""));
  ASSERT_EQ("abc", strlib::removeSuffix("abc", " "));
  ASSERT_EQ("abc", strlib::removeSuffix("abc", "xyz"));

  // True
  ASSERT_EQ("ab", strlib::removeSuffix("abc", "c"));
  ASSERT_EQ("a", strlib::removeSuffix("abc", "bc"));
  ASSERT_EQ("", strlib::removeSuffix("abc", "abc"));

  ////
  ASSERT_EQ("myfile.", strlib::removeSuffix("myfile.txt", "txt"));
  ASSERT_EQ("myfile", strlib::removeSuffix("myfile.txt", ".txt"));
  ASSERT_EQ("", strlib::removeSuffix("myfile.txt", "myfile.txt"));

}

TEST(removeSuffixes) {

  // removeSuffixes((empty, value)
  ASSERT_EQ("", strlib::removeSuffixes("", (std::vector<std::string>){}));
  ASSERT_EQ("", strlib::removeSuffixes("", (std::vector<std::string>){""}));
  ASSERT_EQ("", strlib::removeSuffixes("", (std::vector<std::string>){" "}));
  ASSERT_EQ("", strlib::removeSuffixes("", (std::vector<std::string>){"abc"}));

  // removeSuffixes((blank, value)
  ASSERT_EQ(" ", strlib::removeSuffixes(" ", (std::vector<std::string>){}));
  ASSERT_EQ(" ", strlib::removeSuffixes(" ", (std::vector<std::string>){""}));
  ASSERT_EQ("", strlib::removeSuffixes(" ", (std::vector<std::string>){" "})); // True
  ASSERT_EQ(" ", strlib::removeSuffixes(" ", (std::vector<std::string>){"abc"}));

  // removeSuffixes((value, value)

  // False
  ASSERT_EQ("abc", strlib::removeSuffixes("abc", (std::vector<std::string>){}));
  ASSERT_EQ("abc", strlib::removeSuffixes("abc", (std::vector<std::string>){""}));
  ASSERT_EQ("abc", strlib::removeSuffixes("abc", (std::vector<std::string>){" "}));
  ASSERT_EQ("abc", strlib::removeSuffixes("abc", (std::vector<std::string>){"xyz"}));
  ASSERT_EQ("abc", strlib::removeSuffixes("abc", (std::vector<std::string>){"xyz", "def"}));
  ASSERT_EQ("abc", strlib::removeSuffixes("abc", (std::vector<std::string>){"def", "xyz"}));

  // True
  ASSERT_EQ("ab", strlib::removeSuffixes("abc", (std::vector<std::string>){"c"}));
  ASSERT_EQ("a", strlib::removeSuffixes("abc", (std::vector<std::string>){"bc"}));
  ASSERT_EQ("", strlib::removeSuffixes("abc", (std::vector<std::string>){"abc"}));
  ASSERT_EQ("", strlib::removeSuffixes("abc", (std::vector<std::string>){"abc", "xyz"}));
  ASSERT_EQ("", strlib::removeSuffixes("abc", (std::vector<std::string>){"xyz", "abc"}));

  ASSERT_EQ("a", strlib::removeSuffixes("abc", (std::vector<std::string>){"bc", "abc"})); // first 'bc'

  ////
  ASSERT_EQ("myfile.", strlib::removeSuffixes("myfile.txt", (std::vector<std::string>){"txt"}));
  ASSERT_EQ("myfile", strlib::removeSuffixes("myfile.txt", (std::vector<std::string>){".txt"}));
  ASSERT_EQ("", strlib::removeSuffixes("myfile.txt", (std::vector<std::string>){"myfile.txt"}));

  ASSERT_EQ("myfile", strlib::removeSuffixes("myfile.txt", (std::vector<std::string>){".txt", ".csv"}));

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

TEST(splitLines) {

  // splitLines(empty)
  ASSERT_TRUE(((VS) {} ==strlib::splitLines("")));

  // splitLines(blank)
  ASSERT_TRUE(((VS) {" "} ==strlib::splitLines(" ")));

  // splitLines(value)
  ASSERT_TRUE(((VS) {"Hello"} == strlib::splitLines("Hello")));

  // split=False
  ASSERT_TRUE(( (VS) {"Hello world! It is good world!"} == strlib::splitLines("Hello world! It is good world!")));
  ASSERT_TRUE(((VS) {"Hello world!\tIt is good world!"} == strlib::splitLines("Hello world!\tIt is good world!")));

  // split=True
  ASSERT_TRUE(((VS) {"Hello world!", "It is good world!"} == strlib::splitLines("Hello world!\rIt is good world!")));
  ASSERT_TRUE(((VS) {"Hello world!", "It is good world!"} == strlib::splitLines("Hello world!\nIt is good world!")));
  ASSERT_TRUE(((VS) {"Hello world!", "It is good world!"} == strlib::splitLines("Hello world!\r\nIt is good world!")));

  ////

  ASSERT_TRUE(((VS) {"Hello world!", "It is good world!"} == strlib::splitLines("Hello world!\fIt is good world!")));
  ASSERT_TRUE(((VS) {"Hello world!", "It is good world!"} == strlib::splitLines("Hello world!\vIt is good world!")));

  // IMPORTANT !!!
  ASSERT_TRUE(((VS) {"Hello world!", "", "It is good world!"} == strlib::splitLines("Hello world!\n\rIt is good world!")));
  ASSERT_TRUE(((VS) {"Hello world!", "", "It is good world!"} == strlib::splitLines("Hello world!\n\nIt is good world!")));
  ASSERT_TRUE(((VS) {"Hello world!", "", "It is good world!"} == strlib::splitLines("Hello world!\r\rIt is good world!")));

  ASSERT_TRUE(((VS) {"Hello world!", "", "It is good world!"} == strlib::splitLines("Hello world!\n\r\nIt is good world!")));
  ASSERT_TRUE(((VS) {"Hello world!", "", "", "It is good world!"} == strlib::splitLines("Hello world!\n\n\rIt is good world!")));

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

  SET_TEST(isEmpty_char);
  SET_TEST(isEmpty_string);

  SET_TEST(isBlank);

  SET_TEST(size_char);
  SET_TEST(size_string);

  SET_TEST(equals_char);
  SET_TEST(equals_string);
  SET_TEST(equalsContent_char);

  SET_TEST(normalize);
  SET_TEST(normalizeBlank)
  SET_TEST(defautIfEmpty)
  SET_TEST(trim)
  SET_TEST(trimAll)
  SET_TEST(trimSpace)
  SET_TEST(ltrim)
  SET_TEST(rtrim)

  SET_TEST(contains_char)
  SET_TEST(contains_string)

  SET_TEST(findFirstOf);
  SET_TEST(findLastOf);
  SET_TEST(findFirstNotOf);
  SET_TEST(findLastNotOf);

  SET_TEST(replicate_char);
  SET_TEST(replicate_char_as_string);
  SET_TEST(replicate_string);

  SET_TEST(lpad_char);
  SET_TEST(lpad_char_as_string);
  SET_TEST(lpad_string);

  SET_TEST(rpad_char);
  SET_TEST(rpad_char_as_string);
  SET_TEST(rpad_string);

  SET_TEST(fill_char);
  SET_TEST(fill_char_as_string);
  SET_TEST(fill_string);
  
  SET_TEST(ellipsis);
  SET_TEST(trunc);  
  SET_TEST(left)
  SET_TEST(right)

  SET_TEST(capitalize);
  SET_TEST(decapitalize);  
  SET_TEST(upper);
  SET_TEST(lower);
  SET_TEST(toUpperCase);
  SET_TEST(toLowerCase);
  SET_TEST(toCase);

  SET_TEST(getCaseCode);

  SET_TEST(toCamelCase);
  SET_TEST(toSnakeCase);
  SET_TEST(toKebabCase);
  SET_TEST(reverse);

  SET_TEST(startsWith);
  SET_TEST(startsWithIgnoreCase);
  SET_TEST(endsWith);
  SET_TEST(endsWithIgnoreCase);
  SET_TEST(hasPrefix);
  SET_TEST(hasSuffix);
  SET_TEST(removePrefix);
  SET_TEST(removePrefixes);
  SET_TEST(removeSuffix);
  SET_TEST(removeSuffixes);

  SET_TEST(countChars);
  SET_TEST(countStrings);
  SET_TEST(countWords);
  SET_TEST(replaceAll);
  SET_TEST(split);
  SET_TEST(splitBySeparator);
  SET_TEST(splitBySeparators);
  SET_TEST(splitWords);
  SET_TEST(splitLines);

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
