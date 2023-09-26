#include "plazma/lib/sys/syslib.h"
#include "plazma/lib/num/numlib.h"
#include "plazma/lib/fmt/fmtlib.h"

#include "plazma/lib/task/task_helper.h"
#include "ArrayGenTask.h"

using namespace syslib;

namespace task {

    ArrayGenTask::ArrayGenTask() {}

    ArrayGenTask::~ArrayGenTask() {}

    void ArrayGenTask::execute(TaskContext* ctx) {

        std::string dirName = ctx->getStringParameter(PARAMETER_DIR);
        std::string fileName = ctx->getStringParameter(PARAMETER_FILE);
        std::string filePrefix = ctx->getStringParameter(PARAMETER_FILE_PREFIX);
        std::string fileExt = DEFAULT_FILE_EXT;
        int fileCount = ctx->getIntParameter(PARAMETER_FILE_COUNT, DEFAULT_FILE_COUNT); // by default
        int cols = ctx->getIntParameter(PARAMETER_COLS, DEFAULT_COLS);                  // by default
        int rows = ctx->getIntParameter(PARAMETER_ROWS, DEFAULT_ROWS);                  // by default
        int cellMax = DEFAULT_CELL_MAX;     // 100 millions
        float min = DEFAULT_MIN;
        float max = DEFAULT_MAX;

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

        ////
        // warning parameters
        ////

        int cellCount = cols * rows;
        bool writeFile = true;
        if (cellCount > cellMax) {
            writeFile = false;
            warn(WARN_CANT_WRITE_FILE);
            warn(fmtlib::format(WARN_CELL_LIMIT, std::to_string(cellMax), std::to_string(cellCount)));
        }

        if (hasFileName) {
            std::vector<std::vector<float>> records = numlib::generateFloatArray(cols, rows, min, max);
            if (writeFile) {
                numlib::writeFloatArray(fileName, records);
            }
        } else {
            dirName = getOptionalPath(dirName, filePrefix);
            for (int i = 1; i <= fileCount; i++) {
                fileName = dirName + std::to_string(i) + fileExt;
                std::vector<std::vector<float>> records = numlib::generateFloatArray(cols, rows, min, max);
                if (writeFile) {
                    numlib::writeFloatArray(fileName, records);
                }
            }
        }
    }

    void ArrayGenTask::initParameters() {
        getParameters()->addParameter(PARAMETER_DIR);
        getParameters()->addParameter(PARAMETER_FILE);
        getParameters()->addParameter(PARAMETER_FILE_PREFIX);
        getParameters()->addParameter(PARAMETER_FILE_COUNT);
        getParameters()->addParameter(PARAMETER_COLS);
        getParameters()->addParameter(PARAMETER_ROWS);
    }

}
