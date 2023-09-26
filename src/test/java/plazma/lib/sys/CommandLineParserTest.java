package plazma.lib.sys;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import plazma.lib.AbstractTestCase;
import plazma.lib.sys.CommandLine;
import plazma.lib.sys.CommandLineParser;
import plazma.lib.sys.Option;
import plazma.lib.sys.Options;

public class CommandLineParserTest extends AbstractTestCase {

    public void testParse() {

        CommandLine cmd = null;
        List<Option> optionList = null;
        List<String> argumentList = null;

        String line = "-task format-xml -file test.xml -verbose value1 value2";
        String[] args = line.split(" ");

        CommandLineParser parser = new CommandLineParser();
        cmd = parser.parse(null, args);
        optionList = cmd.getOptions();
        argumentList = cmd.getArguments();

        for (Option option : optionList) {
            System.out.println(option);
        }

        ////

        Options options = new Options();

        // options.addOption(new Option("task", "Task name", true, true));
        // options.addOption(new Option("file", "File name", true, true));
        // options.addOption(new Option("verbose", "Verbose output", false, false));

        options.addRequiredOption("task", "Task name", true);
        options.addRequiredOption("file", "File name", true);
        options.addOption("verbose", "Verbose output");

        cmd = parser.parse(options, args);

        optionList = cmd.getOptions();
        argumentList = cmd.getArguments();

        System.out.println();
        for (Option option : optionList) {
            System.out.println(option);
        }

        System.out.println();
        for (String argument : argumentList) {
            System.out.println("[ " + argument + " ]");
        }

        ////

        options.setLastArgumentAsOption(true);

        cmd = parser.parse(options, args);

        optionList = cmd.getOptions();
        argumentList = cmd.getArguments();

        System.out.println();
        for (Option option : optionList) {
            System.out.println(option);
        }

        System.out.println();
        for (String argument : argumentList) {
            System.out.println("[ " + argument + " ]");
        }

        ////

        Properties parameters = cmd.toParameters();
        // System.out.println("=> Parameters");
        System.out.println();

        Enumeration<?> names = parameters.propertyNames();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String value = parameters.getProperty(name);
            System.out.println("[ " + name + " :: " + value + " ]");
        }

    }

}
