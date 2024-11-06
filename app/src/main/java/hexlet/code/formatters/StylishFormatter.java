package hexlet.code.formatters;

import hexlet.code.model.DiffEntry;

import java.util.List;

public class StylishFormatter {
    public static String format(List<DiffEntry> results) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (var r : results) {
            String result = switch (r.getCompareResult()) {
                case EQUALS -> formatEqualsDiffEntry(r);
                case ADDED -> formatAddedDiffEntry(r);
                case REMOVED -> formatRemovedDiffEntry(r);
                case UPDATED -> formatUpdatedDiffEntry(r);
            };
            stringBuilder.append(result);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String formatUpdatedDiffEntry(DiffEntry d) {
        return String.format("  - %s: %s\n", d.getKey(), d.getOldValue()) +
                String.format("  + %s: %s\n", d.getKey(), d.getValue());
    }

    private static String formatAddedDiffEntry(DiffEntry d) {
        return String.format("  + %s: %s\n", d.getKey(), d.getValue());
    }

    private static String formatRemovedDiffEntry(DiffEntry d) {
        return String.format("  - %s: %s\n", d.getKey(), d.getOldValue());
    }

    private static String formatEqualsDiffEntry(DiffEntry d) {
        return String.format("    %s: %s\n", d.getKey(), d.getValue());
    }
}
