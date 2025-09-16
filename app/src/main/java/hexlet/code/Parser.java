package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    //public Map<String, Object> parseYaml(String filePath) throws Exception {
    //    Yaml yaml = new Yaml();
    //    try (InputStream inputStream = YamlDiff.class.getClassLoader().getResourceAsStream(filePath)) {
    //        return yaml.load(inputStream);
    //    }
    //}
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

    private static Map parseYaml(String content) throws Exception  {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(content, Map.class);
    }

    private static Map parseJson(String content) throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    public static Map parse(String content, String dataFormat) throws Exception {
        switch (dataFormat) {
            case "yml":
            case "yaml":
                return parseYaml(content);
            case "json":
                return parseJson(content);
            default:
                throw new Exception("Unknown format: '" + dataFormat + "'");
        }
    }
}
