#ifndef PLAZMA_KERNEL_LIB_TASK_FORMAT_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_FORMAT_TASK_H

#include <string>

#include "plazma/kernel/lib/task/BaseTask.h"

namespace task {

    class FormatTask: public BaseTask {

        public:

            ///////////////////////////////////////////////////////////
            // parameters:
            ///////////////////////////////////////////////////////////

            const std::string PARAMETER_DIR = "dir";                              // Directory name
            const std::string PARAMETER_FILE = "file";                            // Input file name (alias of 'input-file')
            const std::string PARAMETER_INPUT_FILE = "input-file";                // Input file name
            const std::string PARAMETER_OUTPUT_FILE = "output-file";              // Output file name
            const std::string PARAMETER_TEXT = "text";                            // Text
            const std::string PARAMETER_INLINE = "inline";                        // Inline all flag (XML, JSON)
            const std::string PARAMETER_INLINE_NODE = "inline-node";              // Inline node flag (XML, JSON?)
            const std::string PARAMETER_INLINE_NODE_LIMIT = "inline-node-limit";  // Inline node text limit (XML)
            const std::string PARAMETER_INDENT = "indent";                        // Indent level (XML, JSON?)
            const std::string PARAMETER_CASE = "case";                            // Tag/attribute case: 'lower', 'upper', 'camel' ...(XML)
            const std::string PARAMETER_TAG_CASE = "tag-case";                    // Tag case: 'lower', 'upper', 'camel' ...(XML)
            const std::string PARAMETER_ATTR_CASE = "attr-case";                  // Attribute case: 'lower', 'upper', 'camel' ...(XML)
            const std::string PARAMETER_ATTR_QUOTE = "attr-quote";                // Attribute quote: 'single', 'double', 'none'
            const std::string PARAMETER_TRIM_ATTR = "trim-attr";                  // Trim attribute value
            const std::string PARAMETER_SKIP_COMMENTS = "skip-comment";           // Skip comments (XML, JSON)
            const std::string PARAMETER_SKIP_META = "skip-meta";                  // Skip <?...?> (XML)
            const std::string PARAMETER_SKIP_DTD = "skip-dtd";                    // Skip <!DOCTYPE...> (XML)
            const std::string PARAMETER_DISPLAY = "display";                      // Display result
            const std::string PARAMETER_VERBOSE = "verbose";                      // Verbose flag
            const std::string PARAMETER_VERBOSE_TOKEN = "verbose-token";          // Verbose token flag
            const std::string PARAMETER_COLOR = "color";                          // Color flag
            const std::string PARAMETER_STDERR = "stderr";                        // StdErr mode
            const std::string PARAMETER_TOKENIZE = "tokenize";                    // Tokenize mode only (without parsing)

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_INPUT_NOT_SETTING = "Can't execute task: Input file or text are not setting";
            const std::string ERROR_INPUT_TEXT_EMPTY = "Can't execute task: Input text is empty";

            FormatTask();

            ~FormatTask();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_FORMAT_TASK_H