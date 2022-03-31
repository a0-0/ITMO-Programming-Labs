package main;

import command.CommandInvoker;
import exception.InvalidArgumentException;
import io.ConsoleIO;
import io.UserIO;

import java.io.IOException;

/**
 * Main application class
 */
public class Application {
    CommandInvoker commandInvoker;
    UserIO userIO;
    private static boolean isRunning = true;
    private static String fileName;

    public Application() {}

    public static void setIsRunning(boolean isRunning) {
        Application.isRunning = isRunning;
    }

    public static String getFileName() {
        return fileName;
    }

    public void start(String fileName) {
        Application.fileName = fileName;
        userIO = new ConsoleIO();
        ProductCollectionManager productManager = new ProductLinkedListManager(userIO);
        ProductWriter productWriter = new XmlProductWriter(productManager, fileName);
        ProductReader productReader = new XmlProductReader(productManager, fileName);
        commandInvoker = new CommandInvoker(productManager, userIO, productWriter);
        commandInvoker.putCommands();
        try {
            productManager.addProducts(productReader.read());
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        loop();
    }

    /**
     * Application loop
     */
    private void loop() {
        while(isRunning) {
            try {
                userIO.printUserPrompt();
                String str = userIO.readLine();
                commandInvoker.execute(str);
            } catch (IllegalStateException | IOException ise) {
                userIO.printErrorMessage(ise.getMessage());
            }
        }
    }
}
