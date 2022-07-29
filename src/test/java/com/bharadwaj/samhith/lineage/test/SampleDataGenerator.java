package com.bharadwaj.samhith.lineage.test;

import com.bharadwaj.samhith.lineage.test.models.Lineage;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.PrintWriter;

public class SampleDataGenerator {

    public static final int NO_OF_FILES = 20;

    public static DataBuilder builder=new DataBuilder();


    public static void main(String[] args) {
        for (int i = 0; i < NO_OF_FILES; i++) {
            String file = "files/familyTreeGenerated" + i + ".json";
            Lineage lineage=builder.buildLineageObject();
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                Gson gson = new Gson();
                String jsonString = gson.toJson(lineage);
                out.write(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
