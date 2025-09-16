package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

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
    String strFilePath1 = filePath1.toString();
    String strFilePath2 = filePath2.toString();
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
    //public static void saveProxy(Path filePath, DataHostProxy instance) throws Exception {
    //    String jsonRepresentation = instance.serialize();
    //    Files.writeString(filePath, jsonRepresentation, StandardOpenOption.WRITE);
    //}

    //public static DataHostProxy extractProxy(Path filePath) throws Exception {
    //    String jsonRepresentation = Files.readString(filePath);
    //    DataHostProxy instance = DataHostProxy.unserialize(jsonRepresentation);
    //    return instance;
    //}

    //public static void saveVerbose(Path filePath, DataHostVerbose instance) throws Exception {
    //    String jsonRepresentation = instance.serialize();
    //    Files.writeString(filePath, jsonRepresentation, StandardOpenOption.WRITE);
    //}

    //public static DataHostVerbose extractVerbose(Path filePath) throws Exception {
    //    String jsonRepresentation = Files.readString(filePath);
    //    DataHostVerbose instance = DataHostVerbose.unserialize(jsonRepresentation);
    //    return instance;
    //}
    public static void main(String[] args) throws Exception {
        System.out.printf("Hello World!");
        Path pathFile1 = Paths.get("file1.json");
        Path pathFile2 = Paths.get("file2.json");
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
