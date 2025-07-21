package hexlet.code;
import hexlet.code.formatters.StylishFormatter;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
public class FileComparer {
    public interface Formatter {
        String format(Map<String, Object> mapBefore, Map<String, Object> mapAfter);
    }

    public static String compareFiles(Path filePath1, Path filePath2, Formatter inputFormatter) throws IOException {
        Map<String, Object> mapFromYamlFile1 = JamlLoad.loadYaml(filePath1); //loadYaml(filePath1);
        Map<String, Object> mapFromYamlFile2 = JamlLoad.loadYaml(filePath2); //loadYaml(filePath2);

        // Если не указан форматер, использовать StylishFormatter по умолчанию
        if (inputFormatter == null) {
            inputFormatter = new StylishFormatter()::format;
        }

        return inputFormatter.format(mapFromYamlFile1, mapFromYamlFile2);
    }
    private static Map<String, Object> loadYaml(Path filePath) throws IOException {
        // Метод для загрузки файлов YAML в Map
        Yaml objYaml = new Yaml();
        try (InputStream inpStream = Files.newInputStream(filePath)) {
            return objYaml.load(inpStream);
        }
    }
    public static void main(String[] args) throws IOException {
        Path filePath1 = Path.of("path/to/firstFile.yaml");
        Path filePath2 = Path.of("path/to/secondFile.yaml");

        String strDifferences = compareFiles(filePath1, filePath2, null);
        System.out.println(strDifferences);
    }
}
