#include <string>
#include <iostream>

#include "plazma/lib/str/strlib.h" 
#include "test_helper.h"

void test_normalize() {

  printHeader("TEST strlib::normalize");
  std::string str = strlib::normalize(" abc ");
  
  std::cout << std::endl;
  std::cout << "Normalize string-1: [" << str << "]" << std::endl;

  char* c2 = " abc ";
  std::string s22 = std::string(c2);
  strlib::_trim(s22);

  std::cout << "Normalize string-2: [" << s22 << "]" << std::endl;

}

void test_lpad() {

  printHeader("TEST strlib::lpad");
  std::cout << "lpad((\"Volume\", 20, '*'): " << strlib::lpad("Volume", 20, '*') << std::endl;

}

void test_rpad() {

  printHeader("TEST strlib::rpad");
  std::cout << "rpad(\"Volume\", 20, '.'): " << strlib::rpad("Volume", 20, '.') << ": 1.8 m3" << std::endl;
  std::cout << "rpad(\"Speed\", 20, '.') : " << strlib::rpad("Speed", 20, '.') << ": 180 km/h" << std::endl;  
}

void test_fill() {

  printHeader("TEST strlib::fill");

  std::cout << "fill(\"Volume\", 20, '.'): " << strlib::fill("Volume", 20, '.') << ": 1.8 m3" << std::endl;
  std::cout << "fill(\"Speed\", 20, '.') : " << strlib::fill("Speed", 20, '.') << ": 180 km/h" << std::endl;
  std::cout << "fill(\"Total amount in currency\", 20, '.'): " << strlib::fill("Total amount in currency", 20, '.') << ": $20 000" << std::endl;

}

void test_ellipsis() {

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

void test_strlib_all() {   
    test_normalize();
    test_lpad();
    test_rpad();
    test_fill();
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
}