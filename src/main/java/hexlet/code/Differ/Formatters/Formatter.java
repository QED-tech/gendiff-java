package hexlet.code.Differ.Formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Differ.Diff;

public interface Formatter {
    String format(Diff diff) throws JsonProcessingException;
}
