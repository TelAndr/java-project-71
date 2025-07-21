package hexlet.code;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
public class Parser {
    //public static Map<String, Object> loadYaml(String filePath) throws Exception {
    public Map<String, Object> parseYaml(String filePath) throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = YamlDiff.class.getClassLoader().getResourceAsStream(filePath)) {
            return yaml.load(inputStream);
        }
    }
}
