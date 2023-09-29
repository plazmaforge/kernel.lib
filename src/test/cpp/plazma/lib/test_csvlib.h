#include <string>
#include <iostream>

#include "plazma/lib/data/csv/csvlib.h"
#include "test_helper.h"

void test_CsvTokens() {

  // CSV Input
  std::string input = "1, 'Name', 123.56, 2020-10-10";
  StringList* tokens = csvlib::tokenizeCsvFromText(input); 

  printHeader("CSV-Text");

  std::cout << input << std::endl;
  
  printHeader("CSV-Tokens");
  
  int size = tokens->size();
  for (int i = 0; i < size; i++) {
    std::cout << tokens->get(i) << std::endl;
  }

} 

void test_csvlib_all() {
    test_CsvTokens();
}
 
