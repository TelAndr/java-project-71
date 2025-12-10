package hexlet.code.formatters;

import java.util.Map;
import hexlet.code.Formatter.Format;
import hexlet.code.Status;

public class StylishFormatter implements Format {
    /**
     * Форматирует различия между двумя структурами данных формата Stylish.
     *
     * @param resultDiffMap карта различий, которую нужно отформатировать.
     * @return отформатированная строка.
     */
    @Override
    public String format(Map<String, Status> resultDiffMap) throws Exception {
        StringBuilder resStrBuilder = new StringBuilder("{\n");

        for (String key : resultDiffMap.keySet()) {
            Status objStatusVal = resultDiffMap.get(key);

            switch (objStatusVal.getStatusName()) {
                case "deleted":
                    resStrBuilder.append("  - ").append(key).append(": ")
                            .append(objStatusVal.getOldValue()).append("\n");
                    break;
                case "changed":
                    resStrBuilder.append("  - ").append(key).append(": ")
                            .append(objStatusVal.getOldValue()).append("\n");
                    resStrBuilder.append("  + ").append(key).append(": ")
                            .append(objStatusVal.getNewValue()).append("\n");
                    break;
                case "added":
                    resStrBuilder.append("  + ").append(key).append(": ")
                            .append(objStatusVal.getNewValue()).append("\n");
                    break;
                case "unchanged":
                    resStrBuilder.append("    ").append(key).append(": ")
                            .append(objStatusVal.getNewValue()).append("\n");
                    break;
                default:
                    throw new RuntimeException("Unknown input: " + objStatusVal.getStatusName());
            }
        }
        resStrBuilder.append("}");
        return resStrBuilder.toString();
    }
}
