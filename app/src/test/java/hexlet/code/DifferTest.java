package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {
    @Test
    void ShouldGenerateDiffForJsonFiles() throws Exception {
        var resp = Differ.generate(
                // todo use relative paths
                Path.of("/Users/annkolesnikova/java-project-71/app/src/test/resources/input/json/file1.json"),
                Path.of("/Users/annkolesnikova/java-project-71/app/src/test/resources/input/json/file2.json")
        );
        // todo use relative paths
        var correct = Files.readString(Path.of("/Users/annkolesnikova/java-project-71/app/src/test/resources/output/json/correct.txt"));
        Assertions.assertEquals(correct, resp);
    }
}