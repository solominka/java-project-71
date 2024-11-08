package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, Formatter.STYLISH);
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var map1 = getData(filepath1);
        var map2 = getData(filepath2);

        var diff = DiffBuilder.buildDiff(map1, map2);

        return Formatter.format(diff, format);
    }

    private static Map getData(String filepath) throws Exception {
        Path fullPath = getFullPath(filepath);
        String extension = getExtension(fullPath);
        String data = Files.readString(fullPath);

        return Parser.parse(data, extension);
    }

    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
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
