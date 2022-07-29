package com.bharadwaj.samhith.lineage.service;

import com.bharadwaj.samhith.lineage.models.FamilyLineage;

import java.io.File;

public class LineageProcessorThread implements Runnable {
    private File file;

    public LineageProcessorThread(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println("Staring thread " + Thread.currentThread().getName() + " processing file : " + file.getName());
        FamilyLineageLoader lineageLoader = FamilyLineageLoader.getLineageLoader();
        FamilyLineage lineage = lineageLoader.loadLineageFromFile(file);
        LineageDataProcessor dataProcessor = new LineageDataProcessor();
        dataProcessor.process(lineage, file.getName());
        System.out.println("Processing complete for file :" + file.getName());
    }
}
