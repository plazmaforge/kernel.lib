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

import plazma.lib.text.WriterConfig;

public class JsonWriterConfig extends WriterConfig {
    
    public boolean inlineFlag;     // Output all text in one line
    public String indent;          // Indent node char: 'space', 'tab'
    public String attributeCase;   // Tag name case: 'lower', 'upper', 'camel' ...
    public String attributeQuote;  // Attribute value quote: 'single', 'double', 'none'
    public boolean trimAttribute;  // Trim attribute value
    public boolean colorized;
    
    public JsonWriterConfig() {
        inlineFlag = false;
        indent = "space";           // 'space', 'tab'
        attributeCase = null;       // 'lower', 'upper', 'camel' ...
        attributeQuote = null;      // 'single', 'double', 'none'
        trimAttribute = false;      // trim = false
        colorized = false;
    }
    
   /*
    Return real indent (tab or 1/2 spaces) by config indent
   */
   public String getIndentValue() {
       return normalizeIndentValue(indent);
   }

   public String getAttributeQuoteValue() {
       return normalizeQuoteValue(attributeQuote);
   }

   public String toString() {
       return "JsonWriterConfig[]";
   }
    

}
