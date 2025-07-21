package hexlet.code;
import hexlet.code.formatters.StylishFormatter;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@CommandLine.Command(name = "filecomparer", mixinStandardHelpOptions = true, description = "Compares two files and shows the differences.")
public class FileComparerApp {
    @Option(names = {"-f", "--file1"}, description = "The path to the first file", required = true)
    private Path file1;

    @Option(names = {"-s", "--file2"}, description = "The path to the second file", required = true)
    private Path file2;

    @Option(names = {"-formatter"}, description = "Formatter to use (e.g., stylish, json)", defaultValue = "stylish")
    private String formatter;

    //public static void main(String[] args) {
    //    CommandLine.run(new FileComparerApp(), args);
    //}
    //@Override
    public void run() {
        try {
            Formatter formatterInstance = getFormatterInstance(formatter);
            String result = compareFiles(file1, file2, formatterInstance);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private Formatter getFormatterInstance(String formatterName) {
        switch (formatterName.toLowerCase()) {
            case "stylish":
                return new StylishFormatter()::format;
            // Добавление других форматеров здесь при необходимости
            default:
                throw new IllegalArgumentException("Unknown formatter: " + formatterName);
        }
    }
    private String compareFiles(Path file1, Path file2, Formatter formatter) throws IOException {
        Map<String, Object> mapFromYamlFile1 = JamlLoad.loadYaml(file1); //loadYaml(file1);
        Map<String, Object> mapFromYamlFile2 = JamlLoad.loadYaml(file2); //loadYaml(file2);
        return formatter.format(mapFromYamlFile1, mapFromYamlFile2);
    }
    private Map<String, Object> loadYaml(Path filePath) throws IOException {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = Files.newInputStream(filePath)) {
            return yaml.load(inputStream);
        }
    }
    public interface Formatter {
        String format(Map<String, Object> before, Map<String, Object> after);
    }

}
