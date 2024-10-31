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
        try {
            if (CLI.currentDirectory == null) {
                throw new IllegalStateException("Error: Current directory is not set.");
            }

            path = CLI.currentDirectory.toAbsolutePath().normalize();
            System.out.println(path);
        } catch (Exception e) {
            System.out.println("An error occurred while executing the PWD command: " + e.getMessage());
        }
    }

    public String getPath() {
        execute(null);
        return path != null ? path.toString() : "Error: Path could not be retrieved.";
    }
}
