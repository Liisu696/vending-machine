package entity;

public class Product {

    private Integer cod;
    private String name;
    private Integer price;
    private String size;

    public Product(int cod, String name, int price, String size) {
        this.cod = cod;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
