package org.example.commands;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class MkdirCommand implements command {
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: No directory name provided.");
            return;
        }

        Path dirPath = Paths.get(args[0]);

        try {
            Files.createDirectories(dirPath);
            System.out.println("Directory created: " + dirPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error: Unable to create directory.");
            e.printStackTrace();
        }

    }
}
