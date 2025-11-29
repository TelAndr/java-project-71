package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;

//public class App {
@CommandLine.Command(name = "app", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    //@CommandLine.Parameters(index = "0", description = "The path to the first file.")
    //private Path filePath1; // = Path.of("src/main/resources/file1.json");
    //@CommandLine.Parameters(index = "1", description = "The path to the second file.")
    //private Path filePath2; //= Path.of("src/main/resources/file2.json");
    //private String strFilePath1 = filePath1.toString();
    //private String strFilePath2 = filePath2.toString();
    //@CommandLine.Option(names = {"-f", "--format"}, description = "The format output (default: stylish).",
    //        defaultValue = "stylish")
    //private String formatName = "plain";
    @CommandLine.Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String formatName;

    @CommandLine.Parameters(paramLabel = "filepath1",
            index = "0",
            description = "path to first file")
    private String filePath1;

    @CommandLine.Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to second file")
    private String filePath2;

    @Override
    public Integer call() {

        try {
            String formattedDiff = Differ.generate(filePath1, filePath2, formatName);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 1;
        }

        return 0;
    }
    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
