package com.cart_management;
import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.CSVWriter;


public class ReadCsv {

    private static HashMap<String, ItemInstance> inventoryItems = new HashMap<>();
    private static HashSet<String> cards = new HashSet<>();
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception
    {
        new FileOutputStream("src/main/resources/input/Output.csv").close();
        new FileOutputStream("src/main/resources/input/error.txt").close();

        List<String[]> allRows =fileCSVreader("src/main/resources/input/Dataset.csv");
        List<String[]> allCards =fileCSVreader("src/main/resources/input/Cards.csv");
        List<String[]> inputs =fileCSVreader("Input.csv");

        //Read CSV line by line and use the string array as you want
        for(String[] row : allRows){
            ItemInstance itemInstance= toItems(Arrays.toString(row));
            if(itemInstance!=null)
                inventoryItems.put(itemInstance.getItem(), itemInstance);

        }

        for(String[] card:allCards)
        {
            cards.add(card[0]);

        }

        Essentials essentials = new Essentials();
        Luxury luxury = new Luxury();
        Misc misc = new Misc();

        essentials.setCartCategory(luxury);
        luxury.setCartCategory(misc);

        //validate Input
        Set<String> corrections = new HashSet<>();

        double totalAmount=0;
        Set<String> essentialsSeen = new HashSet<>();
        Set<String> luxurySeen = new HashSet<>();
        Set<String> MissSeen = new HashSet<>();
        for(String[] map : inputs) {
            cards.add(map[2]);

            double amount= essentials.calculateTotal(inventoryItems,map[0],Integer.parseInt(map[1]),corrections,essentialsSeen,luxurySeen,MissSeen);
            totalAmount+=amount;
        }
        if(EssentialTotal.getEssentialCount()<0)
            corrections.addAll(essentialsSeen);

        if(EssentialTotal.luxurySum()<0)
            corrections.addAll(luxurySeen);

        if(EssentialTotal.getMiscSum()<0)
            corrections.addAll(MissSeen);
        //Create record

        List<String[]> updatedCards = new LinkedList<>();
        String[] header= new String[]{"CardNumber"};
        updatedCards.add(header);
        for(String card :cards)
        {
            String[] addCard= new String[]{card};
            updatedCards.add(addCard);
        }


        CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/input/Cards.csv"), ',');
        writer.writeAll(updatedCards);
        writer.flush();
        writer.close();


        if(corrections.size()==0) {
            List<String[]> writting= new LinkedList<>();
            writting.add(new String[]{"Amount deducted"});
            writting.add(new String[]{""+totalAmount});
            writer = new CSVWriter(new FileWriter("src/main/resources/input/Output.csv"), ',');
            writer.writeAll(writting);
            writer.flush();
            writer.close();
        }
        else
        {

            File file = new File("src/main/resources/input/error.txt");

            try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {

                bf.write("Please correct quantities.");
                bf.newLine();

                for ( String input : corrections) {
                    bf.write(input);
                    bf.newLine();
                }

                bf.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static ItemInstance toItems(String entries) {
        entries = entries.substring(1,entries.length()-1);
        String[] entrie = entries.split(",");
        if(entrie.length<4)
            return null;

        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setCategory(entrie[0].trim());
        itemInstance.setItem(entrie[1].trim());
        itemInstance.setQuantity(Integer.valueOf(entrie[2].trim()));
        itemInstance.setPrice(Double.valueOf(entrie[3].trim()));
        return itemInstance;
    }

    private static List<String[]> fileCSVreader(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath), ',', '"', 1);
        return  reader.readAll();

    }


}

