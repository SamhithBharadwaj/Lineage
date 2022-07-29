package com.bharadwaj.samhith.lineage.processes;

import com.bharadwaj.samhith.lineage.processes.output.AgeIndexOutputWriter;
import com.bharadwaj.samhith.lineage.processes.output.LineIndexOutputWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class OutputDataWriter {

    private DataCollector collector;

    public OutputDataWriter(DataCollector collector) {
        this.collector = collector;
    }


    public void writeToFile(String fileName) throws IOException {
        Writer writer = null;
        try {
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

}
