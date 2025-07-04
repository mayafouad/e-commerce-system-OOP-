import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a customer

        Customer customer1 = new Customer("John Doe", "123 Main St", "555-1234", "john@example.com", 100.0);
        Customer customer2 = new Customer("Jane Smith", "456 Elm St", "555-5678", "jane@example.com", 1000.0);
        // Create some products
        Product tv = new TV("TV", 800.0, 5, 15.0);
        Product cheese = new Cheese("Cheddar", 5.0, 10, LocalDate.of(2026, 12, 31), 0.5);
        Product mobileCard = new MobileCard("Mobile Card", 20.0, 15, "555-5678");
        // Add products to the customer's cart
        Cart cart1 = new Cart(List.of(tv, cheese, mobileCard), customer1);
        // Checkout the cart: all products should be available and not expired
        cart1.checkout();

        System.out.println("---------------------------------");
        // Create another customer with insufficient funds
        Cart cart2 = new Cart(List.of(tv, cheese, mobileCard), customer2);
        cart2.checkout();

        System.out.println("---------------------------------");
        // Create a cart with an expired product
        // This should not allow checkout and print an error message
        Product expiredCheese = new Cheese("Expired Cheese", 5.0, 10, LocalDate.of(2022, 12, 31), 0.5);
        Cart expiredCart = new Cart(List.of(expiredCheese), customer1);
        expiredCart.checkout();
        System.out.println("---------------------------------");
        // Create a cart with an out-of-stock product
        // This should not allow checkout and print an error message
        Product outOfStockTV = new TV("Out of Stock TV", 800.0, 0, 15.0);
        Cart outOfStockCart = new Cart(List.of(outOfStockTV), customer2);
        outOfStockCart.checkout();

    }
}
