package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO implements UserIO {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readLine() throws IOException {
        return reader.readLine();
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
