package dao;

import model.Order;
import model.Stock;

import java.util.List;

public interface OrderDao {
    public void addOrder(Order order, int newQuatity, int shopNo)throws Exception;
    public List<Order> getAllOrder()throws Exception;
    public List<Order> getAllOrderByCustomer(int customerID) throws Exception;
}
