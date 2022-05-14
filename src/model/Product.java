package model;

/**
 * The class represent a product entity
 *
 * @author Le Viet Hoang
 */
public class Product {
    int productID;
    float productPrice;
    String productType;

    public Product(int productID, float productPrice, String productType) {
        this.productID = productID;
        this.productPrice = productPrice;
        this.productType = productType;
    }
    public Product() {

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
