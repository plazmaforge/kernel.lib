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

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class DateHelper implements DateConstants {

    private static final int DAYS_PER_CYCLE = 146097;

    /**
     * The number of days from year zero to year 1970. There are five 400 year
     * cycles from year zero to 2000. There are 7 leap years from 1970 to 2000.
     */
    static final long DAYS_0000_TO_1970 = (DAYS_PER_CYCLE * 5L) - (30L * 365L + 7L);

    private static TimeZone GMT_TIME_ZONE = TimeZone.getTimeZone("GMT");

    private static TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    // https://rosettacode.org/wiki/Calendar#C.2B.2B
    // https://rosettacode.org/wiki/Calendar#Java

    // https://www.geeksforgeeks.org/find-day-of-the-week-for-a-given-date/
    // https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week

    // https://stackoverflow.com/questions/40517192/c-day-of-week-for-given-date
    // https://www.tutorialspoint.com/day-of-the-week-in-cplusplus

    private DateHelper() {
    }

    /////////////////////////////////////////////////////////////////////////////////
    //
    // GENERAL INFO
    //
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Check leap year
     * 
     * @param year
     * @return
     */
    static boolean _isLeapYear(long year) {

        // We have 2 implementations:
        //
        // boolean leapYear1 = (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 ==
        // 0));
        // boolean leapYear2 = ( (year & 3) == 0) && ((year % 100) != 0 || (year % 400)
        // == 0 );
        //
        // But implementation 'leapYear2' is faster than 'leapYear1'

        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    /**
     * Return days of month (year, month)
     * 
     * @param year
     * @param month
     * @return
     */
    static int _getDaysOfMonth(int year, int month) {
        // Check leap year
        boolean leap = _isLeapYear(year);
        return _getDaysOfMonthByLeap(month, leap);
    }

    /**
     * Return days of month by 'leap' flag (year, leap)
     * 
     * @param month
     * @param leap
     * @return
     */
    static int _getDaysOfMonthByLeap(int month, boolean leap) {
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

    // Magic numbers
    static int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

    static int _getDayOfWeek(int year, int month, int day) {
        year -= (month < 3) ? 1 : 0;
        int dayOfWeek = (year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    static String _getDayNameOfWeek(int year, int month, int day) {
        int dayOfWeek = _getDayOfWeek(year, month, day);
        return DAYS_OF_WEEK[dayOfWeek - 1];
    }

    static int _getDayOfYear(int year, int month, int day) {
        if (month < 1 || day < 1) {
            return 0;
        }
        if (month == 1) {
            return day;
        }

        // long days = _calculateDays(year, 1, 1, year, month, day);
        // return (int) (days) + 1;

        int m = month - 1;
        int dayCount = YEAR_DAYS[m - 1];
        if (m > 1) {
            boolean leapYear = _isLeapYear(year);
            if (leapYear) {
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

    static long _calculateDays(int year, int month, int day) {
        if (year == 1 && month == 1 && day == 1) {
            return 0;
        }

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

    static long _calculateDays2(int year, int month, int day) {
        if (year == 1 && month == 1 && day == 1) {
            return 0;
        }

        // Get previous year
        // TODO: What about year: [1, 0, -1, -2 ...]
        int y = year - 1;

        // Calculate days in standard years (365 days):
        // ('365 days' * 'years' + '1 day' * 'leap years') = ('365 days' * 'years' +
        // 'leap years')
        // ('leap years' = years / 4 - years / 100 + years / 400)

        // int leapYearCount = y / 4 - y / 100 + y / 400;
        // int leapYearCount = y / 4 - y / 100 + y / 400;
        // int leapYearCount = (y / 4) - (y / 100) + (y / 400);
        // int dayCount = 365 * y + leapYearCount;

        long dayCount = 365 * y + y / 4 - y / 100 + y / 400;

        if (month > 1) {
            int m = month - 1;
            dayCount += YEAR_DAYS[m - 1];
            if (m > 1) {
                // See 'isLeapYear' implementation
                // boolean leapYear = ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) ==
                // 0);
                boolean leapYear = _isLeapYear(year);
                if (leapYear) {
                    dayCount++;
                }
            }
        }
        dayCount += day;
        return dayCount;
    }

    // [IMPEMENTATION-3]

    // Calculate days from '0001-01-01' to 'yyyy-mm-dd'
    //
    // Example:
    // calculateDays(1, 1, 1) = 0
    // calculateDays(1, 1, 2) = 1
    // calculateDays(1, 1, 3) = 1

    static long _calculateDays3(int year, int month, int day) {
        if (year == 1 && month == 1 && day == 1) {
            return 0;
        }
        return _toDayAD(year, month, day) - 365;
    }

    //

    /**
     * Calculate Days between date year1, month1, day1 and year2, month2 , day2
     * 
     * @param year1
     * @param month1
     * @param day1
     * @param year2
     * @param month2
     * @param day2
     * @return
     */

    static long _calculateDays(int year1, int month1, int day1, int year2, int month2, int day2) {
        // TODO
        if (year1 == year2 && month1 == month2) {
            return day2 - day1;

        }
        long days1 = _calculateDays2(year1, month1, day1);
        long days2 = _calculateDays2(year2, month2, day2);
        return days2 - days1;
    }

    // See LocalDate.toEpochDay()

    static long _toDayAD(int year, int month, int day) {
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
            if (_isLeapYear(year) == false) {
                total--;
            }
        }
        return total;
    }

    static long _toEpochDay(int year, int month, int day) {
        return _toDayAD(year, month, day) - DAYS_0000_TO_1970;
    }


    /////////////////////////////////////////////////////////////////////////////////
    //// 2. Instance of Date/Calendar
    /////////////////////////////////////////////////////////////////////////////////

    //// 2.1 Instance of Date/Calendar: by year, month, day, h, m, s, ms

    // truncate time (h, m, s, ms)
    static Date _getDate(int year, int month, int day) {
        return _getDateTime(year, month, day, 0, 0, 0, 0);
    }

    // truncate (ms)
    static Date _getDateTime(int year, int month, int day, int h, int m, int s) {
        return _getDateTime(year, month, day, h, m, s, 0);
    }

    static Date _getDateTime(int year, int month, int day, int h, int m, int s, int ms) {
        Calendar calendar = _getCalendar(year, month, day, h, m, s, ms);
        return calendar.getTime();
    }

    static Date _getTime(int h, int m, int s) {
        return _getTime(h, m, s, 0);
    }

    static Date _getTime(int h, int m, int s, int ms) {
        // Set start date 1970-01-01
        Calendar calendar = _getCalendar(START_YEAR, START_MONTH, START_DAY, h, m, s, ms);
        return calendar.getTime();
    }

    static Calendar _getCalendar(int year, int month, int day) {
        return _getCalendar(year, month, day, 0, 0, 0, 0);
    }

    static Calendar _getCalendar(int year, int month, int day, int h, int m, int s, int ms) {

        // WARNING: All date in GMT time zone
        Calendar calendar = _newCalendar(); // _getGMTCalendar();

        // Date
        _setDate(calendar, year, month, day);

        // Time
        _setTime(calendar, h, m, s, ms);

        return calendar;
    }

    //// 2.2 Instance of Date/Calendar: by time (ms)

    static Date _getDate(long time) {
        time = _truncateTime(time); // truncate
        Calendar calendar = _getCalendar(time);
        return calendar.getTime();
    }

    static Date _getDateTime(long time) {
        Calendar calendar = _getCalendar(time);
        return calendar.getTime();
    }

    static Date _getTime(long time) {
        time = _shiftTime(time); // shift
        Calendar calendar = _getCalendar(time);
        return calendar.getTime();
    }

    static Calendar _getCalendar(long time) {
        Calendar calendar = _newCalendar(); // _getGMTCalendar();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 3. Modify Date/Calendar
    /////////////////////////////////////////////////////////////////////////////////

    //// 3.1 Modify Date/Calendar: by get/set fields

    static int _get(Date date, int field) {
        checkDate(date);
        return _get(_toCalendar(date), field);
    }

    static void _set(Date date, int field, int value) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);
        _set(calendar, field, value);
        date.setTime(calendar.getTimeInMillis());
    }

    //

    static int _get(Calendar calendar, int field) {
        checkCalendar(calendar);
        return calendar.get(field);
    }

    static void _set(Calendar calendar, int field, int value) {
        checkCalendar(calendar);
        calendar.set(field, value);
    }

    //// 3.2 Modify Date: by year, month, day, h, m, s, ms

    static void _setDate(Date date, int year, int month, int day) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);
        _setYear(calendar, year);
        _setMonth(calendar, month);
        _setDay(calendar, day);

        date.setTime(calendar.getTimeInMillis());
    }

    static void _setDateTime(Date date, int year, int month, int day, int h, int m, int s, int ms) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);

        _setDate(calendar, year, month, day);
        _setTime(calendar, h, m, s, ms);
        date.setTime(calendar.getTimeInMillis());
    }

    static void _setTime(Date date, int h, int m, int s, int ms) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);

        _set(calendar, FIELD_HOUR, h);
        _set(calendar, FIELD_MINUTE, m);
        _set(calendar, FIELD_SECOND, s);
        _set(calendar, FIELD_MILLISECOND, ms);
    }

    //// 3.3 Modify Calendar: by year, month, day, h, m, s, ms

    static void _setDate(Calendar calendar, int year, int month, int day) {
        _setYear(calendar, year);
        _setMonth(calendar, month);
        _setDay(calendar, day);
    }

    static void _setDateTime(Calendar calendar, int year, int month, int day, int h, int m, int s, int ms) {
        _setDate(calendar, year, month, day);
        _setTime(calendar, h, m, s, ms);
    }

    static void _setTime(Calendar calendar, int h, int m, int s, int ms) {
        _set(calendar, FIELD_HOUR, h);
        _set(calendar, FIELD_MINUTE, m);
        _set(calendar, FIELD_SECOND, s);
        _set(calendar, FIELD_MILLISECOND, ms);
    }

    //

    static Calendar _getGMTCalendar() {
        return Calendar.getInstance(/* UTC_TIME_ZONE */ GMT_TIME_ZONE);
    }

    static Calendar _newCalendar() {
        return _newCalendar2();
        /*
         * 
         * Calendar gmtCalendar = _getGMTCalendar(); Calendar defCalendar =
         * Calendar.getInstance();
         * 
         * TimeZone timeZone = TimeZone.getDefault(); int offset =
         * timeZone.getOffset(defCalendar.getTimeInMillis()); //int offset =
         * timeZone.getRawOffset() + timeZone.getDSTSavings();
         * 
         * //TODO: DISABLE??? // set offset
         * //gmtCalendar.setTimeInMillis(gmtCalendar.getTimeInMillis() + offset);
         * 
         * return gmtCalendar;
         */
    }

    static Calendar _newCalendar2() {
        return Calendar.getInstance();
    }

    static Date _newDate() {
        // WARNING: All date in GMT time zone
        Calendar calendar = _newCalendar();

        // Reset (truncate) time
        _setTime(calendar, 0, 0, 0, 0);

        return calendar.getTime();
    }

    static Date _newDateTime() {
        // WARNING: All date in GMT time zone
        Calendar calendar = _newCalendar();

        return calendar.getTime();
    }

    static Date _newTime() {
        // WARNING: All date in GMT time zone
        Calendar calendar = _newCalendar();

        // Set start date 1970 Jan 1
        _setDate(calendar, START_YEAR, START_MONTH, START_DAY);

        return calendar.getTime();
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 4. Modify Date/Calendar: by get/get<Filed>
    /////////////////////////////////////////////////////////////////////////////////

    //// 4.1 Modify Date: by get/get<Filed> (Year, Month, Day, Hour, Minute, Second,
    //// Millisecond)

    static int _getYear(Date date) {
        return _get(date, FIELD_YEAR);
    }

    static void _setYear(Date date, int year) {
        _set(date, FIELD_YEAR, year);
    }

    static int _getMonth(Date date) {
        return _get(date, FIELD_MONTH) + 1; // month starts with 0 !!!
    }

    static void _setMonth(Date date, int month) {
        _set(date, FIELD_MONTH, month - 1); // month starts with 0 !!!
    }

    static int _getDay(Date date) {
        return _get(date, FIELD_DAY);
    }

    static void _setDay(Date date, int day) {
        _set(date, FIELD_DAY, day);
    }

    static int _getHour(Date date) {
        return _get(date, FIELD_HOUR);
    }

    static void _setHour(Date date, int hour) {
        _set(date, FIELD_HOUR, hour);
    }

    static int _getMinute(Date date) {
        return _get(date, FIELD_MINUTE);
    }

    static void _setMinute(Date date, int minute) {
        _set(date, FIELD_MINUTE, minute);
    }

    static int _getSecond(Date date) {
        return _get(date, FIELD_SECOND);
    }

    static void _setSecond(Date date, int second) {
        _set(date, FIELD_SECOND, second);
    }

    static int _getMillisecond(Date date) {
        return _get(date, FIELD_MILLISECOND);
    }

    static void _setMillisecond(Date date, int millisecond) {
        _set(date, FIELD_MILLISECOND, millisecond);
    }

    //// 4.2 Modify Calendar: by get/get<Filed> (Year, Month, Day, Hour, Minute,
    //// Second, Millisecond)

    //// Calendar/field

    static int _getYear(Calendar calendar) {
        return calendar.get(FIELD_YEAR);
    }

    static void _setYear(Calendar calendar, int year) {
        calendar.set(FIELD_YEAR, year);
    }

    static int _getMonth(Calendar calendar) {
        return calendar.get(FIELD_MONTH) + 1; // month starts with 0 !!!
    }

    static void _setMonth(Calendar calendar, int month) {
        calendar.set(FIELD_MONTH, month - 1); // month starts with 0 !!!
    }

    static int _getDay(Calendar calendar) {
        return calendar.get(FIELD_DAY);
    }

    static void _setDay(Calendar calendar, int day) {
        calendar.set(FIELD_DAY, day);
    }

    static int _getHour(Calendar calendar) {
        return calendar.get(FIELD_HOUR);
    }

    static void _setHour(Calendar calendar, int hour) {
        calendar.set(FIELD_HOUR, hour);
    }

    static int _getMinute(Calendar calendar) {
        return calendar.get(FIELD_MINUTE);
    }

    static void _setMinute(Calendar calendar, int minute) {
        calendar.set(FIELD_MINUTE, minute);
    }

    static int _getSecond(Calendar calendar) {
        return calendar.get(FIELD_SECOND);
    }

    static void _setSecond(Calendar calendar, int second) {
        calendar.set(FIELD_SECOND, second);
    }

    static int _getMillisecond(Calendar calendar) {
        return calendar.get(FIELD_MILLISECOND);
    }

    static void _setMillisecond(Calendar calendar, int millisecond) {
        calendar.set(FIELD_MILLISECOND, millisecond);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 5. Shift Date/Calendar
    /////////////////////////////////////////////////////////////////////////////////

    //// 5.1 Shift Date/Calendar: by fields

    static void _shift(Date date, int field, int shift) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);
        _set(calendar, field, _get(calendar, field) + shift);
        date.setTime(calendar.getTimeInMillis());
    }

    static void _shift(Calendar calendar, int field, int shift) {
        checkCalendar(calendar);
        _set(calendar, field, _get(calendar, field) + shift);
    }

    //// 5.2 Shift Date/Calendar: shift<Field>

    static void _shiftYear(Date date, int shiftYear) {
        _shift(date, FIELD_YEAR, shiftYear);
    }

    static void _shiftMonth(Date date, int shiftMonth) {
        _shift(date, FIELD_MONTH, shiftMonth);
    }

    static void _shiftDay(Date date, int shiftDay) {
        _shift(date, FIELD_DAY, shiftDay);
    }

    static void _shiftHour(Date date, int shiftHour) {
        _shift(date, FIELD_HOUR, shiftHour);
    }

    static void _shiftMinute(Date date, int shiftMinute) {
        _shift(date, FIELD_MINUTE, shiftMinute);
    }

    static void _shiftSecond(Date date, int shiftSecond) {
        _shift(date, FIELD_SECOND, shiftSecond);
    }

    static void _shiftMillisecond(Date date, int shiftMillisecond) {
        _shift(date, FIELD_MILLISECOND, shiftMillisecond);
    }

    //// 5.3 Prev/Next Year, Month, Day

    static int _prevYear(Date date) {
        Calendar calendar = _toCalendar(date);
        return _prevYear(calendar);
    }

    static int _prevMonth(Date date) {
        Calendar calendar = _toCalendar(date);
        return _prevMonth(calendar);
    }

    static int _prevDay(Date date) {
        Calendar calendar = _toCalendar(date);
        return _prevDay(calendar);
    }

    //

    static int _nextYear(Date date) {
        Calendar calendar = _toCalendar(date);
        return _nextYear(calendar);
    }

    static int _nextMonth(Date date) {
        Calendar calendar = _toCalendar(date);
        return _nextMonth(calendar);
    }

    static int _nextDay(Date date) {
        Calendar calendar = _toCalendar(date);
        return _nextDay(calendar);
    }

    //// 5.4 Prev/Next Year, Month, Day

    static int _prevYear(Calendar calendar) {
        checkCalendar(calendar);
        int currYear = _getYear(calendar); // fixed year by calendar
        return --currYear;
    }

    static int _prevMonth(Calendar calendar) {
        checkCalendar(calendar);
        int currMonth = _getMonth(calendar); // fixed month by calendar
        return currMonth == 1 ? 12 : --currMonth;
    }

    static int _prevDay(Calendar calendar) {
        checkCalendar(calendar);
        _shift(calendar, FIELD_DAY, -1);
        return _getDay(calendar);
    }

    //

    static int _nextYear(Calendar calendar) {
        checkCalendar(calendar);
        int currYear = _getYear(calendar); // fixed year by calendar
        return ++currYear;
    }

    static int _nextMonth(Calendar calendar) {
        checkCalendar(calendar);
        int currMonth = _getMonth(calendar); // fixed month by calendar
        return currMonth == 12 ? 1 : ++currMonth;
    }

    static int _nextDay(Calendar calendar) {
        checkCalendar(calendar);
        _shift(calendar, FIELD_DAY, 1);
        return _getDay(calendar);
    }

    //// 5.5 Prev/Next Year, Month, Day: shift and return

    static int _prevYear(Calendar calendar, int shift) {
        checkCalendar(calendar);
        int currYear = _getYear(calendar); // fixed year by calendar
        currYear -= shift;
        return currYear;
    }

    static int _prevMonth(Calendar calendar, int shift) {
        checkCalendar(calendar);
        int currMonth = _getMonth(calendar); // fixed month by calendar
        currMonth -= shift;
        return _normalizeMonth(currMonth);
    }

    static int _prevDay(Calendar calendar, int shift) {
        checkCalendar(calendar);
        _shift(calendar, FIELD_DAY, -1 * (shift));
        return _getDay(calendar);
    }

    //

    static int _nextYear(Calendar calendar, int shift) {
        checkCalendar(calendar);
        int currYear = _getYear(calendar); // fixed year by calendar
        currYear += shift;
        return currYear;
    }

    static int _nextMonth(Calendar calendar, int shift) {
        checkCalendar(calendar);
        int currMonth = _getMonth(calendar); // fixed month by calendar
        currMonth += shift;
        return _normalizeMonth(currMonth);
    }

    static int _nextDay(Calendar calendar, int shift) {
        checkCalendar(calendar);
        _shift(calendar, FIELD_DAY, shift);
        return _getDay(calendar);
    }

    static int _normalizeMonth(int month) {
        int mod = (month % 12);
        return mod < 1 ? (12 + mod) : mod;
    }

    ////

    //// 5.6 Prev/Next Year, Month, Day: by fields

    static int _prevYear(int year, int month, int day) {
        Calendar calendar = _getCalendar(year, month, day);
        return _prevYear(calendar);
    }

    static int _prevMonth(int year, int month, int day) {
        Calendar calendar = _getCalendar(year, month, day);
        return _prevMonth(calendar);
    }

    static int _prevDay(int year, int month, int day) {
        Calendar calendar = _getCalendar(year, month, day);
        return _prevDay(calendar);
    }

    //

    static int _nextYear(int year, int month, int day) {
        Calendar calendar = _getCalendar(year, month, day);
        return _nextYear(calendar);
    }

    static int _nextMonth(int year, int month, int day) {
        Calendar calendar = _getCalendar(year, month, day);
        return _nextMonth(calendar);
    }

    static int _nextDay(int year, int month, int day) {
        Calendar calendar = _getCalendar(year, month, day);
        return _nextDay(calendar);
    }

    //// 5.7 Prev/Next Date

    static Date _prevDate(Date date) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);
        _prevDay(calendar);
        return calendar.getTime();
    }

    static Date _nextDate(Date date) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);
        _nextDay(calendar);
        return calendar.getTime();
    }

    //// 5.8 getFirst/LastDateOfMonth

    static Date _getFirstDateOfMonth(Date date) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);
        int year = _getYear(calendar);
        int month = _getMonth(calendar);
        return _getFirstDateOfMonth(year, month);
    }

    static Date _getFirstDateOfMonth(int year, int month) {
        return _getDate(year, month, 1);
    }

    //

    static Date _getLastDateOfMonth(Date date) {
        checkDate(date);
        Calendar calendar = _toCalendar(date);
        int year = _getYear(calendar);
        int month = _getMonth(calendar);
        return _getLastDateOfMonth(year, month);
    }

    static Date _getLastDateOfMonth(int year, int month) {
        if (month == 12) {
            year = year + 1;
            month = 1;
        } else {
            month++;
        }
        return _prevDate(_getDate(year, month, 1));
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 6. Calendar info
    /////////////////////////////////////////////////////////////////////////////////

    //// 6.1 getWeekDay, getYearDay

    // DAY_OF_WEEK

    static int _getWeekDay(Date date) {
        return _getWeekDay(date, false);
    }

    static int _getWeekDay(Date date, boolean sundayFirst) {
        Calendar calendar = _toCalendar(date);
        return _getWeekDay(calendar, sundayFirst);
    }

    static int _getWeekDay(Calendar calendar) {
        return _getWeekDay(calendar, false);
    }

    static int _getWeekDay(Calendar calendar, boolean sundayFirst) {
        int dayOfWeek = _get(calendar, Calendar.DAY_OF_WEEK);
        if (sundayFirst) {
            return dayOfWeek;
        }
        return dayOfWeek == 1 ? 7 : dayOfWeek - 1;
    }

    static int _getWeekDay(int year, int month, int day) {
        return _getWeekDay(year, month, day, false);
    }

    static int _getWeekDay(int year, int month, int day, boolean sundayFirst) {
        Calendar calendar = _getCalendar(year, month, day);
        return _getWeekDay(calendar, sundayFirst);
    }

    // DAY_OF_YEAR

    static int _getYearDay(Date date) {
        Calendar calendar = _toCalendar(date);
        return _getYearDay(calendar);
    }

    static int _getYearDay(Calendar calendar) {
        int dayOfYear = _get(calendar, Calendar.DAY_OF_YEAR);
        return dayOfYear;
    }

    static int _getYearDay(int year, int month, int day) {
        Calendar calendar = _getCalendar(year, month, day);
        return _getYearDay(calendar);
    }

    //// 6.2 getDaysInMonth, getDaysInYear

    static int _getDaysInMonth(Date date) {
        checkDate(date);
        Date lastDate = _getLastDateOfMonth(date);
        return _getDay(lastDate);
    }

    static int _getDaysInMonth(int year, int month) {
        Date lastDate = _getLastDateOfMonth(year, month);
        return _getDay(lastDate);
    }

    static int _getDaysInYear(int year) {
        boolean leap = _isLeapYear(year);
        return leap ? 366 : 365;
    }

    //// 6.3 isLeapYear

    static boolean _isLeapYear(Date date) {
        checkDate(date);

        Calendar calendar = _toCalendar(date);
        int year = _getYear(calendar);
        return _isLeapYear(year);
    }

    static boolean _isLeapYear(Calendar calendar) {
        checkCalendar(calendar);
        int year = _getYear(calendar);
        return _isLeapYear(year);
    }

    static boolean _isLeapYear2(Date date) {
        checkDate(date);

        Calendar calendar = _toCalendar(date);
        int year = _getYear(calendar);

        YearMonth yearMonth = YearMonth.of(year, 1);
        return yearMonth.isLeapYear();
    }

    static boolean _isLeapYear3(Date date) {
        checkDate(date);

        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        int year = _getYear(calendar);
        return calendar.isLeapYear(year);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 7. Period info
    /////////////////////////////////////////////////////////////////////////////////

    //// 7.1 Date: (start, end): get Milliseconds, Seconds, Minutes, Hours, Days,
    //// Months, Years

    static long _getMilliseconds(Date start, Date end) {
        checkDatePariod(start, end);
        return _toMillisecond(end) - _toMillisecond(start);
    }

    static long _getSeconds(Date start, Date end) {
        return _toSecond(_getMilliseconds(start, end));
    }

    static long _getMinutes(Date start, Date end) {
        return _toMinute(_getMilliseconds(start, end));
    }

    static long _getHours(Date start, Date end) {
        return _toHour(_getMilliseconds(start, end));
    }

    // DAY - 24H

    static long _getDays24H(Date start, Date end) {
        return _toDay(_getMilliseconds(start, end));
    }

    // DAY - EARTH

    static long _getDays(Date start, Date end) {

        checkDatePariod(start, end);

        Calendar c1 = _toCalendar(start);
        Calendar c2 = _toCalendar(end);

        int y1 = _getYear(c1);
        int y2 = _getYear(c2);

        int m1 = _getMonth(c1);
        int m2 = _getMonth(c2);

        int d1 = _getDay(c1);
        int d2 = _getDay(c2);

        long days1 = _calculateDays2(y1, m1, d1);
        long days2 = _calculateDays2(y2, m2, d2);
        return days2 - days1;
    }

    //

    static long _getMonths(Date start, Date end) {
        checkDatePariod(start, end);

        Calendar c1 = _toCalendar(start);
        Calendar c2 = _toCalendar(end);

        int y1 = _getYear(c1);
        int y2 = _getYear(c2);

        int m1 = _getMonth(c1);
        int m2 = _getMonth(c2);

        int d1 = _getDay(c1);
        int d2 = _getDay(c2);

        int count = 0;
        if (y1 == y2) {
            if (m1 == m2) {
                return 0;
            }
            count = m2 - m1 - 1 + _countNMonth(d1, d2);
        } else {
            if (y2 - y1 == 1) {
                count = (12 - m1) + (m2 - 1 + _countNMonth(d1, d2)); // before + after
            } else {
                int y = y2 - y1 - 1;
                count = (12 - m1) + (y * 12 - 1) + (m2 - 1 + _countNMonth(d1, d2)); // before + full + after
            }
        }
        return count;
    }

    static long _getYears(Date start, Date end) {
        long months = _getMonths(start, end);
        return months / 12;
    }

    //// 7.2 (y1, m1, d1, y2, m2, d2): get Milliseconds, Seconds, Minutes, Hours,
    //// Days, Months, Years

    static long _getMillieconds(int y1, int m1, int d1, int y2, int m2, int d2) {
        Date date1 = _getDate(y1, m1, d1);
        Date date2 = _getDate(y2, m2, d2);
        return _getMilliseconds(date1, date2);
    }

    static long _getSeconds(int y1, int m1, int d1, int y2, int m2, int d2) {
        Date date1 = _getDate(y1, m1, d1);
        Date date2 = _getDate(y2, m2, d2);
        return _getSeconds(date1, date2);
    }

    static long _getMinutes(int y1, int m1, int d1, int y2, int m2, int d2) {
        Date date1 = _getDate(y1, m1, d1);
        Date date2 = _getDate(y2, m2, d2);
        return _getMinutes(date1, date2);
    }

    static long _getHours(int y1, int m1, int d1, int y2, int m2, int d2) {
        Date date1 = _getDate(y1, m1, d1);
        Date date2 = _getDate(y2, m2, d2);
        return _getHours(date1, date2);
    }

    static long _getDays(int y1, int m1, int d1, int y2, int m2, int d2) {
        Date date1 = _getDate(y1, m1, d1);
        Date date2 = _getDate(y2, m2, d2);
        return _getDays(date1, date2);
    }

    static long _getMonths(int y1, int m1, int d1, int y2, int m2, int d2) {
        Date date1 = _getDate(y1, m1, d1);
        Date date2 = _getDate(y2, m2, d2);
        return _getMonths(date1, date2);
    }

    static long _getYears(int y1, int m1, int d1, int y2, int m2, int d2) {
        Date date1 = _getDate(y1, m1, d1);
        Date date2 = _getDate(y2, m2, d2);
        return _getYears(date1, date2);
    }

    ////

    static int _countNMonth(int d1, int d2) {
        return d2 >= d1 ? 1 : 0;
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 8. Period End info
    /////////////////////////////////////////////////////////////////////////////////

    // 1970-01-01: (year, month, day, h, m, s, ms) -> millisecond, second, minute

    // 1970-01-01: (date) -> millisecond
    static long _getMillisecond(int year, int month, int day) {
        Date date = _getDate(year, month, day);
        return _toMillisecond(date);
    }

    static long _getMillisecond(int year, int month, int day, int h, int m, int s) {
        Date date = _getDateTime(year, month, day, h, m, s);
        return _toMillisecond(date);
    }

    static long _getMillisecond(int year, int month, int day, int h, int m, int s, int ms) {
        Date date = _getDateTime(year, month, day, h, m, s, ms);
        return _toMillisecond(date);
    }

    // 1970-01-01: (date) -> second

    static long _getSecond(int year, int month, int day) {
        long time = _getMillisecond(year, month, day);
        return _toSecond(time);
    }

    static long _getSecond(int year, int month, int day, int h, int m, int s) {
        long time = _getMillisecond(year, month, day, h, m, s);
        return _toSecond(time);
    }

    static long _getSecond(int year, int month, int day, int h, int m, int s, int ms) {
        long time = _getMillisecond(year, month, day, h, m, s, ms);
        return _toSecond(time);
    }

    // 1970-01-01: (date) -> minute

    static long _getMinute(int year, int month, int day) {
        long time = _getMillisecond(year, month, day);
        return _toMinute(time);
    }

    static long _getMinute(int year, int month, int day, int h, int m, int s) {
        long time = _getMillisecond(year, month, day, h, m, s);
        return _toMinute(time);
    }

    static long _getMinute(int year, int month, int day, int h, int m, int s, int ms) {
        long time = _getMillisecond(year, month, day, h, m, s, ms);
        return _toMinute(time);
    }

    // 1970-01-01: (date) -> hour

    static long _getHour(int year, int month, int day) {
        long time = _getMillisecond(year, month, day);
        return _toHour(time);
    }

    static long _getHour(int year, int month, int day, int h, int m, int s) {
        long time = _getMillisecond(year, month, day, h, m, s);
        return _toHour(time);
    }

    static long _getHour(int year, int month, int day, int h, int m, int s, int ms) {
        long time = _getMillisecond(year, month, day, h, m, s, ms);
        return _toHour(time);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //// 9. Convert
    /////////////////////////////////////////////////////////////////////////////////

    //// 9.1 Convert Date/Calendar

    static Date _toDate(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.getTime();
    }

    static Calendar _toCalendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = _newCalendar();
        calendar.setTime(date);
        return calendar;
    }

    //// 9.2 Convert time (ms) to Date/Calendar

    static Date _toDate(long time) {
        return new Date(time);
    }

    static java.sql.Date _toSQLDate(long time) {
        return new java.sql.Date(time);
    }

    static java.sql.Time _toSQLTime(long time) {
        return new java.sql.Time(time);
    }

    static java.sql.Timestamp _toSQLTimestamp(long time) {
        return new java.sql.Timestamp(time);
    }

    static Calendar _toCalendar(long time) {
        Calendar calendar = _newCalendar();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    static LocalDate _toLocalDate(long time) {
        // return LocalDate.oofInstant(Instant.ofEpochMilli(time), ZoneOffset.UTC);
        return LocalDate.ofEpochDay(time / 86400000L);
    }

    static LocalDateTime _toLocalDateTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.UTC);
    }

    ////

    // from 1970-01-01
    static long _toMillisecond(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

    // from 1970-01-01
    static long _toMillisecond(Calendar calendar) {
        if (calendar == null) {
            return 0;
        }
        return calendar.getTimeInMillis();
    }

    // from 1970-01-01
    static long _toMillisecond(LocalDate date) {
        if (date == null) {
            return 0;
        }
        // return date.atStartOfDay().toInstant().toEpochMilli();
        return date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    // from 1970-01-01
    static long _toMillisecond(LocalDateTime date) {
        if (date == null) {
            return 0;
        }
        return date.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    //

    static long _toSecond(long time) {
        return time / MILLISECONDS_PER_SECOND;
    }

    static long _toMinute(long time) {
        return time / MILLISECONDS_PER_MINUTE;
    }

    static long _toHour(long time) {
        return time / MILLISECONDS_PER_HOUR;
    }

    static long _toDay(long time) {
        return time / MILLISECONDS_PER_DAY;
    }

    static long _toWeek(long time) {
        return time / MILLISECONDS_PER_WEEK;
    }

    // approximation: days in moth = 30
    static long _toMonth(long time) {
        return time / MILLISECONDS_PER_MONTH;
    }

    // approximation: days in moth = 30
    static long _toYear(long time) {
        return time / MILLISECONDS_PER_YEAR;
    }

    ////

    static long _truncateTime(long time) {
        return (time / MILLISECONDS_PER_DAY) * (MILLISECONDS_PER_DAY);
    }

    static long _shiftTime(long time) {
        return time <= MILLISECONDS_PER_DAY ? time : (time - _truncateTime(time));
    }

    static void checkDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must be not null");
        }
    }

    static void checkCalendar(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException("Calendar must be not null");
        }
    }

    static void checkDatePariod(Date start, Date end) {
        if (start == null && end == null) {
            throw new IllegalArgumentException("Start/End date must be not null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start date must be not null");

        }
        if (end == null) {
            throw new IllegalArgumentException("End date must be not null");
        }
        if (start.getTime() > end.getTime()) {
            throw new IllegalArgumentException("End date must be > Start date");
        }
    }

}
