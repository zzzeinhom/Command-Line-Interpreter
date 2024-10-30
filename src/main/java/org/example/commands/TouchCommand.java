package org.example.commands;
import org.example.CLI;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Optional;

public class TouchCommand implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("touch: missing file name");
        }
        Path file = CLI.currentDirectory.resolve(args[0]);
        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            System.out.println("touch: file already exists " + args[0]);
        } catch (IOException e) {
            System.out.println("touch: cannot create file " + args[0]);
        }
    }
}
