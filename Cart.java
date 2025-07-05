import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

class Pair {
    private Product key;
    private Integer value;

    public Pair(Product key, Integer value) {
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

public class Cart {
    private List<Pair> products;
    private Customer customer;

    public Cart(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product, int quantity) {
        products.add(new Pair(product, quantity));
    }
    public void removeProduct(Product product) {
        products.removeIf(pair -> pair.getKey().equals(product));
    }
    public double subtotal() {
        double totalPrice = 0;
        for (Pair pair : products) {
            totalPrice += pair.getKey().getPrice() * pair.getValue();
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
        List<Product> shipList = new ArrayList<>();
        for (Pair product : products) {
            if (product.getKey().getQuantity() < product.getValue()) {
                System.out.println("Product " + product.getKey().getName() + " is out of stock.");
                return;
            }
            if (product.getKey() instanceof Expirable && ((Expirable) product.getKey()).getExpirationDate().before(new Date(System.currentTimeMillis()))) {
                System.out.println("Product " + product.getKey().getName() + " is expired and cannot be purchased.");
                return;
            }
            if (product.getKey() instanceof Shippable) {
                shipList.add(product.getKey());
            }
            System.out.println(product.getValue() + "* " + product.getKey().getName() + " (Price: $" + product.getKey().getPrice() + ")");
            product.getKey().updateQuantity(-product.getValue());
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
