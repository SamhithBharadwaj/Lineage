package com.bharadwaj.samhith.lineage.test;

import com.bharadwaj.samhith.lineage.service.LineageProcessor;
import com.bharadwaj.samhith.lineage.test.models.Lineage;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SampleDataGenerator2 {

    public static final int NO_OF_FILES = 20;

    public static DataBuilder builder = new DataBuilder();


    public static void main(String[] args) {
        for (int i = 0; i < NO_OF_FILES; i++) {
            String file = "files/familyTreeGenerated" + i + ".json";
            Lineage lineage=builder.buildLineageObject();
            try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
                prepareDirectoryForExport();
                Gson gson = new Gson();
                String jsonString = gson.toJson(lineage);
                out.write(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static void prepareDirectoryForExport() throws IOException {
        File file = new File(LineageProcessor.INPUT_DIRECTORY);
        if (file.exists()) {
            FileUtils.cleanDirectory(file);
        } else {
            file.mkdir();
        }
    }

    private static JsonGenerator getWriterForFile(String fileName) throws IOException {
        JsonFactory f = new ObjectMapper().getFactory();
        JsonGenerator generator = null;
        File file = createFile(fileName);
        generator = f.createGenerator(file, JsonEncoding.UTF8);
        return generator;

    }

    private static File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }

}
