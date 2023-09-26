
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

package plazma.lib.data.xml;

import plazma.lib.text.ReaderConfig;

public class XmlReaderConfig extends ReaderConfig {       
    
    public boolean trimAttribute;    // Trim attributes

    public boolean trimText;         // Trim text
    public boolean trimComment;      // Trim comments
    public boolean trimCData;        // Trim CDATA

    public boolean skipEmptyText;    // Skip empty text
    public boolean skipEmptyComment; // Skip empty comments
    public boolean skipEmptyCData;   // Skip empty data

    public boolean skipComment;      // Skip all comments            
    public boolean skipMeta;         // Skip meta tag (<?...?>)
    public boolean skipDTD;          // Skip DTD tag (<!DOCTYPE...>)
    
    public XmlReaderConfig() {
        trimAttribute = true;        // TRIM

        trimText = false;            // RAW
        trimComment = false;         // RAW
        trimCData = false;           // RAW

        skipEmptyText = true;        // SKIP
        skipEmptyComment = false;
        skipEmptyCData = false;

        skipComment = false;
        skipMeta = false;
        skipDTD = false;
    }


}
