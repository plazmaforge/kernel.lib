#ifndef PLAZMA_KERNEL_LIB_TASK_BASE_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_BASE_TASK_H

#include "TaskContext.h"
#include "Task.h"

namespace task {

    class BaseTask: public Task {

        public:

            BaseTask();

            ~BaseTask();

            virtual void init();

            virtual void initParameters();

            virtual void validate(TaskContext* ctx);

            virtual void validateParameters(Parameters* parameters);

            virtual void dump(TaskContext* ctx);

            virtual void dumpParameters(Parameters* parameters);

            Parameters* getParameters();

            //

            bool isDysplayProcessing();

            void setDysplayProcessing(bool dysplayProcessing);

            bool isDysplayTiming();
            
            void setDysplayTiming(bool dysplayTiming);

        protected:

           bool initFlag = false;

           Parameters* parameters = nullptr;

        private:

           bool dysplayProcessing = true;
           
           bool dysplayTiming = true;

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_BASE_TASK_H