package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.Command;

public class InfoCommand extends AbstractCommand implements Command {
    private final ProductCollectionManager productManager;

    public InfoCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    public void execute() {
        productManager.info();
    }
}
