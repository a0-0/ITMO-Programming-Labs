package command;

import io.UserIO;
import main.*;

import java.io.BufferedReader;
import java.util.*;

/**
 * Class, which selects and invokes command for execution
 */
public class CommandInvoker {

    Stack<String> history = new Stack<>();

    Map<String, Command> commands = new HashMap<>();

    static Set<String> scripts = new HashSet<>();

    ProductCollectionManager productManager;

    BufferedReader reader;

    UserIO userIO;

    ProductWriter productWriter;

    public CommandInvoker(ProductCollectionManager productManager, UserIO userIO, ProductWriter productWriter) {
        this.productManager = productManager;
        this.userIO = userIO;
        this.productWriter = productWriter;
        putCommands();
    }

    public CommandInvoker(ProductCollectionManager productManager, BufferedReader reader) {
        this.productManager = productManager;
        this.reader = reader;
        putCommands();
    }

    public void putCommands() {
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("info", new InfoCommand(productManager));
        commands.put("clear", new ClearCommand(productManager));
        commands.put("save", new SaveCommand(productWriter));
        commands.put("show", new ShowCommand(productManager));
        commands.put("remove_by_id", new RemoveByIdCommand(productManager));
        commands.put("history", new HistoryCommand(history));
        commands.put("add", new AddCommand(productManager, reader, userIO));
        commands.put("execute_script", new ExecuteScriptCommand(productManager));
        commands.put("update", new UpdateCommand(productManager, reader, userIO));
        commands.put("insert_at", new InsertAtCommand(productManager, reader, userIO));
        commands.put("min_by_manufacturer", new MinByManufacturerCommand(productManager));
        commands.put("print_field_ascending_manufacturer", new PrintFieldAscendingManufacturerCommand(productManager));
        commands.put("remove_any_by_unit_of_measure", new RemoveAnyByUnitOfMeasureCommand(productManager));
        commands.put("sort", new SortCommand(productManager));
        commands.put("remove_greater", new RemoveGreaterCommand(productManager, reader, userIO));
    }

    /**
     * Add file name of the script into set for anti-recursive
     * @param name - file name
     */
    public static void addScript(String name) {
        scripts.add(name);
    }

    /**
     * Remove file name of the script from set for anti-recursive
     * @param name - file name
     */
    public static void removeScript(String name) {
        scripts.remove(name);
    }


    /**
     * Push last executed command into stack
     * @param command - command name
     */
    public void pushHistory(String command) {
        if(history.size() > 11) {
            history.remove(history.size() - 1);
        }
        history.push(command.toLowerCase(Locale.ROOT));
    }


    /**
     * Execute command
     * @param inputString - name of the command
     */
    public void execute(String inputString) {
        if (inputString == null) {
            System.err.println("eof detected, terminating the program");
            Application.setIsRunning(false);
            return;
        }
        Command command;
        String[] split = inputString.trim().split("\\s+");
        String[] args = Arrays.copyOfRange(split, 1, split.length);

        if(commands.containsKey(split[0].toLowerCase(Locale.ROOT).trim())) {
            command = commands.get(split[0].toLowerCase(Locale.ROOT).trim());
            command.setArgs(args);
            pushHistory(inputString);
            command.execute();
        } else {
            if (split[0].equals("")) {
                throw new IllegalStateException("unknown command");
            } else {
                throw new IllegalStateException("unknown command: " + split[0]);
            }
        }
    }
}
