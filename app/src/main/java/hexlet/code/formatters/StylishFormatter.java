package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
    public String format(Map<String, Status> resultDiffMap) {
        final String chForRepl = ": ";
        StringBuilder resStrBuilder = new StringBuilder("{\n");
        Set<String> allDiffKeys = new TreeSet<>();
        allDiffKeys.addAll(resultDiffMap.keySet());

        for (String key : allDiffKeys) {
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
                    //throw new Error("Unknown order state: '${order.state}'!");
                    throw new RuntimeException("Unknown input: " + objStatusVal.getStatusName());
                    //resStrBuilder.append("Not set status value. Can not create Property!");
            }
            int endPozStrBuild = resStrBuilder.length() - 1;
            int pozReplElem = resStrBuilder.indexOf("=");
            //while ((pozReplElem = resStrBuilder.indexOf(String.valueOf("="), pozReplElem)) != -1) {
            //    if (pozReplElem >= 0) {
            //        resStrBuilder.replace(pozReplElem, pozReplElem + 1, chForRepl);
            //        pozReplElem++;
            //    }
            //}
        }
        resStrBuilder.append("}");
        return resStrBuilder.toString();
    }
}
