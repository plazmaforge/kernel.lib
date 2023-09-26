#ifndef PLAZMA_LIB_DATA_NODE_BASE_NODE_CONVERTER_H
#define PLAZMA_LIB_DATA_NODE_BASE_NODE_CONVERTER_H

#include <string>
#include <vector>

#include "Node.h"
#include "NodeConverter.h"
#include "plazma/lib/ext/ustring.h"

namespace node {

    class BaseNodeConverter: public NodeConverter {

        public:

            BaseNodeConverter();

            ~BaseNodeConverter();

            node::Node* convert(node::Node* node);            

            std::string toString();

        protected:        
            virtual Node* convertDocumentNode(Node* node) = 0;
            virtual Node* convertNode(Node* node) = 0;

            virtual void convertChildren(node::Node* original, node::Node* node);
            virtual void convertAttrbutes(node::Node* original, node::Node* node);
            virtual void convertAttrbutesAsIs(node::Node* original, node::Node* node);
            virtual void convertAttrbutesAsChildren(node::Node* original, node::Node* node);

            virtual std::string emptyText();
            virtual bool isSkipNode(int nodeType);
            virtual std::string getTextType(int nodeType);

            virtual bool isDocumentType(int nodeType);
            virtual bool isObjectType(int nodeType);
            virtual bool isArrayType(int nodeType);
            virtual bool isAttributeType(int nodeType);
            virtual bool isTextType(int nodeType);
            virtual bool isCommentType(int nodeType);

            virtual int convertNodeType(int nodeType);
            virtual int convertNodeSubType(int nodeSubType);

            virtual node::Node* createDocumentNode();
            virtual node::Node* createObjectNode();
            virtual node::Node* createArrayNode();
            virtual node::Node* createAttributeNode();
            virtual node::Node* createNode();

            virtual Node* convertCloneNode(Node* node);
            virtual Node* convertDefaultNode(Node* node);

            bool useAttributesAsChildren = false;

        

    };

}
#endif // PLAZMA_LIB_DATA_NODE_BASE_NODE_CONVERTER_H