//#pragma once
#ifndef PLAZMA_KERNEL_DATA_XML_XML_READER_CONFIG_H
#define PLAZMA_KERNEL_DATA_XML_XML_READER_CONFIG_H

#include <string>

#include "plazma/kernel/lib/text/ReaderConfig.h"

using namespace text;

namespace xml {

    class XmlReaderConfig: public ReaderConfig {

        public:
            XmlReaderConfig();

            ~XmlReaderConfig();

            std::string toString();

        public:

            bool trimAttribute;    // Trim attributes

            bool trimText;         // Trim text
            bool trimComment;      // Trim comments
            bool trimCData;        // Trim CDATA

            bool skipEmptyText;    // Skip empty text
            bool skipEmptyComment; // Skip empty comments
            bool skipEmptyCData;   // Skip empty data

            bool skipComment;      // Skip all comments            
            bool skipMeta;         // Skip meta tag (<?...?>)
            bool skipDTD;          // Skip DTD tag (<!DOCTYPE...>)

    };

}
#endif // PLAZMA_KERNEL_DATA_XML_XML_READER_CONFIG_H