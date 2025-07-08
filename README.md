

# 🛒 E-Commerce System (OOP)

This project implements a simple object-oriented **e-commerce system** with core functionality around product management, shopping cart behavior, and customer checkout logic.

---

## 📦 Features

* **Product Definition**

  * Each product has a `name`, `price`, and `available quantity`.
  * Products are categorized as:

    * **Expirable Products** (e.g., Cheese, Biscuits) – can expire.
    * **Non-Expirable Products** (e.g., TV, Mobile) – do not expire.
  * Products may also be:

    * **Shippable** (e.g., Cheese, TV) – must have a `weight`.
    * **Non-Shippable** (e.g., Mobile Scratch Cards).

* **Customer Actions**

  * Customers can add products to their cart with a specific quantity (must not exceed available stock).
  * Customers can perform checkout with items in their cart.

---

## 💳 Checkout Behavior

On checkout, the system will:

* ✅ Print to console:

  * **Order Subtotal** – sum of all item prices.
  * **Shipping Fees**
  * **Total Paid Amount** = subtotal + shipping fees.
  * **Customer’s Remaining Balance** after payment.

* ❌ Raise errors for:

  * Empty cart.
  * Insufficient customer balance.
  * Any product in the cart being out of stock or expired.

---

## 🚚 Shipping Service

* Collect all **shippable items** from the cart.
* Send them to the `ShippingService`, which accepts a list of objects implementing the following interface:

```java
public interface Shippable {
    String getName();
    double getWeight();
}
```

---

## 🛠 Technologies

* Java (OOP Concepts)
* Git & GitHub

---
## output
```java
// Create a customer
Customer customer1 = new Customer("John", "123 Main St", "000000", "j@e.com", 100.0);
Customer customer2 = new Customer("Smith", "456 Elm St", "000000", "s@p.com", 1000.0);
// Create some products
Product tv = new TV("TV", 700.0, 5, 15.0);
Product cheese = new Cheese("Cheddar", 5.0, 10, LocalDate.of(2026, 12, 31), 0.5);
Product mobileCard = new MobileCard("Mobile Card", 20.0, 15, "011100");
// Add products to the customer's cart
Cart cart1 = new Cart(customer1);
cart1.addProduct(tv, 1);
cart1.addProduct(cheese, 2);
cart1.addProduct(mobileCard, 1);
```

- Checkout the cart: all products should be available and not expired but the customer with insufficient funds
  ```java
  cart1.checkout();
  ```
  ![Screenshot 2025-07-08 212642](https://github.com/user-attachments/assets/d9048c8e-a27a-4733-8470-ae4caa601ffe)

  -
  ```java
  Cart cart2 = new Cart(customer2);
  cart2.addProduct(tv, 1);
  cart2.addProduct(cheese, 5);
  cart2.addProduct(mobileCard, 1);
  cart2.checkout();
  ```
  ![Screenshot 2025-07-08 212652](https://github.com/user-attachments/assets/3cdb4810-2eb3-4603-b18c-fe55b4093df0)

- Create a cart with an expired product
This should not allow checkout and print an error message
```java
    Product expiredCheese = new Cheese("Expired_Cheese", 5.0, 10, LocalDate.of(2022, 12, 31), 0.5);
    Cart expiredCart = new Cart(customer1);
    expiredCart.addProduct(expiredCheese, 1);
    expiredCart.checkout();
```
![Screenshot 2025-07-08 212658](https://github.com/user-attachments/assets/9236a435-49c7-491e-a289-a91c55b211f6)

- Create a cart with an out-of-stock product
This should not allow checkout and print an error message
```java
Product outOfStockTV = new TV("OutofStock_TV", 800.0, 0, 15.0);
Cart outOfStockCart = new Cart(customer2);
outOfStockCart.addProduct(outOfStockTV, 1);
outOfStockCart.checkout();
```

![Screenshot 2025-07-08 212701](https://github.com/user-attachments/assets/e985cf0f-e827-41aa-9980-77e686389366)
