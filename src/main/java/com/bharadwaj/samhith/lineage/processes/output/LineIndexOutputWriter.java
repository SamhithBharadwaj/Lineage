package com.bharadwaj.samhith.lineage.processes.output;

import com.bharadwaj.samhith.lineage.processes.LineIndex;

import java.io.IOException;
import java.io.Writer;

public class LineIndexOutputWriter {

    private LineIndex lineIndex;
    private Writer writer;

    public LineIndexOutputWriter(LineIndex lineIndex, Writer writer) {
        this.lineIndex = lineIndex;
        this.writer = writer;
    }

    public void write() throws IOException {
        writer.append("Family lines : \n");
        lineIndex.writeLines(writer);
    }
}
