package org.example.commands;

import org.example.CLI;

import java.nio.file.Path;
import java.util.Optional;

public class PWDCommand implements Command {
    private Path path = CLI.currentDirectory;

    @Override
    public void execute(String[] args) {
        commandExecution(args);
    }
    private void commandExecution(String[] args) {
        System.out.println(path.toString());
    }

    public Path getPath() {
        return path;
    }
}
