package org.example.commands;

import org.example.CLI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CATCommand extends Command {
    List<String> fileContent = new ArrayList<>();

    public void setFileContent(String[] args) {
        fileContent.clear();
        for (String arg : args) {
            try {
                Path path = Paths.get(arg);

                if (!path.isAbsolute()) {
                    path = CLI.currentDirectory.resolve(path).normalize();
                }

                File file = path.toFile();

                // Process directories by reading all files inside recursively
                if (file.isDirectory()) {
                    readDirectory(file);
                } else {
                    readFile(file);
                }

            } catch (IOException e) {
                System.out.println("I/O Error while reading file '" + arg + "': " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error while processing file '" + arg + "': " + e.getMessage());
            }
        }
    }

    private void readDirectory(File directory) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    readDirectory(file); // Recursively read subdirectories
                } else {
                    readFile(file);
                }
            }
        }
    }

    private void readFile(File file) throws IOException {
        if (!file.canRead()) {
            System.out.println("Error: File '" + file.getPath() + "' is not readable.");
            return;
        }

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                fileContent.add(data);
            }
        }
    }

    public List<String> getFileContent(String[] args) {
        setFileContent(args);
        return fileContent;
    }

    @Override
    public void execute(String[] args) {
        setFileContent(args);
        for (String line : fileContent) {
            System.out.println(line);
        }
    }
}
