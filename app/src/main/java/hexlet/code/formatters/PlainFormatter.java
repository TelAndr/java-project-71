package hexlet.code.formatters;

import java.util.Map;
import hexlet.code.Formatter.Format;
import hexlet.code.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlainFormatter implements Format {
    /**
     * Форматирует различия между двумя структурами данных формата Plain.
     *
     * @param resultDiffMap карта различий, которую нужно отформатировать.
     * @return отформатированная строка.
     */
    @Override
    public String format(Map<String, Status> resultDiffMap) throws Exception {
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
    //private static String printValue(Object objVal) {
    //    if (objVal == null) {
    //        return "null";
    //    } else if (objVal instanceof String) {
    //        return "'" + objVal + "'";
    //    } else if (objVal instanceof Map || objVal instanceof Iterable) {
    //        return "[complex value]";
    //    } else {
    //        return objVal.toString();
    //    }
    //}
    private static String printValue(Object objVal) {
        ObjectMapper mapper = new ObjectMapper();
        if (objVal == null) {
            return "null";
        } else if (objVal instanceof String) {
            return "'" + objVal + "'";
        } else if (objVal instanceof Map) {
            // Преобразуем Map в строку вида {key1=value1, key2=value2}
            Map<String, Status> map = mapper.convertValue(objVal, Map.class); //(Map<String, Status>) objVal;
            StringBuilder sb = new StringBuilder("'{");
            boolean first = true;
            for (var e : map.entrySet()) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(e.getKey()).append("=").append(e.getValue());
                first = false;
            }
            sb.append("}'");
            return sb.toString();
        } else if (objVal instanceof Iterable) {
            // Обработка списков
            Iterable<Status> iter = mapper.convertValue(objVal, Iterable.class); //(Iterable<Status>) objVal;
            StringBuilder sb = new StringBuilder("[");
            boolean first = true;
            for (Status item : iter) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(printValue(item));
                first = false;
            }
            sb.append("]");
            return sb.toString();
        } else {
            return objVal.toString();
        }
    }
}
