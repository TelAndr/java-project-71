package hexlet.code;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

import static hexlet.code.Formatter.getFormatter;
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

    // Формат данных берём на основе расширения файла, отрезая точку от строки.
    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        // 1. Чтение файлов и определение формата
        String strFileType1 = getDataFormat(filePath1); //determineFileType(filePath1);
        String strFileType2 = getDataFormat(filePath2); //determineFileType(filePath2);
        // 2. Парсинг данных
        Map<String, Object> mapFile1Data = Parser.parse(filePath1, strFileType1); //Parser.parseJsonOrYamlFile(strFileType1, filePath1);
        Map<String, Object> mapFile2Data = Parser.parse(filePath2, strFileType2); //Parser.parseJsonOrYamlFile(strFileType2, filePath2);
        // 3. Построение разницы
        Map<String, Status> resultDiffMap = findDifferentsMap(mapFile1Data, mapFile2Data);
        // 4. Форматирование данных
        Formatter.Format formatter = getFormatter(formatName);
        return formatter.format(resultDiffMap);
    }

    public static Map<String, Object> loadYaml(String filePath) throws Exception {
        //Parser parsObj = new Parser();
        return Parser.parse(filePath, "yaml"); //parsObj.parseYaml(filePath);
    }
}
