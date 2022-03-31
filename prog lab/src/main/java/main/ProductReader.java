package main;

import exception.InvalidArgumentException;
import model.Product;

import java.util.List;

public interface ProductReader {
    List<Product> read() throws InvalidArgumentException;
}
