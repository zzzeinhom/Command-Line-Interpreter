package org.example.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class AppendCommand implements Command {
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: No file name or text provided.");
            return;
        }

        String fileName = args[0];  // First argument is the filename
        String textToAppend = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

        try {
            // Get the absolute path of the file
            Path filePath = Paths.get(fileName).toAbsolutePath().normalize();

            // Create the parent directories if they do not exist
            if (Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            // Create the file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            // Append text to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
                writer.write(textToAppend);
                writer.newLine(); // Add a new line after the appended text
            }

            System.out.println("Text appended to file: " + filePath);
        } catch (InvalidPathException e) {
            System.out.println("Error: File name contains invalid characters.");
        } catch (IOException e) {
            System.out.println("Error: Unable to append text due to an I/O error.");
//            e.printStackTrace();
        } catch (SecurityException e) {
            System.out.println("Error: Permission denied to write to the file.");
        }

        return;
    }
}
