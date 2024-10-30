package org.example.commands;
import org.example.CLI;

import java.nio.file.Paths;
import java.util.Optional;

public class CDCommand implements Command {
    @Override
    public Optional<Object> execute(String[] args) {
        try {
            CLI.currentDirectory = Paths.get(args[0]);
            return Optional.empty();

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("There is no such directory");
            return Optional.empty();
        }
    }
}
