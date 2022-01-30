package hexlet.code.Differ.Parsers;

import java.util.Map;

public interface FileParser {
    public Map parse(String content) throws Exception;
}
