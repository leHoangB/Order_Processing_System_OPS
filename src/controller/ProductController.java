package controller;

import daoImplement.ProductDaoImp;
import model.Product;
import java.util.List;

/**
 * The class contains method process logic and communicate between view and data
 *
 * @author Le Viet Hoang
 */
public class ProductController {
    /**
     * This method calls dao class to get data of a Product and send it to view layer
     *
     * @param (productID) id of a specific product, type: int
     * @return a product object contains data of retrieved product or null if error occurs
     */
    public Product getProductByID(int productID){
        try {
            return new ProductDaoImp().getProduct(productID);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method calls dao class to get all products and send it to view layer
     *
     * @return a list of products or null if error occurs
     */
    public List<Product> getAllProduct(){

        try {
            return new ProductDaoImp().getAllProduct();
        } catch (Exception e) {
            return null;
        }
    }
}
