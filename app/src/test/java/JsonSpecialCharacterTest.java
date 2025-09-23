import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class JsonSpecialCharacterTest {
    @Test
    public void testSpecialCharactersHandling() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        // Загрузка JSON с спецсимволами
        Map<String, Object> mapJson = objMapper.readValue(new File("path/to/special_chars.json"), Map.class);

        // Проверка спецсимволов
        assertEquals("ñandú", mapJson.get("spanish"));
        assertEquals("北京", mapJson.get("chinese"));
        assertEquals("съешь же ещё этих мягких французских булок да выпей чаю", mapJson.get("russian"));
    }
    @Test
    public void testDifferentEncodings() throws Exception {
        ObjectMapper objMapper = new ObjectMapper();

        // Загрузка JSON UTF-8
        File utf8File = new File("path/to/utf8_file.json");
        Map<String, Object> mapUtf8Json = objMapper.readValue(utf8File, Map.class);

        // Проверьте здесь корректность UTF-8

        // Загрузка JSON UTF-16
        File utf16File = new File("path/to/utf16_file.json");
        Map<String, Object> utf16Json = objMapper.readValue(utf16File, Map.class);

        // Проверьте здесь корректность UTF-16

        // Сравнение эквивалентности содержимого
        assertEquals(mapUtf8Json, utf16Json, "Содержимое JSON эквивалентно, несмотря на различия в кодировке");
    }
}
