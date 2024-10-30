import org.example.CLI;
import org.example.commands.CATCommand;
import org.example.commands.CDCommand;
import org.example.commands.PWDCommand;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class CATTest {
    @Test
    public void displayFileContent(){
        CATCommand cat = new CATCommand();
        List<String> content = cat.getFileContent(new String[]{"CATTest\\Test1.txt"});
        List<String> target_content = List.of(new String[]{"Test 1"});
        assertEquals(target_content,content);
    }
    @Test
    public void displayMultipleFileContent(){
        CATCommand cat = new CATCommand();
        List<String> content = cat.getFileContent(new String[]{"CATTest\\Test1.txt", "CATTest\\Test2.txt", "CATTest\\Test3.txt"});
        List<String> target_content = List.of(new String[]{"Test 1", "Test 2", "Test 3"});
        assertEquals(target_content,content);
    }
}
