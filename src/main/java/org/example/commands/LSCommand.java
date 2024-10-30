package org.example.commands;
import org.example.CLI;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;

public class LSCommand implements Command {
    List<String> stringFiles = new ArrayList<>();;

    public Optional<Object> execute(String[] args) {
        List<Path> files = new ArrayList<>();
        boolean showHidden = false;
        boolean reverseOrder = false;

        for (String arg : args) {
            if (arg.equals("-a")) {
                showHidden = true;
            } else if (arg.equals("-r")) {
                reverseOrder = true;
            }
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CLI.currentDirectory)) {
            for (Path path : stream) {
                if (showHidden || !path.getFileName().toString().startsWith(".")) {
                    files.add(path);

                }
            }

            files.sort(Comparator.comparing(Path::getFileName));
            if (reverseOrder) {
                Collections.reverse(files);
            }


            for (Path file : files) {
                stringFiles.add(file.getFileName().toString());
            }
            System.out.println();

            return Optional.of(stringFiles);

        } catch (IOException e) {
            System.out.println("ls: failed to list directory contents");
            return Optional.empty();
        }
    }
}
