package hexlet.code;

import picocli.CommandLine;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

//public class App {
@CommandLine.Command(name = "app", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    //filePath1, filePath2 ...
    //@Option
    //@Parameters
    @CommandLine.Parameters(index = "0", description = "The path to the first file.")
    private Path filePath1; // = Path.of("src/main/resources/file1.json");
    @CommandLine.Parameters(index = "1", description = "The path to the second file.")
    private Path filePath2; //= Path.of("src/main/resources/file2.json");
    private String strFilePath1 = filePath1.toString();
    private String strFilePath2 = filePath2.toString();
    @CommandLine.Option(names = {"-f", "--format"}, description = "The format output (default: stylish).",
            defaultValue = "stylish")
    private String formatName = "plain";

    @Override
    public Integer call() {

        try {
            String formattedDiff = Differ.generate(strFilePath1, strFilePath2, formatName);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 1;
        }

        return 0;
    }
    public static void main(String[] args) throws Exception {
        System.out.printf("Hello World!");
        Path pathFile1 = Paths.get("file1.json");
        Path pathFile2 = Paths.get("file2.json");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
