package hexlet.code.Differ.Parsers;

import java.io.File;

public class ParserFactory {

    public static FileParser create(File file) {

        String extension = ParserFactory.getFileExtension(file);

        if ("json".equals(extension)) {
            return new JsonParser();
        }

        if ("yml".equals(extension)) {
            return new YmlParser();
        }

        throw new RuntimeException(String.format("extension - %s is not support", extension));
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf).replaceAll("\\.", "");
    }
}
