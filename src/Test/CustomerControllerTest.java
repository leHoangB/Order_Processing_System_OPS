package Test;

import controller.CustomerController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {
    CustomerController c = new CustomerController();
    @Test
    void getAllCustomer() {
        assertEquals(0,c.getAllCustomer().toArray().length);
    }

    @Test
    void addCustomerSuccess() {
        assertEquals(1,c.addCustomer("Le Hoang","Ha Giang","0329171878"));
    }

    @Test
    void addCustomerWrongPhone() {
        assertEquals(3,c.addCustomer("Le Hoang","Ha Giang","123123"));
    }

    @Test
    void addCustomerWrongName() {
        assertEquals(4,c.addCustomer("","Ha Giang","0329171878"));
    }

    @Test
    void editCustomerSuccess() {
        assertEquals(1,c.EditCustomer("5","Hoang Le","Ha Noi", "0329171878"));
    }

    @Test
    void editCustomerSuccessMissingData() {
        assertEquals(5,c.EditCustomer("","","", ""));
    }

    @Test
    void editCustomerSuccessInvalidName() {
        assertEquals(4,c.EditCustomer("5","","Ha Noi", "0329171878"));
    }

    @Test
    void editCustomerSuccessInvalidPhone() {
        assertEquals(3,c.EditCustomer("5","Hoang Le","Ha Noi", "329171878"));
    }

    @Test
    void editCustomerSuccessInvalidPhone1() {
        assertEquals(3,c.EditCustomer("5","Hoang Le","Ha Noi", "aasdasdas"));
    }
}