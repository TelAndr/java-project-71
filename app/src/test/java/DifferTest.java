import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
    public static int findDifferenceIndex(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return i;
            }
        }
        return minLength; // строки одинаковы до длины короткой строки
    }
    public static boolean compareIgnoringFormat(String str1, String str2) {
        String s1 = str1.toLowerCase().replaceAll("\\s+", " ").trim();
        String s2 = str2.toLowerCase().replaceAll("\\s+", " ").trim();
        return s1.equals(s2);
    }
    public static boolean compareRemoveSpacesString(String str1, String str2) {
        String s1 = str1.toLowerCase().replaceAll("\\s+", "").trim();
        String s2 = str2.toLowerCase().replaceAll("\\s+", "").trim();
        return s1.equals(s2);
    }
    @Test
    void testInputYamlOutputJsonFiles() throws Exception {
        String filePath1 = "src/main/resources/file1.yml";
        String filePath2 = "src/main/resources/file2.yml";
        String formatName = "json";
        String outResultStr = "";
        String expected = "";
        String actual = "";
        outResultStr = Differ.generate(filePath1, filePath2, formatName);
        expected = Files.readString(Paths.get("../app/src/main/resources/diff.json")).trim()
                .replaceAll("\\r\\n", "")
                .replaceAll("\\r", "");
        actual = outResultStr.trim()
                .replaceAll("\\r\\n", "")
                .replaceAll("\\r", "");
        assertTrue(compareRemoveSpacesString(expected, actual));
        //assertTrue(compareIgnoringFormat(expected, actual));
        //String diffStringJson = convertJsonToString("../app/src/main/resources/diff.json");
        //assertEquals(diffStringJson, outResultStr.trim());
    }

    @Test
    void testInputYamlOutputPlainFiles() throws Exception {
        String filePath1 = "src/main/resources/file1.yml";
        String filePath2 = "src/main/resources/file2.yml";
        String formatName = "plain";
        String outResultStr = "";
        String expected = "";
        String actual = "";
        outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringPlain = convertPlainToString("../app/src/main/resources/diff.plain");
        expected = Files.readString(Paths.get("../app/src/main/resources/diff.plain")).trim()
                .replaceAll("\\r\\n", "")
                .replaceAll("\\r", "");
        actual = outResultStr.trim()
                .replaceAll("\\r\\n", "")
                .replaceAll("\\r", "");
        System.out.println("EXPECTED:\n" + diffStringPlain);
        System.out.println("ACTUAL:\n" + outResultStr);
        assertTrue(compareRemoveSpacesString(expected, actual));
        //assertEquals(diffStringPlain, outResultStr.trim());
    }

    @Test
    void testInputYamlOutputStylishFiles() throws Exception {
        String filePath1 = "src/main/resources/file1.yml";
        String filePath2 = "src/main/resources/file2.yml";
        String formatName = "stylish";
        String outResultStr = "";
        String expected = "";
        String actual = "";
        System.out.println("Текущая директория: " + System.getProperty("user.dir"));
        outResultStr = Differ.generate(filePath1, filePath2, formatName);
        expected = Files.readString(Paths.get("../app/src/main/resources/diff.stylish")).trim()
                .replaceAll("\\r\\n", "\n")
                .replaceAll("\\r", "\n");
        actual = outResultStr.trim()
                .replaceAll("\\r\\n", "\n")
                .replaceAll("\\r", "\n");
        String diffStringStylish = convertStylishToString("../app/src/main/resources/diff.stylish");
        assertTrue(compareIgnoringFormat(expected, actual));
        //assertEquals(diffStringStylish, outResultStr.trim());
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
        String filePath1 = "src/main/resources/file1.json";
        String filePath2 = "src/main/resources/file2.json";
        String formatName = "json";
        String outResultStr = "";
        outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringJson = convertJsonToString("../app/src/main/resources/diff.json");
        assertEquals(diffStringJson, outResultStr.trim());
    }

    @Test
    void testInputJsonOutputPlainFiles() throws Exception {
        String filePath1 = "src/main/resources/file1.json";
        String filePath2 = "src/main/resources/file2.json";
        String formatName = "plain";
        String outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringPlain = convertPlainToString("../app/src/main/resources/diff.plain");
        assertEquals(diffStringPlain, outResultStr.trim());
    }

    @Test
    void testInputJsonOutputStylishFiles() throws Exception {
        String filePath1 = "src/main/resources/file1.json";
        String filePath2 = "src/main/resources/file2.json";
        String formatName = "stylish";
        String outResultStr = "";
        String expected = "";
        String actual = "";
        outResultStr = Differ.generate(filePath1, filePath2, formatName);
        expected = Files.readString(Paths.get("../app/src/main/resources/diff.stylish")).trim()
                .replaceAll("\\r\\n", "\n")
                .replaceAll("\\r", "\n");
        actual = outResultStr.trim()
                .replaceAll("\\r\\n", "\n")
                .replaceAll("\\r", "\n");
        System.out.println("EXPECTED:\n" + expected);
        System.out.println("ACTUAL:\n" + actual);
        assertTrue(compareIgnoringFormat(expected, actual));
        //assertEquals(expected, actual);
    }

    @Test
    void testInputJsonOutputDefaultFiles() throws Exception {
        String filePath1 = "src/main/resources/file1.json";
        String filePath2 = "src/main/resources/file2.json";
        String formatName = "";
        String outResultStr = "";
        outResultStr = Differ.generate(filePath1, filePath2, formatName);
        String diffStringStylish = convertStylishToString("../app/src/main/resources/diff.stylish");
        assertEquals(diffStringStylish, outResultStr.trim());
    }
}
