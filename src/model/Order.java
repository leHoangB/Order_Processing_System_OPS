package model;

/**
 * The class represent a order entity
 *
 * @author Le Viet Hoang
 */
public class Order {
    int orderID;
    int customerID;
    String customerName;
    int productID;
    float amount;
    String orderDate;

    public Order(int orderID, int customerID, String customerName, int productID, float amount, String orderDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.productID = productID;
        this.amount = amount;
        this.orderDate = orderDate;
    }
    public Order( int customerID, String customerName, int productID, float amount, String orderDate) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.productID = productID;
        this.amount = amount;
        this.orderDate = orderDate;
    }
    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
