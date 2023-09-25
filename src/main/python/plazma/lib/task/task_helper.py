import plazma.lib.sys.syslib as syslib
import plazma.lib.fmt.fmtlib as fmtlib

MESSAGE_INFO = "[INFO] %s"
MESSAGE_WARN = "[WARNING] %s"
MESSAGE_ERROR = "[ERROR] %s"

MESSAGE_START_TASK = "Start task: '%s'"
MESSAGE_PROCESSING = "Processing..."
MESSAGE_TOTAL_TIME = "Total time: %s"

DEFAULT_ARG_PREFIX = "-"

def parseArguments(argv):

    # create argument dictionary
    parameters = dict()
    argc = len(argv)
    if (argc == 0):
        return parameters
    
    arg = ""
    key = ""
    value = ""
    i = 0
    j = 0
    clearPrefix = True

    # parse argument array
    # format: -parameter1 value1 -parameter2 value2 ... -parameter<N> value<N>
    # 'value' without 'parameter' will skip 
    while (i < argc):
        arg = argv[i]
        if (arg.startswith(DEFAULT_ARG_PREFIX) and len(arg) > 1):
            key = arg
            if (clearPrefix):
                key = arg[1:]

            value = ""
            j = i + 1
            if (j < argc and not (argv[j].startswith(DEFAULT_ARG_PREFIX))):
                value = argv[j]
                i = j
            parameters[key] = value
        i +=1
    return parameters

def hasParameter(parameters, parameter):
    return parameters.get(parameter) != None

def getParameter(parameters, parameter):
    return parameters.get(parameter)

def error(message):
    print(fmtlib.format(MESSAGE_ERROR, message))

def formatTimeInMilliseconds(time):
    return str(time) + " ms"

def formatTimeInSeconds(time):
    return str(time / 1000.0) + " s"

def formatTime(time):
    if (time > 1000):
        return formatTimeInSeconds(time)        # in seconds
    else:
        return formatTimeInMilliseconds(time)   # in milliseconds

def printLine():
    print("")

def printStartTask(task):
    print(fmtlib.format(MESSAGE_START_TASK, task))

def printProcessing():
    print(MESSAGE_PROCESSING)

def printTotalTime(time):
    print(fmtlib.format(MESSAGE_TOTAL_TIME, formatTime(time)))

def printTotalTimeInMilliseconds(time):
    print(fmtlib.format(MESSAGE_TOTAL_TIME, formatTimeInMilliseconds(time)))

def printTotalTimeInSeconds(time):
    print(fmtlib.format(MESSAGE_TOTAL_TIME, formatTimeInSeconds(time)))

def getOptionalPath(p1, p2):
    return syslib.getPath(p1, p2)