package controller;

import daoImplement.OrderDaoImp;
import model.Order;
import validator.OrderValidator;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The class contains method process logic and communicate between view and data
 *
 * @author Le Viet Hoang
 */
public class OrderController {

    /**
     * This method calls dao class to get all orders and send it to view layer
     *
     * @return a List of Order or null if error occurs
     */
    public List<Order> getAllOder(){
        try {
            return new OrderDaoImp().getAllOrder();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method calls dao class to get all orders by a specific customer and send it to view layer
     *
     * @param (customerID) given customer's id, type: int
     * @return a List of Order or null if error occurs
     */
    public List<Order> searchOder(int customerID){
        try {
            return new OrderDaoImp().getAllOrderByCustomer(customerID);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method calls dao class to add a new Customer after receiving data
     *
     * @param (customerID) ordering customer's id, type: int
     * @param (customerName) ordering customer, type: String
     * @param (productID) ordered product id, type: String
     * @param (orderDate) date which order is made, type: String
     * @param (orderAmount) amount of products ordered in string type for checking, type: String
     * @param (oldQuatity) current amount of stock in shop, type: int
     * @param (shopNo) shop id by which order is for, type: int
     * @return 0 if update successfully, 1 if order date is invalid ,
     *         2 if order amount is invalid, 3 if adding failed on backend, 4 if product is missing
     */
    public int AddOrder( int customerID, String customerName, String productID,
                          String orderDate, String orderAmount,int oldQuatity, int shopNo){
        OrderValidator orderValidator =new OrderValidator();
        int orderQuantity =0;
        int newAmount =0;
        /**
         *  check amount validity
         */
        try {

            orderQuantity = Integer.parseInt(orderAmount);
            if (orderValidator.isValidAmount(orderQuantity, oldQuatity)){
                newAmount = oldQuatity - orderQuantity;
            }else{
                return 2; // invalid amount
            }
        }catch (Exception e){
            return 2; // amount empty or is not number
        }
        /**
        *  check date validity
        */
        if (orderValidator.isValidDate(orderDate)) {
            try {
                String date = (new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(orderDate)));
                new OrderDaoImp().addOrder(
                            new Order(customerID, customerName, Integer.parseInt(productID), orderQuantity, date),
                            newAmount, shopNo
                        );
            }catch (NumberFormatException e){
                return 4; // empty product
            }catch (Exception e) {
                return 3; // dao error
            }
        }else{
            return 1;   // invalid date
        }
        return 0;   // success
    }
}
