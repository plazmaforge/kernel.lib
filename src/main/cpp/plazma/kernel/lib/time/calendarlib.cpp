#include <cmath>
#include <iostream>
#include <vector>

#include "datelib.h"
#include "calendarlib.h"
#include "plazma/kernel/lib/str/strlib.h"

using namespace datelib;

namespace calendarlib {

    // String constants for display Calendar

    const static std::string WEEK_HEADER_1       = "Mo Tu We Th Fr Sa Su";
    const static std::string WEEK_HEADER_2       = "Su Mo Tu We Th Fr Sa";
    const static std::string WEEK_HEADER_UPPER_1 = "MO TU WE TH FR SA SU";
    const static std::string WEEK_HEADER_UPPER_2 = "SU MO TU WE TH FR SA";
    const static std::string MONTH_BLANK_LINE    = "                    ";

    const static std::string PAD = " ";
    const static int COL_LEN = 3; // Mo_

    const static int DEFAULT_COL_PADDING = 2;
    const static int DEFAULT_ROW_PADDING = 1;

    ////

    // https://rosettacode.org/wiki/Calendar#Java

    std::vector<std::string> toMonthCalendarLines(int year, int month, bool sundayFirst, bool upper) {
        
        std::string monthName = MONTH_NAMES[month - 1];
        if (upper) {
            monthName = strlib::toUpperCase(monthName);
        }
        
        std::string weekHeader = sundayFirst ? (upper ? WEEK_HEADER_UPPER_2 : WEEK_HEADER_2) : (upper ? WEEK_HEADER_UPPER_1 : WEEK_HEADER_1);

        bool leapYear = isLeapYear(year);                      // Check leap year
        int monthDays = getDaysOfMonthByLeap(month, leapYear); // Get day count of month
        int dayOfWeek = getDayOfWeek(year, month, 1);          // Get first day of month (day of week) (Monday, Tuesday, ..., Sunday)

        // Fixed dayOfWeek (ISO 8601: Monday=1, Tuesday=2, ..., Sunday=7)
        if (dayOfWeek == 0) {
            dayOfWeek = DAYS_PER_WEEK;
        }
        
        int col = 0;
        int row = 0;
        int last_col = DAYS_PER_WEEK - 1;

        // Get first col by dayOfWeek (first day of month)
        col = sundayFirst ? (dayOfWeek == DAYS_PER_WEEK ? 0 : dayOfWeek) : dayOfWeek - 1;

        //std::cout << "dayOfWeek = " << dayOfWeek << std::endl;
        //std::cout << "col       = " << col << std::endl;
        //std::cout << "monthDays = " << monthDays << std::endl;

        int headeLen = weekHeader.length();
        int monthLen = monthName.length();

        int restLen = headeLen - monthLen;

        int leftPadLen = restLen / 2;
        int rightPadLen =  restLen - leftPadLen;

        std::vector<std::string> lines;

        // center month name in header
        lines.push_back(strlib::replicate(PAD, leftPadLen) + monthName + strlib::replicate(PAD, rightPadLen)); 

        // print week header
        lines.push_back(weekHeader);

        std::string line;

        for (int day = 1; day <= monthDays; day++) {

            // print pad ' ' for first string
            if (col > 0) {
                line.append(day == 1 ? strlib::replicate(PAD, col * COL_LEN) : PAD); // Mo_
            }

            // print pad ' ' for digit < 10
            if (day < 10) {
                line.append(PAD);
            }

            line.append(std::to_string(day));

            // last column
            if (col == last_col) {

                lines.push_back(line);
                line.clear(); // clear

                col = 0;
                row++;

            } else {

                if (day == monthDays) {
                    // last day - last line
                    if (col < last_col) {
                        line.append(strlib::replicate(PAD, (last_col - col) * COL_LEN)); 
                    }
                    lines.push_back(line);
                }
                col++;
            }

        }
        return lines;
    }

    std::vector<std::string> toYearCalendarLines(int year, bool sundayFirst, bool upper) {
        std::vector<std::vector<std::string>> monthList;
        std::vector<std::string> lines;

        int max_line = 0;
        for (int month = 1; month <= MONTHS_PER_YEAR; month++) {
            std::vector<std::string> monthLines = toMonthCalendarLines(year, month, sundayFirst, upper);
            if (monthLines.size() > max_line) {
                max_line = monthLines.size();
            }
            monthList.push_back(monthLines);
        }

        std::string line;
        std::vector<std::string> monthLines;

        int col_count = 3;  // cols - Q1: M11, M12, M13, Q2: M21, M22, M23, ...
        int row_count = Q4; // rows - Q1, Q2, Q3, Q4

        int col_padding = DEFAULT_COL_PADDING;
        std::string col_pad = strlib::replicate(PAD, col_padding);
        int row_padding = DEFAULT_ROW_PADDING;

        int blankLineLen =  ((MONTH_BLANK_LINE.size() + col_padding) * col_count - col_padding); 
        std::string blankLine = strlib::replicate(PAD, blankLineLen);

        for (int row = 0; row < row_count; row++) {

            // calculate start month of row
            int start_month = row * col_count + 1;

            if (row > 0) {
                // add footer of quarter (2 lines)
                for (int i = 0; i < row_padding; i++) {
                    lines.push_back(blankLine);
                }
            }

            for (int i = 0; i < max_line; i++) {

                if (!line.empty()) {
                    line.clear(); // clear
                }                

                for (int col = 0; col < col_count; col++) {

                    // calculate month of cell [row:col]
                    int month = start_month + col;
                    int month_index = month - 1;

                    // add col pad
                    if (col > 0) {
                        line.append(col_pad);
                    }

                    std::vector<std::string> monthLines =  monthList[month_index];
                    if (i < monthLines.size()) {
                        line.append(monthLines[i]);
                    } else {
                        line.append(MONTH_BLANK_LINE);
                    }
                }

                lines.push_back(line);
            }
        }
        return lines;
    }

    ////

    void printMonthCalendar(int year, int month) {
        printMonthCalendar(year, month, false);
    }

    void printMonthCalendar(int year, int month, bool sundayFirst) {
        printMonthCalendar(year, month, sundayFirst, false);
    }

    void printMonthCalendar(int year, int month, bool sundayFirst, bool upper) {
        std::vector<std::string> lines = toMonthCalendarLines(year, month, sundayFirst, upper);
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            std::cout << lines[i] << std::endl;
        }
    }
    
    void printYearCalendar(int year, bool sundayFirst) {
        printYearCalendar(year, sundayFirst, false);
    }

    void printYearCalendar(int year, bool sundayFirst, bool upper) {
        std::vector<std::string> lines = toYearCalendarLines(year, sundayFirst, upper);
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            std::cout << lines[i] << std::endl;
        }
    }

}