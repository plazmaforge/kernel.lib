#include "plazma/kernel/lib/sys/syslib.h"
#include "plazma/kernel/lib/num/numlib.h"

#include "plazma/kernel/lib/task/task_helper.h"
#include "ArrayReadTask.h"

using namespace syslib;

namespace task {

    ArrayReadTask::ArrayReadTask() {}

    ArrayReadTask::~ArrayReadTask() {}

    void ArrayReadTask::execute(TaskContext* ctx) {

        std::string dirName = ctx->getStringParameter(PARAMETER_DIR);
        std::string fileName = ctx->getStringParameter(PARAMETER_FILE);
        std::string filePrefix = ctx->getStringParameter(PARAMETER_FILE_PREFIX);
        std::string fileExt = DEFAULT_FILE_EXT;
        int fileCount = ctx->getIntParameter(PARAMETER_FILE_COUNT, DEFAULT_FILE_COUNT); // by default

        bool hasDirName = ctx->hasParameter(PARAMETER_DIR);
        bool hasFileName = ctx->hasParameter(PARAMETER_FILE);
        bool hasFilePrefix = ctx->hasParameter(PARAMETER_FILE_PREFIX);
        //bool hasFileCount = ctx->hasParameter(PARAMETER_FILE_COUNT);

        ////
        // validate parameters
        ////

        if (!hasDirName && !hasFileName && !hasFilePrefix) {
            error(ERROR_REQUIREMENT_ARGS);
            error(ERROR_SET_FILE);
            return;
        }

        if (hasFileName) {
            std::vector<std::vector<float>> records = numlib::readFloatArray(fileName);
        } else {
            dirName = getOptionalPath(dirName, filePrefix);
            for (int i = 1; i <= fileCount; i++) {
                fileName = dirName + std::to_string(i) + fileExt;
                std::vector<std::vector<float>> records = numlib::readFloatArray(fileName);
            }
        }

    }

    void ArrayReadTask::initParameters() {
        getParameters()->addParameter(PARAMETER_DIR);
        getParameters()->addParameter(PARAMETER_FILE);
        getParameters()->addParameter(PARAMETER_FILE_PREFIX);
        getParameters()->addParameter(PARAMETER_FILE_COUNT);
    }

}
