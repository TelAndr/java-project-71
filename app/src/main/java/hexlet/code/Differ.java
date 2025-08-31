package hexlet.code;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.HashMap;
import java.util.Map;

import static hexlet.code.JsonDiff.findDifferentsJsonMap;

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
        Formatter.Format formatter = getFormatter(formatName);
        String strFileType = determineFileType(filePath1);
        Map<String, Object> file1Data = new HashMap<>();
        Map<String, Object> file2Data = new HashMap<>();
        switch (strFileType) {
            case "json":
                file1Data = JsonDiff.parseJson(filePath1);
                file2Data = JsonDiff.parseJson(filePath2);
                break;
            case "yaml":
                file1Data = loadYaml(filePath1);
                file2Data = loadYaml(filePath2);
        }

        Map<String, Status> resultDiffMap = findDifferentsJsonMap(file1Data, file2Data);
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
    private static Map<String, Object> loadYaml(String filePath) throws Exception {
        Parser parsObj = new Parser();
        return parsObj.parseYaml(filePath);
    }
}
