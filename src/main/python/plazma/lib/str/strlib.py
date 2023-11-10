
EMPTY_STRING = ''
SPACE_CHAR = ' '
ELLIPSIS = '...'
ELLIPSIS_LEN = 3

DEFAULT_CASE_SEPARATOR = '_'
DEFAULT_CASE_SEPARATORS_ = ' -_'
DEFAULT_CASE_SEPARATORS_A = ' -_A'
DEFAULT_ALTER_CASE_SEPARATOR = '-' # XML    
DEFAULT_SNAKE_CASE_SEPARATOR = '_' # SNAKE
DEFAULT_KEBAB_CASE_SEPARATOR = '-' # KEBAB

####################################
# Case Operations
####################################
# caseOp =  1: 'myname': LOWER 
# caseOp =  2: 'MYNAME': UPPER 
# caseOp =  3: 'myName': LOWER_CHAR
# caseOp =  4: 'MyName': UPPER_CHAR    

CO_NONE       = 0
CO_LOWER      = 1
CO_UPPER      = 2
CO_LOWER_CHAR = 3
CO_UPPER_CHAR = 4
#
CO_COUNT      = 4

####################################
# Case Types
####################################
# -  1. lowercase
# -  2. UPPERCASE
# -  3. camelCase
# -  4. CamelCase, PascalCase
# -  5. snake_case
# -  6. SNAKE_CASE, MACRO_CASE, CONSTANT_CASE
# -  7. Snake_Case
# -  8. kebab-case, dash-case, train-case, lisp-case
# -  9. KEBAB-CASE, DASH-CASE, TRAIN-CASE, COBOL-CASE
# - 10. Kebab-Case, Dash-Case, Train-Case, HTTP-Header-Case

CT_NONE       = 0
CT_lowercase  = 1
CT_UPPERCASE  = 2
CT_camelCase  = 3
CT_PascalCase = 4
CT_snake_case = 5
CT_SNAKE_CASE = 6
CT_Snake_Case = 7
CT_kebab_case = 8
CT_KEBAB_CASE = 9
CT_Kebab_Case = 10
#
CT_COUNT      = 10

SNAKE_CONNECTOR = '_' # shake_case
KEBAB_CONNECTOR = '-' # kebab-case

#################################################################################
# 1.1 empty, blank, size
#
# - isEmpty(str)                                               - check empty
# - isBlank(str)                                               - check blank
# - size(str)                                                  - length
#
# - equals(str1, str2)
#
# 1.2 normalization
#
# - normalize(str)                                             - trim, '' - > None
# - normalizeSafe(str)                                         - trim, '' - > ''
# - normalizeBlank(str, trimAll, trimBlank)
# ? normalizeQuoted(str)                                       - trim in quoted value: "\' text    \'" - > "\'text\'" 
#
# - emptyIfNone(str)                                           - None -> ''
#
# - noneIfEmpty(str)                                           - '' -> None
#
# - defaultIfNone(str, defaultStr)                             - str is None ? defaultStr : str
# - defaultIfEmpty(str, defaultStr)                            - isEmpty(str) ? defaultStr : str
#
# 1.3 trim (left, right)
# 
# - trim(str)                                                  - all trim (left, right)
# - trim(str, ch)
#
# - ltrim(str)                                                 - left trim
# - ltrim(str, ch)
#
# - rtrim(str)                                                 - right trim
# - rtrim(str, ch)
#
# 1.4
#
# - contains(str, substr)
#
# - findFirst(str, substr)
# - findFirst(str, substr, pos)
#
# - findLast(str, substr)
# - findLast(str, substr, pos)
#
#
# - findFirstOf(str, substr)
# - findFirstOf(str, substr, pos)
#
# - findLastOf(str, substr)
# - findLastOf(str, substr, pos)
#
# - findFirstNotOf(str, substr)
# - findFirstNotOf(str, substr, pos)
#
# - findLastNotOf(str, substr)
# - findLastNotOf(str, substr, pos)

#################################################################################
#
# 2.1
#
# - replicate(str, n)                                          - replicate("abc", 3) = "abcabcabc" : repeat (?)
# - replicate(ch, n)                                           - replicate('a', 3) = "aaa"
#
# 2.2
#
# - lpad(str, len)                                             - lpad("abc", 5)      = "  abc"
# - lpad(str, len, pad)                                        - lpad("abc", 5, "*") = "**abc"
#
# - rpad(str, len)                                             - rpad("abc", 5)      = "abc  "
# - rpad(str, len, pad)                                        - rpad("abc", 5, "*") = "abc**"
#
# 2.3
#
# - fill(str, en)
# - fill(str, len, pad)
#
# - ellipsis(str, len)
#
# - trunc(str, len)
# - trunc(str, len, ellipsis)
#
# - left(str, len)
# - right(str, len)
#
# 3.1
#
# - capitalize(str, forceRest)  
# - decapitalize(str, forceRest)
#
# - upper(str)
# - lower(str)
#
# - toUpperCase(str)
# - toLowerCase(str)
#
# - toCase(str, upper)
#
# - toCamelCase(str, separators, capitalize)
# - toSnakeCase(str, separators, upper)
# - toKebabCase(str, separators, upper)
#
#  - reverse(String str)                                               - reverse("abc") = "cba"

# 1.1

def isEmpty(str):
    if str is None:
        return True    
    return len(str) == 0

def isBlank(str):
    if str is None:
        return True    
    return len(str.strip()) == 0 # trim

def size(str):
    if str is None:
        return 0
    return len(str)

def equals(str1, str2):
    return str1 == str2    

# 1.2

def normalize(str):
    if str is None:
        return None
    
    if len(str) == 0:
        return None

    res = str.strip() # trim

    if len(res) == 0:
        return None
    else:
        return res

def normalizeSafe(str):
    if str is None:
        return EMPTY_STRING    
    return str.strip() # trim

def normalizeBlank(str, trimAll, trimBlank):
    if str is None:
        return None
   
    if not trimAll and not trimBlank:
        return str
    
    if not trimAll:
        # trimBlank=True, because we have condition (not trimAll and not trimBlank) before
        # Analize blank
        if isBlank(str):
            return None
        else:
            return str
    else:
        return normalize(str)

def emptyIfNone(str):
    if str is None:
        return EMPTY_STRING
    else:
        return str

def noneIfEmpty(str):
    if isEmpty(str):
        return None
    return str

def defaultIfNone(str, defaultStr):
    if str is None:
        return defaultStr
    else:
        return str

def defaultIfEmpty(str, defaultStr):
    if isEmpty(str):
        return defaultStr
    else:
        return str

# Null: experimental
#def emptyIfNull(str):
#    return emptyIfNone(str);

#def nullIfEmpty(str):
#    return noneIfEmpty(str)

#def defaultIfNull(str, defaultStr):
#    return defaultIfNone(str, defaultStr)

# 1.3

## trim

def trim(str, ch = None):
    if str is None:
        return None
    return str.strip(ch)

def trimSpace(str):
    return trim(str, SPACE_CHAR)

def trimAll(str):
    return trim(str, None)

##

def ltrim(str, ch = None):
    if str is None:
        return None
    return str.lstrip(ch)

def ltrimSpace(str):
    return ltrim(str, SPACE_CHAR)

def ltrimAll(str):
    return ltrim(str, None)

##

def rtrim(str, ch = None):
    if str is None:
        return None
    return str.rstrip(ch)

def rtrimSpace(str):
    return rtrim(str, SPACE_CHAR)

def rtrimAll(str):
    return rtrim(str, None)

# 1.4

def contains(str, substr):
    if isEmpty(str) or isEmpty(substr):
        return False

    # '' in ''            = True
    # ''.__contains__('') = True
    # ''.find('')         = 0 (!): str[0] - IndexError
    # ''.find('\0')       = -1
    # ' '.find('')        = 0 (!): str[0] != '' -> ' ' != ''

    # str = ''
    # substr = ''
    # 
    # index = str.find(substr)
    # if index > -1:
    #     str[index]       - IndexError: string index out of range (!)
    #

    # Performance
    #return substr in str            # 0.15
    #return str.__contains__(substr) # 0.25   
    #return str.find(substr) > -1    # 0.30

    return substr in str

def findFirst(str, substr, pos = None):
    if isEmpty(str) or isEmpty(substr):
        return -1

    len = size(str)
    if len == 0:
        return -1

    if pos == None:
        pos = 0 # first index
    
    if pos < 0 or pos >= len:
        return -1
    
    return str.find(substr, pos) # [pos: len]

def findLast(str, substr, pos = None):
    if isEmpty(str) or isEmpty(substr):
        return -1

    len = size(str)
    if len == 0:
        return -1

    if pos == None:
        pos = len - 1 # last index
    
    if pos < 0 or pos >= len:
        return -1
    
    return str.rfind(substr, 0, pos + 1) # [0: pos + 1] exclude last index

def findFirstOf(str, terms, pos = None):
    if isEmpty(str) or isEmpty(terms):
        return -1

    if pos == None:
        pos = 0 # first index

    len = size(str)
    if len == 0:
        return -1
    
    if pos < 0 or pos >= len:
        return -1

    i = pos

    if (len == 1):
        return str.find(terms, pos) # [pos: len]
        #while (i < len):
        #    if (str[i] == terms):
        #        return i
        #    i += 1
    else:
        while (i < len):
            if (contains(terms, str[i])):
                return i
            i += 1

    return -1

def findLastOf(str, terms, pos = None):
    if isEmpty(str) or isEmpty(terms):
        return -1

    len = size(str)
    if len == 0:
        return -1

    if pos == None:
        pos = len - 1 # last index

    if pos < 0 or pos >= len:
        return -1

    i = pos

    if (len == 1):
        return str.rfind(terms, 0, pos + 1) # [0: pos + 1] exclude last index
        #while (i >= 0):
        #    if (str[i] == terms):
        #        return i
        #    i -= 1
    else:
        while (i >= 0):
            if (contains(terms, str[i])):
                return i
            i -= 1

    return -1

def findFirstNotOf(str, terms, pos = None):
    if isEmpty(str) or isEmpty(terms):
        return -1

    if pos == None:
        pos = 0

    len = size(str)
    if len == 0:
        return -1
    
    if pos < 0 or pos >= len:
        return -1

    i = pos

    if (len == 1):
        while (i < len):
            if (str[i] != terms):
                return i
            i += 1
    else:
        while (i < len):
            if (not contains(terms, str[i])):
                return i
            i += 1

    return -1

def findLastNotOf(str, terms, pos = None):
    if isEmpty(str) or isEmpty(terms):
        return -1

    len = size(str)
    if len == 0:
        return -1

    if pos == None:
        pos = len - 1

    if pos < 0 or pos >= len:
        return -1

    i = pos

    if (len == 1):
        while (i >= 0):
            if (str[i] != terms):
                return i
            i -= 1
    else:
        while (i >= 0):
            if (not contains(terms, str[i])):
                return i
            i -= 1

    return -1


# 2.1

def replicate(str, n):
    if str is None:
        return None
    
    if n < 1:
        return EMPTY_STRING

    return str * n

# 2.2

def lpad(str, len, pad = ' '):
    if str is None:
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
    if str is None:
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

# 2.3

def fill(str, len, pad = ' '):
    if str is None:
        return None
    
    # hard format: <=len or empty
    if len < 1:
        return EMPTY_STRING
    
    strLen = size(str)
    if strLen == len:
        return str
    
    # hard format: pad or trunc    
    if strLen < len:
        # add <pad> to right side
        return rpad(str, len, pad)
    else:
        # remove chars from right side
        return trunc(str, len, True) # ellipsis

def ellipsis(str, len):
    return trunc(str, len, True) # ellipsis

def trunc(str, len, ellipsis = False):
    if str is None:
        return None

    # soft format    
    if len < 1:
        return str
    
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
    if str is None:
        return None
    
    # hard format: <=len or empty
    if len < 1:
        return EMPTY_STRING

    strLen = size(str)
    if strLen <= len:
        return str
    else:    
        return str[:len]

def right(str, len):
    if str is None:
        return None

    # hard format: <=len or empty
    if len < 1:
        return EMPTY_STRING

    strLen = size(str)
    if strLen <= len:
        return str
    else:    
        return str[strLen - len:]

# 3.1

def capitalize(str, forceRest = False):
    if isEmpty(str):
        return str
    
    # return str.capitalize()
    # str[0:1].upper() + str[1:].lower()
    # return ''.join([str[:1].lower(), (str[1:].upper() if upper_rest else str[1:])])

    # if 'forceRest' then lower rest
    return ''.join([str[0].upper(), (str[1:].lower() if forceRest else str[1:])])
    
def decapitalize(str, forceRest = False):
    if isEmpty(str):
        return str

    # if 'forceRest' then upper rest
    return ''.join([str[0].lower(), (str[1:].upper() if forceRest else str[1:])])

def upper(str):
    return toUpperCase(str)

def lower(str):
    return toLowerCase(str)

def toUpperCase(str):
    if str is None:
        return None
    return str.upper()

def toLowerCase(str):
    if str is None:
        return None
    return str.lower()

def toCase(str, upper):
    if str is None:
        return None
    if upper:
        return str.upper()
    else:
        return str.lower()

def toCamelCase(str, separators = None, capitalize = True):
    type = 'Camel' if capitalize else 'camel'
    return _toTypeCase(str, type, separators, None)

def toSnakeCase(str, separators = None, upper = False):
    type = 'SNAKE' if upper else 'snake'
    return _toTypeCase(str, type, separators, SNAKE_CONNECTOR)

def toKebabCase(str, separators = None, upper = False):
    type = 'KEBAB' if upper else 'kebab'
    return _toTypeCase(str, type, separators, KEBAB_CONNECTOR)

##################################################################
# Internal
##################################################################

def _splitOp(str, separators):
    result = []

    if isEmpty(str) or isEmpty(separators):
        result.append(str)
        return result

    strLen = len(str)
    sepLen = len(separators)

    ch = ''
    separator = ''
    find = False

    pos = 0
    end = 0
    i = 0
    j = 0

    while i < strLen:

        ch = str[i]
        find = False
        j = 0

        # Find a separator
        while j < sepLen:
            separator = separators[j]
            if separator == 'A': # TODO: 'A' is special marker for check 'Upper Char'
                if ch.isupper():
                    find = True
                    break
                    
            elif ch == separator:
                find = True
                break
            j += 1

        if find:
            end = i

            if pos < end:
                result.append(str[pos:end])

            if separator == 'A': # TODO: 'A' is special marker for check 'Upper Char'
                pos = end     # include 'Upper Char'
            else:
                pos = end + 1 # skip separator

        i += 1
                
    if pos < strLen:
        result.append(str[pos:])

    return result

# Transform token
def _transformToken(token, caseOp, first):
    if isEmpty(token):
        return token
    
    if caseOp == CO_LOWER_CHAR:                 # camelCase
        if first:
            return token[0].lower() + token[1:] # lower char (first)
        else:
            return token[0].upper() + token[1:] # UPPER char (first)

    elif caseOp == CO_UPPER_CHAR:               # CamelCase, PascalCase
        return token[0].upper() + token[1:]     # UPPER char (first)

    elif caseOp == CO_LOWER:  
        return token.lower()                    # lower case

    elif caseOp == CO_UPPER:
        return token.upper()                    # UPPER case

    return token

# Transformation tokens by caseOp
def _transformOp(tokens, caseOp):
    if tokens is None:
        return

    if len(tokens) == 0:
        return

    # No transformation
    if caseOp == CO_NONE:
        return
        
    for i, token in enumerate(tokens):
        tokens[i] = _transformToken(token, caseOp, i == 0)

# caseOp =  1: 'myname': LOWER
# caseOp =  2: 'MYNAME': UPPER
# caseOp =  3: 'myName': LOWER_CHAR
# caseOp =  4: 'MyName': UPPER_CHAR
def _toCaseOp(str, separators, connector, caseOp):
    if isBlank(str):
        return str
        
    result = '' 
    tokens = _splitOp(str, separators)

    #print("str=", str)
    #print("inp=", tokens)
    _transformOp(tokens, caseOp)
    #print("out=", tokens)

    hasConnector = not isEmpty(connector)

    for i in range(0, len(tokens)):
        if i > 0 and hasConnector:
            result = result + connector
        
        result = result + tokens[i]

    return result

# -  1. lowercase, [lower]~
# -  2. UPPERCASE, [upper]~
# -  3. camelCase, [camel]~
# -  4. CamelCase, PascalCase, [pascal]~
# -  5. snake_case, [snake]
# -  6. SNAKE_CASE, MACRO_CASE, CONSTANT_CASE, [SNAKE], [macro]~
# -  7. Snake_Case, [Snake]
# -  8. kebab-case, dash-case, train-case, lisp-case, [kebab]
# -  9. KEBAB-CASE, DASH-CASE, TRAIN-CASE, COBOL-CASE, [KEBAB], [cobol]~
# - 10. Kebab-Case, Dash-Case, Train-Case, HTTP-Header-Case, [Kebab], [http]~

# caseOp =  1: 'myname': LOWER
# caseOp =  2: 'MYNAME': UPPER
# caseOp =  3: 'myName': LOWER_CHAR
# caseOp =  4: 'MyName': UPPER_CHAR
def _toTypeCase(str, type, separators, connector):

    if isEmpty(str):
        return str

    code = getCaseCode(type)
    if code == 0:
        # Invalid case code
        return str        

    # SIMPLE CASE
    if code == CT_lowercase:
        return toCase(str, False) # lower case
    elif code == CT_UPPERCASE:
        return toCase(str, True)  # UPPER case

    # COMPLEX CASE
    _separators = (DEFAULT_CASE_SEPARATORS_A if isEmpty(separators) else separators)

    _connector = connector
    if code == CT_kebab_case or code == CT_KEBAB_CASE or code == CT_Kebab_Case:
        _connector = (KEBAB_CONNECTOR if isEmpty(connector) else connector)
    elif code == CT_snake_case or code  == CT_SNAKE_CASE or code == CT_Snake_Case:
        _connector = (SNAKE_CONNECTOR if isEmpty(connector) else connector)

    _caseOp = getCaseOp(code)
    
    return _toCaseOp(str, _separators, _connector, _caseOp)

    # UNKNOWN CASE: use 'separators', 'connector'
    #_toCaseOp(str, separators, connector, CO_NONE)

# -  1. lowercase, [lower]~
# -  2. UPPERCASE, [upper]~
# -  3. camelCase, [camel]~
# -  4. CamelCase, PascalCase, [pascal]~
# -  5. snake_case, [snake]
# -  6. SNAKE_CASE, MACRO_CASE, CONSTANT_CASE, [SNAKE], [macro]~
# -  7. Snake_Case, [Snake]
# -  8. kebab-case, dash-case, train-case, lisp-case, [kebab]
# -  9. KEBAB-CASE, DASH-CASE, TRAIN-CASE, COBOL-CASE, [KEBAB], [cobol]~
# - 10. Kebab-Case, Dash-Case, Train-Case, HTTP-Header-Case, [Kebab], [http]~

# Return case code by case type
def getCaseCode(type):

    if type is None:
        return CT_NONE

    if type == 'lower':
        return CT_lowercase         # lowercase
    
    elif type == 'upper':
        return CT_UPPERCASE         # UPPERCASE

    elif type == 'camel':
        return CT_camelCase         # camelCase

    elif type == 'Camel' or type == 'Pascal' or type == 'pascal':
        return CT_PascalCase        # PascalCase

    elif type == 'snake':
        return CT_snake_case        # snake_case
    
    elif type == 'SNAKE' or type == 'MACRO' or type == 'macro':
        return CT_SNAKE_CASE        # SNAKE_CASE

    elif type == 'Snake':
        return CT_Snake_Case        # Snake_Case

    elif type == 'kebab' or type == 'dash' or type == 'train' or type == 'lisp':
        return CT_kebab_case        # kebab-case

    elif type == 'KEBAB' or type == 'DASH' or type == 'TRAIN' or type == 'COBOL' or type == 'cobol':
        return CT_KEBAB_CASE        # KEBAB-CASE

    elif type == 'Kebab' or type == 'Dash' or type == 'Train':
        return CT_Kebab_Case        # Kebab_Case

    return CT_NONE

# Return case op by case code
def getCaseOp(code):
    if code == CT_camelCase:
        # camelCase
        return CO_LOWER_CHAR # lower first char

    elif code == CT_PascalCase:
        # CamelCase
        return  CO_UPPER_CHAR # upper first char

    elif code == CT_kebab_case:
        # kebab-case
        return  CO_LOWER

    elif code == CT_KEBAB_CASE:
        # KEBAB-CASE
        return  CO_UPPER

    elif code == CT_Kebab_Case:
        # Kebab_Case
        return  CO_UPPER_CHAR

    elif code == CT_snake_case:
        # snake_case
        return  CO_LOWER

    elif code  == CT_SNAKE_CASE:
        # SNAKE_CASE
        return  CO_UPPER

    elif code  == CT_Snake_Case:
        # Snake_Case
        return  CO_UPPER_CHAR
    
    return CO_NONE
    
##################################################################

def reverse(str):
    if str is None:
        return None
    return str[::-1]

# 4.1

def startsWith(str, substr):
    if isEmpty(str) or isEmpty(substr):
        return False
    return str.startswith(substr)

def endsWith(str, substr):
    if isEmpty(str) or isEmpty(substr):
        return False
    return str.endswith(substr)

##

def hasPrefix(str, substr):
    return startsWith(str, substr)

def hasSuffix(str, substr):
    return endsWith(str, substr)

