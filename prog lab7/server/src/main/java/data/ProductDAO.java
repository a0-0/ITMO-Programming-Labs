package data;

import model.Product;

import java.util.Collection;

public interface ProductDAO {
    Collection<Product> selectProductsToCollection();
    Product getProduct(int id);
    int getNextId();
    void insertProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
    boolean deleteProducts();
}
