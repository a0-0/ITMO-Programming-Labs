package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.Command;

public class ShowCommand extends AbstractCommand implements Command {
    private final ProductCollectionManager productManager;

    public ShowCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    public void execute() {
        productManager.show();
    }
}
