import os
import platform
import time
import locale
import tempfile

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
     #platform.
     if PLATFORM in PLATFORMS:
          initOS()
     else:
          raise Exception("Unsupported platform '%s' for OS versioning" % PLATFORM)

def initOS():
     #global PLATFORM
     global PLATFORM, PROCESSOR, OS_NAME, OS_VERSION, OS_MAJORVER, OS_MINORVER
     if (PLATFORM ==  'Linux'):
          # TODO: Linux
          #pass
          OS_NAME = os.name
          OS_VERSION = os.version                              
     elif (PLATFORM == 'Windows'):
          OS_NAME = platform.system()          #os.name
          OS_NAME = OS_NAME + ' ' + platform.win32_ver()[0]
          OS_MAJORVER = platform.win32_ver()[0]
          OS_MINORVER = platform.win32_ver()[1]
          OS_VERSION = platform.win32_ver()[1] #os.version

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

def getOsArch():
     return platform.machine()

def getOsArchData():
     os_arch = platform.machine()
     os_arch = os_arch.lower()
     is_64bit = (os_arch == "x86_64" or os_arch == "amd64")
     if is_64bit:
          return '64'
     else:
          return ''

def getOsVersion():
     return OS_VERSION #os.version

# user
def getUserName():
     return os.getlogin()

def getUserHome():
     return os.path.expanduser('~')

def getUserDir():
     return os.getcwd()

def getTmpDir():
     return tempfile.gettempdir()

def getEncoding():
     #language, encoding = locale.getdefaultlocale()
     #return encoding
     return None

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
except Exception as e:
	raise Exception("Initialization error")

