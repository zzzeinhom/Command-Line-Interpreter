package org.example.commands;

import static org.apache.commons.io.file.PathUtils.deleteDirectory;
import java.nio.file.*;
import java.util.Optional;

public class MVCommand implements Command {
    @Override
    public Optional<Object> execute(String[] args){
        try {
            Path sourcePath = Paths.get(args[0]);
            Path destinationPath = Paths.get(args[0]);
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
