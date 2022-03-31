package command;

import main.ProductCollectionManager;

public class SortCommand implements Command{
    ProductCollectionManager productManager;

    public SortCommand(ProductCollectionManager productManager) {
            this.productManager = productManager;
    }

    public void execute() {
        productManager.sort();
    }
}
