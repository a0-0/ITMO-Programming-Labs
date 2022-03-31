package io;

import java.io.IOException;

public interface UserIO {
    String readLine() throws IOException;
    void printLine(String line);
    void printErrorMessage(String line);
    void printUserPrompt();
}
