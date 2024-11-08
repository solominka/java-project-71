package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Files;
import java.nio.file.Path;

class DifferTest {
    @ParameterizedTest
    @CsvSource({
        "input/yaml/file1.yml,input/yaml/file2.yml,stylish,output/stylish_correct.txt",
        "input/json/file1.json,input/json/file2.json,stylish,output/stylish_correct.txt",
        "input/yaml/file1.yml,input/yaml/file2.yml,plain,output/plain_correct.txt",
        "input/json/file1.json,input/json/file2.json,plain,output/plain_correct.txt",
        "input/yaml/file1.yml,input/yaml/file2.yml,json,output/json_correct.json",
        "input/json/file1.json,input/json/file2.json,json,output/json_correct.json",
    })
    void shouldGenerateDiffByGivenFormatWithGivenFiles(
            String file1, String file2, String format, String correctPath
    ) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                getResourcePath(file1),
                getResourcePath(file2),
                format
        );
        var correct = Files.readString(Path.of(classLoader.getResource(correctPath).getPath()));
        Assertions.assertEquals(correct, resp);
    }

    @ParameterizedTest
    @CsvSource({
        "input/yaml/file1.yml,input/yaml/file2.yml,output/stylish_correct.txt",
        "input/json/file1.json,input/json/file2.json,output/stylish_correct.txt",
    })
    void shouldGenerateDiffWithDefaultFormat(String file1, String file2, String correctPath) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        var resp = Differ.generate(
                getResourcePath(file1),
                getResourcePath(file2)
        );
        var correct = Files.readString(Path.of(classLoader.getResource(correctPath).getPath()));
        Assertions.assertEquals(correct, resp);
    }

    private String getResourcePath(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource(path).getPath();
    }
}
