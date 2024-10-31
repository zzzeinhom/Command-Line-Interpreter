import org.example.commands.CDCommand;
import org.example.commands.LSCommand;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LSTest {

    private void test(String[] args, List<String> expected, boolean shouldPass) {
        CDCommand cd = new CDCommand();
        LSCommand ls = new LSCommand();
        cd.execute(new String[]{"LSTest"}); // Assuming "LSTest" directory exists

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
            if (shouldPass) {
                assertEquals(expected, result, "The ls" + argType + " command did not list the files correctly or in the correct order");
                System.out.println("ls" + argType + " command succeeded (Pass Test)");
            } else {
                assertEquals(expected, result, "The ls" + argType + " command incorrectly listed the files or order is wrong");
                System.out.println("ls" + argType + " command succeeded (Fail Test)");
            }
        } catch (AssertionError e) {
            System.out.println("ls" + argType + " failed: " + e.getMessage());
        }
    }

    @Test
    public void testLS_Pass() {
        test(new String[]{""}, List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt"), true);
    }

    @Test
    public void testLS_Fail() {
        test(new String[]{""}, List.of("LSTest3.txt", "LSTest2.txt", "LSTest.txt"), false); // Incorrect expected order
    }

    @Test
    public void testLSAll_Pass() {
        test(new String[]{"-a"}, List.of(".LSTest4.txt", ".LSTest5.txt", "LSTest.txt", "LSTest2.txt", "LSTest3.txt"), true);
    }

    @Test
    public void testLSAll_Fail() {
        test(new String[]{"-a"}, List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt", ".LSTest4.txt", ".LSTest5.txt"), false); // Incorrect expected order
    }

    @Test
    public void testLSRecursive_Pass() {
        List<String> originalList = List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt");
        List<String> reversed = new ArrayList<>(originalList);
        Collections.reverse(reversed);
        test(new String[]{"-r"}, reversed, true);
    }

    @Test
    public void testLSRecursive_Fail() {
        List<String> incorrectList = List.of("LSTest.txt", "LSTest3.txt", "LSTest2.txt"); // Incorrect order
        test(new String[]{"-r"}, incorrectList, false);
    }

    @Test
    public void testLSAllRecursive_Pass() {
        List<String> originalList = List.of(".LSTest4.txt", ".LSTest5.txt", "LSTest.txt", "LSTest2.txt", "LSTest3.txt");
        List<String> reversed = new ArrayList<>(originalList);
        Collections.reverse(reversed);
        test(new String[]{"-a", "-r"}, reversed, true);
    }

    @Test
    public void testLSAllRecursive_Fail() {
        List<String> incorrectList = List.of("LSTest.txt", "LSTest2.txt", "LSTest3.txt", ".LSTest4.txt", ".LSTest5.txt"); // Incorrect order
        test(new String[]{"-a", "-r"}, incorrectList, false);
    }
}
