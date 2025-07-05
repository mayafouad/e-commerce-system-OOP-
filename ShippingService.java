import java.util.List;

public class ShippingService {
    private List<order> items;
    public ShippingService(List<order> items) {
        this.items = items;
    }

    public double calcFee() {
        double totalWeight = 0;
        for (order item : items) {
            if (item.getKey() instanceof Shippable) {
                totalWeight += ((Shippable) item.getKey()).getWeight() * item.getValue();
                System.out.println("Shipping " + ((Shippable) item.getKey()).getName() + " with weight: " + ((Shippable) item.getKey()).getWeight() + " kg");
            }
        }
        return totalWeight * 10.0;
    }
}
