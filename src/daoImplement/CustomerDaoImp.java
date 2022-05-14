package daoImplement;

import dao.CustomerDao;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains methods to communicate with user related tables in database
 *
 * @author Le Viet Hoang
 */
public class CustomerDaoImp extends DBContext implements CustomerDao {

    /**
     * This method add a customer into Customer table in database
     *
     * @param (customer) An object of Customer class contains data of new user, type: Customer
     * @return a Customer object contains data of retrieved user or throw an error if occurs
     */
    @Override
    public void addCustomer(Customer customer) throws Exception {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Customer VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getAddress());
            ps.setInt(3,customer.getPhone());
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
     * This method retrieves all Customer in DB
     *
     * @return a List of Customer object contains data of all customer or throw an error if occurs
     */
    @Override
    public List<Customer> getAllCustomer() throws Exception{
        List <Customer> returnList = new ArrayList();
        try {
            String query = "select * from Customer";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customerID"),
                        rs.getString("customerName"),
                        rs.getString("address"),
                        rs.getInt("phone")
                );
                returnList.add(customer);
            }
            return returnList;
        }catch (SQLException e) {
            throw e;
        }
    }

    /**
     * This method updates a customer data in Customer table
     *
     * throw an error if occurs
     */
    @Override
    public void updateCustomer(Customer customer) throws Exception{
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE Customer SET customerName = ?, address = ?, phone = ? WHERE customerID =?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getAddress());
            ps.setInt(3, customer.getPhone());
            ps.setInt(4, customer.getCustomerID());
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
}
