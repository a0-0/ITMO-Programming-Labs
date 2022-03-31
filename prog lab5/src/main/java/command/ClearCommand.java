package command;

import main.ProductCollectionManager;

public class ClearCommand implements Command{
    ProductCollectionManager productManager;

    ClearCommand(ProductCollectionManager productManager) {
        this.productManager = productManager;
    }

    public void execute() {
        productManager.clear();
    }
}
