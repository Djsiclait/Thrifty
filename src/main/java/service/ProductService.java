package service;


import entity.Category;
import entity.Discount;
import entity.Order;
import entity.Product;
import static java.util.Collections.emptyList;
import java.util.List;
import javax.jms.Session;
import org.apache.log4j.Logger;


public class ProductService {
    private static final Logger _log = Logger.getLogger(ProductService.class);
    public final String OPEN_STATUS = "OPEN";

    public Category getCategory(long id) {
        return null;
    }

    public List getAllCategory() {

        return emptyList();
    }

    public void addCategory(Category c) {

    }

    public void removeCategory(long id) {

    }

    public List getAllDiscounts() {

        return emptyList();
    }

    public void addDiscount(Discount c) {

    }

    public void removeDiscount(long id) {

    }


    public List getAllProducts() {

        return emptyList();
    }

    public void addProduct(Product p) {

    }

    public void removeProduct(long id) {

    }

    public List getProductsForCategory(Category c) {

        return emptyList();
    }

    public List getAllOrders() {

        return emptyList();
    }

    public void addOrUpdateOrder(Order o) {

    }


    private Session getSession() {
        return null;
    }



}
