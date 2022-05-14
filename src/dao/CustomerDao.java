package dao;

import model.Customer;

import java.util.List;

public interface CustomerDao {

    public void addCustomer(Customer customer)throws Exception;
    public List<Customer> getAllCustomer()throws Exception;
    public void updateCustomer(Customer customer)throws Exception;
}
