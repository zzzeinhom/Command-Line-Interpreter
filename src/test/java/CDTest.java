import org.example.CLI;
import org.example.commands.CDCommand;
import org.example.commands.PWDCommand;
import org.junit.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
public class CDTest {
    @Test
    public void validDirectoryNavigation(){
        CDCommand cd = new CDCommand();
        cd.execute(new String[] {"C:\\Users"});
        assertEquals("C:\\Users", CLI.currentDirectory.toString());
    }
}