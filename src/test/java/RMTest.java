import org.example.CLI;
import org.example.commands.CDCommand;
import org.example.commands.RMCommand;
import org.example.commands.TouchCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RMTest {

    @Test
    public void testRM() {
        CDCommand cd = new CDCommand();
        cd.execute(new String[] {"RMTest"});
        TouchCommand touch = new TouchCommand();
        touch.execute(new String[] {"testRM.txt"});
        RMCommand rm = new RMCommand();
        try {
            rm.execute(new String[] {"testRM.txt"});
            assertFalse(CLI.currentDirectory.resolve("testRM.txt").toFile().exists(), "The rm command did not delete the file");
            System.out.println("rm command succeeded");
        } catch (AssertionError e) {
            System.out.println("rm command failed: " + e.getMessage());
        }
    }
}
