#ifndef PLAZMA_KERNEL_LIB_TASK_TASK_HELPER_H
#define PLAZMA_KERNEL_LIB_TASK_TASK_HELPER_H

#include <string>

#include "plazma/lib/sys/Parameters.h" 
#include "plazma/lib/sys/Parameter.h" 

using namespace sys;

/*
void printParameter(const std::string& name, const std::string& value);

void printParameter(const std::string& name, const std::string& value, int len);

void printParameter(const std::string& name, const std::string& value, bool setting);
*/

void printParameter(Parameter* parameter, int len);

// string
void printParameter(const std::string& name, const std::string& value, int len, bool setting);

/*

// int
void printParameter(const std::string& name, const int value, int len, bool setting);

// float
void printParameter(const std::string& name, const float value, int len, bool setting);

// double
void printParameter(const std::string& name, const double value, int len, bool setting);
*/

// bool
//void printParameter(const std::string& name, const bool value, int len, bool std::setting);

////

void printStartTask(const std::string &task);

void printProcessing();

void printTotalTime(long time);
    
void printTotalTimeInMilliseconds(long time);
    
void printTotalTimeInSeconds(long time);
    
// format

std::string formatParameterName(const std::string& name, int len);

std::string formatParameterValue(const std::string& value, bool setting);

std::string formatTimeInMilliseconds(long time);

std::string formatTimeInSeconds(long time);

std::string formatTime(long time);

//

int maxParameterLen(const std::string array[], int len);

int maxParameterLen(Parameters* parameters);


// fs

std::string getOptionalPath(const std::string& p1, const std::string& p2);

#endif // PLAZMA_KERNEL_LIB_TASK_TASK_HELPER_H