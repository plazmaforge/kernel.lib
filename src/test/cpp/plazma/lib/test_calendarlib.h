#include <iostream>

#include "plazma/lib/time/calendarlib.h"

#include "test_helper.h"

void test_printMonthCalendar() {

  printHeader("TEST printMonthCalendar");
  
  calendarlib::printMonthCalendar(2020, 9);

  std::cout << std::endl;

  calendarlib::printMonthCalendar(2020, 9, true);

  std::cout << std::endl;

  calendarlib::printMonthCalendar(2020, 3, false);

}

void test_printYearCalendar() {

  printHeader("TEST printYearCalendar");
  
  calendarlib::printYearCalendar(1969, true, false);

}

void test_calendarlib_all() {
    test_printMonthCalendar();
    test_printYearCalendar();
}
