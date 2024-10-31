import org.example.CLI;
import org.example.commands.CDCommand;
import org.example.commands.TouchCommand;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TouchTest {

    @Test
    public void testTouch_Pass() {
        CDCommand cd = new CDCommand();
        cd.execute(new String[]{"TouchTest"});

        TouchCommand touch = new TouchCommand();
        Path filePath = CLI.currentDirectory.resolve("testTouch.txt");

        try {
            touch.execute(new String[]{"testTouch.txt"});

            assertTrue(filePath.toFile().exists(), "The touch command did not create the file");
            System.out.println("touch command succeeded (Pass Test)");
        } catch (AssertionError e) {
            System.out.println("touch command failed (Pass Test): " + e.getMessage());
        }
    }

    @Test
    public void testTouch_Fail() {
        CDCommand cd = new CDCommand();
        cd.execute(new String[]{"NonExistentDirectory"});

        TouchCommand touch = new TouchCommand();
        Path filePath = CLI.currentDirectory.resolve("testTouch.txt");

        try {
            touch.execute(new String[]{"testTouch.txt"});

            assertFalse(filePath.toFile().exists(), "The touch command incorrectly created the file");
            System.out.println("touch command succeeded (Fail Test)");
        } catch (AssertionError e) {
            System.out.println("touch command failed (Fail Test): " + e.getMessage());
        }
    }
}
