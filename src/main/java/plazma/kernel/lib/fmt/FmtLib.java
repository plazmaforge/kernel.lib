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

package plazma.kernel.lib.fmt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import plazma.kernel.lib.str.StrLib;

public class FmtLib {

    // Functions:

    /////////////////////////////////////////////////////////////////////////////////
    //
    // Common
    //
    // 1.1
    //
    // - String format(String message, Object... args)
    //

    /////////////////////////////////////////////////////////////////////////////////
    //
    // Date/Time/DateTime
    //
    // 2.1
    //
    // - String formatDate()
    // - String formatDate(String format)
    // - String formatDate(DateFormat formatter)
    //
    // - String formatDate(Date date)
    // - String formatDate(Date date, String format)
    // - String formatDate(Date date, DateFormat formatter)
    //
    // 2.2
    //
    // - String formatTime()
    // - String formatTime(String format)
    // - String formatTime(DateFormat formatter)
    //
    // - String formatTime(Date date)
    // - String formatTime(Date date, String format)
    // - String formatTime(Date date, DateFormat formatter)
    //
    // 2.3
    //
    // - String formatDateTime()
    // - String formatDateTime(String format)
    // - String formatDateTime(DateFormat formatter)
    //
    // - String formatDateTime(Date date)
    // - String formatDateTime(Date date, String format)
    // - String formatDateTime(Date date, DateFormat formatter)

    /////////////////////////////////////////////////////////////////////////////////
    //
    // Date/Time/DateTimeFormatter
    //
    // 3.1
    //
    // - DateFormat getDateFormatter(String format)
    // - DateFormat getTimeFormatter(String format)
    // - DateFormat getDateTimeFormatter(String format)

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String EMPTY_FORMAT_VALUE = "";
    private static final boolean USE_NORMALIZE_FORMAT = true;

    private static final Map<String, String> dateFormats = new HashMap<>();

    static {

        ////////////////////////////////////////////////////////////////
        //
        // Date
        //
        ////////////////////////////////////////////////////////////////

        // ISO 8601
        dateFormats.put("YYYY-MM-DD", "yyyy-MM-dd");
        dateFormats.put("YYYY.MM.DD", "yyyy.MM.dd");
        dateFormats.put("DD.MM.YYYY", "dd.MM.yyyy");
        dateFormats.put("DD/MM/YYYY", "dd/MM/yyyy");
        dateFormats.put("MM/DD/YYYY", "MM/dd/yyyy");

        // Unicode
        dateFormats.put("yyyy-MM-dd", "yyyy-MM-dd");
        dateFormats.put("yyyy.MM.dd", "yyyy.MM.dd");
        dateFormats.put("dd.MM.yyyy", "dd.MM.yyyy");
        dateFormats.put("dd/MM/yyyy", "dd/MM/yyyy");
        dateFormats.put("MM/dd/yyyy", "MM/dd/yyyy");

        ////////////////////////////////////////////////////////////////
        //
        // Time
        //
        ////////////////////////////////////////////////////////////////

        // ISO 8601
        dateFormats.put("hh:mm:ss", "HH:mm:ss");

        // Unicode
        dateFormats.put("HH:mm:ss", "HH:mm:ss");

        ////////////////////////////////////////////////////////////////
        //
        // DateTime
        //
        ////////////////////////////////////////////////////////////////

        // ISO 8601
        dateFormats.put("YYYY-MM-DD hh:mm:ss", "yyyy-MM-dd HH:mm:ss");
        dateFormats.put("YYYY.MM.DD hh:mm:ss", "yyyy.MM.dd HH:mm:ss");
        dateFormats.put("DD.MM.YYYY hh:mm:ss", "dd.MM.yyyy HH:mm:ss");
        dateFormats.put("DD/MM/YYYY hh:mm:ss", "dd/MM/yyyy HH:mm:ss");
        dateFormats.put("MM/DD/YYYY hh:mm:ss", "MM/dd/yyyy HH:mm:ss");

        // Unicode
        dateFormats.put("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss");
        dateFormats.put("yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss");
        dateFormats.put("dd.MM.yyyy HH:mm:ss", "dd.MM.yyyy HH:mm:ss");
        dateFormats.put("dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm:ss");
        dateFormats.put("MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy HH:mm:ss");

    }

    private FmtLib() {
    }

    private static String _normalizeFormat(Map<String, String> formats, String format, String defaultFormat) {

        // Normalize string
        format = normalize(format);
        if (isEmpty(format)) {
            return defaultFormat;
        }

        if (!USE_NORMALIZE_FORMAT) {
            return format;
        }

        // Normalize format
        String normalizeFormat = formats.get(format);
        if (normalizeFormat != null) {
            return normalizeFormat;
        }
        return format;
    }

    //// 1.1

    public static final String format(String message, Object... args) {
        return String.format(message, args);
    }

    //// 2.1 formatDate

    public static String formatDate() {
        return formatDate(_getCurrentDate());
    }

    public static String formatDate(String format) {
        return formatDate(_getCurrentDate(), format);
    }

    public static String formatDate(DateFormat formatter) {
        return formatDate(_getCurrentDate(), formatter);
    }

    //

    public static String formatDate(Date date) {
        return _formatDate(date, (String) null, DEFAULT_DATE_FORMAT);
    }

    public static String formatDate(Date date, String format) {
        return _formatDate(date, format, DEFAULT_DATE_FORMAT);
    }

    public static String formatDate(Date date, DateFormat formatter) {
        return _formatDate(date, formatter, DEFAULT_DATE_FORMAT);
    }

    //// 2.2 formatTime

    public static String formatTime() {
        return formatTime(_getCurrentDate());
    }

    public static String formatTime(String format) {
        return formatTime(_getCurrentDate(), format);
    }

    public static String formatTime(DateFormat formatter) {
        return formatTime(_getCurrentDate(), formatter);
    }

    //

    public static String formatTime(Date date) {
        return _formatDate(date, (String) null, DEFAULT_TIME_FORMAT);
    }

    public static String formatTime(Date date, String format) {
        return _formatDate(date, format, DEFAULT_TIME_FORMAT);
    }

    public static String formatTime(Date date, DateFormat formatter) {
        return _formatDate(date, formatter, DEFAULT_TIME_FORMAT);
    }

    //// 2.3 formatDateTime

    public static String formatDateTime() {
        return formatDateTime(_getCurrentDate());
    }

    public static String formatDateTime(String format) {
        return formatDateTime(_getCurrentDate(), format);
    }

    public static String formatDateTime(DateFormat formatter) {
        return formatDateTime(_getCurrentDate(), formatter);
    }

    //

    public static String formatDateTime(Date date) {
        return _formatDate(date, (String) null, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String formatDateTime(Date date, String format) {
        return _formatDate(date, format, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String formatDateTime(Date date, DateFormat formatter) {
        return _formatDate(date, formatter, DEFAULT_DATE_TIME_FORMAT);
    }

    //// 3.1

    public static DateFormat getDateFormatter(String format) {
        return _getDateFormatter(format, DEFAULT_DATE_FORMAT);
    }

    public static DateFormat getTimeFormatter(String format) {
        return _getDateFormatter(format, DEFAULT_TIME_FORMAT);
    }

    public static DateFormat getDateTimeFormatter(String format) {
        return _getDateFormatter(format, DEFAULT_DATE_TIME_FORMAT);
    }

    ////

    private static DateFormat _getDateFormatter(String format, String defaulFormat) {
        format = _normalizeFormat(dateFormats, format, defaulFormat);
        return format == null ? null : new SimpleDateFormat(format);
    }

    //

    private static String _formatDate(Date date, DateFormat formatter, String defaultFormat) {
        if (date == null) {
            return EMPTY_FORMAT_VALUE;
        }

        // Normalize formatter
        if (formatter == null && defaultFormat != null) {
            formatter = _getDateFormatter(defaultFormat, null);
        }
        return _formatDate(date, formatter);
    }

    private static String _formatDate(Date date, String format, String defaultFormat) {
        if (date == null) {
            return EMPTY_FORMAT_VALUE;
        }
        DateFormat formatter = _getDateFormatter(format, defaultFormat);
        return _formatDate(date, formatter);
    }

    private static String _formatDate(Date date, DateFormat formatter) {
        if (date == null || formatter == null) {
            return EMPTY_FORMAT_VALUE;
        }
        return formatter.format(date);
    }

    //

    private static Date _getCurrentDate() {
        return new Date();
    }

    private static String normalize(String str) {
        return StrLib.normalize(str);
    }

    private static boolean isEmpty(String str) {
        return StrLib.isEmpty(str);
    }

}
