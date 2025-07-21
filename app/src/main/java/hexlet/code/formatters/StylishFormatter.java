package hexlet.code.formatters;
import hexlet.code.Formatter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import hexlet.code.Formatter.Format;
public class StylishFormatter implements Format {
    @Override
    public String format(Map<String, Object> mapBefore, Map<String, Object> mapAfter) {
        StringBuilder resStrBuilder = new StringBuilder("{\n");

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(mapBefore.keySet());
        allKeys.addAll(mapAfter.keySet());

        for (String key : allKeys) {
            Object valueBefore = mapBefore.get(key);
            Object valueAfter = mapAfter.get(key);

            if (!mapBefore.containsKey(key)) {
                // Key added
                resStrBuilder.append("  + ").append(key).append(": ").append(valueAfter).append("\n");
            } else if (!mapAfter.containsKey(key)) {
                // Key removed
                resStrBuilder.append("  - ").append(key).append(": ").append(valueBefore).append("\n");
            } else if (!valueBefore.equals(valueAfter)) {
                // Key changed
                resStrBuilder.append("  - ").append(key).append(": ").append(valueBefore).append("\n");
                resStrBuilder.append("  + ").append(key).append(": ").append(valueAfter).append("\n");
            } else {
                // Key unchanged
                resStrBuilder.append("    ").append(key).append(": ").append(valueBefore).append("\n");
            }
        }
        resStrBuilder.append("}");

        return resStrBuilder.toString();
    }
    public static void main(String[] args) {
        Map<String, Object> before = Map.of(
                "chars1", List.of("a", "b", "c"),
                "chars2", List.of("d", "e", "f"),
                "checked", false,
                "default", null
        );

        Map<String, Object> after = Map.of(
                "chars1", List.of("a", "b", "c"),
                "chars2", false,
                "checked", true,
                "default", List.of("value1", "value2")
        );

        StylishFormatter stForm = new StylishFormatter();
        System.out.println(stForm.format(before, after));
    }
}
