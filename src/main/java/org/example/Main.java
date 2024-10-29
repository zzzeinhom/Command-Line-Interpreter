package org.example;

public class Main {
    public static void main(String[] args) {
        CLITest cliTest = new CLITest();

        // Set up environment for tests
        cliTest.setUp();

        System.out.println("Testing CLI...");

        // Run each test manually
        try {
            cliTest.testPwd();
            System.out.println("testPwd succeeded");
        } catch (AssertionError e) {
            System.out.println("testPwd failed: " + e.getMessage());
        }



        try {
            cliTest.testListFiles();
            System.out.println("testListFiles succeeded");
        } catch (AssertionError e) {
            System.out.println("testListFiles failed: " + e.getMessage());
        }


        try {
            cliTest.testCreateFile();
            System.out.println("testCreateFile succeeded");
        } catch (AssertionError e) {
            System.out.println("testCreateFile failed: " + e.getMessage());
        }


        try {
            cliTest.testRemoveFile();
            System.out.println("testRemoveFile succeeded");
        } catch (AssertionError e) {
            System.out.println("testRemoveFile failed: " + e.getMessage());
        }

        System.out.println("CLI tested successfully.");


        CLI cli = new CLI();
        cli.start();
    }
}
