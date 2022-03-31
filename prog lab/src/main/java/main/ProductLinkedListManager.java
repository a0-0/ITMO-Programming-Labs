package main;

import io.UserIO;
import model.Product;
import model.ProductFormatter;
import model.UnitOfMeasure;

import java.time.LocalDate;
import java.util.*;

public class ProductLinkedListManager implements ProductCollectionManager {

    private final List<Product> products;

    private final UserIO userIO;

    private long productId = 1;
    private int orgId = 1;

    private final LocalDate dateOfInit = LocalDate.now();

    public ProductLinkedListManager(UserIO userIO) {
        products = new LinkedList<>();
        this.userIO = userIO;
    }


    public void addProduct(Product product) {
        product.setId(productId);
        product.getManufacturer().setId(orgId);
        productId++;
        orgId++;
        products.add(product);
    }

    public void changeProduct(int index, Product product) {
        product.setId(productId);
        product.getManufacturer().setId(orgId);
        productId++;
        orgId++;
        products.set(index, product);
    }

    public void insertProduct(int index, Product product) {
        product.setId(productId);
        product.getManufacturer().setId(orgId);
        productId++;
        orgId++;
        products.add(index, product);
    }

    public void addProducts(List<Product> products) {
        Set<Long> idSet = new HashSet<>();
        if (products.stream().allMatch(t -> idSet.add(t.getId()))) {
            if (products.size() != 0) {
                productId = products.stream().max(Comparator.comparing(Product::getId)).get().getId() + 1;
                orgId = products.stream().mapToInt(v -> v.getManufacturer().getId()).max().getAsInt() + 1;
            }
            this.products.addAll(products);

        } else {
            userIO.printErrorMessage("non-unique id found, using empty collection");
        }
    }


    public List<Product> getProducts() {
        return products;
    }

    public void info() {
        userIO.printLine(String.format("%s, Date of init:%s, Number of objects: %d", products.getClass().getName(),
                dateOfInit, products.size()));
    }

    public void clear() {
        products.clear();
    }

    public void show() {
        ProductFormatter formatter = new ProductFormatter();
        for(Product product: products) {
            userIO.printLine(formatter.formatProduct(product));
        }
    }


    public void removeById(int id) {
        int count = 0;
        Iterator<Product> iterator = products.iterator();
        while(iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId() == id) {
                iterator.remove();
                count++;
            }
        }
        userIO.printLine(String.format("removed %d object(-s)", count));
    }

    public void removeGreater(Product product) {
        products.removeIf(p -> p.compareTo(product) < 0);
    }

    public void sort() {
        Collections.sort(products);
    }

    public void removeAnyByUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        Optional<Product> product = products.stream().filter(p -> p.getUnitOfMeasure() == unitOfMeasure).findFirst();
        product.ifPresent(products::remove);
    }

    public void minByManufacturer() {
        Optional<Product> product = products.stream().min(Comparator.comparing(Product::getManufacturer));
        if (product.isPresent()) {
            ProductFormatter formatter = new ProductFormatter();
            userIO.printLine(formatter.formatProduct(product.get()));
        }
    }

    public void printFieldAscendingManufacturer() {
        ProductFormatter formatter = new ProductFormatter();
        products.stream().sorted().forEach(p -> userIO.printLine(formatter.formatOrganization(p)));
    }

    public void insertAt(int index, Product product) {
        try {
            insertProduct(index, product);
            userIO.printLine("inserted 1 object");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("invalid index");
        }
    }

    public void update(int id, Product product) {
        int index = 0;
        for (Product productOld : products) {
            if (productOld.getId() == id) {
                break;
            }
            index++;
        }

        if (products.size() != 0 && index != products.size()) {
            changeProduct(index, product);
        }
    }
}
