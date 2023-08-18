#ifndef PLAZMA_KERNEL_DATA_XML_XML_PARSER_CONTEXT_H
#define PLAZMA_KERNEL_DATA_XML_XML_PARSER_CONTEXT_H

#include <string>
#include <map>

#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/text/ParserContext.h"

using namespace text;

namespace xml {

    class XmlParserContext: public ParserContext {

        public:

            XmlParserContext();

            ~XmlParserContext();

            std::string toString();

        public:
            int currEvent = 0;

            int nodeEvent = 0;
            
            char* currTag = nullptr;

            char* currAttribute = nullptr;

            node::Node* currNode = nullptr;

            std::map<int, char*> map;

    };

}
#endif // PLAZMA_KERNEL_DATA_XML_XML_PARSER_CONTEXT_H