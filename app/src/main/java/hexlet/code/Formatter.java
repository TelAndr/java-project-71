package hexlet.code;
import java.util.Map;

import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
public class Formatter {
    public String format(Map<String, Status> resultDiffMap) {
        return "default string";
    }

    public interface Format {
        String format(Map<String, Status> resultDiffMap);
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
