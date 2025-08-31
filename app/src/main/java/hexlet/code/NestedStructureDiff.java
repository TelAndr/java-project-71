package hexlet.code;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class NestedStructureDiff {
    // Метод для рекурсивного сравнения
    public static boolean compareStructures(Object obj1, Object obj2, Map<String, String> mapDifferences, String path) {
        if (obj1 instanceof Map && obj2 instanceof Map) {
            Map<String, Object> map1 = (Map<String, Object>) obj1;
            Map<String, Object> map2 = (Map<String, Object>) obj2;
            Set<String> keys = map1.keySet();

            for (Object key : keys) {
                if (!map2.containsKey(key)) {
                    mapDifferences.put(path + key, "Exists in first but not in second");
                } else if (!compareStructures(map1.get(key), map2.get(key), mapDifferences, path + key + ".")) {
                    mapDifferences.put(path + key, "Difference in nested structure");
                }
            }

            // Check for keys that are only in map2
            for (Object key : map2.keySet()) {
                if (!map1.containsKey(key)) {
                    mapDifferences.put(path + key, "Exists in second but not in first");
                }
            }

            return true;
        } else if (obj1 instanceof List && obj2 instanceof List) {
            List<Object> list1 = (List<Object>) obj1;
            List<Object> list2 = (List<Object>) obj2;

            if (list1.size() != list2.size()) {
                mapDifferences.put(path, "List sizes differ");
                return false;
            }

            for (int i = 0; i < list1.size(); i++) {
                if (!compareStructures(list1.get(i), list2.get(i), mapDifferences, path + "[" + i + "]" + ".")) {
                    return false;
                }
            }

            return true;
        } else {
            return obj1.equals(obj2);
        }
    }
    // Метод загрузки YAML
    public static Map<String, Object> loadYaml(String filePath) throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = NestedStructureDiff.class.getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            return yaml.load(inputStream);
        }
    }
}
