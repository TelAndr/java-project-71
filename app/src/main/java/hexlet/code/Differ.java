package hexlet.code;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;
public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Formatter.Format formatter = getFormatter(formatName);
        Map<String, Object> file1Data = loadYaml(filePath1);
        Map<String, Object> file2Data = loadYaml(filePath2);

        return formatter.format(file1Data, file2Data);
    }
    private static Formatter.Format getFormatter(String formatName) {
        switch (formatName.toLowerCase()) {
            case "plain":
                return new PlainFormatter();
            case "stylish":
            default:
                return new StylishFormatter();
        }
    }
    private static Map<String, Object> loadYaml(String filePath) throws Exception {
        Parser parsObj = new Parser();
        //return new Parser().parseYaml(filePath); // Предположим, что ваш Parser класс уже реализован
        return parsObj.parseYaml(filePath);
    }
}
