package hexlet.code.Differ;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        String contentFirstFile = Files.readString(new File(firstFilePath).toPath());
        String contentSecondFile = Files.readString(new File(secondFilePath).toPath());

        return Differ.makeDiff(
                Differ.getData(contentFirstFile),
                Differ.getData(contentSecondFile)
        );
    }

    private static Map getData(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, TreeMap.class);
    }

    private static String makeDiff(Map firstMap, Map secondMap) {
        return "result";
    }

}
