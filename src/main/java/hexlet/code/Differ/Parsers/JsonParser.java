package hexlet.code.Differ.Parsers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.TreeMap;

public class JsonParser implements FileParser {

    /**
     * @param content String
     * @return Map
     * @throws Exception
     */
    @Override
    public Map parse(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, TreeMap.class);
    }
}
