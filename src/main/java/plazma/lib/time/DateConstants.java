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

import java.util.Calendar;

public interface DateConstants {

    int FIELD_YEAR = Calendar.YEAR;
    int FIELD_MONTH = Calendar.MONTH;
    int FIELD_DAY = Calendar.DAY_OF_MONTH; // (!)
    int FIELD_HOUR = Calendar.HOUR_OF_DAY; // (!)
    int FIELD_MINUTE = Calendar.MINUTE;
    int FIELD_SECOND = Calendar.SECOND;
    int FIELD_MILLISECOND = Calendar.MILLISECOND;

    ////////////////////////////////////////////////////////////////////////////////////

    int START_YEAR = 1970; // Unix date only

    int START_MONTH = 1;

    int START_DAY = 1;

    ////

    int SECONDS_PER_MINUTE = 60;

    int MINUTES_PER_HOUR = 60;

    int MILLISECONDS_PER_SECOND = 1000;

    int HOURS_PER_DAY = 24;

    long SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    long SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;

    int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;

    long MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND;

    long MILLISECONDS_PER_HOUR = SECONDS_PER_HOUR * MILLISECONDS_PER_SECOND;

    long MILLISECONDS_PER_DAY = SECONDS_PER_DAY * MILLISECONDS_PER_SECOND;

    // Week/Month/Year

    int DAYS_PER_WEEK = 7;

    int DAYS_PER_MONTH = 30; // Only for standard month

    int MONTHS_PER_YEAR = 12;

    int Q1 = 1;
    int Q2 = 2;
    int Q3 = 3;
    int Q4 = 4;
    int QUARTER_PER_YEAR = Q4;

    long SECONDS_PER_WEEK = DAYS_PER_WEEK * SECONDS_PER_DAY;

    long SECONDS_PER_MONTH = DAYS_PER_MONTH * SECONDS_PER_DAY; // Only for standard month

    long SECONDS_PER_YEAR = MONTHS_PER_YEAR * SECONDS_PER_MONTH; // Only for standard month and year

    long MILLISECONDS_PER_WEEK = SECONDS_PER_WEEK * MILLISECONDS_PER_SECOND;

    long MILLISECONDS_PER_MONTH = SECONDS_PER_MONTH * MILLISECONDS_PER_SECOND; // Only for standard month

    long MILLISECONDS_PER_YEAR = SECONDS_PER_YEAR * MILLISECONDS_PER_SECOND; // Only for standard month and year

    //

    static String[] MONTH_NAMES = {
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
    static String[] DAYS_OF_WEEK = {
            "Monday", 
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday", 
            "Saturday", 
            "Sunday"
            };

    // ISO 8601
    static String[] DAYS_SHORT_OF_WEEK = { 
            "Mo", 
            "Tu", 
            "We", 
            "Th", 
            "Fr", 
            "Sa", 
            "Su" 
            };

    // Gregorian Calendar
    static int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    
    static int[] YEAR_DAYS = { 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };

}
