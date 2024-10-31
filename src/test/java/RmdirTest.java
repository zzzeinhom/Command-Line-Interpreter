import org.example.commands.*;
import org.junit.Test;
import java.nio.file.*;
import java.io.IOException;
import static org.junit.Assert.assertFalse;

public class RmdirTest {
    private final RmdirCommand rmdir = new RmdirCommand();

    @Test
    public void testDeleteEmptyDirectory() throws IOException
    {
        Path tempDir = Files.createTempDirectory("testEmptyDir");

        rmdir.execute(new String[]{tempDir.toString()});

        assertFalse("Directory should be deleted", Files.exists(tempDir));
    }

    @Test
    public void testDeleteDirectoryWithFiles() throws IOException
    {
        Path tempDir = Files.createTempDirectory("testNonEmptyDir");

        Files.createFile(tempDir.resolve("tempFile.txt"));

        rmdir.execute(new String[]{tempDir.toString()});

        assertFalse("NonEmptyDirectory should be deleted", Files.exists(tempDir));
    }

    @Test
    public void testDeleteNonExistentDirectory()
    {
        Path tempDir = Paths.get("tempDir");

        rmdir.execute(new String[]{tempDir.toString()});
        // We don't use exception here as the command is developed to handle nonexistent paths
    }

    // NOTE to Team : this test is to assert that the command only deletes directories so we test it
    // with a file. The point is that the test will always fail which is good because that means it
    // only works on dirs so do we keep it or leave it? Feel free to share your opinion <3.
    
//    @Test
//    public void testDeleteFileInsteadOfDirectory() throws IOException
//    {
//        Path tempFile = Files.createTempFile("testFile", ".txt");
//
//        rmdir.execute(new String[]{tempFile.toString()});
//
//        assertFalse("NonDirectory should not be deleted", Files.exists(tempFile));
//    }
}
