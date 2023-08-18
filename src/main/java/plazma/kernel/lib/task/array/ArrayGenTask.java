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

package plazma.kernel.lib.task.array;

import static plazma.kernel.lib.sys.SysLib.*;
import static plazma.kernel.lib.task.TaskHelper.*;

import plazma.kernel.lib.math.MathLib;
import plazma.kernel.lib.matrix.MatrixLib;
import plazma.kernel.lib.task.BaseTask;
import plazma.kernel.lib.task.TaskContext;

public class ArrayGenTask extends BaseTask {

    // tasks

    public static final String TASK_GENERATE_FLOAT_ARRAY = "generate-float-array";

    // parameters

    private static final String PARAMETER_DIR = "dir";
    private static final String PARAMETER_FILE = "file";
    private static final String PARAMETER_FILE_PREFIX = "file-prefix";
    private static final String PARAMETER_FILE_COUNT = "file-count";
    private static final String PARAMETER_COLS = "cols";
    private static final String PARAMETER_ROWS = "rows";

    private static final String DEFAULT_FILE_EXT = ".csv";
    private static final String DEFAULT_SEPRATOR = ",";
    private static final int DEFAULT_FILE_COUNT = 1;
    private static final int DEFAULT_COLS = 100;
    private static final int DEFAULT_ROWS = 1000;
    private static final int DEFAULT_CELL_MAX = 100000000; // 100 millions
    private static final float DEFAULT_MIN = -100.0f;
    private static final float DEFAULT_MAX = 100.0f;

    private static final String PARAMETER_LIST[] = { 
            PARAMETER_DIR, 
            PARAMETER_FILE, 
            PARAMETER_FILE_PREFIX,
            PARAMETER_FILE_COUNT,
            PARAMETER_COLS, 
            PARAMETER_ROWS
    };

    public void execute(TaskContext ctx) throws Exception {

        String dirName = ctx.getStringParameter(PARAMETER_DIR);
        String fileName = ctx.getStringParameter(PARAMETER_FILE);
        String filePrefix = ctx.getStringParameter(PARAMETER_FILE_PREFIX);
        String fileExt = DEFAULT_FILE_EXT;
        String separator = DEFAULT_SEPRATOR;

        int fileCount = ctx.getIntParameter(PARAMETER_FILE_COUNT, DEFAULT_FILE_COUNT); // by default
        int cols = ctx.getIntParameter(PARAMETER_COLS, DEFAULT_COLS);                  // by default
        int rows = ctx.getIntParameter(PARAMETER_ROWS, DEFAULT_ROWS);                  // by default

        int cellMax = DEFAULT_CELL_MAX;     // 100 millions

        float min = DEFAULT_MIN;
        float max = DEFAULT_MAX;
        
        boolean hasDirName = ctx.hasParameter(PARAMETER_DIR);
        boolean hasFileName = ctx.hasParameter(PARAMETER_FILE);
        boolean hasFilePrefix = ctx.hasParameter(PARAMETER_FILE_PREFIX);        

        ////
        // dump parameters
        ////

        //printParameter(PARAMETER_COLS, formatByDefault(cols, !hasCols), parameterLen);
        //printParameter(PARAMETER_ROWS, formatByDefault(rows, !hasRows), parameterLen);

        ////
        // normalize parameters
        ////

        if (!hasDirName && !hasFileName) {
            dirName = getUserDir();
        }

        if (!hasFilePrefix) {
            filePrefix = "";
        }

        ////
        // validate parameters
        ////

        if (!hasDirName && !hasFileName && !hasFilePrefix) {
            error("Requirement arguments are not setting.");
            error("Set -file or (-dir and -file-prefix).  By default file extension is '.csv'");
            return;
        }

        ////
        // warning parameters
        ////

        int cellCount = cols * rows;
        boolean writeFile = true;
        if (cellCount > cellMax) {
            writeFile = false;
            warn("Can't write file(s) (generation float array in memory only).");
            warn("Cell limit is " + cellMax + ". But real cell count is " + cellCount);
        }

        float[][] floatMatrix = null;

        // long freeMemory = Runtime.getRuntime().freeMemory();

        //////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERATE AND WRITE MATRIX (float)
        //////////////////////////////////////////////////////////////////////////////////////////////////

        if (hasFileName) {
            floatMatrix = MatrixLib.randomFloatMatrix(cols, rows, min, max, (x) -> MathLib.round(x, 2));
            if (writeFile) {
                MatrixLib.writeFloatMatrix(fileName, floatMatrix, separator);
            }
        } else {
            dirName = getOptionalPath(dirName, filePrefix);
            for (int i = 1; i <= fileCount; i++) {
                floatMatrix = MatrixLib.randomFloatMatrix(cols, rows, min, max, (x) -> MathLib.round(x, 2));
                if (writeFile) {
                    fileName = generateFileName(dirName, i, fileExt);
                    MatrixLib.writeFloatMatrix(fileName, floatMatrix, separator);
                }
            }
        }

    }
    
    public void initParameters() {
        getParameters().addParameter(PARAMETER_DIR);
        getParameters().addParameter(PARAMETER_FILE);
        getParameters().addParameter(PARAMETER_FILE_PREFIX);
        getParameters().addParameter(PARAMETER_FILE_COUNT);
        getParameters().addParameter(PARAMETER_COLS);
        getParameters().addParameter(PARAMETER_ROWS);
    }


}
