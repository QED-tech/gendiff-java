package hexlet.code.Formatters;

public class FormatterFactory {
    public static Formatter create(String format) throws Exception {
        switch (format) {
            case "stylish" -> {
                return new StylishFormatter();
            }

            case "plain" -> {
                return new PlainFormatter();
            }

            case "json" -> {
                return new JsonFormatter();
            }

            default -> throw new Exception(String.format("format - %s is not support", format));
        }
    }
}
