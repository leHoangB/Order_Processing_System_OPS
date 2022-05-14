package model;

/**
 * The class represent a stock entity
 *
 * @author Le Viet Hoang
 */
public class Stock {
    int productID;
    int quatity;
    int shopNo;

    public Stock(int productID, int quatity, int shopNo) {
        this.productID = productID;
        this.quatity = quatity;
        this.shopNo = shopNo;
    }

    public Stock() {

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }
}
