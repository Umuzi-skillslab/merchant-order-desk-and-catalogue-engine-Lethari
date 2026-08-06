# Merchant Order Desk and Catalogue Engine

## PayNest Demo
This demo models a simple customer order flow and prints a receipt for the selected products.

## Prerequisites
- Java 21
- Maven Wrapper included
- Plain Java used

## How to run
```bash
cd merchant-order-desk-and-catalogue-engine-Lethari
./mvnw clean compile exec:java -Dexec.mainClass="App.PayNestApplication"
```
This means you do not need a separate Maven installation.

## What this demonstrates
1. Customer and Order domain objects are created.
2. OrderService adds products to the order.
3. ReceiptPrinter prints a formatted order summary to the console.
4. Customers can add multiple items to their basket.

## Design notes
- Monetary values use BigDecimal to avoid floating-point rounding issues.
- The order keeps an internal list of items while exposing an unmodifiable view to callers.
- Product creation is routed through a simple catalog abstraction, which makes it easier to extend the app later.

## Expected output
After running, the terminal should display output similar to:

```text
=================================
 PAYNEST RECEIPT
=================================
Order ID: 101
Customer: Lethabo
---------------------------------
PS5 x1 R15,600.00
Controller x2 R1,700.00
---------------------------------
TOTAL: R17,300.00
=================================
```


