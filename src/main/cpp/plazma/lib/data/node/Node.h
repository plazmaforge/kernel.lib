#ifndef PLAZMA_LIB_DATA_NODE_NODE_H
#define PLAZMA_LIB_DATA_NODE_NODE_H

#include <string>
#include <vector>

#include "AttributeList.h"
#include "plazma/lib/ext/ustring.h"

namespace node {

    class Node {

        public:

            Node();

            ~Node();

            std::string getName();

            void setName(const std::string &name);

            bool hasName();

            ext::ustring getName2();

            void setName2(const ext::ustring &name);

            std::string getText();

            void setText(const std::string &text);

            bool hasText();

            int getType();

            void setType(int type);

            int getSubType();

            void setSubType(int subType);

            Node* getParent();

            void setParent(Node* parent);

            int getParentType();

            int getParentSubType();

            void addChild(Node* child);

            void removeChild(Node* child);

            Node* getChild(int index);

            Node* firstChild();

            Node* lastChild();

            std::vector<Node*>* getChildren();

            bool hasChildren();

            int getChildCount();

            void addAttribute(const std::string &name, const std::string &value);

            void removeAttribute(const std::string &name);

            std::string getAttribute(const std::string &name);

            std::string getAttributeName(const int index);

            std::string getAttributeValue(const int index);
            
            bool hasAttributes();

            int getAttributeCount();

            int getLevel(); // 0 - getParent() == nullptr

            void setIndent(int indent);

            int getIndent();

            bool isType(int type, int subType);

            bool isType(int type);

            bool isSubType(int subType);

            bool isParentType(int type);

            bool isParentType(int type, int subType);

            bool isParentSubType(int subType);

            ////
    
            std::string toString();
            
            Node* clone();

        protected:

            void destroy();

            void destroyAttributes();

            void destroyChildren();

        private:

            std::string name;
            ext::ustring name2;
            std::string text;
            int type;
            int subType;
            Node* parent;
            std::vector<Node*>* children;
            AttributeList* attributes;
            int indent = 0;

    };

}
#endif // PLAZMA_LIB_DATA_NODE_NODE_H