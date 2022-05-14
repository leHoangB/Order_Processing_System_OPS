package dao;

import model.Stock;

import java.util.List;

public interface StockDao {
    public Stock getStock(int productID, int shopNo) throws Exception;
    public List<Integer> getAllShop() throws Exception;
}
