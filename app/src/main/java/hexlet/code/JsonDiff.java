package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class JsonDiff {
    public static Map<String, Object> parseJson(String filePath) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> json = objMapper.readValue(new File("filePath"), Map.class);
        return json;
    }
    public static Map<String, Status> findDifferentsMap(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        Map<String, Object> diffMap = new HashMap<>();
        Map<String, Status> diffMapStatus = new HashMap<>();
        Set<String> setAllKeysJson1 = mapJson1.keySet();
        Set<String> setAllKeysJson2 = mapJson2.keySet();
        //Set<String> filteredKeysJson1;
        //Set<String> filteredKeysJson2;

        for (String curKeyMap : mapJson1.keySet()) {
            //if (!mapJson2.containsKey(curKeyMap) || !mapJson1.get(curKeyMap).equals(mapJson2.get(curKeyMap))) {
            if (!mapJson2.containsKey(curKeyMap) || !Objects.equals(mapJson1.get(curKeyMap), mapJson2.get(curKeyMap))) {
                diffMap.put(curKeyMap, mapJson1.get(curKeyMap));
            }
        }

        for (String curKeyMap : mapJson1.keySet()) {
            if (!mapJson2.containsKey(curKeyMap)) {
                Status objStatus = new Status(Status.DELETED, mapJson1.get(curKeyMap), "");
                diffMapStatus.put(curKeyMap, objStatus);
            } else if (!Objects.equals(mapJson1.get(curKeyMap), mapJson2.get(curKeyMap))) {
                Status objStatus = new Status(Status.CHANGED, mapJson1.get(curKeyMap), mapJson2.get(curKeyMap));
                diffMapStatus.put(curKeyMap, objStatus);
            } else {
                Status objStatus = new Status(Status.UNCHANGED, mapJson1.get(curKeyMap), mapJson2.get(curKeyMap));
                diffMapStatus.put(curKeyMap, objStatus);
            }
        }

        //setAllKeysJson1.stream().filter(curKeyMap -> (!mapJson2.containsKey(curKeyMap) ||
        // !mapJson1.get(curKeyMap).equals(mapJson2.get(curKeyMap))) ).
        //        map(curKeyMap -> diffMap.put(curKeyMap, mapJson1.get(curKeyMap)));
        for (String curKeyMap : mapJson2.keySet()) {
            if (!mapJson1.containsKey(curKeyMap)) {
                diffMap.put(curKeyMap, mapJson2.get(curKeyMap));
            }
        }

        for (String curKeyMap : mapJson2.keySet()) {
            if (!mapJson1.containsKey(curKeyMap)) {
                Status objStatus = new Status(Status.ADDED, "", mapJson2.get(curKeyMap));
                diffMapStatus.put(curKeyMap, objStatus);
            }
        }
        //setAllKeysJson2.stream().filter(curKeyMap -> (!mapJson1.containsKey(curKeyMap))).
        //        map(curKeyMap -> diffMap.put(curKeyMap, mapJson2.get(curKeyMap)));
        return diffMapStatus;
    }
}
