import plazma.kernel.lib.task.task_helper as this
import plazma.kernel.lib.sys.syslib as syslib
import plazma.kernel.lib.num.numlib as numlib

TASK_READ_FLOAT_ARRAY = "read-float-array"

PARAMETER_DIR = "dir"
PARAMETER_FILE =  "file"
PARAMETER_FILE_PREFIX =  "file-prefix"
PARAMETER_FILE_COUNT =  "file-count"

DEFAULT_FILE_COUNT = 1
DEFAULT_FILE_EXT = ".csv"


def executeReadFloatArray(parameters):

    this.printStartTask(TASK_READ_FLOAT_ARRAY)

    dirName = ""
    fileName = ""
    filePrefix = ""
    fileCount = DEFAULT_FILE_COUNT
    fileExt = DEFAULT_FILE_EXT

    hasDirName = False
    hasFileName = False
    hasFilePrefix = False
    hasFileCount = False

    #fileFolder = ""

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


    #################
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

    this.printLine()
    this.printProcessing()

    time = syslib.getTimeInMilliseconds()

    if (hasFileName):
        records = numlib.readFloatArray(fileName)
    else:
        dirName = this.getOptionalPath(dirName, filePrefix);
        for i in range(1, fileCount + 1):
            fileName = dirName + str(i) + fileExt
            records = numlib.readFloatArray(fileName)
            #print(records)

    time = syslib.getTimeInMilliseconds() - time
    this.printTotalTime(time)

