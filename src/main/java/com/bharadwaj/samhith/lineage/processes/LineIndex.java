package com.bharadwaj.samhith.lineage.processes;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LineIndex {
    private List<String> lines = new ArrayList<>();
    private int shortestLength = Integer.MAX_VALUE;
    private int longestlength = Integer.MIN_VALUE;
    private Set<Integer> shortestIndeces = new HashSet<>();
    private Set<Integer> longestIndeces = new HashSet<>();

    public void addLine(String line, int count) {
        updateLongest(count);
        updateShortest(count);
        lines.add(line);
    }

    private void updateShortest(int count) {
        if (count < shortestLength) {
            shortestIndeces.clear();
            shortestIndeces.add(lines.size());
            shortestLength=count;
        } else if (count == shortestLength) {
            shortestIndeces.add(lines.size());
        }
    }

    private void updateLongest(int count) {
        if (count > longestlength) {
            longestIndeces.clear();
            longestIndeces.add(lines.size());
            longestlength=count;
        } else if (count == longestlength) {
            longestIndeces.add(lines.size());
        }
    }

    public void writeLines(Writer writer) throws IOException {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (shortestIndeces.contains(i)) {
                line = line + " - shortest";
            }
            if (longestIndeces.contains(i)) {
                line = line + " - longest";
            }
            writer.append(line + "\n");
        }
        writer.append("\n");
    }
}
