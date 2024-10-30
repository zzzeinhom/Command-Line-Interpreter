package org.example.commands;

import java.nio.file.*;

public class MVCommand implements Command {
    @Override
    public void execute(String[] args){
        try {
            Path sourcePath = Paths.get(args[0]);
            Path destinationPath = Paths.get(args[0]);
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
