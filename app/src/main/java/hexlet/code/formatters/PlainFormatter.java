package hexlet.code.formatters;
import hexlet.code.Formatter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import hexlet.code.Formatter.Format;
public class PlainFormatter implements Format {
    @Override
    public String format(Map<String, Object> mapBefore, Map<String, Object> mapAfter) {
        StringBuilder resStrBuilder = new StringBuilder();

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(mapBefore.keySet());
        allKeys.addAll(mapAfter.keySet());

        for (String key : allKeys) {
            Object objValBefore = mapBefore.get(key);
            Object objValAfter = mapAfter.get(key);

            if (!mapBefore.containsKey(key)) {
                resStrBuilder.append("Property '").append(key).append("' was added with value: ")
                        .append(printValue(objValAfter)).append("\n");
            } else if (!mapAfter.containsKey(key)) {
                resStrBuilder.append("Property '").append(key).append("' was removed\n");
            } else if (!objValBefore.equals(objValAfter)) {
                resStrBuilder.append("Property '").append(key).append("' was updated. From ")
                        .append(printValue(objValBefore)).append(" to ")
                        .append(printValue(objValAfter)).append("\n");
            }
        }

        return resStrBuilder.toString().trim();
    }
    private static String printValue(Object objVal) {
        if (objVal instanceof String) {
            return "'" + objVal + "'";
        } else if (objVal == null) {
            return "null";
        } else if (objVal instanceof Map || objVal instanceof Iterable) {
            return "[complex value]";
        } else {
            return objVal.toString();
        }
    }
    public static void main(String[] args) {
        Map<String, Object> mapBefore = Map.of(
                "chars1", List.of("a", "b", "c"),
                "chars2", List.of("d", "e", "f"),
                "checked", false,
                "default", null,
                "id", 45
        );

        Map<String, Object> mapAfter = Map.of(
                "chars1", List.of("a", "b", "c"),
                "chars2", false,
                "checked", true,
                "default", List.of("value1", "value2"),
                "id", null,
                "key1", "value1"
        );

        PlainFormatter plForm = new PlainFormatter();
        System.out.println(plForm.format(mapBefore, mapAfter));
    }
}
