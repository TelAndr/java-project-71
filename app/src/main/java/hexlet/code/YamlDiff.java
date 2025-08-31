package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class YamlDiff {
    private final Parser parser;

    public YamlDiff() {
        this.parser = new Parser();
    }
    public void compareYamlFiles(String filePath1, String filePath2) throws Exception {
        Map<String, Object> mapFromYamlFile1 = parser.parseYaml(filePath1);
        Map<String, Object> mapFromYamlFile2 = parser.parseYaml(filePath2);
        Set<String> keys = mapFromYamlFile1.keySet();
        Map<String, String> differences = new HashMap<>();

        for (String key : keys) {
            Object objVal1 = mapFromYamlFile1.get(key);
            Object objVal2 = mapFromYamlFile2.get(key);

            if (!mapFromYamlFile2.containsKey(key)) {
                differences.put(key, "Key is missing in second file");
                System.out.println("+" + key + ":" + " " + objVal1);
            } else if (!objVal1.equals(objVal2)) {
                differences.put(key, "Value differs: " + objVal1 + " vs " + objVal2);
                System.out.println("-" + key + ":" + " " + objVal1);
            }
        }
        // Check for keys that are only in yaml2
        for (String key : mapFromYamlFile2.keySet()) {
            if (!mapFromYamlFile1.containsKey(key)) {
                differences.put(key, "Key is missing in first file");
                System.out.println("+" + key + ":" + " " + mapFromYamlFile2.get(key));
            }
        }
        // Выводим различия
        if (differences.isEmpty()) {
            System.out.println("No differences found. The YAML files are identical.");
            //System.out.println(" " + );
        } else {
            differences.forEach((key, diff) -> {
                System.out.println("Difference in key '" + key + "': " + diff);
            });
        }
    }
}
