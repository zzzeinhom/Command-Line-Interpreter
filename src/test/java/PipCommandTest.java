import org.example.CLI;
import org.example.commands.*;
import org.junit.Test;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import static org.junit.Assert.*;

public class PipCommandTest
{
    private final PipCommand PipC = new PipCommand();
    private final CDCommand CdC = new CDCommand();
    private final MkdirCommand MkdirC = new MkdirCommand();

    @Test
    public void testLsToCat()
    {
        CdC.execute(new String[]{"CATTest"});
        String[] args = {"ls", "|", "cat"};

        PipC.execute(args);
    }

    @Test
    public void testPwdtoRmdir()
    {
        MkdirC.execute(new String[]{"test"});
        CdC.execute(new String[]{"./"});
        String[] args = {"pwd", "|", "rmdir"};

        PipC.execute(args);
    }

    @Test
    public void testPwdtoLs()
    {
        String[] args = {"pwd", "|", "ls"};

        PipC.execute(args);
    }
}
