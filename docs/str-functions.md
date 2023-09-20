| Pseudo Code                                             | Java Code                                            | C++ Code                                                           |
| ------------------------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------------ |
| isEmpty(str: string): boolean                           | boolean isEmpty(String str)                          | bool isEmpty(const string& str)                                    |
| isEmpty(str: string, trim: boolean): string             | boolean isEmpty(String str, boolean trim)            | bool isEmpty(const string& str, bool trim)                         |
| isBlank(str: string): boolean                           | boolean isBlank(String str)                          | bool isBlank(const string& str)                                    |
| size(str: string): int                                  | int size(String str)                                 | int size(const string& str)                                        |
| equals(str1: string, str2: string): boolean             | boolean equals(String str1, String str2)             | bool equals(const string& str1, const string& str2)                |
| normalize(str: string): string                          | String normalize(String str)                         | string normalize(const string& str)                                |
| emptyIfNull(str: string): string                        | String emptyIfNull(String str)                       | -                                                                  |
| nullIfEmpty(str: string): string                        | String nullIfEmpty(String str)                       | -                                                                  |
| nullIfEmpty(str: string, boolean trim): string          | String nullIfEmpty(String str, boolean trim)         | -                                                                  |
| defaultIfNull(str: string, defaultStr: string): string  | String defaultIfNull(String str, String defaultStr)  | -                                                                  |
| defaultIfEmpty(str: string, defaultStr: string): string | String defaultIfEmpty(String str, String defaultStr) | string defaultIfEmpty(const string& str, const string& defaultStr) |
| trim(str: string): string                               | String trim(String str)                              | string trim(const string  &str)                                    |
| trim(str: string, ch: char): string                     | String trim(String str, char ch)                     | string trim(const string  &str, char ch)                           |
| ltrim(str: string): string                              | String ltrim(String str)                             | string ltrim(const string  &str)                                   |
| ltrim(str: string, ch: char): string                    | String ltrim(String str, char ch)                    | string ltrim(const string  &str, char ch)                          |
| rtrim(str: string): string                              | String rtrim(String str)                             | string rtrim(const string  &str)                                   |
| rtrim(str: string, ch: char): string                    | String rtrim(String str, char ch)                    | string rtrim(const string  &str, char ch)                          |