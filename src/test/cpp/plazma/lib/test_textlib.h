#include <string>
#include <vector>
#include <iostream>

#include "plazma/lib/text/textlib.h"
#include "plazma/lib/text/TokenizerContext.h"

#include "test_helper.h"

void test_TextTokens() {
  
  TokenizerContext* context = new TokenizerContext();
  context->separators = {",", " ", ";"};
  context->excludeSeparators = {",", " ", ";"};
  context->startQuotes = {"\'", "\""};
  context->endQuotes = {"\'", "\""};

  std::string input = "Hello, ;World, 'Test ,; ' ";
  int inputLen = input.length();
  printHeader("Input:");
  std::cout << input << std::endl;

  StringList* tokens = textlib::tokenize(context, input.c_str(), inputLen); 
  printHeader("Tokens:");

  int size = tokens->size();
  for (int i = 0; i < size; i++) {
    std::cout << tokens->get(i) << std::endl;
  }

}

void test_textlib_all() {
    test_TextTokens();
}


