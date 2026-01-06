import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
public class JsonDataTypeTest {
    private boolean areJsonValuesIdentical(Map<String, Object> mapJson1, Map<String, Object> mapJson2, String key) {
        Object objVal1 = mapJson1.get(key);
        Object objVal2 = mapJson2.get(key);
        return objVal1 != null && objVal1.equals(objVal2);
    }
    @Test
    public void testDifferentDataTypesInJsonFiles() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        // Подготовка JSON файлов с различными типами данных
        Map<String, Object> mapJson1 = objMapper.readValue(new File("src/main/resources/file1.json"), Map.class);
        Map<String, Object> mapJson2 = objMapper.readValue(new File("src/main/resources/file2.json"), Map.class);

        // Проверка как программа обрабатывает различные типы данных
        assertFalse(areJsonValuesIdentical(mapJson1, mapJson2, "stringKey"), "Строки должны отличаться");
        assertFalse(areJsonValuesIdentical(mapJson1, mapJson2, "numberKey"), "Числа должны отличаться");
        assertFalse(areJsonValuesIdentical(mapJson1, mapJson2, "booleanKey"), "Булевы значения должны отличаться");
    }
}
