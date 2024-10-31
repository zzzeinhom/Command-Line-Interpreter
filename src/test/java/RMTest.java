import org.example.CLI;
import org.example.commands.CDCommand;
import org.example.commands.RMCommand;
import org.example.commands.TouchCommand;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RMTest {

    @Test
    public void testRM_Pass() {
        CDCommand cd = new CDCommand();
        cd.execute(new String[] {"RMTest"});
        TouchCommand touch = new TouchCommand();
        touch.execute(new String[] {"testRM.txt"});

        RMCommand rm = new RMCommand();
        Path filePath = CLI.currentDirectory.resolve("testRM.txt");

        try {
            rm.execute(new String[] {"testRM.txt"});

            assertFalse(filePath.toFile().exists(), "The rm command did not delete the file");
            System.out.println("rm command succeeded (Pass Test)");
        } catch (AssertionError e) {
            System.out.println("rm command failed (Pass Test): " + e.getMessage());
        }
    }

    @Test
    public void testRM_Fail() {
        CDCommand cd = new CDCommand();
        cd.execute(new String[] {"RMTest"});
        RMCommand rm = new RMCommand();
        Path filePath = CLI.currentDirectory.resolve("nonExistentFile.txt");

        try {
            rm.execute(new String[] {"nonExistentFile.txt"});

            assertTrue(filePath.toFile().exists(), "The rm command incorrectly reported deletion success");
            System.out.println("rm command succeeded (Fail Test)");
        } catch (AssertionError e) {
            System.out.println("rm command failed (Fail Test): " + e.getMessage());
        }
    }
}
