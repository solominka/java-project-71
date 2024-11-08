package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.model.DiffEntry;

import java.util.List;

public class Formatter {
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";

    public static String format(List<DiffEntry> diff, String format) throws Exception {
        return switch (format) {
            case STYLISH -> StylishFormatter.format(diff);
            case PLAIN -> PlainFormatter.format(diff);
            case JSON -> JsonFormatter.format(diff);
            default -> throw new IllegalStateException("Unexpected format: " + format);
        };
    }
}
