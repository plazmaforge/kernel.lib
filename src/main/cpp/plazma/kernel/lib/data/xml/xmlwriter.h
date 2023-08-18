#ifndef PLAZMA_KERNEL_DATA_XML_XML_WRITER_H
#define PLAZMA_KERNEL_DATA_XML_XML_WRITER_H

#include <string>

#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/ext/StringAppendable.h"
#include "XmlWriterConfig.h"
#include "XmlAppendable.h"

namespace xmlwriter {

    // text

    std::string writeXmlToText(node::Node* root);

    std::string writeXmlToText(xml::XmlWriterConfig* config, node::Node* root);

    // console

    void writeXmlToConsole(node::Node* root);

    void writeXmlToConsole(xml::XmlWriterConfig* config, node::Node* root);

    //

    void writeLevelSpace(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, int level);

    void writeXmlNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level);

    void writeXmlTextNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level);

    void writeXmlCommentNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level);

    void writeXmlCDATANode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level);

    void writeXmlElementNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level);

    //

    void writeXmlAttributes(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node);

    void writeXmlChildren(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level);

}
#endif // PLAZMA_KERNEL_DATA_XML_XML_WRITER_H