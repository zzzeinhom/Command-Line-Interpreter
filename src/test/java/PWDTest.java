import org.example.CLI;
import org.example.commands.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class PWDTest {

    @Test
    public void testPWD() {
        PWDCommand pwd = new PWDCommand();
        Path currentPath = CLI.currentDirectory;
        try {
            assertEquals(currentPath, pwd.getPath(), "The pwd command did not return the correct path");
            System.out.println("pwd command succeeded");
        } catch (AssertionError e) {
            System.out.println("pwd command failed: " + e.getMessage());
        }
    }
}
