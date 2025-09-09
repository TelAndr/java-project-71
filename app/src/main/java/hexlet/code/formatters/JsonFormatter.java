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
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(resultDiffMap);
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
