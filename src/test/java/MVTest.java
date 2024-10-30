import org.example.CLI;
import org.example.commands.CDCommand;
import org.example.commands.MVCommand;
import org.example.commands.PWDCommand;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
public class MVTest {
    @Test
    public void moveFile() {
        MVCommand mv = new MVCommand();
        Path src_path = Path.of("F:\\belal\\Projects\\Java\\OS assign #1\\MVTestSrc");
        Path dest_path = Path.of("F:\\belal\\Projects\\Java\\OS assign #1\\MVTestDest");
        mv.execute(new String[]{src_path.toString(),dest_path.toString()});
        assertTrue(Files.exists(dest_path),"This file exists");
    }
}
