# Merchant-order-desk-and-catalogue-engine
##PayNest Demo
Demo stimulates creating a customer order and printing a receipt.

###As per prerequisites
- Java 21
- Maven Wrapper included
- Plain Java used

###How to Run
```bash
1. from project root
cd merchant-order-desk-and-catalogue-engine-Lethari

#2. Compile and run
./mvnw clean compile exec:java -Dexec.mainClass="App.PayNestApplication"
Which means viewer does not require Maven installed

#What this demonstrates
1.Customer and Order domain objects created
2.OrderService adds products to the order
3.ReceiptPrinter prints a formated order summary to the console.
4 Customer able to add onto their basket

Expected output
After running, the terminal should display:
Order ID: 101
Customer: Lethabo

PS5 x1  R15 600,00
Controller x2  R1700,00

TOTAL:  R17300,00`


