package view;

import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import controller.StockController;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {
    CustomerController customerController = new CustomerController();
    ProductController productController = new ProductController();
    StockController stockController = new StockController();
    OrderController orderController = new OrderController();
    private JButton btnAdd;
    private JTable tblCustomer;
    private JPanel mainPanel;
    private JTabbedPane mainPane;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JButton btnUpdate;
    private JTextField txtID;
    private JButton btnClear;
    private JTable tblOrder;
    private JTextField txtProductID;
    private JTextField txtProductPrice;
    private JTextField txtQuatity;
    private JTextField txtProductType;
    private JTextField txtShopNo;
    private JComboBox cboCustomer;
    private JTextField txtSearchOrder;
    private JButton btnSearchOrder;
    private JButton btnAddOrder;
    private JTextField txtOrderQuangtity;
    private JComboBox cboShopNo;
    private JComboBox cboProduct;
    private JFormattedTextField txtOrderDate;

    public MainView(String title) {
        super(title);
        try {
            MaskFormatter mask = new MaskFormatter("##/##/####");
            mask.install(txtOrderDate);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            txtOrderDate.setText( dtf.format(LocalDateTime.now()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.tblCustomer.setDefaultEditor(Object.class, null); //disable table editor
        this.tblOrder.setDefaultEditor(Object.class, null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(1050,750));
        this.setResizable(false);
        this.pack();
        updateCustomerTable();   // load table data
        updateOrderTable("");
        updateDateOrderTab();

        /**
         * add new customer on click add button
         */
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtID.getText().isEmpty()) {
                    switch (new CustomerController().addCustomer( /* new user */
                            txtName.getText(), txtAddress.getText(), txtPhone.getText())){
                        case 1:
                            JOptionPane.showMessageDialog(null, /* add successful */
                                    "Add Customer successful");
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null,  /* add failed */
                                    "Add Customer failed", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null,  /* add failed */
                                    "Add Customer failed: invalid phone number", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null,  /* add failed */
                                    "Add Customer failed: invalid name", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "ID existed", /* old user */
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                updateCustomerTable(); /* reload table */
            }
        });
        /**
         * update customer on click update button
         */
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtID.getText().isEmpty()) {
                    int success = new CustomerController().EditCustomer( /* old user */
                            txtID.getText(), txtName.getText(), txtAddress.getText(), txtPhone.getText());
                    switch (success) {
                        case 1:
                            JOptionPane.showMessageDialog(null, /* update successful */
                                    "update Customer successful");
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null,  /* update failed */
                                    "update Customer failed", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null,  /* update failed */
                                    "update Customer failed: invalid phone number", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null,  /* update failed */
                                    "update Customer failed: invalid name", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null,  /* update failed */
                                    "update Customer failed: missing data", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, /* new user */
                            "User doesn't exist", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                updateCustomerTable(); /* reload table */
            }
        });

        /**
         * get selected Customer from table to form
         */
        tblCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tblCustomer.getSelectedRow();
                /**
                 * show selected Customer to form
                 */
                txtID.setText(tblCustomer.getModel().getValueAt(row, 0).toString());
                txtName.setText(tblCustomer.getModel().getValueAt(row, 1).toString());
                txtAddress.setText(tblCustomer.getModel().getValueAt(row, 2).toString());
                txtPhone.setText(tblCustomer.getModel().getValueAt(row, 3).toString());
            }
        });

        /**
         * clear form
         */
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtID.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtPhone.setText("");
            }
        });
        cboProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProduct();
            }
        });
        cboShopNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProduct();
            }
        });

        btnAddOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });
        btnSearchOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrderTable(txtSearchOrder.getText());
            }
        });
    }
    private void createUIComponents() {
        updateCustomerTable(); // load data table
    }

    public static void main(String[] args) {
        JFrame frame = new MainView("Customer Manager");
        frame.setVisible(true);
    }

    /**
     * Used to load data form database to table
     */
    private void updateCustomerTable(){
        DefaultTableModel tableModel = (DefaultTableModel) this.tblCustomer.getModel();
        tableModel.setColumnCount(0);
        tableModel.addColumn("Customer id");
        tableModel.addColumn("Customer name");
        tableModel.addColumn("Customer address");
        tableModel.addColumn("Customer phone");
        tableModel.setRowCount(0); // clear rows
        List<Customer> temp = customerController.getAllCustomer(); // get data
        for (model.Customer c: temp) {
            Object[] obj = new Object[]{
                    c.getCustomerID(),
                    c.getCustomerName(),
                    c.getAddress(),
                    "0"+c.getPhone(),
                    // because the requirement stated that phone saved as number so database doesn't save leading 0
            };
            tableModel.addRow(obj); // add retrieved data to table
        }
    }
    /**
     * load data for table order
     */
    private void updateOrderTable(String customerID){
        DefaultTableModel tableModel = (DefaultTableModel) this.tblOrder.getModel();
        tableModel.setColumnCount(0);
        tableModel.addColumn("Order id");
        tableModel.addColumn("Customer id");
        tableModel.addColumn("Customer mame");
        tableModel.addColumn("Product id");
        tableModel.addColumn("Amount");
        tableModel.addColumn("orderDate");

        tableModel.setRowCount(0); // clear rows
        List<Order> temp = new ArrayList<>();
        if(customerID.isEmpty()){
            temp = orderController.getAllOder(); // get data
        }else{
            temp = orderController.searchOder(Integer.parseInt(customerID)); // get data
        }
        if (temp!=null) {
            for (Order o : temp) {
                Object[] obj = new Object[]{
                        o.getOrderID(),
                        o.getCustomerID(),
                        o.getCustomerName(),
                        o.getProductID(),
                        o.getAmount(),
                        o.getAmount(),
                };
                tableModel.addRow(obj); // add retrieved data to table
            }
        }
    }
    /**
     * load data for order tab
     */
    private void updateDateOrderTab(){
        updateCboCustomer();
        updateCboProduct();
        updateCboShop();
    }

    /**
     * load data for chose buying customer JCombobox
     */
    private void updateCboCustomer(){
        List<Customer> customers = customerController.getAllCustomer(); // get data
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        for (Customer customer:customers) {
            cboModel.addElement(new ComboboxItem(customer.getCustomerID(), customer.getCustomerName()));
        }
        cboCustomer.setModel(cboModel);
    }
    /**
     * load data for chose ordering product JCombobox
     */
    private void updateCboProduct(){
        List<Product> products = productController.getAllProduct(); // get data
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        for (Product product:products) {
            cboModel.addElement(new ComboboxItem(product.getProductID(), product.getProductType()));
        }
        cboProduct.setModel(cboModel);
    }
    /**
     * load data for chose ordering shop JCombobox
     */
    private void updateCboShop(){
        List<Integer> allShop = stockController.getAllShop(); // get data
        DefaultComboBoxModel cboModel = new DefaultComboBoxModel();
        for (Integer i:allShop) {
            cboModel.addElement(new ComboboxItem(i,i+""));
        }
        cboShopNo.setModel(cboModel);
    }

    /**
     * load data of chosen product as well as its stop in chosen shop
     */
    private void showProduct(){
        int productID = ((ComboboxItem)cboProduct.getSelectedItem()).getId();
        int shopNo = (((ComboboxItem) cboShopNo.getSelectedItem()).getId());
        Product product= productController.getProductByID(productID);
        Stock stock = stockController.getStockByIdAndShop(productID, shopNo);
        if(stock != null && product!=null){ // show product if both product and shop is chosen
            txtProductID.setText(product.getProductID()+"");
            txtProductPrice.setText(product.getProductPrice()+"");
            txtProductType.setText(product.getProductType());
            txtShopNo.setText(stock.getShopNo()+"");
            txtQuatity.setText(stock.getQuatity()+"");
        }else{
            // clear form if product or stock is missing
            txtProductID.setText("");
            txtProductPrice.setText("");
            txtProductType.setText("");
            txtShopNo.setText("");
            txtQuatity.setText("");
        }
    }
    /**
     * calls controller to add product
     */
    public void addOrder(){
        int customerID = ((ComboboxItem)cboCustomer.getSelectedItem()).getId();
        String customerName =((ComboboxItem)cboCustomer.getSelectedItem()).getName();
        String productID =  txtProductID.getText();
        String Amount= txtOrderQuangtity.getText();
        String orderDate = txtOrderDate.getText();
        String Quantity = txtQuatity.getText();
        String shopNo = txtShopNo.getText();
        if(Quantity.isEmpty()||shopNo.isEmpty()){  // if product not chosen
            JOptionPane.showMessageDialog(null,  /* add failed */
                    "add Order failed: product empty", "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            int success = orderController.AddOrder(customerID, customerName, productID,
                    orderDate, Amount, Integer.parseInt(Quantity),Integer.parseInt(shopNo)
            );
            switch (success) {
                case 0:
                    JOptionPane.showMessageDialog(null, /* add successful */
                            "add Order successful");
                    updateDateOrderTab();
                    txtProductID.setText("");
                    txtProductPrice.setText("");
                    txtProductType.setText("");
                    txtShopNo.setText("");
                    txtQuatity.setText("");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null,  /* add failed */
                            "add Order failed: invalid date", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null,  /* add failed */
                            "add Order failed: invalid amount", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null,  /* add failed */
                            "add Order failed: database error", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null,  /* add failed */
                            "add Order failed: Product empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
            }

        }
    }
}
