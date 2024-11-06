package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.model.CompareResult;
import hexlet.code.model.DiffEntry;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {
    public static String generate(Path filepath1, Path filepath2, Format format) throws Exception {
        var map1 = Parser.parse(filepath1);
        var map2 = Parser.parse(filepath2);

        Set<String> allKeys = new LinkedHashSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<DiffEntry> results = new ArrayList<>();
        allKeys.stream()
                .sorted()
                .forEach(key -> results.add(processKey(key, map1, map2)));

        return switch (format) {
            case STYLISH -> StylishFormatter.format(results);
            case PLAIN -> PlainFormatter.format(results);
            case JSON -> JsonFormatter.format(results);
        };
    }

    private static DiffEntry processKey(String key, Map map1, Map map2) {
        var v1 = map1.getOrDefault(key, null);
        var v2 = map2.getOrDefault(key, null);
        if (map1.containsKey(key)) {
            if (map2.containsKey(key)) {
                if (Objects.equals(v1, v2)) {
                    return new DiffEntry(key, v1, v1, CompareResult.EQUALS);
                } else {
                    return new DiffEntry(key, v2, v1, CompareResult.UPDATED);
                }
            } else {
                return new DiffEntry(key, null, v1, CompareResult.REMOVED);
            }
        } else {
            return new DiffEntry(key, v2, null, CompareResult.ADDED);
        }
    }
}
