import java.util.List;

public class ShippingService {
    private List<Product> items;
    public ShippingService(List<Product> items) {
        this.items = items;
    }

    public double calcFee() {
        double totalWeight = 0;
        for (Product item : items) {
            if (item instanceof Shippable) {
                totalWeight += ((Shippable) item).getWeight();
            }
        }
        return totalWeight * 10;
    }
}
