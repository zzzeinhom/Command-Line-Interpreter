//package org.example;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.nio.file.Paths;
//
//public class CLITest {
//    private CLI cli;
//
//    @BeforeEach
//    public void setUp() {
//        cli = new CLI();
//    }
//
//    private String[] parseArgs(String input) {
//        String[] parts = input.split("\\s+");
//        return parts.length > 1 ? java.util.Arrays.copyOfRange(parts, 1, parts.length) : new String[0];
//    }
//
//    @Test
//    public void testPwd() {
//        String result = cli.pwd();
//        assertEquals(Paths.get(System.getProperty("user.dir")).toString(), result, "The pwd command did not return the correct path");
//    }
//
//    @Test
//    public void testListFiles() {
//        String[] args = parseArgs("ls -a -r");
//        cli.listFiles(args);
//        assertTrue(true, "The ls command did not list the files");
//    }
//
//    @Test
//    public void testCreateFile() {
//        String[] args = parseArgs("touch testCreateFile.txt");
//        cli.createFile(args);
//        assertTrue(Paths.get(cli.pwd()).resolve("testCreateFile.txt").toFile().exists(), "The touch command did not create the file");
//    }
//
//    @Test
//    public void testRemoveFile() {
//        String[] createArgs = parseArgs("touch testRemoveFile.txt");
//        cli.createFile(createArgs);
//        String[] removeArgs = parseArgs("rm testRemoveFile.txt");
//        cli.removeFile(removeArgs);
//        assertFalse(Paths.get(cli.pwd()).resolve("testRemoveFile.txt").toFile().exists(), "The rm command did not delete the file");
//    }
//}