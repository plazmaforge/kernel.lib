#ifndef PLAZMA_LIB_TASK_TASK_H
#define PLAZMA_LIB_TASK_TASK_H

#include <string>

#include "plazma/lib/sys/Parameters.h" 
#include "TaskContext.h"

namespace task {

    class Task {

        public:

            Task();

            virtual ~Task();

            virtual void execute(TaskContext* ctx) = 0;

            virtual void init()  = 0;

            virtual void initParameters() = 0;

            virtual void validate(TaskContext* ctx)  = 0;

            virtual void validateParameters(Parameters* parameters)  = 0;

            virtual void dump(TaskContext* ctx) = 0;

            virtual void dumpParameters(Parameters* parameters) = 0;

            virtual Parameters* getParameters() = 0;

            //

            virtual bool isDysplayProcessing() = 0;

            virtual void setDysplayProcessing(bool dysplayProcessing) = 0;

            virtual bool isDysplayTiming() = 0;
            
            virtual void setDysplayTiming(bool dysplayTiming) = 0;

    };

}
#endif // PLAZMA_LIB_TASK_TASK_H