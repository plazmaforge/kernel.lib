#include <string>
#include <iostream>

#include "plazma/lib/str/strlib.h" 
#include "test_helper.h"

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
  //ASSERT_EQ("", strlib::trim(" \t\n\r\f\v"));
  //ASSERT_EQ("abc", strlib::trim(" \t\n\r\f\vabc"));
  //ASSERT_EQ("abc", strlib::trim("abc \t\n\r\f\v"))
  //ASSERT_EQ("abc", strlib::trim(" \t\n\r\f\vabc \t\n\r\f\v"));

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
  //ASSERT_EQ("", strlib::ltrim(" \t\n\r\f\v"));
  //ASSERT_EQ("abc", strlib::ltrim(" \t\n\r\f\vabc"));
  //ASSERT_EQ("abc", strlib::ltrim("abc \t\n\r\f\v"))
  //ASSERT_EQ("abc", strlib::ltrim(" \t\n\r\f\vabc \t\n\r\f\v"));
  
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
  //ASSERT_EQ("", strlib::rtrim(" \t\n\r\f\v"));
  //ASSERT_EQ(" \t\n\r\f\vabc", strlib::rtrim(" \t\n\r\f\vabc"));
  //ASSERT_EQ("abc", strlib::rtrim("abc \t\n\r\f\v"))
  //ASSERT_EQ(" \t\n\r\f\vabc", strlib::rtrim(" \t\n\r\f\vabc \t\n\r\f\v"));
  
}

// 1.4

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

// 2.2

TEST(lpad) {

  ASSERT_EQ("****Volume", strlib::lpad("Volume", 10, '*'));

}

TEST(rpad) {

  ASSERT_EQ("Volume****", strlib::rpad("Volume", 10, '*'));

}

// 2.3

TEST(fill) {

  ASSERT_EQ("Volume****", strlib::fill("Volume", 10, '*'));
  ASSERT_EQ("Volume**", strlib::fill("Volume", 8, '*'));

}

TEST(ellipsis) {

  ASSERT_EQ("Transla...", strlib::ellipsis("Translation", 10));

}

TEST(trunc) {

  ASSERT_EQ("Abcdef", strlib::trunc("Abcdef123456789", 6));

}

// left
// right

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
  ASSERT_EQ("abcd", strlib::decapitalize("AbCd", true));

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
  SET_TEST(normalize);
  SET_TEST(normalizeBlank)
  SET_TEST(defautIfEmpty)
  SET_TEST(trim)
  SET_TEST(ltrim)
  SET_TEST(rtrim)

  SET_TEST(findFirstOf);
  SET_TEST(findLastOf);

  SET_TEST(findFirstNotOf);
  SET_TEST(findLastNotOf);

  SET_TEST(lpad);
  SET_TEST(rpad);
  SET_TEST(fill);
  SET_TEST(ellipsis);
  SET_TEST(trunc);
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