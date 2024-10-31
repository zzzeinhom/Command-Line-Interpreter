package org.example.commands;

import org.example.CLI;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class AppendCommand extends Command {

    @Override
    public void execute(String[] args) {
        if(args.length < 3)
        {
            System.out.println("Error: Arguments should be: <command> > <file>");
            return;
        }

        String[] commandArgs = Arrays.copyOfRange(args,0, args.length - 2);
        String targetFile = args[args.length - 1];

        String output = runCommand(commandArgs);

        try
        {
            Path path = Paths.get(CLI.currentDirectory.toString(), targetFile).normalize();
            assert output != null;
            Files.writeString(path, output, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Output successfully appended to: " + targetFile);
        }
        catch (IOException e)
        {
            System.out.println("Error appending to file: " + targetFile);
            return;
        }
    }

    private String runCommand (String[] commandArgs)
    {
        if(commandArgs.length == 0)
        {
            System.out.println("Error: No command Provided");
            return null;
        }

        PrintStream originalOutput = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream tempOutput = new PrintStream(outputStream);

        try
        {
            System.setOut(tempOutput);

            Command command = getCommand(commandArgs);
            if(command != null)
            {
                command.execute(Arrays.copyOfRange(commandArgs, 1, commandArgs.length));
            }
            else
            {
                System.out.println("Error: Command not found.");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error executing the command - Try help()");
        }

        finally
        {
            System.setOut(originalOutput);
        }

        return outputStream.toString(StandardCharsets.UTF_8);
    }

    private Command getCommand (String[] commandArgs)
    {
        if(commandArgs.length == 0)
        {
            System.out.println("Error: No command Provided");
            return null;
        }

        switch (commandArgs[0]){
            case "pwd":
                return new PWDCommand();
            case "cat":
                return new CATCommand();
            case "cd":
                return new CDCommand();
            case "ls":
                return new LSCommand();
            case "mv":
                return new MVCommand();
            case "rm":
                return new RMCommand();
            case "touch":
                return new TouchCommand();
            case "mkdir":
                return new MkdirCommand();
            case "rmdir":
                return new RmdirCommand();
            default:
                System.out.println("Error: Unknown Command " + commandArgs[0] + " - Try help()");
                return null;
        }
    }
}
