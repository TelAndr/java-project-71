import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class JsonMissingKeysTest {
    private Map<String, String> findDifferences(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        Map<String, String> differencesKeys = new HashMap<>();
        for (String key : mapJson1.keySet()) {
            //if (json2.containsKey(key) && !json1.get(key).equals(json2.get(key))) {
            //    differences.put(key, "Value in json1: " + json1.get(key) + ", Value in json2: " + json2.get(key));
            //}
            if (!mapJson2.containsKey(key)) {
                differencesKeys.put(key, "Value in json1: " +  mapJson1.get(key));
            }
        }
        return differencesKeys;
    }
    @Test
    public void testDifferentKeysInJsonFiles() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();
        // Загружаем два JSON файла
        Map<String, Object> mapJson1 = objMapper.readValue(new File("path/to/file1.json"), Map.class);
        Map<String, Object> mapJson2 = objMapper.readValue(new File("path/to/file2.json"), Map.class);
        Map<String, String> mapDifferences = findDifferences(mapJson1, mapJson2);
        // Пример проверки, если различия известны
        assertEquals(1, mapDifferences.size(), "Ожидалось одно различие");
        assertTrue(mapDifferences.containsKey("ключСРазличием"), "Ожидалось отличие для `ключСРазличием`");
        assertEquals("Value in json1: 123, Value in json2: 456",
                mapDifferences.get("ключСРазличием"), "Значения должны отличаться");
    }
}
