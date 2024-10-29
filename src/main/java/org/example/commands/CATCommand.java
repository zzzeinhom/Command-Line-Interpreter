package org.example.commands;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.util.Scanner;

public class CATCommand implements command{

    @Override
    public void execute(String[] args) {
        try {
            Path path = Paths.get(args[0]);
            if(Files.isDirectory(path)){
              throw new Exception("Not a valid file path");
            }
            Scanner reader = new Scanner(path.toFile());
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
