
EMPTY_STRING = ''
SPACE_CHAR = ' '
ELLIPSIS = '...'
ELLIPSIS_LEN = 3

#### 1.1

def isEmpty(str):
    if str == None:
        return True    
    return len(str) == 0


def isBlank(str):
    if str == None:
        return True    
    return len(str.strip()) == 0 # trim


def size(str):
    if str == None:
        return 0
    return len(str)


def equals(str1, str2):
    if str1 == None or str2 == None:
        return False    
    return str1 == str2    

#### 1.2

def normalize(str):
    if str == None:
        return None
    
    if len(str) == 0:
        return None

    s = str.strip() # trim

    if len(s) == 0:
        return None
    else:
        return s


def normalizeSafe(str):
    if str == None:
        return EMPTY_STRING    
    return str.strip() # trim


def normalizeBlank(str, trimAll, trimBlank):
    if str == None:
        return None

    #if len(str) == 0:
    #    return EMPTY_STRING
    
    if not trimAll and not trimBlank:
        return str
    
    if not trimAll:
        # trimBlank=True, because we have condition (not trimAll and not trimBlank) before
        # Analize blank
        if isBlank(str):
            return EMPTY_STRING
        else:
            return str
    else:
        return normalize(str)


# None: experimental
def emptyIfNone(str):
    if str == None:
        return EMPTY_STRING
    else:
        return str


def noneIfEmpty(str):
    if str == None or len(str) == 0:
        return None
    else:
        return str


def defaultIfNone(str, defaultStr):
    if str == None:
        return defaultStr
    else:
        return str


def defaultIfEmpty(str, defaultStr):
    if str == None or len(str) == 0:
        return defaultStr
    else:
        return str


# Null: experimental
def emptyIfNull(str):
    return emptyIfNone(str);


def nullIfEmpty(str):
    return noneIfEmpty(str)


def defaultIfNull(str, defaultStr):
    return defaultIfNone(str, defaultStr)

#### 1.3

## trim

def trim(str, ch = None):
    if str == None:
        return None
    return str.strip(ch)


def ltrim(str, ch = None):
    if str == None:
        return None
    return str.lstrip(ch)


def rtrim(str, ch = None):
    if str == None:
        return None
    return str.rstrip(ch)

#### 1.4

def findFirstNotOf(str, ch, start = None):
    if str == None:
        return None

    if start == None:
        start = 0

    len = size(str)
    if len == 0:
        return -1
    
    if start < 0 or start > len - 1:
        return -1

    i = start
    while (i < len):
        if (str[i] != ch):
            return i
        i += 1
    return -1


def findLastNotOf(str, ch, end = None):
    if str == None:
        return None

    len = size(str)
    if len == 0:
        return -1

    if end == None:
        end = len - 1

    if end < 0 or end > len - 1:
        return -1

    i = end
    while (i >= 0):
        if (str[i] != ch):
            return i
        i -= 1
    return -1


#### 2.1

def replicate(str, n):
    if str == None:
        return None
    
    if n < 1:
        return str

    return str * n


#### 2.2

def lpad(str, len, pad = ' '):
    if str == None:
        return None
    
    if len < 1 or pad == None:       # pad == None: no padding
        return str
        
    padLen = size(pad)
    if padLen == 1:
        return str.rjust(len, pad)   # lpad = rjust for padlen = 1
    
    strLen = size(str)
    if len <= strLen or padLen == 0: # padLen == 0: no padding
        return str
    
    fillLen = len - strLen
    padCount = fillLen // padLen

    if fillLen % padLen > 0:
        padCount = padCount + 1

    fillStr = replicate(pad, padCount)

    if size(fillStr) > fillLen:
        return fillStr[:fillLen] + str
    else:
        return fillStr + str


def rpad(str, len, pad = ' '):
    if str == None:
        return None
    
    if len < 1 or pad == None:       # pad == None: no padding
        return str
        
    padLen = size(pad)
    if padLen == 1:
        return str.ljust(len, pad)   # rpad = ljust for padlen = 1
    
    strLen = size(str)
    if len <= strLen or padLen == 0: # padLen == 0: no padding
        return str
    
    fillLen = len - strLen
    padCount = fillLen // padLen

    if fillLen % padLen > 0:
        padCount = padCount + 1

    fillStr = replicate(pad, padCount)

    if size(fillStr) > fillLen:
        return str + fillStr[:fillLen]
    else:
        return str + fillStr


def fill(str, len, pad = ' '):
    if str == None or len < 1:
        return EMPTY_STRING
    
    strLen = size(str)
    if strLen == len:
        return str
    
    if strLen < len:
        # add <pad> to right side
        return rpad(str, len, pad)
    else:
        # remove chars from right side
        return trunc(str, len, False, True)


def ellipsis(str, len):
    return trunc(str, len, False, True)


def trunc(str, len, trim = False, ellipsis = False):
    if str == None:
        return None
    
    if len < 1:
        return str
    
    if trim:
        str = str.strip() # trim

    if size(str) <= len:
        return str
    
    if ellipsis:
        if len <= ELLIPSIS_LEN:
            return str[:len]
        else:
            return str[:len - ELLIPSIS_LEN] + ELLIPSIS
    else:
        return str[:len]
    

def left(str, len):
    if str == None:
        return None
        
    strLen = size(str)
    if strLen <= len:
        return str
    else:    
        return str[:len]


def right(str, len):
    if str == None:
        return None
        
    strLen = size(str)
    if strLen <= len:
        return str
    else:    
        return str[strLen - len:]
