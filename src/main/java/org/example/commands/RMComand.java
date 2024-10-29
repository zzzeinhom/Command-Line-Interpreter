package org.example.commands;
import org.example.CLI;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class RMComand implements command {

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("rm: missing file name");
            return;
        }
        Path file = CLI.currentDirectory.resolve(args[0]);
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            System.out.println("rm: failed to delete file " + args[0]);
        }
    }
}
