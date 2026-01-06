import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class JsonDifferenceTest {
    private Map<String, String> findDifferences(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        Map<String, String> mapDifferences = new HashMap<>();
        for (String key : mapJson1.keySet()) {
            if (mapJson2.containsKey(key) && !mapJson1.get(key).equals(mapJson2.get(key))) {
                mapDifferences.put(key, "Value in json1: " + mapJson1.get(key)
                        + ", Value in json2: " + mapJson2.get(key));
            }
        }
        return mapDifferences;
    }
    @Test
    public void testDifferentValuesInJsonFiles() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();
        // Загружаем два JSON файла
        Map<String, Object> mapJson1 = objMapper.readValue(new File("src/main/resources/file1.json"), Map.class);
        Map<String, Object> mapJson2 = objMapper.readValue(new File("src/main/resources/file2.json"), Map.class);
        Map<String, String> mapDifferences = findDifferences(mapJson1, mapJson2);
        // Пример проверки, если различия известны
        assertEquals(1, mapDifferences.size(), "Ожидалось одно различие");
        assertTrue(mapDifferences.containsKey("ключСРазличием"), "Ожидалось отличие для `ключСРазличием`");
        assertEquals("Value in json1: 123, Value in json2: 456",
                mapDifferences.get("ключСРазличием"), "Значения должны отличаться");
    }
}
