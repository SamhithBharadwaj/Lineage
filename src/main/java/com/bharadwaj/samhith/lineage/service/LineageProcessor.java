package com.bharadwaj.samhith.lineage.service;

import com.bharadwaj.samhith.lineage.models.FamilyLineage;

import java.io.File;
import java.util.List;

public class LineageProcessor {
    public FamilyLineageLoader familyLineageLoader = new FamilyLineageLoader();
    public LineageDataProcessor dataProcessor = new LineageDataProcessor();
    public static final String INPUT_DIRECTORY = "";
    public static final String RESULT_DIRECTORY = "";

    public void processFilesInFolder() {
        File directory = new File(INPUT_DIRECTORY);
        for (File file : directory.listFiles()) {
            List<FamilyLineage> lineages = familyLineageLoader.loadLineagesFromFile(file);
            dataProcessor.process(lineages, file.getName());
        }
    }
}
