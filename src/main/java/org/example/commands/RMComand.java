package org.example.commands;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class RMComand implements command {
    final Path currentDirectory = Paths.get(System.getProperty("user.dir"));

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("rm: missing file name");
            return;
        }
        Path file = currentDirectory.resolve(args[0]);
        try {
            Files.deleteIfExists(file);
        } catch (Exception e) {
            System.out.println("rm: failed to delete file " + args[0]);
        }
    }
}
