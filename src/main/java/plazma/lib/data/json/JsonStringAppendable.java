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

package plazma.lib.data.json;

import plazma.lib.sys.SysLib;

public class JsonStringAppendable implements JsonAppendable {
    
    private boolean colorized;
    
    private StringBuilder buf = new StringBuilder();

    public void setColorized(boolean colorized) {
        this.colorized = colorized;
    }

    public void append(String str) {
        buf.append(str);
    }

    public void appendObject(String str) {
        appendWrap(str, DEFAULT_OBJECT_COLOR);
    }

    public void appendArray(String str) {
        appendWrap(str, DEFAULT_ARRAY_COLOR);
    }

    public void appendAttributeName(String str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_NAME_COLOR);
    }

    public void appendAttributeValue(String str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_VALUE_COLOR);
    }

    public void appendText(String str) {
        appendWrap(str, DEFAULT_TEXT_COLOR);
    }

    ////

    public void appendWrap(String str, int color) {
        if (!colorized) {
            buf.append(str);
            return;
        }
        buf.append(SysLib.getPrintColor(color));
        buf.append(str);
        buf.append(SysLib.getPrintResetColor());
    }

    public String toString() {
        return buf.toString();
    }

}
