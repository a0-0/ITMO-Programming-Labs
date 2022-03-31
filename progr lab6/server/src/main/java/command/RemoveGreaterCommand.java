package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.ProductCommand;
import exception.CommandExecutionException;
import model.Product;

public class RemoveGreaterCommand extends AbstractCommand implements ProductCommand {
    private String[] args;
    private final ProductCollectionManager productManager;

    public RemoveGreaterCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public void execute(Product product) throws CommandExecutionException{
        try {
            productManager.removeGreater(product);
        } catch (NumberFormatException nfe) {
            throw new CommandExecutionException("incorrect argument format");
        }
    }

    @Override
    public void execute() throws CommandExecutionException {
        throw new UnsupportedOperationException();
    }
}
