package plazma.lib.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommandLine {

    private List<Option> options = new ArrayList<Option>();

    private List<String> arguments = new ArrayList<String>();

    public void addOption(Option option) {
        options.add(option);
    }

    public void addArgument(String argument) {
        arguments.add(argument);
    }

    public List<Option> getOptions() {
        return options;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public Properties toParameters() {
        Properties parameters = new Properties();

        Option option = null;
        String name = null;
        String value = null;

        for (int i = 0; i < options.size(); i++) {
            option = options.get(i);

            name = option.getQualtyName();
            value = option.getValue(); // TODO: getValues() => value1, value2, value3

            parameters.setProperty(name, value == null ? "" : value);
        }

        return parameters;

    }

}
