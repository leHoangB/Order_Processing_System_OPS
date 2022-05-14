package daoImplement;

import dao.StockDao;
import model.Stock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains methods to communicate with stock related tables in database
 *
 * @author Le Viet Hoang
 */
public class StockDaoImp extends DBContext implements StockDao {
    /**
     * This method retrieve a specific stock in Stock table
     *
     * throw an error if occurs
     */
    @Override
    public Stock getStock(int productID, int shopNo) throws Exception {
        try {
            String query = "select * from Stock where productID = ? and shopNo = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, shopNo);
            ResultSet rs = ps.executeQuery();
            Stock stock = null;
            while (rs.next()) {
                stock = new Stock(
                        rs.getInt("productID"),
                        rs.getInt("quatity"),
                        rs.getInt("shopNo")
                );
            }
            return  stock;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * This method retrieve all shop id in stock table
     *
     * throw an error if occurs
     */
    @Override
    public List<Integer> getAllShop() throws Exception {
        List <Integer> returnList = new ArrayList();
        try {
            String query = "select shopNo from Stock  Group By shopNo";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                returnList.add(rs.getInt("shopNo"));
            }
            return returnList;
        }catch (SQLException e) {
            throw e;
        }
    }
}
