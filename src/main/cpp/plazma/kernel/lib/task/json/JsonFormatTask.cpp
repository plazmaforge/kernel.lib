
#include "plazma/kernel/lib/sys/syslib.h" 
#include "plazma/kernel/lib/io/iolib.h"

#include "plazma/kernel/lib/data/json/JsonReaderConfig.h"
#include "plazma/kernel/lib/data/json/jsonlib.h"
#include "plazma/kernel/lib/data/node/Node.h"

#include "plazma/kernel/lib/task/task_helper.h"
#include "JsonFormatTask.h"

using namespace syslib;

namespace task {

    JsonFormatTask::JsonFormatTask() {}

    JsonFormatTask::~JsonFormatTask() {}

    void JsonFormatTask::execute(TaskContext* ctx) {

        std::string dirName = ctx->getStringParameter(PARAMETER_DIR);
        std::string fileName = ctx->getStringParameter(PARAMETER_FILE);
        std::string inputFileName = ctx->getStringParameter(PARAMETER_INPUT_FILE);
        std::string outputFileName = ctx->getStringParameter(PARAMETER_OUTPUT_FILE);
        std::string inputText = ctx->getStringParameter(PARAMETER_TEXT); // unquote
        //
        bool inlineFlag = ctx->getFlagParameter(PARAMETER_INLINE);
        std::string indent = ctx->getStringParameter(PARAMETER_INDENT);
        std::string attrCase = ctx->getStringParameter(PARAMETER_ATTR_CASE);
        std::string attrQuote = ctx->getStringParameter(PARAMETER_ATTR_QUOTE);
        bool trimAttr = ctx->getFlagParameter(PARAMETER_TRIM_ATTR);
        // bool skipComment = false;
        // bool skipMeta = false;
        bool display = ctx->getFlagParameter(PARAMETER_DISPLAY);
        bool verbose = ctx->getFlagParameter(PARAMETER_VERBOSE);
        bool verboseToken = ctx->getFlagParameter(PARAMETER_VERBOSE_TOKEN);
        bool color = ctx->getFlagParameter(PARAMETER_COLOR);
        std::string stderr_ = ctx->getStringParameter(PARAMETER_STDERR);
        bool tokenize = ctx->getFlagParameter(PARAMETER_TOKENIZE);

        bool hasDisplay = ctx->hasParameter(PARAMETER_DISPLAY);

        ////
        // normalize parameters
        ////

        // input/output file

        if (inputFileName.empty()) {
            inputFileName = fileName;
        }

        inputFileName = getOptionalPath(dirName, inputFileName);
        outputFileName = getOptionalPath(dirName, outputFileName);

        if (inputFileName.empty() && inputText.empty()) {
            error(ERROR_INPUT_NOT_SETTING);
            return;
        }

        node::Node *node = nullptr;
        StringList *tokens = nullptr;
        std::string outputText = "";

        // Init JsonReaderConfig
        json::JsonReaderConfig *readerConfig = new json::JsonReaderConfig();
        readerConfig->trimAttribute = trimAttr;
        readerConfig->verbose = verbose;
        readerConfig->verboseToken = verboseToken;

        // Init JsonWriterConfig
        json::JsonWriterConfig *writerConfig = new json::JsonWriterConfig();
        writerConfig->inlineFlag = inlineFlag;
        writerConfig->indent = indent;
        writerConfig->attributeCase = attrCase;
        writerConfig->attributeQuote = attrQuote;
        writerConfig->trimAttribute = trimAttr;

        // systemlib::setColorizedConsoleEnabled(color);

        // Read JSON Tokens/Node by Text/File
        if (inputFileName.empty()) {

            // by Text
            if (inputText.empty()) {
                error(ERROR_INPUT_TEXT_EMPTY);
            } else {

                if (tokenize) {
                    tokens = jsonlib::tokenizeJsonFromText(inputText);
                } else {
                    node = jsonlib::readJsonFromText(readerConfig, inputText);
                }
            }

        } else {

            // by File
            if (tokenize) {
                tokens = jsonlib::tokenizeJsonFromFile(inputFileName);
            } else {
                node = jsonlib::readJsonFromFile(readerConfig, inputFileName);
            }
        }

        std::string message;

        if (tokenize) {
            if (tokens ==  nullptr) {
                message = ERROR_JSON_TOKENS_EMPTY;
            }
        } else {
            if (node == nullptr) {
                message = ERROR_JSON_NODE_EMPTY;
            }
        }

        // Display/Write JSON Tokens/Text
        if (!message.empty()) {
            error(message);
        } else {

            // Display flag overrides output file name!
            if (display || (!hasDisplay && outputFileName.empty())) {

                writerConfig->colorized = color;

                // Display Tokens/Text
                if (tokenize) {

                    std::string buf = jsonlib::getTokensText(tokens);
                    syslib::println(buf);

                } else {
                    jsonlib::writeJsonToConsole(writerConfig, node);
                }

            } else if (!outputFileName.empty()) {

                // Write Tokens/Text
                if (tokenize) {

                    std::string buf = jsonlib::getTokensText(tokens);
                    iolib::writeText(outputFileName, buf);

                } else {
                    jsonlib::writeJsonToFile(outputFileName, writerConfig, node);
                }
            }
        }

        ////

        if (node != nullptr) {
            delete node;
        }

        if (tokens != nullptr) {
            delete tokens;
        }

        if (readerConfig != nullptr) {
            delete readerConfig;
        }

        if (writerConfig != nullptr) {
            delete writerConfig;
        }

        ////
    }

    void JsonFormatTask::initParameters() {

        getParameters()->addParameter(PARAMETER_DIR);
        getParameters()->addParameter(PARAMETER_FILE);
        getParameters()->addParameter(PARAMETER_INPUT_FILE);
        getParameters()->addParameter(PARAMETER_OUTPUT_FILE);
        getParameters()->addParameter(PARAMETER_TEXT);
        //
        getParameters()->addParameter(PARAMETER_INLINE);
        getParameters()->addParameter(PARAMETER_INDENT);
        getParameters()->addParameter(PARAMETER_ATTR_CASE);
        getParameters()->addParameter(PARAMETER_ATTR_QUOTE);
        getParameters()->addParameter(PARAMETER_TRIM_ATTR);
        //
        getParameters()->addParameter(PARAMETER_DISPLAY);
        getParameters()->addParameter(PARAMETER_VERBOSE);
        getParameters()->addParameter(PARAMETER_VERBOSE_TOKEN);
        getParameters()->addParameter(PARAMETER_COLOR);
        getParameters()->addParameter(PARAMETER_STDERR);
        getParameters()->addParameter(PARAMETER_TOKENIZE);

    }

}
