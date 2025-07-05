public class order {
    private Product key;
    private Integer value;

    public order(Product key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Product getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
