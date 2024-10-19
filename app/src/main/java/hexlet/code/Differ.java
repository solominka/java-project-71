package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void generate(Path filepath1, Path filepath2) {
        // todo
    }

    public static Map<String, String> parseJson(Path filepath) throws Exception {
        String jsonSource = Files.readString(filepath);
        Map<String, String> parsedMap = mapper.readValue(jsonSource, Map.class);
        return parsedMap;
    }
}
