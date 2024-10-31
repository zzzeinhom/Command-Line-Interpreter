import org.example.commands.MkdirCommand;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
// This is testing for Mkdir Command in our CLI
/* Decsription:
*
*   The Mkdir takes a file path as an argument and creates a directory
*
* */
public class MkdirTest {
    private final MkdirCommand mkdir = new MkdirCommand();

    @Test
    // Testing the creation of a directory
    public void testCreateNewDirectory() throws IOException {
        Path tempDir = Files.createTempDirectory("testDir"); // temp dirctory creating
        // This method usually creates a folder in the temp in C:users

        Path newDir = tempDir.resolve("newDir"); // here we create a new path to subdirectory
        // inside temp directory

        mkdir.execute(new String[]{newDir.toString()});

        assertTrue("Directory should be created", Files.isDirectory(newDir));

        Files.deleteIfExists(newDir);
        Files.deleteIfExists(tempDir);
    }

    @Test
    public void testDirectoryAlreadyExists() throws IOException {
        Path tempDir = Files.createTempDirectory("testExistingDir");

        mkdir.execute(new String[]{tempDir.toString()});

        assertTrue("Directory should exist", Files.isDirectory(tempDir));

        Files.deleteIfExists(tempDir);
    }

}
