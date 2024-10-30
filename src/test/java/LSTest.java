import org.example.commands.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LSTest {
    private void test(String[] args, List<String> expected) {
        CDCommand cd = new CDCommand();
        LSCommand ls = new LSCommand();
        cd.execute(new String[]{"LSTest"});
        StringBuilder argType = new StringBuilder();
        for (String arg : args) {
            if (arg.equals("-a")) {
                argType.append(" -a");
            } else if (arg.equals("-r")) {
                argType.append(" -r");
            }
        }
        try {
            List<String> result = ls.getList(args);
            assertEquals(expected, result, "The ls" + argType
                    + " command did not list the files correctly or in the correct order");
            System.out.println("ls" + argType + " command succeeded");
        } catch (AssertionError e) {
            System.out.println("ls" + argType + " failed: " + e.getMessage());
        }

    }

    @Test
    public void testLS() {
        test(new String[]{""}, List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt"));
    }

    @Test
    public void testLSAll() {
        test(new String[]{"-a"}, List.of(".LSTest4.txt", ".LSTest5.txt", "LSTest.txt", "LSTest2.txt", "LSTest3.txt"));
    }

    @Test
    public void testLSRecursive() {
        List<String> originalList = List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt");
        List<String> reversed = new ArrayList<>(originalList);
        Collections.reverse(reversed);
        test(new String[]{"-r"}, reversed);
    }

    @Test
    public void testLSAllRecursive() {
        List<String> originalList = List.of(".LSTest4.txt", ".LSTest5.txt", "LSTest.txt", "LSTest2.txt", "LSTest3.txt");
        List<String> reversed = new ArrayList<>(originalList);
        Collections.reverse(reversed);
        test(new String[]{"-a", "-r"}, reversed);
    }
}
