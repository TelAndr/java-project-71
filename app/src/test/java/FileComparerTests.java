import hexlet.code.FileComparerApp;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
public class FileComparerTests {
    private final FileComparerApp.Formatter formatter = new StylishFormatter()::format;

    @Test
    public void testIdenticalFlatFiles() throws Exception {
        Map<String, Object> mapYaml1 = Map.of("key1", "value1", "key2", "value2");
        Map<String, Object> mapYaml2 = Map.of("key1", "value1", "key2", "value2");

        String strResFormat = formatter.format(mapYaml1, mapYaml2);
        assertTrue(strResFormat.contains("key1: value1"));
        assertTrue(strResFormat.contains("key2: value2"));
        // Ожидается отсутствие отличий
        assertFalse(strResFormat.contains("+") || strResFormat.contains("-"));
    }
    @Test
    public void testFilesWithDifferentKeys() throws Exception {
        Map<String, Object> mapYaml1 = Map.of("key1", "value1", "key2", "value2");
        Map<String, Object> mapYaml2 = Map.of("key1", "value1", "key3", "value3");

        String strResFormat = formatter.format(mapYaml1, mapYaml2);
        assertTrue(strResFormat.contains("- key2: value2"));
        assertTrue(strResFormat.contains("+ key3: value3"));
    }
    @Test
    public void testFilesWithDifferentValues() throws Exception {
        Map<String, Object> mapYaml1 = Map.of("key1", "value1", "key2", "value2");
        Map<String, Object> mapYaml2 = Map.of("key1", "value1", "key2", "differentValue");

        String strResFormat = formatter.format(mapYaml1, mapYaml2);
        assertTrue(strResFormat.contains("- key2: value2"));
        assertTrue(strResFormat.contains("+ key2: differentValue"));
    }
    @Test
    public void testEmptyFiles() throws Exception {
        Map<String, Object> mapYaml1 = Map.of();
        Map<String, Object> mapYaml2 = Map.of();

        String strResFormat = formatter.format(mapYaml1, mapYaml2);
        // Ожидается отсутствие отличий, пустой результат.
        assertEquals("{\n}", strResFormat.trim());
    }
}
