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

package plazma.lib;

public final class ArithmeticEnvironment {

    private static ArithmeticContext context;

    private ArithmeticEnvironment() {
    }

    public static ArithmeticContext getContext() {
        if (ArithmeticEnvironment.context == null) {
            ArithmeticEnvironment.context = new ArithmeticContext();
        }
        return ArithmeticEnvironment.context;
    }

    public static void initContext(ArithmeticContext context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must be not null");
        }
        ArithmeticEnvironment.context = new ArithmeticContext(context.getNullMode(), context.getOverflowMode());
    }

    ////

    public static ArithmeticContext getContext(ArithmeticContext context) {
        return context == null ? getContext() : context;
    }

    public static boolean isNullException(ArithmeticContext context) {
        return getContext(context).isNullException();
    }

    public static boolean isOverflowException(ArithmeticContext context) {
        return getContext(context).isOverflowException();
    }

    ////

    public static boolean checkNull(Object x, Object y, ArithmeticContext context, String message) {
        if (isNullException(context)) {
            throw new NullPointerException(message);
        }
        if (x == null && y == null) {
            return true;
        }
        if (context.getNullMode() == NullMode.NULL) {
            return true;
        }
        return false;
    }

    public static boolean checkNull(Object x, ArithmeticContext context, String message) {
        if (isNullException(context)) {
            throw new NullPointerException(message);
        }
        if (x == null) {
            return true;
        }
        return false;
    }

    public static boolean checkIndexOut(int index, int fromIndex, int length) {
        return checkIndexOutRange(index, fromIndex, fromIndex + length);
    }

    public static boolean checkIndexOut(int index, int length) {
        return checkIndexOutRange(index, 0, length);
    }

    // toIndex - exclusive!
    public static boolean checkIndexOutRange(int index, int fromIndex, int toIndex) {
        if (index < fromIndex || index >= toIndex) {
            return true;
        }
        return false;
    }

}
