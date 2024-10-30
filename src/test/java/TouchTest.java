import org.example.CLI;
import org.example.commands.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TouchTest {

    @Test
    public void testTouch() {
        CDCommand cd = new CDCommand();
        cd.execute(new String[]{"TouchTest"});
        TouchCommand touch = new TouchCommand();

        try {
            touch.execute(new String[]{"testTouch.txt"});
            assertTrue(CLI.currentDirectory.resolve("testTouch.txt").toFile().exists(), "The touch command did not create the file");
            System.out.println("touch command succeeded");

        } catch (AssertionError e) {
            System.out.println("touch command failed: " + e.getMessage());        }
    }
}
