package hexlet.code;

import hexlet.code.model.CompareResult;
import hexlet.code.model.DiffEntry;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DiffBuilder {
    public static List buildDiff(Map map1, Map map2) {
        Set<String> allKeys = new LinkedHashSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<DiffEntry> results = new ArrayList<>();
        allKeys.stream()
                .sorted()
                .forEach(key -> results.add(processKey(key, map1, map2)));

        return results;
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
