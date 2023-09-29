#include <string>
#include <iostream>

#include "plazma/lib/str/strlib.h" 
#include "test_helper.h"

// 1. TEST NORMALIZE
void test_Normalize() {
  std::string str = strlib::normalize(" abc ");
  
  std::cout << std::endl;
  std::cout << "Normalize string-1: [" << str << "]" << std::endl;

  char* c2 = " abc ";
  std::string s22 = std::string(c2);
  strlib::_trim(s22);

  std::cout << "Normalize string-2: [" << s22 << "]" << std::endl;

}

void test_strlib_all() {
    printHeader("TEST strlib::");
    test_Normalize();
}