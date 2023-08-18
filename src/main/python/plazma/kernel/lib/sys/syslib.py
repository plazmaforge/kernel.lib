import os
import platform
import time

# https://github.com/Adam5Wu/ZWUtils-Python2/blob/master/PlatformOps.py

PLATFORMS = ['Linux' , 'Windows', 'Darwin']

PLATFORM = None
OS_NAME = None
OS_VERSION = None
OS_MAJORVER = None
OS_MINORVER = None

def __module_init():
     global PLATFORM, PLATFORMS
     PLATFORM = platform.system()
     if PLATFORM in PLATFORMS:
          initOS()
     else:
          raise Exception("Unsupported platform '%s' for OS versioning" % PLATFORM)

def initOS():
     global PLATFORM
     if (PLATFORM ==  'Linux'):
          # TODO: Linux
          pass
     elif (PLATFORM == 'Windows'):
          # TODO: Windows
          pass
     elif (PLATFORM == 'Darwin'):
          initMacOS() 

def initMacOS():
     global PLATFORM, PROCESSOR, OS_NAME, OS_VERSION, OS_MAJORVER, OS_MINORVER
     PROCESSOR = platform.machine()
     OS_NAME = "Mac OS X"
     OS_VERSION = platform.mac_ver()[0]
     tokens =  OS_VERSION.split('.')
     OS_MAJORVER = tokens[0]
     OS_MINORVER = tokens[1]
     PLATFORM = "MacOS"

def getOsName():
     return OS_NAME #os.name

def getOsVersion():
     return OS_VERSION #os.version

def getUserName():
     return os.getlogin()

# os
def isOsName(name):
     global PLATFORM
     if (PLATFORM == name):
          return True
     return False
     
# os
def isWindows():
     return isOsName('Windows')

# os
def isLinux():
     return isOsName('Linux')

# os
def isMacOS():
     return isOsName('MacOS')
     
# fs
def getFileSeparator():
     if isWindows():
         return "\\"
     else:
         return "/"

def getPath(p1, p2):
    if (p1 == None and p2 == None):
        return "";
    if (p1 == None):
        return p2
    if (p2 == None):
        return p1
    return p1 + getFileSeparator() + p2

# Unix time (1970-01-01 00:00:00) in milliseconds
def getTimeInMilliseconds():
     return int(round(time.time() * 1000))

# Unix time (1970-01-01 00:00:00) in seconds
def getTimeInSeconds():
     return int(round(time.time()))

try:
	__module_init()
except Exception, e:
	raise Exception("Initialization error")

