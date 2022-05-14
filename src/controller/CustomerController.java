package controller;

import model.Customer;
import daoImplement.CustomerDaoImp;
import validator.CustomerValidator;

import java.util.List;

/**
 * The class contains method process logic and communicate between view and data
 *
 * @author Le Viet Hoang
 */
public class CustomerController {
    /**
     * This method calls dao class to all Customer and send it to view layer
     *
     * @return a Customer object contains data of retrieved user or null if error occurs
     */
    public List<Customer> getAllCustomer(){

        try {
            return new CustomerDaoImp().getAllCustomer();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method calls dao class to add a new Customer after receiving data
     *
     * @param (customerName) Customer name, type: String
     * @param (customerAddress) Customer address, type: String
     * @param (customerPhone) Customer phone number, type: String
     * @return 1 if add successfully 2 if error in backend 3 if invalid phone number 4 if invalid name
     */
    public int addCustomer( String customerName, String customerAddress, String  customerPhone){
        CustomerValidator cv = new CustomerValidator();
        if (!cv.isInvalidName(customerName)){ // check customer name validity
            if (cv.isValidPhone(customerPhone)){ // invalid phone number validity
                try {
                    new CustomerDaoImp().addCustomer(new Customer(
                            customerName, customerAddress, Integer.parseInt(customerPhone)
                    ));
                    return 1; // success
                } catch (Exception e) {
                    return 2; // dao error
                }
            }else{
                return 3; // invalid phone number
            }
        }else {
            return 4; // invalid customer name
        }
    }

    /**
     * This method calls dao class to update new Customer info after receiving data
     *
     * @param (customerID) Customer id, type: String
     * @param (customerName) Customer name, type: String
     * @param (customerAddress) Customer address, type: String
     * @param (customerPhone) Customer phone number, type: String
     * @return 1 if update successfully 2 if error in backend 3 if invalid phone number 4 if invalid name
     */
    public int EditCustomer(String customerID, String customerName, String customerAddress, String customerPhone){
        CustomerValidator cv = new CustomerValidator();
        /**
        *  check if missing data
        */
        if(customerID.isEmpty()||customerAddress.isEmpty()||customerAddress.isEmpty()||customerPhone.isEmpty()){
            return 5; // missing data
        }
        if (!cv.isInvalidName(customerName)){
            if (cv.isValidPhone(customerPhone)){
                try {
                    new CustomerDaoImp().updateCustomer(new Customer(
                            Integer.parseInt(customerID), customerName, customerAddress, Integer.parseInt(customerPhone)
                    ));
                    return 1;  // success
                } catch (Exception e) {
                    return 2; // dao error
                }
            }else{
                return 3;  // invalid phone number
            }
        }else {
            return 4; // invalid customer name
        }
    }
}
