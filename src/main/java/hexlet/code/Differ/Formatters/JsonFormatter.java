package hexlet.code.Differ.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ.Diff;

public class JsonFormatter implements Formatter {
    /**
     * @param diff Diff
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public String format(Diff diff) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
    }
}
