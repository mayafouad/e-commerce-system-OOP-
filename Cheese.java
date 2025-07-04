import java.sql.Date;
import java.time.LocalDate;

public class Cheese extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public Cheese(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public Date getExpirationDate() {
        return Date.valueOf(expiryDate);
    }
    @Override
    public void setExpirationDate(Date date) {
        this.expiryDate = date.toLocalDate();
    }
    @Override
    public double getWeight() {
        return weight;
    }
    @Override
    public String getName() {
        return super.getName();
    }
}
