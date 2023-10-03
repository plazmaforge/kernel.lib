#include <string>
#include <iostream>

//#include <locale>
//#include <codecvt>

#include "plazma/lib/sys/syslib.h"
#include "plazma/lib/ext/ustring.h" 
#include "plazma/lib/str/strlib.h" 
#include "plazma/lib/str/strlib2.h" 
#include "plazma/lib/num/numlib.h" 

#include "plazma/lib/math/mathlib.h"
#include "plazma/lib/time/datelib.h"

#include "plazma/lib/io/iolib.h"
#include "plazma/lib/text/textlib.h"
#include "plazma/lib/data/csv/csvlib.h"
#include "plazma/lib/data/json/jsonlib.h"
#include "plazma/lib/data/xml/xmllib.h"

#include "plazma/lib/data/node/Node.h"

#include "test_helper.h"

#include "test_type.h"
#include "test_strlib.h"
#include "test_datelib.h"
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

void run(std::map<std::string, std::string>& parameters) {
  std::string test = parameters["test"];
  
  if (test == "" || test == "*") {
    test_all(parameters);
    return;
  }

  if (test == "type") {
    test_type_all();
    return;
  }

  if (test == ".") {
    test_min(parameters);
    return;
  }

  if (test == "strlib") {
    test_strlib_all();
    return;
  }

  if (test == "datelib") {
    test_datelib_all();
    return;
  }

  if (test == "node") {
    test_node_all();
    return;
  }

  if (test == "textlib") {
    test_textlib_all();
    return;
  }

  if (test == "csvlib") {
    test_csvlib_all();
    return;
  }

  if (test == "xmllib") {
    test_xmllib_all();
    return;
  }

  if (test == "jsonlib") {
    test_jsonlib_all();
    return;
  }

  if (test == "stdstr") {
    test_stdstr_all(parameters);
    return;
  }

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

  //datelib::printMonthCalendar(2020, 9);

  //cout << endl;

  //datelib::printMonthCalendar(2020, 9, true);

  //cout << endl;

  //datelib::printMonthCalendar(2020, 3, false);

  //cout << endl;

  //datelib::printYearCalendar(1969, true, false);
  
  /*

  cout.precision(17);

  cout << "log(3.5)            = " << mathlib::log(3.5) << endl;                        // 1.252762968495368
  cout << "exp(log(2.4))       = " << mathlib::exp(mathlib::log(2.4)) << endl;          // 2.4

  cout << "pow(10, 4)          = " << mathlib::pow(10, 4) << endl;                      // 10000.0
  cout << "log(10000, 10)      = " << mathlib::log(10000, 10) << endl;                  // 4.0

	cout << "log10(10000)        = " << mathlib::log10(10000) << endl;			              // 4.0
	cout << "log(10000)/log(10)  = " << (mathlib::log(10000) / mathlib::log(10)) << endl;	// 4.0

	cout << "log(1024, 2)        = " << mathlib::log(1024, 2) << endl;			              // 10.0
	cout << "log2(1024)          = " << mathlib::log2(1024) << endl;				              // 10.0
	cout << "pow(2, 10)          = " << mathlib::pow(2, 10) << endl;				              // 1024.0
  */

  /*
  double *x = new double[N];
  double *y = new double[N];
  double *z = new double[N];

  double *f = new double[N]; //malloc(N * sizeof(*f));
  */

  //std::this_thread::sleep_for(std::chrono::seconds(15));

  /*
  float x;

  x = -1;
  cout << "x: " << x << ", std::atanh(x): " << std::atanh(x) << endl;

  x = 1;
  cout << "x: " << x << ", std::atanh(x): " << std::atanh(x) << endl;

  x = NAN;
  cout << "x: " << x << ", std::atanh(x): " << std::asinh(x) << endl;

  x = INFINITY;
  cout << "x: " << x << ", std::atanh(x): " << std::asinh(x) << endl;

  x = -INFINITY;
  cout << "x: " << x << ", std::atanh(x): " << std::asinh(x) << endl;

  x = NAN;
  cout << "x: " << x << ", std::sign(x): " << std::copysign(x, 2) << endl;

  x = INFINITY;
  cout << "x: " << x << ", std::sign(x): " << std::copysign(x, 2) << endl;

  
  x = NAN;
  cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << endl;

  x = 123.456;
  cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << endl;

  x = INFINITY;
  cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << endl;

  cout << "x: " << x << ", isInfinity(x): " << mathlib::isInfinite(x) << endl;
  cout << "x: " << x << ", isPositiveInfinity(x): " << mathlib::isPositiveInfinite(x) << endl;
  cout << "x: " << x << ", isNegativeInfinity(x): " << mathlib::isNegativeInfinite(x) << endl;


  x = -INFINITY;
  cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << endl;

  cout << "x: " << x << ", isInfinity(x): " << mathlib::isInfinite(x) << endl;
  cout << "x: " << x << ", isPositiveInfinity(x): " << mathlib::isPositiveInfinite(x) << endl;
  cout << "x: " << x << ", isNegativeInfinity(x): " << mathlib::isNegativeInfinite(x) << endl;
  */

  /*
  double x = 12345678.12345678;
  //cout.precision(17);

  cout << "round(12345.12345, 4) = " << mathlib::round(12345678.12345678, 4) << endl;

  cout << "x = " << x << endl;
  x = x * 10000;
  cout << "x * 10^4 = " << x << endl;
  x = mathlib::round(x) / 10000;

  cout << "round(x) / 10000 = " << x << endl;
  */


  //float f = 123.45;
  //double d = 123.45;

  //cout << mathlib::ceil(f) << endl;
  //cout << mathlib::ceil(d) << endl;

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
  //setlocale(LC_ALL, "Russian");                 // OK
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


  /*

  cout << "test: trunc(): " << stringlib::trunc("Abcdef123456789", 6) << endl;
  
  cout << "test: shortString(): " << stringlib::shortString("Translation", 10) << endl;

  cout << "test: formatString(): " << stringlib::formatString("Volume", 20, '.') << ": 1.8 m3" << endl;
  cout << "test: formatString(): " << stringlib::formatString("Speed", 20, '.') << ": 180 km/h" << endl;
  cout << "test: formatString(): " << stringlib::formatString("Total amount in currency", 20, '.') << ": $20 000" << endl;

  cout << "test: rpad(b): " << stringlib::rpad("Volume", 20, '.') << ": 1.8 m3" << endl;
  cout << "test: rpad(b): " << stringlib::rpad("Speed", 20, '.') << ": 180 km/h" << endl;
  cout << "test: lpad(b): " << stringlib::lpad("Volume", 20, '*') << endl;

  cout << "test: upper(): " << stringlib::upper("AvSx") << endl;
  cout << "test: lower(): " << stringlib::lower("AvSx") << endl;
  cout << "test: toCase(true): " << stringlib::toCase("AvSx", true) << endl;
  cout << "test: toCase(false): " << stringlib::toCase("AvSx", false) << endl;

  cout << "test: capitalize(): " << stringlib::capitalize("avSx") << endl;
  cout << "test: capitalize(force): " << stringlib::capitalize("avSx", true) << endl;

  cout << "test: decapitalize(): " << stringlib::decapitalize("avSx") << endl;
  cout << "test: decapitalize(force): " << stringlib::decapitalize("avSx", true) << endl;

  cout << "test: toCamelCase(): " << stringlib::toCamelCase("first_name") << endl;
  cout << "test: toLineCase(): " << stringlib::toLineCase("FirstName") << endl;

  cout << "test: reverse(): " << stringlib::reverse("abcdef") << endl;

  cout << "test: hasPrefix(): " << stringlib::hasPrefix("myfile.txt", "abc") << endl;
  cout << "test: hasPrefix(): " << stringlib::hasPrefix("myfile.txt", "my") << endl;

  cout << "test: hasSuffix(): " << stringlib::hasSuffix("myfile.txt", ".doc") << endl;
  cout << "test: hasSuffix(): " << stringlib::hasSuffix("myfile.txt", ".txt") << endl;

  cout << "test: hasSuffixIgnoreCase(): " << stringlib::hasSuffixIgnoreCase("myfile.txt", ".TxT") << endl;

  cout << "test: removePrefix(): " << stringlib::removePrefix("myfile.txt", "my") << endl;
  cout << "test: removePrefix(): " << stringlib::removePrefix("myfile.txt", "myfile.txt") << endl;

  cout << "test: removeSuffix(): " << stringlib::removeSuffix("myfile.txt", ".txt") << endl;
  cout << "test: removeSuffix(): " << stringlib::removeSuffix("myfile.txt", "myfile.txt") << endl;

  cout << "test: countChar(): " << stringlib::countChar("Hello world, my world is veri nice world", 'o') << endl;
  cout << "test: countString(): " << stringlib::countString("Hello world, my world is veri nice world", "world") << endl;

  cout << "test: countWord(): " << stringlib::countWord("Hello world, my world is veri nice world. But we have other worlds.") << endl;

  vector<string> res = stringlib::splitWords("Hello world, my world is veri nice world. But we have other worlds.");
  print(res);
  cout << endl;

  res = stringlib::split("1, 200, 500, -12", ',');
  print(res);
  cout << endl;

  res = stringlib::split("1, 200, 500, -12", " ,");
  print(res);
  cout << endl;

  res = stringlib::split("1, 200| 500|| -12", " ,|");
  print(res);
  cout << endl;
  
  cout << "test: removePrefix(): " << stringlib::removePrefix("myfile.txt", "myfile") << endl;
  cout << "test: removeSuffix(): " << stringlib::removeSuffix("myfile.txt", ".txt") << endl;
  cout << "test: replaceAll(): " << stringlib::replaceAll("abcdef12345abcdef", "a", "A") << endl;

  vector<string> from;
  from.push_back("1");
  from.push_back("2");

  vector<string> to;
  to.push_back("A");
  to.push_back("B");

  cout << "test: replaceAll(): " << stringlib::replaceAll("12345", from, to) << endl;
  cout << "test: isIdentifier(): " << stringlib::isIdentifier("12345") << endl;
  cout << "test: isIdentifier(): " << stringlib::isIdentifier("1abcd") << endl;
  cout << "test: isIdentifier(): " << stringlib::isIdentifier("____") << endl;
  cout << "test: isIdentifier(): " << stringlib::isIdentifier("_abcd123") << endl;
  cout << "test: isIdentifier(): " << stringlib::isIdentifier("abcd123") << endl;

  char a[] = {'H', 'e', 'l', 'l', 'o'};
  char b[] = "Nova Code 1";
  //char* c = "Nova Code 2";

  cout << "test: toStriing(a): " << stringlib::toString(a) << endl;
  cout << "test: toStriing(b): " << stringlib::toString(b) << endl;
  //cout << "test: toStriing(c): " << stringlib::toString(c) << endl;

  */

  //basic_string<char_type> c;>
  // Execute task
  //execute(parameters);

  return 0;
}