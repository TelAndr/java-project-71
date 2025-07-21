package hexlet.code;
import java.util.Map;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
public class Formatter {
    public String format(Map<String, Object> file1Data, Map<String, Object> file2Data) {
        return "default string";
    }

    //String format(Map<String, Object> before, Map<String, Object> after);
    public interface Format {
        String format(Map<String, Object> before, Map<String, Object> after);
    }
    public static Format getFormatter(String formatName) {
        switch (formatName.toLowerCase()) {
            case "plain":
                return new PlainFormatter();
            case "stylish":
            default:
                return new StylishFormatter();
        }
    }
}
