import datetime

#################################################################################
# 
# Common
# 
# 1.1
#
# - format(message, value)
#
    
#################################################################################
#
# Date/Time/DateTime
#
# 2.1
#
# - formatDate(date, sformat)
#
# 2.2
#
# - formatTime(date, format)
#
# 2.3
#
# - string formatDateTime(date, format)
#


DEFAULT_DATE_FORMAT = "%Y-%m-%d"
DEFAULT_TIME_FORMAT = "%H:%M:%S"
DEFAULT_DATE_TIME_FORMAT = "%Y-%m-%d %H:%M:%S"

USE_NORMALIZE_FORMAT = True

dateFormats = {}
initFlag = False

def __module_init():

    initFlag = True

    ################################################################
    #
	# Date
	#
    ################################################################
    
    # ISO 8601
    dateFormats["YYYY-MM-DD"] = "%Y-%m-%d"
    dateFormats["YYYY.MM.DD"] = "%Y.%m.%d"
    dateFormats["DD.MM.YYYY"] = "%d.%m.%Y"
    dateFormats["DD/MM/YYYY"] = "%d/%m/%Y"
    dateFormats["MM/DD/YYYY"] = "%m/%d/%Y"
    
    # Unicode
    dateFormats["yyyy-MM-dd"] = "%Y-%m-%d"
    dateFormats["yyyy.MM.dd"] = "%Y.%m.%d"
    dateFormats["dd.MM.yyyy"] = "%d.%m.%Y"
    dateFormats["dd/MM/yyyy"] = "%d/%m/%Y"	
    dateFormats["MM/dd/yyyy"] = "%m/%d/%Y"



    ################################################################
    #
	# Time
	#
    ################################################################

    # ISO 8601
    dateFormats["hh:mm:ss"] = "%H:%M:%S"
    
    # Unicode
    dateFormats["HH:mm:ss"] = "%H:%M:%S"



    ################################################################
    #
	# DateTime
	#
    ################################################################
    
    # ISO 8601
    dateFormats["YYYY-MM-DD hh:mm:ss"] = "%Y-%m-%d %H:%M:%S"
    dateFormats["YYYY.MM.DD hh:mm:ss"] = "%Y.%m.%d %H:%M:%S"
    dateFormats["DD.MM.YYYY hh:mm:ss"] = "%d.%m.%Y %H:%M:%S"
    dateFormats["DD/MM/YYYY hh:mm:ss"] = "%d/%m/%Y %H:%M:%S"
    dateFormats["MM/DD/YYYY hh:mm:ss"] = "%m/%d/%Y %H:%M:%S"
    
    # Unicode
    dateFormats["yyyy-MM-dd HH:mm:ss"] = "%Y-%m-%d %H:%M:%S"
    dateFormats["yyyy.MM.dd HH:mm:ss"] = "%Y.%m.%d %H:%M:%S"
    dateFormats["dd.MM.yyyy HH:mm:ss"] = "%d.%m.%Y %H:%M:%S"
    dateFormats["dd/MM/yyyy HH:mm:ss"] = "%d/%m/%Y %H:%M:%S"	
    dateFormats["MM/dd/yyyy HH:mm:ss"] = "%m/%d/%Y %H:%M:%S"



## Common

def format(message, value):
    return message % value

## Date

def formatDate(date = None, format = ''):
    if (date == None):
        date = today()

    format = _normalizeDateFormat(format, DEFAULT_DATE_FORMAT)
    return date.strftime(format)

## Time

def formatTime(time = None, format = ''):
    if (time == None):
        time = now()

    format = _normalizeDateFormat(format, DEFAULT_TIME_FORMAT)
    return time.strftime(format)

## DateTime

def formatDateTime(time = None, format = ''):
    format = _normalizeDateFormat(format, DEFAULT_DATE_TIME_FORMAT)
    return formatTime(time, format)

## Today/Now

def today():
    return datetime.date.today()

def now():
    return datetime.datetime.now()

## Utils

def isEmpty(str):
    return str == None or len(str) == 0

def _normalizeDateFormat(format, defaultFormat = None):
    return _normalizeFormat(dateFormats, format, defaultFormat)

def _normalizeFormat(formats, format, defaultFormat = None):
    # Normalize string
    #format = normalize(format);
    if (isEmpty(format)):
        return defaultFormat

    if (not USE_NORMALIZE_FORMAT):
        return format

    # Normalize format
    normalizeFormat = formats.get(format)
    if (not isEmpty(normalizeFormat)):
        return normalizeFormat

    return format


try:
	__module_init()
except Exception as e:
	raise Exception("Initialization error")
