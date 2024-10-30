// LsTest.java

import org.example.CLI;
import org.example.commands.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LSTest {
    public void test(String[] args, List<String> expected) {
        CDCommand cd = new CDCommand();
        LSCommand ls = new LSCommand();
        cd.execute(new String[]{"LSTest"});
        String argType = "";
        for (String arg : args) {
            if (arg.equals("-a")) {
                argType += " -a";
            } else if (arg.equals("-r")) {
                argType += " -r";
            }
        }
        try {
            List<String> result = ls.execute(args);
            assertEquals(expected, result, "The ls" + argType
                    + " command did not list the files correctly or in the correct order");
            System.out.println("ls" + argType + " command succeeded");
        } catch (AssertionError e) {
            System.out.println("ls" + argType + " failed: " + e.getMessage());
        }

    }

    @Test
    public void testLs() {
        test(new String[]{""}, List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt"));
    }

    @Test
    public void testLsAll() {
        test(new String[]{"-a"}, List.of(".LSTest4.txt", ".LSTest5.txt", "LSTest.txt", "LSTest2.txt", "LSTest3.txt"));
    }

    @Test
    public void testLsRecursive() {
        test(new String[]{"-r"}, List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt").reversed());
    }

    @Test
    public void testLsAllRecursive() {
        test(new String[]{"-a", "-r"}, List.of(".LSTest4.txt", ".LSTest5.txt", "LSTest.txt", "LSTest2.txt", "LSTest3.txt").reversed());
    }
}
