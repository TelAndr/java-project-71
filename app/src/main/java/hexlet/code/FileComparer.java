package hexlet.code;
import hexlet.code.formatters.StylishFormatter;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static hexlet.code.JsonDiff.findDifferentsMap;

public class FileComparer {
    public interface Formatter {
        //String format(Map<String, Object> mapBefore, Map<String, Object> mapAfter);
        String format(Map<String, Status> resultDiffMap);
    }

    public static String compareFiles(Path filePath1, Path filePath2, Formatter inputFormatter) throws IOException {
        Map<String, Object> mapFromYamlFile1 = JamlLoad.loadYaml(filePath1); //loadYaml(filePath1);
        Map<String, Object> mapFromYamlFile2 = JamlLoad.loadYaml(filePath2); //loadYaml(filePath2);

        // Если не указан форматер, использовать StylishFormatter по умолчанию
        if (inputFormatter == null) {
            inputFormatter = new StylishFormatter()::format;
        }
        Map<String, Status> resultDiffMap = findDifferentsMap(mapFromYamlFile1, mapFromYamlFile2);
        return inputFormatter.format(resultDiffMap);
    }
    private static Map<String, Object> loadYaml(Path filePath) throws IOException {
        // Метод для загрузки файлов YAML в Map
        Yaml objYaml = new Yaml();
        try (InputStream inpStream = Files.newInputStream(filePath)) {
            return objYaml.load(inpStream);
        }
    }
}
