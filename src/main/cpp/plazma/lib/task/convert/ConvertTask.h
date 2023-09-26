#ifndef PLAZMA_LIB_TASK_CONVERT_TASK_H
#define PLAZMA_LIB_TASK_CONVERT_TASK_H

#include <string>

#include "plazma/lib/sys/define.h"
#include "plazma/lib/task/BaseTask.h"

namespace task {

    class ConvertTask: public BaseTask {

        public:

            ///////////////////////////////////////////////////////////
            // parameters:
            ///////////////////////////////////////////////////////////

            CONST_STRING PARAMETER_DIR = "dir";                              // Directory name
            CONST_STRING PARAMETER_FILE = "file";                            // Input file name (alias of 'input-file')
            CONST_STRING PARAMETER_INPUT_FILE = "input-file";                // Input file name
            CONST_STRING PARAMETER_OUTPUT_FILE = "output-file";              // Output file name
            CONST_STRING PARAMETER_TEXT = "text";                            // Text
            CONST_STRING PARAMETER_INLINE = "inline";                        // Inline all flag (XML, JSON)
            CONST_STRING PARAMETER_INLINE_NODE = "inline-node";              // Inline node flag (XML, JSON?)
            CONST_STRING PARAMETER_INLINE_NODE_LIMIT = "inline-node-limit";  // Inline node text limit (XML)
            CONST_STRING PARAMETER_INDENT = "indent";                        // Indent level (XML, JSON?)
            CONST_STRING PARAMETER_CASE = "case";                            // Tag/attribute case: 'lower', 'upper', 'camel' ...(XML)
            CONST_STRING PARAMETER_TAG_CASE = "tag-case";                    // Tag case: 'lower', 'upper', 'camel' ...(XML)
            CONST_STRING PARAMETER_ATTR_CASE = "attr-case";                  // Attribute case: 'lower', 'upper', 'camel' ...(XML)
            CONST_STRING PARAMETER_ATTR_QUOTE = "attr-quote";                // Attribute quote: 'single', 'double', 'none'
            CONST_STRING PARAMETER_TRIM_ATTR = "trim-attr";                  // Trim attribute value
            CONST_STRING PARAMETER_SKIP_COMMENTS = "skip-comment";           // Skip comments (XML, JSON)
            CONST_STRING PARAMETER_SKIP_META = "skip-meta";                  // Skip <?...?> (XML)
            CONST_STRING PARAMETER_SKIP_DTD = "skip-dtd";                    // Skip <!DOCTYPE...> (XML)
            CONST_STRING PARAMETER_DISPLAY = "display";                      // Display result
            CONST_STRING PARAMETER_VERBOSE = "verbose";                      // Verbose flag
            CONST_STRING PARAMETER_VERBOSE_TOKEN = "verbose-token";          // Verbose token flag
            CONST_STRING PARAMETER_COLOR = "color";                          // Color flag
            CONST_STRING PARAMETER_STDERR = "stderr";                        // StdErr mode
            CONST_STRING PARAMETER_TOKENIZE = "tokenize";                    // Tokenize mode only (without parsing)

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            CONST_STRING ERROR_INPUT_NOT_SETTING = "Can't execute task: Input file or text are not setting";
            CONST_STRING ERROR_INPUT_TEXT_EMPTY = "Can't execute task: Input text is empty";

            ConvertTask();

            ~ConvertTask();

    };

}
#endif // PLAZMA_LIB_TASK_CONVERT_TASK_H