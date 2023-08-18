package plazma.kernel.lib.sys;

import java.util.LinkedHashMap;
import java.util.Map;

public class Options {

    public static final String DEFAULT_ARGUMENT_PREFIX = "-";

    public static final String DEFAULT_LONG_ARGUMENT_PREFIX = "--";

    private String argumentPrefix = DEFAULT_ARGUMENT_PREFIX;

    private String longArgumentPrefix = DEFAULT_LONG_ARGUMENT_PREFIX;

    private boolean lastArgumentOnly = true; // If true processing last arguments only

    private boolean lastArgumentAsOption = false; // If true argument[n] => option {name='n', value='argument'}

    private Map<String, Option> options = new LinkedHashMap<>();

    private Map<String, Option> longOptions = new LinkedHashMap<>();

    public String getArgumentPrefix() {
        return argumentPrefix;
    }

    public void setArgumentPrefix(String argumentPrefix) {
        this.argumentPrefix = argumentPrefix;
    }

    public String getLongArgumentPrefix() {
        return longArgumentPrefix;
    }

    public void setLongArgumentPrefix(String longArgumentPrefix) {
        this.longArgumentPrefix = longArgumentPrefix;
    }

    public boolean isLastArgumentOnly() {
        return lastArgumentOnly;
    }

    public void setLastArgumentOnly(boolean lastArgumentOnly) {
        this.lastArgumentOnly = lastArgumentOnly;
    }

    public boolean isLastArgumentAsOption() {
        return lastArgumentAsOption;
    }

    public void setLastArgumentAsOption(boolean lastArgumentAsOption) {
        this.lastArgumentAsOption = lastArgumentAsOption;
    }

    public Options addOption(Option option) {

        // add short
        options.put(option.getName(), option);

        // add long
        if (option.getLongName() != null) {
            longOptions.put(option.getLongName(), option);
        }

        return this;
    }

    public Options addOption(String name, String description) {
        Option option = new Option(name, description);
        return addOption(option);
    }

    public Options addOption(String name, String description, String type) {
        Option option = new Option(name, description);
        option.setType(type);
        return addOption(option);
    }

    public Options addOption(String name, String description, boolean hasArgument, String type) {
        Option option = new Option(name, description);
        option.setArgument(hasArgument);
        option.setType(type);
        return addOption(option);
    }

    public Options addOption(String name, String description, boolean hasArgument, boolean optionalArgument,
            String type) {
        Option option = new Option(name, description);
        option.setArgument(hasArgument);
        option.setOptionalArgument(optionalArgument);
        option.setType(type);
        return addOption(option);
    }

    public Options addOption(String name, String description, boolean hasArgument, boolean optionalArgument) {
        Option option = new Option(name, description);
        option.setArgument(hasArgument);
        option.setOptionalArgument(optionalArgument);
        return addOption(option);
    }

    public Options addOption(String name, String description, boolean hasArgument) {
        Option option = new Option(name, description);
        option.setArgument(hasArgument);
        return addOption(option);
    }

    ////

    public Options addRequiredOption(String name, String description) {
        Option option = new Option(name, description);
        option.setRequired(true);
        return addOption(option);
    }

    public Options addRequiredOption(String name, String description, String type) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setType(type);
        return addOption(option);
    }

    public Options addRequiredOption(String name, String description, boolean hasArgument, String type) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgument(hasArgument);
        option.setType(type);
        return addOption(option);
    }

    public Options addRequiredOption(String name, String description, boolean hasArgument) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgument(hasArgument);
        return addOption(option);
    }

    public Options addRequiredOption(String name, String description, boolean hasArgument, boolean optionalArgument,
            String type) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgument(hasArgument);
        option.setOptionalArgument(optionalArgument);
        option.setType(type);
        return addOption(option);
    }

    public Options addRequiredOption(String name, String description, boolean hasArgument, boolean optionalArgument) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgument(hasArgument);
        option.setOptionalArgument(optionalArgument);
        return addOption(option);
    }

    ////

    public Options addRequiredMultiOption(String name, String description, int argumentCount, String type) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgumentCount(argumentCount);
        option.setType(type);
        return addOption(option);
    }

    public Options addRequiredMultiOption(String name, String description, int argumentCount, boolean optionalArgument,
            String type) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgumentCount(argumentCount);
        option.setOptionalArgument(optionalArgument);
        option.setType(type);
        return addOption(option);
    }

    public Options addRequiredMultiOption(String name, String description, int argumentCount,
            boolean optionalArgument) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgumentCount(argumentCount);
        option.setOptionalArgument(optionalArgument);
        return addOption(option);
    }

    public Options addRequiredMultiOption(String name, String description, int argumentCount) {
        Option option = new Option(name, description);
        option.setRequired(true);
        option.setArgumentCount(argumentCount);
        return addOption(option);
    }

    ////

    public boolean hasOption(String name) {
        if (name == null) {
            return false;
        }
        return options.containsKey(name) || longOptions.containsKey(name);
    }

    public Option getOption(String name) {
        if (name == null) {
            return null;
        }
        Option option = options.get(name);
        if (option != null) {
            return option;
        }
        return longOptions.get(name);
    }

    public boolean hasOptions() {
        return !options.isEmpty();
    }

}
