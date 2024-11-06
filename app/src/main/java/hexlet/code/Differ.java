package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.model.CompareResult;
import hexlet.code.model.DiffEntry;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, STYLISH);
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var map1 = Parser.parse(getFullPath(filepath1));
        var map2 = Parser.parse(getFullPath(filepath2));

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
            default -> throw new IllegalStateException("Unexpected format: " + format);
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

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

}
