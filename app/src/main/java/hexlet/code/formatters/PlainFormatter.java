package hexlet.code.formatters;

import hexlet.code.model.DiffEntry;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PlainFormatter {
    public static String format(List<DiffEntry> results) {
        StringJoiner joiner = new StringJoiner("\n");
        results
                .stream()
                .map(r -> switch (r.getCompareResult()) {
                    case EQUALS -> null;
                    case ADDED -> formatAddedDiffEntry(r);
                    case REMOVED -> formatRemovedDiffEntry(r);
                    case UPDATED -> formatUpdatedDiffEntry(r);
                })
                .filter(Objects::nonNull)
                .forEach(joiner::add);
        return joiner.toString();
    }

    private static String formatUpdatedDiffEntry(DiffEntry d) {
        return String.format("Property '%s' was updated. From %s to %s", d.getKey(),
                formatValue(d.getValue1()), formatValue(d.getValue2()));
    }

    private static String formatAddedDiffEntry(DiffEntry d) {
        return String.format("Property '%s' was added with value: %s", d.getKey(), formatValue(d.getValue2()));
    }

    private static String formatRemovedDiffEntry(DiffEntry d) {
        return String.format("Property '%s' was removed", d.getKey());
    }

    private static String formatValue(Object v) {
        if (v == null) {
            return "null";
        }
        if (v instanceof Map || v instanceof List) {
            return "[complex value]";
        }
        if (v instanceof String) {
            return String.format("'%s'", v);
        }

        return v.toString();
    }

}
