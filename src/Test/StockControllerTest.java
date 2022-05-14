package Test;

import controller.StockController;
import model.Stock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockControllerTest {
    StockController stockController = new StockController();
    @Test
    void getAllShop() {
        assertEquals(3,stockController.getAllShop());
    }

    @Test
    void getStockByIdAndShop() {
        assertEquals( new Stock(1,10,1),stockController.getStockByIdAndShop(1,1));
    }

    @Test
    void getStockByIdAndShopWrongProduct() {
        assertEquals( null,stockController.getStockByIdAndShop(4,1));
    }

    @Test
    void getStockByIdAndShopWrongShop() {
        assertEquals( null,stockController.getStockByIdAndShop(1,4));
    }

    @Test
    void getStockByIdAndShopWrongBoth() {
        assertEquals( null,stockController.getStockByIdAndShop(4,4));
    }
}