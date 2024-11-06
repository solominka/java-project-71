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
                Path.of(classLoader.getResource("input/json/file1.json").getPath()),
                Path.of(classLoader.getResource("input/json/file2.json").getPath())
        );
        var correct = Files.readString(Path.of(classLoader.getResource("output/correct.txt").getPath()));
        Assertions.assertEquals(correct, resp);
    }

    @Test
    void shouldGenerateDiffForComplicatedJsonFiles() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                Path.of(classLoader.getResource("input/object_values/file1.json").getPath()),
                Path.of(classLoader.getResource("input/object_values/file2.json").getPath())
        );
        var correct = Files.readString(Path.of(classLoader.getResource("output/complicated_correct.txt").getPath()));
        Assertions.assertEquals(correct, resp);
    }

    @Test
    void shouldGenerateDiffForYamlFiles() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                Path.of(classLoader.getResource("input/yaml/file1.yml").getPath()),
                Path.of(classLoader.getResource("input/yaml/file2.yml").getPath())
        );
        var correct = Files.readString(Path.of(classLoader.getResource("output/correct.txt").getPath()));
        Assertions.assertEquals(correct, resp);
    }
}
