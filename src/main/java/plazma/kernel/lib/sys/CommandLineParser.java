package plazma.kernel.lib.sys;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {

    public CommandLine parse(Options options, String[] args) {

        CommandLine cmd = new CommandLine();
        if (args == null || args.length == 0) {
            return cmd;
        }

        boolean checkOption = false;          // Check option (hasOption)
        boolean checkPrefix = false;          // Check prefix '-' or '--' in arguments
        boolean removePrefix = true;          // By default remove '-' or '--' before option name
        boolean lastArgumentOnly = true;      // If true processing last arguments only
        boolean lastArgumentAsOption = false; // If true argument[n] => option {name='n', value='argument'}

        String shortPrefix = Options.DEFAULT_ARGUMENT_PREFIX;
        String longPrefix = Options.DEFAULT_LONG_ARGUMENT_PREFIX;

        if (options != null) {
            checkOption = options.hasOptions();
            lastArgumentOnly = options.isLastArgumentOnly();
            lastArgumentAsOption = options.isLastArgumentAsOption();
            shortPrefix = options.getArgumentPrefix();
            longPrefix = options.getLongArgumentPrefix();
        }
        checkPrefix = shortPrefix != null || longPrefix != null;

        String arg = null;
        String name = null;
        Option option = null; // Current option
        List<String> lastArguments = null;
        int i = 0;

        while (i < args.length) {
            arg = args[i];

            if (arg == null) {
                continue;
            }

            if (!checkPrefix) {
                cmd.addArgument(arg);
                continue;
            }

            name = null;
            int prefixLen = 0;

            if (longPrefix != null && arg.startsWith(longPrefix)) {
                prefixLen = longPrefix.length();
            } else if (shortPrefix != null && arg.startsWith(shortPrefix)) {
                prefixLen = shortPrefix.length();
            }

            if (prefixLen > 0) {

                // arg => name

                name = removePrefix ? arg.substring(prefixLen) : arg;
                option = null;

                if (checkOption) {
                    option = options.getOption(name);
                    if (option == null) {
                        // error ?
                        option = new Option(name, null);
                        option.setArgumentCount(Option.UNLIMITED);
                    } else {
                        option = (Option) option.clone();

                    }
                } else {
                    option = new Option(name, null);
                    option.setArgumentCount(Option.UNLIMITED);
                }

                // Check null because in error mode maybe we don't create option
                if (option != null) {

                    cmd.addOption(option);

                    // reset last arguments
                    if (lastArguments != null && !lastArguments.isEmpty()) {
                        lastArguments.clear();
                    }
                }

            } else {

                // arg => value

                if (option == null) {

                    // arg => first value
                    cmd.addArgument(arg);
                    continue;
                }

                if (option.acceptsAument()) {
                    option.addValue(arg);
                } else {

                    if (lastArgumentOnly) {
                        if (lastArguments == null) {
                            lastArguments = new ArrayList<>();
                        }
                        lastArguments.add(arg);
                    } else {
                        cmd.addArgument(arg);
                    }

                }

            }

            i += 1;

        }

        if (lastArguments != null && !lastArguments.isEmpty()) {
            for (int k = 0; k < lastArguments.size(); k++) {
                arg = lastArguments.get(k);
                if (lastArgumentAsOption) {
                    name = "" + (k + 1);
                    option = new Option(name, null);
                    option.setArgumentCount(1);
                    option.setValue(arg);

                    cmd.addOption(option);
                } else {
                    cmd.addArgument(arg);
                }
            }
        }

        // TODO: Option.getArgumentPosition() => getArguments(position - 1) =>
        // Option.setValue(...)

        return cmd;

    }

}
