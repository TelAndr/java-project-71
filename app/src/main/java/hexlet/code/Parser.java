package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Map;

public class Parser {
    private static Map parseYaml(String content) throws Exception  {
        ObjectMapper mapper =  new ObjectMapper(new YAMLFactory());
        // JsonParserWithComments.createObjectMapper(new YAMLFactory());
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        return mapper.readValue(content, Map.class);
    }

    private static Map parseJson(String content) throws Exception  {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        return mapper.readValue(content, Map.class);
    }

    public static Map parse(String content, String dataFormat) throws Exception {
        switch (dataFormat) {
            case "yml":
            case "yaml":
                return parseYaml(content);
            case "json":
                return parseJson(content);
            default:
                throw new Exception("Unknown format: '" + dataFormat + "'");
        }
    }
}
