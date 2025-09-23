import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class DifferTest {
    private String convertJsonToString(String strPath) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = new String();
        try {
            // Преобразование JSON-файла в строку
            jsonString = mapper.readValue(new File(strPath), String.class);
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    private String convertPlainToString(String strPath) {
        String plainString = new String();
        try {
            plainString = Files.readString(Paths.get(strPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plainString;
    }
    private String convertStylishToString(String strPath) {
        String stylishString = new String();
        try {
            stylishString = Files.readString(Paths.get(strPath), java.nio.charset.StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stylishString;
    }
    @Test
    void testInputYamlOutputJsonFiles() throws Exception {
        String filePath1 = "../app/src/main/resources/file1.yaml";
        String filePath2 = "../app/src/main/resources/file2.yaml";
        String formatName = "json";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringJson = convertJsonToString("../app/src/main/resources/diff.json");
        assertEquals(diffStringJson, outResultStr.trim());
    }

    @Test
    void testInputYamlOutputPlainFiles() throws Exception {
        String filePath1 = "testFile1.yaml";
        String filePath2 = "testFile2.yaml";
        String formatName = "plain";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringPlain = convertPlainToString("../app/src/main/resources/diff.plain");
        assertEquals(diffStringPlain, outResultStr.trim());
    }

    @Test
    void testInputYamlOutputStylishFiles() throws Exception {
        String filePath1 = "testFile1.yaml";
        String filePath2 = "testFile2.yaml";
        String formatName = "stylish";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringStylish = convertStylishToString("../app/src/main/resources/diff.stylish");
        assertEquals(diffStringStylish, outResultStr.trim());
    }

    @Test
    void testInputYamlOutputDefaultFiles() throws Exception {
        String filePath1 = "testFile1.yaml";
        String filePath2 = "testFile2.yaml";
        String formatName = "";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringStylish = convertStylishToString("../app/src/main/resources/diff.stylish");
        assertEquals(diffStringStylish, outResultStr.trim());
    }

    @Test
    void testInputJsonOutputJsonFiles() throws Exception {
        String filePath1 = "../app/src/main/resources/file1.json";
        String filePath2 = "../app/src/main/resources/file2.json";
        String formatName = "json";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringJson = convertJsonToString("../app/src/main/resources/diff.json");
        assertEquals(diffStringJson, outResultStr.trim());
    }

    @Test
    void testInputJsonOutputPlainFiles() throws Exception {
        String filePath1 = "testFile1.json";
        String filePath2 = "testFile2.json";
        String formatName = "plain";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringPlain = convertPlainToString("../app/src/main/resources/diff.plain");
        assertEquals(diffStringPlain, outResultStr.trim());
    }

    @Test
    void testInputJsonOutputStylishFiles() throws Exception {
        String filePath1 = "testFile1.json";
        String filePath2 = "testFile2.json";
        String formatName = "stylish";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringStylish = convertStylishToString("../app/src/main/resources/diff.stylish");
        assertEquals(diffStringStylish, outResultStr.trim());
    }

    @Test
    void testInputJsonOutputDefaultFiles() throws Exception {
        String filePath1 = "testFile1.json";
        String filePath2 = "testFile2.json";
        String formatName = "";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringStylish = convertStylishToString("../app/src/main/resources/diff.stylish");
        assertEquals(diffStringStylish, outResultStr.trim());
    }
}
