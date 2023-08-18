
#include "plazma/kernel/lib/sys/syslib.h" 
#include "plazma/kernel/lib/io/iolib.h"

#include "plazma/kernel/lib/data/xml/xmllib.h"
#include "plazma/kernel/lib/data/json/jsonlib.h"
#include "plazma/kernel/lib/data/node/Node.h"

#include "plazma/kernel/lib/task/task_helper.h"
#include "Xml2JsonConvertTask.h"

using namespace syslib;

// https://onlinejsontools.com
// https://onlinexmltools.com

namespace task {

    Xml2JsonConvertTask::Xml2JsonConvertTask() {}

    Xml2JsonConvertTask::~Xml2JsonConvertTask() {}

    void Xml2JsonConvertTask::execute(TaskContext* ctx) {

        std::string dirName = ctx->getStringParameter(PARAMETER_DIR);
        std::string fileName = ctx->getStringParameter(PARAMETER_FILE);
        std::string inputFileName = ctx->getStringParameter(PARAMETER_INPUT_FILE);
        std::string outputFileName = ctx->getStringParameter(PARAMETER_OUTPUT_FILE);
        std::string inputText = ctx->getStringParameter(PARAMETER_TEXT); // unquote
        //
        bool inlineFlag = ctx->getFlagParameter(PARAMETER_INLINE);
        bool inlineNodeFlag = ctx->getFlagParameter(PARAMETER_INLINE_NODE);
        int inlineNodeLimit = ctx->getIntParameter(PARAMETER_INLINE_NODE_LIMIT);
        std::string indent = ctx->getStringParameter(PARAMETER_INDENT);
        std::string caseValue = ctx->getStringParameter(PARAMETER_CASE);
        std::string tagCase = ctx->getStringParameter(PARAMETER_TAG_CASE);
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

        // tag/attr case

        if (tagCase.empty()) {
            tagCase = caseValue;
        }

        if (attrCase.empty()) {
            attrCase = caseValue;
        }

        node::Node *node = nullptr;
        node::Node* outputNode = nullptr;
        std::string outputText = "";

        // Init XmlReaderConfig
        xml::XmlReaderConfig *readerConfig = new xml::XmlReaderConfig();
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

        // Read XML Tokens/Node by Text/File
        if (inputFileName.empty()) {

            // by Text
            if (inputText.empty()) {
                error(ERROR_INPUT_TEXT_EMPTY);
            } else {
                node = xmllib::readXmlFromText(readerConfig, inputText);
            }

        } else {

            // by File
            node = xmllib::readXmlFromFile(readerConfig, inputFileName);
        }


        // Display/Write JSON Text
        if (node == nullptr) {
            error(ERROR_XML_NODE_EMPTY);
        } else {

            outputNode = xmllib::convertXmlToJson(node);

            // Display flag overrides output file name!
            if (display || (!hasDisplay && outputFileName.empty())) {

                writerConfig->colorized = color;

                // Display text
                jsonlib::writeJsonToConsole(writerConfig, outputNode);

            } else if (!outputFileName.empty()) {

                // Write text
                jsonlib::writeJsonToFile(outputFileName, writerConfig, outputNode);
            }
        }

        ////

        if (node != nullptr) {
            delete node;
        }

        if (outputNode != nullptr) {
            delete outputNode;
        }

        if (readerConfig != nullptr) {
            delete readerConfig;
        }

        if (writerConfig != nullptr) {
            delete writerConfig;
        }

        ////
    }

    void Xml2JsonConvertTask::initParameters() {

        getParameters()->addParameter(PARAMETER_DIR);
        getParameters()->addParameter(PARAMETER_FILE);
        getParameters()->addParameter(PARAMETER_INPUT_FILE);
        getParameters()->addParameter(PARAMETER_OUTPUT_FILE);
        getParameters()->addParameter(PARAMETER_TEXT);
        //
        getParameters()->addParameter(PARAMETER_INLINE);
        getParameters()->addParameter(PARAMETER_INLINE_NODE);
        getParameters()->addParameter(PARAMETER_INLINE_NODE_LIMIT);
        getParameters()->addParameter(PARAMETER_INDENT);
        getParameters()->addParameter(PARAMETER_CASE);
        getParameters()->addParameter(PARAMETER_TAG_CASE);
        getParameters()->addParameter(PARAMETER_ATTR_CASE);
        getParameters()->addParameter(PARAMETER_ATTR_QUOTE);
        getParameters()->addParameter(PARAMETER_TRIM_ATTR);
        //
        getParameters()->addParameter(PARAMETER_DISPLAY);
        getParameters()->addParameter(PARAMETER_VERBOSE);
        getParameters()->addParameter(PARAMETER_VERBOSE_TOKEN);
        getParameters()->addParameter(PARAMETER_COLOR);
        getParameters()->addParameter(PARAMETER_STDERR);

    }

}
