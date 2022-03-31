package io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO implements UserIO {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Console console = System.console();

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public String readPasswordLine() throws IOException{
        if (console == null) {
            return readLine();
        }
        char[] passChar = console.readPassword();
        if (passChar == null) {
            throw new IOException();
        }
        return new String(passChar);
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printErrorMessage(String line) {
        System.err.println(line);
    }

    public void printUserPrompt() {
        System.out.print(">");
    }
}
