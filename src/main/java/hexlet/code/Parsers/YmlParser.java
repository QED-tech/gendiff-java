package hexlet.code.Parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;
import java.util.TreeMap;

public class YmlParser implements FileParser {

    /**
     * @param content String
     * @return Map
     * @throws Exception
     */
    @Override
    public Map parse(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(content, TreeMap.class);
    }
}
