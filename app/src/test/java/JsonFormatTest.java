import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.Formatter;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class JsonFormatTest {
    private final JsonFormatter formatter = new JsonFormatter();
    //private boolean areJsonValuesIdentical(Map<String, Object> json1, Map<String, Object> json2, String key) {
    //    Object value1 = json1.get(key);
    //    Object value2 = json2.get(key);
    //    return value1 != null && value1.equals(value2);
    //}
    //@Test
    //public void testDifferentDataTypesInJsonFiles() throws Exception {
    //    ObjectMapper objectMapper = new ObjectMapper();

        // Подготовка JSON файлов с различными типами данных
    //    Map<String, Object> json1 = objectMapper.readValue(new File("path/to/file1.json"), Map.class);
    //    Map<String, Object> json2 = objectMapper.readValue(new File("path/to/file2.json"), Map.class);

        // Проверка как программа обрабатывает различные типы данных
    //    assertFalse(areJsonValuesIdentical(json1, json2, "stringKey"), "Строки должны отличаться");
    //    assertFalse(areJsonValuesIdentical(json1, json2, "numberKey"), "Числа должны отличаться");
    //    assertFalse(areJsonValuesIdentical(json1, json2, "booleanKey"), "Булевы значения должны отличаться");
    //}
    @Test
    public void testIdenticalJsonObjects() {
        Map<String, Object> mapJson1 = Map.of("key1", "value1", "key2", "value2");
        Map<String, Object> mapJson2 = Map.of("key1", "value1", "key2", "value2");

        String strResult = formatter.format(mapJson1, mapJson2);
        assertEquals("{}", strResult, "No difference should be found between identical JSON objects");
    }
    @Test
    public void testWithDifferentKeys() {
        Map<String, Object> mapJson1 = Map.of("key1", "value1");
        Map<String, Object> mapJson2 = Map.of("key2", "value2");

        String strExpected = "{ \"key1\": \"removed\", \"key2\": \"added\" }"; // Example structure
        String strResult = formatter.format(mapJson1, mapJson2);
        assertEquals(strExpected, strResult);
    }
    @Test
    public void testWithChangedValues() {
        Map<String, Object> mapJson1 = Map.of("key1", "value1");
        Map<String, Object> mapJson2 = Map.of("key1", "newValue");

        String strExpected = "{ \"key1\": \"from value1 to newValue\" }"; // Example structure
        String strResult = formatter.format(mapJson1, mapJson2);
        assertEquals(strExpected, strResult);
    }
    @Test
    public void testComplexStructures() {
        Map<String, Object> mapJson1 = Map.of("key1", Map.of("subkey", "value1"), "key2", "value2");
        Map<String, Object> mapJson2 = Map.of("key1", Map.of("subkey", "value2"), "key2", "value2");

        String strExpected = "{ \"key1.subkey\": \"from value1 to value2\" }"; // Example structure
        String strResult = formatter.format(mapJson1, mapJson2);
        assertEquals(strExpected, strResult);
    }
}
