#ifndef PLAZMA_LIB_TEST_HELPER_H
#define PLAZMA_LIB_TEST_HELPER_H

#include <string>
#include <iostream>

/*
void print(std::string str) {
  //printf(str.c_str());
  std::cout << str;
}

void println(std::string str) {
  print("\n");
}

void println() {
  print("\n");
}
*/

void printHeader(std::string str) {
  //println();
  //println(str);
  //println("============================================================================================");

  //iolib::_out(str);
  //printf("\n%s\n", str.c_str());
  //printf("============================================================================================\n");

  std::cout << std::endl << str << std::endl;
  std::cout << "============================================================================================" << std::endl;
}

#endif //PLAZMA_LIB_TEST_HELPER_H


