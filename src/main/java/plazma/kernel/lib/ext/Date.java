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

package plazma.kernel.lib.ext;

import java.io.Serializable;
import java.util.Calendar;

public class Date implements Serializable {

    private static final long serialVersionUID = 6119570633162543515L;

    private int year;

    private int month;

    private int day;

    public Date() {
        super();

        // NonJS (!)
        Calendar calendar = getCalendar();

        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Date(int year, int month, int day) {
        super();

        // NonJS (!)
        Calendar calendar = getCalendar();

        // Set to Calendar for normalization
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    // NonJS (!)
    private static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    // Use for JS (!): Own implementation
    private void init(int year, int month, int day) {
        this.year = normalizeYear(year);
        this.month = normalizeMonth(month);
        this.day = normalizeDay(year, month, day);
    }

    private int normalizeYear(int year) {
        // TODO
        return year == 0 ? 1 : year;
    }

    private int normalizeMonth(int month) {
        // TODO
        return month;
    }

    private int normalizeDay(int year, int month, int day) {
        // TODO
        return day;
    }

    ////

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    ////

    public long getInstant() {
        // TODO
        return 0;
    }

}