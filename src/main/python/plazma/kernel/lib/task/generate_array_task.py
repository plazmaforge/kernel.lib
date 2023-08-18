import random

import plazma.kernel.lib.task.task_helper as this
import plazma.kernel.lib.sys.syslib as syslib
import plazma.kernel.lib.num.numlib as numlib

TASK_GENERATE_FLOAT_ARRAY = "generate-float-array"

PARAMETER_DIR = "dir"
PARAMETER_FILE =  "file"
PARAMETER_FILE_PREFIX =  "file-prefix"
PARAMETER_FILE_COUNT =  "file-count"
PARAMETER_COLS =  "cols"
PARAMETER_ROWS =  "rows"

DEFAULT_FILE_COUNT = 1
DEFAULT_FILE_EXT = ".csv"
DEFAULT_COLS = 100
DEFAULT_ROWS = 1000
DEFAULT_MIN = -100.0
DEFAULT_MAX = 100.0
   

def executeGenerateFloatArray(parameters):

    this.printStartTask(TASK_GENERATE_FLOAT_ARRAY)

    dirName = ""
    fileName = ""
    filePrefix = ""
    fileCount = DEFAULT_FILE_COUNT
    fileExt = DEFAULT_FILE_EXT
    cols = DEFAULT_COLS
    rows = DEFAULT_ROWS

    min = DEFAULT_MIN
    max = DEFAULT_MAX

    hasDirName = False
    hasFileName = False
    hasFilePrefix = False
    hasFileCount = False

    if (this.hasParameter(parameters, PARAMETER_DIR)):
        hasDirName = True
        dirName = parameters[PARAMETER_DIR]

    if (this.hasParameter(parameters, PARAMETER_FILE)):
        hasFileName = True
        fileName = parameters[PARAMETER_FILE]

    if (this.hasParameter(parameters, PARAMETER_FILE_PREFIX)):
        hasFilePrefix = True
        filePrefix = parameters[PARAMETER_FILE_PREFIX]

    if (this.hasParameter(parameters, PARAMETER_FILE_COUNT)):
        hasFileCount = True
        fileCount = int(parameters[PARAMETER_FILE_COUNT])

    if (this.hasParameter(parameters, PARAMETER_COLS)):
        cols = int(parameters[PARAMETER_COLS])

    if (this.hasParameter(parameters, PARAMETER_ROWS)):
        rows = int(parameters[PARAMETER_ROWS])

    ################
    # dump parameters
    #################

    if (len(dirName) == 0):
        print(" -dir..........: <not setting>")
    else:
        print(" -dir..........: " + dirName)

    if (len(fileName) == 0):
        print(" -file.........: <not setting>")
    else:
        print(" -file.........: " + fileName)

    if (len(filePrefix) == 0):
        print(" -file-prefix..: <not setting>")
    else:
        print(" -file-prefix..: " + filePrefix)

    if (fileCount <= 0):
        print(" -file-count...: <not setting>")
    else:
        print(" -file-count...: " + str(fileCount))

    if (cols <= 0):
        print(" -cols.........: <not setting>")
    else:
        print(" -cols.........: " + str(cols))

    if (rows <= 0):
        print(" -rows.........: <not setting>")
    else:
        print(" -rows.........: " + str(rows))

    this.printLine()
    this.printProcessing()

    time = syslib.getTimeInMilliseconds()

    if (hasFileName):
        records = numlib.generateFloatArray(cols, rows, min, max)
        numlib.writeFloatArray(fileName, records)
    else:
        dirName = this.getOptionalPath(dirName, filePrefix);
        for i in range(1, fileCount + 1):
            fileName = dirName + str(i) + fileExt
            records = numlib.generateFloatArray(cols, rows, min, max)
            numlib.writeFloatArray(fileName, records)

    time = syslib.getTimeInMilliseconds() - time
    this.printTotalTime(time)

