package hexlet.code.Differ.Formatters;

import hexlet.code.Differ.Diff;

import java.util.ArrayList;
import java.util.HashMap;

public class PlainFormatter implements Formatter {
    @Override
    public String format(Diff diff) {
        StringBuilder diffView = new StringBuilder();
        var keys = diff.keySet();
        var iterator = keys.iterator();
        var count = 0;

        do {
            var key = iterator.next();
            HashMap metaInfo = (HashMap) diff.get(key);

            switch ((String) metaInfo.get("reason")) {
                case "deleted" -> diffView.append(String.format("Property '%s' was removed", key));
                case "changed" -> diffView.append(
                        String.format("Property '%s' was updated. From %s to %s", key,
                        this.normalizeValue(metaInfo.get("old_value")),
                        this.normalizeValue(metaInfo.get("new_value")))
                );
                default -> diffView.append(String.format("Property '%s' was added with value: %s", key, this.normalizeValue(metaInfo.get("value"))));
            }

            if (++count < keys.size()) {
                diffView.append("\n");
            }
        } while (iterator.hasNext());

        return diffView.toString();
    }

    private String normalizeValue(Object value) {
        String valueType = value.getClass().getSimpleName();

        if (valueType.equals("ArrayList") || valueType.equals("LinkedHashMap")) {
            return "[complex value]";
        }

        if (valueType.equals("String") && !value.equals("null")) {
            return String.format("'%s'", value);
        }

        return value.toString();
    }
}
