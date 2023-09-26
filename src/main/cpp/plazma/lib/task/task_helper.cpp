#include <string>

#include "task_helper.h"
#include "plazma/lib/sys/syslib.h"
#include "plazma/lib/str/strlib.h"
#include "plazma/lib/fmt/fmtlib.h"
#include "plazma/lib/fs/fslib.h"

const std::string MESSAGE_PROCESSING = "Processing...";
const std::string MESSAGE_START_TASK = "Start task: '%s'";
const std::string MESSAGE_TOTAL_TIME = "Total time: %s";

const std::string MESSAGE_NOT_SETTING = "<not setting>";

/*

void printParameter(const std::string& name, const std::string& value) {
    printParameter(name, value, 0, true);
}

void printParameter(const std::string& name, const std::string& value, int len) {
    printParameter(name, value, len, true);
}

void printParameter(const std::string& name, const std::string& value, bool setting) {
    printParameter(name, value, 0, setting);
}
*/

void printParameter(Parameter* parameter, int len) {
    if (parameter == nullptr) {
            return;            
    }
    std::string name = parameter->getName();
    std::string value = parameter->getValue();
    std::string dataType = parameter->getDataType();
    bool setting = parameter->hasValue();

    std::string displayName = formatParameterName(name, len);
    std::string displayValue = formatParameterValue(value, setting);
        
        //if ("Date".equalsIgnoreCase(dataType)) {
        //    displayValue = format
        //}        

    syslib::println(" " + displayName + ": " + displayValue);
}

// string
void printParameter(const std::string& name, const std::string& value, int len, bool setting) {
    syslib::println(" " + formatParameterName(name, len)  + ": " + formatParameterValue(value, setting));    
}

/*
// int
void printParameter(const std::string& name, const int value, int len, bool setting) {
    printParameter(name, std::to_string(value), len, setting);
}

// float
void printParameter(const std::string& name, const float value, int len, bool setting) {
    printParameter(name, std::to_string(value), len, setting);
}

// double
void printParameter(const std::string& name, const double value, int len, bool setting) {
    printParameter(name, std::to_string(value), len, setting);
}
*/

// bool
//void printParameter(const std::string& name, const bool value, int len, bool setting) {
//    printParameter(name, (value ? "true" : "false") /*to_string(value)*/, len, setting);
//}

////

void printProcessing() {
    syslib::printMessage(MESSAGE_PROCESSING);
}

void printStartTask(const std::string &task) {
    // format message
    syslib::printfMessage(MESSAGE_START_TASK, task);
}

void printTotalTimeStr(const std::string &timeStr) {
    // format message
    syslib::printfMessage(MESSAGE_TOTAL_TIME, timeStr);
}

void printTotalTime(long time) {
    printTotalTimeStr(formatTime(time));
}
    
void printTotalTimeInMilliseconds(long time) {
    printTotalTimeStr(formatTimeInMilliseconds(time));
}
    
void printTotalTimeInSeconds(long time) {
    printTotalTimeStr(formatTimeInSeconds(time));
}
    
// format

std::string formatParameterName(const std::string& name, int len) {
    // -<name>......
    return "-" + (len < 1 ? name : strlib::rpad(name, len, "."));
}

std::string formatParameterValue(const std::string& value, bool setting) {
    // <value>
    // not setting
    return (setting ? value : MESSAGE_NOT_SETTING);
}

std::string formatTimeInMilliseconds(long time) {
    return std::to_string(time) + " ms";
}

std::string formatTimeInSeconds(long time) {
    return std::to_string(time / 1000.0) + " s";
}

std::string formatTime(long time) {
    if (time > 1000) {
        return formatTimeInSeconds(time);        // in seconds
    } else {
        return formatTimeInMilliseconds(time);   // in milliseconds
    }
}

////

int maxParameterLen(const std::string array[], int len) {
    return strlib::maxLen(array, len) + 2; // '<name>..'
}

int maxParameterLen(Parameters* parameters) {

    if (parameters == nullptr) {
        return 0;
    }

    int result = 0;
    int len = 0;
    int count = parameters->getParameterCount();

    Parameter* parameter = nullptr;
    std::string name;

    for (int i = 0; i < count; i++) {
        parameter = parameters->getParameter(i);
        name = parameter->getName();
        len = name.length();
        if (len > result) {
            result = len;
        }
    }

    return result + 2; // '<name>..'
}

// fs
std::string getOptionalPath(const std::string& path1, const std::string& path2) {
    return fslib::getOptionalPath(path1, path2);
}

// fs
std::string generateFileName(std::string& dir, std::string& filePrefix, int number, std::string& fileExt) {
  return fslib::generateFileName(dir, filePrefix, number, fileExt);
}

// fs
std::string generateFileName(std::string& filePrefix, int number, std::string& fileExt) {
  return fslib::generateFileName(filePrefix, number, fileExt);
}


