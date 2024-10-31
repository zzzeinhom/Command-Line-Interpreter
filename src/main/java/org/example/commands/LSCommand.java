package org.example.commands;
import org.example.CLI;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.*;

public class LSCommand extends Command {

    List<String> ListFiles = new ArrayList<>();
    public List<String> getList(String[] args) {
        commandExecution(args);
        return ListFiles;
    }

    @Override
    public void execute(String[] args) {
        commandExecution(args);
        for (String file : ListFiles) {
            System.out.println(file);
        }

    }

    private void commandExecution(String[] args) {
        List<Path> files = new ArrayList<>();
        ListFiles.clear();
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
                ListFiles.add(file.getFileName().toString());
            }


        } catch (IOException e) {
            System.out.println("ls: failed to list directory contents");
        }
    }
}
