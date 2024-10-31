package org.example.commands;
import org.example.CLI;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class CDCommand extends Command {
    @Override
    public void execute(String[] args) {
        try {
            Path path = Paths.get(args[0]);
            if(Files.exists(path))
             CLI.currentDirectory = Paths.get(args[0]);
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("There is no such directory");
        }
    }
}
