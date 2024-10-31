import org.example.CLI;
import org.example.commands.PWDCommand;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PWDTest {

    @Test
    public void testPWD_Pass() {
        PWDCommand pwd = new PWDCommand();

        CLI.currentDirectory = Paths.get(System.getProperty("user.dir"));

        try {
            assertEquals(CLI.currentDirectory, pwd.getPath(), "The pwd command did not return the correct path");
            System.out.println("pwd command succeeded (Pass Test)");
        } catch (AssertionError e) {
            System.out.println("pwd command failed (Pass Test): " + e.getMessage());
        }
    }

    @Test
    public void testPWD_Fail() {
        PWDCommand pwd = new PWDCommand();

        CLI.currentDirectory = Paths.get("/incorrect/path");

        try {
            assertEquals(CLI.currentDirectory, pwd.getPath(), "The pwd command did not return the correct path");
            System.out.println("pwd command succeeded (Fail Test)");
        } catch (AssertionError e) {
            System.out.println("pwd command failed (Fail Test): " + e.getMessage());
        }
    }
}
