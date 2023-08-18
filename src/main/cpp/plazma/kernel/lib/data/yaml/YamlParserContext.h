#ifndef PLAZMA_KERNEL_DATA_YAML_YAML_PARSER_CONTEXT_H
#define PLAZMA_KERNEL_DATA_YAML_YAML_PARSER_CONTEXT_H

#include <string>
#include <map>

#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/text/ParserContext.h"
#include "plazma/kernel/lib/ext/StringList.h"

using namespace text;

namespace yaml {

    class YamlParserContext: public ParserContext {

        public:
            YamlParserContext();
            ~YamlParserContext();

            bool levelDown(node::Node* node, bool sync);
            bool levelDown(node::Node* node);
            bool levelUp(node::Node* node);
            bool levelUp(node::Node* node, int count);
            bool levelUp();

            void resetBlankLineState();
            void addLine(std::string line);
            void clearLines();
            std::string getLines();
            std::string getFirstLines(const char* attribute);
            int getLineSize();
            int getRealSize();

            int getIndent(const char* token);
            int getSpaceCount(const char* token);
            bool checkBlankLine(const char* token);
            bool checkNewLine(const char* token);
            bool checkNextNewLine(ext::StringList* tokens, int tokenLen, int i);

            std::string toString();

        public:
            int currEvent = 0;
            int prevEvent = 0;
            int nodeEvent = 0;

            char* currAttribute = nullptr;

            node::Node* currNode = nullptr;
            node::Node* lastNode = nullptr; // YAML

            std::map<int, char*> map;
            bool isRestored = false;
            
            bool isNewLine = false;
            bool isBlankLine = false;
            bool isNewBlankLine = false;
            int spaceCount = 0;
            int indent = 0;

            std::vector<std::string> lines;
            bool multiValue = false;
    };

}
#endif // PLAZMA_KERNEL_DATA_YAML_YAML_PARSER_CONTEXT_H