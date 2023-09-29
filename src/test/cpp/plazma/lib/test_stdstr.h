#ifndef PLAZMA_LIB_TEST_STDSTR_H
#define PLAZMA_LIB_TEST_STDSTR_H

#include <string>
#include <map>
#include <iostream>

#include "plazma/lib/str/strlib2.h" 
#include "plazma/lib/io/iolib.h"

// 5. TEST STRING
void test_LatinString() {

  printHeader("OUTPUT LATIN STRING");

  std::string** xx = new std::string*[3];

  //const std::string** xx = (std::string *[]) {"dd"};

  std::string s1 = "String1"; 
  std::string s2 = "String2"; 
  std::string s3 = "String3"; 

  xx[0] = &s1;
  xx[1] = &s2;
  xx[2] = &s3;

  std::cout << std::endl;

  for (int k = 0; k < 3; k++) {
    std::cout << *xx[k] << std::endl;
  }

  char* test = new char[3];
  test[0] = 'a';
  test[1] = 'b';
  test[2] = 'c';

  std::cout << std::endl;
  //std::cout << "TEST-STR-LEN-1: " << strlen(test) << std::endl;
  //std::cout << "TEST-STR-LEN-2: " << strlen(test) << std::endl;

} 


// 6. TEST UNICODE STRING
void test_UnicodeString(std::map<std::string, std::string>& parameters) {

  // ===========================================================================================
  // TEST OUTPUT STRING CONSTANT
  // ===========================================================================================

  // [ЇїІіÄäüöß]
  // [Hello world! Привет мир! 😃]

  printHeader("OUTPUT STRING CONSTANT");

  std::cout << std::endl;
  std::cout << "1.  COUT--s8: Hello world! Привет мир!" << std::endl;
  std::cout << u8"2.  COUT--u8: Hello world! Привет мир!" << std::endl;
  std::wcout << L"3. WCOUT--ws: Hello world! Привет мир!" << std::endl;
  iolib::_out("4.  _OUT--s8: Hello world! Привет мир!\n");
  iolib::_out(L"5.  _OUT--ws: Hello world! Привет мир!\n");

  // ===========================================================================================
  // TEST OUTPUT STRING VARIABLE
  // ===========================================================================================

  printHeader("OUTPUT STRING VARIABLE");

  std::string test_s8 = "1.  COUT--s8: Hello world! Привет мир!";
  std::wstring test_ws = L"2. WCOUT--ws: Hello world! Привет мир!";
  std::string test_s8_ = "3.  _OUT--s8: Hello world! Привет мир!\n";
  std::wstring test_ws_ = L"4.  _OUT--ws: Hello world! Привет мир!\n";
  ext::ustring test_us = iolib::wstring_to_ustring (L"5.  _OUT--us: Hello world! Привет мир!\n");

  std::cout << std::endl;
  std::cout << test_s8 << std::endl;
  std::wcout << test_ws << std::endl;
  iolib::_out(test_s8_);
  iolib::_out(test_ws_);
  iolib::_out(test_us);


  std::cout << std::endl;
  std::cout << " COUT--xs: ";
  std::cout << "\xc3\xbc" << std::endl;


  // ===========================================================================================
  // TEST OUTPUT STRING FROM FILE
  // ===========================================================================================

  printHeader("OUTPUT STRING FROM FILE");

  std::string testFileName = "test_utf.txt";

  test_s8 = iolib::readText(testFileName);
  std::cout << "Read text from file: " << testFileName << std::endl;

  test_ws = iolib::utf8_to_wstring(test_s8);
  test_us = iolib::utf8_to_ustring(test_s8);
  std::cout << "Convert text from file: " << testFileName << std::endl;

  // Length

  std::cout << std::endl;
  std::cout << "Length of [s8]: " << test_s8.length() << std::endl;
  std::cout << "Length of [ws]: " << test_ws.length() << std::endl;
  std::cout << "Length of [us]: " << test_us.length() << std::endl;

  // s8
  std::cout << std::endl;
  std::cout << "1.  COUT--s8: From file    [ string]: ";
  std::cout << test_s8 << std::endl;

  iolib::_out("2.  _OUT--s8: From file    [ string]: "); 
  iolib::_out(test_s8); iolib::_out("\n");

  strlib2::_toCase(test_s8, true);
  iolib::_out("3.  _OUT--s8: From file UP [ string]: ");
  iolib::_out(test_s8); iolib::_out("\n");

  strlib2::_toCase(test_s8, false);
  iolib::_out("4.  _OUT--s8: From file LW [ string]: ");
  iolib::_out(test_s8); iolib::_out("\n");

  // ws
  iolib::_out("5.  _OUT--ws: From file    [wstring]: ");
  iolib::_out(test_ws); iolib::_out("\n");

  strlib2::_toCase(test_ws, true);
  iolib::_out("6.  _OUT--ws: From file UP [wstring]: ");
  iolib::_out(test_ws); iolib::_out("\n");

  strlib2::_toCase(test_ws, false);
  iolib::_out("7.  _OUT--ws: From file LW [wstring]: ");
  iolib::_out(test_ws); iolib::_out("\n");

  // us
  iolib::_out("8.  _OUT--us: From file    [ustring]: ");
  iolib::_out(test_us); iolib::_out("\n");

  strlib2::_toCase(test_us, true);
  iolib::_out("9.  _OUT--us: From file UP [ustring]: ");
  iolib::_out(test_us); iolib::_out("\n");

  strlib2::_toCase(test_us, false);
  iolib::_out("X.  _OUT--us: From file LW [ustring]: ");
  iolib::_out(test_us); iolib::_out("\n");


  // ===========================================================================================
  // TEST OUTPUT STRING FROM ARGUMENTS
  // ===========================================================================================

  std::string test_s8_arg = parameters["text"];
  std::wstring test_ws_arg = iolib::arg_to_wstring(test_s8_arg);
  ext::ustring test_us_arg = iolib::arg_to_ustring(test_s8_arg);

  printHeader("OUTPUT STRING FROM ARGUMENTS");

  std::cout << std::endl;
  std::cout << "1.  COUT--s8: " << test_s8_arg << std::endl;
  std::wcout << L"2. WCOUT--ws: " << test_ws_arg << std::endl;
  iolib::_out("3.  _OUT--s8: "); iolib::_out(test_s8_arg); iolib::_out("\n");
  iolib::_out(L"4.  _OUT--ws: "); iolib::_out(test_ws_arg); iolib::_out("\n");
  iolib::_out(L"5.  _OUT--us: "); iolib::_out(test_us_arg); iolib::_out("\n");


  // ===========================================================================================
  // TEST OUTPUT STRING FROM INPUT
  // ===========================================================================================

  printHeader("OUTPUT STRING FROM INPUT");

  std::cout << std::endl;
  std::cout << " COUT----: Input text: ";
  std::cout << std::endl;

  //cin >> test_in;
  test_s8 = iolib::_in();
  test_ws = iolib::utf8_to_wstring(test_s8);
  test_us = iolib::utf8_to_ustring(test_s8);
  //cout << test_in << endl;

  std::cout << std::endl;

  // s8
  iolib::_out(" _OUT--s8: From input   [ string]: "); 
  iolib::_out(test_s8); iolib::_out("\n");

  // ws
  iolib::_out(" _OUT--ws: From input   [wstring]: "); 
  iolib::_out(test_ws); iolib::_out("\n");

  // us
  iolib::_out(" _OUT--us: From input   [ustring]: "); 
  iolib::_out(test_us); iolib::_out("\n");

  //char16_t v = 42;
  //std::wcout << static_cast<wchar_t>(v) << std::endl;

  //wchar_t * unicode_text = L"aäbcdefghijklmnoöpqrsßtuüvwxyz";
  //wprintf(L"%ls \n", unicode_text);
  //wprintf (L"Characters: %ls \n", L"aäbcdefghijklmnoöpqrsßtuüvwxyz");
  //printf("File not found: %s \n", "MyFile.txt");

  //int mode = fwide(stdout,0);
  //std::cout<<"0.mode="<<mode<<std::endl;
  //std::cout<<"ABC"<<std::endl;
  //mode = fwide(stdout,0);
  //std::cout<<"1.mode="<<mode<<std::endl;
  //freopen(NULL,"w",stdout);
  //setlocale(LC_ALL, "en_US.UTF-8");
  //mode = fwide(stdout,0);
  //std::wcout<<stW_2<<mode<<std::endl;


}

void test_stdstr_all(std::map<std::string, std::string>& parameters) {
  test_LatinString();
  test_UnicodeString(parameters);
}

#endif // PLAZMA_LIB_TEST_STDSTR_H