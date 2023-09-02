#include <iostream>

#include "plazma/kernel/lib/str/strlib.h"
#include "plazma/kernel/lib/num/numlib.h"

#include "sysarg.h"

namespace syslib {

/**
 * Parse application argument array and return parameter map
 */
std::map<std::string, std::string> parseArguments(int argc, char* argv[]) {
  
  std::map<std::string, std::string> parameters;
  std::string arg = "";
  std::string key = "";
  std::string value = "";
  int i = 0;
  int j = 0;
  bool clearPrefix = true;

  // parse argument array
  // format: -parameter1 value1 -parameter2 value2 ... -parameter<N> value<N>
  // 'value' without 'parameter' will skip 
  while (i < argc) {
    arg = argv[i];
    if (strlib::startsWith(arg, DEFAULT_ARG_PREFIX) && arg.length() > 1) {
      key = arg;
      if (clearPrefix) {
        key = key.substr(1);
      }
      value = "";
      j = i + 1;
      if (j < argc && !strlib::startsWith(argv[j], DEFAULT_ARG_PREFIX)) {
        value = argv[j];
        i = j;        
      }
      // insert or update
      parameters[key] = value; 
    }
    i += 1;
  }
  return parameters;

}

////

void printParameters(std::map<std::string, std::string> &parameters) {
  std::map<std::string, std::string>::iterator it = parameters.begin();
    while(it != parameters.end()) {
      std::cout << it->first << " : " << it->second << std::endl;
        it++;
    }
}

bool hasParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return parameters.find(parameter) != parameters.end();
}

std::string getParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return parameters[parameter];
}

// int

int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return numlib::toInt(getParameter(parameters, parameter));
}

int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, int def) {
    return numlib::toInt(getParameter(parameters, parameter), def);
}

// float

float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return numlib::toFloat(getParameter(parameters, parameter));
}

float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, float def) {
    return numlib::toFloat(getParameter(parameters, parameter), def);
}

// double

float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return numlib::toFloat(getParameter(parameters, parameter));
}

float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, double def) {
    return numlib::toDouble(getParameter(parameters, parameter), def);
}


}
//#endif // PLAZMA_KERNEL_LIB_SYSARG_H