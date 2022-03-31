package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.Command;
import exception.CommandExecutionException;
import model.UnitOfMeasure;

import java.util.Locale;

public class RemoveAnyByUnitOfMeasureCommand extends AbstractCommand implements Command {
    ProductCollectionManager productManager;
    String[] args;

    public RemoveAnyByUnitOfMeasureCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    public void execute() throws CommandExecutionException {
        try {
            if (args.length == 1) {
                productManager.removeAnyByUnitOfMeasure(UnitOfMeasure.valueOf(args[0].toUpperCase(Locale.ROOT)));
            } else {
                throw new CommandExecutionException("expected 1 argument, got " + args.length);
            }
        } catch (NumberFormatException nfe) {
            throw new CommandExecutionException("incorrect argument format");
        }
    }
}
