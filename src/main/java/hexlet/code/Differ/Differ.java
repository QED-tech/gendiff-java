package hexlet.code.Differ;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class Differ {

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        String contentFirstFile = Files.readString(new File(firstFilePath).toPath());
        String contentSecondFile = Files.readString(new File(secondFilePath).toPath());

        var diff = Differ.makeDiff(Differ.getData(contentFirstFile), Differ.getData(contentSecondFile));
        return Differ.diffToString(diff);
    }

    private static Map getData(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, TreeMap.class);
    }

    private static String diffToString(Map diff) {
        StringBuilder diffView = new StringBuilder();
        var keys = diff.keySet();
        var iterator = keys.iterator();

        do {
            var key = iterator.next();
            HashMap metaInfo = (HashMap) diff.get(key);

            switch ((String) metaInfo.get("reason")) {
                case "equals" -> {
                    diffView.append(String.format("  %s: %s%n", key, metaInfo.get("value")));
                    continue;
                }

                case "deleted" -> {
                    diffView.append(String.format("- %s: %s%n", key, metaInfo.get("value")));
                    continue;
                }

                case "changed" -> {
                    diffView.append(String.format("- %s: %s%n", key, metaInfo.get("old_value")));
                    diffView.append(String.format("+ %s: %s%n", key, metaInfo.get("new_value")));
                    continue;
                }
            }


            diffView.append(String.format("+ %s: %s%n", key, metaInfo.get("value")));
        } while (iterator.hasNext());

        return String.format("{%n%s}", diffView.toString());
    }

    private static Map makeDiff(Map firstMap, Map secondMap) {
        HashSet<String> keysSet = new HashSet<>();
        var firstMapKeys = firstMap.keySet();
        var secondMapKeys = secondMap.keySet();

        keysSet.addAll(firstMapKeys);
        keysSet.addAll(secondMapKeys);

        Iterator<String> iterator = keysSet.iterator();

        var diff = new TreeMap<>();

        do {
            var key = iterator.next();
            var valueFirst = firstMap.get(key);
            var valueSecond = secondMap.get(key);

            var metaInfo = new HashMap<>();

            if (firstMapKeys.contains(key) && secondMapKeys.contains(key) && valueFirst.equals(valueSecond)) {
                metaInfo.put("value", valueFirst);
                metaInfo.put("reason", "equals");
                metaInfo.put("isParent", false);
                diff.put(key, metaInfo);
                continue;
            }

            if (firstMapKeys.contains(key) && secondMapKeys.contains(key) && !valueFirst.equals(valueSecond)) {
                metaInfo.put("old_value", valueFirst);
                metaInfo.put("new_value", valueSecond);
                metaInfo.put("reason", "changed");
                metaInfo.put("isParent", false);
                diff.put(key, metaInfo);
                continue;
            }

            if (firstMapKeys.contains(key) && !secondMapKeys.contains(key)) {
                metaInfo.put("value", valueFirst);
                metaInfo.put("reason", "deleted");
                metaInfo.put("isParent", false);
                diff.put(key, metaInfo);
                continue;
            }


            metaInfo.put("value", valueSecond);
            metaInfo.put("reason", "added");
            metaInfo.put("isParent", false);
            diff.put(key, metaInfo);

        } while (iterator.hasNext());

        return diff;
    }

}
