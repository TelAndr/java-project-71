package hexlet.code;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class JamlLoad {
    public static Map<String, Object> loadYaml(Path filePath) throws IOException {
        // Метод для загрузки файлов YAML в Map
        Yaml objYaml = new Yaml();
        try (InputStream inpStream = Files.newInputStream(filePath)) {
            return objYaml.load(inpStream);
        }
    }
}
