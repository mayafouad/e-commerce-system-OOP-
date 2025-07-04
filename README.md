

# 🛒 E-Commerce System (OOP)

This project implements a simple object-oriented **e-commerce system** with core functionality around product management, shopping cart behavior, and customer checkout logic.

---

## 📦 Features

* **Product Definition**

  * Each product has a `name`, `price`, and `available quantity`.
  * Products are categorized as:

    * **Perishable Products** (e.g., Cheese, Biscuits) – can expire.
    * **Non-Perishable Products** (e.g., TV, Mobile) – do not expire.
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

If applicable:

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
* Console-based I/O


