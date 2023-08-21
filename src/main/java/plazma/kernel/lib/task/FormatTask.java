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

package plazma.kernel.lib.task;


public abstract class FormatTask extends BaseTask {

    ///////////////////////////////////////////////////////////
    // General Format Task (XML, JSON, CSV)
    ///////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////
    // parameters:
    ///////////////////////////////////////////////////////////

    protected static final String PARAMETER_DIR = "dir";                             // Directory name
    protected static final String PARAMETER_FILE = "file";                           // Input file name (alias of 'input-file')
    protected static final String PARAMETER_INPUT_FILE = "input-file";               // Input file name
    protected static final String PARAMETER_OUTPUT_FILE = "output-file";             // Output file name
    protected static final String PARAMETER_TEXT = "text";                           // Text
    protected static final String PARAMETER_INLINE = "inline";                       // Inline all flag (XML, JSON)
    protected static final String PARAMETER_INLINE_NODE = "inline-node";             // Inline node flag (XML, JSON?)
    protected static final String PARAMETER_INLINE_NODE_LIMIT = "inline-node-limit"; // Inline node text limit (XML)
    protected static final String PARAMETER_INDENT = "indent";                       // Indent level (XML, JSON?)
    protected static final String PARAMETER_CASE = "case";                           // Tag/attribute case: 'lower', 'upper', 'camel' ...(XML)
    protected static final String PARAMETER_TAG_CASE = "tag-case";                   // Tag case: 'lower', 'upper', 'camel' ...(XML)
    protected static final String PARAMETER_ATTR_CASE = "attr-case";                 // Attribute case: 'lower', 'upper', 'camel' ...(XML)
    protected static final String PARAMETER_ATTR_QUOTE = "attr-quote";               // Attribute quote: 'single', 'double', 'none'
    protected static final String PARAMETER_TRIM_ATTR = "trim-attr";                 // Trim attribute value
    protected static final String PARAMETER_SKIP_COMMENTS = "skip-comments";         // Skip comments (XML)
    protected static final String PARAMETER_SKIP_META = "skip-meta";                 // Skip <?...?> (XML)
    protected static final String PARAMETER_SKIP_DTD = "skip-dtd";                   // Skip <!DOCTYPE...> (XML)
    protected static final String PARAMETER_DISPLAY = "display";                     // Display result
    protected static final String PARAMETER_VERBOSE = "verbose";                     // Verbose flag
    protected static final String PARAMETER_VERBOSE_TOKEN = "verbose-token";         // Verbose token flag
    protected static final String PARAMETER_COLOR = "color";                         // Color flag
    protected static final String PARAMETER_STDERR = "stderr";                       // StdErr mode
    protected static final String PARAMETER_TOKENIZE = "tokenize";                   // Tokenize mode only (without parsing)

    ///////////////////////////////////////////////////////////
    // errors:
    ///////////////////////////////////////////////////////////

    public static final String ERROR_INPUT_NOT_SETTING = "Can't execute task: Input file or text are not setting";
    public static final String ERROR_INPUT_TEXT_EMPTY = "Can't execute task: Input text is empty";


}
