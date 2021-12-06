package com.inventory;
import java.io.*;
import java.util.*;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class ReadCsv {

    private static HashMap<String, Items> inventoryItems = new HashMap<>();
    private static HashSet<String> cards = new HashSet<>();
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception
    {
        new FileOutputStream("src/main/resources/input/Output.csv").close();
        new FileOutputStream("src/main/resources/input/error.txt").close();
        System.out.println("Your input File is :");
        List<String[]> allRows =readCSVFile("src/main/resources/input/Dataset.csv");
        List<String[]> allCards =readCSVFile("src/main/resources/input/Cards.csv");
        List<String[]> inputs =readCSVFile("Input.csv");

        //Read CSV line by line and use the string array as you want
        for(String[] row : allRows){
            Items item= toItems(Arrays.toString(row));
            if(item!=null)
                inventoryItems.put(item.getItem(), item);

        }
        System.out.println(cards);
        for(String[] card:allCards)
        {
            cards.add(card[0]);

        }
        System.out.println(cards);
        Essentials essentials = new Essentials();
        Luxury luxury = new Luxury();
        Misc misc = new Misc();

        essentials.setNextChain(luxury);
        luxury.setNextChain(misc);

        //validate Input
        Set<String> corrections = new HashSet<>();

        double totalAmount=0;
        Set<String> essentialsSeen = new HashSet<>();
        Set<String> luxurySeen = new HashSet<>();
        Set<String> MissSeen = new HashSet<>();
        for(String[] map : inputs) {
            cards.add(map[2]);

            double amount= essentials.calculate(inventoryItems,map[0],Integer.parseInt(map[1]),corrections,essentialsSeen,luxurySeen,MissSeen);
            totalAmount+=amount;
        }
        if(EssentialTotal.getEssentialCount()<0)
            corrections.addAll(essentialsSeen);

        if(EssentialTotal.luxurySum()<0)
            corrections.addAll(luxurySeen);

        if(EssentialTotal.getMiscSum(quantity)<0)
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
            System.out.println("Please correct quantities");
            File file = new File("src/main/resources/input/error.txt");

            try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {

                // create new BufferedWriter for the output file
                bf.write("Please correct quantities.");
                bf.newLine();

                // iterate map entries
                for ( String input : corrections) {

                    // put key and value separated by a colon

                    bf.write(input);

                    // new line
                    bf.newLine();
                }

                bf.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // always close the writer
        }
    }

    private static List<String[]> readCSVFile(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath), ',', '"', 1);
        return  reader.readAll();

    }

    private static Items toItems(String column) {
        column = column.substring(1,column.length()-1);
        String[] columns = column.split(",");
        if(columns.length<4)
             return null;


        return Items.builder().Category(toValue(columns[0].trim()))
                .item(columns[1].trim())
                .quantity(Integer.valueOf(columns[2].trim()))
                .price(Double.valueOf(columns[3].trim()))
                .build();
    }

    static Category toValue(String category)
    {
        return switch (category) {
            case "Essential" -> Category.ESSENTIALS;
            case "Luxury" -> Category.LUXURY;
            case "Miscellaneous" -> Category.MISCELLENEOUS;
            default -> Category.MISCELLENEOUS;
        };
    }
}

