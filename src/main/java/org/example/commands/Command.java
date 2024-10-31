package org.example.commands;

public abstract class Command {

    public abstract void execute(String[] args);
    public abstract String returnResult(String[] args);
    public abstract void executeWithInput( String[] args, String input );


}
