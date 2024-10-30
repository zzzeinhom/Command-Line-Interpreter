package org.example.commands;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CATCommand implements Command {
    List<String> fileContent = new ArrayList<>();

    public void setFileContent(String[] args) {
        try {
            for(int i = 0; i < args.length; ++i){
                Path path = Paths.get(args[i]);
                File file = path.toFile();
                if (Files.isDirectory(path)) {
                    throw new Exception("Not a valid file path");
                }
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    fileContent.add(data);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
