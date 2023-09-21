
BLANK_STRING = ""

#### 1.1

def isEmpty(str, trim = False):
    if (str == None):
        return True

    if (not trim):
        return len(str) == 0
    
    return len(str.strip()) == 0 # trim

def isBlank(str):
    if (str == None):
        return True
    
    return len(str.strip()) == 0 # trim

def size(str):
    if (str == None):
        return 0
    return len(str)

def equals(str1, str2):
    if (str1 == None or str2 == None):
        return False    
    return str1 == str2    

#### 1.2

def normalize(str):
    if (str == None):
        return None
    return str.strip() # trim

def normalizeBlank(str, trimAll, trimBlank):
    if (str == None):
        return None

    if (len(str) == 0):
        return BLANK_STRING
    
    if (not trimAll and not trimBlank):
        return str
    
    if (not trimAll):
        # trimBlank=True, because we have condition (not trimAll and not trimBlank) before
        # Analize blank
        if (isBlank(str)):
            return BLANK_STRING
        else:
            return str
    else:
        return normalize(str)

# None: experimental
def emptyIfNone(str):
    if (str == None):
        return BLANK_STRING
    else:
        return str

def noneIfEmpty(str):
    if (str == None or len(str) == 0):
        return None
    else:
        return str

def defaultIfNone(str, defaultStr):
    if (str == None):
        return defaultStr
    else:
        return str

def defaultIfEmpty(str, defaultStr):
    if (str == None or len(str) == 0):
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

#### 2.1

def replicate(str, n):
    if (str == None):
        return None
    return str * n

## trim

def trim(str, ch = None):
    if (str == None):
        return None
    return str.strip(ch)

def ltrim(str, ch = None):
    if (str == None):
        return None
    return str.lstrip(ch)

def rtrim(str, ch = None):
    if (str == None):
        return None
    return str.rstrip(ch)

def findFirstNotOf(str, ch, start = None):
    if (str == None):
        return None

    if (start == None):
        start = 0

    len = size(str)
    if (len == 0):
        return -1
    
    if (start < 0 or start > len - 1):
        return -1

    i = start
    while (i < len):        
        if (str[i] != ch):
            return i
        i += 1
    return -1

def findLastNotOf(str, ch, end = None):
    if (str == None):
        return None

    len = size(str)
    if (len == 0):
        return -1

    if (end == None):
        end = len - 1

    if (end < 0 or end > len - 1):
        return -1

    i = end
    while (i >= 0):        
        if (str[i] != ch):
            return i
        i -= 1
    return -1

