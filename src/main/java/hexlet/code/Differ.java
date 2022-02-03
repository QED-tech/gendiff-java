package hexlet.code;

import hexlet.code.Parsers.FileParser;
import hexlet.code.Parsers.ParserFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        File firstFile = new File(filePath1);
        File secondFile = new File(filePath2);

        String contentFirstFile = Files.readString(firstFile.toPath());
        String contentSecondFile = Files.readString(secondFile.toPath());

        FileParser fileParser = ParserFactory.create(firstFile);

        return Differ
                .makeDiff(fileParser.parse(contentFirstFile), fileParser.parse(contentSecondFile))
                .toFormat(formatName);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static Diff makeDiff(Map firstMap, Map secondMap) {
        HashSet<String> keysSet = new HashSet<>();
        var firstMapKeys = firstMap.keySet();
        var secondMapKeys = secondMap.keySet();

        keysSet.addAll(firstMapKeys);
        keysSet.addAll(secondMapKeys);

        Iterator<String> iterator = keysSet.iterator();

        var diff = new Diff();

        do {
            var key = iterator.next();
            var valueFirst = firstMap.get(key) == null ? "null" : firstMap.get(key);
            var valueSecond = secondMap.get(key) == null ? "null" : secondMap.get(key);

            if (firstMapKeys.contains(key) && secondMapKeys.contains(key) && valueFirst.equals(valueSecond)) {
                diff.put(key, Differ.getMetaInfoByState("equals", valueFirst, valueSecond));
                continue;
            }

            if (firstMapKeys.contains(key) && secondMapKeys.contains(key) && !valueFirst.equals(valueSecond)) {
                diff.put(key, Differ.getMetaInfoByState("changed", valueFirst, valueSecond));
                continue;
            }

            if (firstMapKeys.contains(key) && !secondMapKeys.contains(key)) {
                diff.put(key, Differ.getMetaInfoByState("deleted", valueFirst, valueSecond));
                continue;
            }

            diff.put(key, Differ.getMetaInfoByState("added", valueFirst, valueSecond));
        } while (iterator.hasNext());

        return diff;
    }

    private static HashMap getMetaInfoByState(String state, Object valueFromFirstMap, Object valueFromSecondMap) {
        var metaInfo = new HashMap<>();
        switch (state) {
            case "equals", "deleted" -> {
                metaInfo.put("value", valueFromFirstMap);
                metaInfo.put("reason", state);
                return metaInfo;
            }
            case "changed" -> {
                metaInfo.put("old_value", valueFromFirstMap);
                metaInfo.put("new_value", valueFromSecondMap);
                metaInfo.put("reason", state);
                return metaInfo;
            }
            case "added" -> {
                metaInfo.put("value", valueFromSecondMap);
                metaInfo.put("reason", "added");
                return metaInfo;
            }
            default -> {
                return metaInfo;
            }
        }
    }

}
