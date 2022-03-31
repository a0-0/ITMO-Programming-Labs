package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.ProductCommand;
import exception.CommandExecutionException;
import model.Product;

public class InsertAtCommand extends AbstractCommand implements ProductCommand {
    private String[] args;
    private final ProductCollectionManager productManager;

    public InsertAtCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args.clone();
    }

    @Override
    public void execute() throws CommandExecutionException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(Product product) throws CommandExecutionException {
        try {
            if (args.length == 1) {
                productManager.insertAt(Integer.parseInt(args[0]), product);
            } else {
                throw new CommandExecutionException("expected 1 argument, got " + args.length);
            }
        }  catch (NumberFormatException nfe) {
            throw new CommandExecutionException("incorrect argument format");
        }
    }
}
