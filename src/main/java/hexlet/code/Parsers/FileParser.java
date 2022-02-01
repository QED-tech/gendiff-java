package hexlet.code.Parsers;

import java.util.Map;

public interface FileParser {
    Map parse(String content) throws Exception;
}
