#include <iostream>

#include "plazma/kernel/lib/time/datelib.h"
#include "plazma/kernel/lib/time/calendarlib.h"

#include "CalendarTask.h"

//https://stackoverflow.com/questions/17223096/outputting-date-and-time-in-c-using-stdchrono

namespace task {

    CalendarTask::CalendarTask() {
        setDysplayProcessing(false);
        setDysplayTiming(false);
    }

    CalendarTask::~CalendarTask() {}

    void CalendarTask::execute(TaskContext* ctx) {
        std::string taskName = ctx->getTaskName();

        if (taskName == TASK_PRINT_CALENDAR) {
            executePrintCalendar(ctx);
        } else if (taskName == TASK_PRINT_YEAR_CALENDAR) {
            executePrintYearCalendar(ctx);
        } else if (taskName == TASK_PRINT_MONTH_CALENDAR) {
            executePrintMonthCalendar(ctx);
        }

    }

    void CalendarTask::initParameters() {
        getParameters()->addParameter(PARAMETER_TYPE);
        getParameters()->addParameter(PARAMETER_YEAR);
        getParameters()->addParameter(PARAMETER_MONTH);
    }

    ////

    void CalendarTask::executePrintCalendar(TaskContext* ctx) {
        std::string type = "";
        if (ctx->hasParameter(PARAMETER_TYPE)) {
            type = ctx->getStringParameter(PARAMETER_TYPE);
        }

        if (type == "month") {
            executePrintMonthCalendar(ctx);
        } else {
            executePrintYearCalendar(ctx);
        }
    }

    void CalendarTask::executePrintYearCalendar(TaskContext* ctx) {
        int year = getYear(ctx);

        std::cout << "Year: " << year << std::endl;
        calendarlib::printYearCalendar(year, false, false);
    }

    void CalendarTask::executePrintMonthCalendar(TaskContext* ctx) {
        int year = getYear(ctx);
        int month = getMonth(ctx);

        std::cout << "Year: " << year << ", Month: " << month << std::endl;
        calendarlib::printMonthCalendar(year, month, false, false);
    }

    ////

    int CalendarTask::getYear(TaskContext* ctx) {
        int year = 0;
        if (ctx->hasParameter(PARAMETER_YEAR)) {
            year = ctx->getIntParameter(PARAMETER_YEAR);
        }
        if (year == 0) {
            // if 'year' is not setting then use current year
            year = datelib::getCurrentYear();
        };
        return year;
    }

    int CalendarTask::getMonth(TaskContext* ctx) {
        int month = 0;
        if (ctx->hasParameter(PARAMETER_MONTH)) {
            month = ctx->getIntParameter(PARAMETER_MONTH);
        }
        if (month == 0) {
            // if 'month' is not setting then use current month
            month = datelib::getCurrentMonth();
        };
        return month;
    }

}
