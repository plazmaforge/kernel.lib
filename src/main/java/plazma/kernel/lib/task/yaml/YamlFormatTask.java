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

package plazma.kernel.lib.task.yaml;

import static plazma.kernel.lib.sys.SysLib.*;
import static plazma.kernel.lib.task.TaskHelper.*;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.data.yaml.YamlLib;
import plazma.kernel.lib.data.yaml.YamlReaderConfig;
import plazma.kernel.lib.data.yaml.YamlWriterConfig;
import plazma.kernel.lib.str.StrLib;
import plazma.kernel.lib.task.FormatTask;
import plazma.kernel.lib.task.TaskContext;

public class YamlFormatTask extends FormatTask {

    public static final String TASK_FORMAT_YAML = "format-yaml";
    
    public static final String ERROR_YAML_NODE_EMPTY = "Can't execute task: YAML Node is empty";
        
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
        boolean tokenize = ctx.getFlagParameter(PARAMETER_TOKENIZE);

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

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // READ MATRIX (float)
        //////////////////////////////////////////////////////////////////////////////////////////////////
        
        Node node = null;
        String outputText = null;
        
        // Init YamlReaderConfig
        YamlReaderConfig readerConfig = new YamlReaderConfig();
        readerConfig.trimAttribute = trimAttr;
        readerConfig.verbose = verbose;
        readerConfig.verboseToken = verboseToken;

        // Init YamlWriterConfig
        YamlWriterConfig writerConfig = new YamlWriterConfig();
        //writerConfig.inlineFlag = inlineFlag;
        //writerConfig.inlineNodeFlag = inlineNodeFlag;
        //writerConfig.inlineNodeLimit = inlineNodeLimit;
        //writerConfig.indent = indent;
        //writerConfig.tagCase = tagCase;
        writerConfig.attributeCase = attrCase;
        writerConfig.attributeQuote = attrQuote;
        writerConfig.trimAttribute = trimAttr;

        // Read YAML Node by Text/File
        if (isEmpty(inputFileName)) {

            // by Text
            if (isEmpty(inputText)) {
                error(ERROR_INPUT_TEXT_EMPTY);
            } else {
                node = YamlLib.readYamlFromText(readerConfig, inputText);
            }

        } else {

            // by File
            node = YamlLib.readYamlFromFile(readerConfig, inputFileName);
        }

        // Display/Write YAML Text
        if (node == null) {
            error(ERROR_YAML_NODE_EMPTY);
        } else {
                        
            if (isEmpty(outputFileName)) {
                
                writerConfig.colorized = color;
                
                // Display Text
                YamlLib.writeYamlToConsole(writerConfig, node);
                                
            } else {
                
                // Write Text
                YamlLib.writeYamlToFile(outputFileName, writerConfig, node);
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
