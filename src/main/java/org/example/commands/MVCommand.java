package org.example.commands;

import org.apache.commons.io.FileUtils;
import java.nio.file.DirectoryStream;
import static org.apache.commons.io.file.PathUtils.deleteDirectory;
import java.nio.file.*;
public class MVCommand implements command {
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
