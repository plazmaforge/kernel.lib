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

package plazma.lib.text;

public class TokenizerContext {

    public String[] separators;

    public String[] excludeSeparators;

    public String[] flexSeparators;

    public String[] startQuotes;

    public String[] endQuotes;

    public boolean useEscape = true;

    public boolean inParseState = true;  // 'false' for XML

    public boolean inTextState = false;

    public boolean isStartQuote = false; // start quote flag

    public boolean isEndQuote = false;   // end quote flag
    

    public TokenizerContext() {
        super();
    }

    public boolean isFindQuote(char ch) {
        // Start or End: 'ch' is quote
        return (isStartQuote || isEndQuote) && ((ch != '\'' || (ch == '\'' && inParseState)) && (ch != '"' || (ch == '"' && inParseState)));        
    }

    public void updateState(String separator) {
        // do nothing by default
    }

    public boolean isQuoteChar(char ch) {
        // All chars are candidates for quotes by default
        return true;
    }

    public boolean isSeparatorChar(char ch) {
        // All chars are candidates for separators by default
        return true;
    }

    public boolean isEmptySeparators() {
        return separators == null || separators.length == 0;
    }

    public boolean isEmptyExcludeSeparators() {
        return excludeSeparators == null || excludeSeparators.length == 0;
    }

    public boolean isEmptyFlexSeparators() {
        return flexSeparators == null || flexSeparators.length == 0;
    }

    public boolean isEmptyStartQuotes() {
        return startQuotes == null || startQuotes.length == 0;
    }

    public boolean isEmptyEndQuotes() {
        return endQuotes == null || endQuotes.length == 0;
    }

    public boolean isEmptyQuotes() {
        return isEmptyStartQuotes() && isEmptyEndQuotes();
    }

    ////

    public String toString() {
        return "TokenizerContext[]";
    }

    public void destroy() {
        separators = null;
        excludeSeparators = null;
        flexSeparators = null;
        startQuotes = null;
        endQuotes = null;
    }

}
