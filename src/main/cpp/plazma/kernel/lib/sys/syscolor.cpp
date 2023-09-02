#include "syscolor.h"

namespace syslib {

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

   std::string getForegroundCode(const int foreground) { // Unix only
      switch(foreground) {
        case  0: return "30"; // COLOR_BLACK      0
        case  1: return "34"; // COLOR_DARK_BLUE  1
        case  2: return "32"; // COLOR_DARK_GREEN 2
        case  3: return "36"; // COLOR_LIGHT_BLUE 3
        case  4: return "31"; // COLOR_DARK_RED   4
        case  5: return "35"; // COLOR_MAGENTA    5
        case  6: return "33"; // COLOR_ORANGE     6
        case  7: return "37"; // COLOR_LIGHT_GRAY 7
        case  8: return "90"; // COLOR_GRAY       8
        case  9: return "94"; // COLOR_BLUE       9
        case 10: return "92"; // COLOR_GREEN     10
        case 11: return "96"; // COLOR_CYAN      11
        case 12: return "91"; // COLOR_RED       12
        case 13: return "95"; // COLOR_PINK      13
        case 14: return "93"; // COLOR_YELLOW    14
        case 15: return "97"; // COLOR_WHITE     15
        default: return "37";
      }
    }

    std::string getBackgroundCode(const int background) { // Unix only
      switch(background) {
        case  0: return  "40"; // COLOR_BLACK      0
        case  1: return  "44"; // COLOR_DARK_BLUE  1
        case  2: return  "42"; // COLOR_DARK_GREEN 2
        case  3: return  "46"; // COLOR_LIGHT_BLUE 3
        case  4: return  "41"; // COLOR_DARK_RED   4
        case  5: return  "45"; // COLOR_MAGENTA    5
        case  6: return  "43"; // COLOR_ORANGE     6
        case  7: return  "47"; // COLOR_LIGHT_GRAY 7
        case  8: return "100"; // COLOR_GRAY       8
        case  9: return "104"; // COLOR_BLUE       9
        case 10: return "102"; // COLOR_GREEN     10
        case 11: return "106"; // COLOR_CYAN      11
        case 12: return "101"; // COLOR_RED       12
        case 13: return "105"; // COLOR_PINK      13
        case 14: return "103"; // COLOR_YELLOW    14
        case 15: return "107"; // COLOR_WHITE     15
        default: return  "40";
      }
    }

    //////////////////////////////////////////////////////////////////////////////////////

    std::string getPrintColor(const int foreground) { // Unix only
      return "\033[" + getForegroundCode(foreground) + "m";
    }

    std::string getPrintColor(const int foreground, const int background) { // Unix only
      return "\033[" + getForegroundCode(foreground) + ";" + getBackgroundCode(background) + "m";
    }

    std::string getPrintResetColor() { // Unix only
      return "\033[0m"; // reset color
    }

}
