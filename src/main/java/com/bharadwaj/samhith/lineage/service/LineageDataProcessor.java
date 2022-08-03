package com.bharadwaj.samhith.lineage.service;

import com.bharadwaj.samhith.lineage.Lineage;
import com.bharadwaj.samhith.lineage.models.FamilyLineage;
import com.bharadwaj.samhith.lineage.processes.DataCollector;
import com.bharadwaj.samhith.lineage.processes.OutputDataWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class LineageDataProcessor {

public static final String OUTPUT_FILE_PATH="output/";
    public void process(FamilyLineage lineage, String fileName) {
        try {
            DataCollector collector = new DataCollector();
            collector.iterateFamilyLineage(lineage);
            OutputDataWriter writer = new OutputDataWriter(collector);
            writer.writeToFile(OUTPUT_FILE_PATH + fileName.replace(".json", ".txt"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
