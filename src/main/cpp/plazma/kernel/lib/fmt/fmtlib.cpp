#include <string>
#include <chrono>

#include <vector>
#include <iterator>
#include <map>

#include "fmtlib.h"

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
//

namespace fmtlib {

const std::string DEFAULT_DATE_FORMAT = "%Y-%m-%d";
const std::string DEFAULT_TIME_FORMAT = "%H:%M:%S";
const std::string DEFAULT_DATE_TIME_FORMAT = "%Y-%m-%d %H:%M:%S";

const std::string EMPTY_FORMAT_VALUE = "";
const bool USE_NORMALIZE_FORMAT = true;

std::map<std::string, std::string> dateFormats;
bool initFlag = false;

void _init() {

    initFlag = true;

    ////////////////////////////////////////////////////////////////
	//
	// Date
	//
	////////////////////////////////////////////////////////////////

	// ISO 8601
	dateFormats["YYYY-MM-DD"] = "%Y-%m-%d";
	dateFormats["YYYY.MM.DD"] = "%Y.%m.%d";
	dateFormats["DD.MM.YYYY"] = "%d.%m.%Y";
	dateFormats["DD/MM/YYYY"] = "%d/%m/%Y";
	dateFormats["MM/DD/YYYY"] = "%m/%d/%Y";

	// Unicode
	dateFormats["yyyy-MM-dd"] = "%Y-%m-%d";
	dateFormats["yyyy.MM.dd"] = "%Y.%m.%d";
	dateFormats["dd.MM.yyyy"] = "%d.%m.%Y";
	dateFormats["dd/MM/yyyy"] = "%d/%m/%Y";	
    dateFormats["MM/dd/yyyy"] = "%m/%d/%Y";



    ////////////////////////////////////////////////////////////////
	//
	// Time
	//
	////////////////////////////////////////////////////////////////
	
	// ISO 8601
	dateFormats["hh:mm:ss"] = "%H:%M:%S";
	
	// Unicode
	dateFormats["HH:mm:ss"] = "%H:%M:%S";	



	////////////////////////////////////////////////////////////////
	//
	// DateTime
	//
	////////////////////////////////////////////////////////////////
	
	// ISO 8601	
	dateFormats["YYYY-MM-DD hh:mm:ss"] = "%Y-%m-%d %H:%M:%S";
	dateFormats["YYYY.MM.DD hh:mm:ss"] = "%Y.%m.%d %H:%M:%S";
	dateFormats["DD.MM.YYYY hh:mm:ss"] = "%d.%m.%Y %H:%M:%S";
	dateFormats["DD/MM/YYYY hh:mm:ss"] = "%d/%m/%Y %H:%M:%S";
	dateFormats["MM/DD/YYYY hh:mm:ss"] = "%m/%d/%Y %H:%M:%S";

	// Unicode
	dateFormats["yyyy-MM-dd HH:mm:ss"] = "%Y-%m-%d %H:%M:%S";
	dateFormats["yyyy.MM.dd HH:mm:ss"] = "%Y.%m.%d %H:%M:%S";
	dateFormats["dd.MM.yyyy HH:mm:ss"] = "%d.%m.%Y %H:%M:%S";
	dateFormats["dd/MM/yyyy HH:mm:ss"] = "%d/%m/%Y %H:%M:%S";
	dateFormats["MM/dd/yyyy HH:mm:ss"] = "%m/%d/%Y %H:%M:%S";

}

// 1.1 Common

std::string _vformat(/*FILE *fp,*/ const char *fmt, va_list va) {

    // https://stackoverflow.com/questions/1657883/variable-number-of-arguments-in-c
    // https://stackoverflow.com/questions/7315936/which-of-sprintf-snprintf-is-more-secure
    // https://en.cppreference.com/w/cpp/utility/variadic/va_arg

    va_list va2;
    va_copy(va2, va);

    size_t n = std::vsnprintf(NULL, 0, fmt, va2) + 1; // +1 for the '\0'
 
    char str[n]; // 50
    //int n = sizeof str;

    //const char* v1 = va_arg(va, const char*);
    std::vsnprintf(str, n, fmt, va);

    return str;
}

std::string vformat(const char *fmt, ...) {
    va_list va;
    va_start(va, fmt);
    std::string str = _vformat(fmt, va);
    va_end(va);
    return str;
}

std::string format(const std::string& message, const std::string& value) {
	return vformat(message.c_str(), value.c_str());
    //char buff[100]; // TODO: what about dynamic size?
    //snprintf(buff, sizeof(buff), message.c_str(), value.c_str());
    //std::string result = buff;
    //return result;
}

std::string format(const std::string& message, const std::string& value1, const std::string& value2) {
	return vformat(message.c_str(), value1.c_str(), value2.c_str());
}

////

std::string _normalizeFormat(std::map<std::string, std::string>& formats, const std::string& format, const std::string& defaultFormat) {
	
	// Normalize string
	//format = normalize(format);

	std::string result;
	if (format.empty()) {
		result = defaultFormat;
	    return result;
	}
	
	if (!USE_NORMALIZE_FORMAT) {
		result = format;
	    return result;
	}
	
	// Normalize format
	result = formats[format];
	if (!result.empty()) {
	    return result;
	}
	result = format;
	return result;
}

std::string _formatDate(const std::chrono::system_clock::time_point& date, const std::string& format, const std::string& defaultFormat) {

    // Initialization
    if (!initFlag) {
        _init();
    }

    // Normalize format
    std::string resultFormat = _normalizeFormat(dateFormats, format, defaultFormat);

    std::time_t now = std::chrono::system_clock::to_time_t(date);

    std::string result(30, '\0'); // TODO: what about dynamic size?
    std::strftime(&result[0], result.size(), resultFormat.c_str(), std::localtime(&now));

    return result;
}

std::string _formatDate(const std::chrono::system_clock::time_point& date, const std::string& format) {
    return _formatDate(date, format, "");
}

std::chrono::system_clock::time_point _getCurrentDate() {
    return std::chrono::system_clock::now();
}

// 2.1 Date

std::string formatDate() {
    return _formatDate(_getCurrentDate(), DEFAULT_DATE_FORMAT);
}

std::string formatDate(const std::string& format) {
    return _formatDate(_getCurrentDate(), format, DEFAULT_DATE_FORMAT);
}

//

std::string formatDate(const std::chrono::system_clock::time_point& date) {
    return _formatDate(date, DEFAULT_DATE_FORMAT);
}

std::string formatDate(const std::chrono::system_clock::time_point& date, const std::string& format) {
    return _formatDate(date, format, DEFAULT_DATE_FORMAT);
}

// 2.2 Time

std::string formatTime() {
    return _formatDate(_getCurrentDate(), DEFAULT_TIME_FORMAT);
}

std::string formatTime(const std::string& format) {
    return _formatDate(_getCurrentDate(), format, DEFAULT_TIME_FORMAT);
}

//

std::string formatTime(const std::chrono::system_clock::time_point& date) {
    return _formatDate(date, DEFAULT_TIME_FORMAT);
}

std::string formatTime(const std::chrono::system_clock::time_point& date, const std::string& format) {
    return _formatDate(date, format, DEFAULT_TIME_FORMAT);
}

// 2.3 DateTime

std::string formatDateTime() {
    return _formatDate(_getCurrentDate(), DEFAULT_DATE_TIME_FORMAT);
}

std::string formatDateTime(const std::string& format) {
    return _formatDate(_getCurrentDate(), format, DEFAULT_DATE_TIME_FORMAT);
}

//

std::string formatDateTime(const std::chrono::system_clock::time_point& date) {
    return _formatDate(date, DEFAULT_DATE_TIME_FORMAT);
}

std::string formatDateTime(const std::chrono::system_clock::time_point& date, const std::string& format) {
    return _formatDate(date, format, DEFAULT_DATE_TIME_FORMAT);
}

}