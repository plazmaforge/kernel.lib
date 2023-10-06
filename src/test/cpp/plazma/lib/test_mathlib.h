#include <cmath>
#include <iostream>

#include "plazma/lib/math/mathlib.h"

#include "test_helper.h"

void test_log() {

  printHeader("TEST log/log2/log10");

  //std::cout.precision(17);

  std::cout << "log(3.5)            = " << mathlib::log(3.5) << std::endl;                          // 1.252762968495368
  std::cout << "exp(log(2.4))       = " << mathlib::exp(mathlib::log(2.4)) << std::endl;            // 2.4

  std::cout << "pow(10, 4)          = " << mathlib::pow(10, 4) << std::endl;                        // 10000.0
  std::cout << "log(10000, 10)      = " << mathlib::log(10000, 10) << std::endl;                    // 4.0

  std::cout << "log10(10000)        = " << mathlib::log10(10000) << std::endl;                      // 4.0
  std::cout << "log(10000)/log(10)  = " << (mathlib::log(10000) / mathlib::log(10)) << std::endl;	// 4.0

  std::cout << "log(1024, 2)        = " << mathlib::log(1024, 2) << std::endl;                      // 10.0
  std::cout << "log2(1024)          = " << mathlib::log2(1024) << std::endl;                        // 10.0

  std::cout << "pow(2, 10)          = " << mathlib::pow(2, 10) << std::endl;                        // 1024.0

}

void test_round() {

  printHeader("TEST round");

  //cout.precision(17);

  double x = 12345678.12345678;

  std::cout << "round(12345.12345, 4) = " << mathlib::round(12345678.12345678, 4) << std::endl;

  std::cout << "x = " << x << std::endl;
  x = x * 10000;
  std::cout << "x * 10^4 = " << x << std::endl;
  x = mathlib::round(x) / 10000;

  std::cout << "round(x) / 10000 = " << x << std::endl;

  //float f = 123.45;
  //double d = 123.45;

  //std::cout << mathlib::ceil(f) << std::endl;
  //std::cout << mathlib::ceil(d) << std::endl;

}

void test_InfinityAndNaN() {

  printHeader("TEST Infinity/NaN");
  
  float x;

  x = -1;
  std::cout << "x: " << x << ", std::atanh(x): " << std::atanh(x) << std::endl;

  x = 1;
  std::cout << "x: " << x << ", std::atanh(x): " << std::atanh(x) << std::endl;

  x = NAN;
  std::cout << "x: " << x << ", std::atanh(x): " << std::asinh(x) << std::endl;

  x = INFINITY;
  std::cout << "x: " << x << ", std::atanh(x): " << std::asinh(x) << std::endl;

  x = -INFINITY;
  std::cout << "x: " << x << ", std::atanh(x): " << std::asinh(x) << std::endl;

  x = NAN;
  std::cout << "x: " << x << ", std::sign(x): " << std::copysign(x, 2) << std::endl;

  x = INFINITY;
  std::cout << "x: " << x << ", std::sign(x): " << std::copysign(x, 2) << std::endl;

  
  x = NAN;
  std::cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << std::endl;

  x = 123.456;
  std::cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << std::endl;

  x = INFINITY;
  std::cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << std::endl;

  std::cout << "x: " << x << ", isInfinity(x): " << mathlib::isInfinite(x) << std::endl;
  std::cout << "x: " << x << ", isPositiveInfinity(x): " << mathlib::isPositiveInfinite(x) << std::endl;
  std::cout << "x: " << x << ", isNegativeInfinity(x): " << mathlib::isNegativeInfinite(x) << std::endl;


  x = -INFINITY;
  std::cout << "x: " << x << ", isNaN(x): " << mathlib::isNaN(x) << std::endl;

  std::cout << "x: " << x << ", isInfinity(x): " << mathlib::isInfinite(x) << std::endl;
  std::cout << "x: " << x << ", isPositiveInfinity(x): " << mathlib::isPositiveInfinite(x) << std::endl;
  std::cout << "x: " << x << ", isNegativeInfinity(x): " << mathlib::isNegativeInfinite(x) << std::endl;


}

void test_mathlib_all() {
    test_log();
    test_round();
    test_InfinityAndNaN();
}