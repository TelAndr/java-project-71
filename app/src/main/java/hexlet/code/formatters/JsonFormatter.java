package hexlet.code.formatters;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String format(Map<String, Status> resultDiffMap) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(resultDiffMap);
    }
}
