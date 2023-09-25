import sys
import os
import datetime

import plazma.lib.task.task_helper as this
import plazma.lib.task.task_executor as executor

import plazma.lib.str.strlib as strlib

MESSAGE_TASK_NOT_SETTING = "-task is not setting"
MESSAGE_TASK_EMPTY = "Task is empty"

PARAMETER_VERSION = "version"
PARAMETER_HELP = "help"
PARAMETER_TASK = "task"
PARAMETER_TASK_LIST = "task-list"

def printVersion():
    print("Run [python] v1.8.0\n")

def printUsage():
    print("Usage: run [options]")
    print("Options:")
    
    print(" -version")
    print(" -help")
    print(" -task <name> [-<param> <value>]")
    print(" -task-list")
    

def printTaskList():
    executor.printTaskList()

def execute(parameters):
    taskName = parameters.get(PARAMETER_TASK)

    # check none
    if (taskName == None):
        this.error(MESSAGE_TASK_NOT_SETTING)
        return

    # check empty
    if (len(taskName) == 0):
        this.error(MESSAGE_TASK_EMPTY)
        return

    # execute task
    executor.executeTask(taskName, parameters)

def main():

    # Load command line arguments
    argv = sys.argv

    # Parse command line arguments
    parameters = this.parseArguments(argv[1:])
 
    # VERSION
    if (this.hasParameter(parameters, PARAMETER_VERSION)):
        printVersion()
        return

    # HELP
    if (this.hasParameter(parameters, PARAMETER_HELP)):
        printUsage()
        return

    # TASK
    if (this.hasParameter(parameters, PARAMETER_TASK)):
        execute(parameters)
        return

    # TASK LIST
    if (this.hasParameter(parameters, PARAMETER_TASK_LIST)):
        printTaskList()
        return


    # TASK NO
    print(MESSAGE_TASK_NOT_SETTING)
    printUsage()

if __name__ == '__main__':
    main()

