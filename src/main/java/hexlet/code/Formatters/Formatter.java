package hexlet.code.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Diff;

public interface Formatter {
    String format(Diff diff) throws JsonProcessingException;
}
