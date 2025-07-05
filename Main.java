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
        // Checkout the cart: all products should be available and not expired but the customer with insufficient funds
        cart1.checkout();

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println();
        Cart cart2 = new Cart(customer2);
        cart2.addProduct(tv, 1);
        cart2.addProduct(cheese, 5);
        cart2.addProduct(mobileCard, 1);
        cart2.checkout();

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println();
        // Create a cart with an expired product
        // This should not allow checkout and print an error message
        Product expiredCheese = new Cheese("Expired_Cheese", 5.0, 10, LocalDate.of(2022, 12, 31), 0.5);
        Cart expiredCart = new Cart(customer1);
        expiredCart.addProduct(expiredCheese, 1);
        expiredCart.checkout();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println();
        // Create a cart with an out-of-stock product
        // This should not allow checkout and print an error message
        Product outOfStockTV = new TV("OutofStock_TV", 800.0, 0, 15.0);
        Cart outOfStockCart = new Cart(customer2);
        outOfStockCart.addProduct(outOfStockTV, 1);
        outOfStockCart.checkout();
        System.out.println();
    }
}



/*
output:

Checking out the following products:
1* TV (Price: $800.0)
2* Cheddar (Price: $5.0)
1* Mobile Card (Price: $20.0)
Shipping TV with weight: 15.0 kg
Shipping Cheddar with weight: 0.5 kg
_____________________________
Subtotal: $830.0
Shipping fee: $155.0
Total: $985.0
Insufficient balance. Please add more funds to your account.
------------------------------------------------------------------------------------

Checking out the following products:
1* TV (Price: $800.0)
5* Cheddar (Price: $5.0)
1* Mobile Card (Price: $20.0)
Shipping TV with weight: 15.0 kg
Shipping Cheddar with weight: 0.5 kg
_____________________________
Subtotal: $845.0
Shipping fee: $155.0
Total: $1000.0
Payment successful.
Your new balance is $0.0
-----------------------------------------------------------------------------------

Checking out the following products:
Product Expired_Cheese is expired and cannot be purchased.
-----------------------------------------------------------------------------------

Checking out the following products:
Product OutofStock_TV is out of stock.

 */
