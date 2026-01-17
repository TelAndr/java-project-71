import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
//import java.io.FileNotFoundException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import java.nio.file.Files;
//import java.nio.file.Paths;

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

    //@Test
    //public void testBothJsonFilesEmpty() throws Exception {
    //    ObjectMapper objMapper = new ObjectMapper();

    //    Map<String, Object> mapJson1 = objMapper.readValue(new File("src/main/resources/file1.json"), Map.class);
    //    Map<String, Object> mapJson2 = objMapper.readValue(new File("src/main/resources/file2.json"), Map.class);

    //    assertTrue(areJsonFilesIdentical(mapJson1, mapJson2), "Оба JSON файла пусты и должны считаться идентичными");
    //}
    @Test
    public void testOneJsonFileEmpty() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();
        int indentFactor = 4;

        // Создаем пустой JSON-объект
        JSONObject jsonObject = new JSONObject();

        // Путь к файлу
        String filePath = "src/main/resources/empty.json";

        try (FileWriter file = new FileWriter(filePath)) {
            // Записываем JSON в файл
            file.write(jsonObject.toString(indentFactor)); // 4 — отступ для форматирования
            System.out.println("Пустой JSON-файл создан: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> nonEmptyJson = objMapper.readValue(new File("src/main/resources/file1.json"),
                Map.class);
        Map<String, Object> emptyJson = objMapper.readValue(new File("src/main/resources/empty.json"),
                Map.class);

        //System.out.println("Путь: " + emptyJson.getAbsolutePath());
        //System.out.println("Существует: " + emptyJson.exists());
        boolean isEmptyFile1 = nonEmptyJson.isEmpty();
        boolean isEmptyFile2 = emptyJson.isEmpty();
        boolean resultComp = isEmptyFile1 || isEmptyFile2;
        assertTrue(nonEmptyJson.isEmpty() || emptyJson.isEmpty(),
                "Один из JSON файлов пуст и должен считаться не равным");
    }
    //@Test
    //public void testEmptyFileReadException() {
    //    ObjectMapper objMapper = new ObjectMapper();

    //    assertThrows(JsonMappingException.class, () -> {
    //        objMapper.readValue(Files.newInputStream(Paths.get("src/main/resources/emptyExceptMod.json")),
    //                Map.class);
    //    }, "Пустой JSON файл должен вызвать исключение при считывании");
    //}
}
