package org.example.commands;

import org.apache.commons.io.FileUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import static org.apache.commons.io.file.PathUtils.deleteDirectory;

public class MVCommand implements command {
    @Override
    public void execute(String[] args){
        try {
            Path sourcePath = Paths.get(args[0]);
            Path destinationPath = Paths.get(args[0]);
            if(Files.isDirectory(sourcePath)){
                FileUtils.copyDirectory(sourcePath.toFile(), destinationPath.toFile());
                deleteDirectory(sourcePath);
            }
            else{
                Files.copy(sourcePath, destinationPath);
                Files.delete(sourcePath);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
