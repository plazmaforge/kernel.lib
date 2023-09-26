#ifndef PLAZMA_LIB_IO_IOLIB_WIN_H
#define PLAZMA_LIB_IO_IOLIB_WIN_H

#include <string>
#include <locale>
#include <codecvt>

#ifdef _WIN32
#include <io.h>
#include <windows.h>

namespace iolib_win {

// ============================================================================================
// SOLUTION-1
// ============================================================================================

std::string ConvertWideToAnsi(const std::wstring &wstr) {
    int count = WideCharToMultiByte(CP_ACP, 0, wstr.c_str(), wstr.length(),
                                    NULL, 0, NULL, NULL);
    std::string str(count, 0);
    WideCharToMultiByte(CP_ACP, 0, wstr.c_str(), -1, &str[0], count, NULL,
                        NULL);
    return str;
}

std::wstring ConvertAnsiToWide(const std::string &str) {
    int count =
        MultiByteToWideChar(CP_ACP, 0, str.c_str(), str.length(), NULL, 0);
    std::wstring wstr(count, 0);
    MultiByteToWideChar(CP_ACP, 0, str.c_str(), str.length(), &wstr[0], count);
    return wstr;
}

std::string ConvertWideToUtf8(const std::wstring &wstr) {
    int count = WideCharToMultiByte(CP_UTF8, 0, wstr.c_str(), wstr.length(),
                                    NULL, 0, NULL, NULL);
    std::string str(count, 0);
    WideCharToMultiByte(CP_UTF8, 0, wstr.c_str(), -1, &str[0], count, NULL,
                        NULL);
    return str;
}

std::wstring ConvertUtf8ToWide(const std::string &str) {
    int count =
        MultiByteToWideChar(CP_UTF8, 0, str.c_str(), str.length(), NULL, 0);
    std::wstring wstr(count, 0);
    MultiByteToWideChar(CP_UTF8, 0, str.c_str(), str.length(), &wstr[0], count);
    return wstr;
}

// ============================================================================================
// SOLUTION-2
// ============================================================================================
std::wstring ConvertStringToWstring(const std::string &str) {
    if (str.empty()) {
        return std::wstring();
    }
    int num_chars = MultiByteToWideChar(CP_ACP, MB_ERR_INVALID_CHARS,
                                        str.c_str(), str.length(), NULL, 0);
    std::wstring wstrTo;
    if (num_chars) {
        wstrTo.resize(num_chars);
        if (MultiByteToWideChar(CP_ACP, MB_ERR_INVALID_CHARS, str.c_str(),
                                str.length(), &wstrTo[0], num_chars)) {
            return wstrTo;
        }
    }
    return std::wstring();
}

// =======================================================================================
// 
// =======================================================================================

// DWORD handleId = STD_OUTPUT_HANDLE;
void _WriteConsole(const HANDLE handle, const std::wstring &buffer) {
    if (buffer.empty()) {
        return;
    }
    DWORD size; // write size
    ::WriteConsoleW(handle, buffer.c_str(), buffer.size(), &size, NULL);
}

void _WriteConsole(const HANDLE handle, const std::string &buffer) {
    if (buffer.empty()) {
        return;
    }
    const std::wstring wbuffer = ConvertUtf8ToWide(buffer); //utf8_to_wstring(buffer);
    _WriteConsole(handle, wbuffer);
}

void _WriteConsole(const DWORD handleId, const std::wstring &buffer) {
    const HANDLE handle = ::GetStdHandle(handleId);
    _WriteConsole(handle, buffer);
}

void _WriteConsole(const DWORD handleId, const std::string &buffer) {
    const HANDLE handle = ::GetStdHandle(handleId);
    _WriteConsole(handle, buffer);
}

void _WriteConsoleOutput(const std::wstring &buffer) {
    _WriteConsole(STD_OUTPUT_HANDLE, buffer);
}

void _WriteConsoleOutput(const std::string &buffer) {
    _WriteConsole(STD_OUTPUT_HANDLE, buffer);
}

void _WriteConsoleError(const std::wstring &buffer) {
    _WriteConsole(STD_ERROR_HANDLE, buffer);
}

void _WriteConsoleError(const std::string &buffer) {
    _WriteConsole(STD_ERROR_HANDLE, buffer);
}

////

std::string _ReadConsole(const HANDLE handle) {
    wchar_t wbuffer[128];
    DWORD size; // read size
    if (!::ReadConsoleW(handle, wbuffer, ARRAYSIZE(wbuffer) - 1, &size, NULL)) {
        return "";
    }
    
    wbuffer[size] = L'\0';

    //std::string buffer = ConvertWideToUtf8(wbuffer);
    //setg(&buffer[0], &buffer[0], &buffer[0] + buffer.size());

    return ConvertWideToUtf8(wbuffer) ;// wstring_to_utf8(wbuffer);
}

std::string _ReadConsole(const DWORD handleId) {
    const HANDLE handle = ::GetStdHandle(handleId);
    return _ReadConsole(handle);
}

std::string _ReadConsoleInput() {
    return _ReadConsole(STD_INPUT_HANDLE);
}

// https://stackoverflow.com/questions/1660492/utf-8-output-on-windows-console
// mike.dld's answer
class ConsoleStreamBufWin32 : public std::streambuf {
  public:
    ConsoleStreamBufWin32(DWORD handleId, bool isInput);

  protected:
    // std::basic_streambuf
    virtual std::streambuf *setbuf(char_type *s, std::streamsize n);
    virtual int sync();
    virtual int_type underflow();
    virtual int_type overflow(int_type c = traits_type::eof());

  private:
    HANDLE const m_handle;
    bool const m_isInput;
    std::string m_buffer;
};

ConsoleStreamBufWin32::ConsoleStreamBufWin32(DWORD handleId, bool isInput)
    : m_handle(::GetStdHandle(handleId)), m_isInput(isInput), m_buffer() {
    if (m_isInput) {
        setg(0, 0, 0);
    }
}

std::streambuf *ConsoleStreamBufWin32::setbuf(char_type * /*s*/,
                                              std::streamsize /*n*/) {
    return 0;
}

int ConsoleStreamBufWin32::sync() {
    if (m_isInput) {
        ::FlushConsoleInputBuffer(m_handle);
        setg(0, 0, 0);
    } else {
        if (m_buffer.empty()) {
            return 0;
        }

        ////
        //std::wstring const wideBuffer = utf8_to_wstring(m_buffer);
        //DWORD writtenSize;
        //::WriteConsoleW(m_handle, wideBuffer.c_str(), wideBuffer.size(), &writtenSize, NULL);
        ////

        ////
        _WriteConsole(m_handle, m_buffer);
        ////

    }

    m_buffer.clear();

    return 0;
}


ConsoleStreamBufWin32::int_type ConsoleStreamBufWin32::underflow() {
    if (!m_isInput) {
        return traits_type::eof();
    }

    if (gptr() >= egptr()) {

        ////
        //wchar_t wideBuffer[128];
        //DWORD readSize;
        //if (!::ReadConsoleW(m_handle, wideBuffer, ARRAYSIZE(wideBuffer) - 1, &readSize, NULL)) {
        //    return traits_type::eof();
        //}
        //wideBuffer[readSize] = L'\0';
        //m_buffer = wstring_to_utf8(wideBuffer);
        ////

        ////
        std::string input = _ReadConsole(m_handle);
        if (input.empty()) {
            return traits_type::eof();
        }
        m_buffer = input;
        ////

        setg(&m_buffer[0], &m_buffer[0], &m_buffer[0] + m_buffer.size());

        if (gptr() >= egptr()) {
            return traits_type::eof();
        }
    }

    return sgetc();
}

ConsoleStreamBufWin32::int_type ConsoleStreamBufWin32::overflow(int_type c) {
    if (m_isInput) {
        return traits_type::eof();
    }

    m_buffer += traits_type::to_char_type(c);
    return traits_type::not_eof(c);
}

template <typename StreamT>
inline void FixStdStream(DWORD handleId, bool isInput, StreamT &stream) {
    if (::GetFileType(::GetStdHandle(handleId)) == FILE_TYPE_CHAR) {
        stream.rdbuf(new ConsoleStreamBufWin32(handleId, isInput));
    }
}

// some code are from this blog
// https://blog.csdn.net/witton/article/details/108087135

/*
#define printf(fmt, ...) __fprint(stdout, fmt, ##__VA_ARGS__)

int __vfprint(FILE *fp, const char *fmt, va_list va) {
    // https://stackoverflow.com/questions/7315936/which-of-sprintf-snprintf-is-more-secure
    size_t nbytes = snprintf(NULL, 0, fmt, va) + 1; 
    // +1 for the '\0' 
    char *str = (char *)malloc(nbytes);
    snprintf(str, nbytes, fmt, va);

    //std::cout << str;
    _WriteConsoleOutput(str);
    
    free(str);
    return nbytes;
}

int __fprint(FILE *fp, const char *fmt, ...) {
    va_list va;
    va_start(va, fmt);
    int n = __vfprint(fp, fmt, va);
    va_end(va);
    return n;
}

void _printf(const char *fmt, ...) {
    printf(fmt);
}
*/


//// INIT

void init_utf8_console_win() {
    FixStdStream(STD_INPUT_HANDLE, true, std::cin);
    FixStdStream(STD_OUTPUT_HANDLE, false, std::cout);
    FixStdStream(STD_ERROR_HANDLE, false, std::cerr);
}

//// CONVERT

// convert UTF-8 string to wstring
std::wstring utf8_to_wstring(const std::string &str) {
    return ConvertUtf8ToWide(str);
    // return ConvertStringToWstring(str);
}

// convert wstring to UTF-8 string
std::string wstring_to_utf8(const std::wstring &str) {
    return ConvertWideToUtf8(str);
}

std::wstring ansi_to_wstring(const std::string &str) {
    return ConvertAnsiToWide(str);
}

std::string wstring_to_ansi(const std::wstring &str) {
   return ConvertWideToAnsi(str);
}

// CONSOLE

void _out(const std::string &str) {
    _WriteConsoleOutput(str);
}

void _out(const std::wstring &str) {
    _WriteConsoleOutput(str);
}

void _err(const std::string &str) {
    _WriteConsoleError(str);
}

void _err(const std::wstring &str) {
    _WriteConsoleError(str);
}

} // namespace iolib

#endif

#endif // PLAZMA_LIB_IO_IOLIB_WIN_H