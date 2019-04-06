public class Pair {
    private Integer sum;
    private String beverage;

    public Pair(Integer sum, String beverage) {
        this.sum = sum;
        this.beverage = beverage;
    }

    public Integer getSum() {
        return sum;
    }

    public String getBeverage() {
        return beverage;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }
}
