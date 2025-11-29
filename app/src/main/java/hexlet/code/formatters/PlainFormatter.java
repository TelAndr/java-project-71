package hexlet.code.formatters;

import java.util.Map;
import hexlet.code.Formatter.Format;
import hexlet.code.Status;

public class PlainFormatter implements Format {
    /**
     * Форматирует различия между двумя структурами данных формата Plain.
     *
     * @param resultDiffMap карта различий, которую нужно отформатировать.
     * @return отформатированная строка.
     */
    @Override
    public String format(Map<String, Status> resultDiffMap) {
        StringBuilder resStrBuilder = new StringBuilder();

        for (String key : resultDiffMap.keySet()) {
            Status objStatusVal = resultDiffMap.get(key);

            switch (objStatusVal.getStatusName()) {
                case "deleted":
                    resStrBuilder.append("Property '").append(key).append("' was removed\n");
                    break;
                case "changed":
                    resStrBuilder.append("Property '").append(key).append("' was updated. From ")
                            .append(printValue(objStatusVal.getOldValue())).append(" to ")
                            .append(printValue(objStatusVal.getNewValue())).append("\n");
                    break;
                case "added":
                    resStrBuilder.append("Property '").append(key).append("' was added with value: ")
                            .append(printValue(objStatusVal.getNewValue())).append("\n");
                    break;
                case "unchanged":

                    break;
                default:
                    throw new RuntimeException("Unknown input: " + objStatusVal.getStatusName());
            }
        }
        return resStrBuilder.toString().trim();
    }
    private static String printValue(Object objVal) {
        if (objVal == null) {
            return "null";
        } else if (objVal instanceof String) {
            return "'" + objVal + "'";
        } else if (objVal instanceof Map || objVal instanceof Iterable) {
            return "[complex value]";
        } else {
            return objVal.toString();
        }
    }
}
