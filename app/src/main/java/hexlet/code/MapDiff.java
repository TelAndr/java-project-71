package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Objects;

public class MapDiff {
    public static Map<String, Status> findDifferentsMap(Map<String, Object> mapObj1, Map<String, Object> mapObj2) {
        Set<String> keys = new TreeSet(Comparator.naturalOrder());
        keys.addAll(mapObj1.keySet());
        keys.addAll(mapObj2.keySet());
        Map<String, Status> diffMapStatus = new TreeMap<>();
        for (String curKeyMap : keys) {
            if (mapObj1.containsKey(curKeyMap) && !mapObj2.containsKey(curKeyMap)) {
                Status objStatus = new Status(Status.DELETED, mapObj1.get(curKeyMap), "");
                diffMapStatus.put(curKeyMap, objStatus);
            } else if (mapObj1.containsKey(curKeyMap)
                    && !Objects.equals(mapObj1.get(curKeyMap), mapObj2.get(curKeyMap))) {
                Status objStatus = new Status(Status.CHANGED, mapObj1.get(curKeyMap), mapObj2.get(curKeyMap));
                diffMapStatus.put(curKeyMap, objStatus);
            } else {
                Status objStatus = new Status(Status.UNCHANGED, mapObj1.get(curKeyMap), mapObj2.get(curKeyMap));
                diffMapStatus.put(curKeyMap, objStatus);
            }
            if (!mapObj1.containsKey(curKeyMap) && mapObj2.containsKey(curKeyMap)) {
                Status objStatus = new Status(Status.ADDED, "", mapObj2.get(curKeyMap));
                diffMapStatus.put(curKeyMap, objStatus);
            }
        }
        return diffMapStatus;
    }
}
