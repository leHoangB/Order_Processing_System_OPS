package dao;

import model.Product;

import java.util.List;

public interface ProductDao {
    public Product getProduct(int productID) throws Exception;
    public List<Product> getAllProduct()throws Exception;

}
