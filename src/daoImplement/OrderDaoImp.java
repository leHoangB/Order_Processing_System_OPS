package daoImplement;

import dao.OrderDao;
import model.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains methods to communicate with order related tables in database
 *
 * @author Le Viet Hoang
 */
public class OrderDaoImp extends DBContext implements OrderDao {

    /**
     * This method add a new order in Order table
     *
     * throw an error if occurs
     */
    @Override
    public void addOrder(Order order, int newQuatity, int shopNo) throws Exception {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Order] VALUES (?, ?, ?, ?, ?);" +
                    "UPDATE Stock SET quatity = ? WHERE productID = ? and shopNo = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, order.getCustomerID());
            ps.setString(2, order.getCustomerName());
            ps.setInt(3,order.getProductID());
            ps.setFloat(4,order.getAmount());
            ps.setString(5, order.getOrderDate());
            ps.setInt(6,newQuatity);
            ps.setInt(7,order.getProductID());
            ps.setInt(8,shopNo);
            ps.executeUpdate(); //update to database
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * This method retrieve all orders in Order table
     *
     * throw an error if occurs
     */
    @Override
    public List<Order> getAllOrder() throws Exception {
        List <Order> returnList = new ArrayList();
        try {
            String query = "select * from [Order]";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("orderID"),
                        rs.getInt("customerID"),
                        rs.getString("customerName"),
                        rs.getInt("productID"),
                        rs.getFloat("amount"),
                        rs.getString("orderDate")
                );
                returnList.add(order);
            }
            return returnList;
        }catch (SQLException e) {
            throw e;
        }
    }

    /**
     * This method retrieve all orders by one customer in Order table
     *
     * throw an error if occurs
     */
    @Override
    public List<Order> getAllOrderByCustomer(int customerID) throws Exception {
        List <Order> returnList = new ArrayList();
        try {
            String query = "select * from [Order] where customerID = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,customerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("orderID"),
                        rs.getInt("customerID"),
                        rs.getString("customerName"),
                        rs.getInt("productID"),
                        rs.getFloat("amount"),
                        rs.getString("orderDate")
                );
                returnList.add(order);
            }
            return returnList;
        }catch (SQLException e) {
            throw e;
        }
    }
}
