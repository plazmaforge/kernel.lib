
BLANK_STRING = ""

#### 1.1

def isEmpty(str, trim = False):
    if (not trim):
        return _isEmpty(str)
    return _isEmpty(str.strip())

def _isEmpty(str):
    return size(str) == 0

def size(str):
    return len(str)

#### 1.2

#### 1.3

#### 2.1

def replicate(str, n):
    if (n < 1):
        BLANK_STRING
    return str * n

## trim

def trim(str, ch = None):
    if (ch == None):
        return str.strip()

    len = size(str)
    if (len == 0):
        return str
    
    # find left position of last 'ch'
    posFirst = findFirstNotOf(str, ch, len)

    # not found
    if (posFirst < 0):
        return str

    # all chars are 'ch'
    if (posFirst == len - 1):
        return ""
  
    posFirst += 1

    # find right position of first 'ch'
    posLast = findLastNotOf(str, ch, len)

    if (posLast < 0):
        return str[posFirst:]

    return str[posFirst:posLast]


def ltrim(str, ch = None):
    if (ch == None):
        return str.lstrip()

    len = size(str)
    if (len == 0):
        return str
    
    # find left position of last 'ch'
    pos = findFirstNotOf(str, ch, len)

    # not found 'ch'
    if (pos < 0):
        return str

    # all chars are 'ch'
    if (pos == len - 1):
        return ""

    pos += 1
    return str[pos:]


def rtrim(str, ch = None):
    if (ch == None):
        return str.rstrip()

    len = size(str)
    if (len == 0):
        return str
    
    # find right position of first 'ch'
    pos = findLastNotOf(str, ch, len)

    # not found 'ch'
    if (pos < 0):
        return str

    return str[0:b]


def findFirstNotOf(str, ch, len = None):
    if (len == None):
        len = size(str)

    if (len == 0):
        return -1
    
    # find left position of last 'ch'
    p = -1
    i = 0
    while (i < len):
        i += 1
        if (str[i] != ch):
            break
        p = i
    return p

def findLastNotOf(str, ch, len = None):
    if (len == None):
        len = size(str)

    if (len == 0):
        return -1
    
    # find right position of first 'ch'
    p = -1
    i = len
    while (i >= 0):
        i -= 1
        if (str[i] != ch):
            break
        p = i
    return p

