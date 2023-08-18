
#include "plazma/kernel/lib/sys/syslib.h" 
#include "plazma/kernel/lib/io/iolib.h"

#include "plazma/kernel/lib/data/xml/xmllib.h"
#include "plazma/kernel/lib/data/node/Node.h"

#include "plazma/kernel/lib/task/task_helper.h"
#include "XmlFormatTask.h"

using namespace syslib;

namespace task {

    XmlFormatTask::XmlFormatTask() {}

    XmlFormatTask::~XmlFormatTask() {}

    void XmlFormatTask::execute(TaskContext* ctx) {

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

        // tag/attr case

        if (tagCase.empty()) {
            tagCase = caseValue;
        }

        if (attrCase.empty()) {
            attrCase = caseValue;
        }

        node::Node *node = nullptr;
        StringList *tokens = nullptr;
        std::string outputText = "";

        // Init XmlReaderConfig
        xml::XmlReaderConfig *readerConfig = new xml::XmlReaderConfig();
        readerConfig->trimAttribute = trimAttr;
        readerConfig->verbose = verbose;
        readerConfig->verboseToken = verboseToken;

        // Init XmlWriterConfig
        xml::XmlWriterConfig *writerConfig = new xml::XmlWriterConfig();
        writerConfig->inlineFlag = inlineFlag;
        writerConfig->inlineNodeFlag = inlineNodeFlag;
        writerConfig->inlineNodeLimit = inlineNodeLimit;
        writerConfig->indent = indent;
        writerConfig->tagCase = tagCase;
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

                if (tokenize) {
                    tokens = xmllib::tokenizeXmlFromText(inputText);
                } else {
                    node = xmllib::readXmlFromText(readerConfig, inputText);
                }
            }

        } else {

            // by File
            if (tokenize) {
                tokens = xmllib::tokenizeXmlFromFile(inputFileName);
            } else {
                node = xmllib::readXmlFromFile(readerConfig, inputFileName);
            }
        }

        std::string message;

        if (tokenize) {
            if (tokens == nullptr) {
                message = ERROR_XML_TOKENS_EMPTY;
            }
        } else {
            if (node == nullptr) {
                message = ERROR_XML_NODE_EMPTY;
            }
        }

        // Display/Write XML Tokens/Text
        if (!message.empty()) {
            error(message);
        } else {

            // Display flag overrides output file name!
            if (display || (!hasDisplay && outputFileName.empty())) {

                writerConfig->colorized = color;

                // Display Tokens/Text
                if (tokenize) {

                    std::string buf = xmllib::getTokensText(tokens);
                    syslib::println(buf);

                } else {
                    xmllib::writeXmlToConsole(writerConfig, node);
                }

            } else if (!outputFileName.empty()) {

                // Write Tokens/Text
                if (tokenize) {

                    std::string buf = xmllib::getTokensText(tokens);
                    iolib::writeText(outputFileName, buf);

                } else {
                    xmllib::writeXmlToFile(outputFileName, writerConfig, node);
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

    void XmlFormatTask::initParameters() {

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
        getParameters()->addParameter(PARAMETER_TOKENIZE);

    }

}
