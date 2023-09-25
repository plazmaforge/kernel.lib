import plazma.lib.task.task_helper as this

import plazma.lib.task.sys.print_task as print_task 
import plazma.lib.task.array.read_array_task as read_array_task 
import plazma.lib.task.array.generate_array_task as generate_array_task
import plazma.lib.task.test.test_task as test_task

#import plazma.lib.sys.syslib as syslib
import plazma.lib.fmt.fmtlib as fmtlib

MESSAGE_TASK_LIST = "Task List:"
MESSAGE_TASK_NOT_FOUND = "Task not found: '%s'"

TASK_LIST = [

   # print
   print_task.TASK_PRINT_SYSTEM_INFO,
   print_task.TASK_PRINT_DATE,
   print_task.TASK_PRINT_TIME,
   print_task.TASK_PRINT_DATE_TIME,

   # array
   read_array_task.TASK_READ_FLOAT_ARRAY,
   generate_array_task.TASK_GENERATE_FLOAT_ARRAY,

   # test
   test_task.TASK_TEST
]

def checkTaskList(taskName):
    return taskName in TASK_LIST

def printTaskList():
    print(MESSAGE_TASK_LIST)
    for taskName in TASK_LIST:
        print(" " + taskName)

def executeTask(taskName, parameters):

     # check
    if (not checkTaskList(taskName)):
        this.error(fmtlib.format(MESSAGE_TASK_NOT_FOUND , taskName))
        return

    # print
    if (taskName == print_task.TASK_PRINT_SYSTEM_INFO):
        print_task.executePrintSystemInfo(parameters)
    elif (taskName == print_task.TASK_PRINT_DATE):
        print_task.executePrintDate(parameters)
    elif (taskName == print_task.TASK_PRINT_TIME):
        print_task.executePrintTime(parameters)
    elif (taskName == print_task.TASK_PRINT_DATE_TIME):
        print_task.executePrintDateTime(parameters)

    # array
    elif (taskName == read_array_task.TASK_READ_FLOAT_ARRAY):
        read_array_task.executeReadFloatArray(parameters)
    elif (taskName == generate_array_task.TASK_GENERATE_FLOAT_ARRAY):
        generate_array_task.executeGenerateFloatArray(parameters)

    #test
    elif (taskName == test_task.TASK_TEST):
        test_task.executeTest(parameters)


