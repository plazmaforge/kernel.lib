#include <iostream>

#include "plazma/kernel/lib/sys/syslib.h"
#include "plazma/kernel/lib/math/mathlib.h"
#include "plazma/kernel/lib/num/numlib.h"
#include "plazma/kernel/lib/fmt/fmtlib.h"

#include "SysTask.h"

//https://stackoverflow.com/questions/17223096/outputting-date-and-time-in-c-using-stdchrono

namespace task {

    SysTask::SysTask() {
        setDysplayProcessing(false);
        setDysplayTiming(false);
    }

    SysTask::~SysTask() {}

    void SysTask::execute(TaskContext* ctx) {
        std::string taskName = ctx->getTaskName();

        if (taskName == TASK_PRINT_SYSTEM_INFO) {
            executePrintSystemInfo(ctx);
        } else if (taskName == TASK_PRINT_DATE) {
            executePrintDate(ctx);
        } else if (taskName == TASK_PRINT_TIME) {
            executePrintTime(ctx);
        } else if (taskName == TASK_PRINT_DATE_TIME) {
            executePrintDateTime(ctx);
        //

        } else if (taskName == TASK_PRINT_FACT) {
            executePrintFact(ctx);
        } else if (taskName == TASK_PRINT_FIB) {
            executePrintFib(ctx);
        }

    }

    void SysTask::initParameters() {

        // print-date/time/date-time
        getParameters()->addParameter(PARAMETER_FORMAT);

        // print-fact/fib
        getParameters()->addParameter(PARAMETER_VALUE);
    }

    ////

    void SysTask::executePrintSystemInfo(TaskContext* ctx) {
        std::cout << "System Info" << std::endl;
        std::cout << " os.name    : " << syslib::getOsName() << std::endl;
        std::cout << " os.version : " << syslib::getOsVersion() << std::endl;
    }

    //

    void SysTask::executePrintDate(TaskContext* ctx) {
        std::cout << formatCurrentDate(ctx) << std::endl;
    }

    void SysTask::executePrintTime(TaskContext* ctx) {
        std::cout << formatCurrentTime(ctx) << std::endl;
    }

    void SysTask::executePrintDateTime(TaskContext* ctx) {
        std::cout << formatCurrentDateTime(ctx) << std::endl;
    }

    //

    void SysTask::executePrintFact(TaskContext* ctx) {
        std::string value = getValue(ctx);

        int n = 0;
        if (!value.empty()) {
            n = numlib::toInt(value);
        }

        // Call factorial
        long result = mathlib::fact(n);
        std::cout << result << std::endl;
    }

    void SysTask::executePrintFib(TaskContext* ctx) {
        std::string value = getValue(ctx);

        int n = 0;
        if (!value.empty()) {
            n = numlib::toInt(value);
        }

        // Call fibonacci sequence (0 1 1 2 3 5 ...)
        std::vector<long> seq = mathlib::fibseq(n);

        // TODO: use collectionlib: toString(vector<> data, std::string delim)
        int size = seq.size();
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                std::cout << " ";
            }
            std::cout << seq[i];
        }
        std::cout << std::endl;
    }

    ////

    std::string SysTask::formatCurrentDate(TaskContext* ctx) {
        std::string format = getFormat(ctx);
        return fmtlib::formatDate(format);
    }

    std::string SysTask::formatCurrentTime(TaskContext* ctx) {
        std::string format = getFormat(ctx);
        return fmtlib::formatTime(format);
    }

    std::string SysTask::formatCurrentDateTime(TaskContext* ctx) {
        std::string format = getFormat(ctx);
        return fmtlib::formatDateTime(format);
    }

    ////

    std::string SysTask::getFormat(TaskContext* ctx) {
        return ctx->getStringParameter(PARAMETER_FORMAT);
    }

    std::string SysTask::getValue(TaskContext* ctx) {
        return ctx->getStringParameter(PARAMETER_VALUE);
    }

}
