package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonDiff {
    public static Map<String, Object> findDifferentsJsonMap(Map<String, Object> mapJson1, Map<String, Object> mapJson2) {
        Map<String, Object> diffMap = new HashMap<>();
        Set<String> setAllKeysJson1 = mapJson1.keySet();
        Set<String> setAllKeysJson2 = mapJson2.keySet();
        //Set<String> filteredKeysJson1;
        //Set<String> filteredKeysJson2;
        for (String curKeyMap : mapJson1.keySet()) {
            if (!mapJson2.containsKey(curKeyMap) || !mapJson1.get(curKeyMap).equals(mapJson2.get(curKeyMap))) {
                diffMap.put(curKeyMap, mapJson1.get(curKeyMap));
            }
        }
        //setAllKeysJson1.stream().filter(curKeyMap -> (!mapJson2.containsKey(curKeyMap) || !mapJson1.get(curKeyMap).equals(mapJson2.get(curKeyMap))) ).
        //        map(curKeyMap -> diffMap.put(curKeyMap, mapJson1.get(curKeyMap)));
        for (String curKeyMap : mapJson2.keySet()) {
            if (!mapJson1.containsKey(curKeyMap)) {
                diffMap.put(curKeyMap, mapJson2.get(curKeyMap));
            }
        }
        //setAllKeysJson2.stream().filter(curKeyMap -> (!mapJson1.containsKey(curKeyMap))).
        //        map(curKeyMap -> diffMap.put(curKeyMap, mapJson2.get(curKeyMap)));
        return diffMap;
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> json1 = objMapper.readValue(new File("file1.json"), Map.class);
        Map<String, Object> json2 = objMapper.readValue(new File("file2.json"), Map.class);
        Map<String, Object> resultDiffMap = findDifferentsJsonMap(json1, json2);
    }
}
