import org.yaml.snakeyaml.Yaml;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class YamlNestedStructuresTest {
    public static boolean compareNestedStructures(Object obj1, Object obj2) {
        if (obj1 instanceof Map && obj2 instanceof Map) {
            Map<String, Object> map1 = (Map<String, Object>) obj1;
            Map<String, Object> map2 = (Map<String, Object>) obj2;
            if (map1.size() != map2.size()) {
                return false;
            }
            for (Object key : map1.keySet()) {
                if (!map2.containsKey(key) || !compareNestedStructures(map1.get(key), map2.get(key))) {
                    return false;
                }
            }
            return true;
        } else {
            return obj1.equals(obj2);
        }
    }
    public Map<String, Object> loadYaml(String filePath) throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            return yaml.load(inputStream);
        }
    }
    @Test
    public void testIdenticalNestedYamlStructures() throws Exception {
        Map<String, Object> mapYaml1 = loadYaml("path/to/nested_file1.yaml");
        Map<String, Object> mapYaml2 = loadYaml("path/to/nested_file2.yaml");

        assertTrue(compareNestedStructures(mapYaml1, mapYaml2), "YAML структуры должны быть идентичны");
    }
}
