package hexlet.code.Formatters;

import hexlet.code.Diff;

import java.util.HashMap;

public class StylishFormatter implements Formatter {
    /**
     * @param diff Diff
     * @return
     */
    @Override
    public String format(Diff diff) {
        StringBuilder diffView = new StringBuilder();
        var keys = diff.keySet();
        var iterator = keys.iterator();

        do {
            var key = iterator.next();
            HashMap metaInfo = (HashMap) diff.get(key);

            switch ((String) metaInfo.get("reason")) {
                case "equals" -> diffView.append(String.format("    %s: %s%n", key, metaInfo.get("value")));
                case "deleted" -> diffView.append(String.format("  - %s: %s%n", key, metaInfo.get("value")));
                case "changed" -> {
                    diffView.append(String.format("  - %s: %s%n", key, metaInfo.get("old_value")));
                    diffView.append(String.format("  + %s: %s%n", key, metaInfo.get("new_value")));
                }
                default -> diffView.append(String.format("  + %s: %s%n", key, metaInfo.get("value")));
            }

        } while (iterator.hasNext());

        return String.format("{%n%s}", diffView.toString());
    }
}
