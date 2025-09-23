package hexlet.code.formatters;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.Formatter.Format;
import hexlet.code.Status;

public class JsonFormatter implements Format {
    /**
     * Форматирует различия между двумя json структурами данных.
     *
     * @param resultDiffMap карта различий, которую нужно отформатировать.
     * @return отформатированная строка.
     */
    @Override
    //public String format(Map<String, Object> mapBefore, Map<String, Object> mapAfter) {
    public String format(Map<String, Status> resultDiffMap) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(resultDiffMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while converting to JSON", e);
        }
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
