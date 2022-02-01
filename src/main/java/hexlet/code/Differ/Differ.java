package hexlet.code.Differ;

import hexlet.code.Differ.Parsers.FileParser;
import hexlet.code.Differ.Parsers.ParserFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class Differ {

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        File firstFile = new File(firstFilePath);
        File secondFile = new File(secondFilePath);

        String contentFirstFile = Files.readString(firstFile.toPath());
        String contentSecondFile = Files.readString(secondFile.toPath());

        FileParser fileParser = ParserFactory.create(firstFile);

        return Differ
                .makeDiff(fileParser.parse(contentFirstFile), fileParser.parse(contentSecondFile))
                .toString();
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
