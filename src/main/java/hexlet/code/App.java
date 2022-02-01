package hexlet.code;

import hexlet.code.Differ.Differ;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "differ", mixinStandardHelpOptions = true, version = "differ 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @CommandLine.Option(names = {"-f", "--format=format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    /**
     * @return int
     */
    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(this.filepath1, this.filepath2, this.format);
        System.out.println(diff);
        return 0;
    }
}
