#include <string>
#include <iostream>

#include "plazma/lib/str/strlib.h" 
#include "test_helper.h"

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

// 1.2

TEST(lpad) {

  ASSERT_EQ("****Volume", strlib::lpad("Volume", 10, '*'));

}

TEST(rpad) {

  ASSERT_EQ("Volume****", strlib::rpad("Volume", 10, '*'));

}

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

TEST(reverse) {

  ASSERT_EQ("", strlib::reverse(""));
  ASSERT_EQ(" ", strlib::reverse(" "));
  ASSERT_EQ("*", strlib::reverse("*"));
  ASSERT_EQ("**", strlib::reverse("**"));
  ASSERT_EQ("***", strlib::reverse("***"));
  ASSERT_EQ("****", strlib::reverse("****"));
  ASSERT_EQ("fedcba", strlib::reverse("abcdef"));

}

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

TEST(count) {

  ASSERT_EQ(4, strlib::countChar("Hello world, my world is veri nice world", 'o'));
  ASSERT_EQ(3, strlib::countString("Hello world, my world is veri nice world", "world"));
  ASSERT_EQ(13, strlib::countWord("Hello world, my world is veri nice world. But we have other worlds."));

}

TEST(split) {

  ASSERT_TRUE(((std::vector<std::string>){"1","200", "500", "-12"} == strlib::split("1,200,500,-12", ',')));
  ASSERT_TRUE(((std::vector<std::string>){"1","200", "500", "-12"} == strlib::split("1|200|500|-12", '|')));

  ASSERT_TRUE(((std::vector<std::string>){"1", " 200", " 500", " -12"} == strlib::split("1, 200, 500, -12", ',')));
  ASSERT_TRUE(((std::vector<std::string>){"1", " 200", " 500", " -12"} == strlib::split("1| 200| 500| -12", '|')));

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1, 200, 500, -12", " ,")));
  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1| 200| 500| -12", " |")));

  ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "-12"} == strlib::split("1, 200| 500|| -12", " ,|")));

  //ASSERT_TRUE(((std::vector<std::string>){"1", "200", "500", "", "-12"} == strlib::split("1, 200| 500|| -12", ",|", true))); // trim

}

TEST(splitWords) {

  std::vector<std::string> words = {"Hello", "world", "my", "world", "is", "very", "nice", "world", "But", "we", "have", "other", "worlds"};
  ASSERT_TRUE((words == strlib::splitWords("Hello world, my world is very nice world. But we have other worlds.")));

}

TEST(replaceAll) {

  ASSERT_EQ("Abcdef12345Abcdef", strlib::replaceAll("abcdef12345abcdef", "a", "A"));

  std::vector<std::string> from = {"1", "2", "3", "4", "5", "6"};
  std::vector<std::string> to = {"A", "B", "C", "D", "E", "F"};
  ASSERT_EQ("ABCDEF", strlib::replaceAll("123456", from, to));

}

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

TEST(toString) {
 
  char a[] = {'H', 'e', 'l', 'l', 'o'};
  char b[] = "Hello";

  ASSERT_EQ("Hello", strlib::toString(a));
  ASSERT_EQ("Hello", strlib::toString(b));
   
}


INIT(strlib) {

  SET_TEST(isEmpty);
  SET_TEST(isBlank);
  SET_TEST(size);
  SET_TEST(equals);
  SET_TEST(normalize);

  SET_TEST(lpad);
  SET_TEST(rpad);
  SET_TEST(fill);
  SET_TEST(ellipsis);
  SET_TEST(trunc);
  SET_TEST(toUpperCase);
  SET_TEST(toLowerCase);
  SET_TEST(toCase);
  SET_TEST(toCamelCase);
  SET_TEST(toSnakeCase);
  SET_TEST(toKebabCase);
  SET_TEST(capitalize);
  SET_TEST(decapitalize);
  SET_TEST(reverse);
  SET_TEST(hasPrefix);
  SET_TEST(hasSuffix);
  SET_TEST(removePrefix);
  SET_TEST(removeSuffix);
  SET_TEST(count);
  SET_TEST(split);
  SET_TEST(splitWords);
  SET_TEST(replaceAll);
  SET_TEST(isIdentifier);
  SET_TEST(toString);

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