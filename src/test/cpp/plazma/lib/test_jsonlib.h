#include <string>
#include <iostream>

#include "plazma/lib/data/json/jsonlib.h"
#include "test_helper.h"

void test_JsonTokens() {

  // Json Input
  std::string input = "{'id': 1234, 'name': 'Alex', 'contacts': [1, 2, 3]}";
  StringList* tokens = jsonlib::tokenizeJsonFromText(input); 

  printHeader("Json-Text");
  
  std::cout << input << std::endl;

  printHeader("Json-Tokens");
  
  int size = tokens->size();
  for (int i = 0; i < size; i++) {
    std::cout << tokens->get(i) << std::endl;
  }

} 

void test_jsonlib_all() {
    test_JsonTokens();
}

