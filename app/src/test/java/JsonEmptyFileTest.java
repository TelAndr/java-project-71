import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonEmptyFileTest {
    private boolean findEmptyJsonFiles(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        //if (mapJson1.isEmpty() || mapJson2.isEmpty() || (mapJson1.isEmpty() && mapJson2.isEmpty())) {
        //    return true;
        //} else {
        //    return false;
        //}
        return (mapJson1.isEmpty() || mapJson2.isEmpty() || (mapJson1.isEmpty() && mapJson2.isEmpty()));
    }
    private boolean areJsonFilesIdentical(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        return mapJson1.equals(mapJson2);
    }
    //@Test
    //public void testEmptyKeysValuesInJsonFiles() throws Exception {
    //    ObjectMapper objectMapper = new ObjectMapper();

        // Загружаем JSON файлы
    //    Map<String, Object> json1 = objectMapper.readValue(new File("path/to/file1.json"), Map.class);
    //    Map<String, Object> json2 = objectMapper.readValue(new File("path/to/file2.json"), Map.class);
    //    boolean isEmptyMap = findEmptyJsonFiles(json1, json2);
    //    assertTrue(isEmptyMap, true);
    //}
    @Test
    public void testBothJsonFilesEmpty() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        Map<String, Object> mapJson1 = objMapper.readValue(new File("path/to/empty_file1.json"), Map.class);
        Map<String, Object> mapJson2 = objMapper.readValue(new File("path/to/empty_file2.json"), Map.class);

        assertTrue(areJsonFilesIdentical(mapJson1, mapJson2), "Оба JSON файла пусты и должны считаться идентичными");
    }
    @Test
    public void testOneJsonFileEmpty() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        Map<String, Object> nonEmptyJson = objMapper.readValue(new File("path/to/non_empty_file.json"), Map.class);
        Map<String, Object> emptyJson = objMapper.readValue(new File("path/to/empty_file.json"), Map.class);

        assertTrue(nonEmptyJson.isEmpty() || emptyJson.isEmpty(),
                "Один из JSON файлов пуст и должен считаться не равным");
    }
    @Test
    public void testEmptyFileReadException() {
        ObjectMapper objMapper = new ObjectMapper();

        assertThrows(JsonMappingException.class, () -> {
            objMapper.readValue(Files.newInputStream(Paths.get("path/to/empty_file.json")),
                    Map.class);
        }, "Пустой JSON файл должен вызвать исключение при считывании");
    }
}
