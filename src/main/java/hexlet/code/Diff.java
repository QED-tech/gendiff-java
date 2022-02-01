package hexlet.code;

import hexlet.code.Formatters.FormatterFactory;

import java.util.TreeMap;

public class Diff extends TreeMap {

    /**
     * @param format String
     * @return String
     */
    public String toFormat(String format) throws Exception {
        return FormatterFactory
                .create(format)
                .format(this);
    }
}
