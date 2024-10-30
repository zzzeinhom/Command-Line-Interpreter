package org.example.commands;

import java.io.IOException;
import java.nio.file.*;

public class RmdirCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No directory path provided.");
            return;
        }

        Path dirPath = Paths.get(args[0]);

        if (!Files.exists(dirPath)) {
            System.out.println("Error: Directory does not exist - " + dirPath);
            return;
        }

        if (!Files.isDirectory(dirPath)) {
            System.out.println("Error: The specified path is not a directory - " + dirPath);
            return;
        }

        try {
            deleteDirectory(dirPath);
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
