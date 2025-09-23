import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import static org.testng.Assert.assertTrue;

public class JsonKeyOrderTest {
    private boolean findKeysOrder(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        //Set<String> extraKeys = new HashSet<>();
        int countEquals = 0;
        ArrayList<String> json1OrderedKeys = new ArrayList<>();
        ArrayList<String> json2OrderedKeys = new ArrayList<>();
        for (String key : mapJson1.keySet()) {
            json1OrderedKeys.add(key);
        }
        for (String key : mapJson2.keySet()) {
            json2OrderedKeys.add(key);
        }
        int sizeOrderedKeys = json1OrderedKeys.size();
        for (int indArr = 0; indArr < sizeOrderedKeys; ++indArr) {
            if (json1OrderedKeys.get(indArr).equals(json2OrderedKeys.get(indArr))) {
                countEquals++;
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean areJsonFilesEquivalent(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        return mapJson1.equals(mapJson2);
    }
    @Test
    public void testOrderOfKeysInJsonFiles() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        // Загружаем JSON файлы, которые имеют одинаковые ключи и значения, но в разном порядке
        Map<String, Object> mapJson1 = objMapper.readValue(new File("path/to/file1.json"), Map.class);
        Map<String, Object> mapJson2 = objMapper.readValue(new File("path/to/file2.json"), Map.class);

        assertTrue(areJsonFilesEquivalent(mapJson1, mapJson2),
                "JSON файлы должны быть эквивалентны вне зависимости от порядка ключей");
    }
}
