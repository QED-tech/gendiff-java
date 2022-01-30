package hexlet.code.Differ;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class DifferTest {

    /**
     * @param file1 String
     * @param file2 String
     * @param resultFile String
     * @throws Exception
     */
    @ParameterizedTest
    @MethodSource("dataProvider")
    void testDiffer(String file1, String file2, String resultFile) throws Exception {
        var actual = Differ.generate(
                file1,
                file2
        );
        var excepted = Files.readString(new File(resultFile).toPath());

        assertEquals(actual, excepted);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments(
                        "src/test/resources/json/flat/file1.json",
                        "src/test/resources/json/flat/file2.json",
                        "src/test/resources/json/flat/result.txt"
                ),
                arguments(
                        "src/test/resources/yml/flat/file1.yml",
                        "src/test/resources/yml/flat/file2.yml",
                        "src/test/resources/yml/flat/result.txt"
                )
        );
    }

}
