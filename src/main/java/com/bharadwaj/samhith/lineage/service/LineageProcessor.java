package com.bharadwaj.samhith.lineage.service;

import com.bharadwaj.samhith.lineage.models.FamilyLineage;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LineageProcessor {

    public static final String INPUT_DIRECTORY = "files/";
    public static final String RESULT_DIRECTORY = "";

    public void processFilesInFolder(){
        File directory = new File(INPUT_DIRECTORY);
        ExecutorService executorService=getExecutorService();
        for (File file : directory.listFiles()) {
            executorService.execute(new LineageProcessorThread(file));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                //Do nothing
            }
        }
        System.out.println("All threads done");
    }

    private ExecutorService getExecutorService(){
        return Executors.newFixedThreadPool(5);
    }
}
