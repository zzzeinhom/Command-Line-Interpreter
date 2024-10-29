package org.example;

import java.nio.file.*;
import java.util.*;

public class CLI {
    public static Path currentDirectory;

    public CLI() {
        currentDirectory = Paths.get(System.getProperty("user.dir"));
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our CLI system! Type 'help' for a list of commands.");

        while (true) {
            System.out.print(currentDirectory + "> ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");
            String command = parts[0];
            String[] args = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0];

            switch (command) {
                case "exit":
                    if (exit()) return;
                    break;
                case "help":
                    System.out.println(help());
                    break;
                case "pwd":
                    System.out.println(pwd());
                    break;
                case "cd":
                    changeDirectory(args);
                    break;
                case "ls":
                    listFiles(args);
                    break;
                case "mkdir":
                    createDirectory(args);
                    break;
                case "rmdir":
                    removeDirectory(args);
                    break;
                case "touch":
                    createFile(args);
                    break;
                case "mv":
                    moveFile(args);
                    break;
                case "rm":
                    removeFile(args);
                    break;
                case "cat":
                    cat(args);  // Placeholder for 'cat' command implementation
                    break;
                case ">":
                    redirectOutput(args);  // Placeholder for output redirection implementation
                    break;
                case ">>":
                    appendOutput(args);  // Placeholder for output append implementation
                    break;
                case "|":
                    pipeOutput(args);  // Placeholder for piping implementation
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        }
    }




    public String pwd() {
        return currentDirectory.toString();
    }

    public void changeDirectory(String[] args) {
        // TODO: Implement this
    }

    public void listFiles(String[] args) {
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

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory)) {
            for (Path path : stream) {
                if (showHidden || !path.getFileName().toString().startsWith(".")) {
                    files.add(path);
                }
            }

            files.sort(Comparator.comparing(Path::getFileName));
            if (reverseOrder) {
                Collections.reverse(files);
            }

            for (Path entry : files) {
                System.out.print(entry.getFileName() + " ");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("ls: failed to list directory contents");
        }
    }

    public void createDirectory(String[] args) {
        // TODO: Implement this
    }

    public void removeDirectory(String[] args) {
        // TODO: Implement this
    }

    public void createFile(String[] args) {
        if (args.length == 0) {
            System.out.println("touch: missing file name");
            return;
        }
        Path file = currentDirectory.resolve(args[0]);
        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            System.out.println("touch: file already exists " + args[0]);
        } catch (Exception e) {
            System.out.println("touch: cannot create file " + args[0]);
        }
    }

    public void moveFile(String[] args) {
        // TODO: Implement this
    }

    public void removeFile(String[] args) {
        if (args.length == 0) {
            System.out.println("rm: missing file name");
            return;
        }
        Path file = currentDirectory.resolve(args[0]);
        try {
            Files.deleteIfExists(file);
        } catch (Exception e) {
            System.out.println("rm: failed to delete file " + args[0]);
        }
    }

    private void pipeOutput(String[] args) {
        // TODO: Implement this
    }

    private void appendOutput(String[] args) {
        // TODO: Implement this
    }

    private void redirectOutput(String[] args) {
        // TODO: Implement this
    }

    private void cat(String[] args) {
        // TODO: Implement this
    }

    public String help() {
        return """
            Available commands:
            pwd               : Print the current working directory
            cd <directory>    : Change the current directory
            ls [-a] [-r]      : List directory contents
                                -a for all files including hidden files
                                -r for reverse order
            mkdir <directory> : Create a new directory
            rmdir <directory> : Remove a directory
            touch <file>      : Create an empty file
            mv <src> <dest>   : Move or rename a file
            rm <file>         : Remove a file
            cat <file>        : Display the contents of a file
            > <file>          : Redirect output to a file (create/overwrite)
            >> <file>         : Redirect output to a file (append)
            | <command>       : Pipe output to another command
            help              : Display this help message
            exit              : Exit the CLI
            """;
    }

    public boolean exit() {
        System.out.println("Exiting CLI.");
        return true;
    }
}
