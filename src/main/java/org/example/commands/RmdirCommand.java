package org.example.commands;

import org.example.CLI;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

public class RmdirCommand extends Command
{
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No directory path provided.");
            return;
        }

        String input = String.join(" ", args);
        Path dirPath = Paths.get(input).toAbsolutePath().normalize();

        if (!Files.exists(dirPath)) {
            System.out.println("Error: Directory does not exist - " + dirPath);
            return;
        }

        if (!Files.isDirectory(dirPath)) {
            System.out.println("Error: The specified path is not a directory - " + dirPath);
            return;
        }

        try {
            Path parent = dirPath.getParent();
            deleteDirectory(dirPath);
            CLI.currentDirectory = parent;
            System.out.println("Directory deleted successfully: " + dirPath);
        } catch (IOException e) {
            System.out.println("Error deleting directory: " + e.getMessage());
        }
    }

    private void deleteDirectory(Path path) throws IOException
    {
        if(Files.isDirectory(path))
        {
            try(DirectoryStream<Path> entries = Files.newDirectoryStream(path))
            {
                for(Path entry : entries)
                {
                    deleteDirectory(entry);
                }
            }
        }
        Files.delete(path);
    }
}
