package hexlet.code;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public Map<String, Object> parseYaml(String filePath) throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = YamlDiff.class.getClassLoader().getResourceAsStream(filePath)) {
            return yaml.load(inputStream);
        }
    }
    public static Map<String, Object> parseJsonOrYamlFile(String strFileType, String filePath) throws Exception {
        Map<String, Object> fileData = new HashMap<>();
        switch (strFileType) {
            case "json":
                fileData = JsonDiff.parseJson(filePath);
                break;
            case "yaml":
                fileData = Differ.loadYaml(filePath);
        }
        return fileData;
    }
}
