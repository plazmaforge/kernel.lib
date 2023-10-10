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

TEST(size) {

  ASSERT_EQ(0, strlib::size(""));

  ASSERT_EQ(1, strlib::size(" "));
  ASSERT_EQ(2, strlib::size("  "));

  ASSERT_EQ(3, strlib::size("abc"));
  ASSERT_EQ(4, strlib::size(" abc"));
  ASSERT_EQ(4, strlib::size("abc "));
  ASSERT_EQ(5, strlib::size(" abc "));

}

TEST(normalize) {

  // normalize(empty)
  ASSERT_EQ("", strlib::normalize(""));
  ASSERT_EQ("", strlib::normalize(" "));
  ASSERT_EQ("", strlib::normalize("  "));

  // normalize(value)
  ASSERT_EQ("abc", strlib::normalize("abc"));
  ASSERT_EQ("abc", strlib::normalize(" abc"));
  ASSERT_EQ("abc", strlib::normalize("abc "));
  ASSERT_EQ("abc", strlib::normalize(" abc "));

}

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

  printHeader("TEST strlib::ellipsis");
  std::cout << "ellipsis(\"Translation\", 10): " << strlib::ellipsis("Translation", 10) << std::endl;

}

void test_trunc() {

  printHeader("TEST strlib::trunc");  
  std::cout << "trunc(\"Abcdef123456789\"): " << strlib::trunc("Abcdef123456789", 6) << std::endl;

}

void test_case() {

  printHeader("TEST strlib::case");

  std::cout << "upper(\"AvSx\")               : " << strlib::upper("AvSx") << std::endl;
  std::cout << "lower(\"AvSx\")               : " << strlib::lower("AvSx") << std::endl;
  std::cout << "toCase(\"AvSx\", true)        : " << strlib::toCase("AvSx", true) << std::endl;
  std::cout << "toCase(\"AvSx\", false)       : " << strlib::toCase("AvSx", false) << std::endl;

  std::cout << "capitalize(\"avSx\")          : " << strlib::capitalize("avSx") << std::endl;
  std::cout << "capitalize(\"avSx\", true)    : " << strlib::capitalize("avSx", true) << std::endl;

  std::cout << "decapitalize(\"avSx\")        : " << strlib::decapitalize("avSx") << std::endl;
  std::cout << "decapitalize(\"avSx\", true)  : " << strlib::decapitalize("avSx", true) << std::endl;

  std::cout << "toCamelCase(\"first_name\")   : " << strlib::toCamelCase("first_name") << std::endl;
  std::cout << "toSnakeCase(\"FirstName\")    : " << strlib::toSnakeCase("FirstName") << std::endl;

}

void test_reverse() {

  printHeader("TEST strlib::reverse");
  std::cout << "reverse(\"abcdef\")                         : " << strlib::reverse("abcdef") << std::endl;

}

void test_hasPrefix() {

  printHeader("TEST strlib::hasPrefix");
  std::cout << "hasPrefix(\"myfile.txt\", \"abc\")            : " << strlib::hasPrefix("myfile.txt", "abc") << std::endl;
  std::cout << "hasPrefix(\"myfile.txt\", \"my\")             : " << strlib::hasPrefix("myfile.txt", "my") << std::endl;

}

void test_hasSuffix() {

  printHeader("TEST strlib::hasSuffix");
  std::cout << "hasSuffix(\"myfile.txt\", \".doc\")           : " << strlib::hasSuffix("myfile.txt", ".doc") << std::endl;
  std::cout << "hasSuffix(\"myfile.txt\", \".txt\")           : " << strlib::hasSuffix("myfile.txt", ".txt") << std::endl;

  std::cout << "hasSuffixIgnoreCase(\"myfile.txt\", \".TxT\") : " << strlib::hasSuffixIgnoreCase("myfile.txt", ".TxT") << std::endl;

}

void test_removePrefix() {

  printHeader("TEST strlib::removePrefix");
  std::cout << "removePrefix(\"myfile.txt\", \"my\")          : " << strlib::removePrefix("myfile.txt", "my") << std::endl;
  std::cout << "removePrefix(\"myfile.txt\", \"myfile.txt\")  : " << strlib::removePrefix("myfile.txt", "myfile.txt") << std::endl;

}

void test_removeSuffix() {

  printHeader("TEST strlib::removeSuffix");
  std::cout << "removeSuffix(\"myfile.txt\", \".txt\")        : " << strlib::removeSuffix("myfile.txt", ".txt") << std::endl;
  std::cout << "removeSuffix(\"myfile.txt\", \"myfile.txt\")  : " << strlib::removeSuffix("myfile.txt", "myfile.txt") << std::endl;
}

void test_count() {

  printHeader("TEST strlib::count");
  
  std::cout << "countChar()                               : " << strlib::countChar("Hello world, my world is veri nice world", 'o') << std::endl;
  std::cout << "countString()                             : " << strlib::countString("Hello world, my world is veri nice world", "world") << std::endl;
  std::cout << "countWord()                               : " << strlib::countWord("Hello world, my world is veri nice world. But we have other worlds.") << std::endl;


}

void test_split() {

  printHeader("TEST strlib::split");

  std::vector<std::string> res;

  std::cout << "split(\"1, 200, 500, -12\", ',')  : " << std::endl;
  res = strlib::split("1, 200, 500, -12", ',');
  println(res);

  std::cout << "split(\"1, 200, 500, -12\", ' ,') : " << std::endl;
  res = strlib::split("1, 200, 500, -12", " ,");
  println(res);

  std::cout << "split(\"1, 200| 500|| -12\", \" ,|\") :" << std::endl;
  res = strlib::split("1, 200| 500|| -12", " ,|");
  println(res);

}

void test_splitWords() {

  printHeader("TEST strlib::splitWords");

  std::cout << "splitWords(\"Hello world, my world is very nice world. But we have other worlds.\"): " << std::endl;
  std::vector<std::string> res = strlib::splitWords("Hello world, my world is very nice world. But we have other worlds.");
  println(res);

}

void test_replaceAll() {

  printHeader("TEST strlib::replaceAll");

  std::cout << "replaceAll(): " << strlib::replaceAll("abcdef12345abcdef", "a", "A") << std::endl;

  std::vector<std::string> from;
  from.push_back("1");
  from.push_back("2");

  std::vector<std::string> to;
  to.push_back("A");
  to.push_back("B");

  std::cout << "replaceAll(): " << strlib::replaceAll("12345", from, to) << std::endl;

}

void test_isIdentifier() {

  printHeader("TEST strlib::isIdentifier");

  std::cout << "isIdentifier(\"12345\")    : " << strlib::isIdentifier("12345") << std::endl;
  std::cout << "isIdentifier(\"1abcd\")    : " << strlib::isIdentifier("1abcd") << std::endl;
  std::cout << "isIdentifier(\"____\")     : " << strlib::isIdentifier("____") << std::endl;
  std::cout << "isIdentifier(\"_abcd123\") : " << strlib::isIdentifier("_abcd123") << std::endl;
  std::cout << "isIdentifier(\"abcd123\")  : " << strlib::isIdentifier("abcd123") << std::endl;

}

void test_toString() {
 
  printHeader("TEST strlib::toString");

  char a[] = {'H', 'e', 'l', 'l', 'o'};
  char b[] = "Nova Code 1";
  //char* c = "Nova Code 2";

  std::cout << "toStriing({'H', 'e', 'l', 'l', 'o'}) : " << strlib::toString(a) << std::endl;
  std::cout << "toStriing(\"Nova Code 1\")              : " << strlib::toString(b) << std::endl;
  //std::cout << "toStriing(c): " << strlib::toString(c) << std::endl;
   
}


INIT(strlib) {
  SET_CASE(strlib);

  SET_TEST(isEmpty);
  SET_TEST(size);
  SET_TEST(normalize);
  SET_TEST(lpad);
  SET_TEST(rpad);
  SET_TEST(fill);
}


//TEST_ALL(strlib) { 

//     RUN(isEmpty);
//     RUN(size);
//     RUN(normalize);
//     RUN(lpad);
//     RUN(rpad);
//     RUN(fill);

    /*        
    test_ellipsis();
    test_trunc();
    test_case();
    test_reverse();
    test_hasPrefix();
    test_hasSuffix();
    test_removePrefix();
    test_removeSuffix();
    test_count();
    test_split();
    test_splitWords();
    test_replaceAll();
    test_isIdentifier();
    test_toString();
    */
    
 //}