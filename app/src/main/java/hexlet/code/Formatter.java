package hexlet.code;
import java.util.Map;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.StylishFormatter;
import hexlet.code.formatters.PlainFormatter;
public class Formatter {
    /**
     * Форматирует различия между двумя структурами данных.
     * Этот метод может быть переопределен в подклассах для изменения поведения.
     *
     * @param resultDiffMap карта различий, которую нужно отформатировать.
     * @return отформатированная строка.
     */
    public String format(Map<String, Status> resultDiffMap) {
        return "default string";
    }

    public interface Format {
        String format(Map<String, Status> resultDiffMap) throws Exception;
    }
    public static Format getFormatter(String formatName) {
        switch (formatName.toLowerCase()) {
            case "json":
                return new JsonFormatter();
            case "plain":
                return new PlainFormatter();
            case "stylish":
            default:
                return new StylishFormatter();
        }
    }
}
