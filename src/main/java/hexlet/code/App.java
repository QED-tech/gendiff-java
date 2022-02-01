package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "differ", mixinStandardHelpOptions = true, version = "differ 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @CommandLine.Option(
            names = {"-f", "--format=format"},
            defaultValue = "stylish",
            description = "output format [default: stylish]"
    )
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    /**
     * @return int
     */
    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(filePath1, filePath2, format);
        System.out.println(diff);
        return 0;
    }
}
