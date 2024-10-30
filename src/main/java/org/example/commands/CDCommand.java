package org.example.commands;
import org.example.CLI;
import java.nio.file.Paths;
import java.util.Optional;

public class CDCommand implements Command {
    @Override
    public void execute(String[] args) {
        try {
            CLI.currentDirectory = Paths.get(args[0]);

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("There is no such directory");
        }
    }
}
