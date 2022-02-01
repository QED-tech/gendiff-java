package hexlet.code.Parsers;

import java.io.File;

public class ParserFactory {

    public static FileParser create(File file) {
        String extension = ParserFactory.getFileExtension(file);

        switch (extension) {
            case "json" -> {
                return new JsonParser();
            }

            case "yml" -> {
                return new YmlParser();
            }

            default -> throw new RuntimeException(String.format("extension - %s is not support", extension));
        }
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
