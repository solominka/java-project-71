package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private Format format = Format.STYLISH;

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private Path filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private Path filepath2;

    @Override
    public Integer call() throws Exception {
        System.out.println(
                Differ.generate(filepath1, filepath2)
        );
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
