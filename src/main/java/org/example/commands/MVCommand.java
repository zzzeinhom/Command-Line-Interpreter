package org.example.commands;

import java.nio.file.*;

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
            else{
                if (Files.isDirectory(destinationPath))
                    Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                else throw new Exception("There is no such directory");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
