package Test;

import controller.OrderController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {
    OrderController orderController = new OrderController();
    @Test
    void getAllOder() {
        assertEquals(0,orderController.getAllOder());
    }

    @Test
    void searchOder() {
        assertEquals(0,orderController.searchOder(1));
    }

}