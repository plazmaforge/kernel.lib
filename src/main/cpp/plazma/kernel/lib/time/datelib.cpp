#include <cmath>
//#include <ostream>
#include <iostream>
#include <vector>

#include "datelib.h"
#include "plazma/kernel/lib/fmt/fmtlib.h"
#include "plazma/kernel/lib/num/numlib.h"

namespace datelib {

    // https://rosettacode.org/wiki/Calendar#C.2B.2B
    // https://rosettacode.org/wiki/Calendar#Java

    // https://www.geeksforgeeks.org/find-day-of-the-week-for-a-given-date/
    // https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week

    // https://stackoverflow.com/questions/40517192/c-day-of-week-for-given-date 
    // https://www.tutorialspoint.com/day-of-the-week-in-cplusplus

    // https://github.com/apache/xerces-c/blob/master/src/xercesc/util/XMLDateTime.cpp


    //
    // Check leep year
    //
    bool isLeapYear(long year) {

        // We have 2 implementations:
        //
        // bool leapYear1 = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
        // bool leapYear2 = ( (year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0  );
        //
        // But implementation 'leapYear2' is faster than 'leapYear1'

        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    //
    // Return days of month (year, month)
    //
    int getDaysOfMonth(int year, int month) {
        // Check leap year
        bool leap = isLeapYear(year);
        return getDaysOfMonthByLeap(month, leap);
    }
    
    //
    // Return days of month by 'leap' flag (year, leap)
    //
    int getDaysOfMonthByLeap(int month, bool leap) {
        if (month < 1 || month > MONTHS_PER_YEAR) {
            return 0; // Incorrect month: TODO: Maybe shift month
        }
        if (month == 2 && leap) { 
            return 29; // LeapYear: Feb - 29 days
        }
        return DAYS[month - 1];
    }

    // https://www.geeksforgeeks.org/find-day-of-the-week-for-a-given-date/
    // https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
    int getDayOfWeek(int year, int month, int day)  {
        static int t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        year -= month < 3;
        return ( year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7;  
    }

    std::string getDayNameOfWeek(int year, int month, int day) {
        int dayOfWeek = getDayOfWeek(year, month, day);
        // TODO
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return DAYS_OF_WEEK[dayOfWeek - 1];
    }

    int getDayOfYear(int year, int month, int day) {
        if (month < 1 || day < 1) {
            return 0;
        }
        if (month == 1) {
            return day;
        }

        //if (month == 1 && day == 1) {
        //    return 1;
        //}

        //long days = calculateDays(year, 1, 1, year, month, day);
        //return (int) (days) + 1;

        int m = month - 1;
        int dayCount = YEAR_DAYS[m - 1];
        if (m > 1) {
            // See 'isLeapYear' implementation
            bool leapYear = ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
            //bool leapYear = isLeapYear(year);
            if (leapYear ) {
                dayCount++;
            }
        }
        dayCount += day;
        return dayCount;

    }

    // [IMPEMENTATION-1]

    // Calculate days from '0001-01-01' to 'yyyy-mm-dd'
    // 
    // Example: 
    // calculateDays(1, 1, 1) = 0
    // calculateDays(1, 1, 2) = 1
    // calculateDays(1, 1, 3) = 1

    long calculateDays(int year, int month, int day) {
        month = (month + 9) % 12;
        year -= month / 10;
        long dn = 365 * year + year / 4 - year / 100 + year / 400 + (month * 306 + 5) / 10 + (day - 1);
        return dn - 305; // ??? Why 305
    }

    // [IMPEMENTATION-2]

    // Calculate days from '0001-01-01' to 'yyyy-mm-dd'
    // 
    // Example: 
    // calculateDays(1, 1, 1) = 0
    // calculateDays(1, 1, 2) = 1
    // calculateDays(1, 1, 3) = 1

    long calculateDays2(int year, int month, int day) {

        // Get previous year
        // TODO: What about year: [1, 0, -1, -2 ...]
        int y = year - 1;

        // Calculate days in standard years (365 days):
        // ('365 days' * 'years' + '1 day' * 'leap years') = ('365 days' * 'years' + 'leap years') 
        // ('leap years' = years / 4 - years / 100 + years / 400)

        long dayCount = 365 * y + y / 4 - y / 100 + y / 400;
        
        if (month > 1) {
            int m = month - 1;
            dayCount += YEAR_DAYS[m - 1];
            if (m > 1) {
                // See 'isLeapYear' implementation
                bool leapYear = ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
                //bool leapYear = isLeapYear(year);
                if (leapYear ) {
                    dayCount++;
                }
            }
        }
        dayCount += day;
        return dayCount;
    }

    long calculateDays(int year1, int month1, int day1, int year2, int month2, int day2) {
        // TODO
        if (year1 == year2 && month1 == month2) {
            return day2 - day1;
        }
        long days1 = calculateDays(year1, month1, day1);
        long days2 = calculateDays(year2, month2, day2);
        return days2 - days1;
    }

    ////

    // See Java: LocalDate.toEpochDay()
    // Calculate days from 0000 year (AD)
    long toDayAD(int year, int month, int day) {
        long y = year;
        long m = month;
        long total = 0;
        total += 365 * y;
        if (y >= 0) {
            total += (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
        } else {
            total -= y / -4 - y / -100 + y / -400;
        }
        total += ((367 * m - 362) / 12);
        total += day - 1;
        if (m > 2) {
            total--;
            if (isLeapYear(year) == false) {
                total--;
            }
        }
        return total;
    }

    // Calculate days from 1970 year (epoch)
    long toEpochDay(int year, int month, int day) {
        return toDayAD(year, month, day) - DAYS_0000_TO_1970;
    }

    //// 

    int _DateToInt(std::string format) {
        // %Y-%m-%d %H:%M:%S
        std::string str = fmtlib::formatDate(format);
        if (str.empty()) {
            return 0;
        }
        return numlib::toInt(str);
    }

    // Year
    int getCurrentYear() {
        return _DateToInt("%Y");
    }

    // Month of Year [1..12]
    int getCurrentMonth() {
        return _DateToInt("%m");
    }

    // Day of Month [1..31]
    int getCurrentDay() {
        return _DateToInt("%d");
    }

} // namespace datelib