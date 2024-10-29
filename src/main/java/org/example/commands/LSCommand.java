package org.example.commands;
import org.example.CLI;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LSCommand implements command {
    static List<String> files ;

    @Override
    public void execute(String[] args) {
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
                System.out.print(file.getFileName() + " ");
                this.files.add(file.getFileName().toString());
            }
            System.out.println();

        } catch (IOException e) {
            System.out.println("ls: failed to list directory contents");
        }
    }
}
