package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
class DataHostVerbose {
    private int timeout;
    private boolean verbose;
    private String host;

    DataHostVerbose(int timeout, boolean verbose, String host) {
        this.timeout = timeout;
        this.verbose = verbose;
        this.host = host;
    }
    public String serialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRepresentation = objectMapper.writeValueAsString(this);
        return jsonRepresentation;
    }

    public static DataHostVerbose unserialize(String jsonRepresentation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonRepresentation, DataHostVerbose.class);
    }
}
