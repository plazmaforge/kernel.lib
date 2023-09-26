package plazma.lib.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Option implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    // public static final int UNINITIALIZED = -1;
    // public static final int UNLIMITED = -2;

    public static final int UNLIMITED = -1;

    private String name;

    private String longName;

    private String description;

    private boolean required;

    private boolean optionalArgument;

    private int argumentCount;

    private String type;

    private List<String> values;

    // -name # without value
    // -name value # with value
    //
    // -name [value] # optional value
    //
    // -name value1 value2 # multi value
    // -name=value # value with =
    //
    // -n value # short value
    // --name value # long value

    public Option() {
        super();
    }

    public Option(String name) {
        this(name, null);
    }

    public Option(String name, String description) {
        this.name = name;

        if (name != null) {
            String[] array = name.split(",");
            if (array.length > 0) {
                this.name = normalize(array[0]);
            }
            if (array.length > 1) {
                this.longName = normalize(array[1]);
            }
        }

        this.description = description;
    }

    public Option(String name, String description, boolean required) {
        this(name, description);
        this.required = required;
    }

    public Option(String name, String description, boolean required, String type) {
        this(name, description);
        this.required = required;
        this.type = type;
    }

    public Option(String name, String longName, String description, boolean required) {
        this(name, longName, description, null);
        this.required = required;
    }

    public Option(String name, String description, String type) {
        this(name, description);
        this.type = type;
    }

    public Option(String name, String longName, String description, String type) {
        this.name = name;
        this.longName = longName;
        this.description = description;
        this.type = type;
    }

    public Option(String name, String longName, String description, boolean required, String type) {
        this(name, longName, description, type);
        this.required = required;
        this.type = type;
    }

    ////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getQualtyName() {
        if (longName != null) {
            return longName;
        }
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean hasOptionalArgument() {
        return optionalArgument;
    }

    public void setOptionalArgument(boolean optionalArgument) {
        this.optionalArgument = optionalArgument;
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public void setArgumentCount(int argumentCount) {
        this.argumentCount = argumentCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setArgument(boolean hasArgument) {
        this.argumentCount = hasArgument ? 1 : 0;
    }

    public boolean hasArguments() {
        return argumentCount > 0 || argumentCount == UNLIMITED;
    }

    // public boolean hasArguments() {
    // return argumentCount > 1 || argumentCount == UNLIMITED;
    // }

    // public boolean hasArguments() {
    // return argumentCount > 0 || argumentCount == UNLIMITED;
    // }

    public boolean acceptsAument() {

        return hasArguments() && (getArgumentCount() == UNLIMITED || getValueCount() < getArgumentCount());

        // return (hasArgument() || hasArguments() || hasOptionalArgument()) &&
        // (argumentCount <= 0 || values.size() < argumentCount);
    }

    public void addValue(final String value) {
        if (!acceptsAument()) {
            throw new IllegalArgumentException("Cannot add value, list full.");
        }

        if (values == null) {
            values = new ArrayList<>();
        }

        // store value
        values.add(value);
    }

    public String getValue() {
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

    private String normalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.trim();
    }

    public void setValue(final String value) {
        if (values == null) {
            values = new ArrayList<>();
        }
        if (values.isEmpty()) {
            values.add(value);
        } else {
            values.set(0, value);
        }
    }

    public int getValueCount() {
        return values == null ? 0 : values.size();
    }

    @Override
    public Object clone() {
        try {
            final Option option = (Option) super.clone();
            if (values != null) {
                option.values = new ArrayList<>(values);
            }
            return option;
        } catch (final CloneNotSupportedException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder().append("[ option: ");

        buf.append(name);

        if (longName != null) {
            buf.append("|").append(longName);
        }

        if (argumentCount == UNLIMITED) {
            buf.append(" ");
            buf.append("[ARG...]");
        } else if (argumentCount > 1) {
            buf.append(" ");
            buf.append(" [ARG.." + argumentCount + "]");
        } else if (argumentCount == 1) {
            buf.append(" ");
            buf.append(" [ARG]");
        }

        if (optionalArgument && (argumentCount == UNLIMITED || argumentCount > 0)) {
            buf.append("?");
        }

        if (description != null) {
            buf.append(" :: ").append(description);
        }

        if (type != null) {
            buf.append(" :: ").append(type);
        }

        if (values != null && !values.isEmpty()) {
            buf.append(" :: ");
            for (int i = 0; i < values.size(); i++) {
                if (i > 0) {
                    buf.append(", ");
                }
                buf.append(values.get(i));
            }
        }

        buf.append(" ]");

        return buf.toString();
    }

}
