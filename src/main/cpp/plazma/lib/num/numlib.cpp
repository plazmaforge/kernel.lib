#include <iostream>
#include <fstream>
#include <sstream>
#include <cmath>

#include "numlib.h"

namespace numlib {

  //const bool DEFAULT_BOOLEAN = false;
    
  //const byte DEFAULT_BYTE = (byte) 0;

  //const char DEFAULT_CHAR = '';

  //const short DEFAULT_SHORT = 0;

  const int DEFAULT_INT = 0;

  //const long DEFAULT_LONG = 0;

  const float DEFAULT_FLOAT = 0.0;

  const double DEFAULT_DOUBLE = 0.0;


  int parseInt(const std::string &input) {
    return std::atoi(input.c_str());
  }

  // https://stackoverflow.com/questions/24971438/parsing-float-function-in-c
  // http://www.fastformat.org/
  // https://rosettacode.org/wiki/Real_constants_and_functions

  float parseFloat(const std::string &input) {

    //stof(input);
    //strtof(input.c_str(), 0); // OK !!!
    //atof(input.c_str());

    const char *p = input.c_str();
    if (!*p || *p == '?')
      return 0; //TODO: NAN_D;
    int s = 1;
    while (*p == ' ')
      p++;

    if (*p == '-') {
      s = -1;
      p++;
    }

    float acc = 0;
    while (*p >= '0' && *p <= '9')
      acc = acc * 10 + *p++ - '0';

    if (*p == '.') {
      float k = 0.1;
      p++;
      while (*p >= '0' && *p <= '9')
      {
        acc += (*p++ - '0') * k;
        k *= 0.1;
      }
    }
    //TODO: if (*p) die("Invalid numeric format");
    return s * acc;
  }

  double parseDouble(const std::string &input) {

    //stof(input);
    //strtof(input.c_str(), 0); // OK !!!
    //atof(input.c_str());

    const char *p = input.c_str();
    if (!*p || *p == '?')
      return 0; //TODO: NAN_D;
    int s = 1;
    while (*p == ' ')
      p++;

    if (*p == '-') {
      s = -1;
      p++;
    }

    double acc = 0;
    while (*p >= '0' && *p <= '9')
      acc = acc * 10 + *p++ - '0';

    if (*p == '.') {
      float k = 0.1;
      p++;
      while (*p >= '0' && *p <= '9') {
        acc += (*p++ - '0') * k;
        k *= 0.1;
      }
    }
    //TODO: if (*p) die("Invalid numeric format");
    return s * acc;
  }

  ////

  // int

  int toInt(const std::string &str) {
    return toInt(str, DEFAULT_INT);
  }

  int toInt(const std::string &str, int def) {
    if (str.empty()) {
      return def;
    }
    return parseInt(str);
  }

  // float

  float toFloat(const std::string &str) {
    return toFloat(str, DEFAULT_FLOAT);
  }

  float toFloat(const std::string &str, float def) {
    if (str.empty()) {
      return def;
    }
    return parseFloat(str);
  }


  // double

  double toDouble(const std::string &str) {
    return toDouble(str, DEFAULT_DOUBLE);
  }

  double toDouble(const std::string &str, double def) {
    if (str.empty()) {
      return def;
    }
    return parseDouble(str);
  }

  ////

  float randomFloat(float min, float max) {
    return (max - min) * ((((float)rand()) / (float)RAND_MAX)) + min;
  }

  std::vector<float> splitFloat(const std::string &str, char delim) {
    std::vector<float> result;
    std::stringstream ss(str);
    std::string item;
    while (getline(ss, item, delim)) {
      result.push_back(parseFloat(item));
    }
    return result;
  }

  std::vector<std::vector<float>> readFloatArray(std::ifstream &file) {
    std::string line;
    std::vector<std::vector<float>> records;
    while (getline(file, line)) {
      std::vector<float> values = splitFloat(line, ',');
      records.push_back(values);
    }
    return records;
  }

  std::vector<std::vector<float>> readFloatArray(const std::string &fileName) {
    std::ifstream file;
    std::string line;

    file.open(fileName);
    std::vector<std::vector<float>> records;
    if (file.is_open()) {
      records = readFloatArray(file);
    } else {
      std::cout << "Cannot open file: " + fileName << std::endl;
    }
    file.close();
    return records;
  }

  // https://www.geeksforgeeks.org/convert-floating-point-number-string/
  // https://blog.benoitblanchon.fr/lightweight-float-to-string/

  void reverse(char *str, int len) { 
    int i = 0, j = len - 1, temp; 
    while (i < j) { 
        temp = str[i]; 
        str[i] = str[j]; 
        str[j] = temp; 
        i++; 
        j--; 
    } 
} 

// Converts a given integer x to std::string str[].  
// d is the number of digits required in the output.  
// If d is more than the number of digits in x,  
// then 0s are added at the beginning. 
int intToStr(int x, char str[], int d, bool minus) { 

    int i = 0; 
    while (x) { 
        str[i++] = (x % 10) + '0'; 
        x = x / 10; 
    } 

    if (d == 0 && minus) {
       str[i++] = '-';
    }
  
    // If number of digits required is more, then 
    // add 0s at the beginning 
    while (i < d) {
        str[i++] = '0'; 
    }
  
    reverse(str, i); 
    str[i] = '\0'; 
    return i; 
} 

float pow10x(int exp) {
        if (exp == 1) {
           return 10; 
        } else if (exp == 2) {
           return 100; 
        } else if (exp == 3) {
           return 1000; 
        } else if (exp == 4) {
           return 10000; 
        }
        return pow(10, exp); 
}

// Converts a floating-point/double number to a std::string. 
void ftoa(float n, char* res, int afterpoint) { 

    bool minus = false;
    if (n < 0) {
      n = -n;
      minus = true;
    }

    // Extract integer part 
    int ipart = (int) n; 
  
    // Extract floating part 
    float fpart = n - (float) ipart; 
  
    // convert integer part to std::string 
    int i = intToStr(ipart, res, 0, minus); 
  
    // check for display option after point 
    if (afterpoint != 0) { 
        res[i] = '.'; // add dot 
  
        // Get the value of fraction part upto given no. 
        // of points after dot. The third parameter  
        // is needed to handle cases like 233.007 

        //fpart = fpart * pow(10, afterpoint); 
        fpart = fpart * pow10x(afterpoint); 

        //if (afterpoint == 1) {
        //   fpart = fpart * 10; 
        //} else if (afterpoint == 2) {
        //   fpart = fpart * 100; 
        //} else if (afterpoint == 3) {
        //   fpart = fpart * 1000; 
        //} else if (afterpoint == 4) {
        //   fpart = fpart * 10000; 
        //} else {
        //   fpart = fpart * pow(10, afterpoint); 
        //}

  
        intToStr((int) fpart, res + i + 1, afterpoint, false); 
    } 
} 







void writeFloatArray(std::ofstream& file, std::vector<std::vector<float>> &records) {

  int cols = 0;
  int rows = records.size();
  std::string line;
  std::string lines = ""; //[rows];
  bool useWitespace = false;

  for (int i = 0; i < rows; i++) {

    std::vector<float> values = records.at(i);
    float value;
    cols = values.size();
    line = "";
    for (int j = 0; j < cols; j++) {
      value = values.at(j);

      // add separator
      if (j > 0) {
        //line += ", ";
        if (useWitespace) {
          line += ", ";
        } else {
          line += ",";
        }
        //file << ", ";
      }

      // convert float to std::string
      char res[20]; 
      ftoa(value, res, 2); 

      //if (value <0) {
      //  line += "-";
      //}

      // add value to line
      int c = 0;
      while(res[c] != '\0') {
        line.append(1, res[c]);
        ++c;
      }

      //line += to_string(value);
      //file << res;

    }

    // add line to array
    //lines[i] = line + "\n";
    lines.append((line + "\n"));

    // add line to file
    //file << line << "\n";

  }

  // add lilnes to file
  file << lines;

}

void writeFloatArray(const std::string &fileName, std::vector<std::vector<float>> &records) {
  std::ofstream file;

  file.open(fileName);
  if (file.is_open()) {
    writeFloatArray(file, records);
  } else {
    std::cout << "Cannot open file: " + fileName << std::endl;
  }
  file.close();
}

std::vector<std::vector<float>> generateFloatArray(int cols, int rows, float min, float max) {

  std::vector<std::vector<float>> records;
  for (int i = 0; i < rows; i++) {

    std::vector<float> values;
    float value;
    for (int j = 0; j < cols; j++) {
      value = randomFloat(min, max);
      values.push_back(value);
    }

    records.push_back(values);
  }
  return records;
}


//// TODO: See mathlib

//// 4.1 trunc, truncInt, truntDec

// cast: lost data
int intValue(float value) {
  return (int)value;
}

// cast: lost data
long longValue(double value) {
  return (long)value;
}

////

// 23.45 => 23
float trunc(float value) {
  // cast to int
  return intValue(value);
}

// 23.45 => 23
long trunc(double value) {
  // cast to long
  return longValue(value);
}

////

// 23.45 => 23
int truncInt(float value) {
  return intValue(value);
}

// 23.45 => 23
long truncInt(double value) {
  return longValue(value);
}

////

// 23.45 => 0.45
float truncDec(float value) {
  return value - intValue(value);
}

// 23.45 => 0.45
double truncDec(double value) {
  return value - longValue(value);
}

}
