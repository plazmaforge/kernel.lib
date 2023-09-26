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

package plazma.lib.task.json;

import static plazma.lib.sys.SysLib.error;
import static plazma.lib.task.TaskHelper.getOptionalPath;
import static plazma.lib.task.TaskHelper.isEmpty;

import plazma.lib.data.json.JsonLib;
import plazma.lib.data.json.JsonReaderConfig;
import plazma.lib.data.node.Node;
import plazma.lib.data.xml.XmlLib;
import plazma.lib.data.xml.XmlWriterConfig;
import plazma.lib.str.StrLib;
import plazma.lib.task.ConvertTask;
import plazma.lib.task.TaskContext;

public class Json2XmlConvertTask extends ConvertTask {

    public static final String TASK_CONVERT_JSON2XML  = "convert-json2xml";
    
    ///////////////////////////////////////////////////////////
    // errors:
    ///////////////////////////////////////////////////////////

    public static final String ERROR_JSON_NODE_EMPTY = "Can't execute task: JSON Node is empty";
    public static final String ERROR_JSON_TOKENS_EMPTY = "Can't execute task: JSON Tokens are empty";
    //public static final String ERROR_XML_NODE_EMPTY = "Can't execute task: XML Node is empty";            

    public void execute(TaskContext ctx) throws Exception {
        
        String dirName = ctx.getStringParameter(PARAMETER_DIR);
        String fileName = ctx.getStringParameter(PARAMETER_FILE);
        String inputFileName = ctx.getStringParameter(PARAMETER_INPUT_FILE);
        String outputFileName = ctx.getStringParameter(PARAMETER_OUTPUT_FILE);
        //
        String inputText = ctx.getStringParameter(PARAMETER_TEXT);
        boolean inlineFlag = ctx.getFlagParameter(PARAMETER_INLINE);
        boolean inlineNodeFlag = ctx.getFlagParameter(PARAMETER_INLINE_NODE);
        int inlineNodeLimit = ctx.getIntParameter(PARAMETER_INLINE_NODE_LIMIT);
        String indent = ctx.getStringParameter(PARAMETER_INDENT);
        String caseValue = ctx.getStringParameter(PARAMETER_CASE);
        String tagCase = ctx.getStringParameter(PARAMETER_TAG_CASE);
        String attrCase = ctx.getStringParameter(PARAMETER_ATTR_CASE);
        String attrQuote = ctx.getStringParameter(PARAMETER_ATTR_QUOTE);
        boolean trimAttr = ctx.getFlagParameter(PARAMETER_TRIM_ATTR);
        // boolean skipComments = false;
        // boolean skipMeta = false;        
        boolean display = ctx.getFlagParameter(PARAMETER_DISPLAY);
        boolean verbose = ctx.getFlagParameter(PARAMETER_VERBOSE);
        boolean verboseToken = ctx.getFlagParameter(PARAMETER_VERBOSE_TOKEN);
        boolean color = ctx.getFlagParameter(PARAMETER_COLOR);
        String stderr_ = ctx.getStringParameter(PARAMETER_STDERR);

        boolean hasDisplay = ctx.hasParameter(PARAMETER_DISPLAY);

                
        // unquote text
        if (inputText != null) {
            inputText = StrLib.unquote(inputText);
        }

        ////
        // normalize parameters
        ////

        // input/output file

        if (isEmpty(inputFileName)) {
            inputFileName = fileName;
        }

        inputFileName = getOptionalPath(dirName, inputFileName);
        outputFileName = getOptionalPath(dirName, outputFileName);

        // tag/attribute case

        if (isEmpty(tagCase)) {
            tagCase = caseValue;
        }

        if (isEmpty(attrCase)) {
            attrCase = caseValue;
        }

        if (isEmpty(inputFileName) && isEmpty(inputText)) {
            error(ERROR_INPUT_NOT_SETTING);
            return;
        }

        // if (!hasDir && !hasFile) {
        // dir = SysLib.getUserDir();
        // }

        
        Node node = null;
        Node outputNode = null;
        //String outputText = null;
        
        // Init JsonReaderConfig
        JsonReaderConfig readerConfig = new JsonReaderConfig();
        readerConfig.trimAttribute = trimAttr;
        readerConfig.verbose = verbose;
        readerConfig.verboseToken = verboseToken;

        // Init XmlWriterConfig
        XmlWriterConfig writerConfig = new XmlWriterConfig();
        writerConfig.inlineFlag = inlineFlag;
        writerConfig.inlineNodeFlag = inlineNodeFlag;
        writerConfig.inlineNodeLimit = inlineNodeLimit;
        writerConfig.indent = indent;
        writerConfig.tagCase = tagCase;
        writerConfig.attributeCase = attrCase;
        writerConfig.attributeQuote = attrQuote;
        writerConfig.trimAttribute = trimAttr;

        // Read JSON Node by Text/File
        if (isEmpty(inputFileName)) {

            // by Text
            if (isEmpty(inputText)) {
                error(ERROR_INPUT_TEXT_EMPTY);
            } else {
                node = JsonLib.readJsonFromText(readerConfig, inputText);
            }

        } else {

            // by File
            node = JsonLib.readJsonFromFile(readerConfig, inputFileName);
        }

        // Display/Write XML Text
        if (node == null) {
            error(ERROR_JSON_NODE_EMPTY);
        } else {
                        
            outputNode = JsonLib.convertJsonToXml(node);

            // Display flag overrides output file name!
            if (display || (!hasDisplay && isEmpty(outputFileName))) {

                writerConfig.colorized = color;

                // Display text
                XmlLib.writeXmlToConsole(writerConfig, outputNode);

            } else if (!isEmpty(outputFileName)) {

                // Write text
                XmlLib.writeXmlToFile(outputFileName, writerConfig, outputNode);
            }

        }

    }

    public void initParameters() {

        getParameters().addParameter(PARAMETER_DIR);
        getParameters().addParameter(PARAMETER_FILE);
        getParameters().addParameter(PARAMETER_INPUT_FILE);
        getParameters().addParameter(PARAMETER_OUTPUT_FILE);
        getParameters().addParameter(PARAMETER_TEXT);
        //
        getParameters().addParameter(PARAMETER_INLINE);
        getParameters().addParameter(PARAMETER_INLINE_NODE);
        getParameters().addParameter(PARAMETER_INLINE_NODE_LIMIT);
        getParameters().addParameter(PARAMETER_INDENT);
        getParameters().addParameter(PARAMETER_CASE);
        getParameters().addParameter(PARAMETER_TAG_CASE);
        getParameters().addParameter(PARAMETER_ATTR_CASE);
        getParameters().addParameter(PARAMETER_ATTR_QUOTE);
        getParameters().addParameter(PARAMETER_TRIM_ATTR);
        //
        getParameters().addParameter(PARAMETER_DISPLAY);
        getParameters().addParameter(PARAMETER_VERBOSE);
        getParameters().addParameter(PARAMETER_VERBOSE_TOKEN);
        getParameters().addParameter(PARAMETER_COLOR);
        //getParameters().addParameter(PARAMETER_STDERR);
        //getParameters().addParameter(PARAMETER_TOKENIZE);

    }

}
