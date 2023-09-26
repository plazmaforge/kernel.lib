#ifndef PLAZMA_KERNEL_LIB_SYSCOLOR_H
#define PLAZMA_KERNEL_LIB_SYSCOLOR_H

#include <string>

namespace syslib {

    #define COLOR_BLACK      0
    #define COLOR_DARK_BLUE  1
    #define COLOR_DARK_GREEN 2
    #define COLOR_LIGHT_BLUE 3
    #define COLOR_DARK_RED   4
    #define COLOR_MAGENTA    5
    #define COLOR_ORANGE     6
    #define COLOR_LIGHT_GRAY 7
    #define COLOR_GRAY       8
    #define COLOR_BLUE       9
    #define COLOR_GREEN     10
    #define COLOR_CYAN      11
    #define COLOR_RED       12
    #define COLOR_PINK      13
    #define COLOR_YELLOW    14
    #define COLOR_WHITE     15

    const int COLOR_INFO = COLOR_GREEN;
    const int COLOR_WARN = COLOR_YELLOW;
    const int COLOR_ERROR = COLOR_RED;

    ///////////////////////////////////////////////////////////////////////////////////////
    //https://stackoverflow.com/questions/4053837/colorizing-text-in-the-console-with-c

    //https://github.com/sharkdp/dbg-macro/
    //https://github.com/sharkdp/dbg-macro/blob/57e71b8a2f29bd4832647518e21a613e3854d877/dbg.h#L79-L81

    //static constexpr const char* const
    //CONST_STRING ANSI_EMPTY = "";
    //CONST_STRING ANSI_DEBUG = "\x1b[02m";
    //CONST_STRING ANSI_ERROR = "\x1B[31m";
    //CONST_STRING ANSI_WARN = "\x1b[33m";
    //CONST_STRING ANSI_EXPRESSION = "\x1b[36m";
    //CONST_STRING ANSI_VALUE = "\x1b[01m";
    //CONST_STRING ANSI_TYPE = "\x1b[32m"; // info
    //CONST_STRING ANSI_RESET = "\x1b[0m";

    //////////////////////////////////////////////////////////////////////////////////////

    /*
    Name            FG  BG
    Black           30  40
    Red             31  41
    Green           32  42
    Yellow          33  43
    Blue            34  44
    Magenta         35  45
    Cyan            36  46
    White           37  47
    Bright Black    90  100
    Bright Red      91  101
    Bright Green    92  102
    Bright Yellow   93  103
    Bright Blue     94  104
    Bright Magenta  95  105
    Bright Cyan     96  106
    Bright White    97  107
   */

   //////////////////////////////////////////////////////////////////////////////////////

   //cout<<"\n1. \x1b[1mBOLD\x1b[0m";
   //cout<<"\n3. \x1b[3mITALIC\x1b[0m";
   //cout<<"\n4. \x1b[4mUNDERLINE\x1b[0m";
   //cout<<"\n5. \x1b[5mBLINKING\x1b[0m";
   //cout<<"\n7. \x1b[7mHIGHLIGHT\x1b[0m";
   //cout<<"\n8. \x1b[8mPRINT NOTHING\x1b[0m";
   //cout<<"\n30. \x1b[30mBLACK\x1b[0m";
   //cout<<"\n31. \x1b[31mRED\x1b[0m";
   //cout<<"\n32. \x1b[32mGREEN\x1b[0m";
   //cout<<"\n33. \x1b[33mPURPLE\x1b[0m";
   //cout<<"\n34. \x1b[34mYELLOW\x1b[0m";
   //cout<<"\n35. \x1b[35mPINK\x1b[0m";
   //cout<<"\n36. \x1b[36mLIGHTBLUE\x1b[0m";
   //cout<<"\n37. \x1b[37mWHITE\x1b[0m";

   //cout<<"\n40. \x1b[40m Black Background      \x1b[0m";
   //cout<<"\n41. \x1b[41m Red Background        \x1b[0m";
   //cout<<"\n42. \x1b[42m Green Background      \x1b[0m";
   //cout<<"\n43. \x1b[43m Yellow Background     \x1b[0m";
   //cout<<"\n44. \x1b[44m Blue Background       \x1b[0m";
   //cout<<"\n45. \x1b[45m Pink Background       \x1b[0m";
   //cout<<"\n46. \x1b[46m Light Blue Background \x1b[0m";

   //////////////////////////////////////////////////////////////////////////////////////

   std::string getForegroundCode(const int foreground);

   std::string getBackgroundCode(const int background);

   //////////////////////////////////////////////////////////////////////////////////////

   std::string getPrintColor(const int foreground);

   std::string getPrintColor(const int foreground, const int background);

   std::string getPrintResetColor();

}

#endif // PLAZMA_KERNEL_LIB_SYSCOLOR_H