# ShoppingCartJava

## CMPE 202 Individual Assignment
Name: Harshit Bhoraskar <br/>
SJSU ID: 015218606

## Prerequisities to run the code <br/>
Need the following with their respective path variable:
Java
Maven
JDK


## Steps to run the code

1. Clone the repo and navigate to cart_management <br/>
2. Paste the input file "Input.csv" inside the inventory folder cart_management
3. Open cmd navigate to the project folder ,run the command - mvn compile
4. Then Run the command - mvn clean install
5. After that run - mvn -X clean install exec:java -Dexec.mainClass="com.cart_management.ReadCsv" -Dexec.args="Input.csv"
6. You will be able to see the outputs generated in cart_management\src\main\resources\input
