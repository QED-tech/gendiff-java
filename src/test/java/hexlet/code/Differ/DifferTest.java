package hexlet.code.Differ;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class DifferTest {

    @Test
    void testDiffer() throws Exception {
        var actual = Differ.generate(
                "src/test/resources/json/flat/file1.json",
                "src/test/resources/json/flat/file2.json"
        );
        var excepted = Files.readString(new File("src/test/resources/json/flat/result.txt").toPath());

        assertEquals(actual, excepted);
    }

}
