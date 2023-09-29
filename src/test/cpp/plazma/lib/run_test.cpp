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

#include "test_strlib.h"
#include "test_node.h"
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


// 3. TEST READ/WRITE XML
void testReadWriteXml() {

  std::string fileName = "../../src/test/resources/FirstJasper.jrxml";

  std::string reportXml = iolib::readText(fileName);

  //cout << reportXml << endl;

  StringList* tokens = xmllib::tokenizeXmlFromText(reportXml); 

  printHeader("Tokens Report XML");
  std::cout << "Size: " << tokens->size() << std::endl;
  std::cout << std::endl;

  // READ TEXT
  long t1 = syslib::startTime();
  std::string text = iolib::readText(fileName);
  t1 = syslib::stopTime(t1);
  std::cout << "Read Text Time: " << t1 << "ms" << std::endl;

  // PARSE REPORT
  t1 = syslib::startTime();
  tokens = xmllib::tokenizeXmlFromText(text);
  t1 = syslib::stopTime(t1);
  std::cout << "Parse Report Time: " << t1 << "ms" << std::endl;

  // READ REPORT
  node::Node* reportNode = nullptr;
  t1 = syslib::startTime();

  int K = 100; //100000;

  for (int i = 0; i < K; i++) {
      reportNode = xmllib::readXmlFromFile(fileName);
  }
  t1 = syslib::stopTime(t1);

  std::cout << "[TOTAL] Count: " << K << ", Time: " << t1 << "ms" << std::endl;
  std::cout << "Read Report Time: " << (t1/ K * 1.0) << "ms" << std::endl;

  //cout << endl;  
  //cout << "ReportNode:" << endl;
  //cout << "----------------------------------" << endl;
  //cout << xmllib::writeXmlToText(reportNode) << endl; // TODO: DOESN'T WORK: Segmentation fault: 11

  //string t = xmllib::writeXmlToText(reportNode);

  //iolib::writeText(t, z);
  //xmllib::writeXmlToFile(reportNode, z2);

  //delete reportNode;

}

void test_min(std::map<std::string, std::string>& parameters) {
  test_strlib_all();
  test_node_all();
  test_stdstr_all(parameters);  
}

void test_all(std::map<std::string, std::string>& parameters) {
  test_strlib_all();
  test_node_all();
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

  if (test == ".") {
    test_min(parameters);
    return;
  }

  if (test == "strlib") {
    test_strlib_all();
    return;
  }

  if (test == "node") {
    test_node_all();
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

  // 1. TEST NORMALIZE
  //test_Normalize();

  // 2. TEST NODE
  //test_Node();

  // 3. TEST READ/WRITE XML
  //testReadWriteXml();

  // 4. TEST TOKENS
  //testTokens();

  // 5. TEST STRING
  //test_LatinString();

  // 6. TEST UNICODE STRING
  //test_UnicodeString(parameters);

  /*
   string testString = "123456";
   const char* testChars = testString.c_str();
   csonst char* testChars2 = "123456"; 
   const char* testChars3 = new char[6] {'1', '2', '3', '4', '5' ,'6'}; 

   int testStringLen1 = testString.length();
   int testStringLen2 = strlen(testChars);
   int testStringLen3 = getStringLen(testChars);
   int testStringLen4 = getStringLen(testChars2);
   int testStringLen5 = getStringLen(testChars3);

   cout << "testStringLen1: " << testStringLen1 << endl;
   cout << "testStringLen2: " << testStringLen2 << endl;
   cout << "testStringLen3: " << testStringLen3 << endl;
   cout << "testStringLen4: " << testStringLen4 << endl;
   cout << "testStringLen5: " << testStringLen5 << endl;
   */

  
  /*
  node::Node* noodePtr1;
  node::Node* noodePtr2 = new node::Node();

  node::Node nnn = createNode0("Name3");
  node::Node* noodePtr3 = &nnn;

  //auto ccc = reinterpret_cast<std::byte*>(noodePtr3);
  //uintptr_t zzz = reinterpret_cast<uintptr_t>(noodePtr3);
  uintptr_t zzz = createNode2("Name3333");
  node::Node* noodePtr4 = reinterpret_cast<node::Node*>(zzz);

  long xxx = createNode3("Name4444");
  node::Node* noodePtr5 = reinterpret_cast<node::Node*>(xxx);

  string yyys = createNode4("Name777");
  long yyy = stol(yyys);
  node::Node* noodePtr6 = reinterpret_cast<node::Node*>(yyy);

  cout << "noodePtr1: " << noodePtr1 << endl;
  cout << "noodePtr2: " << noodePtr2 << endl;
  cout << "noodePtr3: " << noodePtr3 << endl;

  cout << "Node Ptr3: " << noodePtr3->getName() << endl;

  //cout << "^         : " << ccc << endl;
  cout << "zzz      : " << zzz << endl;
  cout << "Node     : " << noodePtr4->getName() << endl;

  cout << "xxx      : " << xxx << endl;
  cout << "Node     : " << noodePtr5->getName() << endl;

  cout << "yyy      : " << xxx << endl;
  cout << "Node     : " << noodePtr6->getName() << endl;
  */


  /*


  string input = "Hello, ;World, 'Test ,; ' ";
  int inputLen = input.length();

  int separatorLen = 3;
  char* separators = new char[separatorLen];
  separators[0] = ',';
  separators[1] = ' ';
  separators[2] = ';';

  int ignoreSeparatorLen = 3;
  char* ignoreSeparators = new char[ignoreSeparatorLen];
  ignoreSeparators[0] = ',';
  ignoreSeparators[1] = ' ';
  ignoreSeparators[2] = ';';

  int startQuoteLen = 2;
  char* startQuotes = new char[startQuoteLen];
  startQuotes[0] = '\'';
  startQuotes[1] = '"';

  int endQuoteLen = 2;
  char* endQuotes = new char[endQuoteLen];
  endQuotes[0] = '\'';
  endQuotes[1] = '"';

  vector<string> tokens = textlib::tokenize(input.c_str(), inputLen, separators, separatorLen, ignoreSeparators, ignoreSeparatorLen, startQuotes, startQuoteLen, endQuotes, endQuoteLen); 
  cout << "Tokens:" << endl;
  cout << "-------------" << endl;
  
  int size = tokens.size();
  for (int i = 0; i < size; i++) {
    cout << tokens[i] << endl;
  }

  tokens = textlib::tokenize(input.c_str(), inputLen, separators, separatorLen, false, startQuotes, startQuoteLen); 

  cout << endl;
  cout << "Tokens:" << endl;
  cout << "-------------" << endl;
  
  size = tokens.size();
  for (int i = 0; i < size; i++) {
    cout << tokens[i] << endl;
  }

  vector<string> separatorList;
  separatorList.push_back(",");
  separatorList.push_back(" ");
  separatorList.push_back(";");

  vector<string> ignoreSeparatorList;
  ignoreSeparatorList.push_back(",");
  ignoreSeparatorList.push_back(" ");
  ignoreSeparatorList.push_back(";");

vector<string> flexSeparators;

  vector<string> startQuoteList;
  startQuoteList.push_back("'");
  startQuoteList.push_back("\"");
  
  vector<string> endQuoteList;
  endQuoteList.push_back("'");
  endQuoteList.push_back("\"");
  
  tokens = textlib::tokenize(input.c_str(), inputLen, separatorList, ignoreSeparatorList, flexSeparators, startQuoteList, endQuoteList); 

  cout << endl;
  cout << "Tokens-String:" << endl;
  cout << "-------------" << endl;
  
  size = tokens.size();
  for (int i = 0; i < size; i++) {
    cout << tokens[i] << endl;
  }

  */

  //datelib::printMonthCalendar(2020, 9);

  //cout << endl;

  //datelib::printMonthCalendar(2020, 9, true);

  //cout << endl;

  //datelib::printMonthCalendar(2020, 3, false);

  //cout << endl;

  //datelib::printYearCalendar(1969, true, false);

  //int days = datelib::calculateDays(1970, 1, 1);

  /*
 
  cout << "days(1, 1, 1)  = " << datelib::calculateDays(1, 1, 1) << endl;
  cout << "days(1970, 1, 1)  = " << datelib::calculateDays(1970, 1, 1) << endl;
  cout << "days(1970, 1, 2)  = " << datelib::calculateDays(1970, 1, 2) << endl;

  cout << "days(2020, 9, 8)  = " << datelib::calculateDays(2020, 9, 8) << endl;

  cout << "getDayOfWeek(1970, 1, 1)  = " << datelib::getDayOfWeek(1970, 1, 1) << endl;
  cout << "getDayOfWeek(1970, 1, 2)  = " << datelib::getDayOfWeek(1970, 1, 2) << endl;

  cout << "getDayOfWeek(2020, 9, 7)  = " << datelib::getDayOfWeek(2020, 9, 7) << endl;
  cout << "getDayOfWeek(2020, 9, 8)  = " << datelib::getDayOfWeek(2020, 9, 8) << endl;
  cout << "getDayOfWeek(2020, 9, 9)  = " << datelib::getDayOfWeek(2020, 9, 9) << endl;

  cout << "getDayNameOfWeek(2020, 9, 7)  = " << datelib::getDayNameOfWeek(2020, 9, 7) << endl;
  cout << "getDayNameOfWeek(2020, 9, 8)  = " << datelib::getDayNameOfWeek(2020, 9, 8) << endl;
  cout << "getDayNameOfWeek(2020, 9, 9)  = " << datelib::getDayNameOfWeek(2020, 9, 9) << endl;
  */


  /*
  for (int year = -5; year <= 20; year++) {
    cout << year << (datelib::isLeapYear(year) ? "+" : "") << endl;
  }
  */
  

  //cout << "year"; 
  //cout << "\t" << "bit";

  /*

    int c = 0;
  int m = 0;

  for (int year = 1; year <= 100; year++) {

    bool ly_1 = datelib::isLeapYear(year); 
    bool ly_2 = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    bool ly_3 = (year % 4) == 0;

    //if (ly_1 == ly_2 && ly_1 == ly_3) {
    //  continue;
    //}

    c = year / 4;
    m = year - (c * 4);

    cout << year; 
    cout << "\t" << (ly_1 ? "+" : "-");
    cout << (ly_2 ? "+" : "-");
    cout << (ly_3 ? "+" : "-");
    cout << "\t" << c;
    cout << "\t" << m;

    cout << endl;
  }
  */

  //int days1 = 0;
  //int days2 = 0;
/*
  for (int year = 1; year <= 100000; year++) {
    bool leapYear = datelib::isLeapYear(year);
    for (int month = 1; month <= 12; month++) {
      int days = datelib::DAYS[month - 1];
      if (month == 2 && leapYear) {
        days = 29; // 29 days
      }
      for (int day = 1; day <= days; day++) {
        days1 = datelib::calculateDays(year, month, day);
        days2 = datelib::calculateDays2(year, month, day);

        if (days1 == days2) {
          continue;
        }

        cout << year << "-" << month << "-" << day << ": " << days1 << ", " << days2 << endl;
      }

    }
  }
*/
  
   /*
  // TEST-2
  int TEST_COUNT = 100; //10000000;
  long time = systemlib::startTime();
  for (int year = 1; year <= TEST_COUNT; year++) {
    bool leapYear = datelib::isLeapYear(year);
    for (int month = 1; month <= 12; month++) {
      int days = datelib::DAYS[month - 1];
      if (month == 2 && leapYear) {
        days = 29; // 29 days
      }
      for (int day = 1; day <= days; day++) {
        days1 = datelib::calculateDays(year, month, day);
      }

    }
  }
  time = systemlib::stopTime(time);
  cout << "TEST-2: " << time << endl;

  // TEST-1
  time = systemlib::startTime();
  for (int year = 1; year <= TEST_COUNT; year++) {
    bool leapYear = datelib::isLeapYear(year);
    for (int month = 1; month <= 12; month++) {
      int days = datelib::DAYS[month - 1];
      if (month == 2 && leapYear) {
        days = 29; // 29 days
      }
      for (int day = 1; day <= days; day++) {
        days2 = datelib::calculateDays(year, month, day);
      }

    }
  }
  time = systemlib::stopTime(time);
  cout << "TEST-1: " << time << endl;
  */
  

  //int year = 5;
  //int x = year / 4; 
  

 /*
  for (int year = 1970; year <= 2000; year++) {
    cout << year << (datelib::isLeapYear(year) ? "+" : "") << endl;
  }
  */

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