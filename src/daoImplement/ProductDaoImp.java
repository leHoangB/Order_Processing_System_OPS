package daoImplement;

import dao.ProductDao;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains methods to communicate with product related tables in database
 *
 * @author Le Viet Hoang
 */
public class ProductDaoImp extends DBContext implements ProductDao {

    /**
     * This method retrieve a specific product in product table
     *
     * throw an error if occurs
     */
    @Override
    public Product getProduct(int productID) throws Exception {
        try {
            String query = "select * from Product where productID = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            Product product = new Product();
            while (rs.next()) {
                product = new Product(
                        rs.getInt("productID"),
                        rs.getFloat("productPrice"),
                        rs.getString("productType")
                );
            }
            return  product;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * This method retrieve all products in Product table
     *
     * throw an error if occurs
     */
    @Override
    public List<Product> getAllProduct() throws Exception {
        List <Product> returnList = new ArrayList();
        try {
            String query = "select * from Product";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productID"),
                        rs.getFloat("productPrice"),
                        rs.getString("productType")
                );
                returnList.add(product);
            }
            return returnList;
        }catch (SQLException e) {
            throw e;
        }
    }


}
