package org.example.commands;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;
import java.util.Optional;

public class MkdirCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Error: No directory name provided.");
        }

        try {
            Path dirPath = Paths.get(args[0]).toAbsolutePath().normalize();

            // Check if directory path points to an existing directory
            if (Files.isDirectory(dirPath)) {
                System.out.println("Directory already exists: " + dirPath);
            }

            if (Files.exists(dirPath) && !Files.isDirectory(dirPath)) {
                System.out.println("Error: A file with the same name already exists.");
            }

            Files.createDirectories(dirPath);
            System.out.println("Directory created: " + dirPath);
        } catch (InvalidPathException e) {
            System.out.println("Error: Directory name contains invalid characters.");
        } catch (FileAlreadyExistsException e) {
            System.out.println("Error: Directory already exists.");
        } catch (IOException e) {
            System.out.println("Error: Unable to create directory due to an I/O error.");
            e.printStackTrace();
        } catch (SecurityException e) {
            System.out.println("Error: Permission denied to create directory.");
        }

    }
}
