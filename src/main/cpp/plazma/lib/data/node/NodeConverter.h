#ifndef PLAZMA_KERNEL_DATA_NODE_NODE_CONVERTER_H
#define PLAZMA_KERNEL_DATA_NODE_NODE_CONVERTER_H

#include <string>
#include <vector>

#include "Node.h"
#include "plazma/lib/ext/ustring.h"

namespace node {

    class NodeConverter {

        public:

            NodeConverter();

            ~NodeConverter();

            virtual Node* convert(Node* node) = 0;

            std::string toString();

    };

}
#endif // PLAZMA_KERNEL_DATA_NODE_NODE_CONVERTER_H