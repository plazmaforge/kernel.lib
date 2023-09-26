/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.lib.time;

import java.util.ArrayList;
import java.util.List;

import plazma.lib.str.StrLib;

public class CalendarLib implements DateConstants {
    
    // String constants for display Calendar

    static final String WEEK_HEADER_1 = "Mo Tu We Th Fr Sa Su";
    static final String WEEK_HEADER_2 = "Su Mo Tu We Th Fr Sa";
    static final String WEEK_HEADER_UPPER_1 = "MO TU WE TH FR SA SU";
    static final String WEEK_HEADER_UPPER_2 = "SU MO TU WE TH FR SA";
    static final String MONTH_BLANK_LINE = "                    ";
    
    static final String PAD = " ";
    static final int COL_LEN = 3;
    static final int DEFAULT_COL_PADDING = 2;
    static final int DEFAULT_ROW_PADDING = 1;
    
    ////

    // https://rosettacode.org/wiki/Calendar#Java

    public static List<String> toMonthCalendarLines(int year, int month, boolean sundayFirst, boolean upper) {

        String monthName = MONTH_NAMES[month - 1];
        if (upper) {
            monthName = StrLib.toUpperCase(monthName);
        }

        String weekHeader = sundayFirst ? (upper ? WEEK_HEADER_UPPER_2 : WEEK_HEADER_2) : (upper ? WEEK_HEADER_UPPER_1 : WEEK_HEADER_1);

        boolean leapYear = DateLib.isLeapYear(year);                   // Check leap year
        int monthDays = DateLib.getDaysOfMonthByLeap(month, leapYear); // Get day count of month
        int dayOfWeek = DateLib.getDayOfWeek(year, month, 1);          // Get first day of month (day of week) (Monday, Tuesday, ..., Sunday)

        // Fixed dayOfWeek (ISO 8601: Monday=1, Tuesday=2, ..., Sunday=7)
        if (dayOfWeek == 0) {
            dayOfWeek = DAYS_PER_WEEK;
        }

        int col = 0;
        int row = 0;
        int last_col = DAYS_PER_WEEK - 1;

        // Get first col by dayOfWeek (first day of month)
        col = sundayFirst ? (dayOfWeek == DAYS_PER_WEEK ? 0 : dayOfWeek) : dayOfWeek - 1;

        //System.out.println("dayOfWeek = " + dayOfWeek);
        //System.out.println("col = " + col);
        //System.out.println("monthDays = " + monthDays);

        int headeLen = weekHeader.length();
        int monthLen = monthName.length();

        int restLen = headeLen - monthLen;

        int leftPadLen = restLen / 2;
        int rightPadLen = restLen - leftPadLen;

        List<String> lines = new ArrayList<>();

        // center month name in header
        lines.add(StrLib.replicate(PAD, leftPadLen) + monthName + StrLib.replicate(PAD, rightPadLen));

        // print week header
        lines.add(weekHeader);

        StringBuilder line = new StringBuilder();

        for (int day = 1; day <= monthDays; day++) {

            // print pad ' ' for first string
            if (col > 0) {
                line.append(day == 1 ? StrLib.replicate(PAD, col * COL_LEN) : PAD);
            }

            // print pad ' ' for digit < 10
            if (day < 10) {
                line.append(PAD);
            }

            line.append(day);

            // last column
            if (col == last_col) {

                // Add line
                lines.add(line.toString());

                line.setLength(0);

                col = 0;
                row++;

            } else {

                if (day == monthDays) {
                    // last day - last line
                    if (col < last_col) {
                        line.append(StrLib.replicate(PAD, (last_col - col) * COL_LEN));
                    }

                    lines.add(line.toString());
                }
                col++;
            }

        }
        return lines;
    }

    public static List<String> toYearCalendarLines(int year, boolean sundayFirst, boolean upper) {
        List<List<String>> monthList = new ArrayList<>();
        List<String> monthLines = null;
        List<String> lines = new ArrayList<>();

        int max_line = 0;
        for (int month = 1; month <= MONTHS_PER_YEAR; month++) {
            monthLines = toMonthCalendarLines(year, month, sundayFirst, upper);
            if (monthLines.size() > max_line) {
                max_line = monthLines.size();
            }
            monthList.add(monthLines);
        }

        StringBuilder line = new StringBuilder();

        int col_count = 3;  // cols - Q1: M11, M12, M13, Q2: M21, M22, M23, ...
        int row_count = Q4; // rows - Q1, Q2, Q3, Q4
        
        int col_padding = DEFAULT_COL_PADDING;
        String col_pad = StrLib.replicate(PAD, col_padding);
        int row_padding = DEFAULT_ROW_PADDING;
        
        int blankLineLen = ((MONTH_BLANK_LINE.length() + col_padding) * col_count - col_padding);
        String blankLine = StrLib.replicate(PAD, blankLineLen);

        for (int row = 0; row < row_count; row++) {

            // calculate start month of row
            int start_month = row * col_count + 1;

            if (row > 0) {
                // add footer of quarter (2 lines)
                for (int i = 0; i < row_padding; i++) {
                    lines.add(blankLine);                    
                }
            }

            for (int i = 0; i < max_line; i++) {

                if (line.length() != 0) {
                    line.setLength(0); // clear                    
                }
                
                for (int col = 0; col < col_count; col++) {

                    // calculate month of cell [row:col]
                    int month = start_month + col;
                    int month_index = month - 1;

                    // add col pad
                    if (col > 0) {
                        line.append(col_pad);
                    }

                    monthLines = monthList.get(month_index);
                    if (i < monthLines.size()) {
                        line.append(monthLines.get(i));
                    } else {
                        line.append(MONTH_BLANK_LINE);
                    }

                }

                lines.add(line.toString());
            }
        }
        return lines;
    }

    ////

    public static void printMonthCalendar(int year, int month, boolean sundayFirst) {
        printMonthCalendar(year, month, sundayFirst, false);
    }

    public static void printMonthCalendar(int year, int month, boolean sundayFirst, boolean upper) {
        List<String> lines = toMonthCalendarLines(year, month, sundayFirst, upper);
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            System.out.println(lines.get(i));
        }
    }

    public static void printYearCalendar(int year, boolean sundayFirst) {
        printYearCalendar(year, sundayFirst, false);
    }

    public static void printYearCalendar(int year, boolean sundayFirst, boolean upper) {
        List<String> lines = toYearCalendarLines(year, sundayFirst, upper);
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            System.out.println(lines.get(i));
        }
    }


}
