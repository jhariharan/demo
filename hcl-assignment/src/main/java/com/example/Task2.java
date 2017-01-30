package com.example;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by arvind on 30/12/16.
 */
public class Task2 {

    public static void main(String[] args) {

       // String csvFile = "/Users/arvind/Desktop/assignment/assignment/country-codes.csv";

        ClassLoader classLoader = new Task2().getClass().getClassLoader();
        File csvFile = new File(classLoader.getResource("country-codes.csv").getFile());

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            reader.readNext();
            Map<String, String> map = new HashMap<>();
            while ((line = reader.readNext()) != null) {
               map.put(line[1], line[0]);
            }

            Map<String, String> result = new LinkedHashMap<>();
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
