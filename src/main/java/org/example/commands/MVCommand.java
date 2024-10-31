package org.example.commands;

import java.nio.file.*;
import java.util.List;

public class MVCommand extends Command {
    @Override
    public void execute(String[] args){
        try {
            Path sourcePath = Paths.get(args[0]);
            Path destinationPath = Paths.get(args[1]);
            if(Files.isRegularFile(sourcePath)){
                if(Files.isDirectory(destinationPath)){
                    // Add the file name to the end of destination path.
                    Files.move(sourcePath, destinationPath.resolve(sourcePath.getFileName()));
                }
                else{
                    Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        catch (Exception e){
            System.out.println("There is not directory or file");
        }
    }
}
