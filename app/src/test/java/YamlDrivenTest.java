import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static hexlet.code.MapDiff.findDifferentsMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class YamlDrivenTest {
    static class TestCase {
        private String description;
        private String inputFile1;
        private String inputFile2;
        private String expectedResult;
    }
    private TestCase loadTestCase(String yamlFilePath) throws Exception {
        Yaml yaml = new Yaml(new Constructor(TestCase.class));
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(yamlFilePath)) {
            return yaml.load(inputStream);
        }
    }
    @Test
    @DisplayName("Test case 1: Identical JSONs")
    public void testYamlDrivenScenario() throws Exception {
        TestCase testCase = loadTestCase("test_cases/test_case_1.yml");

        // Вызов тестируемой функции сравнения файлов
        String strResult = compareJsonFiles(testCase.inputFile1, testCase.inputFile2);

        // Проверка результата
        assertEquals(testCase.expectedResult, strResult, testCase.description);
    }
    private String compareJsonFiles(String file1, String file2) throws IOException {
        // Здесь реализован ваш код для сравнения файлов
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> json1 = objMapper.readValue(new File("file1"), Map.class);
        Map<String, Object> json2 = objMapper.readValue(new File("file2"), Map.class);
        Map<String, Status> resultDiffMap = findDifferentsMap(json1, json2);
        return (resultDiffMap.isEmpty()) ? "identical" : "not identical";
    }
}
