package commands;

import exception.CommandExecutionException;
import model.Product;

public interface ProductCommand extends Command {
    void execute(Product product) throws CommandExecutionException;
}
