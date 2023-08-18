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

package plazma.kernel.lib.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateLib implements DateConstants {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    // 1. Instance of Date/Calendar (alias)
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1.1 Instance of Date/Calendar: by year, month, day, h, m, s, ms
    //
    // - date(year, month, day)
    // - datetime(year, month, day, h, m, s, ms)
    // - time(h, m, s, ms)
    // - calendar(year, month, day, h, m, s, ms)
    //
    // 1.2 Instance of Date/Calendar: by time (ms)
    //
    // - date(time)
    // - datetime(time)
    // - time(time)
    // - calendar(time)
    //
    // 1.3 Instance of Date/Calendar: current
    //
    // - date()
    // - datetime()
    // - time()
    // - calendar()

    /////////////////////////////////////////////////////////////////////////////////
    // 2. Instance of Date/Calendar
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 2.1 Instance of Date/Calendar: by year, month, day, h, m, s, ms
    //
    // - getDate(year, month, day)
    // - getDateTime(year, month, day, h, m, s)
    // - getDateTime(year, month, day, h, m, s, ms)
    // - getTime(h, m, s, ms)
    // - getCalendar(year, month, day)
    // - getCalendar(year, month, day, h, m, s)
    // - getCalendar(year, month, day, h, m, s, ms)
    //
    // 2.2 Instance of Date/Calendar: by time (ms)
    //
    // - getDate(time)
    // - getDateTime(time)
    // - getTime(time)
    // - getCalendar(time)
    //
    // 2.3 Instance of Date/Calendar: current
    //
    // - newDate()
    // - newDateTime()
    // - newTime()
    // - newCalendar()
    // - getGMTCalendar()

    /////////////////////////////////////////////////////////////////////////////////
    // 3. Modify Date/Calendar
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 3.1 Modify Date/Calendar: by get/set fields
    //
    // - get(date, field)
    // - set(date, field, value)
    //
    // - get(calendar, field)
    // - set(calendar, field, value)
    //
    // 3.2 Modify Date: by year, month, day, h, m, s, ms
    //
    // - setDate(date, year, month, day)
    // - setDateTime(date, year, month, day, h, m, s)
    // - setDateTime(date, year, month, day, h, m, s, ms)
    // - setTime(date, h, m, s)
    // - setTime(date, h, m, s, ms)
    //
    // 3.3 Modify Calendar: by year, month, day, h, m, s, ms
    //
    // - setDate(calendar, year, month, day)
    // - setDateTime(calendar, year, month, day, h, m, s)
    // - setDateTime(calendar, year, month, day, h, m, s, ms)
    // - setTime(calendar, h, m, s)
    // - setTime(calendar, h, m, s, ms)

    /////////////////////////////////////////////////////////////////////////////////
    // 4. Modify Date/Calendar: by get/get<Filed>
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 4.1 Modify Date: by get/get<Filed> (Year, Month, Day, Hour, Minute, Second,
    ///////////////////////////////////////////////////////////////////////////////// Millisecond)
    //
    // - getYear(date)
    // - setYear(date, year)
    //
    // - getMonth(date)
    // - setMonth(date, month)
    //
    // - getDay(date)
    // - setDay(date, day)
    //
    // - getHour(date)
    // - setHour(date, hour)
    //
    // - getMinute(date)
    // - setMinute(date, minute)
    //
    // - getSecond(date)
    // - setSecond(date, second)
    //
    // - getMillisecond(date)
    // - setMillisecond(date, millisecond)
    //
    // 4.2 Modify Calendar: by get/get<Filed> (Year, Month, Day, Hour, Minute,
    ///////////////////////////////////////////////////////////////////////////////// Second,
    ///////////////////////////////////////////////////////////////////////////////// Millisecond)
    //
    // - getYear(calendar)
    // - setYear(calendar, year)
    //
    // - getMonth(calendar)
    // - setMonth(calendar, month)
    //
    // - getDay(calendar)
    // - setDay(calendar, day)
    //
    // - getHour(calendar)
    // - setHour(calendar, hour)
    //
    // - getMinute(calendar)
    // - setMinute(calendar, minute)
    //
    // - getSecond(calendar)
    // - setSecond(calendar, second)
    //
    // - getMillisecond(calendar)
    // - setMillisecond(calendar, millisecond)

    /////////////////////////////////////////////////////////////////////////////////
    // 5. Shift Date/Calendar
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 5.1 Shift Date/Calendar: by fields
    //
    // - shift(date, field, shift)
    // - shift(calendar, field, shift)
    //
    // 5.2 Shift Date/Calendar: shift<Field>
    // - shiftYear(date, shiftYear)
    // - shiftMonth(date, shiftMonth)
    // - shiftDay(date, shiftDay)
    // - shiftHour(date, shiftHour)
    // - shiftMinute(date, shiftMinute)
    // - shiftSecond(date, shiftSecond)
    // - shiftMillisecond(date, shiftMillisecond)
    //
    // 5.3 Prev/Next Year, Month, Day
    //
    // - prevYear(date)
    // - prevMonth(date)
    // - prevDay(date)
    //
    // - nextYear(date)
    // - nextMonth(date)
    // - nextDay(date)
    //
    // 5.4 Prev/Next Year, Month, Day
    //
    // - prevYear(calendar)
    // - prevMonth(calendar)
    // - prevDay(calendar)
    //
    // - nextYear(calendar)
    // - nextMonth(calendar)
    // - nextDay(calendar)
    //
    // 5.5 Prev/Next Year, Month, Day: by fields
    //
    // - prevYear(year, month, day)
    // - prevMonth(year, month, day)
    // - prevDay(year, month, day)
    //
    // - nextYear(year, month, day)
    // - nextMonth(year, month, day)
    // - nextDay(year, month, day)
    //
    // 5.6 Prev/Next Date
    //
    // - prevDate(date)
    // - nextDate(date)
    //
    // 5.7 getFirst/LastDateOfMonth
    //
    // - getFirstDateOfMonth(date)
    // - getFirstDateOfMonth(year, month)
    //
    // - getLastDateOfMonth(date)
    // - getLastDateOfMonth(year, month)

    /////////////////////////////////////////////////////////////////////////////////
    // 6. Calendar info
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 6.1 getWeekDay, getYearDay
    //
    // - getWeekDay: 1..7
    // - getYearDay: 1..366
    //
    // 6.2 getDaysInMonth, getDaysInYear
    //
    // - getDaysInMonth: 1..31
    // - getDaysInYear: 1..366
    //
    // 6.3 isLeapYear
    //
    // - isLeapYear(date)
    // - isLeapYear(calendar)
    // - isLeapYear(year)

    /////////////////////////////////////////////////////////////////////////////////
    // 7. Period info
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 7.1 Date: (start, end): get Milliseconds, Seconds, Minutes, Hours, Days,
    ///////////////////////////////////////////////////////////////////////////////// Months,
    ///////////////////////////////////////////////////////////////////////////////// Years
    //
    // - getMilliseconds(start, end)
    // - getSeconds(start, end)
    // - getMinutes(start, end)
    // - getHours(start, end)
    // - getDays(start, end)
    // - getMonths(start, end)
    // - getYears(start, end)
    //
    // 7.2 (y1, m1, d1, y2, m2, d2): get Milliseconds, Seconds, Minutes, Hours,
    ///////////////////////////////////////////////////////////////////////////////// Days,
    ///////////////////////////////////////////////////////////////////////////////// Months,
    ///////////////////////////////////////////////////////////////////////////////// Years
    //
    // - getMilliseconds(y1, m1, d1, y2, m2, d2)
    // - getSeconds(y1, m1, d1, y2, m2, d2)
    // - getMinutes(y1, m1, d1, y2, m2, d2)
    // - getHours(y1, m1, d1, y2, m2, d2)
    // - getDays(y1, m1, d1, y2, m2, d2)
    // - getMonths(y1, m1, d1, y2, m2, d2)
    // - getYears(y1, m1, d1, y2, m2, d2)

    /////////////////////////////////////////////////////////////////////////////////
    // 8. Period End info
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 8.1
    //
    // - getMillisecond(year, month, day)
    // - getMillisecond(year, month, day, h, m, s)
    // - getMillisecond(year, month, day, h, m, s, ms)
    //
    // - getSecond(year, month, day)
    // - getSecond(year, month, day, h, m, s)
    // - getSecond(year, month, day, h, m, s, ms)
    //
    // - getMinute(year, month, day)
    // - getMinute(year, month, day, h, m, s)
    // - getMinute(year, month, day, h, m, s, ms)
    //
    // - getHour(year, month, day)
    // - getHour(year, month, day, h, m, s)
    // - getHour(year, month, day, h, m, s, ms)

    /////////////////////////////////////////////////////////////////////////////////
    // 9. Convert
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 9.1 Convert Date/Calendar
    //
    // - toDate(calendar)
    // - toCalendar(date)
    //
    // 9.2 Convert time (ms) to Date/Calendar
    //
    // - toDate(time)
    // - toSQLDate(time)
    // - toSQLTime(time)
    // - toSQLTimestamp(time)
    // - toCalendar(time)
    // - toLocalDate(time)
    // - toLocalDateTime(time)

    // https://rosettacode.org/wiki/Calendar#C.2B.2B
    // https://rosettacode.org/wiki/Calendar#Java

    // https://www.geeksforgeeks.org/find-day-of-the-week-for-a-given-date/
    // https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week

    // https://stackoverflow.com/questions/40517192/c-day-of-week-for-given-date
    // https://www.tutorialspoint.com/day-of-the-week-in-cplusplus

    private DateLib() {
    }

    public static boolean isLeapYear(int year) {
        return DateHelper._isLeapYear(year);
    }

    /**
     * Return days of month (year, month)
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        return DateHelper._getDaysOfMonth(year, month);
    }

    /**
     * Return days of month by 'leap' flag (year, leap)
     * 
     * @param month
     * @param leap
     * @return
     */
    public static int getDaysOfMonthByLeap(int month, boolean leap) {
        return DateHelper._getDaysOfMonthByLeap(month, leap);
    }

    /**
     * Return Day of Week (1..7): 1 - Monday ... 7 - Sunday
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getDayOfWeek(int year, int month, int day) {
        return DateHelper._getDayOfWeek(year, month, day);
    }

    public static String _getDayNameOfWeek(int year, int month, int day) {
        return DateHelper._getDayNameOfWeek(year, month, day);
    }

    //

    public static int getDayOfYear(int year, int month, int day) {
        return DateHelper._getDayOfYear(year, month, day);
    }

    // CALCULATE DAYS

    public static long calculateDays(int year, int month, int day) {
        return DateHelper._calculateDays(year, month, day);
    }

    public static long calculateDays2(int year, int month, int day) {
        return DateHelper._calculateDays2(year, month, day);
    }

    public static long calculateDays3(int year, int month, int day) {
        return DateHelper._calculateDays3(year, month, day);
    }

    //

    public static long calculateDays(int year1, int month1, int day1, int year2, int month2, int day2) {
        return DateHelper._calculateDays(year1, month1, day1, year2, month2, day2);
    }

    ////

    //// 1.1 Instance of Date/Calendar: by year, month, day, h, m, s, ms

    public static Date date(int year, int month, int day) {
        return getDate(year, month, day);
    }

    public static Date datetime(int year, int month, int day, int h, int m, int s, int ms) {
        return getDateTime(year, month, day, h, m, s, ms);
    }

    public static Date time(int h, int m, int s, int ms) {
        return getTime(h, m, s, ms);
    }

    public static Calendar calendar(int year, int month, int day, int h, int m, int s, int ms) {
        return getCalendar(year, month, day, h, m, s, ms);
    }

    //// 1.2 Instance of Date/Calendar: by time (ms)

    public static Date date(long time) {
        return getDate(time);
    }

    public static Date datetime(long time) {
        return getDateTime(time);
    }

    public static Date time(long time) {
        return getTime(time);
    }

    public static Calendar calendar(long time) {
        return getCalendar(time);
    }

    //// 1.3 Instance of Date/Calendar: current
    //// current: date, datetime, time, calendar

    public static Date date() {
        return newDate();
    }

    public static Date datetime() {
        return newDateTime();
    }

    public static Date time() {
        return newTime();
    }

    public static Calendar calendar() {
        return newCalendar();
    }

    //// 2.1 Instance of Date/Calendar: by year, month, day, h, m, s, ms
    //// getDate(year, month, day, h, m, s, ms)

    public static Date getDate(int year, int month, int day) {
        return DateHelper._getDate(year, month, day);
    }

    public static Date getDateTime(int year, int month, int day, int h, int m, int s) {
        return DateHelper._getDateTime(year, month, day, h, m, s);
    }

    public static Date getDateTime(int year, int month, int day, int h, int m, int s, int ms) {
        return DateHelper._getDateTime(year, month, day, h, m, s, ms);
    }

    public static Date getTime(int h, int m, int s) {
        return DateHelper._getTime(h, m, s);
    }

    public static Date getTime(int h, int m, int s, int ms) {
        return DateHelper._getTime(h, m, s, ms);
    }

    public static Calendar getCalendar(int year, int month, int day) {
        return DateHelper._getCalendar(year, month, day, 0, 0, 0, 0);
    }

    public static Calendar getCalendar(int year, int month, int day, int h, int m, int s) {
        return DateHelper._getCalendar(year, month, day, h, m, s, 0);
    }

    public static Calendar getCalendar(int year, int month, int day, int h, int m, int s, int ms) {
        return DateHelper._getCalendar(year, month, day, h, m, s, ms);
    }

    //// 2.2 Instance of Date/Calendar: by time (ms)
    //// getDate(time)

    public static Date getDate(long time) {
        return DateHelper._getDate(time);
    }

    public static Date getDateTime(long time) {
        return DateHelper._getDateTime(time);
    }

    public static Date getTime(long time) {
        return DateHelper._getTime(time);
    }

    public static Calendar getCalendar(long time) {
        return DateHelper._getCalendar(time);
    }

    //// 2.3 Instance of Date/Calendar: current

    public static Date newDate() {
        return DateHelper._newDate();
    }

    public static Date newDateTime() {
        return DateHelper._newDateTime();
    }

    public static Date newTime() {
        return DateHelper._newTime();
    }

    public static Calendar newCalendar() {
        return DateHelper._newCalendar();
    }

    public static Calendar getGMTCalendar() {
        return DateHelper._getGMTCalendar();
    }

    //// 3.1 Modify Date/Calendar: by get/set fields of Date/Calendar

    public static int get(Date date, int field) {
        return DateHelper._get(date, field);
    }

    public static void set(Date date, int field, int value) {
        DateHelper._set(date, field, value);
    }

    //

    public static int get(Calendar calendar, int field) {
        return DateHelper._get(calendar, field);
    }

    public static void set(Calendar calendar, int field, int value) {
        DateHelper._set(calendar, field, value);
    }

    //// 3.2 Modify Date: by year, month, day, h, m, s, ms

    public static void setDate(Date date, int year, int month, int day) {
        DateHelper._setDate(date, year, month, day);
    }

    public static void setDateTime(Date date, int year, int month, int day, int h, int m, int s) {
        DateHelper._setDateTime(date, year, month, day, h, m, s, 0);
    }

    public static void setDateTime(Date date, int year, int month, int day, int h, int m, int s, int ms) {
        DateHelper._setDateTime(date, year, month, day, h, m, s, ms);
    }

    public static void setTime(Date date, int h, int m, int s) {
        DateHelper._setTime(date, h, m, s, 0);
    }

    public static void setTime(Date date, int h, int m, int s, int ms) {
        DateHelper._setTime(date, h, m, s, ms);
    }

    //// 3.3 Modify Calendar: by year, month, day, h, m, s, ms

    public static void setDate(Calendar calendar, int year, int month, int day) {
        DateHelper._setDate(calendar, year, month, day);
    }

    public static void setDateTime(Calendar calendar, int year, int month, int day, int h, int m, int s) {
        DateHelper._setDateTime(calendar, year, month, day, h, m, s, 0);
    }

    public static void setDateTime(Calendar calendar, int year, int month, int day, int h, int m, int s, int ms) {
        DateHelper._setDateTime(calendar, year, month, day, h, m, s, ms);
    }

    public static void setTime(Calendar calendar, int h, int m, int s) {
        DateHelper._setTime(calendar, h, m, s, 0);
    }

    public static void setTime(Calendar calendar, int h, int m, int s, int ms) {
        DateHelper._setTime(calendar, h, m, s, ms);
    }

    //// 4.1 Modify Date: by get/get<Filed> (Year, Month, Day, Hour, Minute, Second,
    //// Millisecond)

    public static int getYear(Date date) {
        return DateHelper._getYear(date);
    }

    public static void setYear(Date date, int year) {
        DateHelper._setYear(date, year);
    }

    public static int getMonth(Date date) {
        return DateHelper._getMonth(date);
    }

    public static void setMonth(Date date, int month) {
        DateHelper._setMonth(date, month);
    }

    public static int getDay(Date date) {
        return DateHelper._getDay(date);
    }

    public static void setDay(Date date, int day) {
        DateHelper._setDay(date, day);
    }

    public static int getHour(Date date) {
        return DateHelper._getHour(date);
    }

    public static void setHour(Date date, int hour) {
        DateHelper._setHour(date, hour);
    }

    public static int getMinute(Date date) {
        return DateHelper._getMinute(date);
    }

    public static void setMinute(Date date, int minute) {
        DateHelper._setMinute(date, minute);
    }

    public static int getSecond(Date date) {
        return DateHelper._getSecond(date);
    }

    public static void setSecond(Date date, int second) {
        DateHelper._setSecond(date, second);
    }

    public static int getMillisecond(Date date) {
        return DateHelper._getMillisecond(date);
    }

    public static void setMillisecond(Date date, int millisecond) {
        DateHelper._setMillisecond(date, millisecond);
    }

    //// 4.2 Modify Calendar: by get/get<Filed> (Year, Month, Day, Hour, Minute,
    //// Second, Millisecond)

    public static int getYear(Calendar calendar) {
        return DateHelper._getYear(calendar);
    }

    public static void setYear(Calendar calendar, int year) {
        DateHelper._setYear(calendar, year);
    }

    public static int getMonth(Calendar calendar) {
        return DateHelper._getMonth(calendar);
    }

    public static void setMonth(Calendar calendar, int month) {
        DateHelper._setMonth(calendar, month);
    }

    public static int getDay(Calendar calendar) {
        return DateHelper._getDay(calendar);
    }

    public static void setDay(Calendar calendar, int day) {
        DateHelper._setDay(calendar, day);
    }

    public static int getHour(Calendar calendar) {
        return DateHelper._getHour(calendar);
    }

    public static void setHour(Calendar calendar, int hour) {
        DateHelper._setHour(calendar, hour);
    }

    public static int getMinute(Calendar calendar) {
        return DateHelper._getMinute(calendar);
    }

    public static void setMinute(Calendar calendar, int minute) {
        DateHelper._setMinute(calendar, minute);
    }

    public static int getSecond(Calendar calendar) {
        return DateHelper._getSecond(calendar);
    }

    public static void setSecond(Calendar calendar, int second) {
        DateHelper._setSecond(calendar, second);
    }

    static int getMillisecond(Calendar calendar) {
        return DateHelper._getMillisecond(calendar);
    }

    static void setMillisecond(Calendar calendar, int millisecond) {
        DateHelper._setMillisecond(calendar, millisecond);
    }

    //// 5.1 Shift Date/Calendar: by fields

    public static void shift(Date date, int field, int shift) {
        DateHelper._shift(date, field, shift);
    }

    public static void shift(Calendar calendar, int field, int shift) {
        DateHelper._shift(calendar, field, shift);
    }

    //// 5.2 Shift Date/Calendar: shift<Field>

    public static void shiftYear(Date date, int shiftYear) {
        DateHelper._shiftYear(date, shiftYear);
    }

    public static void shiftMonth(Date date, int shiftMonth) {
        DateHelper._shiftMonth(date, shiftMonth);
    }

    public static void shiftDay(Date date, int shiftDay) {
        DateHelper._shiftDay(date, shiftDay);
    }

    public static void shiftHour(Date date, int shiftHour) {
        DateHelper._shiftHour(date, shiftHour);
    }

    public static void shiftMinute(Date date, int shiftMinute) {
        DateHelper._shiftMinute(date, shiftMinute);
    }

    public static void shiftSecond(Date date, int shiftSecond) {
        DateHelper._shiftSecond(date, shiftSecond);
    }

    public static void shiftMillisecond(Date date, int shiftMillisecond) {
        DateHelper._shiftMillisecond(date, shiftMillisecond);
    }

    //// 5.3 Prev/Next Year, Month, Day

    public static int prevYear(Date date) {
        return DateHelper._prevYear(date);
    }

    public static int prevMonth(Date date) {
        return DateHelper._prevMonth(date);
    }

    public static int prevDay(Date date) {
        return DateHelper._prevDay(date);
    }

    //

    public static int nextYear(Date date) {
        return DateHelper._nextYear(date);
    }

    public static int nextMonth(Date date) {
        return DateHelper._nextMonth(date);
    }

    public static int nextDay(Date date) {
        return DateHelper._nextDay(date);
    }

    //// 5.4 Prev/Next Year, Month, Day

    public static int prevYear(Calendar calendar) {
        return DateHelper._prevYear(calendar);
    }

    public static int prevMonth(Calendar calendar) {
        return DateHelper._prevMonth(calendar);
    }

    public static int prevDay(Calendar calendar) {
        return DateHelper._prevDay(calendar);
    }

    //

    public static int nextYear(Calendar calendar) {
        return DateHelper._nextYear(calendar);
    }

    public static int nextMonth(Calendar calendar) {
        return DateHelper._nextMonth(calendar);
    }

    public static int nextDay(Calendar calendar) {
        return DateHelper._nextDay(calendar);
    }

    ////

    //// 5.5 Prev/Next Year, Month, Day: shift and return

    public static int prevYear(Calendar calendar, int shift) {
        return DateHelper._prevYear(calendar, shift);
    }

    public static int prevMonth(Calendar calendar, int shift) {
        return DateHelper._prevMonth(calendar, shift);
    }

    public static int prevDay(Calendar calendar, int shift) {
        return DateHelper._prevDay(calendar, shift);
    }

    //

    public static int nextYear(Calendar calendar, int shift) {
        return DateHelper._nextYear(calendar, shift);
    }

    public static int nextMonth(Calendar calendar, int shift) {
        return DateHelper._nextMonth(calendar, shift);
    }

    public static int nextDay(Calendar calendar, int shift) {
        return DateHelper._nextDay(calendar, shift);
    }

    ////

    //// 5.6 Prev/Next Year, Month, Day: by fields

    public static int prevYear(int year, int month, int day) {
        return DateHelper._prevYear(year, month, day);
    }

    public static int prevMonth(int year, int month, int day) {
        return DateHelper._prevMonth(year, month, day);
    }

    public static int prevDay(int year, int month, int day) {
        return DateHelper._prevDay(year, month, day);
    }

    //

    public static int nextYear(int year, int month, int day) {
        return DateHelper._nextYear(year, month, day);
    }

    public static int nextMonth(int year, int month, int day) {
        return DateHelper._nextMonth(year, month, day);
    }

    public static int nextDay(int year, int month, int day) {
        return DateHelper._nextDay(year, month, day);
    }

    //// 5.7 Prev/Next Date

    public static Date prevDate(Date date) {
        return DateHelper._prevDate(date);
    }

    public static Date nextDate(Date date) {
        return DateHelper._nextDate(date);
    }

    //// 5.8 getFirs/LasttDateOfMonth

    public static Date getFirstDateOfMonth(Date date) {
        return DateHelper._getFirstDateOfMonth(date);
    }

    public static Date getFirstDateOfMonth(int year, int month) {
        return DateHelper._getFirstDateOfMonth(year, month);
    }

    public static Date getLastDateOfMonth(Date date) {
        return DateHelper._getLastDateOfMonth(date);
    }

    public static Date getLastDateOfMonth(int year, int month) {
        return DateHelper._getLastDateOfMonth(year, month);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 6. Calendar info
    /////////////////////////////////////////////////////////////////////////////////

    //// 6.1 getWeek/YearDay

    // DAY_OF_WEEK

    public static int getWeekDay(Date date) {
        return DateHelper._getWeekDay(date);
    }

    public static int getWeekDay(Date date, boolean sundayFirst) {
        return DateHelper._getWeekDay(date, sundayFirst);
    }

    public static int getWeekDay(Calendar calendar) {
        return DateHelper._getWeekDay(calendar);
    }

    public static int getWeekDay(Calendar calendar, boolean sundayFirst) {
        return DateHelper._getWeekDay(calendar, sundayFirst);
    }

    public static int getWeekDay(int year, int month, int day) {
        return DateHelper._getWeekDay(year, month, day);
    }

    public static int getWeekDay(int year, int month, int day, boolean sundayFirst) {
        return DateHelper._getWeekDay(year, month, day, sundayFirst);
    }

    // DAY_OF_YEAR

    public static int getYearDay(Date date) {
        return DateHelper._getYearDay(date);
    }

    public static int getYearDay(Calendar calendar) {
        return DateHelper._getYearDay(calendar);
    }

    //

    public static int getYearDay(int year, int month, int day) {
        return DateHelper._getYearDay(year, month, day);
    }

    //// 6.2 getDaysInMonth, getDaysInYear

    public static int getDaysInMonth(Date date) {
        return DateHelper._getDaysInMonth(date);
    }

    public static int getDaysInMonth(int year, int month) {
        return DateHelper._getDaysInMonth(year, month);
    }

    public static int getDaysInYear(int year) {
        return DateHelper._getDaysInYear(year);
    }

    //// 6.3 isLeapYear

    public static boolean isLeapYear(Date date) {
        return DateHelper._isLeapYear(date);
    }

    // public static boolean isLeapYear(int year) {
    // return DateHelper._isLeapYear(year);
    // }

    /////////////////////////////////////////////////////////////////////////////////
    //// 7. Period info
    /////////////////////////////////////////////////////////////////////////////////

    //// 7.1 Date: (start, end): get Milliseconds, Seconds, Minutes, Hours, Days,
    //// Months, Years

    public static long getMilliseconds(Date start, Date end) {
        return DateHelper._getMilliseconds(start, end);
    }

    public static long getSeconds(Date start, Date end) {
        return DateHelper._getSeconds(start, end);
    }

    public static long getMinutes(Date start, Date end) {
        return DateHelper._getMinutes(start, end);
    }

    public static long getHours(Date start, Date end) {
        return DateHelper._getHours(start, end);
    }

    // DAY - 24H

    public static long getDays24H(Date start, Date end) {
        return DateHelper._getDays(start, end);
    }

    // DAY - EARTH

    public static long getDays(Date start, Date end) {
        return DateHelper._getDays(start, end);
    }

    public static long getMonths(Date start, Date end) {
        return DateHelper._getMonths(start, end);
    }

    public static long getYears(Date start, Date end) {
        return DateHelper._getYears(start, end);
    }

    //// 7.2 (y1, m1, d1, y2, m2, d2): get Milliseconds, Seconds, Minutes, Hours,
    //// Days, Months, Years

    public static long getMilliseconds(int y1, int m1, int d1, int y2, int m2, int d2) {
        return DateHelper._getMillieconds(y1, m1, d1, y2, m2, d2);
    }

    public static long getSeconds(int y1, int m1, int d1, int y2, int m2, int d2) {
        return DateHelper._getSeconds(y1, m1, d1, y2, m2, d2);
    }

    public static long getMinutes(int y1, int m1, int d1, int y2, int m2, int d2) {
        return DateHelper._getMinutes(y1, m1, d1, y2, m2, d2);
    }

    // DAY - 24H
    public static long getDays(int y1, int m1, int d1, int y2, int m2, int d2) {
        return DateHelper._getDays(y1, m1, d1, y2, m2, d2);
    }

    public static long getMonths(int y1, int m1, int d1, int y2, int m2, int d2) {
        return DateHelper._getMonths(y1, m1, d1, y2, m2, d2);
    }

    public static long getYears(int y1, int m1, int d1, int y2, int m2, int d2) {
        return DateHelper._getYears(y1, m1, d1, y2, m2, d2);
    }

    /////////////////////////////////////////////////////////////////////////////////
    // 8. Period End info
    /////////////////////////////////////////////////////////////////////////////////

    // 1970-01-01: (year, month, day, h, m, s, ms) -> millisecond, second, minute

    // 1970-01-01: (date) -> millisecond
    public static long getMillisecond(int year, int month, int day) {
        return DateHelper._getMillisecond(year, month, day);
    }

    public static long getMillisecond(int year, int month, int day, int h, int m, int s) {
        return DateHelper._getMillisecond(year, month, day, h, m, s);
    }

    public static long getMillisecond(int year, int month, int day, int h, int m, int s, int ms) {
        return DateHelper._getMillisecond(year, month, day, h, m, s, ms);
    }

    // 1970-01-01: (date) -> second

    public static long getSecond(int year, int month, int day) {
        return DateHelper._getSecond(year, month, day);
    }

    public static long getSecond(int year, int month, int day, int h, int m, int s) {
        return DateHelper._getSecond(year, month, day, h, m, s);
    }

    public static long getSecond(int year, int month, int day, int h, int m, int s, int ms) {
        return DateHelper._getSecond(year, month, day, h, m, s, ms);
    }

    // 1970-01-01: (date) -> minute

    public static long getMinute(int year, int month, int day) {
        return DateHelper._getMinute(year, month, day);
    }

    public static long getMinute(int year, int month, int day, int h, int m, int s) {
        return DateHelper._getMinute(year, month, day, h, m, s);
    }

    public static long getMinute(int year, int month, int day, int h, int m, int s, int ms) {
        return DateHelper._getMinute(year, month, day, h, m, s, ms);
    }

    // 1970-01-01: (date) -> hour

    public static long getHour(int year, int month, int day) {
        return DateHelper._getHour(year, month, day);
    }

    public static long getHour(int year, int month, int day, int h, int m, int s) {
        return DateHelper._getHour(year, month, day, h, m, s);
    }

    public static long getHour(int year, int month, int day, int h, int m, int s, int ms) {
        return DateHelper._getHour(year, month, day, h, m, s, ms);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 9. Convert
    /////////////////////////////////////////////////////////////////////////////////

    //// 9.1 Convert Date/Calendar

    public static Date toDate(Calendar calendar) {
        return DateHelper._toDate(calendar);
    }

    public static Calendar toCalendar(Date date) {
        return DateHelper._toCalendar(date);
    }

    //// 9.2 Convert time (ms) to Date/Calendar

    public static Date toDate(long time) {
        return DateHelper._toDate(time);
    }

    public static java.sql.Date toSQLDate(long time) {
        return DateHelper._toSQLDate(time);
    }

    public static java.sql.Time toSQLTime(long time) {
        return DateHelper._toSQLTime(time);
    }

    public static java.sql.Timestamp toSQLTimestamp(long time) {
        return DateHelper._toSQLTimestamp(time);
    }

    public static Calendar toCalendar(long time) {
        return DateHelper._toCalendar(time);
    }

    static LocalDate toLocalDate(long time) {
        return DateHelper._toLocalDate(time);
    }

    static LocalDateTime toLocalDateTime(long time) {
        return DateHelper._toLocalDateTime(time);
    }

    ////

    // from 1970-01-01
    public static long toMillisecond(Date date) {
        return DateHelper._toMillisecond(date);
    }

    // from 1970-01-01
    public static long toMillisecond(Calendar calendar) {
        return DateHelper._toMillisecond(calendar);
    }

    // from 1970-01-01
    static long toMillisecond(LocalDate date) {
        return DateHelper._toMillisecond(date);
    }

    // from 1970-01-01
    public static long toMillisecond(LocalDateTime date) {
        return DateHelper._toMillisecond(date);
    }

    //

    public static long toSecond(long time) {
        return DateHelper._toSecond(time);
    }

    static long toMinute(long time) {
        return DateHelper._toMinute(time);
    }

    static long toHour(long time) {
        return DateHelper._toHour(time);
    }

    static long toDay(long time) {
        return DateHelper._toDay(time);
    }

    static long toWeek(long time) {
        return DateHelper._toWeek(time);
    }

    // approximation: days in moth = 30
    static long toMonth(long time) {
        return DateHelper._toMonth(time);
    }

    // approximation: days in moth = 30
    static long toYear(long time) {
        return DateHelper._toYear(time);
    }

    ////

    public static long truncateTime(long time) {
        return (time / MILLISECONDS_PER_DAY) * (MILLISECONDS_PER_DAY);
    }

    public static long shiftTime(long time) {
        return time <= MILLISECONDS_PER_DAY ? time : (time - truncateTime(time));
    }

    ////

    public static int normalizeMonth(int month) {
        return DateHelper._normalizeMonth(month);
    }

    ////

    // Year
    public static int getCurrentYear() {
        return getYear(new Date());
    }

    // Month of Year [1..12]
    public static int getCurrentMonth() {
        return getMonth(new Date());
    }

    // Day of Month [1..31]
    public static int getCurrentDay() {
        return getDay(new Date());
    }

}
