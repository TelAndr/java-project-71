package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
class DataHostProxy {
    private String host;
    private int timeout;
    private String proxy;
    private boolean follow;

    public String serialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRepresentation = objectMapper.writeValueAsString(this);
        return jsonRepresentation;
    }

    public static DataHostProxy unserialize(String jsonRepresentation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonRepresentation, DataHostProxy.class);
    }
}
