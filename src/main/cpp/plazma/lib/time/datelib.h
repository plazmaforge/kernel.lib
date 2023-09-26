#ifndef PLAZMA_LIB_TIME_DATELIB_H
#define PLAZMA_LIB_TIME_DATELIB_H

#include <string>

namespace datelib {

    /*

    const int START_YEAR = 1970;
    
    const int START_MONTH = 1;
    
    const int START_DAY = 1;

    const int START_QUARTER = 1;
    
    ////
    
    const int SECONDS_PER_MINUTE = 60;
    
    const int MINUTES_PER_HOUR = 60;
    
    const int MILLISECONDS_PER_SECOND = 1000;
    
    const int HOURS_PER_DAY = 24;
    
    const long SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    
    const long SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;
    
    const int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
    
    const long MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND;
    
    const long MILLISECONDS_PER_HOUR = SECONDS_PER_HOUR * MILLISECONDS_PER_SECOND;
    
    const long MILLISECONDS_PER_DAY = SECONDS_PER_DAY * MILLISECONDS_PER_SECOND;
    */
    
   
    // Week/Month/Year
    
    const int DAYS_PER_WEEK = 7;
    
    //const int DAYS_PER_MONTH = 30; // Only for standard month
    
    const int MONTHS_PER_YEAR = 12;

    //

    //const int Q1 = 1;
    //const int Q2 = 2;
    //const int Q3 = 3;
    const int Q4 = 4;
    //const int QUARTER_PER_YEAR = Q4;
    
    /*
    const long SECONDS_PER_WEEK = DAYS_PER_WEEK * SECONDS_PER_DAY;
    
    const long SECONDS_PER_MONTH = DAYS_PER_MONTH * SECONDS_PER_DAY; // Only for standard month
    
    const long SECONDS_PER_YEAR = MONTHS_PER_YEAR * SECONDS_PER_MONTH; // Only for standard month and year
    
    const long MILLISECONDS_PER_WEEK = SECONDS_PER_WEEK * MILLISECONDS_PER_SECOND;
    
    const long MILLISECONDS_PER_MONTH = SECONDS_PER_MONTH * MILLISECONDS_PER_SECOND; // Only for standard month
    
    const long MILLISECONDS_PER_YEAR = SECONDS_PER_YEAR * MILLISECONDS_PER_SECOND; // Only for standard month and year

    */

   const static std::string MONTH_NAMES[] = {
    "January",
    "February",
    "March",

    "April",
    "May",
    "June",

    "July",
    "August",
    "September",

    "October",
    "November",
    "December"
   };

   // ISO 8601
   const static std::string DAYS_OF_WEEK[] = {
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday",
   };

   // ISO 8601
   const static std::string DAYS_SHORT_OF_WEEK[] = {
    "Mo",
    "Tu",
    "We",
    "Th",
    "Fr",
    "Sa",
    "Su",
   };


   // GregorianCalendar

    const static int DAYS[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    const static int YEAR_DAYS[] = {31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    /**
     * The number of days in a 400 year cycle.
     */
    const static int DAYS_PER_CYCLE = 146097;

    /**
     * The number of days from year zero to year 1970.
     * There are five 400 year cycles from year zero to 2000.
     * There are 7 leap years from 1970 to 2000.
     */
    const static long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);


    bool isLeapYear(long year);

    int getDaysOfMonth(int year, int month);

    int getDaysOfMonthByLeap(int month, bool leap);

    int getDayOfWeek(int year, int month, int day);

    std::string getDayNameOfWeek(int year, int month, int day);

    int getDayOfYear(int year, int month, int day);
    
    // [IMPEMENTATION-1]

    // Calculate days from '0001-01-01' to 'yyyy-mm-dd'
    // 
    // Example: 
    // calculateDays(1, 1, 1) = 0
    // calculateDays(1, 1, 2) = 1
    // calculateDays(1, 1, 3) = 1

    long calculateDays(int y, int m, int d);


    // [IMPEMENTATION-2]

    // Calculate days from '0001-01-01' to 'yyyy-mm-dd'
    // 
    // Example: 
    // calculateDays(1, 1, 1) = 0
    // calculateDays(1, 1, 2) = 1
    // calculateDays(1, 1, 3) = 1

    long calculateDays2(int year, int month, int day);

    long calculateDays(int year1, int month1, int day1, int year2, int month2, int day2);

    ////

    long toDayAD(int year, int month, int day);

    long toEpochDay(int year, int month, int day);

    ////

    // Year
    int getCurrentYear();

    // Month of Year [1..12]
    int getCurrentMonth();

    // Day of Month [1..31]
    int getCurrentDay();

}

#endif // PLAZMA_LIB_TIME_DATELIB_H