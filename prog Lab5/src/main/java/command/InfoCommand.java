package command;

import main.ProductCollectionManager;

public class InfoCommand implements Command{
    ProductCollectionManager productManager;

    InfoCommand(ProductCollectionManager productManager) {
        this.productManager = productManager;
    }

    public void execute() {
        productManager.info();
    }
}
