#include <string>
#include <iostream>

#include "plazma/lib/data/xml/xmllib.h"
#include "test_helper.h"

void test_XmlTokens() {

  // XML Input
  std::string input = "<table    spacing='0>0'>   <tr><td>1</td><td>2</td></tr></table>";
  StringList* tokens = xmllib::tokenizeXmlFromText(input); 

  printHeader("XML-Text");
  
  std::cout << input << std::endl;

  printHeader("XML-Tokens");
  
  int size = tokens->size();
  for (int i = 0; i < size; i++) {
    std::cout << tokens->get(i) << std::endl;
  }

  node::Node* node2 = xmllib::parseXmlFromTokens(tokens);
  std::string nodeString = xmllib::writeXmlToText(node2);
  std::cout << nodeString << std::endl;

} 

void test_xmllib_all() {
    test_XmlTokens();
}
