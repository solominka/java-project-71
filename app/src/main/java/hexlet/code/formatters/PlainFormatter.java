package hexlet.code.formatters;

import hexlet.code.model.DiffEntry;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<DiffEntry> results) {
        StringBuilder stringBuilder = new StringBuilder();
        for (var r : results) {
            String result = switch (r.getCompareResult()) {
                case EQUALS -> "";
                case ADDED -> formatAddedDiffEntry(r);
                case REMOVED -> formatRemovedDiffEntry(r);
                case UPDATED -> formatUpdatedDiffEntry(r);
            };
            stringBuilder.append(result);
        }
        return stringBuilder.toString();
    }

    private static String formatUpdatedDiffEntry(DiffEntry d) {
        return String.format("Property '%s' was updated. From %s to %s\n", d.getKey(), formatValue(d.getOldValue()), formatValue(d.getValue()));
    }

    private static String formatAddedDiffEntry(DiffEntry d) {
        return String.format("Property '%s' was added with value: %s\n", d.getKey(), formatValue(d.getValue()));
    }

    private static String formatRemovedDiffEntry(DiffEntry d) {
        return String.format("Property '%s' was removed\n", d.getKey());
    }

    private static String formatValue(Object v) {
        if (v == null) {
            return "null";
        }
        if (v instanceof Map || v instanceof List) {
            return "[complex value]";
        }
        if (v instanceof String) {
            return "'" + v + "'";
        }

        return v.toString();
    }

}