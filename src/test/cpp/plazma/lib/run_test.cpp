#include <string>
#include <iostream>

//#include <locale>
//#include <codecvt>

//#include "plazma/lib/sys/syslib.h"
//#include "plazma/lib/ext/ustring.h" 
//#include "plazma/lib/str/strlib.h" 
//#include "plazma/lib/str/strlib2.h" 
//#include "plazma/lib/num/numlib.h" 

//#include "plazma/lib/math/mathlib.h"
//#include "plazma/lib/time/datelib.h"
//#include "plazma/lib/io/iolib.h"
//#include "plazma/lib/text/textlib.h"
//#include "plazma/lib/data/csv/csvlib.h"
//#include "plazma/lib/data/json/jsonlib.h"
//#include "plazma/lib/data/xml/xmllib.h"

//#include "plazma/lib/data/node/Node.h"

#include "test_helper.h"

#include "test_type.h"
#include "test_mathlib.h"
#include "test_strlib.h"
#include "test_datelib.h"
#include "test_calendarlib.h"
#include "test_node.h"
#include "test_textlib.h"
#include "test_csvlib.h"
#include "test_xmllib.h"
#include "test_jsonlib.h"

#include "test_stdstr.h"

// TODO
#include <cmath>

//using namespace mathlib;
using namespace syslib;
using namespace ext;


// ============================================================================================
// TESTS
// ============================================================================================

void test_min(std::map<std::string, std::string>& parameters) {
  test_type_all();
  test_strlib_all();
  test_node_all();
  test_stdstr_all(parameters);  
}

void test_all(std::map<std::string, std::string>& parameters) {
  test_type_all();
  test_strlib_all();
  test_datelib_all();
  test_node_all();
  test_textlib_all();
  test_csvlib_all();
  test_xmllib_all();
  test_jsonlib_all();
  test_stdstr_all(parameters);  
}

bool test_by_name(std::string& test, std::map<std::string, std::string>& parameters) {

  if (test == "type") {
    test_type_all();
    return true;
  }

  if (test == "mathlib") {
    test_mathlib_all();
    return true;
  }

  if (test == "strlib") {
    test_strlib_all();
    return true;
  }

  if (test == "datelib") {
    test_datelib_all();
    return true;
  }

  if (test == "calendarlib") {
    test_calendarlib_all();
    return true;
  }

  if (test == "node") {
    test_node_all();
    return true;
  }

  if (test == "textlib") {
    test_textlib_all();
    return true;
  }

  if (test == "csvlib") {
    test_csvlib_all();
    return true;
  }

  if (test == "xmllib") {
    test_xmllib_all();
    return true;
  }

  if (test == "jsonlib") {
    test_jsonlib_all();
    return true;
  }

  if (test == "stdstr") {
    test_stdstr_all(parameters);
    return true;
  }

  return false;

}

void run(std::map<std::string, std::string>& parameters) {
  std::string test = parameters["test"];
  
  // all
  if (test == "" || test == "*") {
    test_all(parameters);
    return;
  }

  // min
  if (test == ".") {
    test_min(parameters);
    return;
  }

  // by name
  if (test_by_name(test, parameters)) {
    return;
  }

  // all - not found
  test_all(parameters);

}

int main(int argc, char* argv[]) {

  //setlocale(LC_ALL, "en_US.UTF-8");
  //SetConsoleOutputCP(CP_UTF8);
  //SetConsoleCP(CP_UTF8);

  iolib::init_utf8_console();

  // Parse command line arguments
  std::map<std::string, std::string> parameters = parseArguments(argc, argv);

  run(parameters);

  //cout << "Locale BEFORE: " << setlocale(LC_ALL, NULL) << endl;

  // For correct result need:
  //
  // 1. setlocale(...)
  // 2. Use wstring: wstring s = L"mystring"
  // 3. Use wprintf(...): wprintf(L"mystring") or wprintf(s) 

  //setlocale(LC_CTYPE, "UTF-8");               // FAIL
  //setlocale(LC_ALL, "UTF-8");                 // FAIL
  //setlocale(LC_ALL, "ru_RU.1251");            // FAIL
  //setlocale(LC_ALL, "ru_RU.UTF-8");           // FAIL
  //setlocale(LC_ALL, "Russian_Russia.UTF-8");  // FAIL
  //setlocale(LC_ALL, "Russian_Russia.1251");   // OK
  //setlocale(LC_ALL, "Russian_Russia.UTF8");   // FAIL
  //setlocale(LC_ALL, "Russian");               // OK
  //setlocale(LC_ALL, "Ukrainian");             // OK 'i' - problem (?)
  //setlocale(0, "");                           // OK - default locale

  //cout << "Locale AFTER: " << setlocale(LC_ALL, NULL) << endl;

  //std::wstring ws1 = L"Їжачок і лисичка є добрими друзями-CONST-1\n"; // 'i' - urk
  //std::wstring ws2 = L"Їжачок i лисичка є добрими друзями-CONST-2\n"; // 'i' - eng

  ////wprintf(L"%c\n", L'й');
  ////wprintf(L"%c\n", L'і'); 

  //wprintf(L"Привет UTF8\n");
  //wprintf(L"Пока UTF8\n");
  //wprintf(L"Їжачок і лисичка\n");

  //wprintf(ws1.c_str());
  //wprintf(ws2.c_str());

  //cout << u8"Їжачок і лисичка\n" << endl;
  //cout << "Їжачок і лисичка\n" << endl;
  //cout << "Їжачок і лисичка є добрими друзями-CONST-1 Іі Її Ґґ: ß Üü Öö Ää \n" << endl;

  //std::filesystem

  return 0;
}