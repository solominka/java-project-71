package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    public static Map parse(String data, String extension) throws Exception {
        return switch (extension) {
            case "json" -> parseJson(data);
            case "yml" -> parseYaml(data);
            default -> throw new IllegalArgumentException("unsupported file extension: " + extension);
        };
    }

    private static Map parseJson(String data) throws Exception {
        return JSON_MAPPER.readValue(data, Map.class);
    }

    private static Map parseYaml(String data) throws Exception {
        return YAML_MAPPER.readValue(data, Map.class);
    }
}
