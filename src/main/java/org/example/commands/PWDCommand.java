package org.example.commands;

import org.example.CLI;

public class PWDCommand {
    String execute() {
        return CLI.currentDirectory.toString();
    }
}
