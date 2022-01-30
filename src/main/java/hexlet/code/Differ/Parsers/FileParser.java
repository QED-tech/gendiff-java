package hexlet.code.Differ.Parsers;

import java.util.Map;

public interface FileParser {
    Map parse(String content) throws Exception;
}
