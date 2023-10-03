#include <string>
#include <vector>
#include <iostream>

#include "plazma/lib/time/datelib.h"
#include "plazma/lib/sys/syslib.h"

#include "test_helper.h"

void test_calculateDays() {

  printHeader("TEST calculateDays");

  std::cout << "days(1, 1, 1)  = " << datelib::calculateDays(1, 1, 1) << std::endl;
  std::cout << "days(1970, 1, 1)  = " << datelib::calculateDays(1970, 1, 1) << std::endl;
  std::cout << "days(1970, 1, 2)  = " << datelib::calculateDays(1970, 1, 2) << std::endl;
  std::cout << "days(2020, 9, 8)  = " << datelib::calculateDays(2020, 9, 8) << std::endl;

}

void test_calculateDays_1_and_2() {

  printHeader("TEST calculateDays 1 and 2");

  int days1 = 0;
  int days2 = 0;

  bool success = true;

  for (int year = 1; year <= 100000; year++) {
    bool leapYear = datelib::isLeapYear(year);
    for (int month = 1; month <= 12; month++) {
      int days = datelib::DAYS[month - 1]; // days of month
      if (month == 2 && leapYear) {
        days = 29; // 29 days
      }
      for (int day = 1; day <= days; day++) {
        days1 = datelib::calculateDays(year, month, day);  // Algo 1
        days2 = datelib::calculateDays2(year, month, day); // Algo 2

        if (days1 == days2) {
          continue;
        }
        success = false;

        std::cout << "Incorrect Algo: " << year << "-" << month << "-" << day << ": " << days1 << ", " << days2 << std::endl;
      }

    }
  }

  if (success) {
    std::cout << "Algo is corrected" << std::endl;
  }

}

void test_calculateDays_1_and_2_Performance() {

  printHeader("TEST calculateDays 1 and 2 Performance");

  int days1 = 0;
  int days2 = 0;

  
  int TEST_COUNT = 100;// 100; //10000000;

  // ALGO-1
  long time = syslib::startTime();
  for (int year = 1; year <= TEST_COUNT; year++) {
    bool leapYear = datelib::isLeapYear(year);
    for (int month = 1; month <= 12; month++) {
      int days = datelib::DAYS[month - 1];
      if (month == 2 && leapYear) {
        days = 29; // 29 days
      }
      for (int day = 1; day <= days; day++) {
        days1 = datelib::calculateDays(year, month, day); // Algo 1
      }

    }
  }
  time = syslib::stopTime(time);
  std::cout << "ALGO-1: " << time << std::endl;

  // ALGO-2
  time = syslib::startTime();
  for (int year = 1; year <= TEST_COUNT; year++) {
    bool leapYear = datelib::isLeapYear(year);
    for (int month = 1; month <= 12; month++) {
      int days = datelib::DAYS[month - 1];
      if (month == 2 && leapYear) {
        days = 29; // 29 days
      }
      for (int day = 1; day <= days; day++) {
        days2 = datelib::calculateDays2(year, month, day); // Algo 2
      }

    }
  }
  time = syslib::stopTime(time);
  std::cout << "ALGO-2: " << time << std::endl;


}

void test_getDayOfWeek() {

  printHeader("TEST getDayOfWeek");

  std::cout << "getDayOfWeek(1970, 1, 1)  = " << datelib::getDayOfWeek(1970, 1, 1) << std::endl;
  std::cout << "getDayOfWeek(1970, 1, 2)  = " << datelib::getDayOfWeek(1970, 1, 2) << std::endl;

  std::cout << "getDayOfWeek(2020, 9, 7)  = " << datelib::getDayOfWeek(2020, 9, 7) << std::endl;
  std::cout << "getDayOfWeek(2020, 9, 8)  = " << datelib::getDayOfWeek(2020, 9, 8) << std::endl;
  std::cout << "getDayOfWeek(2020, 9, 9)  = " << datelib::getDayOfWeek(2020, 9, 9) << std::endl;

}

void test_getDayNameOfWeek() {

  printHeader("TEST getDayNameOfWeek");

  std::cout << "getDayNameOfWeek(2020, 9, 7)  = " << datelib::getDayNameOfWeek(2020, 9, 7) << std::endl;
  std::cout << "getDayNameOfWeek(2020, 9, 8)  = " << datelib::getDayNameOfWeek(2020, 9, 8) << std::endl;
  std::cout << "getDayNameOfWeek(2020, 9, 9)  = " << datelib::getDayNameOfWeek(2020, 9, 9) << std::endl;

}

void test_isLeapYear() {

  printHeader("TEST isLeapYear");

  for (int year = -5; year <= 20; year++) {
    std::cout << year << (datelib::isLeapYear(year) ? "+" : "") << std::endl;
  }

  /*
  for (int year = 1970; year <= 2000; year++) {
    std::cout << year << (datelib::isLeapYear(year) ? "+" : "") << std::endl;
  } 
  */

}

void test_isLeapYear2() {

  printHeader("TEST isLeapYear2");

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

    std::cout << year; 
    std::cout << "\t" << (ly_1 ? "+" : "-");
    std::cout << (ly_2 ? "+" : "-");
    std::cout << (ly_3 ? "+" : "-");
    std::cout << "\t" << c;
    std::cout << "\t" << m;

    std::cout << std::endl;
  }


}

void test_datelib_all() {
    test_calculateDays();
    test_calculateDays_1_and_2();
    test_calculateDays_1_and_2_Performance();
    test_getDayOfWeek();
    test_getDayNameOfWeek();
    test_isLeapYear();
}