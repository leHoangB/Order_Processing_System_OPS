package model;

/**
 * The class represent a customer entity
 *
 * @author Le Viet Hoang
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private int phone;

    public Customer(int customerID, String customerName, String address, int phone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
    }
    public Customer(String customerName, String address, int phone) {
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
    }
    public Customer() {

    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        phone = phone;
    }
}
