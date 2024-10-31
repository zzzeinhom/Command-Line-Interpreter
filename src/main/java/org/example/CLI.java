package org.example;

import org.example.commands.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {

    public static Path currentDirectory;
    private Map<String, Command> commands;

    public CLI() {
        // Initialize the command map with command objects
        currentDirectory = Paths.get(System.getProperty("user.dir"));
        commands = new HashMap<>();
        commands.put("pwd", new PWDCommand());
        commands.put("cd", new CDCommand());
        commands.put("ls", new LSCommand());
        commands.put("mkdir", new MkdirCommand());
        commands.put("rmdir", new RmdirCommand());
        commands.put("touch", new TouchCommand());
        commands.put("cat", new CATCommand());
        commands.put("append", new AppendCommand());
        commands.put("mv", new MVCommand());
        commands.put("rm", new RMCommand());
        //TODO: add more commands
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the CLI! Type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();


            // Check for exit command
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting CLI...");
                break;
            }

            // Check for help command
            if (input.equalsIgnoreCase("help")) {
                System.out.println(help());
                continue;
            }

            // Parse the input
            String[] parts = StringSplitter.split(input);
            String commandName = parts[0];
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);

            // Execute the command if it exists
            Command command = commands.get(commandName);
            if (command != null) {
                command.execute(args);
            } else {
                System.out.println("Unknown command: " + commandName);
            }
        }

        scanner.close();
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

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.start();
    }
}
