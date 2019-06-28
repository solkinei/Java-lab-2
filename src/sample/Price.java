package sample;

public class Price {
    private String drink;
    private Integer price;

    public Price(String drink, Integer price) {
        this.drink = drink;
        this.price = price;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}