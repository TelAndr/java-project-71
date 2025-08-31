package hexlet.code.formatters;
import hexlet.code.Formatter;

import java.util.*;

import hexlet.code.Formatter.Format;
import hexlet.code.Status;

public class PlainFormatter implements Format {
    @Override
    //public String format(Map<String, Object> mapBefore, Map<String, Object> mapAfter) {
    public String format(Map<String, Status> resultDiffMap) {
        StringBuilder resStrBuilder = new StringBuilder();
        //Map<String, Status> resultDiffMap = new HashMap<>();

        Set<String> allKeys = new TreeSet<>();
        //allKeys.addAll(mapBefore.keySet());
        //allKeys.addAll(mapAfter.keySet());

        Set<String> allDiffKeys = new TreeSet<>();
        allDiffKeys.addAll(resultDiffMap.keySet());

        //for (String key : allKeys) {
        for (String key : allDiffKeys) {
                //Object objValBefore = mapBefore.get(key);
                //Object objValAfter = mapAfter.get(key);
                Status objStatusVal = resultDiffMap.get(key);

                switch(objStatusVal.getStatusName()) {
                    case "deleted":
                        //resStrBuilder.append("  - ").append(key).append(": ").append(objStatusVal.getOldValue()).append("\n");
                        resStrBuilder.append("Property '").append(key).append("' was removed\n");
                        break;
                    case "changed":
                        //resStrBuilder.append("  - ").append(key).append(": ").append(objStatusVal.getOldValue()).append("\n");
                        //resStrBuilder.append("  + ").append(key).append(": ").append(objStatusVal.getNewValue()).append("\n");
                        resStrBuilder.append("Property '").append(key).append("' was updated. From ")
                                .append(printValue(objStatusVal.getOldValue())).append(" to ")
                                .append(printValue(objStatusVal.getNewValue())).append("\n");
                        break;
                    case "added":
                        //resStrBuilder.append("  + ").append(key).append(": ").append(objStatusVal.getNewValue()).append("\n");
                        resStrBuilder.append("Property '").append(key).append("' was added with value: ")
                                .append(printValue(objStatusVal.getNewValue())).append("\n");
                        break;
                }

                //if (!mapBefore.containsKey(key)) {
                //    resStrBuilder.append("Property '").append(key).append("' was added with value: ")
                //            .append(printValue(objValAfter)).append("\n");
                //} else if (!mapAfter.containsKey(key)) {
                //    resStrBuilder.append("Property '").append(key).append("' was removed\n");
                //} else if (!objValBefore.equals(objValAfter)) {
                //    resStrBuilder.append("Property '").append(key).append("' was updated. From ")
                //            .append(printValue(objValBefore)).append(" to ")
                //            .append(printValue(objValAfter)).append("\n");
                //}
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
    //public static void main(String[] args) {
    //    Map<String, Object> mapBefore = Map.of(
    //            "chars1", List.of("a", "b", "c"),
    //            "chars2", List.of("d", "e", "f"),
    //            "checked", false,
    //            "default", null,
    //            "id", 45
    //    );

    //    Map<String, Object> mapAfter = Map.of(
    //            "chars1", List.of("a", "b", "c"),
    //            "chars2", false,
    //            "checked", true,
    //            "default", List.of("value1", "value2"),
    //            "id", null,
    //            "key1", "value1"
    //    );

    //    PlainFormatter plForm = new PlainFormatter();
    //    System.out.println(plForm.format(mapBefore, mapAfter));
    //}
}
