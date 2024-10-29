package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final ObjectMapper yamlMapper = new YAMLMapper();

    public static Map parse(Path filepath) throws Exception {
        return switch (getExtension(filepath)) {
            case "json" -> parseJson(filepath);
            case "yml" -> parseYaml(filepath);
            default -> throw new IllegalArgumentException("unsupported file extension: " + getExtension(filepath));
        };
    }

    private static Map parseJson(Path filepath) throws Exception {
        String jsonSource = Files.readString(filepath);
        return jsonMapper.readValue(jsonSource, Map.class);
    }

    private static Map parseYaml(Path filepath) throws Exception {
        String yamlSource = Files.readString(filepath);
        return yamlMapper.readValue(yamlSource, Map.class);
    }

    private static String getExtension(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        } else {
            return fileName.substring(dotIndex + 1);
        }
    }
}
