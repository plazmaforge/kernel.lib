#ifndef PLAZMA_KERNEL_FMT_FMTLIB_H
#define PLAZMA_KERNEL_FMT_FMTLIB_H

#include <string>
#include <chrono>

/////////////////////////////////////////////////////////////////////////////////
// 
// Common
// 
// 1.1
//
// - string format(const string& message, const string& value)
//
    
/////////////////////////////////////////////////////////////////////////////////
//
// Date/Time/DateTime
//
// 2.1
//
// - string formatDate()
// - string formatDate(string format)
//
// - string formatDate(time_point date)
// - string formatDate(time_point date, string format)
//
// 2.2
//
// - string formatTime()
// - string formatTime(string format)
//
// - string formatTime(time_point date)
// - string formatTime(time_point date, string format)
//
// 2.3
//
// - string formatDateTime()
// - string formatDateTime(time_point format)
//
// - string formatDateTime(time_point date)
// - string formatDateTime(time_point date, string format)

namespace fmtlib {

// 1.1 Common

std::string vformat(const char *fmt, ...);

std::string format(const std::string& message, const std::string& value);

std::string format(const std::string& message, const std::string& value1, const std::string& value2);

// 2.1 Date

std::string formatDate();

std::string formatDate(const std::string& format);

//

std::string formatDate(const std::chrono::system_clock::time_point& date);

std::string formatDate(const std::chrono::system_clock::time_point& date, const std::string& format);

// 2.2 Time

std::string formatTime();

std::string formatTime(const std::string& format);

//

std::string formatTime(const std::chrono::system_clock::time_point& date);

std::string formatTime(const std::chrono::system_clock::time_point& date, const std::string& format);

// 2.3 DateTime

std::string formatDateTime();

std::string formatDateTime(const std::string& format);

//

std::string formatDateTime(const std::chrono::system_clock::time_point& date);

std::string formatDateTime(const std::chrono::system_clock::time_point& date, const std::string& format);

}


#endif // PLAZMA_KERNEL_LIB_FMT_FMTLIB_H