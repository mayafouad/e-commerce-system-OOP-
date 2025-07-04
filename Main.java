import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // Create a customer

        Customer customer1 = new Customer("John", "123 Main St", "000000", "j@e.com", 100.0);
        Customer customer2 = new Customer("Smith", "456 Elm St", "000000", "s@p.com", 1000.0);
        // Create some products
        Product tv = new TV("TV", 800.0, 5, 15.0);
        Product cheese = new Cheese("Cheddar", 5.0, 10, LocalDate.of(2026, 12, 31), 0.5);
        Product mobileCard = new MobileCard("Mobile Card", 20.0, 15, "011100");
        // Add products to the customer's cart
        Cart cart1 = new Cart(customer1);
        cart1.addProduct(tv, 1);
        cart1.addProduct(cheese, 2);
        cart1.addProduct(mobileCard, 1);
        // Checkout the cart: all products should be available and not expired
        cart1.checkout();

        System.out.println("---------------------------------");
        // Create another customer with insufficient funds
        Cart cart2 = new Cart(customer2);
        cart2.addProduct(tv, 1);
        cart2.addProduct(cheese, 5);
        cart2.addProduct(mobileCard, 1);
        cart2.checkout();

        System.out.println("---------------------------------");
        // Create a cart with an expired product
        // This should not allow checkout and print an error message
        Product expiredCheese = new Cheese("Expired_Cheese", 5.0, 10, LocalDate.of(2022, 12, 31), 0.5);
        Cart expiredCart = new Cart(customer1);
        expiredCart.addProduct(expiredCheese, 1);
        expiredCart.checkout();
        System.out.println("---------------------------------");
        // Create a cart with an out-of-stock product
        // This should not allow checkout and print an error message
        Product outOfStockTV = new TV("OutofStock_TV", 800.0, 0, 15.0);
        Cart outOfStockCart = new Cart(customer2);
        outOfStockCart.addProduct(outOfStockTV, 1);
        outOfStockCart.checkout();

    }
}



