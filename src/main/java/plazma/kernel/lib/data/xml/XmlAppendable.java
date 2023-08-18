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

package plazma.kernel.lib.data.xml;

import static plazma.kernel.lib.sys.SysLib.*;

public interface XmlAppendable extends plazma.kernel.lib.ext.Appendable {
        
    public static final int DEFAULT_TAG_COLOR = COLOR_GREEN;                  // "\x1b[92m";
    public static final int DEFAULT_TAG_NAME_COLOR = COLOR_LIGHT_BLUE;        // "\x1b[36m";
    public static final int DEFAULT_ATTRIBUTE_NAME_COLOR = COLOR_MAGENTA;     // "\x1b[35m";
    public static final int DEFAULT_ATTRIBUTE_VALUE_COLOR = COLOR_DARK_GREEN; // "\x1b[32m";
    public static final int DEFAULT_TEXT_COLOR = COLOR_BLUE;                  // "\x1b[94m";
    public static final int DEFAULT_CDATA_COLOR = COLOR_ORANGE;               // "\x1b[33m";
    
    void setColorized(boolean colorized);
    
    void appendTag(String str);

    void appendTagName(String str);

    void appendAttributeName(String str);

    void appendAttributeValue(String str);

    void appendText(String str);

    void appendCDATA(String str);

}
