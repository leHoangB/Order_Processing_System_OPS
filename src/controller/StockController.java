package controller;

import daoImplement.StockDaoImp;
import model.Stock;
import java.util.List;

/**
 * The class contains method process logic and communicate between view and data
 *
 * @author Le Viet Hoang
 */
public class StockController {
    /**
     * This method calls dao class to get all shop id and send it to view layer
     *
     * @return a list of shop id or null if error occurs
     */
    public List<Integer> getAllShop(){
        try {
            return new StockDaoImp().getAllShop();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This method calls dao class to get data of a stock of a specific product from a shop and send it to view layer
     *
     * @param (productID) id of a specific product, type: int
     * @param (shopNo) id of a shop, type: int
     * @return a Stock object contains data of retrieved Stock or null if error occurs
     */
    public Stock getStockByIdAndShop(int productID,int shopNo){
        try {
            return new StockDaoImp().getStock(productID,shopNo);
        } catch (Exception e) {
            return null;
        }
    }
}
