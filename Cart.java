import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<order> products;
    private Customer customer;

    public Cart(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product, int quantity) {
        products.add(new order(product, quantity));
    }
    public void removeProduct(Product product) {
        products.removeIf(order -> order.getKey().equals(product));
    }
    public double subtotal() {
        double totalPrice = 0;
        for (order order : products) {
            totalPrice += order.getKey().getPrice() * order.getValue();
        }
        return totalPrice;
    }
    public void checkout() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty. Please add products before checking out.");
            return;
        }
        double shippingFee = 0;
        System.out.println("Checking out the following products:");
        List<order> shipList = new ArrayList<>();
        for (order order : products) {
            if (order.getKey().getQuantity() < order.getValue()) {
                System.out.println("Product " + order.getKey().getName() + " is out of stock.");
                return;
            }
            if (order.getKey() instanceof Expirable && ((Expirable) order.getKey()).getExpirationDate().before(new Date(System.currentTimeMillis()))) {
                System.out.println("Product " + order.getKey().getName() + " is expired and cannot be purchased.");
                return;
            }
            if (order.getKey() instanceof Shippable) {
                shipList.add(order);
            }
            System.out.println(order.getValue() + "* " + order.getKey().getName() + " (Price: $" + order.getKey().getPrice() + ")");
            order.getKey().updateQuantity(-order.getValue());
        }
        if(shipList.size() > 0){
            ShippingService shippingService = new ShippingService(shipList);
            shippingFee = shippingService.calcFee();
        }
        System.out.println("_____________________________");
        System.out.println("Subtotal: $" + subtotal());
        double total = subtotal() + shippingFee;
        System.out.println("Shipping fee: $" + shippingFee);
        System.out.println("Total: $" + total);
        double balance = customer.getBalance();
        if (balance >= total) {
            System.out.println("Payment successful.");
            customer.setBalance(balance - total);
            System.out.println("Your new balance is $" + customer.getBalance());
        } else {
            System.out.println("Insufficient balance. Please add more funds to your account.");
        }
    }

}
