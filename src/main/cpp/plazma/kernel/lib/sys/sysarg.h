#ifndef PLAZMA_KERNEL_LIB_SYSARG_H
#define PLAZMA_KERNEL_LIB_SYSARG_H

#include <string>
#include <map>

#include "define.h"

namespace syslib {

  CONST_STRING DEFAULT_ARG_PREFIX = "-";

  std::map<std::string, std::string> parseArguments(int argc, char* argv[]);


////

void printParameters(std::map<std::string, std::string> &parameters);

bool hasParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

std::string getParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

// int

int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, int def);

// float

float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, float def);

// double

float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, double def);

}
#endif // PLAZMA_KERNEL_LIB_SYSARG_H