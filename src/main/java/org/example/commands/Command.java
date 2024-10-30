package org.example.commands;

import java.util.Optional;

public interface Command {
    Optional<Object> execute(String[] args);
}