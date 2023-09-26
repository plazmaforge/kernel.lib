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

package plazma.lib.task.array;

import static plazma.lib.sys.SysLib.*;
import static plazma.lib.task.TaskHelper.*;

import plazma.lib.matrix.MatrixLib;
import plazma.lib.task.BaseTask;
import plazma.lib.task.TaskContext;

public class ArrayReadTask extends BaseTask {

    // array
    public static final String TASK_READ_FLOAT_ARRAY = "read-float-array";

    private static final String PARAMETER_DIR = "dir";
    private static final String PARAMETER_FILE = "file";
    private static final String PARAMETER_FILE_PREFIX = "file-prefix";
    private static final String PARAMETER_FILE_COUNT = "file-count";

    private static final String DEFAULT_FILE_EXT = ".csv";
    private static final String DEFAULT_SEPRATOR = ",";
    private static final int DEFAULT_FILE_COUNT = 1;

    public static final String PARAMETER_LIST[] = { 
            PARAMETER_DIR, 
            PARAMETER_FILE, 
            PARAMETER_FILE_PREFIX,
            PARAMETER_FILE_COUNT
    };


    public void execute(TaskContext ctx) throws Exception {

        String dirName = ctx.getStringParameter(PARAMETER_DIR);
        String fileName = ctx.getStringParameter(PARAMETER_FILE);
        String filePrefix = ctx.getStringParameter(PARAMETER_FILE_PREFIX);
        String fileExt = DEFAULT_FILE_EXT;
        int fileCount = ctx.getIntParameter(PARAMETER_FILE_COUNT, DEFAULT_FILE_COUNT); // by default

        boolean hasDirName = ctx.hasParameter(PARAMETER_DIR);
        boolean hasFileName = ctx.hasParameter(PARAMETER_FILE);
        boolean hasFilePrefix = ctx.hasParameter(PARAMETER_FILE_PREFIX);

        //int parameterLen = maxParameterLen(PARAMETER_LIST);

        ////
        // dump parameters
        ////

        //println();
        //printParameter(PARAMETER_DIR, dirName, parameterLen, hasValue(dirName));
        //printParameter(PARAMETER_FILE, fileName, parameterLen, hasValue(fileName));
        //printParameter(PARAMETER_FILE_PREFIX, filePrefix, parameterLen, hasValue(filePrefix));
        //printParameter(PARAMETER_FILE_COUNT, formatByDefault(fileCount, !hasFileCount), parameterLen);
        //println();

        ////
        // validate parameters
        ////

        if (!hasDirName && !hasFileName && !hasFilePrefix) {
            error("Requirement arguments are not setting.");
            error("Set -file or (-dir and -file-prefix). By default file extension is '.csv'");
            return;
        }

        ////
        // normalize parameters
        ////

        if (!hasDirName && !hasFileName) {
            dirName = getUserDir();
        }

        if (!hasFilePrefix) {
            filePrefix = "";
        }

        float[][] floatMatrix = null;

        // long freeMemory = Runtime.getRuntime().freeMemory();

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // READ MATRIX (float)
        //////////////////////////////////////////////////////////////////////////////////////////////////

        if (hasFileName) {
            floatMatrix = MatrixLib.readFloatMatrix(fileName);
        } else {
            dirName = getOptionalPath(dirName, filePrefix);
            for (int i = 1; i <= fileCount; i++) {
                fileName = generateFileName(dirName, i, fileExt);
                floatMatrix = MatrixLib.readFloatMatrix(fileName);
            }
        }

    }

    public void initParameters() {
        getParameters().addParameter(PARAMETER_DIR);
        getParameters().addParameter(PARAMETER_FILE);
        getParameters().addParameter(PARAMETER_FILE_PREFIX);
        getParameters().addParameter(PARAMETER_FILE_COUNT);
    }

}
