package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    // Формат данных берём на основе расширения файла, отрезая точку от строки.
    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        // 1. Чтение файлов и определение формата
        String fileContent1 = Files.readString(Path.of(filePath1));
        String fileContent2 = Files.readString(Path.of(filePath2));
        String strFileType1 = getDataFormat(filePath1); //determineFileType(filePath1);
        String strFileType2 = getDataFormat(filePath2); //determineFileType(filePath2);
        // 2. Парсинг данных
        Map<String, Object> mapFile1Data = Parser.parse(fileContent1, strFileType1);
        Map<String, Object> mapFile2Data = Parser.parse(fileContent2, strFileType2);
        // 3. Построение разницы
        //Map<String, Status> resultDiffMap = new TreeMap<>();
        Map<String, Status> resultDiffMap = MapDiff.findDifferentsMap(mapFile1Data, mapFile2Data);
        // 4. Форматирование данных
        Formatter.Format formatter = Formatter.getFormatter(formatName);
        return formatter.format(resultDiffMap);
    }
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
