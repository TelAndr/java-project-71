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

public class App {
    public static void saveProxy(Path filePath, DataHostProxy instance) throws Exception {
        String jsonRepresentation = instance.serialize();
        Files.writeString(filePath, jsonRepresentation, StandardOpenOption.WRITE);
    }

    public static DataHostProxy extractProxy(Path filePath) throws Exception {
        String jsonRepresentation = Files.readString(filePath);
        DataHostProxy instance = DataHostProxy.unserialize(jsonRepresentation);
        return instance;
    }

    public static void saveVerbose(Path filePath, DataHostVerbose instance) throws Exception {
        String jsonRepresentation = instance.serialize();
        Files.writeString(filePath, jsonRepresentation, StandardOpenOption.WRITE);
    }

    public static DataHostVerbose extractVerbose(Path filePath) throws Exception {
        String jsonRepresentation = Files.readString(filePath);
        DataHostVerbose instance = DataHostVerbose.unserialize(jsonRepresentation);
        return instance;
    }
    public static void main(String[] args) throws Exception {
            System.out.printf("Hello World!");
            DataHostProxy dhp = new DataHostProxy("hexlet.io", 50, "123.234.53.22", false);
            DataHostVerbose dhv = new DataHostVerbose(20, true, "hexlet.io");
            Path pathFile1 = Paths.get("file1.json");
            Path pathFile2 = Paths.get("file2.json");
            saveProxy(pathFile1, dhp);
            saveVerbose(pathFile2, dhv);
            extractProxy(pathFile1);
            extractProxy(pathFile2);
            int exitCode = new CommandLine(new CheckSum()).execute(args);
            System.exit(exitCode);
    }
}
