package command;

import collection.ProductCollectionManager;
import commands.AbstractCommand;
import commands.ProductCommand;
import model.Product;

public class AddCommand extends AbstractCommand implements ProductCommand {
    private final ProductCollectionManager productManager;

    public AddCommand(ProductCollectionManager productManager, boolean req) {
        super(req);
        this.productManager = productManager;
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    public void execute(Product product) {
        productManager.addProduct(product);
    }
}
