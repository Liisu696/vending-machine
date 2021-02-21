package manager;

import entity.Coin;
import entity.CurrencyType;
import entity.Product;
import service.DatabaseService;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine implements IVendingMachine {

    private CurrencyType currency;
    private Map<Product, Integer> productStock;
    private TreeMap<Coin, Integer> coinStock;

    private transient DatabaseService dbService;

    Scanner in = new Scanner(System.in);

    public VendingMachine() {
    }

    @Override
    public void displayMenu() {
        System.out.println("Welcome!");
        System.out.println("cod  Product\t Price \tSize");
        for (Product product : productStock.keySet()) {
            System.out.println(product.getCod()
                    +  "\t" + product.getName()
                    +"\t\t" + product.getPrice()+currency
                    + "\t" + product.getSize());
        }
    }

    @Override
    public void displayCoinStock() {
        System.out.println("Cod  Value");
        for (Coin coin : coinStock.keySet()) {
            System.out.println(coin.getCod() + "  " + coin.getValue()+currency);
        }
    }

    @Override
    public void deliverProduct(Product product) {
        productStock.put(product, productStock.get(product) - 1);
        System.out.println("Please take your product.");
    }

    @Override
    public Integer insertCoin(Integer productPrice) {
        Integer sum = 0;
        int option;
        int toPay;
        boolean ok;

        while (sum < productPrice) {
            System.out.println("Insert coin: ");
            ok = false;
            option = in.nextInt();

            for (Coin coin : coinStock.keySet()) {
                if (coin.getCod() == option) {
                    coinStock.put(coin, 1 + coinStock.get(coin));
                    sum += coin.getValue();
                    System.out.println("Amount: " + sum + " " + currency);
                    toPay = productPrice - sum;
                    System.out.println("Amount left to be paid: " + toPay + " " + currency);
                    ok = true;
                }
            }

            if (ok == false) {
                System.out.println("Option is not valid");
            }
        }
        return null;
    }

    @Override
    public Product buyProduct() {
        System.out.println("Choose a product: ");
        int option = in.nextInt();
        boolean ok = false;

        if (option == 0) {
            this.shutdown();
        }

        for (Product product : productStock.keySet()) {
            if (product.getCod().equals(option)) {
                Integer quantity = productStock.get(product);
                if (quantity > 0) {
                    return product;
                } else {
                    System.out.println("Product not available!");
                    return this.buyProduct();
                }
            }
        }

        if (ok == false) {
            System.out.println("Option is not valid.");
            return this.buyProduct();
        }

        return null;
    }

    @Override
    public void payChange(Integer change) {
        if (change == 0) {
            System.out.println("No change to pay back.");
        } else {
            for (Coin coin : coinStock.keySet()) {
                while (coin.getValue() <= change) {
                    if (coinStock.get(coin) > 0) {
                        System.out.println("Paying change: " + coin.getValue() + " " + currency);
                        coinStock.put(coin, coinStock.get(coin) - 1);
                        change = change - coin.getValue();
                    } else {
                        break;
                    }
                }
            }

            if (change == 0) {
                System.out.println("Successfully returned all your change.");
            } else {
                System.out.println("Not enough change.");
            }
        }
    }

    @Override
    public void start() {
        while (true) {
            this.displayMenu();
            Product product = this.buyProduct();
            this.displayCoinStock();
            Integer sum = this.insertCoin(product.getPrice());
            this.deliverProduct(product);
            this.payChange(sum - product.getPrice());

//            dbService.write(this);
        }
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

    public void setDbService(DatabaseService dbService) {
        this.dbService = dbService;
    }
}
