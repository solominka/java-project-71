package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {
    @Test
    void shouldGenerateDiffForJsonFiles() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                classLoader.getResource("input/json/file1.json").getPath(),
                classLoader.getResource("input/json/file2.json").getPath(),
                "stylish"
        );
        var correct = Files.readString(Path.of(classLoader.getResource("output/stylish_correct.txt").getPath()));
        Assertions.assertEquals(correct, resp);
    }

    @Test
    void shouldGenerateDiffForYamlFiles() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                classLoader.getResource("input/yaml/file1.yml").getPath(),
                classLoader.getResource("input/yaml/file2.yml").getPath(),
                "stylish"
        );
        var correct = Files.readString(Path.of(classLoader.getResource("output/stylish_correct.txt").getPath()));
        Assertions.assertEquals(correct, resp);
    }

    @Test
    void shouldGeneratePlainDiff() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                classLoader.getResource("input/yaml/file1.yml").getPath(),
                classLoader.getResource("input/yaml/file2.yml").getPath(),
                "plain"
        );
        var correct = Files.readString(Path.of(classLoader.getResource("output/plain_correct.txt").getPath()));
        Assertions.assertEquals(correct, resp);
    }

    @Test
    void shouldGenerateJsonDiff() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                classLoader.getResource("input/yaml/file1.yml").getPath(),
                classLoader.getResource("input/yaml/file2.yml").getPath(),
                "json"
        );
        var correct = Files.readString(Path.of(classLoader.getResource("output/json_correct.json").getPath()));
        Assertions.assertEquals(correct, resp);
    }
}
