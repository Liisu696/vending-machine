package manager;

import entity.Product;

public interface IVendingMachine {
    void displayMenu();
    void displayCoinStock();
    void deliverProduct(Product product);
    Integer insertCoin(Integer productPrice);
    Product buyProduct();
    void payChange(Integer change);
    void start();
    void shutdown();
}
