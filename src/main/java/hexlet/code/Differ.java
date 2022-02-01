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

            var metaInfo = new HashMap<>();

            if (firstMapKeys.contains(key) && secondMapKeys.contains(key) && valueFirst.equals(valueSecond)) {
                metaInfo.put("value", valueFirst);
                metaInfo.put("reason", "equals");
                diff.put(key, metaInfo);
                continue;
            }

            if (firstMapKeys.contains(key) && secondMapKeys.contains(key) && !valueFirst.equals(valueSecond)) {
                metaInfo.put("old_value", valueFirst);
                metaInfo.put("new_value", valueSecond);
                metaInfo.put("reason", "changed");
                diff.put(key, metaInfo);
                continue;
            }

            if (firstMapKeys.contains(key) && !secondMapKeys.contains(key)) {
                metaInfo.put("value", valueFirst);
                metaInfo.put("reason", "deleted");
                diff.put(key, metaInfo);
                continue;
            }


            metaInfo.put("value", valueSecond);
            metaInfo.put("reason", "added");
            diff.put(key, metaInfo);

        } while (iterator.hasNext());

        return diff;
    }

}
