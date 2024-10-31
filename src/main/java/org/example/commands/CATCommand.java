package org.example.commands;
import org.example.CLI;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CATCommand extends Command {
    List<String> fileContent = new ArrayList<>();

    public void setFileContent(String[] args) {
        fileContent.clear();
        try {
            for(int i = 0; i < args.length; ++i){
                Path path = Paths.get(args[i]);
                if(!path.isAbsolute()){
                    path = CLI.currentDirectory.resolve(path);
                }
                File file = path.toFile();
                Scanner reader = new Scanner(file.getName());
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    fileContent.add(data);
                }
                reader.close();
            }
        }
        catch (Exception e){
            System.out.println("There is no such directory");
        }
    }
    
    public List<String> getFileContent(String[] args){
        setFileContent(args);
        return fileContent;
    }

    @Override
    public void execute(String[] args) {
        setFileContent(args);
        for (String line: fileContent) {
            System.out.println(line);
        }
    }

}
