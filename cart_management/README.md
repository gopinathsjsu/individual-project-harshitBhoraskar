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

1. Clone the repository and navigate to the Inventory folder <br/>
1. Clone the repo and navigate to cart_management <br/>
2. Paste the input file "Input.csv" inside the inventory folder
3. Run the command - mvn compile <br/>
4. Run the command - mvn clean install <br/>
5. Run the command - mvn -X clean install exec:java -Dexec.mainClass="com.inventory.ReadCsv" -Dexec.args="Input.csv"
6. You will be able to see the outputs generated in Inventory\src\main\resources\input
