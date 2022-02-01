package hexlet.code.Differ;

import java.util.HashMap;
import java.util.TreeMap;

public class Diff extends TreeMap {

    /**
     * @return String
     * @param format String
     */
    public String toFormat(String format) {
        if (format.equals("stylish")) {
            return this.makeStylishFormat();
        }

        return "";
    }

    private String makeStylishFormat() {
        StringBuilder diffView = new StringBuilder();
        var keys = this.keySet();
        var iterator = keys.iterator();

        do {
            var key = iterator.next();
            HashMap metaInfo = (HashMap) this.get(key);

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
