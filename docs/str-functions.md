| Pseudo Code                                             | Java                                                  | C++                                                                | Python                              |
| ------------------------------------------------------- | ----------------------------------------------------- | ------------------------------------------------------------------ | ----------------------------------- |
| isEmpty(str: string): boolean                           | boolean isEmpty(String str)                           | bool isEmpty(const string& str)                                    | def isEmpty(str)                    |
| isBlank(str: string): boolean                           | boolean isBlank(String str)                           | bool isBlank(const string& str)                                    | def isBlank(str)                    |
| size(str: string): int                                  | int size(String str)                                  | int size(const string& str)                                        | def size(str)                       |
| equals(str1: string, str2: string): boolean             | boolean equals(String str1, String str2)              | bool equals(const string& str1, const string& str2)                | def equals(str1, str2)              |
| normalize(str: string): string                          | String normalize(String str)                          | string normalize(const string& str)                                | def normalize(str)                  |
| emptyIfNull(str: string): string                        | String emptyIfNull(String str)                        | -                                                                  | -                                   |
| nullIfEmpty(str: string): string                        | String nullIfEmpty(String str)                        | -                                                                  | -                                   |
| nullIfEmpty(str: string, boolean trim): string          | String nullIfEmpty(String str, boolean trim)          | -                                                                  | -                                   |
| defaultIfNull(str: string, defaultStr: string): string  | String defaultIfNull(String str, String defaultStr)   | -                                                                  | -                                   |
| defaultIfEmpty(str: string, defaultStr: string): string | String defaultIfEmpty(String str, String defaultStr)  | string defaultIfEmpty(const string& str, const string& defaultStr) | def defaultIfEmpty(str, defaultStr) |
| trim(str: string): string                               | String trim(String str)                               | string trim(const string& str)                                     | +
| trim(str: string, ch: char): string                     | String trim(String str, char ch)                      | string trim(const string& str, char ch)                            | def trim(str, ch = None)
| ltrim(str: string): string                              | String ltrim(String str)                              | string ltrim(const string& str)                                    | +
| ltrim(str: string, ch: char): string                    | String ltrim(String str, char ch)                     | string ltrim(const string& str, char ch)                           | def ltrim(str, ch = None)
| rtrim(str: string): string                              | String rtrim(String str)                              | string rtrim(const string& str)                                    | +
| rtrim(str: string, ch: char): string                    | String rtrim(String str, char ch)                     | string rtrim(const string& str, char ch)                           | def rtrim(str, ch = None)
| findFirstNotOf(str: string, ch: char): int              | String findFirstNotOf(String str, char ch)            | string findFirstNotOf(const string& str, char ch)                  | +
| findFirstNotOf(str: string, ch: char, start: int)       | String findFirstNotOf(String str, char ch, int start) | string findFirstNotOf(const string& str, char ch, int start)       | def findFirstNotOf(str, ch, start = None)
| findLastNotOf(str: string, ch: char): int               | String findLastNotOf(String str, char ch)             | string findLastNotOf(const string& str, char ch)                   | +
| findLastNotOf(str: string, ch: char, end: int): int     | String findLastNotOf(String str, char ch, int end)    | string findLastNotOf(const string& str, char ch, int end)          | def findLastNotOf(str, ch, end = None)
| replicate(str: string, n: int): string                  | String replicate(String str, int n)                   | string replicate(const string& str, int n)                         | def replicate(str, n)
| replicate(ch: char, n: int): string                     | String replicate(char ch, int n)                      | string replicate(const char ch, int n)                             | +
| lpad(str: string, len: int): string                     | String lpad(String str, int len)                      | string lpad(const string& str, int len)                            | +
| lpad(str: string, len: int, pad: string): string        | String lpad(String str, int len, String pad)          | string lpad(const string& str, int len, const string& pad)         | def lpad(str, len, pad = " ")
| lpad(str: string, len: int, pad: string): string        | String lpad(String str, int len, char pad)            | string lpad(const string& str, int len, const char pad)            | +
| rpad(str: string, len: int): string                     | String rpad(String str, int len)                      | string rpad(const string& str, int len)                            | +
| rpad(str: string, len: int, pad: string): string        | String rpad(String str, int len, String pad)          | string rpad(const string& str, int len, const string& pad)         | def rpad(str, len, pad = " ")
| rpad(str: string, len: int, pad: string): string        | String rpad(String str, int len, char pad)            | string rpad(const string& str, int len, const char pad)            | +
| fill(str: string, len: int): string                     | String fill(String str, int len)                      | string fill(const string& str, int len)                            | +
| fill(str: string, len: int, pad: string): string        | String fill(String str, int len, String pad)          | string fill(const string& str, int len, const string& pad)         | def fill(str, len, pad = " ")
| fill(str: string, len: int, pad: char): string          | String fill(String str, int len, char pad)            | string fill(const string& str, int len, const char pad)            | +
| ellipsis(str: string, len: int): string                 | String ellipsis(String str, int len)                  | string ellipsis(const string& str, int len)                        | def ellipsis(str, len)
| trunc(str: string, len: int): string                    | String trunc(String str, int len)                     | string trunc(const string& str, int len)                           | def trunc(str, len, trim = False, ellipsis = False)
| left(str: string, len: int): string                     | String left(String str, int len)                      | string left(const string& str, int len)                            | def left(str, len)
| right(str: string, len: int): string                    | String right(String str, int len)                     | string right(const string& str, int len)                           | def right(str, len)



    








    
    
    


