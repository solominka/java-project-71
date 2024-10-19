package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.CompareResult;
import hexlet.code.model.DiffEntry;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String generate(Path filepath1, Path filepath2) throws Exception {
        var map1 = parseJson(filepath1);
        var map2 = parseJson(filepath2);

        Set<String> allKeys = new LinkedHashSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<DiffEntry> results = new ArrayList<>();
        allKeys.stream()
                .sorted()
                .forEach(key -> results.addAll(processKey(key, map1, map2)));
        return buildStringResponse(results);
    }

    private static Map<String, String> parseJson(Path filepath) throws Exception {
        String jsonSource = Files.readString(filepath);
        return mapper.readValue(jsonSource, Map.class);
    }

    private static List<DiffEntry> processKey(String key, Map map1, Map map2) {
        var v1 = map1.getOrDefault(key, null);
        var v2 = map2.getOrDefault(key, null);
        if (v1 != null) {
            if (v2 != null) {
                if (Objects.equals(v1, v2)) {
                    return List.of(new DiffEntry(key, v1, CompareResult.EQUALS));
                } else {
                    return List.of(
                            new DiffEntry(key, v1, CompareResult.REMOVED),
                            new DiffEntry(key, v2, CompareResult.ADDED)
                    );
                }
            } else {
                return List.of(new DiffEntry(key, v1, CompareResult.REMOVED));
            }
        } else {
            return List.of(new DiffEntry(key, v2, CompareResult.ADDED));
        }
    }

    private static String buildStringResponse(List<DiffEntry> results) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (var r : results) {
            stringBuilder.append("  ").append(r).append('\n');
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}