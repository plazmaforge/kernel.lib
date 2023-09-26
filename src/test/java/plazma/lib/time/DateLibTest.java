package plazma.lib.time;

import java.util.Date;
import java.time.LocalDate;
import java.time.Year;

import plazma.lib.AbstractTestCase;
import plazma.lib.time.DateLib;

public class DateLibTest extends AbstractTestCase {

    public void testNow() {

        Date currDate = DateLib.date();
        Date currTime = DateLib.time();

        println("Curr Date: " + currDate);
        println("Curr Time: " + currTime);

    }

    public void testDays() {
        println(DateLib.getDays(1974, 7, 27, 2019, 12, 19));
        println(DateLib.getDays(2019, 12, 19, 2019, 12, 19));
        println(DateLib.getDays(2019, 12, 18, 2019, 12, 19));
        println();
        println(DateLib.getYears(1974, 7, 27, 2019, 12, 19));
        println(DateLib.getMonths(1974, 7, 27, 2019, 12, 19));
        println();
        println(DateLib.getSeconds(1900, 1, 1, 1974, 7, 27));
        println(DateLib.getDays(1900, 1, 1, 1974, 7, 27));
        // println(DateLib.getMilliseconds(1900, 1, 1, 1974, 7, 27));

        println(DateLib.toMillisecond(LocalDate.of(1974, 7, 27)));
        println(DateLib.getMilliseconds(1970, 1, 1, 1974, 7, 27));

        LocalDate localDate1 = LocalDate.of(1974, 7, 27);
        long localTimestamp = DateLib.toMillisecond(localDate1);
        LocalDate localDate2 = DateLib.toLocalDate(localTimestamp);

        println();
        println(localTimestamp);
        println(localDate1);
        println(localDate2);

        //

        assertEquals(19, DateLib.prevDay(2019, 12, 20));
        assertEquals(29, DateLib.prevDay(2019, 11, 30));
        assertEquals(30, DateLib.prevDay(2019, 12, 31));

        assertEquals(11, DateLib.prevMonth(2019, 12, 20));
        assertEquals(10, DateLib.prevMonth(2019, 11, 30));
        assertEquals(11, DateLib.prevMonth(2019, 12, 31));

        assertEquals(2020, DateLib.nextYear(2019, 12, 20));
        assertEquals(2020, DateLib.nextYear(2019, 11, 30));
        assertEquals(2020, DateLib.nextYear(2019, 12, 31));

        //

        assertEquals(21, DateLib.nextDay(2019, 12, 20));
        assertEquals(1, DateLib.nextDay(2019, 11, 30));
        assertEquals(1, DateLib.nextDay(2019, 12, 31));

        assertEquals(1, DateLib.nextMonth(2019, 12, 20));
        assertEquals(12, DateLib.nextMonth(2019, 11, 30));
        assertEquals(1, DateLib.nextMonth(2019, 12, 31));

        assertEquals(2020, DateLib.nextYear(2019, 12, 20));
        assertEquals(2020, DateLib.nextYear(2019, 11, 30));
        assertEquals(2020, DateLib.nextYear(2019, 12, 31));

        //

        println();
        println("nextDay");

        println("2019-12-20: " + DateLib.nextDay(2019, 12, 20));
        println("2019-11-30: " + DateLib.nextDay(2019, 11, 30));
        println("2019-12-31: " + DateLib.nextDay(2019, 12, 31));

        //

        assertEquals(19, DateLib.prevDay(DateLib.getDate(2019, 12, 20)));
        assertEquals(29, DateLib.prevDay(DateLib.getDate(2019, 11, 30)));
        assertEquals(30, DateLib.prevDay(DateLib.getDate(2019, 12, 31)));

        assertEquals(11, DateLib.prevMonth(DateLib.getDate(2019, 12, 20)));
        assertEquals(10, DateLib.prevMonth(DateLib.getDate(2019, 11, 30)));
        assertEquals(11, DateLib.prevMonth(DateLib.getDate(2019, 12, 31)));

        assertEquals(2020, DateLib.nextYear(DateLib.getDate(2019, 12, 20)));
        assertEquals(2020, DateLib.nextYear(DateLib.getDate(2019, 11, 30)));
        assertEquals(2020, DateLib.nextYear(DateLib.getDate(2019, 12, 31)));

        //

        println(DateLib.prevDate(DateLib.getDate(2019, 12, 20)));
        println(DateLib.nextDate(DateLib.getDate(2019, 12, 20)));

        //

        assertEquals(6, DateLib.getWeekDay(DateLib.getDate(2019, 12, 21)));
        assertEquals(7, DateLib.getWeekDay(DateLib.getDate(2019, 12, 22)));
        assertEquals(1, DateLib.getWeekDay(DateLib.getDate(2019, 12, 23)));
        assertEquals(2, DateLib.getWeekDay(DateLib.getDate(2019, 12, 24)));

        println(DateLib.getYearDay(DateLib.getDate(2019, 12, 20)));

        println(DateLib.getFirstDateOfMonth(2019, 12));
        println(DateLib.getLastDateOfMonth(2019, 12));

        // for (int year = 1; year < 3000; year++) {
        // if (DateLib.isLeapYear(year) ) {
        // println(">> " + year);
        // }
        // }

        for (int year = 1974; year < 2020; year++) {
            for (int month = 1; month <= 12; month++) {
                Date d = DateLib.getDate(year, month, 13);
                int weekDay = DateLib.getWeekDay(d);
                if (weekDay == 5) {
                    println(">> " + d);
                }
            }
        }

        println("Normalize Month:");
        int month = 0;
        for (month = -30; month <= 30; month++) {
            println("" + month + ": " + (month % 12) + ": " + DateLib.normalizeMonth(month));
        }

    }

    public void testLeapYear() {
        int MAX_YEAR = 10000;
        for (int year = 1; year <= MAX_YEAR; year++) {
            boolean leapYear1 = DateLib.isLeapYear(year); // 1. DateLib
            boolean leapYear2 = Year.isLeap(year); // 2. Java
            assertTrue(leapYear1 == leapYear2);
        }
    }

    public void testDayOfWeek() {
        int MAX_YEAR = 10000;
        for (int year = 1; year <= MAX_YEAR; year++) {
            boolean leapYear = DateLib.isLeapYear(year);

            for (int month = 1; month <= 12; month++) {
                int days = DateLib.DAYS[month - 1];
                if (month == 2 && leapYear) {
                    days = 29; // 29 days
                }
                for (int day = 1; day <= days; day++) {
                    int dayOfWeek1 = DateLib.getDayOfWeek(year, month, day);

                    LocalDate date = LocalDate.of(year, month, day);
                    int dayOfWeek2 = date.getDayOfWeek().getValue();

                    assertEquals(dayOfWeek1, dayOfWeek2);
                }

            }

        }
    }

    public void testDayOfYear() {
        int MAX_YEAR = 10000;
        for (int year = 1; year <= MAX_YEAR; year++) {
            boolean leapYear = DateLib.isLeapYear(year);

            for (int month = 1; month <= 12; month++) {
                int days = DateLib.DAYS[month - 1];
                if (month == 2 && leapYear) {
                    days = 29; // 29 days
                }
                for (int day = 1; day <= days; day++) {
                    int dayOfYear1 = DateLib.getDayOfYear(year, month, day);

                    LocalDate date = LocalDate.of(year, month, day);
                    int dayOfYear2 = date.getDayOfYear();

                    if (dayOfYear1 != dayOfYear2) {
                        System.out.println("LOCAL_DATE_1: " + date);
                        DateLib.getDayOfYear(year, month, day);

                    }

                    assertEquals(dayOfYear1, dayOfYear2);
                }

            }

        }
    }

    public void testCalculateDays() {

        // long days1 = DateLib.calculateDays(2020, 9, 21);
        // long days2 = DateLib.getDays(1, 1, 1, 2020, 9, 21);

        // long days1 = DateLib.calculateDays(1974, 7, 27);
        // long days2 = DateLib.getDays(1, 1, 1, 1974, 7, 27);

        long days1 = DateLib.calculateDays2(2000, 1, 1);
        long days2 = DateLib.getDays(1, 1, 1, 2000, 1, 1);
        long days3 = DateLib.getDays(1, 1, 1, 2000, 1, 1);

        System.out.println("Days1: " + days1);
        System.out.println("Days2: " + days2);

        int leapYearCount1 = 0;
        int leapYearCount3 = 0;

//	DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	for (int y = 1; y <= 1999; y++) {
//	    if (Year.isLeap(y)) {
//		
//	    
//	    //if (DateLib.isLeapYear(y)) {
//		leapYearCount1++;
//	    }
//	    
//		String z = "" + y;
//		if (y < 10) {
//		    z = "000" + y;
//		} else if (y < 100) {
//		    z = "00" + y;
//		} else if (y < 1000 ) {
//		    z = "0" + y;
//		}
//
//	    
//	    LocalDate d = LocalDate.parse("" + z + "-01-01", df); 
//	    // import java.time.LocalDate;
//	    leapYearCount3 += d.lengthOfYear(); 
//	    
//	}

        int y = 1999;
        int leapYearCount2 = (y / 4) - (y / 100) + (y / 400);

        System.out.println("LeapYearCount1: " + leapYearCount1);
        System.out.println("LeapYearCount2: " + leapYearCount2);
        System.out.println("LeapYearCount3: " + leapYearCount3);

        int TEST_COUNT = 100000;

        System.out.println("toEpochDay: " + (DateLib.calculateDays3(1, 1, 1)));
        System.out.println("toEpochDay: " + (DateLib.calculateDays3(2000, 1, 1)));
        System.out.println("toEpochDay: " + (DateLib.calculateDays(2020, 9, 21) - DateLib.calculateDays(1974, 7, 27)));

        System.out.println("toEpochDay: " + DateLib.calculateDays(1974, 7, 27, 2020, 9, 21));

        for (int year = 1; year <= TEST_COUNT; year++) {
            boolean leapYear = DateLib.isLeapYear(year);
            // boolean leapYear = Year.isLeap(year);
            for (int month = 1; month <= 12; month++) {
                int days = DateLib.DAYS[month - 1];
                if (month == 2 && leapYear) {
                    days = 29; // 29 days
                }
                for (int day = 1; day <= days; day++) {

                    days1 = DateLib.calculateDays(year, month, day);
                    days2 = DateLib.calculateDays2(year, month, day);
                    days3 = DateLib.calculateDays3(year, month, day);

                    if (!(days1 == days2 && days1 == days3)) {
                        System.out.println(">>>>>>> " + leapYear + " " + year + "-" + month + "-" + day + ": " + days1
                                + ":" + days2 + ":" + days3);
                    }

                }

            }
        }

    }

    // public void testPrintCalendar() {
    // DateLib.printYearCalendar(2020, false);
    // }
}
