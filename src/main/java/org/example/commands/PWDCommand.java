package org.example.commands;

import org.example.CLI;

import java.util.Optional;

public class PWDCommand implements Command {

    @Override
    public Optional<Object> execute(String[] args) {
        String path = CLI.currentDirectory.toString();
        return Optional.of(path);
    }
}
