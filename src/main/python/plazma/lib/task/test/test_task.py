import time

import plazma.lib.task.task_helper as this
import plazma.lib.sys.syslib as syslib

#
# Intel Core i5, 1.4 GHz, 8GB, Mac OS X
# ========================================
# Java:         386 ms
# C++:          930 ms
# C++ (-O2):    365 ms
# Swift:      3 497 ms
# Python:     8 450 ms
# 

#
# Intel Core i5, 2.6 GHz, 16GB, Windows 10
# ========================================
# Java:         477 ms
# C++:          639 ms
# C++ (-O2)     316 ms
# Swift:            -
# Python:    13 418 ms
# 

# Java vs C++ vs PHP vs Python vs Perl vs Ruby
# https://habr.com/ru/post/66562/

TASK_TEST = "test"

def executeTest(args):

    this.printStartTask(TASK_TEST)
    time = syslib.getTimeInMilliseconds()

    r = 0
    COUNT = 10000

    #########################################
    # Performance 1
    #########################################
    elements = range(0, COUNT)
    #elements = xrange(0, COUNT)    
    #elements = [i for i in range(0, COUNT)]

    for i in elements:
        for j in elements:
            r = (r + (i * j) % 100) % 47

    '''
    #########################################
    # Performance 2
    #########################################
    i = 0
    j = 0

    while i < COUNT:
        j = 0
        while j < COUNT:
            r = (r + (i * j) % 100) % 47
            j += 1
        i += 1 
    '''

    print("Answer: " + str(r))

    time = syslib.getTimeInMilliseconds() - time
    this.printTotalTimeInMilliseconds(time)
