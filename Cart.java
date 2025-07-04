import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;
    private Customer customer;

    public Cart(List<Product> products, Customer customer) {
        this.products = products;
        this.customer = customer;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public double subtotal() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
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
        for (Product product : products) {
            if (product.getQuantity() <= 0) {
                System.out.println("Product " + product.getName() + " is out of stock.");
                return;
            }
            if (product instanceof Expirable && ((Expirable) product).getExpirationDate().before(new Date(System.currentTimeMillis()))) {
                System.out.println("Product " + product.getName() + " is expired and cannot be purchased.");
                return;
            }
            if (product instanceof Shippable) {
                shipList.add(product);
            }
            System.out.println("- " + product.getName() + " (Price: $" + product.getPrice() + ")");
            product.updateQuantity(1);
            if(shipList.size() > 0){
                ShippingService shippingService = new ShippingService(shipList);
                shippingFee = shippingService.calcFee();
            }
        }
        System.out.println("Subtotal: $" + subtotal());
        double total = subtotal() + shippingFee;
        System.out.println("Total (including shipping): $" + total);
        double balance = customer.getBalance();
        if (balance >= total) {
            System.out.println("Payment successful.");
            customer.setBalance(balance - total);
        } else {
            System.out.println("Insufficient balance. Please add more funds to your account.");
        }
    }

}
