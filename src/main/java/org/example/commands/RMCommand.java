package org.example.commands;
import org.example.CLI;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Optional;

public class RMCommand implements Command {

    @Override
    public Optional<Object> execute(String[] args) {
        if (args.length == 0) {
            System.out.println("rm: missing file name");
            return Optional.empty();
        }
        Path file = CLI.currentDirectory.resolve(args[0]);
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            System.out.println("rm: failed to delete file " + args[0]);
        }
        return Optional.empty();
    }
}
