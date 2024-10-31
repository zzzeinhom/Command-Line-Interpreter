package org.example.commands;

import org.example.CLI;

import java.nio.file.Path;
import java.util.Optional;

public class PWDCommand extends Command {
    private Path path;

    @Override
    public void execute(String[] args) {
        commandExecution();
    }
    private void commandExecution() {
        path = CLI.currentDirectory.toAbsolutePath().normalize();
        System.out.println(path);
    }

    public String getPath() {
        execute(null);
        return path.toString();
    }
}
