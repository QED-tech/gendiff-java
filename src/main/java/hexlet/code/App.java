package hexlet.code;

import hexlet.code.Differ.Differ;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "differ", mixinStandardHelpOptions = true, version = "differ 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    /**
     * @return int
     */
    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate("1", "2");
        System.out.println(diff);
        return 0;
    }
}
