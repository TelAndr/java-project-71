package hexlet.code;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

import static hexlet.code.JsonDiff.findDifferentsMap;

public class Differ {
    public static String determineFileType(String filePath) {
        if (filePath.endsWith(".json")) {
            return "json";
        } else if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            return "yaml";
        } else {
            return "unknown";
        }
    }
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        // 1. Чтение файлов и определение формата
        String strFileType1 = determineFileType(filePath1);
        String strFileType2 = determineFileType(filePath2);
        // 2. Парсинг данных
        Map<String, Object> mapFile1Data = Parser.parseJsonOrYamlFile(strFileType1, filePath1);
        Map<String, Object> mapFile2Data = Parser.parseJsonOrYamlFile(strFileType2, filePath2);
        // 3. Построение разницы
        Map<String, Status> resultDiffMap = findDifferentsMap(mapFile1Data, mapFile2Data);
        // 4. Форматирование данных
        Formatter.Format formatter = getFormatter(formatName);
        return formatter.format(resultDiffMap);
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
    public static Map<String, Object> loadYaml(String filePath) throws Exception {
        Parser parsObj = new Parser();
        return parsObj.parseYaml(filePath);
    }
}
