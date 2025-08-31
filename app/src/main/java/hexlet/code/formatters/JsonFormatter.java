package hexlet.code.formatters;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.Formatter.Format;
import hexlet.code.Status;

public class JsonFormatter implements Format {
    @Override
    //public String format(Map<String, Object> mapBefore, Map<String, Object> mapAfter) {
    public String format(Map<String, Status > resultDiffMap) {
        Map<String, Object> differences = new HashMap<>();
        //Set<String> allKeys = new TreeSet<>(mapBefore.keySet());
        Set<String> allDiffKeys = new TreeSet<>(resultDiffMap.keySet());
        //allKeys.addAll(mapBefore.keySet());
        //allKeys.addAll(mapAfter.keySet());

        //for (String key : allKeys) {
        for (String key : allDiffKeys) {
            //Object objValBefore = mapBefore.get(key);
            //Object objValAfter = mapAfter.get(key);
            Status objStatusVal = resultDiffMap.get(key);

            switch(objStatusVal.getStatusName()) {
                case "deleted":
                    differences.put(key, Map.of("status", "added", "newValue", objStatusVal.getOldValue()));
                    break;
                case "changed":
                    differences.put(key, Map.of("status", "changed", "oldValue", objStatusVal.getOldValue(),
                            "newValue", objStatusVal.getNewValue()));
                    break;
                case "added":
                    differences.put(key, Map.of("status", "removed", "oldValue", objStatusVal.getNewValue()));
                    break;
            }

            //if (!mapBefore.containsKey(key)) {
            //    differences.put(key, Map.of("status", "added", "newValue", objValAfter));
            //} else if (!mapAfter.containsKey(key)) {
            //    differences.put(key, Map.of("status", "removed", "oldValue", objValBefore));
            //} else if (!objValBefore.equals(objValAfter)) {
            //    differences.put(key, Map.of("status", "changed", "oldValue", objValBefore,
            //            "newValue", objValAfter));
            //}
        }
        return convertToJson(differences);
    }
    private String convertToJson(Map<String, Object> data) {
        ObjectMapper objMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while converting to JSON", e);
        }
    }
}
