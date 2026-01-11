//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
public class JsonIOExceptionTest {
    @Test
    public void testFileNotFound() {
        ObjectMapper objectMapper = new ObjectMapper();
        File nonExistentFile = new File("path/to/non_existent_file.json");

        // Ожидание исключения IOException
        assertThrows(IOException.class, () -> {
            objectMapper.readValue(nonExistentFile, Map.class);
        });
    }
    //@Test
    //public void testInvalidJsonFormat() throws IOException {
    //    ObjectMapper objectMapper = new ObjectMapper();
    //    File invalidJsonFile = new File("path/to/invalid_format.json");

        // Ожидание исключения JsonParseException или JsonMappingException
    //    assertThrows(JsonMappingException.class, () -> {
    //        objectMapper.readValue(invalidJsonFile, Map.class);
    //    });
    //}
}
