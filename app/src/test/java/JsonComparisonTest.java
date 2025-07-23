import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonComparisonTest {
    private boolean areJsonFilesIdentical(String pathToFile1, String pathToFile2) throws Exception {
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> objJson1 = objMapper.readValue(new File(pathToFile1), Map.class);
        Map<String, Object> objJson2 = objMapper.readValue(new File(pathToFile2), Map.class);
        return objJson1.equals(objJson2);
    }
    @Test
    public void testIdenticalJsonFiles() throws Exception {
        // Путь к идентичным JSON файлам
        String pathToFile1 = "path/to/identical_file1.json";
        String pathToFile2 = "path/to/identical_file2.json";

        assertTrue(areJsonFilesIdentical(pathToFile1, pathToFile2), "Файлы должны быть идентичны");
    }
    @Test
    public void testDifferentJsonFiles() throws Exception {
        // Путь к разным JSON файлам
        String pathToFile1 = "path/to/identical_file1.json";
        String pathToFile3 = "path/to/different_file.json";

        assertFalse(areJsonFilesIdentical(pathToFile1, pathToFile3), "Файлы не должны быть идентичны");
    }
}
