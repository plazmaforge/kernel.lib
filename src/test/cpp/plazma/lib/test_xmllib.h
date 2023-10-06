#include <string>
#include <iostream>

#include "plazma/lib/io/iolib.h"
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

// 3. TEST READ/WRITE XML
void test_ReadWriteXml() {

  std::string fileName = "../../src/test/resources/FirstJasper.jrxml";
  StringList* tokens = nullptr;

  printHeader("TEST Report XML");

  // READ TEXT
  long t1 = syslib::startTime();
  std::string text = iolib::readText(fileName);
  t1 = syslib::stopTime(t1);
  std::cout << "Read Text Time: " << t1 << "ms" << std::endl;

  // TOKENIZE REPORT
  t1 = syslib::startTime();
  tokens = xmllib::tokenizeXmlFromText(text);
  t1 = syslib::stopTime(t1);
  std::cout << "Tokenize Report Time: " << t1 << "ms" << std::endl;

  std::cout << "Tokens Size: " << tokens->size() << std::endl;
  std::cout << std::endl;

  // READ REPORT
  node::Node* reportNode = nullptr;
  t1 = syslib::startTime();

  int K = 100; //100000;

  for (int i = 0; i < K; i++) {
      reportNode = xmllib::readXmlFromFile(fileName);
  }
  t1 = syslib::stopTime(t1);

  std::cout << "[TOTAL] Count: " << K << ", Time: " << t1 << "ms" << std::endl;
  std::cout << "Read Report Time: " << (t1/ K * 1.0) << "ms" << std::endl;

  //cout << endl;  
  //cout << "ReportNode:" << endl;
  //cout << "----------------------------------" << endl;
  //cout << xmllib::writeXmlToText(reportNode) << endl; // TODO: DOESN'T WORK: Segmentation fault: 11

  //string t = xmllib::writeXmlToText(reportNode);

  //iolib::writeText(t, z);
  //xmllib::writeXmlToFile(reportNode, z2);

  //delete reportNode;

}

void test_xmllib_all() {
    test_XmlTokens();
    //test_ReadWriteXml();
}
