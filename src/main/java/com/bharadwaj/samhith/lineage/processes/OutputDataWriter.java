package com.bharadwaj.samhith.lineage.processes;

import com.bharadwaj.samhith.lineage.processes.output.AgeIndexOutputWriter;
import com.bharadwaj.samhith.lineage.processes.output.LineIndexOutputWriter;
import com.bharadwaj.samhith.lineage.service.LineageDataProcessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OutputDataWriter {

    private DataCollector collector;

    public OutputDataWriter(DataCollector collector) {
        this.collector = collector;
    }


    public void writeToFile(String fileName) throws IOException {
        Writer writer = null;
        try {
            prepareDirectoryForExport();
            writer = getWriterForFile(fileName);
            writeDataFromLineIndex(writer);
            writeDataFromAgeIndex(writer);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void writeDataFromAgeIndex(Writer writer) throws IOException {
        MembersAgeIndex index = collector.getAgeIndex();
        AgeIndexOutputWriter ageIndexOutputWriter = new AgeIndexOutputWriter(index, writer);
        ageIndexOutputWriter.write();
    }

    private void writeDataFromLineIndex(Writer writer) throws IOException {
        LineIndex index = collector.getLineIndex();
        LineIndexOutputWriter lineIndexOutputWriter = new LineIndexOutputWriter(index, writer);
        lineIndexOutputWriter.write();
    }

    private Writer getWriterForFile(String fileName) throws IOException {
        return new BufferedWriter(new FileWriter(fileName, true));

    }

    private void prepareDirectoryForExport() throws IOException {
        File file = new File(LineageDataProcessor.OUTPUT_FILE_PATH);
        if (file.exists()) {
            FileUtils.cleanDirectory(file);
        } else {
            file.mkdir();
        }
    }

}
