#include <string>

#include "YamlParserContext.h"

namespace yaml {

    YamlParserContext::YamlParserContext() {

        currEvent = 0;
        prevEvent = 0;
        nodeEvent = 0;

        currAttribute = nullptr;
        currNode = nullptr;
        lastNode = nullptr;

        isRestored = false;

        isNewLine = false;
        isBlankLine = false;
        isNewBlankLine = false;        
        spaceCount = 0; 
        indent = 0;

    }

    YamlParserContext::~YamlParserContext() {
        //currAttribute = nullptr;
        //currNode = nullptr;

        if (currAttribute != nullptr) {
            delete currAttribute;
        }
        //if (currNode != nullptr) {
        //    delete currNode;
        //}

        //cout << "Destroy YamlParserContext: " << endl;
    }

    bool YamlParserContext::levelDown(node::Node* node, bool sync) {
        if (node == nullptr) {
            return false;
        }
        
        this->currNode = node;

        if (sync) {
            this->lastNode = node;
        }

        // Level DOWN
        this->level++;

        return true;
    }

    bool YamlParserContext::levelDown(node::Node* node) {
        return levelDown(node, false);
    }

    bool YamlParserContext::levelUp(node::Node* node) {

        this->currNode = node;

        // Level UP
        this->level--;

        return true;
    }

    bool YamlParserContext::levelUp(node::Node* node, int count) {

        this->currNode = node;

        // Level UP
        for (int i = 0; i < count; i++) {
            this->level--;
        }
                
        return true;
    }

    bool YamlParserContext::levelUp() {
        if (this->currNode == nullptr) {
            return false;
        }

        return levelUp(this->currNode->getParent());

        //this->currNode = this->currNode->getParent();
        // Level UP
        //this->level--;
        //return true;
    }


    void YamlParserContext::resetBlankLineState() {
        spaceCount = 0;
        isBlankLine = false;
        isNewBlankLine = false;
    }

    ////

    void YamlParserContext::addLine(std::string line) {
        this->lines.push_back(line);
    }

    void YamlParserContext::clearLines() {
        this->lines.clear();
    }

    std::string YamlParserContext::getLines() {
        if (this->lines.empty()) {
            return "";
        }
        int size = this->lines.size();
        std::string result;
        std::string line;
        for (int i = 0; i < size; i++) {
            line = this->lines.at(i);
            result.append(line);
        }
        return result;
    }

    std::string YamlParserContext::getFirstLines(const char* attribute) {
        if (this->lines.empty()) {
            return "";
        }

        int size = this->lines.size();
        std::string result;
        std::string line;
        std::string a;

        bool check = attribute != nullptr;
        if (check) {
            a = attribute;
        }
        std::vector<std::string> lines;
        for (int i = 0; i < size; i++) {
            line = this->lines.at(i);
            if (check) {
                //if (eq(line.c_str(), attribute)) {
                if (line == a) {
                    break;
                }
            }
            
            lines.push_back(line);
        }

        if (lines.empty()) {
            return "";
        }

        int start = -1;
        int end = -1;

        // Check '\n' before
        for (int i = 0; i < lines.size(); i++) {
            line = lines.at(i);
            if (line != "\n") {
                start = i;
                break;
            }            
        }

        if (start == -1) {
            return "";
        }

        // Check '\n' after        
        for (int i = lines.size() - 1; i >= 0; i--) {
            line = lines.at(i);
            if (line != "\n") {
                end = i;
                break;
            }            
        }

        for (int i = start; i <= end; i++) {
            line = lines.at(i);
            result.append(line);
        }

        return result;
    }

    int YamlParserContext::getLineSize() {
        return this->lines.size();                
    }

    int YamlParserContext::getRealSize() {
        if (this->lines.empty()) {
            return 0;
        }
        int size = this->lines.size();
        int result = 0;
        std::string line;
        for (int i = 0; i < size; i++) {
            line = this->lines.at(i);
            if (line != "\n") {
                result++;
            }
        }
        return result;
    }

    ////

    int YamlParserContext::getIndent(const char* token) {
        if (token == nullptr) {
            return 0;
        }
        std::string str = token;
        if (str.empty()) {
            return 0;
        }
        int count = str.length();
        for (int i = 0; i < count; i++) {
            if (str[i] != ' ') {
                return i;
            }
        }
        return count;
    }

    int YamlParserContext::getSpaceCount(const char* token) {
        if (token == nullptr) {
            return 0;
        }
        std::string str = token;
        if (str.empty()) {
            return 0;
        }
        int indent = getIndent(token);
        int count = str.length();
        return indent < count ? 0 : count;

        //for (int i = 0; i < count; i++) {
        //    if (str[i] != ' ') {
        //        return 0;
        //    }
        //}
        //return count;
    }

    bool YamlParserContext::checkBlankLine(const char* token) {
        int count = getSpaceCount(token);
        return count > 0;
    }

    bool YamlParserContext::checkNewLine(const char* token) {
        if (token == nullptr) {
            return false;
        }
        return (eq(token, "\r") 
            || eq(token, "\n") 
            || eq(token, "\r\n"));
    }

    bool YamlParserContext::checkNextNewLine(ext::StringList* tokens, int tokenLen, int i) {
        if (tokens == nullptr) {
            return false;
        }
        
        i++;
        // Has next token
        if (i >= tokenLen) {
            return false;
        }

        // Get next token
        char* nextToken = tokens->get(i);
        if (nextToken == nullptr) {
            return false;
        }

        // Next token is NewLine
        return checkNewLine(nextToken);
    }

    ////

    std::string YamlParserContext::toString() {
        return "YamlParserContext[]";
    }

}
