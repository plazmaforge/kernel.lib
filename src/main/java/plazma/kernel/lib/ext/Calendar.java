package plazma.kernel.lib.ext;

public class Calendar {

    public static final int MONTH_COUNT = 12;

    public static final int[] MONTH_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    public static final int YEAR_DAYS = 365;
    public static final int YEAR_DAYS_LEAP = 366;

    public static boolean isLeapYear(int year) {
        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    public static int[] getMonthDays(int year) {
        boolean leapYear = isLeapYear(year);
        int[] days = new int[MONTH_COUNT];
        for (int i = 0; i < MONTH_COUNT; i++) {
            days[i] = MONTH_DAYS[i];
            if (leapYear && i == 1) { // February
                days[i] = 29;
            }
        }
        return days;
    }

    public static int getYearDays(int year) {
        boolean leapYear = isLeapYear(year);
        return leapYear ? YEAR_DAYS_LEAP : YEAR_DAYS;
    }

    public static int getDateInstant(int year, int month, int day) {

        if (year < 1970) {
            // TODO
            throw new IllegalAccessError("Not implemented yet");
        }

        // int yearDelta = year - 1970;
        int totalDays = 0;
        int days = 0;

        // Add days of previous years
        if (year > 1970) {
            for (int y = 1970; y < year; y++) {
                days = getYearDays(y);
                totalDays += days;
            }
        } else if (year < 1970) {

            // TODO: -totalYears (?)
            for (int y = year; y < 1970; y++) {
                days = getYearDays(y);
                totalDays += days;
            }
        }

        // Add days of previous months
        if (month > 1) {
            boolean leapYear = isLeapYear(year);
            for (int m = 1; m < month; m++) {
                days = MONTH_DAYS[m - 1];
                if (leapYear && m == 2) {
                    days = 29;
                }

                totalDays += days;

            }
        }

        // Add days of current month
        totalDays += day;

        // Convert days to milliseconds
        return totalDays * 24 * 60 * 60 * 1000;

    }

}
