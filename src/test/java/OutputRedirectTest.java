import org.example.CLI;
import org.example.commands.*;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import static org.junit.Assert.*;

public class OutputRedirectTest {
    private final OutputRedirectCommand ORC = new OutputRedirectCommand();

    @Test
    public void testRedirectToNonExistentFile() throws IOException {
        String[] args = {"pwd", ">", "testOutput.txt"};
        Path targetFile = Paths.get("testOutput.txt");

        ORC.execute(args);

        assertTrue(Files.exists(targetFile));
        String content = Files.readString(targetFile, StandardCharsets.UTF_8);
        Path pathDir = Paths.get("").toAbsolutePath().normalize();
        assertTrue(content.contains(pathDir.toString()));

        Files.deleteIfExists(targetFile);
    }

    @Test
    public void testInvalidPath() {
        String[] args = {"pwd", ">", "/invalid_path/output.txt"};

        ORC.execute(args);
    }

    @Test
    public void testEmptyCommand() {
        String[] args = {">", "testOutput.txt"};

        ORC.execute(args);

        Path targetFile = Paths.get("testOutput.txt");
        assertFalse(Files.exists(targetFile));
    }
}
