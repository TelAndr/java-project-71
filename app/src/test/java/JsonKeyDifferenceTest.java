import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class JsonKeyDifferenceTest {
    private Set<String> findExtraKeys(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        Set<String> extraKeys = new HashSet<>();
        for (String key : mapJson1.keySet()) {
            if (!mapJson2.containsKey(key)) {
                extraKeys.add(key);
            }
        }
        return extraKeys;
    }
    @Test
    public void testExtraKeysInJsonFiles() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        // Загружаем JSON файлы
        Map<String, Object> mapJson1 = objMapper.readValue(new File("path/to/file1.json"), Map.class);
        Map<String, Object> mapJson2 = objMapper.readValue(new File("path/to/file2.json"), Map.class);

        // Поиск дополнительных ключей в json1, которых нет в json2
        Set<String> extraKeysInJson1 = findExtraKeys(mapJson1, mapJson2);
        Set<String> extraKeysInJson2 = findExtraKeys(mapJson2, mapJson1);

        // Проверка наличия ожидаемых дополнительных ключей
        assertEquals(1, extraKeysInJson1.size(), "Ожидался один дополнительный ключ в первом JSON");
        assertTrue(extraKeysInJson1.contains("дополнительныйКлюч1"), "Ожидался дополнительный ключ: `дополнительныйКлюч1`");

        assertEquals(1, extraKeysInJson2.size(), "Ожидался один дополнительный ключ во втором JSON");
        assertTrue(extraKeysInJson2.contains("дополнительныйКлюч2"), "Ожидался дополнительный ключ: `дополнительныйКлюч2`");
    }
}
